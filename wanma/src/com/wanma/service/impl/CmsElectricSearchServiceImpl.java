package com.wanma.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.ApplicationConsts;
import com.wanma.common.JudgeNullUtils;
import com.wanma.dao.CmsElectricSearchMapper;
import com.wanma.dao.CmsPowerstationMapper;
import com.wanma.model.ElectricPileDetail;
import com.wanma.model.ElectricPileMap;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblPowerstation;
import com.wanma.service.CmsElectricSearchService;

@Service
public class CmsElectricSearchServiceImpl implements CmsElectricSearchService {

	@Autowired
	CmsElectricSearchMapper tblElectricpileMapper;
	@Autowired
	CmsPowerstationMapper tblPowerstationMapper;
	
	/**
	 * 获取地图模式电桩列表
	 */
	@Override
	public List<ElectricPileMap> getElectricMapList(TblPowerstation tblPowerstation,TblElectricpile tblElectricpile) {
		
		List<ElectricPileMap> electricPileMapList= new ArrayList<ElectricPileMap>();
		if(tblPowerstation.getPostPoweruser()==null||tblPowerstation.getPostPoweruser()==3){
			//01:获取充电点列表
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
		    electricPileMapList.add(electricPileMap);		    
		}
		return electricPileMapList;
	}

	@Override
	public ElectricPileDetail getCurrentPileDetail(String eid) {
		// TODO Auto-generated method stub
		return tblElectricpileMapper.getCurrentPileDetail(eid);
	}

	@Override
	public List<TblElectricpilehead> getCurrentHeadList(String eid) {
		return tblElectricpileMapper.getCurrentHeadList(eid);
	}

	@Override
	public TblElectricpilehead getHeadDetail(Map<String, String> params) {
		return tblElectricpileMapper.getHeadDetail(params);
	}

	@Override
	public List<String> queryErrorCodeListByUser(Map<String, String> params) {
		return tblElectricpileMapper.queryErrorCodeListByUser(params);
	}
  

}
