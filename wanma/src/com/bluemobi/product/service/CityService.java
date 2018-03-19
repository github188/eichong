/** 
 * FileName CityService.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */

package com.bluemobi.product.service;

import java.util.List;

import com.bluemobi.product.model.City;

/**
 * FileName CityService.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/8/9
 * 
 * 城市业务处理接口
 */
public interface CityService {

	/**
	 * 根据城市ID取得城市名称
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param city
	 *            城市信息
	 * @return String城市名称
	 * @throws 无
	 */
	public String getCityName(City city);

	/**
	 * 根据省份ID取得城市一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param provinceId
	 *            城市ID
	 * @return List<City> 城市一览
	 * @throws 无
	 */
	public List<City> getCityList(String provinceId);

	public List<City> getCityList1(String provinceId) ;
	/**
	 * 根据省份ID取得城市一览(权限控制)
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param city
	 *            城市信息
	 * @return List<City> 城市一览
	 * @throws 无
	 */
	public List<City> getCityListByAuth(City city);
}
