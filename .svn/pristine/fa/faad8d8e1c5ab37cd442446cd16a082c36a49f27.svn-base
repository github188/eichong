package com.wanma.controller.nari;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblPartner;
import com.wanma.model.TblPowerstation;
import com.wanma.model.TblRateInformation;
import com.wanma.model.TcbElectric;
import com.wanma.service.CmsRateInfoService;
import com.wanma.service.NariChargeOrderPushService;
import com.wanma.service.PileFilterService;
import com.wanma.service.TblChargingOrderService;
import com.wanma.service.TblElectricpileHeadService;
import com.wanma.service.TblElectricpileService;
import com.wanma.service.TblPowerstationService;
import com.wanma.service.TblRateInformationService;
import com.wanma.service.TblReconciliationService;
import com.wanma.service.TcbPartnerService;
import com.wanma.support.common.RedisService;
import com.wanma.support.common.ResultResponse;
import com.wanma.support.common.WanmaConstants;
import com.wanma.support.simple.JudgeNullUtils;
import com.wanma.support.utils.AesCBC;
import com.wanma.support.utils.JsonResult;
import com.wanma.support.utils.RateinformationUtil;
import com.wanma.support.utils.TokenUtil;

@Controller
@RequestMapping("/nari/v1.0.0")
public class NariControllerV1 {
	private static final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Logger LOGGER = LoggerFactory.getLogger(NariControllerV1.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private RedisService redisService;
	@Autowired
	private TblElectricpileHeadService hService;
	@Autowired
	private TblRateInformationService rateService;
	@Autowired
	private TblPowerstationService psService;
	@Autowired
	private TblElectricpileService elcService;
	@Autowired
	private PileFilterService pileFilterService;
	@Autowired
	private TblElectricpileHeadService headService;
	@Autowired
	private TblChargingOrderService ordService;
	@Autowired
	private TblReconciliationService recobcilicationService;
	@Autowired
	private CmsRateInfoService rateInfoService;
	@Autowired
	private NariChargeOrderPushService nariPushService;
	@Autowired
	private TcbPartnerService partnerService;

	/**
	 * 1:获取Token
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/query_token",produces="application/json;charset=utf-8")
	@ResponseBody
	public String query_token(@RequestBody Map<String, String> params)
			throws Exception {
		String dataStr = params.get("Data");
		String OperatorID = params.get("OperatorID");
		LOGGER.info("~~~~~~~~~~~~~~~~~~~~请求Data："+dataStr+"~~~~~~~~~~~~~~~~~~~~");
		TblPartner tblPartner = partnerService.PartnerInfo(OperatorID);
		JSONObject reqData = JSON.parseObject(AesCBC.getInstance().decrypt(
				dataStr, "utf-8", tblPartner.getAesSecret(), tblPartner.getAesIv()));
		if (reqData == null) {
			return JsonResult.handleResult(JsonResult.RESULT_Post, JsonResult.MSG_Null, "","","").toString();
		}
		String org = reqData.get("OperatorID").toString();
		String secret = reqData.get("OperatorSecret").toString();
		if (StringUtils.isBlank(org) || StringUtils.isBlank(secret)) {
			return JsonResult.handleResult(JsonResult.RESULT_Null, JsonResult.MSG_Null, "","","").toString();
		}	
		Map<String, Object> data = new HashMap<String, Object>();
		if (!tblPartner.getWmTokenSecret().equals(secret)) {
			data.put("operatorID", org);
			data.put("succStat", 1);
			data.put("accessToken", "");
			data.put("tokenAvailableTime", 0);
			data.put("failReason", 2);
			data.put("accessToken", "");
			String date = AesCBC.getInstance().encrypt(
					new JSONObject(data).toString(), "utf-8", tblPartner.getAesSecret(), tblPartner.getAesIv());
			String key = JsonResult.RESULT_OK+JsonResult.MSG_Ok+ date;
			return JsonResult.handleResult(JsonResult.RESULT_Key, JsonResult.MSG_Key, date,tblPartner.getSigSecret(),key).toString();
		}
		String tkVal = redisService.strGet(WanmaConstants.PREFIX_TOKWEN + org);
		String t = "";
		if (tkVal != null) {
			long termT = System.currentTimeMillis()
					- Long.valueOf(tkVal.split(",")[0]);
			if (termT > WanmaConstants.PREFIX_TOKWEN_TERM) {
				t = TokenUtil.makeToken(org, secret);
				redisService.strSet(WanmaConstants.PREFIX_TOKWEN + org,
						System.currentTimeMillis() + "," + t);
			} else {
				t = tkVal.split(",")[1];
			}
		} else {
			t = TokenUtil.makeToken(org, secret);
			redisService.strSet(WanmaConstants.PREFIX_TOKWEN + org,
					System.currentTimeMillis() + "," + t);
		}
		data.put("OperatorID", org);
		data.put("SuccStat", 0);
		data.put("AccessToken", t);
		data.put("TokenAvailableTime", WanmaConstants.PREFIX_TOKWEN_TERM);
		data.put("FailReason", 0);
		String date = AesCBC.getInstance().encrypt(
				new JSONObject(data).toString(), "utf-8",  tblPartner.getAesSecret(), tblPartner.getAesIv());
		String key = JsonResult.RESULT_OK+JsonResult.MSG_Ok+ date;
		return JsonResult.handleResult(JsonResult.RESULT_OK, JsonResult.MSG_Ok, date,key,tblPartner.getSigSecret());
	
	}

	/**
	 * @Description: 查询设备状态
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/query_station_status",produces="application/json;charset=utf-8")
	@ResponseBody
	public String stationStatus(@RequestBody Map<String, String> params)
			throws Exception {
		LOGGER.info("================查询设备状态begin==================");
		String OperatorID = params.get("OperatorID");
		TblPartner tblPartner = partnerService.PartnerInfo(OperatorID);
		JSONObject jsonData = JSON.parseObject(AesCBC.getInstance().decrypt(
				params.get("Data"), "utf-8", tblPartner.getAesSecret(), tblPartner.getAesIv()));
		String ids = jsonData.get("StationIDs").toString();
		@SuppressWarnings("deprecation")
		List<String> stationIDs = JSONArray.toList(JSONArray.fromObject(ids));
		int count = stationIDs.size();
		if (count > 50) {
			@SuppressWarnings("rawtypes")
			ResultResponse resultRespone = new ResultResponse();
			resultRespone.setStatus(500);
			resultRespone.setMsg("数组长度超长！");
			return resultRespone.toString();
		}
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Map<String, Object> psData = null;
		for (String psId : stationIDs) {
			psData = new HashMap<String, Object>();
			psData.put("StationID", psId);
			TblElectricpilehead hModel = new TblElectricpilehead();
			hModel.setPsId(Integer.parseInt(psId));
			hModel.setCpyId(tblPartner.getCpyId());
			List<Map<String, Object>> maps=new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> mapList = hService.NariGetHeadStsByPsId(hModel);
			for (Map<String, Object> stringObjectMap : mapList) {
				Map<String, Object>test =new HashMap<String, Object>();
				if ("0".equals(stringObjectMap.get("Status").toString())){
					test.put("Status", 0);
				}else if ("1".equals(stringObjectMap.get("Status").toString())) {
					test.put("Status", 1);
				} else if ("3".equals(stringObjectMap.get("Status").toString())) {
					test.put("Status", 3);
				} else if ("2".equals(stringObjectMap.get("Status").toString())) {
					test.put("Status", 2);
				} else if ("4".equals(stringObjectMap.get("Status").toString())) {
					test.put("Status", 4);
				} else {
					test.put("Status", 255);	
				}
				test.put("ConnectorID", stringObjectMap.get("ConnectorID").toString());
				test.put("CurrentA", stringObjectMap.get("CurrentA").toString());
				test.put("CurrentB", stringObjectMap.get("CurrentB").toString());
				test.put("CurrentC", stringObjectMap.get("CurrentC").toString());
				test.put("VoltageA", stringObjectMap.get("VoltageA").toString());
				test.put("VoltageB", stringObjectMap.get("VoltageB").toString());
				test.put("VoltageC", stringObjectMap.get("VoltageC").toString());
				test.put("SOC",String.format("%.1f", 0.0));
				test.put("ParkStatus", stringObjectMap.get("ParkStatus").toString());
				test.put("LockStatus", stringObjectMap.get("LockStatus").toString());		
				test.put("Begin_time", "");
				test.put("Current_kwh", String.format("%.1f", 0.0));
				test.put("Current_meter", String.format("%.1f", 0.0));
				test.put("Car_num", "");
				test.put("Bat_voltage", String.format("%.2f",0.00));
				test.put("Max_voltage_no", 0);
				test.put("Max_voltage", String.format("%.3f", 0.000));
				test.put("Max_temp_no", 0);
				test.put("Max_temp", String.format("%.1f", 0.0));
				test.put("Bms_req_voltage", String.format("%.2f", 0.00));
				test.put("Bms_req_current", String.format("%.2f", 0.00));
				test.put("Max_allowed_voltage", String.format("%.2f", 0.00));
				test.put("Max_allowed_current", String.format("%.2f", 0.00));
				maps.add(test);
			}
			psData.put("ConnectorStatusInfos", maps);
			data.add(psData);
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("Total", stationIDs.size());
		returnMap.put("StationStatusInfos", data);
		LOGGER.info("================查询设备状态end==================");
		String date = AesCBC.getInstance().encrypt(
				new JSONObject(returnMap).toString(), "utf-8", tblPartner.getAesSecret(), tblPartner.getAesIv());
		String key = JsonResult.RESULT_OK+JsonResult.MSG_Ok+ date;
		return  JsonResult.handleResult(JsonResult.RESULT_OK, JsonResult.MSG_Ok, date,key,tblPartner.getSigSecret()).toString();
	}

	/**
	 * @Description: 查询电站信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/query_stations_info")
	@ResponseBody
	public String stationsInfo(@RequestBody Map<String, String> params)
			throws Exception {
		LOGGER.info("================查询电站信息begin==================");
		String OperatorID = params.get("OperatorID");
		TblPartner tblPartner = partnerService.PartnerInfo(OperatorID);
		JSONObject jsonData = JSON.parseObject(AesCBC.getInstance().decrypt(
				params.get("Data"), "utf-8",  tblPartner.getAesSecret(), tblPartner.getAesIv()));
		String lastQueryTime = jsonData.getString("LastQueryTime");
		String pageNo = jsonData.get("PageNo").toString();
		String pageSize = jsonData.get("PageSize").toString();
		if (StringUtils.isBlank(pageNo)){
			pageNo = "1";
		}
		if (StringUtils.isBlank(pageSize)){
			pageSize = "10";
		}
		List<TblPowerstation> psList = null;
		Map<String, Object> data = new HashMap<String, Object>();
		int count = 0;
		Map<String, Object> Map = new HashMap<String, Object>();
		Map.put("pageNo", Integer.parseInt(pageNo) - 1);
		Map.put("pageSize", Integer.parseInt(pageSize));
		Map.put("cpyId", tblPartner.getCpyId());
		if (lastQueryTime != null && !lastQueryTime.isEmpty()) {
			LOGGER.info("获取最近更新站点信息开始，上次查询时间：" + lastQueryTime + "开始页" + pageNo + "；每页显示数量：" + pageSize);
			Date queryTime = fmt.parse(lastQueryTime);
			Map.put("queryTime", queryTime);
			count = psService.getUpdatedCount(Map);
			psList = psService.getUpdatedList(Map);
			LOGGER.info("获取最近更新站点信息结束");
		} else {
			LOGGER.info("获取全部站点信息开始");
			count = psService.getPowerStationCount(Map);
			psList = psService.getPowerStationList(Map);
			LOGGER.info("获取全部站点信息结束");
		}
		data.put("ItemSize", count);
		data.put("PageCount", count / Integer.parseInt(pageSize) + 1);
		data.put("PageNo", pageNo);
		List<Map<String, Object>> psDataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> psData = null;
		Map<String, Object> elcData = null;
		Map<String, Object> hData = null;
		TblElectricpile pile = new TblElectricpile();
		for (TblPowerstation psModel : psList) {
			psData = new HashMap<String, Object>();
			psData.put("StationID", String.valueOf(psModel.getPkPowerstation()));
			psData.put("OperatorID", "321895837");
			psData.put("EquipmentOwnerID", "321895837");
			psData.put("StationName", psModel.getPostName());
			psData.put("CountryCode", "CN");
			psData.put("AreaCode", psModel.getPostOwnCountyCode());
			psData.put("Address", psModel.getPostAddress());
			psData.put("StationTel", psModel.getPostPhone());
			psData.put("ServiceTel", psModel.getPostPhone());
			psData.put("StationType", 255);
			if (psModel.getPostStatus() == 10)
				psData.put("StationStatus", 5);
			if (psModel.getPostStatus() == 15)
				psData.put("StationStatus", 50);
			else
				psData.put("StationStatus", 0);
			psData.put("ParkNums", 0);
			psData.put("StationLng", psModel.getPostLongitude());
			psData.put("StationLat", psModel.getPostLatitude());
			psData.put("SiteGuide", "");
			psData.put("Construction", 255);
			String postPic = psModel.getPostPic();
			postPic = postPic == null ? "" : postPic;
			psData.put("Pictures", postPic.split(","));
			psData.put("MatchCars", "");
			psData.put("ParkInfo", "");
			psData.put("ParkOwner", "");
			psData.put("ParkManager", "");
			psData.put("OpenAllDay", 1);
			psData.put("BusineHours", psModel.getPoStOnlineTime());
			psData.put("MinElectricityPrice", rateInfoService.selectMinPriceByPsId(psModel.getPkPowerstation()));
			psData.put("ParkFree", "");
			psData.put("ParkFee", "");
			psData.put("SupportOrder", 0);
			psData.put("Remark", "");
			pile.setElpiRelevancepowerstation(psModel.getPkPowerstation());
			List<TblElectricpile> pList = elcService.getElectricPileByPowerStationId(pile);
			pile = pList.get(0);
			Map<String, Object> rateParm = new HashMap<String, Object>();
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
			Map<String, Object> test = new HashMap<String, Object>();
			test.put("cpyId", tblPartner.getCpyId());
			test.put("stationNo", psModel.getPkPowerstation());
			List<TcbElectric> elcList = elcService.getElectricList(test);
			List<Map<String, Object>> elcDataList = new ArrayList<Map<String, Object>>();
			for (TcbElectric e : elcList) {
				elcData = new HashMap<>();
				elcData.put("EquipmentID", e.getEquipNo());
				elcData.put("ManufacturerID", "");
				elcData.put("ManufacturerName","");
				elcData.put("EquipmentModel", e.getEquipmentModel());
				elcData.put("EquipmentName", e.getEquipName());
				elcData.put("ProductionDate", "");
				if ("14".equals(e.getEquipType())){
					elcData.put("EquipmentType", 2);
				}else if ("5".equals(e.getEquipType())){
					elcData.put("EquipmentType", 1);
				}else{
					elcData.put("EquipmentType", 5);
				}
				if("0".equals(e.getEquipStatus()))
				    elcData.put("EquipmentStatus", 5);
				if("1".equals(e.getEquipStatus()))
					elcData.put("EquipmentStatus", 50);
				else
					elcData.put("EquipmentStatus", 0);
		        String powerRating  = e.getPowerRating();
                String  substring= powerRating.substring(0,powerRating.length()-2);
			    BigDecimal power = new BigDecimal (substring);
				elcData.put("Power", power.setScale(1, BigDecimal.ROUND_HALF_UP));
				elcData.put("EquipmentLng", Double.valueOf(Double.parseDouble(e.getElcLng())));
				elcData.put("EquipmentLat", Double.valueOf(Double.parseDouble(e.getElcLat())));
				TblElectricpilehead hModel = new TblElectricpilehead();
				hModel.setPkElectricpile(e.getPkElc());
				List<TblElectricpilehead> headList = hService.getList(hModel);
				List<Map<String, Object>> headDataList = new ArrayList<Map<String, Object>>();
				for (TblElectricpilehead h : headList) {
					hData = new HashMap<String, Object>();
					hData.put("ConnectorID",String.format("%s%02d", e.getEquipNo(),h.getEpheElectricpileHeadId()));
					hData.put("ConnectorName", h.getEpheElectricpileheadname());
					if ("14".equals(e.getEquipType())){
						hData.put("ConnectorType", 3);
					}else if ("5".equals(e.getEquipType())){
						hData.put("ConnectorType", 4);
					}
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
			psDataList.add(psData);
		}
		data.put("StationInfos", psDataList);
		LOGGER.info("================查询电站信息end==================");
		String date = AesCBC.getInstance().encrypt(
				new JSONObject(data).toString(), "utf-8", tblPartner.getAesSecret(), tblPartner.getAesIv());
		String key = JsonResult.RESULT_OK+JsonResult.MSG_Ok+ date;
		return  JsonResult.handleResult(JsonResult.RESULT_OK, JsonResult.MSG_Ok, date,key,tblPartner.getSigSecret()).toString();
	}

	
	/**
     * 查询统计信息
     * @param params
     * @return
     * @throws Exception
     */
	@ResponseBody
    @RequestMapping("/query_station_stats")
    public String getStationStats(@RequestBody Map<String, String> params)throws Exception {
    	LOGGER.info(".................查询统计信息-begin.......................");
		String OperatorID = params.get("OperatorID");
		TblPartner tblPartner = partnerService.PartnerInfo(OperatorID);
		JSONObject jsonData = JSON.parseObject(AesCBC.getInstance().decrypt(params.get("Data"),"utf-8", tblPartner.getAesSecret(), tblPartner.getAesIv()));
		String stationId = jsonData.getString("StationID");
    	String startTime = jsonData.getString("StartTime");
    	String endTime = jsonData.getString("EndTime");
    	if(StringUtils.isBlank(stationId) || StringUtils.isBlank(startTime) 
    			|| StringUtils.isBlank(endTime) ){
    		return JsonResult.handleResult(JsonResult.RESULT_Null, JsonResult.MSG_Null, "","", "").toString();
    	}  
    	LOGGER.info(".................检验是否是白名单-begin.......................");
    	Map<String,Object> mapl = new HashMap<String,Object>();
    	mapl.put("OperatorID", OperatorID);
    	mapl.put("stationId", stationId);
    	int count = elcService.checkPowerStation(mapl);
    	if(count == 0){
    		return JsonResult.handleResult(JsonResult.RESULT_Data_Error,JsonResult.MSG_No_StationID,"","","").toString();
    	}
    	LOGGER.info(".................检验是否是白名单-end.......................");
    	//把endTime增加1天
    	Date date=sdf.parse(endTime);
    	Calendar cld=Calendar.getInstance();
    	cld.setTime(date);
    	cld.add(Calendar.DATE, 1);
    	String endTimes= sdf.format(cld.getTime());
    	//获取充电站统计信息
    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("stationId", stationId);
    	model.put("startTime", startTime);
    	model.put("endTime", endTimes); 	
    	//电站累计电量
    	String stationEle = null ;
    	List<Map<String,Object>> eleList = new ArrayList<Map<String,Object>>();
    	try {
    		stationEle = elcService.getStationMeterNum(model);
    		eleList = elcService.getEleMeterNum(model);
		} catch (Exception e) {
			return JsonResult.handleResult(JsonResult.RESULT_Data_Error,JsonResult.MSG_No_StationID,"","","").toString();
		}
    	//查询结果为空
    	if(StringUtils.isBlank(stationEle) || eleList.size() == 0){
    		LOGGER.info("...........该电站在该时期内没有相应数据...................");
    		Map<String,Object> stationInfo = new HashMap<String,Object>();
    		stationInfo.put("StationID", stationId);
        	stationInfo.put("StartTime", startTime);
        	stationInfo.put("EndTime", endTime);
        	stationInfo.put("StationElectricity", 0);  
        	stationInfo.put("EquipmentStatsInfos", null);
        	Map<String, Object> data = new HashMap<String,Object>();
        	data.put("StationStats", stationInfo);
        	LOGGER.info("..................查询统计信息-end...................");
        	//数据加密
        	String datas = AesCBC.getInstance().encrypt(new JSONObject(data).toString(), "utf-8", tblPartner.getAesSecret(), tblPartner.getAesIv());
        	String key = JsonResult.RESULT_OK+JsonResult.MSG_Ok+ datas;
        	return  JsonResult.handleResult(JsonResult.RESULT_OK, JsonResult.MSG_Ok, datas,key,tblPartner.getSigSecret()).toString();			
    	} 
    	//查询结果不为空
    	BigDecimal stationElectricity = new BigDecimal(stationEle);
    	//充电站统计信息
    	Map<String,Object> stationInfo = new HashMap<String,Object>();
    	stationInfo.put("StationID", stationId);
    	stationInfo.put("StartTime", startTime);
    	stationInfo.put("EndTime", endTime);
    	stationInfo.put("StationElectricity", stationElectricity.setScale(1, BigDecimal.ROUND_HALF_UP));  	
    	//充电设备统计信息
    	List<Map<String, Object>> eleInfo = new ArrayList<Map<String,Object>>();   	
    	for(int i=0;i<eleList.size();i++){
    		Map<String,Object>  eleMap = eleList.get(i);
    		String epCode = eleMap.get("epCode").toString();
    		String eleMeter = eleMap.get("eleMeter").toString();  
    		BigDecimal eqElectricity = new BigDecimal(eleMeter);
        	Map<String,Object> eleData = new HashMap<String,Object>();
        	eleData.put("EquipmentID", epCode);
    		eleData.put("EquipmentElectricity", eqElectricity.setScale(1, BigDecimal.ROUND_HALF_UP));
    		List<Map<String, Object>> headList = new ArrayList<Map<String,Object>>(); 
    		Map<String,Object> hmap = new HashMap<String,Object>();
    		hmap.put("epCode", epCode);
    		hmap.put("startTime", startTime);
    		hmap.put("endTime", endTimes); 
    		headList = elcService.getHeadMeterNum(hmap);
    		//充电设备接口统计信息
    		Map<String,Object> ehData = new HashMap<String,Object>();
    		List<Map<String, Object>> ehInfo = new ArrayList<Map<String,Object>>();
    		for (int j = 0; j < headList.size(); j++) {
				Map<String, Object> headMap = headList.get(j);
				int headId = Integer.parseInt( headMap.get("headId").toString());
				String headMeter = headMap.get("headMeter").toString();				
				String ConnectorID =null;
	    		if(headId < 10){
	    			ConnectorID = epCode + "0" +headId;
	    		}else{
	    			ConnectorID = epCode + headId;
	    		}
	    		ehData.put("ConnectorID", ConnectorID);
        		BigDecimal gm = new BigDecimal(headMeter);
        		ehData.put("ConnectorElectricity", gm.setScale(1, BigDecimal.ROUND_HALF_UP));
        		ehInfo.add(ehData);
			}
        	eleData.put("ConnectorStatsInfos",ehInfo);
        	eleInfo.add(eleData);
    	}
    	stationInfo.put("EquipmentStatsInfos", eleInfo);
    	Map<String, Object> data = new HashMap<String,Object>();
    	data.put("StationStats", stationInfo);
    	LOGGER.info("..................查询统计信息-end...................");
    	//数据加密
    	String datas = AesCBC.getInstance().encrypt(new JSONObject(data).toString(), "utf-8",  tblPartner.getAesSecret(), tblPartner.getAesIv());
    	String key = JsonResult.RESULT_OK+JsonResult.MSG_Ok+ datas;
    	return  JsonResult.handleResult(JsonResult.RESULT_OK, JsonResult.MSG_Ok, datas,key,tblPartner.getSigSecret()).toString();			
	}
	
	/**
	 * 查询订单信息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/query_order_info")
	public String getOrderInfo(@RequestBody Map<String, String> params)throws Exception {
		LOGGER.info("....................查询订单信息（南瑞）-begin.......................");
		String OperatorID = params.get("OperatorID");
		TblPartner tblPartner = partnerService.PartnerInfo(OperatorID);
		JSONObject jsonData = JSON.parseObject(AesCBC.getInstance().decrypt(params.get("Data"),"utf-8",tblPartner.getAesSecret(), tblPartner.getAesIv()));
		String queryStartTime = jsonData.getString("QueryStartTime");
		String queryEndTime =  jsonData.getString("QueryEndTime");
    	//获取充电订单信息
    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("startTime", queryStartTime);
    	model.put("endTime", queryEndTime); 
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	try {
			list = ordService.getNariChargeOrder(model);
		} catch (Exception e) {
			return JsonResult.handleResult(JsonResult.RESULT_Data_Error,JsonResult.MSG_No_ConnectorID,"","","").toString();
		}
    	//查询结果为空
    	if(list.size() == 0){
    		LOGGER.info("...........该时期内没有相应数据...................");	
        	Map<String, Object> data = new HashMap<String,Object>();
        	data.put("OrderInfos", null);
        	LOGGER.info(".................查询订单信息（南瑞）-end...................");
        	//数据加密
        	String datas = AesCBC.getInstance().encrypt(new JSONObject(data).toString(), "utf-8",tblPartner.getAesSecret(), tblPartner.getAesIv());
        	String key = JsonResult.RESULT_OK+JsonResult.MSG_Ok+ datas;
        	return  JsonResult.handleResult(JsonResult.RESULT_OK, JsonResult.MSG_Ok, datas,key,tblPartner.getSigSecret()).toString();			
    	} 
    	List<Map<String,Object>> nrList = new ArrayList<Map<String,Object>>();
    	for (int i = 0; i < list.size(); i++) {
			Map<String,Object> map = list.get(i);
			 //桩体编码
			String epCode = map.get("epCode").toString();
			//枪头编号
			int ehId = Integer.parseInt(map.get("ehId").toString());
			//充电设备接口编码--桩体编码   + 枪头编号（两位）
			String ConnectorID = null;
			if(ehId < 10){
				ConnectorID = epCode + "0" +ehId;
			}else{
				epCode = epCode + ehId;
			}
			String startChargeSeq = map.get("startChargeSeq").toString();
			String userPhone = map.get("userPhone").toString();
			//累计> 充电量、电费、服务费、总金额
			String totalPower = map.get("totalPower").toString();
			String elecMoney = map.get("elecMoney").toString();
			String serviceMoney = map.get("serviceMoney").toString();
			String totalMoney = map.get("totalMoney").toString();
			//尖峰平谷各时段电量
			String cuspElect = map.get("cuspElect").toString();
			String peakElect = map.get("peakElect").toString();
			String flatElect = map.get("flatElect").toString();
			String valleyElect = map.get("valleyElect").toString();
			//充电开始、结束时间
			String startTime = map.get("startTime").toString();
			String endTime = map.get("endTime").toString();
			Map<String,Object> nr = new HashMap<String,Object>();
			nr.put("OperatorID", "321895837");
			nr.put("ConnectorID", ConnectorID);
			nr.put("StartChargeSeq", startChargeSeq);
			nr.put("UserChargeType", 3);
			if("0".equals(userPhone)){
				nr.put("MobileNumber", null);
			}else{
				nr.put("MobileNumber", userPhone);
			}
			nr.put("Money", new BigDecimal(totalMoney));
			nr.put("ElectMoney", new BigDecimal(elecMoney));
			nr.put("ServiceMoney", new BigDecimal(serviceMoney));
			BigDecimal elect = new BigDecimal(totalPower);
			nr.put("Elect", elect.setScale(1,BigDecimal.ROUND_HALF_UP));
			//尖阶段信息
			BigDecimal cuspEle = new BigDecimal(cuspElect);
			nr.put("CuspElect",cuspEle.setScale(1,BigDecimal.ROUND_HALF_UP));
			nr.put("CuspElectPrice", 0);
			nr.put("CuspServicePrice", 0);
			nr.put("CuspMoney", 0);
			nr.put("CuspElectMoney", 0);
			nr.put("CuspServiceMoney", 0);
			//峰阶段信息
			BigDecimal peakEle = new BigDecimal(peakElect);
			nr.put("PeakElect", peakEle.setScale(1,BigDecimal.ROUND_HALF_UP));
			nr.put("PeakElectPrice", 0);
			nr.put("PeakServicePrice", 0);
			nr.put("PeakMoney", 0);
			nr.put("PeakElectMoney", 0);
			nr.put("PeakServiceMoney", 0);
			//平阶段信息
			BigDecimal flatEle = new BigDecimal(flatElect);
			nr.put("FlatElect", flatEle.setScale(1,BigDecimal.ROUND_HALF_UP));
			nr.put("FlatElectPrice", 0);
			nr.put("FlatServicePrice", 0);
			nr.put("FlatMoney", 0);
			nr.put("FlatElectMoney", 0);
			nr.put("FlatServiceMoney", 0);
			//谷阶段信息
			BigDecimal valleyEle = new BigDecimal(valleyElect);
			nr.put("ValleyElect", valleyEle.setScale(1,BigDecimal.ROUND_HALF_UP));
			nr.put("ValleyElectPrice", 0);
			nr.put("ValleyServicePrice", 0);
			nr.put("ValleyMoney", 0);
			nr.put("ValleyElectMoney", 0);
			nr.put("ValleyServiceMoney", 0);
			nr.put("StartTime", startTime);
			nr.put("EndTime", endTime);
			nr.put("PaymentAmount", 0);
			nr.put("PayTime", null);
			nr.put("DiscountInfo", null);
			nrList.add(nr);
		}
    	Map<String,Object> data = new HashMap<String,Object>();
    	data.put("OrderInfos", nrList);
    	LOGGER.info("....................查询订单信息（南瑞）-end.......................");
    	//数据加密
    	String datas = AesCBC.getInstance().encrypt(new JSONObject(data).toString(), "utf-8", tblPartner.getAesSecret(), tblPartner.getAesIv());
    	String key = JsonResult.RESULT_OK+JsonResult.MSG_Ok+ datas;
    	return  JsonResult.handleResult(JsonResult.RESULT_OK, JsonResult.MSG_Ok, datas,key,tblPartner.getSigSecret()).toString();	
	}	

	
}
