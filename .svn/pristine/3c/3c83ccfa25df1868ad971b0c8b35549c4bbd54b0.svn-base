package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pub.model.Entity;
import com.wanma.dao.StatisticRealtimeMapper;
import com.wanma.service.StatisticRealtimeService;

@Service
public class StatisticRealtimeServiceImpl implements StatisticRealtimeService{

	@Autowired
	private StatisticRealtimeMapper realtimeMapper;
	
	@Override
	public Map<String, Object> realtimeData() {
		return realtimeMapper.realtimeData();
	}

	@Override
	public List<Map<String, Object>> realtimeDataForHour() {
		return realtimeMapper.realtimeDataForHour();
	}

	@Override
	public List<Map<String, Object>> realtimeDataList() {
		return realtimeMapper.realtimeDataList();
	}

}
