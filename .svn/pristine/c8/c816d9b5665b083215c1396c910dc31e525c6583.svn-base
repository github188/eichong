/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblElectricpilehead;

/**
 * 反馈表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface WebElectricpileheadMapper {

	/**
	 * 获取枪头数据
	 * 
	 * @param feedback
	 */
	public <K, V> List<Map<K, V>> find(TblElectricpilehead tblElectricpilehead);

	/**
	 * 根据id获取枪头数据
	 * 
	 * @param params
	 */
	public TblElectricpilehead get(Map<String, Object> params);

	/**
	 * 更新枪口状态 取消预约时，如果枪口状态为充电中，则不需要改变状态
	 * 
	 * @param params
	 */
	public int updateState(Map<String, Object> params);
}
