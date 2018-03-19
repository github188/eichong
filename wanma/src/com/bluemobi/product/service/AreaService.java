/** 
 * FileName AreaService.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */

package com.bluemobi.product.service;

import java.util.List;

import com.bluemobi.product.model.Area;

/**
 * FileName AreaService.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/8/9
 * 
 * 区县业务处理接口
 */
public interface AreaService {

	/**
	 * 根据省份ID取得区县一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param cityId
	 *            城市ID
	 * @return List<Area> 区县一览
	 * @throws 无
	 */
	public List<Area> getAreaList(String cityId);
	public List<Area> getAreaList1(String cityId);
	/**
	 * 根据省份名称和区县名称取得区县ID
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param provinceName
	 *            省份名称
	 * @param areaName
	 *            区县名称
	 * @return String 区县ID
	 * @throws 无
	 */
	public String getAreaByName(String provinceName, String areaName);

	/**
	 * 根据城市ID取得区县一览(权限控制)
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param area
	 *            区县信息
	 * @return List<Area> 区县一览
	 * @throws 无
	 */
	public List<Area> getAreaListByAuth(Area area);
}
