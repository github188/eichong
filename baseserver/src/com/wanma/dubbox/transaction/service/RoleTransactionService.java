/**     
 * @Title:  TblUserService.java   
 * @Package com.wanma.service   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年4月28日 下午3:59:00   
 * @version V1.0     
 */
package com.wanma.dubbox.transaction.service;


import com.wanma.dubbox.model.TblRole;

/**
 * @author lhy
 *
 */
public interface RoleTransactionService {

	void insert(TblRole model,String menuIds);

	void update(TblRole model,String menuIds);
	
	void delete(TblRole model) throws Exception;
	
}
