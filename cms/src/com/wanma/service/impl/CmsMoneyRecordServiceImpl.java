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

}
