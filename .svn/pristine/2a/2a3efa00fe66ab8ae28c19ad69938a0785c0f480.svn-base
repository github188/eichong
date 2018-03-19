package com.wanma.service;

import java.util.List;

import com.wanma.model.TblProduct;
import com.wanma.model.TblProductcategory;
/**
 * FileName CmsProductService.java
 * 
 * Version 1.0
 * 
 * Create by xiay
 * 
 * 商品管理处理接口
 */
public interface CmsProductService {
	/**
	 * 根据产品ID取得产品信息
	 * 
	 * @author xiay
	 * @return 无
	 */
	public TblProduct findProduct(String pkProduct);

	/**
	 * 添加产品
	 * 
	 * @author xiay
	 * @return 无
	 */
	public void saveProduct(TblProduct tblProduct,List<TblProduct> proList);

	/**
	 * 编辑产品
	 * 
	 * @author xiay
	 * @return 无
	 */
	public void modifyProduct(TblProduct tblProduct,List<TblProduct> proList);
	
	/**
	 * 删除产品
	 * 
	 * @author xiay
	 * @return 无
	 */
	public void deleteProduct(TblProduct tblProduct);
	
	/**
	 * 批量删除
	 * 
	 * @author xiay
	 * @return 无
	 */
	public void deleteProductAll(String pkProducts);
	
	/**
	 * 取得产品一览
	 *  
	 * @author xiay
	 * @return 无
	 */
	public List<TblProduct> getProductList();

	/**
	 * 查询产品个数
	 * 
	 * @author xiay
	 * @return 无
	 */
	public long searchProductCount(TblProduct tblProduct);

	/**
	 * 查询产品一览
	 * 
	 * @author xiay
	 * @return 无
	 */
	public List<TblProduct> searchProductList(TblProduct tblProduct);
	
	/**
	 * 查询所属类目
	 * 
	 * @param tblCarinfo
	 * @return
	 */
	public List<TblProductcategory> searchCategoryList(TblProductcategory record);
	
	/**
	 * 上架
	 * 
	 * 
	 */
	public void updateStatus(TblProduct record);
	
	/**
	 * 批量上架
	 * 
	 * 
	 */
	public void updateStatusAll(String pkProducts);
	
	/**
	 * 删除商品图片
	 * 
	 * 
	 */
	public void deleteImage(TblProduct tblProduct,List<TblProduct> proList);
}
