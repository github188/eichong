/**     
 * @Title:  HbaseController.java   
 * @Package com.wanma.controller.itf   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年2月25日 下午2:39:53   
 * @version V1.0     
 */
package com.wanma.controller.itf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.filter.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wanma.hbase.CommitLog;
import com.wanma.hbase.FilterBuilder;
import com.wanma.hbase.RealtimeConstant;
import com.wanma.hbase.RealtimeUtil;
import com.wanma.support.common.FailedResponse;
import com.wanma.support.common.SuccessResponse;

/**
 * @author bc
 *
 */
@Controller
@RequestMapping("/itf/hbase")
public class HbaseController {
	private RealtimeUtil realtimeUtil = RealtimeUtil.getInstance();

	/**
	 * @Description: 添加操作日志
	 * @return: ResponseBody
	 */
	@RequestMapping("/addCommitLog")
	@ResponseBody
	public String addCommitLog(CommitLog commitLog, HttpServletRequest request) {
		commitLog.setCreateDate(new Date());
		commitLog.setUpdateDate(new Date());
		boolean flag = RealtimeUtil.addLogData(commitLog);
		if (flag) {
			return new SuccessResponse().toString();
		} else {
			return new FailedResponse().toString();
		}
	}

	/**
	 * @Description: 电桩实时信息
	 * @return: ResponseBody
	 */
	@RequestMapping("/getRealtimeData")
	@ResponseBody
	public String getRealtimeData(HttpServletRequest request) {
		String epCode = request.getParameter("epCode");
		String headId = request.getParameter("headId");
		String type = request.getParameter("epType");
		String tableName = RealtimeConstant.TABLE_REALTIME_JL;
		if ("5".equals(type)) {
			tableName = RealtimeConstant.TABLE_REALTIME_ZL;
		}

		Map map = realtimeUtil.getData(tableName, epCode + headId);
		if (map == null || map.get("3_1") == null)
			return "{}";
		Map result = new HashMap();
		Integer chargeStatus = new Integer(map.get("3_1").toString());
		result.put("chargeStatus", chargeStatus);
		result.put("voltageValue", nullToZero(map.get("3_3")));
		List<Integer> errorList = new ArrayList<Integer>();
		if (chargeStatus != null) {
			if (chargeStatus == 3) {
				result.put("currentValue", nullToZero(map.get("3_4")));
				result.put("chargedTime", nullToZero(map.get("3_6")));
				result.put("presentChargeValue", nullToZero(map.get("4_4")));
				result.put("batteryType", map.get("3_17"));
				result.put("carIdentification", map.get("4_9"));
				/* result.put("carLicense", map.get("4_10")); */
				result.put("carTotalVoltage", map.get("3_36"));
				result.put("bpHighestVoltage", map.get("3_35"));
				result.put("bpLowestTemperature", map.get("3_35"));
				result.put("bpHighestTemperature", map.get("3_34"));
				result.put("soc", map.get("3_5"));
				result.put("chargeType", map.get("2_9"));
				result.put("chargeRemainTime", nullToZero(map.get("3_7")));
				result.put("powerHighestTemperature", map.get("3_137"));
			} else if (chargeStatus >= 30) {// 30以上为运行故障
				errorList.add(chargeStatus - 29);
			}
		}
		// 1:电桩初始化2:欠压故障3:过压故障 4:过电流故障5:防雷器故障6:电表故障7:接触器故障8:绝缘检查9:急停
		// 10:读卡器故障11:过温故障12:直流输出过流13:直流输出过压14:BMS过压15:BMS欠压16:BMS通信异常
		// 17:蓄电池过温告警18:蓄电池过流告警
		// 10读卡器故障
		if (map.get("1_6") != null && map.get("1_6").toString().equals("1")) {
			errorList.add(10);
		}
		// 11过温故障
		if ((map.get("2_2") != null && map.get("2_2").toString().equals("1"))
				|| (map.get("2_12") != null && map.get("2_12").toString()
						.equals("1"))) {
			errorList.add(11);
		}
		// 12直流输出过流
		if (map.get("1_21") != null && map.get("1_21").toString().equals("1")) {
			errorList.add(12);
		}
		// 13直流输出过压
		if (map.get("2_15") != null && map.get("2_15").toString().equals("1")) {
			errorList.add(13);
		}
		// 14BMS过压
		if (map.get("2_16") != null && map.get("2_16").toString().equals("1")) {
			errorList.add(14);
		}
		// 15BMS欠压
		if (map.get("2_16") != null && map.get("2_16").toString().equals("2")) {
			errorList.add(15);
		}
		// 16BMS通信异常
		if (map.get("1_19") != null && map.get("1_19").toString().equals("1")) {
			errorList.add(16);
		}
		// 17蓄电池过温告警
		if (map.get("2_11") != null && map.get("2_11").toString().equals("1")) {
			errorList.add(17);
		}
		// 18蓄电池过流告警
		if (map.get("2_14") != null && map.get("2_14").toString().equals("1")) {
			errorList.add(18);
		}
		result.put("errorList", errorList);
		return JSON.toJSONString(result);
	}

