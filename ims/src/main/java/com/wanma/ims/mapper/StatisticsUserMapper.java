package com.wanma.ims.mapper;

import java.util.List;
import java.util.Map;

public interface StatisticsUserMapper {

	Map<String, Object> queryUserDataCount(Map<String, Object> params);

	List<Map<String, Object>> queryAppUserDataLine(Map<String, Object> params);

	List<Map<String, Object>> queryCardUserDataLine(Map<String, Object> params);

	List<Map<String, Object>> queryUserDataList(Map<String, Object> params);

	Long queryUserDataListCount(Map<String, Object> params);





}
