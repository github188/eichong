package com.wanma.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.echong.common.MessageManager;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblPartner;
import com.wanma.model.TblPowerstation;
import com.wanma.model.TblRateInformation;
import com.wanma.model.TcbElectric;
import com.wanma.model.cdzts.AreaMessage;
import com.wanma.model.cdzts.PowerstationPush;
import com.wanma.model.cdzts.TblElectricpileheadForSH;
import com.wanma.service.AreaMessageService;
import com.wanma.service.CmsPowerstationService;
import com.wanma.service.CmsRateInfoService;
import com.wanma.service.CommonService;
import com.wanma.service.ElectricPileListService;
import com.wanma.service.PowerStationPushService;
import com.wanma.service.TblElectricpileHeadService;
import com.wanma.service.TblElectricpileService;
import com.wanma.service.TblPowerstationService;
import com.wanma.service.TblRateInformationService;
import com.wanma.service.TcbPartnerService;
import com.wanma.support.cdzts.WanmaConstants;
import com.wanma.support.common.RedisService;
import com.wanma.support.simple.JudgeNullUtils;
import com.wanma.support.utils.CecPost;
import com.wanma.support.utils.RateinformationUtil;

@Service
public class PowerStationPushServiceImpl implements PowerStationPushService{
	private static final Logger LOGGER = LoggerFactory.getLogger(PowerStationPushServiceImpl.class);
	
	@Autowired
	private TblRateInformationService rateService;
	@Autowired
	private CmsPowerstationService powerstationService;
	@Autowired
	private ElectricPileListService electricPileListService;
	@Autowired
	private CmsRateInfoService rateInfoService;
	@Autowired
	private TblElectricpileService elcService;
	@Autowired
	private TblElectricpileHeadService hService;
	@Autowired
	private TcbPartnerService partnerService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private TblPowerstationService psService;
	@Autowired
	private AreaMessageService amsgService;
	@Autowired
    private RedisService redisService;
	
