package com.wanma.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.util.ObjectUtil;
import com.wanma.service.StatisticAssertService;

/**
 * 统计分析-资产统计
 * 
 * @author 
 * 
 */
@Controller
@RequestMapping("/admin/statistic")
public class StatisticAssertController {

	private static Logger log = Logger.getLogger(StatisticAssertController.class);
	
	@Autowired
	StatisticAssertService statisticAssertService;
	
	@RequestMapping("/assertPage")
	public String assertPage(){
		return "backstage/statistic/assert";
	}
	
	/**
	 * 充电点数 v1,直流桩数 v2,交流桩数 v3,电桩枪口数v4
	 * @return
	 */
	@RequestMapping("/assertDataCount")
	@ResponseBody
	public String assertDataCount(){
		BaseResult result = new BaseResult();
		try{
			Map<String,Object> map = statisticAssertService.queryAssertDataCount();
			if(ObjectUtil.isEmpty(map)){
				map = new HashMap<String,Object>();
			}
			result.setData(map);
		}catch(Exception e){
			log.error(this.getClass() + ".assertDataCount() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	
	/**
	 * 电桩区域分布-地图(省份、充电点、电桩)
	 * @return
	 */
	@RequestMapping("/assertDataMap")
	@ResponseBody
	public String assertDataMap(){
		BaseResult result = new BaseResult();
		try{
			Map<String,Object> resultMap=new HashMap<String, Object>();
			List<Map<String,Object>> list = statisticAssertService.queryAssertDataMap();
			
			Map<String,Object> provinceMap = null;
			List<Map<String, Object>> provinceList = new ArrayList<Map<String, Object>>();
			if(ObjectUtil.isNotEmpty(list)){
				for (Map<String, Object> map2 : list) {
					provinceMap = new HashMap<String,Object>();
					provinceMap.put("name", map2.get("provinceName"));
					provinceMap.put("value", map2.get("pileNum"));
					provinceMap.put("id", map2.get("provinceCode"));
					provinceList.add(provinceMap);
				}
			}
			resultMap.put("legend", new String[]{"电桩数"});
			resultMap.put("columns", provinceList);
			result.setData(resultMap);
		}catch(Exception e){
			log.error(this.getClass() + ".assertDataMap() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	
	/**
	 * 交直流电桩占比-饼图(交流桩、直流桩)
	 * @return
	 */
	@RequestMapping("/assertDataPie")
	@ResponseBody
	public String assertDataPie(){
		BaseResult result = new BaseResult();
		try{
			Map<String,Object> resultMap=new HashMap<String, Object>();
			resultMap.put("legend", new String[]{"交流桩数量", "直流桩数量"});
			Map<String,Object> map = statisticAssertService.queryAssertDataCount();
			if(ObjectUtil.isNotEmpty(map)){
				resultMap.put("直流桩数量", map.get("v1"));
				resultMap.put("交流桩数量", map.get("v2"));
			}
			result.setData(resultMap);
		}catch(Exception e){
			log.error(this.getClass() + ".assertDataCount() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	
	/**
	 * 按地域省份、地市维度进行统计(v1区域v3充电点数、v4电桩数、v5交流桩、v6直流桩)
	 * @return
	 */
	@RequestMapping("/assertDataDetail")
	@ResponseBody
	public String assertDataDetail(){
		BaseResult result = new BaseResult();
		try{
			List<Map<String,Object>> list = statisticAssertService.queryAssertDataDetail();
			if(ObjectUtil.isEmpty(list)){
				list = new ArrayList<Map<String,Object>>();
			}
			result.setData(list);
		}catch(Exception e){
			log.error(this.getClass() + ".assertDataDetail() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
}
