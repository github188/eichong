package com.wanma.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsPowerRateMapper;
import com.wanma.service.CmsPowerRateService;
@Service
public class CmsPowerRateServiceImpl implements CmsPowerRateService {

	
	@Autowired
	private CmsPowerRateMapper cmsRateInfoMapper;
	
	
	
	@Override
	public List<HashMap<String, Object>> getPowerrateList(
			Map<String, Object> params){		
			return cmsRateInfoMapper.getPowerrateList(params);		
	}



	@Override
	public int getPowerrateListCount() {	
		return cmsRateInfoMapper.getPowerrateListCount();
	}



	@Override
	public void updatePowerrate(Map<String, Object> params) {
		cmsRateInfoMapper.updatePowerrate(params);		
	}

}
