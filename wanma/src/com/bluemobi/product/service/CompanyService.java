/**
 * FileName:CompanyService.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 *//*
package com.bluemobi.product.service;

import java.util.List;

import com.bluemobi.product.model.CompanyModel;

*//**
 * 公司业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 *//*
public interface CompanyService {

	*//**
	 * 取得公司信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyId
	 *            公司ID
	 * @return 无
	 * @throws 无
	 *//*
	public CompanyModel findCompany(String companyId);

	*//**
	 * 取得公司一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<Company> 公司一览
	 * @throws 无
	 *//*
	public List<CompanyModel> getCompanyList();

	*//**
	 * 查询公司一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param departmentModel
	 *            查询用公司对象
	 * @return List<CompanyModel> 公司一览
	 * @throws 无
	 *//*
	public List<CompanyModel> searchCompanyList(CompanyModel departmentModel);

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
	public Long searchCompanyCount(CompanyModel departmentModel);

	*//**
	 * 添加公司
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param ExpressAgent
	 *            公司信息
	 * @return Company 公司信息
	 * @throws 无
	 *//*
	public String addCompany(CompanyModel companyModel);

	*//**
	 * 编辑公司
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param ExpressAgent
	 *            公司信息
	 * @return String 处理结果标识
	 * @throws 无
	 *//*
	public String modifyCompany(CompanyModel companyModel);

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
	public void removeCompany(String companyId);

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
	public String checkCompanyUnique(String companyId);

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
	public String checkCompanyNameUnique(String companyName, String companyId);

}
*/