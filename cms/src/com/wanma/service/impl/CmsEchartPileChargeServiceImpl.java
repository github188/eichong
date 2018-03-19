package com.wanma.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.base.common.JsonUtil;
import com.wanma.dao.CmsStatisticMapper;
import com.wanma.model.echarts.EchartsParamModel;

@Service
public class CmsEchartPileChargeServiceImpl extends CmsEchartsServiceImpl {
	@Autowired
	CmsStatisticMapper statisticsMapper;

	@Override
	public void setData(JSONObject obj, EchartsParamModel paramsModel) {
		setPileBespokingData(obj, paramsModel);
		setPileChargingData(obj, paramsModel);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setPileBespokingData(JSONObject obj,
			EchartsParamModel paramsModel) {
		JSONObject jsonModel = JsonUtil.getJsonObject(path
				+ "/json/pileBespokingBar.json");
		List<Map<Object, Object>> pileBespokinglist = statisticsMapper
				.getPileBespoking(paramsModel);
		if (pileBespokinglist != null && !pileBespokinglist.isEmpty()) {
			List<String> areaList = new ArrayList<String>();
			String city = "";
			int count = 0;
			// 得到城市分组
			for (Map<Object, Object> tempObj : pileBespokinglist) {
				city = (String) tempObj.get("城市");
				if (!areaList.contains(city)) {
					areaList.add(city);
				} else {
					break;
				}
			}
			int[] countArray = new int[areaList.size()];
			for (Map<Object, Object> tempObj : pileBespokinglist) {
				city = (String) tempObj.get("城市");
				count = ((Long) tempObj.get("电桩数量")).intValue();
				countArray[areaList.indexOf(city)] = count;
			}
			// xAxis
			Map xAxisMap = new HashMap();
			xAxisMap.put("type", "category");
			xAxisMap.put("data", areaList);
			jsonModel.put("xAxis", xAxisMap);
			// series
			Map map = new HashMap();
			map.put("name", "电桩数量");
			map.put("type", "bar");
			map.put("data", countArray);
			List list = new ArrayList();
			list.add(map);
			jsonModel.put("series", list);
			obj.put("data1", jsonModel);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setPileChargingData(JSONObject obj,
			EchartsParamModel paramsModel) {
		JSONObject jsonModel = JsonUtil.getJsonObject(path
				+ "/json/pileChargingBar.json");
		List<Map<Object, Object>> pileCharginglist = statisticsMapper
				.getPileCharging(paramsModel);
		if (pileCharginglist != null && !pileCharginglist.isEmpty()) {
			List<String> areaList = new ArrayList<String>();
			String city = "";
			int count = 0;
			// 得到城市分组
			for (Map<Object, Object> tempObj : pileCharginglist) {
				city = (String) tempObj.get("城市");
				if (!areaList.contains(city)) {
					areaList.add(city);
				} else {
					break;
				}
			}
			int[] countArray = new int[areaList.size()];
			for (Map<Object, Object> tempObj : pileCharginglist) {
				city = (String) tempObj.get("城市");
				count = ((Long) tempObj.get("电桩数量")).intValue();
				countArray[areaList.indexOf(city)] = count;
			}
			// xAxis
			Map xAxisMap = new HashMap();
			xAxisMap.put("type", "category");
			xAxisMap.put("data", areaList);
			jsonModel.put("xAxis", xAxisMap);
			// series
			Map map = new HashMap();
			map.put("name", "电桩数量");
			map.put("type", "bar");
			map.put("data", countArray);
			List list = new ArrayList();
			list.add(map);
			jsonModel.put("series", list);
			obj.put("data2", jsonModel);
		}
	}

}
