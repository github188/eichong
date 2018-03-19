package com.wanma.controller;
 
import java.io.IOException;
import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.BaseSuccess;
import com.base.common.SessionMgr;
import com.base.common.WanmaConstants;
import com.base.util.ExcelUtil;
import com.base.util.JudgeNullUtils;
import com.base.util.StringUtil;
import com.pub.model.Pager;
import com.pub.model.TblUser;
import com.wanma.dao.TblElectricpileheadMapper;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblPowerstation;
import com.wanma.service.CmsConfigService;
import com.wanma.service.CmsPowerstationService;
import com.wanma.service.ElectricPileListService;
/**
 * 运营管理-桩体管理-电桩信息
 * 
 * @author 
 * 
 */
@Controller
@RequestMapping("/admin/electric/")
public class CmsTblElectricpileController {
	/** 日志文件生成器 */
	private static Logger log = Logger
			.getLogger(CmsTblElectricpileController.class);
	@Autowired
	private ElectricPileListService electricPileListService;
	@Autowired
	private TblElectricpileheadMapper tblElectricpileheadMapper;
	@Autowired
	private CmsConfigService configService;
	@Autowired
	private CmsPowerstationService powerstationService;

	@RequestMapping(value = "/electricListPage")
	public String getElectricListPage(HttpServletRequest request) {
		return "backstage/electric/electricList";
	}

