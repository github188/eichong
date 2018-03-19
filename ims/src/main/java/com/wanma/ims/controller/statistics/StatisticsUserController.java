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
import com.wanma.ims.service.StatisticsUserService;

/**
 * 统计分析-充电消费统计-用户数统计
 */
@Controller
@RequestMapping("/manage/statistic")
public class StatisticsUserController extends BaseController{
	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(ActivityController.class);
	@Autowired
	private StatisticsUserService statisticsUserService;
	
	
	/**
	 * 用户数统计 顶部
	 */
	@RequestMapping("/userDataCount")
	@ResponseBody
	public void userDataCount(HttpServletRequest request){
		JsonResult result = new JsonResult();
		try {
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("beginTime", beginTime);
			params.put("endTime", endTime);
			Map<String,Object> map = statisticsUserService.queryUserDataCount(params);
			Map<String,Integer> resultMap = new HashMap<String, Integer>();
			resultMap.put("countTotal",Integer.parseInt(map.get("countTotalApp").toString())+Integer.parseInt(map.get("countTotalCard").toString()));
			resultMap.put("countYesterday",Integer.parseInt(map.get("countYesterdayApp").toString())+Integer.parseInt(map.get("countYesterdayCard").toString()));
			result.setDataObject(resultMap);
			responseJson(result);
		} catch (Exception e) {
			log.error(this.getClass()+".userDataCount() error:",e);
			responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"用户数统计失败"));
			return;
		}
	}	
		/**
		 * 用户数统计 线形图
		 */
		@RequestMapping("/userDataLine")
		@ResponseBody
		public void userDataLine(HttpServletRequest request){
			JsonResult result = new JsonResult();
			try {
				String beginTime = request.getParameter("beginTime");
				String endTime = request.getParameter("endTime");
				if(StringUtils.isEmpty(beginTime)||StringUtils.isEmpty(endTime)){
					responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"开始结束时间不存在"));
					return;
				}
				String type = request.getParameter("type");
				Map<String,Object> params=new HashMap<String, Object>();
				params.put("beginTime", beginTime);
				params.put("endTime", endTime);
				params.put("type", type);
				List<Map<String,Object>> list = statisticsUserService.queryUserDataLine(params);
				Map<String,Object> resultMap=new HashMap<String, Object>();
				resultMap.put("legend", new String[]{"用户数量"});
				List<Object> columns = new ArrayList<Object>();
				List<Object> userCount = new ArrayList<Object>();
				if(CollectionUtils.isNotEmpty(list)){
					for (Map<String, Object> map : list) {
						columns.add(map.get("time"));
						userCount.add(map.get("countTotal"));
					}
				}
				resultMap.put("columns", columns);
				resultMap.put("用户数量", userCount);
				result.setDataObject(resultMap);
				responseJson(result);
			} catch (Exception e) {
				log.error(this.getClass()+".userDataLine() error:",e);
				responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"用户数折线统计失败"));
				return;
			}
		}	
			/**
			 * 用户数统计 列表
			 */
			@RequestMapping("/userDataList")
			@ResponseBody
			public void userDataList(HttpServletRequest request,Pager pager){
				JsonResult result = new JsonResult();
				try {
					String beginTime = request.getParameter("beginTime");
					String endTime = request.getParameter("endTime");
					Map<String,Object> params=new HashMap<String, Object>();
					params.put("beginTime", beginTime);
					params.put("endTime", endTime);
					params.put("pager", pager);
					Long total = statisticsUserService.queryUserDataListCount(params);
					if (total <= pager.getOffset()) {
						pager.setPageNo(1L);
					}
					pager.setTotal(total);
					List<Map<String,Object>> resultList = statisticsUserService.queryUserDataList(params);
					result.setPager(pager);
					result.setDataObject(resultList);
					responseJson(result);
				} catch (Exception e) {
					log.error(this.getClass()+".userDataList() error:",e);
					responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"用户数列表统计失败"));
					return;
				}
			}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
