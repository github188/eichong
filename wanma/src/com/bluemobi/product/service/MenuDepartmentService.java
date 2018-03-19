/**
 * FileName:MenuDepartmentService.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.service;

import java.util.List;

import com.bluemobi.product.model.MenuDepartmentModel;

/**
 * 菜单部门权限业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface MenuDepartmentService {

	/**
	 * 取得菜单部门权限信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuDepartmentModel
	 *            菜单部门权限对象
	 * @return 无
	 * @throws 无
	 */
	public MenuDepartmentModel findMenuDepartment(
			MenuDepartmentModel menuDepartmentModel);

	/**
	 * 取得菜单部门权限一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<MenuDepartment> 菜单部门权限一览
	 * @throws 无
	 */
	public List<MenuDepartmentModel> getMenuDepartmentList();

	/**
	 * 添加菜单部门权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param MenuDepartmentModel
	 *            菜单部门权限信息
	 * @return MenuDepartment 菜单部门权限信息
	 * @throws 无
	 */
	public MenuDepartmentModel addMenuDepartment(
			MenuDepartmentModel menuDepartmentModel);

	/**
	 * 编辑菜单部门权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param MenuDepartmentModel
	 *            菜单部门权限信息
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public String modifyMenuDepartment(MenuDepartmentModel menuDepartmentModel);

	/**
	 * 删除菜单部门权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuDepartmentModel
	 *            菜单部门权限对象
	 * @return 无
	 * @throws 无
	 */
	public void removeMenuDepartment(MenuDepartmentModel menuDepartmentModel);

}
