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

import com.wanma.dubbox.model.TblCity;

/**
 * @author gx
 *
 */
public interface TblCityService {

	int update(TblCity record);

	TblCity selectOne(TblCity model);

	int getCount(TblCity model);
	
	List<TblCity> getList(TblCity model);
	
}
