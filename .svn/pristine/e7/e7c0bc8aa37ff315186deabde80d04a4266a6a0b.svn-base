package com.wanma.app.dao;

import java.util.List;
import java.util.Map;


public interface AppElecPileStarMapper {
	
	/**
	 * 保存星级评价
	 * @param params
	 */
	public void insert(Map<String, Object> params);
	/**
	 * 根据电桩id获取平均星评
	 * @param epId
	 * @return
	 */
	public List<?> countStarByElecPileId(int epId);
	
	/**
	 * 根据电桩id获取所有星评总和
	 * @param epId
	 * @return
	 */
	public List<?> countAllStarByElecPileId(int epId);
	/**
	 * 根据桩id和用户id获取该用户对桩的星评数
	 * @param param
	 * @return
	 */
	public int getNumByUidEpid(Map<String, Object> params);
}
