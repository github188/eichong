package com.wanma.app.dao;

import java.util.Map;

import com.wanma.model.OrderAddress;



/**
 * @Description: 已购商品安装地址关联表操作dao
 * @author songjf
 * @createTime：2015-6-16 下午06:35:21
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface AppOrderAddressMapper {    
	/**
	 * @Title: getOrderAddress
	 * @Description: 根据地址主键和商品主键获取信息 
	 * @param orderAddress
	 * @return
	 */
	public OrderAddress getOrderAddress(OrderAddress orderAddress);
	
	/**
	 * @Title: insertOrderAddress
	 * @Description: 新增已购商品安装地址信息
	 * @param orderAddress
	 * @return
	 */
	public int insertOrderAddress(OrderAddress orderAddress);
	
	/**
	 * @Title: updateOrderAddress
	 * @Description: 更新已购商品安装地址信息
	 * @param orderAddress
	 * @return
	 */
	public int updateOrderAddress(OrderAddress orderAddress);
	
	/**
	 * @Title: deleteOrderAddress
	 * @Description: 删除已购商品安装地址信息
	 * @param pkOrderaddress
	 * 			主键
	 * @return
	 */
	public int deleteOrderAddress(java.lang.Integer pkOrderaddress );
	
	/**
	 * @Title: findTotalInstallInfo
	 * @Description: 根据主键获取未提交的预约安装商品的总数和总价格
	 * @param OrderAddresses
	 * 			已购商品安装地址关联表主键（多个）
	 * @return
	 */
	public Map<String,Object> findTotalInstallInfo(String OrderAddresses);
	
	/**
	 * @Title: findInstallInfo
	 * @Description: 根据主键获取未提交的预约安装商品的总数和总价格
	 * @param addressid
	 * 			已购商品安装地址关联表主键
	 * @return
	 */
	public Map<String,Object> findInstallInfo(String addressid);
	
	/**
	 * @Title: deleteByInstallAddress
	 * @Description: 根据安装地址主键删除已购商品安装地址信息
	 * @param pkInstalladdress
	 * 			安装地址主键
	 * @return
	 */
	public int deleteByInstallAddress(java.lang.Integer pkInstalladdress );

}


