package com.wanma.ims.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


/**
 * 用户统计
 */
public interface StatisticsUserService {

	public Map<String,Object> queryUserDataCount(Map<String, Object> params);

	public List<Map<String, Object>> queryUserDataLine(Map<String, Object> params) throws ParseException;

	public List<Map<String, Object>> queryUserDataList(Map<String, Object> params) throws ParseException;

	public Long queryUserDataListCount(Map<String, Object> params);
	
}
