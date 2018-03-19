package com.wanma.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsMoneyRecordMapper;
import com.wanma.service.CmsMoneyRecordService;

@Service
public class CmsMoneyRecordServiceImpl implements CmsMoneyRecordService {

	@Autowired
	CmsMoneyRecordMapper moneyRecord;
	
	public HashMap<String,Object> getTotalRecharge() {		
		return moneyRecord.getTotalRecharge();
	}


	public HashMap<String,Object> getTotalPurchase() {	
		return moneyRecord.getTotalPurchase();
	}

	public HashMap<String,Object> getTotalAccount() {	
		return moneyRecord.getTotalAccount();
	}

	public List<HashMap<String, Object>> getUserMoneyRecordList(
			Map<String, Object> params) {		
		return moneyRecord.getUserMoneyRecordList(params);
	}



	public long getUserMoneyRecordCount(Map<String, Object> params) {		
		return  moneyRecord.getUserMoneyRecordCount(params);
	}


	@Override
	public HashMap<String,Object> getUserTotalRecharge(Map<String,Object> params) {		
		return moneyRecord.getUserTotalRecharge(params);
	}


	@Override
	public HashMap<String,Object> getUserTotalPurchase(Map<String,Object> params) {
		
		return moneyRecord.getUserTotalPurchase(params);
	}
	

	@Override
	public List<HashMap<String, Object>> getMoneyRecordListByUserId(
			Map<String, Object> params) {		
		return moneyRecord.getMoneyRecordListByUserId(params);
	}


	@Override
	public long getMoneyRecordCountByUserId(Map<String, Object> params) {		
		return moneyRecord.getMoneyRecordCountByUserId(params);
	}



	@Override
	public long getPartnerMoneyRecordCount(Map<String, Object> params) {
		return moneyRecord.getPartnerMoneyRecordCount(params);
	}


	@Override
	public List<HashMap<String, Object>> getPartnerMoneyRecordList(
			Map<String, Object> params) {
		return moneyRecord.getPartnerMoneyRecordList(params);
	}


	@Override
	public long getMoneyRecordCountByCompanyNameNumber(
			Map<String, Object> params) {
		return moneyRecord.getMoneyRecordCountByCompanyNameNumber(params);
	}


	@Override
	public List<HashMap<String, Object>> getMoneyRecordListByCompanyNameNumber(
			Map<String, Object> params) {
		return moneyRecord.getMoneyRecordListByCompanyNameNumber(params);
	}

	@Override
	public int updateNormAccount(Map<String, Object> map) {
		
		return moneyRecord.updateNormAccount(map);
	}


	@Override
	public String getPuHiUserId(String userAccount) {
		return moneyRecord.getPuHiUserId(userAccount);
	}


	@Override
	public int insertPurhistory(Map<String, Object> hiMap) {
		return moneyRecord.insertPurhistory(hiMap);
	}



	@Override
	public Map<String, Object> getRefundInfo(String userId) {
		return moneyRecord.getRefundInfo(userId);
	}

	@Override
	public int refund(String userAccount, String refundAccount,String puHiUserId) throws Exception{
		try {
			Map<String, Object> map =  new HashMap<String,Object>();
			map.put("userAccount", userAccount);
			map.put("refundAccount", refundAccount);
			int updateRefund = updateNormAccount(map);
			int refundHistory=0;
			if(updateRefund==1){
				Map<String,Object> hiMap = new HashMap<String,Object>();
				hiMap.put("puHiUserId", puHiUserId);
				hiMap.put("refundAccount", refundAccount);
				refundHistory = insertPurhistory(hiMap);
			}
			return refundHistory;
			
		} catch (Exception e) {
			throw e;
		}
		
				
		
	}




}
