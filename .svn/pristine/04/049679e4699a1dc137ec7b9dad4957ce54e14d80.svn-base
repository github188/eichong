package com.wanma.app.controller;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.service.GovBusiPayService;
import com.wanma.app.util.AlipayNotify;
import com.wanma.app.util.PayConfig;
import com.wanma.app.util.RSA;
import com.wanma.app.util.WXUtil;
import com.wanma.app.util.wxhandler.ClientRequestHandler;
import com.wanma.app.util.wxhandler.PrepayIdRequestHandler;

@Controller
@RequestMapping("/gov/pay")
public class GovBusiPayController {
	private static Logger log = Logger.getLogger(GovBusiPayController.class);
	@Autowired
	private GovBusiPayService govBusiPayService;

	/**
	 * 获取支付宝的sigin值
	 * 
	 * @param subject
	 *            标题
	 * @param body
	 *            用户主键
	 * @param price
	 *            价格
	 * @param userMobel
	 *            用户手机号
	 * @return
	 */
	@RequestMapping("/aliSign")
	@ResponseBody
	public String getAliSign(String subject, String body, String price,
			String userMobel) {
		if (StringUtils.isEmpty(subject) || StringUtils.isEmpty(body)
				|| StringUtils.isEmpty(price) || StringUtils.isEmpty(userMobel)) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}

		StringBuilder sb = new StringBuilder();
		sb.append("partner=\"");
		sb.append(PayConfig.ALI_PARTNER);
		sb.append("\"&out_trade_no=\"");
		sb.append(getOutTradeNo(userMobel));
		sb.append("\"&subject=\"");
		sb.append(subject);
		sb.append("\"&body=\"");
		sb.append(body);
		sb.append("\"&total_fee=\"");
		sb.append(price);
		sb.append("\"&notify_url=\"");

		// 网址需要做URL编码
		// sb.append(URLEncoder.encode("http://notify.java.jpxx.org/index.jsp"));
		// sb.append(URLEncoder.encode(Constant.URL+"ddk/pay/doAlipay"));
		MessageManager mmg = new MessageManager();
		sb.append(URLEncoder.encode(mmg.getSystemProperties("pay.notify")
				+ "/gov/pay/notify_url.do"));
		sb.append("\"&service=\"mobile.securitypay.pay");
		sb.append("\"&_input_charset=\"UTF-8");
		sb.append("\"&return_url=\"");
		sb.append(URLEncoder.encode("http://m.alipay.com"));
		sb.append("\"&payment_type=\"1");
		sb.append("\"&seller_id=\"");
		sb.append(PayConfig.ALI_SELLER);

		// 如果show_url值为空，可不传
		sb.append("\"&it_b_pay=\"30m");
		sb.append("\"");
		String info = sb.toString();
		String sign = RSA.sign(info, PayConfig.ALI_RSA_PRIVATE, "utf-8");
		sign = URLEncoder.encode(sign);
		info += "&sign=\"" + sign + "\"&" + getAliSignType();

