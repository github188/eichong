/** 
 * FileName UserService.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */

package com.wanma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.TblCityMapper;
import com.wanma.service.TblCityService;

/**
 * FileName TblProvinceService.java
 * 
 * Version 2.2
 * 
 * Create by gx 2016/5/24
 * 
 * 省份表
 */
@Service
public class TblCityServiceImpl implements TblCityService {
	
	/** 省份表操作用DAO */
	@Autowired
	private TblCityMapper tblCityMapper;
	

	@Override
	public String getCityName(String cityId) {
		return tblCityMapper.getCityName(cityId);
	}

	
	
}
