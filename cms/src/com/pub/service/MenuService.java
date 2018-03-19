package com.pub.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pub.model.MenuModel;
import com.pub.model.Node;
import com.pub.model.RoleModel;
import com.pub.model.TblUser;
 
/**
 * 菜单业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface MenuService {

	/**
	 * 取得菜单信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuId
	 *            菜单ID
	 * @return 无
	 * @throws 无
	 */
	public MenuModel findMenu(String menuId,TblUser loginUser);

	public MenuModel getMenu(String menuId);
	/**
	 * 取得菜单一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<Menu> 菜单一览
	 * @throws 无
	 */
	public List<MenuModel> getMenuList();
	public List<MenuModel> getMenuList(MenuModel menu);
	public Long getMenuListCount(MenuModel menu);

	/**
	 * 添加菜单
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param MenuModel
	 *            菜单信息
	 * @return 无
	 * @throws 无
	 */
	public void addMenu(MenuModel menuModel);

	/**
	 * 编辑菜单
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuModel
	 *            菜单信息
	 * @return 无
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

	/**
	 * 菜单唯一性检查
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuId
	 *            菜单ID
	 * @return String 菜单唯一性检查结果 "true":通过 "false":不通过
	 * @throws 无
	 */
	public String checkMenuUnique(String menuId);
	
	
	public String getMenuListByRoleIdAndMenuIds(String roleId,String menuIds);

	public List<MenuModel> getMenuListByRoleList(List<RoleModel> roleList);
	/**
	 * 菜单唯一性检查
	 * 
	 * @author wbc
	 * @since Version 1.0
	 * @param menuList:菜单权限LIST，isLookUp:是否是查找带回树
	 *            
	 * @return String 菜单唯一性检查结果 "true":通过 "false":不通过
	 * @throws 无
	 */
	public String getMenuTree(List<MenuModel> menuList,boolean isLookUp);

	public Node getChildrenMenu(HttpServletRequest request, MenuModel menu);


	
}
