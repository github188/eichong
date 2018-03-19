/** 
 * FileName AppApiServiceImpl.java
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
import com.bluemobi.product.dao.AppApiDepartmentMapper;
import com.bluemobi.product.dao.AppApiMapper;
import com.bluemobi.product.dao.AppApiPostMapper;
import com.bluemobi.product.dao.AppApiRoleMapper;
import com.bluemobi.product.dao.CompanyPostMapper;
import com.bluemobi.product.dao.DepartmentMapper;
import com.bluemobi.product.dao.RoleMapper;
import com.bluemobi.product.model.AppApiDepartmentModel;
import com.bluemobi.product.model.AppApiModel;
import com.bluemobi.product.model.AppApiPostModel;
import com.bluemobi.product.model.AppApiRoleModel;
import com.bluemobi.product.model.CompanyPostModel;
import com.bluemobi.product.model.DepartmentModel;
import com.bluemobi.product.model.RoleModel;
import com.bluemobi.product.service.AppApiService;

*//**
 * FileName AppApiServiceImpl.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * App接口业务处理类
 *//*
@Service
public class AppApiServiceImpl implements AppApiService {

	*//** App接口表操作用DAO *//*
	@Autowired
	private AppApiMapper appApiMapper;

	*//** App接口职位权限表操作用DAO *//*
	@Autowired
	AppApiPostMapper appApiPostMapper;

	*//** App接口角色权限表操作用DAO *//*
	@Autowired
	AppApiRoleMapper appApiRoleMapper;

	*//** App接口部门权限表操作用DAO *//*
	@Autowired
	AppApiDepartmentMapper appApiDepartmentMapper;

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
	 * 查询App接口数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiModel
	 *            查询用App接口对象
	 * @return long App接口数
	 * @throws 无
	 *//*
	public long searchAppApiCount(AppApiModel appApiModel) {
		// App接口数
		long dataCount = 0;

		// 取得App接口数
		dataCount = appApiMapper.searchAppApiCount(appApiModel);

		// 返回App接口数
		return dataCount;
	}

	*//**
	 * 查询App接口一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiModel
	 *            查询用App接口对象
	 * @return List<AppApiModel> App接口一览
	 * @throws 无
	 *//*
	public List<AppApiModel> searchAppApiList(AppApiModel appApiModel) {

		// App接口一览
		List<AppApiModel> appApiList = null;

		// 取得App接口一览
		appApiList = appApiMapper.searchAppApiList(appApiModel);

		// 返回App接口一览
		return appApiList;
	}

	*//**
	 * 取得App接口信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiId
	 *            App接口ID
	 * @return 无
	 * @throws 无
	 *//*
	public AppApiModel findAppApi(String appApiId) {

		// App接口信息
		AppApiModel appApiModel;
		// App接口信息
		AppApiModel searchModel = new AppApiModel();
		searchModel.setAppApiId(appApiId);

		// 取得App接口信息
		appApiModel = appApiMapper.findAppApi(appApiId);

		// 设置App接口权限相关信息
		setAppApiAuthInfo(appApiModel);

		// 返回App接口信息
		return appApiModel;
	}

	*//**
	 * 添加App接口
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiModel
	 *            App接口信息
	 * @return 无
	 * @throws 无
	 *//*
	public void addAppApi(AppApiModel appApiModel) {

		// 调用DAO执行App接口添加处理
		appApiMapper.addAppApi(appApiModel);

		// App接口权限相关处理
		processAppApiAuthInfo(appApiModel);
	}

	*//**
	 * 编辑App接口
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiModel
	 *            App接口信息
	 * @return 无
	 * @throws 无
	 *//*
	public void modifyAppApi(AppApiModel appApiModel) {

		// 调用DAO执行App接口更新处理
		appApiMapper.modifyAppApi(appApiModel);

		// App接口权限相关处理
		processAppApiAuthInfo(appApiModel);
	}

	*//**
	 * 删除App接口
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiId
	 *            App接口ID
	 * @return 无
	 * @throws 无
	 *//*
	public void removeAppApi(String appApiId) {

		// 调用DAO执行App接口更新处理
		appApiMapper.removeAppApi(appApiId);
		//
		removeAllAppApiAuthInfo(appApiId);
	}

	*//**
	 * 取得App接口一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<AppApiModel> App接口一览
	 * @throws 无
	 *//*
	public List<AppApiModel> getAppApiList() {
		// App接口一览
		List<AppApiModel> listAppApi;

		// 取得App接口一览
		listAppApi = appApiMapper.getAppApiList();

		// 返回App接口一览
		return listAppApi;
	}

	*//**
	 * App接口唯一性检查
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiModel
	 *            App接口信息
	 * @return String App接口唯一性检查结果 "true":通过 "false":不通过
	 * @throws 无
	 *//*
	public String checkAppApiUnique(String appApiId) {

		// 处理结果
		String processResult = CommonConsts.CHECK_RESULT_OK;
		// App接口数
		int appApiCount = 0;

		// 根据App接口登录ID取得App接口数
		appApiCount = appApiMapper.getAppApiCountById(appApiId);

		// 如果取得的App接口数大于0个，返回错误标识
		if (appApiCount > 0) {
			return CommonConsts.CHECK_RESULT_NG;
		}

		// 返回处理结果标识
		return processResult;

	}

	*//**
	 * App接口Url唯一性检查
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param url
	 *            App接口url
	 * @return String App接口url唯一性检查结果 "true":通过 "false":不通过
	 * @throws 无
	 *//*
	public String checkUrlUnique(String url) {

		// 处理结果
		String processResult = CommonConsts.CHECK_RESULT_OK;
		// App接口url数
		int appApiCount = 0;

		// 根据App接口url登录ID取得App接口url数
		appApiCount = appApiMapper.getUrlCountById(url);

		// 如果取得的App接口url数大于0个，返回错误标识
		if (appApiCount > 0) {
			return CommonConsts.CHECK_RESULT_NG;
		}

		// 返回处理结果标识
		return processResult;

	}

	*//**
	 * 设置App接口权限相关信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiModel
	 *            App接口信息
	 * @return 无
	 * @throws 无
	 *//*
	private void setAppApiAuthInfo(AppApiModel appApiModel) {
		// App接口ID
		String appApiId = "";
		// App接口职位权限列表
		List<CompanyPostModel> postList = null;
		// App接口角色权限列表
		List<RoleModel> roleList = null;
		// App接口部门权限列表
		List<DepartmentModel> deptList = null;

		if (appApiModel == null) {
			return;
		}

		// 取得App接口ID
		appApiId = appApiModel.getAppApiId();

		// 取得拥有App接口权限相关列表
		postList = companyPostMapper.getAppApiPostList(appApiId);
		roleList = roleMapper.getAppApiRoleList(appApiId);
		deptList = departmentMapper.getAppApiDepartmentList(appApiId);

		// 将拥有App接口权限相关列表设置到App接口对象
		appApiModel.setPostList(postList);
		appApiModel.setRoleList(roleList);
		appApiModel.setDeptList(deptList);
	}

	*//**
	 * App接口权限相关处理
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiModel
	 *            App接口信息
	 * @return 无
	 * @throws 无
	 *//*
	private void processAppApiAuthInfo(AppApiModel appApiModel) {
		// App接口职位权限列表
		List<AppApiPostModel> appApiPostList = null;
		// App接口角色权限列表
		List<AppApiRoleModel> appApiRoleList = null;
		// App接口部门权限列表
		List<AppApiDepartmentModel> appApiDeptList = null;

		appApiPostList = appApiModel.getAppApiPostList();
		appApiRoleList = appApiModel.getAppApiRoleList();
		appApiDeptList = appApiModel.getAppApiDeptList();

		if (appApiPostList != null && appApiPostList.size() > 0) {

			//
			// App接口职位权限追加/删除处理
			//
			for (AppApiPostModel appApiPostModel : appApiPostList) {

				// 处理分类
				String processType = appApiPostModel.getPrcessFlg();

				if (StringUtils.equals(CommonConsts.PROCESS_FLG_ADD,
						processType)) {
					// 调用DAO执行App接口职位权限添加处理
					appApiPostMapper.addAppApiPost(appApiPostModel);
				}

				if (StringUtils.equals(CommonConsts.PROCESS_FLG_DELETE,
						processType)) {
					// 调用DAO执行App接口职位权限删除处理
					appApiPostMapper.removeAppApiPost(appApiPostModel);
				}
			}
		}

		if (appApiRoleList != null && appApiRoleList.size() > 0) {

			//
			// App接口部门权限追加/删除处理
			//
			for (AppApiRoleModel appApiRoleModel : appApiRoleList) {

				// 处理分类
				String processType = appApiRoleModel.getPrcessFlg();

				if (StringUtils.equals(CommonConsts.PROCESS_FLG_ADD,
						processType)) {
					// 调用DAO执行App接口部门权限添加处理
					appApiRoleMapper.addAppApiRole(appApiRoleModel);
				}

				if (StringUtils.equals(CommonConsts.PROCESS_FLG_DELETE,
						processType)) {
					// 调用DAO执行App接口部门权限添加处理
					appApiRoleMapper.removeAppApiRole(appApiRoleModel);
				}
			}
		}

		if (appApiDeptList != null && appApiDeptList.size() > 0) {

			//
			// App接口部门权限追加/删除处理
			//
			for (AppApiDepartmentModel appApiDepartmentModel : appApiDeptList) {

				// 处理分类
				String processType = appApiDepartmentModel.getPrcessFlg();

				if (StringUtils.equals(CommonConsts.PROCESS_FLG_ADD,
						processType)) {
					// 调用DAO执行App接口部门权限添加处理
					appApiDepartmentMapper
							.addAppApiDepartment(appApiDepartmentModel);
				}

				if (StringUtils.equals(CommonConsts.PROCESS_FLG_DELETE,
						processType)) {
					// 调用DAO执行App接口部门权限添加处理
					appApiDepartmentMapper
							.removeAppApiDepartment(appApiDepartmentModel);
				}
			}
		}
	}

	*//**
	 * 删除App接口权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiId
	 *            App接口ID
	 * @return 无
	 * @throws 无
	 *//*
	private void removeAllAppApiAuthInfo(String appApiId) {

		// 生成App接口职位权限删除对象
		AppApiPostModel appApiPostModel = new AppApiPostModel();
		appApiPostModel.setAppApiId(appApiId);
		// 删除App接口下所有职位权限
		appApiPostMapper.removeAppApiPost(appApiPostModel);

		// 生成App接口角色权限删除对象
		AppApiRoleModel appApiRoleModel = new AppApiRoleModel();
		appApiRoleModel.setAppApiId(appApiId);
		// 删除App接口下所有角色权限
		appApiRoleMapper.removeAppApiRole(appApiRoleModel);

		// 生成App接口部门权限删除对象
		AppApiDepartmentModel appApiDepartmentModel = new AppApiDepartmentModel();
		appApiDepartmentModel.setAppApiId(appApiId);
		// 删除App接口下所有部门权限
		appApiDepartmentMapper.removeAppApiDepartment(appApiDepartmentModel);

	}

}
*/