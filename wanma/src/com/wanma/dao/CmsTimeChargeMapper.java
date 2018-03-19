package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblTimeCharge;

public interface CmsTimeChargeMapper {

	List<TblTimeCharge> getElectricpileList(TblTimeCharge timeCharge);

	long getElectricpileCount(TblTimeCharge timeCharge);

	void insertTimeCharge(TblTimeCharge timeCharge);

	int findCodeFormTimeCharge(String elpiElectricpilecode);

	void updateTimeCharge(TblTimeCharge timeCharge);
	
}
