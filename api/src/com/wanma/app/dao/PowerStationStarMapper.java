package com.wanma.app.dao;

import java.util.List;
import java.util.Map;


public interface PowerStationStarMapper {
	
	/**
	 * 保存星级评价
	 * @param params
	 */
	public void insert(Map<String, Object> params);
	
	/**
	 * 统计电站星级
	 * @param psId
	 * @return
	 */
	public List<?> countPsStarByPowerId(int psId);
	
	/**
	 * 根据用户id和电站id统计发表星评的次数
	 * @param params
	 * @return
	 */
	public int getStarNumByUserIdPsId(Map<String,Object> params);
}
