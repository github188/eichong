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
import com.base.util.StringUtil;
import com.wanma.service.StatisticRealtimeService;
/**
 * 统计分析-实时数据
 * 
 * @author 
 * 
 */
@Controller
@RequestMapping("/admin/statistic")
public class StatisticRealtimeController {

	@Autowired
	private StatisticRealtimeService realtimeService;

	@RequestMapping("/realtimePage")
	public String realtimePage() {
		return "backstage/statistic/realtimeData";
	}

	/**
	 * 实时数据
	 * 
	 * @return
	 */
	@RequestMapping("/realtimeData")
	@ResponseBody
	public String realtimeData() {
		Map<String, Object> map = realtimeService.realtimeData();
		if(ObjectUtil.isNotEmpty(map)){
			return new BaseResult(map).toString();
		}else{
			return new BaseFail("无数据").toString();
		}
		
	}

	/**
	 * 充电(金额和次数)折线图数据
	 * 
	 * @return
	 */
	@RequestMapping("/realtimeDataForHour")
	@ResponseBody
	public String realtimeDataForHour() {
		List<Map<String, Object>> list = realtimeService.realtimeDataForHour();
		if(ObjectUtil.isNotEmpty(list)){
			int size = StringUtil.nullToZero(list.get(list.size() - 1).get("hour")
					.toString());
			//初始化到当日0点
			String startHour = DateUtil.getNow("yyyyMMdd")+"00";
			String[] columns = new String[size];
			String[] nums = new String[size];
			String[] moneys = new String[size];
			for (int i = 0; i < size; i++) {
				// 获取时间
				columns[i] = DateUtil.dateCalculate(startHour,
						Calendar.HOUR_OF_DAY, i, "yyyyMMddHH", "HH");
				for (Map<String, Object> obj : list) {
					if (obj.get("hour").equals(columns[i])) {
						nums[i] = obj.get("num").toString();
						moneys[i] = obj.get("money").toString();
					}
				}
				if (nums[i] == null) {
					nums[i] = "0";
				}
				if (moneys[i] == null) {
					moneys[i] = "0";
				}
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("legend", new String[] { "充电金额", "次数" });
			map.put("columns", columns);
			map.put("充电金额", moneys);
			map.put("次数", nums);
			return new BaseResult(map).toString();
		}else{
			return new BaseFail("无数据").toString();
		}
		
	}

	/**
	 * 实时数据列表
	 * 
	 * @return
	 */
	@RequestMapping("/realtimeDataList")
	@ResponseBody
	public String realtimeDataList() {
		List<Map<String, Object>> list = realtimeService.realtimeDataList();
		return new BaseResult(list).toString();
	}
}
