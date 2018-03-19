package com.wanma.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wanma.model.TblFeedback;
import com.wanma.web.support.common.Response;

import javax.servlet.http.HttpServletRequest;

/** 
* @ClassName: TblFeedbackMapper 
* @Description: 用户意见反馈服务层
* @author chenb
* @date 2015年3月15日 下午6:38:51  
*/
public interface WebTblFeedbackService {
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
	public void addFeedback(TblFeedback feedback);
	
	
	/**
	 * 反馈列表
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> getFeedbackList(Map<String, Object> params);
	/**
	 * 反馈信息条数
	 * @param params
	 * @return
	 */
	public long feedbackCount(Map<String,Object>params);


}
