package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblSocCharge;

public interface CmsSocChargeMapper {

	public List<TblSocCharge> getElectricpileList(TblSocCharge socCharge);

	public long getElectricpileCount(TblSocCharge socCharge);

	public int findCodeFormSocCharge(String elpiElectricpilecode);

	public void insertSocCharge(TblSocCharge socCharge);

	public void updateSocCharge(TblSocCharge socCharge);

}
