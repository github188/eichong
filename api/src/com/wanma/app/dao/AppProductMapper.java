package com.wanma.app.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblProduct;
import com.wanma.page.Page;

/**
 * @Description: 产品操作dao接口
 * @author songjf
 * @createTime：2015-3-15 下午04:58:49
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface AppProductMapper {
	public final static String PREFIX = AppProductMapper.class.getName();

	
	/**
	 * @Title: getShareProduct
	 * @Description: 商品分享信息
	 * @param params
	 * @return
	 */
	public TblProduct getShareProduct(TblProduct tblProduct);
	
	public TblProduct get(java.lang.Integer pkProduct);

	public <K, V> Map<K, V> findOne(java.lang.Integer pkProduct);

	public <T, K, V> List<T> find(Map<K, V> params);

	public int insert(TblProduct tblProduct);

	public int update(TblProduct tblProduct);

	public int delete(java.lang.Integer pkProduct);

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

	/**
	 * @Title: findProducts
	 * @Description:获取上架产品
	 * @param params
	 * @return
	 */
	public <E, K, V> List<E> findProducts(Map<K, V> params);

	/**
	 * @Title: findProductDetail
	 * @Description: 获取产品详情
	 * @param params
	 * @return
	 */
	public <K, V> Map<K, V> findProductDetail(Map<K, V> params);
	
	/**获取热门产品详情
	 * @Title: findProductDetail
	 * @Description: 
	 * @param params
	 * @return
	 */
	public List<TblProduct> getHotRecommendDetail(TblProduct tblProduct);
	/**
	 * @Title: findProducts
	 * @Description:获取上架产品
	 * @param params
	 * @return
	 */
	public <E, K, V> List<E> findProductListByShopSearch(TblProduct tblProduct);
	
	/**
	 * @Title: selectById
	 * @Description: 立即购买 获取商品信息
	 * @param params
	 * @return 
	 */
	public <K, V> Map<K, V> selectById(Map<K, V> params);

}
