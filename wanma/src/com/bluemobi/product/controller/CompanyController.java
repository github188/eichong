/**
 * FileName:CompanyController.java
 * Author: Administrator
 * Create: 2014年7月4日
 * Last Modified: 2014年7月4日
 * Version: V1.0 
 *//*
package com.bluemobi.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.model.CompanyModel;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.model.common.TreeModel;
import com.bluemobi.product.service.CompanyService;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.TreeUtil;

*//**
 * 公司相关处理控制器
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月4日
 *//*

@Controller
@RequestMapping("/admin/company")
public class CompanyController {

	*//** 公司业务处理对象 *//*
	@Autowired
	private CompanyService companyService;

	*//**
	 * 公司登录处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/companyList")
	public String getCompanyList(@ModelAttribute("pager") DwzPagerMySQL pager,
			Model model) {

		// 登录公司信息
		List<CompanyModel> companyList = null;

		// 取得公司列表
		companyList = companyService.getCompanyList();

		// 将公司信息放到会话中
		model.addAttribute("companyList", companyList);
		model.addAttribute("pager", pager);

		// 跳转至管理员主页面
		return "admin/company/listCompany";
	}

	*//**
	 * 公司添加初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/newCompany")
	public String newCompany(Model model) {
		String companyId = "";
		CompanyModel companyModel = new CompanyModel();

		companyId = ProcessInfoCommon.getRandomKey();

		companyModel.setCompanyId(companyId);
		// 将公司信息设置到画面显示对象
		model.addAttribute("companyModel", companyModel);
		// 跳转至公司添加页面
		return "admin/company/company";
	}

	*//**
	 * 公司添加处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param companyModel
	 *            公司输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/saveCompany", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String saveCompany(
			@ModelAttribute("companyModel") CompanyModel companyModel,
			BindingResult result, HttpServletRequest request, Model model)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		// 添加的公司信息
		String newCompanyId;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "companyAddPage", "",
					"");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		// 设置更新者用户信息
		ProcessInfoCommon.setCreateUserInfo(companyModel, request);

		// 执行公司添加处理，并取得添加成功的公司详细信息
		newCompanyId = companyService.addCompany(companyModel);

		// 如果添加公司处理成功
		if (null != newCompanyId) {
			// 设置成功并返回公司一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "companyM", "", "");
			// 如果添加公司处理不成功
		} else {
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "companyM", "",
					"");
		}

		// 刷新公司树
		refreshCompanyTree(model);

		// 将公司信息设置到画面显示对象
		model.addAttribute("companyModel", companyModel);

		// 跳转至用户查看页面
		// return "admin/company/mainFrame";

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	*//**
	 * 公司拷贝初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param companyId
	 *            公司ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/copyCompany", method = RequestMethod.GET)
	public String copyCompany(@RequestParam("companyId") String companyId,
			Model model) {

		// 取得编辑对象公司信息
		CompanyModel companyModel = companyService.findCompany(companyId);

		// 新公司信息的设置
		companyModel.setCompanyId(ProcessInfoCommon.getRandomKey());

		// 清空公司信息
		ProcessInfoCommon.clearProcessUser(companyModel);

		// 将公司信息设置到画面显示对象
		model.addAttribute("companyModel", companyModel);

		// 跳转至公司编辑页面
		return "admin/company/company";
	}

	*//**
	 * 公司编辑初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param companyId
	 *            公司ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/editCompany", method = RequestMethod.GET)
	public String editCompany(@RequestParam("companyId") String companyId,
			Model model) {

		// 取得编辑对象公司信息
		CompanyModel companyModel = companyService.findCompany(companyId);

		// 将公司信息设置到画面显示对象
		model.addAttribute("companyModel", companyModel);

		// 跳转至公司编辑页面
		return "admin/company/editCompany";
	}

	*//**
	 * 公司编辑初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param companyId
	 *            公司ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/editCompanyLink", method = RequestMethod.GET)
	public String editCompanyLink(@RequestParam("modelKey") String modelKeys,
			Model model) {
		String companyId = "";
		String[] companyIds = modelKeys.split(",");
		if (companyIds.length == 2) {
			companyId = companyIds[0];
		}
		// 取得编辑对象公司信息
		CompanyModel companyModel = companyService.findCompany(companyId);

		// 将公司信息设置到画面显示对象
		model.addAttribute("companyModel", companyModel);

		// 跳转至公司编辑页面
		return "admin/company/company";
	}

	*//**
	 * 公司查看初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param companyId
	 *            公司ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/viewCompany", method = RequestMethod.GET)
	public String viewCompany(@RequestParam("companyId") String companyId,
			Model model) {
		// 取得查看对象公司信息
		CompanyModel companyModel = companyService.findCompany(companyId);

		// 将公司信息设置到画面显示对象
		model.addAttribute("companyModel", companyModel);

		// 跳转至公司查看页面
		return "admin/company/viewCompany";
	}

	*//**
	 * 公司编辑处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param companyModel
	 *            公司输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/modifyCompany", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String modifyCompany(
			@ModelAttribute("companyModel") CompanyModel companyModel,
			BindingResult result, Model model, HttpServletRequest request)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		// 更新处理结果
		String processResult = "";

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "companyM", "", "");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		// 设置更新者用户信息
		ProcessInfoCommon.setUpdateUserInfo(companyModel, request);

		// 执行公司添加处理，并取得处理结果信息
		processResult = companyService.modifyCompany(companyModel);

		// 如果更新公司处理成功
		if (CommonConsts.PROCESS_STATUS_OK.equals(processResult)) {
			// 设置成功并返回公司一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "companyM", "", "");
			// 如果更新公司处理不成功
		} else {
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "companyM", "",
					"");
		}

		// 刷新画面公司树
		refreshCompanyTree(model);
		// 刷新画面公司信息
		refreshCompanyData(model, companyModel.getCompanyId());

		// 跳转至公司编辑页面
		// return "admin/company/mainFrame";

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	*//**
	 * 公司删除处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param companyId
	 *            公司ID
	 * @param model
	 *            画面显示对象
	 * @return String 处理结果信息
	 * @throws 无
	 *//*
	@RequestMapping(value = "/removeCompany")
	@ResponseBody
	public String removeCompany(@RequestParam("companyId") String companyId) {
		// 执行删除处理
		companyService.removeCompany(companyId);

		// 设置处理结果信息
		DwzAjaxResult result = new DwzAjaxResult("200", "删除成功", "companyM", "",
				"");

		// 跳转至公司编辑页面
		// return "admin/company/mainFrame";

		// 返回处理结果信息
		return new JsonObject(result).toString();
	}

	*//**
	 * 公司唯一性检查
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param companyId
	 *            公司ID
	 * @return String 处理结果信息
	 * @throws 无
	 *//*
	@RequestMapping(value = "/checkCompanyUnique")
	@ResponseBody
	public String checkCompanyUnique(@RequestParam("companyId") String companyId) {

		// 返回处理结果信息
		return companyService.checkCompanyUnique(companyId);
	}

	*//**
	 * 用户查看初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param model
	 *            画面显示对象
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/companyMain")
	public String companyMain(Model model, HttpServletRequest request) {
		
		 * TreeUtil treeUtil = new TreeUtil(); List<TreeModel> treeList =
		 * treeUtil.getDeptCompanyData();
		 * treeUtil.setHref("company/editCompanyLink.do");
		 * treeUtil.setIsPutParameter(true); treeUtil.setTarget("ajax");
		 * treeUtil.setRel("companyBox");
		 
		String strHtml = getCompanyTree(false);

		// 将用户信息设置到画面显示对象
		model.addAttribute("treeModel", strHtml);

		// 跳转至用户查看页面
		return "admin/company/mainFrame";
	}

	*//**
	 * 用户查看初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param model
	 *            画面显示对象
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/companyDeptTree")
	public String companyDeptTree(Model model, HttpServletRequest request) {
		
		 * TreeUtil treeUtil = new TreeUtil(); List<TreeModel> treeList =
		 * treeUtil.getDeptTreeData();
		 * treeUtil.setHref("company/editCompany.do");
		 * treeUtil.setIsPutParameter(true); treeUtil.setTarget("ajax");
		 * treeUtil.setRel("jbsxBox");
		 
		String strHtml = getCompanyTree(true);

		// 将用户信息设置到画面显示对象
		model.addAttribute("treeModel", strHtml);

		// 跳转至用户查看页面
		return "admin/company/mainFrame";
	}

	private String getCompanyTree(boolean isDept) {
		String strHtml = "";
		TreeUtil treeUtil = new TreeUtil();
		List<TreeModel> treeList = null;
		if (isDept) {
			treeList = treeUtil.getDeptTreeData();
			treeUtil.setHref("company/editDepartment.do");
			treeUtil.setRel("deptBox");
		} else {
			treeList = treeUtil.getCompanyTreeData();
			treeUtil.setHref("company/editCompanyLink.do");
			treeUtil.setRel("companyBox");
		}
		treeUtil.setIsPutParameter(true);
		treeUtil.setTarget("ajax");
		strHtml = treeUtil.writeTree(treeList);

		return strHtml;
	}

	public void refreshCompanyTree(Model model) {
		String strHtml = getCompanyTree(false);

		// 将用户信息设置到画面显示对象
		model.addAttribute("treeModel", strHtml);

	}

	public void refreshCompanyData(Model model, String companyId) {

		// 取得修改后公司信息
		CompanyModel companyModel = companyService.findCompany(companyId);
		// 将用户信息设置到画面显示对象
		model.addAttribute("companyModel", companyModel);

	}
}
*/