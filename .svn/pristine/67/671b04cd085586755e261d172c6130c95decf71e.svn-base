package com.wanma.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wanma.model.Cooperate;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;


public interface CmsFilterMapper {

	long getCooperateCount(Cooperate cooperate);

	List<Cooperate> getCooperateList(Cooperate cooperate);

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

	void deletePwStation(HashMap<String,String> map);

	void addPwStation(HashMap<String,String> map);

	void deleteComRela(Map<String, String> map);

	void deletePile(HashMap<String, String> map);

	void savePile(HashMap<String, String> map);
	/**
	 * 导出充电站Excel
	 * @param paramsModel
	 * @return
	 */
	List<Map<String,Object>>getFilterPwListExport(TblPowerstation paramsModel );
	
	



	
	
}