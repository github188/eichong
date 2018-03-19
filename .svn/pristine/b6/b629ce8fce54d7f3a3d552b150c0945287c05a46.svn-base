package com.wanma.web.support.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.bluemobi.product.utils.StringUtil;

public class HttpRequest {
	private static Logger log = Logger.getLogger(HttpRequest.class);

	public static String post(String url, Map<String, String> params) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		log.info("create httppost:" + url);
		HttpPost post = postForm(url, params);
		post.setHeader("ContentType",
				"application/x-www-form-urlencoded;charset=UTF-8");

		body = invoke(httpclient, post);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	public static String get(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		log.info("create httppost:" + url);
		HttpGet get = new HttpGet(url);
		body = invoke(httpclient, get);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	private static String invoke(DefaultHttpClient httpclient,
			HttpUriRequest httpost) {

		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);

		return body;
	}

	private static String paseResponse(HttpResponse response) {
		log.info("get response from http server..");
		HttpEntity entity = response.getEntity();

		log.info("response status: " + response.getStatusLine());
		String charset = EntityUtils.getContentCharSet(entity);
		log.info(charset);

		String body = null;
		try {
			body = EntityUtils.toString(entity);
			log.info(body);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return body;
	}

	private static HttpResponse sendRequest(DefaultHttpClient httpclient,
			HttpUriRequest httpost) {
		log.info("execute post...");
		HttpResponse response = null;

		try {
			response = httpclient.execute(httpost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private static HttpPost postForm(String url, Map<String, String> params) {

		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}

		try {
			log.info("set utf-8 form entity to httppost");
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return httpost;
	}

	public static void main2(String[] args) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "1");
		params.put("password", "2");
		params.put("t", "NTA3Mkhma1JRSjA5NzI3NDgyMzA1ODc3ODAx");
		params.put("pkElectricpilehead", "1881");
		params.put("pkElectricPile", "13370");

		String xml = HttpRequest.post(
				"http://127.0.0.1/api/app/bespoke/selectBespoke.do", params);
		System.out.println(xml);
	}

	public static void main(String[] args) {
		String url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
		Map<String, String> params = new HashMap<String, String>();
		params.put("account", "cf_acwl");
		params.put("password", StringUtil.MD5Encode("xx2SBt"));
		params.put("mobile", "18057138912");
		params.put("content", "您的验证码是：510432。请不要把验证码泄露给其他人。");
		String SubmitResult = HttpRequest.post(url, params);
		// System.out.println(SubmitResult);
		try {
			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();
			// 短信平台返回标示，详情看文档
			String code = root.elementText("code");
			log.info("短信返回结果：" + code + "返回成功标示为2，其他返回都标示为失败!");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
