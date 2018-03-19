/** 
 * FileName ProcessInfoCommon.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */
package com.bluemobi.product.common;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.bluemobi.product.model.UserModel;
import com.bluemobi.product.model.common.BasicModel;
import com.bluemobi.product.utils.HttpServletRequestUtil;
import com.bluemobi.product.utils.StringUtil;

/**
 * FileName ProcessInfoCommon.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 数据库新增/更新者用户信息设置共有
 */
public class ProcessInfoCommon {

	/**
	 * 设置更新者用户信息
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param commonModel
	 *            设置对象
	 * @param request
	 *            画面请求信息
	 * @return 无
	 * @throws 无
	 */
	public static void setUpdateUserInfo(BasicModel commonModel,
			HttpServletRequest request) {

		// 登录用户信息
		UserModel loginUser;
		// 登录用户Id（默认为guest）
		String userLogin = "guest";

		// 如果设置对象为空，不执行设置处理
		if (commonModel == null) {
			return;
		}

		// 取得登录用户信息
		loginUser = (UserModel) request.getSession().getAttribute(
				WebConst.SESS_PRINCIPAL);

		// 如果设置对象不为空
		if (loginUser != null) {
			// 取得登录用户Id
			userLogin = loginUser.getUserId();
		}

		// 设置最后更新用户ID为登录用户Id
		commonModel.setLastUpdateUser(userLogin);
	}

	/**
	 * 设置更新者用户信息
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param commonModel
	 *            设置对象
	 * @return 无
	 * @throws 无
	 */
	public static void setUpdateUserInfo(BasicModel commonModel) {

		HttpServletRequest request = HttpServletRequestUtil.getHttpRequest();

		// 登录用户信息
		UserModel loginUser;
		// 登录用户Id（默认为guest）
		String userLogin = "guest";

		// 如果设置对象为空，不执行设置处理
		if (commonModel == null) {
			return;
		}

		// 取得登录用户信息
		loginUser = (UserModel) request.getSession().getAttribute(
				WebConst.SESS_PRINCIPAL);

		// 如果设置对象不为空
		if (loginUser != null) {
			// 取得登录用户Id
			userLogin = loginUser.getUserId();
		}

		// 设置最后更新用户ID为登录用户Id
		commonModel.setLastUpdateUser(userLogin);
	}

	/**
	 * 清空处理者用户信息
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param commonModel
	 *            设置对象
	 * @return 无
	 * @throws 无
	 */
	public static void clearProcessUser(BasicModel commonModel) {

		// 清空创建用户ID
		commonModel.setCreateUser(null);
		// 清空创建时间
		commonModel.setCreateDate(null);
		// 清空最后更新用户ID
		commonModel.setLastUpdateUser(null);
		// 清空最后更新时间
		commonModel.setLastUpdateDate(null);
	}

	/**
	 * 设置创建者用户信息
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param commonModel
	 *            设置对象
	 * @param request
	 *            画面请求信息
	 * @return 无
	 * @throws 无
	 */
	public static void setCreateUserInfo(BasicModel commonModel,
			HttpServletRequest request) {

		// 登录用户信息
		UserModel loginUser;
		// 登录用户Id（默认为guest）
		String userLogin = "guest";

		// 如果设置对象为空，不执行设置处理
		if (commonModel == null) {
			return;
		}

		// 取得登录用户信息
		loginUser = (UserModel) request.getSession().getAttribute(
				WebConst.SESS_PRINCIPAL);

		// 如果设置对象不为空
		if (loginUser != null) {
			// 取得登录用户Id
			userLogin = loginUser.getUserId();
		}

		// 设置创建用户ID为登录用户Id
		commonModel.setCreateUser(userLogin);
		// 设置最后更新用户ID为登录用户Id
		commonModel.setLastUpdateUser(userLogin);
	}

	/**
	 * 设置创建者用户信息
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param commonModel
	 *            设置对象
	 * @param request
	 *            画面请求信息
	 * @return 无
	 * @throws 无
	 */
	public static void setCreateUserInfo(BasicModel commonModel) {

		HttpServletRequest request = HttpServletRequestUtil.getHttpRequest();

		// 登录用户信息
		UserModel loginUser;
		// 登录用户Id（默认为guest）
		String userLogin = "guest";

		// 如果设置对象为空，不执行设置处理
		if (commonModel == null) {
			return;
		}

		// 取得登录用户信息
		loginUser = (UserModel) request.getSession().getAttribute(
				WebConst.SESS_PRINCIPAL);

		// 如果设置对象不为空
		if (loginUser != null) {
			// 取得登录用户Id
			userLogin = loginUser.getUserId();
		}

		// 设置创建用户ID为登录用户Id
		commonModel.setCreateUser(userLogin);
		// 设置最后更新用户ID为登录用户Id
		commonModel.setLastUpdateUser(userLogin);
	}

	/**
	 * 设置创建者用户信息
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param commonModel
	 *            设置对象
	 * @param request
	 *            画面请求信息
	 * @return 无
	 * @throws 无
	 */
	public static void setDefaultPassword(UserModel userModel) {

		// 用户默认密码（默认为"123456"）
		String defaultPassword = CommonConsts.DEFAULT_PASSWORD_STRING;
		// 系统配置文件中设置的默认密码
		String settingPassword = "";

		// 系统配置文件信息
		Map<String, String> propertiesMap = null;

		// 取得系统配置文件信息
		propertiesMap = MessageManager.getMessageManager()
				.getSystemProperties();

		// 从系统配置文件中取得设置的默认密码
		settingPassword = propertiesMap
				.get(CommonConsts.MAP_KEY_DEFAULT_USER_PASSWORD);

		// 如果系统配置文件设置了默认密码，以系统设置的默认密码优先
		if (StringUtil.isNotEmpty(settingPassword)) {
			defaultPassword = settingPassword;
		}

		// 设置创建用户ID为登录用户Id
		userModel.setUserPassword(defaultPassword);
	}

	/**
	 * 取得随机数（默认10位）
	 * 
	 * @return 随机数
	 */
	public static String getRandomKey() {
		return getRandomKey(10);
	}

	/**
	 * 根据指定位数取得随机数
	 * 
	 * @param length指定位数
	 * @return 随机数
	 */
	public static String getRandomKey(int length) {
		Random randGen = null;
		char[] numbersAndLetters = null;
		if (length < 1) {
			return null;
		}

		randGen = new Random();
		numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
				+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	/**
	 * 根据指定位数取得随机数
	 * 
	 * @param length指定位数
	 * @return 随机数
	 */
	public static String getDigitRandomKey(int length) {
		Random randGen = null;
		char[] numbersAndLetters = null;
		if (length < 1) {
			return null;
		}

		randGen = new Random();
		numbersAndLetters = ("0123456789").toCharArray();

		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];
		}
		return new String(randBuffer);
	}

	public static void main(String args[]) {
		System.out.println(ProcessInfoCommon.getDigitRandomKey(6));
	}
}
