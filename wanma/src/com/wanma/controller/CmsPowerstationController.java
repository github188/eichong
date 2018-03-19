package com.wanma.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.model.Area;
import com.bluemobi.product.model.City;
import com.bluemobi.product.model.Province;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.service.AreaService;
import com.bluemobi.product.service.CityService;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.app.dao.TblElectricpileMapper;
import com.wanma.app.service.ElectricPileListService;
import com.wanma.common.CacheEntity;
import com.wanma.common.CacheManager;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.SHStopService;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.PowerStationDetail;
import com.wanma.model.TblConfigcontent;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblElectricpileheadForSH;
import com.wanma.model.TblPowerstation;
import com.wanma.model.TblRateinformation;
import com.wanma.model.TblUser;
import com.wanma.model.TcbElectric;
import com.wanma.service.CmsCommitLogService;
import com.wanma.service.CmsConfigcontentService;
import com.wanma.service.CmsPowerStationDetailService;
import com.wanma.service.CmsPowerstationService;
import com.wanma.service.impl.CmsRateInfoServiceImpl;
import com.wanma.web.support.utils.ApiUtil;
import com.wanma.web.support.utils.HttpsUtil;

/**
 * @Description: 充电点管理控制器
 * @author songjf
 * @createTime：2015-3-31 下午04:02:18
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/powerstation")
public class CmsPowerstationController {
	// 日志输出对象
	private static Logger log = Logger
			.getLogger(CmsPowerstationController.class);
	// 充电点处理对象
	@Autowired
	private CmsPowerstationService powerstationService;
	@Autowired
	private CmsConfigcontentService msConfigcontentService;
	@Autowired
	private ElectricPileListService electricPileListService;
	@Autowired
	private TblElectricpileMapper tblElectricpileMapper;
	@Autowired
	private CmsPowerStationDetailService powerStationDetailService;
	@Autowired
	private CityService cityService;
	@Autowired
	private AreaService areaService;
	@Autowired
	CmsCommitLogService commitLogService;
	@Autowired
	private CmsRateInfoServiceImpl rateInfoService;
	/**
	 * @Title: powersList
	 * @Description: 充电点列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/getPowersList")
	public String getPowersList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblPowerstation powerstation, Model model,
			HttpServletRequest request) {
		log.info("************获取充电点列表-start************");
		// 获取登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);

		// 个体/纯商家只能查询所属电桩
		if (loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_BUSINESS) {
			powerstation.setPoStUserName(loginUser.getUserAccount());
		} else if (loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_BUSINESS_NORMAL) {
			powerstation.setPoStUserName(loginUser.getUserId() + "");
		}
		powerstation.setPoStCreateUserId(loginUser.getUserId().toString());
		powerstation.setUserLevel(loginUser.getUserLevel().toString());
		// 充电点总数
		long total = powerstationService.selectPowerCount(powerstation);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置查询参数
		powerstation.setPager(pager);
		// 获取充电点列表
		List<TblPowerstation> powerstationList = powerstationService
				.findPowers(powerstation);
		pager.setTotal(total);

		// 将查询结果显示到画面
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		model.addAttribute("loginUserLevel", loginUser.getUserLevel());
		model.addAttribute("powerstationList", powerstationList);
		model.addAttribute("powerstation", powerstation);
		model.addAttribute("pager", pager);
		log.info("************获取充电点列表-end************");
		// 跳转至车型列表页面
		return "backstage/powerStation/powerStation-list";
	}

	/**
	 * @Title: powersList
	 * @Description: 充电点列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/getStationDetail")
	public String getStationDetail(
			@ModelAttribute TblPowerstation powerstation, Model model) {
		// 电桩Id
		PowerStationDetail powerStationDetail = new PowerStationDetail();
		try {
			if (powerstation.getPkPowerstation() != null) {
				powerStationDetail = powerStationDetailService
						.getPowerStationDetail(powerstation);
			}

		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取充电点详情数据失败", e);
		}
		model.addAttribute("station", powerStationDetail);
		// 跳转至车型列表页面
		return "backstage/powerStation/powerStation-detail";
	}

	/**
	 * @Title: powersList
	 * @Description: 待审批列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/getExaminePowersList")
	public String getExaminePowersList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblPowerstation powerstation, Model model,
			HttpServletRequest request) {

		// 登陆用户
		TblUser user = SessionMgr.getWebUser(request);
		powerstation.setPostStatus(5);
		// 获取充电点列表
		powerstation.setUserLevel(user.getUserLevel().toString());
		powerstation.setPoStCreateUserId(user.getUserId().toString());
		// 充电点总数
		long total = powerstationService.selectPowerCount(powerstation);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置查询参数
		powerstation.setPager(pager);
		List<TblPowerstation> powerstationList = powerstationService
				.findPowers(powerstation);
		pager.setTotal(total);

		// 将查询结果显示到画面
		model.addAttribute("powerstationList", powerstationList);
		model.addAttribute("powerstation", powerstation);
		model.addAttribute("pager", pager);
		model.addAttribute("loginUserLevel", user.getUserLevel());

		// 跳转至车型列表页面
		return "backstage/powerStation/powerStation-examine-list";
	}
	/**
	 * 充电点上线驳回页面
	 */
	@RequestMapping(value = "/onlineReasonUi")
	public String onlineReasonUi(
			@RequestParam("id") String pkPowerstation, Model model) {
		model.addAttribute("pkPowerstation", pkPowerstation);
		// 跳转至用户选择画面
		return "backstage/powerStation/powerStation-onReason-list";
	}

	/**
	 * @Title: powersList
	 * @Description: 上线审批列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/getOnLinePowersList")
	public String getOnLinePowersList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblPowerstation powerstation, Model model,
			HttpServletRequest request) {
		// 登陆用户
		TblUser user = SessionMgr.getWebUser(request);
		powerstation.setPostStatus(12);
		// 获取充电点列表
		powerstation.setUserLevel(user.getUserLevel().toString());
		powerstation.setPoStCreateUserId(user.getUserId().toString());
		// 充电点总数
		long total = powerstationService.selectPowerCount(powerstation);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置分页对象
		powerstation.setPager(pager);
		List<TblPowerstation> powerstationList = powerstationService
				.findPowers(powerstation);
		pager.setTotal(total);

		// 将查询结果显示到画面
		model.addAttribute("powerstationList", powerstationList);
		model.addAttribute("powerstation", powerstation);
		model.addAttribute("pager", pager);
		model.addAttribute("loginUserLevel", user.getUserLevel());

		// 跳转至车型列表页面
		return "backstage/powerStation/powerStation-onLine-list";
	}
	/**
	 * 分享申请，针对充电点状态为专属的数据修改为分享申请中
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */

	@RequestMapping(value = "/shareApplication")
	@ResponseBody
	public String shareApplication(
			@RequestParam("ids") String elpiElectricpilecode,
			@RequestParam("changeType") String changeType) {
		try {
			if (changeType.equalsIgnoreCase("toApplication")) {// 状态重置--备注：elpiElectricpilecode传递过来的是主键
				return toApplication(elpiElectricpilecode).toJSONString();
			}
		} catch (Exception e) {
			// 出错日志
			log.error("操作失败", e);
		}
		// 返回处理结果信息
		return new DwzAjaxResult("300", "操作失败", "getPowersList",
				"", "").toJSONString();
	}

	/**
	 * 电桩分享申请
	 * 
	 * @return
	 */
	private DwzAjaxResult toApplication(String elpiElectricpilecode) {

		DwzAjaxResult result = new DwzAjaxResult("200", "修改成功",
				"getPowersList", "", "");
		//String errorCode = "";
		String[] electricpilecode = null;
		if (elpiElectricpilecode.lastIndexOf(",") > 0) {
			electricpilecode = elpiElectricpilecode.split(",");
		} else {
			electricpilecode = new String[] { elpiElectricpilecode };
		}
		
		for (int j = 0; j < electricpilecode.length; j++) {
			
			TblPowerstation tblPowerstation =new TblPowerstation();
			tblPowerstation.setPkPowerstation(Integer
					.parseInt(electricpilecode[j]));
			tblPowerstation=powerstationService.getPowerById(tblPowerstation);
			if (tblPowerstation.getPostStatus() != 10) {
				DwzAjaxResult result_error = new DwzAjaxResult("300",
						"请选择状态为专属的数据", "getPowersList", "", "");
				return result_error;
			}
		}
		for (int i = 0; i < electricpilecode.length; i++) {
			TblElectricpile tblElectricpile = new TblElectricpile();
			tblElectricpile.setRelevancePowerStation(Integer
					.parseInt(electricpilecode[i]));
				
			tblElectricpile.setElpiState(12);
			List<Map<String, Object>> electricList = (List<Map<String, Object>>) electricPileListService
					.findRelevancePowerStation(tblElectricpile);
			//充电点下电桩也一起重置为分享中状态
			for (Map<String, Object> pile : electricList) {
				electricPileListService.updateElectricPileSate(
						pile.get("elpiElectricpilecode").toString(), 12,"0");
			}
			powerstationService.changeStateById(
					JudgeNullUtils.nvlStr(electricpilecode[i]),12);
		}
		return result;
	}

	/***
	 * 修改充电点状态
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changePowersState")
	@ResponseBody
	public String changePowersState(@RequestParam("ids") String powersId,
			@RequestParam("changeType") String changeType) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		String message = null;
		try {

			if (changeType.equalsIgnoreCase("onLinePage")) {// 上线通过
				return onLinePage(powersId).toJSONString();
			} else if (changeType.equalsIgnoreCase("examineOkPage")) {// 审批通过
				return examineOkPage(powersId).toJSONString();
			} else if (changeType.equalsIgnoreCase("examinePage")) {// 提交审批
				return examinePage(powersId).toJSONString();
			} else if (changeType.equalsIgnoreCase("offLinePage")) {// 离线申请
				return offLinePage(powersId).toJSONString();
			}

		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			message = message + "失败";
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", message,
					"getOnLinePowersList", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	
	/***
	 * 修改充电点状态为分享状态，针对2，4版本增加分享申请中，专属状态转变为草稿状态
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changePowersState_02")
	@ResponseBody
	public String changePowersState_02(@RequestParam("ids") String powersId,
			@RequestParam("changeType") String changeType) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		String message = null;
		try {

			if (changeType.equalsIgnoreCase("toInit")) {// 上线通过
				return toInit(powersId).toJSONString();
			} 

		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			message = message + "失败";
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", message,
					"getOnLinePowersList", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	
	/**
	 * 充电点状态重置 electricStateType=0;
	 * 
	 * @return
	 */
	private DwzAjaxResult toInit(String elpiElectricpilecode) {
		DwzAjaxResult result = new DwzAjaxResult("200", "修改成功",
				"getPowersList", "", "");
		//String errorCode = "";
		String[] electricpilecode = null;
		if (elpiElectricpilecode.lastIndexOf(",") > 0) {
			electricpilecode = elpiElectricpilecode.split(",");
		} else {
			electricpilecode = new String[] { elpiElectricpilecode };
		}
		for (int i = 0; i < electricpilecode.length; i++) {
			
			TblPowerstation tblPowerstation =new TblPowerstation();
			tblPowerstation.setPkPowerstation(Integer
					.parseInt(electricpilecode[i]));
			tblPowerstation=powerstationService.getPowerById(tblPowerstation);
			if (tblPowerstation.getPostStatus() != 10) {
				DwzAjaxResult result_error = new DwzAjaxResult("300",
						"请选择状态为专属的数据", "getPowersList", "", "");
				return result_error;
			}
			//充电点下电桩也一起重置为草稿状态
			/*for (Map<String, Object> pile : electricList) {
				electricPileListService.updateElectricPileSate(
						pile.get("elpiElectricpilecode").toString(), 0,"0");
			}*/
			
		}
		for (int j = 0; j < electricpilecode.length; j++) {
			powerstationService.changeStateById(
					JudgeNullUtils.nvlStr(electricpilecode[j]),0);
		}
		return result;

	}
	/***
	 * 删除充电点
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/removePower")
	@ResponseBody
	public String removePower(@RequestParam("ids") String ids) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		String message = null;
		try {
			return removePowerOparate(ids).toJSONString();
		} catch (Exception e) {
			// 出错日志
			log.error("操作失败", e);
			System.out.print("操作失败" + e);
			message = message + "失败";
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", message, "getPowersList", "",
					"");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	private DwzAjaxResult removePowerOparate(String powercode) {
		DwzAjaxResult result = new DwzAjaxResult("200", "操作成功",
				"getPowersList", "", "");
		TblElectricpile tblElectricpile = new TblElectricpile();
		String[] electricpilecode = null;
		if (powercode.lastIndexOf(",") > 0) {// 多个审批
			electricpilecode = powercode.split(",");
		} else {
			electricpilecode = new String[] { powercode };
		}
		String error1 = "", error2 = "";
		for (int i = 0; i < electricpilecode.length; i++) {
			// 获取充电点绑定电桩数量
			tblElectricpile.setElPiRelevancePowerStation(JudgeNullUtils
					.nvlInteger(electricpilecode[i]));
			int eleCount = tblElectricpileMapper
					.getElectricpileByPowerId(tblElectricpile);
			// 获取充电点信息
			TblPowerstation tblPowerstation = new TblPowerstation();
			tblPowerstation.setPkPowerstation(JudgeNullUtils
					.nvlInteger(electricpilecode[i]));
			tblPowerstation = powerstationService
					.getPowersById(tblPowerstation);
			if (eleCount >= 1) {
				error1 += electricpilecode[i] + ",";
			}
			if (tblPowerstation.getPostStatus() != 0) {
				error2 += electricpilecode[i] + ",";
			}
		}
		String errorCode = "";
		if (StringUtils.isNotBlank(error1)) {
			errorCode += "已绑定电桩：" + error1;
		}
		if (StringUtils.isNotBlank(error2)) {
			errorCode += "不是草稿状态:" + error2;
		}

		if (StringUtils.isNotBlank(errorCode)) {
			result.setStatusCode("300");
			result.setMessage(StringUtils.removeEnd(errorCode, ","));
			return result;
		}
		for (int i = 0; i < electricpilecode.length; i++) {
			powerstationService.removePower(JudgeNullUtils
					.nvlInteger(electricpilecode[i]));
		}
		return result;
	}

	/**
	 * 充电点提交审批
	 * 
	 * @return
	 */
	private DwzAjaxResult examinePage(String elpiElectricpilecode) {
		DwzAjaxResult result = new DwzAjaxResult("200", "操作成功",
				"getPowersList", "", "");
		String[] electricpilecode = null;
		if (elpiElectricpilecode.lastIndexOf(",") > 0) {
			electricpilecode = elpiElectricpilecode.split(",");
		} else {
			electricpilecode = new String[] { elpiElectricpilecode };
		}
		String errorCode = "";
		for (int i = 0; i < electricpilecode.length; i++) {
			TblPowerstation tblPowerstation = new TblPowerstation();
			tblPowerstation.setPkPowerstation(Integer
					.parseInt(electricpilecode[i]));
			tblPowerstation = powerstationService
					.getPowersById(tblPowerstation);
			if (tblPowerstation.getPostStatus() != 0) {// 草稿状态才能提交审批
				errorCode += electricpilecode[i] + ",";
			}
		}
		if (StringUtils.isNotBlank(errorCode)) {
			result.setStatusCode("300");
			result.setMessage("不是草稿状态:" + StringUtils.removeEnd(errorCode, ","));
			return result;
		}
		for (int i = 0; i < electricpilecode.length; i++) {
			powerstationService.changeStateById(
					JudgeNullUtils.nvlStr(electricpilecode[i]), 5);
		}
		return result;
	}

	/**
	 * 充电点离线申请
	 * 
	 * @return
	 */
	private DwzAjaxResult offLinePage(String elpiElectricpilecode) {
		DwzAjaxResult result = new DwzAjaxResult("200", "操作成功",
				"getPowersList", "", "");
		String[] electricpilecode = null;
		if (elpiElectricpilecode.lastIndexOf(",") > 0) {
			electricpilecode = elpiElectricpilecode.split(",");
		} else {
			electricpilecode = new String[] { elpiElectricpilecode };
		}
		String errorCode = "";
		for (int i = 0; i < electricpilecode.length; i++) {
			TblPowerstation tblPowerstation = new TblPowerstation();
			tblPowerstation.setPkPowerstation(Integer
					.parseInt(electricpilecode[i]));
			tblPowerstation = powerstationService
					.getPowersById(tblPowerstation);
			if (tblPowerstation.getPostStatus() != 15) {// 上线桩体才能离线申请
				errorCode += electricpilecode[i] + ",";
			}
		}
		if (StringUtils.isNotBlank(errorCode)) {
			result.setStatusCode("300");
			result.setMessage("不是分享状态:" + StringUtils.removeEnd(errorCode, ","));
			return result;
		}
		for (int i = 0; i < electricpilecode.length; i++) {
			powerstationService.changeStateById(
					JudgeNullUtils.nvlStr(electricpilecode[i]), 10);
		}
		return result;

	}

	
	
	
	
	/**
	 * 充电点上线审批 electricStateType=15;
	 * 
	 * @return
	 */
	private DwzAjaxResult onLinePage(String elpiElectricpilecode) {
		DwzAjaxResult result = new DwzAjaxResult("200", "上线成功",
				"getOnLinePowersList", "", "");
		String errorCode = "";
		String[] electricpilecode = null;
		if (elpiElectricpilecode.lastIndexOf(",") > 0) {
			electricpilecode = elpiElectricpilecode.split(",");
		} else {
			electricpilecode = new String[] { elpiElectricpilecode };
		}
		for (int i = 0; i < electricpilecode.length; i++) {
			TblElectricpile tblElectricpile = new TblElectricpile();
			tblElectricpile.setRelevancePowerStation(Integer
					.parseInt(electricpilecode[i]));
			tblElectricpile.setElpiState(15);
			List<Map<String, Object>> electricList = (List<Map<String, Object>>) electricPileListService
					.findRelevancePowerStation(tblElectricpile);
			for (Map<String, Object> pile : electricList) {
				electricPileListService.updateElectricPileSate(
						pile.get("elpiElectricpilecode").toString(), 15,"0");
			}
			powerstationService.changeStateById(
					JudgeNullUtils.nvlStr(electricpilecode[i]), 15);
		}
		return result;

	}

	/**
	 * 充电点通过审批
	 * 
	 * @return
	 */
	private DwzAjaxResult examineOkPage(String elpiElectricpilecode) {
		DwzAjaxResult result = new DwzAjaxResult("200", "审批成功",
				"getExaminePowersList", "", "");
		String[] electricpilecode = null;
		if (elpiElectricpilecode.lastIndexOf(",") > 0) {
			electricpilecode = elpiElectricpilecode.split(",");
		} else {
			electricpilecode = new String[] { elpiElectricpilecode };
		}
		String errorCode = "";
		if (StringUtils.isNotBlank(errorCode)) {
			result.setStatusCode("300");
			result.setMessage(StringUtils.removeEnd(errorCode, ","));
			return result;
		}
		for (int i = 0; i < electricpilecode.length; i++) {
			powerstationService.changeStateById(
					JudgeNullUtils.nvlStr(electricpilecode[i]), 10);
		}
		return result;

	}

	/**
	 * 电桩申请驳回页面
	 */
	@RequestMapping(value = "/examineReasonUi")
	public String examineReasonUi(@RequestParam("id") String powersId,
			Model model) {
		model.addAttribute("powersId", powersId);
		// 跳转至用户选择画面
		return "backstage/powerStation/powerStation-examineReason-list";
	}
	
	/***
	 * 电桩上线驳回
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/onReason")
	@ResponseBody
	public String onReason(TblPowerstation tblPowerstation,
			HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		String ids = request.getParameter("ids");
		String[] idArr = ids.split(",");
		try {

			if (StringUtils.isBlank(tblPowerstation.getRejectionReason())
					|| tblPowerstation.getRejectionReason().equals(
							"请输入回驳原因")) {
				dwzResult = new DwzAjaxResult("300", "请输入回驳原因",
						"getOnLinePowersList", "", "");
				return new JsonObject(dwzResult).toString();
			}
			
			
			for (String s : idArr) {
				TblElectricpile tblElectricpile = new TblElectricpile();
				tblElectricpile.setRelevancePowerStation(Integer
						.parseInt(s));
				List<Map<String, Object>> electricList = (List<Map<String, Object>>) electricPileListService
						.findRelevancePowerStation(tblElectricpile);
				//充电点下电桩也一起重置为分享中状态
				for (Map<String, Object> pile : electricList) {
					electricPileListService.updateElectricPileSate(
							pile.get("elpiElectricpilecode").toString(), 10,"0");
				}
				powerstationService.changeStateById_02(
						JudgeNullUtils.nvlStr(Integer
								.parseInt(s)),10,tblPowerstation.getRejectionReason());
				

			}
			dwzResult = new DwzAjaxResult("200", "驳回成功",
					"getOnLinePowersList", "closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "驳回失败", "getOnLinePowersList",
					"", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/***
	 * 电桩驳回
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/examineReason")
	@ResponseBody
	public String examineReason(TblPowerstation tblPowerstation,
			HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		String ids = request.getParameter("ids");
		String[] idArr = ids.split(",");
		try {
			if (StringUtils.isBlank(tblPowerstation.getRejectionReason())
					|| tblPowerstation.getRejectionReason().equals("请输入回驳原因")) {
				dwzResult = new DwzAjaxResult("300", "请输入回驳原因",
						"examineReasonPage", "", "");
				return new JsonObject(dwzResult).toString();
			}
			for (String s : idArr) {
				tblPowerstation.setPkPowerstation(new Integer(s));
				tblPowerstation.setPostStatus(0);
				powerstationService
						.changeElectricPileExamineReason(tblPowerstation);
			}
			dwzResult = new DwzAjaxResult("200", "驳回成功",
					"getExaminePowersList", "closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "驳回失败", "examineReasonPage",
					"", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/***
	 * 添加充电点初始化界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addPowersUi")
	public String addPowersUi(Model model, HttpServletRequest request) {
		// 系统初始化缓存数据
				CacheEntity provinceCache = CacheManager
						.getCacheInfo("provinceCodeList");
				List<Province> provinceCodeList = (List<Province>) provinceCache
						.getValue();
				// 获取省份列表
				model.addAttribute("provinceCodeList", provinceCodeList);
		return "backstage/powerStation/powerStation-add";
	}
	/**
	 * 城市地区代码
	 * 
	 * @author xiay
	 * @return Object
	 * @throws 无
	 */
	@RequestMapping("/getCityCode")
	@ResponseBody
	public Object getCityCode(
			@RequestParam(value = "proviceId") String proviceId) {
		try {
			// 身份格式02|上海 需特殊处理
			List<City> cities = cityService.getCityList1(proviceId);
			return JSONObject.toJSONString(cities);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e);
		}
		return null;
	}

	/**
	 * 城市区县代码
	 * 
	 * @author xiay
	 * @return Object
	 * @throws 无
	 */
	@RequestMapping("/getCountyCode")
	@ResponseBody
	public Object getCountyCode(@RequestParam(value = "cityId") String cityId) {
		// 身份格式02|上海 需特殊处理
		List<Area> areas = areaService.getAreaList1(cityId);
		return JSONObject.toJSONString(areas);
	}
	/***
	 * 添加充电点
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addPowers")
	@ResponseBody
	public String addPowers(
			@RequestParam("tblElectricpile.pkElectricpile") String postEleids,
			TblPowerstation tblPowerstation,
			@RequestParam(value = "listImage", required = false) MultipartFile[] listImage,
			@RequestParam(value = "detailImage", required = false) MultipartFile[] detailImage,
			HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {

			// 获取登陆用户
			TblUser loginUser = SessionMgr.getWebUser(request);
			tblPowerstation.setPostEleids(postEleids);
			if (checkLongitudeLatitude(JudgeNullUtils.nvlStr(tblPowerstation
					.getPostLongitude()))) {
				dwzResult = new DwzAjaxResult("300", "经度最多小数点前4位小数点后6位",
						"electricAddPage", "", "");
				return new JsonObject(dwzResult).toString();
			}
			if (checkLongitudeLatitude(JudgeNullUtils.nvlStr(tblPowerstation
					.getPostLatitude()))) {
				dwzResult = new DwzAjaxResult("300", "纬度最多小数点前4位小数点后6位",
						"electricAddPage", "", "");
				return new JsonObject(dwzResult).toString();
			}
//			/** 新增电桩省市区(chengym20150609) start ***/
			String province = tblPowerstation.getPostOwnProvinceCode();
			if (!StringUtils.isBlank(province)) {
				if (StringUtils.isBlank(tblPowerstation.getPostOwnCityCode())) {
					dwzResult = new DwzAjaxResult("300", "请填写城市",
							"electricAddPage", "", "");
					return new JsonObject(dwzResult).toString();
				}
				if (StringUtils.isBlank(tblPowerstation.getPostOwnCountyCode())) {
					dwzResult = new DwzAjaxResult("300", "请填写区县",
							"electricAddPage", "", "");
					return new JsonObject(dwzResult).toString();
				}
			}
			/* 20151201 */
			String fileId = request.getParameter("fileId");
			tblPowerstation.setPostPic(fileId);
