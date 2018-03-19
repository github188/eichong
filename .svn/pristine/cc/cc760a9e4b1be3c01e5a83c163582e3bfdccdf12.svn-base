/**
 * FileName:DepartmentController.java
 * Author: Administrator
 * Create: 2014年7月4日
 * Last Modified: 2014年7月4日
 * Version: V1.0 
 *//*
package com.bluemobi.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import com.bluemobi.product.common.WebConst;
import com.bluemobi.product.model.DepartmentModel;
import com.bluemobi.product.model.UserDepartmentModel;
import com.bluemobi.product.model.UserModel;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.model.common.TreeModel;
import com.bluemobi.product.service.DepartmentService;
import com.bluemobi.product.service.UserService;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.ObjectUtil;
import com.bluemobi.product.utils.StringUtil;
import com.bluemobi.product.utils.TreeUtil;
import com.bluemobi.product.utils.UserUtil;

*//**
 * 部门相关处理控制器
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月4日
 *//*

@Controller
@RequestMapping("/admin/department")
public class DepartmentController {

	*//** 日志文件生成器 *//*
	private static Logger log = Logger.getLogger(DepartmentController.class);

	*//** 部门业务处理对象 *//*
	@Autowired
	private DepartmentService departmentService;

	*//** 用户业务处理对象 *//*
	@Autowired
	private UserService userService;

	*//**
	 * 取得部门列表
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
	@RequestMapping(value = "/departmentList")
	public String getDepartmentList(
			@ModelAttribute("pager") DwzPagerMySQL pager, Model model) {

		// 部门列表
		List<DepartmentModel> departmentList = null;

		// 取得部门列表
		departmentList = departmentService.getDepartmentList();

		// 将部门信息放到会话中
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("pager", pager);

		// 跳转至管理员主页面
		return "admin/department/listDepartment";
	}

	*//**
	 * 部门登录处理
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
	@RequestMapping(value = "/searchDeptUserList")
	public String searchDeptUserList(
			@ModelAttribute("userDepartmentModel") UserDepartmentModel searchModel,
			BindingResult result, @ModelAttribute("pager") DwzPagerMySQL pager,
			Model model) {

		// 部门用户列表
		List<UserModel> userList = null;

		// 取得部门列表
		userList = userService.searchDeptUserList(searchModel);

		// 将用户信息放到显示对象中
		model.addAttribute("userSelectList", userList);
		model.addAttribute("pager", pager);

		// 跳转至用户列表显示AJAX画面
		return "admin/common/userAddAjax";
	}

	*//**
	 * 部门添加初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/newDepartment")
	public String newDepartment(@RequestParam("companyId") String companyId,
			@RequestParam("departmentId") String parentDepartmentId,
			Model model, HttpServletRequest request) {

		// 清空部门用户Session中的信息
		clearDeptUserSession(request);

		String departmentId = "";
		DepartmentModel departmentModel = new DepartmentModel();

		departmentId = ProcessInfoCommon.getRandomKey();

		departmentModel.setDepartmentId(departmentId);
		departmentModel.setCompanyId(companyId);
		departmentModel.setParentDepartmentId(parentDepartmentId);
		// 将部门信息设置到画面显示对象
		model.addAttribute("departmentModel", departmentModel);
		model.addAttribute("userLevel", UserUtil.getLoginUserLevel());
		// 跳转至部门添加页面
		return "admin/department/department";
	}

	*//**
	 * 部门添加处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param departmentModel
	 *            部门输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/saveDepartment", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String saveDepartment(
			@ModelAttribute("departmentModel") DepartmentModel departmentModel,
			BindingResult result, HttpServletRequest request, Model model,
			@RequestParam("obj.companyId") String companyId,
			@RequestParam("obj.departmentId") String departmentId)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		// 部门添加的结果信息
		String processResult;

		// 部门用户列表
		List<UserDepartmentModel> userDepartmentList = null;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "departmentAddPage",
					"", "");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		if (StringUtil.isNotEmpty(companyId)
				&& StringUtil.isNotEmpty(departmentId)) {
			departmentModel.setResponsibleCompany(companyId);
			departmentModel.setResponsibleDepart(departmentId);
		}

		// 设置更新者用户信息
		ProcessInfoCommon.setCreateUserInfo(departmentModel, request);

		// 取得部门用户列表
		userDepartmentList = getDeptUserBySession(departmentModel, request);

		// 执行部门添加处理，并取得部门添加的结果信息
		processResult = departmentService.addDepartment(departmentModel,
				userDepartmentList);

		// 如果更新部门处理成功
		if (CommonConsts.PROCESS_STATUS_OK.equals(processResult)) {
			// 设置成功并返回部门一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "departmentM", "", "");
			// 如果更新部门处理不成功
		} else {
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "departmentM",
					"", "");
		}
		// 刷新部门树
		refreshDepartmentTree(model);

		// 将部门信息设置到画面显示对象
		model.addAttribute("departmentModel", departmentModel);

		request.getSession().setAttribute(WebConst.SESS_DEPT_INFO,
				departmentModel);
		request.getSession().setAttribute(WebConst.SESS_DEPT_USERS,
				departmentModel);

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	*//**
	 * 部门拷贝初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param departmentId
	 *            部门ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/copyDepartment", method = RequestMethod.GET)
	public String copyDepartment(@RequestParam("companyId") String companyId,
			@RequestParam("departmentId") String departmentId, Model model) {

		// 取得拷贝对象部门信息
		DepartmentModel departmentModel = getDepartmentData(companyId,
				departmentId);

		// 新部门信息的设置
		departmentModel.setDepartmentId(ProcessInfoCommon.getRandomKey());

		// 清空部门信息
		ProcessInfoCommon.clearProcessUser(departmentModel);

		// 将部门信息设置到画面显示对象
		model.addAttribute("departmentModel", departmentModel);

		// 跳转至部门编辑页面
		return "admin/department/department";
	}

	*//**
	 * 部门编辑初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param departmentId
	 *            部门ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/editDepartment", method = RequestMethod.GET)
	public String editDepartment(@RequestParam("modelKey") String modelKeys,
			Model model, HttpServletRequest request) {

		// 清空部门用户Session中的信息
		clearDeptUserSession(request);

		// 部门key
		String[] departmentIds = modelKeys.split(",");

		// 公司ID
		String companyId = "";
		// 部门ID
		String departmentId = "";
		// 部门用户列表
		List<UserModel> userList = null;

		// 对象部门信息
		DepartmentModel departmentModel = null;

		if (departmentIds.length == 2) {
			// 公司ID
			companyId = departmentIds[0];
			// 部门ID
			departmentId = departmentIds[1];

			// 取得对象部门信息
			departmentModel = getDepartmentData(companyId, departmentId);

			// 部门用户列表
			userList = getDeptUserList(companyId, departmentId);
		}

		// 将部门信息设置到画面显示对象
		model.addAttribute("departmentModel", departmentModel);

		// 将用户信息放到会话中
		model.addAttribute("userList", userList);
		model.addAttribute("userLevel", UserUtil.getLoginUserLevel());

		request.getSession().setAttribute(WebConst.SESS_DEPT_INFO,
				departmentModel);
		request.getSession().setAttribute(WebConst.SESS_DEPT_USERS, userList);

		// 跳转至部门编辑页面
		return "admin/department/department";
	}

	*//**
	 * 部门编辑处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param departmentModel
	 *            部门输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/modifyDepartment", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String modifyDepartment(
			@ModelAttribute("departmentModel") DepartmentModel departmentModel,
			BindingResult result, Model model, HttpServletRequest request,
			@RequestParam("obj.companyId") String companyId,
			@RequestParam("obj.departmentId") String departmentId)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		// 更新处理结果
		String processResult = "";
		// 部门用户列表
		List<UserDepartmentModel> userDepartmentList = null;

		// 对象部门信息
		DepartmentModel newModel = null;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "departmentM", "", "");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		// 设置更新者用户信息
		ProcessInfoCommon.setUpdateUserInfo(departmentModel, request);

		if (StringUtil.isNotEmpty(companyId)
				&& StringUtil.isNotEmpty(departmentId)) {
			departmentModel.setResponsibleCompany(companyId);
			departmentModel.setResponsibleDepart(departmentId);
		}

		// 取得部门用户列表
		userDepartmentList = getDeptUserBySession(departmentModel, request);

		// 执行部门更新处理，并取得处理结果信息
		processResult = departmentService.modifyDepartment(departmentModel,
				userDepartmentList);

		// 如果更新部门处理成功
		if (CommonConsts.PROCESS_STATUS_OK.equals(processResult)) {
			// 设置成功并返回部门一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "departmentM", "", "");
			// 如果更新部门处理不成功
		} else {
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "departmentM",
					"", "");
		}

		// 刷新画面部门树
		refreshDepartmentTree(model);

		// 取得对象部门信息
		newModel = getDepartmentData(departmentModel.getCompanyId(),
				departmentModel.getDepartmentId());

		// 将部门信息设置到画面显示对象
		model.addAttribute("departmentModel", newModel);

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	*//**
	 * 部门删除处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param departmentId
	 *            部门ID
	 * @param model
	 *            画面显示对象
	 * @return String 处理结果信息
	 * @throws 无
	 *//*
	@RequestMapping(value = "/removeDepartment")
	@ResponseBody
	public String removeDepartment(
			@ModelAttribute("departmentModel") DepartmentModel departmentModel) {
		// 设置处理结果信息
		DwzAjaxResult result = null;
		try {
			// 执行删除处理
			departmentService.removeDepartment(departmentModel);
			// 设置处理结果信息
			result = new DwzAjaxResult("200", "删除成功", "departmentM", "", "");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			// 设置处理结果信息
			result = new DwzAjaxResult("300", "删除失败", "departmentM", "", "");
		}

		// 返回处理结果信息
		return new JsonObject(result).toString();
	}

	*//**
	 * 部门用户删除处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param userId
	 *            用户ID
	 * @param model
	 *            画面显示对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/removeDeptUser")
	public String removeDeptUser(@RequestParam("userId") String userId,
			Model model, HttpServletRequest request) {

		// 用户列表
		List<UserModel> userList = null;

		// 取得选择对象用户信息
		UserModel userModel = userService.findUser(userId);

		// 追加用户对象到session
		userList = deleteDeptUser(userModel, request);
		//
		// 将用户信息放到画面显示对象中
		//
		model.addAttribute("userSelectList", userList);

		// // 跳转至用户列表显示AJAX画面
		return "admin/common/userAddAjax";
	}

	*//**
	 * 删除并返回用户列表（将修改后的用户列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param userModel
	 *            用户对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@SuppressWarnings("unchecked")
	private List<UserModel> deleteDeptUser(UserModel userModel,
			HttpServletRequest request) {

		// 用户列表
		List<UserModel> userList = null;
		// 追加用户
		List<UserModel> userAddList = null;
		// 删除用户
		List<UserModel> userDeleteList = null;

		//
		// 从会话中取得用户列表
		//
		userList = (List<UserModel>) request.getSession().getAttribute(
				WebConst.SESS_DEPT_USERS);
		userAddList = (List<UserModel>) request.getSession().getAttribute(
				WebConst.SESS_DEPT_ADD_USERS);
		userDeleteList = (List<UserModel>) request.getSession().getAttribute(
				WebConst.SESS_DEPT_DELETE_USERS);

		if (ObjectUtil.isEmpty(userList)) {
			userList = new ArrayList<UserModel>();
		}

		if (ObjectUtil.isEmpty(userDeleteList)) {
			userDeleteList = new ArrayList<UserModel>();
		}

		//
		// 如果当前用户列表中含有该用户，从列表中删除
		//
		if (UserUtil.containsUser(userList, userModel)) {
			UserUtil.removeUser(userList, userModel);
			if (UserUtil.containsUser(userAddList, userModel)) {
				UserUtil.removeUser(userAddList, userModel);
			} else {
				userDeleteList.add(userModel);
			}
		}

		//
		// 将用户列表放到会话中
		//
		request.getSession().setAttribute(WebConst.SESS_DEPT_ADD_USERS,
				userAddList);
		request.getSession().setAttribute(WebConst.SESS_DEPT_DELETE_USERS,
				userDeleteList);
		request.getSession().setAttribute(WebConst.SESS_DEPT_USERS, userList);

		// 返回用户列表
		return userList;
	}

	*//**
	 * 部门唯一性检查
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param departmentId
	 *            部门ID
	 * @return String 处理结果信息
	 * @throws 无
	 *//*
	@RequestMapping(value = "/checkDepartmentUnique")
	@ResponseBody
	public String checkDepartmentUnique(
			@RequestParam("companyId") String companyId,
			@RequestParam("departmentId") String departmentId) {

		// 部门对象
		DepartmentModel departmentModel = new DepartmentModel();
		departmentModel.setCompanyId(companyId);
		departmentModel.setDepartmentId(departmentId);

		// 返回处理结果信息
		return departmentService.checkDepartmentUnique(departmentModel);
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
	@RequestMapping(value = "/departmentMain")
	public String departmentMain(Model model, HttpServletRequest request) {
		
		 * TreeUtil treeUtil = new TreeUtil(); List<TreeModel> treeList =
		 * treeUtil.getDeptDepartmentData();
		 * treeUtil.setHref("department/editDepartmentLink.do");
		 * treeUtil.setIsPutParameter(true); treeUtil.setTarget("ajax");
		 * treeUtil.setRel("departmentBox");
		 
		String strHtml = getDepartmentTree(false);

		// 将用户信息设置到画面显示对象
		model.addAttribute("deptTreeModel", strHtml);

		// 跳转至用户查看页面
		return "admin/department/mainFrame";
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
	@RequestMapping(value = "/departmentDeptTree")
	public String departmentDeptTree(Model model, HttpServletRequest request) {
		
		 * TreeUtil treeUtil = new TreeUtil(); List<TreeModel> treeList =
		 * treeUtil.getDeptTreeData();
		 * treeUtil.setHref("department/editDepartment.do");
		 * treeUtil.setIsPutParameter(true); treeUtil.setTarget("ajax");
		 * treeUtil.setRel("jbsxBox");
		 
		String strHtml = getDepartmentTree(true);

		// 将用户信息设置到画面显示对象
		model.addAttribute("deptTreeModel", strHtml);

		// 跳转至用户查看页面
		return "admin/department/mainFrame";
	}

	*//**
	 * 刷新部门画面
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/refreshDeptPage")
	public String refreshDeptPage(Model model, HttpServletRequest request) {

		// 部门用户列表
		List<UserModel> userList = null;

		// 对象部门信息
		DepartmentModel departmentModel = null;

		departmentModel = (DepartmentModel) request.getSession().getAttribute(
				WebConst.SESS_DEPT_INFO);
		userList = (List<UserModel>) request.getSession().getAttribute(
				WebConst.SESS_DEPT_USERS);

		// 将部门信息设置到画面显示对象
		model.addAttribute("departmentModel", departmentModel);

		// 将用户信息放到会话中
		model.addAttribute("userList", userList);

		// 跳转至用户查看页面
		return "admin/department/department";

	}

	private String getDepartmentTree(boolean isDept) {
		String strHtml = "";
		TreeUtil treeUtil = new TreeUtil();
		List<TreeModel> treeList = null;

		treeList = treeUtil.getDeptTreeData();
		treeUtil.setHref("department/editDepartment.do");
		treeUtil.setRel("deptBox");

		treeUtil.setIsPutParameter(true);
		treeUtil.setTarget("ajax");
		strHtml = treeUtil.writeTree(treeList);

		return strHtml;
	}

	public void refreshDepartmentTree(Model model) {
		String strHtml = getDepartmentTree(false);

		// 将用户信息设置到画面显示对象
		model.addAttribute("deptTreeModel", strHtml);

	}

	*//**
	 * 取得部门信息
	 * 
	 * @param companyId
	 *            部门ID
	 * @param departmentId
	 *            部门ID
	 * @param DepartmentModel
	 *            部门信息
	 *//*
	public DepartmentModel getDepartmentData(String companyId,
			String departmentId) {

		// 查询用部门对象
		DepartmentModel searchModel = new DepartmentModel();

		// 部门ID
		searchModel.setCompanyId(companyId);
		// 部门ID
		searchModel.setDepartmentId(departmentId);

		// 取得部门信息
		DepartmentModel departmentModel = departmentService
				.findDepartment(searchModel);

		// 返回部门
		return departmentModel;

	}

	*//**
	 * 取得部门用户列表
	 * 
	 * @param companyId
	 *            部门ID
	 * @param departmentId
	 *            部门ID
	 * @param List
	 *            <UserModel> 部门用户列表
	 *//*
	private List<UserModel> getDeptUserList(String companyId,
			String departmentId) {

		// 部门用户列表
		List<UserModel> userList = null;
		// 查询用部门对象
		DepartmentModel searchModel = new DepartmentModel();

		// 部门ID
		searchModel.setCompanyId(companyId);
		// 部门ID
		searchModel.setDepartmentId(departmentId);

		// 取得部门用户列表
		userList = userService.getDeptUserList(searchModel);

		// 返回部门用户列表
		return userList;

	}

	*//**
	 * 清空部门用户Session中的信息
	 * 
	 * @param request
	 *//*
	@SuppressWarnings("unchecked")
	private List<UserDepartmentModel> getDeptUserBySession(
			DepartmentModel departmentModel, HttpServletRequest request) {

		// 部门用户列表
		List<UserDepartmentModel> userDepartmentList = null;
		// 追加对象部门用户列表
		List<UserModel> addUserList = null;
		// 删除对象部门用户列表
		List<UserModel> deleteUserList = null;

		addUserList = (List<UserModel>) request.getSession().getAttribute(
				WebConst.SESS_DEPT_ADD_USERS);
		deleteUserList = (List<UserModel>) request.getSession().getAttribute(
				WebConst.SESS_DEPT_DELETE_USERS);

		userDepartmentList = UserUtil.mergeProcessUser(addUserList,
				deleteUserList, departmentModel);

		// 部门用户
		// request.getSession().setAttribute(WebConst.SESS_DEPT_USERS, null);
		// 追加对象部门用户
		request.getSession().setAttribute(WebConst.SESS_DEPT_ADD_USERS, null);
		// 删除对象部门用户
		request.getSession()
				.setAttribute(WebConst.SESS_DEPT_DELETE_USERS, null);

		return userDepartmentList;
	}

	*//**
	 * 清空部门用户Session中的信息
	 * 
	 * @param request
	 *//*
	private void clearDeptUserSession(HttpServletRequest request) {
		// 部门用户
		// request.getSession().setAttribute(WebConst.SESS_DEPT_USERS, null);
		// 追加对象部门用户
		request.getSession().setAttribute(WebConst.SESS_DEPT_ADD_USERS, null);
		// 删除对象部门用户
		request.getSession()
				.setAttribute(WebConst.SESS_DEPT_DELETE_USERS, null);
	}
}
*/