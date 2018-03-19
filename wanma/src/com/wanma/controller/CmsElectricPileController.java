package com.wanma.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.app.controller.ElectricPileListController;
import com.wanma.app.dao.TblElectricpileheadMapper;
import com.wanma.app.service.ElectricPileListService;
import com.wanma.common.CacheEntity;
import com.wanma.common.CacheManager;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.PowerElectricPile;
import com.wanma.model.TblCarmaker;
import com.wanma.model.TblCompany;
import com.wanma.model.TblConfigcontent;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblEquipmentVersion;
import com.wanma.model.TblGateservice;
import com.wanma.model.TblRateInfo;
import com.wanma.model.TblTypespan;
import com.wanma.model.TblUser;
import com.wanma.service.CmsCarmakerService;
import com.wanma.service.CmsCommitLogService;
import com.wanma.service.CmsConfigcontentService;
import com.wanma.service.CmsEquipmentVersionService;
import com.wanma.service.CmsPureBusinessService;
import com.wanma.service.CmsTblTypespanService;
import com.wanma.service.CompanyManagerService;
import com.wanma.service.GateService;
import com.wanma.service.impl.CmsRateInfoServiceImpl;
import com.wanma.service.impl.ElectricPileExportServiceImpl;
import com.wanma.web.support.utils.ApiUtil;
import com.wanma.web.support.utils.ExcelUtil;
import com.wanma.web.support.utils.HttpsUtil;

