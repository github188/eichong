package com.wanma.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.model.PowerStationComment;
import com.wanma.web.dao.PowerStationCommentMapper;
import com.wanma.web.dao.PowerStationCommentPraiseMapper;

@Service
public class WebPowerStationCommentServiceImpl {
	@Autowired
	private PowerStationCommentMapper pscMapper;
	@Autowired
	private PowerStationCommentPraiseMapper pscPraiseMapper;

	/**
	 * 获取电站评论分页列表
	 * 
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> getPsCommentsPageList(
			Map<String, Object> params) {
		return pscMapper.getPsCommentsPageList(params);
	}

	/**
	 * 获取电站评论回复数据
	 * 
	 * @param param
	 * @return
	 */
	public List<HashMap<String, Object>> getPsReplyCommentsList(
			String param) {
		return pscMapper.getPsReplyCommentsList(param);
	}

	/**
	 * 保存电站评论
	 * 
	 * @param param
	 */
	public void insertPsCommnet(PowerStationComment param) {

		pscMapper.insert(param);
	}

	/**
	 * 保存点赞信息
	 * 
	 * @param param
	 */
	public void insertPsPraiseCommnet(Map<String, Object> param) {
		pscPraiseMapper.insert(param);
	}

	/**
	 * 更新点赞数量
	 * 
	 * @param param
	 */
	public void updatePsPraiseCount(Map<String, Object> param) {
		pscPraiseMapper.updatePraiseCount(param);
	}
	
	public boolean checkIsHavePraise(Map<String, Object> param){
		return pscPraiseMapper.getMyPraiseCount(param)  > 0;
	}

	public long countPsCommentsByPowerId(Map<String, Object> params) {
		return pscMapper.countPsCommentsByPowerId(params);
	}

	public void updatePsReplyCount(Map<String, Object> params) {
		pscMapper.updatePsReplyCount(params);
	}

	public void insertReply(Map<String, Object> params) {
		pscMapper.insertReply(params);
	}
	
	public Object getPsCommentsRowNum(Map<String, Object> params) {
		return pscMapper.getPsCommentsRowNum(params);
	}
}
