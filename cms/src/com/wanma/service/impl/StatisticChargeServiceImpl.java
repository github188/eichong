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
import com.wanma.dao.StatisticChargeMapper;
import com.wanma.service.StatisticChargeService;

@Service
public class StatisticChargeServiceImpl implements StatisticChargeService{
	
	private static Logger log = Logger.getLogger(StatisticChargeServiceImpl.class);
	
	@Autowired
	private StatisticChargeMapper statisticChargeMapper;

	@Override
	public Map<String, Object> queryChargeDataCount(Map<String,Object> params) {
		Map<String,Object> map = null;
		try{
			map =  statisticChargeMapper.selectChargeDataCount(params);
			if(ObjectUtil.isEmpty(map)){
				map = new HashMap<String,Object>();
			}
		}catch(Exception e){
			log.error(this.getClass() + ".queryChargeDataCount() error:"
					+ e.getLocalizedMessage());
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> queryChargeDataLine(Map<String,Object> params) {
		return queryChargeDataDetail(params);
	}

	@Override
	public Map<String, Object> queryChargeDataPie(Map<String,Object> params) {
		Map<String,Object> map = null;
		try{
			map =  statisticChargeMapper.selectChargeDataPie(params);
			if(ObjectUtil.isEmpty(map)){
				map = new HashMap<String,Object>();
			}
		}catch(Exception e){
			log.error(this.getClass() + ".queryChargeDataPie() error:"
					+ e.getLocalizedMessage());
		}
		return map;
	}
	
	@Override
	public Integer countChargeDataDetail(Map<String, Object> params) {
		return statisticChargeMapper.countChargeDataDetail(params);
	}
	@Override
	public List<Map<String, Object>> queryChargeDataDetail(Map<String,Object> params) {
		List<Map<String,Object>> list = null;
		try{
			list =  statisticChargeMapper.selectChargeDataDetail(params);
			if(CollectionUtils.isEmpty(list)){
				list = new ArrayList<Map<String,Object>>();
			}
		}catch(Exception e){
			log.error(this.getClass() + ".queryChargeDataCount() error:"
					+ e.getLocalizedMessage());
		}
		return list;
	}
}
