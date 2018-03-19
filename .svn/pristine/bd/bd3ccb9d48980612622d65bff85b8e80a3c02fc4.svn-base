package com.wanma.controller;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.WanmaConstants;
import com.wanma.service.CmsConfigService;

@Controller
@RequestMapping("/admin/config/")
public class CmsConfigController {
	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(CmsConfigController.class);
	@Autowired
	private CmsConfigService configService;

	@RequestMapping(value = "/dictList")
	@ResponseBody
	public String dictList(HttpServletRequest request) {
		// key值定义,0:是否;1:电桩类型 (壁挂式);2:电桩使用类型(电动车);3:充电方式;4:功率;5:接口;6:枪头状态;7:搜索范围
		// 8:SIM卡运营商; 9:集中器状态 ;10:普通用户注册来源 ;11:制造厂商 ;12:电桩状态 ;13:绑定 ;14:预约支持
		// ;15:付款方式 ; 16:电池类型 17:车型充电方式;18:充值渠道19:订单状态;20:预约订单状态;21:用户状态;
		// 22:运营平台；29:故障类型 ;
		String type = request.getParameter("type");
		Map<String, List<Map<String, Object>>> tempMap = new HashMap<String, List<Map<String, Object>>>();
		if (StringUtils.isNotBlank(type)) {
			String[] typeArray = type.split(",");
			for (String s : typeArray) {
				tempMap.put(s, WanmaConstants.dictMap.get(s));
			}
		} else {
			tempMap = WanmaConstants.dictMap;
		}
		return new BaseResult(tempMap).toString();
	}

	@RequestMapping(value = "/provinceList")
	@ResponseBody
	public String provinceList(HttpServletRequest request) {
		return new BaseResult(WanmaConstants.provinceList).toString();
	}

	@RequestMapping(value = "/cityList")
	@ResponseBody
	public String cityList(HttpServletRequest request) {
		// 省编号
		String pId = request.getParameter("pId");
		Object cityList = WanmaConstants.cityMap.get(pId);
		return new BaseResult(cityList).toString();
	}

	@RequestMapping(value = "/areaList")
	@ResponseBody
	public String areaList(HttpServletRequest request) {
		// 市编号
		String cId = request.getParameter("cId");
		Object areaList = WanmaConstants.areaMap.get(cId);
		return new BaseResult(areaList).toString();
	}

	/**
	 * 制造厂商列表
	 *
	 * @param path
	 * @return
	 */
	@RequestMapping("pileMakerList")
	@ResponseBody
	public String pileMakerList() {
		BaseResult baseResult = new BaseFail(5001);
		List<Map<String, Object>> list = configService
				.getPileMakerDictList();
		baseResult = new BaseResult(list);
		return baseResult.toString();
	}
	/**
	 * 产品型号列表
	 *
	 * @param path
	 * @return
	 */
	@RequestMapping("typeSpanList")
	@ResponseBody
	public String typeSpanList() {
		BaseResult baseResult = new BaseFail(5001);
		List<Map<String, Object>> list = configService
				.getTypespanDictList();
		baseResult = new BaseResult(list);
		return baseResult.toString();
	}
	
	/**
	 * 费率列表
	 *
	 * @param path
	 * @return
	 */
	@RequestMapping("rateInfoList")
	@ResponseBody
	public String rateInfoList() {
		BaseResult baseResult = new BaseFail(5001);
		List<Map<String, Object>> list = configService
				.getRateInfoList();
		baseResult = new BaseResult(list);
		return baseResult.toString();
	}
	
	/**
	 * 汽车品牌列表
	 *
	 * @param path
	 * @return
	 */
	@RequestMapping("carCompanyList")
	@ResponseBody
	public String carCompanyList() {
		BaseResult baseResult = new BaseFail(5001);
		List<Map<String, Object>> list = configService
				.getCarCompanyList();
		baseResult = new BaseResult(list);
		return baseResult.toString();
	}

	
	/**
	 * 制造厂商列表
	 *
	 * @param path
	 * @return
	 */
	@RequestMapping("companyList")
	@ResponseBody
	public String companyList() {
		BaseResult baseResult = new BaseFail(5001);
		List<Map<String, Object>> list = configService
				.getCompanyDictList();
		baseResult = new BaseResult(list);
		return baseResult.toString();
	}
}
