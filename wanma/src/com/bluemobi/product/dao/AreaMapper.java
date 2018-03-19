/** 
 * FileName AreaMapper.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/8/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */
package com.bluemobi.product.dao;

import java.util.List;
import java.util.Map;

import com.bluemobi.product.model.Area;

/**
 * FileName AreaMapper.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/8/9
 * 
 * 区县表操作用DAO接口Mapper
 */
public interface AreaMapper {

	/**
	 * 根据城市ID取得区县一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param area
	 *            区县信息
	 * @return List<Area> 区县一览
	 * @throws 无
	 */
	public List<Area> getAreaList(Area area);
	public List<Area> getAreaList1(Area area);
	/**
	 * 根据省份名称和区县名称取得区县ID
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param area
	 *            区县信息
	 * @return String 区县ID
	 * @throws 无
	 */
	public String getAreaByName(Area area);

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
	
	/**
	 * 获取省市区列表
	 * @return
	 */
	public List<Map<String, String>> getProvincCityAreaList();
}
