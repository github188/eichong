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

import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;


/**
 * 反馈表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface WebPowerstationMapper {

	/**
	 * 获取电站数据
	 * @param feedback
	 */
	public <K, V> List<Map<K, V>> findPowerstation(Map<K, V> params);
	/**电站数据分页使用*/
	public <K, V> long countPowerstation(Map<K, V> params);
	/**
	 * 获取地图模式电站数据
	 * @param feedback
	 */
	public <K, V> List<Map<K, V>> getPowerstationForMap(TblPowerstation tblPowerstation);
	
	/**
	 * 通过主键获取电桩信息
	 * @param feedback
	 */
	public <K, V> Map<K, V> getPowerstationById(TblPowerstation tblPowerstation);
	/**
	 * 获取相关电站数据
	 * @param feedback
	 */
	public <K, V> List<Map<K, V>> getPowerstation(Map<K, V> params);
}
