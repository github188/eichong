package com.wanma.app.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.OrderInfo;
import com.wanma.model.TblOrder;
import com.wanma.model.TblOrderdetail;
import com.wanma.page.Page;

/**
 * 数据访问接口
 * 
 */
public interface AppOrderMapper {
	public final static String PREFIX = AppOrderMapper.class.getName();

	public TblOrder get(java.lang.Integer pkOrder);

	public <K, V> Map<K, V> findOne(java.lang.Integer pkOrder);

	public <T, K, V> List<T> find(Map<K, V> params);

	/**
	 * @Title: insert
	 * @Description: 新增订单
	 * @param params
	 * @return
	 */
	public int insert(TblOrder tblOrder);

	public int update(TblOrder tblOrder);

	public int delete(java.lang.Integer pkOrder);

	/**
	 * @Title: selectOrderDetail
	 * @Description: 完成支付 获取订单详情
	 * @param params
	 * @return
	 */
	public List<OrderInfo> selectOrderDetail(java.lang.Integer pkOrder);

	/**
	 * @Title: selectOrderInfo
	 * @Description: 获取此订单中商品总数，总价，订单号，成交时间
	 * @param params
	 * @return
	 */
	public <K, V> Map<K, V> selectOrderInfo(java.lang.Integer pkOrder);

	/**
	 * @Title: deleteOrder
	 * @Description: 删除订单 更新订单状态为删除状态
	 * @param params
	 * @return
	 */
	public <K, V> int deleteOrder(Map<K, V> params);
	
	/**
	 * @Title: selectNoAddProduct
	 * @Description: 获取已购买还未设置安装地址的商品 
	 * @param ordeUserid
	 * 			用户id
	 * @return
	 */
	public List<TblOrderdetail> selectNoAddProduct(String ordeUserid);
	
	/**
	 * @Title: updateOrderStatus
	 * @Description: 更新订单状态
	 * @param tblOrder
	 * @return int
	 */
	public int updateOrderStatus(TblOrder tblOrder);

}
