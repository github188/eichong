/**
 * FileName:RoleService.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bluemobi.product.model.RoleModel;
import com.bluemobi.product.model.UserRoleModel;
import com.wanma.model.TblUser;

/**
 * 角色业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface RoleService {

	/**
	 * 取得角色信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param roleId
	 *            角色ID
	 * @return RoleModel 角色信息
	 * @throws 无
	 */
	public RoleModel findRole(String roleId);

	/**
	 * 取得角色一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<Role> 角色一览
	 * @throws 无
	 */
	public List<RoleModel> getRoleList();

	/**
	 * 查询角色一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param roleModel
	 *            查询用角色对象
	 * @return List<RoleModel> 角色一览
	 * @throws 无
	 */
	public List<RoleModel> searchRoleList(RoleModel roleModel);

	/**
	 * 查询角色数据条数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param roleModel
	 *            查询用角色对象
	 * @return long 角色数据条数
	 * @throws 无
	 */
	public long searchRoleCount(RoleModel roleModel);

	/**
	 * 添加角色
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param RoleModel
	 *            角色信息
	 * @param userRoleList
	 *            角色用户列表
	 * @return Role 角色信息
	 * @throws 无
	 */
	public String addRole(RoleModel roleModel, List<UserRoleModel> userRoleList);

	/**
	 * 编辑角色
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param RoleModel
	 *            角色信息
	 * @param userRoleList
	 *            角色用户列表
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public String modifyRole(RoleModel roleModel,
			List<UserRoleModel> userRoleList);

	/**
	 * 删除角色
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param roleId
	 *            角色ID
	 * @return 无
	 * @throws 无
	 */
	public void removeRole(String roleId);

	/**
	 * 角色唯一性检查
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param roleId
	 *            角色信息
	 * @return String 角色唯一性检查结果 "true":通过 "false":不通过
	 * @throws 无
	 */
	public String checkRoleUnique(String roleId);
	
	public String saveRole(RoleModel roleModel, String menuIds);
	
	public String modifyRole(RoleModel roleModel, String menuIds);

	public List<RoleModel> getRoleListByUserId(Long userId);

	public List<RoleModel> getParentRoleListByCurrentUser(TblUser user);

	public Long getParentRoleListCountByCurrentUser(TblUser user);

	public List<RoleModel> getRoleListByCompanyId(Integer companyId);
	
	public String updateUserRoleRelation(Long userId,String roleIds);

	/**
	 * @Title: initRolesAndMenus 
	 * @Description: TODO 	
	 * @author wbc	
	 * 2015年11月4日 	
	 * @return: void 
	 */
	public void initRolesAndMenus(HttpServletRequest request);

	/**
	 * @Title: getBusinessNormalRole 
	 * @Description: TODO 	
	 * @author wbc	
	 * 2015年11月12日 	
	 * @return: RoleModel 
	 */
	public RoleModel getBusinessNormalRole();

	/**
	 * @Description: TODO 	
	 * @author wbc	
	 * 2015年12月1日 	
	 * @return: int 
	 */
	public int checkCommonUnique(Map<String, String> map);


}
