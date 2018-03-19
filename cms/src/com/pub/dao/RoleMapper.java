package com.pub.dao;

import java.util.List;
import java.util.Map;

import com.pub.model.RoleModel;
import com.pub.model.TblUser;
 
/**
 * 角色表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface RoleMapper {

	/**
	 * 根据用户角色D取得角色数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param roleId
	 *            角色ID
	 * @return int 角色数
	 * @throws 无
	 */
	public int getRoleCountById(String roleId);

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
	 * @return List<RoleModel> 角色一览
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
	 * @return Role 角色信息
	 * @throws 无
	 */
	public void addRole(RoleModel roleModel);

	/**
	 * 编辑角色
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param RoleModel
	 *            角色信息
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public void modifyRole(RoleModel roleModel);

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
	 * 删除角色(包括子角色)
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param roleId
	 *            角色ID
	 * @return 无
	 * @throws 无
	 */
	public void removeSelfAndChildRole(String roleId);

	/**
	 * 取得菜单角色权限列表
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuId
	 *            菜单ID
	 * @return 取得菜单角色权限列表
	 * @throws 无
	 */
	public List<RoleModel> getMenuRoleList1(RoleModel roleModel);

	/**
	 * 取得菜单角色权限列表
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuId
	 *            菜单ID
	 * @return 取得菜单角色权限列表
	 * @throws 无
	 */
	public List<RoleModel> getMenuRoleList(String menuId);

	/**
	 * 取得画面功能角色权限列表
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param actionId
	 *            画面功能ID
	 * @return 取得画面功能角色权限列表
	 * @throws 无
	 */
	public List<RoleModel> getActionRoleList(String actionId);

	/**
	 * 取得App接口角色权限列表
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiId
	 *            App接口ID
	 * @return 取得App接口角色权限列表
	 * @throws 无
	 */
	public List<RoleModel> getAppApiRoleList(String appApiId);

	public void modifyRoleMenuRelation(String roleId, String menuIds);

	public List<RoleModel> getRoleListByUserId(Long userId);

	public List<RoleModel> getParentRoleList(TblUser user);

	public List<RoleModel> getParentRoleListByUserLevel(TblUser user);

	public List<RoleModel> getParentRoleListByCompanyId(TblUser user);

	public List<RoleModel> getParentRoleListByCreateUserId(TblUser user);

	public Long getParentRoleListCount(TblUser user);

	public Long getParentRoleListCountByUserLevel(TblUser user);

	public Long getParentRoleListCountByCompanyId(TblUser user);

	public Long getParentRoleListCountByCreateUserId(TblUser user);

	public void deleteUserRoleRelation(Long userId, String roleIds);

	public void saveUserRoleRelation(Long userId, String roleIds);

	/**
	 * @Title: getRole 
	 * @Description: TODO 	
	 * @author wbc	
	 * 2015年11月12日 	
	 * @return: RoleModel 
	 */
	public RoleModel getRole(RoleModel role);

	/**
	 * @Description: TODO 	
	 * @author wbc	
	 * 2015年12月1日 	
	 * @return: int 
	 */
	public int checkCommonUnique(Map<String, String> map);

}
