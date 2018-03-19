package com.wanma.controller.simple;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.controller.itf.ChargeGiftController;
import com.wanma.model.simple.PowerStation;
import com.wanma.service.PowerStationService;
import com.wanma.service.SimplePointService;
import com.wanma.support.common.AccessErrorResult;
import com.wanma.support.common.FailedResponse;

/**
 * 停简单-创建充电站、充电桩，绑定充电枪
 * @author lyh
 *
 */
@Controller
@RequestMapping("/simple")
public class SimplePointController {
	@Autowired
	private SimplePointService simpleService;
	@Autowired
	private PowerStationService psService;
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ChargeGiftController.class);

	/**
	 * 创建充电站
	 * @param request
	 * @return
	 */
	@RequestMapping("/createStation")
	@ResponseBody
	public String createStation(HttpServletRequest request) {
		String org = request.getParameter("org");		
		if (StringUtils.isBlank(org))
			return new FailedResponse(1001, "params error").toString();
		LOGGER.info("获取充电站信息！");
		String sps = simpleService.getPowerStationInfo(org);

		return sps;

	}
	
	
	/**
	 * 创建充电桩
	 * @param request
	 * @return
	 */
	@RequestMapping("/createPoint")
	@ResponseBody
	public String createPoint(HttpServletRequest request) {
		String org= request.getParameter("org");		
		if (StringUtils.isBlank(org))
			return new FailedResponse(1001, "params error").toString();
		LOGGER.info("获取充电桩信息！");
		String tcp=simpleService.getChargePileInfo(org);
		return tcp;
		
	}
	
	/**
	 * 绑定充电枪到充电桩
	 * @param request
	 * @return
	 */
	@RequestMapping("/bindGun")
	@ResponseBody
	public String bindGun(HttpServletRequest request) {
		String org= request.getParameter("org");
		if (StringUtils.isBlank(org))
			return new FailedResponse(1001, "params error").toString();
		LOGGER.info("获取充电枪信息！");
		String bingGunInfo=simpleService.getBindGunInfo(org);
		
		return bingGunInfo;
		
	}
	/**
	 * 创建单个充电站
	 * @param request
	 * @return
	 */
	@RequestMapping("/createSingleStation")
	@ResponseBody
	public String createSingleStation(HttpServletRequest request) {
		String org= request.getParameter("org");
		String stationId = request.getParameter("stationId");
		if (StringUtils.isBlank(org))
			return new FailedResponse(1001, "params error").toString();
		if (StringUtils.isBlank(stationId))
			return new FailedResponse(1001, "params error").toString();
		LOGGER.info("获取充电站信息！");
		PowerStation ps = new PowerStation();
		ps.setCpyCompanyNumber(Integer.parseInt(org));
		ps.setPkPowerstation(Integer.parseInt(stationId));
		int count = psService.getCountResult(ps);
		String ss = null;
		if (count != 0) {
			ss = simpleService.getSingleStationInfo(stationId);
		} else {
			ss = "查询失败，请核对公司标识与电站ID是否匹配！";
			ss = new AccessErrorResult(1001, ss).toString();
		}
		return ss;
	}

}
