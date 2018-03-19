/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.Feedback;
import com.wanma.model.TblCity;
import com.wanma.web.support.common.Response;

/**
 * 反馈信息业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface WebCityService {

	/**
	 *  获取所有城市接口
	 *  
	 * @return
	 */
	public Response<?> getAll(Map<String,String> param);

}
