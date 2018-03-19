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
import com.wanma.dao.StatisticFaultMapper;
import com.wanma.service.StatisticFaultService;

@Service
public class StatisticFaultServiceImpl implements StatisticFaultService{
	
	private static Logger log = Logger.getLogger(StatisticFaultServiceImpl.class);
	
	@Autowired
	private StatisticFaultMapper statisticFaultMapper;

	@Override
	public Map<String, Object> queryFaultDataCount(Map<String, Object> params) {
		Map<String,Object> map = null;
		try{
			map =  statisticFaultMapper.selectFaultDataCount(params);
			Map<String,Object> offlineMap = statisticFaultMapper.selectFaultOfflineCount(params);
			if(ObjectUtil.isEmpty(map)){
				map = new HashMap<String,Object>();
			}
			if(ObjectUtil.isNotEmpty(offlineMap)){
				map.put("v2", offlineMap.get("v2"));
			}
		}catch(Exception e){
			log.error(this.getClass() + ".queryFaultDataCount() error:"
					+ e.getLocalizedMessage());
		}
		return map;
	}

	@Override
	public Integer countChargeFaultDetail(
			Map<String, Object> params) {
		return statisticFaultMapper.countFaultDataDetail(params);
	}
	
	@Override
	public List<Map<String, Object>> queryChargeFaultDetail(Map<String, Object> params) {
		List<Map<String,Object>> list = null;
		try{
			list =  statisticFaultMapper.selectFaultDataDetail(params);
			if(CollectionUtils.isEmpty(list)){
				list = new ArrayList<Map<String,Object>>();
			}
		}catch(Exception e){
			log.error(this.getClass() + ".queryChargeFaultDetail() error:"
					+ e.getLocalizedMessage());
		}
		return list;
	}

	

	
	
}