//			String postOwnProvinceCode = request.getParameter("postOwnProvinceCode");
//			String postOwnCityCode = request.getParameter("postOwnCityCode");
//			String postOwnCountyCode = request.getParameter("postOwnCountyCode");
//			tblPowerstation.setPostOwnProvinceCode(postOwnProvinceCode);
//			tblPowerstation.setPostOwnCityCode(postOwnCityCode);
//			tblPowerstation.setPostOwnCountyCode(postOwnCountyCode);
			// 新增车型
			powerstationService.addPowers(tblPowerstation, listImage,
					detailImage, loginUser);
			dwzResult = new DwzAjaxResult("200", "新增成功", "getPowersList",
					"closeCurrent", "");
			sendToShsz(tblPowerstation);//新增电站向上海停车办发送推送
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "新增失败", "electricAddPage", "",
					"");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 校验经纬度
	 * 
	 * @param str
	 * @return 经度最多为四位整数，六位小数
	 */
	private boolean checkLongitudeLatitude(String str) {
		boolean flag = false;
		if (str.lastIndexOf(".") > 0) {

			if (str.substring(0, str.lastIndexOf(".")).length() > 4) {
				flag = true;
				return flag;
			}
			if (str.substring(str.lastIndexOf(".") + 1, str.length()).length() > 6) {
				flag = true;
				return flag;
			}
		} else {
			if (str.length() > 4) {
				flag = true;
				return flag;
			}
		}
		return flag;
	}

	/***
	 * 编辑充电点初始化界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changePowersUi")
	public String changePowersUi(@RequestParam("id") int powerId, Model model) {

		TblPowerstation tblPowerstation = new TblPowerstation();
		tblPowerstation.setPkPowerstation(powerId);
		tblPowerstation = powerstationService.getPowerById(tblPowerstation);
		CacheEntity provinceCache = CacheManager
				.getCacheInfo("provinceCodeList");
		List<Province> provinceCodeLis1 = (List<Province>) provinceCache
				.getValue();
		List<City> cities = cityService.getCityList1(tblPowerstation
				.getPostOwnProvinceCode());
		List<Area> areas = areaService.getAreaList1(tblPowerstation
				.getPostOwnCityCode());
		// 获取省份列表
		model.addAttribute("provinceCodeList", provinceCodeLis1);
		// 获取城市列表
		model.addAttribute("cityList", cities);
		// 获取区县列表
		model.addAttribute("countyList", areas);
		TblElectricpile tblElectricpile = new TblElectricpile();
		tblElectricpile.setRelevancePowerStation(powerId);
		List<Map<String, Object>> electricList = (List<Map<String, Object>>) electricPileListService
				.getElectricpileByCondition(tblElectricpile);
		model.addAttribute("tblPowerstation", tblPowerstation);
		model.addAttribute("electricList", electricList);
		return "backstage/powerStation/powerStation-change";
	}

	/***
	 * 编辑充电点
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changePower")
	@ResponseBody
	public String changePower(
			@RequestParam("tblElectricpile.pkElectricpile") String postEleids,
			TblPowerstation tblPowerstation,
			@RequestParam(value = "listImage", required = false) MultipartFile[] listImage,
			@RequestParam(value = "detailImage", required = false) MultipartFile[] detailImage,
			HttpServletRequest request) {
		// 获取登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			tblPowerstation.setPostEleids(postEleids);
			//修改充电点
			powerstationService.updatePowers(tblPowerstation, listImage,
					detailImage);
			//绑定电桩
			String[] pkElectricpile=request.getParameterValues("pkElectricpile");
			String[] oldEpNum=request.getParameterValues("oldEpNum");
			String[] epNum=request.getParameterValues("epNum");
			String[] rateId=request.getParameterValues("rateId");
			String[] oldRateId=request.getParameterValues("oldRateId");
			String[] index=request.getParameterValues("index");
			String[] electricPileCode=request.getParameterValues("electricPileCode");
			//设置费率的省市区为该点电站的省市区
			TblRateinformation rateinformation = new TblRateinformation();
			rateinformation.setRaInAreaId(tblPowerstation.getPostOwnCountyCode());
			rateinformation.setRaInCityId(tblPowerstation.getPostOwnCityCode());
			rateinformation.setRaInProvinceId(tblPowerstation.getPostOwnProvinceCode());
			rateinformation.setUserId(loginUser.getUserId().toString());;
			rateinformation.setUserLevel(loginUser.getUserLevel());
			TblElectricpile pile=new TblElectricpile();
			String error="";
			int flag=0;//费率错误数量标志
			if(pkElectricpile!=null){
				for(int i=0;i<pkElectricpile.length;i++){
					//未做修改的电桩不处理(编号和费率)
					if(!oldEpNum[i].equals(epNum[i]) || !oldRateId[i].equals(rateId[i])){
						flag++;
						//若填写费率id为空,或费率id为数据库中不存在的id,则失败
						if(rateId[i].equals("")){
							error += "序号"+index[i]+"电桩编辑费率失败"+"<br>";
						}else{
							rateinformation.setPkRateinformation(Integer.parseInt(rateId[i]));
							if(powerstationService.selectRateId(rateinformation)==0){
								error += "序号"+index[i]+"电桩编辑费率失败"+"<br>";
							}
						}	
					}
				}
				if(error!=""&&flag>1){
					dwzResult = new DwzAjaxResult("300", error, "electricAddPage", "","");
					return new JsonObject(dwzResult).toString();
				}else if(error!=""&&flag==1){
					dwzResult = new DwzAjaxResult("300", "编辑失败", "electricAddPage", "","");
					return new JsonObject(dwzResult).toString();
				}else if(error==""){
					for(int i=0;i<pkElectricpile.length;i++){
						//未做修改的电桩不处理(编号和费率)
						if(!oldEpNum[i].equals(epNum[i]) || !oldRateId[i].equals(rateId[i])){
								rateinformation.setPkRateinformation(Integer.parseInt(rateId[i]));
								pile.setElPiRateInformationId(Integer.parseInt(rateId[i]));
								pile.setPkElectricpile(new Integer(pkElectricpile[i]));
								pile.setEp_num(new Integer(epNum[i]));
								pile.setRelevancePowerStation(tblPowerstation.getPkPowerstation());
								pile.setElpiBinding(1);
								powerstationService.electricPileBindPower(pile);
								//费率修改 实时下发
								String sendStr = "";
								sendStr += electricPileCode[i];
								sendStr += ":" + rateinformation.getPkRateinformation();
								MessageManager m = new MessageManager();
								String apiBaseUrl = m.getSystemProperties("apiRoot");
								String token = ApiUtil.getToken();
								//调用接口更新费率
								HttpsUtil.getResponseParam(apiBaseUrl + "/app/net/sendRate.do?paramStrs=" + sendStr + "&t=" + token, "status");
								commitLogService.insert("费率更新命令下发，主键：["
										+ rateinformation.getPkRateinformation() + "]");
							}
						}
					}
				}
			dwzResult = new DwzAjaxResult("200", "编辑成功", "getPowersList","closeCurrent", "");
			sendToShsz(tblPowerstation);//修改电站向上海停车办发送推送
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "编辑失败", "electricAddPage", "",
					"");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	public void sendToShsz(TblPowerstation psModel){
		if(psModel.getPostOwnProvinceCode().equals("310000")){
			psModel = powerstationService.getPowerById(psModel);
			Map<String, Object> psData = new HashMap<>();
			Map<String, Object> elcData = null;
			Map<String, Object> hData = null;
			psData = new HashMap<String, Object>();
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
//			psData.put("MatchCars", "");
//			psData.put("ParkInfo", "");
//			psData.put("ParkOwner", "");
//			psData.put("ParkManager", "");
			psData.put("OpenAllDay", 1);
//			psData.put("BusineHours", psModel.getPoStOnlineTime());
			psData.put("MinElectricityPrice", rateInfoService.selectMinPriceByPsId(psModel.getPkPowerstation()));
//			psData.put("ElectricityFee", "待");
//			psData.put("ServiceFee", "待");
			psData.put("ParkFree", 0);
//			psData.put("ParkFee", "");
			psData.put("Payment", "线上");
			psData.put("SupportOrder", psModel.getPostIsappoint()==1?1:0);
//			psData.put("Remark", psModel.getPostRemark());
			List<TcbElectric> elcList = electricPileListService.getElectricpileListByPsId(psModel.getPkPowerstation());
			List<Map<String, Object>> elcDataList = new ArrayList<Map<String,Object>>();
			for(TcbElectric e:elcList){
				elcData = new HashMap<String, Object>();
				elcData.put("EquipmentID", e.getEquipNo());
				elcData.put("ManufacturerID", WanmaConstants.PRI_OPERATOR_ID);
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
				TblElectricpileheadForSH hModel = new TblElectricpileheadForSH();
				hModel.setPkElectricpile(e.getPkElc());
				List<TblElectricpileheadForSH> headList = electricPileListService.getList(hModel);
				List<Map<String, Object>> headDataList = new ArrayList<Map<String,Object>>();
				for(TblElectricpileheadForSH h:headList){
					hData = new HashMap<String, Object>();
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
			MessageManager manager=MessageManager.getMessageManager();
			SHStopService.send2SHStop(manager.getSystemProperties("URL_SEND_STATION"), psData);
			
		}
	}
		
	/***
	 * 充电点详情
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showPowers")
	public String showPowers(@RequestParam("id") int powerId, Model model) {

		TblPowerstation tblPowerstation = new TblPowerstation();
		tblPowerstation.setPkPowerstation(powerId);
		tblPowerstation = powerstationService.getPowerById(tblPowerstation);

		CacheEntity provinceCache = CacheManager
				.getCacheInfo("provinceCodeList");
		List<Province> provinceCodeLis1 = (List<Province>) provinceCache
				.getValue();
		List<City> cities = cityService.getCityList1(tblPowerstation
				.getPostOwnProvinceCode());
		List<Area> areas = areaService.getAreaList1(tblPowerstation
				.getPostOwnCityCode());
		// 获取省份列表
		model.addAttribute("provinceCodeList", provinceCodeLis1);
		// 获取城市列表
		model.addAttribute("cityList", cities);
		// 获取区县列表
		model.addAttribute("countyList", areas);
		TblElectricpile tblElectricpile = new TblElectricpile();
		tblElectricpile.setRelevancePowerStation(powerId);
		List<Map<String, Object>> electricList = (List<Map<String, Object>>) electricPileListService
				.getElectricpileByCondition(tblElectricpile);
		model.addAttribute("tblPowerstation", tblPowerstation);
		model.addAttribute("electricList", electricList);
		model.addAttribute("tblPowerstation", tblPowerstation);
		return "backstage/powerStation/powerStation-show";
	}

	/**
	 * 绑定电桩列表
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getPowersElectricPileList")
	public String getPowersElectricPileList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblElectricpile tblElectricpile, Model model,
			HttpServletRequest request) {

		try {
			// ------------|01：初始化筛选条件|-----------
			TblConfigcontent tblConfigcontent = new TblConfigcontent();
			tblConfigcontent.setCocoConfigparameterid(3);
			// 获取充电方式
			List<TblConfigcontent> chargeList = msConfigcontentService
					.findContentList(tblConfigcontent);
			model.addAttribute("chargeList", chargeList);

			// ------------|02：查询电桩业务处理|-----------
			// 设置分页对象
			tblElectricpile.setPager(pager);

			// 获取登陆用户
			TblUser loginUser = SessionMgr.getWebUser(request);
			tblElectricpile.setElpiUserid(loginUser.getUserId().toString());
			tblElectricpile.setUserLevel(loginUser.getUserLevel() + "");
			// 充电点总数
			long total = electricPileListService
					.getElectricpileByConditionCount1(tblElectricpile);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置查询参数
			tblElectricpile.setPager(pager);
			List<Map<String, Object>> electricList = (List<Map<String, Object>>) electricPileListService
					.getElectricpileByCondition1(tblElectricpile);
			pager.setTotal(total);

			model.addAttribute("electricList", electricList);
			model.addAttribute("pager", pager);
			model.addAttribute("tblElectricpile", tblElectricpile);
		} catch (Exception e) {
			log.error("获取电桩状态失败", e);
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		// 跳转至管理员主页面
		return "backstage/powerStation/powerStation-electric-list";
	}

	/***
	 * 解绑
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/unbindElectricPile")
	@ResponseBody
	public String unbindElectricPile(
			@RequestParam("pkElectricpile") int pkElectricpile) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			TblElectricpile tblElectricpile = new TblElectricpile();
			tblElectricpile.setPkElectricpile(pkElectricpile);
			tblElectricpile.setRelevancePowerStation(0);
			tblElectricpile.setElpiBinding(0);
			electricPileListService.electricPileBindPower(tblElectricpile);

			dwzResult = new DwzAjaxResult("200", "解绑成功", "powerOperate", "", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "解绑失败", "powerOperate", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
}
