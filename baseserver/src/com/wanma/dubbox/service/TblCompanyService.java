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

import com.wanma.dubbox.model.TblCompany;

/**
 * @author gx
 *
 */
public interface TblCompanyService {
	
	int delete(TblCompany model) throws Exception;

	int insert(TblCompany record);

	int update(TblCompany record);

	TblCompany selectOne(TblCompany model);

	int getCount(TblCompany model);
	
	List<TblCompany> getList(TblCompany model);
	
}
