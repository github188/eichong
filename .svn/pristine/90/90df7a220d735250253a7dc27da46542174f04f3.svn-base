package com.wanma.web.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wanma.model.TblFeedback;
import com.wanma.page.Page;

/**
 * 数据访问接口
 *
 */
public interface WebTblFeedbackMapper {    
    public final static String PREFIX = WebTblFeedbackMapper.class.getName();
    
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


