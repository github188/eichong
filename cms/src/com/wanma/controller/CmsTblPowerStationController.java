package com.wanma.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.ApiUtil;
import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.BaseSuccess;
import com.base.common.GlobalSystem;
import com.base.common.HttpsUtil;
import com.base.common.SessionMgr;
import com.base.common.WanmaConstants;
import com.base.util.ExcelUtil;
import com.pub.model.Pager;
import com.pub.model.TblUser;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;
import com.wanma.model.TblRateinformation;
import com.wanma.service.CmsCommitLogService;
import com.wanma.service.CmsPowerstationService;
import com.wanma.service.CmsRateInfoService;
import com.wanma.service.ElectricPileListService;
/**
 * 运营管理-充电点信息
 * 
 * @author 
 * 
 */
@Controller
@RequestMapping("/admin/station/")
public class CmsTblPowerStationController {
	/** 日志文件生成器 */
	private static Logger log = Logger
			.getLogger(CmsTblPowerStationController.class);
	@Autowired
	private ElectricPileListService electricPileListService;
	@Autowired
	private CmsPowerstationService powerstationService;
	@Autowired
	CmsCommitLogService commitLogService;
	@Autowired
	private CmsRateInfoService rateinfoService;

	@RequestMapping(value = "/stationListPage")
	public String stationListPage(HttpServletRequest request) {
		return "backstage/station/stationList";
	}

