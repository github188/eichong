/**
 * FileName:ItemRoleService.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.service;

import java.util.List;

import com.bluemobi.product.model.ItemRoleModel;

/**
 * 数据库字段角色权限业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface ItemRoleService {

	/**
	 * 取得数据库字段角色权限信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param itemRoleModel
	 *            数据库字段角色权限对象
	 * @return 无
	 * @throws 无
	 */
	public ItemRoleModel findItemRole(ItemRoleModel itemRoleModel);

	/**
	 * 取得数据库字段角色权限一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<ItemRole> 数据库字段角色权限一览
	 * @throws 无
	 */
	public List<ItemRoleModel> getItemRoleList();

	/**
	 * 添加数据库字段角色权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param ItemRoleModel
	 *            数据库字段角色权限信息
	 * @return ItemRole 数据库字段角色权限信息
	 * @throws 无
	 */
	public ItemRoleModel addItemRole(ItemRoleModel itemRoleModel);

	/**
	 * 编辑数据库字段角色权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param ItemRoleModel
	 *            数据库字段角色权限信息
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public String modifyItemRole(ItemRoleModel itemRoleModel);

	/**
	 * 删除数据库字段角色权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param itemRoleModel
	 *            数据库字段角色权限对象
	 * @return 无
	 * @throws 无
	 */
	public void removeItemRole(ItemRoleModel itemRoleModel);

}
