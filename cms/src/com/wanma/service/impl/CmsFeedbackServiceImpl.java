package com.wanma.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.util.ObjectUtil;
import com.wanma.dao.CmsFeedbackMapper;
import com.wanma.model.Feedback;
import com.wanma.service.CmsFeedbackService;

@Service
public class CmsFeedbackServiceImpl implements CmsFeedbackService {

	@Autowired
	private CmsFeedbackMapper feedbackDao;
	
	/**
	 * 取得意见反馈表一览
	 * 
	 */
	@Override
	public List<Feedback> getFeedbackList(Feedback feedback) {
		// 意见反馈表一览
		List<Feedback> listFeedback;
		
		//取得意见反馈表一览
		listFeedback = feedbackDao.getFeedbackList(feedback);
		
		//返回意见反馈表一览
		return listFeedback;
	}
	
	/**
	 * 查询意见反馈个数
	 * 
	 */
	public long searchFeedbackCount(Feedback feedback) {
		// 意见反馈个数
		long dataCount;

		// 取得意见反馈个数
		dataCount = feedbackDao.searchFeedbackCount(feedback);

		// 返回意见反馈个数
		return dataCount;

	}
	

	/**
	 * 查询意见反馈表一览
	 * 
	 */
	@Override
	public List<Feedback> searchFeedbackList(Feedback feedback) {
		// 意见反馈表一览
		List<Feedback> listFeedback;
				
		//取得意见反馈表一览
		listFeedback = feedbackDao.searchFeedbackList(feedback);
				
		//返回意见反馈表一览
		return listFeedback;
	}

	@Override
	public  Feedback findFeedback(String feedbackId) {
		// TODO Auto-generated method stub
		return feedbackDao.findFeedback(feedbackId);
	}
	
	/**
	 * 删除意见反馈列表
	 * 
	 */
	@Override
	public int removeFeedback(String feedbackId) {
		// TODO Auto-generated method stub
		return feedbackDao.removeFeedback(feedbackId);
	}
	
	/**
	 * 批量删除意见反馈列表
	 * 
	 */
	@Override
	public void removeFeedbacks(String feedbackIds,HttpServletRequest request) {
		if(!ObjectUtil.isEmpty(feedbackIds)){
			String[] array = feedbackIds.split(",");
			for(String feedbackId : array){
				Feedback feedback =  findFeedback(feedbackId);
				feedbackDao.removeFeedback(feedbackId);
			}
			
		}
	}

	@Override
	public int updateFeedbackReason(Feedback feedback) {
		feedback.setLastUpdateDate(new Date());
		return feedbackDao.updateFeedbackReason(feedback);
	}
	
}








