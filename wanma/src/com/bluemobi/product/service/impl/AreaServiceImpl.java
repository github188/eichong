/** 
 * FileName AreaService.java
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

import com.bluemobi.product.dao.AreaMapper;
import com.bluemobi.product.model.Area;
import com.bluemobi.product.service.AreaService;
import com.bluemobi.product.utils.StringUtil;

/**
 * FileName AreaService.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/8/9
 * 
 * 区县业务处理接口
 */
@Service
public class AreaServiceImpl implements AreaService {

	/** 区县操作用DAO */
	@Autowired
	private AreaMapper areaMapper;

	/**
	 * 根据省份ID取得区县一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param areaId
	 *            城市ID
	 * @return List<Area> 区县一览
	 * @throws 无
	 */
	public List<Area> getAreaList(String cityId) {

		Area area = new Area();

		if (StringUtil.isNotEmpty(cityId)) {
			area.setCityId(cityId);
		}
		// 取得城市一览
		return areaMapper.getAreaList(area);
	}
	/**
	 * 根据省份ID取得区县一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param areaId
	 *            城市ID
	 * @return List<Area> 区县一览
	 * @throws 无
	 */
	public List<Area> getAreaList1(String cityId) {

		Area area = new Area();

		if (StringUtil.isNotEmpty(cityId)) {
			area.setCityId(cityId);
		}
		// 取得城市一览
		return areaMapper.getAreaList1(area);
	}
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
	public String getAreaByName(String provinceName, String areaName) {

		if (StringUtil.isEmpty(provinceName) || StringUtil.isEmpty(areaName)) {
			return null;
		}

		Area searchModel = new Area();
		searchModel.setProvinceName(provinceName);
		searchModel.setAreaName(areaName);
		// 取得城市一览
		return areaMapper.getAreaByName(searchModel);
	}

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
	public List<Area> getAreaListByAuth(Area area) {
		return areaMapper.getAreaListByAuth(area);
	}
}
