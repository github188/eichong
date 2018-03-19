package com.wanma.ims.controller.statistics;

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

import com.alibaba.druid.util.StringUtils;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.activity.ActivityController;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.StatisticsAppService;

/**
 * 统计分析-充电消费统计-APP充值统计
 */
@Controller
@RequestMapping("/manage/statistic")
public class StatisticsAppController extends  BaseController {
	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(ActivityController.class);
	@Autowired
	private StatisticsAppService statisticsAppService;
	/**
	 * app充值统计 顶部
	 */
	@RequestMapping("/appRechargeDataCount")
	@ResponseBody
	public void appRechargeDataCount(HttpServletRequest request){
		JsonResult result = new JsonResult();
		try {
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("beginTime", beginTime);
			params.put("endTime", endTime);
			Map<String,Object> map = statisticsAppService.queryAppRechargeDataCount(params);	
			Map<String,Object> resultMap = new HashMap<String, Object>();
			resultMap.put("countTotal",map.get("countTotal").toString() );
			resultMap.put("countTotalMoney",map.get("countTotalMoney").toString());
			resultMap.put("countYesterday",map.get("countYesterday").toString());
			resultMap.put("countYesterdayMoney",map.get("countYesterdayMoney").toString());
			result.setDataObject(resultMap);
			responseJson(result);
		} catch (Exception e) {
			log.error(this.getClass()+".appRechargeDataCount() error:",e);
			responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"app充值统计失败"));
			return;
		}
	}
	
	/**
	 * app充值统计 线形图
	 */
	@RequestMapping("/appRechargeDataLine")
	@ResponseBody
	public void appRechargeDataLine(HttpServletRequest request){
		JsonResult result = new JsonResult();
		try {
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			if(StringUtils.isEmpty(beginTime)||StringUtils.isEmpty(endTime)){
				responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"开始结束时间不存在"));
				return;
			}
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("beginTime", beginTime);
			params.put("endTime", endTime);
			List<Map<String,Object>> list = statisticsAppService.queryAppRechargeDataLine(params);
			Map<String,Object> resultMap=new HashMap<String, Object>();
			resultMap.put("legend", new String[]{"充值金额"});
			List<Object> columns = new ArrayList<Object>();
			List<Object> rechargeMoney = new ArrayList<Object>();
			if(CollectionUtils.isNotEmpty(list)){
				for (Map<String, Object> map : list) {
					columns.add(map.get("time"));
					rechargeMoney.add(map.get("countTotalMoney"));
				}
			}
			resultMap.put("columns", columns);
			resultMap.put("充值金额", rechargeMoney);
			result.setDataObject(resultMap);
			responseJson(result);      
		} catch (Exception e) {
			log.error(this.getClass()+".appRechargeDataLine() error:",e);
			responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION," app充值统计折线统计失败"));
			return;
		}
	}	
	/**
	 * app充值统计  列表
	 */
	@RequestMapping("/appRechargeDataList")
	@ResponseBody
	public void appRechargeDataList(HttpServletRequest request,Pager pager){
		JsonResult result = new JsonResult();
		try {
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("beginTime", beginTime);
			params.put("endTime", endTime);
			params.put("pager", pager);
			Long total = statisticsAppService.queryAppRechargeDataListCount(params);
			if (total <= pager.getOffset()) {
				pager.setPageNo(1L);
			}
			pager.setTotal(total);
			List<Map<String,Object>> resultList = statisticsAppService.queryAppRechargeDataList(params);
			result.setPager(pager);
			result.setDataObject(resultList);
			responseJson(result);
		} catch (Exception e) {
			log.error(this.getClass()+".appRechargeDataList() error:",e);
			responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"app充值统计列表统计失败"));
			return;
		}
	}
	
	
	
	
	
	
	
	
}
