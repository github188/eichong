/**
 * FileName:CompanyMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.dao;

import java.util.List;

import com.bluemobi.product.model.CompanyModel;

/**
 * 公司表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface CompanyMapper {

	/**
	 * 根据用户公司D取得公司数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param departmentId
	 *            公司ID
	 * @return int 公司数
	 * @throws 无
	 */
	public int getCompanyCountById(String departmentId);

	/**
	 * 根据用户公司D取得公司数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyModel
	 *            公司对像
	 * @return int 公司数
	 * @throws 无
	 */
	public int getCompanyCountByName(CompanyModel companyModel);

	/**
	 * 取得公司信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param departmentId
	 *            公司ID
	 * @return CompanyModel 公司信息
	 * @throws 无
	 */
	public CompanyModel findCompany(String departmentId);

	/**
	 * 取得公司一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<CompanyModel> 公司一览
	 * @throws 无
	 */
	public List<CompanyModel> getCompanyList();

	/**
	 * 查询公司一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param departmentModel
	 *            查询用公司对象
	 * @return List<CompanyModel> 公司一览
	 * @throws 无
	 */
	public List<CompanyModel> searchCompanyList(CompanyModel departmentModel);

	/**
	 * 查询公司个数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param departmentModel
	 *            查询用公司对象
	 * @return 公司个数
	 * @throws 无
	 */
	public Long searchCompanyCount(CompanyModel departmentModel);

	/**
	 * 添加公司
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param ExpressAgent
	 *            公司信息
	 * @return Company 公司信息
	 * @throws 无
	 */
	public void addCompany(CompanyModel departmentModel);

	/**
	 * 编辑公司
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param ExpressAgent
	 *            公司信息
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public void modifyCompany(CompanyModel departmentModel);

	/**
	 * 删除公司
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param departmentId
	 *            公司ID
	 * @return 无
	 * @throws 无
	 */
	public void removeCompany(String departmentId);

	/**
	 * 取得社区物业公司信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param communityId
	 *            社区ID
	 * @return CompanyModel 公司信息
	 * @throws 无
	 */
	public CompanyModel getCommunityEstate(String communityId);

}
