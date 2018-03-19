package com.wanma.web.controller;

import com.wanma.web.service.WebCityService;
import com.wanma.web.service.impl.WebAreaServiceImpl;
import com.wanma.web.service.impl.WebProvinceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 获取城市列表
 */
@Controller
@RequestMapping("/web/city")
public class WebCityController {

	@Autowired
	private WebCityService appCityService;

	@Autowired
	private WebProvinceServiceImpl provinceService;

	@Autowired
	private WebAreaServiceImpl areaService;


	/**
	 * 获取省份
	 * @param param
	 * @return
	 */
	@RequestMapping("/getProvinces")
	@ResponseBody
	public String getProvinces(@RequestParam Map<String,String> param){
		return provinceService.getAll(param).toString();
	}

	/**
	 * 获取城市
	 * @param param
	 * @return
	 */
	@RequestMapping("/getCityList")
	@ResponseBody
	public String getCityList(@RequestParam Map<String,String> param){
		return appCityService.getAll(param).toString();
	}


	/**
	 * 获取区
	 * @param param
	 * @return
	 */
	@RequestMapping("/getAreas")
	@ResponseBody
	public String getAreas(@RequestParam Map<String,String> param){
		return areaService.getAll(param).toString();
	}
}