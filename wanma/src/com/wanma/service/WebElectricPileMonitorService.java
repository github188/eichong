package com.wanma.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.ElectricPileList;
import com.wanma.model.ElectricPileMap;
import com.wanma.model.ElectricPileMonitor;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;

public interface WebElectricPileMonitorService {
	public TblElectricpile getPileMonitorByUserId(String userId);

	public List<ElectricPileMonitor> getElectricPileMonitorForList(
			Map<String, Object> params);

	public long getElectricpileMapCount(Map<String, Object> params);

	public List<ElectricPileMonitor> getElectricPileMonitorForMap(
			Map<String, Object> params);

	public List<ElectricPileMonitor> getElectricPileListByStationId(
			Map<String, Object> params);

	public Map getAllPileCount(Map<String, Object> params);
	
	public Map getChargingCount(Map<String, Object> params);
	
	public Map pileErrorCount(Map<String, Object> params);

	public List<ElectricPileMonitor> queryErrorPile(Map<String, Object> params);

	public Object getBespokeCount(Map<String, Object> params);

	public Object getOnlineCount(Map<String, Object> params);

	public Object getOfflineCount(Map<String, Object> params);

	public long getElectricpileMapV2Count(Map<String, Object> params);

	public List<ElectricPileMonitor> getElectricPileMonitorForListV2(
			Map<String, Object> params);

	public List<ElectricPileMonitor> getElectricPileMonitorForMapV2(
			Map<String, Object> params);

	public List<Map<String, Object>> getHeadDetailByPointId(
			Map<String, Object> params);

	public Map<String, Object> getElectricPileByCode(
			Map<String, Object> params);
	
	public  Map<String, Object> getChargeStatics_01(
			Map<String, Object> params);
	public  Map<String, Object> getChargeStatics_02(
			Map<String, Object> params);
	public  Map<String, Object> getChargeStatics_03(
			Map<String, Object> params);
	public  Map<String, Object> getChargeStatics_04(
			Map<String, Object> params);
	public  Map<String, Object> getChargeStatics_demo(
			Map<String, Object> params);

}
