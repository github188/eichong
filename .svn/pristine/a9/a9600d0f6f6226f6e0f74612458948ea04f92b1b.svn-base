package com.wanma.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wanma.model.ElectricPileComment;


public interface WebElecPileCommentMapper {
	/**
	 * 根据电桩id统计该桩的评论总数
	 * @param psc_PsId
	 * @return
	 */
	public long countEpCommentsByPowerId(Map<String, Object> params);
	
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
	public List<HashMap<String, Object>> getEpCommentsPageList(Map<String, Object> params);
	
	/**
	 * 保存评论
	 * @param params
	 */
	public void insert(ElectricPileComment params);
	
	/**
	 * 删除评论
	 * @param cId
	 */
	public void delete(int cId);

	public List<HashMap<String, Object>> getEpReplyCommentsList(String param);


	public void updateEpReplyCount(Map<String, Object> params);

	public void insertEpReply(Map<String, Object> params);

	public Object getEpCommentsRowNum(Map<String, Object> params);


}
