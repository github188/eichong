package com.wanma.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.common.SessionMgr;
import com.wanma.model.ElectricPileList;
import com.wanma.model.ElectricPileMap;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;
import com.wanma.model.TblUser;
import com.wanma.model.Userinfo;
import com.wanma.web.service.WebElectricPileListService;
import com.wanma.web.service.WebElectricPileMapService;
import com.wanma.web.support.utils.JsonUtil;

/**
 * 获取电桩列表(地图模式)
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-11 下午04:25:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/web/electricPileMap")
public class WebElectricPileMapController {
	private static Log log = LogFactory.getLog(WebElectricPileMapController.class);

	@Autowired
	private WebElectricPileMapService electricPileMapService;
	/** 反馈业务处理对象 */
	@Autowired
	private WebElectricPileListService electricPileListService;

	
	/**
	 * 地图模式数据查询
	 */
	@RequestMapping("/getElectricPileMapList")
	@ResponseBody
	public String getElectricPileMapList(HttpServletRequest request,@RequestParam Map<String, Object> params) {
		String suitable=(String) params.get("suitable");
		if(suitable!=null){
			if (!StringUtil.isEmpty(suitable)) {
				TblUser user=SessionMgr.getWebUser(request);
				TblElectricpile pile=electricPileMapService.getPileConditionByUserId(user.getUserId());					
				if(pile!=null){
					Integer chargingmode=pile.getElpiChargingmode();
					if(chargingmode!=null){
						if(chargingmode==1){
							params.put("chargingMode", null);
						}else{
							params.put("chargingMode",chargingmode);
						}
					}
					Integer elpiPowerinterface=pile.getElpiPowerinterface();
					if(elpiPowerinterface!=null){
						params.put("powerInterface",elpiPowerinterface);
					}
				}else{
					String s = "您还未填写车牌及车型，请到个人中心--我的资料中修改--needSelectValue"; 
					return JsonUtil.object2json(s);
				}
				
			}	
		}
		List<ElectricPileList> electricPileList = electricPileListService.getElectricPileForList(params);
//		RedisClient redisClient=new RedisClient();
//    	redisClient.addListClickCount();
		// 返回处理成功信息
		return JsonUtil.object2json(electricPileList);
	}
	
	
	/**
	 * 地图模式数据查询----------------getElectricPileMapList备份，如果不出问题，半个月后可删除,wbc于2015-09-29
	 */
	@RequestMapping("/getElectricPileMapList2")
	@ResponseBody
	public String getElectricPileMapList2(HttpServletRequest request) {
				//3电桩电站 4电动自行车
				String screenType = RequestParamUtil.getEncodeParam(request, "screenType");
				//搜索半径
				String screenRadius = RequestParamUtil.getEncodeParam(request, "screenRadius");
				//经度
				String longitude = RequestParamUtil.getEncodeParam(request, "longitude");
				//维度
				String latitude = RequestParamUtil.getEncodeParam(request, "latitude");
				//电桩/站 状态
				String screenState = RequestParamUtil.getEncodeParam(request, "screenState");
				//是否支持预约
				String screenSubscribe = RequestParamUtil.getEncodeParam(request, "screenSubscribe");
				//充电方式
				String elpiChargingmode = RequestParamUtil.getEncodeParam(request, "chargingMode");
				//接口方式 7国标，19美标，20欧标
				String powerInterface = RequestParamUtil.getEncodeParam(request, "powerInterface");
				//地址
				String address = request.getParameter("address");
				//枪头状态(空闲中)
				String headState=request.getParameter("headState");
				//连接状态(支持预约)
				String commStatus=request.getParameter("commStatus");
				//适合我的
				String suitable=request.getParameter("suitable");
				
		    	List<ElectricPileMap> electricPileMapList=new ArrayList();
		    	try {
		    		TblPowerstation tblPowerstation=new TblPowerstation();
		    		TblElectricpile tblElectricpile=new TblElectricpile();
		    		if (!StringUtil.isEmpty(address)) {
					  tblPowerstation.setPostAddress(address);
					  tblElectricpile.setElpiElectricpileaddress(address);
					}
					if (!StringUtil.isEmpty(headState)) {
					  tblPowerstation.setHeadState(new Integer(headState));
					  tblElectricpile.setHeadState(new Integer(headState));
					}
					if (!StringUtil.isEmpty(commStatus)) {
					  tblPowerstation.setCommStatus(new Integer(commStatus));
					  tblElectricpile.setCommStatus(new Integer(commStatus));
					}
					if (!StringUtil.isEmpty(suitable)) {
						TblUser user=SessionMgr.getWebUser(request);
						TblElectricpile pile=electricPileMapService.getPileConditionByUserId(user.getUserId());
						if(pile!=null){
							Integer chargingmode=pile.getElpiChargingmode();
							if(chargingmode!=null){
								if(chargingmode==1){
									elpiChargingmode=null;
								}else{
									elpiChargingmode=chargingmode+"";
								}
							}
							Integer elpiPowerinterface=pile.getElpiPowerinterface();
							if(elpiPowerinterface!=null){
								powerInterface=elpiPowerinterface+"";
							}
						}
						
					}
					if (!StringUtil.isEmpty(screenType)) {
					  tblPowerstation.setPostPoweruser(Integer.parseInt(screenType));
					  tblElectricpile.setElpiPoweruser(Integer.parseInt(screenType));
					}
					if (!StringUtil.isEmpty(screenRadius)) {
						// 未输入经纬度
						if (StringUtil.isEmpty(longitude) || StringUtil.isEmpty(latitude)) {
							// 返回未输入经纬度错误信息
							return new AccessErrorResult("error.msg.invalid.parameter").toString();
						}
						tblPowerstation.setPostLongitude(new BigDecimal(longitude));
				    	tblPowerstation.setPostLatitude(new BigDecimal(latitude));
				    	tblPowerstation.setScreenRadius(screenRadius);
				    	  
						tblElectricpile.setElpiLongitude(new BigDecimal(longitude));
						tblElectricpile.setElpiLatitude(new BigDecimal(latitude));
						tblElectricpile.setScreenRadius(screenRadius);
					}
					if (!StringUtil.isEmpty(screenState)) {
						tblPowerstation.setPostStatus(Integer.parseInt(screenState));
						tblElectricpile.setElpiState(Integer.parseInt(screenState));
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
					electricPileMapList=electricPileMapService.getElectricMapList(tblPowerstation, tblElectricpile);
		    	} catch (Exception e) {
					// 保存错误LOG
					log.error(e.getLocalizedMessage());
					log.error("获取地图模式数据失败", e);
					// 返回登录信息错误信息
					return new AccessErrorResult("error.msg.invalid.parameter")
							.toString();
				}
				// 返回处理成功信息
		    	String s=JsonUtil.object2json(electricPileMapList);
//		    	RedisClient redisClient=new RedisClient(); 
//		    	redisClient.addMapClickCount();
				return s;
	}
}