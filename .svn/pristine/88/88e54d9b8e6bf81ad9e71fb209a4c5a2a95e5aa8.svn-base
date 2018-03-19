/** 
 * FileName AuthCodeService.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */

package com.bluemobi.product.service;

import com.bluemobi.product.model.AuthCode;

/**
 * FileName AuthCodeService.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/8/9
 * 
 * 验证码业务处理接口
 */
public interface AuthCodeService {

	/**
	 * 发送验证码
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param mobileNumber
	 *            手机号
	 * @return String 验证码
	 * @throws 无
	 */
	public String sendAuthCode(String mobileNumber);

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
	 * 
	 * @return boolean 验证码确认结果
	 * @throws 无
	 */
	public boolean checkAuthCode(String authCode, String mobileNumber);

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
	public boolean checkAuthCodeByTradeId(String authCode, String tradeId);

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
	public void saveAuthCode(AuthCode authCode);

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
	public void deleteAuthCode(String mobileNumber);
}
