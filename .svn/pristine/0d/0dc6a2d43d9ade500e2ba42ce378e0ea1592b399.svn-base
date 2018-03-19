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
import com.wanma.model.ElectricPileList;
import com.wanma.web.dao.WebElectricpileMapper;
import com.wanma.web.dao.WebPowerstationMapper;
import com.wanma.web.service.WebElectricPileListService;

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
public class WebElectricPileListServiceImpl implements WebElectricPileListService {

	@Autowired
	WebElectricpileMapper tblElectricpileMapper;
	@Autowired
	WebPowerstationMapper tblPowerstationMapper;
	
	/**
	 * 获取地图模式电桩列表
	 * @param electricTypeId 汽车类型ID
	 * @param distance 距离 m
	 * @param price 价格
	 * @param evaluate 好评
	 */
	@Override
	public List<ElectricPileList> getElectricPileList(Map<String, Object> params) {

		List<ElectricPileList> electricPileList= new ArrayList<ElectricPileList>();
		
		//01:获取电站列表
		List<Map<String,Object>> powersList = tblPowerstationMapper.findPowerstation(params);
		for (int i = 0; i < powersList.size(); i++) {
			ElectricPileList electricPileLists = new ElectricPileList();
		    Map<String,Object> powersLMap = powersList.get(i);
			electricPileLists.setElectricId(JudgeNullUtils.nvlStr(powersLMap.get("pk_PowerStation")));
			electricPileLists.setElectricType(ApplicationConsts.ElectricPileManager.POWERSTATION);
			electricPileLists.setElectricName(JudgeNullUtils.nvlStr(powersLMap.get("poSt_Name")));
			electricPileLists.setElectricPileSum(JudgeNullUtils.nvlStr(powersLMap.get("electricPileCount")));
			electricPileLists.setElectricImage(JudgeNullUtils.nvlStr(powersLMap.get("poSt_Pic")));
			electricPileLists.setElectricAddress(JudgeNullUtils.nvlStr(powersLMap.get("poSt_Address")));
			electricPileList.add(electricPileLists);
		}
		//01:获取电桩列表
		List<Map<String,Object>> electricpileList = tblElectricpileMapper.findElectricpile(params);
		for (int i = 0; i < electricpileList.size(); i++) {
			ElectricPileList electricPileLists = new ElectricPileList();
			Map<String,Object> electricPileMap = electricpileList.get(i);
			electricPileLists.setElectricId(JudgeNullUtils.nvlStr(electricPileMap.get("pk_ElectricPile")));
			electricPileLists.setElectricType(ApplicationConsts.ElectricPileManager.ELECTRICPILE);
			electricPileLists.setElectricName(JudgeNullUtils.nvlStr(electricPileMap.get("elPi_ElectricPileName")));
			electricPileLists.setElectricImage(JudgeNullUtils.nvlStr(electricPileMap.get("elPi_Image")));
			
			electricPileLists.setElectricUse(JudgeNullUtils.nvlStr(electricPileMap.get("elPi_PowerUser")));//
			electricPileLists.setElectriChargingMode(JudgeNullUtils.nvlStr(electricPileMap.get("elPi_ChargingMode")));//
			electricPileLists.setElectricPowerInterface(JudgeNullUtils.nvlStr(electricPileMap.get("elPi_PowerInterface")));//
			electricPileLists.setElectricPowerSize(JudgeNullUtils.nvlStr(electricPileMap.get("elPi_PowerSize")));
			electricPileLists.setElectricMaxElectricity(JudgeNullUtils.nvlStr(electricPileMap.get("elPi_MaxElectricity")));
			electricPileLists.setElectricAddress(JudgeNullUtils.nvlStr(electricPileMap.get("elPi_ElectricPileAddress")));
			electricPileList.add(electricPileLists);
		    
		}
		return electricPileList;
	}
	@Override
	public long countPowerstation(Map<String, Object> params) {
		return tblPowerstationMapper.countPowerstation(params);
	}
	@Override
	public long countElectricPile(Map<String, Object> params) {
		return tblElectricpileMapper.countElectricPile(params);
	}
	
	/**
	 * 获取相关电桩列表
	 * @param longitude 经度
	 * @param latitude 纬度
	 */
	@Override
	public List<Map<String, Object>> getRelatedList(Map<String, Object> params) {
		return tblPowerstationMapper.getPowerstation(params);
	}
	
	/**
	 * 获取列表模式电桩列表
	 * @param electricTypeId 汽车类型ID
	 * @param distance 距离 m
	 * @param price 价格
	 * @param evaluate 好评
	 */
	@Override
	public List<ElectricPileList> getElectricPileForList(Map<String, Object> params) {
		return tblElectricpileMapper.getElectricpileForList(params);
	}
	@Override
	public long countElectricPileForList(Map<String, Object> params) {
		return tblElectricpileMapper.countElectricPileForList(params);
	}
}
