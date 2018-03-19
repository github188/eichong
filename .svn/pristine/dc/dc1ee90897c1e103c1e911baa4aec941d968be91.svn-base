package com.wanma.app.controller;

import java.util.ArrayList;
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
import com.wanma.app.service.ElectricPileMapService;
import com.wanma.common.JudgeNullUtils;
import com.wanma.model.ElectricPileMap;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;


/**
 * 获取电桩列表(地图模式)
  * @Description:
  * @createTime：2015-3-11 下午04:25:53 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
@Controller
@RequestMapping("/app/electricPileMap")
public class ElectricPileMapController {
    private static Logger log = Logger.getLogger(ElectricPileMapController.class);
	
    @Autowired
    private ElectricPileMapService electricPileMapService;

    
    /**
     * 地图模式数据查询
     */
    @RequestMapping("/getElectricPileMapList")
	@ResponseBody
	public String  getCityList(HttpServletRequest request) throws AppRequestErrorException {
    	//3电桩电站 4电动自行车
		//String screenType = RequestParamUtil.getEncodeParam(request, "screenType");
		//搜索半径
		//String screenRadius = RequestParamUtil.getEncodeParam(request, "screenRadius");
		//经度
		//String longitude = RequestParamUtil.getEncodeParam(request, "longitude");
		//维度
		//String latitude = RequestParamUtil.getEncodeParam(request, "latitude");
		//电桩/站 状态
		//String screenState = RequestParamUtil.getEncodeParam(request, "screenState");
		//是否支持预约
		String screenSubscribe = RequestParamUtil.getEncodeParam(request, "screenSubscribe");
		//充电方式
		String elpiChargingmode = RequestParamUtil.getEncodeParam(request, "chargingMode");
		//接口方式 7国标，19美标，20欧标
		String powerInterface = RequestParamUtil.getEncodeParam(request, "powerInterface");
		//城市代码
		String cityCode = RequestParamUtil.getEncodeParam(request, "cityCode");
		//区县代码
		String countyCode = RequestParamUtil.getEncodeParam(request, "countyCode");
		//接口上次调用时间 必须与城市代码一起传入
		String reqTime = RequestParamUtil.getEncodeParam(request, "reqTime");
		//空闲充电点 （智能、联网、有空闲枪头的桩）
		String freeStatus = RequestParamUtil.getEncodeParam(request, "freeStatus");
		//适合爱车 必须与用户id一起传入
		String matchMyCar = RequestParamUtil.getEncodeParam(request, "matchMyCar");
		//用户id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		//是否分享
		String share = RequestParamUtil.getEncodeParam(request, "share");
    	List<ElectricPileMap> electricPileMapList=new ArrayList();
    	try {
    		TblPowerstation tblPowerstation=new TblPowerstation();
    		TblElectricpile tblElectricpile=new TblElectricpile();
			/*if (!StringUtil.isEmpty(screenType)) {
			  tblPowerstation.setPostPoweruser(Integer.parseInt(screenType));
			  tblElectricpile.setElpiPoweruser(Integer.parseInt(screenType));
			  
			}*/
    		if(!StringUtils.isEmpty(cityCode) || !StringUtils.isEmpty(reqTime)){
    			if(StringUtils.isEmpty(cityCode) || StringUtils.isEmpty(reqTime)){
    				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
    			}
    			
    			tblPowerstation.setPostAreacode(cityCode);
    			tblElectricpile.setElPiOwnCityCode(cityCode);
    			tblPowerstation.setPostUpdatedate(reqTime);
    			tblElectricpile.setElpiUpdatedate(reqTime);
    			
    		}
			if (!StringUtil.isEmpty(countyCode)) {
				tblPowerstation.setCountyCode(countyCode);
				tblElectricpile.setElPiOwnCountyCode(countyCode);
			}
			if (!StringUtil.isEmpty(screenSubscribe)) {
				tblPowerstation.setPostIsappoint(Integer.parseInt(screenSubscribe));
				tblElectricpile.setElpiIsappoint(Integer.parseInt(screenSubscribe));
			}
			if(!StringUtil.isEmpty(elpiChargingmode)){
				tblPowerstation.setChargingMode(Integer.parseInt(elpiChargingmode));
				tblElectricpile.setElpiChargingmode(Integer.parseInt(elpiChargingmode));
			}
			if(!StringUtil.isEmpty(powerInterface)){
				tblPowerstation.setElpiPowerinterface(Integer.parseInt(powerInterface));
				tblElectricpile.setElpiPowerinterface(Integer.parseInt(powerInterface));
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
			if(!StringUtil.isEmpty(share)){
				tblPowerstation.setPostStatus(15);
				tblElectricpile.setElpiState(15);
			}
			//设置适合爱车的查询条件
			if(!StringUtils.isEmpty(matchMyCar)){
				Map<String, String> map = electricPileMapService.getPileConditionByUserId(Integer.parseInt(userId));
				//直流交流根据勾选情况，勾选优先
				if(StringUtils.isEmpty(elpiChargingmode)){
					tblPowerstation.setChargingMode(JudgeNullUtils.nvlInteget(map.get("elPi_ChargingMode")));
					tblElectricpile.setElpiChargingmode(JudgeNullUtils.nvlInteget(map.get("elPi_ChargingMode")));
				}
				//国标欧标根据车的参数来
				tblPowerstation.setElpiPowerinterface(JudgeNullUtils.nvlInteget(map.get("elPi_PowerInterface")));
				tblElectricpile.setElpiPowerinterface(JudgeNullUtils.nvlInteget(map.get("elPi_PowerInterface")));
			}
			electricPileMapList=electricPileMapService.getElectricMapList(tblPowerstation, tblElectricpile);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取地图模式数据失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000,"error.msg.invalid.parameter").toString();
		}
		// 返回处理成功信息
		//return new AccessSuccessResult(electricPileMapList).toString();
    	return AccessSuccessResult.nAccessSuccessResult(electricPileMapList).toJSONString();
	}
    
    /**
     * 获取地图锚点的简介
     * @param params
     * 			type 2电站1电桩， eid 电站、电桩id，lat 维度，lng 经度
     * @return
     */
    @RequestMapping("/getAnchorSummary")
	@ResponseBody
    public String getAnchorSummary(@RequestParam Map<String, Object> params){
    	if(StringUtils.isEmpty(params.get("type")) || StringUtils.isEmpty(params.get("eid"))){
    		return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
    	}
    	
    	Map<String, String> map = electricPileMapService.getAnchorSummary(params);
    	
    	return new AccessSuccessResult(map).toString();
    }
    
    /**
     * 获取市级充电点聚合
     * @param params
     * 			lng 经度， lat 维度
     * @return
     * @throws Exception 
     */
    @RequestMapping("/getCtyPoints")
	@ResponseBody
    public String getCtyPoints(@RequestParam Map<String, Object> params) throws Exception{
    	
    	if(StringUtils.isEmpty(params.get("cpynum"))){
    		params.put("cpynum", "1000");
    		
    	}
    	if(StringUtils.isEmpty(params.get("location")) ){
    		return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
    	}
    	List<Map<String, Object>> data = electricPileMapService.getCtyPoints(params);
    	return new AccessSuccessResult(data).toString();
    }
    
    /**
     * 获取省级充电点聚合
     * @param params
     * 			lng 经度， lat 维度
     * @return
     */
    @RequestMapping("/getProPoints")
	@ResponseBody
    public String getProPoints(@RequestParam Map<String, Object> params){
    	if(StringUtils.isEmpty(params.get("cpynum"))){
    		params.put("cpynum", "1000");
    	}
    	List<Map<String, Object>> data = electricPileMapService.getProPoints(params);
    	return new AccessSuccessResult(data).toString();
    }
}