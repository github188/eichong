/**
 * FileName:MUserService.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.service;

import java.util.List;

import com.wanma.model.TblUser;

/**
 * 用户业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface MUserService {

	/**
	 * 根据用户登录ID取得用户数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userLogin
	 *            用户登录ID
	 * @return int 用户数
	 * @throws 无
	 */
	public int getUserCountById(String userLogin);

	/**
	 * 根据用户登录ID取得用户数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userLogin
	 *            用户登录ID
	 * @param userPassword
	 *            用户密码
	 * @return int 用户数
	 * @throws 无
	 */
	public int getTblUserCountByPassword(TblUser loginTblUser);

	/**
	 * 根据用户ID取得用户信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userId
	 *            用户ID
	 * @return 无
	 * @throws 无
	 */
	public TblUser findTblUser(long userId);

	/**
	 * 添加用户
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param TblUser
	 *            用户信息
	 * @return 无
	 * @throws int
	 *             用户ID
	 */
	public int saveTblUser(TblUser user);

	/**
	 * 编辑用户
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param TblUser
	 *            用户信息
	 * @return 无
	 * @throws 无
	 */
	public void modifyTblUser(TblUser user);

	/**
	 * 删除用户
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userId
	 *            用户ID
	 * @return 无
	 * @throws 无
	 */
	public void removeTblUser(long userId);

	/**
	 * 取得用户一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<TblUser> 用户一览
	 * @throws 无
	 */
	public List<TblUser> getTblUserList();

}
