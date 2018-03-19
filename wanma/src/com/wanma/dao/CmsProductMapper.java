package com.wanma.dao;

import java.util.List;
import com.wanma.model.TblProduct;
import com.wanma.model.TblProductcategory;

/**
 * tbl_Product
 * 
 * @author xiay
 * @return 无
 */
public interface CmsProductMapper {
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
	public int addProduct(TblProduct tblProduct);

	/**
	 * 编辑产品
	 * 
	 * @author xiay
	 * @return 无
	 */
	public void modifyProduct(TblProduct tblProduct);
	
	/**
	 * 删除产品
	 * 
	 * @author xiay
	 * @return 无
	 */
	public int removeProduct(TblProduct tblProduct);
	
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
	 * 修改状态
	 * 
	 * @param TblProduct
	 * @return
	 */
	public int updateStatus(TblProduct record);
	
	/**
	 * 查询所属类目
	 * 
	 * @author xiay
	 * @return 无
	 */
	public List<TblProductcategory> searchCategoryList(TblProductcategory record);
	
}
