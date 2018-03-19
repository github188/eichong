package com.wanma.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface WebElecPileStarMapper {
	
	/**
	 * 保存星级评价
	 * @param params
	 */
	public void insert(Map<String, Object> params);
	/**
	 * 根据电桩id获取星评
	 * @param epId
	 * @return
	 */
	public List<?> countStarByElecPileId(int epId);
	/**
	 * 获取星评
	 * @param params
	 * @return
	 */
	
	public HashMap<String,Object> getEpCommentStar(Map<String, Object> params);
}
