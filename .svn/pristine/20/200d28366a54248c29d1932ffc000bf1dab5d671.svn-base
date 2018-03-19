/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.utils.IDSequenceUtil;
import com.wanma.web.dao.WebTblFeedbackMapper;
import com.wanma.web.service.WebTblFeedbackService;
import com.wanma.common.ApplicationConsts;
import com.wanma.model.TblFeedback;

/**
 * 反馈信息业务处理类
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
@Service
public class WebTblFeedbackServiceImpl implements WebTblFeedbackService {

	/** 聊天主表操作用DAO */
	@Autowired
	WebTblFeedbackMapper webFeedbackMapper;

	/**
	 * 添加反馈
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param Feedback
	 *            反馈信息
	 * @return 无
	 * @throws 无
	 */
	
	public void addFeedback(TblFeedback feedback) {
		// 添加反馈
		webFeedbackMapper.addFeedback(feedback);
	}

	@Override
	public List<HashMap<String, Object>> getFeedbackList(
			Map<String, Object> params) {		
		return webFeedbackMapper.getFeedbackList(params);
	}

	@Override
	public long feedbackCount(Map<String, Object> params) {
		return webFeedbackMapper.feedbackCount(params);
	}





}
