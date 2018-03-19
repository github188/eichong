package com.wanma.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.MonitorHomeMapper;
import com.wanma.dao.StatisticAssertMapper;
import com.wanma.dao.StatisticChargeMapper;
import com.wanma.dao.StatisticFaultMapper;
import com.wanma.service.MonitorHomeService;

@Service
public class MonitorHomeServiceImpl implements MonitorHomeService{
	
	@Autowired
	private MonitorHomeMapper monitorHomeMapper;
	@Autowired 
	private StatisticChargeMapper statisticChargeMapper;
	@Autowired 
	private StatisticAssertMapper statisticAssertMapper;
	@Autowired 
	private StatisticFaultMapper statisticFaultMapper;
	
	@Override
	public Map<String, Object> home(Map<String, Object> params) {
		// 今日充电情况
		Map<String,Object> map = statisticChargeMapper.selectChargeTodayDataCount(params);
		if(null == map){
			map = new HashMap<String,Object>();
		}
		// 累计充电度数
		Map<String,Object> map2 = statisticChargeMapper.selectChargeDataCount(params);
		if(null != map2){ 
			map.put("allChargeC", map2.get("v1"));
		}
		// 总桩数
		Map<String,Object> map3 = statisticAssertMapper.selectElecPileData(params);
		if(null != map3){
			map.put("allPileNum", map3.get("pileNum"));
		}
		// 离线数
		Map<String,Object> map4 = statisticFaultMapper.selectFaultOfflineCount(params);
		if(null != map4){
			map.put("offlinePileNum", map4.get("v2"));
		}
		// 今日故障数
		Map<String,Object> map5 = statisticFaultMapper.selectFaultTodayCount(params);
		if(null != map5){
			map.put("faultNum", map5.get("v1"));
		}
		return  map;
	}
	
	@Override
	public int countChargePointMap(Map<String, Object> params) {
		return monitorHomeMapper.countChargePointMap(params);
	}
	
	@Override
	public List<Map<String, Object>> queryChargePointMap(
			Map<String, Object> params) {
		return monitorHomeMapper.selectChargePointMap(params);
	}

	@Override
	public List<Map<String, Object>> queryChargePoint4Detail(
			Map<String, Object> params) {
		return monitorHomeMapper.selectPileHeadDetail4Pid(params);
	}

	@Override
	public Map<String, Object> queryChargeCount(Map<String, Object> params) {
		return monitorHomeMapper.selectChargeCount(params);
	}

	@Override
	public Map<String, Object> queryChargeToday(Map<String, Object> params) {
		return monitorHomeMapper.selectChargeToday(params);
	}

	@Override
	public Map<String, Object> queryElecCount(Map<String, Object> params) {
		return monitorHomeMapper.selectElecCount(params);
	}

	@Override
	public Map<String, Object> queryElecHeadFaultCount(
			Map<String, Object> params) {
		return monitorHomeMapper.selectElecHeadFaultCount(params);
	}

	@Override
	public Map<String, Object> queryElecHeadChargeCount(
			Map<String, Object> params) {
		Map<String,Object> map = new HashMap<String,Object>();
		// 今日
		Map<String,Object> mapToday = monitorHomeMapper.selectElecHeadChargeToday(params);
		map.put("mapToday",null == mapToday?null : mapToday);
		// 本月
		Map<String,Object> mapMonth = monitorHomeMapper.selectElecHeadChargeMonth(params);
		map.put("mapMonth",null == mapMonth?null : mapMonth);
		// 今年
		Map<String,Object> mapYear = monitorHomeMapper.selectElecHeadChargeYear(params);
		map.put("mapYear",null == mapYear?null : mapYear);
		// 历史
		Map<String,Object> mapHistory = monitorHomeMapper.selectElecHeadChargeHistory(params);
		map.put("mapHistory",null == mapHistory?null : mapHistory);
		return map;
	}

	@Override
	public Map<String, Object> queryElecRatPower(String params) {
		return monitorHomeMapper.selectElecRatPower(params);
	}



}
