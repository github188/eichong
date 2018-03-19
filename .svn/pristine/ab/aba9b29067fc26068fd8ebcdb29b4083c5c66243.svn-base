package com.wanma.controller;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.model.echarts.ChargeEchartsModel;
import com.bluemobi.product.model.echarts.ConsumptionEchartsModel;
import com.bluemobi.product.model.echarts.PeopleCountEchartsModel;
import com.bluemobi.product.model.echarts.PileEchartsModel;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblUser;
import com.wanma.service.CmsEchartDataCountService;
import com.wanma.service.impl.CmsEchartPeopleChargeServiceImpl;
import com.wanma.service.impl.CmsEchartPeopleConsumptionServiceImpl;
import com.wanma.service.impl.CmsEchartPeopleFromServiceImpl;
import com.wanma.service.impl.CmsEchartPeopleZcServiceImpl;
import com.wanma.service.impl.CmsEchartPileBespokeServiceImpl;
import com.wanma.service.impl.CmsEchartPileChargeServiceImpl;
import com.wanma.service.impl.CmsEchartPileChargedServiceImpl;
import com.wanma.service.impl.CmsEchartPileMapServiceImpl;
import com.wanma.service.impl.CmsEchartPileServiceImpl;

/**
 * @Description: 图表controller
 * @author wubc
 * @createTime：2015-6-29
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/chart")
public class CmsChartController {
	@Autowired
	private CmsEchartPeopleZcServiceImpl peopleZcService;
	@Autowired
	private CmsEchartPeopleFromServiceImpl peopleFromService;
	@Autowired
	private CmsEchartPeopleConsumptionServiceImpl consumptionService;
	@Autowired
	private CmsEchartPeopleChargeServiceImpl chargeService;
	@Autowired
	private CmsEchartPileServiceImpl echartPileService;
	@Autowired
	private CmsEchartPileChargedServiceImpl echartPileChargedService;
	@Autowired
	private CmsEchartPileChargeServiceImpl echartPileChargeService;
	@Autowired
	private CmsEchartPileMapServiceImpl echartPileMapService;
	@Autowired
	private CmsEchartPileBespokeServiceImpl echartPileBespokeService;
	@Autowired
	private CmsEchartDataCountService dataCountServiceImpl;

	@RequestMapping("/peopleEcharts")
	public String peopleEcharts() {
		return "echarts/peopleCountChart";
	}

	@RequestMapping("/peopleConsumptionEcharts")
	public String peopleConsumptionEcharts() {
		return "echarts/peopleConsumptionChart";
	}

	@RequestMapping("/peopleChargeEcharts")
	public String peopleChargeEcharts() {
		return "echarts/peopleChargeChart";
	}

	@RequestMapping("/getPeopleZcDate")
	@ResponseBody
	public String getPeopleZcDate(
			@ModelAttribute("peopleCountEchartsModel") PeopleCountEchartsModel peopleCountEchartsModel,
			HttpServletRequest request)
			throws URISyntaxException {
		TblUser loginUser = SessionMgr.getWebUser(request);
		peopleCountEchartsModel.setUserId(loginUser.getUserId().toString());
		return peopleZcService.getJsonData(peopleCountEchartsModel);
	}

	@RequestMapping("/getPeopleFromDate")
	@ResponseBody
	public String getPeopleFromDate(
			@ModelAttribute("peopleCountEchartsModel") PeopleCountEchartsModel peopleCountEchartsModel,
			HttpServletRequest request)
			throws URISyntaxException {
		TblUser loginUser = SessionMgr.getWebUser(request);
		peopleCountEchartsModel.setUserId(loginUser.getUserId().toString());
		return peopleFromService.getJsonData(peopleCountEchartsModel);
	}

	@RequestMapping("/getPeopleConsumptionDate")
	@ResponseBody
	public String getPeopleConsumptionDate(
			@ModelAttribute("chartsModel") ConsumptionEchartsModel chartsModel,HttpServletRequest request)
			throws URISyntaxException {
		TblUser loginUser = SessionMgr.getWebUser(request);
		chartsModel.setUserId(loginUser.getUserId().toString());
		return consumptionService.getJsonData(chartsModel);
	}

	@RequestMapping("/getPeopleChargeDate")
	@ResponseBody
	public String getPeopleChargeDate(
			@ModelAttribute("chartsModel") ChargeEchartsModel chartsModel,HttpServletRequest request)
			throws URISyntaxException {
		TblUser loginUser = SessionMgr.getWebUser(request);
		chartsModel.setUserId(loginUser.getUserId().toString());
		return chargeService.getJsonData(chartsModel);
	}

	@RequestMapping("/pileEcharts")
	public String pileEcharts() {
		return "echarts/pileEcharts";
	}

	@RequestMapping("/getPileInfoData")
	@ResponseBody
	public String getPileInfoData(
			@ModelAttribute("pileEchartsModel") PileEchartsModel pileEchartsModel,
			HttpServletRequest request) throws URISyntaxException {
		setUserLimit(pileEchartsModel, request);
		TblUser loginUser = SessionMgr.getWebUser(request);
		pileEchartsModel.setUserIdForShow(loginUser.getUserId().toString());
		return echartPileService.getJsonData(pileEchartsModel);
	}

	@RequestMapping("/pileBespokeEcharts")
	public String pileBespokeEcharts() {
		return "echarts/pileBespokeEcharts";
	}

	@RequestMapping("/getPileBespokeDate")
	@ResponseBody
	public String getPileBespokeDate(
			@ModelAttribute("pileEchartsModel") PileEchartsModel pileEchartsModel,
			HttpServletRequest request) throws URISyntaxException {
		setUserLimit(pileEchartsModel, request);
		return echartPileBespokeService.getJsonData(pileEchartsModel);
	}

	@RequestMapping("/pileChargeEcharts")
	public String pileChargeEcharts() {
		return "echarts/pileChargeEcharts";
	}

	@RequestMapping("/getPileChargeData")
	@ResponseBody
	public String getPileChargeData(
			@ModelAttribute("pileEchartsModel") PileEchartsModel pileEchartsModel,
			HttpServletRequest request) throws URISyntaxException {
		setUserLimit(pileEchartsModel, request);
		return echartPileChargeService.getJsonData(pileEchartsModel);
	}

	@RequestMapping("/pileChargedEcharts")
	public String pileChargedEcharts() {
		return "echarts/pileChargedEcharts";
	}

	@RequestMapping("/getPileChargedData")
	@ResponseBody
	public String getPileChargedData(
			@ModelAttribute("pileEchartsModel") PileEchartsModel pileEchartsModel,
			HttpServletRequest request) throws URISyntaxException {
		setUserLimit(pileEchartsModel, request);
		return echartPileChargedService.getJsonData(pileEchartsModel);
	}

	@RequestMapping("/pileMapEcharts")
	public String pileMapEcharts() {
		return "echarts/pileMapEcharts";
	}

	@RequestMapping("/getPileMapData")
	@ResponseBody
	public String getPileMapData(
			@ModelAttribute("pileEchartsModel") PileEchartsModel pileEchartsModel,
			HttpServletRequest request) throws URISyntaxException {
		setUserLimit(pileEchartsModel, request);
		return echartPileMapService.getJsonData(pileEchartsModel);
	}

	@RequestMapping("/index")
	public String index() {
		return "echarts/echartsIndex";
	}

	@RequestMapping("/getZcPeopleCount")
	@ResponseBody
	public String getZcPeopleCount(HttpServletRequest request) throws URISyntaxException {
		TblUser loginUser = SessionMgr.getWebUser(request);
			return dataCountServiceImpl.getZcPeopleCount(loginUser.getUserId());
	}

	@RequestMapping("/getCzPeopleCount")
	@ResponseBody
	public String getCzPeopleCount(HttpServletRequest request) throws URISyntaxException {
		TblUser loginUser = SessionMgr.getWebUser(request);
			return dataCountServiceImpl.getCzPeopleCount(loginUser.getUserId());
	}

	@RequestMapping("/getAllPileCount")
	@ResponseBody
	public String getAllPileCount(HttpServletRequest request) throws URISyntaxException {
		TblUser loginUser = SessionMgr.getWebUser(request);
			return dataCountServiceImpl.getAllPileCount(loginUser.getUserId());
	}

	@RequestMapping("/getAichongPileCount")
	@ResponseBody
	public String getAichongPileCount(HttpServletRequest request) throws URISyntaxException {
		TblUser loginUser = SessionMgr.getWebUser(request);
			return dataCountServiceImpl.getAichongPileCount(loginUser.getUserId());
	}

	@RequestMapping("/chekcUser")
	@ResponseBody
	public String chekcUser(HttpServletRequest request)
			throws URISyntaxException {
		TblUser loginUser = SessionMgr.getWebUser(request);
		JSONObject obj = new JSONObject();
		obj.put("userLevel", loginUser.getUserLevel());
		return obj.toString();
	}

	private void setUserLimit(PileEchartsModel pileEchartsModel,
			HttpServletRequest request) {
		TblUser tblUser = SessionMgr.getWebUser(request);
		pileEchartsModel.setUserIdForShow(tblUser.getUserId().toString());
		if (tblUser.getUserLevel() != null && tblUser.getUserLevel() > 2)
			pileEchartsModel.setUserId(tblUser.getUserId()+"");
			pileEchartsModel.setUserLevel(tblUser.getUserLevel());
	}
}
