/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.ElectricPileList;

/**
 * 反馈信息业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface WebElectricPileListService {

	/**
	 * 获取地图模式电桩列表
	 * @param electricTypeId 汽车类型ID
	 * @param distance 距离 m
	 * @param price 价格
	 * @param evaluate 好评
	 */
	public List<ElectricPileList> getElectricPileList(Map<String, Object> params);
	public long countPowerstation(Map<String, Object> params);
	public long countElectricPile(Map<String, Object> params);
	
	/**
	 * 获取相关电桩列表
	 * @param longitude 经度
	 * @param latitude 纬度
	 */
	public List<Map<String, Object>> getRelatedList(Map<String, Object> params);

	/**
	 * 获取列表模式电桩列表
	 * @param electricTypeId 汽车类型ID
	 * @param distance 距离 m
	 * @param price 价格
	 * @param evaluate 好评
	 */
	public List<ElectricPileList> getElectricPileForList(Map<String, Object> params);
	public long countElectricPileForList(Map<String, Object> params);

}
