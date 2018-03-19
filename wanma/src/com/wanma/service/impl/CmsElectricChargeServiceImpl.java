package com.wanma.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsRateInfoMapper;
import com.wanma.service.CmsElectricChargeService;
@Service
public class CmsElectricChargeServiceImpl implements CmsElectricChargeService {

	
	@Autowired
	private CmsRateInfoMapper cmsRateInfoMapper;
	
	
	
	@Override
	public List<HashMap<String, Object>> getElectricCharge(
			Map<String, Object> params){		
			return cmsRateInfoMapper.searchProvinceList(params);		
	}



	@Override
	public int getElectricChargeCount() {	
		return cmsRateInfoMapper.getElectricChargeCount();
	}



	@Override
	public void updateElectricCharge(Map<String, Object> params) {
		cmsRateInfoMapper.updateElectricCharge(params);		
	}

	@Override
	public void updateRateInfoByProvince(Map<String, Object> params) {
		cmsRateInfoMapper.updateRateInfoByProvince(params);		
	}
}
