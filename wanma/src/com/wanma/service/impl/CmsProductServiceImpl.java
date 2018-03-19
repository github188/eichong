package com.wanma.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.common.dao.MultipartFileDao;
import com.bluemobi.product.utils.MultipartFileUtil;
import com.wanma.common.WanmaConstants;
import com.wanma.common.log.SystemControllerLog;
import com.wanma.dao.CmsProductMapper;
import com.wanma.model.TblProduct;
import com.wanma.model.TblProductcategory;
import com.wanma.service.CmsProductService;

@Service
public class CmsProductServiceImpl implements CmsProductService {

	/** 产品表操作到 */
	@Autowired
	private CmsProductMapper tblproductDao;

	/**
	 * 根据商品ID取得商品信息
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param ProductId
	 *            商品ID
	 * @return TblProduct 登录商品信息
	 * @throws 无
	 */
	public TblProduct findProduct(String pkProduct) {

		// 商品信息
		TblProduct Product;

		// 取得商品信息
		Product = tblproductDao.findProduct(pkProduct);

		// 返回商品一览
		return Product;
	}

	/**
	 * 添加商品
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param TblProduct
	 *            商品信息
	 * @return 无
	 * @throws 无
	 */
	@SystemControllerLog(description = "添加商品")
	public void saveProduct(TblProduct tblProduct, List<TblProduct> proList) {

		// 调用DAO执行商品添加处理
		tblproductDao.addProduct(tblProduct);

		for (TblProduct product : proList) {
			if (product.getProdpicType()
					.equals(WanmaConstants.MULTI_LIST_IMAGE)) {
				// 列表图片
				MultipartFileUtil.saveAllMulti(product,
						WanmaConstants.MULTI_LIST_IMAGE,
						String.valueOf(tblProduct.getPkProduct()));
			} else if (product.getProdpicType().equals(
					WanmaConstants.MULTI_DETAIL_IMAGE)) {
				// 详情图片
				MultipartFileUtil.saveAllMulti(product,
						WanmaConstants.MULTI_DETAIL_IMAGE,
						String.valueOf(tblProduct.getPkProduct()));
			}
		}
	}

	/**
	 * 编辑商品
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param TblProduct
	 *            商品信息
	 * @return 无
	 * @throws 无
	 */
	@SystemControllerLog(description = "编辑商品")
	public void modifyProduct(TblProduct tblProduct, List<TblProduct> proList) {
		// 判断是否打折
		if (null == tblProduct.getProdDiscountprice()) {
			// 不打折，折扣价格设置为-1，更新数据库中此值为null
			tblProduct.setProdDiscountprice(new BigDecimal(-1));
		}
		tblProduct.setProdUpdatedate(new Date());
		// 调用DAO执行商品更新处理
		tblproductDao.modifyProduct(tblProduct);

		for (TblProduct product : proList) {
			if (product.getProdpicType()
					.equals(WanmaConstants.MULTI_LIST_IMAGE)) {
				// 列表图片
				MultipartFileDao multipartFileDao = new MultipartFileDao();
				List<String> allMultiFileName = multipartFileDao
						.getAllMultiFileName(WanmaConstants.MULTI_LIST_IMAGE,
								String.valueOf(tblProduct.getPkProduct()));
				MultipartFileUtil.delelteMulti(allMultiFileName,
						WanmaConstants.MULTI_LIST_IMAGE,
						String.valueOf(tblProduct.getPkProduct()));

				MultipartFileUtil.saveAllMulti(product,
						WanmaConstants.MULTI_LIST_IMAGE,
						String.valueOf(tblProduct.getPkProduct()));
			} else if (product.getProdpicType().equals(
					WanmaConstants.MULTI_DETAIL_IMAGE)) {
				// 详情图片
				MultipartFileDao multipartFileDao = new MultipartFileDao();
				List<String> allMultiFileName = multipartFileDao
						.getAllMultiFileName(WanmaConstants.MULTI_DETAIL_IMAGE,
								String.valueOf(tblProduct.getPkProduct()));
				MultipartFileUtil.delelteMulti(allMultiFileName,
						WanmaConstants.MULTI_DETAIL_IMAGE,
						String.valueOf(tblProduct.getPkProduct()));

				MultipartFileUtil.saveAllMulti(product,
						WanmaConstants.MULTI_DETAIL_IMAGE,
						String.valueOf(tblProduct.getPkProduct()));
			}
		}
	}

