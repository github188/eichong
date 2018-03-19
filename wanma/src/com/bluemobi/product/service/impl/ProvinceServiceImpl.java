/** 
 * FileName ProvinceService.java
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

import com.bluemobi.product.dao.ProvinceMapper;
import com.bluemobi.product.model.Province;
import com.bluemobi.product.model.common.BasicModel;
import com.bluemobi.product.service.ProvinceService;

/**
 * FileName ProvinceService.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/8/9
 * 
 * 省份业务处理类
 */
@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {

	/** 省份操作用DAO */
	@Autowired
	private ProvinceMapper provinceMapper;

	/**
	 * 根据省份ID取得省份名称
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param provinceId
	 *            省份ID
	 * @return String 省份名称
	 * @throws 无
	 */
	public String getProvinceName(String provinceId) {
		// 根据省份ID取得省份名称
		return provinceMapper.getProvinceName(provinceId);
	}

	/**
	 * 取得省份一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @return List<Province> 省份一览
	 * @throws 无
	 */
	public List<Province> getProvinceList() {
		// 取得省份一览
		return provinceMapper.getProvinceList();

	}

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
	public List<Province> getProvinceListByAuth(BasicModel authModel) {
		// 取得省份一览
		return provinceMapper.getProvinceListByAuth(authModel);

	}
}
