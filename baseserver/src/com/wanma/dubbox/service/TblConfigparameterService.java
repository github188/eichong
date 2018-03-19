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

import com.wanma.dubbox.model.TblConfigparameter;
/**
 * @author gx
 *
 */
public interface TblConfigparameterService {

	int insert(TblConfigparameter model);

	int update(TblConfigparameter model);

	int delete(TblConfigparameter model);

	TblConfigparameter selectOne(TblConfigparameter model);

	int getCount(TblConfigparameter model);
	
	List<TblConfigparameter> getList(TblConfigparameter model);
	
}
