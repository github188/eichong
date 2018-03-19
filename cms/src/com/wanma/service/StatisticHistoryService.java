package com.wanma.service;

import java.util.List;
import java.util.Map;

import com.pub.model.Entity;

public interface StatisticHistoryService {

	public Map<String, Object> historyData(Entity entity);

	public List<Map<String, Object>> historyChargeDataForDay(Entity entity);

	public List<Map<String, Object>> historyDataForDay(Entity entity);

	public List<Map<String, Object>> historyDataList(Entity entity);

	public Long historyDataListCount(Entity entity);

}
