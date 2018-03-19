package com.wanma.web.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.model.ElectricPileComment;
import com.wanma.web.dao.WebElecPileCommentMapper;
import com.wanma.web.dao.WebElecpileCommentPraiseMapper;

@Service
public class WebElecPileCommentServiceImpl {
	@Autowired
	private WebElecPileCommentMapper elcMapper;
	@Autowired
	private WebElecpileCommentPraiseMapper  elPraiseMapper;
	/** 
	 * 获取电站评论分页列表
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> getEpCommentsPageList(Map<String, Object> params){
		return elcMapper.getEpCommentsPageList(params);	
	}
	
	/**
	 * 保存电站评论
	 * @param param
	 */
	public void insertEpCommnet(ElectricPileComment param){			
		elcMapper.insert(param);
	}
	//电站总数
	public long countEpCommentsByPowerId(Map<String, Object> params){
		return  elcMapper.countEpCommentsByPowerId(params);
	}

	public List<HashMap<String, Object>> getEpReplyCommentsList(String param) {
		return elcMapper.getEpReplyCommentsList(param);
	}
	
	public boolean checkIsHavePraise(Map<String, Object> param){
		return elPraiseMapper.getMyPraiseCount(param)  > 0;
	}
	
	public void updateEpReplyCount(Map<String, Object> params) {
		elcMapper.updateEpReplyCount(params);
	}

	public void insertEpReply(Map<String, Object> params) {
		elcMapper.insertEpReply(params);
	}

	public void insertEpPraiseCommnet(Map<String, Object> params) {
		elPraiseMapper.insert(params);
		
	}

	public void updateEpPraiseCount(Map<String, Object> params) {
		elPraiseMapper.updatePraiseCount(params);
		
	}

	public Object getEpCommentsRowNum(Map<String, Object> params) {
		return elcMapper.getEpCommentsRowNum(params);
	}
}
