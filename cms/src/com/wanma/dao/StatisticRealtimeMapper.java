package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.pub.model.Entity;

public interface StatisticRealtimeMapper {

	public Map<String, Object> realtimeData();

	public List<Map<String, Object>> realtimeDataForHour();

	public List<Map<String, Object>> realtimeDataList();

}
