/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblShoppingcart;

/**
 * @Description: 购物车业务处理接口
 * @author songjf
 * @createTime：2015-3-16 下午05:49:01
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface AppShoppingcartService {

	/**
	 * @Title: insertIntoCart
	 * @Description: 加入购物车
	 * @param shoppingcart
	 * @return
	 */
	public int insertIntoCart(TblShoppingcart shoppingcart);

	/**
	 * @Title: selectByshCaProductid
	 * @Description: 根据产品id获取购物车信息
	 * @param params
	 * @return
	 */
	public TblShoppingcart selectByshCaProductid(Map<String, Object> params);

	/**
	 * @Title: updateCart
	 * @Description: 更新购物车
	 * @param params
	 * @return
	 */
	public int updateCart(TblShoppingcart shoppingcart);

	/**
	 * @Title: deleteCart
	 * @Description: 删除购物车
	 * @param params
	 * @return
	 */
	public void deleteCart(Map<String, Object> params);

	/**
	 * @Title: findMyCart
	 * @Description: 获取我的购物车
	 * @param params
	 * @return
	 */
	public List<TblShoppingcart> findMyCart(java.lang.Integer pkUserInfo);

}
