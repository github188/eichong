/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblUserCollect;



/**
 * 反馈表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface TblUserCollectMapper {
	
//	public List<TblUserCollect> find(TblUserCollect tblUserCollect);
//	
//	public List<Map<String,Object>> getUserCollectDetail(TblUserCollect tblUserCollect);
//	
//	public void insert(TblUserCollect tblUserCollect);
//	
//	public void delete(TblUserCollect tblUserCollect);
	
	public List<Map<String, Object>> getUserCollectList(Map<String, Object> param);
}
