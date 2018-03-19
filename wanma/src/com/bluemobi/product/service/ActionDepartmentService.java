/**
 * FileName:ActionDepartmentService.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.service;

import java.util.List;

import com.bluemobi.product.model.ActionDepartmentModel;

/**
 * 画面功能部门权限业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface ActionDepartmentService {

	/**
	 * 取得画面功能部门权限信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param actionDepartmentModel
	 *            画面功能部门权限对象
	 * @return 无
	 * @throws 无
	 */
	public ActionDepartmentModel findActionDepartment(
			ActionDepartmentModel actionDepartmentModel);

	/**
	 * 取得画面功能部门权限一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<ActionDepartment> 画面功能部门权限一览
	 * @throws 无
	 */
	public List<ActionDepartmentModel> getActionDepartmentList();

	/**
	 * 添加画面功能部门权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param ActionDepartmentModel
	 *            画面功能部门权限信息
	 * @return ActionDepartment 画面功能部门权限信息
	 * @throws 无
	 */
	public ActionDepartmentModel addActionDepartment(
			ActionDepartmentModel actionDepartmentModel);

	/**
	 * 编辑画面功能部门权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param ActionDepartmentModel
	 *            画面功能部门权限信息
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public String modifyActionDepartment(
			ActionDepartmentModel actionDepartmentModel);

	/**
	 * 删除画面功能部门权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param actionDepartmentModel
	 *            画面功能部门权限对象
	 * @return 无
	 * @throws 无
	 */
	public void removeActionDepartment(
			ActionDepartmentModel actionDepartmentModel);

}
