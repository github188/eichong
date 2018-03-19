/** 
 * FileName CommonController.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/8/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */
package com.bluemobi.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.model.Area;
import com.bluemobi.product.model.City;
import com.bluemobi.product.model.CodeDetail;
import com.bluemobi.product.model.Province;
import com.bluemobi.product.service.AreaService;
import com.bluemobi.product.service.CityService;
import com.bluemobi.product.service.GroupDetailService;
//import com.bluemobi.product.service.ProvinceService;
import com.bluemobi.product.utils.JacksonJsonMapper;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.RequestParamUtil;

/**
 * FileName CommonController.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/8/9
 * 
 * APP公共处理控制器
 */
@Controller
@RequestMapping("/admin/commonData")
public class CommonController {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(CommonController.class);

	/** 用户业务处理对象 */
	@Autowired
	private GroupDetailService groupDetailService;

	/** 省份业务处理对象 */
	//@Autowired
	//private ProvinceService provinceService;

	/** 城市业务处理对象 */
	@Autowired
	private CityService cityService;

	/** 区县业务处理对象 */
	@Autowired
	private AreaService areaService;

	/**
	 * 取得公共分类列表
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param request
	 *            请求信息
	 * @return 处理结果JSON信息
	 * @throws 无
	 */
	@RequestMapping(value = "/codeList", method = RequestMethod.GET)
	@ResponseBody
	public String categoryList(HttpServletRequest request) {

		// 分类组信息列表
		List<CodeDetail> codeList = null;
		// 分类组ID
		String codeGroupId = RequestParamUtil.getEncodeParam(request,
				"codeGroupId");

		try {
			// 执行分类组信息查询处理
			codeList = groupDetailService.getCodeDetailList(codeGroupId);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
		}
		// 返回处理成功信息以及分类组信息列表
		return new JsonObject(codeList).toString();

	}

	/**
	 * 根据省份ID取得城市一览
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param request
	 *            请求信息
	 * @return 处理结果JSON信息
	 * @throws 无
	 */
	@RequestMapping(value = "/cityList")
	@ResponseBody
	public String getCityList(HttpServletRequest request, String provinceId,
			String titleVal) {

		// 城市列表
		List<City> cityList = new ArrayList<City>();
		// 返回数据
		List<String[]> citys = new ArrayList<String[]>();
		// 城市查询对象
		City city = new City();

		try {

			// titleVal== -1 时，设置默认选项
			if (titleVal != null && titleVal.equals("-1")) {
				citys.add(new String[] { "", "不限" });
			}

			if (provinceId == null || provinceId.length() == 0
					|| provinceId.equals("all")) {
				return JSONObject.toJSONString(provinceId);
			}
			city.setProvinceId(provinceId);

			// 执行城市查询处理
			cityList = cityService.getCityList(provinceId);

			//
			// 转换成数组
			//
			if (cityList != null && cityList.size() > 0) {

				for (City tempcity : cityList) {
					citys.add(new String[] { tempcity.getCityId(),
							tempcity.getCityName() });
				}
			}
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
		}
		// 返回处理成功信息以及城市列表
		return JSONObject.toJSONString(citys);

	}

	/**
	 * 根据城市ID取得区县一览
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param request
	 *            请求信息
	 * @return 处理结果JSON信息
	 * @throws 无
	 */
	@RequestMapping(value = "/areaList")
	@ResponseBody
	public String areaList(HttpServletRequest request, String cityId,
			String titleVal) {

		// 区县列表
		List<Area> areaList = new ArrayList<Area>();
		// 返回数据
		List<String[]> areas = new ArrayList<String[]>();

		try {

			// titleVal== -1 时，设置默认选项
			if (titleVal != null && titleVal.equals("-1")) {
				areas.add(new String[] { "", "不限" });
			}

			if (cityId == null || cityId.length() == 0 || cityId.equals("all")) {
				return JSONObject.toJSONString(cityId);
			}
			Area area = new Area();
			area.setCityId(cityId);

			// 执行区县查询处理
			areaList = areaService.getAreaList(cityId);

			//
			// 转换成数组
			//
			if (areaList != null && areaList.size() > 0) {

				for (Area tempArea : areaList) {
					areas.add(new String[] { tempArea.getAreaId(),
							tempArea.getAreaName() });
				}
			}
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
		}
		// 返回处理成功信息以及区县列表
		return JSONObject.toJSONString(areas);

	}

	/**
	 * 取得省份览
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param request
	 *            请求信息
	 * @return 处理结果JSON信息
	 * @throws 无
	 */
	/*@RequestMapping(value = "/provinceList")
	@ResponseBody
	public String getProvinceList(HttpServletRequest request) {

		// 省份列表
		List<Province> provinceList = null;

		try {

			// 执行省份查询处理
			provinceList = provinceService.getProvinceList();

		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
		}
		// 返回处理成功信息以及省份列表
		return JSONObject.toJSONString(provinceList);

	}*/

}
