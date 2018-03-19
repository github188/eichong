package com.wanma.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wanma.model.TblCarcompany;

/**
 * @Description: 电动车品牌类型
 * @author songjf
 * @createTime：2015-4-1 下午07:33:56
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface CmsCarCompanyMapper {

	public final static String PREFIX = CmsCarCompanyMapper.class.getName();

	/**
	 * 增加汽车厂家
	 * 
	 * @param params
	 */
	public void insertCarCompany(TblCarcompany params);

	/**
	 * 修改汽车厂家
	 * 
	 * @param params
	 */
	public void updateCarCompany(TblCarcompany params);

	/**
	 * 通过ID删除汽车厂家
	 * 
	 * @param params
	 */
	public void deleteCarCompanyById(TblCarcompany params);

	/**
	 * 查询汽车厂家列表
	 * 
	 * @param params
	 * @return
	 */
	public List<TblCarcompany> findCarCompanyList(TblCarcompany params);

	public long findCarCompanyCount(TblCarcompany params);

	/**
	 * 根据ID查找摸个汽车厂家
	 * 
	 * @param params
	 * @return
	 */

	public TblCarcompany findCarCompanyById(TblCarcompany params);

	/**
	 * 根据ID查找摸个汽车厂家 是否有用该名字命名的厂家
	 * 
	 * @param params
	 * @return
	 */

	public TblCarcompany getByProperty(TblCarcompany params);

}
