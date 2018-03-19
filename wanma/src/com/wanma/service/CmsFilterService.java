package com.wanma.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wanma.model.Cooperate;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;




public interface CmsFilterService {


	List<Cooperate> getCooperateList(Cooperate cooperate);

	long getCooperateCount(Cooperate cooperate);

	List<Cooperate> getCpyCompany();

	void ChangeCpyOperate(Map<String,String> map);

	long getFilterPwCount(TblPowerstation powerStation);

	List<TblPowerstation> getFilterPwList(TblPowerstation powerStation);

	long getFilterAddPileCount(TblElectricpile tblElectricpile);

	List<TblElectricpile> getFilterAddPileList(TblElectricpile tblElectricpile);

	long getFilterPileCount(TblElectricpile tblElectricpile);

	List<TblElectricpile> getFilterPileList(TblElectricpile tblElectricpile);

	long getAllPwCount(TblPowerstation tblPowerstation);

	List<TblPowerstation> getAllPwList(TblPowerstation tblPowerstation);



	void addPwStation(String[] idArray, String companyId);

	void modifyCpyOperate(Map<String, String> map);

	void deletePileByPw(String[] idArray, String companyId);

	void deletePile(HashMap<String, String> map);

	void savePile(String[] idArray, HashMap<String, String> map);

	
}
