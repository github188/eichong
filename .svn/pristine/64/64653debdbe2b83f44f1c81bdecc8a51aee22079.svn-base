package com.wanma.controller;
 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.BaseSuccess;
import com.pub.controller.BaseController;
import com.wanma.service.impl.CmsPowerRateServiceImpl;

/**
 * 运营管理-配置管理-省份电费
 * 
 * @author bc
 *
 */
@Controller
@RequestMapping("/admin/powerrate")
public class CmsPowerRateController extends BaseController {

	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsPowerRateController.class);

	@Autowired
	CmsPowerRateServiceImpl powerRateService;

	@RequestMapping(value = "/powerRatePage")
	public String powerRatePage(HttpServletRequest request) {
		return "backstage/powerrate/powerRateList";
	}

	@RequestMapping(value = "/getPowerRateList")
	@ResponseBody
	public String getPowerRateList(HttpServletRequest request,
			@ModelAttribute HashMap<String, Object> params, String provinceId,
			Model model) {
		BaseResult baseResult = new BaseFail(5001);
		List<HashMap<String, Object>> electricChargeList = new ArrayList<HashMap<String, Object>>();
		params.put("provinceId", provinceId);
		electricChargeList = powerRateService.getPowerrateList(params);
		baseResult = new BaseResult(electricChargeList);
		return baseResult.toString();
	}

	@RequestMapping("/powerRateDetail")
	@ResponseBody
	public String powerRateDetail(String provinceId) {
		try {
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("provinceId", provinceId);
			List<HashMap<String, Object>> electricChargeList = powerRateService.getPowerrateList(params);
			return new BaseResult(electricChargeList.get(0)).toString();
		} catch (Exception e) {
			log.error(this.getClass() + ".powerRateDetail() error:"
					+ e.getLocalizedMessage());
			return new BaseFail(5001).toString();
		}
	}

	@RequestMapping("/powerRateModify")
	@ResponseBody
	public String powerRateModify(
			@ModelAttribute HashMap<String, Object> params,
			HttpServletRequest request) {
		String provinceId = request.getParameter("provinceId");
		String Tip_Electricity = request.getParameter("Tip_Electricity");
		String Peak_Electricity = request.getParameter("Peak_Electricity");
		String Flat_Electricity = request.getParameter("Flat_Electricity");
		String Valley_Electricity = request.getParameter("Valley_Electricity");
		params.put("provinceId", provinceId);
		params.put("Tip_Electricity", Tip_Electricity);
		params.put("Peak_Electricity", Peak_Electricity);
		params.put("Flat_Electricity", Flat_Electricity);
		params.put("Valley_Electricity", Valley_Electricity);
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
		String LAST_UPDATE_DATE = dateFormat.format(now);
		params.put("LAST_UPDATE_DATE", LAST_UPDATE_DATE);
		BaseResult baseResult = new BaseSuccess();
		try {
			powerRateService.updatePowerrate(params);
		} catch (Exception e) {
			log.error(this.getClass() + ".powerRateModify() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		// 返回处理结果信息
		return baseResult.toString();
	}

}
