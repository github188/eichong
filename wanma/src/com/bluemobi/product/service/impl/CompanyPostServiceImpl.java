/** 
 * FileName CompanyPostServiceImpl.java
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
import com.bluemobi.product.dao.CompanyMapper;
import com.bluemobi.product.dao.CompanyPostMapper;
import com.bluemobi.product.dao.UserPostMapper;
import com.bluemobi.product.dao.UserRoleMapper;
import com.bluemobi.product.model.CompanyPostModel;
import com.bluemobi.product.model.UserPostModel;
import com.bluemobi.product.service.CompanyPostService;

*//**
 * FileName CompanyPostServiceImpl.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 职位业务处理类
 *//*
@Service
public class CompanyPostServiceImpl implements CompanyPostService {

	*//** 职位表操作用DAO *//*
	@Autowired
	private CompanyPostMapper companyPostMapper;

	*//** 用户职位表操作用DAO *//*
	@Autowired
	UserPostMapper userPostMapper;

	*//** 公司表操作用DAO *//*
	@Autowired
	private CompanyMapper companyMapper;

	*//** 用户角色表操作用DAO *//*
	@Autowired
	UserRoleMapper userRoleMapper;

	*//**
	 * 取得职位信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param searchDeptModel
	 *            职位信息
	 * @return CompanyPostModel 职位信息
	 * @throws 无
	 *//*
	public CompanyPostModel findCompanyPost(CompanyPostModel searchDeptModel) {

		// 职位信息
		CompanyPostModel post;

		// 取得职位信息
		post = companyPostMapper.findPost(searchDeptModel);

		// 返回职位一览
		return post;
	}

	*//**
	 * 添加职位
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyPostModel
	 *            职位信息
	 * @param userPostList
	 *            职位用户列表
	 * @return Post 职位信息
	 * @throws 无
	 *//*
	public void addCompanyPost(CompanyPostModel companyPostModel,
			List<UserPostModel> userPostList) {

		// 调用DAO执行职位添加处理
		companyPostMapper.addPost(companyPostModel);

		if (userPostList != null && userPostList.size() > 0) {

			//
			// 职位用户追加处理
			//
			for (UserPostModel userPostModel : userPostList) {

				// 处理分类
				String processType = userPostModel.getPrcessFlg();

				if (StringUtils.equals(CommonConsts.PROCESS_FLG_ADD,
						processType)) {
					// 调用DAO执行职位用户添加处理
					userPostMapper.addUserPost(userPostModel);
				}
			}
		}
	}

	*//**
	 * 编辑职位
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyPostModel
	 *            职位信息
	 * @param userPostList
	 *            职位用户列表
	 * @return 无
	 * @throws 无
	 *//*
	public void modifyCompanyPost(CompanyPostModel companyPostModel,
			List<UserPostModel> userPostList) {

		// 调用DAO执行职位更新处理
		companyPostMapper.modifyPost(companyPostModel);

		if (userPostList != null && userPostList.size() > 0) {

			//
			// 职位用户追加/删除处理
			//
			for (UserPostModel userPostModel : userPostList) {

				// 处理分类
				String processType = userPostModel.getPrcessFlg();

				if (StringUtils.equals(CommonConsts.PROCESS_FLG_ADD,
						processType)) {
					// 调用DAO执行职位用户添加处理
					userPostMapper.addUserPost(userPostModel);
				}

				if (StringUtils.equals(CommonConsts.PROCESS_FLG_DELETE,
						processType)) {
					// 调用DAO执行职位用户添加处理
					userPostMapper.removeUserPost(userPostModel);
				}
			}
		}
	}

	*//**
	 * 删除职位
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyPostModel
	 *            职位对象
	 * @return 无
	 * @throws 无
	 *//*
	public void removeCompanyPost(CompanyPostModel companyPostModel) {

		// 职位用户删除用对象
		UserPostModel userPostModel = new UserPostModel();

		userPostModel.setCompanyId(companyPostModel.getCompanyId());
		userPostModel.setPostId(companyPostModel.getCompanyId());

		// 调用DAO执行职位删除处理
		companyPostMapper.removePost(companyPostModel);
		// 调用DAO执行职位用户删除处理
		userPostMapper.removeUserPost(userPostModel);

	}

	*//**
	 * 取得职位一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<CompanyPostModel> 职位一览
	 * @throws 无
	 *//*
	public List<CompanyPostModel> getCompanyPostList() {
		// 职位一览
		List<CompanyPostModel> listPost;

		// 取得职位一览
		listPost = companyPostMapper.getPostList();

		// 返回职位一览
		return listPost;
	}

	*//**
	 * 查询职位总数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyPostModel
	 *            查询用职位对象
	 * @return long 职位总数
	 * @throws 无
	 *//*
	public long searchPostCount(CompanyPostModel companyPostModel) {
		// 查询职位总数
		return companyPostMapper.searchPostCount(companyPostModel);
	}

	*//**
	 * 查询职位一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param searchModel
	 *            职位信息
	 * @return List<CompanyPostModel> 职位一览
	 * @throws 无
	 *//*
	public List<CompanyPostModel> searchPostList(CompanyPostModel searchModel) {
		// 职位一览
		List<CompanyPostModel> listPost;

		// 取得职位一览
		listPost = companyPostMapper.searchPostList(searchModel);

		// 返回职位一览
		return listPost;

	}

	*//**
	 * 职位唯一性检查
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyPostModel
	 *            职位信息
	 * @return String 职位唯一性检查结果 "true":通过 "false":不通过
	 * @throws 无
	 *//*
	public String checkPostUnique(CompanyPostModel companyPostModel) {

		// 处理结果
		String processResult = CommonConsts.CHECK_RESULT_OK;
		// 职位数
		int postCount = 0;

		// 根据职位登录ID取得职位数
		postCount = companyPostMapper.getPostCountById(companyPostModel);

		// 如果取得的职位数大于0个，返回错误标识
		if (postCount > 0) {
			return CommonConsts.CHECK_RESULT_NG;
		}

		// 返回处理结果标识
		return processResult;

	}

}
*/