	/**
	 * 删除商品图片
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param TblProduct
	 *            商品信息
	 * @return 无
	 * @throws 无
	 */
	@SystemControllerLog(description = "删除商品图片")
	public void deleteImage(TblProduct tblProduct, List<TblProduct> proList) {

		// 调用DAO执行商品更新处理
		tblproductDao.modifyProduct(tblProduct);

		for (TblProduct product : proList) {
			if (product.getProdpicType()
					.equals(WanmaConstants.MULTI_LIST_IMAGE)) {
				// 列表图片
				MultipartFileDao multipartFileDao = new MultipartFileDao();
				List<String> allMultiFileName = multipartFileDao
						.getAllMultiFileName(WanmaConstants.MULTI_LIST_IMAGE,
								String.valueOf(tblProduct.getPkProduct()));
				MultipartFileUtil.delelteMulti(allMultiFileName,
						WanmaConstants.MULTI_LIST_IMAGE,
						String.valueOf(tblProduct.getPkProduct()));
			} else if (product.getProdpicType().equals(
					WanmaConstants.MULTI_DETAIL_IMAGE)) {
				// 详情图片
				MultipartFileDao multipartFileDao = new MultipartFileDao();
				List<String> allMultiFileName = multipartFileDao
						.getAllMultiFileName(WanmaConstants.MULTI_DETAIL_IMAGE,
								String.valueOf(tblProduct.getPkProduct()));
				MultipartFileUtil.delelteMulti(allMultiFileName,
						WanmaConstants.MULTI_DETAIL_IMAGE,
						String.valueOf(tblProduct.getPkProduct()));
			}
		}
	}

	/**
	 * 删除商品
	 * 
	 * @return
	 * 
	 */
	@SystemControllerLog(description = "删除商品")
	@Override
	public void deleteProduct(TblProduct tblProduct) {
		// TODO Auto-generated method stub
		tblproductDao.removeProduct(tblProduct);
	}

	/**
	 * 批量删除
	 * 
	 */
	@SystemControllerLog(description = "批量删除商品")
	@Override
	public void deleteProductAll(String pkProducts) {
		// TODO Auto-generated method stub
		String[] deleteArray = pkProducts.split(",");
		TblProduct product = new TblProduct();

		for (int i = 0; i < deleteArray.length; i++) {
			product.setPkProduct(Integer.valueOf(deleteArray[i]));
			tblproductDao.removeProduct(product);
		}

	}

	/**
	 * 取得商品一览
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @return List<TblProduct> 商品一览
	 * @throws 无
	 */
	public List<TblProduct> getProductList() {
		// 商品一览
		List<TblProduct> listProduct;

		// 取得商品一览
		listProduct = tblproductDao.getProductList();

		// 返回商品一览
		return listProduct;
	}

	/**
	 * 查询商品个数
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param TblProduct
	 *            商品信息
	 * @return List<TblProduct> 商品一览
	 * @throws 无
	 */
	public long searchProductCount(TblProduct tblProduct) {
		// 商品个数
		long dataCount;

		// 取得商品个数
		dataCount = tblproductDao.searchProductCount(tblProduct);

		// 返回商品个数
		return dataCount;

	}

	/**
	 * 查询商品一览
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param TblProduct
	 * @return List<TblProduct> 商品一览
	 * @throws 无
	 */
	public List<TblProduct> searchProductList(TblProduct tblProduct) {
		// 商品一览
		List<TblProduct> listProduct;

		// 取得商品一览
		listProduct = tblproductDao.searchProductList(tblProduct);

		// 返回商品一览
		return listProduct;

	}

	/**
	 * 查询所属项目类型
	 * 
	 */
	@Override
	public List<TblProductcategory> searchCategoryList(TblProductcategory record) {
		// TODO Auto-generated method stub
		return tblproductDao.searchCategoryList(record);
	}

	/**
	 * 上架
	 * 
	 */
	@SystemControllerLog(description = "商品上架")
	@Override
	public void updateStatus(TblProduct record) {
		// TODO Auto-generated method stub
		tblproductDao.updateStatus(record);
	}

	/**
	 * 批量上架
	 * 
	 */
	@SystemControllerLog(description = "商品批量上架")
	@Override
	public void updateStatusAll(String pkProducts) {
		// TODO Auto-generated method stub
		String[] removeArray = pkProducts.split(",");
		TblProduct product = new TblProduct();

		for (int i = 0; i < removeArray.length; i++) {
			product.setPkProduct(Integer.valueOf(removeArray[i]));
			product.setProdProductlsadd(2);
			tblproductDao.updateStatus(product);
		}
	}

}
