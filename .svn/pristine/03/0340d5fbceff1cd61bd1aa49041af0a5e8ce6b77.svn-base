/**
 * FileName:MenuMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.dao;

import java.util.List;

import com.bluemobi.product.model.MenuModel;

/**
 * 菜单表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface MenuMapper {

	/**
	 * 根据用户菜单ID取得菜单数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuId
	 *            菜单ID
	 * @return int 菜单数
	 * @throws 无
	 */
	public int getMenuCountById(String menuId);

	/**
	 * 取得菜单信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuId
	 *            菜单ID
	 * @return MenuModel 菜单信息
	 * @throws 无
	 */
	public MenuModel findMenu(String menuId);

	/**
	 * 取得菜单一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<MenuModel> 菜单一览
	 * @throws 无
	 */
	public List<MenuModel> getMenuList();

	/**
	 * 查询菜单一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuModel
	 *            查询用菜单对象
	 * @return List<MenuModel> 菜单一览
	 * @throws 无
	 */
	public List<MenuModel> searchMenuList(MenuModel menuModel);

	public Long searchMenuListCount(MenuModel menu);

	/**
	 * 添加菜单
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param MenuModel
	 *            菜单信息
	 * @return Menu 菜单信息
	 * @throws 无
	 */
	public void addMenu(MenuModel menuModel);

	/**
	 * 编辑菜单
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param MenuModel
	 *            菜单信息
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public void modifyMenu(MenuModel menuModel);

	/**
	 * 删除菜单
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuId
	 *            菜单ID
	 * @return 无
	 * @throws 无
	 */
	public void removeMenu(String menuId);

	public List<MenuModel> getCheckedMenuListByRoleIdAndMenuIds(String roleId,
			String menuIds);

	public List<MenuModel> getMenuListByRoleIds(String removeEnd);

	public void removeMenusByRoleId(String roleId);

}
