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

import com.wanma.dubbox.model.TblHomepage;

/**
 * @author gx
 *
 */
public interface TblHomepageService {

	int insert(TblHomepage record);

	TblHomepage selectOne(TblHomepage record);

	int update(TblHomepage record);

	int getCount(TblHomepage record);
	
	List<TblHomepage> getList(TblHomepage record);

}
