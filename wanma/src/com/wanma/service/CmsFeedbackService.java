package com.wanma.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wanma.model.Feedback;

/**
 * 意见反馈处理器
 * 
 * @author xiay
 *
 */
public interface CmsFeedbackService {
	/**
	 * 根据Id查询意见反馈列表
	 * 
	 * @return
	 */
	public  Feedback findFeedback(String feedbackId);

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
	
	/**
	  * 批量删除意见反馈列表
	 * 
	 * @param feedback
	 * @return
	 */
	public void removeFeedbacks(String feedbackIds,HttpServletRequest request);
	
	/**
	 * 更新反馈意见列表
	 */
	 public  int   updateFeedbackReason(Feedback feedback);

}


