package com.wanma.app.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblPurchasehistory;


/**
 * 数据访问接口
 * 
 */
public interface WebPurchasehistoryMapper {

	/**
	 * 获取消费记录
	 * @return String orderId
	 */
	public <T, K, V> List<TblPurchasehistory> findPage(Map<K, V> params);
	/**消费记录分页使用*/
	public <K, V> long countConsume(Map<K, V> params);
	
	/**
	 * 获取购物订单详情
	 * @return String orderId
	 */
	public <T, K, V> List<T> findWallet(Map<K, V> params);
	/**购物订单分页使用*/
	public <K, V> long countWallet(Map<K, V> params);
	/**
	 * 增加充值记录
	 * @return TblPurchasehistory purchase
	 */
	public int insert(TblPurchasehistory purchase);
	/**
	 * 查看充值记录
	 * @return TblPurchasehistory purchase
	 */
	public long getPurchaseHistory(TblPurchasehistory purchase);
	
	public void insertCompany(TblPurchasehistory purchase);
}
