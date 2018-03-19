package com.wanma.app.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.MyOrder;
import com.wanma.model.TblUseraddress;

/**
 * @Description: 用户收货地址表操作dao
 * @author songjf
 * @createTime：2015-3-21 下午06:35:21
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface AppUseraddressMapper {
	public final static String PREFIX = AppUseraddressMapper.class.getName();

	/**
	 * @Title: getAddress
	 * @Description: 根据id获取用户收货地址
	 * @param params
	 * @return
	 */
	public TblUseraddress getAddress(java.lang.Integer pkUseraddress);

	/**
	 * @Title: findDefault
	 * @Description: 获取用户默认地址
	 * @param params
	 * @return 
	 */
	public <K, V> Map<K, V> findDefault(java.lang.Integer pkUserInfo);

	/**
	 * @Title: findAddresses
	 * @Description: 获取用户收货地址
	 * @param params
	 * @return
	 */
	public <T, K, V> List<T> findAddresses(Map<K, V> params);

	/**
	 * @Title: insertAddress
	 * @Description: 新增用户收货地址
	 * @param params
	 * @return
	 */
	public int insertAddress(TblUseraddress tblProductaddress);

	/**
	 * @Title: updateAddress
	 * @Description: 更新用户收货地址
	 * @param params
	 * @return
	 */
	public int updateAddress(TblUseraddress tblProductaddress);

	/**
	 * @Title: deleteAddress
	 * @Description: 删除用户收货地址
	 * @param params
	 * @return
	 */
	public int deleteAddress(java.lang.Integer pkUseraddress);

	/**
	 * @Title: updateDefault
	 * @Description: 根据id更新数据
	 * @param params
	 * @return
	 */
	public <K, V> int updateDefault(Map<K, V> params);

	/**
	 * @Title: updateIsNotDefault
	 * @Description: 更新用户地址为非默认
	 * @param params
	 * @return
	 */
	public <K, V> int updateIsNotDefault(Map<K, V> params);
	/**
	 * 获取收货地址
	 * @return String orderId
	 */
	List<TblUseraddress> find(TblUseraddress tblUseraddress);

}
