package com.wanma.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.TblRateInformationMapper;
import com.wanma.model.TblRateInformation;
import com.wanma.service.TblRateInformationService;




/**
 * @Description:费率信息Service Implementation
 * @author Dongyu
 * @createTime 2015/12/08 12:43
 * @updater 
 * @updateTime    
 * @version v1.0
 */
@Service
public class TblRateInformationServiceImpl implements TblRateInformationService {

	@Autowired
	private TblRateInformationMapper tblRateInformationMapper;
	
	@Override
	public TblRateInformation getById(Long pkRateInformation) {
		
		TblRateInformation rateInformation = tblRateInformationMapper.getById(pkRateInformation);
		return rateInformation;
	}

	@Override
	public Double selectMinPriceByPsId(Integer pkPowerstation) {
		return tblRateInformationMapper.selectMinPriceByPsId(pkPowerstation);
	}

	@Override
	public TblRateInformation getPriceById(Map<String, Object> params) {
		return tblRateInformationMapper.getPriceById(params);
	}

	@Override
	public TblRateInformation queryByRateInfo(Map<String, Object> params) {
		return tblRateInformationMapper.queryByRateInfo(params);
	}

	@Override
	public TblRateInformation queryByPriceId(Map<String, Object> params) {
		return tblRateInformationMapper.queryByPriceId(params);
	}

}