	/**
	 * @Title: powersList
	 * @Description: 充电点列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/getStationList")
	@ResponseBody
	public String getPowersList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblPowerstation powerstation, Model model,
			HttpServletRequest request) {
		BaseResult baseResult = new BaseFail(5001);
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
		for (TblPowerstation station : powerstationList) {
			station.setExtValue1(WanmaConstants.getConfigName("12", station
					.getPostStatus().toString()));
		}
		pager.setTotal(total);
		baseResult = new BaseResult(powerstationList, pager);
		log.info("************获取充电点列表-end************");
		return baseResult.toString();
	}

	/**
	 * 充电点详情页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/stationDetailPage")
	public String stationDetailPage(HttpServletRequest request) {
		return "backstage/station/stationDetail";
	}

	/**
	 * 充电点详情
	 * 
	 * @param request
	 * @param tblElectricpile
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/stationDetail")
	@ResponseBody
	public String stationDetail(HttpServletRequest request,
			TblPowerstation tblPowerstation, Model model) {
		tblPowerstation = powerstationService.getPowerById(tblPowerstation);
		TblElectricpile tblElectricpile = new TblElectricpile();
		tblElectricpile.setRelevancePowerStation(tblPowerstation
				.getPkPowerstation());
		List<TblElectricpile> electricList = electricPileListService
				.getElectricpileByCondition(tblElectricpile);
		for (TblElectricpile electric : electricList) {
			electric.setExtValue1(WanmaConstants.getConfigName("12", electric
					.getElpiState().toString()));
			electric.setExtValue2(WanmaConstants.getConfigName("3", electric
					.getElpiChargingmode().toString()));
			electric.setExtValue3(WanmaConstants.getConfigName("4", electric
					.getElpiPowersize().toString()));
		}
		tblPowerstation.setElectricList(electricList);
		if (request.getParameter("format") != null) {
			// 省市区
			List<Map<String, Object>> provinceList = WanmaConstants.provinceList;
			String pId = tblPowerstation.getPostOwnProvinceCode();
			String cId = tblPowerstation.getPostOwnCityCode();
			String aId = tblPowerstation.getPostOwnCountyCode();
			for (Map<String, Object> p : provinceList) {
				if (p.get("provinceId").toString().equals(pId)) {
					tblPowerstation.setExtValue1(p.get("provinceName")
							.toString());
					break;
				}
			}
			Map<String, Object> cityMap = WanmaConstants.cityMap;
			List<Map<String, Object>> cityList = (List<Map<String, Object>>) cityMap
					.get(pId);
			for (Map<String, Object> c : cityList) {
				if (c.get("cityId").toString().equals(cId)) {
					tblPowerstation.setExtValue2(c.get("cityName").toString());
					break;
				}
			}
			Map<String, Object> areaMap = WanmaConstants.areaMap;
			List<Map<String, Object>> areaList = (List<Map<String, Object>>) areaMap
					.get(cId);
			for (Map<String, Object> a : areaList) {
				if (a.get("areaId").toString().equals(aId)) {
					tblPowerstation.setExtValue3(a.get("areaName").toString());
					break;
				}
			}
			// 充电点状态
			tblPowerstation.setExtValue4(WanmaConstants.getConfigName("12",
					tblPowerstation.getPostStatus().toString()));
			// 是否支持预约
			tblPowerstation.setExtValue5(WanmaConstants.getConfigName("0",
					tblPowerstation.getPostIsappoint().toString()));
		}
		return new BaseResult(tblPowerstation).toString();
	}

	/**
	 * 充电点编辑页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/stationEditPage")
	public String stationEditPage(HttpServletRequest request) {
		return "backstage/station/stationEdit";
	}

	/**
	 * 充电点修改
	 * 
	 * @param request
	 * @param tblElectricpile
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/stationModify")
	@ResponseBody
	public String stationModify(HttpServletRequest request,
			TblPowerstation tblPowerstation, Model model) {
		try {
			// 获取登陆用户
			TblUser loginUser = SessionMgr.getWebUser(request);
			TblElectricpile tblElectricpile = new TblElectricpile();
			tblElectricpile.setRelevancePowerStation(tblPowerstation
					.getPkPowerstation());
			List<TblElectricpile> tempList = tblPowerstation.getElectricList();
			// 设置费率的省市区为该点电站的省市区
			TblRateinformation rateinformation = new TblRateinformation();
			rateinformation.setRaInAreaId(tblPowerstation
					.getPostOwnCountyCode());
			rateinformation.setRaInCityId(tblPowerstation.getPostOwnCityCode());
			rateinformation.setRaInProvinceId(tblPowerstation
					.getPostOwnProvinceCode());
			rateinformation.setUserId(loginUser.getUserId().toString());
			;
			rateinformation.setUserLevel(loginUser.getUserLevel());
			List<TblRateinformation> rateinfoList = rateinfoService
					.getRateinfoList(rateinformation);
			// 校验费率是否正确
			boolean flag = false;
			String error = "";
			tempList = tempList == null ? new ArrayList<TblElectricpile>()
					: tempList;
			for (TblElectricpile electric : tempList) {
				flag = false;
				for (TblRateinformation rateinfo : rateinfoList) {
					if (electric.getElPiRateInformationId() == rateinfo
							.getPkRateinformation()) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					error += electric.getElpiElectricpilecode() + ",";
				}
			}
			if (StringUtils.isNotBlank(error)) {
				return new BaseFail(StringUtils.removeEnd(error, ",") + "费率不可用")
						.toString();
			}
			// 更新电站和电桩
			powerstationService.updatePowers(tblPowerstation, null, null);
			for (TblElectricpile electric : tempList) {
				electric.setRelevancePowerStation(tblPowerstation
						.getPkPowerstation());
				electricPileListService.updateByElecId(electric);
				// 费率修改实时下发
				if (!electric.getExtValue1().equals(
						electric.getElPiRateInformationId().toString())) {
					String sendStr = "";
					sendStr += electric.getElpiElectricpilecode();
					sendStr += ":" + electric.getElPiRateInformationId();
					String apiBaseUrl = GlobalSystem.getConfig("apiRoot");
					String token = ApiUtil.getToken();
					// 调用接口更新费率
					HttpsUtil.getResponseParam(apiBaseUrl
							+ "/app/net/sendRate.do?paramStrs=" + sendStr
							+ "&t=" + token, "status");
					commitLogService.insert("费率更新命令下发，主键：["
							+ electric.getElPiRateInformationId() + "]");
				}
			}
			return new BaseSuccess().toString();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(this.getClass() + ".stationModify() error:"
					+ e.getLocalizedMessage());
			return new BaseFail(5001).toString();
		}
	}

	/***
	 * 删除充电点
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/stationRemove")
	@ResponseBody
	public String stationRemove(TblPowerstation powerstation) {
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		try {
			List<TblPowerstation> powerstationList = powerstationService
					.findPowers(powerstation);
			String error = "", error1 = "", error2 = "";
			for (TblPowerstation station : powerstationList) {
				if (station.getPostStatus() != 0) {
					error1 += station.getPkPowerstation() + ",";
				}
				if (station.getElectricPileCount() > 0) {
					error2 += station.getPkPowerstation() + ",";
				}
			}

			if (StringUtils.isNotBlank(error1)) {
				error += "不是草稿状态:" + error1;
			}
			if (StringUtils.isNotBlank(error2)) {
				error += "已绑定电桩:" + error2;
			}
			if (StringUtils.isNotBlank(error)) {
				return new BaseFail(StringUtils.removeEnd(error, ","))
						.toString();
			}
			powerstationService.removePowers(powerstation.getIds());
			return baseResult.toString();
		} catch (Exception e) {
			log.error(this.getClass() + ".stationRemove() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

	/**
	 * 充电点分享 electricStateType=15;
	 * 
	 * @return
	 */
	@RequestMapping(value = "/stationOnLine")
	@ResponseBody
	private String stationOnLine(TblPowerstation powerstation) {
		BaseResult baseResult = new BaseSuccess();
		try {
			powerstation.setPostStatus(15);
			powerstationService.changeStateByIds(powerstation);
			// 设置电桩分享
			TblElectricpile electricpile = new TblElectricpile();
			electricpile.setIds(powerstation.getIds());
			electricpile.setElpiState(15);
			electricpile.setCompanyNumber("0");
			electricPileListService.updateStateByPowerIds(electricpile);
		} catch (Exception e) {
			log.error(this.getClass() + ".stationOnLine() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

	/**
	 * 充电点专属
	 * 
	 * @return
	 */
	@RequestMapping(value = "/stationOffLine")
	@ResponseBody
	public String staticOffLine(TblPowerstation powerstation) {
		BaseResult baseResult = new BaseSuccess();
		try {
			powerstation.setPostStatus(10);
			powerstationService.changeStateByIds(powerstation);
			// 设置电桩专属
			TblElectricpile electricpile = new TblElectricpile();
			electricpile.setIds(powerstation.getIds());
			electricpile.setElpiState(10);
			electricPileListService.updateStateByPowerIds(electricpile);
		} catch (Exception e) {
			log.error(this.getClass() + ".staticOffLine() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

	/**
	 * 充电点导出
	 * 
	 * @param pager
	 * @param powerstation
	 * @param model
	 * @param request
	 */
	@RequestMapping("/stationExport")
	@ResponseBody
	public void stationExport(@ModelAttribute TblPowerstation powerstation,
			HttpServletRequest request, HttpServletResponse response) {
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
		// 获取充电点列表
		List<TblPowerstation> powerstationList = powerstationService
				.findPowers(powerstation);
		for (TblPowerstation station : powerstationList) {
			station.setExtValue1(WanmaConstants.getConfigName("12", station
					.getPostStatus().toString()));
		}

		// excel导出
		ExcelUtil eu = new ExcelUtil();
		// 转换成excel可用的数据格式
		List<String[]> dataList = new ArrayList<String[]>();
		String[] data = new String[] { "充电点名称", "充电点状态", "电桩总数", "充电点地址",
				"开放时间" };
		dataList.add(data);

		for (TblPowerstation obj : powerstationList) {
			data = new String[5];
			data[0] = obj.getPostName();
			data[1] = WanmaConstants.getConfigName("12", obj.getPostStatus()
					.toString());
			data[2] = obj.getElectricPileCount() + "";
			data[3] = obj.getPostAddress();
			data[4] = obj.getPoStOnlineTime();
			dataList.add(data);
		}
		try {
			eu.export(dataList, response, "充电点列表.xlsx");
		} catch (IOException e) {
			e.printStackTrace();
			log.error(this.getClass() + ".stationExport() error:"
					+ e.getLocalizedMessage());
		}
	}
}
