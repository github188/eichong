package com.wanma.ims.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wanma.ims.common.domain.RoleDO;
 
/**
 * 角色Mapper
 * 
 * @version V1.0
 * @author zcy
 * @date 2017年5月31日
 */
public interface RoleMapper {

	/**
	 * 根据userId获取role列表
	 * @param 
	 */
	public List<RoleDO> selectRoleListByUserId(@Param("userId") Long userId);
	
	public List<String> selectRoleIdsByUserId(@Param("userId") Long userId);
	/**
	 * 统计-超级管理员、管理员角色列表
	 * @param 
	 */
	public Long countRoleListByAdmin(RoleDO roleDO);
	/**
	 * 统计-合作公司业务员角色列表
	 * @param 
	 */
	public Long countRoleListByOperatorId(RoleDO roleDO);
	/**
	 * 统计-自己创建角色列表
	 * @param 
	 */
	public Long countRoleListByCreateUserId(RoleDO roleDO);
	/**
	 * 查询-超级管理员、管理员角色列表
	 * @param 
	 */
	public List<RoleDO> selectRoleListByAdmin(RoleDO roleDO);
	/**
	 * 查询-合作公司业务员角色列表
	 * @param 
	 */
	public List<RoleDO> selectRoleListByOperatorId(RoleDO roleDO);
	/**
	 * 查询-自己创建角色列表
	 * @param 
	 */
	public List<RoleDO> selectRoleListByCreateUserId(RoleDO roleDO);
	/**
	 * 根据roleId查询RoleDO
	 * @param 
	 */
	public RoleDO selectRoleById(String roleId);
	/**
	 * 添加-角色
	 * @param 
	 */
	public Integer insertRole(RoleDO roleDO);
	/**
	 * 编辑-角色
	 * @param 
	 */
	public Integer updateRole(RoleDO roleDO);
	/**
	 * 修改-角色菜单权限
	 * @param 
	 */
	public void updateRoleMenuRela(String roleId, String menuIds);
	
	/**
	 * 初始化所有角色 
	 */
	public List<RoleDO> selectAllRoleList();
	
	/**
	 * 删除角色 
	 */
	public int deleteRoleById(String roleId);
	
	/**
	 * 删除角色下菜单 
	 */
	public int deleteRoleMenuByRoleId(String roleId);
	
	/**
	 * 角色校验 
	 */
	public int checkRole(@Param("roleName")String roleName);
	
}
