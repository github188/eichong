/**
 * FileName:BluemobiCommon.java
 * Author: Administrator
 * Create: 2014年6月30日
 * Last Modified: 2014年6月30日
 * Version: V1.0 
 */
package com.base.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.base.util.DateUtil;
import com.base.util.MD5Util;
import com.esms.MessageData;
import com.esms.PostMsg;
import com.esms.common.entity.Account;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;

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
	 * 发送短信(玄武)
	 *
	 * @param messageContent
	 *            信息内容
	 * @param mobileNumber
	 *            手机号
	 *
	 * @return String 短信发送成功标识 003：成功 004：不成功
	 */
	public static String sendShortMessage(String messageContent,
			String mobileNumber) {

		// 短信发送成功标识
		String sendFlg = MSG_SEND_RESULT_OK;
		// 平台返回发送结果
		int resultCode = 0;

		if (messageContent == null || mobileNumber == null
				|| mobileNumber.trim().length() == 0
				|| messageContent.trim().length() == 0) {
			return MSG_SEND_RESULT_NG;
		}

		try {
			Account ac = new Account("", "");// 用户名密码
			PostMsg pm = new PostMsg();
			pm.getCmHost().setHost("211.147.239.62", 8450);// 设置网关的IP和port，用于发送信息
			pm.getWsHost().setHost("211.147.239.62", 8460);// 设置网关的
															// IP和port，用于获取账号信息、上行、状态报告等等

			UUID batchId = UUID.randomUUID();
			MTPack pack = new MTPack();
			pack.setBatchID(batchId);
			pack.setBatchName(batchId.toString());
			pack.setMsgType(MTPack.MsgType.SMS);
			pack.setBizType(0);
			pack.setDistinctFlag(false);
			ArrayList<MessageData> msgs = new ArrayList<MessageData>();

			pack.setSendType(MTPack.SendType.MASS);
			msgs.add(new MessageData(mobileNumber, messageContent));
			pack.setMsgs(msgs);

			GsmsResponse resp = pm.post(ac, pack);
			// 取得平台返回结果
			resultCode = resp.getResult();

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(batchId);
			stringBuilder.append(":");
			stringBuilder.append(mobileNumber);
			stringBuilder.append(":");
			stringBuilder.append(messageContent);
			stringBuilder.append(":");
			stringBuilder.append(resp);

			if (resultCode < 0) {
				sendFlg = MSG_SEND_RESULT_NG;
			}

			log.info(stringBuilder);

		} catch (Exception e) {
			e.printStackTrace();
			sendFlg = MSG_SEND_RESULT_NG;
			log.error(e.getLocalizedMessage());
		}

		// 返回短信发送成功标识
		return sendFlg;

	}

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
			
//		HttpClient client = new HttpClient();
//		PostMethod method = new PostMethod(
//				"http://106.ihuyi.cn/webservice/sms.php?method=Submit");
//
//		// client.getParams().setContentCharset("GBK");
//		client.getParams().setContentCharset("UTF-8");
//		method.setRequestHeader("ContentType",
//				"application/x-www-form-urlencoded;charset=UTF-8");
//
//		// int mobile_code = (int)((Math.random()*9+1)*100000);
//
//		// System.out.println(mobile);
//
//		// String content = new String("您的验证码是：" + mobile_code +
//		// "。请不要把验证码泄露给其他人。");
//
//		NameValuePair[] data = {// 提交短信
//				new NameValuePair("account", "cf_acwl"),
//				// new NameValuePair("password", "xx2SBt‍"),
//				// //密码可以使用明文密码或使用32位MD5加密
//				new NameValuePair("password", StringUtil.MD5Encode("xx2SBt")),
//				new NameValuePair("mobile", mobileNumber),
//				new NameValuePair("content", messageContent), };
//
//		method.setRequestBody(data);
		
		
		try {
			/*client.executeMethod(method);
			String SubmitResult = method.getResponseBodyAsString();*/
			String url="http://106.ihuyi.cn/webservice/sms.php?method=Submit";
			Map<String, String> params = new HashMap<String, String>();
			params.put("account", "cf_acwl");
			params.put("password", MD5Util.Md5("xx2SBt"));
			params.put("mobile", mobileNumber);
			params.put("content", messageContent);
			String SubmitResult=HttpRequest.post(url, params);
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

		} catch (Exception e) {
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
		return "wm"+DateUtil.getNow("yyyyMMddHHmmssSS")+random.nextInt( 1000 );
	}

	public static void main(String[] args) {
		String content = new String("您的验证码是：" + 123213 + "。请不要把验证码泄露给其他人。");
		BluemobiCommon.sendWanMatMessage(content, "18066722251");
	}
}
