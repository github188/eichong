package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblProductcategory;

/**
 * @Description: 商品分类操作dao
 * @author songjf
 * @createTime：2015-4-1 下午11:24:13
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface CmsProductcategoryMapper {

	/**
	 * @Title: findCategory
	 * @Description: 获取商品分类
	 * @param params
	 * @return
	 */
	public TblProductcategory findCategory(java.lang.Integer pkProductcategory);

	/**
	 * @Title: findCategoryList
	 * @Description: 获取商品分类列表
	 * @param params
	 * @return
	 */
	public <T, K, V> List<T> findCategoryList(
			TblProductcategory tblProductcategory);

	/**
	 * @Title: findCount
	 * @Description: 获取商品分类总数
	 * @param params
	 * @return
	 */
	public long findCount(TblProductcategory tblProductcategory);

	/**
	 * @Title: insertCategory
	 * @Description: 新增商品分类
	 * @param params
	 * @return
	 */
	public int insertCategory(TblProductcategory tblProductcategory);

	/**
	 * @Title: updateCategory
	 * @Description: 更新商品分类
	 * @param params
	 * @return
	 */
	public int updateCategory(TblProductcategory tblProductcategory);

	/**
	 * @Title: deleteById
	 * @Description: 删除商品分类
	 * @param params
	 * @return
	 */
	public int deleteById(java.lang.Integer pkProductcategory);

}
