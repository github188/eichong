package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pub.model.Entity;
import com.wanma.dao.StatisticHistoryMapper;
import com.wanma.service.StatisticHistoryService;

@Service
public class StatisticHistoryServiceImpl implements StatisticHistoryService{
	@Autowired
	private StatisticHistoryMapper historyMapper;
	
	@Override
	public Map<String, Object> historyData(Entity entity) {
		return historyMapper.historyData(entity);
	}

	@Override
	public List<Map<String, Object>> historyChargeDataForDay(Entity entity) {
		return historyMapper.historyChargeDataForDay(entity);
	}

	@Override
	public List<Map<String, Object>> historyDataForDay(Entity entity) {
		return historyMapper.historyDataForDay(entity);
	}

	@Override
	public List<Map<String, Object>> historyDataList(Entity entity) {
		return historyMapper.historyDataList(entity);
	}

	@Override
	public Long historyDataListCount(Entity entity) {
		return historyMapper.historyDataListCount(entity);
	}

}
