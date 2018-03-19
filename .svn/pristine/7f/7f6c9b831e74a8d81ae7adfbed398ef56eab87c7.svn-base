package com.wanma.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.RequestParamUtil;
import com.wanma.common.WanmaConstants;
import com.wanma.service.impl.CmsElectricChargeServiceImpl;


@Controller
@RequestMapping("/admin/electricCharge")
public class CmsElectricChargeController extends BaseController{


	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsElectricChargeController.class);
	
	@Autowired
	CmsElectricChargeServiceImpl  electricChargeService;
	
	@RequestMapping(value = "/getElectricChargeList")
	public String getElectricCharge(HttpServletRequest request,
			@ModelAttribute HashMap<String,Object> params,String provinceId,
			Model model){		
		if(!checkOprateValid(request)){
			return WanmaConstants.ERROR_PAGE;
		}
		List<HashMap<String, Object>> electricChargeList = new ArrayList<HashMap<String,Object>>();
		params.put("provinceId", provinceId);	  
		electricChargeList =  electricChargeService.getElectricCharge(params);				
		model.addAttribute("electricChargeList", electricChargeList);
		model.addAttribute("params", params);
		return "backstage/electricCharge/electricCharge-list";
	}
	
	@RequestMapping("/editElectricCharge")
	public String editElectricCharge(String provinceId, Model model,@ModelAttribute HashMap<String,Object> params) {	
		List<HashMap<String, Object>> electricChargeList = null;
		params.put("provinceId", provinceId);
		electricChargeList = electricChargeService.getElectricCharge(params);
		model.addAttribute("electricChargeList", electricChargeList);		
		return "backstage/electricCharge/electricCharge-edit";
	}
	@RequestMapping("/updateElectricCharge")
	@ResponseBody
	public String updateElectricCharge(@ModelAttribute HashMap<String,Object> params,HttpServletRequest request) {
		
		String provinceId = RequestParamUtil.getEncodeParam(request, "provinceId");
        String CREATE_DATE=request.getParameter("CREATE_DATE");
        String PROVINCE_NAME=request.getParameter("PROVINCE_NAME");
        String Tip_Electricity=request.getParameter("Tip_Electricity");
        String Peak_Electricity=request.getParameter("Peak_Electricity");
        String Flat_Electricity=request.getParameter("Flat_Electricity");
        String Valley_Electricity=request.getParameter("Valley_Electricity");
        params.put("provinceId", provinceId);
		params.put("CREATE_DATE", CREATE_DATE);
		params.put("PROVINCE_NAME", PROVINCE_NAME);
		params.put("Tip_Electricity", Tip_Electricity);
		params.put("Peak_Electricity",Peak_Electricity);
		params.put("Flat_Electricity",Flat_Electricity);
		params.put("Valley_Electricity",Valley_Electricity);
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		String LAST_UPDATE_DATE = dateFormat.format( now ); 
		params.put("LAST_UPDATE_DATE", LAST_UPDATE_DATE);				
		DwzAjaxResult dwzResult = null;
		try {
			electricChargeService.updateElectricCharge(params);
		//	electricChargeService.updateRateInfoByProvince(params);
			dwzResult = new DwzAjaxResult("200", "编辑成功", "electricChargeList",
					"closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "编辑失败", "electricChargeList", "", "");
		}
		log.info("******************更新电动车品牌类型-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	
}
