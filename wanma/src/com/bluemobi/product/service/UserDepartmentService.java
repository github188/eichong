/**
 * FileName:UserDepartmentService.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.service;

import java.util.List;

import com.bluemobi.product.model.UserDepartmentModel;

/**
 * 用户部门业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface UserDepartmentService {

	/**
	 * 取得用户部门信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userDepartmentModel
	 *            用户部门对象
	 * @return 无
	 * @throws 无
	 */
	public UserDepartmentModel findUserDepartment(
			UserDepartmentModel userDepartmentModel);

	/**
	 * 取得用户部门一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<UserDepartment> 用户部门一览
	 * @throws 无
	 */
	public List<UserDepartmentModel> getUserDepartmentList();

	/**
	 * 添加用户部门
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param UserDepartmentModel
	 *            用户部门信息
	 * @return UserDepartment 用户部门信息
	 * @throws 无
	 */
	public UserDepartmentModel addUserDepartment(
			UserDepartmentModel userDepartmentModel);

	/**
	 * 编辑用户部门
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param UserDepartmentModel
	 *            用户部门信息
	 * @param userDepartmentList
	 *            部门用户列表
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public String modifyUserDepartment(UserDepartmentModel userDepartmentModel);

	/**
	 * 删除用户部门
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userDepartmentModel
	 *            用户部门对象
	 * @return 无
	 * @throws 无
	 */
	public void removeUserDepartment(UserDepartmentModel userDepartmentModel);

}
