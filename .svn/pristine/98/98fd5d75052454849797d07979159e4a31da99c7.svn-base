package com.wanma.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.SessionMgr;
import com.wanma.dao.CmsSocChargeMapper;
import com.wanma.model.TblSocCharge;
import com.wanma.model.TblUser;
import com.wanma.service.CmsSocChargeService;
import com.wanma.web.support.utils.ApiUtil;
import com.wanma.web.support.utils.HttpsUtil;
@Service
public class CmsSocChargeServiceImpl implements CmsSocChargeService{
	
	@Autowired
	private CmsSocChargeMapper cmsSocChargeMapper;
	
	@Override
	public List<TblSocCharge> getElectricpileList(TblSocCharge socCharge) {
		return cmsSocChargeMapper.getElectricpileList(socCharge);
	}

	@Override
	public long getElectricpileCount(TblSocCharge socCharge) {
		return cmsSocChargeMapper.getElectricpileCount(socCharge);
	}

	@Override
	public int findCodeFormSocCharge(String elpiElectricpilecode) {
		return cmsSocChargeMapper.findCodeFormSocCharge(elpiElectricpilecode);
	}

	@Override
	public void insertSocCharge(TblSocCharge socCharge) {
		cmsSocChargeMapper.insertSocCharge(socCharge);
	}

	@Override
	public void updateSocCharge(TblSocCharge socCharge) {
		cmsSocChargeMapper.updateSocCharge(socCharge);		
	}

}
