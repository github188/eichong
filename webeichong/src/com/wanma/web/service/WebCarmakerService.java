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

import com.wanma.model.TblCarmaker;

/**
 * 制造厂商业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface WebCarmakerService {

	/**
	 * 获取所有制造厂商
	 * 
	 * @return
	 */
	public List<TblCarmaker> getAll(Map<String, String> param);

	public TblCarmaker getOne(Map<String, String> param);

	public int insert(TblCarmaker tblCarMaker);

	public int update(TblCarmaker tblCarMaker);

	public int delete(java.lang.Integer pkCarmaker);

}
