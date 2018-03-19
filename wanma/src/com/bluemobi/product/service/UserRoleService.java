/**
 * FileName:UserRoleService.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.service;

import java.util.List;

import com.bluemobi.product.model.UserRoleModel;

/**
 * 用户角色业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface UserRoleService {

	/**
	 * 取得用户角色信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userRoleModel
	 *            用户角色对象
	 * @return 无
	 * @throws 无
	 */
	public UserRoleModel findUserRole(UserRoleModel userRoleModel);

	/**
	 * 取得用户角色一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<UserRole> 用户角色一览
	 * @throws 无
	 */
	public List<UserRoleModel> getUserRoleList();

	/**
	 * 添加用户角色
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param UserRoleModel
	 *            用户角色信息
	 * @return UserRole 用户角色信息
	 * @throws 无
	 */
	public UserRoleModel addUserRole(UserRoleModel userRoleModel);

	/**
	 * 编辑用户角色
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param UserRoleModel
	 *            用户角色信息
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public String modifyUserRole(UserRoleModel userRoleModel);

	/**
	 * 删除用户角色
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userRoleModel
	 *            用户角色对象
	 * @return 无
	 * @throws 无
	 */
	public void removeUserRole(UserRoleModel userRoleModel);

}
