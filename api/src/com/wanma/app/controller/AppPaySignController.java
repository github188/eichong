package com.wanma.app.controller;

import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.util.PayConfig;
import com.wanma.app.util.RSA;
import com.wanma.app.util.WXUtil;
import com.wanma.app.util.wxhandler.ClientRequestHandler;
import com.wanma.app.util.wxhandler.PrepayIdRequestHandler;

@Controller
@RequestMapping("/app/pay")
public class AppPaySignController {
	private static Logger log = Logger.getLogger(AppPaySignController.class);

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
			String userMobel,String ccode) {
		if (StringUtils.isEmpty(subject) || StringUtils.isEmpty(body)
				|| StringUtils.isEmpty(price) || StringUtils.isEmpty(userMobel)) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}

		
		
				
	if(ccode == null || ccode == "")
	{
		log.info("为空");
	}else{
		body = body+"_"+ccode;
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
				+ "/alipay/notify_url.do"));
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
		
		
		
		 Map map = new HashMap();  
	        Enumeration paramNames = request.getParameterNames();  
	        while (paramNames.hasMoreElements()) {  
	            String paramName = (String) paramNames.nextElement();  
	  
	            String[] paramValues = request.getParameterValues(paramName);  
	            if (paramValues.length == 1) {  
	                String paramValue = paramValues[0];  
	                if (paramValue.length() != 0) {  
	                    map.put(paramName, paramValue);  
	                }  
	            }  
	        }  
	  
	        Set<Map.Entry<String, String>> set = map.entrySet();  
	        System.out.println("------------------------------");  
	        for (Map.Entry entry : set) {  
	            System.out.println(entry.getKey() + ":" + entry.getValue());  
	        }  
	        System.out.println("------------------------------");  
		
		
		String userMobel = request.getParameter("userMobel");
		String body = request.getParameter("body");
		String price = request.getParameter("price");
		String ipAddr = request.getParameter("ipAddr");
		String tradeType = request.getParameter("tradeType");
		String userId = request.getParameter("userId");
		String chargeType = request.getParameter("chargeType");
		String orderId = request.getParameter("orderId");
		//attach
		String attach = "";
		if (StringUtils.isEmpty(userMobel) || StringUtils.isEmpty(body)
				|| StringUtils.isEmpty(price) || StringUtils.isEmpty(ipAddr)
				|| StringUtils.isEmpty(tradeType)
				|| StringUtils.isEmpty(userId)
				|| StringUtils.isEmpty(chargeType)
				|| StringUtils.isEmpty(orderId)) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}

		Map<Object, Object> resInfo = new HashMap<Object, Object>();
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

		/*
		 * StringBuffer xml = new StringBuffer(); xml.append("</xml>");
		 * List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
		 * packageParams.add(new BasicNameValuePair("appid", Constants.APP_ID));
		 * packageParams.add(new BasicNameValuePair("body", "充充币"));
		 * packageParams.add(new BasicNameValuePair("mch_id",
		 * Constants.MCH_ID)); packageParams.add(new
		 * BasicNameValuePair("nonce_str", nonceStr)); packageParams.add(new
		 * BasicNameValuePair("notify_url",
		 * "http://www.eichong.com/alipay/notify_url.do"));
		 * packageParams.add(new
		 * BasicNameValuePair("out_trade_no",genOutTradNo()));
		 * //packageParams.add(new
		 * BasicNameValuePair("spbill_create_ip","127.0.0.1"));
		 * packageParams.add(new
		 * BasicNameValuePair("spbill_create_ip","196.168.1.1"));
		 * packageParams.add(new BasicNameValuePair("total_fee", "1"));
		 * packageParams.add(new BasicNameValuePair("trade_type", "APP"));
		 * String sign = genPackageSign(packageParams); packageParams.add(new
		 * BasicNameValuePair("sign", sign));
		 * 
		 * String xmlstring = toXml(packageParams);
		 */

		String xml_body = "";

		String noncestr = WXUtil.getNonceStr();
		String timestamp = WXUtil.getTimeStamp();
		String traceid = "";
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
		if(request.getParameter("attach") == null || request.getParameter("attach") == "")
		{
			log.info("为空");
		}else{
			log.info("地区不为空");
			attach = request.getParameter("attach");
			prepayReqHandler.setParameter("attach", attach);
			//out_trade_no= out_trade_no+"_"+request.getParameter("attach");
		}
	

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

}