	@Override
	public int shParkingPush(String powerStationId) {
		try {
			LOGGER.info("......向上海停车办推送电站信息-begin,电站ID:{}",powerStationId);	
			PowerstationPush psModel = powerstationService.getPowerById(powerStationId);
			Map<String, Object> psData = null;
			Map<String, Object> elcData = null;
			Map<String, Object> hData = null;
			psData = new HashMap<>();
			psData.put("StationID", psModel.getPkPowerstation());
			psData.put("OperatorID", WanmaConstants.PRI_OPERATOR_ID);
			psData.put("EquipmentOwnerID", WanmaConstants.PRI_OPERATOR_ID);
			psData.put("StationName", psModel.getPostName());
			psData.put("CountryCode", "CN");
			psData.put("AreaCode", psModel.getPostOwnCountyCode());
			psData.put("Address", psModel.getPostAddress());
			psData.put("StationTel", psModel.getPostPhone());
			psData.put("ServiceTel", psModel.getPostPhone());
			if(psModel.getPostStatus() == 10){//第一阶段分享的对他们公共的，专属的都定义为物流专用
				psData.put("StationType", 102);
			}else if(psModel.getPostStatus() == 15){
				psData.put("StationType", 1);
			}else {
				psData.put("StationType", 255);
			}
			psData.put("StationStatus", 50);
			psData.put("ParkNums", 0);
			psData.put("StationLng", psModel.getPostLongitude());
			psData.put("StationLat", psModel.getPostLatitude());
			psData.put("SiteGuide", "");
			psData.put("Construction", 255);
			String postPic = psModel.getPostPic();
			postPic = postPic ==null? "":postPic;
			psData.put("Pictures", postPic.split(","));
			psData.put("OpenAllDay", 1);
			psData.put("MinElectricityPrice", rateInfoService.selectMinPriceByPsId(psModel.getPkPowerstation()));
			psData.put("ParkFree", psModel.getParkFree());
			psData.put("Payment", "线上");
			psData.put("SupportOrder", 0);
			List<TcbElectric> elcList = electricPileListService.getElectricpileListByPsId(psModel.getPkPowerstation());
			List<Map<String, Object>> elcDataList = new ArrayList<Map<String,Object>>();
			for(TcbElectric e:elcList){
			elcData = new HashMap<>();
			elcData.put("EquipmentID", e.getEquipNo());
			elcData.put("ManufacturerID", "MA27W7H33");
			elcData.put("EquipmentModel", e.getEquipNo());
			elcData.put("EquipmentName", e.getEquipName());
			elcData.put("ProductionDate", "");
			if("14".equals(e.getEquipType())){
				elcData.put("EquipmentType", 2);
			}else if("5".equals(e.getEquipType())){
				elcData.put("EquipmentType", 1);
			}else{
				elcData.put("EquipmentType", 3);
			}
			if("1".equals(e.getDeleteFlag())){
				elcData.put("EquipmentStatus", 5);
			}else if("0".equals(e.getDeleteFlag())){
				elcData.put("EquipmentStatus", 50);
			}else{
				elcData.put("EquipmentStatus", 0);
			}
			elcData.put("EquipmentPower", e.getPowerRating().substring(0, e.getPowerRating().length()-2));
			elcData.put("NewNationalStandard", 1);
			elcData.put("EquipmentLng", e.getElcLng());
			elcData.put("EquipmentLat", e.getElcLat());
			//推送参数
			TblElectricpileheadForSH hModel = new TblElectricpileheadForSH();
			hModel.setPkElectricpile(e.getPkElc());
			List<TblElectricpileheadForSH> headList = electricPileListService.getList(hModel);
			List<Map<String, Object>> headDataList = new ArrayList<>();
				for(TblElectricpileheadForSH h:headList){
					hData = new HashMap<>();
					hData.put("ConnectorID", String.format("%s%02d", e.getEquipNo(), h.getEpheElectricpileHeadId()));
					hData.put("ConnectorName", h.getEpheElectricpileheadname());
					if("14".equals(e.getEquipType()))
						hData.put("ConnectorType", 3);
					if("5".equals(e.getEquipType()))
						hData.put("ConnectorType", 4);
					hData.put("VoltageUpperLimits", Integer.parseInt(e.getVoltageRated().substring(0, e.getVoltageRated().length()-3)));
					hData.put("VoltageLowerLimits", Integer.parseInt(e.getVoltageRated().substring(0, e.getVoltageRated().length()-3)));
					hData.put("Current", Integer.parseInt(e.getCurrentRated().substring(0, e.getCurrentRated().length()-3)));
					hData.put("Power", e.getPowerRating().substring(0, e.getPowerRating().length()-2));
					hData.put("ParkNo", "");
					headDataList.add(hData);
				}
			elcData.put("ConnectorInfos", headDataList);
			elcDataList.add(elcData);
			}
			psData.put("EquipmentInfos", elcDataList);
			//获取加密信息	
	        String operatorID = "425010765";
			TblPartner tblPartner = partnerService.PartnerInfo(operatorID);
			//电站信息变化推送URL
			String pushStationInfoUrl = tblPartner.getPushOrderCheckUrl();
			LOGGER.info("...........上海停车办获取token-begin.........");
			Map<String, String> map =  commonService.getShPakingToken(operatorID);	
			String token = map.get("AccessToken");	
			LOGGER.info("...........上海停车办获取token-end.........");
			Map<String,Object> data = new HashMap<>();
			data.put("StationInfo", psData);
			String pushDate = new JSONObject(data).toString();
			LOGGER.info("向上海停车办推送电站的信息：{}",pushDate);
			JSONObject jsonData = CecPost.HttpPost(pushStationInfoUrl, WanmaConstants.PRI_OPERATOR_ID,token, 
					tblPartner.getSigSecret(), pushDate,tblPartner.getAesSecret(), tblPartner.getAesIv());
			LOGGER.info("上海停车办-充电站推送回执：{}",jsonData);
			int status = 1;
			if (jsonData != null ){
				status= Integer.parseInt(jsonData.getString("Status")); 
			}
			if(status == 1){
				return 1;
			}
			LOGGER.info(".................向上海停车办推送电站信息--end................");
		} catch (Exception e) {
			LOGGER.info("....向上海停车办推送电站信息失败..........");
			return 1;
		}
		return 0;
		
	}

