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

import com.wanma.dubbox.model.TblCardapplicationform;

/**
 * @author gx
 *
 */
public interface TblCardapplicationformService {

	int insert(TblCardapplicationform record);

	int update(TblCardapplicationform record);

	TblCardapplicationform selectOne(TblCardapplicationform model);

	int getCount(TblCardapplicationform model);
	
	List<TblCardapplicationform> getList(TblCardapplicationform model);
	
}
