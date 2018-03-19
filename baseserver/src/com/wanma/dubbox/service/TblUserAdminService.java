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

import com.wanma.dubbox.model.TblUserAdmin;

/**
 * @author lhy
 *
 */
public interface TblUserAdminService {

	int insert(TblUserAdmin record);

	TblUserAdmin selectOne(TblUserAdmin record);

	int update(TblUserAdmin record);

	int getCount(TblUserAdmin record);

	List<TblUserAdmin> getList(TblUserAdmin record);
}
