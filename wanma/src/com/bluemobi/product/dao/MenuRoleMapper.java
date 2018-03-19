/**
 * FileName:MenuRoleMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.dao;

import com.bluemobi.product.model.MenuRoleModel;

/**
 * 菜单角色权限表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface MenuRoleMapper {

	/**
	 * 添加菜单角色权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param MenuRoleModel
	 *            菜单角色权限信息
	 * @return MenuRole 菜单角色权限信息
	 * @throws 无
	 */
	public void addMenuRole(MenuRoleModel menuRoleModel);

	/**
	 * 删除菜单角色权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuRoleId
	 *            菜单角色权限对象
	 * @return 无
	 * @throws 无
	 */
	public void removeMenuRole(MenuRoleModel menuRoleModel);
}
