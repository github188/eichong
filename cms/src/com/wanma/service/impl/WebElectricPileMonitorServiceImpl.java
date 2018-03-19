package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsElectricPileMapper;
import com.wanma.model.ElectricPileMonitor;
import com.wanma.model.TblElectricpile;
import com.wanma.service.WebElectricPileMonitorService;

@Service
public class WebElectricPileMonitorServiceImpl implements
		WebElectricPileMonitorService {
	@Autowired
	CmsElectricPileMapper tblElectricpileMonitorMapper;

	@Override
	public TblElectricpile getPileMonitorByUserId(String userId) {
		return tblElectricpileMonitorMapper.getPileMonitorByUserId(userId);
	}

	@Override
	public List<ElectricPileMonitor> getElectricPileMonitorForList(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return tblElectricpileMonitorMapper
				.getElectricPileMonitorForList(params);
	}

	@Override
	public List<ElectricPileMonitor> getElectricPileMonitorForMap(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return tblElectricpileMonitorMapper
				.getElectricPileMonitorForList(params);
	}

	@Override
	public long getElectricpileMapCount(Map<String, Object> params) {

		return tblElectricpileMonitorMapper
				.getElectricPileMonitorForListCount(params);
	}

	@Override
	public List<ElectricPileMonitor> getElectricPileListByStationId(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return tblElectricpileMonitorMapper
				.getElectricPileListByStationId(params);
	}

	@Override
	public Map getAllPileCount(Map<String, Object> params) {
		return tblElectricpileMonitorMapper.getAllPileCount(params);

	}

	@Override
	public Map getChargingCount(Map<String, Object> params) {
		return tblElectricpileMonitorMapper.getChargingCount(params);
	}

	@Override
	public Map pileErrorCount(Map<String, Object> params) {
		return tblElectricpileMonitorMapper.pileErrorCount(params);
	}

	@Override
	public List<ElectricPileMonitor> queryErrorPile(Map<String, Object> params) {
		return tblElectricpileMonitorMapper.queryErrorPile(params);
	}

	@Override
	public Object getBespokeCount(Map<String, Object> params) {
		return tblElectricpileMonitorMapper.getBespokeCount(params);

	}

	@Override
	public Object getOnlineCount(Map<String, Object> params) {
		return tblElectricpileMonitorMapper.getOnlineCount(params);
	}

	@Override
	public Object getOfflineCount(Map<String, Object> params) {
		return tblElectricpileMonitorMapper.getOfflineCount(params);
	}

	@Override
	public long getElectricpileMapV2Count(Map<String, Object> params) {
		return tblElectricpileMonitorMapper
				.getElectricPileMonitorForListV2Count(params);
	}

	@Override
	public List<ElectricPileMonitor> getElectricPileMonitorForListV2(
			Map<String, Object> params) {
		return tblElectricpileMonitorMapper
				.getElectricPileMonitorForListV2(params);
	}

	@Override
	public List<ElectricPileMonitor> getElectricPileMonitorForMapV2(
			Map<String, Object> params) {
		return tblElectricpileMonitorMapper
				.getElectricPileMonitorForListV2(params);
	}

	@Override
	public List<Map<String, Object>> getHeadDetailByPointId(
			Map<String, Object> params) {
			return tblElectricpileMonitorMapper.getHeadDetailByPoint(params);
	}

	@Override
	public Map<String, Object> getElectricPileByCode(
			Map<String, Object> params) {
		return tblElectricpileMonitorMapper
				.getElectricPileByCode(params);
	}

	@Override
	public  Map<String, Object> getChargeStatics_01(
			Map<String, Object> params) {
		return tblElectricpileMonitorMapper
				.getChargeStatics_01(params);
	}
	@Override
	public  Map<String, Object> getChargeStatics_02(
			Map<String, Object> params) {
		return tblElectricpileMonitorMapper
				.getChargeStatics_02(params);
	}
	@Override
	public  Map<String, Object> getChargeStatics_03(
			Map<String, Object> params) {
		return tblElectricpileMonitorMapper
				.getChargeStatics_03(params);
	}
	@Override
	public  Map<String, Object> getChargeStatics_04(
			Map<String, Object> params) {
		return tblElectricpileMonitorMapper
				.getChargeStatics_04(params);
	}
	@Override
	public  Map<String, Object> getChargeStatics_demo(
			Map<String, Object> params) {
		return tblElectricpileMonitorMapper
				.getChargeStatics_demo(params);
	}
}
