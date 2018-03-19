/**
 * FileName:DepartmentInclusionMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.dao;

import java.util.List;

import com.bluemobi.product.model.DepartmentInclusionModel;

/**
 * 部门关系表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface DepartmentInclusionMapper {

	/**
	 * 添加部门关系
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param DepartmentInclusionModel
	 *            部门关系信息
	 * @return DeptInclusion 部门关系信息
	 * @throws 无
	 */
	public void addDeptInclusion(DepartmentInclusionModel deptInclusionModel);

	/**
	 * 编辑部门关系
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param DepartmentInclusionModel
	 *            部门关系信息
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public void modifyDeptInclusion(DepartmentInclusionModel deptInclusionModel);

	/**
	 * 删除部门关系
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param deptInclusionId
	 *            部门关系对象
	 * @return 无
	 * @throws 无
	 */
	public void removeDeptInclusion(DepartmentInclusionModel deptInclusionModel);

	/**
	 * 取得直属上级部门ID
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param searchDeptModel
	 *            部门信息
	 * @return String 直属上级部门ID
	 * @throws 无
	 */
	public String getRealParentDept(DepartmentInclusionModel searchDeptModel);

	/**
	 * 取得所有上级部门
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param searchModel
	 *            部门关系信息
	 * @return List<DepartmentInclusionModel> 所有上级部门
	 * @throws 无
	 */
	public List<DepartmentInclusionModel> getAllParentDept(
			DepartmentInclusionModel searchModel);

	/**
	 * 取得所有下级部门关系（包含自身）
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param searchModel
	 *            部门关系信息
	 * @return List<DepartmentInclusionModel> 所有下级部门关系（包含自身）
	 * @throws 无
	 */
	public List<DepartmentInclusionModel> getAllSelfAndChildInc(
			DepartmentInclusionModel searchModel);

}
