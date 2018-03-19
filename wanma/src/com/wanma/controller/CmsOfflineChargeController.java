package com.wanma.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.service.TblUserService;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.MultipartFileUtil;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblCompany;
import com.wanma.model.TblOrder;
import com.wanma.service.CmsCommitLogService;
import com.wanma.service.CmsOfflineChargeService;
import com.wanma.web.support.utils.ApiUtil;
import com.wanma.web.support.utils.ExcelUtil;
import com.wanma.web.support.utils.HttpsUtil;

@Controller
@RequestMapping("/admin/offlineCharge")
public class CmsOfflineChargeController {

	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsOfflineChargeController.class);
	@Autowired
	private CmsOfflineChargeService offlineChargeService;
	@Autowired
	CmsCommitLogService commitLogService;
	/**
	 * 离线充电次数管理列表
	 * 
	 * @param pager
	 * @param model
	 * @param request
	 * @return
	 */ 
	@RequestMapping(value = "/listOfflineCharge")
	public String listActivity(@ModelAttribute("pager") DwzPagerMySQL pager, Model model,TblCompany  company,
			HttpServletRequest request) {
			long total = offlineChargeService.getCompanyCount(company);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			company.setPager(pager);
			pager.setTotal(total);
			List<TblCompany> companyList =offlineChargeService.getCompanyList(company);
			model.addAttribute("company", company);
			model.addAttribute("companyList", companyList);
		
		// 跳转至管理员主页面
		return "backstage/offlineCharge/listOfflineCharge";
	}
	
	/**
	 * 离线充电次数管理新增跳转
	 * 
	 * @param pager
	 * @param model
	 * @param request
	 * @return
	 */ 
	@RequestMapping(value = "/OfflineChargeAddUi")
	public String OfflineChargeAddUi(@ModelAttribute("pager") DwzPagerMySQL pager, Model model,TblCompany  company,
			HttpServletRequest request) {
		List<TblCompany> companyList =offlineChargeService.getUnsetCompanyList();
		
		model.addAttribute("companyList", companyList);
		// 跳转至管理员主页面
		return "backstage/offlineCharge/newOfflineCharge";
	}
	
	@RequestMapping(value = "/getCpyCompanyNumber")
	@ResponseBody
	public String getCpyCompanyNumber(HttpServletRequest request) {
		String pkCompanyid=request.getParameter("pkCompanyid");
		String cpyCompanyNumber = offlineChargeService.getCpyCompanyNumberById(Integer.parseInt(pkCompanyid));
		return new JsonObject(cpyCompanyNumber).toString();
	}
	
	
	/**
	 * 离线充电次数管理新增
	 * 
	 * @param pager
	 * @param model
	 * @param request
	 * @return
	 */ 
	@RequestMapping(value = "/OfflineChargeAdd")
	@ResponseBody
	public String OfflineChargeAdd(TblCompany  company,HttpServletRequest request,HttpSession session) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			offlineChargeService.insertOfflineCharge(company);
			//拼成要发送的格式
			String sendStr = "";
			sendStr = company.getCpyCompanyNumber();
			sendStr += ":" + company.getCpyNum();
			MessageManager m = new MessageManager();
			String apiBaseUrl = m.getSystemProperties("apiRoot");
			String token = ApiUtil.getToken();
			//调用接口更新费率
			HttpsUtil.getResponseParam(apiBaseUrl + "/app/net/sendCompanyNum.do?paramStrs=" + sendStr + "&t=" + token, "status");
			commitLogService.insert("离线充电次数修改命令下发，公司主键：["+ company.getPkCompanyid() + "]，次数：["+company.getCpyNum()+"]");
			dwzResult = new DwzAjaxResult("200", "新增成功", "listOfflineCharge", "closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "新增失败", "OfflineChargeAddUi", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	/**
	 * 离线充电次数管理编辑跳转
	 * 
	 * @param pager
	 * @param model
	 * @param request
	 * @return
	 */ 
	@RequestMapping(value = "/OfflineChargeEditUi")
	public String OfflineChargeAddUi(@RequestParam("pkCompanyid") int pkCompanyid , Model model,TblCompany  company,
			HttpServletRequest request) {
		company = offlineChargeService.getCompanyById(pkCompanyid);
		
		model.addAttribute("company", company);
		// 跳转至管理员主页面
		return "backstage/offlineCharge/editOfflineCharge";
	}
	
	/**
	 * 离线充电次数管理编辑
	 * 
	 * @param pager
	 * @param model
	 * @param request
	 * @return
	 */ 
	@RequestMapping(value = "/OfflineChargeEdit")
	@ResponseBody
	public String OfflineChargeEdit(TblCompany  company,HttpServletRequest request,HttpSession session) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			offlineChargeService.insertOfflineCharge(company);
			//拼成要发送的格式
			String sendStr = "";
			sendStr = company.getCpyCompanyNumber();
			sendStr += ":" + company.getCpyNum();
			MessageManager m = new MessageManager();
			String apiBaseUrl = m.getSystemProperties("apiRoot");
			String token = ApiUtil.getToken();
			//调用接口更新费率
			HttpsUtil.getResponseParam(apiBaseUrl + "/app/net/sendCompanyNum.do?paramStrs=" + sendStr + "&t=" + token, "status");
			commitLogService.insert("离线充电次数修改命令下发，公司主键：["+ company.getPkCompanyid() + "]，次数：["+company.getCpyNum()+"]");
			dwzResult = new DwzAjaxResult("200", "编辑成功", "listOfflineCharge", "closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "编辑失败", "OfflineChargeEditUi", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	
	/**
	 * 删除充电次数管理编辑
	 * 
	 * @param pager
	 * @param model
	 * @param request
	 * @return
	 */ 
	@RequestMapping(value = "/delelteOfflineCharge")
	@ResponseBody
	public String delelteOfflineCharge(@RequestParam("pkCompanyids") int[] pkCompanyids , Model model,
			HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			for(int i=0 ;i<pkCompanyids.length;i++){
				offlineChargeService.deleteOfflineCharge(pkCompanyids[i]);	
				TblCompany company = new TblCompany();
				company = offlineChargeService.getCompanyById(pkCompanyids[i]);
				//拼成要发送的格式
				String sendStr = "";
				sendStr = company.getCpyCompanyNumber();
				sendStr += ":" + company.getCpyNum();
				MessageManager m = new MessageManager();
				String apiBaseUrl = m.getSystemProperties("apiRoot");
				String token = ApiUtil.getToken();
				//调用接口更新费率
				HttpsUtil.getResponseParam(apiBaseUrl + "/app/net/sendCompanyNum.do?paramStrs=" + sendStr + "&t=" + token, "status");
				commitLogService.insert("离线充电次数修改命令下发，公司主键：["+ company.getPkCompanyid() + "]，次数：["+company.getCpyNum()+"]");
			}
			dwzResult = new DwzAjaxResult("200", "删除成功", "listOfflineCharge", "", "");
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", "", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
}
