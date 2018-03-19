/** 
 * FileName DepartmentServiceImpl.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 *//*
package com.bluemobi.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.dao.DepartmentInclusionMapper;
import com.bluemobi.product.dao.DepartmentMapper;
import com.bluemobi.product.dao.UserDepartmentMapper;
import com.bluemobi.product.model.DepartmentInclusionModel;
import com.bluemobi.product.model.DepartmentModel;
import com.bluemobi.product.model.UserDepartmentModel;
import com.bluemobi.product.service.DepartmentService;

*//**
 * FileName DepartmentServiceImpl.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 部门业务处理类
 *//*
@Service
public class DepartmentServiceImpl implements DepartmentService {

	// 日志输出对象
	private static Logger log = Logger.getLogger(DepartmentServiceImpl.class);

	*//** 部门表操作用DAO *//*
	@Autowired
	private DepartmentMapper departmentMapper;

	*//** 部门关系表操作用DAO *//*
	@Autowired
	DepartmentInclusionMapper departmentInclusionMapper;

	*//** 用户部门表操作用DAO *//*
	@Autowired
	UserDepartmentMapper userDepartmentMapper;

	*//**
	 * 取得部门信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param searchDeptModel
	 *            部门信息
	 * @return DepartmentModel 部门信息
	 * @throws 无
	 *//*
	public DepartmentModel findDepartment(DepartmentModel searchDeptModel) {

		// 部门信息
		DepartmentModel department;
		// 上级部门名称
		String parentDeptName = "";

		// 取得部门信息
		department = departmentMapper.findDepartment(searchDeptModel);

		// 取得上级部门名称
		parentDeptName = departmentMapper.getParentDeptName(searchDeptModel);

		// 设置直属上级部门ID和明朝
		department.setParentDepartmentId(getRealParentDept(searchDeptModel));
		department.setParentDepartmentName(parentDeptName);

		// 返回部门一览
		return department;
	}

	*//**
	 * 添加部门
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param departmentModel
	 *            部门信息
	 * @param userDepartmentList
	 *            部门用户列表
	 * @return Department 部门信息
	 * @throws 无
	 *//*
	public String addDepartment(DepartmentModel departmentModel,
			List<UserDepartmentModel> userDepartmentList) {
		// 更新处理结果
		String processResult = CommonConsts.PROCESS_STATUS_OK;

		// 部门关系一览
		List<DepartmentInclusionModel> deptInclusionList = null;

		// 生成部门关系一览
		deptInclusionList = getDeptInclusionList(departmentModel);

		try {
			// 调用DAO执行部门添加处理
			departmentMapper.addDepartment(departmentModel);

			if (userDepartmentList != null && userDepartmentList.size() > 0) {
				//
				// 部门用户追加处理
				//
				for (UserDepartmentModel userDepartmentModel : userDepartmentList) {

					// 处理分类
					String processType = userDepartmentModel.getPrcessFlg();

					if (StringUtils.equals(CommonConsts.PROCESS_FLG_ADD,
							processType)) {
						// 调用DAO执行部门用户添加处理
						userDepartmentMapper
								.addUserDepartment(userDepartmentModel);
					}
				}
			}

			if (deptInclusionList != null && deptInclusionList.size() > 0) {

				//
				// 部门关系追加
				//
				for (DepartmentInclusionModel deptInclusionModel : deptInclusionList) {
					// 调用DAO执行部门关系添加处理
					departmentInclusionMapper
							.addDeptInclusion(deptInclusionModel);
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

	*//**
	 * 编辑部门
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param DepartmentModel
	 *            部门信息
	 * @param userDepartmentList
	 *            部门用户列表
	 * @return String 处理结果标识
	 * @throws 无
	 *//*
	public String modifyDepartment(DepartmentModel department,
			List<UserDepartmentModel> userDepartmentList) {

		// 更新处理结果
		String processResult = CommonConsts.PROCESS_STATUS_OK;

		try {
			// 调用DAO执行部门更新处理
			departmentMapper.modifyDepartment(department);

			if (userDepartmentList != null && userDepartmentList.size() > 0) {

				//
				// 部门用户追加/删除处理
				//
				for (UserDepartmentModel userDepartmentModel : userDepartmentList) {

					// 处理分类
					String processType = userDepartmentModel.getPrcessFlg();

					if (StringUtils.equals(CommonConsts.PROCESS_FLG_ADD,
							processType)) {
						// 调用DAO执行部门用户添加处理
						userDepartmentMapper
								.addUserDepartment(userDepartmentModel);
					}

					if (StringUtils.equals(CommonConsts.PROCESS_FLG_DELETE,
							processType)) {
						// 调用DAO执行部门用户添加处理
						userDepartmentMapper
								.removeUserDepartment(userDepartmentModel);
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

	*//**
	 * 删除部门
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param departmentModel
	 *            部门对象
	 * @return 无
	 * @throws 无
	 *//*
	public void removeDepartment(DepartmentModel departmentModel) {

		// 部门所有关系删除条件对象
		DepartmentInclusionModel searchInclusionModel = new DepartmentInclusionModel();
		searchInclusionModel.setCompanyId(departmentModel.getCompanyId());
		searchInclusionModel.setDepartmentId(departmentModel.getDepartmentId());

		// 取得部门所有关系（包括子部门）
		List<DepartmentInclusionModel> removeDeptIncList = departmentInclusionMapper
				.getAllSelfAndChildInc(searchInclusionModel);

		// 调用DAO执行部门更新处理
		departmentMapper.removeSelfAndChildDept(departmentModel);

		// 删除该部门所有关系
		for (DepartmentInclusionModel deptInclusionModel : removeDeptIncList) {
			departmentInclusionMapper.removeDeptInclusion(deptInclusionModel);
		}
	}

	*//**
	 * 取得部门一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<DepartmentModel> 部门一览
	 * @throws 无
	 *//*
	public List<DepartmentModel> getDepartmentList() {
		// 部门一览
		List<DepartmentModel> listDepartment;

		// 取得部门一览
		listDepartment = departmentMapper.getDepartmentList();

		// 返回部门一览
		return listDepartment;
	}

	*//**
	 * 查询部门一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<DepartmentModel> 部门一览
	 * @throws 无
	 *//*
	public List<DepartmentModel> searchDepartmentList(DepartmentModel department) {
		// 部门一览
		List<DepartmentModel> listDepartment;

		// 取得部门一览
		listDepartment = departmentMapper.searchDepartmentList(department);

		// 返回部门一览
		return listDepartment;

	}

	*//**
	 * 查询部门数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param departmentModel
	 *            查询用部门对象
	 * @return long 部门数
	 * @throws 无
	 *//*
	public long searchDepartmentCount(
			DepartmentModel departmentModel) {
		// 查询部门数
		return departmentMapper.searchDepartmentCount(departmentModel);
	}

	*//**
	 * 部门唯一性检查
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param departmentModel
	 *            部门信息
	 * @return String 部门唯一性检查结果 "true":通过 "false":不通过
	 * @throws 无
	 *//*
	public String checkDepartmentUnique(DepartmentModel departmentModel) {

		// 处理结果
		String processResult = CommonConsts.CHECK_RESULT_OK;
		// 部门数
		int departmentCount = 0;

		// 根据部门登录ID取得部门数
		departmentCount = departmentMapper
				.getDepartmentCountById(departmentModel);

		// 如果取得的部门数大于0个，返回错误标识
		if (departmentCount > 0) {
			return CommonConsts.CHECK_RESULT_NG;
		}

		// 返回处理结果标识
		return processResult;

	}

	*//**
	 * 取得直属上级部门ID
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param searchDeptModel
	 *            部门信息
	 * @return String 直属上级部门ID
	 * @throws 无
	 *//*
	public String getRealParentDept(DepartmentModel departmentModel) {
		// 直属上级部门ID
		String realParentDept = "";

		// 查询用部门关系对象
		DepartmentInclusionModel searchDeptModel = new DepartmentInclusionModel();

		// 公司ID
		searchDeptModel.setCompanyId(departmentModel.getCompanyId());
		// 部门ID
		searchDeptModel.setDepartmentId(departmentModel.getDepartmentId());

		// 取得直属上级部门ID
		realParentDept = departmentInclusionMapper
				.getRealParentDept(searchDeptModel);

		// 返回直属上级部门ID
		return realParentDept;
	}

	*//**
	 * 生成部门关系列表
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param DepartmentModel
	 *            部门信息
	 * @return List<DepartmentInclusionModel> 部门关系列表
	 * @throws 无
	 *//*
	public List<DepartmentInclusionModel> getDeptInclusionList(
			DepartmentModel departmentModel) {
		// 上级部门ID
		String parentDepartmentId = "";
		// 公司ID
		String companyId = "";
		// 部门ID
		String departmentId = "";
		// 自身部门关系
		DepartmentInclusionModel selfDeptIncModel = new DepartmentInclusionModel();
		// 所有上级部门关系
		List<DepartmentInclusionModel> allDeptIncList = new ArrayList<DepartmentInclusionModel>();
		// 临时所有上级部门关系
		List<DepartmentInclusionModel> tempDeptIncList = new ArrayList<DepartmentInclusionModel>();

		// 查询用部门关系对象
		DepartmentInclusionModel searchModel = new DepartmentInclusionModel();

		// 取得上级部门ID
		parentDepartmentId = departmentModel.getParentDepartmentId();
		// 取得公司ID
		companyId = departmentModel.getCompanyId();
		// 取得公司ID
		departmentId = departmentModel.getDepartmentId();

		// 生成自身部门关系
		selfDeptIncModel.setCompanyId(companyId);
		selfDeptIncModel.setParentDepartmentId(departmentId);
		selfDeptIncModel.setDepartmentId(departmentId);
		selfDeptIncModel.setDepth(0);
		ProcessInfoCommon.setCreateUserInfo(selfDeptIncModel);
		allDeptIncList.add(selfDeptIncModel);

		// 生成上级部门查询用对象
		searchModel.setCompanyId(companyId);
		searchModel.setDepartmentId(parentDepartmentId);

		// 根据直属部门取得直属部门的所有上级部门
		tempDeptIncList = departmentInclusionMapper
				.getAllParentDept(searchModel);

		// 所有直属部门的所有上级部门深度加1
		for (DepartmentInclusionModel tempDeptIncModel : tempDeptIncList) {
			tempDeptIncModel.setDepth(tempDeptIncModel.getDepth() + 1);
			tempDeptIncModel.setDepartmentId(departmentId);
			ProcessInfoCommon.setCreateUserInfo(tempDeptIncModel);
		}

		// 将修改后的直属部门的所有上级部门关系加入当前部门关系
		allDeptIncList.addAll(tempDeptIncList);

		// 返回所有上级部门关系
		return allDeptIncList;
	}

}
*/