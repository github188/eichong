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

import com.bluemobi.product.common.WebConst;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.RequestParamUtil;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblUser;
import com.wanma.service.impl.CmsFeeLimitServiceImpl;


@Controller
@RequestMapping("admin/feelimit")
public class CmsFeeLimitController {

	
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsFeeLimitController.class);
	
	@Autowired
	CmsFeeLimitServiceImpl  cmsFeeLimitService;
	
	@RequestMapping(value = "/searchServiceLimitList")
	public String searchServiceLimitList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute HashMap<String,Object> params,String provinceId,
			Model model,HttpServletRequest request){	
		log.info("************获取费率上限列表-start************");		
		List<HashMap<String, Object>> serviceLimitList = new ArrayList<HashMap<String,Object>>();
		params.put("offset", pager.getOffset());
		params.put("numPerPage", pager.getNumPerPage());	   
		params.put("provinceId", provinceId);
	    long total = cmsFeeLimitService.searchServiceLimitCount(params);
		if(total<=pager.getOffset()){
			pager.setPageNum(1L);
			params.put("offset", 0);
		}
		serviceLimitList =  cmsFeeLimitService.searchServiceLimitList(params);				
		pager.setTotal(total);		
		
		// 取得登录用户信息
		TblUser loginUser = SessionMgr.getWebUser(request);
		model.addAttribute("params", params);
		model.addAttribute("serviceLimitList", serviceLimitList);
		model.addAttribute("pager", pager);
		model.addAttribute("loginUserLevel", loginUser.getUserLevel());
		log.info("************获取费率上限列表-end************");
		return "backstage/feeLimit/serviceLimit-list";
	}
	
	@RequestMapping("/editServiceLimit")
	public String editCarinfo(String cityId, Model model,@ModelAttribute HashMap<String,Object> params) {	
		log.info("************跳入编辑费率上限页面-start************");		
		List<HashMap<String, Object>> serviceLimit = null;
		params.put("cityId", cityId);
		serviceLimit = cmsFeeLimitService.findServiceLimit(params);
		model.addAttribute("serviceLimitList", serviceLimit);	
		log.info("************跳入编辑费率上限页面-end************");
		return "backstage/feeLimit/serviceLimit-edit";
	}
	@RequestMapping("/updateServiceLimit")
	@ResponseBody
	public String updateFeedbackReason(@ModelAttribute HashMap<String,Object> params,HttpServletRequest request) {
		log.info("************费率上限编辑-start************");	
		String serviceLimit=request.getParameter("serviceLimit");
		DwzAjaxResult dwzResult = null;
		if(!serviceLimit.matches("^\\d+(\\.\\d+)?$")){
			dwzResult = new DwzAjaxResult("300", "服务费上限不能为非数字", "ServiceLimitList",
					"closeCurrent", "");
			return new JsonObject(dwzResult).toString();
		}
		String cityId = RequestParamUtil.getEncodeParam(request, "cityId");
		String provinceId = RequestParamUtil.getEncodeParam(request, "provinceId");
        String createDate=request.getParameter("createDate");
        String cityName=request.getParameter("cityName");       
        params.put("cityId", cityId);
		params.put("provinceId", provinceId);
		params.put("createDate", createDate);
		params.put("cityName", cityName);
		params.put("serviceLimit",serviceLimit);
		if(!cmsFeeLimitService.checkMaxHighFeel(params)){
			dwzResult = new DwzAjaxResult("300", "低于同一个城市的已有费率的最高服务费值", "ServiceLimitList",
					"closeCurrent", "");
			return new JsonObject(dwzResult).toString();
		}
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		String lastUpdatedate = dateFormat.format( now ); 
		params.put("lastUpdatedate", lastUpdatedate);						
		try {
			cmsFeeLimitService.updateServiceLimitById(params);
			dwzResult = new DwzAjaxResult("200", "编辑成功", "ServiceLimitList",
					"closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "编辑失败", "ServiceLimitList", "", "");
		}
		log.info("******************费率上限编辑-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
}
