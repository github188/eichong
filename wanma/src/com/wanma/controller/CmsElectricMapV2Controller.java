package com.wanma.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.common.MessageManager;
import com.wanma.common.PageResponse;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.ElectricPileDetail;
import com.wanma.model.ElectricPileMonitor;
import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblUser;
import com.wanma.service.CmsElectricSearchService;
import com.wanma.service.WebElectricPileMonitorService;
import com.wanma.service.impl.PowerStationDetailServiceMapImpl;
import com.wanma.service.impl.TblCityServiceImpl;
import com.wanma.web.support.utils.HttpRequest;

/**
 * FrontEndDispatcherCtrl 地图跳转
 * 
 * @author Haner
 */
@Controller
@RequestMapping("/admin/electricPileMonitorV2")
public class CmsElectricMapV2Controller {
	@Autowired
	private WebElectricPileMonitorService webElectricPileMonitorService;
	@Autowired
	private CmsElectricSearchService electricPileMapService;

	@Autowired
	private PowerStationDetailServiceMapImpl powerStationDetailService;
	@Autowired
	private TblCityServiceImpl tblCityService;

	/**
	 * 地图电桩查询
	 *
	 * @param path
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("search")
	public String electric(@RequestParam Map<String, Object> params, Model model)
			throws UnsupportedEncodingException {
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		model.addAttribute("cityCode", params.get("cityCode"));
		model.addAttribute("jwdStr", params.get("jwdStr"));
		String cName = tblCityService.getCityName(params.get("cityCode")
				.toString());
		model.addAttribute("cityName", cName);
		return "backstage/monitor/electric-mapV2";
	}

	/**
	 * 地图信息查询
	 * 
	 * @param request
	 * @param params
	 * @param pager
	 * @param model
	 * @param electricPileMonitor
	 * @return
	 */
	@RequestMapping("getElectricPileForMap")
	@ResponseBody
	public String getElectricPileForMap(HttpServletRequest request,
			@RequestParam Map<String, Object> params) {
		TblUser loginUser = SessionMgr.getWebUser(request);
		params.put("userLevel", loginUser.getUserLevel());
		params.put("userId", loginUser.getUserId());
		List<ElectricPileMonitor> electricPileMonitorMapList = webElectricPileMonitorService
				.getElectricPileMonitorForMapV2(params);
		ElectricPileMonitor electricPileMonitor = new ElectricPileMonitor();
		electricPileMonitor
				.setElectricPileMonitorMapList(electricPileMonitorMapList);
		return JSON.toJSONString(electricPileMonitor);
	}

	/**
	 * 地图列表查询
	 * 
	 * @param request
	 * @param params
	 * @param pager
	 * @param model
	 * @param electricPileMonitor
	 * @return
	 */
	@RequestMapping("getElectricPileMapList")
	@ResponseBody
	public String getElectricPileMapList(HttpServletRequest request,
			@RequestParam Map<String, Object> params) {
		// 电桩充电点总数
		PageResponse<List<ElectricPileMonitor>> pager = new PageResponse<List<ElectricPileMonitor>>(
				Integer.valueOf((String) params.get("pageNum")),
				Integer.valueOf((String) params.get("pageSize")));
		params.put("pager", pager);
		TblUser loginUser = SessionMgr.getWebUser(request);
		params.put("userLevel", loginUser.getUserLevel());
		params.put("userId", loginUser.getUserId());
		long total = webElectricPileMonitorService
				.getElectricpileMapV2Count(params);
		List<ElectricPileMonitor> electricPileMonitorList = webElectricPileMonitorService
				.getElectricPileMonitorForListV2(params);
		pager.setCountData(total);
		pager.setDate(electricPileMonitorList);
		return JSON.toJSONString(pager);
	}

	/**
	 * 电桩列表查询
	 * 
	 * @param request
	 * @param params
	 * @param pager
	 * @param model
	 * @param electricPileMonitor
	 * @return
	 */
	@RequestMapping("getElectricPileListByStationId")
	@ResponseBody
	public String getElectricPileListByStationId(HttpServletRequest request,
			@RequestParam Map<String, Object> params) {
		List<ElectricPileMonitor> electricPileListByStationId = webElectricPileMonitorService
				.getElectricPileListByStationId(params);
		return JSON.toJSONString(electricPileListByStationId);
	}

