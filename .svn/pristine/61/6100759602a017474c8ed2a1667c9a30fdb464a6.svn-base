/**
 * FileName:PostMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.dao;

import java.util.List;

import com.bluemobi.product.model.CompanyPostModel;

/**
 * 职位表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface CompanyPostMapper {

	/**
	 * 根据职位ID取得职位数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param searchModel
	 *            职位信息
	 * @return int 职位数
	 * @throws 无
	 */
	public int getPostCountById(CompanyPostModel searchModel);

	/**
	 * 取得职位信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param searchModel
	 *            职位信息
	 * @return CompanyPostModel 职位信息
	 * @throws 无
	 */
	public CompanyPostModel findPost(CompanyPostModel searchModel);

	/**
	 * 取得职位一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<CompanyPostModel> 职位一览
	 * @throws 无
	 */
	public List<CompanyPostModel> getPostList();

	/**
	 * 查询职位总数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyPostModel
	 *            查询用职位对象
	 * @return long 职位总数
	 * @throws 无
	 */
	public long searchPostCount(CompanyPostModel companyPostModel);

	/**
	 * 查询职位一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyPostModel
	 *            查询用职位对象
	 * @return List<CompanyPostModel> 职位一览
	 * @throws 无
	 */
	public List<CompanyPostModel> searchPostList(
			CompanyPostModel companyPostModel);

	/**
	 * 添加职位
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param CompanyPostModel
	 *            职位信息
	 * @return Post 职位信息
	 * @throws 无
	 */
	public void addPost(CompanyPostModel companyPostModel);

	/**
	 * 编辑职位
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param CompanyPostModel
	 *            职位信息
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public void modifyPost(CompanyPostModel companyPostModel);

	/**
	 * 删除职位
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param companyPostModel
	 *            职位信息
	 * @return 无
	 * @throws 无
	 */
	public void removePost(CompanyPostModel companyPostModel);

	/**
	 * 取得菜单职位权限列表
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuId
	 *            菜单ID
	 * @return 取得菜单职位权限列表
	 * @throws 无
	 */
	public List<CompanyPostModel> getMenuPostList(String menuId);

	/**
	 * 取得画面功能职位权限列表
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param actionId
	 *            画面功能ID
	 * @return 取得画面功能职位权限列表
	 * @throws 无
	 */
	public List<CompanyPostModel> getActionPostList(String actionId);

	/**
	 * 取得App接口职位权限列表
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiId
	 *            App接口ID
	 * @return 取得App接口职位权限列表
	 * @throws 无
	 */
	public List<CompanyPostModel> getAppApiPostList(String appApiId);

}