		return new AccessSuccessResult(info).toString();
	}

	private String getAliSignType() {
		return "sign_type=\"RSA\"";
	}

	public String getOutTradeNo(String userMobel) {
		Date date = new Date();
		String key = userMobel + date.getTime();
		return key;
	}

	@RequestMapping("/wxTempOrder")
	@ResponseBody
	public String wxTempOrder(HttpServletRequest request,
			HttpServletResponse response) {
		String userMobel = request.getParameter("userMobel");
		String body = request.getParameter("body");
		String price = request.getParameter("price");
		String ipAddr = request.getParameter("ipAddr");
		String tradeType = request.getParameter("tradeType");
		String userId = request.getParameter("userId");
		String chargeType = request.getParameter("chargeType");
		String orderId = request.getParameter("orderId");
		String attach = request.getParameter("attach");
		if (StringUtils.isEmpty(userMobel) || StringUtils.isEmpty(body)
				|| StringUtils.isEmpty(price) || StringUtils.isEmpty(ipAddr)
				|| StringUtils.isEmpty(tradeType)
				|| StringUtils.isEmpty(userId)
				|| StringUtils.isEmpty(chargeType)
				|| StringUtils.isEmpty(orderId)
				|| StringUtils.isEmpty(attach)) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}

		// 接收财付通通知的URL
		MessageManager mmg = new MessageManager();
		String notify_url = mmg.getSystemProperties("pay.notify")
				+ "/tenpay/notify.jsp";// do";
		// String notify_url = "http://www.eichong.com/tenpay/notify.jsp";
		Date date = new Date();
		// 订单号，此处用时间加用户id生成，商户根据自己情况调整，只要保持全局唯一就行
		String out_trade_no = date.getTime() + "ech" + userId + "_"
				+ chargeType + "_" + orderId;

		PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(
				request, response);// 获取prepayid的请求类
		ClientRequestHandler clientHandler = new ClientRequestHandler(request,
				response);// 返回客户端支付参数的请求类


		String xml_body = "";

		String noncestr = WXUtil.getNonceStr();
		String timestamp = WXUtil.getTimeStamp();
		// //设置获取prepayid支付参数
		prepayReqHandler.setParameter("appid", PayConfig.WX_APP_ID);
		prepayReqHandler.setParameter("mch_id", PayConfig.WX_PARTNER);
		// prepayReqHandler.setParameter("appkey", PayConfig.WX_APP_KEY);
		prepayReqHandler.setParameter("nonce_str", noncestr);
		prepayReqHandler.setParameter("body", body);
		prepayReqHandler.setParameter("out_trade_no", out_trade_no);
		prepayReqHandler.setParameter("total_fee", price);
		prepayReqHandler.setParameter("spbill_create_ip", ipAddr);
		prepayReqHandler.setParameter("notify_url", notify_url);
		prepayReqHandler.setParameter("trade_type", tradeType);
		prepayReqHandler.setParameter("attach", attach);

		// 生成获取预支付签名
		String sign = prepayReqHandler.createSHA1Sign();
		// 增加非参与签名的额外参数
		prepayReqHandler.setParameter("sign", sign);

		// prepayReqHandler.setParameter("sign_method",PayConfig.WX_SIGN_METHOD);
		prepayReqHandler.setGateUrl(PayConfig.WX_GATEURL);

		// 提交预支付后，获取prepayId
		String prepayid = "";
		try {
			prepayid = prepayReqHandler.sendPrepay();
		} catch (DocumentException e) {
			log.error(e.getMessage());
			return new AccessErrorResult(1001, "error.msg.invalid.parameter")
					.toString();
		}

		// 吐回给客户端的参数
		if (null != prepayid && !"".equals(prepayid)) {

			// 输出参数列表
			clientHandler.setParameter("appid", PayConfig.WX_APP_ID);
			// clientHandler.setParameter("appkey", PayConfig.WX_APP_KEY);
			clientHandler.setParameter("noncestr", noncestr);
			// clientHandler.setParameter("package", "Sign=" + packageValue);
			clientHandler.setParameter("package", "Sign=WXPay");
			clientHandler.setParameter("partnerid", PayConfig.WX_PARTNER);
			clientHandler.setParameter("prepayid", prepayid);
			clientHandler.setParameter("timestamp", timestamp);
			sign = clientHandler.createSHA1Sign();
			clientHandler.setParameter("sign", sign);

			xml_body = clientHandler.getXmlBody();

			return new AccessSuccessResult(xml_body).toString();
		} else {

			return new AccessErrorResult(1061, "error.wx.msg.empty.prepayid")
					.toString();
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
		
		if (AlipayNotify.verify(params)) {// 验证成功
			log.error("异步验证成功，tradeStatus: " + tradeStatus);
			boolean flag = false;
			if (tradeStatus.equals("TRADE_FINISHED")
					|| tradeStatus.equals("TRADE_SUCCESS")) {
				flag = govBusiPayService.addReChargeRecord(request);
			}
			log.error("异步数据插入数据库，flag： " + flag);
			if (flag) {
				return "success";
			} else {
				return "fail";
			}
		} else {// 验证失败
			log.error("异步验证失败 ,充值失败,订单号： "+tradeNo);
			return "fail";
		}

	}

}
