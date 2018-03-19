/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.dao;

import java.util.List;

import com.wanma.model.TblSearchhistory;



/**
 * 反馈表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface WebSearchhistoryMapper {
	public void insert(TblSearchhistory tblSearchhistory);
	public void removeMallSearchHistory();
	public List<TblSearchhistory> find();
}
