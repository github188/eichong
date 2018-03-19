/**
 * FileName:RoleInclusionService.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.service;

import java.util.List;

import com.bluemobi.product.model.RoleInclusionModel;

/**
 * 角色关系业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface RoleInclusionService {

	/**
	 * 取得角色关系信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param roleInclusionModel
	 *            角色关系对象
	 * @return 无
	 * @throws 无
	 */
	public RoleInclusionModel findRoleInclusion(
			RoleInclusionModel roleInclusionModel);

	/**
	 * 取得角色关系一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<RoleInclusion> 角色关系一览
	 * @throws 无
	 */
	public List<RoleInclusionModel> getRoleInclusionList();

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
	public RoleInclusionModel addRoleInclusion(
			RoleInclusionModel roleInclusionModel);

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
	public String modifyRoleInclusion(RoleInclusionModel roleInclusionModel);

	/**
	 * 删除角色关系
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param roleInclusionModel
	 *            角色关系对象
	 * @return 无
	 * @throws 无
	 */
	public void removeRoleInclusion(RoleInclusionModel roleInclusionModel);

}
