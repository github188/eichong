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
 * 统计分析-充电消费统计-充电费用占比
 */
@Controller
@RequestMapping("/manage/statistic/")
public class StatisticsChargeMoneyController extends BaseController{
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OrderStatisticService orderStatisticService;
	
	// A.充电金额  B.充电电费 C:充电服务费  time:日期
	@RequestMapping("/countReportChargeMoney")
	public void countReportChargeMoney(){
		JsonResult result = new JsonResult();
		Map<String,Object> params = initParam();
		Map<String,String> map = orderStatisticService.countChargeMoney(params);
		result.setDataObject(map);
		responseJson(result);
	}
	
	@RequestMapping("/getReportChargeMoneyData")
	public void getReportChargeMoneyData(){
		JsonResult result = new JsonResult();
		Map<String,Object> params = initParam();
		Map<String,Object> resultMap=new HashMap<String, Object>();
		List<Object> columns = new ArrayList<Object>();
		List<Object> JMoeny=new ArrayList<Object>();
		List<Object> FMoeny=new ArrayList<Object>();
		List<Object> PMoney=new ArrayList<Object>();
		List<Object> GMoney=new ArrayList<Object>();
		String type = request.getParameter("type");
		if("B".equals(type)){
			List<Map<String,Object>> list = orderStatisticService.countChargeElectricMoney(params);
			if(CollectionUtils.isNotEmpty(list)){
				for (Map<String, Object> map : list) {
					columns.add(map.get("time"));
					JMoeny.add(map.get("A"));
					FMoeny.add(map.get("B"));
					PMoney.add(map.get("C"));
					GMoney.add(map.get("D"));
				}
			}
		}
		
		if("C".equals(type)){
			List<Map<String,Object>> list = orderStatisticService.countChargeServiceMoney(params);
			if(CollectionUtils.isNotEmpty(list)){
				for (Map<String, Object> map : list) {
					columns.add(map.get("time"));
					JMoeny.add(map.get("A"));
					FMoeny.add(map.get("B"));
					PMoney.add(map.get("C"));
					GMoney.add(map.get("D"));
				}
			}
		}
		resultMap.put("columns", columns);
		resultMap.put("legend", new String[]{"尖","峰","平","谷"});
		resultMap.put("尖", JMoeny);
		resultMap.put("峰", FMoeny);
		resultMap.put("平", PMoney);
		resultMap.put("谷", GMoney);
		result.setDataObject(resultMap);
		responseJson(result);
	}
	
	@RequestMapping("/getReportChargeMoneyDetail")
	public void getReportChargeMoneyDetail(Pager pager){
		JsonResult result = new JsonResult();
		try {
			Map<String,Object> params = initParam();
			params.put("pager", pager);
			Long total = orderStatisticService.countChargeDataDetail(params);
			if (total <= pager.getOffset()) {
				pager.setPageNo(1L);
			}
			pager.setTotal(total);
			List<Map<String,Object>> map = orderStatisticService.getChargeMoneyDetail(params);
			result.setPager(pager);
			result.setDataObject(map);
			responseJson(result);
		} catch (Exception e) {
			LOGGER.debug(this.getClass() + ".getReportChargeMoneyDetail() error : ", e);
			responseJson(new JsonException("充电费用统计失败"));
			return;
		}
		responseJson(result);
	}
	
	private Map<String,Object> initParam(){
		Map<String,Object> params = new HashMap<String,Object>();
		String cpyNumber = request.getParameter("cpyNumber");
		String startGmtCreateY = request.getParameter("startGmtCreateY");
		String endGmtCreateY = request.getParameter("endGmtCreateY");
		params.put("cpyNumber", cpyNumber);
		params.put("startGmtCreateY", startGmtCreateY);
		params.put("endGmtCreateY", endGmtCreateY);
		return params;
	}
}
