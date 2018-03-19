/**     
 * @Title:  TblUserService.java   
 * @Package com.wanma.service   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年4月28日 下午3:59:00   
 * @version V1.0     
 */
package com.wanma.service;

import com.wanma.model.ChargeGive;
import com.wanma.model.TblUser;

/**
 * @author bc
 *
 */
public interface TblUserService {
	public TblUser getNormUserByAccount(String userAccount);

	public void insertUser(TblUser user) throws Exception;
	
	
	//更新tbl_user_normal表
	public int updateNormalUser(TblUser tblUser);

	public void addCharge(ChargeGive chargegive, TblUser user);
}