/**
 * 
 * 电桩管理
 * 
 * @Description:
 * @author xiay
 * @createTime：2015-3-25 上午10:57:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/electric/")
public class CmsElectricPileController {

	/** 日志文件生成器 */
	private static Logger log = Logger
			.getLogger(ElectricPileListController.class);
	/* 电桩信息导出Excel */
	@Autowired
	private ElectricPileExportServiceImpl electricPileExportService;

	@Autowired
	private CmsConfigcontentService msConfigcontentService;
	@Autowired
	private ElectricPileListService electricPileListService;
	@Autowired
	private TblElectricpileheadMapper tblElectricpileheadMapper;
	/* 制造厂商service */
	@Autowired
	private CmsCarmakerService carmakerService;

	@Autowired
	private CmsRateInfoServiceImpl rateInfoService;
	@Autowired
	private CityService cityService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private CmsPureBusinessService pureBusinessService;
	@Autowired
	private GateService gateService;
	@Autowired
	private CompanyManagerService companyManagerService;
	@Autowired
	private CmsEquipmentVersionService equipmentVersionService;
	@Autowired
	private CmsTblTypespanService tblTypespanService;
	@Autowired
	CmsCommitLogService commitLogService;

	/**
	 * 获取电桩查找列表模式数据
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getElectricPileList")
	public String getElectricPileList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblElectricpile tblElectricpile, Model model,
			HttpServletRequest request) {

		try {

			// ------------|02：查询电桩业务处理|-----------
			// 获取登陆用户
			TblUser loginUser = SessionMgr.getWebUser(request);

			// 个体/纯商家只能查询所属电桩

			tblElectricpile.setElpiUserid(loginUser.getUserId().toString());
			tblElectricpile.setUserLevel(loginUser.getUserLevel().toString());
			// 获取地区信息
			model.addAttribute("proviceMap", WanmaConstants.provinceMap);
			// 制造厂商下拉框
			List<TblElectricpile> getElpiMaker = null;
			getElpiMaker = electricPileListService.getElpiMaker();
			model.addAttribute("getElpiMaker", getElpiMaker);
			String proviceCode = tblElectricpile.getElPiOwnProvinceCode();
			if (StringUtils.isNotBlank(proviceCode)) {
				List<Object> cityList = new ArrayList<Object>();
				for (String citycode : WanmaConstants.provinceCityMap
						.get(proviceCode)) {
					Map<String, Object> cityMap = WanmaConstants.cityMap;
					cityList.add(cityMap.get(citycode));
				}
				model.addAttribute("cityList", cityList);
			}
			String cityCode = tblElectricpile.getElPiOwnCityCode();
			if (StringUtils.isNotBlank(cityCode)) {
				List<Object> areaList = new ArrayList<Object>();
				for (String areacode : WanmaConstants.cityAreaMap.get(cityCode)) {
					Map<String, Object> areaMap = WanmaConstants.areaMap;
					areaList.add(areaMap.get(areacode));
				}
				model.addAttribute("areaList", areaList);
			}
			// 电桩总数
			long total = electricPileListService
					.getElectricpileByConditionCount(tblElectricpile);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置查询参数
			tblElectricpile.setPager(pager);
			List<Map<String, Object>> electricList = (List<Map<String, Object>>) electricPileListService
					.getElectricpileByCondition(tblElectricpile);
			pager.setTotal(total);
			model.addAttribute("electricList", electricList);
			model.addAttribute("pager", pager);
			model.addAttribute("tblElectricpile", tblElectricpile);
			model.addAttribute("loginUser", loginUser);
		} catch (Exception e) {
			log.error("获取电桩状态失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		// 跳转至管理员主页面
		return "backstage/electric/electric-list";
	}

	/**
	 * 电桩分享申请
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
		return new DwzAjaxResult("300", "操作失败", "getOnLineElectricPileList",
				"", "").toJSONString();
	}

	/***
	 * 添加电桩初始化界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addElectricPileUi")
	public String addElectricPileUi(Model model, HttpServletRequest request) {
		// 登陆用户
		TblUser user = SessionMgr.getWebUser(request);
		// ------------|01：初始化筛选条件|-----------
		TblConfigcontent tblConfigcontent = new TblConfigcontent();
		tblConfigcontent
				.setCocoConfigpstatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
		tblConfigcontent.setCocoConfigparameterid(3);
		// 获取充电方式
		List<TblConfigcontent> chargeList = msConfigcontentService
				.findContentList(tblConfigcontent);
		model.addAttribute("chargeList", chargeList);
		// 获取产品型号信息
		List<TblTypespan> typespanList = electricPileListService
				.getTypespanList();
		model.addAttribute("typespanList", typespanList);

		// 获取功率
		tblConfigcontent.setCocoConfigparameterid(4);
		List<TblConfigcontent> powerList = msConfigcontentService
				.findContentList(tblConfigcontent);
		Collections.sort(powerList, new Comparator<TblConfigcontent>() {
			public int compare(TblConfigcontent o1, TblConfigcontent o2) {
				if (Float
						.parseFloat(o1
								.getCocoContent()
								.toLowerCase()
								.substring(
										0,
										o1.getCocoContent().toLowerCase()
												.indexOf("k"))) > Float
						.parseFloat(o2
								.getCocoContent()
								.toLowerCase()
								.substring(
										0,
										o2.getCocoContent().toLowerCase()
												.indexOf("k")))) {
					return 1;
				}
				if (Float
						.parseFloat(o1
								.getCocoContent()
								.toLowerCase()
								.substring(
										0,
										o1.getCocoContent().toLowerCase()
												.indexOf("k"))) == Float
						.parseFloat(o2
								.getCocoContent()
								.toLowerCase()
								.substring(
										0,
										o2.getCocoContent().toLowerCase()
												.indexOf("k")))) {
					return 0;
				}
				return -1;
			}
		});
		model.addAttribute("powerList", powerList);

		// 桩体用途
		tblConfigcontent.setCocoConfigparameterid(2);
		List<TblConfigcontent> elctrcUseList = msConfigcontentService
				.findContentList(tblConfigcontent);
		model.addAttribute("elctrcUseList", elctrcUseList);

		// 获取电桩类型
		tblConfigcontent.setCocoConfigparameterid(1);
		List<TblConfigcontent> typeList = msConfigcontentService
				.findContentList(tblConfigcontent);
		model.addAttribute("typeList", typeList);

		// 获取接口标准
		tblConfigcontent.setCocoConfigparameterid(5);
		List<TblConfigcontent> connectorList = msConfigcontentService
				.findContentList(tblConfigcontent);
		model.addAttribute("connectorList", connectorList);
		// 制造商
		tblConfigcontent.setCocoConfigparameterid(11);
		List<TblCarmaker> markList = carmakerService.getAll(null);
		// List<TblConfigcontent>
		// markList=msConfigcontentService.findContentList(tblConfigcontent);
		model.addAttribute("markList", markList);

		/** 电桩省市区(chengym20150609) start ***/
		// 系统初始化缓存数据
		CacheEntity provinceCache = CacheManager
				.getCacheInfo("provinceCodeList");
		List<Province> provinceCodeList = (List<Province>) provinceCache
				.getValue();
		/** 电桩省市区(chengym20150609) start ***/
		List<TblGateservice> gateList = gateService.getGateList();
		model.addAttribute("gateList", gateList);

		// 获取省份列表
		model.addAttribute("provinceCodeList", provinceCodeList);
		model.addAttribute("loginUserLevel", user.getUserLevel());
		return "backstage/electric/electric-add";
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
	 * 添加电桩
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addElectricPile")
	@ResponseBody
	public String addElectricPile(
			TblElectricpile tblElectricpile,
			@RequestParam(value = "listImage", required = false) MultipartFile[] listImage,
			@RequestParam(value = "detailImage", required = false) MultipartFile[] detailImage,
			HttpSession session, HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		// String ephNumArr[]=request.getParameterValues("ephNum");
		// String arr2[]=request.getParameterValues("arr1");
		// String arr3[]=request.getParameterValues("arr1");
		// String arr4[]=request.getParameterValues("arr1");
		// String arr5[]=request.getParameterValues("arr1");
		List<TblElectricpilehead> head = new ArrayList<TblElectricpilehead>();
		try {
			// 电桩下线时间
			// String offlineTime = RequestParamUtil.getEncodeParam(request,
			// "offline");
			// if(StringUtil.isNotEmpty(offlineTime)){
			// tblElectricpile.setOfflineTime(DateUtil.parse(offlineTime,
			// "yyyy-MM-dd HH:mm"));
			// }

			// 获取登陆用户
			TblUser loginUser = SessionMgr.getWebUser(request);
			if (checkLongitudeLatitude(JudgeNullUtils.nvlStr(tblElectricpile
					.getElpiLongitude()))) {
				dwzResult = new DwzAjaxResult("300", "经度最多小数点前4位小数点后6位",
						"electricAddPage", "", "");
				return new JsonObject(dwzResult).toString();
			}
			if (checkLongitudeLatitude(JudgeNullUtils.nvlStr(tblElectricpile
					.getElpiLatitude()))) {
				dwzResult = new DwzAjaxResult("300", "纬度最多小数点前4位小数点后6位",
						"electricAddPage", "", "");
				return new JsonObject(dwzResult).toString();
			}
			/** 新增电桩省市区(chengym20150609) start ***/
			String province = tblElectricpile.getElPiOwnProvinceCode();
			if (!StringUtils.isBlank(province)) {
				if (StringUtils.isBlank(tblElectricpile.getElPiOwnCityCode())) {
					dwzResult = new DwzAjaxResult("300", "请填写城市",
							"electricAddPage", "", "");
					return new JsonObject(dwzResult).toString();
				}
				if (StringUtils.isBlank(tblElectricpile.getElPiOwnCountyCode())) {
					dwzResult = new DwzAjaxResult("300", "请填写区县",
							"electricAddPage", "", "");
					return new JsonObject(dwzResult).toString();
				}
			}
			if (!StringUtils.isBlank(tblElectricpile.getElPiSimMac())) {
				if (tblElectricpile.getElPiSimMac().length() > 20) {
					dwzResult = new DwzAjaxResult("300", "SIM卡号字数超限",
							"electricAddPage", "", "");
					return new JsonObject(dwzResult).toString();
				}
			}
			// if (StringUtils.isBlank(tblElectricpile.getElPiSimPhoneNum())) {
			// boolean flag = false;
			// try {
			// Pattern regex = Pattern.compile("(\\+\\d+)?1[3458]\\d{9}$");
			// Matcher matcher = regex.matcher(tblElectricpile
			// .getElPiSimPhoneNum());
			// flag = matcher.matches();
			// if (!flag) {
			// dwzResult = new DwzAjaxResult("300", "SIM卡手机号码有误",
			// "electricAddPage", "", "");
			// return new JsonObject(dwzResult).toString();
			// }
			// } catch (Exception e) {
			// dwzResult = new DwzAjaxResult("300", "SIM卡手机号码有误",
			// "electricAddPage", "", "");
			// return new JsonObject(dwzResult).toString();
			// }
			// }
			/** 新增电桩省市区(chengym20150609) end ***/
			/* 20150609 */
			tblElectricpile.setElpiAreacode("");
			tblElectricpile.setElpiRejectionreason("");
			tblElectricpile.setElpiImage("");
			tblElectricpile.setElpiDetailimage("");
			// tblElectricpile.setElpiOutputvoltage(new BigDecimal(0));
			tblElectricpile.setElpiInputvoltage(new BigDecimal(0));
			// tblElectricpile.setElpiOutputcurrent(new BigDecimal(0));
			tblElectricpile.setElpiInputcurrent(new BigDecimal(0));
			// tblElectricpile.setElpiRemark("");
			tblElectricpile.setElpiCarid("");
			tblElectricpile.setElpiPaystyle(1);
			tblElectricpile.setElpiMaxelectricity(new BigDecimal(0));
			if (StringUtil.isEmpty(tblElectricpile.getOfflineTime())) {
				tblElectricpile.setOfflineTime("");
			}

			/* 20151201 */
			String fileId = request.getParameter("fileId");
			tblElectricpile.setElpiImage(fileId);
			// String typespan = request.getParameter("typespan");
			// tblElectricpile.setElpiTypeSpanId(Integer.parseInt(typespan));
			// 新增车型
			electricPileListService.addElectricpile(tblElectricpile, listImage,
					detailImage, loginUser);
			dwzResult = new DwzAjaxResult("200", "新增成功", "getElectricPileList",
					"closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
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

	/**
	 * 电桩编号唯一性检查
	 * 
	 */
	@RequestMapping(value = "/checkElectricUnique")
	@ResponseBody
	public String checkElectricUnique(
			@RequestParam("elpiElectricpilecode") String elpiElectricpilecode) {
		// 返回处理结果信息
		return electricPileListService
				.checkElectricUnique(elpiElectricpilecode);
	}

	/***
	 * 修改电桩页面初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeElectricPileUi")
	public String changeElectricPileUi(@RequestParam("id") int pkElectricpile,
			Model model, HttpServletRequest request) {
		// 登陆用户
		TblUser user = SessionMgr.getWebUser(request);
		// ------------|01：初始话修改页面元素|-----------
		TblConfigcontent tblConfigcontent = new TblConfigcontent();
		tblConfigcontent
				.setCocoConfigpstatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
		tblConfigcontent.setCocoConfigparameterid(3);
		// 获取充电方式
		List<TblConfigcontent> chargeList = msConfigcontentService
				.findContentList(tblConfigcontent);
		model.addAttribute("chargeList", chargeList);

		// 获取功率
		tblConfigcontent.setCocoConfigparameterid(4);
		List<TblConfigcontent> powerList = msConfigcontentService
				.findContentList(tblConfigcontent);
		Collections.sort(powerList, new Comparator<TblConfigcontent>() {
			public int compare(TblConfigcontent o1, TblConfigcontent o2) {
				if (Float
						.parseFloat(o1
								.getCocoContent()
								.toLowerCase()
								.substring(
										0,
										o1.getCocoContent().toLowerCase()
												.indexOf("k"))) > Float
						.parseFloat(o2
								.getCocoContent()
								.toLowerCase()
								.substring(
										0,
										o2.getCocoContent().toLowerCase()
												.indexOf("k")))) {
					return 1;
				}
				if (Float
						.parseFloat(o1
								.getCocoContent()
								.toLowerCase()
								.substring(
										0,
										o1.getCocoContent().toLowerCase()
												.indexOf("k"))) == Float
						.parseFloat(o2
								.getCocoContent()
								.toLowerCase()
								.substring(
										0,
										o2.getCocoContent().toLowerCase()
												.indexOf("k")))) {
					return 0;
				}
				return -1;
			}
		});
		model.addAttribute("powerList", powerList);

		// 桩体用途
		tblConfigcontent.setCocoConfigparameterid(2);
		List<TblConfigcontent> elctrcUseList = msConfigcontentService
				.findContentList(tblConfigcontent);
		model.addAttribute("elctrcUseList", elctrcUseList);

		// 获取电桩类型
		tblConfigcontent.setCocoConfigparameterid(1);
		List<TblConfigcontent> typeList = msConfigcontentService
				.findContentList(tblConfigcontent);
		model.addAttribute("typeList", typeList);

		// 获取接口标准
		tblConfigcontent.setCocoConfigparameterid(5);
		List<TblConfigcontent> connectorList = msConfigcontentService
				.findContentList(tblConfigcontent);
		model.addAttribute("connectorList", connectorList);
		// 制造商
		tblConfigcontent.setCocoConfigparameterid(11);
		List<TblCarmaker> markList = carmakerService.getAll(null);
		model.addAttribute("markList", markList);
		// 获取产品型号信息
		List<TblTypespan> typespanList = electricPileListService
				.getTypespanList();
		model.addAttribute("typespanList", typespanList);
		// 获取电桩的版本
		List<TblEquipmentVersion> epVersionList = equipmentVersionService
				.getVersionByProductID(pkElectricpile);
		model.addAttribute("epVersionList", epVersionList);
		// 获取登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		// 初始化用户所选值
		TblElectricpile tblElectricpile = new TblElectricpile();
		tblElectricpile.setPkElectricpile(pkElectricpile);
		tblElectricpile = electricPileListService
				.getElectricpileById(tblElectricpile);
		// String offlineTime = tblElectricpile.getOfflineTime();
		// if (StringUtil.isEmpty(offlineTime)) {
		// offlineTime = DateUtil.toDateFormat(DateUtil.parse(offlineTime,
		// "yyyy-MM-dd HH:mm"), "yyyy-MM-dd HH:mm");
		// tblElectricpile.setOfflineTime(offlineTime);
		// }

		// 系统初始化缓存数据
		CacheEntity provinceCache = CacheManager
				.getCacheInfo("provinceCodeList");
		List<Province> provinceCodeLis1 = (List<Province>) provinceCache
				.getValue();
		List<City> cities = cityService.getCityList1(tblElectricpile
				.getElPiOwnProvinceCode());
		List<Area> areas = areaService.getAreaList1(tblElectricpile
				.getElPiOwnCityCode());

		List<TblRateInfo> rateList = null;
		TblRateInfo rateInfo = new TblRateInfo();
		rateInfo.setRaIn_AreaId(tblElectricpile.getElPiOwnCountyCode());
		Integer userLevel = new Integer(user.getUserLevel());
		if (userLevel != null) {
			if (userLevel == WanmaConstants.USER_LEVEL_BUSINESS_NORMAL) {
				rateInfo.setUserId(tblElectricpile.getElpiUserid());
				rateList = rateInfoService.getRateInfoByUser(rateInfo);
			} else if (userLevel == WanmaConstants.USER_LEVEL_BUSINESS) {
				// TblPureBusiness business =
				// pureBusinessService.findBusinessByUserId(tblElectricpile.getElpiUserid());
				// 商家主账号
				// String parentLoveLoginId = user.getUserId().toString();
				rateInfo.setUserId(tblElectricpile.getElpiUserid());
				// 如果主账号未空，则子账号即为主账号
				// if (StringUtil.isEmpty(parentLoveLoginId)) {
				// rateInfo.setParentLoveLoginId(business.getLoveLogin());
				// } else {
				// rateInfo.setParentLoveLoginId(parentLoveLoginId);
				// }
				rateList = rateInfoService.getRateInfoByCompany(rateInfo);
			} else {
				rateList = rateInfoService.getRateInfoByWM(rateInfo);
			}
		} else {
			if (StringUtil.isNotEmpty(tblElectricpile
					.getElPiRateInformationId() + "")) {
				Map<String, Object> map = rateInfoService
						.getRateInfoById(tblElectricpile
								.getElPiRateInformationId());
				if (null != map) {
					rateInfo.setRaIn_ReservationRate((BigDecimal) map
							.get("raIn_ReservationRate"));
					rateInfo.setPk_RateInformation((Integer) map
							.get("pk_RateInformation"));
					rateList = new ArrayList<TblRateInfo>();
					rateList.add(rateInfo);
				}
			}

		}

		List<TblGateservice> gateList = gateService.getGateList();
		model.addAttribute("gateList", gateList);

		// 获取省份列表
		model.addAttribute("provinceCodeList", provinceCodeLis1);
		// 获取城市列表
		model.addAttribute("cityList", cities);
		// 获取区县列表
		model.addAttribute("countyList", areas);
		model.addAttribute("tblElectricpile", tblElectricpile);
		model.addAttribute("loginUser", loginUser);
		model.addAttribute("rateList", rateList);
		// 登陆用户级别
		model.addAttribute("loginUserLevel", user.getUserLevel());
		return "backstage/electric/electric-change";
	}

	/***
	 * 修改电桩
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeElectricPile")
	@ResponseBody
	public String changeElectricPile(
			@RequestParam("tblElectricpile.elPiOwner") String elPiOwner,
			TblElectricpile tblElectricpile,
			@RequestParam(value = "listImage", required = false) MultipartFile[] listImage,
			@RequestParam(value = "detailImage", required = false) MultipartFile[] detailImage,
			HttpSession session, HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {

			// 获取登陆用户
			if (!StringUtils.isBlank(elPiOwner)) {
				tblElectricpile.setElPiUserName(elPiOwner);
			}
			TblUser loginUser = SessionMgr.getWebUser(request);
			String province = tblElectricpile.getElPiOwnProvinceCode();
			if (!StringUtils.isBlank(province)) {
				if (StringUtils.isBlank(tblElectricpile.getElPiOwnCityCode())) {
					dwzResult = new DwzAjaxResult("300", "请填写城市",
							"electricEditPage", "", "");
					return new JsonObject(dwzResult).toString();
				}
				if (StringUtils.isBlank(tblElectricpile.getElPiOwnCountyCode())) {
					dwzResult = new DwzAjaxResult("300", "请填写区县",
							"electricEditPage", "", "");
					return new JsonObject(dwzResult).toString();
				}

			}
			if (!StringUtils.isBlank(tblElectricpile.getElPiSimMac())) {
				if (tblElectricpile.getElPiSimMac().length() > 20) {
					dwzResult = new DwzAjaxResult("300", "SIM卡号字数超限",
							"electricAddPage", "", "");
					return new JsonObject(dwzResult).toString();
				}
			}
			if (!StringUtils.isBlank(tblElectricpile.getElPiSimPhoneNum())) {
				// boolean flag = false;
				try {
					// Pattern regex =
					// Pattern.compile("(\\+\\d+)?1[3458]\\d{9}$");
					// Matcher matcher = regex.matcher(tblElectricpile
					// .getElPiSimPhoneNum());
					// flag = matcher.matches();
					// if (!flag) {
					// dwzResult = new DwzAjaxResult("300", "SIM卡手机号码有误",
					// "electricAddPage", "", "");
					// return new JsonObject(dwzResult).toString();
					// }
				} catch (Exception e) {
					dwzResult = new DwzAjaxResult("300", "SIM卡手机号码有误",
							"electricAddPage", "", "");
					return new JsonObject(dwzResult).toString();
				}
			}

			// 费率修改 实时下发
			String sendStr = "";
			sendStr += tblElectricpile.getElpiElectricpilecode();
			sendStr += ":" + tblElectricpile.getElPiRateInformationId();
			MessageManager m = new MessageManager();
			String apiBaseUrl = m.getSystemProperties("apiRoot");
			String token = ApiUtil.getToken();
			// 调用接口更新费率
			HttpsUtil.getResponseParam(apiBaseUrl
					+ "/app/net/sendRate.do?paramStrs=" + sendStr + "&t="
					+ token, "status");
			commitLogService.insert("费率更新命令下发，主键：["
					+ tblElectricpile.getElPiRateInformationId() + "]");

			electricPileListService.changeElectricpile(tblElectricpile,
					listImage, detailImage, loginUser);
			dwzResult = new DwzAjaxResult("200", "修改成功", "getElectricPileList",
					"closeCurrent", "");

		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "修改失败", "electricEditPage",
					"", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/***
	 * 查看电桩
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showElectricPile")
	public String showElectricPile(@RequestParam("id") int pkElectricpile,
			Model model, HttpServletRequest request) {
		// 登陆用户
		TblUser user = SessionMgr.getWebUser(request);
		// ------------|01：初始话修改页面元素|-----------
		TblConfigcontent tblConfigcontent = new TblConfigcontent();
		tblConfigcontent
				.setCocoConfigpstatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
		tblConfigcontent.setCocoConfigparameterid(3);
		// 获取充电方式
		List<TblConfigcontent> chargeList = msConfigcontentService
				.findContentList(tblConfigcontent);
		model.addAttribute("chargeList", chargeList);

		// 获取接口标准
		tblConfigcontent.setCocoConfigparameterid(5);
		List<TblConfigcontent> connectorList = msConfigcontentService
				.findContentList(tblConfigcontent);
		model.addAttribute("connectorList", connectorList);

		// 获取功率
		tblConfigcontent.setCocoConfigparameterid(4);
		List<TblConfigcontent> powerList = msConfigcontentService
				.findContentList(tblConfigcontent);
		Collections.sort(powerList, new Comparator<TblConfigcontent>() {
			public int compare(TblConfigcontent o1, TblConfigcontent o2) {
				if (Float
						.parseFloat(o1
								.getCocoContent()
								.toLowerCase()
								.substring(
										0,
										o1.getCocoContent().toLowerCase()
												.indexOf("k"))) > Float
						.parseFloat(o2
								.getCocoContent()
								.toLowerCase()
								.substring(
										0,
										o2.getCocoContent().toLowerCase()
												.indexOf("k")))) {
					return 1;
				}
				if (Float
						.parseFloat(o1
								.getCocoContent()
								.toLowerCase()
								.substring(
										0,
										o1.getCocoContent().toLowerCase()
												.indexOf("k"))) == Float
						.parseFloat(o2
								.getCocoContent()
								.toLowerCase()
								.substring(
										0,
										o2.getCocoContent().toLowerCase()
												.indexOf("k")))) {
					return 0;
				}
				return -1;
			}
		});
		model.addAttribute("powerList", powerList);

		// 桩体用途
		tblConfigcontent.setCocoConfigparameterid(2);
		List<TblConfigcontent> elctrcUseList = msConfigcontentService
				.findContentList(tblConfigcontent);
		model.addAttribute("elctrcUseList", elctrcUseList);

		// 获取电桩类型
		tblConfigcontent.setCocoConfigparameterid(1);
		List<TblConfigcontent> typeList = msConfigcontentService
				.findContentList(tblConfigcontent);
		model.addAttribute("typeList", typeList);
		// 获取电桩的版本
		List<TblEquipmentVersion> epVersionList = equipmentVersionService
				.getVersionByProductID(pkElectricpile);
		model.addAttribute("epVersionList", epVersionList);
		// 制造商
		tblConfigcontent.setCocoConfigparameterid(11);
		List<TblCarmaker> markList = carmakerService.getAll(null);
		// List<TblConfigcontent> markList = msConfigcontentService
		// .findContentList(tblConfigcontent);
		model.addAttribute("markList", markList);
		// 获取产品型号信息
		List<TblTypespan> typespanList = electricPileListService
				.getTypespanList();
		model.addAttribute("typespanList", typespanList);
		// 初始化用户所选值
		TblElectricpile tblElectricpile = new TblElectricpile();
		tblElectricpile.setPkElectricpile(pkElectricpile);
		tblElectricpile = electricPileListService
				.getElectricpileById(tblElectricpile);
		// String offlineTime = tblElectricpile.getOfflineTime();
		// if(StringUtil.isNotEmpty(offlineTime)){
		// offlineTime = DateUtil.toDateFormat(DateUtil.parse(offlineTime,
		// "yyyy-MM-dd HH:mm"), "yyyy-MM-dd HH:mm");
		// tblElectricpile.setOfflineTime(offlineTime);
		// }

		// 系统初始化缓存数据
		CacheEntity provinceCache = CacheManager
				.getCacheInfo("provinceCodeList");
		List<Province> provinceCodeLis1 = (List<Province>) provinceCache
				.getValue();
		List<City> cities = cityService.getCityList1(tblElectricpile
				.getElPiOwnProvinceCode());
		List<Area> areas = areaService.getAreaList1(tblElectricpile
				.getElPiOwnCityCode());

		List<TblGateservice> gateList = gateService.getGateList();
		model.addAttribute("gateList", gateList);

		// 获取省份列表
		model.addAttribute("provinceCodeList", provinceCodeLis1);
		// 获取城市列表
		model.addAttribute("cityList", cities);
		// 获取区县列表
		model.addAttribute("countyList", areas);
		// 登陆用户级别
		model.addAttribute("loginUserLevel", user.getUserLevel());

		model.addAttribute("tblElectricpile", tblElectricpile);
		return "backstage/electric/electric-detail";
	}

	/**
	 * 获取待审批电桩列表
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getExamineElectricPileList")
	public String getExamineElectricPileList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblElectricpile tblElectricpile, Model model,
			HttpServletRequest request) {

		try {
			// ------------|01：初始化筛选条件|-----------
			/*
			 * if
			 * (!StringUtil.isEmpty(tblElectricpile.getElpiElectricpilecode()))
			 * { if (!isNum(tblElectricpile.getElpiElectricpilecode())) {
			 * tblElectricpile.setElpiElectricpilecode(null); } }
			 */

			// 登陆用户
			TblUser user = SessionMgr.getWebUser(request);

			TblConfigcontent tblConfigcontent = new TblConfigcontent();
			tblConfigcontent
					.setCocoConfigpstatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
			tblConfigcontent.setCocoConfigparameterid(3);
			// 获取充电方式
			List<TblConfigcontent> chargeList = msConfigcontentService
					.findContentList(tblConfigcontent);
			model.addAttribute("chargeList", chargeList);

			// 获取功率
			tblConfigcontent.setCocoConfigparameterid(4);
			List<TblConfigcontent> powerList = msConfigcontentService
					.findContentList(tblConfigcontent);
			Collections.sort(powerList, new Comparator<TblConfigcontent>() {
				public int compare(TblConfigcontent o1, TblConfigcontent o2) {
					if (Float.parseFloat(o1
							.getCocoContent()
							.toLowerCase()
							.substring(
									0,
									o1.getCocoContent().toLowerCase()
											.indexOf("k"))) > Float
							.parseFloat(o2
									.getCocoContent()
									.toLowerCase()
									.substring(
											0,
											o2.getCocoContent().toLowerCase()
													.indexOf("k")))) {
						return 1;
					}
					if (Float.parseFloat(o1
							.getCocoContent()
							.toLowerCase()
							.substring(
									0,
									o1.getCocoContent().toLowerCase()
											.indexOf("k"))) == Float
							.parseFloat(o2
									.getCocoContent()
									.toLowerCase()
									.substring(
											0,
											o2.getCocoContent().toLowerCase()
													.indexOf("k")))) {
						return 0;
					}
					return -1;
				}
			});
			model.addAttribute("powerList", powerList);

			// 获取电桩类型
			tblConfigcontent.setCocoConfigparameterid(1);
			List<TblConfigcontent> typeList = msConfigcontentService
					.findContentList(tblConfigcontent);
			model.addAttribute("typeList", typeList);

			// 获取接口标准
			tblConfigcontent.setCocoConfigparameterid(5);
			List<TblConfigcontent> connectorList = msConfigcontentService
					.findContentList(tblConfigcontent);
			model.addAttribute("connectorList", connectorList);

			// ------------|02：查询电桩业务处理|-----------
			tblElectricpile.setElpiState(5);// 上线
			tblElectricpile.setUserLevel(user.getUserLevel().toString());
			tblElectricpile.setElpiUserid(user.getUserId().toString());
			// 电桩总数
			long total = electricPileListService
					.getElectricpileByConditionCount(tblElectricpile);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblElectricpile.setPager(pager);
			List<Map<String, Object>> electricList = (List<Map<String, Object>>) electricPileListService
					.getElectricpileByCondition(tblElectricpile);
			pager.setTotal(total);

			model.addAttribute("electricList", electricList);
			model.addAttribute("pager", pager);
			model.addAttribute("tblElectricpile", tblElectricpile);
			model.addAttribute("loginUserLevel", user.getUserLevel());
		} catch (Exception e) {
			log.error("获取待审批电桩列表", e);
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		// 跳转至管理员主页面
		return "backstage/electric/electric-examine-list";
	}

	/**
	 * 获取待上线电桩列表
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getOnLineElectricPileList")
	public String getOnLineElectricPileList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblElectricpile tblElectricpile, Model model,
			HttpServletRequest request) {

		try {
			// ------------|01：初始化筛选条件|-----------
			/*
			 * if
			 * (!StringUtil.isEmpty(tblElectricpile.getElpiElectricpilecode()))
			 * { if (!isNum(tblElectricpile.getElpiElectricpilecode())) {
			 * tblElectricpile.setElpiElectricpilecode(null); } }
			 */

			// 登陆用户
			TblUser user = SessionMgr.getWebUser(request);

			TblConfigcontent tblConfigcontent = new TblConfigcontent();
			tblConfigcontent
					.setCocoConfigpstatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
			tblConfigcontent.setCocoConfigparameterid(3);
			// 获取充电方式
			List<TblConfigcontent> chargeList = msConfigcontentService
					.findContentList(tblConfigcontent);
			model.addAttribute("chargeList", chargeList);

			// 获取功率
			tblConfigcontent.setCocoConfigparameterid(4);
			List<TblConfigcontent> powerList = msConfigcontentService
					.findContentList(tblConfigcontent);
			Collections.sort(powerList, new Comparator<TblConfigcontent>() {
				public int compare(TblConfigcontent o1, TblConfigcontent o2) {
					if (Float.parseFloat(o1
							.getCocoContent()
							.toLowerCase()
							.substring(
									0,
									o1.getCocoContent().toLowerCase()
											.indexOf("k"))) > Float
							.parseFloat(o2
									.getCocoContent()
									.toLowerCase()
									.substring(
											0,
											o2.getCocoContent().toLowerCase()
													.indexOf("k")))) {
						return 1;
					}
					if (Float.parseFloat(o1
							.getCocoContent()
							.toLowerCase()
							.substring(
									0,
									o1.getCocoContent().toLowerCase()
											.indexOf("k"))) == Float
							.parseFloat(o2
									.getCocoContent()
									.toLowerCase()
									.substring(
											0,
											o2.getCocoContent().toLowerCase()
													.indexOf("k")))) {
						return 0;
					}
					return -1;
				}
			});
			model.addAttribute("powerList", powerList);

			// 获取电桩类型
			tblConfigcontent.setCocoConfigparameterid(1);
			List<TblConfigcontent> typeList = msConfigcontentService
					.findContentList(tblConfigcontent);
			model.addAttribute("typeList", typeList);

			// 获取接口标准
			tblConfigcontent.setCocoConfigparameterid(5);
			List<TblConfigcontent> connectorList = msConfigcontentService
					.findContentList(tblConfigcontent);
			model.addAttribute("connectorList", connectorList);

			// ------------|02：查询电桩业务处理|-----------
			// 设置分页对象
			tblElectricpile.setPager(pager);
			tblElectricpile.setElpiState(12);// 上线
			tblElectricpile.setUserLevel(user.getUserLevel().toString());
			tblElectricpile.setElpiUserid(user.getUserId().toString());
			// 电桩总数
			long total = electricPileListService
					.getElectricpileByConditionCount(tblElectricpile);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置查询参数
			tblElectricpile.setPager(pager);
			List<Map<String, Object>> electricList = (List<Map<String, Object>>) electricPileListService
					.getElectricpileByCondition(tblElectricpile);
			pager.setTotal(total);

			model.addAttribute("electricList", electricList);
			model.addAttribute("pager", pager);
			model.addAttribute("tblElectricpile", tblElectricpile);
			model.addAttribute("loginUserLevel", user.getUserLevel());
		} catch (Exception e) {
			log.error("获取待上线电桩列表", e);
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		// 跳转至管理员主页面
		return "backstage/electric/electric-onLine-list";
	}

	/***
	 * 删除电桩
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/removeElectricPile")
	@ResponseBody
	public String removeElectricPile(@RequestParam("ids") String ids) {
		// 处理结果信息
		DwzAjaxResult dwzResult = new DwzAjaxResult("200", "操作成功",
				"getElectricPileList", "", "");
		try {
			String[] idArray = ids.split(",");
			TblElectricpile tblElectricpile = new TblElectricpile();
			String error1 = "", error2 = "", error3 = "";
			for (String id : idArray) {
				tblElectricpile
						.setPkElectricpile(JudgeNullUtils.nvlInteger(id));
				tblElectricpile = electricPileListService
						.getElectricpileById(tblElectricpile);
				if (tblElectricpile.getElpiState() > 0) {
					error1 += tblElectricpile.getElpiElectricpilecode() + ",";
				}
				if (tblElectricpile.getElpiBinding() > 0) {
					error2 += tblElectricpile.getElpiElectricpilecode() + ",";

				}
				if (tblElectricpile.getPkConcentratorID() > 0) {
					error3 += tblElectricpile.getElpiElectricpilecode() + ",";
				}
			}
			String errorCode = "";
			// if (StringUtils.isNotBlank(error1)) {
			// errorCode += "不是草稿状态:" + error1;
			// }

			if (StringUtils.isNotBlank(error2)) {
				errorCode += "已绑定充电点:" + error2;
			}

			if (StringUtils.isNotBlank(error3)) {
				errorCode += "已绑定集中器:" + error3;
			}
			// 出错返回出错结果
			if (StringUtils.isNotBlank(errorCode)) {
				dwzResult.setStatusCode("300");
				dwzResult.setMessage(StringUtils.removeEnd(errorCode, ","));
				return dwzResult.toJSONString();
			}
			// 不出错执行删除操作
			for (String id : idArray) {
				tblElectricpile
						.setPkElectricpile(JudgeNullUtils.nvlInteger(id));
				if (tblElectricpile.getElpiState() == 0
						|| tblElectricpile.getElpiState() == 5) {
					tblElectricpileheadMapper.deleteByEleId(JudgeNullUtils
							.nvlInteger(id));
				} else {
					tblElectricpileheadMapper.updateDeleteFlag(JudgeNullUtils
							.nvlInteger(id));
				}
				electricPileListService.removeElectricPile(tblElectricpile);
			}
			return dwzResult.toJSONString();
		} catch (Exception e) {
			// 出错日志
			log.error("操作失败", e);
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "操作失败:系统出错",
					"getElectricPileList", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/***
	 * 修改电桩状态
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeElectricPileState")
	@ResponseBody
	public String changeElectricPileState(
			@RequestParam("ids") String pkElectricpile,
			@RequestParam("changeType") String changeType) {
		try {
			if (changeType.equalsIgnoreCase("onLinePage")) {// 上线通过--备注：elpiElectricpilecode传递过来的是电桩编号
				return onLinePage(pkElectricpile).toJSONString();
			} else if (changeType.equalsIgnoreCase("examineOkPage")) {// 审批通过--备注：elpiElectricpilecode传递过来的是电桩编号
				return examineOkPage(pkElectricpile).toJSONString();
			} else if (changeType.equalsIgnoreCase("examinePage")) {// 提交审批--备注：elpiElectricpilecode传递过来的是主键
				return examinePage(pkElectricpile).toJSONString();
			} else if (changeType.equalsIgnoreCase("offLinePage")) {// 离线申请--备注：elpiElectricpilecode传递过来的是主键
				return offLinePage(pkElectricpile).toJSONString();
			} else if (changeType.equalsIgnoreCase("toInit")) {// 状态重置--备注：elpiElectricpilecode传递过来的是主键
				return toInit(pkElectricpile).toJSONString();
			}
		} catch (Exception e) {
			// 出错日志
			log.error("操作失败", e);
		}
		// 返回处理结果信息
		return new DwzAjaxResult("300", "操作失败", "getOnLineElectricPileList",
				"", "").toJSONString();
	}

	/***
	 * 修改电桩状态
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeElectricPileState_02")
	@ResponseBody
	public String changeElectricPileState_02(
			@RequestParam("ids") String elpiElectricpilecode,
			@RequestParam("changeType") String changeType) {
		try {
			if (changeType.equalsIgnoreCase("toInit")) {// 状态重置--备注：elpiElectricpilecode传递过来的是主键
				return toInit(elpiElectricpilecode).toJSONString();
			}
		} catch (Exception e) {
			// 出错日志
			log.error("操作失败", e);
		}
		// 返回处理结果信息
		return new DwzAjaxResult("300", "操作失败", "getOnLineElectricPileList",
				"", "").toJSONString();
	}

	/**
	 * 电桩分享申请详细方法，专属电桩由状态10转为状态12分享申请中
	 * 
	 * @return
	 */
	private DwzAjaxResult toApplication(String elpiElectricpilecode) {

		DwzAjaxResult result = new DwzAjaxResult("200", "提交分享申请",
				"getElectricPileList", "", "");
		String[] electricpilecode = null;
		if (elpiElectricpilecode.lastIndexOf(",") > 0) {// 多个审批
			electricpilecode = elpiElectricpilecode.split(",");
		} else {
			electricpilecode = new String[] { elpiElectricpilecode };
		}
		String errorCode = "";
		List<TblElectricpile> electricList = new ArrayList<TblElectricpile>();
		// 校验所选择的数据是否为专属数据
		for (String code : electricpilecode) {
			TblElectricpile tblElectricpile = new TblElectricpile();
			tblElectricpile.setPkElectricpile(Integer.parseInt(code));
			tblElectricpile = electricPileListService
					.getElectricpileById(tblElectricpile);
			electricList.add(tblElectricpile);
			if (tblElectricpile.getElpiState() != 10) {
				DwzAjaxResult result_error = new DwzAjaxResult("300",
						"请选择状态为专属的数据", "getElectricPileList", "", "");
				return result_error;
			}

		}
		// 批量修改状态
		try {

			for (TblElectricpile pile : electricList) {

				electricPileListService.updateElectricPileSate(
						pile.getElpiElectricpilecode(), 12, null);
			}
		} catch (Exception e) {
			result.setMessage("更改失败:" + StringUtils.removeEnd(errorCode, ","));
			result.setStatusCode("300");
			return result;
		}
		return result;
	}

	/**
	 * 电桩重置，专属状态转为草稿状态
	 * 
	 * @return
	 */
	private DwzAjaxResult toInit(String elpiElectricpilecode) {

		DwzAjaxResult result = new DwzAjaxResult("200", "修改成功",
				"getElectricPileList", "", "");
		String[] electricpilecode = null;
		if (elpiElectricpilecode.lastIndexOf(",") > 0) {// 多个审批
			electricpilecode = elpiElectricpilecode.split(",");
		} else {
			electricpilecode = new String[] { elpiElectricpilecode };
		}
		String errorCode = "";
		List<TblElectricpile> electricList = new ArrayList<TblElectricpile>();
		for (String code : electricpilecode) {
			TblElectricpile tblElectricpile = new TblElectricpile();
			tblElectricpile.setPkElectricpile(Integer.parseInt(code));
			tblElectricpile = electricPileListService
					.getElectricpileById(tblElectricpile);
			electricList.add(tblElectricpile);
			if (tblElectricpile.getElpiState() != 10) {
				DwzAjaxResult result_error = new DwzAjaxResult("300",
						"请选择状态为专属的数据", "getElectricPileList", "", "");
				return result_error;
			}
			if (tblElectricpile.getRelevancePowerStation() != 0) {
				DwzAjaxResult result_error = new DwzAjaxResult("300",
						"已绑定充电点，不能变为草稿状态", "getElectricPileList", "", "");
				return result_error;
			}
		}
		try {

			for (TblElectricpile pile : electricList) {
				electricPileListService.updateElectricPileSate(
						pile.getElpiElectricpilecode(), 0, null);
			}
		} catch (Exception e) {
			result.setMessage("更改失败:" + StringUtils.removeEnd(errorCode, ","));
			result.setStatusCode("300");
			return result;
		}
		return result;
	}

	/**
	 * 电桩提交审批
	 * 
	 * @return
	 */
	private DwzAjaxResult examinePage(String elpiElectricpilecode) {

		DwzAjaxResult result = new DwzAjaxResult("200", "审批成功",
				"getElectricPileList", "", "");
		String[] electricpilecode = null;
		if (elpiElectricpilecode.lastIndexOf(",") > 0) {// 多个审批
			electricpilecode = elpiElectricpilecode.split(",");
		} else {
			electricpilecode = new String[] { elpiElectricpilecode };
		}
		String errorCode = "";
		List<TblElectricpile> electricList = new ArrayList<TblElectricpile>();
		for (String code : electricpilecode) {
			TblElectricpile tblElectricpile = new TblElectricpile();
			tblElectricpile.setPkElectricpile(Integer.parseInt(code));
			tblElectricpile = electricPileListService
					.getElectricpileById(tblElectricpile);
			electricList.add(tblElectricpile);
			if (tblElectricpile.getElpiState() != 0) {// 草稿状态才能提交审批
				errorCode += tblElectricpile.getElpiElectricpilecode() + ",";
			}

		}
		if (StringUtils.isNotBlank(errorCode)) {
			result.setMessage("不是草稿状态:" + StringUtils.removeEnd(errorCode, ","));
			result.setStatusCode("300");
		} else {
			for (TblElectricpile pile : electricList) {
				electricPileListService.updateElectricPileSate(
						pile.getElpiElectricpilecode(), 5, null);
			}
		}
		return result;
	}

	/**
	 * 电桩离线申请
	 * 
	 * @return
	 */
	private DwzAjaxResult offLinePage(String elpiElectricpilecode) {
		DwzAjaxResult result = new DwzAjaxResult("200", "申请成功",
				"getElectricPileList", "", "");
		String[] electricpilecode = null;
		if (elpiElectricpilecode.lastIndexOf(",") > 0) {// 多个审批
			electricpilecode = elpiElectricpilecode.split(",");
		} else {
			electricpilecode = new String[] { elpiElectricpilecode };
		}

		String error1 = "", error2 = "", error3 = "";
		List<TblElectricpile> electricList = new ArrayList<TblElectricpile>();
		for (String code : electricpilecode) {
			TblElectricpile tblElectricpile = new TblElectricpile();
			tblElectricpile.setPkElectricpile(Integer.parseInt(code));
			tblElectricpile = electricPileListService
					.getElectricpileById(tblElectricpile);
			electricList.add(tblElectricpile);
			// 上线桩体才能离线申请
			if (tblElectricpile.getElpiState() != 15) {
				error1 += tblElectricpile.getElpiElectricpilecode() + ",";
			}
			// 被绑定的电桩不允许变成专属状态
			if (tblElectricpile.getElpiBinding() != 0) {
				error2 += tblElectricpile.getElpiElectricpilecode() + ",";
			}
			// 充电或预约中的电桩不能变成专属状态
			if (electricPileListService.isBespokeOrCharging(tblElectricpile)) {
				error3 += tblElectricpile.getElpiElectricpilecode() + ",";
			}
		}
		String errorCode = "";
		if (StringUtils.isNotBlank(error1)) {
			errorCode += "不是上线状态:" + error1;
		}

		if (StringUtils.isNotBlank(error2)) {
			errorCode += "电桩已绑定充电点:" + error2;
		}

		if (StringUtils.isNotBlank(error3)) {
			errorCode += "正在预约或充电中:" + error3;
		}
		if (StringUtils.isNotBlank(errorCode)) {
			result.setStatusCode("300");
			result.setMessage(StringUtils.removeEnd(errorCode, ","));
			return result;
		}
		for (TblElectricpile pile : electricList) {
			electricPileListService.updateElectricPileSate(
					pile.getElpiElectricpilecode(), 10, null);
		}
		return result;

	}

	/**
	 * 电桩上线审批 electricStateType=15;
	 * 
	 * @return
	 */
	private DwzAjaxResult onLinePage(String pkElectricpile) {
		DwzAjaxResult result = new DwzAjaxResult("200", "上线成功",
				"getOnLineElectricPil", "", "");
		TblElectricpile tblElectricpile = new TblElectricpile();
		String[] electricpileId = null;
		if (pkElectricpile.lastIndexOf(",") > 0) {// 多个审批
			electricpileId = pkElectricpile.split(",");
		} else {
			electricpileId = new String[] { pkElectricpile };
		}
		String error1 = "", error2 = "", error3 = "", error4 = "";
		for (int i = 0; i < electricpileId.length; i++) {
			// tblElectricpile = electricPileListService
			// .getElectricPileByCode(electricpilecode[i]);
			tblElectricpile.setPkElectricpile(Integer
					.parseInt(electricpileId[i]));
			tblElectricpile = electricPileListService
					.getElectricpileById(tblElectricpile);
			if (tblElectricpile.getElpiBinding() != 0) {// 电桩被绑定
				error1 += tblElectricpile.getElpiElectricpilecode() + ",";
			} else if (tblElectricpile.getElpiIsappoint() == null
					|| tblElectricpile.getElpiIsappoint() == 0) {// 电桩不可预约
				error2 += tblElectricpile.getElpiElectricpilecode() + ",";
			} else if (tblElectricpile.getElPiHaveGps() == null
					|| tblElectricpile.getElPiHaveGps() == 0) {// 电桩不支持通讯
				error3 += tblElectricpile.getElpiElectricpilecode() + ",";
			} else if (tblElectricpile.getElPiRateInformationId() == null
					|| tblElectricpile.getElPiRateInformationId() == 0) {// 电桩无费率
				error4 += tblElectricpile.getElpiElectricpilecode() + ",";
			}
		}
		String errorCode = "";
		if (StringUtils.isNotBlank(error1)) {
			errorCode += "电桩被绑定：" + error1;
		}
		if (StringUtils.isNotBlank(error2)) {
			errorCode += "电桩不可预约:" + error2;
		}
		if (StringUtils.isNotBlank(error3)) {
			errorCode += "电桩不支持通讯:" + error3;
		}
		if (StringUtils.isNotBlank(error4)) {
			errorCode += "电桩无费率:" + error4;
		}

		if (StringUtils.isNotBlank(errorCode)) {
			result.setStatusCode("300");
			result.setMessage(StringUtils.removeEnd(errorCode, ","));
			return result;
		}
		for (int i = 0; i < electricpileId.length; i++) {
			tblElectricpile.setPkElectricpile(Integer
					.parseInt(electricpileId[i]));
			tblElectricpile = electricPileListService
					.getElectricpileById(tblElectricpile);
			electricPileListService.updateElectricPileSate(
					tblElectricpile.getElpiElectricpilecode(), 15, "0");
		}
		return result;

	}

	/**
	 * 电桩通过审批
	 * 
	 * @return
	 */
	private DwzAjaxResult examineOkPage(String elpiElectricpilecode) {
		DwzAjaxResult result = new DwzAjaxResult("200", "电桩审批成功",
				"getExamineElectricPi", "", "");
		String[] electricpilecode = null;
		if (elpiElectricpilecode.lastIndexOf(",") > 0) {// 多个审批
			electricpilecode = elpiElectricpilecode.split(",");
		} else {
			electricpilecode = new String[] { elpiElectricpilecode };
		}
		String error1 = "", error2 = "";
		TblElectricpile tblElectricpile = null;
		for (int i = 0; i < electricpilecode.length; i++) {
			tblElectricpile = electricPileListService
					.getElectricPileByCode(electricpilecode[i]);
			if (tblElectricpile.getElPiRateInformationId() == null
					|| tblElectricpile.getElPiRateInformationId() == 0) {
				error1 += electricpilecode[i] + ",";
			} else if (StringUtils.isBlank(electricpilecode[i])) {
				error2 = "电桩编号不能为空";
			}
		}
		String errorCode = "";
		if (StringUtils.isNotBlank(error1)) {
			errorCode += "电桩没有绑定费率:" + error1;
		}
		if (StringUtils.isNotBlank(error2)) {
			errorCode += error2;
		}

		if (StringUtils.isNotBlank(errorCode)) {
			result.setStatusCode("300");
			result.setMessage(StringUtils.removeEnd(errorCode, ","));
			return result;
		}
		for (int i = 0; i < electricpilecode.length; i++) {
			electricPileListService.updateSateAndCode(
					JudgeNullUtils.nvlStr(electricpilecode[i]), 10);
		}
		return result;
	}

	/**
	 * 电桩申请驳回页面
	 */
	@RequestMapping(value = "/examineReasonUi")
	public String examineReasonUi(
			@RequestParam("id") String elpiElectricpilecode, Model model) {
		model.addAttribute("elpiElectricpilecode", elpiElectricpilecode);
		// 跳转至用户选择画面
		return "backstage/electric/electric-examineReason-list";
	}

	/**
	 * 电桩上线驳回页面
	 */
	@RequestMapping(value = "/onlineReasonUi")
	public String onlineReasonUi(
			@RequestParam("id") String elpiElectricpilecode, Model model) {
		model.addAttribute("elpiElectricpilecode", elpiElectricpilecode);
		// 跳转至用户选择画面
		return "backstage/electric/electric-onReason-list";
	}

	/***
	 * 电桩上线驳回
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/onReason")
	@ResponseBody
	public String onReason(TblElectricpile tblElectricpile,
			HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		String ids = request.getParameter("ids");
		String[] idArr = ids.split(",");
		try {

			if (StringUtils.isBlank(tblElectricpile.getElpiRejectionreason())
					|| tblElectricpile.getElpiRejectionreason().equals(
							"请输入回驳原因")) {
				dwzResult = new DwzAjaxResult("300", "请输入回驳原因",
						"getOnLineElectricPil", "", "");
				return new JsonObject(dwzResult).toString();
			}
			for (String s : idArr) {
				tblElectricpile.setPkElectricpile(Integer.parseInt(s));
				;
				tblElectricpile.setElpiState(10);
				electricPileListService
						.changeElectricPileExamineReason(tblElectricpile);
			}
			dwzResult = new DwzAjaxResult("200", "驳回成功",
					"getOnLineElectricPil", "closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "驳回失败",
					"getOnLineElectricPil", "", "");
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
	public String examineReason(TblElectricpile tblElectricpile,
			HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		String ids = request.getParameter("ids");
		String[] idArr = ids.split(",");
		try {

			if (StringUtils.isBlank(tblElectricpile.getElpiRejectionreason())
					|| tblElectricpile.getElpiRejectionreason().equals(
							"请输入回驳原因")) {
				dwzResult = new DwzAjaxResult("300", "请输入回驳原因",
						"examineReasonPage", "", "");
				return new JsonObject(dwzResult).toString();
			}
			for (String s : idArr) {
				tblElectricpile.setElpiElectricpilecode(s);
				tblElectricpile.setElpiState(0);
				electricPileListService
						.changeElectricPileExamineReason(tblElectricpile);
			}
			dwzResult = new DwzAjaxResult("200", "驳回成功",
					"getExamineElectricPi", "closeCurrent", "");
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

	/**
	 * 用户一览处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param backRel
	 *            返回对象Rel
	 * @param processType
	 *            处理对象区分
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *             备注：用户列表默认获取管理员用户 TblUser(userLevel 1-管理员 2-商家 3-个体商家)
	 */
	@RequestMapping(value = "/userSelectList")
	public String getUserList(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblUser TblUser, Model model) {

		// 设置分页对象
		TblUser.setPager(pager);
		// 取得用户列表
		List<TblUser> userList = null;// userService.searchUserListByLevel(TblUser);
		// 用户总数
		long total = 0;// userService.searchUserCountByLevel(TblUser);
		pager.setTotal(total);

		// 获取爱充网全称
		MessageManager manager = MessageManager.getMessageManager();
		String acwName = manager.getSystemProperties("company.acw");
		// 将相关信息放到画面显示对象中
		model.addAttribute("userSelectList", userList);
		model.addAttribute("pager", pager);
		model.addAttribute("TblUser", TblUser);
		model.addAttribute("acwCompanyName", acwName);

		// 跳转至用户选择画面
		return "backstage/electric/electric-user";
	}

	public static boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	/***
	 * 根据登陆用户id所属公司和地区获取费率信息
	 * 
	 * @param model
	 * @return List<TblRateInfo>
	 */
	@RequestMapping("/getRateInfo")
	@ResponseBody
	public Object getRateInfo(HttpServletRequest request) {
		// 登陆用户
		TblUser user = SessionMgr.getWebUser(request);
		// 区code
		String raInAreaId = RequestParamUtil.getEncodeParam(request,
				"countyCode");
		String[] p1 = raInAreaId.split("\\|");
		// 用户级别
		Integer userLevel = user.getUserLevel();
		TblRateInfo rateInfo = new TblRateInfo();
		rateInfo.setRaIn_AreaId(p1[0]);
		List<TblRateInfo> rateList = null;
		// 个体商家
		if (userLevel == WanmaConstants.USER_LEVEL_BUSINESS_NORMAL) {
			rateInfo.setUserId(user.getUserId() + "");
			rateList = rateInfoService.getRateInfoByUser(rateInfo);
		} else if (userLevel == WanmaConstants.USER_LEVEL_BUSINESS) { // 纯商家
			// TblPureBusiness business = pureBusinessService
			// .findBusinessByUserId(user.getUserId()+"");
			// // 商家主账号
			// String parentLoveLoginId = business.getPuBuParentLoveLoginId();
			// // 如果主账号未空，则子账号即为主账号
			// if (StringUtil.isEmpty(parentLoveLoginId)) {
			// rateInfo.setParentLoveLoginId(business.getLoveLogin());
			// } else {
			// rateInfo.setParentLoveLoginId(parentLoveLoginId);
			// }
			// rateInfo.setUserId(user.getUserName());
			// rateList = rateInfoService.getRateInfoByUser(rateInfo);
			rateInfo.setUserId(user.getUserId().toString());
			rateList = rateInfoService.getRateInfoByCompany(rateInfo);
		} else { // 万马

			rateList = rateInfoService.getRateInfoByWM(rateInfo);
		}
		return JSONObject.toJSONString(rateList);
	}

	/**
	 * 获取电桩及枪头 查找列表模式数据
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/getElectricPileHeadList")
	public String getElectricPileHeadList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblElectricpile tblElectricpile, Model model,
			HttpServletRequest request) {

		try {

			// ------------|02：查询电桩枪头处理|-----------
			// 获取登陆用户
			TblUser loginUser = SessionMgr.getWebUser(request);

			// 获取地区信息
			model.addAttribute("proviceMap", WanmaConstants.provinceMap);
			String proviceCode = tblElectricpile.getElPiOwnProvinceCode();
			if (StringUtils.isNotBlank(proviceCode)) {
				List<Object> cityList = new ArrayList<Object>();
				for (String citycode : WanmaConstants.provinceCityMap
						.get(proviceCode)) {
					Map<String, Object> cityMap = WanmaConstants.cityMap;
					cityList.add(cityMap.get(citycode));
				}
				model.addAttribute("cityList", cityList);
			}
			String cityCode = tblElectricpile.getElPiOwnCityCode();
			if (StringUtils.isNotBlank(cityCode)) {
				List<Object> areaList = new ArrayList<Object>();
				for (String areacode : WanmaConstants.cityAreaMap.get(cityCode)) {
					Map<String, Object> areaMap = WanmaConstants.areaMap;
					areaList.add(areaMap.get(areacode));
				}
				model.addAttribute("areaList", areaList);
			}
			tblElectricpile.setUserLevel(loginUser.getUserLevel().toString());
			tblElectricpile.setElpiUserid(loginUser.getUserId().toString());
			// 电桩总数
			long total = electricPileListService
					.getCountElectricPileHeadList(tblElectricpile);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置查询参数
			tblElectricpile.setPager(pager);
			List<PowerElectricPile> electricList = (List<PowerElectricPile>) electricPileListService
					.getElectricPileHeadList(tblElectricpile);
			pager.setTotal(total);

			model.addAttribute("electricList", electricList);
			model.addAttribute("pager", pager);
			model.addAttribute("tblElectricpile", tblElectricpile);
			model.addAttribute("loginUser", loginUser);

		} catch (Exception e) {
			log.error("获取电桩状态失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		// 跳转至管理员主页面
		return "backstage/electric/electric-head-list";
	}

	/**
	 * 获取省市树结构
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/getAreaTree")
	public String getAreaTree(Model model) {

		try {
			model.addAttribute("areaTreeData",
					electricPileListService.getAreaTree());

		} catch (Exception e) {
			log.error("获取省市树结构", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		// 跳转至管理员主页面
		return "backstage/electric/areaZtree";
	}

	/**
	 * 导入电桩
	 * 
	 */
	@RequestMapping(value = "/toImport")
	public String toImportElectricpile(
			@RequestParam(value = "file", required = false) MultipartFile[] file) {
		// 跳转至管理员主页面
		return "backstage/electric/electric-import";
	}

	/**
	 * 导入电桩
	 * 
	 */
	@RequestMapping(value = "/importElectricpile")
	@ResponseBody
	public String importElectricpile(
			@RequestParam(value = "file", required = false) MultipartFile[] file,
			TblRateInfo rateInfo, HttpServletRequest request) {
		DwzAjaxResult dwzResult = null;
		ExcelUtil eu = new ExcelUtil();
		List<String[]> list = null;
		try {
			String[] arr = null;
			TblElectricpile electric = null;
			String tempStr = "";
			String[] tempArr = null, tempArr2 = null;
			;
			int elpiChargingmode = 0;
			int elpiPowersize = 0;
			int elpiType = 0;
			int elpiPowerinterface = 0;
			int elpiMaker = 0;
			int elPiOwnerCompany = 0;
			String elPiOwnProvinceCode = "";
			String elPiOwnCityCode = "";
			String elPiOwnCountyCode = "";
			String areaName = "";// 地区名称
			String ownerShip = "";// 初始化所有权
			List<TblConfigcontent> elpiChargingmodeList = WanmaConstants
					.getConfigByType(WanmaConstants.CONFIG_PILE_CHARGING_MODE);
			List<TblConfigcontent> elpiTypeList = WanmaConstants
					.getConfigByType(WanmaConstants.CONFIG_PILE_TYPE);
			List<TblConfigcontent> elpiPowerinterfaceList = WanmaConstants
					.getConfigByType(WanmaConstants.CONFIG_PILE_POWER_INTERFACE);
			List<TblCarmaker> elpiMakerList = carmakerService.getAll(null);
			List<Province> provinceList = WanmaConstants.provinceList;
			List<City> cityList = WanmaConstants.cityList;
			List<Area> areaList = WanmaConstants.areaList;
			List<Map<String, Object>> elpiPowersizeList = msConfigcontentService
					.getConfigContentListByCpId("4");
			// 读取EXCEL
			list = eu.readExcelToList(file[0].getInputStream());
			TblUser loginUser = SessionMgr.getWebUser(request);
			List<TblElectricpilehead> headList = null;
			TblElectricpilehead head = null;
			List<TblElectricpile> pileList = new ArrayList<TblElectricpile>();
			for (int i = 2; i < list.size(); i++) {
				arr = list.get(i);
				electric = new TblElectricpile();
				electric.setElpiElectricpilename(arr[0]);
				electric.setElpiLongitude(new BigDecimal(arr[1]));
				electric.setElpiLatitude(new BigDecimal(arr[2]));
				// 电桩充电方式
				elpiChargingmode = getConfigData(arr[3], elpiChargingmodeList);
				electric.setElpiChargingmode(elpiChargingmode);
				// 电桩额定功率
				Map<String, String> pszParam = new HashMap<String, String>();
				pszParam.put("cpId", "4");
				pszParam.put("txt", arr[4]);
				elpiPowersize = msConfigcontentService
						.getConfigIdByContent(pszParam);
				electric.setElpiPowersize(elpiPowersize);
				electric.setElpiElectricpileaddress(arr[5]);
				// 电桩类型
				elpiType = getConfigData(arr[6], elpiTypeList);
				electric.setElpiType(elpiType);
				// 电桩接口方式
				elpiPowerinterface = getConfigData(arr[7],
						elpiPowerinterfaceList);
				electric.setElpiPowerinterface(elpiPowerinterface);
				// 电桩制造商
				elpiMaker = 0;
				tempStr = arr[8];
				for (TblCarmaker carmaker : elpiMakerList) {
					if (tempStr.equals(carmaker.getMakerName())) {
						elpiMaker = carmaker.getPkCarmaker();
						break;
					}
				}
				electric.setElpiMaker(elpiMaker);
				// 运营平台
				elPiOwnerCompany = getElPiOwnerCompanyByStr(arr[9]);
				electric.setElPiOwnerCompany(elPiOwnerCompany);

				// 省
				tempStr = arr[10];
				for (Province province : provinceList) {
					if (tempStr.equals(province.getProvinceName())) {
						elPiOwnProvinceCode = province.getProvinceId();
						break;
					}
				}
				electric.setElPiOwnProvinceCode(elPiOwnProvinceCode);
				// 市
				tempStr = arr[11];
				for (City city : cityList) {
					if (tempStr.equals(city.getCityName())) {
						elPiOwnCityCode = city.getCityId();
						break;
					}
				}
				electric.setElPiOwnCityCode(elPiOwnCityCode);
				// 区
				tempStr = arr[12];
				for (Area area : areaList) {
					if (tempStr.equals(area.getAreaName())) {
						areaName = area.getAreaName();
						elPiOwnCountyCode = area.getAreaId();
						break;
					}
				}
				electric.setElPiOwnCountyCode(elPiOwnCountyCode);
				electric.setElpiIsappoint(getIfElseData(arr[13]));
				electric.setElPiHaveGps(getIfElseData(arr[14]));
				electric.setElPiHaveLedFlash(getIfElseData(arr[15]));
				electric.setElPiHaveConnectLine(getIfElseData(arr[16]));
				electric.setElpiOutputvoltage(new BigDecimal(arr[17]));
				electric.setElpiOutputcurrent(new BigDecimal(arr[18]));
				// 枪头信息
				headList = new ArrayList<TblElectricpilehead>();
				tempStr = arr[19];
				tempArr = tempStr.split(",");
				for (String s : tempArr) {
					tempArr2 = s.split(":");
					head = new TblElectricpilehead();
					head.setEphNum(tempArr2[0]);
					head.setHavaCarPlaceLock(new Integer(tempArr2[1]));
					head.setHaveRadar(new Integer(tempArr2[2]));
					headList.add(head);
				}
				electric.setElpiPowernumber(tempArr.length);
				electric.setHeadList(headList);

				// 产品类型
				tempStr = arr[20];
				if (tempStr != "" && tempStr != null) {
					Integer spanId = tblTypespanService.getTypeSpanId(tempStr);
					if (spanId != null) {
						electric.setElpiTypeSpanId(spanId);
					} else {
						dwzResult = new DwzAjaxResult("300", "导入第" + (i + 1)
								+ "条数据失败:当前产品型号:" + tempStr + ",不存在,请修改后重新导入",
								"electricOperate", "", "");
						return new JsonObject(dwzResult).toString();
					}
				} else {
					dwzResult = new DwzAjaxResult("300", "导入第" + (i + 1)
							+ "条数据失败:产品型号不能为空", "electricOperate", "", "");
					return new JsonObject(dwzResult).toString();
				}
				// 费率
				tempStr = arr[21];
				if (tempStr != "" && tempStr != null) {
					TblUser user = SessionMgr.getWebUser(request);
					rateInfo.setRaIn_AreaId(elPiOwnCountyCode);
					rateInfo.setUserId(user.getUserId() + "");
					rateInfo.setUser_level(user.getUserLevel());
					int tempStr1 = Integer.parseInt(tempStr);
					rateInfo.setPk_RateInformation(tempStr1);

					// 获取当前用户类型的费率列表
					int count = rateInfoService.getRateInfoList(rateInfo);
					if (count > 0) {
						electric.setElPiRateInformationId(new Integer(tempStr));
					} else {
						dwzResult = new DwzAjaxResult("300", "导入第" + (i + 1)
								+ "条数据失败:当前用户下地区:" + areaName + ",不存在费率:"
								+ tempStr + ",请修改后重新导入", "electricOperate", "",
								"");
						return new JsonObject(dwzResult).toString();
					}
				} else {
					dwzResult = new DwzAjaxResult("300", "导入第" + (i + 1)
							+ "条数据失败:费率不能为空", "electricOperate", "", "");
					return new JsonObject(dwzResult).toString();
				}
				// 所有权
				tempStr = arr[22];
				electric.setOwnerShip(tempStr);
				// 非必填项
				tempStr = "";
				if (arr.length >= 24) {
					tempStr = StringUtils.isNotBlank(arr[23]) ? arr[23] : "";
				}
				electric.setElPi_Tell(tempStr);
				tempStr = "";
				if (arr.length >= 25) {
					tempStr = StringUtils.isNotBlank(arr[24]) ? arr[24] : "";
				}
				electric.setElPiOnlineTime(tempStr);
				tempStr = "0";
				if (arr.length >= 26) {
					tempStr = StringUtils.isNotBlank(arr[25]) ? arr[25] : "0";
				}
				electric.setElPiSimPhoneNum(tempStr);
				tempStr = "";
				if (arr.length >= 27) {
					tempStr = StringUtils.isNotBlank(arr[26]) ? arr[26] : "";
				}
				electric.setElPiSimMac(tempStr);
				tempStr = "";
				if (arr.length >= 28) {
					tempStr = StringUtils.isNotBlank(arr[27]) ? arr[27] : "0";
				}
				electric.setCompanyNumber(tempStr);
				tempStr = "";
				if (arr.length >= 29) {
					tempStr = StringUtils.isNotBlank(arr[28]) ? arr[28] : "";
				}
				electric.setOfflineTime(tempStr);
				// 空值填充
				electric.setElpiAreacode("");
				electric.setElpiRejectionreason("");
				electric.setElpiPoweruser(3);
				electric.setElpiImage("");
				electric.setElpiDetailimage("");
				electric.setElpiInputvoltage(new BigDecimal(0));
				electric.setElpiInputcurrent(new BigDecimal(0));
				electric.setElpiRemark("");
				electric.setElpiCarid("");
				electric.setElpiPaystyle(1);
				electric.setElpiMaxelectricity(new BigDecimal(0));
				electric.setGateId(0);
				// electric.setOfflineTime("");
				pileList.add(electric);
			}

			electricPileListService.insertElectricpile(pileList, null, null,
					loginUser);
			// for (TblElectricpile pile : pileList) {
			// electricPileListService.addElectricpile(pile, null, null,
			// loginUser);
			// }
			dwzResult = new DwzAjaxResult("200", "导入成功", "getElectricPileList",
					"closeCurrent", "");
		} catch (Exception e) {
			log.error("导入出错:" + e.getLocalizedMessage());
			e.printStackTrace();
			dwzResult = new DwzAjaxResult("300", "导入失败:请检查后重新导入",
					"electricOperate", "", "");
		}
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 导出电桩
	 * 
	 */
	@RequestMapping(value = "/electricExport")
	@ResponseBody
	public void ElectricPileExport(
			@ModelAttribute("paramModel") TblElectricpile paramModel,
			HttpServletRequest req, HttpServletResponse res) {
		log.info("开始导出桩体信息");
		try {
			TblUser loginUser = SessionMgr.getWebUser(req);
			paramModel.setElpiUserid(loginUser.getUserId().toString());
			paramModel.setUserLevel(loginUser.getUserLevel().toString());
			electricPileExportService.export(res, paramModel, "桩体信息报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出桩体信息出错，数据写入出错");
		}
	}

	/**
	 * 查询公司标识列表
	 * 
	 * @param pager
	 * @param tblElectricpile
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getCompanyNumberList")
	public String getCompanyNumberList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblCompany tblCompany, Model model,
			HttpServletRequest request) {
		try {
			// 获取登陆用户
			TblUser loginUser = SessionMgr.getWebUser(request);
			tblCompany.setUserId(loginUser.getUserId());
			tblCompany.setUserLevel(loginUser.getUserLevel());
			tblCompany.setBindQueryFlag("1");
			// 用户总数
			long total = companyManagerService
					.findCompanyFlagListCount(tblCompany);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblCompany.setPager(pager);
			List<TblCompany> companyList = companyManagerService
					.findCompanyFlagList(tblCompany);
			pager.setTotal(total);

			model.addAttribute("companyList", companyList);
			model.addAttribute("listSize", companyList.size());
			model.addAttribute("pager", pager);
			model.addAttribute("tblCompany", tblCompany);
		} catch (Exception e) {
			log.error("获取电桩状态失败", e);
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		// 跳转至管理员主页面
		return "backstage/electric/companyNumber";
	}

	private int getIfElseData(String typeContent) {
		if ("是".equals(typeContent)) {
			return 1;
		}
		return 0;
	}

	private int getConfigData(String typeContent, List<TblConfigcontent> list) {
		int typeId = 0;
		for (TblConfigcontent config : list) {
			if (typeContent.equals(config.getCocoContent())) {
				typeId = config.getPkConfigcontent();
				break;
			}
		}
		return typeId;
	}

	private int getElPiOwnerCompanyByStr(String elPiOwnerCompany) {
		if ("爱充".equals(elPiOwnerCompany)) {
			return 1;
		} else if ("国网".equals(elPiOwnerCompany)) {
			return 2;
		} else if ("特斯拉".equals(elPiOwnerCompany)) {
			return 3;
		} else if ("其他".equals(elPiOwnerCompany)) {
			return 0;
		}
		return 0;
	}
}
