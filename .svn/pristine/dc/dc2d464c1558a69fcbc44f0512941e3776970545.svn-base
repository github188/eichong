/** 
 * FileName AuthCodeMapper.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */

package com.bluemobi.product.dao;

import com.bluemobi.product.model.AuthCode;

/**
 * FileName AuthCodeMapper.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 验证码表操作用DAO接口Mapper
 */
public interface AuthCodeMapper {

	/**
	 * 取得验证码
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param mobileNumber
	 *            手机号
	 * @return AuthCode 验证码对象
	 * @throws 无
	 */
	public AuthCode getAuthCodeByMobile(String mobileNumber);

	/**
	 * 根据交易ID取得验证码
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param mobileNumber
	 *            手机号
	 * @return AuthCode 验证码对象
	 * @throws 无
	 */
	public AuthCode getAuthCodeByTradeId(String tradeId);

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

	/**
	 * 根据ID删除验证码
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param authCode
	 *            验证码
	 * @return 无  验证码
	 * @throws 无
	 */
	public void deleteAuthCodeById(String authCode);

}
