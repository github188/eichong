/**
 * FileName:CompanyPostService.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 *//*
package com.bluemobi.product.service;

import java.util.List;

import com.bluemobi.product.model.CompanyPostModel;
import com.bluemobi.product.model.UserPostModel;

*//**
 * 公司职位业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 *//*
public interface CompanyPostService {

	*//**
	 * 取得公司职位信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyPostModel
	 *            公司职位对象
	 * @return 无
	 * @throws 无
	 *//*
	public CompanyPostModel findCompanyPost(CompanyPostModel companyPostModel);

	*//**
	 * 取得公司职位一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<CompanyPost> 公司职位一览
	 * @throws 无
	 *//*
	public List<CompanyPostModel> getCompanyPostList();

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
	public long searchPostCount(CompanyPostModel companyPostModel);

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
	public List<CompanyPostModel> searchPostList(CompanyPostModel searchModel);

	*//**
	 * 添加公司职位
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyPostModel
	 *            公司职位信息
	 * @param userPostList
	 *            职位用户列表
	 * @return 无
	 * @throws 无
	 *//*
	public void addCompanyPost(CompanyPostModel companyPostModel,
			List<UserPostModel> userPostList);

	*//**
	 * 编辑公司职位
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyPostModel
	 *            公司职位信息
	 * @param userPostList
	 *            职位用户列表
	 * @return 无
	 * @throws 无
	 *//*
	public void modifyCompanyPost(CompanyPostModel companyPostModel,
			List<UserPostModel> userPostList);

	*//**
	 * 删除公司职位
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyPostModel
	 *            公司职位对象
	 * @return 无
	 * @throws 无
	 *//*
	public void removeCompanyPost(CompanyPostModel companyPostModel);

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
	public String checkPostUnique(CompanyPostModel companyPostModel);

}
*/