package com.wanma.web.service;

import com.wanma.model.AuthCode;

public interface AuthCodeService {
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
	public String sendAuthCode(String mobileNumber,String authCode);
	
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
	public boolean checkAuthCode(String authCode, String usinPhone);
}
