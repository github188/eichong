package com.wanma.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanma.app.dao.GovBusiUserMapper;
import com.wanma.app.service.GovBusiUserService;
import com.wanma.model.GovBusiUser;

@Service
@Transactional
public class GovBusiUserServiceImpl implements GovBusiUserService{

	/** 用户信息业务操作DAO */
	@Autowired
	private GovBusiUserMapper gbuMapper;
	
	/**
	 * 获取政企版用户信息
	 */
	@Override
	public GovBusiUser getGovBusiUserInfo(String userId) {
		// 获取政企版用户信息--用户姓名、手机号码、头像、企业名称
		GovBusiUser info = gbuMapper.getUserBaseInfo(userId);
		String userName = info.getUserName();
		String userPhone = info.getUserPhone();
		String userImage = info.getUserImage();
		String companyName = info.getCompanyName();
		//获取政企版用户信息--付费策略、结算方式、用户余额
		info = gbuMapper.getUserAccount(userId);
		int paymentRule =info.getPaymentRule();
		int tradeType = info.getTradeType();
		String accountBalance = info.getAccountBalance();				
		//获取政企版用户信息--预冻结金额
		info=gbuMapper.getChargingRecord(userId);
		String chReFrozenAmt;
		if(info == null){
			chReFrozenAmt = "0";
		}else{
			chReFrozenAmt = info.getChReFrozenAmt();
		}
		//获取政企版用户信息--充电记录已完成数量、充电记录未完成数量，消费金额
		info=gbuMapper.getAccountPc(userId);
		int chargingRecordNum = info.getChargingRecordNum();
		int chargingNum= info.getChargingNum();
		String puHiMonetary = info.getPuHiMonetary();
		
		GovBusiUser resultMap = new GovBusiUser();
		resultMap.setUserId(Integer.parseInt(userId));
		resultMap.setUserName(userName);
		resultMap.setUserPhone(userPhone);
		resultMap.setUserImage(userImage);
		resultMap.setCompanyName(companyName);
		resultMap.setPaymentRule(paymentRule);
		resultMap.setTradeType(tradeType);
		resultMap.setAccountBalance(accountBalance);
		resultMap.setChargingRecordNum(chargingRecordNum);
		resultMap.setChReFrozenAmt(chReFrozenAmt);
		resultMap.setChargingNum(chargingNum);
		resultMap.setPuHiMonetary(puHiMonetary);
		
		return resultMap;
	}
	/**
	 * 查询手机号是否已被绑定
	 */
	@Override
	public int getPhoneCount(String usinPhone) {
		int count = gbuMapper.getPhoneCount(usinPhone);
		return count;
	}
	
	/**
	 * 验证用户是否存在（绑定手机号）
	 */
	@Override
	public int getUserById(String userId) {
		int count = gbuMapper.getUserById(userId);
		return count;
	}
	
	/**
	 * 绑定手机号
	 */
	@Override
	public int bindPhoneNumber(String userId, String usinPhone) {
		GovBusiUser info = new GovBusiUser();
		info.setUserId(Integer.parseInt(userId));
		info.setUserPhone(usinPhone);
		int bindPhone = gbuMapper.bindPhoneNumber(info);
		
		return bindPhone;
	}


}