	@Override
	public int nariPush(String powerStationId) {
		try {
			LOGGER.info("......南京南瑞充电站信息变化推送-begin,电站ID:{}",powerStationId);	
			//获取南瑞加密信息	
	        String operatorID = "01294771X";
			TblPartner tblPartner = partnerService.PartnerInfo(operatorID);
			PowerstationPush psModel = powerstationService.getPowerById(powerStationId);
			Map<String, Object> psData = null;
			Map<String, Object> elcData = null;
			Map<String, Object> hData = null;
			psData = new HashMap<>();
			psData.put("StationID", powerStationId);
			psData.put("OperatorID", "321895837");
			psData.put("EquipmentOwnerID", "321895837");
			psData.put("StationName", psModel.getPostName());
			psData.put("CountryCode", "CN");
			psData.put("AreaCode", psModel.getPostOwnCountyCode());
			psData.put("Address", psModel.getPostAddress());
			psData.put("StationTel", psModel.getPostPhone());
			psData.put("ServiceTel", psModel.getPostPhone());
			if(psModel.getPostStatus() == 10){//第一阶段分享的对他们公共的，专属的都定义为物流专用
				psData.put("StationType", 102);
			}else if(psModel.getPostStatus() == 15){
				psData.put("StationType", 1);
			}else {
				psData.put("StationType", 255);
			}
			psData.put("StationStatus", 50);
			psData.put("ParkNums", 0);
			psData.put("StationLng", psModel.getPostLongitude());
			psData.put("StationLat", psModel.getPostLatitude());
			psData.put("SiteGuide", "");
			psData.put("Construction", 255);
			String postPic = psModel.getPostPic();
			postPic = postPic ==null? "":postPic;
			psData.put("Pictures", postPic.split(","));
			psData.put("MatchCars", "");
			psData.put("ParkInfo", "");
			psData.put("ParkOwner", "");
			psData.put("ParkManager", "");
			psData.put("OpenAllDay", 1);
			psData.put("BusineHours", psModel.getPoStOnlineTime());
			psData.put("MinElectricityPrice", rateInfoService.selectMinPriceByPsId(psModel.getPkPowerstation()));
			psData.put("ParkFree", null);
			psData.put("ParkFee", "");
			psData.put("SupportOrder", 0);
			psData.put("Remark", "");
			TblElectricpile pile = new TblElectricpile();
			pile.setElpiRelevancepowerstation(psModel.getPkPowerstation());
			List<TblElectricpile> pList = elcService.getElectricPileByPowerStationId(pile);
			pile = pList.get(0);
			Map<String, Object> rateParm = new HashMap<>();
			rateParm.put("pkRateinformation", pile.getElpiRateinformationid());		
			TblRateInformation rate = rateService.getPriceById(rateParm);
			if (rate.getRaInServiceCharge() !=null || !rate.getRaInServiceCharge().equals(0)){
				String mark =RateinformationUtil.getCurrentPowerRateMark(JudgeNullUtils.nvlStr(rate.getRaInQuantumDate()));
				switch (mark) {
				case "1":
					psData.put("ElectricityFee",String.valueOf(rate.getRaInTipTimeTariff()));
					psData.put("ServiceFee",String.valueOf(rate.getRaInServiceCharge()));
					break;
				case "2":
					psData.put("ElectricityFee",String.valueOf(rate.getRaInPeakElectricityPrice()));
					psData.put("ServiceFee",String.valueOf(rate.getRaInServiceCharge()));
					break;
				case "3":
			        psData.put("ElectricityFee",String.valueOf(rate.getRaInUsualPrice()));
			    	psData.put("ServiceFee",String.valueOf(rate.getRaInServiceCharge()));
				case "4":		
					psData.put("ElectricityFee",String.valueOf(rate.getRaInValleyTimePrice()));
					psData.put("ServiceFee",String.valueOf(rate.getRaInServiceCharge()));
					break;
				default:
					psData.put("ElectricityFee", "");
					psData.put("ServiceFee", String.valueOf(rate.getRaInServiceCharge()));
				}
			} else  {
				String mark =RateinformationUtil.getCurrentPowerRateMark(JudgeNullUtils.nvlStr(rate.getRaInQuantumDate()));
				switch (mark) {
				case "1":
					psData.put("ElectricityFee",String.valueOf(rate.getRaInTipTimeTariff()));
					psData.put("ServiceFee", String.valueOf(rate.getRaInTipTimeTariffMoney()));
					break;
				case "2":
					psData.put("ElectricityFee",String.valueOf(rate.getRaInPeakElectricityPrice()));
					psData.put("ServiceFee",String.valueOf(rate.getRaInPeakElectricityMoney()));
					break;
				case "3":
			        psData.put("ElectricityFee",String.valueOf(rate.getRaInUsualPrice()));
					psData.put("ServiceFee", String.valueOf(rate.getRaInUsualMoney()));
					break;
				case "4":		
					psData.put("ElectricityFee",String.valueOf(rate.getRaInValleyTimePrice()));
					psData.put("ServiceFee", String.valueOf(rate.getRaInValleyTimeMoney()));
					break;
				default:
					psData.put("ElectricityFee", "");
					psData.put("ServiceFee", "");
				}
			}
			Map<String, Object> test = new HashMap<>();
			test.put("cpyId", tblPartner.getCpyId());
			test.put("stationNo", psModel.getPkPowerstation());
			List<TcbElectric> elcList = elcService.getElectricList(test);
			List<Map<String, Object>> elcDataList = new ArrayList<>();
			for (TcbElectric e : elcList) {
				elcData = new HashMap<>();
				elcData.put("EquipmentID", e.getEquipNo());
				elcData.put("ManufacturerID", "");
				elcData.put("ManufacturerName", "");
				elcData.put("EquipmentModel", e.getEquipNo());
				elcData.put("EquipmentName", e.getEquipName());
				elcData.put("ProductionDate", "");
				if ("14".equals(e.getEquipType()))
					elcData.put("EquipmentType", 2);
				if ("5".equals(e.getEquipType()))
					elcData.put("EquipmentType", 1);
				else
					elcData.put("EquipmentType", 3);
				if("0".equals(e.getEquipStatus()))
				    elcData.put("EquipmentStatus", 5);
				if("1".equals(e.getEquipStatus()))
					elcData.put("EquipmentStatus", 50);
				else
					elcData.put("EquipmentStatus", 0);
				String powerRating = e.getPowerRating();
				 String  substring= powerRating.substring(0,powerRating.length()-2);
				BigDecimal power = new BigDecimal(substring);
				elcData.put("Power",power.setScale(1, BigDecimal.ROUND_HALF_UP));
				String eLng = e.getElcLng();
				BigDecimal  eqLng= new BigDecimal (eLng);
				elcData.put("EquipmentLng", eqLng.setScale(6, BigDecimal.ROUND_HALF_UP));
				String eLat = e.getElcLng();
				BigDecimal  eqLat= new BigDecimal (eLat);
				elcData.put("EquipmentLat", eqLat.setScale(6, BigDecimal.ROUND_HALF_UP));
				TblElectricpilehead hModel = new TblElectricpilehead();
				hModel.setPkElectricpile(e.getPkElc());
				List<TblElectricpilehead> headList = hService.getList(hModel);
				List<Map<String, Object>> headDataList = new ArrayList<>();
				for (TblElectricpilehead h : headList) {
					hData = new HashMap<>();
					hData.put("ConnectorID",String.format("%s%02d", e.getEquipNo(),h.getEpheElectricpileHeadId()));
					hData.put("ConnectorName", h.getEpheElectricpileheadname());
					if ("14".equals(e.getEquipType()))
						hData.put("ConnectorType", 3);
					if ("5".equals(e.getEquipType()))
						hData.put("ConnectorType", 4);
					hData.put("VoltageUpperLimits",Integer.parseInt(e.getVoltageRated().split("\\.")[0]));
					hData.put("VoltageLowerLimits",Integer.parseInt(e.getVoltageRated().split("\\.")[0]) );
					hData.put("Current", Integer.parseInt(e.getCurrentRated().split("\\.")[0]));
					String powers  = e.getPowerRating();
		            String  substr= powers.substring(0,powers.length()-2);
					BigDecimal power1 = new BigDecimal (substr);
					hData.put("Power", power1.setScale(1, BigDecimal.ROUND_HALF_UP));
					hData.put("ParkNo", "");
					hData.put("NationalStandard", 2);
					headDataList.add(hData);
				}
				elcData.put("ConnectorInfos", headDataList);
				elcDataList.add(elcData);
				}					
			psData.put("EquipmentInfos", elcDataList);
			List<Map<String, Object>> list = new ArrayList<>();
			list.add(psData);
			Map<String, Object> data = new HashMap<>();
			data.put("StationInfo", list);
			String nariData = new JSONObject(data).toString();
			LOGGER.info("向南京南瑞推送电站的信息:{}",nariData);
			//电站信息变化推送URL
			String pushStationInfoUrl = tblPartner.getPushOrderCheckUrl();
			LOGGER.info("...........南京南瑞获取token-begin.........");
			Map<String, String> map  = commonService.getCecToken(operatorID);	
			String token = map.get("AccessToken");
			LOGGER.info("...........南京南瑞获取token-end.........");
			JSONObject jsonData = CecPost.HttpPost(pushStationInfoUrl, "321895837",token, tblPartner.getSigSecret(), 
					nariData,tblPartner.getAesSecret(), tblPartner.getAesIv());
			LOGGER.info("南京南瑞-充电站推送回执：{}",jsonData);
			int status = 1;
			if (jsonData != null ){
				status= Integer.parseInt(jsonData.getString("Status")); 
			}
			if(status == 1){
				return 1;
			}
			LOGGER.info("...南京南瑞充电站信息变化推送-end..........");
		} catch (Exception e) {
			LOGGER.info("...南京南瑞充电站信息变化推送失败..........");
			return 1;
		}
		return 0;
		
	}