	/**
	 * @Description: 电流电压实时信息列表
	 * @return: ResponseBody
	 */
	@RequestMapping("/getChargingData")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public String getVoltageData(HttpServletRequest request) {
		Map result = new HashMap();
		String epCode = request.getParameter("epCode");
		String headId = request.getParameter("headId");
		String type = request.getParameter("epType");
		String tableName = RealtimeConstant.TABLE_REALTIME_JL;
		if ("5".equals(type)) {
			tableName = RealtimeConstant.TABLE_REALTIME_ZL;
		}
		// 电桩不在充电状态下 返回空值
		Map resultMap = realtimeUtil.getData(tableName, epCode + headId);
		if (resultMap == null || resultMap.isEmpty()
				|| !resultMap.get("3_1").toString().equals("3"))
			return "{}";

		tableName = RealtimeConstant.TABLE_HISTORY_JL;
		if ("5".equals(type)) {
			tableName = RealtimeConstant.TABLE_HISTORY_ZL;
		}
		// 得到最近的充电记录时间
		long t = System.currentTimeMillis();
		List<Filter> arr = new ArrayList<Filter>();
		arr.add(new FilterBuilder(RealtimeConstant.FITER_EQUAL, "sx", "epCode",
				epCode).buildFilter());
		arr.add(new FilterBuilder(RealtimeConstant.FITER_EQUAL, "sx", "3_1",
				"3").buildFilter());
		List<Map<String, String>> chargingList = realtimeUtil.getList(
				tableName, arr, t - 43200000, t);
		Map<String, String> startObj = null;
		if (!chargingList.isEmpty()) {
			startObj = chargingList.get(chargingList.size() - 1);
		}
		if (startObj == null) {
			return JSON.toJSONString(result);
		}
		// 根据充电开始时间查询充电信息列表
		arr = new ArrayList<Filter>();
		arr.add(new FilterBuilder(RealtimeConstant.FITER_EQUAL, "sx", "epCode",
				epCode).buildFilter());
		List<Map<String, String>> chargingList2 = realtimeUtil.getList(
				tableName, arr, new Long(startObj.get("ts")) + 1, t);
		List<Map<String, Object>> cList = new ArrayList<Map<String, Object>>();
		Map<String, Object> tempMap = null;
		Map<String, Object> tempMap2 = new HashMap<String, Object>();
		tempMap2.put("voltageValue", 0);
		tempMap2.put("currentValue", 0);
		for (Map<String, String> map : chargingList2) {
			// 状态变化，停止充电
			if (map.get("3_1") != null
					&& !map.get("3_1").toString().equals("3"))
				break;
			tempMap = new HashMap<String, Object>();
			fillValue(map.get("3_3"), "voltageValue", tempMap2, tempMap);
			fillValue(map.get("3_4"), "currentValue", tempMap2, tempMap);
			fillValue(map.get("3_34"), "temperature", tempMap2, tempMap);
			fillValue(map.get("4_1"), "allChargeValue", tempMap2, tempMap);
			fillValue(map.get("4_4"), "presentChargeValue", tempMap2, tempMap);
			fillValue(map.get("4_2"), "presentChargeFee", tempMap2, tempMap);
			fillValue(map.get("3_41"), "av", tempMap2, tempMap);
			fillValue(map.get("3_42"), "bv", tempMap2, tempMap);
			fillValue(map.get("3_43"), "cv", tempMap2, tempMap);
			fillValue(map.get("3_44"), "ac", tempMap2, tempMap);
			fillValue(map.get("3_45"), "bc", tempMap2, tempMap);
			fillValue(map.get("3_46"), "cc", tempMap2, tempMap);
			fillValue(map.get("3_5"), "soc", tempMap2, tempMap);
			tempMap.put("ts", nullToZero(map.get("ts")));
			tempMap.put("epCode", nullToZero(map.get("epCode")));

			cList.add(tempMap);
		}
		result.put("data", cList);
		return JSON.toJSONString(result);
	}