	/**
	 * 点击查询
	 * 
	 * @param request
	 * @param params
	 * @param pager
	 * @param model
	 * @param electricPileMonitor
	 * @return
	 */
	@RequestMapping("getElectricPileDetail")
	@ResponseBody
	public String getElectricPileDetail(HttpServletRequest request,
			@RequestParam Map<String, String> params) {
		String id = params.get("electricId").toString();
		ElectricPileDetail pileDetail = electricPileMapService
				.getCurrentPileDetail(id);
		List<TblElectricpilehead> headList = electricPileMapService
				.getCurrentHeadList(id);
		ElectricPileMonitor electricPileMonitor = new ElectricPileMonitor();
		electricPileMonitor.setHeadList(headList);
		electricPileMonitor.setPileDetail(pileDetail);
		params.put("epCode", pileDetail.getElectricPileNo());
		params.put("elPiChargingMode", pileDetail.getElectricPileChargingMode());
		electricPileMonitor.setFengzhiHtml(powerStationDetailService
				.makeFengzhiStr(powerStationDetailService
						.getTblRateinformation(id)));
		MessageManager manager = MessageManager.getMessageManager();
		String submitResult = HttpRequest.post(
				manager.getSystemProperties("hbaseUrl") + "/getRealtimeData",
				params);
		if ("".equals(submitResult))
			electricPileMonitor.setMonitorData(new Object());
		else
			electricPileMonitor.setMonitorData(JSON.parse(submitResult));
		return JSON.toJSONString(electricPileMonitor);
	}

	/**
	 * 故障电桩列表查询
	 * 
	 * @param request
	 * @param params
	 * @param pager
	 * @param model
	 * @param electricPileMonitor
	 * @return
	 */
	@RequestMapping("queryErrorPile")
	@ResponseBody
	public String queryErrorPile(@RequestParam Map<String, Object> params) {
		List<ElectricPileMonitor> electricPileList = webElectricPileMonitorService
				.queryErrorPile(params);
		return JSON.toJSONString(electricPileList);
	}

	/**
	 * 枪头详情
	 */
	@RequestMapping("headDetailUi")
	public String headDetailUi(@RequestParam Map<String, Object> params,
			Model model) throws UnsupportedEncodingException {
		model.addAttribute("params", params);
		return "backstage/monitor/pileDetail";
	}

	@RequestMapping("headDetail")
	@ResponseBody
	public String headDetail(@RequestParam Map<String, Object> params) {
		List<Map<String, Object>> headDetail = webElectricPileMonitorService
				.getHeadDetailByPointId(params);
		return JSON.toJSONString(headDetail);
	}

	/**
	 * 实时信息（交流）
	 */
	@RequestMapping("headNowDataUiAC")
	public String headNowDataACUi(@RequestParam Map<String, Object> params,
			Model model) {
		model.addAttribute("params", params);
		return "backstage/monitor/headNowDataAC";
	}

	/**
	 * 实时信息（直流）
	 */
	@RequestMapping("headNowDataUiDC")
	public String headNowDataUiDC(@RequestParam Map<String, Object> params,
			Model model) {
		model.addAttribute("params", params);
		return "backstage/monitor/headNowDataDC";
	}

	/**
	 * 源端电源模块数据
	 */
	@RequestMapping("powerModule")
	public String powerModule(@RequestParam Map<String, Object> params,
			Model model) {
		model.addAttribute("params", params);
		return "backstage/monitor/powerModuleData";
	}

	/**
	 * 充电曲线（交流）
	 */
	@RequestMapping("headChargeLineUiAC")
	public String headChargeLineUiAC(@RequestParam Map<String, Object> params,
			Model model) {
		model.addAttribute("params", params);
		return "backstage/monitor/headChargeLineAC";
	}

	/**
	 * 充电曲线（直流）
	 */
	@RequestMapping("headChargeLineUiDC")
	public String headChargeLineUiDC(@RequestParam Map<String, Object> params,
			Model model) {
		model.addAttribute("params", params);
		return "backstage/monitor/headChargeLineDC";
	}

	/**
	 * 历史记录详情（交流）
	 */
	@RequestMapping("headHistoryUiAC")
	public String headHistoryUiAC(@RequestParam Map<String, Object> params,
			Model model) {
		model.addAttribute("params", params);
		return "backstage/monitor/headHistoryAC";
	}

	/**
	 * 历史记录详情（直流）
	 */
	@RequestMapping("headHistoryUiDC")
	public String headHistoryUiDC(@RequestParam Map<String, Object> params,
			Model model) {
		model.addAttribute("params", params);
		return "backstage/monitor/headHistoryDC";
	}

