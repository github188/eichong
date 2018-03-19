package com.wanma.service;

import java.util.List;

import com.wanma.model.TblEleChargeOffLine;

public interface CmsEleChargeOffLineService {

	public long getEleChargeOffLineCount(TblEleChargeOffLine tblEcOffLine);

	public int findCodeFormEleChargeOffLine(String elpiElectricpilecode);

	public void insertEcOffLine(TblEleChargeOffLine tblEcOffLine);

	public void updateEcOffLine(TblEleChargeOffLine tblEcOffLine);

	public List<TblEleChargeOffLine> getEleChargeOffLineList(TblEleChargeOffLine tblEcOffLine);


}
