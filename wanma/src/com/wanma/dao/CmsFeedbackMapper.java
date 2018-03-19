package com.wanma.dao;


import java.util.List;
import java.util.Map;

import com.wanma.model.Feedback;

/**
 * 意见反馈
 * @author xiay
 * 数据访问接口
 *
 */
public interface CmsFeedbackMapper {
	
	/**
	 * 根据ID查询意见反馈列表
	 * 
	 * @param feedbackId
	 * @return
	 */
	public Feedback findFeedback(String feedbackId);

	/**
	 * 取得意见反馈列表
	 * 
	 * @return
	 */
	public List<Feedback> getFeedbackList(Feedback feedback);
	
	/**
	 * 查询意见反馈个数
	 * 
	 * @param feedback
	 * @return
	 */
	public long searchFeedbackCount(Feedback feedback);

	
	/**
	 * 查询意见反馈列表
	 * 
	 * @param feedback
	 * @return
	 */
	public List<Feedback> searchFeedbackList(Feedback feedback);
	
	/**
     * 删除意见反馈列表
	 * 
	 * @param feedback
	 * @return
     */
    public int removeFeedback(String feedbackId);
    
    public  int   updateFeedbackReason(Feedback feedback);

}


