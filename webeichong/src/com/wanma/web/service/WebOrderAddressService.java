package com.wanma.web.service;

import com.wanma.model.OrderAddress;

/**
 * @Description: 已购商品安装地址关联表业务处理接口
 * @author songjf
 * @createTime：2015-6-16 下午06:35:21
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface WebOrderAddressService {

	/**
	 * @Title: getOrderAddress
	 * @Description: 根据地址主键和商品主键获取信息
	 * @param OrderAddress
	 * @return
	 */
	public OrderAddress getOrderAddress(OrderAddress orderAddress);

	/**
	 * @Title: insertOrderAddress
	 * @Description: 新增已购商品安装地址信息
	 * @param OrderAddress
	 * @return
	 */
	public void insertOrderAddress(String productIds,String productNames,String Quantities,String pkOrderdetails,int pkInstalladdress);

	/**
	 * @Title: updateOrderAddress
	 * @Description: 更新已购商品安装地址信息
	 * @param OrderAddress
	 * @return
	 */
	public int updateOrderAddress(OrderAddress orderAddress);

	/**
	 * @Title: deleteOrderAddress
	 * @Description: 删除已购商品安装地址信息
	 * @param pkOrderaddress
	 *            主键
	 * @return
	 */
	public int deleteOrderAddress(java.lang.Integer pkOrderaddress);

}
