/** 
 * FileName PeaceLiveCommonServiceImpl.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/11/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */

package com.bluemobi.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bluemobi.product.dao.AreaMapper;
import com.bluemobi.product.dao.CityMapper;
//import com.bluemobi.product.dao.ProvinceMapper;
import com.bluemobi.product.model.Area;
import com.bluemobi.product.model.City;
import com.bluemobi.product.model.Province;
import com.bluemobi.product.model.common.BasicModel;
import com.bluemobi.product.service.LocaltionCommonService;
import com.bluemobi.product.utils.StringUtil;

/**
 * FileName PeaceLiveCommonServiceImpl.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/11/9
 * 
 * 区域公共业务处理类
 */
@Service
public class LocaltionCommonServiceImpl implements LocaltionCommonService {
	/** 省份操作用DAO */
	//@Autowired
	//private ProvinceMapper provinceMapper;
	/** 城市操作用DAO */
	@Autowired
	private CityMapper cityMapper;
	/** 区县操作用DAO */
	@Autowired
	private AreaMapper areaMapper;

	/**
	 * 设置省份、城市、区县、社区等信息到画面显示对象中
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param basicModel
	 *            基础对象
	 * @param model
	 *            画面显示对象
	 * @return 无
	 * @throws 无
	 */
	/*public void setAllCommonDataToModel(BasicModel basicModel, Model model) {

		// 省份下拉
		List<Province> provinceList = provinceMapper.getProvinceList();

		if (basicModel != null) {
			// 城市列表
			List<City> cityList = new ArrayList<City>();
			// 执行城市查询处理
			City city = new City();

			if (StringUtil.isNotEmpty(basicModel.getProvinceId())) {
				city.setProvinceId(basicModel.getProvinceId());

				cityList = cityMapper.getCityList(city);
			}
			// 区县列表
			List<Area> areaList = new ArrayList<Area>();

			Area area = new Area();

			if (StringUtil.isNotEmpty(basicModel.getCityId())) {
				area.setCityId(basicModel.getCityId());
				// 执行区县查询处理
				areaList = areaMapper.getAreaList(area);
			}

			model.addAttribute("cityList", cityList);
			model.addAttribute("areaList", areaList);
		}
		model.addAttribute("provinceList", provinceList);
	}*/

	/**
	 * 设置省份、城市、区县、社区等信息到画面显示对象中
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param basicModel
	 *            基础对象
	 * @param model
	 *            画面显示对象
	 * @return 无
	 * @throws 无
	 */
	/*public void setAllCommonDataToModelMap(Map<String, Object> basicModel,
			Model model) {

		// 省份下拉
		List<Province> provinceList = provinceMapper.getProvinceList();

		if (basicModel != null) {
			// 城市列表
			List<City> cityList = new ArrayList<City>();
			// 执行城市查询处理
			City city = new City();

			if (basicModel.get("provinceId") != null) {
				city.setProvinceId(String.valueOf(basicModel.get("provinceId")));

				cityList = cityMapper.getCityList(city);
			}
			// 区县列表
			List<Area> areaList = new ArrayList<Area>();

			Area area = new Area();

			if (basicModel.get("cityId") != null) {
				area.setCityId(String.valueOf(basicModel.get("cityId")));
				// 执行区县查询处理
				areaList = areaMapper.getAreaList(area);
			}

			model.addAttribute("cityList", cityList);
			model.addAttribute("areaList", areaList);
		}
		model.addAttribute("provinceList", provinceList);
	}*/

	/**
	 * 设置省份、城市、区县等信息到画面显示对象中
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param basicModel
	 *            基础对象
	 * @param model
	 *            画面显示对象
	 * @return 无
	 * @throws 无
	 */
	/*public void setAllAreaDataToModel(BasicModel basicModel, Model model) {

		// 省份下拉
		List<Province> provinceList = provinceMapper.getProvinceList();

		if (basicModel != null) {
			// 城市列表
			List<City> cityList = new ArrayList<City>();
			// 执行城市查询处理
			City city = new City();

			if (StringUtil.isNotEmpty(basicModel.getProvinceId())) {
				city.setProvinceId(basicModel.getProvinceId());

				cityList = cityMapper.getCityList(city);
			}
			// 区县列表
			List<Area> areaList = new ArrayList<Area>();

			Area area = new Area();

			if (StringUtil.isNotEmpty(basicModel.getCityId())) {
				area.setCityId(basicModel.getCityId());
				// 执行区县查询处理
				areaList = areaMapper.getAreaList(area);
			}

			model.addAttribute("cityList", cityList);
			model.addAttribute("areaList", areaList);
		}
		model.addAttribute("provinceList", provinceList);
	}*/
}
