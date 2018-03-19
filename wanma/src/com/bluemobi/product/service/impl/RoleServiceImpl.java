/** 
 * FileName RoleServiceImpl.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */
package com.bluemobi.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.dao.MenuMapper;
import com.bluemobi.product.dao.RoleInclusionMapper;
import com.bluemobi.product.dao.RoleMapper;
import com.bluemobi.product.dao.UserRoleMapper;
import com.bluemobi.product.model.MenuModel;
import com.bluemobi.product.model.RoleInclusionModel;
import com.bluemobi.product.model.RoleModel;
import com.bluemobi.product.model.UserRoleModel;
import com.bluemobi.product.service.RoleService;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblUser;

/**
 * FileName RoleServiceImpl.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 角色业务处理类
 */
@Service
public class RoleServiceImpl implements RoleService {

	// 日志输出对象
	private static Logger log = Logger.getLogger(RoleServiceImpl.class);

	/** 角色表操作用DAO */
	@Autowired
	private RoleMapper roleMapper;

	/** 角色关系表操作用DAO */
	@Autowired
	RoleInclusionMapper roleInclusionMapper;

	/** 用户角色表操作用DAO */
	@Autowired
	UserRoleMapper userRoleMapper;
	@Autowired
	MenuMapper menuMapper;

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
	public RoleModel findRole(String roleId) {

		// 角色信息
		RoleModel role;

		// 取得角色信息
		role = roleMapper.findRole(roleId);

		// 设置直属上级角色ID
		role.setParentRoleId(getRealParentRole(roleId));

		// 返回角色一览
		return role;
	}

	/**
	 * 添加角色
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param roleModel
	 *            角色信息
	 * @param userRoleList
	 *            角色用户列表
	 * @return Role 角色信息
	 * @throws 无
	 */
	public String addRole(RoleModel roleModel, List<UserRoleModel> userRoleList) {
		// 更新处理结果
		String processResult = CommonConsts.PROCESS_STATUS_OK;

		// 角色关系一览
		List<RoleInclusionModel> deptInclusionList = null;

		// 生成角色关系一览
		deptInclusionList = getRoleInclusionList(roleModel);

		try {
			// 调用DAO执行角色添加处理
			roleMapper.addRole(roleModel);

			if (userRoleList != null && userRoleList.size() > 0) {
				//
				// 角色用户追加处理
				//
				for (UserRoleModel userRoleModel : userRoleList) {

					// 处理分类
					String processType = userRoleModel.getPrcessFlg();

					if (CommonConsts.PROCESS_FLG_ADD.equals(processType)) {
						// 调用DAO执行角色用户添加处理
						userRoleMapper.addUserRole(userRoleModel);
					}
				}
			}

			if (deptInclusionList != null && deptInclusionList.size() > 0) {

				//
				// 角色关系追加
				//
				for (RoleInclusionModel deptInclusionModel : deptInclusionList) {
					// 调用DAO执行角色关系添加处理
					roleInclusionMapper.addRoleInclusion(deptInclusionModel);
				}
			}
		} catch (Exception e) {
			// 返回处理错误标识
			processResult = CommonConsts.PROCESS_STATUS_NG;
			// 登录日志信息
			log.error(e.getLocalizedMessage());

		}

		// 返回更新处理结果
		return processResult;
	}

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
	public String modifyRole(RoleModel role, List<UserRoleModel> userRoleList) {

		// 更新处理结果
		String processResult = CommonConsts.PROCESS_STATUS_OK;

		try {
			// 调用DAO执行角色更新处理
			roleMapper.modifyRole(role);

			if (userRoleList != null && userRoleList.size() > 0) {

				//
				// 角色用户追加/删除处理
				//
				for (UserRoleModel userRoleModel : userRoleList) {

					// 处理分类
					String processType = userRoleModel.getPrcessFlg();

					if (CommonConsts.PROCESS_FLG_ADD.equals(processType)) {
						// 调用DAO执行角色用户添加处理
						userRoleMapper.addUserRole(userRoleModel);
					}

					if (CommonConsts.PROCESS_FLG_DELETE.equals(processType)) {
						// 调用DAO执行角色用户添加处理
						userRoleMapper.removeUserRole(userRoleModel);
					}
				}
			}
		} catch (Exception e) {
			// 返回处理错误标识
			processResult = CommonConsts.PROCESS_STATUS_NG;
			// 登录日志信息
			log.error(e.getLocalizedMessage());
		}

		// 返回更新处理结果
		return processResult;
	}

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
	public void removeRole(String roleId) {
		// 调用DAO执行角色更新处理
		roleMapper.removeRole(roleId);
		//删除该角色跟用户之间的关系
		UserRoleModel userRole=new UserRoleModel();
		userRole.setRoleId(roleId);
		userRoleMapper.removeUserRole(userRole);
		//删除角色菜单之间的关系
		menuMapper.removeMenusByRoleId(roleId);
		
	}

