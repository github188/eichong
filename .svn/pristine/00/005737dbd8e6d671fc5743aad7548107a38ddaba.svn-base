package com.wanma.ims.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.ims.mapper.StatisticsUserMapper;
import com.wanma.ims.service.StatisticsUserService;


@Service("statisticsUserService")
public class StatisticsUserServiceImpl implements StatisticsUserService{
	@Autowired
	private StatisticsUserMapper statisticsUserMapper;
	@Autowired
	private StatisticsAppServiceImpl statisticsAppServiceImpl;
	
	@Override
	public Map<String, Object> queryUserDataCount(Map<String, Object> params) {
		return statisticsUserMapper.queryUserDataCount(params);
	}


	@Override
	public List<Map<String, Object>> queryUserDataLine(Map<String, Object> params) throws ParseException {
		if("A".equals(params.get("type"))){
			List<Map<String, Object>> appUserList = statisticsUserMapper.queryAppUserDataLine(params);
			return statisticsAppServiceImpl.initList(params.get("beginTime").toString(), params.get("endTime").toString(), appUserList, "time","countTotal");
		}else {
			List<Map<String, Object>> cardUserList = statisticsUserMapper.queryCardUserDataLine(params);
			return statisticsAppServiceImpl.initList(params.get("beginTime").toString(), params.get("endTime").toString(), cardUserList, "time","countTotal");
		}
	}


	@Override
	public List<Map<String, Object>> queryUserDataList(Map<String, Object> params) throws ParseException {
		return statisticsUserMapper.queryUserDataList(params);
		
	}


	@Override
	public Long queryUserDataListCount(Map<String, Object> params) {
		return statisticsUserMapper.queryUserDataListCount(params);
	}



}
