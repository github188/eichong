/**
 * FileName:MenuPostService.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.service;

import java.util.List;

import com.bluemobi.product.model.MenuPostModel;

/**
 * 菜单职位权限业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface MenuPostService {

	/**
	 * 取得菜单职位权限表信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param actionDepartmentModel
	 *            菜单职位权限表对象
	 * @return 无
	 * @throws 无
	 */
	public MenuPostModel findMenuPost(MenuPostModel actionDepartmentModel);

	/**
	 * 取得菜单职位权限表一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<MenuPost> 菜单职位权限表一览
	 * @throws 无
	 */
	public List<MenuPostModel> getMenuPostList();

	/**
	 * 添加菜单职位权限表
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param MenuPostModel
	 *            菜单职位权限表信息
	 * @return MenuPost 菜单职位权限表信息
	 * @throws 无
	 */
	public MenuPostModel addMenuPost(MenuPostModel actionDepartmentModel);

	/**
	 * 编辑菜单职位权限表
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param MenuPostModel
	 *            菜单职位权限表信息
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public String modifyMenuPost(MenuPostModel actionDepartmentModel);

	/**
	 * 删除菜单职位权限表
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param actionDepartmentModel
	 *            菜单职位权限表对象
	 * @return 无
	 * @throws 无
	 */
	public void removeMenuPost(MenuPostModel actionDepartmentModel);

}
