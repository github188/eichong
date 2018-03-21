package com.wanma.ims.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.wanma.ims.common.domain.*;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.mapper.OrderMapper;
import com.wanma.ims.mapper.RateInfoMapper;
import com.wanma.ims.mapper.UserMapper;
import com.wanma.ims.redis.RedisDataCenter;
import com.wanma.ims.service.ElectricPileService;
import com.wanma.ims.service.MultipartFileService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.ims.mapper.DataCenterMapper;
import com.wanma.ims.service.DataCenterService;

@Service
public class DataCenterServiceImpl implements DataCenterService {
	@Autowired
	private DataCenterMapper dataCenterMapper;
	@Autowired
	private RedisDataCenter redisDataCenter;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RateInfoMapper rateInfoMapper;
	@Autowired
	private ElectricPileService electricPileService;
	@Autowired
	private MultipartFileService multipartFileService;
	@Override
	public Map<String, Object> getHistoryDataForOrder(Map<String, String> params) {
		Map<String, Object> map;
		String key = "ims:history_data_order:"+"cpyId_"+params.get("cpyId")+":provinceCode_"+params.get("provinceCode")+":cityCode_"+params.get("cityCode");
		if (redisDataCenter.strGet(key)==null){
			map = dataCenterMapper.getHistoryDataForOrder(params);
			redisDataCenter.strSetWithTime(key,map,new Long(6), TimeUnit.HOURS);
			return map;
		}else {
			return (Map<String, Object>) redisDataCenter.strGet(key);
		}
	}
	@Override
	public Map<String, Object> getHistoryDataForElectric(Map<String, String> params) {
		Map<String, Object> map;
		String key = "ims:history_data_electric:"+"cpyId_"+params.get("cpyId")+":provinceCode_"+params.get("provinceCode")+":cityCode_"+params.get("cityCode");
		if (redisDataCenter.strGet(key)==null){
			map = getHistoryDataForElectricMap(params);
			redisDataCenter.strSetWithTime(key,map, (long) 6, TimeUnit.HOURS);
			return map;
		}else {
			return (Map<String, Object>) redisDataCenter.strGet(key);
		}
	}
	public  Map<String,Object> getHistoryDataForElectricMap(Map<String, String> params){
		Map<String, Object> map = new HashMap<>();
		String electircCount = dataCenterMapper.getElectircCount(params);
		String electircHeadCount = dataCenterMapper.getElectircHeadCount(params);
		String powerStationCount = dataCenterMapper.getPowerStationCount(params);
		String chargeCount = dataCenterMapper.getChargeCountByEp(params);
		map.put("electircCount", electircCount);
		map.put("electircHeadCount", electircHeadCount);
		map.put("powerStationCount", powerStationCount);
		map.put("chargeCount", chargeCount);
		return map;
	}
	@Override
	public List<Map<String, Object>> getSelectScope() {
		List<Map<String, Object>> returnList = new ArrayList<>();
		String key = "ims:dataCenter:selectScope:";
		if (redisDataCenter.strGet(key)==null){
			List<Map<String, String>> provinceScopeList = dataCenterMapper.getProvinceScope();
			for (int i = 0; i < provinceScopeList.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("value", provinceScopeList.get(i).get("provinceCode"));
				map.put("label", provinceScopeList.get(i).get("provinceName"));
				returnList.add(map);
			}
			List<Map<String, String>> cityList = dataCenterMapper.getCityScope();
			for (int i = 0; i < returnList.size(); i++) {
				List<Map<String, Object>> cpyChildren = new ArrayList<Map<String, Object>>();
				for (int j = 0; j < cityList.size(); j++) {
					if (returnList.get(i).get("value").equals(cityList.get(j).get("provinceCode"))) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("value", cityList.get(j).get("cityCode"));
						map.put("label", cityList.get(j).get("cityName"));
						List<Map<String, String>> cpyList = dataCenterMapper.getCpyList(cityList.get(j).get("cityCode"));
						map.put("children", cpyList);
						cpyChildren.add(map);
					}
				}
				returnList.get(i).put("children", cpyChildren);
			}
			redisDataCenter.strSetWithTime(key,returnList, (long) 6, TimeUnit.HOURS);
			return returnList;
		}else {
			return (List<Map<String, Object>>) redisDataCenter.strGet(key);
		}
	}
	@Override
	public Map<String, Object> getHistoryDataForUser(Map<String, String> params) {
		Map<String, Object> map;
		String key = "ims:history_data_User:"+"cpyId_"+params.get("cpyId")+":provinceCode_"+params.get("provinceCode")+":cityCode_"+params.get("cityCode");
		if (redisDataCenter.strGet(key)==null){
			map = getHistoryDataForUserMap(params);
			redisDataCenter.strSetWithTime(key,map,new Long(6), TimeUnit.HOURS);
			return map;
		}else {
			return (Map<String, Object>) redisDataCenter.strGet(key);
		}
	}
	public  Map<String,Object> getHistoryDataForUserMap(Map<String, String> params){
		Map<String, Object> map = new HashMap<>();
		String userNomalCount = dataCenterMapper.getUserNomalCount(params);
		String consumAmount = dataCenterMapper.getConsumAmount(params);
		String chargeCount = dataCenterMapper.getChargeCountByUser(params);
		map.put("userNomalCount", userNomalCount);
		map.put("consumAmount", consumAmount);
		map.put("chargeCount", chargeCount);
		return map;
	}
	@Override
	public List<Map<String, Object>> getMapDataForOrder(Map<String, Object> params) {
		List<Map<String, Object>> list;
		String key = "ims:map_data_order:"+"cpyId_"+params.get("cpyId")+":provinceCode_"+params.get("provinceCode")+":cityCode_"+params.get("cityCode");
		if (redisDataCenter.strGet(key)==null){
			list = dataCenterMapper.getMapDataForOrder(params);
			redisDataCenter.strSetWithTime(key,list, (long) 1, TimeUnit.MINUTES);
			return list;
		}else {
			return (List<Map<String, Object>>) redisDataCenter.strGet(key);
		}
	}

	@Override
	public List<Map<String, Object>> getMapDataForElectric(Map<String, Object> params) {
		List<Map<String, Object>> list;
		String key = "ims:map_data_electric:"+"cpyId_"+params.get("cpyId")+":provinceCode_"+params.get("provinceCode")+":cityCode_"+params.get("cityCode");
		if (redisDataCenter.strGet(key)==null){
			list = dataCenterMapper.getMapDataForElectric(params);
			redisDataCenter.strSetWithTime(key,list,new Long(1), TimeUnit.MINUTES);
			return list;
		}else {
			return (List<Map<String, Object>>) redisDataCenter.strGet(key);
		}
	}

	@Override
	public List<Map<String, Object>> getMapDataForUser(Map<String, Object> params) {
		List<Map<String, Object>> List;
		if ("".equals(params.get("provinceCode")) ||"".equals(params.get("cityCode"))){
			List = dataCenterMapper.getMapDataForUser(params);
		}else {
			List = dataCenterMapper.getMapDataForUserLevel3(params);
		}
		return List;
	}

	@Override
	public Map<String, Object> getHeadRealTimeInfo(Map<String, Object> myJsonObject, ElectricPileDO electricPileDO) {
		Map<String, Object> params = new HashMap<>();
		Map<String,String> order = new HashMap<>();
		if("3".equals(getStringValue("3_1",myJsonObject))){
			 order = dataCenterMapper.getOrderByPileId(electricPileDO.getId());
		}
			params.put("vin",getStringValue("4_1",myJsonObject));
		//电池类型
		Map<String, String> map = WanmaConstants.getBattBeryType();
		params.put("batteryType",myJsonObject.get("3_17")==null?"未知":map.get(myJsonObject.get("3_17")));
		//电池容量
		params.put("batterycapacity",getStringValue("3_18",myJsonObject));
		//订单编号
		params.put("chargeCode",order.get("code")==null?"":order.get("code"));
		//用户信息
		if (order.get("userId")!=null){
			params= getUserInfo(params, Long.valueOf(order.get("userId")));
		}
		//开始充电时间
		params.put("beginChargeTime",order.get("startTime")==null?"":order.get("startTime"));
		//预冻金额
		params.put("chargeFrozenAmt",order.get("frozenAmt")==null?"":order.get("frozenAmt"));
		//费率
		if (order.get("rateId")!=null){
			RateInfoDO rateInfoDO = new RateInfoDO();
			rateInfoDO.setPk_RateInformation(Long.valueOf(order.get("rateId")));
			rateInfoDO = rateInfoMapper.getRateInfoById(rateInfoDO);
			params.put("rateInfo",rateInfoDO);
			params.put("rateInfoId",order.get("rateId"));
		}else {
			params.put("rateInfo","");
			params.put("rateInfoId","");
		}

		//电桩部分
		params.put("powerHighestTemperature",getDoubleValue("3_26", myJsonObject, 10));
		params.put("chargeTime",getStringValue("3_6",myJsonObject));
		params.put("restTime",getStringValue("3_7",myJsonObject));
		//错误类型
		params= getErrorInfo(params,myJsonObject);
		//车辆部分
		params.put("soc",getStringValue("3_5", myJsonObject));
		params.put("carTotalVoltage",getDoubleValue("3_36",myJsonObject,10));
		params.put("bpHighestVoltage",getDoubleValue("3_33",myJsonObject,10));
		params.put("bpHighestTemperature",getDoubleValue("3_34",myJsonObject,10));
		params.put("bpLowestTemperature",getDoubleValue("3_35",myJsonObject,10));
		//电压表 电流表 电量表
		params.put("voltageValue",getDoubleValue("3_3",myJsonObject,10));
		params.put("currentValue",getDoubleValue("3_4",myJsonObject,10));
		params.put("presentChargeValue",getDoubleValue("4_1",myJsonObject,1000));
		//曲线图
		params.put("threePhaseVoltage", myJsonObject.get("threePhaseVoltage") == null ? "" :
				echarsFormat((List<Map<String, Object>>) myJsonObject.get("threePhaseVoltage"),"三项电压"));
		params.put("threePhaseCurrent",myJsonObject.get("threePhaseCurrent")==null?"":
				echarsFormat((List<Map<String, Object>>) myJsonObject.get("threePhaseCurrent"), "三项电流"));
		params.put("outputVoltage",myJsonObject.get("outputVoltage")==null?"":
				echarsFormat((List<Map<String, Object>>) myJsonObject.get("outputVoltage"), "输出电压"));
		params.put("outputCurrent",myJsonObject.get("outputCurrent")==null?"":
				echarsFormat((List<Map<String, Object>>) myJsonObject.get("outputCurrent"), "输出电流"));
		params.put("outputPower",myJsonObject.get("outputPower")==null?"":
				echarsFormat((List<Map<String, Object>>) myJsonObject.get("outputPower"), "输出功率"));
		params.put("temperature",myJsonObject.get("temperature")==null?"":
				echarsFormat((List<Map<String, Object>>) myJsonObject.get("temperature"), "输出温度"));
		return params;
	}

	@Override
	public Map<String, Object> getPowerStationPileHeadOnlineState(Map<String, Object> searchModel) {
		Map<String, Object> map = new HashMap<>();
		searchModel.put("headStatus",WanmaConstants.ELECTRICPILEHEAD_BATTERY);
		String chargingCount = dataCenterMapper.getOnlineStateCount(searchModel);
		searchModel.put("headStatus",WanmaConstants.ELECTRIC_PILE_HEAD_FREE);
		String freeCount = dataCenterMapper.getOnlineStateCount(searchModel);
		searchModel.put("headStatus",WanmaConstants.ELECTRICPILEHEAD_STOP);
		String errorCount = dataCenterMapper.getOnlineStateCount(searchModel);
		String disconnectCount = dataCenterMapper.getPileDisconnectCount(searchModel);
		map.put("chargingCount",chargingCount);
		map.put("freeCount",freeCount);
		map.put("errorCount",errorCount);
		map.put("disconnectCount",disconnectCount);
		return map;
	}

	@Override
	public Map<String, Object> getParkingLockState(Map<String, Object> searchModel) {
		Map<String, Object> map = new HashMap<>();
		searchModel.put("status",WanmaConstants.PARKING_LOCK_NORMAL);
		String nomalCount = dataCenterMapper.getParkingLockState(searchModel);
		searchModel.put("status",WanmaConstants.PARKING_LOCK_USING);
		String usingCount = dataCenterMapper.getParkingLockState(searchModel);
		searchModel.put("status",WanmaConstants.PARKING_LOCK_ERROR);
		String errorCount = dataCenterMapper.getParkingLockState(searchModel);
		map.put("nomalCount",nomalCount);
		map.put("usingCount",usingCount);
		map.put("errorCount",errorCount);
		return map;
	}

	@Override
	public Map<String, Object> getPowerStationHistoryData(Map<String, Object> searchModel) {
		Map<String, Object> map = new HashMap<>();
		//昨日
		searchModel.put("time",0);
		Map<String, Object> yesterdayMap = dataCenterMapper.getPowerStationOrderHistoryData(searchModel);
		map.put("yesterdayOrderCount",yesterdayMap.get("orderCount"));
		map.put("yesterdayChargeCount",yesterdayMap.get("chargeCount"));
		//上个月
		searchModel.put("time",1);
		Map<String, Object> lastMonthMap = dataCenterMapper.getPowerStationOrderHistoryData(searchModel);
		map.put("lastMonthOrderCount",lastMonthMap.get("orderCount"));
		map.put("lastMonthChargeCount",lastMonthMap.get("chargeCount"));
		//历史所有
		searchModel.put("time",2);
		Map<String, Object> allMap = dataCenterMapper.getPowerStationOrderHistoryData(searchModel);
		map.put("allCountOrderCount",allMap.get("orderCount"));
		map.put("allCountChargeCount",allMap.get("chargeCount"));

		String  epError = dataCenterMapper.getEpErrorCountByPsId(searchModel);
		map.put("epError",epError);
		String  orderError = dataCenterMapper.getOrderErrorCountByPsId(searchModel);
		map.put("orderError",orderError);
		return map;
	}

	@Override
	public List<Map<String, Object>> getStartChargeRecord(String key) {
		List<Map<String, Object>> record = new ArrayList();
		List subList = (List) redisDataCenter.listGetAll(key);
		for(int i=0;i<subList.size();i++){
			JSONObject jsonObject = JSONObject.fromObject(subList.get(i)) ;
			Map map = (Map) JSONObject.toBean(jsonObject,HashMap.class);
			record.add(map);
		}
		for (int i=0;i<record.size();i++){
			String userId = (String) record.get(i).get("userId");
			UserDO userDO =userMapper.selectUserByUserId(new Long(userId));
			String powerStationName = dataCenterMapper.getPowerStationNameByEpCode(record.get(i).get("epCode").toString());
			record.get(i).put("userAccount",userDO ==null?"":userDO.getUserAccount());
			record.get(i).put("powerStationName", powerStationName);
		}
		return record;
	}

	@Override
	public Map<String, Object> getChargePowerCurve(String key) {
		Map<String, Object> returnMap = new HashMap<>();
		List<Map<String, Object>> record = new ArrayList();
		List subList = (List) redisDataCenter.listGetAll(key);
		for(int i=0;i<subList.size();i++){
			JSONObject jsonObject = JSONObject.fromObject(subList.get(i)) ;
			Map map = (Map) JSONObject.toBean(jsonObject,HashMap.class);
			record.add(map);
		}

	if (null != record){
		returnMap=echarsFormat(record,"充电电量");
		List<String> time = (List<String>) returnMap.get("time");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		for (int i=0;i<time.size();i++){
			time.set(i, sdf.format(time.get(i)));
		}
		returnMap.put("time",time);
	}
		return returnMap;
	}

	@Override
	public Map<String, Object> getRealTimeDateForOrder(Map<String, String> params) {
		Map<String, Object> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("YYMMdd");
		String time = sdf.format(new Date());
		String key=null;
		if ("".equals(params.get("provinceCode"))&&"".equals(params.get("cpyId"))){
			key = "ims:today_charge_data_"+time;
		}else if ("".equals(params.get("provinceCode"))&&!"".equals(params.get("cpyId"))){
			key = "ims:today_charge_data_"+time+":cpyId_"+params.get("cpyId");
		}else if (!"".equals(params.get("provinceCode"))&&"".equals(params.get("cpyId"))){
			key = "ims:today_charge_data_"+time+":provinceCode_"+params.get("provinceCode");
		}else if (!"".equals(params.get("provinceCode"))&&!"".equals(params.get("cpyId"))){
			key = "ims:today_charge_data_"+time+":provinceCode_"+params.get("provinceCode")+":cpyId_"+params.get("cpyId");
		}
		if (redisDataCenter.strGet(key)==null){
			map = dataCenterMapper.getRealTimeDateForOrder(params);
			redisDataCenter.strSetWithTime(key,map,new Long(2),TimeUnit.MINUTES);
			return map;
		}else {
			return (Map<String, Object>) redisDataCenter.strGet(key);
		}
	}

	@Override
	public Map<String, Object> getRealTimeDateForElectric(Map<String, String> params) {
		Map<String, Object> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("YYMMdd");
		String time = sdf.format(new Date());
		String key=null;
		if ("".equals(params.get("provinceCode"))&&"".equals(params.get("cpyId"))){
			key = "ims:today_electric_data_"+time;
		}else if ("".equals(params.get("provinceCode"))&&!"".equals(params.get("cpyId"))){
			key = "ims:today_electric_data_"+time+":cpyId_"+params.get("cpyId");
		}else if (!"".equals(params.get("provinceCode"))&&"".equals(params.get("cpyId"))){
			key = "ims:today_electric_data_"+time+":provinceCode_"+params.get("provinceCode");
		}else if (!"".equals(params.get("provinceCode"))&&!"".equals(params.get("cpyId"))){
			key = "ims:today_electric_data_"+time+":provinceCode_"+params.get("provinceCode")+":cpyId_"+params.get("cpyId");
		}
		if (redisDataCenter.strGet(key)==null){
			params.put("headState","6");
			String realTimeChargeHead = dataCenterMapper.getElectircHeadCount(params);
			params.put("today","today");
			String chargeCount = dataCenterMapper.getChargeCountByEp(params);
			String errorCount = dataCenterMapper.getErrorCountToday(params);
			map.put("realTimeChargeHead",realTimeChargeHead);
			map.put("chargeCount",chargeCount);
			map.put("errorCount",errorCount);
			redisDataCenter.strSetWithTime(key,map,new Long(2),TimeUnit.MINUTES);
			return map;
		}else {
			return (Map<String, Object>) redisDataCenter.strGet(key);
		}
	}

	@Override
	public Map<String, Object> getRealTimeDateForUser(Map<String, String> params) {
		Map<String, Object> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("YYMMdd");
		String time = sdf.format(new Date());
		String key=null;
		if ("".equals(params.get("provinceCode"))){
			key = "ims:today_user_data_"+time;
		}else if (!"".equals(params.get("provinceCode"))&&"".equals(params.get("cityCode"))){
			key = "ims:today_user_data_"+time+":provinceCode_"+params.get("provinceCode");
		}else if (!"".equals(params.get("provinceCode"))&&!"".equals(params.get("cityCode"))){
			key = "ims:today_user_data_"+time+":provinceCode_"+params.get("provinceCode")+":cityCode_"+params.get("cityCode");
		}
		if (redisDataCenter.strGet(key)==null){
			map = dataCenterMapper.getRealTimeDateForUser(params);
			String newUser = dataCenterMapper.getNewUserToday(params);
			map.put("newUser", newUser);
			redisDataCenter.strSetWithTime(key, map, new Long(2), TimeUnit.MINUTES);
			return map;
		}else {
			return (Map<String, Object>) redisDataCenter.strGet(key);
		}
		}

	@Override
	public List<Map<String, Object>> getUserChargeRank(Map<String, String> params) {
		return dataCenterMapper.getUserChargeRank(params);
	}

	@Override
	public Map<String, Object> getChargeCount5Days(Map<String, String> params) {
		List<Map<String, Object>> list = dataCenterMapper.getChargeCount5Days(params);
		if (list !=null){
			return echarsFormat(list,"近5日充电量");
		}else {
			return null;
		}
	}

	@Override
	public Map<String, Object> getDetailStaticData(String epCode, UserDO login) {
		Map<String, Object> map = new HashMap<>();
		ElectricPileDO electricPileDO = new ElectricPileDO();
		Map<String, Object> basicMap = dataCenterMapper.getDetailStaticData(epCode);
		electricPileDO = electricPileService.getElectricPileById(new Long(basicMap.get("id").toString()), login);
		String referenceId="";
		if (electricPileDO.getTypeId()==1&&"7kw".equals(electricPileDO.getChPower())&&electricPileDO.getChargingMethod()==14){
			referenceId=WanmaConstants.EP_7KW_14_1;
		}else if (electricPileDO.getTypeId()==2&&"40kw".equals(electricPileDO.getChPower())&&electricPileDO.getChargingMethod()==14){
			referenceId=WanmaConstants.EP_40KW_14_2;
		}else if (electricPileDO.getTypeId()==1&&"40kw".equals(electricPileDO.getChPower())&&electricPileDO.getChargingMethod()==14){
			referenceId=WanmaConstants.EP_40KW_14_1;
		}else if (electricPileDO.getMuzzleNumber()==1&&"120kw".equals(electricPileDO.getChPower())&&electricPileDO.getChargingMethod()==5){
			referenceId=WanmaConstants.EP_120KW_5_SINGLEHEAD;
		}else if (electricPileDO.getMuzzleNumber()==2&&"120kw".equals(electricPileDO.getChPower())&&electricPileDO.getChargingMethod()==5){
			referenceId=WanmaConstants.EP_120KW_5_DOUBLEHEAD;
		}else if (electricPileDO.getMuzzleNumber()==2&&"160kw".equals(electricPileDO.getChPower())&&electricPileDO.getChargingMethod()==5){
			referenceId=WanmaConstants.EP_160KW_5_DOUBLEHEAD;
		}
		List<String> picImgList = multipartFileService.getAllMultiUrl(WanmaConstants.DATACENTER_EP_PIC, referenceId+"");
		if (picImgList.size()!=0){
			map.put("picImgList",picImgList.get(0));
		}
		map.put("epCode",electricPileDO.getCode());
		map.put("chargeMode",electricPileDO.getChargingMethod());
		map.put("headNum",electricPileDO.getMuzzleNumber());
		map.put("type",electricPileDO.getTypeId());
		map.put("pileMaker",electricPileDO.getPileMaker());
		map.put("power",electricPileDO.getChPower());
		map.put("creatDate",basicMap.get("creatDate"));
		return map;
	}

	public Map<String, Object> echarsFormat(List<Map<String, Object>> list,String legend){
		Map<String, Object> map = new HashMap<>();
		List<Object> time = new ArrayList<>();
		List<Object> value = new ArrayList<>();
		for (int i=0;i<list.size();i++){
			time.add(list.get(i).get("time"));
			value.add(list.get(i).get("value"));
		}
		map.put("legend",legend);
		map.put("time",time);
		map.put("value",value);
		return map;
	}

	public Map<String, Object> getUserInfo(Map<String, Object> params,Long userId){
		Map<String,Object> userInfoMap = dataCenterMapper.getUserInfoForRealTimeById(userId);
		params.put("userAccount",userInfoMap.get("userAccount"));
		Map<String,String> user = new HashMap<>();
		String cpyType = userInfoMap.get("cpyType").toString();
		if ("0".equals(cpyType)){//app用户
			user = dataCenterMapper.getNomalUserInfoById(userId);
		}else if ("1".equals(cpyType)){//渠道用户
			user = dataCenterMapper.getCpyUserInfoById(userId);
		}else {//单用卡用户
			user = dataCenterMapper.getCardUserInfoById(userId);
		}
		params.put("tradeType",user.get("tradeType")=="1"?"信用":"储值");
		List<Map<String,String>> list = dataCenterMapper.getUserCardForRealTime(user);
		String cardNumber = "";
		for (int i=0;i<list.size();i++){
			cardNumber += list.get(i).get("cardNumber")+" ";
		}
		params.put("cardNumber",cardNumber);
		return params;
	}

	public Map<String, Object> getErrorInfo(Map<String, Object> params,Map<String, Object> myJsonObject){
		String errorType = "";
		errorType += "1".equals(myJsonObject.get("1_6"))?"读卡器通讯故障 ":"";
		errorType += "1".equals(myJsonObject.get("1_7"))?"急停按钮动作故障 ":"";
		errorType += "1".equals(myJsonObject.get("1_8"))?"避雷器故障 ":"";
		errorType += "1".equals(myJsonObject.get("1_9"))?"绝缘检测故障 ":"";
		errorType += "1".equals(myJsonObject.get("1_12"))?"电度表异常 ":"";
		errorType += "1".equals(myJsonObject.get("1_17"))?"电池反接故障 ":"";
		errorType += "1".equals(myJsonObject.get("1_18"))?"烟雾报警故障 ":"";
		errorType += "1".equals(myJsonObject.get("1_19"))?"BMS通信异常 ":"";
		errorType += "1".equals(myJsonObject.get("1_20"))?"直流电度表异常故障 ":"";
		errorType += "1".equals(myJsonObject.get("1_21"))?"直流输出过流告警 ":"";
		errorType += "1".equals(myJsonObject.get("2_1"))?"交流输入电压过压 ":"";
		errorType += "2".equals(myJsonObject.get("2_1"))?"交流输入电压欠压 ":"";
		errorType += "1".equals(myJsonObject.get("2_2"))?"充电机过温故障 ":"";
		errorType += "1".equals(myJsonObject.get("2_3"))?"交流电流过负荷告警 ":"";
		errorType += "1".equals(myJsonObject.get("2_12"))?"输出连接器过温 ":"";
		errorType += "1".equals(myJsonObject.get("2_14"))?"整车蓄电池充电过流告警 ":"";
		switch (getStringValue("3_1",myJsonObject)){
			case "31":
				errorType +="欠压故障 ";
			case "32":
				errorType +="过压故障 ";
			case "33":
				errorType +="过电流故障 ";
			case "34":
				errorType +="防雷器故障 ";
			case "35":
				errorType +="电表故障 ";
			case "36":
				errorType +="接触器故障 ";
			case "37":
				errorType +="绝缘检查 ";
			case "38":
				errorType +="急停 ";
			default:break;
		}
		if ("1".equals(getStringValue("2_15", myJsonObject))){
			errorType += "直流母线输出过压";
		}else if ("2".equals(getStringValue("2_15",myJsonObject))){
			errorType += "直流母线输出欠压";
		}
		if ("1".equals(getStringValue("2_16",myJsonObject))){
			errorType += "BMS过压告警";
		}else if ("2".equals(getStringValue("2_16",myJsonObject))){
			errorType += "BMS欠压告警";
		}
		params.put("isError","有");
		params.put("errorType",errorType);
		return params;
	}
 	private String  getStringValue(String key, Map<String, Object> myJsonObject){
		return myJsonObject.get(key)==null?"":myJsonObject.get(key).toString();
	}
	private String  getDoubleValue(String key, Map<String, Object> myJsonObject,int size){
		if (myJsonObject.get(key)!=null){
			int value = Integer.valueOf(myJsonObject.get(key).toString()) ;
			Double dd;
			dd = (double) (value / size);
			return dd.toString();
		}else {
			return "";
		}
	}


}
