/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.utils.HttpUtil;
import com.wanma.app.dao.TblElectricpileMapper;
import com.wanma.app.dao.TblPowerstationMapper;
import com.wanma.app.service.ElectricPileMapService;
import com.wanma.common.ApplicationConsts;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.WanmaConstants;
import com.wanma.model.ElectricPileMap;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;

/***
 *
 *   电桩查找(列表模式) 
  * @Description:
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
@Service
public class ElectricPileMapServiceImpl implements ElectricPileMapService {

	@Autowired
	TblElectricpileMapper tblElectricpileMapper;
	@Autowired
	TblPowerstationMapper tblPowerstationMapper;
	
	/**
	 * 获取地图模式电桩列表
	 */
	@Override
	public List<ElectricPileMap> getElectricMapList(TblPowerstation tblPowerstation,TblElectricpile tblElectricpile) {
		List<ElectricPileMap> electricPileMapList= new ArrayList<ElectricPileMap>();
		//01:获取电站列表
		List<?> powersList=tblPowerstationMapper.getPowerstationForMap(tblPowerstation);
		if(null !=powersList){
			for (int i = 0; i < powersList.size(); i++) {
				ElectricPileMap electricPileMap=new ElectricPileMap();
			    Map<String,Object> powersLMap=(Map<String,Object>)powersList.get(i);
			    electricPileMap.setElectricId(JudgeNullUtils.nvlStr(powersLMap.get("pk_PowerStation")));
			    electricPileMap.setElectricType(ApplicationConsts.ElectricPileManager.POWERSTATION);
			   
			    electricPileMap.setElectricState(JudgeNullUtils.nvlStr(powersLMap.get("poSt_Status")));
			    electricPileMap.setLongitude(JudgeNullUtils.nvlStr(powersLMap.get("poSt_Longitude")));
			    electricPileMap.setLatitude(JudgeNullUtils.nvlStr(powersLMap.get("poSt_Latitude")));
			    electricPileMap.setCityCode(JudgeNullUtils.nvlStr(powersLMap.get("poSt_OwnCityCode")));
			    electricPileMap.setElectricName(JudgeNullUtils.nvlStr(powersLMap.get("poSt_Name")));
			    electricPileMap.setElectricAddress(JudgeNullUtils.nvlStr(powersLMap.get("poSt_Address")));
			    electricPileMap.setIsAppoint(JudgeNullUtils.nvlInteget(powersLMap.get("poSt_IsAppoint")));
			    electricPileMap.setProCode(JudgeNullUtils.nvlStr(powersLMap.get("poSt_OwnProvinceCode")));
			    electricPileMap.setCouCode(JudgeNullUtils.nvlStr(powersLMap.get("poSt_OwnCountyCode")));
			    electricPileMap.setDel(0);
				electricPileMapList.add(electricPileMap);
			}
		}
		//01:获取电桩列表
		tblElectricpile.setElpiBinding(0);
		List<?> electricpileList=tblElectricpileMapper.getElectricpileForMap(tblElectricpile);
		if(null != electricpileList){
			for (int i = 0; i < electricpileList.size(); i++) {
				ElectricPileMap electricPileMap=new ElectricPileMap();
			    Map<String,Object> electricpileMap=(Map<String,Object>)electricpileList.get(i);
			    electricPileMap.setElectricId(JudgeNullUtils.nvlStr(electricpileMap.get("pk_ElectricPile")));
			    electricPileMap.setElectricType(ApplicationConsts.ElectricPileManager.ELECTRICPILE);
			    
			    electricPileMap.setElectricState(JudgeNullUtils.nvlStr(electricpileMap.get("elPi_State")));
			    electricPileMap.setLongitude(JudgeNullUtils.nvlStr(electricpileMap.get("elPi_Longitude")));
			    electricPileMap.setLatitude(JudgeNullUtils.nvlStr(electricpileMap.get("elPi_Latitude")));
			    electricPileMap.setCityCode(JudgeNullUtils.nvlStr(electricpileMap.get("elPi_OwnCityCode")));
			    electricPileMap.setProCode(JudgeNullUtils.nvlStr(electricpileMap.get("elPi_OwnProvinceCode")));
			    electricPileMap.setCouCode(JudgeNullUtils.nvlStr(electricpileMap.get("elPi_OwnCountyCode")));
			    electricPileMap.setElectricName(JudgeNullUtils.nvlStr(electricpileMap.get("elPi_ElectricPileName")));
			    electricPileMap.setElectricAddress(JudgeNullUtils.nvlStr(electricpileMap.get("elPi_ElectricPileAddress")));
			    electricPileMap.setIsAppoint(JudgeNullUtils.nvlInteget(electricpileMap.get("elPi_IsAppoint")));
			    electricPileMap.setDel(JudgeNullUtils.nvlInteget(electricpileMap.get("delete_flag")));
			    electricPileMapList.add(electricPileMap);
			    
			}
		}
		return electricPileMapList;
	}
  
	/**
	 * 获取地图锚点简介
	 */
	public Map<String, String> getAnchorSummary(Map<String, Object> params){
		Map<String, String> map = new HashMap<String, String>();
		//2站1桩
		if("1".equals(params.get("type"))){
			map = tblElectricpileMapper.getAnchorSummaryEp(params);
		}else{
			map = tblPowerstationMapper.getAnchorSummaryPs(params);
		}
		return map;
	}
	
	/**
	 * 根据用户id获取汽车的 直交流和接口标准
	 * @param userId
	 * @return
	 */
	public Map<String, String> getPileConditionByUserId(int userId){
		return tblElectricpileMapper.getPileConditionByUserId(userId);
	}

	/**
	 * 获取市级充电点聚合
	 * @return
	 * @throws IOException 
	 */
	@Override
	public List<Map<String, Object>> getCtyPoints(Map<String, Object> params) throws Exception {
		
		params.put("val", getProvinceCode(params.get("location").toString()));
		List<Map<String, String>> points = tblPowerstationMapper.getCtyPoints(params);
		List<Map<String, Object>> locationCountList = new ArrayList<Map<String,Object>>();
		Map<String, Object> locationCountMap = null;
		//Map<String, String> jwdMap = WanmaConstants.jwdMap;
		for(Map<String, String> point:points){
			String cityCode = point.get("cd");
			if(cityCode != null && !"".equals(cityCode)){
				locationCountMap = new HashMap<String, Object>();
				locationCountMap.put("location", cityCode);
				locationCountMap.put("count", point.get("pCount"));
				locationCountList.add(locationCountMap);
			}
		}
		return locationCountList;
	}
	
	private String getProvinceCode(String location) {
		try{
		String url = WanmaConstants.GEOCODE_WEBAPI_ROOTPATH+"regeo?key="+WanmaConstants.GEOCODE_MAP_KEY+"&location="+location;
		JSONObject locationData = JSON.parseObject(HttpUtil.getGeocodeMapStr(url));
		return JSON.parseObject(JSON.parseObject(locationData.getString("regeocode")).getString("addressComponent")).getString("adcode").substring(0,3)+"000";
	
		}
		catch(Exception e){
			return "330000";
		}
	}

	/**
	 * 获取省级充电点聚合
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getProPoints(Map<String, Object> params) {
		List<Map<String, String>> points = tblPowerstationMapper.getProPoints(params);
		List<Map<String, Object>> locationCountList = new ArrayList<Map<String,Object>>();
		Map<String, Object> locationCountMap = null;
		//Map<String, String> jwdMap = WanmaConstants.jwdMap;
		for(Map<String, String> point:points){
			String proCode = point.get("pd");
			if(proCode != null && !"".equals(proCode)){
				locationCountMap = new HashMap<String, Object>();
				locationCountMap.put("location", proCode);
				locationCountMap.put("count", point.get("pCount"));
				locationCountList.add(locationCountMap);
			}
		}
		return locationCountList;
	}
}
