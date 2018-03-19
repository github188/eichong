package com.wanma.ims.controller.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.OrderStatisticService;

/**
 * 统计分析-充电消费统计-充电数据占比
 */
@Controller
@RequestMapping("/manage/statistic")
public class StatisticsChargeDataController extends BaseController{
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OrderStatisticService orderStatisticService;
	
	// rowNo:排名 A.充电电量 C.充电次数 D.充电金额  cpyName:渠道名称
	@RequestMapping("/getReportChargeData")
	public void getReportChargeData(){
		JsonResult result = new JsonResult();
		Map<String,Object> params = initParam();
		List<Map<String,Object>> list = orderStatisticService.countChargeDataTop10(params);
		Map<String,Object> resultMap=new HashMap<String, Object>();
		List<Object> columns = new ArrayList<Object>();
		List<Object> charge = new ArrayList<Object>();
		String type = request.getParameter("type");
		if(CollectionUtils.isNotEmpty(list)){
			for (Map<String, Object> map : list) {
				columns.add(map.get("cpyName"));
				if("A".equals(type)){
					charge.add(map.get("A"));
				}
				if("C".equals(type)){
					charge.add(map.get("C"));
				}
				if("D".equals(type)){
					charge.add(map.get("D"));
				}
			}
		}
        if("A".equals(type)){
            resultMap.put("legend", new String[]{"充电度数"});
            resultMap.put("充电度数", charge);
        }
        if("C".equals(type)){
            resultMap.put("legend", new String[]{"充电次数"});
            resultMap.put("充电次数", charge);
        }
        if("D".equals(type)){
            resultMap.put("legend", new String[]{"充电金额"});
            resultMap.put("充电金额", charge);
        }
		resultMap.put("columns", columns);
		result.setDataObject(resultMap);
		responseJson(result);
	}
	
	@RequestMapping("/getReportChargeDetail")
	public void getReportChargeDetail(Pager pager){
		JsonResult result = new JsonResult();
		try {
			Map<String,Object> params = initParam();
			params.put("pager", pager);
			Long total = orderStatisticService.countChargeDataDetail(params);
			if (total <= pager.getOffset()) {
				pager.setPageNo(1L);
			}
			pager.setTotal(total);
			List<Map<String,Object>> map = orderStatisticService.getChargeDataDetail(params);
			result.setPager(pager);
			result.setDataObject(map);
			responseJson(result);
		} catch (Exception e) {
			LOGGER.debug(this.getClass() + ".getReportCpyChargeDetail() error : ", e);
			responseJson(new JsonException("获取充电数据对比失败"));
			return;
		}
		responseJson(result);
	}
	

	// type:A.充电电量 C.充电次数 D.充电金额  powerStationName:充电点名称
	@RequestMapping("/getPowerStationChargeData")
	public void getPowerStationChargeData(){
		JsonResult result = new JsonResult();
		Map<String,Object> params = initParam();
		List<Map<String,Object>> list = orderStatisticService.getPowerStationChargeDataDetailTop10(params);
		Map<String,Object> resultMap=new HashMap<String, Object>();
		List<Object> columns = new ArrayList<Object>();
		List<Object> charge = new ArrayList<Object>();
		String type = request.getParameter("type");
		if(CollectionUtils.isNotEmpty(list)){
			for (Map<String, Object> map : list) {
				columns.add(map.get("powerStationName"));
				if("A".equals(type)){
					charge.add(map.get("A"));
				}
				if("C".equals(type)){
					charge.add(map.get("C"));
				}
				if("D".equals(type)){
					charge.add(map.get("D"));
				}
			}
		}
        if("A".equals(type)){
            resultMap.put("legend", new String[]{"充电度数"});
            resultMap.put("充电度数", charge);
        }
        if("C".equals(type)){
            resultMap.put("legend", new String[]{"充电次数"});
            resultMap.put("充电次数", charge);
        }
        if("D".equals(type)){
            resultMap.put("legend", new String[]{"充电金额"});
            resultMap.put("充电金额", charge);
        }
		resultMap.put("columns", columns);
		result.setDataObject(resultMap);
		responseJson(result);
	}

	@RequestMapping("/getPowerStationChargeDataDetail")
	public void getPowerStationChargeDataDetail(Pager pager){
		JsonResult result = new JsonResult();
		try {
			Map<String,Object> params = initParam();
			params.put("pager", pager);
			Long total = orderStatisticService.countPowerStationChargeDataDetail(params);
			if (total <= pager.getOffset()) {
				pager.setPageNo(1L);
			}
			pager.setTotal(total);
			List<Map<String,Object>> map = orderStatisticService.getPowerStationChargeDataDetail(params);
			result.setPager(pager);
			result.setDataObject(map);
			responseJson(result);
		} catch (Exception e) {
			LOGGER.debug(this.getClass() + ".getPowerStationChargeDataDetail() error : ", e);
			responseJson(new JsonException("获取充电数据对比失败！"));
			return;
		}
		responseJson(result);
	}
	
	private Map<String,Object> initParam(){
		Map<String,Object> params = new HashMap<String,Object>();
		String startGmtCreate = request.getParameter("startGmtCreate");
		String endGmtCreate = request.getParameter("endGmtCreate");
		String type = request.getParameter("type");
		params.put("startGmtCreate", startGmtCreate);
		params.put("endGmtCreate", endGmtCreate);
		params.put("type", type);
		return params;
	}


}
