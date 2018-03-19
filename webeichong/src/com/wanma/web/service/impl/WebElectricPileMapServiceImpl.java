/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.ApplicationConsts;
import com.wanma.common.JudgeNullUtils;
import com.wanma.model.ElectricPileMap;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;
import com.wanma.web.dao.WebElectricpileMapper;
import com.wanma.web.dao.WebPowerstationMapper;
import com.wanma.web.service.WebElectricPileMapService;

/***
 *
 *   电桩查找(列表模式) 
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-13 下午04:51:34 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
@Service
public class WebElectricPileMapServiceImpl implements WebElectricPileMapService {

	@Autowired
	WebElectricpileMapper tblElectricpileMapper;
	@Autowired
	WebPowerstationMapper tblPowerstationMapper;
	
	/**
	 * 获取地图模式电桩列表
	 */
	@Override
	public List<ElectricPileMap> getElectricMapList(TblPowerstation tblPowerstation,TblElectricpile tblElectricpile) {
		
		List<ElectricPileMap> electricPileMapList= new ArrayList<ElectricPileMap>();
		if(tblPowerstation.getPostPoweruser()==null||tblPowerstation.getPostPoweruser()==3){
			//01:获取电站列表
			List<?> powersList=tblPowerstationMapper.getPowerstationForMap(tblPowerstation);
			for (int i = 0; i < powersList.size(); i++) {
				ElectricPileMap electricPileMap=new ElectricPileMap();
			    Map<String,Object> powersLMap=(Map<String,Object>)powersList.get(i);
			    electricPileMap.setElectricId(JudgeNullUtils.nvlStr(powersLMap.get("pk_PowerStation")));
			    electricPileMap.setElectricType(ApplicationConsts.ElectricPileManager.POWERSTATION);
	//		    if("4".equals(powersLMap.get("elPi_PowerUser").toString())){
	//		    	 electricPileMap.setElectricType(ApplicationConsts.ElectricPileManager.BYCILEPILE);
	//		    }
			    electricPileMap.setElectricPileSum(JudgeNullUtils.nvlStr(powersLMap.get("electricPileSum")));
			    electricPileMap.setElectricState(JudgeNullUtils.nvlStr(powersLMap.get("poSt_Status")));
			    electricPileMap.setLongitude(JudgeNullUtils.nvlStr(powersLMap.get("poSt_Longitude")));
			    electricPileMap.setLatitude(JudgeNullUtils.nvlStr(powersLMap.get("poSt_Latitude")));
			    electricPileMap.setElectricAddress(JudgeNullUtils.nvlStr(powersLMap.get("poSt_Address")));
			    electricPileMap.setElectricImage(JudgeNullUtils.nvlStr(powersLMap.get("poSt_Pic")));
			    electricPileMap.setElectricName(JudgeNullUtils.nvlStr(powersLMap.get("electricName")));
			    electricPileMap.setHeadNum(JudgeNullUtils.nvlStr(powersLMap.get("headNum")));
			    electricPileMap.setFreeHeadNum(JudgeNullUtils.nvlStr(powersLMap.get("freeHeadNum")));
			    electricPileMap.setCompanyType(JudgeNullUtils.nvlStr(powersLMap.get("companyType")));
				electricPileMapList.add(electricPileMap);
			}
		}
		//01:获取电桩列表
		tblElectricpile.setElpiBinding(0);
		List<?> electricpileList=tblElectricpileMapper.getElectricpileForMap(tblElectricpile);
		for (int i = 0; i < electricpileList.size(); i++) {
			ElectricPileMap electricPileMap=new ElectricPileMap();
		    Map<String,Object> electricpileMap=(Map<String,Object>)electricpileList.get(i);
		    electricPileMap.setElectricId(JudgeNullUtils.nvlStr(electricpileMap.get("pk_ElectricPile")));
		    electricPileMap.setElectricType(ApplicationConsts.ElectricPileManager.ELECTRICPILE);
		    if("4".equals(electricpileMap.get("elPi_PowerUser").toString())){
		    	 electricPileMap.setElectricType(ApplicationConsts.ElectricPileManager.BYCILEPILE);
		    }
		    electricPileMap.setElectricState(JudgeNullUtils.nvlStr(electricpileMap.get("elPi_State")));
		    electricPileMap.setLongitude(JudgeNullUtils.nvlStr(electricpileMap.get("elPi_Longitude")));
		    electricPileMap.setLatitude(JudgeNullUtils.nvlStr(electricpileMap.get("elPi_Latitude")));
		    electricPileMap.setElectricAddress(JudgeNullUtils.nvlStr(electricpileMap.get("elpi_electricpileaddress")));
		    electricPileMap.setElectricImage(JudgeNullUtils.nvlStr(electricpileMap.get("elpi_Image")));
		    electricPileMap.setElectricName(JudgeNullUtils.nvlStr(electricpileMap.get("elPi_ElectricPileName")));
		    electricPileMap.setHeadNum(JudgeNullUtils.nvlStr(electricpileMap.get("headNum")));
		    electricPileMap.setFreeHeadNum(JudgeNullUtils.nvlStr(electricpileMap.get("freeHeadNum")));
		    electricPileMap.setCompanyType(JudgeNullUtils.nvlStr(electricpileMap.get("companyType")));
		    electricPileMapList.add(electricPileMap);
		    
		}
		return electricPileMapList;
	}

	@Override
	public TblElectricpile getPileConditionByUserId(Long userId) {
		return tblElectricpileMapper.getPileConditionByUserId(userId);
	}
  

}
