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
import com.wanma.dao.CmsElectricPileMapper;
import com.wanma.dao.CmsElectricpileheadMapper;
import com.wanma.model.ElectricPileDetail;
import com.wanma.model.PowerElectricPileHead;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblElectricpilehead;
import com.wanma.service.ElectricPileDetaillMapService;

/***
 *
 * 充电点详情
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-13 下午04:51:34
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class ElectricPileDetailMapServiceImpl implements
		ElectricPileDetaillMapService {

	@Autowired
	CmsElectricPileMapper tblElectricpileMapper;
	@Autowired
	CmsElectricpileheadMapper tblElectricpileheadMapper;
/*	@Autowired
	WebProductcommentMapper productcommentMapper;*/

	@Override
	public ElectricPileDetail getElectricPileDetail(
			TblElectricpile tblElectricpile) {
		// TODO Auto-generated method stub
		ElectricPileDetail electricPileDetail = null;

		// 01:获取电桩基本信息
		Map<String, Object> electricpileMap = tblElectricpileMapper
				.findDetailById(tblElectricpile);

		if (electricpileMap != null) {
			electricPileDetail = new ElectricPileDetail();
			electricPileDetail.setPk_ElectricPile(JudgeNullUtils
					.nvlStr(electricpileMap.get("pkElectricPile")));
			electricPileDetail.setElectricPileUserName(JudgeNullUtils
					.nvlStr(electricpileMap.get("elpiPoweruser")));
			electricPileDetail.setElectricPileImage(JudgeNullUtils
					.nvlStr(electricpileMap.get("elPiImage")));
			electricPileDetail.setElectricPileName(JudgeNullUtils
					.nvlStr(electricpileMap.get("elPiElectricPileName")));
			electricPileDetail.setElectricPileState(JudgeNullUtils
					.nvlStr(electricpileMap.get("elPiState")));
			electricPileDetail.setElectricPileNo(JudgeNullUtils
					.nvlStr(electricpileMap.get("elPiElectricPileCode")));
			electricPileDetail.setElectricPileChargingMode(JudgeNullUtils
					.nvlStr(electricpileMap.get("elPiChargingMode")));
			electricPileDetail.setElectricPileParam(JudgeNullUtils
					.nvlStr(electricpileMap.get("elPiPowerSize")));
			electricPileDetail.setElectricPileAdress(JudgeNullUtils
					.nvlStr(electricpileMap.get("elPiElectricPileAddress")));
			electricPileDetail.setIsCollect(JudgeNullUtils
					.nvlStr(electricpileMap.get("isCollect")));
			electricPileDetail.setElectricPowerInterface(JudgeNullUtils
					.nvlStr(electricpileMap.get("elPiPowerInterface")));
			electricPileDetail.setElectricPowerSize(JudgeNullUtils
					.nvlStr0(electricpileMap.get("elPiPowerSize")));
			electricPileDetail.setElectricMaxElectricity(JudgeNullUtils
					.nvlStr0(electricpileMap.get("elPiMaxElectricity")));
			electricPileDetail.setElpiLongitude(JudgeNullUtils
					.nvlStr0(electricpileMap.get("elPiLongitude")));
			electricPileDetail.setElpiLatitude(JudgeNullUtils
					.nvlStr0(electricpileMap.get("elPiLatitude")));
			electricPileDetail.setRaInReservationRate(JudgeNullUtils
					.nvlStr0(electricpileMap.get("raInReservationRate")));
			electricPileDetail.setRaInServiceCharge(JudgeNullUtils
					.nvlStr0(electricpileMap.get("raInServiceCharge")));
			electricPileDetail.setCommStatus(new Integer(JudgeNullUtils
					.nvlStr0(electricpileMap.get("commStatus"))));
			electricPileDetail.setElectricPileCommentStar(JudgeNullUtils
					.nvlStr0(electricpileMap.get("epStarCount")));
			electricPileDetail.setOnlineTime(JudgeNullUtils
					.nvlStr0(electricpileMap.get("elPiOnlineTime")));
			String phone = JudgeNullUtils.nvlStr(electricpileMap
					.get("elPiTell"));
			electricPileDetail.setElectricPileTell(StringUtils
					.isNotBlank(phone) ? phone : WanmaConstants.WANMA_PHONE);
			electricPileDetail.setTotalChargeDl(JudgeNullUtils.nvlStr0(electricpileMap.get("totalChargeDl")));
			electricPileDetail.setTotalChargeTime(JudgeNullUtils.nvlStr0(electricpileMap.get("totalChargeTime")));
			electricPileDetail.setTotalChargeAmt(JudgeNullUtils.nvlStr0(electricpileMap.get("totalChargeAmt")));
			// 02:获取枪头列表
			TblElectricpilehead tblElectricpilehead = new TblElectricpilehead();
			tblElectricpilehead.setPkElectricpile(JudgeNullUtils
					.nvlInteger(tblElectricpile.getPkElectricpile()));
			List<Map<String, Object>> electricpileheadList = tblElectricpileheadMapper
					.findMonitorHeadInfo(tblElectricpilehead);
			List<PowerElectricPileHead> pileHeadList = new ArrayList<PowerElectricPileHead>();
			for (int i = 0; i < electricpileheadList.size(); i++) {
				Map<String, Object> electricpileheadListMap = (Map<String, Object>) electricpileheadList
						.get(i);
				PowerElectricPileHead powerElectricPileHead = new PowerElectricPileHead();
				powerElectricPileHead.setPileHeadName(JudgeNullUtils
						.nvlStr(electricpileheadListMap
								.get("ePHe_ElectricpileHeadName")));
				powerElectricPileHead.setPileHeadState(JudgeNullUtils
						.nvlStr(electricpileheadListMap
								.get("ePHe_ElectricpileHeadState")));
				powerElectricPileHead.setPileHeadId(JudgeNullUtils
						.nvlStr(electricpileheadListMap
								.get("pk_ElectricpileHead")));
				pileHeadList.add(powerElectricPileHead);
			}

			int headListSize = pileHeadList.size();
			if (headListSize < 3) {
				for (int j = headListSize; j < 3; j++)
					pileHeadList.add(new PowerElectricPileHead());
			} else if (headListSize > 3) {
				List<PowerElectricPileHead> sortPileHeadList = new ArrayList<PowerElectricPileHead>();
				for (int j = 0; j < headListSize; j++) {
					PowerElectricPileHead pileHead = pileHeadList.get(j);
					if ("0".equals(pileHead.getPileHeadState())) {
						sortPileHeadList.add(pileHead);
					}
				}
				for (int j = 0; j < headListSize; j++) {
					PowerElectricPileHead pileHead = pileHeadList.get(j);
					if (!"0".equals(pileHead.getPileHeadState())) {
						sortPileHeadList.add(pileHead);
					}
				}
				pileHeadList = sortPileHeadList;
			}
			// 添加枪头
			electricPileDetail.setPileHeadList(pileHeadList);

			/*// 03：获取充电点评论
			TblProductcomment tblProductcomment = new TblProductcomment();
			tblProductcomment.setPrcoProductid(JudgeNullUtils.nvlLang(
					tblElectricpile.getPkElectricpile()).intValue());
			tblProductcomment
					.setPrcoCommentType(ApplicationConsts.ElectricPileManager.COMMENT_ELECTRIC);
			List<TblProductcomment> commentList = productcommentMapper
					.findProCommentsByPowerId(tblProductcomment);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("prCoProductId",
					JudgeNullUtils.nvlLang(tblElectricpile.getPkElectricpile())
							.intValue());
			params.put("prcoCommentType",
					ApplicationConsts.ElectricPileManager.COMMENT_ELECTRIC);
			electricPileDetail.setElectricPileCommentSum(String
					.valueOf(productcommentMapper.countProComments(params)));
			electricPileDetail.setCommentList(commentList);*/
		}
		return electricPileDetail;
	}

	@Override
	public TblElectricpile getShareElectricpile(TblElectricpile tblElectricpile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectPileInfo(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TblElectricpile getElectricPileByCode(String elpiElectricpilecode) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getKongxianCount(ElectricPileDetail electricPileDetail){
		int count = 0;
		if("1".equals(electricPileDetail.getCommStatus())){
			List<PowerElectricPileHead> headList = electricPileDetail.getPileHeadList();
			for(PowerElectricPileHead headInfo:headList){
				if("0".equals(headInfo.getPileHeadState())){
					count++;
					continue;
				}
			}
		}
		return count;
	}
}
