/** 
 * FileName CityService.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */

package com.bluemobi.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.dao.CityMapper;
import com.bluemobi.product.model.City;
import com.bluemobi.product.service.CityService;
import com.bluemobi.product.utils.StringUtil;

/**
 * FileName CityService.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/8/9
 * 
 * 城市业务处理类
 */
@Service
public class CityServiceImpl implements CityService {

	/** 城市操作用DAO */
	@Autowired
	private CityMapper cityMapper;

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
	public String getCityName(City city) {

		// 取得城市名称
		return cityMapper.getCityName(city);
	}

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
	public List<City> getCityList(String provinceId) {

		City city = new City();

		if (StringUtil.isNotEmpty(provinceId)) {
			city.setProvinceId(provinceId);
		}
		// 取得城市一览
		return cityMapper.getCityList(city);

	}
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
	public List<City> getCityList1(String provinceId) {

		City city = new City();

		if (StringUtil.isNotEmpty(provinceId)) {
			city.setProvinceId(provinceId);
		}
		// 取得城市一览
		return cityMapper.getCityList1(city);

	}

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
	public List<City> getCityListByAuth(City city) {
		// 取得城市一览
		return cityMapper.getCityListByAuth(city);
	}
}
