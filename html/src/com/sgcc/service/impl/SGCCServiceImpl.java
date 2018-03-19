package com.sgcc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sgcc.constant.CommonConsts;
import com.sgcc.service.SGCCService;
import com.sgcc.utils.DateUtil;
import com.sgcc.utils.HttpUtilsOfSGCC;
import com.wanma.dao.SGCCInfoMapper;
import com.wanma.model.sgcc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zangyaoyi on 2017/6/1.
 */
@Service
public class SGCCServiceImpl implements SGCCService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SGCCServiceImpl.class);

	@Autowired
	private SGCCInfoMapper sgccInfoMapper;

	@Override
	public void syncStationInfo(String accessToken, Date startTime, Date endTime) {
		LOGGER.info(
				"SGCCService syncStationInfo is begin:accessToken:{}|startTime:{}|endTime:{}",
				accessToken, startTime, endTime);
		int operateId = sgccInfoMapper.getOperateId(Integer
				.valueOf(CommonConsts.SGCC_COMPANY_NUMBER));
		List<SyncStationInfo> stationInfos = sgccInfoMapper.listStationInfo(
				operateId, startTime, endTime);
		for (SyncStationInfo stationInfo : stationInfos) {
			stationInfo.setOperatorID(CommonConsts.SGCC_OPERATOR_ID);
			stationInfo.setEquipmentOwnerID(CommonConsts.SGCC_OPERATOR_ID);
			// todo:车位数量
			stationInfo.setParkNums(0);
		}
		HttpUtilsOfSGCC.sendData(CommonConsts.SGCC_BASE_URL
				+ CommonConsts.SGCC_SYNC_STATION_INFO, accessToken,
				JSONArray.toJSONString(stationInfos));
		LOGGER.info("SGCCService syncStationInfo is end");
	}

	@Override
	public void syncStationStatsInfo(String accessToken, Date startTime,
			Date endTime) {
		LOGGER.info(
				"SGCCService syncStationStatsInfo is begin:accessToken:{}|startTime:{}|endTime:{}",
				accessToken, startTime, endTime);
		int operateId = sgccInfoMapper.getOperateId(Integer
				.valueOf(CommonConsts.SGCC_COMPANY_NUMBER));
		int diff = DateUtil.getDiffDay(startTime, endTime);
		for (int i = 0; i <= diff; i++) {
			Date time = DateUtil.addDateDays(startTime, i);
			String beginTime = DateUtil.format(time, DateUtil.DATE_TIME_FORMAT);
			List<Map<String, String>> maps = sgccInfoMapper.listStation(
					operateId, beginTime);
			Map<String, SyncEquipmentStatsInfo> noRepeatInfos = fullEquipmentStatsInfo(
					operateId, time);
			List<SyncStationStatsInfo> stationStatsInfos = fullStationStatsInfos(
					maps, noRepeatInfos, time);
			HttpUtilsOfSGCC.sendData(CommonConsts.SGCC_BASE_URL
					+ CommonConsts.SGCC_SYNC_STATION_STATS_INFO, accessToken,
					JSONArray.toJSONString(stationStatsInfos));
		}
		LOGGER.info("SGCCService syncStationStatsInfo is end");
	}

	@Override
	public void syncEquipmentInfo(String accessToken, Date startTime,
			Date endTime) {
		LOGGER.info(
				"SGCCService syncEquipmentInfo is begin:accessToken:{}|startTime:{}|endTime:{}",
				accessToken, startTime, endTime);
		int operateId = sgccInfoMapper.getOperateId(Integer
				.valueOf(CommonConsts.SGCC_COMPANY_NUMBER));
		List<SyncEquipmentInfo> equipmentInfos = sgccInfoMapper
				.listEquipmentInfo(operateId, startTime, endTime);
		int equipmentType;
		int equipmentStatus;
		for (SyncEquipmentInfo equipmentInfo : equipmentInfos) {
			// todo:生产公司标识，桩型号
			equipmentInfo.setManufacturerID("667095618");
			equipmentType = equipmentInfo.getEquipmentType();
			equipmentStatus = equipmentInfo.getEquipmentStatus();
			equipmentInfo.setEquipmentType(1);
			if (14 == equipmentType) {
				equipmentInfo.setEquipmentType(2);
			}
			equipmentInfo.setEquipmentStatus(50);
			if (0 == equipmentStatus) {
				equipmentInfo.setEquipmentStatus(0);
			}
		}
		HttpUtilsOfSGCC.sendData(CommonConsts.SGCC_BASE_URL
				+ CommonConsts.SGCC_SYNC_EQUIPMENT_INFO, accessToken,
				JSONArray.toJSONString(equipmentInfos));
		LOGGER.info("SGCCService syncEquipmentInfo is end");
	}

	@Override
	public void syncEquipmentStatsInfo(String accessToken, Date startTime,
			Date endTime) {
		LOGGER.info(
				"SGCCService syncEquipmentStatsInfo is begin:accessToken:{}|startTime:{}|endTime:{}",
				accessToken, startTime, endTime);
		int operateId = sgccInfoMapper.getOperateId(Integer
				.valueOf(CommonConsts.SGCC_COMPANY_NUMBER));
		int diff = DateUtil.getDiffDay(startTime, endTime);
		for (int i = 0; i <= diff; i++) {
			Date time = DateUtil.addDateDays(startTime, i);
			Map<String, SyncEquipmentStatsInfo> map = fullEquipmentStatsInfo(
					operateId, time);
			List<SyncEquipmentStatsInfo> noRepeatInfos = new ArrayList<>(
					map.values());
			HttpUtilsOfSGCC.sendData(CommonConsts.SGCC_BASE_URL
					+ CommonConsts.SGCC_SYNC_EQUIPMENT_STATS_INFO, accessToken,
					JSONArray.toJSONString(noRepeatInfos));
		}
		LOGGER.info("SGCCService syncEquipmentStatsInfo is end");
	}

	@Override
	public void syncConnectorInfo(String accessToken, Date startTime,
			Date endTime) {
		LOGGER.info(
				"SGCCService syncConnectorInfo is begin:accessToken:{}|startTime:{}|endTime:{}",
				accessToken, startTime, endTime);
		int operateId = sgccInfoMapper.getOperateId(Integer
				.valueOf(CommonConsts.SGCC_COMPANY_NUMBER));
		List<SyncConnectorInfo> connectorInfos = sgccInfoMapper
				.listConnectorInfo(operateId, startTime, endTime);
		String equipmentID;
		String connectorID;
		int connectorType;
		for (SyncConnectorInfo connectorInfo : connectorInfos) {
			connectorType = connectorInfo.getConnectorType();
			connectorInfo.setConnectorType(4);
			if (14 == connectorType) {
				connectorInfo.setConnectorType(3);
			}
			equipmentID = connectorInfo.getEquipmentID();
			connectorID = connectorInfo.getConnectorID();
			connectorInfo.setConnectorID(equipmentID + connectorID);
		}
		HttpUtilsOfSGCC.sendData(CommonConsts.SGCC_BASE_URL
				+ CommonConsts.SGCC_SYNC_CONNECTOR_INFO, accessToken,
				JSONArray.toJSONString(connectorInfos));
		LOGGER.info("SGCCService syncConnectorInfo is end");
	}

	private List<SyncStationStatsInfo> fullStationStatsInfos(
			List<Map<String, String>> maps,
			Map<String, SyncEquipmentStatsInfo> noRepeatInfos, Date startTime) {
		List<SyncStationStatsInfo> stationStatsInfos = new ArrayList<>();
		Map<String, String> equipmentOfStation = new HashMap();
		Map<String, SyncStationStatsInfo> statsInfoMap = new HashMap();
		String statsDate = DateUtil.format(startTime, "yyyy-MM-dd");
		for (Map<String, String> map : maps) {
			Object stationID = map.get("StationID");
			Object equipmentID = map.get("EquipmentID");
			equipmentOfStation
					.put(equipmentID.toString(), stationID.toString());
			statsInfoMap.put(stationID.toString(), new SyncStationStatsInfo());
		}

		for (String equipmentID : equipmentOfStation.keySet()) {
			String stationID = equipmentOfStation.get(equipmentID);
			SyncStationStatsInfo stationStatsInfo = statsInfoMap.get(stationID);
			stationStatsInfo.setStationID(stationID.toString());
			stationStatsInfo.setStatsDate(statsDate);
			handleStationStatsInfo(stationStatsInfo,
					noRepeatInfos.get(equipmentID));
			statsInfoMap.put(stationID, stationStatsInfo);

		}
		stationStatsInfos.addAll(statsInfoMap.values());
		return stationStatsInfos;
	}

	private void handleStationStatsInfo(SyncStationStatsInfo stationStatsInfo,
			SyncEquipmentStatsInfo info) {
		BigDecimal electricity = stationStatsInfo.getStationElectricity() == null ? new BigDecimal(
				0) : stationStatsInfo.getStationElectricity();
		BigDecimal chargeTime = stationStatsInfo.getStationChargeTime() == null ? new BigDecimal(
				0) : stationStatsInfo.getStationChargeTime();
		electricity = electricity.add(info.getEquipmentElectricity()).setScale(
				1, BigDecimal.ROUND_HALF_UP);
		chargeTime = chargeTime.add(info.getEquipmentChargeTime()).setScale(1,
				BigDecimal.ROUND_HALF_UP);
		stationStatsInfo.setStationChargeTime(chargeTime);
		stationStatsInfo.setStationElectricity(electricity);

	}

	private Map<String, SyncEquipmentStatsInfo> fullEquipmentStatsInfo(
			int operateId, Date startTime) {
		String statsDate = DateUtil.format(startTime, "yyyy-MM-dd");
		String beginTime = DateUtil
				.format(startTime, DateUtil.DATE_TIME_FORMAT);
		Map<String, SyncEquipmentStatsInfo> map = new HashMap();
		List<SyncEquipmentStatsInfo> equipmentStatsInfos = sgccInfoMapper
				.listEquipmentStatsInfo(operateId, beginTime);
		for (SyncEquipmentStatsInfo equipmentStatsInfo : equipmentStatsInfos) {
			equipmentStatsInfo.setStatsDate(statsDate);
			if (-1 == equipmentStatsInfo.getEquipmentChargeTime().signum()) {
				equipmentStatsInfo.setEquipmentChargeTime(new BigDecimal(0.0));
				LOGGER.info(
						"syncEquipmentStatsInfo chargeTime is error:model=",
						JSON.toJSONString(equipmentStatsInfo));
			} else {
				equipmentStatsInfo.setEquipmentChargeTime(equipmentStatsInfo
						.getEquipmentChargeTime().setScale(1,
								BigDecimal.ROUND_HALF_UP));
			}
			equipmentStatsInfo.setEquipmentElectricity(equipmentStatsInfo
					.getEquipmentElectricity().setScale(1,
							BigDecimal.ROUND_HALF_UP));
			if (null != map.get(equipmentStatsInfo.getEquipmentID())) {
				handleEquipmentStatsInfo(equipmentStatsInfo,
						map.get(equipmentStatsInfo.getEquipmentID()));
			} else {

				map.put(equipmentStatsInfo.getEquipmentID(), equipmentStatsInfo);
			}
		}
		return map;
	}

	private void handleEquipmentStatsInfo(SyncEquipmentStatsInfo news,
			SyncEquipmentStatsInfo old) {
		BigDecimal electricity;
		BigDecimal chargeTime;
		electricity = old.getEquipmentElectricity()
				.add(news.getEquipmentElectricity())
				.setScale(1, BigDecimal.ROUND_HALF_UP);
		chargeTime = old.getEquipmentChargeTime()
				.add(news.getEquipmentChargeTime())
				.setScale(1, BigDecimal.ROUND_HALF_UP);
		chargeTime = chargeTime.divide(new BigDecimal(1200), 1,
				BigDecimal.ROUND_HALF_UP);
		old.setEquipmentChargeTime(chargeTime);
		old.setEquipmentElectricity(electricity);
	}
}
