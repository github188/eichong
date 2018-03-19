package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblEleChargeOffLine;

public interface CmsEleChargeOffLineMapper {

	long getEleChargeOffLineCount(TblEleChargeOffLine tblEcOffLine);

	int findCodeFormEleChargeOffLine(String elpiElectricpilecode);

	void insertEcOffLine(TblEleChargeOffLine tblEcOffLine);

	void updateEcOffLine(TblEleChargeOffLine tblEcOffLine);

	List<TblEleChargeOffLine> getEleChargeOffLineList(TblEleChargeOffLine tblEcOffLine);

}
