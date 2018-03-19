package com.wanma.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.util.ObjectUtil;
import com.pub.model.Pager;
import com.wanma.service.StatisticFaultService;

/**
 * 统计分析-故障统计
 * 
 * @author 
 * 
 */
@Controller
@RequestMapping("/admin/statistic")
public class StatisticFaultController {

	private static Logger log = Logger.getLogger(StatisticFaultController.class);
	
	@Autowired
	StatisticFaultService statisticFaultService;
	
	@RequestMapping("/faultPage")
	public String assertPage(){
		return "backstage/statistic/fault";
	}
	/**
	 * 故障电桩数 v1,离线电桩数 v2,违规拔枪 v3,BMS通信异常v4,电表异常v5,急停v6,接触器故障v7,过流停止v8,过压停止v9,防雷器故障v10
	 * @return
	 */
	@RequestMapping("/faultDataCount")
	@ResponseBody
	public String faultDataCount(HttpServletRequest request){
		BaseResult result = new BaseResult();
		try{
			String provinceCode = request.getParameter("provinceCode");
			String cityCode = request.getParameter("cityCode");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("provinceCode", provinceCode);
			params.put("cityCode", cityCode);
			Map<String,Object> map = statisticFaultService.queryFaultDataCount(params);
			if(!map.containsKey("v1")){
				map.put("v1", 0);
			}
			if(!map.containsKey("v2")){
				map.put("v2", 0);
			}
			if(!map.containsKey("v3")){
				map.put("v3", 0);
			}
			if(!map.containsKey("v4")){
				map.put("v4", 0);
			}
			if(!map.containsKey("v5")){
				map.put("v5", 0);
			}
			if(!map.containsKey("v6")){
				map.put("v6", 0);
			}
			if(!map.containsKey("v7")){
				map.put("v7", 0);
			}
			if(!map.containsKey("v8")){
				map.put("v8", 0);
			}
			if(!map.containsKey("v9")){
				map.put("v9", 0);
			}
			if(!map.containsKey("v10")){
				map.put("v10", 0);
			}
			result.setData(map);
		}catch(Exception e){
			log.error(this.getClass() + ".faultDataCount() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	
	/**
	 * 故障占比-饼图(违规拔枪、BMS通信异常、电表异常、急停、接触器故障、过流停止、过压停止、防雷器故障、连接线断掉、漏电保护器、电桩断电、ccu故障、显示屏故障、电源模块故障)
	 * @return
	 */
	@RequestMapping("/faultDataPie")
	@ResponseBody
	public String faultDataPie(HttpServletRequest request){
		
		BaseResult result = new BaseResult();
		try{
			String provinceCode = request.getParameter("provinceCode");
			String cityCode = request.getParameter("cityCode");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("provinceCode", provinceCode);
			params.put("cityCode", cityCode);
			Map<String,Object> map = statisticFaultService.queryFaultDataCount(params);
			Map<String,Object> resultMap=new HashMap<String, Object>();
			resultMap.put("legend", new String[]{"违规拔枪","BMS通信异常","电表异常","急停","接触器故障","过流停止","过压停止","防雷器故障","连接线断掉","漏电保护器","电桩断电","ccu故障","显示屏故障","电源模块故障"});
			if(ObjectUtil.isNotEmpty(map)){
				resultMap.put("违规拔枪", map.get("v3"));
				resultMap.put("BMS通信异常", map.get("v4"));
				resultMap.put("电表异常", map.get("v5"));
				resultMap.put("急停", map.get("v6"));
				resultMap.put("接触器故障", map.get("v7"));
				resultMap.put("过流停止", map.get("v8"));
				resultMap.put("过压停止", map.get("v9"));
				resultMap.put("防雷器故障", map.get("v10"));
				resultMap.put("连接线断掉", map.get("v11"));
				resultMap.put("漏电保护器", map.get("v12"));
				resultMap.put("电桩断电", map.get("v13"));
				resultMap.put("ccu故障", map.get("v14"));
				resultMap.put("显示屏故障", map.get("v15"));
				resultMap.put("电源模块故障", map.get("v16"));
			}
			result.setData(resultMap);
		}catch(Exception e){
			log.error(this.getClass() + ".faultDataPie() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	
	/**
	 * 详细数据(time时间 v1故障名称、v2桩体编号、v3电桩地址)
	 * @return
	 */
	@RequestMapping("/faultDataDetail")
	@ResponseBody
	public String faultDataDetail(HttpServletRequest request,Pager pager){
		BaseResult result = new BaseResult();
		try{
			String provinceCode = request.getParameter("provinceCode");
			String cityCode = request.getParameter("cityCode");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("provinceCode", provinceCode);
			params.put("cityCode", cityCode);
			long total = statisticFaultService.countChargeFaultDetail(params);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			pager.setTotal(total);
			params.put("pager", pager);
			List<Map<String,Object>> list = statisticFaultService.queryChargeFaultDetail(params);
			result.setData(list);
			result.setPager(pager);
		}catch(Exception e){
			log.error(this.getClass() + ".faultDataDetail() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
}
