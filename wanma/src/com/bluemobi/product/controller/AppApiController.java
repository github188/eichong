/**
 * FileName:AppApiController.java
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
import javax.servlet.http.HttpSession;

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
import com.bluemobi.product.common.DataMergeCommon;
import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.common.WebConst;
import com.bluemobi.product.model.AppApiDepartmentModel;
import com.bluemobi.product.model.AppApiModel;
import com.bluemobi.product.model.AppApiPostModel;
import com.bluemobi.product.model.AppApiRoleModel;
import com.bluemobi.product.model.CompanyPostModel;
import com.bluemobi.product.model.DepartmentModel;
import com.bluemobi.product.model.RoleModel;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.service.AppApiService;
import com.bluemobi.product.service.CompanyPostService;
import com.bluemobi.product.service.DepartmentService;
import com.bluemobi.product.service.RoleService;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.ObjectUtil;
import com.bluemobi.product.utils.StringUtil;

*//**
 * App接口相关处理控制器
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月4日
 *//*

@Controller
@RequestMapping("/admin/appApi")
public class AppApiController {

	*//** 日志文件生成器 *//*
	private static Logger log = Logger.getLogger(AppApiController.class);

	*//** App接口业务处理对象 *//*
	@Autowired
	private AppApiService appApiService;

	*//** 角色业务处理对象 *//*
	@Autowired
	private RoleService roleService;

	*//** 部门业务处理对象 *//*
	@Autowired
	private DepartmentService departmentService;

	*//** 部门业务处理对象 *//*
	@Autowired
	private CompanyPostService companyPostService;

	*//**
	 * App接口一览处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/searchAppApiList")
	public String searchAppApiList(
			@RequestParam("searchAppApiId") String appApiId, Model model) {

		// 登录App接口信息
		List<AppApiModel> appApiList = null;
		// 查询用App接口对象
		AppApiModel searchModel = new AppApiModel();

		// 设置App接口ID
		if (StringUtil.isNotEmpty(appApiId)) {
			searchModel.setAppApiId(appApiId);
		}

		// 取得App接口列表
		appApiList = appApiService.searchAppApiList(searchModel);

		// 将App接口信息放到会话中
		model.addAttribute("appApiList", appApiList);
		model.addAttribute("searchAppApiModel", searchModel);

		// 跳转至管理员主页面
		return "admin/appApi/listAppApi";
	}

	*//**
	 * App接口添加初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/newAppApi")
	public String newAppApi(Model model, HttpServletRequest request) {

		// 清空Session中App接口权限的信息
		clearAppApiAuthSession(request);
		// 清空Session中App接口权限修改的信息
		clearModifyAppApiAuthSession(request);

		String appApiId = "";
		AppApiModel appApiModel = new AppApiModel();

		appApiId = ProcessInfoCommon.getRandomKey();

		appApiModel.setAppApiId(appApiId);
		// 将App接口信息设置到画面显示对象
		model.addAttribute("appApiModel", appApiModel);
		// 跳转至App接口添加页面
		return "admin/appApi/appApi";
	}

	*//**
	 * App接口添加处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param appApiModel
	 *            App接口输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/saveAppApi", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String saveAppApi(
			@ModelAttribute("appApiModel") AppApiModel appApiModel,
			BindingResult result, HttpServletRequest request, Model model)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		// App接口添加的结果信息
		String processResult;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "appApiAddPage", "",
					"");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		// 设置更新者用户信息
		ProcessInfoCommon.setCreateUserInfo(appApiModel);

		// 设置App接口权限列表
		setAppApiAuthInfo(appApiModel, request);

		// 执行App接口添加处理，并取得App接口添加的结果信息
		try {
			appApiService.addAppApi(appApiModel);
			processResult = CommonConsts.PROCESS_STATUS_OK;
		} catch (Exception e) {
			processResult = CommonConsts.PROCESS_STATUS_NG;
			log.error(e.getLocalizedMessage());
		}

		// 如果更新App接口处理成功
		if (CommonConsts.PROCESS_STATUS_OK.equals(processResult)) {
			// 设置成功并返回App接口一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "appApiM", "", "");
			// 如果更新App接口处理不成功
		} else {
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "appApiM", "", "");
		}

		// 将App接口信息设置到画面显示对象
		model.addAttribute("appApiModel", appApiModel);

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	*//**
	 * App接口编辑初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param appApiId
	 *            App接口ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/editAppApi", method = RequestMethod.GET)
	public String editAppApi(@RequestParam("appApiId") String appApiId,
			Model model, HttpServletRequest request) {

		// 清空Session中App接口权限的信息
		clearAppApiAuthSession(request);
		// 清空Session中App接口权限修改的信息
		clearModifyAppApiAuthSession(request);

		// 对象App接口信息
		AppApiModel appApiModel = null;
		// 取得对象App接口信息
		appApiModel = getAppApiData(appApiId);

		// 将App接口信息设置到画面显示对象
		model.addAttribute("appApiModel", appApiModel);

		// 将App接口权限信息放到会话中
		setAppApiInfoToSession(appApiModel, request);

		// 跳转至App接口编辑页面
		return "admin/appApi/appApi";
	}

	*//**
	 * App接口编辑处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param appApiModel
	 *            App接口输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/modifyAppApi", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String modifyAppApi(
			@ModelAttribute("appApiModel") AppApiModel appApiModel,
			BindingResult result, Model model, HttpServletRequest request)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		// 更新处理结果
		String processResult = "";

		// 对象App接口信息
		AppApiModel newModel = null;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "appApiM", "", "");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		// 设置更新者用户信息
		ProcessInfoCommon.setUpdateUserInfo(appApiModel, request);

		// 设置App接口权限列表
		setAppApiAuthInfo(appApiModel, request);

		// 执行App接口更新处理，并取得处理结果信息
		try {
			appApiService.modifyAppApi(appApiModel);
			processResult = CommonConsts.PROCESS_STATUS_OK;
		} catch (Exception e) {
			processResult = CommonConsts.PROCESS_STATUS_NG;
			log.error(e.getLocalizedMessage());
		}

		// 如果更新App接口处理成功
		if (CommonConsts.PROCESS_STATUS_OK.equals(processResult)) {
			// 设置成功并返回App接口一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "appApiM", "", "");
			// 如果更新App接口处理不成功
		} else {
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "appApiM", "", "");
		}

		// 取得对象App接口信息
		newModel = getAppApiData(appApiModel.getAppApiId());

		// 将App接口信息设置到画面显示对象
		model.addAttribute("appApiModel", newModel);

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	*//**
	 * App接口删除处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param appApiId
	 *            App接口ID
	 * @param model
	 *            画面显示对象
	 * @return String 处理结果信息
	 * @throws 无
	 *//*
	@RequestMapping(value = "/removeAppApi")
	@ResponseBody
	public String removeAppApi(
			@ModelAttribute("appApiModel") AppApiModel appApiModel) {

		String appApiId = appApiModel.getAppApiId();

		// 更新处理结果
		String processResult = "";
		// 处理结果信息
		DwzAjaxResult dwzResult;

		try {
			// 执行删除处理
			appApiService.removeAppApi(appApiId);
			processResult = CommonConsts.PROCESS_STATUS_OK;
		} catch (Exception e) {
			processResult = CommonConsts.PROCESS_STATUS_NG;
			log.error(e.getLocalizedMessage());
		}

		// 如果更新App接口处理成功
		if (CommonConsts.PROCESS_STATUS_OK.equals(processResult)) {
			// 设置成功并返回App接口一览画面信息
			dwzResult = new DwzAjaxResult("200", "删除成功", "appApiM", "", "");
			// 如果删除App接口处理不成功
		} else {
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败:系统错误", "appApiM", "", "");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	*//**
	 * 角色删除处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param roleId
	 *            角色ID
	 * @param model
	 *            画面显示对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/removeAppApiRole")
	public String removeAppApiRole(@RequestParam("roleId") String roleId,
			Model model, HttpServletRequest request) {

		// 取得选择对象角色信息
		RoleModel roleModel = roleService.findRole(roleId);

		// 角色列表
		List<RoleModel> roleList = null;

		// 追加角色对象到session
		roleList = deleteAppApiRole(roleModel, request);
		//
		// 将角色信息放到画面显示对象中
		//
		model.addAttribute("roleSelectList", roleList);

		// // 跳转至角色列表显示AJAX画面
		return "admin/common/roleAddAjax";
	}

	*//**
	 * 删除并返回角色列表（将修改后的角色列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param roleModel
	 *            角色对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@SuppressWarnings("unchecked")
	private List<RoleModel> deleteAppApiRole(RoleModel roleModel,
			HttpServletRequest request) {

		// 角色列表
		List<RoleModel> roleList = null;
		// 追加角色
		List<RoleModel> roleDeleteList = null;

		//
		// 从会话中取得角色列表
		//
		roleList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_APP_ROLE);
		roleDeleteList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_APP_ROLE_DELETE);

		if (ObjectUtil.isEmpty(roleList)) {
			roleList = new ArrayList<RoleModel>();
		}

		if (ObjectUtil.isEmpty(roleDeleteList)) {
			roleDeleteList = new ArrayList<RoleModel>();
		}

		//
		// 如果当前角色列表中含有该角色，从列表中删除
		//
		if (DataMergeCommon.containsRole(roleList, roleModel)) {
			RoleModel.removeRole(roleList, roleModel);
			roleDeleteList.add(roleModel);
		}

		//
		// 将追加角色列表放到会话中
		//
		request.getSession().setAttribute(WebConst.SESS_APP_ROLE_DELETE,
				roleDeleteList);
		request.getSession().setAttribute(WebConst.SESS_APP_ROLE, roleList);

		// 返回角色列表
		return roleList;
	}

	*//**
	 * 职位删除处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param postIds
	 *            公司ID和职位ID
	 * @param model
	 *            画面显示对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/removeAppApiPost")
	public String removeAppApiPost(@RequestParam("postIds") String postIds,
			Model model, HttpServletRequest request) {

		// 角色信息查询用对象
		CompanyPostModel searchModel = new CompanyPostModel();
		// 公司ID
		String companyId = "";
		// 职位ID
		String postId = "";
		String[] idSplit = postIds.split(",");
		//
		// 取得公司ID和职位ID
		//
		if (idSplit.length == 2) {
			companyId = idSplit[0];
			postId = idSplit[1];
		}

		// 设置职位信息查询用对象信息
		searchModel.setCompanyId(companyId);
		searchModel.setPostId(postId);

		// 取得角色信息
		CompanyPostModel postModel = companyPostService
				.findCompanyPost(searchModel);

		// 职位列表
		List<CompanyPostModel> postList = null;

		// 追加职位对象到session
		postList = deleteAppApiPost(postModel, request);
		//
		// 将职位信息放到画面显示对象中
		//
		model.addAttribute("postSelectList", postList);

		// // 跳转至职位列表显示AJAX画面
		return "admin/common/postAddAjax";
	}

	*//**
	 * 删除并返回职位列表（将修改后的职位列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param postModel
	 *            职位对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@SuppressWarnings("unchecked")
	private List<CompanyPostModel> deleteAppApiPost(CompanyPostModel postModel,
			HttpServletRequest request) {

		// 职位列表
		List<CompanyPostModel> postList = null;
		// 追加职位
		List<CompanyPostModel> postDeleteList = null;

		//
		// 从会话中取得职位列表
		//
		postList = (List<CompanyPostModel>) request.getSession().getAttribute(
				WebConst.SESS_APP_POST);
		postDeleteList = (List<CompanyPostModel>) request.getSession()
				.getAttribute(WebConst.SESS_APP_POST_DELETE);

		if (ObjectUtil.isEmpty(postList)) {
			postList = new ArrayList<CompanyPostModel>();
		}

		if (ObjectUtil.isEmpty(postDeleteList)) {
			postDeleteList = new ArrayList<CompanyPostModel>();
		}

		//
		// 如果当前职位列表中含有该职位，从列表中删除
		//
		if (DataMergeCommon.containsPost(postList, postModel)) {
			CompanyPostModel.removePost(postList, postModel);
			postDeleteList.add(postModel);
		}

		//
		// 将追加职位列表放到会话中
		//
		request.getSession().setAttribute(WebConst.SESS_APP_POST_DELETE,
				postDeleteList);
		request.getSession().setAttribute(WebConst.SESS_APP_POST, postList);

		// 返回职位列表
		return postList;
	}

	*//**
	 * 部门删除处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param departmentIds
	 *            公司ID和部门ID
	 * @param model
	 *            画面显示对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/removeAppApiDept")
	public String removeAppApiDepartment(
			@RequestParam("departmentIds") String departmentIds, Model model,
			HttpServletRequest request) {

		// 角色信息查询用对象
		DepartmentModel searchModel = new DepartmentModel();
		// 公司ID
		String companyId = "";
		// 部门ID
		String departmentId = "";
		String[] idSplit = departmentIds.split(",");
		//
		// 取得公司ID和部门ID
		//
		if (idSplit.length == 2) {
			companyId = idSplit[0];
			departmentId = idSplit[1];
		}

		// 设置部门信息查询用对象信息
		searchModel.setCompanyId(companyId);
		searchModel.setDepartmentId(departmentId);

		// 取得角色信息
		DepartmentModel departmentModel = departmentService
				.findDepartment(searchModel);

		// 部门列表
		List<DepartmentModel> departmentList = null;

		// 追加部门对象到session
		departmentList = deleteAppApiDepartment(departmentModel, request);
		//
		// 将部门信息放到画面显示对象中
		//
		model.addAttribute("departmentSelectList", departmentList);

		// // 跳转至部门列表显示AJAX画面
		return "admin/common/departmentAddAjax";
	}

	*//**
	 * 删除并返回部门列表（将修改后的部门列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param departmentModel
	 *            部门对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@SuppressWarnings("unchecked")
	private List<DepartmentModel> deleteAppApiDepartment(
			DepartmentModel departmentModel, HttpServletRequest request) {

		// 部门列表
		List<DepartmentModel> departmentList = null;
		// 追加部门
		List<DepartmentModel> departmentDeleteList = null;

		//
		// 从会话中取得部门列表
		//
		departmentList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_APP_DEPT);
		departmentDeleteList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_APP_DEPT_DELETE);

		if (ObjectUtil.isEmpty(departmentList)) {
			departmentList = new ArrayList<DepartmentModel>();
		}

		if (ObjectUtil.isEmpty(departmentDeleteList)) {
			departmentDeleteList = new ArrayList<DepartmentModel>();
		}

		//
		// 如果当前部门列表中含有该部门，从列表中删除
		//
		if (DataMergeCommon.containsDepartment(departmentList, departmentModel)) {
			DepartmentModel.removeDepartment(departmentList, departmentModel);
			departmentDeleteList.add(departmentModel);
		}

		//
		// 将追加部门列表放到会话中
		//
		request.getSession().setAttribute(WebConst.SESS_APP_DEPT_DELETE,
				departmentDeleteList);
		request.getSession().setAttribute(WebConst.SESS_APP_DEPT,
				departmentList);

		// 返回部门列表
		return departmentList;
	}

	*//**
	 * App接口唯一性检查
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param appApiId
	 *            App接口ID
	 * @return String 处理结果信息
	 * @throws 无
	 *//*
	@RequestMapping(value = "/checkAppApiUnique")
	@ResponseBody
	public String checkAppApiUnique(@RequestParam("appApiId") String appApiId) {

		// 返回处理结果信息
		return appApiService.checkAppApiUnique(appApiId);
	}

	*//**
	 * App接口URL唯一性检查
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param appApiId
	 *            App接口ID
	 * @return String 处理结果信息
	 * @throws 无
	 *//*
	@RequestMapping(value = "/checkUrlUnique")
	@ResponseBody
	public String checkUrlUnique(@RequestParam("url") String url) {

		// 返回处理结果信息
		return appApiService.checkUrlUnique(url);
	}

	*//**
	 * 用户查看初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/appApiMain")
	public String appApiMain(Model model,
			@ModelAttribute AppApiModel searchAppApiModel) {

		// 登录App接口信息
		List<AppApiModel> appApiList = null;

		// 取得App接口列表
		appApiList = appApiService.getAppApiList();

		// 将App接口信息放到会话中
		model.addAttribute("appApiList", appApiList);
		model.addAttribute("searchAppApiModel", searchAppApiModel);

		// 跳转至用户查看页面
		return "admin/appApi/mainFrame";
	}

	*//**
	 * 取得App接口信息
	 * 
	 * @param pageId
	 *            页面ID
	 * @param appApiId
	 *            App接口ID
	 * @param AppApiModel
	 *            App接口信息
	 *//*
	public AppApiModel getAppApiData(String appApiId) {
		// 取得App接口信息
		AppApiModel appApiModel = appApiService.findAppApi(appApiId);

		// 返回App接口
		return appApiModel;

	}

	*//**
	 * 清空App接口职位权限Session中的信息
	 * 
	 * @param request
	 *//*
	@SuppressWarnings("unchecked")
	private List<AppApiPostModel> getAppApiPostBySession(
			AppApiModel appApiModel, HttpServletRequest request) {

		// App接口职位权限列表
		List<AppApiPostModel> appApiPostList = null;
		// 追加对象App接口职位权限列表
		List<CompanyPostModel> addPostList = null;
		// 删除对象App接口职位权限列表
		List<CompanyPostModel> deletePostList = null;

		addPostList = (List<CompanyPostModel>) request.getSession()
				.getAttribute(WebConst.SESS_APP_POST_ADD);
		deletePostList = (List<CompanyPostModel>) request.getSession()
				.getAttribute(WebConst.SESS_APP_POST_DELETE);

		appApiPostList = DataMergeCommon.mergeAppApiPost(addPostList,
				deletePostList, appApiModel);

		// 追加对象App接口职位权限
		request.getSession().setAttribute(WebConst.SESS_APP_POST_ADD, null);
		// 删除对象App接口职位权限
		request.getSession().setAttribute(WebConst.SESS_APP_POST_DELETE, null);

		return appApiPostList;
	}

	*//**
	 * 清空App接口角色权限Session中的信息，并返回该对象
	 * 
	 * @param request
	 *//*
	@SuppressWarnings("unchecked")
	private List<AppApiRoleModel> getAppApiRoleBySession(
			AppApiModel appApiModel, HttpServletRequest request) {

		// App接口角色权限列表
		List<AppApiRoleModel> appApiRoleList = null;
		// 追加对象App接口角色权限列表
		List<RoleModel> addRoleList = null;
		// 删除对象App接口角色权限列表
		List<RoleModel> deleteRoleList = null;

		addRoleList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_APP_ROLE_ADD);
		deleteRoleList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_APP_ROLE_DELETE);

		appApiRoleList = DataMergeCommon.mergeAppApiRole(addRoleList,
				deleteRoleList, appApiModel);

		// 追加对象App接口角色权限
		request.getSession().setAttribute(WebConst.SESS_APP_ROLE_ADD, null);
		// 删除对象App接口角色权限
		request.getSession().setAttribute(WebConst.SESS_APP_ROLE_DELETE, null);

		return appApiRoleList;
	}

	*//**
	 * 清空App接口角色权限Session中的信息，并返回该对象
	 * 
	 * @param request
	 *//*
	@SuppressWarnings("unchecked")
	private List<AppApiDepartmentModel> getAppApiDeptBySession(
			AppApiModel appApiModel, HttpServletRequest request) {

		// App接口角色权限列表
		List<AppApiDepartmentModel> appApiDepartmentList = null;
		// 追加对象App接口角色权限列表
		List<DepartmentModel> addDepartmentList = null;
		// 删除对象App接口角色权限列表
		List<DepartmentModel> deleteDepartmentList = null;

		addDepartmentList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_APP_DEPT_ADD);
		deleteDepartmentList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_APP_DEPT_DELETE);

		appApiDepartmentList = DataMergeCommon.mergeAppApiDepartment(
				addDepartmentList, deleteDepartmentList, appApiModel);

		// 追加对象App接口角色权限
		request.getSession().setAttribute(WebConst.SESS_APP_DEPT_ADD, null);
		// 删除对象App接口角色权限
		request.getSession().setAttribute(WebConst.SESS_APP_DEPT_DELETE, null);

		return appApiDepartmentList;
	}

	*//**
	 * 清空Session中App接口权限的信息
	 * 
	 * @param request
	 *//*
	private void clearAppApiAuthSession(HttpServletRequest request) {
		// App接口信息
		request.getSession().setAttribute(WebConst.SESS_APP_INFO, null);
		// App接口角色权限
		request.getSession().setAttribute(WebConst.SESS_APP_ROLE, null);
		// App接口职位权限
		request.getSession().setAttribute(WebConst.SESS_APP_POST, null);
		// App接口部门权限
		request.getSession().setAttribute(WebConst.SESS_APP_DEPT, null);
	}

	*//**
	 * 清空Session中App接口权限修改的信息
	 * 
	 * @param request
	 *//*
	private void clearModifyAppApiAuthSession(HttpServletRequest request) {

		// 追加对象App接口职位权限
		request.getSession().setAttribute(WebConst.SESS_APP_POST_ADD, null);
		// 删除对象App接口职位权限
		request.getSession().setAttribute(WebConst.SESS_APP_POST_DELETE, null);
		// 追加对象App接口角色权限
		request.getSession().setAttribute(WebConst.SESS_APP_ROLE_ADD, null);
		// 删除对象App接口角色权限
		request.getSession().setAttribute(WebConst.SESS_APP_ROLE_DELETE, null);
		// 追加对象App接口角色权限
		request.getSession().setAttribute(WebConst.SESS_APP_DEPT_ADD, null);
		// 删除对象App接口角色权限
		request.getSession().setAttribute(WebConst.SESS_APP_DEPT_DELETE, null);

	}

	*//**
	 * 设置部门信息到session中
	 * 
	 * @param appApiModel
	 * @param request
	 *//*
	private void setAppApiInfoToSession(AppApiModel appApiModel,
			HttpServletRequest request) {

		HttpSession session = request.getSession();

		if (appApiModel == null) {
			return;
		}
		// App接口职位权限列表
		List<CompanyPostModel> appApiPostList = appApiModel.getPostList();

		// App接口角色权限列表
		List<RoleModel> appApiRoleList = appApiModel.getRoleList();

		// App接口部门权限列表
		List<DepartmentModel> appApiDeptList = appApiModel.getDeptList();

		session.setAttribute(WebConst.SESS_APP_INFO, appApiModel);
		session.setAttribute(WebConst.SESS_APP_POST, appApiPostList);
		session.setAttribute(WebConst.SESS_APP_ROLE, appApiRoleList);
		session.setAttribute(WebConst.SESS_APP_DEPT, appApiDeptList);
	}

	*//**
	 * 设置App接口权限对象（部门，角色，部门）
	 * 
	 * @param appApiModel
	 *            App接口对象
	 * @param request
	 *            请求对象
	 * @return 无
	 * @exception 无
	 * 
	 *//*
	private void setAppApiAuthInfo(AppApiModel appApiModel,
			HttpServletRequest request) {

		if (appApiModel == null) {
			return;
		}
		// App接口职位权限列表
		List<AppApiPostModel> appApiPostList = null;

		// App接口角色权限列表
		List<AppApiRoleModel> appApiRoleList = null;

		// App接口部门权限列表
		List<AppApiDepartmentModel> appApiDeptList = null;

		// 取得App接口职位权限列表
		appApiPostList = getAppApiPostBySession(appApiModel, request);
		// 取得App接口角色权限列表
		appApiRoleList = getAppApiRoleBySession(appApiModel, request);
		// 取得App接口部门权限列表
		appApiDeptList = getAppApiDeptBySession(appApiModel, request);

		appApiModel.setAppApiPostList(appApiPostList);
		appApiModel.setAppApiRoleList(appApiRoleList);
		appApiModel.setAppApiDeptList(appApiDeptList);
	}
}
*/