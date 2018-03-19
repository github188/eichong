package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsElectricPileMapper;
import com.wanma.service.ElectricPileService;

@Service
public class ElectricPileServiceImpl implements ElectricPileService{
	
	/**
	 * 根据费率id查询智能桩的编码
	 * @param rateId
	 * @return
	 */
	public List<String> getEpCodesByRateId(int rateId){
		return epMapper.getEpCodesByRateId(rateId);
	}
	
	@Autowired
	CmsElectricPileMapper epMapper;
}
