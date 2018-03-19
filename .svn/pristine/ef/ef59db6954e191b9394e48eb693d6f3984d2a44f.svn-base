package com.wanma.controller.wechat.utils;

import java.io.StringReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.DocumentException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.wanma.support.utils.MD5Util;




public class PrepayIdRequestHandler extends RequestHandler {

	public PrepayIdRequestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
	}

	/**
	 * 创建签名SHA1
	 * 
	 * @param signParams
	 * @return
	 * @throws Exception
	 */
	public String createSHA1Sign() {
		StringBuffer sb = new StringBuffer();
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + v + "&");
		}
		sb.append("key=");
		sb.append(PayConfig.WX_PARTNER_KEY);
		String params = sb.toString();
		String appsign = MD5Util.MD5Encode(params,"utf-8").toUpperCase();//SHA1Util.strTOSHA(params);
		//this.setDebugInfo(this.getDebugInfo() + "\r\n" + "sha1 sb:" + params);
		//this.setDebugInfo(this.getDebugInfo() + "\r\n" + "app sign:" + appsign);
		return appsign;
	}

	// 提交预支付
	public String sendPrepay() throws DocumentException {
		String prepayid = "";
		StringBuffer sb = new StringBuffer("<xml>");
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v)) {
				sb.append("<" + k + ">");
				sb.append(v);
				sb.append("</" + k + ">");
				//sb.append("\"" + k + "\":\"" + v + "\",");
			}
		}
		String params = sb.append("</xml>").toString();
		//params += "</xml>";

		String requestUrl = PayConfig.WX_GATEURL;//super.getGateUrl();
		
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setReqContent(requestUrl);
		String resContent = "";
		
		if (httpClient.callHttpPost(requestUrl, params)) {
			resContent = httpClient.getResContent();
			if (resContent.indexOf("prepay_id") >= 0) {
				org.dom4j.Document document=org.dom4j.DocumentHelper.parseText(resContent);
				org.dom4j.Element root=document.getRootElement();
				prepayid=root.elementText("prepay_id");
				//prepayid = parsePrepayId(resContent);//JsonUtil.getJsonValue(resContent, "prepayid");
			}
		}
		return prepayid;
	}
	
	/*public void sendRefund() throws DocumentException {
	//	String prepayid = "";
		StringBuffer sb = new StringBuffer("<xml>");
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v)) {
				sb.append("<" + k + ">");
				sb.append(v);
				sb.append("</" + k + ">");
				//sb.append("\"" + k + "\":\"" + v + "\",");
			}
		}
		String params = sb.append("</xml>").toString();
		//params += "</xml>";

		String requestUrl = PayConfig.WX_REFUNDURL;//super.getGateUrl();
		
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setReqContent(requestUrl);
		String resContent = "";
		
		if (httpClient.callHttpPost(requestUrl, params)) {
			resContent = httpClient.getResContent();
			if (resContent.indexOf("prepay_id") >= 0) {
				org.dom4j.Document document=org.dom4j.DocumentHelper.parseText(resContent);
				org.dom4j.Element root=document.getRootElement();
				prepayid=root.elementText("prepay_id");
				//prepayid = parsePrepayId(resContent);//JsonUtil.getJsonValue(resContent, "prepayid");
			}
		}
		
	}*/
	

	public static String parsePrepayId(String protocolXML) {   
        String prepayId = "";
        try {   
             DocumentBuilderFactory factory = DocumentBuilderFactory   
                     .newInstance();   
             DocumentBuilder builder = factory.newDocumentBuilder();   
             Document doc = builder   
                     .parse(new InputSource(new StringReader(protocolXML)));   
  
             Element root = doc.getDocumentElement();   
             NodeList books = root.getChildNodes();   
            if (books != null) {   
                for (int i = 0; i < books.getLength(); i++) {   
                     Node book = books.item(i);  
                     if("prepay_id".equals(book.getNodeName())){
                    	 prepayId = book.getNodeValue();
                    	 break;
                     }
                 }   
             }   
         } catch (Exception e) {   
             e.printStackTrace();   
         }  
        
        return prepayId;
     }   
	
	// 判断access_token是否失效
	public String sendAccessToken() {
		String accesstoken = "";
		StringBuffer sb = new StringBuffer("{");
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"appkey".equals(k)) {
				sb.append("\"" + k + "\":\"" + v + "\",");
			}
		}
		String params = sb.substring(0, sb.lastIndexOf(","));
		params += "}";

		String requestUrl = super.getGateUrl();
//		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "requestUrl:"
//				+ requestUrl);
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setReqContent(requestUrl);
		String resContent = "";
//		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "post data:" + params);
		if (httpClient.callHttpPost(requestUrl, params)) {
			resContent = httpClient.getResContent();
			if (2 == resContent.indexOf("errcode")) {
				accesstoken = resContent.substring(11, 16);//获取对应的errcode的值
			}
		}
		return accesstoken;
	}
}