	@Override
	public int alipayStationPush(String lastQueryTime) {
		try {
			LOGGER.info("...............支付宝充电站信息推送-begin，上次推送时间：{}",lastQueryTime);	
			//获取支付宝组织机构代码以及加密信息
	        String operatorID = MessageManager.getSystemProperties("alipay.operatorID");
			TblPartner tblPartner = partnerService.PartnerInfo(operatorID);
	        Map<String,Object> model = new HashMap<>();
	        model.put("queryTime", lastQueryTime);
	        model.put("cpyId", tblPartner.getCpyId());
	        List<TblPowerstation> psList = psService.getUpdatedList(model);
			if(psList.isEmpty()){
				LOGGER.error("......支付宝充电站信息推送失败:无相关的电站信息变化..........");
				return 1;
			}else{
				List<Map<String, Object>> psDataList = new ArrayList<>();
				Map<String, Object> psData = null;
				Map<String, Object> elcData = null;
				Map<String, Object> hData = null;
				TblElectricpile pile = new TblElectricpile();
				for(TblPowerstation psModel : psList){
					psData = new HashMap<>();
					psData.put("StationID", String.valueOf(psModel.getPkPowerstation()));
					LOGGER.info("......支付宝充电站信息推送-电站ID：{}{}",psModel.getPkPowerstation(),"......");
					psData.put("OperatorID", "321895837");
					psData.put("BusineHours", psModel.getPoStOnlineTime());
					psData.put("StationName", psModel.getPostName());
					psData.put("Address", psModel.getPostAddress());
					AreaMessage msg = new AreaMessage();
					msg.setAreaCode(psModel.getPostOwnCountyCode());
					AreaMessage areaData = amsgService.getAllMessage(msg);
					psData.put("adm1_chn", areaData.getProvinceName());
					psData.put("adm2_chn", areaData.getCityName());
					psData.put("adm3_chn", areaData.getAreaName());
					psData.put("StationLng", psModel.getPostLongitude());
					psData.put("StationLat", psModel.getPostLatitude());
					psData.put("AreaCode", psModel.getPostOwnCountyCode());
					psData.put("ServiceTel", psModel.getPostPhone());
					psData.put("StationTel", psModel.getPostPhone());
					psData.put("StationType", 255);
					if (psModel.getPostStatus() == 10)
						psData.put("StationStatus", 5);
					if (psModel.getPostStatus() == 15)
						psData.put("StationStatus", 50);
					else
						psData.put("StationStatus", 0);
					psData.put("ParkNums", 0);
					String postPic = psModel.getPostPic();
					postPic = postPic == null ? "" : postPic;
					psData.put("Pictures", postPic.split(","));
					psData.put("EquipmentOwnerID", "321895837");
					psData.put("CountryCode", "CN");
					psData.put("ParkInfo", "");
					pile.setElpiRelevancepowerstation(psModel.getPkPowerstation());
					List<TblElectricpile> pList = elcService.getElectricPileByPowerStationId(pile);
					pile = pList.get(0);
					Map<String, Object> rateParm = new HashMap<>();
					rateParm.put("pkRateinformation", pile.getElpiRateinformationid());
					TblRateInformation rate = rateService.getPriceById(rateParm);
					if (rate.getRaInServiceCharge() !=null || !rate.getRaInServiceCharge().equals(0)){
						String mark =RateinformationUtil.getCurrentPowerRateMark(JudgeNullUtils.nvlStr(rate.getRaInQuantumDate()));
						switch (mark) {
						case "1":
							psData.put("ElectricityFee",String.valueOf(rate.getRaInTipTimeTariff()));
							psData.put("ServiceFee",String.valueOf(rate.getRaInServiceCharge()));
							break;
						case "2":
							psData.put("ElectricityFee",String.valueOf(rate.getRaInPeakElectricityPrice()));
							psData.put("ServiceFee",String.valueOf(rate.getRaInServiceCharge()));
							break;
						case "3":
					        psData.put("ElectricityFee",String.valueOf(rate.getRaInUsualPrice()));
					    	psData.put("ServiceFee",String.valueOf(rate.getRaInServiceCharge()));
						case "4":		
							psData.put("ElectricityFee",String.valueOf(rate.getRaInValleyTimePrice()));
							psData.put("ServiceFee",String.valueOf(rate.getRaInServiceCharge()));
							break;
						default:
							psData.put("ElectricityFee", "");
							psData.put("ServiceFee", String.valueOf(rate.getRaInServiceCharge()));
						}
					} else  {
						String mark =RateinformationUtil.getCurrentPowerRateMark(JudgeNullUtils.nvlStr(rate.getRaInQuantumDate()));
						switch (mark) {
						case "1":
							psData.put("ElectricityFee",String.valueOf(rate.getRaInTipTimeTariff()));
							psData.put("ServiceFee", String.valueOf(rate.getRaInTipTimeTariffMoney()));
							break;
						case "2":
							psData.put("ElectricityFee",String.valueOf(rate.getRaInPeakElectricityPrice()));
							psData.put("ServiceFee",String.valueOf(rate.getRaInPeakElectricityMoney()));
							break;
						case "3":
					        psData.put("ElectricityFee",String.valueOf(rate.getRaInUsualPrice()));
							psData.put("ServiceFee", String.valueOf(rate.getRaInUsualMoney()));
							break;
						case "4":		
							psData.put("ElectricityFee",String.valueOf(rate.getRaInValleyTimePrice()));
							psData.put("ServiceFee", String.valueOf(rate.getRaInValleyTimeMoney()));
							break;
						default:
							psData.put("ElectricityFee", "");
							psData.put("ServiceFee", "");
						}
					}
					psData.put("ParkFee", "");
					Map<String, Object> test = new HashMap<>();
					test.put("cpyId",tblPartner.getCpyId());
					test.put("stationNo", psModel.getPkPowerstation());
					List<TcbElectric> elcList = elcService.getElectricList(test);
					List<Map<String, Object>> elcDataList = new ArrayList<>();
					for (TcbElectric e : elcList) {
						elcData = new HashMap<>();
						elcData.put("EquipmentID", e.getEquipNo());
						elcData.put("ManufacturerID","");
						elcData.put("EquipmentModel", e.getEquipmentModel());
						elcData.put("EquipmentName", e.getEquipName());
						if ("14".equals(e.getEquipType()))
							elcData.put("EquipmentType", 2);
						if ("5".equals(e.getEquipType()))
							elcData.put("EquipmentType", 1);
						else
							elcData.put("EquipmentType", 3);
					    String powerRating  = e.getPowerRating();
		                String  substring= powerRating.substring(0,powerRating.length()-2);
					    BigDecimal power = new BigDecimal (substring);
						elcData.put("Power", power.setScale(1, BigDecimal.ROUND_HALF_UP));
						TblElectricpilehead hModel = new TblElectricpilehead();
						hModel.setPkElectricpile(e.getPkElc());
						List<TblElectricpilehead> headList = hService.getList(hModel);
						List<Map<String, Object>> headDataList = new ArrayList<>();
						for (TblElectricpilehead h : headList) {
							hData = new HashMap<>();
							hData.put("ConnectorID",String.format("%s%02d", e.getEquipNo(),h.getEpheElectricpileHeadId()));
							if ("14".equals(e.getEquipType()))
								hData.put("ConnectorType", 3);
							if ("5".equals(e.getEquipType()))
								hData.put("ConnectorType", 4);
							hData.put("VoltageUpperLimits",Integer.parseInt(e.getVoltageRated().split("\\.")[0]));
							hData.put("VoltageLowerLimits",Integer.parseInt(e.getVoltageRated().split("\\.")[0]) );
							hData.put("Current", Integer.parseInt(e.getCurrentRated().split("\\.")[0]));
						    String powers  = e.getPowerRating();
				            String  substr= powers.substring(0,powers.length()-2);
							BigDecimal power1 = new BigDecimal (substr);
							hData.put("Power", power1.setScale(1, BigDecimal.ROUND_HALF_UP));
							hData.put("NationalStandard", 2);
							headDataList.add(hData);
						}
						elcData.put("ConnectorInfos", headDataList);
						elcDataList.add(elcData);
					}
					psData.put("EquipmentInfos", elcDataList);
					psDataList.add(psData);
				}
				Map<String, Object> data = new HashMap<>();
				data.put("StationInfos", psDataList);
				String  apData = new JSONObject(data).toString();
				LOGGER.info("......支付宝充电站信息推送-电站信息:{}",apData);
				//电站信息推送URL
				String stationPushUrl = tblPartner.getPushOrderCheckUrl();
				LOGGER.info("..............支付宝城市服务对接获取token-begin................................");
				Map<String, String> mmp = commonService.getCecToken(operatorID);	
				String token = mmp.get("AccessToken");
				LOGGER.info("..............支付宝城市服务对接获取token-end................................");
				JSONObject jsonData = CecPost.HttpPost(stationPushUrl, "321895837",token, tblPartner.getSigSecret(), 
						apData,tblPartner.getAesSecret(), tblPartner.getAesIv());
				LOGGER.info("支付宝城市服务-充电站推送回执：{}",jsonData);
				LOGGER.info("..............支付宝充电站信息推送--end........................");
				//支付宝充电站信息推送时间写入redis缓存
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				String now = fmt.format(date);
				String time = redisService.strGet(WanmaConstants.ALIPAY_ORDER_TIME);
				if(time ==null){
					redisService.strSet(WanmaConstants.ALIPAY_ORDER_TIME, now);
				}else{
					redisService.strRemove(WanmaConstants.ALIPAY_ORDER_TIME);
					redisService.strSet(WanmaConstants.ALIPAY_ORDER_TIME, now);
				}
			}
		} catch (Exception e) {
			LOGGER.error("支付宝电站信息推送失败");
			return 1;
		}
		return 0;
	}
	
	
}
