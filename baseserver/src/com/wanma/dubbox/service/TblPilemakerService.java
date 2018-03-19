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

import com.wanma.dubbox.model.TblPilemaker;
/**
 * @author gx
 *
 */
public interface TblPilemakerService {

	int insert(TblPilemaker model);

	int update(TblPilemaker model);

	int delete(TblPilemaker model);

	TblPilemaker selectOne(TblPilemaker model);

	int getCount(TblPilemaker model);
	
	List<TblPilemaker> getList(TblPilemaker model);
	
}
