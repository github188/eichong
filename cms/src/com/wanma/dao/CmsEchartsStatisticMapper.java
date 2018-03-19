package com.wanma.dao;

import java.util.List;
import java.util.Map;

public interface CmsEchartsStatisticMapper {
	/**
	 * 获取充电点数量
	 * @return
	 */
	public <K,V> Map<K,V> queryAllPointCount(Map<String, Object> params);

	/**
	 * 获取电桩数量
	 * @return
	 */
	public <K,V> Map<K,V> queryPileInfoCount(Map<String, Object> params);

	/**
	 * 获取用户数量
	 * @return
	 */
	public <K,V> Map<K,V> queryUserCount(Map<String, Object> params);

	/**
	 * 获取充电情况数量
	 * @return
	 */
	public <K,V> Map<K,V> queryChargeInfoCount(Map<String, Object> params);

	/**
	 * 获取枪头数量
	 * @return
	 */
	public <K,V> Map<K,V> queryHeadInfoCount(Map<String, Object> params);

	/**
	 * 抢头充电统计（累计）
	 * @param paramsModel
	 * @return
	 */
	public <K, V> List<Map<K, V>> getHeadChargeConsumeAll(Map<String, Object> paramsModel);

	public <K,V> Map<K,V> queryAllHeadCount(Map<String, Object> params);

	public <K, V> List<Map<K, V>> getMapData(Map<String, Object> params);

	public <K,V> Map<K,V> queryPileInfoCountInner(
			Map<String, Object> params);

	public <K,V> Map<K,V> queryAllHeadCountInner(Map<String, Object> params);

	public <K,V> Map<K,V> queryChargeInfoCountInner(Map<String, Object> params);

	public <K,V> Map<K,V> queryHeadInfoCountInner(Map<String, Object> params);
/**
 * 获得枪头的额定功率
 */
	public String getPilePowerSize(Map<String, Object> params);
}
