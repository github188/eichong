package com.wanma.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.util.DateUtil;
import com.base.util.ObjectUtil;
import com.pub.model.Entity;
import com.pub.model.Pager;
import com.wanma.service.StatisticHistoryService;

/**
 * 统计分析-历史数据
 * 
 * @author 
 * 
 */
@Controller
@RequestMapping("/admin/statistic")
public class StatisticHistoryController {
	@Autowired
	private StatisticHistoryService historyService;
	
	@RequestMapping("/historyPage")
	public String historyPage(){
		return "backstage/statistic/historyData";
	}
	/**
	 * 历史数据
	 * @return
	 */
	@RequestMapping("/historyData")
	@ResponseBody
	public String historyData(Entity entity){
		Map<String,Object> map=historyService.historyData(entity);
		if(ObjectUtil.isEmpty(map)){
			map=new HashMap<String, Object>();
		}
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
		return new BaseResult(map).toString();
	}
	
	
	/**
	 * 充值(金额和次数)折线图数据
	 * @return
	 */
	@RequestMapping("/historyChargeDataForDay")
	@ResponseBody
	public String historyChargeDataForDay(Entity entity){
		List<Map<String, Object>> list = historyService.historyChargeDataForDay(entity);
		if(ObjectUtil.isNotEmpty(list)){
			String startDate=list.get(0).get("date").toString();
			String endDate=list.get(list.size()-1).get("date").toString();
			int size=DateUtil.subtract(endDate, startDate, DateUtil.TYPE_COM_YMD, Calendar.DATE).intValue()+1;
			String[] columns = new String[size];
			String[] nums = new String[size];
			String[] moneys = new String[size];
			for (int i = 0; i < size; i++) {
				// 获取时间
				columns[i] =DateUtil.addDay(startDate, i, DateUtil.TYPE_COM_YMD, DateUtil.TYPE_COM_YMD);
				for (Map<String, Object> obj : list) {
					if (obj.get("date").equals(columns[i])) {
						nums[i] = obj.get("num1").toString();
						moneys[i] = obj.get("money1").toString();
					}
				}
				if (nums[i] == null) {
					nums[i] = "0";
				}
				if (moneys[i] == null) {
					moneys[i] = "0";
				}
			}
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("legend", new String[]{"充值金额", "次数"});
			map.put("columns", columns);
			map.put("充值金额", moneys);
			map.put("次数", nums);
			return new BaseResult(map).toString();
		}else{
			return new BaseFail(5001).toString();
		}
		
		
	}
	
	/**
	 * 充电(金额和次数)折线图数据
	 * @return
	 */
	@RequestMapping("/historyDataForDay")
	@ResponseBody
	public String historyDataForDay(Entity entity){
		List<Map<String, Object>> list = historyService.historyChargeDataForDay(entity);
		if(ObjectUtil.isNotEmpty(list)){
			String startDate=list.get(0).get("date").toString();
			String endDate=list.get(list.size()-1).get("date").toString();
			int size=DateUtil.subtract(endDate, startDate, DateUtil.TYPE_COM_YMD, Calendar.DATE).intValue()+1;
			String[] columns = new String[size];
			String[] nums = new String[size];
			String[] moneys = new String[size];
			for (int i = 0; i < size; i++) {
				// 获取时间
				columns[i] =DateUtil.addDay(startDate, i, DateUtil.TYPE_COM_YMD, DateUtil.TYPE_COM_YMD);
				for (Map<String, Object> obj : list) {
					if (obj.get("date").equals(columns[i])) {
						nums[i] = obj.get("num2").toString();
						moneys[i] = obj.get("money2").toString();
					}
				}
				if (nums[i] == null) {
					nums[i] = "0";
				}
				if (moneys[i] == null) {
					moneys[i] = "0";
				}
			}
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("legend", new String[]{"充电金额", "次数"});
			map.put("columns", columns);
			map.put("充电金额", moneys);
			map.put("次数", nums);
			return new BaseResult(map).toString();
		}else{
			return new BaseFail(5001).toString();
		}
	}
	
	
	
	/**
	 * 历史数据列表
	 * @return
	 */
	@RequestMapping("/historyDataList")
	@ResponseBody
	public String historyDataList(Entity entity, Pager pager){
		long total = historyService.historyDataListCount(entity);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		entity.setPager(pager);
		pager.setTotal(total);
		List<Map<String,Object>> list = historyService.historyDataList(entity);
		return new BaseResult(list,pager).toString();
	}
}
