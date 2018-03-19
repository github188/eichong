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

import com.wanma.ims.mapper.StatisticsAppMapper;
import com.wanma.ims.service.StatisticsAppService;


@Service("statisticsAppService")
public class StatisticsAppServiceImpl implements StatisticsAppService{
	@Autowired
	private StatisticsAppMapper statisticsAppMapper;

	@Override
	public Map<String, Object> queryAppRechargeDataCount(Map<String, Object> params) {
		return statisticsAppMapper.queryAppRechargeDataCount(params);
	}

	@Override
	public List<Map<String, Object>> queryAppRechargeDataLine(Map<String, Object> params) throws ParseException {
		List<Map<String, Object>> appRechargeList = statisticsAppMapper.queryAppRechargeDataLine(params);
		return initList(params.get("beginTime").toString(), params.get("endTime").toString(), appRechargeList,"time","countTotalMoney");
	}
	/**
	 *  折线图数据加0
	 * @param beginTime
	 * @param endTime
	 * @param list
	 * @return
	 * @throws ParseException 
	 */
	public List<Map<String, Object>> initList(String beginTime,String endTime,List<Map<String, Object>> list,String xAxis,String yAxis) throws ParseException {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		 Date beginDate = sdf.parse(beginTime); 
		 Calendar beginCalendar = Calendar.getInstance();
		 beginCalendar.setTime(beginDate);
		 Date endDate = sdf.parse(endTime); 
		 Calendar endCalendar = Calendar.getInstance();
		 endCalendar.setTime(endDate);
		 do {
				Map<String, Object> listMap = new  HashMap<String, Object>();
				for(Map<String, Object> map:list){
					if(sdf.format(beginCalendar.getTime()).equals(map.get(xAxis))){
						listMap.put(yAxis, map.get(yAxis));
					}
				}
				if (!listMap.containsKey(yAxis)) {
					listMap.put(yAxis, 0);
				}
				listMap.put(xAxis, sdf.format(beginCalendar.getTime()));
				resultList.add(listMap);
				beginCalendar.add(Calendar.DAY_OF_MONTH, 1);
			} while (beginCalendar.getTime().compareTo(endCalendar.getTime())<1);
		return resultList;
		
		
	}
	@Override
	public List<Map<String, Object>> queryAppRechargeDataList(Map<String, Object> params) {
		return statisticsAppMapper.queryAppRechargeDataList(params);
	}

	@Override
	public Long queryAppRechargeDataListCount(Map<String, Object> params) {
		return statisticsAppMapper.queryAppRechargeDataListCount(params);
	}
	
	
	



}
