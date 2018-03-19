/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.common.WanmaConstants;
import com.base.util.JudgeNullUtils;
import com.wanma.dao.CmsElectricSearchMapper;
import com.wanma.dao.CmsElectricpileheadMapper;
import com.wanma.dao.CmsPowerstationMapper;
import com.wanma.model.PowerElectricPileHead;
import com.wanma.model.PowerStationDetail;
import com.wanma.model.PowerStationElictric;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblPowerstation;
import com.wanma.service.CmsPowerStationDetailService;

/***
 *
 *   充电点详情  
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-13 下午04:51:34 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
@Service
public class CmsPowerStationDetailServiceImpl implements CmsPowerStationDetailService {

	@Autowired
	CmsElectricSearchMapper tblElectricpileMapper;
	@Autowired
	CmsPowerstationMapper tblPowerstationMapper;
	@Autowired
	CmsElectricpileheadMapper tblElectricpileheadMapper;
	@Override
	public PowerStationDetail getPowerStationDetail(TblPowerstation tblPowerstation) {
		// TODO Auto-generated method stub
		PowerStationDetail powerStationDetail = null;
		
		//01：获取充电点信息
		Map<String,Object> powerStationInfoMap = tblPowerstationMapper.getPowerstationById(tblPowerstation);
		
		if(powerStationInfoMap != null){
			powerStationDetail = new PowerStationDetail();
			powerStationDetail.setPowerStationId(JudgeNullUtils.nvlStr(powerStationInfoMap.get("pkPowerstation")));
			powerStationDetail.setPowerStationName(JudgeNullUtils.nvlStr(powerStationInfoMap.get("postName")));
			powerStationDetail.setPowerStationImage(JudgeNullUtils.nvlStr(powerStationInfoMap.get("postPic")));
			powerStationDetail.setPowerStationState(JudgeNullUtils.nvlStr(powerStationInfoMap.get("postStatus")));
			powerStationDetail.setPowerElectricpileSum(JudgeNullUtils.nvlStr(powerStationInfoMap.get("pileSum")));
			powerStationDetail.setPowerStationAddress(JudgeNullUtils.nvlStr(powerStationInfoMap.get("postAddress")));
			powerStationDetail.setPostLongitude(JudgeNullUtils.nvlStr0(powerStationInfoMap.get("postLongitude")));
			powerStationDetail.setPostLatitude(JudgeNullUtils.nvlStr0(powerStationInfoMap.get("postLatitude")));
			powerStationDetail.setIsCollect(JudgeNullUtils.nvlStr(powerStationInfoMap.get("isCollect")));
			powerStationDetail.setPowerCommentStar(JudgeNullUtils.nvlStr0(powerStationInfoMap.get("powerCommentStar")));
			String phone=JudgeNullUtils.nvlStr(powerStationInfoMap.get("postPhone"));
			powerStationDetail.setPowerStationTell(StringUtils.isNotBlank(phone)?phone:WanmaConstants.WANMA_PHONE);
			//02：获取电桩列表
			TblElectricpile tblElectricpile =new TblElectricpile();
			tblElectricpile.setElPiRelevancePowerStation(tblPowerstation.getPkPowerstation());
			List<Map<String,Object>> electricpileList = tblElectricpileMapper.getElectricpileById(tblElectricpile);
			List<PowerStationElictric> powerStationElictricList = new ArrayList<PowerStationElictric>();
			
			for (int i = 0; i < electricpileList.size(); i++) {
				Map<String,Object> electricpileListMap = (Map<String, Object>) electricpileList.get(i);
				PowerStationElictric powerStationElictric=new PowerStationElictric();
				powerStationElictric.setElictricPicId(JudgeNullUtils.nvlStr(electricpileListMap.get("pkElectricpile")));
				powerStationElictric.setElictricPicName(JudgeNullUtils.nvlStr(electricpileListMap.get("elpiElectricpilename")));
				//powerStationElictric.setPicPowerSum(JudgeNullUtils.nvlStr(electricpileListMap.get("pileHeadSum")));
				powerStationElictric.setElictricPicImage(JudgeNullUtils.nvlStr(electricpileListMap.get("elpiImage")));
				powerStationElictric.setElectricPileNo(JudgeNullUtils.nvlStr(electricpileListMap.get("elpiElectricpilecode")));
				powerStationElictric.setElectricPileUserName(JudgeNullUtils.nvlStr(electricpileListMap.get("elpiPoweruser")));
				powerStationElictric.setElectricPileChargingMode(JudgeNullUtils.nvlStr(electricpileListMap.get("elpiChargingmode")));
				powerStationElictric.setElectricPowerSize(JudgeNullUtils.nvlStr(electricpileListMap.get("elpiPowersize")));
				powerStationElictric.setElectricPowerInterface(JudgeNullUtils.nvlStr(electricpileListMap.get("elpiPowerinterface")));
				powerStationElictric.setElectricNum(JudgeNullUtils.nvlStr(electricpileListMap.get("ep_num")));
				powerStationElictric.setRaInReservationRate(JudgeNullUtils.nvlStr0(electricpileListMap.get("raInReservationRate")));
				powerStationElictric.setRaInServiceCharge(JudgeNullUtils.nvlStr0(electricpileListMap.get("raInServiceCharge")));
				powerStationElictric.setElectricPileConnStatus(JudgeNullUtils.nvlStr0(electricpileListMap.get("commStatus")));
				powerStationElictric.setElectricPileState(JudgeNullUtils.nvlStr0(electricpileListMap.get("elpiState")));
				//03:获取枪头列表
				TblElectricpilehead tblElectricpilehead = new TblElectricpilehead();
				tblElectricpilehead.setPkElectricpile(JudgeNullUtils.nvlInteger(electricpileListMap.get("pkElectricpile")));
				List<Map<String,Object>> electricpileheadList = tblElectricpileheadMapper.find(tblElectricpilehead);
				List<PowerElectricPileHead> pileHeadList = new ArrayList<PowerElectricPileHead>();
				for (int j = 0; j < electricpileheadList.size(); j++) {
					Map<String,Object> electricpileheadListMap = (Map<String, Object>) electricpileheadList.get(j);
					PowerElectricPileHead powerElectricPileHead = new PowerElectricPileHead();
					powerElectricPileHead.setPileHeadName(JudgeNullUtils.nvlStr(electricpileheadListMap.get("epheElectricpileheadname")));
					powerElectricPileHead.setPileHeadState(JudgeNullUtils.nvlStr(electricpileheadListMap.get("epheElectricpileheadstate")));
					powerElectricPileHead.setPileHeadId(JudgeNullUtils.nvlStr(electricpileheadListMap.get("pkElectricpilehead")));
					pileHeadList.add(powerElectricPileHead);
				}
				//添加枪头
				powerStationElictric.setPileHeadList(pileHeadList);
				powerStationElictricList.add(powerStationElictric);
			}
			//添加电桩列表
			powerStationDetail.setPowerElectricpileList(powerStationElictricList);
//			//03：获取充电点评论
//			TblProductcomment tblProductcomment = new TblProductcomment();
//			tblProductcomment.setPrcoProductid(JudgeNullUtils.nvlLang(tblPowerstation.getPkPowerstation()).intValue());
//			tblProductcomment.setPrcoCommentType(ApplicationConsts.ElectricPileManager.ELECTRIC_POWER);
//			List<TblProductcomment> commentList = productcommentMapper.findProCommentsByPowerId(tblProductcomment);
//			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("prCoProductId", JudgeNullUtils.nvlLang(tblPowerstation.getPkPowerstation()).intValue());
//			params.put("prcoCommentType", ApplicationConsts.ElectricPileManager.ELECTRIC_POWER);
//			powerStationDetail.setPowerCommentSum(String.valueOf(productcommentMapper.countProComments(params)));
//			powerStationDetail.setCommentList(commentList);
		}
		
		return powerStationDetail;
	}

}
