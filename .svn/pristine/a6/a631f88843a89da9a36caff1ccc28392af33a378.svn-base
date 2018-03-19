package com.wanma.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.util.ObjectUtil;
import com.wanma.dao.StatisticAssertMapper;
import com.wanma.service.StatisticAssertService;

@Service
public class StatisticAssertServiceImpl implements StatisticAssertService{
	
	private static Logger log = Logger.getLogger(StatisticAssertServiceImpl.class);
	
	@Autowired
	private StatisticAssertMapper statisticAssertMapper;

	@Override
	public Map<String, Object> queryAssertDataCount() {
		Map<String,Object> map = null;
		try{
			map =  statisticAssertMapper.selectAssertDataCount();
			if(ObjectUtil.isEmpty(map)){
				map = new HashMap<String,Object>();
			}
		}catch(Exception e){
			log.error(this.getClass() + ".queryAssertDataCount() error:"
					+ e.getLocalizedMessage());
		}
		return map;
		
	}

	@Override
	public List<Map<String, Object>> queryAssertDataMap() {
		List<Map<String,Object>> list = null;
		try{
			list =  statisticAssertMapper.selectAssertDataMap();
			if(CollectionUtils.isEmpty(list)){
				list = new ArrayList<Map<String,Object>>();
			}
		}catch(Exception e){
			log.error(this.getClass() + ".queryAssertDataMap() error:"
					+ e.getLocalizedMessage());
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> queryAssertDataDetail() {
		List<Map<String,Object>> list = null;
		try{
			list =  statisticAssertMapper.selectAssertDataDetail();
			if(CollectionUtils.isEmpty(list)){
				list = new ArrayList<Map<String,Object>>();
			}
		}catch(Exception e){
			log.error(this.getClass() + ".queryAssertDataDetail() error:"
					+ e.getLocalizedMessage());
		}
		return list;
	}
}
