/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.dao;

import java.util.List;

import com.wanma.model.Feedback;

/**
 * 反馈表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface AppFeedbackMapper {

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
	public void addFeedback(Feedback feedback);

	/**
	 * 取得反馈信息一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<Feedback> 反馈信息
	 * @throws 无
	 */
	public List<Feedback> getFeedbackList();

}