	/**
	 * 取得角色一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<RoleModel> 角色一览
	 * @throws 无
	 */
	public List<RoleModel> getRoleList() {
		// 角色一览
		List<RoleModel> listRole;

		// 取得角色一览
		listRole = roleMapper.getRoleList();

		// 返回角色一览
		return listRole;
	}

	/**
	 * 查询角色一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<RoleModel> 角色一览
	 * @throws 无
	 */
	public List<RoleModel> searchRoleList(RoleModel role) {
		// 角色一览
		List<RoleModel> listRole;

		// 取得角色一览
		listRole = roleMapper.searchRoleList(role);

		// 返回角色一览
		return listRole;

	}

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
	public long searchRoleCount(RoleModel roleModel) {

		// 返回角色数据条数
		return roleMapper.searchRoleCount(roleModel);
	}

	/**
	 * 角色唯一性检查
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param roleModel
	 *            角色信息
	 * @return String 角色唯一性检查结果 "true":通过 "false":不通过
	 * @throws 无
	 */
	public String checkRoleUnique(String roleId) {

		// 处理结果
		String processResult = CommonConsts.CHECK_RESULT_OK;
		// 角色数
		int roleCount = 0;

		// 根据角色登录ID取得角色数
		roleCount = roleMapper.getRoleCountById(roleId);

		// 如果取得的角色数大于0个，返回错误标识
		if (roleCount > 0) {
			return CommonConsts.CHECK_RESULT_NG;
		}

		// 返回处理结果标识
		return processResult;

	}

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
	public String getRealParentRole(String roleId) {
		// 直属上级角色ID
		String realParentRole = "";

		// 取得直属上级角色ID
		realParentRole = roleInclusionMapper.getRealParentRole(roleId);

		// 返回直属上级角色ID
		return realParentRole;
	}

	/**
	 * 生成角色关系列表
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param RoleModel
	 *            角色信息
	 * @return List<RoleInclusionModel> 角色关系列表
	 * @throws 无
	 */
	private List<RoleInclusionModel> getRoleInclusionList(RoleModel roleModel) {
		// 上级角色ID
		String parentRoleId = "";
		// 角色ID
		String roleId = "";
		// 自身角色关系
		RoleInclusionModel selfRoleIncModel = new RoleInclusionModel();
		// 所有上级角色关系
		List<RoleInclusionModel> allRoleIncList = new ArrayList<RoleInclusionModel>();
		// 临时所有上级角色关系
		List<RoleInclusionModel> tempRoleIncList = new ArrayList<RoleInclusionModel>();

		// 取得上级角色ID
		parentRoleId = roleModel.getParentRoleId();
		// 取得公司ID
		roleId = roleModel.getRoleId();

		// 生成自身角色关系
		selfRoleIncModel.setParentRoleId(roleId);
		selfRoleIncModel.setRoleId(roleId);
		selfRoleIncModel.setDepth(0);
		ProcessInfoCommon.setCreateUserInfo(selfRoleIncModel);
		allRoleIncList.add(selfRoleIncModel);

		// 根据直属角色取得直属角色的所有上级角色
		tempRoleIncList = roleInclusionMapper.getAllParentRole(parentRoleId);

		// 所有直属角色的所有上级角色深度加1
		for (RoleInclusionModel tempRoleIncModel : tempRoleIncList) {
			tempRoleIncModel.setDepth(tempRoleIncModel.getDepth() + 1);
			tempRoleIncModel.setRoleId(roleId);
			ProcessInfoCommon.setCreateUserInfo(tempRoleIncModel);
		}

		// 将修改后的直属角色的所有上级角色关系加入当前角色关系
		allRoleIncList.addAll(tempRoleIncList);

		// 返回所有上级角色关系
		return allRoleIncList;
	}
	
