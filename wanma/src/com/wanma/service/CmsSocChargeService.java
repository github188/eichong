package com.wanma.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wanma.model.TblSocCharge;

public interface CmsSocChargeService {

	public List<TblSocCharge> getElectricpileList(TblSocCharge socCharge);
	
	public long getElectricpileCount(TblSocCharge socCharge);

	public int findCodeFormSocCharge(String elpiElectricpilecode);

	public void insertSocCharge(TblSocCharge socCharge);

	public void updateSocCharge(TblSocCharge socCharge);

}
