package com.wanma.service;

import java.util.List;

import com.wanma.model.TblOrder;
import com.wanma.model.TblOrderdetail;
/**
 * FileName TblOrderService.java
 * 
 * Version 1.0
 * 
 * Create by xiay
 * 
 * 预约安装处理接口
 */
public interface CmsOrderService {

	/**
	 * 根据预约安装ID取得预约安装信息
	 *
	 * @return
	 * @throws
	 */
	public TblOrder findOrder(String pkOrder);
	

	/**
	 * 取得预约安装一览
	 * 
	 * @return 
	 * @throws 
	 */
	public List<TblOrder> getOrderList();

	/**
	 * 查询预约安装个数
	 * 
	 * @return 
	 * @throws 
	 */
	public long searchOrderCount(TblOrder tblOrder);
	

	/**
	 * 查询预约安装一览
	 * 
	 * @return
	 * @throws 
	 */
	public List<TblOrder> searchOrderList(TblOrder tblOrder);
	
	/**
	 * 根据预约安装ID取得预约安装信息 --商城
	 *
	 * @return
	 * @throws
	 */
	public TblOrder findShop(String pkOrder);
	

	/**
	 * 取得预约安装一览--商城
	 * 
	 * @return 
	 * @throws 
	 */
	public List<TblOrder> getShopList();

	/**
	 * 查询预约安装个数--商城
	 * 
	 * @return 
	 * @throws 
	 */
	public long searchShopCount(TblOrderdetail orderdetail);
	

	/**
	 * 查询预约安装一览--商城
	 * 
	 * @return
	 * @throws 
	 */
	public List<TblOrderdetail> searchShopList(TblOrderdetail orderdetail);
	
	
	
	/**
	 * 根据预约安装ID取得预约安装信息 --纯商家
	 *
	 * @return
	 * @throws
	 */
	public TblOrder findFirmShop(String pkOrder);
	

	/**
	 * 取得预约安装一览--纯商家
	 * 
	 * @return 
	 * @throws 
	 */
	public List<TblOrder> getFirmShopList();

	/**
	 * 查询预约安装个数--纯商家
	 * 
	 * @return 
	 * @throws 
	 */
	public long searchFirmShopCount(TblOrderdetail orderdetail);
	

	/**
	 * 查询预约安装一览--纯商家
	 * 
	 * @return
	 * @throws 
	 */
	public List<TblOrderdetail> searchFirmShopList(TblOrderdetail orderdetail);
	
	
	
	
	/**
	 * 根据订单ID取得订单信息 --产品
	 *
	 * @return
	 * @throws
	 */
	public TblOrder findProOrder(String pkOrder);
	

	/**
	 * 取得订单一览--产品
	 * 
	 * @return 
	 * @throws 
	 */
	public List<TblOrder> getProOrderList();

	/**
	 * 查询订单个数--产品
	 * 
	 * @return 
	 * @throws 
	 */
	public long searcProOrderCount(TblOrder tblOrder);
	

	/**
	 * 查询订单一览--产品
	 * 
	 * @return
	 * @throws 
	 */
	public List<TblOrder> searchProOrderList(TblOrder tblOrder);
	
	/**
	 * 更新订单状态 
	 * 
	 * @author xiay
	 * @return 无
	 */
	public void updateOrderStatus(TblOrder tblOrder);
	
	/**
	 * 根据订单获取订单商品列表
	 * 
	 * @author xiay
	 * @return List<TblOrderdetail>
	 */
	public  List<TblOrderdetail> findByOrderId(TblOrderdetail orderdetail);
	
	/**
	 * 根据订单获取已购买商品总数
	 * 
	 * @author xiay
	 * @return int
	 */
	public int findCountByOrderId(TblOrderdetail orderdetail);
}
