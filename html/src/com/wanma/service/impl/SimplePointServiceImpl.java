package com.wanma.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wanma.controller.simple.SimpleChargeController;
import com.wanma.model.simple.PowerStation;
import com.wanma.model.simple.TblChargePile;
import com.wanma.service.ChargePileService;
import com.wanma.service.PowerStationService;
import com.wanma.service.SimplePointService;
import com.wanma.support.common.AccessSuccessResult;
import com.wanma.support.common.MessageManager;
import com.wanma.support.simple.CommonConsts;
import com.wanma.support.simple.PinyinUtils;
import com.wanma.support.simple.SimpleUtils;

@Service
public class SimplePointServiceImpl implements SimplePointService {
	@Autowired
	private PowerStationService psService;
	@Autowired
	private ChargePileService cpService;	

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SimpleChargeController.class);
	/**
	 * 创建充电站
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Override
	public String getPowerStationInfo(String org) {
		//获取pk_PowerStation
		List<Map<String,Object>> list= psService.getPkPowerStations(org);
		//把请求参数封装成map
		Map<String, Object> data = new HashMap<String, Object>();
		//多个map时，添加到List
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		//遍历list
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> m=list.get(i);
			String psId = m.get("pkPowerStation").toString();
			PowerStation ps = new PowerStation();
			ps.setPkPowerstation(Integer.parseInt(psId));
			Integer pkPowerStation = ps.getPkPowerstation();
			//获取电站列表信息
			ps = psService.getPowerStationList(pkPowerStation);
			//获取充电站参数信息
			String postName = ps.getPostName();
			int postStatus = ps.getPostStatus();
			String provinceCode = ps.getPostOwnProvinceCode();
			String cityCode = ps.getPostOwnCityCode();
			String districtCode = ps.getPostOwnCountyCode();
			String provinceName = ps.getProvinceName();
			String cityName = ps.getCityName();
			String districtName = ps.getAreaName();
			String address = ps.getPostAddress();
			String stationTel = ps.getPostPhone();			
			BigDecimal longitude = ps.getPostLongitude();
			BigDecimal latitude = ps.getPostLatitude();
			String remark = ps.getPostRemark();
			// 获取充电站名称
			StringBuffer stationName = new StringBuffer();
			stationName.append("爱充-").append(cityName).append("-").append(postName);
			//获取充电站编码
			StringBuffer stationCode=new StringBuffer();
			stationCode.append("eichong-").append(PinyinUtils.toPinyin(cityName)).append("-").append(pkPowerStation);
			// 获取充电站类型
			String stationType = null;
			if (postStatus == 15) {
				stationType = "0";
			} else if (postStatus == 10) {
				stationType = "3";
			} else {
				stationType = "4";
			}
			String stationState = "0";
			Integer parkNum = null;
			String openTime = "000000";
			String closeTime = "235959";
			double costAmount = 0;
			double chargeAmount= 0;
			// 获取配置文件信息
			MessageManager manager = MessageManager.getMessageManager();
			String service = manager.getInterfaceProperties("simpleStation.service");
			String operatorId = manager.getInterfaceProperties("simple.operatorId");
			//获取公共配置文件的参数信息
			Map<String, Object> simpleMap = getSimpleData();
			data.put("service", service);
			data.putAll(simpleMap);
			data.put("operatorId", operatorId);
			data.put("stationName", stationName);
			data.put("stationCode", stationCode);
			data.put("stationType", stationType);
			data.put("stationState", stationState);
			data.put("parkNum", parkNum);
			data.put("provinceCode", provinceCode);
			data.put("provinceName", provinceName);
			data.put("cityCode", cityCode);
			data.put("cityName", cityName);
			data.put("districtCode", districtCode);
			data.put("districtName", districtName);
			data.put("address", address);
			data.put("stationTel", stationTel);
			data.put("openTime", openTime);
			data.put("closeTime",closeTime);
			data.put("longitude", longitude);
			data.put("latitude", latitude);
			data.put("wLongitude", null);
			data.put("wLatitude", null);
			data.put("costAmount", costAmount);
			data.put("chargeAmount", chargeAmount);
			data.put("remark", remark);		
			//打印日志
			LOGGER.info("请求参数："+data.toString());
			//把map转为list
			dataList.add(data);
		}
		//与停简单进行对接
		if( !(CommonConsts.SIMPLE_URL == null|| CommonConsts.SIMPLE_URL=="")){
			//向停简单发送请求参数
			String result = SimpleUtils.sendSimple(CommonConsts.SIMPLE_URL, data.toString());
			LOGGER.info("响应参数："+result);
			//把响应参数充字符串转为map
			Map<String,Object> map = (Map<String, Object>)JSON.parse(result);  
			String timestamp=null;
			String returnCode=null;
			String returnMsg=null;
			String isSuccess=null;
			String errorMSG = null;
			String stationId = null;
			for (Object maps : map.entrySet()) {
				Set set = map.keySet();
				Iterator ir = set.iterator();
				while (ir.hasNext()) {
					Object key = ir.next();
					if (key != null && ((String) key).equals("timestamp")) {
						timestamp = map.get(key).toString();
					}
					if (key != null && ((String) key).equals("returnCode")) {
						returnCode = map.get(key).toString();
					}
					if (key != null && ((String) key).equals("returnMsg")) {
						returnMsg = map.get(key).toString();
					}
					if (key != null && ((String) key).equals("isSuccess")) {
						isSuccess = map.get(key).toString();
					}
					if (key != null && ((String) key).equals("errorMSG")) {
						errorMSG = map.get(key).toString();
					}
					if (key != null && ((String) key).equals("stationId")) {
						stationId = map.get(key).toString();
					}
				}
			}
			String rc = null;
			if ("T".equals(returnCode)) {
				rc = "成功";
			}
			if ("F".equals(returnCode)) {
				rc = "失败";
			}
			String rm = null;
			if ("OK".equals(returnMsg)) {
				rm = "成功";
			} else {
				rm = returnMsg;
			}
			String isu = null;
			if ("0".equals(isSuccess)) {
				isu = "成功";
			} else {
				isu = "失败";
			}
			String em = null;
			if ("".equals(errorMSG)) {
				em = "成功";
			} else {
				em = errorMSG;
			}
			LOGGER.info("创建充电站响应参数" + "\n" + "响应时间：" + timestamp + "\n"
					+ "开放平台返回状态码：" + rc + "\n" + "开放平台返回消息:" + rm + "\n"
					+ "充电桩服务返回状态码:" + isu + "\n" + "充电桩服务返回错误消息:" + em + "\n"
					+ "开放平台充电站ID:"+stationId);
		}	
		return new AccessSuccessResult(dataList).toString();
		
		
	}

	/**
	 * 创建充电桩
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@Override
	public String getChargePileInfo(String org) {
		//获取pk_ElectricPile
		List<Map<String,Object>> list= cpService.getPkElectricPiles(org);
		//把请求参数封装成map
		Map<String, Object> data = new HashMap<String, Object>();
		//多个map时，添加到List
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		//遍历list
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> m = list.get(i);
			String pkIds = m.get("pkElectricPile").toString();
			TblChargePile cp = new TblChargePile();
			cp.setPkElectricpile(Integer.parseInt(pkIds));
			Integer pkElectricpile = cp.getPkElectricpile();
			//获取电桩信息列表
			cp = cpService.getChargePileList(pkElectricpile);
			//获取电桩参数信息
			String stationId = cp.getPkPowerStation();
			String postName = cp.getPostName();
			String epCode = cp.getElpiElectricpilecode();
			String provinceCode = cp.getElPiOwnProvinceCode();
			String provinceName = cp.getProvinceName();
			String cityCode = cp.getElPiOwnCityCode();
			String cityName = cp.getCityName();
			String districtCode = cp.getElPiOwnCountyCode();
			String districtName = cp.getAreaName();
			String address = cp.getElpiElectricpileaddress();
			int state = cp.getCommStatus();
			int epChargeMode = cp.getElpiChargingmode();
			String chargeType = null;
			if (epChargeMode == 5) {
				chargeType = "1";
			}
			if (epChargeMode == 14) {
				chargeType = "0";
			}
			String deviceType= cp.getTsModelName();
			BigDecimal longitude = cp.getElpiLongitude();
			BigDecimal latitude = cp.getElpiLatitude();
			String picture= cp.getElpiImage();
			//获取充电桩名称
			StringBuffer pointName= new StringBuffer();
			pointName.append("爱充-").append(cityName).append("-").append(postName).append("-").append(epCode);
			//获取充电桩编码
			StringBuffer pointCode= new StringBuffer();
			pointCode.append("eichong-").append(PinyinUtils.toPinyin(cityName)).append("-").append(PinyinUtils.toPinyin(postName)).append("-").append(epCode);			
			
			String openTime = "000000";
			String closeTime = "235959";
			//获取配置文件信息
			MessageManager manager = MessageManager.getMessageManager();
			String service = manager.getInterfaceProperties("simplePoint.service");		
			//获取公共配置文件的参数信息
			Map<String, Object> simpleMap = getSimpleData();
			data.put("service", service);
			data.putAll(simpleMap);
			data.put("stationId", stationId);
			data.put("pointName", pointName);
			data.put("pointCode", pointCode);
			data.put("provinceCode", provinceCode);
			data.put("provinceName", provinceName);
			data.put("cityCode", cityCode);
			data.put("cityName", cityName);
			data.put("districtCode", districtCode);
			data.put("districtName", districtName);
			data.put("address", address);
			data.put("dialingCode", null);
			data.put("state", state);
			data.put("openTime", openTime);
			data.put("closeTime",closeTime);
			data.put("chargeType", chargeType);
			data.put("deviceType", deviceType);
			data.put("parkId", null);
			data.put("longitude", longitude);
			data.put("latitude", latitude);
			data.put("wLongitude", null);
			data.put("wLatitude", null);
			data.put("pictureX", null);
			data.put("pictureY", null);
			data.put("picture", picture);			
			//打印日志
			LOGGER.info("请求参数："+data.toString());
			//把map转为list
			dataList.add(data);
		}
		//与停简单进行对接
		if( !(CommonConsts.SIMPLE_URL == null|| CommonConsts.SIMPLE_URL=="")){
			//向停简单发送请求参数
			String result = SimpleUtils.sendSimple(CommonConsts.SIMPLE_URL, data.toString());
			LOGGER.info("响应参数："+result);
			//把响应参数充字符串转为map
			Map<String, Object> map = (Map<String, Object>) JSON.parse(result);
			String timestamp = null;
			String returnCode = null;
			String returnMsg = null;
			String isSuccess = null;
			String errorMSG = null;
			String pointId = null;
			for (Object maps : map.entrySet()) {
				Set set = map.keySet();
				Iterator ir = set.iterator();
				while (ir.hasNext()) {
					Object key = ir.next();
					if (key != null && ((String) key).equals("timestamp")) {
						timestamp = map.get(key).toString();
					}
					if (key != null && ((String) key).equals("returnCode")) {
						returnCode = map.get(key).toString();
					}
					if (key != null && ((String) key).equals("returnMsg")) {
						returnMsg = map.get(key).toString();
					}
					if (key != null && ((String) key).equals("isSuccess")) {
						isSuccess = map.get(key).toString();
					}
					if (key != null && ((String) key).equals("errorMSG")) {
						errorMSG = map.get(key).toString();
					}
					if (key != null && ((String) key).equals("pointId")) {
						pointId = map.get(key).toString();
					}
				}
			}
			String rc = null;
			if ("T".equals(returnCode)) {
				rc = "成功";
			}
			if ("F".equals(returnCode)) {
				rc = "失败";
			}
			String rm = null;
			if ("OK".equals(returnMsg)) {
				rm = "成功";
			} else {
				rm = returnMsg;
			}
			String isu = null;
			if ("0".equals(isSuccess)) {
				isu = "成功";
			} else {
				isu = "失败";
			}
			String em = null;
			if ("".equals(errorMSG)) {
				em = "成功";
			} else {
				em = errorMSG;
			}
			LOGGER.info("创建充电桩响应参数" + "\n" + "响应时间：" + timestamp + "\n"
					+ "开放平台返回状态码：" + rc + "\n" + "开放平台返回消息:" + rm + "\n"
					+ "充电桩服务返回状态码:" + isu + "\n" + "充电桩服务返回错误消息:" + em + "\n"
					+ "开放平台充电桩ID:"+pointId);
		}
		return new AccessSuccessResult(dataList).toString();
	}

	/**
	 * 绑定充电枪到充电桩
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@Override
	public String getBindGunInfo(String org) {
		//获取pk_ElectricPile
		List<Map<String,Object>> list= cpService.getPkElectricPiles(org);
		//把请求参数封装成map
		Map<String, Object> data = new HashMap<String, Object>();
		//多个map时，添加到List
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		//遍历list
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> m=list.get(i);
			String pkIds=m.get("pkElectricPile").toString();
			TblChargePile cp = new TblChargePile();
			cp.setPkElectricpile(Integer.parseInt(pkIds));
			Integer pkElectricpile=cp.getPkElectricpile();
			//获取电桩以及电枪信息列表
			TblChargePile bg = cpService.getBingGunList(pkElectricpile);
			String pointId = bg.getElpiElectricpilecode();
			String cityName = bg.getCityName();
			String postName = bg.getPostName();
			int epNum = bg.getEpNum();
			int epHeadId = bg.getEpHeadId();
			int status = bg.getStatus();
			String state=null;
			if(status==0){
				state="0";
			}else if(status==3){
				state="1";
			}else if(status==6 || status==17){
				state="2";
			}else if(status==9){
				state="3";
			}
			// 获取充电枪名称
			StringBuffer gunName = new StringBuffer();
			gunName.append("爱充-").append(cityName).append("-").append(postName).append("-").append(epNum).append("号桩-").append(epHeadId).append("号枪");
			//获取充电枪编码
			StringBuffer gunCode=new StringBuffer();
			gunCode.append("eichong-").append(PinyinUtils.toPinyin(cityName)).append("-").append(PinyinUtils.toPinyin(postName)).append("-")
				.append(epNum).append("号桩-").append(epHeadId).append("号枪");
			//获取配置文件信息
			MessageManager manager = MessageManager.getMessageManager();
			String service = manager.getInterfaceProperties("simpleBindGun.service");
			//获取公共配置文件的参数信息
			Map<String, Object> simpleMap = getSimpleData();
			data.put("service", service);
			data.putAll(simpleMap);
			data.put("pointId", pointId);
			data.put("gunName", gunName);
			data.put("gunCode", gunCode);
			data.put("state", state);
			//打印日志
			LOGGER.info("请求参数："+data.toString());
			//把map转为list
			dataList.add(data);
		}
		//与停简单进行对接
		if( !(CommonConsts.SIMPLE_URL == null|| CommonConsts.SIMPLE_URL=="")){
			//向停简单发送请求参数
			String result = SimpleUtils.sendSimple(CommonConsts.SIMPLE_URL, data.toString());
			LOGGER.info("响应参数："+result);
			//把响应参数充字符串转为map
			Map<String, Object> map = (Map<String, Object>) JSON.parse(result);
			String timestamp = null;
			String returnCode = null;
			String returnMsg = null;
			String isSuccess = null;
			String errorMSG = null;
			String gunId = null;
			String qrcodePicture = null;
			for (Object maps : map.entrySet()) {
				Set set = map.keySet();
				Iterator ir = set.iterator();
				while (ir.hasNext()) {
					Object key = ir.next();
					if (key != null && ((String) key).equals("timestamp")) {
						timestamp = map.get(key).toString();
					}
					if (key != null && ((String) key).equals("returnCode")) {
						returnCode = map.get(key).toString();
					}
					if (key != null && ((String) key).equals("returnMsg")) {
						returnMsg = map.get(key).toString();
					}
					if (key != null && ((String) key).equals("isSuccess")) {
						isSuccess = map.get(key).toString();
					}
					if (key != null && ((String) key).equals("errorMSG")) {
						isSuccess = map.get(key).toString();
					}
					if (key != null && ((String) key).equals("gunId")) {
						gunId =  map.get(key).toString();
					}
					if (key != null && ((String) key).equals("qrcodePicture")) {
						qrcodePicture = map.get(key).toString();
					}
				}
			}
			String rc = null;
			if ("T".equals(returnCode)) {
				rc = "成功";
			}
			if ("F".equals(returnCode)) {
				rc = "失败";
			}
			String rm = null;
			if ("OK".equals(returnMsg)) {
				rm = "成功";
			} else {
				rm = returnMsg;
			}
			String isu = null;
			if ("0".equals(isSuccess)) {
				isu = "成功";
			} else {
				isu = "失败";
			}
			String em = null;
			if ("".equals(errorMSG)) {
				em = "成功";
			} else {
				em = errorMSG;
			}
			LOGGER.info("绑定充电枪响应参数" + "\n" + "响应时间：" + timestamp + "\n"
					+ "开放平台返回状态码：" + rc + "\n" + "开放平台返回消息:" + rm + "\n"
					+ "充电桩服务返回状态码:" + isu + "\n" + "充电桩服务返回错误消息:" + em + "\n"
					+ "开放平台充电枪ID:"+gunId+"\n"+"二维码图片地址："+qrcodePicture);
		}
		return new AccessSuccessResult(data).toString();
	}  
	
	/**
	 * 创建单个电站
	 */
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	@Override
	public String getSingleStationInfo(String psId) {
		PowerStation ps = new PowerStation();
		ps.setPkPowerstation(Integer.parseInt(psId));
		Integer pkPowerStation = ps.getPkPowerstation();
		//获取电站列表信息
		ps = psService.getPowerStationList(pkPowerStation);
		//获取充电站参数信息
		String postName = ps.getPostName();
		int postStatus = ps.getPostStatus();
		String provinceCode = ps.getPostOwnProvinceCode();
		String cityCode = ps.getPostOwnCityCode();
		String districtCode = ps.getPostOwnCountyCode();
		String provinceName = ps.getProvinceName();
		String cityName = ps.getCityName();
		String districtName = ps.getAreaName();
		String address = ps.getPostAddress();
		String stationTel = ps.getPostPhone();			
		BigDecimal longitude = ps.getPostLongitude();
		BigDecimal latitude = ps.getPostLatitude();
		String remark = ps.getPostRemark();
		// 获取充电站名称
		StringBuffer stationName = new StringBuffer();
		stationName.append("爱充-").append(cityName).append("-").append(postName);
		//获取充电站编码
		StringBuffer stationCode=new StringBuffer();
		stationCode.append("eichong-").append(PinyinUtils.toPinyin(cityName)).append("-").append(pkPowerStation);
		// 获取充电站类型
		String stationType = null;
		if (postStatus == 15) {
			stationType = "0";
		} else if (postStatus == 10) {
			stationType = "3";
		} else {
			stationType = "4";
		}
		String stationState = "0";
		Integer parkNum = null;
		String openTime = "000000";
		String closeTime = "235959";
		double costAmount = 0;
		double chargeAmount= 0;
		// 获取配置文件信息
		MessageManager manager = MessageManager.getMessageManager();
		String service = manager.getInterfaceProperties("simpleStation.service");
		String operatorId = manager.getInterfaceProperties("simple.operatorId");
		//获取公共配置文件的参数信息
		Map<String, Object> simpleMap = getSimpleData();
		//把请求参数封装成map
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("service", service);
		data.putAll(simpleMap);
		data.put("operatorId", operatorId);
		data.put("stationName", stationName);
		data.put("stationCode", stationCode);
		data.put("stationType", stationType);
		data.put("stationState", stationState);
		data.put("parkNum", parkNum);
		data.put("provinceCode", provinceCode);
		data.put("provinceName", provinceName);
		data.put("cityCode", cityCode);
		data.put("cityName", cityName);
		data.put("districtCode", districtCode);
		data.put("districtName", districtName);
		data.put("address", address);
		data.put("stationTel", stationTel);
		data.put("openTime", openTime);
		data.put("closeTime",closeTime);
		data.put("longitude", longitude);
		data.put("latitude", latitude);
		data.put("wLongitude", null);
		data.put("wLatitude", null);
		data.put("costAmount", costAmount);
		data.put("chargeAmount", chargeAmount);
		data.put("remark", remark);		
		//打印日志
		LOGGER.info("请求参数："+data.toString());
		//与停简单进行对接
				if( !(CommonConsts.SIMPLE_URL == null|| CommonConsts.SIMPLE_URL=="")){
					//向停简单发送请求参数
					String result = SimpleUtils.sendSimple(CommonConsts.SIMPLE_URL, data.toString());
					LOGGER.info("响应参数："+result);
					//把响应参数充字符串转为map
					Map<String,Object> map = (Map<String, Object>)JSON.parse(result);  
					String timestamp=null;
					String returnCode=null;
					String returnMsg=null;
					String isSuccess=null;
					String errorMSG = null;
					String stationId = null;
					for (Object maps : map.entrySet()) {
						Set set = map.keySet();
						Iterator ir = set.iterator();
						while (ir.hasNext()) {
							Object key = ir.next();
							if (key != null && ((String) key).equals("timestamp")) {
								timestamp = map.get(key).toString();
							}
							if (key != null && ((String) key).equals("returnCode")) {
								returnCode = map.get(key).toString();
							}
							if (key != null && ((String) key).equals("returnMsg")) {
								returnMsg = map.get(key).toString();
							}
							if (key != null && ((String) key).equals("isSuccess")) {
								isSuccess = map.get(key).toString();
							}
							if (key != null && ((String) key).equals("errorMSG")) {
								errorMSG = map.get(key).toString();
							}
							if (key != null && ((String) key).equals("stationId")) {
								stationId = map.get(key).toString();
							}
						}
					}
					String rc = null;
					if ("T".equals(returnCode)) {
						rc = "成功";
					}
					if ("F".equals(returnCode)) {
						rc = "失败";
					}
					String rm = null;
					if ("OK".equals(returnMsg)) {
						rm = "成功";
					} else {
						rm = returnMsg;
					}
					String isu = null;
					if ("0".equals(isSuccess)) {
						isu = "成功";
					} else {
						isu = "失败";
					}
					String em = null;
					if ("".equals(errorMSG)) {
						em = "成功";
					} else {
						em = errorMSG;
					}
					LOGGER.info("创建充电站响应参数" + "\n" + "响应时间：" + timestamp + "\n"
							+ "开放平台返回状态码：" + rc + "\n" + "开放平台返回消息:" + rm + "\n"
							+ "充电桩服务返回状态码:" + isu + "\n" + "充电桩服务返回错误消息:" + em + "\n"
							+ "开放平台充电站ID:"+stationId);
				}	
				return new AccessSuccessResult(data).toString();
	}
	
	/**
	 * 停简单配置参数
	 * @return
	 */
	public Map<String,Object> getSimpleData(){
		Map<String, Object> map = new HashMap<String, Object>();
		//获取配置文件信息
		MessageManager manager = MessageManager.getMessageManager();
		String version = manager.getInterfaceProperties("simple.version");
		String sign = manager.getInterfaceProperties("simple.sign");
		String partner = manager.getInterfaceProperties("simple.partner");
		String charset = manager.getInterfaceProperties("simple.charset");
		String signType= manager.getInterfaceProperties("simple.signType");
		// 获取日期及格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
		Date now = new Date();
		String timestamp = sdf.format(now);
		map.put("version", version);
		map.put("sign", sign);
		map.put("partner", partner);
		map.put("timestamp", timestamp);
		map.put("charset", charset);
		map.put("signType", signType);
		return map;
		
	}

	
}
