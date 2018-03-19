package com.wanma.web.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.common.BluemobiCommon;
import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.utils.DateUtil;
import com.bluemobi.product.utils.ObjectUtil;
import com.wanma.model.AuthCode;
import com.wanma.web.dao.AuthCodeMapper;
import com.wanma.web.service.AuthCodeService;

@Service
public class AuthCodeServiceImpl implements AuthCodeService{
	
	@Autowired
	private AuthCodeMapper authCodeMapper;
	
	@Override
	public AuthCode getAuthCodeByMobile(String mobileNumber) {
		return authCodeMapper.getAuthCodeByMobile(mobileNumber);
	}

	@Override
	public AuthCode getAuthCodeByTradeId(String tradeId) {
		return authCodeMapper.getAuthCodeByTradeId(tradeId);
	}

	@Override
	public void saveAuthCode(AuthCode authCode) {
		authCodeMapper.saveAuthCode(authCode);
		
	}

	@Override
	public void deleteAuthCode(String mobileNumber) {
		authCodeMapper.deleteAuthCode(mobileNumber);
	}

	@Override
	public void deleteAuthCodeById(String authCode) {
		authCodeMapper.deleteAuthCodeById(authCode);
	}
	
	public boolean checkAuthCode(String authCode, String usinPhone){
		// 验证码确认结果
				boolean checkAuthCode = true;
				// 验证码对象
				AuthCode authCodeObject = null;
				// 验证码有效时间
				long effectSends = CommonConsts.AUTHCODE_EFFECTIVE_TIME;
				// 验证码生成的时间
				Date createDate = null;
				// 系统时间
				Date sysDate = new Date();
				// 时间比较结果
				int compareResult = 0;
				// 数据库保存的验证码
				String sysAuthCode = "";

				// 根据手机号取得验证码对象
				authCodeObject = authCodeMapper.getAuthCodeByMobile(usinPhone);

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
					createDate = DateUtil.getAddSecond(createDate, effectSends);
					// 比较系统时间和加上有效时长验证码生成的时间
					compareResult = DateUtil.compareDate(sysDate, createDate);

					//
					// 如果系统时间超过有效时间或者验证码不对，则为无效验证码
					//
					System.out.println(compareResult> 0);
					System.out.println(sysAuthCode.equals(authCode));
					if (compareResult > 0 || !sysAuthCode.equals(authCode)) {
						checkAuthCode = false;
					}
				}

				// 返回验证码确认结果
				return checkAuthCode;
	}
	
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

	public String sendAuthCode(String mobileNumber, String authCode) {
		// 验证码对象
		AuthCode authCodeObject = new AuthCode();
		/*// 验证码
		String authCode = "";

		// 生成验证码
		authCode = ProcessInfoCommon.getDigitRandomKey(6);
		//
		// 设置验证码信息
		//
*/		authCodeObject.setAuthCode(authCode);
		authCodeObject.setMobileNumber(mobileNumber);

		// 删除该手机号之前发出的验证码
		authCodeMapper.deleteAuthCode(mobileNumber);
		// 保存验证码
		authCodeMapper.saveAuthCode(authCodeObject);
		/* 由于某些短信平台有自己固定的短信模板，因此此处进行模板适配处理 */
		// 发送验证码
		BluemobiCommon.sendWanMatMessage(new String("您的验证码：" 
				+ authCode + "打死都不要告诉别人。如非本人操作，不用理会，走你！"), mobileNumber);
		return null;
		
	}
}
