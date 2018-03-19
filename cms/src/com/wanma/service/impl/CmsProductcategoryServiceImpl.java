package com.wanma.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsProductcategoryMapper;
import com.wanma.model.TblProductcategory;
import com.wanma.service.CmsProductcategoryService;

/**
 * @Description: 商品分类处理实现类
 * @author songjf
 * @createTime：2015-4-1 下午11:38:44
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class CmsProductcategoryServiceImpl implements CmsProductcategoryService {
	/* 商品分类操作dao */
	@Autowired
	private CmsProductcategoryMapper productcategoryMapper;

	/**
	 * @Title: findCategory
	 * @Description: 获取商品分类
	 * @param params
	 * @return
	 */
	@Override
	public TblProductcategory findCategory(Integer pkProductcategory) {
		return productcategoryMapper.findCategory(pkProductcategory);
	}

	/**
	 * @Title: findCategoryList
	 * @Description: 获取商品分类列表
	 * @param params
	 * @return
	 */
	@Override
	public <T, K, V> List<T> findCategoryList(
			TblProductcategory tblProductcategory) {
		return productcategoryMapper.findCategoryList(tblProductcategory);
	}

	/**
	 * @Title: findCount
	 * @Description: 获取商品分类总数
	 * @param params
	 * @return
	 */
	@Override
	public long findCount(TblProductcategory tblProductcategory) {
		return productcategoryMapper.findCount(tblProductcategory);
	}

	/**
	 * @Title: insertCategory
	 * @Description: 新增商品分类
	 * @param params
	 * @return
	 */
	@Override
	public int insertCategory(TblProductcategory tblProductcategory) {
		tblProductcategory.setPrcaCreatedate(new Date());
		tblProductcategory.setPrcaUpdatedate(new Date());
		tblProductcategory.setPrcaParentid(0);
		return productcategoryMapper.insertCategory(tblProductcategory);
	}

	/**
	 * @Title: updateCategory
	 * @Description: 更新商品分类
	 * @param params
	 * @return
	 */
	@Override
	public int updateCategory(TblProductcategory tblProductcategory) {
		tblProductcategory.setPrcaUpdatedate(new Date());
		return productcategoryMapper.updateCategory(tblProductcategory);
	}

	/**
	 * @Title: deleteById
	 * @Description: 删除商品分类
	 * @param params
	 * @return
	 */
	@Override
	public int deleteById(Integer pkProductcategory) {
		return productcategoryMapper.deleteById(pkProductcategory);
	}

	/**
	 * @Title: deleteProductcategorys
	 * @Description: 批量删除商品分类
	 * @param params
	 * @return
	 */
	@Override
	public void deleteProductcategorys(String pkProductcategorys) {
		String[] categoryArray = pkProductcategorys.split(",");
		for (int i = 0; i < categoryArray.length; i++) {
			productcategoryMapper.deleteById(Integer
					.parseInt(categoryArray[i]));
		}
	}

}
