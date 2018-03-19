/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.AffirmOrder;
import com.wanma.model.MyOrder;
import com.wanma.model.TblOrder;
import com.wanma.model.TblOrderdetail;
import com.wanma.model.TblOrdertracking;
import com.wanma.web.support.common.ResultResponse;

/**
 * 反馈信息业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface WebOrderService {

	/**
	 * 获取我的订单
	 */
	public List<MyOrder> getMyOrder(Map<String, Object> params);
	public long countMyOrder(Map<String, Object> params);

	/**
	 * 搜索消费订单
	 */
	public List<MyOrder> getShopOrde(Map<String, Object> params);
	public long countShopOrde(Map<String, Object> params);

	/**
	 * 搜索安装订单
	 */
	public List<MyOrder> getInstallOrde(Map<String, Object> params);
	public long countInstallOrde(Map<String, Object> params);

	/**
	 * @Title: confirmOrder
	 * @Description:立即购买 获取用户默认地址 商品信息
	 * @param params
	 * @return
	 */
	public Map<String, Object> confirmOrder(Map<String, Object> params);

	/**
	 * @Title: insertOrderInfo
	 * @Description: 确认订单，插入订单信息
	 * @param params
	 * @return
	 */
//	public int insertOrderInfo(TblOrder order, String[] shcaProductids,
//			String[] prodProductNames, String[] shcaCounts, String[] prodPrices);
	public int insertOrderInfo(TblOrder order, String shcaProductids,
			String prodProductNames, String shcaCounts, String prodPrices);

	/**
	 * @Title: deleteOrder
	 * @Description: 删除订单
	 * @param params
	 * @return
	 */
	public void deleteOrder(Map<String, Object> params);

	/**
	 * 用户确认订单
	 */
	public AffirmOrder AffirmUserOrder(AffirmOrder affirmOrder);

	/**
	 * @Title: selectOrderDetails
	 * @Description: 获取订单详情
	 * @param params
	 * @return
	 */
	public Map<String, Object> selectOrderDetails(java.lang.Integer pkOrder);
	
	/**
	 * @Title: insertOrder
	 * @Description: 购物车页面，结算新增订单
	 * @param params
	 * @return
	 */
	public ResultResponse<String> insertShopOrder(String ids,String counts, String totalMoney,
			String userId,int ordeCommodityTotal);
	
	/**
	 * @Title: get
	 * @Description: 获取订单信息
	 * @param params
	 * @return
	 */
	public TblOrder get(java.lang.Integer pkOrder);
	
	/**
	 * @Title: selectProductsByOrderId
	 * @Description: 获取订单中商品信息
	 * @param params
	 * @return
	 */
	public List<TblOrderdetail> selectProductsByOrderId(
			java.lang.Integer orDeParentId);
	
	/**
	 * @Title: updateOrder
	 * @Description: 结算，更新订单状态和收货地址
	 * @param params
	 * @return
	 */
	public void updateOrder(String pkOrder, String userId);
	
	/**
	 * @Title: selectOrdertracking
	 * @Description: 获取订单跟踪信息
	 * @param pkOrder
	 *            订单主键
	 * @return
	 */
	public List<TblOrdertracking> findOrderTracks(Integer pkOrder);
	
	/**
	 * @Title: selectNoAddProduct
	 * @Description: 获取已购买还未设置安装地址的商品 
	 * @param ordeUserid
	 *        用户id
	 * @return
	 */
	public List<TblOrderdetail> selectNoAddProduct(String ordeUserid);
	
	/**
	 * @Title: updateOrderstatus
	 * @Description: 更新订单状态
	 * @param tblOrder
	 */
	public void updateOrderstatus(TblOrder tblOrder);
}
