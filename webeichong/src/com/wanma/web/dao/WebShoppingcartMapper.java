package com.wanma.web.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblShoppingcart;
import com.wanma.page.Page;

/**
 * 数据访问接口
 * 
 */
public interface WebShoppingcartMapper {
	public final static String PREFIX = WebShoppingcartMapper.class.getName();

	public TblShoppingcart get(java.lang.Integer pkShoppingcart);

	public <K, V> Map<K, V> findOne(java.lang.Integer pkShoppingcart);

	public <T, K, V> List<T> find(Map<K, V> params);

	/**
	 * @Title: insert
	 * @Description: 加入购物车
	 * @param params
	 * @return
	 */
	public int insert(TblShoppingcart tblShoppingcart);

	/**
	 * @Title: update
	 * @Description: 更新购物车
	 * @param params
	 * @return
	 */
	public int update(TblShoppingcart tblShoppingcart);

	/**
	 * @Title: delete
	 * @Description: 根据主键删除购物车
	 * @param params
	 * @return
	 */
	public int delete(java.lang.Integer pkShoppingcart);

	/**
	 * @Title: selectByshCaProductid
	 * @Description: 根据商品id查询购物车信息
	 * @param params
	 * @return
	 */
	public TblShoppingcart selectByshCaProductid(Map<String, Object> params);

	/**
	 * @Title: findMyCart
	 * @Description: 获取我的购物车
	 * @param params
	 * @return
	 */
	public List<TblShoppingcart> findMyCart(java.lang.Integer pkUserInfo);

	/**
	 * @Title: deleteCart
	 * @Description: 根据用户id和产品id删除购物车
	 * @param params
	 * @return
	 */
	public <K, V> int deleteCart(Map<K, V> params);
	
	/**
	 * @Title: selectByshCaProductidNew
	 * @Description: 根据商品id查询购物车信息
	 * @param params
	 * @return
	 */
	public TblShoppingcart selectByshCaProductidNew(Map<String, Object> params);

}
