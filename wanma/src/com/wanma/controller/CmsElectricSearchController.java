package com.wanma.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.model.ElectricPileMap;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;
import com.wanma.service.CmsElectricSearchService;
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
@RequestMapping("/admin/electricPileMap")
public class CmsElectricSearchController {
	private static Log log = LogFactory.getLog(CmsElectricSearchController.class);

	@Autowired
	private CmsElectricSearchService electricPileMapService;

	/**
	 * 地图模式数据查询
	 */
	
	
	@RequestMapping("/getElectricPileMapPage")
	public String getElectricPileMapPage(){
		
		 return "backstage/electricSearchMode/electricSearch-list";
	}
	
	
	@RequestMapping("/getElectricPileMapList")
	@ResponseBody
	public String getElectricPileMapList(HttpServletRequest request) {
				
				log.info("地图模式数据查询列表");
		        // 处理结果信息
		        DwzAjaxResult dwzResult;
				//3电桩充电点 4电动自行车
				String screenType = RequestParamUtil.getEncodeParam(request, "screenType");
				//搜索半径
				String screenRadius = RequestParamUtil.getEncodeParam(request, "screenRadius");
				//经度
				String longitude = RequestParamUtil.getEncodeParam(request, "longitude");
				//纬度
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
				
		    	List<ElectricPileMap> electricPileMapList=new ArrayList();
		    	try {
		    		TblPowerstation tblPowerstation=new TblPowerstation();
		    		TblElectricpile tblElectricpile=new TblElectricpile();
		    		if (!StringUtil.isEmpty(address)) {
						  tblPowerstation.setPostAddress(address);
						  tblElectricpile.setElpiElectricpileaddress(address);
						}
					if (!StringUtil.isEmpty(screenType)) {
					  tblPowerstation.setPostPoweruser(Integer.parseInt(screenType));
					  tblElectricpile.setElpiPoweruser(Integer.parseInt(screenType));
					  
					}
					if (!StringUtil.isEmpty(screenRadius)) {
						// 未输入经纬度
						if (StringUtil.isEmpty(longitude) || StringUtil.isEmpty(latitude)) {
							// 返回未输入经纬度错误信息
							return new AccessErrorResult(0, "error.msg.invalid.parameter").toString();
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
		    		 // 出错日志
		            log.error( e.getLocalizedMessage() );

		            // 设置处理错误信息
		            dwzResult = new DwzAjaxResult( "300", "查询失败:系统错误", "commitlogList",
		                    "", "" );
				}
		    					// 返回处理成功信息
		    	String s=JsonUtil.object2json(electricPileMapList);
				return s;
	}
	
	
}