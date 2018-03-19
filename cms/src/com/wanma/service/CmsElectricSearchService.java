package com.wanma.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.ElectricPileDetail;
import com.wanma.model.ElectricPileMap;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblPowerstation;

public interface CmsElectricSearchService {
	/**
	 * 获取地图模式电桩列表
	 */
	public List<ElectricPileMap> getElectricMapList(TblPowerstation tblPowerstation,TblElectricpile tblElectricpile);
	
	public ElectricPileDetail getCurrentPileDetail(String eid);

	public List<TblElectricpilehead> getCurrentHeadList(String eid);

	public TblElectricpilehead getHeadDetail(Map<String, String> params);

	public List<String> queryErrorCodeListByUser(Map<String, String> params);
}
