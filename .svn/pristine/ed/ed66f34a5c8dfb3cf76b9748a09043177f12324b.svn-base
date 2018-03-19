package com.wanma.controller.tpi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.model.TblElectricpile;
import com.wanma.service.TblElectricpileHeadService;
import com.wanma.service.TblElectricpileService;
import com.wanma.service.TblUserService;
import com.wanma.support.common.FailedResponse;
import com.wanma.support.common.RedisService;
import com.wanma.support.common.ResultResponse;
import com.wanma.support.common.WanmaConstants;
import com.wanma.support.utils.HttpUtil;

/**
 * @Description: 电桩管理控制层
 * @author wbc
 * @createTime：2015-11-19 16:25:05
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/tpi/electric")
public class TpiElectricpileController {
	@Autowired
	private TblElectricpileService electricpileService;
	@Autowired
	private TblElectricpileHeadService electricpileHeadService;
	@Autowired
	private TblUserService userService;

	@Autowired
	private RedisService redisService;

	/**
	 * @Description: 电桩列表查询
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/getEpList")
	@ResponseBody
	public String getElectricpileListForMap(HttpServletRequest request) {
		String cityCode = request.getParameter("cityCode");
		String reqTime = request.getParameter("reqTime");
		TblElectricpile electric = new TblElectricpile();
		electric.setElpiOwncitycode(StringUtils.isNotBlank(cityCode) ? cityCode
				: null);
		electric.setElpiUpdatedate(StringUtils.isNotBlank(reqTime) ? reqTime
				: null);
		List<TblElectricpile> electricPileList = electricpileService
				.getElectricpileListForMap(electric);
		List<Map> tempList = new ArrayList<Map>();
		Map tempMap = null;
		for (TblElectricpile p : electricPileList) {
			tempMap = new HashMap();
			tempMap.put("electricId", p.getPkElectricpile());
			tempMap.put("electricType", p.getElectricType());
			tempMap.put("electricState", p.getElpiState());
			tempMap.put("longitude", p.getElpiLongitude());
			tempMap.put("latitude", p.getElpiLatitude());
			tempMap.put("cityCode", p.getElpiOwncitycode());
			tempMap.put("electricName", p.getElpiElectricpilename());
			tempMap.put("electricAddress", p.getElpiElectricpileaddress());
			tempMap.put("isAppoint", p.getElpiIsappoint());
			tempList.add(tempMap);
		}
		return new ResultResponse(tempList).toString();
	}

	/**
	 * @Description: 地图锚点站、桩简介
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/getAnchorSummary")
	@ResponseBody
	public String getAnchorSummary(HttpServletRequest request) {
		String electricId = request.getParameter("electricId");
		// electricType:1电桩，2电站
		String electricType = request.getParameter("electricType");
		if (StringUtils.isBlank(electricId)
				|| StringUtils.isBlank(electricType)) {
			return new FailedResponse(1001, "params error").toString();
		}
		String lng = request.getParameter("lng");
		String lat = request.getParameter("lat");
		Map params = new HashMap();
		params.put("lng", lng);
		params.put("lat", lat);
		params.put("eid", electricId);
		params.put("type", electricType);
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_ELECTRIC_DETAILMAP,
				params);
	}

}