	/**
	 * 电桩列表
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getElectricPileList")
	@ResponseBody
	public String getElectricPileList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblElectricpile tblElectricpile, Model model,
			HttpServletRequest request) {
		BaseResult baseResult = new BaseFail(5001);
		try {
			// ------------|02：查询电桩业务处理|-----------
			// 获取登陆用户
			TblUser loginUser = SessionMgr.getWebUser(request);
			// 个体/纯商家只能查询所属电桩
			tblElectricpile.setElpiUserid(loginUser.getUserId().toString());
			tblElectricpile.setUserLevel(loginUser.getUserLevel().toString());
			// 电桩总数
			long total = electricPileListService
					.getElectricpileByConditionCount(tblElectricpile);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置查询参数
			tblElectricpile.setPager(pager);
			List<TblElectricpile> electricList = electricPileListService
					.getElectricpileByCondition(tblElectricpile);
			for (TblElectricpile electric : electricList) {
				electric.setExtValue1(WanmaConstants.getConfigName("3",
						electric.getElpiChargingmode().toString()));
				electric.setExtValue2(WanmaConstants.getConfigName("4",
						electric.getElpiPowersize().toString()));
				electric.setExtValue3(WanmaConstants.getConfigName("12",
						electric.getElpiState().toString()));
			}
			pager.setTotal(total);
			baseResult = new BaseResult(electricList, pager);
		} catch (Exception e) {
			log.error(this.getClass() + ".getElectricPileList() error:"
					+ e.getLocalizedMessage());
			return new BaseFail(5001).toString();
		}
		// 跳转至管理员主页面
		return baseResult.toString();
	}

	/**
	 * 电桩详情页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/electricDetailPage")
	public String electricDetailPage(HttpServletRequest request) {
		return "backstage/electric/electricDetail";
	}

	/**
	 * 电桩详情
	 * 
	 * @param request
	 * @param tblElectricpile
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/electricDetail")
	@ResponseBody
	public String electricDetail(HttpServletRequest request,
			TblElectricpile tblElectricpile, Model model) {
		tblElectricpile = electricPileListService
				.getElectricpileById(tblElectricpile);
		if (request.getParameter("format") != null) {
			tblElectricpile.setExtValue1(WanmaConstants.getConfigName("3",
					tblElectricpile.getElpiChargingmode().toString()));
			tblElectricpile.setExtValue2(WanmaConstants.getConfigName("12",
					tblElectricpile.getElpiState().toString()));
			tblElectricpile.setExtValue3(WanmaConstants.getConfigName("4",
					tblElectricpile.getElpiPowersize().toString()));
			tblElectricpile.setExtValue4(WanmaConstants.getConfigName("1",
					tblElectricpile.getElpiType().toString()));
			tblElectricpile.setExtValue5(WanmaConstants.getConfigName("5",
					tblElectricpile.getElpiPowerinterface().toString()));
			tblElectricpile.setExtValue6(WanmaConstants.getConfigName("11",
					tblElectricpile.getElpiMaker().toString()));
			tblElectricpile.setExtValue7(WanmaConstants.getConfigName("22",
					tblElectricpile.getElPiOwnerCompany().toString()));
			tblElectricpile.setExtValue8(WanmaConstants.getConfigName("0",
					tblElectricpile.getElPiHaveConnectLine().toString()));
			tblElectricpile.setExtValue9(WanmaConstants.getConfigName("0",
					tblElectricpile.getElPiHaveGps().toString()));
			tblElectricpile.setExtValue10(WanmaConstants.getConfigName("0",
					tblElectricpile.getElpiIsappoint().toString()));
			tblElectricpile.setExtValue11(WanmaConstants.getConfigName("0",
					tblElectricpile.getElPiHaveLedFlash().toString()));
			// 省市区
			List<Map<String, Object>> provinceList = WanmaConstants.provinceList;
			String pId = tblElectricpile.getElPiOwnProvinceCode();
			String cId = tblElectricpile.getElPiOwnCityCode();
			String aId = tblElectricpile.getElPiOwnCountyCode();
			tblElectricpile.setExtValue12(WanmaConstants.getConfigName(
					provinceList, pId, "provinceId", "provinceName"));
			Map<String, Object> cityMap = WanmaConstants.cityMap;
			List<Map<String, Object>> cityList = (List<Map<String, Object>>) cityMap
					.get(pId);
			tblElectricpile.setExtValue13(WanmaConstants.getConfigName(
					cityList, cId, "cityId", "cityName"));
			Map<String, Object> areaMap = WanmaConstants.areaMap;
			List<Map<String, Object>> areaList = (List<Map<String, Object>>) areaMap
					.get(cId);
			tblElectricpile.setExtValue14(WanmaConstants.getConfigName(
					areaList, aId, "areaId", "areaName"));
			// 电桩制造商
			List<Map<String, Object>> list = configService
					.getPileMakerDictList();
			tblElectricpile.setExtValue15(WanmaConstants.getConfigName(list,
					tblElectricpile.getElpiMaker().toString()));
			// 产品型号
			list = configService.getTypespanDictList();
			tblElectricpile.setExtValue16(WanmaConstants.getConfigName(list,
					tblElectricpile.getElpiTypeSpanId().toString()));

			// 费率
			list = configService.getRateInfoList();
			tblElectricpile.setExtValue17(WanmaConstants.getConfigName(list,
					tblElectricpile.getElPiRateInformationId().toString()));

		}
		return new BaseResult(tblElectricpile).toString();
	}

	/**
	 * 电桩编辑页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/electricEditPage")
	public String electricEditPage(HttpServletRequest request) {
		return "backstage/electric/electricEdit";
	}

	/**
	 * 电桩修改
	 * 
	 * @param request
	 * @param tblElectricpile
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/electricModify")
	@ResponseBody
	public String electricModify(HttpServletRequest request,
			TblElectricpile tblElectricpile, Model model) {
		try {
			electricPileListService.changeElectricpile(tblElectricpile, null,
					null, SessionMgr.getWebUser(request));
			return new BaseSuccess().toString();
		} catch (Exception e) {
			log.error(this.getClass() + ".electricModify() error:"
					+ e.getLocalizedMessage());
			return new BaseFail(5001).toString();
		}
	}

	/***
	 * 删除电桩
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/electricRemove")
	@ResponseBody
	public String electricRemove(@RequestParam("ids") String ids) {
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
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
			if (StringUtils.isNotBlank(error1)) {
				errorCode += "不是草稿状态:" + error1;
			}

			if (StringUtils.isNotBlank(error2)) {
				errorCode += "已绑定充电点:" + error2;
			}

			if (StringUtils.isNotBlank(error3)) {
				errorCode += "已绑定集中器:" + error3;
			}
			// 出错返回出错结果
			if (StringUtils.isNotBlank(errorCode)) {
				return new BaseFail(StringUtils.removeEnd(errorCode, ","))
						.toString();
			}
			// 不出错执行删除操作
			for (String id : idArray) {
				tblElectricpile
						.setPkElectricpile(JudgeNullUtils.nvlInteger(id));
				tblElectricpileheadMapper.deleteByEleId(JudgeNullUtils
						.nvlInteger(id));
				electricPileListService.removeElectricPile(tblElectricpile);
			}
			return baseResult.toString();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(this.getClass() + ".electricRemove() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

	/**
	 * 电桩分享 electricStateType=15;
	 * 
	 * @return
	 */
	@RequestMapping(value = "/electricOnLine")
	@ResponseBody
	private String electricOnLine(String ids) {
		BaseResult result = new BaseSuccess();
		TblElectricpile tblElectricpile = new TblElectricpile();
		String[] electricpileId = null;
		if (ids.lastIndexOf(",") > 0) {// 多个审批
			electricpileId = ids.split(",");
		} else {
			electricpileId = new String[] { ids };
		}
		String error1 = "", error2 = "", error3 = "", error4 = "";
		for (int i = 0; i < electricpileId.length; i++) {
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
			return new BaseFail(StringUtils.removeEnd(errorCode, ","))
					.toString();
		}
		for (int i = 0; i < electricpileId.length; i++) {
			tblElectricpile.setPkElectricpile(Integer
					.parseInt(electricpileId[i]));
			tblElectricpile = electricPileListService
					.getElectricpileById(tblElectricpile);
			electricPileListService.updateElectricPileSate(
					tblElectricpile.getElpiElectricpilecode(), 15, "0");
		}
		return result.toString();
	}

