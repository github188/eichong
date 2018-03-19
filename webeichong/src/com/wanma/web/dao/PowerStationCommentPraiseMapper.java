package com.wanma.web.dao;

import java.util.Map;


public interface PowerStationCommentPraiseMapper {
	/**
	 * 保存点赞信息
	 * @param params
	 */
	public void insert(Map<String, Object> params);
	/**
	 * 修改点赞数量
	 * @param params
	 */
	public void updatePraiseCount(Map<String, Object> params);
	
	public int getMyPraiseCount(Map<String, Object> param);
}
