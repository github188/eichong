/** 
 * FileName ProvinceMapper.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/8/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */
package com.bluemobi.product.dao;

import java.util.List;

import com.bluemobi.product.model.Province;
import com.bluemobi.product.model.common.BasicModel;

/**
 * FileName ProvinceMapper.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/8/9
 * 
 * 省份表操作用DAO接口Mapper
 */
public interface ProvinceMapper {

	/**
	 * 根据省份ID取得城市名称
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param provinceId
	 *            省份ID
	 * @return String 省份名称
	 * @throws 无
	 */
	public String getProvinceName(String provinceId);

	/**
	 * 根据省份ID取得详细一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param provinceId
	 *            省份ID
	 * @return List<Province> 详细一览
	 * @throws 无
	 */
	public List<Province> getProvinceList();

	/**
	 * 根据省份ID取得详细一览(权限控制)
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param provinceId
	 *            省份ID
	 * @return List<Province> 详细一览
	 * @throws 无
	 */
	public List<Province> getProvinceListByAuth(BasicModel authModel);
}
