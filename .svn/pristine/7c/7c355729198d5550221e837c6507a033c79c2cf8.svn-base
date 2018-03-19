package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.bluemobi.product.model.echarts.EchartsParamModel;

public interface CmsStatisticMapper {
	/**
	 * 按时间统计注册人数
	 * @param limitDate
	 */
	public <K, V> List<Map<K, V>> queryMonthPeopleCountList(EchartsParamModel paramsModel);
	/**
	 * 按地区统计注册人数
	 * @param 
	 */
	public <K, V> List<Map<K, V>> getRegisterCountByArea();
	/**
	 * 按来源统计注册人数
	 * @param 
	 */
	
	public <K, V> List<Map<K, V>> queryZcFromPeopleCountList(EchartsParamModel paramsModel);
	/**
	 * 用户消费类型统计
	 * @param 
	 */						
	public <K, V> List<Map<K, V>> queryPeopleConsumptionList(EchartsParamModel paramsModel);
	/**
	 * 用户充值统计
	 * @param 
	 */						
	public <K, V> List<Map<K, V>> queryPeopleChargeList(EchartsParamModel paramsModel);
	
	/**
	 * 电桩数量统计
	 * @param 
	 */
	public <K, V> List<Map<K, V>> getPileCount(EchartsParamModel paramsModel);
	/**
	 * 电桩分布统计
	 * @param string 
	 * @param 
	 */
	public <K, V> List<Map<K, V>> getDistributePileByArea(EchartsParamModel paramsModel);
	/**
	 * 按区新增分享电桩统计
	 * @param 
	 */
	public <K, V> List<Map<K, V>> getNewSharedPileByArea();
	/**
	 * 按区新增专享电桩统计
	 * @param 
	 */
	public <K, V> List<Map<K, V>> getNewUnSharedPileByArea();
	
	/**
	 * 电桩消费统计
	 * @param 
	 */
	public <K, V> List<Map<K, V>> getPileConsume(EchartsParamModel model);
	/**
	 * 电桩故障统计
	 * @param 
	 */
	public <K, V> List<Map<K, V>> getPileFault(EchartsParamModel model);
	/**
	 * 电桩充电中统计
	 * @param 
	 */
	public <K, V> List<Map<K, V>> getPileCharging(EchartsParamModel paramsModel);
	/**
	 * 电桩预约中统计
	 * @param 
	 */
	public <K, V> List<Map<K, V>> getPileBespoking(EchartsParamModel paramsModel);
	/**
	 * 电桩分享数量省份统计
	 * @param paramsModel 
	 * @param 
	 */
	public <K, V> List<Map<K, V>> getPileProviceCount(EchartsParamModel paramsModel);
	
	
	/**
	 * 电桩预约统计
	 * @param paramsModel
	 * @return
	 */
	public <K,V>List<Map<K,V>> queryPileBespokeCountList(EchartsParamModel paramsModel);
	
	/**
	 * 数据挖掘电桩充电相关查询
	 * @param paramsModel
	 * @return
	 */
	
	public <K,V>List<Map<K,V>> queryPileChargeCountList(EchartsParamModel paramsModel);
	public <K,V> Map<K,V> getZcPeopleCount();
	public <K,V> Map<K,V> getCzPeopleCount();
	public <K,V> Map<K,V> getAllPileCount();
	public <K,V> Map<K,V> getAichongPileCount();
	public List<Map<Object, Object>> getPileProviceAndCompanyCount();
	public List<Map<Object, Object>> getPileProviceCountV2(
			EchartsParamModel paramsModel);
}
