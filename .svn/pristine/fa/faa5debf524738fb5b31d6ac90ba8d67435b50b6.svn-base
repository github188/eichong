/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bluemobi.product.utils.MultipartFileUtil;
import com.wanma.app.dao.AppElecPileCommentMapper;
import com.wanma.app.dao.AppElecPileStarMapper;
import com.wanma.app.dao.AppProductcommentMapper;
import com.wanma.app.dao.TblElectricpileMapper;
import com.wanma.app.dao.TblElectricpileheadMapper;
import com.wanma.app.service.ElectricPileDetaillService;
import com.wanma.common.ApplicationConsts;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.WanmaConstants;
import com.wanma.model.ElectricPileDetail;
import com.wanma.model.PowerElectricPileHead;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblPowerstation;
import com.wanma.model.TblProductcomment;

/***
 * 
 * 电站详情
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-13 下午04:51:34
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service("electricPileDetailService")
public class ElectricPileDetailServiceImpl implements
		ElectricPileDetaillService {

	@Autowired
	TblElectricpileMapper tblElectricpileMapper;
	@Autowired
	TblElectricpileheadMapper tblElectricpileheadMapper;
	@Autowired
	AppProductcommentMapper appProductcommentMapper;
	@Autowired
	AppElecPileCommentMapper appElecPileCommentMapper;
	@Autowired
	AppElecPileStarMapper appElecPileStarMappr;
	/**
	 * @Title: getSharePowerstation
	 * @Description: 查询电桩分享信息
	 * @param TblPowerstation
	 * @return
	 */
	/*@Override
	public TblElectricpile getShareElectricpile(TblElectricpile tblElectricpile) {
		TblElectricpile newElectricpile = tblElectricpileMapper
				.getShareElectricPile(tblElectricpile);
		// 获取电桩图片
		List<String> listImage = MultipartFileUtil.getAllMultiUrl(
				WanmaConstants.MULTI_TYPE_ELECTRICT_LIST_IMAGE,
				newElectricpile.getPkElectricpile() + "");
		if (listImage.size() == 0) {
			newElectricpile
					.setElpiImage("http://localhost:8080/wanma/upload/shareImage/share.jpg");
		} else {
			newElectricpile
					.setElpiImage(JudgeNullUtils.nvlStr(listImage.get(0)));
		}
		return newElectricpile;
	}*/

	@Override
	public List<ElectricPileDetail> getElectricPileDetail(
			TblElectricpile tblElectricpile) {
		// TODO Auto-generated method stub
		List<ElectricPileDetail> electricPileDetailList = new ArrayList();

		// 01:获取电桩基本信息
		List<TblElectricpile> electricpileList = tblElectricpileMapper.findOneN(tblElectricpile);
		Map<String, Object> electricpileMap = (Map<String, Object>) electricpileList.get(0);

		ElectricPileDetail electricPileDetail = new ElectricPileDetail();
		electricPileDetail.setPk_ElectricPile(JudgeNullUtils.nvlStr(electricpileMap.get("pkElectricpile")));
		electricPileDetail.setElectricPileName(JudgeNullUtils.nvlStr(electricpileMap.get("elpiElectricpilename")));
		electricPileDetail.setElectricPileImage(JudgeNullUtils
				.nvlStr(electricpileMap.get("elpiImage")));
		electricPileDetail.setPowerUser(JudgeNullUtils.nvlInteget(electricpileMap.get("elpiPoweruser")));
		electricPileDetail.setElectricPileUserName(JudgeNullUtils
				.nvlStr(electricpileMap.get("elPi_PowerUserName")));
		electricPileDetail.setElectricPileState(JudgeNullUtils
				.nvlStr(electricpileMap.get("elpiState")));
		electricPileDetail.setElectricPileNo(JudgeNullUtils
				.nvlStr(electricpileMap.get("elpiElectricpilecode")));
		electricPileDetail.setElectricPileChargingMode(JudgeNullUtils
				.nvlStr(electricpileMap.get("elPi_ChargingModeName")));
		electricPileDetail.setElectricPileParam(JudgeNullUtils
				.nvlStr(electricpileMap.get("elpiPowersize")));
		electricPileDetail.setElectricPileAdress(JudgeNullUtils
				.nvlStr(electricpileMap.get("elpiElectricpileaddress")));
		electricPileDetail.setElectricPileTell(JudgeNullUtils
				.nvlStr(electricpileMap.get("elPi_Tell")));
		electricPileDetail.setElpiLongitude(JudgeNullUtils
				.nvlStr(electricpileMap.get("elpiLongitude")));
		electricPileDetail.setElpiLatitude(JudgeNullUtils
				.nvlStr(electricpileMap.get("elpiLatitude")));
		electricPileDetail.setComm_status(JudgeNullUtils.nvlInteget(electricpileMap.get("comm_status")));
		electricPileDetail.setOnlineTime(JudgeNullUtils.nvlStr(electricpileMap.get("elPi_OnlineTime")));
		electricPileDetail.setJlHeadNum(JudgeNullUtils.nvlStr(electricpileMap.get("jlHeadNum")));
		electricPileDetail.setJlFreeHeadNum(JudgeNullUtils.nvlStr(electricpileMap.get("jlFreeHeadNum")));
		electricPileDetail.setZlHeadNum(JudgeNullUtils.nvlStr(electricpileMap.get("zlHeadNum")));
		electricPileDetail.setZlFreeHeadNum(JudgeNullUtils.nvlStr(electricpileMap.get("zlFreeHeadNum")));
		electricPileDetail.setOwnerCompany(JudgeNullUtils.nvlStr(electricpileMap.get("elPi_OwnerCompany")));
		electricPileDetail.setHaveLine(JudgeNullUtils.nvlInteget(electricpileMap.get("haveLine")));
		electricPileDetail.setParkFee(JudgeNullUtils.nvlStr(electricpileMap.get("elPi_Parking_Fee")));
		String dis = JudgeNullUtils.nvlStr(electricpileMap.get("distance"));
		if("".equals(dis)){
			electricPileDetail.setDistance("0");
		}else{
			electricPileDetail.setDistance(dis);
		}
		//electricPileDetail.setelectric
		electricPileDetail.setIsCollect(JudgeNullUtils.nvlStr(electricpileMap.get("isCollect")));
		electricPileDetail.setElectricPileRemark(JudgeNullUtils.nvlStr(electricpileMap.get("elPi_Remark")));
		electricPileDetail.setRateId(JudgeNullUtils.nvlInteget(electricpileMap.get("elPi_RateInformationId")));
		electricPileDetail.setRaIn_ReservationRate(
				new BigDecimal(JudgeNullUtils.nvlStr(electricpileMap.get("raIn_ReservationRate"))));
		electricPileDetail.setRaIn_ServiceCharge(
				new BigDecimal(JudgeNullUtils.nvlStr(electricpileMap.get("raIn_ServiceCharge"))));
		electricPileDetail.setElectricPowerInterface(JudgeNullUtils.nvlStr(electricpileMap.get("elpiPowerinterface")));
		//电费格式化
		String mark = PowerStationDetailServiceImpl.getCurrentPowerRateMark(JudgeNullUtils.nvlStr(electricpileMap.get("rateDate")));
		switch(mark){
			case "1":
				electricPileDetail.setCurrentRate(new BigDecimal(JudgeNullUtils.nvlStr(electricpileMap.get("jPrice"))));
				break;
			case "2":
				electricPileDetail.setCurrentRate(new BigDecimal(JudgeNullUtils.nvlStr(electricpileMap.get("fPrice"))));
				break;
			case "3":
				electricPileDetail.setCurrentRate(new BigDecimal(JudgeNullUtils.nvlStr(electricpileMap.get("pPrice"))));
				break;
			case "4":
				electricPileDetail.setCurrentRate(new BigDecimal(JudgeNullUtils.nvlStr(electricpileMap.get("gPrice"))));
				break;
			default:
				electricPileDetail.setCurrentRate(new BigDecimal(0));
		}
		
		String ps = "";
		if(!StringUtils.isEmpty(electricpileMap.get("elpiPowersize"))){
			String st = electricpileMap.get("elpiPowersize").toString();
			if("6".equals(st)){
				ps = "3.5kw";
			}else if("15".equals(st)){
				ps = "7kw";
			}else if("16".equals(st)){
				ps = "20kw";
			}else if("17".equals(st)){
				ps = "50kw";
			}else if("18".equals(st)){
				ps = "75kw";
			}else if("44".equals(st)){
				ps = "10kw";
			}else if("43".equals(st)){
				ps = "120kw";
			}
		}
		electricPileDetail.setElectricPowerSize(ps);
		electricPileDetail.setElectricPileChargingMode(JudgeNullUtils.nvlStr(electricpileMap.get("elpiChargingmode")));
		// 02:获取枪头列表
		TblElectricpilehead tblElectricpilehead = new TblElectricpilehead();
		tblElectricpilehead.setPkElectricpile(JudgeNullUtils
				.nvlInteget(tblElectricpile.getPkElectricpile()));
		List<TblElectricpilehead> electricpileheadList = tblElectricpileheadMapper
				.find(tblElectricpilehead);
		List<PowerElectricPileHead> pileHeadList = new ArrayList();
		for (int j = 0; j < electricpileheadList.size(); j++) {
			Map<String, Object> electricpileheadListMap = (Map<String, Object>) electricpileheadList.get(j);
			PowerElectricPileHead powerElectricPileHead = new PowerElectricPileHead();
			powerElectricPileHead.setPileHeadName(JudgeNullUtils
					.nvlStr(electricpileheadListMap
							.get("epheElectricpileheadname")));
			powerElectricPileHead.setPileHeadState(JudgeNullUtils
					.nvlStr(electricpileheadListMap
							.get("epheElectricpileheadstate")));
			powerElectricPileHead.setPileHeadId(JudgeNullUtils
					.nvlStr(electricpileheadListMap.get("pkElectricpilehead")));
			pileHeadList.add(powerElectricPileHead);
		}
		// 添加枪头
		electricPileDetail.setPileHeadList(pileHeadList);

		// 03：获取电站评论
		TblProductcomment tblProductcomment = new TblProductcomment();
		tblProductcomment.setPrcoProductid(JudgeNullUtils.nvlLang(
				tblElectricpile.getPkElectricpile()).intValue());
		tblProductcomment
				.setPrcoCommentType(ApplicationConsts.ElectricPileManager.COMMENT_ELECTRIC);
		List<?> commentList = appElecPileCommentMapper.countEpCommentsByPowerId(tblElectricpile.getPkElectricpile());
		/*List<?> commentList = appProductcommentMapper
				.findProCommentsByPowerId(tblProductcomment);*/
		Map<String, Object> commentMap = (Map<String, Object>) commentList.get(0);
		electricPileDetail.setElectricPileCommentSum(JudgeNullUtils
				.nvlStr(commentMap.get("powerCommentCount")));
		/*electricPileDetail.setElectricPileCommentUser(JudgeNullUtils
				.nvlStr(commentMap.get("prcoUsername")));
		electricPileDetail.setElectricPileCommentStar(JudgeNullUtils
				.nvlStr(commentMap.get("prcoCommentstart")));
		electricPileDetail.setElectricPileCommentContent(JudgeNullUtils
				.nvlStr(commentMap.get("prcoContent")));*/
		List<?> starList = appElecPileStarMappr.countStarByElecPileId(tblElectricpile.getPkElectricpile());
		Map<String, Object> starMap = (Map<String, Object>) starList.get(0);
		electricPileDetail.setElectricPileCommentStar(JudgeNullUtils
				.nvlStr(starMap.get("epStarCount")));
		electricPileDetailList.add(electricPileDetail);

		return electricPileDetailList;
	}

	/**
	 * @Title: selectPileInfo
	 * @Description: 手机端扫描二维码显示电桩和枪口信息
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> selectPileInfo(Map<String, Object> params) {
		return tblElectricpileMapper.selectPileInfo(params);
	}

	/**
	 * @Title: selectPileInfo
	 * @Description: 手机端输入二维码编码显示电桩和枪口信息
	 * @param params
	 * @return
	 */
	public Map<String, Object> zNumSelPileInfo(String zCodeNum){
		return tblElectricpileMapper.zNumSelPileInfo(zCodeNum);
		//return new HashMap<String, Object>();
	}
	
	/**
	 * @Title:getElectricPileByCode
	 * @Description: 根据电桩编号获取电桩信息
	 * @param params
	 * @return
	 */
	@Override
	public TblElectricpile getElectricPileByCode(String elpiElectricpilecode) {
		return tblElectricpileMapper
				.getElectricPileByCode(elpiElectricpilecode);
	}

	@Override
	public List<Map<String, String>> getEpCityCode() {
		
		return tblElectricpileMapper.getEpCityCode();
	}

	
	/*public TblElectricpile getGateIdByEpId(String epId){
		tblElectricpile = new TblElectricpile();
		tble
		return tblElectricpileMapper.get(tblElectricpile);
	}*/
}
