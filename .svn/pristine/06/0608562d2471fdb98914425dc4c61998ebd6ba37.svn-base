/** 
 * FileName ActionServiceImpl.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 *//*
package com.bluemobi.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.dao.ActionDepartmentMapper;
import com.bluemobi.product.dao.ActionMapper;
import com.bluemobi.product.dao.ActionPostMapper;
import com.bluemobi.product.dao.ActionRoleMapper;
import com.bluemobi.product.dao.CompanyPostMapper;
import com.bluemobi.product.dao.DepartmentMapper;
import com.bluemobi.product.dao.RoleMapper;
import com.bluemobi.product.model.ActionDepartmentModel;
import com.bluemobi.product.model.ActionModel;
import com.bluemobi.product.model.ActionPostModel;
import com.bluemobi.product.model.ActionRoleModel;
import com.bluemobi.product.model.CompanyPostModel;
import com.bluemobi.product.model.DepartmentModel;
import com.bluemobi.product.model.RoleModel;
import com.bluemobi.product.service.ActionService;

*//**
 * FileName ActionServiceImpl.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 功能业务处理类
 *//*
@Service
public class ActionServiceImpl implements ActionService {

	*//** 功能表操作用DAO *//*
	@Autowired
	private ActionMapper actionMapper;

	*//** 功能职位权限表操作用DAO *//*
	@Autowired
	ActionPostMapper actionPostMapper;

	*//** 功能角色权限表操作用DAO *//*
	@Autowired
	ActionRoleMapper actionRoleMapper;

	*//** 功能部门权限表操作用DAO *//*
	@Autowired
	ActionDepartmentMapper actionDepartmentMapper;

	*//** 职位表操作用DAO *//*
	@Autowired
	CompanyPostMapper companyPostMapper;

	*//** 角色表操作用DAO *//*
	@Autowired
	RoleMapper roleMapper;

	*//** 部门表操作用DAO *//*
	@Autowired
	DepartmentMapper departmentMapper;

	*//**
	 * 查询画面功能数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param actionModel
	 *            查询用画面功能对象
	 * @return long 画面功能数
	 * @throws 无
	 *//*
	public long searchActionCount(ActionModel actionModel) {
		// 画面功能数
		long dataCount = 0;

		// 取得画面功能数
		dataCount = actionMapper.searchActionCount(actionModel);

		// 返回画面功能数
		return dataCount;
	}

	*//**
	 * 查询画面功能一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param actionModel
	 *            查询用画面功能对象
	 * @return List<ActionModel> 画面功能一览
	 * @throws 无
	 *//*
	public List<ActionModel> searchActionList(ActionModel actionModel) {

		// 画面功能一览
		List<ActionModel> actionList = null;

		// 取得画面功能一览
		actionList = actionMapper.searchActionList(actionModel);

		// 返回画面功能一览
		return actionList;
	}

	*//**
	 * 取得画面功能信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param pageId
	 *            画面ID
	 * @param actionId
	 *            画面功能ID
	 * @return 无
	 * @throws 无
	 *//*
	public ActionModel findAction(String pageId, String actionId) {

		// 功能信息
		ActionModel actionModel;
		// 功能信息
		ActionModel searchModel = new ActionModel();
		searchModel.setPageId(pageId);
		searchModel.setActionId(actionId);

		// 取得功能信息
		actionModel = actionMapper.findAction(searchModel);

		// 设置功能权限相关信息
		setActionAuthInfo(actionModel);

		// 返回功能信息
		return actionModel;
	}

	*//**
	 * 取得画面功能信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param pageId
	 *            画面ID
	 * @return 无
	 * @throws 无
	 *//*
	public ActionModel findPage(String pageId) {

		// 功能信息
		ActionModel actionModel;

		// 取得功能信息
		actionModel = actionMapper.findPage(pageId);

		// 设置功能权限相关信息
		setActionAuthInfo(actionModel);

		// 返回功能信息
		return actionModel;
	}

	*//**
	 * 添加功能
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param actionModel
	 *            功能信息
	 * @return 无
	 * @throws 无
	 *//*
	public void addAction(ActionModel actionModel) {

		// 调用DAO执行功能添加处理
		actionMapper.addAction(actionModel);

		// 功能权限相关处理
		processActionAuthInfo(actionModel);
	}

	*//**
	 * 编辑功能
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param actionModel
	 *            功能信息
	 * @return 无
	 * @throws 无
	 *//*
	public void modifyAction(ActionModel actionModel) {

		// 调用DAO执行功能更新处理
		actionMapper.modifyAction(actionModel);

		// 功能权限相关处理
		processActionAuthInfo(actionModel);
	}

	*//**
	 * 删除功能
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param actionId
	 *            功能ID
	 * @return 无
	 * @throws 无
	 *//*
	public void removeAction(String actionId) {

		// 调用DAO执行功能更新处理
		actionMapper.removeAction(actionId);
		//
		removeAllActionAuthInfo(actionId);
	}

	*//**
	 * 取得功能一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<ActionModel> 功能一览
	 * @throws 无
	 *//*
	public List<ActionModel> getActionList() {
		// 功能一览
		List<ActionModel> listAction;

		// 取得功能一览
		listAction = actionMapper.getActionList();

		// 返回功能一览
		return listAction;
	}

	*//**
	 * 功能唯一性检查
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param actionModel
	 *            功能信息
	 * @return String 功能唯一性检查结果 "true":通过 "false":不通过
	 * @throws 无
	 *//*
	public String checkActionUnique(String actionId) {

		// 处理结果
		String processResult = CommonConsts.CHECK_RESULT_OK;
		// 功能数
		int actionCount = 0;

		// 根据功能登录ID取得功能数
		actionCount = actionMapper.getActionCountById(actionId);

		// 如果取得的功能数大于0个，返回错误标识
		if (actionCount > 0) {
			return CommonConsts.CHECK_RESULT_NG;
		}

		// 返回处理结果标识
		return processResult;

	}

	*//**
	 * 设置功能权限相关信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param actionModel
	 *            功能信息
	 * @return 无
	 * @throws 无
	 *//*
	private void setActionAuthInfo(ActionModel actionModel) {
		// 功能ID
		String actionId = "";
		// 功能职位权限列表
		List<CompanyPostModel> postList = null;
		// 功能角色权限列表
		List<RoleModel> roleList = null;
		// 功能部门权限列表
		List<DepartmentModel> deptList = null;

		if (actionModel == null) {
			return;
		}

		// 取得功能ID
		actionId = actionModel.getActionId();

		// 取得拥有功能权限相关列表
		postList = companyPostMapper.getActionPostList(actionId);
		roleList = roleMapper.getActionRoleList(actionId);
		deptList = departmentMapper.getActionDepartmentList(actionId);

		// 将拥有功能权限相关列表设置到功能对象
		actionModel.setPostList(postList);
		actionModel.setRoleList(roleList);
		actionModel.setDeptList(deptList);
	}

	*//**
	 * 功能权限相关处理
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param actionModel
	 *            功能信息
	 * @return 无
	 * @throws 无
	 *//*
	private void processActionAuthInfo(ActionModel actionModel) {
		// 功能职位权限列表
		List<ActionPostModel> actionPostList = null;
		// 功能角色权限列表
		List<ActionRoleModel> actionRoleList = null;
		// 功能部门权限列表
		List<ActionDepartmentModel> actionDeptList = null;

		actionPostList = actionModel.getActionPostList();
		actionRoleList = actionModel.getActionRoleList();
		actionDeptList = actionModel.getActionDeptList();

		if (actionPostList != null && actionPostList.size() > 0) {

			//
			// 功能职位权限追加/删除处理
			//
			for (ActionPostModel actionPostModel : actionPostList) {

				// 处理分类
				String processType = actionPostModel.getPrcessFlg();

				if (StringUtils.equals(CommonConsts.PROCESS_FLG_ADD,
						processType)) {
					// 调用DAO执行功能职位权限添加处理
					actionPostMapper.addActionPost(actionPostModel);
				}

				if (StringUtils.equals(CommonConsts.PROCESS_FLG_DELETE,
						processType)) {
					// 调用DAO执行画面功能职位权限删除处理
					actionPostMapper.removeActionPost(actionPostModel);
				}
			}
		}

		if (actionRoleList != null && actionRoleList.size() > 0) {

			//
			// 功能部门权限追加/删除处理
			//
			for (ActionRoleModel actionRoleModel : actionRoleList) {

				// 处理分类
				String processType = actionRoleModel.getPrcessFlg();

				if (StringUtils.equals(CommonConsts.PROCESS_FLG_ADD,
						processType)) {
					// 调用DAO执行功能部门权限添加处理
					actionRoleMapper.addActionRole(actionRoleModel);
				}

				if (StringUtils.equals(CommonConsts.PROCESS_FLG_DELETE,
						processType)) {
					// 调用DAO执行功能部门权限添加处理
					actionRoleMapper.removeActionRole(actionRoleModel);
				}
			}
		}

		if (actionDeptList != null && actionDeptList.size() > 0) {

			//
			// 功能部门权限追加/删除处理
			//
			for (ActionDepartmentModel actionDepartmentModel : actionDeptList) {

				// 处理分类
				String processType = actionDepartmentModel.getPrcessFlg();

				if (StringUtils.equals(CommonConsts.PROCESS_FLG_ADD,
						processType)) {
					// 调用DAO执行功能部门权限添加处理
					actionDepartmentMapper
							.addActionDepartment(actionDepartmentModel);
				}

				if (StringUtils.equals(CommonConsts.PROCESS_FLG_DELETE,
						processType)) {
					// 调用DAO执行功能部门权限添加处理
					actionDepartmentMapper
							.removeActionDepartment(actionDepartmentModel);
				}
			}
		}
	}

	*//**
	 * 删除功能权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param actionId
	 *            功能ID
	 * @return 无
	 * @throws 无
	 *//*
	private void removeAllActionAuthInfo(String actionId) {

		// 生成功能职位权限删除对象
		ActionPostModel actionPostModel = new ActionPostModel();
		actionPostModel.setActionId(actionId);
		// 删除功能下所有职位权限
		actionPostMapper.removeActionPost(actionPostModel);

		// 生成功能角色权限删除对象
		ActionRoleModel actionRoleModel = new ActionRoleModel();
		actionRoleModel.setActionId(actionId);
		// 删除功能下所有角色权限
		actionRoleMapper.removeActionRole(actionRoleModel);

		// 生成功能部门权限删除对象
		ActionDepartmentModel actionDepartmentModel = new ActionDepartmentModel();
		actionDepartmentModel.setActionId(actionId);
		// 删除功能下所有部门权限
		actionDepartmentMapper.removeActionDepartment(actionDepartmentModel);

	}

}
*/