	private void fillValue(String value, String key,
			Map<String, Object> tempMap2, Map<String, Object> tempMap) {
		if (value != null) {
			tempMap2.put(key, value);
		}
		tempMap.put(key, tempMap2.get(key));
	}

	private double nullToZero(Object obj) {
		if (obj == null)
			return 0.0;
		return StringUtils.isBlank(obj.toString()) ? 0 : new Double(
				obj.toString());
	}

	/**
	 * @Description: 操作信息
	 * @return: ResponseBody
	 */
	@RequestMapping("/getOperationData")
	@ResponseBody
	public String getOperationData(HttpServletRequest request) {
		Map result = new HashMap();
		String epCode = request.getParameter("epCode");
		String headId = request.getParameter("headId");
		String type = request.getParameter("epType");
		String monthStr = request.getParameter("date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			String tableName = RealtimeConstant.TABLE_HISTORY_JL;
			if ("5".equals(type)) {
				tableName = RealtimeConstant.TABLE_HISTORY_ZL;
			}
			// 得到最近的充电记录时间
			Date d = sdf.parse(monthStr + "01000000");
			long t1 = d.getTime();
			d.setMonth(d.getMonth() + 1);
			long t2 = d.getTime();
			System.out.println(t1);
			System.out.println(t2);
			List<Filter> arr = new ArrayList<Filter>();
			// 根据操作开始时间查询操作列表
			arr = new ArrayList<Filter>();
			arr.add(new FilterBuilder(RealtimeConstant.FITER_EQUAL, "sx",
					"epCode", epCode).buildFilter());
			arr.add(new FilterBuilder(RealtimeConstant.FITER_NOT_EQUAL, "sx",
					"3_1", "-1").buildFilter());
			List<Map<String, String>> chargingList2 = realtimeUtil.getList(
					tableName, arr, t1, t2);
			List<Map<String, Object>> cList = new ArrayList<Map<String, Object>>();
			Map<String, Object> tempMap = null;
			for (Map<String, String> map : chargingList2) {
				tempMap = new HashMap<String, Object>();
				if (isOperateMap(map)) {
					tempMap.put("status", nullToZero(map.get("3_1")));
					tempMap.put("ts", nullToZero(map.get("ts")));
					cList.add(tempMap);
				}
			}
			result.put("data", cList);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return JSON.toJSONString(result);
	}

	private boolean isOperateMap(Map map) {
		String v = map.get("3_1").toString();
		int value = new Integer(v);
		if (value == 1 || value == 3 || value == 8 || value >= 30) {
			return true;
		}
		return false;
	}

	/**
	 * @Description: 故障信息
	 * @return: ResponseBody
	 */
	@RequestMapping("/getErrorData")
	@ResponseBody
	public String getErrorData(HttpServletRequest request) {
		Map result = new HashMap();
		try {
			// 得到最近的充电记录时间
			long t2 = System.currentTimeMillis();
			long t1 = t2 - 30 * 24 * 3600 * 1000L;
			System.out.println(t1);
			System.out.println(t2);
			String tableName = RealtimeConstant.TABLE_HISTORY_JL;
			List<Map<String, Object>> list = getErrorList(tableName, t1, t2);

			tableName = RealtimeConstant.TABLE_HISTORY_ZL;
			List<Map<String, Object>> list2 = getErrorList(tableName, t1, t2);
			list = MergeAndSortList(list, list2);
			result.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return JSON.toJSONString(result);
	}

	private List<Map<String, Object>> getErrorList(String tableName, long t1,
			long t2) {
		// 根据操作开始时间查询操作列表
		List<Filter> arr = new ArrayList<Filter>();
		arr = new ArrayList<Filter>();
		arr.add(new FilterBuilder(RealtimeConstant.FITER_NOT_EQUAL, "sx",
				"3_1", "3").buildFilter());
		List<Map<String, String>> chargingList2 = realtimeUtil.getList(
				tableName, arr, t1, t2);
		List<Map<String, Object>> cList = new ArrayList<Map<String, Object>>();
		Map<String, Object> tempMap = null;
		int count = 0;
		for (Map<String, String> map : chargingList2) {
			tempMap = new HashMap<String, Object>();
			if (isErrorMap(map)) {
				tempMap.put("epCode", map.get("epCode"));
				tempMap.put("status", nullToZero(map.get("3_1")));
				tempMap.put("ts", nullToZero(map.get("ts")));
				cList.add(tempMap);
			}
		}
		return cList;
	}

	private List<Map<String, Object>> MergeAndSortList(
			List<Map<String, Object>> l1, List<Map<String, Object>> l2) {
		if (l1.isEmpty()) {
			l1 = new ArrayList<Map<String, Object>>();
		}
		if (!l2.isEmpty()) {
			l1.addAll(l2);
		}
		Collections.sort(l1, new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> arg0,
					Map<String, Object> arg1) {
				Long l0 = Math.round((Double) arg0.get("ts"));
				Long l1 = Math.round((Double) arg1.get("ts"));
				return l1.compareTo(l0);
			}
		});
		if (l1.size() >= 5) {
			l1 = l1.subList(0, 5);
		}
		return l1;
	}

