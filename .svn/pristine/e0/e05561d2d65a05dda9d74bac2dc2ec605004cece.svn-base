package com.wanma.app.dao;

import java.util.List;
import java.util.Map;


public interface AppElecPileCommentMapper {
	/**
	 * 根据电站id统计该站的评论总数
	 * @param psc_PsId
	 * @return
	 */
	public List<?> countEpCommentsByPowerId(int epc_EpId);
	
	/**
	 * 根据电站id获取电站的所有评论
	 * @param psc_PsId
	 * @return
	 */
	public List<?> getAllCommentsByEpId(int psc_PsId);
	
	/**
	 * 获取电站评论的分页数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getEpCommentsPageList(Map<String, Object> params);
	
	/**
	 * 保存评论
	 * @param params
	 */
	public void insert(Map<String, Object> params);
	
	/**
	 * 删除评论
	 * @param cId
	 */
	public void delete(int cId);
}
