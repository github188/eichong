/**
 * FileName:BluemobiCommon.java
 * Author: Administrator
 * Create: 2014年6月30日
 * Last Modified: 2014年6月30日
 * Version: V1.0 
 */
package com.bluemobi.product.common;

import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.bluemobi.product.utils.StringUtil;
import com.wanma.app.util.DateUtil;
import com.wanma.common.ApplicationCommon;

/**
 * 公共处理类
 *
 * @version V1.0
 * @author Administrator
 * @date 2014年6月30日
 */
public class BluemobiCommon {

	/** 日志生成器 */
	private static Logger log = Logger.getLogger(BluemobiCommon.class);

	/** 短信发送失败标识 */
	public static String MSG_SEND_RESULT_NG = "001";
	/** 短信发送成功标识 */
	public static String MSG_SEND_RESULT_OK = "002";

	/**
	 * 发送短信(万马项目)
	 *
	 * @param messageContent
	 *            信息内容
	 * @param mobileNumber
	 *            手机号
	 *
	 * @return String 短信发送成功标识 002：成功 001：不成功
	 */
	public static String sendWanMatMessage(String messageContent,
			String mobileNumber) {

		// 短信发送成功标识
		String sendFlg = MSG_SEND_RESULT_OK;
		if (messageContent == null || mobileNumber == null
				|| mobileNumber.trim().length() == 0
				|| messageContent.trim().length() == 0) {
			return MSG_SEND_RESULT_NG;
		}

		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(
				"http://106.ihuyi.cn/webservice/sms.php?method=Submit");

		// client.getParams().setContentCharset("GBK");
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType",
				"application/x-www-form-urlencoded;charset=UTF-8");

		// int mobile_code = (int)((Math.random()*9+1)*100000);

		// System.out.println(mobile);

		// String content = new String("您的验证码是：" + mobile_code +
		// "。请不要把验证码泄露给其他人。");

		NameValuePair[] data = {// 提交短信
				new NameValuePair("account", "cf_acwl"),
				// new NameValuePair("password", "xx2SBt‍"),
				// //密码可以使用明文密码或使用32位MD5加密
				new NameValuePair("password", StringUtil.MD5Encode("xx2SBt")),
				new NameValuePair("mobile", mobileNumber),
				new NameValuePair("content", messageContent), };

		method.setRequestBody(data);

		try {
			client.executeMethod(method);

			String SubmitResult = method.getResponseBodyAsString();

			// System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();

			// 短信平台返回标示，详情看文档
			String code = root.elementText("code");
			log.info("短信返回结果：" + code+"返回成功标示为2，其他返回都标示为失败!");
			/*
			 * String msg = root.elementText("msg"); String smsid =
			 * root.elementText("smsid");
			 */
			// 返回成功标示为2，其他返回都标示为失败
			if (!code.equals("2"))
				sendFlg = MSG_SEND_RESULT_NG;

		} catch (HttpException e) {
			e.printStackTrace();
			sendFlg = MSG_SEND_RESULT_NG;
			log.error(e.getLocalizedMessage());
		} catch (IOException e) {
			e.printStackTrace();
			sendFlg = MSG_SEND_RESULT_NG;
			log.error(e.getLocalizedMessage());
		} catch (DocumentException e) {
			e.printStackTrace();
			sendFlg = MSG_SEND_RESULT_NG;
			log.error(e.getLocalizedMessage());
		}
		// 返回短信发送成功标识
		return sendFlg;

	}

	/**
	 * 生成订单标号
	 * @return String
	 */
	public static String getOrderNo(){
		Random random = new Random();
		return "wm"+DateUtil.currentYourDate("yyyyMMddHHmmssSS")+random.nextInt( 1000 );
	}

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		String content = new String("尊敬的用户：" + cal.get(Calendar.MONTH)  + 1
					+ "月" + cal.get(Calendar.DAY_OF_MONTH) + "日您成功充值" 
					+ "150" + "元，当前您的账户余额为" + "200" + "元。");
		BluemobiCommon.sendWanMatMessage(content, "136758982174");
	}
}
