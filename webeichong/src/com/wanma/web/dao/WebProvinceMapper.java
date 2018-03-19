/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.dao;

import com.wanma.model.TblCity;
import com.wanma.model.WebProvince;

import java.util.List;
import java.util.Map;

/**
 * 反馈表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface WebProvinceMapper {

	public List<WebProvince> getAll(Map<String, String> param);

	public long getCount(Map<String, String> param);
}