	private boolean isErrorMap(Map map) {
		String v = map.get("3_1").toString();
		int value = new Integer(v);
		if (value == 1 || value >= 30) {
			return true;
		}
		return false;
	}

	/**
	 * @Description: 电源模块信息列表
	 * @return: ResponseBody
	 */
	@RequestMapping("/getBatteryModulesData")
	@ResponseBody
	public String getBatteryModulesData(HttpServletRequest request) {
		Map result = new HashMap();
		String epCode = request.getParameter("epCode");
		String headId = request.getParameter("headId");
		String type = request.getParameter("epType");
		try {
			String tableName = RealtimeConstant.TABLE_REALTIME_JL;
			if ("5".equals(type)) {
				tableName = RealtimeConstant.TABLE_REALTIME_ZL;
			}
			Map map = realtimeUtil.getData(tableName, epCode + headId);
			if (map == null)
				return "{}";
			List<Map<String, Object>> cList = new ArrayList<Map<String, Object>>();
			Map<String, Object> tempMap = null;
			int i = 0;
			while (hasBatteryInfo(map, i)) {
				tempMap = getBatteryInfoMap(map, i);
				cList.add(tempMap);
				i++;
			}
			result.put("data", cList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(result);
	}

	/**
	 * @Description: 电源模块信息
	 * @return: ResponseBody
	 */
	@RequestMapping("/getBatteryModuleData")
	@ResponseBody
	public String getBatteryModuleData(HttpServletRequest request) {
		Map result = new HashMap();
		String epCode = request.getParameter("epCode");
		String headId = request.getParameter("headId");
		String type = request.getParameter("epType");
		String index = request.getParameter("index");
		try {
			String tableName = RealtimeConstant.TABLE_HISTORY_JL;
			if ("5".equals(type)) {
				tableName = RealtimeConstant.TABLE_HISTORY_ZL;
			}
			// 得到最近的充电记录时间
			long t = System.currentTimeMillis();
			List<Filter> arr = new ArrayList<Filter>();
			arr.add(new FilterBuilder(RealtimeConstant.FITER_EQUAL, "sx",
					"epCode", epCode).buildFilter());
			arr.add(new FilterBuilder(RealtimeConstant.FITER_EQUAL, "sx",
					"3_1", "3").buildFilter());
			List<Map<String, String>> chargingList = realtimeUtil.getList(
					tableName, arr, t - 43200000, t);
			Map<String, String> startObj = null;
			if (!chargingList.isEmpty()) {
				startObj = chargingList.get(chargingList.size() - 1);
			}
			if (startObj == null) {
				return JSON.toJSONString(result);
			}
			// 根据操作开始时间查询操作列表
			arr = new ArrayList<Filter>();
			arr.add(new FilterBuilder(RealtimeConstant.FITER_EQUAL, "sx",
					"epCode", epCode).buildFilter());
			List<Map<String, String>> chargingList2 = realtimeUtil.getList(
					tableName, arr, new Long(startObj.get("ts")) + 1, t);
			List<Map<String, Object>> cList = new ArrayList<Map<String, Object>>();
			Map<String, Object> tempMap = null;
			for (Map<String, String> map : chargingList2) {
				tempMap = getBatteryInfoMap(map, new Integer(index));
				cList.add(tempMap);
			}
			result.put("data", cList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(result);
	}

	private boolean hasBatteryInfo(Map<String, String> chargeMap, int i) {
		return chargeMap.get("3_" + (129 + i * 9)) == null ? false : true;
	}

	private Map<String, Object> getBatteryInfoMap(
			Map<String, String> chargeMap, int i) {
		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("index", i + "");
		tempMap.put("tv", nullToZero(chargeMap.get("3_" + (129 + i * 9))));
		tempMap.put("tc", nullToZero(chargeMap.get("3_" + (130 + i * 9))));
		tempMap.put("av", nullToZero(chargeMap.get("3_" + (131 + i * 9))));
		tempMap.put("bv", nullToZero(chargeMap.get("3_" + (132 + i * 9))));
		tempMap.put("cv", nullToZero(chargeMap.get("3_" + (133 + i * 9))));
		tempMap.put("ac", nullToZero(chargeMap.get("3_" + (134 + i * 9))));
		tempMap.put("bc", nullToZero(chargeMap.get("3_" + (135 + i * 9))));
		tempMap.put("cc", nullToZero(chargeMap.get("3_" + (136 + i * 9))));
		tempMap.put("tt", nullToZero(chargeMap.get("3_" + (137 + i * 9))));
		return tempMap;
	}

	/**
	 * 人工结算信息查询
	 * 
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/getPersonSettle")
	@ResponseBody
	public String getPersonSettle(HttpServletRequest request)
			throws ParseException {
		Map<String, String> result = new HashMap<String, String>();
		String chOr_PileNumber = request.getParameter("chOr_PileNumber");
		String chOr_Muzzle = request.getParameter("chOr_Muzzle");
		String begin_charge_time = request.getParameter("begin_charge_time");
		String chargingMode = request.getParameter("chargingMode");
		String tableName = RealtimeConstant.TABLE_HISTORY_JL;
		if ("5".equals(chargingMode)) {
			tableName = RealtimeConstant.TABLE_HISTORY_ZL;
		}
		// 得到最近的充电记录时间
		long t = System.currentTimeMillis();
		Date t2 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse(begin_charge_time);
		long t3 = t2.getTime();//
		t3 = t3 - 300000;

		List<Filter> record = new ArrayList<Filter>();
		record.add(new FilterBuilder(RealtimeConstant.FITER_EQUAL, "sx",
				"epId", chOr_PileNumber + chOr_Muzzle).buildFilter());
		List<Map<String, String>> recordList = realtimeUtil.getList(tableName,
				record, t3, t);

		int i = 0;
		String flag = "";

		while (i < recordList.size()) {

			if (!"0.0".equals(recordList.get(i).get("4_2"))
					&& recordList.get(i).get("4_2") != null) {

				result.put("money", recordList.get(i).get("4_2"));
				result.put("power", recordList.get(i).get("4_4"));
				result.put("charingTime", recordList.get(i).get("ts"));

				break;

			}

			i++;
		}

		if (result.get("money") == null || "".equals(result.get("money"))) {
			flag = "0";

		} else
			flag = "1";

		result.put("flag", flag);

		return JSON.toJSONString(result);
	}
}
