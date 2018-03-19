package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsEleChargeOffLineMapper;
import com.wanma.model.TblEleChargeOffLine;
import com.wanma.service.CmsEleChargeOffLineService;
@Service
public class CmsEleChargeOffLineServiceImpl implements CmsEleChargeOffLineService{
	@Autowired
	private CmsEleChargeOffLineMapper cmsEleChargeOffLineMapper;
	
	@Override
	public long getEleChargeOffLineCount(TblEleChargeOffLine tblEcOffLine) {
		return cmsEleChargeOffLineMapper.getEleChargeOffLineCount(tblEcOffLine);
	}

	@Override
	public int findCodeFormEleChargeOffLine(String elpiElectricpilecode) {
		return cmsEleChargeOffLineMapper.findCodeFormEleChargeOffLine(elpiElectricpilecode);
	}

	@Override
	public void insertEcOffLine(TblEleChargeOffLine tblEcOffLine) {
		cmsEleChargeOffLineMapper.insertEcOffLine(tblEcOffLine);
	}

	@Override
	public void updateEcOffLine(TblEleChargeOffLine tblEcOffLine) {
		cmsEleChargeOffLineMapper.updateEcOffLine(tblEcOffLine);
	}

	@Override
	public List<TblEleChargeOffLine> getEleChargeOffLineList(TblEleChargeOffLine tblEcOffLine) {
		return cmsEleChargeOffLineMapper.getEleChargeOffLineList(tblEcOffLine);
	}

}
