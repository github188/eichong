package com.wanma.app.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.app.service.ElectricPileListService;
import com.wanma.app.service.ElectricPileMapService;
import com.wanma.common.JudgeNullUtils;
import com.wanma.model.ElectricPileList;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;

/**
 *   电桩查找(列表模式) 
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-13 下午02:58:19 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
@Controller
@RequestMapping("/app/electricPileList")
public class ElectricPileListController {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(ElectricPileListController.class);

	/** 反馈业务处理对象 */
	@Autowired
	private ElectricPileListService electricPileListService;
	@Autowired
	private ElectricPileMapService electricPileMapService;
	/**
	 * 获取电桩查找列表模式数据
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	/**
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	/*@RequestMapping("/getElectricPileList")
	@ResponseBody
	public String getElectricPileList(HttpServletRequest request)
			throws AppRequestErrorException {

		//车型
		String electricTypeId = RequestParamUtil.getEncodeParam(request, "electricTypeId");
		//搜索半径
		String screenRadius = RequestParamUtil.getEncodeParam(request, "screenRadius");
		//经度
		String Longitude = RequestParamUtil.getEncodeParam(request, "Longitude");
		//维度
		String Latitude = RequestParamUtil.getEncodeParam(request, "Latitude");
		//价格
		String electricPrices = RequestParamUtil.getEncodeParam(request, "electricPrices");
		//好评
		String electricEvaluate = RequestParamUtil.getEncodeParam(request, "electricEvaluate");

		
		List<ElectricPileList> electricPileList=null;
		try {
			TblPowerstation tblPowerstation=new TblPowerstation();
			TblElectricpile tblElectricpile=new TblElectricpile();
			// 未输入经纬度
			if (StringUtil.isEmpty(Longitude) || StringUtil.isEmpty(Latitude)) {
				// 返回未输入经纬度错误信息
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			if (!StringUtil.isEmpty(electricTypeId)) {
				tblPowerstation.setPostPoweruser(Integer.parseInt(electricTypeId));
				tblElectricpile.setElpiPoweruser(Integer.parseInt(electricTypeId));
			}
			if (!StringUtil.isEmpty(screenRadius)) {
				tblPowerstation.setScreenRadius(screenRadius);
				tblElectricpile.setScreenRadius(screenRadius);
			}
			if (!StringUtil.isEmpty(electricPrices)) {
				tblPowerstation.setElectricPrices(electricPrices);
				tblElectricpile.setScreenRadius(screenRadius);
			}
			if (!StringUtil.isEmpty(electricEvaluate)) {
				tblPowerstation.setElectricEvaluate(electricEvaluate);
				tblElectricpile.setElectricEvaluate(electricEvaluate);
			}
			tblPowerstation.setPostLongitude(new BigDecimal(Longitude));
			tblPowerstation.setPostLatitude(new BigDecimal(Latitude));
			tblElectricpile.setElpiLongitude(new BigDecimal(Longitude));
			tblElectricpile.setElpiLatitude(new BigDecimal(Latitude));
			electricPileList=electricPileListService.getElectricPileList(tblPowerstation,tblElectricpile);
			
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电桩列表失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000,"error.msg.invalid.parameter")
					.toString();
		}

		// 返回处理成功信息
		return new AccessSuccessResult(electricPileList).toString();
	}*/
	
	@RequestMapping("/getElectricPileListN")
	@ResponseBody
	public String getElectricPileListN(HttpServletRequest request)
			throws AppRequestErrorException {

		//距离
		//String screenRadius = RequestParamUtil.getEncodeParam(request, "screenRadius");
		//经度
		String Longitude = RequestParamUtil.getEncodeParam(request, "Longitude");
		//维度
		String Latitude = RequestParamUtil.getEncodeParam(request, "Latitude");
		//价格
		//String electricPrices = RequestParamUtil.getEncodeParam(request, "electricPrices");
		//好评
		//String electricEvaluate = RequestParamUtil.getEncodeParam(request, "electricEvaluate");
		//接口方式 7国标 19美标 20欧标
		String powerInterface = RequestParamUtil.getEncodeParam(request, "powerInterface");
		//电桩状态（10普通，15智能）
		//String screenState = RequestParamUtil.getEncodeParam(request, "screenState");
		//充电模式（5直流，14交流）
		String chargingMode = RequestParamUtil.getEncodeParam(request, "chargingMode");
		//空闲充电点 （智能、联网、有空闲枪头的桩）
		String freeStatus = RequestParamUtil.getEncodeParam(request, "freeStatus");
		//适合爱车 必须与用户id一起传入
		String matchMyCar = RequestParamUtil.getEncodeParam(request, "matchMyCar");
		//用户id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		//搜索关键字
		String searchKey = RequestParamUtil.getEncodeParam(request, "searchKey");
		
		List<ElectricPileList> electricPileList=null;
		try {
			TblPowerstation tblPowerstation=new TblPowerstation();
			TblElectricpile tblElectricpile=new TblElectricpile();
			// 未输入经纬度
			if (StringUtil.isEmpty(Longitude) || StringUtil.isEmpty(Latitude)) {
				// 返回未输入经纬度错误信息
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			if(!StringUtils.isEmpty(searchKey)){
				tblPowerstation.setSearchName(searchKey);
				tblElectricpile.setSearchName(searchKey);
			}
			/*if (!StringUtil.isEmpty(electricTypeId)) {
				tblPowerstation.setPostPoweruser(Integer.parseInt(electricTypeId));
				tblElectricpile.setElpiPoweruser(Integer.parseInt(electricTypeId));
			}*/
			/*if (!StringUtil.isEmpty(screenRadius)) {
				tblPowerstation.setScreenRadius("1");
				tblElectricpile.setScreenRadius("1");
			}
			if (!StringUtil.isEmpty(electricPrices)) {
				tblPowerstation.setElectricPrices("1");
				tblElectricpile.setElectricPrices("1");
			}
			if (!StringUtil.isEmpty(electricEvaluate)) {
				tblPowerstation.setElectricEvaluate("1");
				tblElectricpile.setElectricEvaluate("1");
			}*/
			if(!StringUtil.isEmpty(powerInterface)){
				tblPowerstation.setElpiPowerinterface(Integer.parseInt(powerInterface));
				tblElectricpile.setElpiPowerinterface(Integer.parseInt(powerInterface));
			}
			/*if(!StringUtil.isEmpty(screenState)){
				tblPowerstation.setPostStatus(Integer.parseInt(screenState));
				tblElectricpile.setElpiState(Integer.parseInt(screenState));			
			}*/
			if(!StringUtil.isEmpty(chargingMode)){
				tblPowerstation.setChargingMode(Integer.parseInt(chargingMode));
				tblElectricpile.setElpiChargingmode(Integer.parseInt(chargingMode));
			}
			//设置空闲充电点查询条件
			if(!StringUtil.isEmpty(freeStatus)){
				tblPowerstation.setPostStatus(15);
				tblElectricpile.setElpiState(15);
				tblPowerstation.setConnectStatus(1);
				tblElectricpile.setComm_status(1);
				tblPowerstation.setHeadStatus("0");
				tblElectricpile.setHeadStatus("0");
			}
			//设置适合爱车的查询条件
			if(!StringUtils.isEmpty(matchMyCar)){
				Map<String, String> map = electricPileMapService.getPileConditionByUserId(Integer.parseInt(userId));
				//直流交流根据勾选情况，勾选优先
				if(StringUtils.isEmpty(chargingMode)){
					tblPowerstation.setChargingMode(JudgeNullUtils.nvlInteget(map.get("elPi_ChargingMode")));
					tblElectricpile.setElpiChargingmode(JudgeNullUtils.nvlInteget(map.get("elPi_ChargingMode")));
				}
				//国标欧标根据车的参数来
				tblPowerstation.setElpiPowerinterface(JudgeNullUtils.nvlInteget(map.get("elPi_PowerInterface")));
				tblElectricpile.setElpiPowerinterface(JudgeNullUtils.nvlInteget(map.get("elPi_PowerInterface")));
			}
			
			tblPowerstation.setPostLongitude(new BigDecimal(Longitude));
			tblPowerstation.setPostLatitude(new BigDecimal(Latitude));
			tblElectricpile.setElpiLongitude(new BigDecimal(Longitude));
			tblElectricpile.setElpiLatitude(new BigDecimal(Latitude));
			electricPileList=electricPileListService.getElectricPileListN(tblPowerstation,tblElectricpile);
			
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电桩列表失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000,"error.msg.invalid.parameter").toString();
		}

		// 返回处理成功信息
		//return new AccessSuccessResult(electricPileList).toString();
		return AccessSuccessResult.nAccessSuccessResult(electricPileList).toJSONString();
	}
	/**
	 * 快速检索
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping("/getSearchElectricList")
	@ResponseBody
	public String getSearchingElectricList(HttpServletRequest request)
			throws AppRequestErrorException {

		//检索名称
		String searchName = RequestParamUtil.getEncodeParam(request, "searchName");
		//搜索半径
		String screenRadius = RequestParamUtil.getEncodeParam(request, "screenRadius");
		//经度
		String Longitude = RequestParamUtil.getEncodeParam(request, "Longitude");
		//维度
		String Latitude = RequestParamUtil.getEncodeParam(request, "Latitude");
		//价格
		String electricPrices = RequestParamUtil.getEncodeParam(request, "electricPrices");
		//好评
		String electricEvaluate = RequestParamUtil.getEncodeParam(request, "electricEvaluate");

		
		List<ElectricPileList> electricPileList=null;
		try {
			TblPowerstation tblPowerstation=new TblPowerstation();
			TblElectricpile tblElectricpile=new TblElectricpile();
			// 未输入经纬度
			if (StringUtil.isEmpty(Longitude) || StringUtil.isEmpty(Latitude)) {
				// 返回未输入经纬度错误信息
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			if (!StringUtil.isEmpty(searchName)) {
				tblPowerstation.setSearchName(searchName);
				tblElectricpile.setSearchName(searchName);
			}
			if (!StringUtil.isEmpty(screenRadius)) {
				tblPowerstation.setScreenRadius(screenRadius);
				tblElectricpile.setScreenRadius(screenRadius);
			}
			if (!StringUtil.isEmpty(electricPrices)) {
				tblPowerstation.setElectricPrices(electricPrices);
				tblElectricpile.setScreenRadius(screenRadius);
			}
			if (!StringUtil.isEmpty(electricEvaluate)) {
				tblPowerstation.setElectricEvaluate(electricEvaluate);
				tblElectricpile.setElectricEvaluate(electricEvaluate);
			}
			tblPowerstation.setPostLongitude(new BigDecimal(Longitude));
			tblPowerstation.setPostLatitude(new BigDecimal(Latitude));
			tblElectricpile.setElpiLongitude(new BigDecimal(Longitude));
			tblElectricpile.setElpiLatitude(new BigDecimal(Latitude));
			electricPileList=electricPileListService.getElectricPileList(tblPowerstation,tblElectricpile);
			
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电桩列表失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000,"error.msg.invalid.parameter")
					.toString();
		}

		// 返回处理成功信息
		return new AccessSuccessResult(electricPileList).toString();
	}
 
	/**
	 * 新的快速检索
	 * @param params
	 * 			keyword 关键字
	 * 			elecType 类型（1电站，2电桩，3自行车充电电）
	 * 			orderType 排序（1距离，2价格，3好评）
	 * 			Longitude 经度
	 * 			Latitude 维度
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping("/getSearchElectricListN")
	@ResponseBody
	public String getSearchingElectricList(@RequestParam HashMap<String, String> params)
			throws AppRequestErrorException {

		if(StringUtils.isEmpty(params.get("keyword")) || StringUtils.isEmpty(params.get("elecType"))
				|| StringUtils.isEmpty(params.get("orderType")) || StringUtils.isEmpty(params.get("orderType"))
				|| StringUtils.isEmpty(params.get("Longitude")) || StringUtils.isEmpty(params.get("Latitude"))){
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		}

		
		List<Map<String, String>> searchElectricPileList= new ArrayList<Map<String,String>>();
		try {
			searchElectricPileList=electricPileListService.getElecSearchList(params);
			
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电桩列表失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}

		// 返回处理成功信息
		return new AccessSuccessResult(searchElectricPileList).toString();
	}
	
	/**
	 * app启动时初始化电桩数据到本地
	 * @param epId
	 * @return
	 */
	@RequestMapping("/initEp")
	@ResponseBody
	public String initEpFromDB(Integer epId){
		if(StringUtils.isEmpty(epId)){
			epId = 0;
		}
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try{
			list = electricPileListService.initEpFromDB(epId);
		}catch(Exception e){
			log.error(e.getMessage());
			return new AccessErrorResult(2004, "error.msg.invalid.parameter").toString();
		}
		return new AccessSuccessResult(list).toString();
	}
}
