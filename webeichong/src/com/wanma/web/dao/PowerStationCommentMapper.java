package com.wanma.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wanma.model.PowerStationComment;


public interface PowerStationCommentMapper {
	/**
	 * 根据电站id统计该站的评论总数
	 * @param psc_PsId
	 * @return
	 */
	public long countPsCommentsByPowerId(Map<String, Object> params);
	
	/**
	 * 根据电站id获取电站的所有评论
	 * @param psc_PsId
	 * @return
	 */
	public List<?> getAllCommentsByPowerId(int psc_PsId);
	
	/**
	 * 获取电站评论的分页数据
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> getPsCommentsPageList(Map<String, Object> params);
	
	/**
	 * 获取电站评论回复的数据
	 * @param param
	 * @return
	 */
	public List<HashMap<String, Object>> getPsReplyCommentsList(String param);
	
	/**
	 * 保存评论
	 * @param params
	 */
	public int insert(PowerStationComment params);
	
	/**
	 * 删除评论
	 * @param cId
	 */
	public void delete(int cId);

	public void updatePsReplyCount(Map<String, Object> params);

	public void insertReply(Map<String, Object> params);

	public int getPsCommentsPageNum(Map<String, Object> params);

	public Object getPsCommentsRowNum(Map<String, Object> params);
}
