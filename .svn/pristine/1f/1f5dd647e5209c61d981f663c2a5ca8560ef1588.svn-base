/**
 * FileName:RoleInclusionMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.dao;

import java.util.List;

import com.bluemobi.product.model.RoleInclusionModel;

/**
 * 角色关系表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface RoleInclusionMapper {

	/**
	 * 添加角色关系
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param RoleInclusionModel
	 *            角色关系信息
	 * @return RoleInclusion 角色关系信息
	 * @throws 无
	 */
	public void addRoleInclusion(RoleInclusionModel roleInclusionModel);

	/**
	 * 编辑角色关系
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param RoleInclusionModel
	 *            角色关系信息
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public void modifyRoleInclusion(RoleInclusionModel roleInclusionModel);

	/**
	 * 删除角色关系
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param roleInclusionId
	 *            角色关系对象
	 * @return 无
	 * @throws 无
	 */
	public void removeRoleInclusion(RoleInclusionModel roleInclusionModel);

	/**
	 * 取得直属上级角色ID
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param roleId
	 *            角色ID
	 * @return String 直属上级角色ID
	 * @throws 无
	 */
	public String getRealParentRole(String roleId);

	/**
	 * 取得所有上级角色
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param roleId
	 *            角色ID
	 * @return List<RoleInclusionModel> 所有上级角色
	 * @throws 无
	 */
	public List<RoleInclusionModel> getAllParentRole(String roleId);

	/**
	 * 取得所有下级角色关系（包含自身）
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param roleId
	 *            角色ID
	 * @return List<RoleInclusionModel> 所有下级角色关系（包含自身）
	 * @throws 无
	 */
	public List<RoleInclusionModel> getAllSelfAndChildInc(String roleId);

}
