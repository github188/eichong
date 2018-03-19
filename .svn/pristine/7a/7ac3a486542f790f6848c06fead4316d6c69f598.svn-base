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
import com.wanma.model.TblRateinformation;

/**
 * 反馈表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface WebElectricpileMapper {

	/**
	 * 获取电桩数据
	 * 
	 * @param feedback
	 */
	public <K, V> List<Map<K, V>> findElectricpile(Map<K, V> params);
	/**电桩数据分页使用*/
	public <K, V> long countElectricPile(Map<K, V> params);


	/**
	 * 获取电桩数据
	 * 
	 * @param feedback
	 */
	public <T, K, V> List<T> getElectricpileForList(Map<K, V> params);
	/**电桩数据分页使用*/
	public <K, V> long countElectricPileForList(Map<K, V> params);
	
	/**
	 * 获取地图模式电桩数据
	 * 
	 * @param feedback
	 */
	public <K, V> List<Map<K, V>> getElectricpileForMap(TblElectricpile tblElectricpile);

	/**
	 * 通过Id获取电桩数据
	 * 
	 * @param feedback
	 */
	public <K, V> List<Map<K, V>> getElectricpileById(TblPowerstation tblPowerstation);

	/**
	 * 通过Id获取电桩数据
	 * 
	 * @param feedback
	 */
	public <K, V> Map<K, V> findOne(TblElectricpile tblElectricpile);

	/**
	 * 通过枪头Id获取电桩数据
	 * 
	 * @param params
	 */
	public <T, K, V> T getbyPkElecpileHead(Map<K, V> params);
	/**
	 * 获取相关电桩数据
	 * 
	 * @param feedback
	 */
	public <K, V> List<Map<K, V>> getElectricpile(Map<K, V> params);
	
	/**
	 * 插入个人中心桩体分享信息
	 * @param params
	 */
	public void insert(TblElectricpile tblElectricpile);
	public Map<String, Object> getTblRateinformation(String elictric);
	public Map<String, Object> findDetailById(TblElectricpile tblElectricpile);
	/**
	 * 通过用户帐号喜好获取电桩筛选条件
	 */
	public TblElectricpile getPileConditionByUserId(Long userId);

}
