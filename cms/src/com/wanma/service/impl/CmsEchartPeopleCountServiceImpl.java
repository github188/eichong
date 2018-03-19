package com.wanma.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.base.common.JsonUtil;
import com.wanma.dao.CmsStatisticMapper;
import com.wanma.model.echarts.EchartsParamModel;

@Service
public class CmsEchartPeopleCountServiceImpl extends CmsEchartsServiceImpl {
	@Autowired
	private CmsStatisticMapper cmsStatisticMapper;

	@Override
	public void setData(JSONObject obj, EchartsParamModel paramsModel) {
		setDate1(obj, paramsModel);
		setDate2temp(obj, paramsModel);
	}

	private void setDate1(JSONObject obj, EchartsParamModel paramsModel) {
		List<Map<String, Object>> monthPeopleCountList = cmsStatisticMapper
				.queryMonthPeopleCountList(paramsModel);
		if (monthPeopleCountList != null && monthPeopleCountList.size() > 0) {
			int initLength = 12;
			if("Y".equals(paramsModel.getFirstLoadFlag()))
				initLength = 6;
			JSONObject jsonModel = JsonUtil.getJsonObject(path
					+ "/json/datePeopleBar.json");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", "人数");
			map.put("type", "bar");
			double[] arr1 = new double[monthPeopleCountList.size() <= initLength ? initLength
					: monthPeopleCountList.size()];
			String[] strs1 = new String[monthPeopleCountList.size() <= initLength ? initLength
					: monthPeopleCountList.size()];
			int countListLength = monthPeopleCountList.size();
			for (int i = 0; i < countListLength; i++) {
				Map<String, Object> beanMap = monthPeopleCountList.get(i);
				arr1[i] = Double.valueOf(beanMap.get("count").toString());
				strs1[i] = beanMap.get("month").toString();
			}
			if (strs1[initLength - 1] == null) {
				int countListlength = monthPeopleCountList.size();
				for (int i = countListlength; i < initLength; i++) {
					strs1[i] = "";
					arr1[i] = 0;
				}
			}
			map.put("data", arr1);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list.add(map);
			Map<String, Object> map1 = new HashMap<String, Object>();
			// 分隔年月
			//obj.put("year", remakeDate(strs1));
			map1.put("type", "category");
			map1.put("data", strs1);
			List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
			list1.add(map1);
			jsonModel.put("xAxis", list1);
			jsonModel.put("series", list);
			obj.put("data1", jsonModel);
		}
	}

	@SuppressWarnings("unused")
	private void setDate2(JSONObject obj, EchartsParamModel paramsModel) {
		List<Map<String, Object>> monthPeopleCountList = cmsStatisticMapper
				.queryZcFromPeopleCountList(paramsModel);
		// 报表样式JSON格式
		if (monthPeopleCountList != null && monthPeopleCountList.size() > 0) {
			int initLength = 5;
			JSONObject jsonModel = JsonUtil.getJsonObject(path
					+ "/json/zcFromPeopleBar.json");
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < monthPeopleCountList.size(); i++) {
				double[] arr1 = new double[1];
				Map<String, Object> beanMap = monthPeopleCountList.get(i);
				arr1[0] = Double.valueOf(beanMap.get("count").toString());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", beanMap.get("zcfrom"));
				map.put("type", "bar");
				map.put("data", arr1);
				list.add(map);
			}
			if (list.size() < initLength) {
				for (int i = list.size(); i < initLength; i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					double[] arr1 = new double[1];
					arr1[0] = 0;
					map.put("name", "");
					map.put("type", "bar");
					map.put("data", arr1);
					list.add(map);
				}
			}
			Map<String, Object> map1 = new HashMap<String, Object>();
			String[] strs1 = new String[1];
			strs1[0] = "";
			map1.put("type", "category");
			map1.put("data", strs1);
			jsonModel.put("yAxis", map1);
			jsonModel.put("series", list);
			obj.put("data2", jsonModel);
		}
	}

	private void setDate2temp(JSONObject obj, EchartsParamModel paramsModel) {
		List<Map<String, Object>> beanList = cmsStatisticMapper
				.queryZcFromPeopleCountList(paramsModel);
		// 报表样式JSON格式
		if (beanList != null && beanList.size() > 0) {
			JSONObject jsonModel = JsonUtil.getJsonObject(path
					+ "/json/zcFromPeopleBar.json");
			int initMonthLength = 12;
			List<Object> monthList = new ArrayList<Object>();
			List<Object> typeList = new ArrayList<Object>();
			Map<String, Double> dataMap = new HashMap<String, Double>();
			for (Map<String, Object> beanMap : beanList) {
				monthList.add(beanMap.get("month"));
				typeList.add(beanMap.get("zcfrom"));
				dataMap.put(beanMap.get("month") + "" + beanMap.get("zcfrom"),
						Double.valueOf(beanMap.get("count").toString()));

			}
			// X轴月份序列
			Object[] monthGroup = null;
			if ("Y".equals(paramsModel.getFirstLoadFlag()))
				monthGroup = makeBeginMonthGroup(monthList);
			else
				monthGroup = makeGroup(monthList, initMonthLength);
			// 种类去重
			
			Set<Object> typeSet = new HashSet<Object>();
			typeSet.addAll(typeList);
			Object[] typeGroup = typeSet.toArray();
			// Y轴数据
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			for (Object type : typeGroup) {
				makeData((String) type, monthGroup, dataMap, dataList);
			}
			//分隔年月
			//obj.put("year", remakeDate(monthGroup));
			Map<String, Object> monthMap = new HashMap<String, Object>();
			monthMap.put("type", "category");
			monthMap.put("data", monthGroup);
			jsonModel.put("xAxis", monthMap);
			jsonModel.put("series", dataList);
			
			obj.put("data2", jsonModel);
		}
	}
	
	/**
	 * 组装消费数据，Y轴左轴为充值次数，右轴为充值金额
	 * 
	 * @param list
	 * @param initLength
	 * @return
	 */
	private void makeData(String type, Object[] monthGroup,
			Map<String, Double> dataMap, List<Map<String, Object>> dataList) {
		double[] dataGroupCount = new double[monthGroup.length];
		for (int i = 0; i < monthGroup.length; i++) {
			Double dataCount = dataMap.get(monthGroup[i] + type);
			if (dataCount != null) {
				dataGroupCount[i] = dataCount;
			} else {
				dataGroupCount[i] = 0;
			}
		}
		Map<String, Object> dataMapEachYleft = new HashMap<String, Object>();
		dataMapEachYleft.put("name", type);
		dataMapEachYleft.put("type", "bar");
		dataMapEachYleft.put("data", dataGroupCount);
		dataList.add(dataMapEachYleft);
	}

}
