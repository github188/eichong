package com.wanma.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pub.model.TblUser;

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
import com.base.common.SessionMgr;
import com.wanma.service.MonitorHomeService;


/**
 * 运营管理-监控分析
 * @author bc
 *
 */
@Controller
@RequestMapping("/admin/monitor")
public class MonitorHomeController {
private static Logger log = Logger.getLogger(StatisticAssertController.class);
	
	@Autowired
	MonitorHomeService monitorHomeService;
	
	@RequestMapping("/monitorHomePage")
	public String assertPage(){
		return "backstage/monitor/home";
	}
	
	/**
	 * 监控首页（echarts地图）
	 * @param provinceCode 省编码
	 * @return 
	 */
	@RequestMapping("/home")
	@ResponseBody
	public String home(HttpServletRequest request){
		BaseResult result = new BaseResult();
		try{
			String provinceCode = request.getParameter("provinceCode");
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("provinceCode", provinceCode);
			params.put("cityCode", ""); // 统计维度全国、省  方便公用sql
			Map<String,Object> map = monitorHomeService.home(params);
			if(ObjectUtil.isEmpty(map)){
				map = new HashMap<String,Object>();
			}
			result.setData(map);
		}catch(Exception e){
			log.error(this.getClass() + ".home() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	/**
	 * 详细信息-高德地图(地图)
	 * @param 
	 * @return 充电点ID、充点电名称、充电点地址
	 */
	@RequestMapping("/chargePointMap")
	@ResponseBody
	public String chargePointMap(HttpServletRequest request){
		BaseResult result = new BaseResult();
		try{
			String provinceCode = request.getParameter("provinceCode");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("provinceCode", provinceCode);
			TblUser loginUser = SessionMgr.getWebUser(request);
			params.put("userLevel", loginUser.getUserLevel());
			params.put("userId", loginUser.getUserId());
			List<Map<String,Object>> list = monitorHomeService.queryChargePointMap(params);
			result.setData(list);
		}catch(Exception e){
			log.error(this.getClass() + ".chargePointInfo() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	/**
	 * 详细信息-高德地图（右侧 列表）
	 * @param 
	 * @return 充电点ID、充点电名称、充电点地址、充电点状态
	 */
	@RequestMapping("/chargePointInfo")
	@ResponseBody
	public String chargePointInfo(HttpServletRequest request,Pager pager){
		BaseResult result = new BaseResult();
		try{
			String pointInfo = request.getParameter("pointInfo");
			String headState = request.getParameter("headState");
			String provinceCode = request.getParameter("provinceCode");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("pointInfo", pointInfo);
			params.put("headState", headState);
			params.put("provinceCode", provinceCode);
			TblUser loginUser = SessionMgr.getWebUser(request);
			params.put("userLevel", loginUser.getUserLevel());
			params.put("userId", loginUser.getUserId());
			int total = monitorHomeService.countChargePointMap(params);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			pager.setTotal(new Long(total));
			params.put("pager", pager);
			List<Map<String,Object>> list = monitorHomeService.queryChargePointMap(params);
			result.setData(list);
			result.setPager(pager);
		}catch(Exception e){
			log.error(this.getClass() + ".chargePointMap() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	
	/**
	 * 充电点信息-累计充电次数、累计充电时长、累计充电度数（左侧）
	 * @param pId 充电点Id 
	 * @return
	 */
	@RequestMapping("/queryChargeCount")
	@ResponseBody
	public String queryChargeCount(HttpServletRequest request){
		BaseResult result = new BaseResult();
		try{
			String pId = request.getParameter("pId");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("pId", pId);
			Map<String,Object> resultMap = monitorHomeService.queryChargeCount(params);
			result.setData(resultMap);
		}catch(Exception e){
			log.error(this.getClass() + ".queryChargeCount() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	/**
	 * 充电点信息-今日充电次数、今日充电时长、今日充电度数（左侧）
	 * @param pId 充电点Id 
	 * @return
	 */
	@RequestMapping("/queryChargeToday")
	@ResponseBody
	public String queryChargeToday(HttpServletRequest request){
		BaseResult result = new BaseResult();
		try{
			String pId = request.getParameter("pId");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("pId", pId);
			Map<String,Object> resultMap = monitorHomeService.queryChargeToday(params);
			result.setData(resultMap);
		}catch(Exception e){
			log.error(this.getClass() + ".queryChargeToday() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	/**
	 * 充电点信息-电桩总数、枪口总数、直流桩数、交流桩数（左侧）
	 * @param pId 充电点Id 
	 * @return
	 */
	@RequestMapping("/queryElecCount")
	@ResponseBody
	public String queryElecCount(HttpServletRequest request){
		BaseResult result = new BaseResult();
		try{
			String pId = request.getParameter("pId");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("pId", pId);
			Map<String,Object> resultMap = monitorHomeService.queryElecCount(params);
			result.setData(resultMap);
		}catch(Exception e){
			log.error(this.getClass() + ".queryElecCount() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	/**
	 * 充电点信息-充电、空闲、故障、离线（左侧）
	 * @param pId 充电点Id 
	 * @return
	 */
	@RequestMapping("/queryElecHeadFaultCount")
	@ResponseBody
	public String queryElecHeadFaultCount(HttpServletRequest request){
		BaseResult result = new BaseResult();
		try{
			String pId = request.getParameter("pId");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("pId", pId);
			Map<String,Object> resultMap = monitorHomeService.queryChargeToday(params);
			result.setData(resultMap);
		}catch(Exception e){
			log.error(this.getClass() + ".queryElecHeadFaultCount() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	/**
	 * 充电点枪信息（右侧）
	 * @param pId 充电点Id 
	 * @return
	 */
	@RequestMapping("/chargePoint4Detail")
	@ResponseBody
	public String chargePoint4Detail(HttpServletRequest request){
		BaseResult result = new BaseResult();
		try{
			String pId = request.getParameter("pId");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("pId", pId);
			List<Map<String,Object>> resultMap = monitorHomeService.queryChargePoint4Detail(params);
			result.setData(resultMap);
		}catch(Exception e){
			log.error(this.getClass() + ".chargePoint4Detail() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	/**
	 * （枪）实时信息
	 *  1.额度功率 2.hbase接口
	 *  http://10.9.2.109/hbase/itf/hbase/getRealtimeData?headCode=1&epCode=3301851010000071&epType=14
	 * @param epCode:桩编码   headCode：枪编码  epType:交流、直流
	 * @return
	 */
	@RequestMapping("/elecHeadRealtimeCount")
	@ResponseBody
	public String elecHeadRealtimeCount(HttpServletRequest request){
		BaseResult result = new BaseResult();
		try{
			String headId = request.getParameter("headId");
			result.setData(monitorHomeService.queryElecRatPower(headId));
		}catch(Exception e){
			log.error(this.getClass() + ".elecHeadRealtimeCount() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
	/**
	 * （枪）充电统计
	 * @param epCode:桩编码   headCode：枪编码  headId:枪主键
	 * (历史遗留问题，枪主键还是枪编码============)
	 * @return
	 */
	@RequestMapping("/elecHeadChargeCount")
	@ResponseBody
	public String elecHeadChargeCount(HttpServletRequest request){
		BaseResult result = new BaseResult();
		try{
			String epCode = request.getParameter("epCode");
//			String headId = request.getParameter("headId");
			String headCode = request.getParameter("headCode");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("epCode", epCode);
//			params.put("headId", headId);
			params.put("headCode", headCode);
			Map<String,Object> map = monitorHomeService.queryElecHeadChargeCount(params);
			result.setData(map);
		}catch(Exception e){
			log.error(this.getClass() + ".elecHeadChargeCount() error:"
					+ e.getLocalizedMessage());
			result = new BaseFail(5001);
		}
		return result.toString();
	}
}
