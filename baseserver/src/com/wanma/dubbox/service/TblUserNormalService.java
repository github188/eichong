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

import com.wanma.dubbox.model.TblUserNormal;

/**
 * @author gx
 *
 */
public interface TblUserNormalService {

	int delete(TblUserNormal record);

	int insert(TblUserNormal record);

	TblUserNormal selectOne(TblUserNormal record);

	int update(TblUserNormal record);

	int getCount(TblUserNormal record);
	
	List<TblUserNormal> getList(TblUserNormal record);

}
