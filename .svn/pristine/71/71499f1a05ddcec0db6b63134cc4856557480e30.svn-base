package com.wanma.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.pub.model.Pager;
import com.wanma.service.StatisticChargeService;

/**
 * 统计分析-充电统计
 * 
 * @author 
 * 
 */
@Controller
@RequestMapping("/admin/statistic")
public class StatisticChargeController {

	private static Logger log = Logger.getLogger(StatisticChargeController.class);
	
	@Autowired
	StatisticChargeService statisticChargeService;
	
	@RequestMapping("/chargePage")
	public String assertPage(){
		return "backstage/statistic/charge";
	}
	
	/**
	 * 充电度数 v1,充电时长(小时) v2,充电次数 v3,充电金额v4
	 * @return
	 */
	@RequestMapping("/chargeDataCount")
	@ResponseBody
	public String chargeDataCount(HttpServletRequest request){
		BaseResult result = new BaseResult();
		try{
			String provinceCode = request.getParameter("provinceCode");
			String cityCode = request.getParameter("cityCode");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("provinceCode", provinceCode);
			params.put("cityCode", cityCode);
			Map<String,Object> map = statisticChargeService.queryChargeDataCount(params);
			if(null == map.get("v1")){
				map.put("v1", 0);
			}
			if(null == map.get("v2")){
				map.put("v2", 0);
			}
			if(null == map.get("v3")){
				map.put("v3", 0);
			}
			if(null == map.get("v4")){
				map.put("v4", 0);
			}
			result.setData(map);
		}catch(Exception e){
			log.error(this.getClass() + ".chargeDataCount() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	
	/**
	 * 充电度数-线形图(充电度数)
	 * @return
	 */
	@RequestMapping("/chargeDataLine")
	@ResponseBody
	public String chargeDataLine(HttpServletRequest request){
		BaseResult result = new BaseResult();
		try{
			String provinceCode = request.getParameter("provinceCode");
			String cityCode = request.getParameter("cityCode");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("provinceCode", provinceCode);
			params.put("cityCode", cityCode);
			List<Map<String,Object>> list = statisticChargeService.queryChargeDataLine(params);
			Map<String,Object> resultMap=new HashMap<String, Object>();
			resultMap.put("legend", new String[]{"充电度数"});
			List<Object> columns = new ArrayList<Object>();
			List<Object> charge = new ArrayList<Object>();
			if(CollectionUtils.isNotEmpty(list)){
				for (Map<String, Object> map : list) {
					columns.add(map.get("time"));
					charge.add(map.get("v1"));
				}
			}
			resultMap.put("columns", columns);
			resultMap.put("充电度数", charge);
			result.setData(resultMap);
		}catch(Exception e){
			log.error(this.getClass() + ".assertDataDetail() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	
	/**
	 * 交直流充电占比-饼状图
	 * @return
	 */
	@RequestMapping("/chargeDataPie")
	@ResponseBody
	public String chargeDataPie(HttpServletRequest request){
		BaseResult result = new BaseResult();
		try{
			String provinceCode = request.getParameter("provinceCode");
			String cityCode = request.getParameter("cityCode");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("provinceCode", provinceCode);
			params.put("cityCode", cityCode);
			Map<String,Object> map = statisticChargeService.queryChargeDataPie(params);
			Map<String,Object> resultMap=new HashMap<String, Object>();
			resultMap.put("legend", new String[]{"直流桩充电度数", "交流桩充电度数"});
			resultMap.put("columns", null==map.get("time")?0:map.get("time"));
			resultMap.put("直流桩充电度数", null==map.get("v1")?0:map.get("v1"));
			resultMap.put("交流桩充电度数", null==map.get("v2")?0:map.get("v2"));
			result.setData(resultMap);
		}catch(Exception e){
			log.error(this.getClass() + ".chargeDataPie() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	
	/**
	 * 详细数据(v1时间v2充电度数v3充电时长v4充电次数v5充电金额(元))
	 * @return
	 */
	@RequestMapping("/chargeDataDetail")
	@ResponseBody
	public String chargeDataDetail(Pager pager,HttpServletRequest request){
		BaseResult result = new BaseResult();
		try{
			String provinceCode = request.getParameter("provinceCode");
			String cityCode = request.getParameter("cityCode");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("provinceCode", provinceCode);
			params.put("cityCode", cityCode);
			long total = statisticChargeService.countChargeDataDetail(params);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			pager.setTotal(total);
			params.put("pager", pager);
			List<Map<String,Object>> list = statisticChargeService.queryChargeDataDetail(params);
			result.setData(list);
			result.setPager(pager);
		}catch(Exception e){
			log.error(this.getClass() + ".chargeDataDetail() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	
	
}
