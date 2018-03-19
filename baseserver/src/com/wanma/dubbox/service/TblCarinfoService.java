/**     
 * @Title:  TblUserService.java   
 * @Package com.wanma.service   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年4月28日 下午3:59:00   
 * @version V1.0     
 */
package com.wanma.dubbox.service;

import java.util.List;

import com.wanma.dubbox.model.TblCarinfo;

/**
 * @author gx
 *
 */
public interface TblCarinfoService {
	
	int delete(TblCarinfo model);

	int insert(TblCarinfo record);

	int update(TblCarinfo record);

	TblCarinfo selectOne(TblCarinfo model);

	int getCount(TblCarinfo model);
	
	List<TblCarinfo> getList(TblCarinfo model);
	
}
