package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsTimeChargeMapper;
import com.wanma.model.TblTimeCharge;
import com.wanma.service.CmsTimeChargeService;
@Service
public class CmsTimeChargeServiceImpl implements CmsTimeChargeService{
	@Autowired
	CmsTimeChargeMapper cmsTimeChargeMapper;
	@Override
	public List<TblTimeCharge> getElectricpileList(TblTimeCharge timeCharge) {
		return cmsTimeChargeMapper.getElectricpileList(timeCharge);
	}
	@Override
	public long getElectricpileCount(TblTimeCharge timeCharge) {
		return cmsTimeChargeMapper.getElectricpileCount(timeCharge);
	}
	@Override
	public void insertTimeCharge(TblTimeCharge timeCharge) {
		 cmsTimeChargeMapper.insertTimeCharge(timeCharge);
	}
	@Override
	public int findCodeFormTimeCharge(String elpiElectricpilecode) {
		return cmsTimeChargeMapper.findCodeFormTimeCharge(elpiElectricpilecode);
	}
	@Override
	public void updateTimeCharge(TblTimeCharge timeCharge) {
		 cmsTimeChargeMapper.updateTimeCharge(timeCharge);
		
	}

}
