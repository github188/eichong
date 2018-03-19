/** 
 * FileName AuthCodeService.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */

package com.bluemobi.product.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.dao.AuthCodeMapper;
import com.bluemobi.product.model.AuthCode;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.service.AuthCodeService;
import com.bluemobi.product.utils.DateUtil;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.ObjectUtil;
import com.wanma.common.AliSMS;
import com.wanma.common.ApplicationCommon;

/**
 * FileName AuthCodeService.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/8/9
 * 
 * 验证码业务处理类
 */
@Service
public class AuthCodeServiceImpl implements AuthCodeService {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(AuthCodeServiceImpl.class);

	/** 验证码操作用DAO */
	@Autowired
	AuthCodeMapper authCodeMapper;

	/**
	 * 取得验证码
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param mobileNumber
	 *            手机号
	 * @return String 验证码
	 * @throws 无
	 */
	public String sendAuthCode(String mobileNumber) {
		return sendAuthCode(mobileNumber, null);
	}

	public String sendAuthCode(String mobileNumber, String special) {
		DwzAjaxResult dwzResult = null;
		// 验证码对象
		AuthCode authCodeObject = new AuthCode();
		// 验证码
		String authCode = "";

		// 生成验证码
		authCode = ProcessInfoCommon.getDigitRandomKey(4);
		//
		// 设置验证码信息
		//
		authCodeObject.setAuthCode(authCode);
		authCodeObject.setMobileNumber(mobileNumber);

		// 删除该手机号之前发出的验证码
		authCodeMapper.deleteAuthCode(mobileNumber);
		// 保存验证码
		authCodeMapper.saveAuthCode(authCodeObject);
		/* 由于某些短信平台有自己固定的短信模板，因此此处进行模板适配处理 */
		// 发送验证码
		if (special == null){
//			return ApplicationCommon.sendWanMatMessage(new String("您的验证码：" 
//					+ authCode + "打死都不要告诉别人。如非本人操作，不用理会，走你！"), mobileNumber);
			//阿里大于
			JSONObject parValue = new JSONObject();
			parValue.put("code", authCode.trim());
			if(AliSMS.sendAliSMS(mobileNumber, "SMS_17070054", parValue.toString())){
				dwzResult = new DwzAjaxResult("200", "短信发送成功!", "", "", "");
			}else{
				dwzResult = new DwzAjaxResult("300", "短信发送失败!", "", "", "");
			}
		}else{
			return ApplicationCommon.sendWanMatMessage("短信验证码:" + authCode,
					mobileNumber);
		}
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 检查验证码
	 * 
	 * @author yangweir
	 * @since Version 1.0 发送验证码
	 * 
	 * @param authCode
	 *            验证码
	 * @param mobileNumber
	 *            手机号
	 * @return boolean 验证码确认结果
	 * @throws 无
	 */
	public boolean checkAuthCode(String authCode, String mobileNumber) {
		// 验证码确认结果
		boolean checkAuthCode = true;
		// 验证码对象
		AuthCode authCodeObject = null;
		// 验证码有效时间
		long effectMinutes = 0;
		// 验证码有效时间(字符串)
		String strMinutes = "0";
		// 验证码生成的时间
		Date createDate = null;
		// 系统时间
		Date sysDate = new Date();
		// 时间比较结果
		int compareResult = 0;
		// 数据库保存的验证码
		String sysAuthCode = "";

		// 从系统配置文件中取得验证码有效时间
		strMinutes = MessageManager.getMessageManager().getSystemProperties(
				"auth.code.effective.minutes");
		//
		// 将系统设置的有效时间转换成长整形数据
		//
		try {
			effectMinutes = Long.parseLong(strMinutes);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}

		// 根据手机号取得验证码对象
		authCodeObject = authCodeMapper.getAuthCodeByMobile(mobileNumber);

		// 判断验证码
		if (ObjectUtil.isEmpty(authCodeObject)) {
			// 取不到验证码对象，则为无效验证码
			checkAuthCode = false;
		} else {
			// 验证码生成的时间
			createDate = authCodeObject.getCreateDate();
			// 取得系统中的验证码
			sysAuthCode = authCodeObject.getAuthCode();

			// 为验证码生成时间加上有效时长
			createDate = DateUtil.getAddMinute(createDate, effectMinutes);
			// 比较系统时间和加上有效时长验证码生成的时间
			compareResult = DateUtil.compareDate(sysDate, createDate);

			//
			// 如果系统时间超过有效时间或者验证码不对，则为无效验证码
			//
			if (compareResult > 0 || !StringUtils.equals(sysAuthCode, authCode)) {
				checkAuthCode = false;
			}
		}

		if (checkAuthCode) {
			// 删除该手机号之前发出的验证码
			authCodeMapper.deleteAuthCodeById(authCode);
		}

		// 返回验证码确认结果
		return checkAuthCode;
	}

	/**
	 * 根据交易ID检查验证码
	 * 
	 * @author yangweir
	 * @since Version 1.0 发送验证码
	 * 
	 * @param authCode
	 *            验证码
	 * @param tradeId
	 *            交易ID
	 * @return boolean 验证码确认结果
	 * @throws 无
	 */
	public boolean checkAuthCodeByTradeId(String authCode, String tradeId) {
		// 验证码确认结果
		boolean checkAuthCode = true;
		// 验证码对象
		AuthCode authCodeObject = null;
		// 验证码有效时间
		long effectMinutes = 0;
		// 验证码有效时间(字符串)
		String strMinutes = "0";
		// 验证码生成的时间
		Date createDate = null;
		// 系统时间
		Date sysDate = new Date();
		// 时间比较结果
		int compareResult = 0;
		// 数据库保存的验证码
		String sysAuthCode = "";

		// 从系统配置文件中取得验证码有效时间
		strMinutes = MessageManager.getMessageManager().getSystemProperties(
				"auth.code.effective.minutes");
		//
		// 将系统设置的有效时间转换成长整形数据
		//
		try {
			effectMinutes = Long.parseLong(strMinutes);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}

		// 根据手机号取得验证码对象
		authCodeObject = authCodeMapper.getAuthCodeByTradeId(tradeId);

		// 判断验证码
		if (ObjectUtil.isEmpty(authCodeObject)) {
			// 取不到验证码对象，则为无效验证码
			checkAuthCode = false;
		} else {
			// 验证码生成的时间
			createDate = authCodeObject.getCreateDate();
			// 取得系统中的验证码
			sysAuthCode = authCodeObject.getAuthCode();

			// 为验证码生成时间加上有效时长
			createDate = DateUtil.getAddMinute(createDate, effectMinutes);
			// 比较系统时间和加上有效时长验证码生成的时间
			compareResult = DateUtil.compareDate(sysDate, createDate);

			//
			// 如果系统时间超过有效时间或者验证码不对，则为无效验证码
			//
			if (compareResult > 0 || !StringUtils.equals(sysAuthCode, authCode)) {
				checkAuthCode = false;
			}
		}

		// 返回验证码确认结果
		return checkAuthCode;
	}

	/**
	 * 保存验证码
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param authCode
	 *            验证码对象
	 * @return 无
	 * @throws 无
	 */
	public void saveAuthCode(AuthCode authCode) {
		authCodeMapper.saveAuthCode(authCode);
	}

	/**
	 * 删除验证码
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param mobileNumber
	 *            手机号
	 * @return String 验证码
	 * @throws 无
	 */
	public void deleteAuthCode(String mobileNumber) {
		authCodeMapper.deleteAuthCode(mobileNumber);
	}
}
