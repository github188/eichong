package com.wanma.ims.service;

import java.util.List;

import com.wanma.ims.common.domain.UserAdminDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.UserRoleDO;
import com.wanma.ims.common.dto.BaseResultDTO;

/**
 * <p>管理员管理<p>
 * @author zhangchunyan
 * @date 2017-7-4
 */
public interface UserAdminService {
   
	/**
	 * <p>管理员-统计<p>
	 * @param userAdminDO
	 * @author zhangchunyan
	 * @date 2017-7-4
	 */
	public Long countUserAdminList(UserAdminDO userAdminDO);
	
	/**
	 * <p>管理员-查询<p>
	 * @param userAdminDO
	 * @author zhangchunyan
	 * @date 2017-7-4
	 */
	public List<UserAdminDO> getUserAdminList(UserAdminDO userAdminDO);
	
	/**
	 * <p>管理员-新增<p>
	 * @param userAdminDO
	 * @author zhangchunyan
	 * @date 2017-7-4
	 */
	public BaseResultDTO addUserAdmin(UserAdminDO userAdminDO,UserDO loginUser) throws Exception;
	
	/**
	 * <p>管理员-根据ID查询<包括角色信息><p>
	 * @param userAdminDO
	 * @author zhangchunyan
	 * @date 2017-7-4
	 */
	public UserAdminDO viewUserAdmin(Long userId);
	
	/**
	 * <p>管理员-根据ID查询<p>
	 * @param userAdminDO
	 * @author zhangchunyan
	 * @date 2017-7-4
	 */
	public UserAdminDO getUserAdminById(Long userId);
	
	/**
	 * <p>管理员-根据cpyId查询管理员<p>
	 * @param cpyId
	 * @author zhangchunyan
	 * @date 2017-7-4
	 */
	public List<UserAdminDO> getUserAdminByCpyId(Long cpyId);
	
	/**
	 * <p>管理员-公司主页<p>
	 * @param cpyId
	 * @author zhangchunyan
	 * @date 2017-7-4
	 */
	public List<UserAdminDO> getUserAccountByCpyId(Long cpyId);
	
	/**
	 * <p>管理员-修改角色<p>
	 * @param adminId、roleId
	 * @author zhangchunyan
	 * @date 2017-7-4
	 */
	public boolean modifyUserRole(UserRoleDO userRole) throws Exception;
}