	/**
	 * 电桩专属
	 * 
	 * @return
	 */
	@RequestMapping(value = "/electricOffLine")
	@ResponseBody
	public String electricOffLine(String ids) {
		BaseResult result = new BaseSuccess();
		String[] idArray = null;
		if (ids.lastIndexOf(",") > 0) {// 多个审批
			idArray = ids.split(",");
		} else {
			idArray = new String[] { ids };
		}
		String error1 = "", error2 = "";
		TblElectricpile tblElectricpile = null;
		for (int i = 0; i < idArray.length; i++) {
			tblElectricpile = new TblElectricpile();
			tblElectricpile.setPkElectricpile(Integer.parseInt(idArray[i]));
			tblElectricpile = electricPileListService
					.getElectricpileById(tblElectricpile);
			if (tblElectricpile.getElPiRateInformationId() == null
					|| tblElectricpile.getElPiRateInformationId() == 0) {
				error1 += idArray[i] + ",";
			} else if (StringUtils.isBlank(idArray[i])) {
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
			return new BaseFail(StringUtils.removeEnd(errorCode, ","))
					.toString();
		}
		for (int i = 0; i < idArray.length; i++) {
			electricPileListService.updateSateAndCode(
					JudgeNullUtils.nvlStr(idArray[i]), 10);
		}
		return result.toString();
	}

	/**
	 * 导入电桩
	 * 
	 */
	@RequestMapping(value = "/electricImport")
	@ResponseBody
	public String electricImport(
			@RequestParam(value = "file", required = false) MultipartFile[] file,
			HttpServletRequest request) {
		BaseResult baseResult = new BaseSuccess();
		TblUser loginUser = SessionMgr.getWebUser(request);
		ExcelUtil eu = new ExcelUtil();
		List<String[]> list = null;
		try {
			String[] arr = null;
			TblElectricpile electric = null;
			// 读取EXCEL
			list = eu.readExcelToList(file[0].getInputStream());
			List<TblElectricpilehead> headList = null;
			TblElectricpilehead head = null;
			List<TblElectricpile> pileList = new ArrayList<TblElectricpile>();
			TblPowerstation station = new TblPowerstation();
			station.setPostName("");
			String temp = "";
			String[] tempArr, tempArr2;
			List<Map<String, Object>> typeSpanList = configService
					.getTypespanDictList();
			List<Map<String, Object>> pileMakerlist = configService
					.getPileMakerDictList();
			for (int i = 2; i < list.size(); i++) {
				arr = list.get(i);
				temp = arr[0];
				if (!temp.equals(station.getPostName())) {
					station.setPostName(temp);
					station = powerstationService
							.getPowerstationByName(station);
					// 不存在，创建充电点
					if (station == null) {
						station = new TblPowerstation();
						station.setPostName(temp);
						temp = WanmaConstants.getConfigData(
								WanmaConstants.provinceList, arr[1],
								"provinceName", "provinceId");
						station.setPostOwnProvinceCode(temp);
						List<Map<String, Object>> cityList = (List<Map<String, Object>>) WanmaConstants.cityMap
								.get(temp);
						temp = WanmaConstants.getConfigData(cityList, arr[2],
								"cityName", "cityId");
						station.setPostOwnCityCode(temp);
						List<Map<String, Object>> areaList = (List<Map<String, Object>>) WanmaConstants.areaMap
								.get(temp);
						temp = WanmaConstants.getConfigData(areaList, arr[3],
								"areaName", "areaId");
						station.setPostOwnCountyCode(temp);
						station.setPostLongitude(new BigDecimal(arr[4]));
						station.setPostLatitude(new BigDecimal(arr[5]));
						station.setPostAddress(arr[6]);
						station.setPostPhone(arr[7]);
						station.setPoStOnlineTime(arr[8]);
						station.setPostEleids("");
						station.setPostIsappoint(0);
						station.setPoStUserName("");
						powerstationService.addPowers(station, null, null,
								loginUser);
					}
				} else {
					electric = new TblElectricpile();
					// 充电点的公有信息
					electric.setElPiRelevancePowerStation(station
							.getPkPowerstation());
					electric.setElpiElectricpileaddress(station
							.getPostAddress());
					electric.setElPiOwnProvinceCode(station
							.getPostOwnProvinceCode());
					electric.setElPiOwnCityCode(station.getPostOwnCityCode());
					electric.setElPiOwnCountyCode(station
							.getPostOwnCountyCode());
					electric.setElpiLongitude(station.getPostLongitude());
					electric.setElpiLatitude(station.getPostLatitude());
					electric.setElpiTell(station.getPostPhone());
					electric.setElPiOnlineTime(station.getPoStOnlineTime());

					electric.setElpiElectricpilename(arr[9]);
					// 费率
					electric.setElPiRateInformationId(StringUtil
							.nullToZero(arr[10]));
					// 电桩充电方式
					temp = WanmaConstants.getConfigData("3", arr[11]);
					electric.setElpiChargingmode(StringUtil.nullToZero(temp));
					// 电桩额定功率
					temp = WanmaConstants.getConfigData("4", arr[12]);
					electric.setElpiPowersize(StringUtil.nullToZero(temp));
					// 电桩类型
					temp = WanmaConstants.getConfigData("1", arr[13]);
					electric.setElpiType(StringUtil.nullToZero(temp));
					// 电桩接口方式
					temp = WanmaConstants.getConfigData("5", arr[14]);
					electric.setElpiPowerinterface(StringUtil.nullToZero(temp));
					// 是否有枪()
					temp = WanmaConstants.getConfigData("0", arr[15]);
					electric.setElPiHaveConnectLine(StringUtil.nullToZero(temp));
					// 最大输出电压
					electric.setElpiOutputvoltage(new BigDecimal(arr[16]));
					// 最大输出电流
					electric.setElpiOutputcurrent(new BigDecimal(arr[17]));
					// 产品型号
					temp = WanmaConstants.getConfigData(typeSpanList, arr[18]);
					electric.setElpiTypeSpanId(StringUtil.nullToZero(temp));
					// 电桩制造商
					temp = WanmaConstants.getConfigData(pileMakerlist, arr[19]);
					electric.setElpiMaker(StringUtil.nullToZero(temp));
					// 公司标识
					electric.setCompanyNumber(arr[20]);
					// 资产所有者
					electric.setOwnerShip(arr[21]);
					// 运营商
					temp = WanmaConstants.getConfigData("22", arr[22]);
					electric.setElPiOwnerCompany(StringUtil.nullToZero(temp));
					// SIM卡号
					electric.setElPiSimPhoneNum(arr[23]);
					// SIM卡编码
					electric.setElPiSimMac(arr[24]);
					// 25合作公司名称,暂时不用
					// 停车费
					electric.setElpiParkingFee(arr[26]);
					// 到期时间
					electric.setOfflineTime(arr[27]);
					// 枪头信息
					headList = new ArrayList<TblElectricpilehead>();
					temp = arr[28];
					tempArr = temp.split(",");
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
					// 是否可预约
					temp = WanmaConstants.getConfigData("0", arr[29]);
					electric.setElpiIsappoint(StringUtil.nullToZero(temp));
					// 是否支持通讯
					temp = WanmaConstants.getConfigData("0", arr[30]);
					electric.setElPiHaveGps(StringUtil.nullToZero(temp));
					// 是否有LED灯
					temp = WanmaConstants.getConfigData("0", arr[31]);
					electric.setElPiHaveLedFlash(StringUtil.nullToZero(temp));

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
					pileList.add(electric);
				}

			}
			for (TblElectricpile pile : pileList) {
				electricPileListService.addElectricpile(pile, null, null,
						loginUser);
				System.out.println(pile);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(this.getClass() + ".electricImport() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

	/**
	 * 导入电桩
	 * 
	 */
	@RequestMapping(value = "/electricExport")
	@ResponseBody
	public void electricExport(
			@ModelAttribute TblElectricpile tblElectricpile,
			HttpServletRequest request,HttpServletResponse response) {
		TblUser loginUser = SessionMgr.getWebUser(request);
		// 个体/纯商家只能查询所属电桩
		tblElectricpile.setElpiUserid(loginUser.getUserId().toString());
		tblElectricpile.setUserLevel(loginUser.getUserLevel().toString());
		List<TblElectricpile> electricList = electricPileListService
				.getElectricpileByCondition(tblElectricpile);
		
		//excel导出
		ExcelUtil eu = new ExcelUtil();
		//转换成excel可用的数据格式
		List<String[]> dataList=new ArrayList<String[]>();
		String[] data=new String[]{"桩体编码","电桩名称","充电方式","功率","电桩状态","商家名称"};
		dataList.add(data);
		for (TblElectricpile electric : electricList) {
			data=new String[6];
			data[0]=electric.getElpiElectricpilecode();
			data[1]=electric.getElpiElectricpilename();
			data[2]=WanmaConstants.getConfigName("3",
					electric.getElpiChargingmode().toString());
			data[3]=WanmaConstants.getConfigName("4",
					electric.getElpiPowersize().toString());
			data[4]=WanmaConstants.getConfigName("12",
					electric.getElpiState().toString());
			data[5]=electric.getElPiUserName();
			dataList.add(data);
		}
		try {
			eu.export(dataList, response, "电桩列表.xlsx");
		} catch (IOException e) {
			e.printStackTrace();
			log.error(this.getClass() + ".electricExport() error:"
					+ e.getLocalizedMessage());
		}
	}
}