	/**
	 * 枪头充电统计（交流）
	 */
	@RequestMapping("headChargeStatisticsUiAC")
	public String headChargeStatisticsUiAC(
			@RequestParam Map<String, Object> params, Model model) {
		model.addAttribute("params", params);
	
		//查询枪头累计充电统计数据-getChargeStatics_01
		Map<String, Object> map_01 = webElectricPileMonitorService
				.getChargeStatics_01(params);
		//如果查询数据为空，默认查询出的数据为0
		if (map_01 == null) {

			map_01 = webElectricPileMonitorService
					.getChargeStatics_demo(params);
		}
		//查询本年枪口充电统计-getChargeStatics_02
		Map<String, Object> map_02 = webElectricPileMonitorService
				.getChargeStatics_02(params);
		//如果查询数据为空，默认查询出的数据为0
		if (map_02 == null) {

			map_02 = webElectricPileMonitorService
					.getChargeStatics_demo(params);
		}
		//查询本月枪口充电统计-getChargeStatics_03
		Map<String, Object> map_03 = webElectricPileMonitorService
				.getChargeStatics_03(params);
		//如果查询数据为空，默认查询出的数据为0
		if (map_03 == null) {

			map_03 = webElectricPileMonitorService
					.getChargeStatics_demo(params);
		}
		//查询当天枪口充电统计-getChargeStatics_04
		Map<String, Object> map_04 = webElectricPileMonitorService
				.getChargeStatics_04(params);
		//如果查询数据为空，默认查询出的数据为0
		if (map_04 == null) {

			map_04 = webElectricPileMonitorService
					.getChargeStatics_demo(params);
		}
		//查询电桩Id，map_demo.pkId
		Map<String, Object> map_demo = webElectricPileMonitorService
				.getChargeStatics_demo(params);
		model.addAttribute("map_01", map_01);
		model.addAttribute("map_02", map_02);
		model.addAttribute("map_03", map_03);
		model.addAttribute("map_04", map_04);
		model.addAttribute("map_demo", map_demo);
		//枪头编号5位
		String headId=String.format("%05d", Integer.parseInt(params.get("headId").toString()));
		model.addAttribute("headId", headId);
		
		Date now = new Date();
		SimpleDateFormat dateFormat_01 = new SimpleDateFormat("yyyy");
		SimpleDateFormat dateFormat_02 = new SimpleDateFormat("yyyy.MM");
		SimpleDateFormat dateFormat_03 = new SimpleDateFormat("yyyy.MM.dd");
		//前台动态时间展示
		String between = map_01.get("ch01") + "-至今";
		String nowYear = dateFormat_01.format(now);
		String nowMonth = dateFormat_02.format(now);
		String nowDay = dateFormat_03.format(now);
       //时间注入
		model.addAttribute("nowYear", nowYear);
		model.addAttribute("nowMonth", nowMonth);
		model.addAttribute("nowDay", nowDay);
		model.addAttribute("between", between);

		return "backstage/monitor/headChargingStatisticsAC";
	}

	/**
	 * 枪头充电统计（直流）
	 */

