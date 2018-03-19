package com.wanma.ims.service;

import java.util.List;

import com.wanma.ims.common.domain.FeedBackDO;
import com.wanma.ims.common.dto.BaseResultDTO;



/**
 * 意见反馈
 */
public interface FeedBackService {
	
	/**
	 * 获取意见反馈列表
	 * @param feedBack
	 * @return
	 */
	long getFeedBackListCount(FeedBackDO feedBack);
	List<FeedBackDO> getFeedBackList(FeedBackDO feedBack);
	/**
	 * 根据主键获取意见反馈
	 * @param feedBack
	 * @return
	 */
	FeedBackDO getFeedBackById(int pkFeedBack);
	/**
	 * 处理意见反馈
	 * @param feedBack
	 * @return
	 */
	BaseResultDTO editFeedBack(FeedBackDO feedBack);
	
	

}
