package com.wanma.ims.mapper;

import com.wanma.ims.common.domain.UserRoleDO;

public interface UserRoleMapper {

	/**
	 * 添加用户角色
	 */
	public int insertUserRole(UserRoleDO userRoleDO);

	/**
	 * 删除用户角色
	 */
	public int deleteUserRole(UserRoleDO userRoleDO);
	
	/**
	 * 修改用户角色 
	 */
	public int updateUserRole(UserRoleDO userRoleDO);
	
	/**
	 * 根据角色查询用户 
	 */
	public int selectUserListByRoleId(String roleId);
}
