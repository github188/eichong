package com.wanma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.RateInfoPushMapper;
import com.wanma.service.CmsRateInfoService;

@Service
public class CmsRateInfoServiceImpl implements CmsRateInfoService{

	@Autowired
	private RateInfoPushMapper cmsRateInfoMapper;

	public Double selectMinPriceByPsId(Integer pkPowerstation) {
		return cmsRateInfoMapper.selectMinPriceByPsId(pkPowerstation);
	}
	
	
}
