package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.ElectricPileList;
import com.wanma.model.ElectricPileMonitor;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;

public interface CmsElectricPileMapper {
	
	/**
	 * 根据费率id查询智能桩的编码
	 * @param rateId
	 * @return
	 */
	public List<String> getEpCodesByRateId(int rateId);
	public List<ElectricPileList> getElectricPileForList(Map<String, Object> params);
	/**
	 * 通过Id获取电桩数据
	 * 
	 * @param feedback
	 */
	public <K, V> List<Map<K, V>> getElectricpileById(TblPowerstation tblPowerstation);
	public Map<String, Object> getTblRateinformation(String elictric);
	public Map<String, Object> findDetailById(TblElectricpile tblElectricpile);
	public TblElectricpile getPileMonitorByUserId(String s);

	public List<ElectricPileMonitor> getElectricPileMonitorForList(
			Map<String, Object> params);
	public List<ElectricPileMonitor> getElectricPileMonitorForMap(
			Map<String, Object> params);
	
	public long getElectricPileMonitorForListCount(
			Map<String, Object> params);
	public List<ElectricPileMonitor> getElectricPileListByStationId(
			Map<String, Object> params);
	public Map getAllPileCount(
			Map<String, Object> params);
	public Map getChargingCount(Map<String, Object> params);
	public Map pileErrorCount(Map<String, Object> params);
	public List<ElectricPileMonitor> queryErrorPile(Map<String, Object> params);
	public Object getBespokeCount(Map<String, Object> params);
	public Object getOnlineCount(Map<String, Object> params);
	public Object getOfflineCount(Map<String, Object> params);
	public List<ElectricPileMonitor> getElectricPileMonitorForListV2(
			Map<String, Object> params);
	public long getElectricPileMonitorForListV2Count(Map<String, Object> params);
	public List<Map<String, Object>> getHeadDetailByPoint(
			Map<String, Object> params);
	public Map<String, Object> getElectricPileByCode(
			Map<String, Object> params);
	public Map<String, Object> getChargeStatics_01(
			Map<String, Object> params);
	public Map<String, Object> getChargeStatics_02(
			Map<String, Object> params);
	public Map<String, Object> getChargeStatics_03(
			Map<String, Object> params);
	public Map<String, Object> getChargeStatics_04(
			Map<String, Object> params);
	public Map<String, Object> getChargeStatics_demo(
			Map<String, Object> params);
}
