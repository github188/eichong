package com.wanma.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wanma.dao.CmsStatisticMapper;
import com.wanma.service.CmsEchartDataCountService;

@Service
public class CmsEchartDataCountServiceImpl implements CmsEchartDataCountService{
	@Autowired
	private CmsStatisticMapper cmsStatisticMapper;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getZcPeopleCount(Long userId) {
		JSONObject obj = new JSONObject();
		Map zcPeopleCountMap  = cmsStatisticMapper.getZcPeopleCount();
		if(userId==8231){
			zcPeopleCountMap.put("count", (Long)zcPeopleCountMap.get("count")*5);
		}
		obj.put("data", zcPeopleCountMap);
		return obj.toString();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getCzPeopleCount(Long userId) {
		JSONObject obj = new JSONObject();
		Map czPeopleCountMap  = cmsStatisticMapper.getCzPeopleCount();
		if(userId==8231){
			czPeopleCountMap.put("count", (Long)czPeopleCountMap.get("count")*5);
		}
		obj.put("data", czPeopleCountMap);
		return obj.toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getAllPileCount(Long userId) {
		JSONObject obj = new JSONObject();
		Map allpileCountMap  = cmsStatisticMapper.getAllPileCount();
		if(userId==8231){
			allpileCountMap.put("electricPositionCount", ((BigDecimal) allpileCountMap.get("electricPositionCount")).intValue()*5);
			allpileCountMap.put("pile_count", (Long)allpileCountMap.get("pile_count")*5);
		}
		obj.put("data", allpileCountMap);
		return obj.toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getAichongPileCount(Long userId) {
		JSONObject obj = new JSONObject();
		Map aichongPileCountMap  = cmsStatisticMapper.getAichongPileCount();
		if(userId==8231){
			aichongPileCountMap.put("electricPositionCount", ((BigDecimal) aichongPileCountMap.get("electricPositionCount")).intValue()*5);
			aichongPileCountMap.put("pile_count", (Long)aichongPileCountMap.get("pile_count")*5);
		}
		obj.put("data",aichongPileCountMap);
		return obj.toString();
	}


}
