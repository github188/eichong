package com.wanma.support.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.wanma.model.TblRateInformation;
import com.wanma.support.simple.JudgeNullUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;

public class RateinformationUtil {
	private static final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");

	/**
	 * 获取当前时间对应的尖峰平谷那个段
	 *
	 * @param jsonRate 格式化的费率字符串
	 * @return 1尖2峰3平4谷
	 */
	public static String getCurrentPowerRateMark(String jsonRate) {
		if (StringUtils.isEmpty(jsonRate)) {
			return "0";
		}
		JSONObject jo = JSONObject.parseObject(jsonRate);
		JSONArray ja = jo.getJSONArray("data");
		Calendar c = Calendar.getInstance();
		for (int i = 0; i < ja.size(); i++) {
			JSONObject cjo = ja.getJSONObject(i);
			int currentM = (c.get(Calendar.HOUR_OF_DAY) * 60 + c.get(Calendar.MINUTE));
			if (Integer.parseInt(cjo.getString("st")) < currentM && Integer.parseInt(cjo.getString("et")) > currentM) {
				return cjo.getString("mark");
			}
		}
		return "0";
	}


	/**
	 * 获取当前时间下的费率以及起始时间
	 * @param model
	 * @return
	 */
	public static List getPowerRateMarks(TblRateInformation model) {
		Map<String, Object> data = new HashMap<>();
		List<Map<String, Object>> rateMapList = new ArrayList<>();

		if (model == null) {
			data.put("ElecPrice", 0.0000);
			data.put("SevicePrice", 0.0000);
			data.put("StartTime", "");
			rateMapList.add(data);
			return rateMapList;
		}
		//判断费率是否老费率否则为新账户费率
		if (!model.getRaInType().equals("3")) {
			Map<String, Map<String, Object>> jsonMap = (Map<String, Map<String, Object>>) JSON
					.parse(model.getRaInQuantumDate());
			List<Map<String, Object>> rateList = (List<Map<String, Object>>) jsonMap
					.get("data");
			for (Map<String, Object> rateInfo : rateList) {
				Map<String, Object> rate2 = new HashMap<>();
				int st = Integer.valueOf(rateInfo.get("st").toString());
				Integer timeSt = (int) Math.floor(st / 60);
				String stMin = String.valueOf(st - timeSt * 60);
				String timeStart = String.format("%02d", timeSt);
				if ("1".equals(rateInfo.get("mark"))) {
					rate2.put("ElecPrice", String.valueOf(model.getRaInTipTimeTariff()));
					rate2.put("SevicePrice", String.valueOf(model.getRaInServiceCharge()));
				}
				if ("2".equals(rateInfo.get("mark"))) {
					rate2.put("ElecPrice", String.valueOf(model.getRaInPeakElectricityPrice()));
					rate2.put("SevicePrice", String.valueOf(model.getRaInServiceCharge()));
				}
				if ("3".equals(rateInfo.get("mark"))) {
					rate2.put("ElecPrice", String.valueOf(model.getRaInUsualPrice()));
					rate2.put("SevicePrice", String.valueOf(model.getRaInServiceCharge()));
				}
				if ("4".equals(rateInfo.get("mark"))) {
					rate2.put("ElecPrice", String.valueOf(model.getRaInValleyTimePrice()));
					rate2.put("SevicePrice", String.valueOf(model.getRaInServiceCharge()));
				}
				String aa="00";
				rate2.put("StartTime",timeStart + ":" + (stMin.length() == 1 ? "0" + stMin : stMin )+":"+aa);
				rateMapList.add(rate2);
			}
			return rateMapList;
		} else {
			Map<String, Map<String, Object>> jsonMap = (Map<String, Map<String, Object>>) JSON
					.parse(model.getRaInQuantumDate());
			List<Map<String, Object>> rateList = (List<Map<String, Object>>) jsonMap
					.get("data");
			for (Map<String, Object> rateInfo : rateList) {
				Map<String, Object> rate2 = new HashMap<>();
				int st = Integer.valueOf(rateInfo.get("st").toString());
				Integer timeSt = (int) Math.floor(st / 60);
				String stMin = String.valueOf(st - timeSt * 60);
				String timeStart = String.format("%02d", timeSt);
				if ("1".equals(rateInfo.get("mark"))) {
					rate2.put("ElecPrice", String.valueOf(model.getRaInTipTimeTariff()));
					rate2.put("SevicePrice", String.valueOf(model.getRaInTipTimeTariffMoney()));
				}
				if ("2".equals(rateInfo.get("mark"))) {
					rate2.put("ElecPrice", String.valueOf(model.getRaInPeakElectricityPrice()));
					rate2.put("SevicePrice", String.valueOf(model.getRaInPeakElectricityMoney()));
				}
				if ("3".equals(rateInfo.get("mark"))) {
					rate2.put("ElecPrice", String.valueOf(model.getRaInUsualPrice()));
					rate2.put("SevicePrice", String.valueOf(model.getRaInUsualMoney()));
				}
				if ("4".equals(rateInfo.get("mark"))) {
					rate2.put("ElecPrice", String.valueOf(model.getRaInValleyTimePrice()));
					rate2.put("SevicePrice", String.valueOf(model.getRaInValleyTimeMoney()));
				}
				String aa="00";
				rate2.put("StartTime",timeStart + ":" + (stMin.length() == 1 ? "0" + stMin : stMin )+":"+aa);
				rateMapList.add(rate2);
			}
		}
		return rateMapList;
	}
}
