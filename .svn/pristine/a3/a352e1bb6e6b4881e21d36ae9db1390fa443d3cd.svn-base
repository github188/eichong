package com.wanma.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wanma.dao.TblUserMapper;
import com.wanma.model.TblUser;
import com.wanma.service.GreenLandUserService;
import com.wanma.support.utils.HttpRequest;
import com.wanma.support.utils.MD5Util;
import com.wanma.support.utils.StringUtil;

/**
 * FileName UserServiceImpl.java
 * 
 * Version 1.0
 * 
 * Create by lhy 2015/11/20
 * 
 * 用户业务处理类
 */
@Service
public class GreenLandUserServiceImpl implements GreenLandUserService {
	static Logger log = Logger.getLogger(GreenLandUserServiceImpl.class);

	/** 用户表操作用DAO */
	@Autowired
	private TblUserMapper tblUserMapper;

	/** 短信发送失败标识 */
	public static String MSG_SEND_RESULT_NG = "001";
	/** 短信发送成功标识 */
	public static String MSG_SEND_RESULT_OK = "002";

	/**
	 * 添加用户
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param UserModel
	 *            用户信息
	 * @return 无
	 * @throws 无
	 */
	private void insertUser(TblUser user) throws Exception {
		try {
			// 调用DAO执行用户添加处理
			tblUserMapper.insert(user);
			tblUserMapper.insertNormalUser(user);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 根据账户查询用户信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param UserModel
	 *            用户信息
	 * @return 无
	 * @throws 无
	 */
	private TblUser getByAccount(String userAccount) throws Exception {
		return tblUserMapper.getGreenLangUserByAccount(userAccount);
	}

	/**
	 * 组装用户信息
	 * 
	 * @param jsonData
	 * @return
	 */
	private TblUser json2User(JSONObject jsonData) {
		TblUser user = new TblUser();
		JSONObject userRoomData = (JSONObject) jsonData.get("RoomData");
		user.setUserAccount(jsonData.get("MobileNo").toString());
		user.setUserLevel(6);
		user.setUserPassword(MD5Util.Md5("000000"));
		user.setNormSex((Integer) jsonData.get("Gender"));
		user.setNormRealName(jsonData.get("Realname").toString());
		user.setNormName(jsonData.get("NickName").toString());
		user.setNormBirthday(jsonData.get("Birthday").toString());
		user.setUserImage(jsonData.get("HeadLogo").toString());
		user.setNormAddress(userRoomData.getString("Province")
				+ userRoomData.getString("City")
				+ userRoomData.getString("Area")
				+ userRoomData.getString("Street")
				+ userRoomData.getString("Address"));
		return user;
	}

	@Override
	public void dealWithUserData(String jsonStr)throws Exception {
		JSONObject jsonData = (JSONObject) JSONObject.parseObject(jsonStr).get(
				"Data");
		try {
			String userAccount = jsonData.get("MobileNo").toString();
			TblUser user = getByAccount(userAccount);
			if (user == null) {
				insertUser(json2User(jsonData));
				sendWanMatMessage("您的用户信息保存成功,初始密码为:000000,请尽快修改",
						userAccount);
			} else {
				System.out.println(user.getUserId());
			}

		} catch (Exception e) {
			throw e;
		}
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
	private static String sendWanMatMessage(String messageContent,
			String mobileNumber) {

		// 短信发送成功标识
		String sendFlg = MSG_SEND_RESULT_OK;
		if (messageContent == null || mobileNumber == null
				|| mobileNumber.trim().length() == 0
				|| messageContent.trim().length() == 0) {
			return MSG_SEND_RESULT_NG;
		}
		try {
			String url="http://106.ihuyi.cn/webservice/sms.php?method=Submit";
			Map<String, String> params = new HashMap<String, String>();
			params.put("account", "cf_acwl");
			params.put("password", StringUtil.MD5Encode("xx2SBt"));
			params.put("mobile", mobileNumber);
			params.put("content", messageContent);
			String SubmitResult=HttpRequest.post(url, params);

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();

			// 短信平台返回标示，详情看文档
			String code = root.elementText("code");
			log.info("短信返回结果：" + code+"返回成功标示为2，其他返回都标示为失败!");
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
}
