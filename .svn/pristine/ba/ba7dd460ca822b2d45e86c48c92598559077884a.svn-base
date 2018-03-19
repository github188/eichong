package com.wanma.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.model.echarts.EchartsParamModel;
import com.wanma.dao.CmsStatisticMapper;
import com.wanma.web.support.utils.JsonUtil;

@Service
public class CmsEchartPeopleZcServiceImpl extends CmsEchartsServiceImpl {
	@Autowired
	private CmsStatisticMapper cmsStatisticMapper;

	@Override
	public void setData(JSONObject obj, EchartsParamModel paramsModel) {
		setDate(obj, paramsModel);
	}

	private void setDate(JSONObject obj, EchartsParamModel paramsModel) {
		List<Map<String, Object>> monthPeopleCountList = cmsStatisticMapper
				.queryMonthPeopleCountList(paramsModel);
		String userId  = paramsModel.getUserId();
		if (monthPeopleCountList != null && monthPeopleCountList.size() > 0) {
			int initLength = 12;
			JSONObject jsonModel = JsonUtil.getJsonObject(path
					+ "/json/datePeopleBar.json");
			double[] arr1 = new double[monthPeopleCountList.size() <= initLength ? initLength
					: monthPeopleCountList.size()];
			double[] arr1Temp = new double[6];
			String[] strs1 = new String[monthPeopleCountList.size() <= initLength ? initLength
					: monthPeopleCountList.size()];
			int countListLength = monthPeopleCountList.size();
			String[] strs2 = new String[arr1Temp.length];
			for (int i = 0; i < countListLength; i++) {
				Map<String, Object> beanMap = monthPeopleCountList.get(i);
				arr1[i] = Double.valueOf(beanMap.get("count").toString());
				strs1[i] = beanMap.get("month").toString();
				if(countListLength > strs2.length){
					if(i>=countListLength-6){
						strs2[i-(countListLength-6)] = beanMap.get("month").toString();
						arr1Temp[i-(countListLength-6)] = Double.valueOf(beanMap.get("count").toString());
					}
				}else{
					strs2[i] = strs1[i];
					arr1Temp[i] = arr1[i];
				}
			}
			if (strs1[initLength - 1] == null) {
				int countListlength = monthPeopleCountList.size();
				for (int i = countListlength; i < initLength; i++) {
					strs1[i] = "";
					arr1[i] = 0;
						
				}
			}
			for(int i=0;i<strs2.length;i++){
				if(strs2[i]==null){
					strs2[i] = "";
					arr1Temp[i] = 0;
				}
			}
			//大图图数据
			Map<String, Object> map = new HashMap<String, Object>();
			if("8231".equals(userId)){//当参观者账号登录的时候需要
				for(int i=0;i<arr1.length;i++){
					arr1[i] = arr1[i]*5;
				}
				for(int i=0;i<arr1Temp.length;i++){
					arr1Temp[i] = arr1Temp[i]*5;
				}
			}
			map.put("name", "人数");
			map.put("type", "bar");
			map.put("data", arr1);
			//小图数据
			Map<String, Object> mapTemp = new HashMap<String, Object>();
			mapTemp.put("name", "人数");
			mapTemp.put("type", "bar");
			mapTemp.put("data", arr1Temp);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list.add(map);
			List<Map<String, Object>> listTemp = new ArrayList<Map<String, Object>>();
			listTemp.add(mapTemp);
			Map<String, Object> map1 = new HashMap<String, Object>();
			// 分隔年月
			// obj.put("year", remakeDate(strs1));
			map1.put("type", "category");
			map1.put("data", strs2);
			List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
			list1.add(map1);
			jsonModel.put("xAxis", list1);
			jsonModel.put("series", listTemp);
			jsonModel.put("innerMonthGroup", strs1);
			jsonModel.put("tempDataList",list);
			obj.put("data1", jsonModel);
		}
	}

}
