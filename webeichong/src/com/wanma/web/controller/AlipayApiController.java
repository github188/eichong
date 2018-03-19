package com.wanma.web.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.common.BluemobiCommon;
import com.wanma.alipay.AlipayConfig;
import com.wanma.alipay.AlipayNotify;
import com.wanma.alipay.AlipaySubmit;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.SessionMgr;
import com.wanma.model.CommitLog;
import com.wanma.model.TblUser;
import com.wanma.web.dao.CmsCommitLogMapper;
import com.wanma.web.service.WebUserinfoService;

/**
 * 支付宝Controller
 * 
 * @author
 * @修改日期 2015-5-25下午1:29:53
 */
@Controller
@RequestMapping(value = "/alipay")
public class AlipayApiController {
	private static Log log = LogFactory.getLog(AlipayApiController.class);
	/**
	 * 用户信息业务处理对象
	 */
	@Autowired
	private WebUserinfoService userinfoService;
	@Autowired
	private CmsCommitLogMapper commitLogDao;

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "frontend/alipay/fund";// 付款的页面。本页面是为了测试而使用的
	}

	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public String deposit(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		// PrintWriter out = response.getWriter();
		String result = "";
		Date date = new Date();
		// 支付类型
		// 必填，不能修改
		String payment_type = "1";
		// 服务器异步通知页面路径
		// 需http://格式的完整路径，不能加?id=123这类自定义参数
		String notify_url = AlipayConfig.NOTIFY_URL;
		// 页面跳转同步通知页面路径
		// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
		String return_url = AlipayConfig.RETURN_URL;
		// 商户订单号.
		// 商户网站订单系统中唯一订单号，必填
		// String out_trade_no = date.getTime() + "";
		// 订单名称
		// 必填
		String subject = AlipayConfig.SUBJECT;
		// 防钓鱼时间戳
		// 若要使用请调用类文件submit中的query_timestamp函数
		String anti_phishing_key = "";// AlipaySubmit.query_timestamp();
		// 客户端的IP地址
		// 非局域网的外网IP地址，如：221.0.0.1
		String exter_invoke_ip = "";
		String total_fee = ServletRequestUtils.getStringParameter(request,
				"amount", "");
		String body = ServletRequestUtils.getStringParameter(request, "body",
				SessionMgr.getUserId(request) + "");
		// 商品展示地址
		String show_url = ServletRequestUtils.getStringParameter(request,
				"show_url", AlipayConfig.SHOW_URL);
		// 需以http://开头的完整路径，例如：http://www.xxx.com/myorder.html

		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");// 接口服务----即时到账
		sParaTemp.put("partner", AlipayConfig.partner);// 支付宝PID
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);// 统一编码
		sParaTemp.put("payment_type", payment_type);// 支付类型
		sParaTemp.put("notify_url", notify_url);// 异步通知页面
		sParaTemp.put("return_url", return_url);// 页面跳转同步通知页面
		sParaTemp.put("seller_email", AlipayConfig.seller_email);// 卖家支付宝账号
		sParaTemp.put("out_trade_no", date.getTime() + payment_type);// 商品订单编号
		sParaTemp.put("subject", subject);// 商品名称
		sParaTemp.put("total_fee", total_fee);// 价格
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

		// 建立请求
		try {
			String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "post",
					"确认");
			String s = JSONObject.toJSONString(sHtmlText);
			model.addAttribute("content", sHtmlText);
			result = "{\"success\":true,\"msg\":\"跳转成功\"}";
			response.getWriter().write(sHtmlText);
			return "frontend/alipay/deposit";
		} catch (Exception e) {
			log.error(e);
			result = "{\"success\":false,\"msg\":\"跳转失败，请稍候再试！\"}";
			model.addAttribute("content", result);
			return "frontend/alipay/deposit";
		}
	}

	/**
	 * 同步通知的页面的Controller
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author
	 */
	@RequestMapping(value = "/return_url")
	public String Return_url(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		log.error("同步阿里requestParams:" + params);
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			log.error("name:" + name + ",valueStr:" + valueStr);
			params.put(name, valueStr);
		}
		String tradeStatus = formatString(request.getParameter("trade_status"));
		// String notifyId = request.getParameter("notify_id");
		boolean verifyFlag = AlipayNotify.verify(params);
		log.error("验证数据，verifyFlag： " + verifyFlag);
		if (tradeStatus.equals("TRADE_FINISHED")) {
			return "frontend/alipay/finish";
		} else if (tradeStatus.equals("TRADE_SUCCESS")) {
			return "frontend/alipay/success";
		} else {
			return "frontend/alipay/fail";
		}
	}

	/**
	 * 异步通知付款状态的Controller
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/notify_url")
	public @ResponseBody String async(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		log.error("异步阿里requestParams:" + params);
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			log.error("异步数据,name:" + name + ",valueStr:" + valueStr);
			params.put(name, valueStr);
		}
		String tradeNo = request.getParameter("out_trade_no");
		String tradeStatus = request.getParameter("trade_status");
		String totalFee = request.getParameter("total_fee");
		// String notifyId = request.getParameter("notify_id");
		// 金额必须是10，20，50，100，200，500中的一个，不然不做数据录入
		if (AlipayNotify.verify(params)) {// 验证成功
			log.error("异步验证成功，tradeStatus: " + tradeStatus);
			boolean flag = false;
			if (tradeStatus.equals("TRADE_FINISHED")
					|| tradeStatus.equals("TRADE_SUCCESS")) {
				flag = userinfoService.addReChargeRecord(request);
			}
			log.error("异步数据插入数据库，flag： " + flag);
			if (flag) {
				return "success";
			} else {
				return "fail";
			}
		} else {// 验证失败
			chargeErrorLog(params, request);
			log.error("异步验证失败  ");
			return "fail";
		}

	}

	/**
	 * @Description: TODO
	 * @author wbc 2016年1月21日
	 * @return: boolean
	 */

	private String formatString(String s) {
		String ss = "";
		try {
			ss = new String(s.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("formatString失败 ", e);
		}
		return ss;

	}

	private boolean validFee(String totalFee) {
		if ("10".equals(totalFee) || "20".equals(totalFee)
				|| "50".equals(totalFee) || "100".equals(totalFee)
				|| "200".equals(totalFee) || "500".equals(totalFee)) {
			return true;
		}
		return false;
	}

	// 充值异常日志记录
	private void chargeErrorLog(Map<String, String> params,
			HttpServletRequest request) {
		CommitLog commitLog = new CommitLog();
		commitLog.setLogName("支付宝");
		commitLog.setLogContent(request.getParameter("total_fee") );
		String[] extArray = request.getParameter("body").split("_");
		String userId=extArray[0];
		commitLog.setCoLoUserId(userId);
		userinfoService.chargeErrorLog(commitLog, request);
	}

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}