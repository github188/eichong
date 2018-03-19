package com.wanma.web.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblUseraddress;

/**
 * @Description: 用户收货地址业务处理接口
 * @author songjf
 * @createTime：2015-3-21 下午06:43:21
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface WebUseraddressService {

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
	public int insertAddress(TblUseraddress useraddress);

	/**
	 * @Title: updateAddress
	 * @Description: 更新用户收货地址
	 * @param params
	 * @return
	 */
	public int updateAddress(TblUseraddress useraddress);

	/**
	 * @Title: deleteAddress
	 * @Description: 删除用户收货地址
	 * @param params
	 * @return
	 */
	public void deleteAddress(Map<String, Object> params);

	/**
	 * @Title: getAddress
	 * @Description: 根据id获取用户收货地址
	 * @param params
	 * @return
	 */
	public TblUseraddress getAddress(java.lang.Integer pkUseraddress);

	/**
	 * @Title: updateIsDefault
	 * @Description: 更新用户默认地址
	 * @param params
	 * @return
	 */
	public void updateIsDefault(Map<String, Object> params);

	/**
	 * @Title: findDefault
	 * @Description: 获取用户默认地址
	 * @param params
	 * @return
	 */
	public Map<String,Object> findDefault(int pkUserInfo);
	
	/**
	 * @Title: findDefaultNew
	 * @Description: 获取用户默认地址
	 * @param params
	 * @return
	 */
	public TblUseraddress findDefaultNew(int pkUserInfo);
	
	/**
	 * @Title: deleteAddressNew
	 * @Description: 删除用户收货地址
	 * @param params
	 * @return
	 */
	public void deleteAddressNew(String pkUseraddress);
}
