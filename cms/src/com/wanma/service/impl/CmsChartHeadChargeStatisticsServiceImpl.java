package com.wanma.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.base.common.JsonUtil;
import com.wanma.dao.CmsEchartsStatisticMapper;

@Service
public class CmsChartHeadChargeStatisticsServiceImpl extends CmsEchartsV2ServiceImpl {
	@Autowired
	CmsEchartsStatisticMapper statisticsMapper;
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setData(JSONObject obj, Map<String, Object> paramsModel) {
		JSONObject jsonModel = JsonUtil.getJsonObject(path
				+ "/json/headConsumeChart.json");
		List<Map<Object, Object>> pileConsumelist=statisticsMapper.getHeadChargeConsumeAll(paramsModel);
		if (pileConsumelist != null &&!pileConsumelist.isEmpty()){
			List<String> monthList=new ArrayList<String>();
			String month="";
			String earns="";
			String quantity="";
			int count=0;
			//得到城市分组
			for(Map<Object, Object> tempObj:pileConsumelist){
				month=String.valueOf(tempObj.get("收益月份")).replace("-", "");
				if(!monthList.contains(month)){
					monthList.add(month);
				}else{
					break;
				}
			}
			String[] earnsArray=new String[monthList.size()];
			String[] quantityArray=new String[monthList.size()];
			int[] countArray=new int[monthList.size()];
			for(Map<Object, Object> tempObj:pileConsumelist){
				month=String.valueOf(tempObj.get("收益月份")).replace("-", "");
				earns=String.valueOf(tempObj.get("充电金额"));
				quantity=String.valueOf(tempObj.get("充电度数"));
				count=((Long) tempObj.get("充电次数")).intValue();
				earnsArray[monthList.indexOf(month)]=earns;
				quantityArray[monthList.indexOf(month)]=quantity;
				countArray[monthList.indexOf(month)]=count;
			}
			//xAxis
			Map xAxisMap = new HashMap();
			xAxisMap.put("type", "category");
			xAxisMap.put("data", monthList);
			jsonModel.put("xAxis", xAxisMap);
			//series
			Map map = new HashMap();
			map.put("name", "充电金额");
			map.put("type", "line");
			map.put("data", earnsArray);
			Map map2 = new HashMap();
			map2.put("name", "充电度数");
			map2.put("type", "line");
			map2.put("data", quantityArray);
			Map map3 = new HashMap();
			map3.put("name", "充电次数");
			map3.put("type", "line");
			map3.put("data", countArray);
			List list = new ArrayList();
			list.add(map);
			list.add(map2);
			list.add(map3);
			jsonModel.put("series", list);
			obj.put("data", jsonModel);
		}	
	}
}
