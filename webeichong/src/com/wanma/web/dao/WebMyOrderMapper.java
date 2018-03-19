package com.wanma.web.dao;

import java.util.List;
import java.util.Map;


/**
 * 数据访问接口
 * 
 */
public interface WebMyOrderMapper {

	/**
	 * 获取购物订单String orderState
	 * @return
	 */
	public <T, K, V> List<T> getShopOrde(Map<K, V> params);
	/**购物订单分页使用*/
	public <K, V> long countShopOrde(Map<K, V> params);
	/**
	 * 获取购物订单详情
	 * @return String orderId
	 */
	public <T, K, V> List<T> getShopOrdeDetail(Map<K, V> params);
	/**
	 * 获取安装订单
	 * @return String orderState
	 */
	public <T, K, V> List<T> getInstallOrde(Map<K, V> params);
	/**安装订单分页使用*/
	public <K, V> long countInstallOrde(Map<K, V> params);
	/**
	 * 获取安装订单详情
	 * @return String orderId
	 */
	public <T, K, V> List<T> getInstallOrdeDetail(Map<K, V> params);

	/**
	 * 获取全部/已完成订单String orderState
	 * @return
	 */
	public <T, K, V> List<T> getMyOrder(Map<K, V> params);
	/**全部/已完成订单分页使用*/
	public <K, V> long countMyOrder(Map<K, V> params);
}
