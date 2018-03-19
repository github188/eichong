/** 
 * FileName CompanyServiceImpl.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 *//*
package com.bluemobi.product.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.dao.CompanyMapper;
import com.bluemobi.product.dao.CompanyPostMapper;
import com.bluemobi.product.dao.DepartmentInclusionMapper;
import com.bluemobi.product.dao.DepartmentMapper;
import com.bluemobi.product.dao.RoleMapper;
import com.bluemobi.product.dao.UserDepartmentMapper;
import com.bluemobi.product.dao.UserMapper;
import com.bluemobi.product.dao.UserRoleMapper;
import com.bluemobi.product.model.CompanyModel;
import com.bluemobi.product.model.DepartmentInclusionModel;
import com.bluemobi.product.model.DepartmentModel;
import com.bluemobi.product.service.CompanyService;
import com.bluemobi.product.utils.MultipartFileUtil;
import com.bluemobi.product.utils.StringUtil;

*//**
 * FileName CompanyServiceImpl.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 公司业务处理类
 *//*
@Service
public class CompanyServiceImpl implements CompanyService {

	// 日志输出对象
	private static Logger log = Logger.getLogger(DepartmentServiceImpl.class);

	*//** 公司表操作用DAO *//*
	@Autowired
	private CompanyMapper companyMapper;

	*//** 部门表操作用DAO *//*
	@Autowired
	private DepartmentMapper departmentMapper;

	*//** 部门关系表操作用DAO *//*
	@Autowired
	private DepartmentInclusionMapper departmentInclusionMapper;

	*//** 职位表操作用DAO *//*
	@Autowired
	private CompanyPostMapper companyPostMapper;

	*//** 用户表操作用DAO *//*
	@Autowired
	private UserMapper userMapper;

	*//** 角色表操作用DAO *//*
	@Autowired
	private RoleMapper roleMapper;

	*//** 用户角色表操作用DAO *//*
	@Autowired
	UserRoleMapper userRoleMapper;

	*//** 用户部门表操作用DAO *//*
	@Autowired
	UserDepartmentMapper userDepartmentMapper;

	*//**
	 * 根据公司ID取得公司信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyId
	 *            公司ID
	 * @return CompanyModel 登录公司信息
	 * @throws 无
	 *//*
	public CompanyModel findCompany(String companyId) {

		// 公司信息
		CompanyModel company;

		// 取得公司信息
		company = companyMapper.findCompany(companyId);

		// 返回公司一览
		return company;
	}

	*//**
	 * 添加公司
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param ExpressAgent
	 *            公司信息
	 * @return CompanyModel 公司信息
	 * @throws 无
	 *//*
	public String addCompany(CompanyModel companyModel) {

		// 部门对象
		DepartmentModel departmentModel = null;
		// 部门关系对象
		DepartmentInclusionModel deptInclusionModel = null;

		// 取得部门对象
		departmentModel = makeCompanyDept(companyModel, false);
		// 取得部门关系对象
		deptInclusionModel = makeCompanyDeptInc(companyModel, false);

		// 如果公司类型为空，默认为系统公司
		if (StringUtil.isEmpty(companyModel.getCompanyType())) {
			companyModel.setCompanyType(CommonConsts.COMPANY_TYPE_SYSTEM);
		}
		
		// 调用DAO执行公司添加处理
		companyMapper.addCompany(companyModel);

		// 添加部门表信息
		departmentMapper.addDepartment(departmentModel);
		// 添加部门关系表信息
		departmentInclusionMapper.addDeptInclusion(deptInclusionModel);

		// 保存商品图片
		MultipartFileUtil.saveAllMulti(companyModel,
				CommonConsts.MULTI_TYPE_COMPANY, companyModel.getCompanyId(),
				companyModel.getCreateUser(),false);

		return companyModel.getCompanyId();
	}

	*//**
	 * 编辑公司
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyModel
	 *            公司信息
	 * @return String 处理结果标识
	 * @throws 无
	 *//*
	public String modifyCompany(CompanyModel companyModel) {

		// 部门对象
		DepartmentModel departmentModel = null;
		// 更新处理结果
		String processResult = CommonConsts.PROCESS_STATUS_OK;

		// 取得部门对象
		departmentModel = makeCompanyDept(companyModel, false);

		// 保存商品图片
		MultipartFileUtil.saveAllMulti(companyModel,
				CommonConsts.MULTI_TYPE_COMPANY, companyModel.getCompanyId(),
				companyModel.getCreateUser(),false);

		try {
			// 调用DAO执行公司更新处理
			companyMapper.modifyCompany(companyModel);
			// 调用DAO执行部门更新处理
			departmentMapper.modifyDepartment(departmentModel);
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
	 * 删除公司
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyId
	 *            公司ID
	 * @return 无
	 * @throws 无
	 *//*
	public void removeCompany(String companyId) {
		// 部门对象
		DepartmentModel departmentModel = new DepartmentModel();
		// 部门关系对象
		DepartmentInclusionModel deptInclusionModel = new DepartmentInclusionModel();

		// 设置公司ID
		departmentModel.setCompanyId(companyId);
		deptInclusionModel.setCompanyId(companyId);

		// 调用DAO执行公司更新处理
		companyMapper.removeCompany(companyId);

		// 删除该公司所有部门
		departmentMapper.removeDepartment(departmentModel);

		// 删除该公司所有部门关系
		departmentInclusionMapper.removeDeptInclusion(deptInclusionModel);
	}

	*//**
	 * 取得公司一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<CompanyModel> 公司一览
	 * @throws 无
	 *//*
	public List<CompanyModel> getCompanyList() {
		// 公司一览
		List<CompanyModel> listCompany;

		// 取得公司一览
		listCompany = companyMapper.getCompanyList();

		// 返回公司一览
		return listCompany;
	}

	*//**
	 * 查询公司一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<CompanyModel> 公司一览
	 * @throws 无
	 *//*
	public List<CompanyModel> searchCompanyList(CompanyModel company) {
		// 公司一览
		List<CompanyModel> listCompany;

		// 取得公司一览
		listCompany = companyMapper.searchCompanyList(company);

		// 返回公司一览
		return listCompany;

	}

	*//**
	 * 查询公司个数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param departmentModel
	 *            查询用公司对象
	 * @return 公司个数
	 * @throws 无
	 *//*
	@Override
	public Long searchCompanyCount(CompanyModel departmentModel) {
		return companyMapper.searchCompanyCount(departmentModel);
	}

	*//**
	 * 公司唯一性检查
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyId
	 *            公司ID
	 * @return String 公司唯一性检查结果 "true":通过 "false":不通过
	 * @throws 无
	 *//*
	public String checkCompanyUnique(String companyId) {

		// 处理结果
		String processResult = CommonConsts.CHECK_RESULT_OK;
		// 公司数
		int companyCount = 0;

		// 根据公司登录ID取得公司数
		companyCount = companyMapper.getCompanyCountById(companyId);

		// 如果取得的公司数大于0个，返回错误标识
		if (companyCount > 0) {
			return CommonConsts.CHECK_RESULT_NG;
		}

		// 返回处理结果标识
		return processResult;

	}

	*//**
	 * 公司名称唯一性检查
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyName
	 *            公司名称
	 * @param companyId
	 *            公司ID
	 * @return String 公司唯一性检查结果 "true":通过 "false":不通过
	 * @throws 无
	 *//*
	public String checkCompanyNameUnique(String companyName, String companyId) {

		// 处理结果
		String processResult = CommonConsts.CHECK_RESULT_OK;
		// 公司数
		int companyCount = 0;
		CompanyModel searchModel = new CompanyModel();
		searchModel.setCompanyName(companyName);
		searchModel.setCompanyId(companyId);

		// 根据公司登录ID取得公司数
		companyCount = companyMapper.getCompanyCountByName(searchModel);

		// 如果取得的公司数大于0个，返回错误标识
		if (companyCount > 0) {
			return CommonConsts.CHECK_RESULT_NG;
		}

		// 返回处理结果标识
		return processResult;

	}

	*//**
	 * 根据公司信息，生成对应的部门对象
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyModel
	 *            公司对象
	 * @param isUpdate
	 *            是否是更新处理
	 * @return DepartmentModel 部门对象
	 * @throws 无
	 *//*
	private DepartmentModel makeCompanyDept(CompanyModel companyModel,
			boolean isUpdate) {
		// 部门对象
		DepartmentModel departmentModel = new DepartmentModel();

		//
		// 如果公司信息不为空时，执行部门对象生成处理
		//
		if (companyModel != null) {
			// 部门ID
			departmentModel.setCompanyId(companyModel.getCompanyId());
			// 部门ID
			departmentModel.setDepartmentId(companyModel.getCompanyId());
			// 部门名称
			departmentModel.setDepartmentName(companyModel.getCompanyName());
			//
			// 如果不是更新时，设置创建用户信息
			//
			if (!isUpdate) {
				// 创建用户
				departmentModel.setCreateUser(companyModel.getCreateUser());
			}
			// 最后更新用户
			departmentModel.setLastUpdateUser(companyModel.getLastUpdateUser());
		}

		// 返回部门对象
		return departmentModel;
	}

	*//**
	 * 根据公司信息，生成对应的部门关系对象
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyModel
	 *            公司对象
	 * @param isUpdate
	 *            是否是更新处理
	 * @return DepartmentInclusionModel 部门关系对象
	 * @throws 无
	 *//*
	private DepartmentInclusionModel makeCompanyDeptInc(
			CompanyModel companyModel, boolean isUpdate) {
		// 部门关系对象
		DepartmentInclusionModel deptInclusionModel = new DepartmentInclusionModel();

		//
		// 如果公司信息不为空时，执行部门关系对象生成处理
		//
		if (companyModel != null) {
			// 部门ID
			deptInclusionModel.setCompanyId(companyModel.getCompanyId());
			// 父部门ID
			deptInclusionModel.setParentDepartmentId(companyModel
					.getCompanyId());
			// 部门名称
			// 部门ID
			deptInclusionModel.setDepartmentId(companyModel.getCompanyId());
			// 部门名称
			deptInclusionModel.setDepth(0);
			//
			// 如果不是更新时，设置创建用户信息
			//
			if (!isUpdate) {
				// 创建用户
				deptInclusionModel.setCreateUser(companyModel.getCreateUser());
			}
			// 最后更新用户
			deptInclusionModel.setLastUpdateUser(companyModel
					.getLastUpdateUser());
		}

		// 返回部门关系对象
		return deptInclusionModel;
	}
}
*/