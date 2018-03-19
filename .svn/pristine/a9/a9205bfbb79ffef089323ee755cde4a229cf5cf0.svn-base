package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pub.model.Entity;
import com.wanma.dao.StatisticRegisteMapper;
import com.wanma.service.StatisticRegisteService;

@Service
public class StatisticRegisteServiceImpl implements StatisticRegisteService{
	@Autowired
	private StatisticRegisteMapper registeMapper;
	
	@Override
	public Map<String, Object> userRegisteCount() {
		return registeMapper.userRegisteCount();
	}

	@Override
	public List<Map<String, Object>> userRegisteLatest() {
		return registeMapper.userRegisteLatest();
	}

	@Override
	public List<Map<String, Object>> userRegisteForMonth() {
		return registeMapper.userRegisteForMonth();
	}

	@Override
	public List<Map<String, Object>> userRegisteList(Entity pager) {
		return registeMapper.userRegisteList(pager);
	}

	@Override
	public Long userRegisteListCount() {
		return registeMapper.userRegisteListCount();
	}

}
