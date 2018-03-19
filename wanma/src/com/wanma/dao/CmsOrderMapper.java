package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblOrder;

/**
 * 商城商品订单
 * 
 * @author xiay
 *
 */
public interface CmsOrderMapper {
	/**
	 * 根据商城商品ID取得商城商品信息
	 * 
	 * @author xiay
	 * @return 无
	 */
	public TblOrder findOrder(String pkOrder);
	
	/**
	 * 取得商城商品一览
	 *  
	 * @author xiay
	 * @return 无
	 */
	public List<TblOrder> getOrderList();

	/**
	 * 查询商城商品个数
	 * 
	 * @author xiay
	 * @return 无
	 */
	public long searchOrderCount(TblOrder tblOrder);

	/**
	 * 查询商城商品一览
	 * 
	 * @author xiay
	 * @return 无
	 */
	public List<TblOrder> searchOrderList(TblOrder tblOrder);
	
	
	
	/**
	 * 根据商城商品ID取得商城商品信息
	 * 
	 * @author xiay
	 * @return 无
	 */
	public TblOrder findShop(String pkOrder);
	
	/**
	 * 取得商城商品一览
	 *  
	 * @author xiay
	 * @return 无
	 */
	public List<TblOrder> getShopList();

	/**
	 * 查询商城商品个数
	 * 
	 * @author xiay
	 * @return 无
	 */
	public long searchShopCount(TblOrder tblOrder);

	/**
	 * 查询商城商品一览
	 * 
	 * @author xiay
	 * @return 无
	 */
	public List<TblOrder> searchShopList(TblOrder tblOrder);
	
	
	
	
	/**
	 * 根据商城商品ID取得商城商品信息
	 * 
	 * @author xiay
	 * @return 无
	 */
	public TblOrder findFirmShop(String pkOrder);
	
	/**
	 * 取得商城商品一览
	 *  
	 * @author xiay
	 * @return 无
	 */
	public List<TblOrder> getFirmShopList();

	/**
	 * 查询商城商品个数
	 * 
	 * @author xiay
	 * @return 无
	 */
	public long searchFirmShopCount(TblOrder tblOrder);

	/**
	 * 查询商城商品一览
	 * 
	 * @author xiay
	 * @return 无
	 */
	public List<TblOrder> searchFirmShopList(TblOrder tblOrder);
	
	
	
	
	/**
	 * 根据订单ID取得订单信息（产品）
	 * 
	 * @author xiay
	 * @return 无
	 */
	public TblOrder findProOrder(String pkOrder);
	
	/**
	 * 取得订单一览
	 *  
	 * @author xiay
	 * @return 无
	 */
	public List<TblOrder> getProOrderList();

	/**
	 * 查询订单个数
	 * 
	 * @author xiay
	 * @return 无
	 */
	public long searcProOrderCount(TblOrder tblOrder);

	/**
	 * 查询订单一览
	 * 
	 * @author xiay
	 * @return 无
	 */
	public List<TblOrder> searchProOrderList(TblOrder tblOrder);
	
	/**
	 * 更新订单状态 
	 * 
	 * @author xiay
	 * @return 无
	 */
	public int updateOrderStatus(TblOrder tblOrder);
	
}