	@RequestMapping("headChargeStatisticsUiDC")
	public String headChargeStatisticsUiDC(
			@RequestParam Map<String, Object> params, Model model) {
		model.addAttribute("params", params);
		//查询枪头累计充电统计数据-getChargeStatics_01
		Map<String, Object> map_01 = webElectricPileMonitorService
				.getChargeStatics_01(params);
		//如果查询数据为空，默认查询出的数据为0
		if (map_01 == null) {

			map_01 = webElectricPileMonitorService
					.getChargeStatics_demo(params);
		}
		//查询本年枪口充电统计-getChargeStatics_02
		Map<String, Object> map_02 = webElectricPileMonitorService
				.getChargeStatics_02(params);
		//如果查询数据为空，默认查询出的数据为0
		if (map_02 == null) {

			map_02 = webElectricPileMonitorService
					.getChargeStatics_demo(params);
		}
		//查询本月枪口充电统计-getChargeStatics_03
		Map<String, Object> map_03 = webElectricPileMonitorService
				.getChargeStatics_03(params);
		//如果查询数据为空，默认查询出的数据为0
		if (map_03 == null) {

			map_03 = webElectricPileMonitorService
					.getChargeStatics_demo(params);
		}
		//查询当天枪口充电统计-getChargeStatics_04
		Map<String, Object> map_04 = webElectricPileMonitorService
				.getChargeStatics_04(params);
		//如果查询数据为空，默认查询出的数据为0
		if (map_04 == null) {

			map_04 = webElectricPileMonitorService
					.getChargeStatics_demo(params);
		}
		//查询电桩Id，map_demo.pkId
		Map<String, Object> map_demo = webElectricPileMonitorService
				.getChargeStatics_demo(params);
		model.addAttribute("map_01", map_01);
		model.addAttribute("map_02", map_02);
		model.addAttribute("map_03", map_03);
		model.addAttribute("map_04", map_04);
		model.addAttribute("map_demo", map_demo);
		
		Date now = new Date();
		SimpleDateFormat dateFormat_01 = new SimpleDateFormat("yyyy");
		SimpleDateFormat dateFormat_02 = new SimpleDateFormat("yyyy.MM");
		SimpleDateFormat dateFormat_03 = new SimpleDateFormat("yyyy.MM.dd");
		//前台动态时间展示
		String between=map_01.get("ch01")+"-至今";
		String nowYear = dateFormat_01.format(now);
		String nowMonth = dateFormat_02.format(now);
		String nowDay = dateFormat_03.format(now);
        //时间注入
		model.addAttribute("nowYear", nowYear);
		model.addAttribute("nowMonth", nowMonth);
		model.addAttribute("nowDay", nowDay);
		model.addAttribute("between", between);
		String headId=String.format("%05d", Integer.parseInt(params.get("headId").toString()));
		model.addAttribute("headId",headId);
		
		return "backstage/monitor/headChargingStatisticsDC";
	}

	/**
	 * 枪头历史信息
	 * 
	 * @param request
	 * @param params
	 * @param pager
	 * @param model
	 * @param electricPileMonitor
	 * @return
	 */
	@RequestMapping("getHeadHistoryList")
	@ResponseBody
	public String getHeadHistoryList(@RequestParam Map<String, String> params) {
		return HttpRequest.post(MessageManager.getMessageManager()
				.getSystemProperties("hbaseUrl") + "/getOperationData", params);
	}

	/**
	 * 实时故障列表
	 * 
	 * @param request
	 * @param params
	 * @param pager
	 * @param model
	 * @param electricPileMonitor
	 * @return
	 */
	@RequestMapping("getErrorData")
	@ResponseBody
	public String getErrorData(@RequestParam Map<String, String> params,
			HttpServletRequest request) {
		String dataStr = HttpRequest.post(MessageManager.getMessageManager()
				.getSystemProperties("hbaseUrl") + "/getErrorData", params);
		String strTemp = dataStr.replace("{\"data\":", "");
		strTemp = strTemp.substring(0, strTemp.length() - 1);
		JSONArray array = JSON.parseArray(strTemp);
		JSONArray array1 = new JSONArray();
		if (array.size() > 0) {
			String codeStr = "";
			for (Object obj : array) {
				codeStr += ((JSONObject) obj).get("epCode") + ",";
			}
			TblUser loginUser = SessionMgr.getWebUser(request);
			params.put("userLevel", loginUser.getUserLevel().toString());
			params.put("userId", loginUser.getUserId().toString());
			params.put("codeStr", codeStr.substring(0, codeStr.length() - 1));
			List<String> codeList = electricPileMapService
					.queryErrorCodeListByUser(params);
			for (Object obj : array) {
				if (codeList.contains(((JSONObject) obj).get("epCode")))
					array1.add(obj);
			}
//			List<Map> mapList = new ArrayList<Map>();
//			for (String obj : codeList) {
//				Map map = new HashMap<String, String>();
//				map.put("epCode", obj);
//				map.put("status", 35);
//				array1.add(JSON.toJSON(map));
//			}
		}
		return array1.toJSONString();
	}

	/**
	 * 历史记录详情（直流）
	 */
	@RequestMapping("powerChargingLineUi")
	public String powerChargingLineUi(@RequestParam Map<String, Object> params,
			Model model) {
		model.addAttribute("params", params);
		return "backstage/monitor/powerChargeLine";
	}

	@RequestMapping("getElectricPileByCode")
	@ResponseBody
	public String getElectricPileByCode(@RequestParam Map<String, Object> params) {
		Map<String, Object> data = webElectricPileMonitorService
				.getElectricPileByCode(params);
		return JSON.toJSONString(data);
	}
}
