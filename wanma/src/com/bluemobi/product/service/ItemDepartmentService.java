/**
 * FileName:ItemDepartmentService.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.service;

import java.util.List;

import com.bluemobi.product.model.ItemDepartmentModel;

/**
 * 数据库字段部门权限业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface ItemDepartmentService {

	/**
	 * 取得数据库字段部门权限信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param itemDepartmentModel
	 *            数据库字段部门权限对象
	 * @return 无
	 * @throws 无
	 */
	public ItemDepartmentModel findItemDepartment(
			ItemDepartmentModel itemDepartmentModel);

	/**
	 * 取得数据库字段部门权限一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<ItemDepartment> 数据库字段部门权限一览
	 * @throws 无
	 */
	public List<ItemDepartmentModel> getItemDepartmentList();

	/**
	 * 添加数据库字段部门权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param ItemDepartmentModel
	 *            数据库字段部门权限信息
	 * @return ItemDepartment 数据库字段部门权限信息
	 * @throws 无
	 */
	public ItemDepartmentModel addItemDepartment(
			ItemDepartmentModel itemDepartmentModel);

	/**
	 * 编辑数据库字段部门权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param ItemDepartmentModel
	 *            数据库字段部门权限信息
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public String modifyItemDepartment(ItemDepartmentModel itemDepartmentModel);

	/**
	 * 删除数据库字段部门权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param itemDepartmentModel
	 *            数据库字段部门权限对象
	 * @return 无
	 * @throws 无
	 */
	public void removeItemDepartment(ItemDepartmentModel itemDepartmentModel);

}