	@Override
	public String saveRole(RoleModel roleModel, String menuIds) {
		roleMapper.addRole(roleModel);
		roleMapper.modifyRoleMenuRelation(roleModel.getRoleId(),menuIds);
		return null;
	}
	
	@Override
	public String modifyRole(RoleModel roleModel, String menuIds) {
		roleMapper.modifyRole(roleModel);
		roleMapper.modifyRoleMenuRelation(roleModel.getRoleId(),menuIds);
		return null;
	}

	@Override
	public List<RoleModel> getRoleListByUserId(Long userId) {
		return roleMapper.getRoleListByUserId(userId);
	}

	@Override
	public List<RoleModel> getParentRoleListByCurrentUser(TblUser user) {
		//超级管理员可以查看自己等级及以下的角色
		if(user.getUserLevel()==WanmaConstants.USER_LEVEL_SUPER){
			return roleMapper.getParentRoleList(user);
		}else if(user.getUserLevel()==WanmaConstants.USER_LEVEL_ADMIN){
			//管理员可以查看自己等级及以下的角色
			return roleMapper.getParentRoleList(user);
		}else if(user.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS){
			//纯商户可以查看自己公司人员创建的角色
			return roleMapper.getParentRoleListByCompanyId(user);
		}else{
			//其余人可以只能创建的角色
			return roleMapper.getParentRoleListByCreateUserId(user);
		}
		
	}

	@Override
	public Long getParentRoleListCountByCurrentUser(TblUser user) {
		//超级管理员可以查看自己等级及以下的角色
		if(user.getUserLevel()==WanmaConstants.USER_LEVEL_SUPER){
			return roleMapper.getParentRoleListCount(user);
		}else if(user.getUserLevel()==WanmaConstants.USER_LEVEL_ADMIN){
			//管理员可以查看自己等级的角色
			return roleMapper.getParentRoleListCount(user);
		}else if(user.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS){
			//纯商户可以查看自己公司人员创建的角色
			return roleMapper.getParentRoleListCountByCompanyId(user);
		}else{
			//其余人可以只能创建的角色
			return roleMapper.getParentRoleListCountByCreateUserId(user);
		}
	}

	@Override
	public List<RoleModel> getRoleListByCompanyId(Integer companyId) {
		TblUser user=new TblUser();
		user.setBusiCompanyId(companyId);
		return roleMapper.getParentRoleListByCompanyId(user);
	}

	@Override
	public String updateUserRoleRelation(Long userId, String roleIds) {
		roleMapper.deleteUserRoleRelation(userId,roleIds);
		if(StringUtils.isNotBlank(roleIds)){
			roleMapper.saveUserRoleRelation(userId,roleIds);
		}
		
		return null;
	}

	@Override
	public void initRolesAndMenus(HttpServletRequest request) {
		TblUser user=SessionMgr.getWebUser(request);
		List<RoleModel> roleList=getRoleListByUserId(user.getUserId());
		List<MenuModel> menuList=new ArrayList<MenuModel>();
		String roleIds="";
		for(RoleModel role:roleList){
			roleIds+=role.getRoleId()+",";
		}
		roleIds=StringUtils.removeEnd(roleIds, ",");
		//有角色时或者超级管理员查看菜单
		if(StringUtils.isNotBlank(roleIds)||user.getUserLevel()==WanmaConstants.USER_LEVEL_SUPER){
			menuList=menuMapper.getMenuListByRoleIds(roleIds);
		}
		SessionMgr.addUserRoles(request, roleList);
		SessionMgr.addUserMenus(request, menuList);
		
	}

	/* 
	* Title: getBusinessNormalRole   
	* Description: 
	* @return    
	* @see com.bluemobi.product.service.RoleService#getBusinessNormalRole()    
	*/
	@Override
	public RoleModel getBusinessNormalRole() {
		RoleModel role=WanmaConstants.businessNormalRole;
		if(role==null){
			role=new RoleModel();
			role.setRoleName("个体商家");
			role=roleMapper.getRole(role);
		}
		return role;
	}

	/*   
	* Description: 
	* @param map
	* @return    
	*/
	@Override
	public int checkCommonUnique(Map<String, String> map) {
		return roleMapper.checkCommonUnique(map);
	}


	
}
