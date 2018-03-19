/**
 * FileName:ActionController.java
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
import com.bluemobi.product.model.ActionDepartmentModel;
import com.bluemobi.product.model.ActionModel;
import com.bluemobi.product.model.ActionPostModel;
import com.bluemobi.product.model.ActionRoleModel;
import com.bluemobi.product.model.CompanyPostModel;
import com.bluemobi.product.model.DepartmentModel;
import com.bluemobi.product.model.RoleModel;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.model.common.TreeModel;
import com.bluemobi.product.service.ActionService;
import com.bluemobi.product.service.CompanyPostService;
import com.bluemobi.product.service.DepartmentService;
import com.bluemobi.product.service.RoleService;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.ObjectUtil;
import com.bluemobi.product.utils.TreeUtil;

*//**
 * 功能相关处理控制器
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月4日
 *//*

@Controller
@RequestMapping("/admin/action")
public class ActionController {

	*//** 日志文件生成器 *//*
	private static Logger log = Logger.getLogger(ActionController.class);

	*//** 功能业务处理对象 *//*
	@Autowired
	private ActionService actionService;

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
	 * 功能一览处理
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
	@RequestMapping(value = "/actionList")
	public String getActionList(@ModelAttribute("pager") DwzPagerMySQL pager,
			Model model) {

		// 登录功能信息
		List<ActionModel> actionList = null;

		// 取得功能列表
		actionList = actionService.getActionList();

		// 将功能信息放到会话中
		model.addAttribute("actionList", actionList);
		model.addAttribute("pager", pager);

		// 跳转至管理员主页面
		return "admin/action/listAction";
	}

	*//**
	 * 功能添加初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/newAction")
	public String newAction(@RequestParam("actionId") String parentActionId,
			Model model, HttpServletRequest request) {

		// 清空Session中功能权限的信息
		clearActionAuthSession(request);
		// 清空Session中功能权限修改的信息
		clearModifyActionAuthSession(request);

		String actionId = "";
		ActionModel actionModel = new ActionModel();

		actionId = ProcessInfoCommon.getRandomKey();

		actionModel.setActionId(actionId);
		// 将功能信息设置到画面显示对象
		model.addAttribute("actionModel", actionModel);
		// 跳转至功能添加页面
		return "admin/action/action";
	}

	*//**
	 * 功能添加处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param actionModel
	 *            功能输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/saveAction", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String saveAction(
			@ModelAttribute("actionModel") ActionModel actionModel,
			BindingResult result, HttpServletRequest request, Model model)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		// 功能添加的结果信息
		String processResult;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "actionAddPage", "",
					"");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		// 设置更新者用户信息
		ProcessInfoCommon.setCreateUserInfo(actionModel);

		// 设置功能权限列表
		setActionAuthInfo(actionModel, request);

		// 执行功能添加处理，并取得功能添加的结果信息
		try {
			actionService.addAction(actionModel);
			processResult = CommonConsts.PROCESS_STATUS_OK;
		} catch (Exception e) {
			processResult = CommonConsts.PROCESS_STATUS_NG;
			log.error(e.getLocalizedMessage());
		}

		// 如果更新功能处理成功
		if (CommonConsts.PROCESS_STATUS_OK.equals(processResult)) {
			// 设置成功并返回功能一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "actionM", "", "");
			// 如果更新功能处理不成功
		} else {
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "actionM", "", "");
		}

		// 将功能信息设置到画面显示对象
		model.addAttribute("actionModel", actionModel);

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	*//**
	 * 功能拷贝初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param actionId
	 *            功能ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/copyAction", method = RequestMethod.GET)
	public String copyAction(@RequestParam("actionId") String actionId,
			Model model, HttpServletRequest request) {

		// 清空Session中功能权限的信息
		// clearActionAuthSession(request);
		// 清空Session中功能权限修改的信息
		clearModifyActionAuthSession(request);

		// 取得拷贝对象功能信息
		ActionModel actionModel = getActionData(actionId);

		// 新功能信息的设置
		actionModel.setActionId(ProcessInfoCommon.getRandomKey());

		// 清空功能信息
		ProcessInfoCommon.clearProcessUser(actionModel);

		// 将功能信息设置到画面显示对象
		model.addAttribute("actionModel", actionModel);

		// 跳转至功能编辑页面
		return "admin/action/action";
	}

	*//**
	 * 功能编辑初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param actionId
	 *            功能ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/editAction", method = RequestMethod.GET)
	public String editAction(@RequestParam("modelKey") String modelKeys,
			Model model, HttpServletRequest request) {

		// 清空Session中功能权限的信息
		// clearActionAuthSession(request);
		// 清空Session中功能权限修改的信息
		clearModifyActionAuthSession(request);

		// 功能ID
		String[] actionIds = modelKeys.split(",");

		// 画面ID
		String pageId = "";
		// 功能ID
		String actionId = "";

		// 对象功能信息
		ActionModel actionModel = null;

		if (actionIds.length == 1) {
			pageId = actionIds[0];
			// 取得对象功能信息
			actionModel = getActionData(pageId);
		} else if (actionIds.length == 2) {
			pageId = actionIds[0];
			actionId = actionIds[1];
			// 取得对象功能信息
			actionModel = getActionData(pageId, actionId);
		}

		// 将功能信息设置到画面显示对象
		model.addAttribute("actionModel", actionModel);

		// 将功能权限信息放到会话中
		setActionInfoToSession(actionModel, request);

		// 跳转至功能编辑页面
		return "admin/action/action";
	}

	*//**
	 * 功能编辑处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param actionModel
	 *            功能输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/modifyAction", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String modifyAction(
			@ModelAttribute("actionModel") ActionModel actionModel,
			BindingResult result, Model model, HttpServletRequest request)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		// 更新处理结果
		String processResult = "";

		// 对象功能信息
		ActionModel newModel = null;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "actionM", "", "");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		// 设置更新者用户信息
		ProcessInfoCommon.setUpdateUserInfo(actionModel, request);

		// 设置功能权限列表
		setActionAuthInfo(actionModel, request);

		// 执行功能更新处理，并取得处理结果信息
		try {
			actionService.modifyAction(actionModel);
			processResult = CommonConsts.PROCESS_STATUS_OK;
		} catch (Exception e) {
			processResult = CommonConsts.PROCESS_STATUS_NG;
			log.error(e.getLocalizedMessage());
		}

		// 如果更新功能处理成功
		if (CommonConsts.PROCESS_STATUS_OK.equals(processResult)) {
			// 设置成功并返回功能一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "actionM", "", "");
			// 如果更新功能处理不成功
		} else {
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "actionM", "", "");
		}

		// 取得对象功能信息
		newModel = getActionData(actionModel.getActionId());

		// 将功能信息设置到画面显示对象
		model.addAttribute("actionModel", newModel);

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	*//**
	 * 功能删除处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param actionId
	 *            功能ID
	 * @param model
	 *            画面显示对象
	 * @return String 处理结果信息
	 * @throws 无
	 *//*
	@RequestMapping(value = "/removeAction")
	@ResponseBody
	public String removeAction(
			@ModelAttribute("actionModel") ActionModel actionModel) {

		String actionId = actionModel.getActionId();

		// 更新处理结果
		String processResult = "";
		// 处理结果信息
		DwzAjaxResult dwzResult;

		try {
			// 执行删除处理
			actionService.removeAction(actionId);
			processResult = CommonConsts.PROCESS_STATUS_OK;
		} catch (Exception e) {
			processResult = CommonConsts.PROCESS_STATUS_NG;
			log.error(e.getLocalizedMessage());
		}

		// 如果更新功能处理成功
		if (CommonConsts.PROCESS_STATUS_OK.equals(processResult)) {
			// 设置成功并返回功能一览画面信息
			dwzResult = new DwzAjaxResult("200", "删除成功", "actionM", "", "");
			// 如果删除功能处理不成功
		} else {
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败:系统错误", "actionM", "", "");
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
	@RequestMapping(value = "/removeActionRole")
	public String removeActionRole(@RequestParam("roleId") String roleId,
			Model model, HttpServletRequest request) {

		// 取得选择对象角色信息
		RoleModel roleModel = roleService.findRole(roleId);

		// 角色列表
		List<RoleModel> roleList = null;

		// 追加角色对象到session
		roleList = deleteActionRole(roleModel, request);
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
	private List<RoleModel> deleteActionRole(RoleModel roleModel,
			HttpServletRequest request) {

		// 角色列表
		List<RoleModel> roleList = null;
		// 追加角色
		List<RoleModel> roleDeleteList = null;

		//
		// 从会话中取得角色列表
		//
		roleList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_ACTION_ROLE);
		roleDeleteList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_ACTION_ROLE_DELETE);

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
		request.getSession().setAttribute(WebConst.SESS_ACTION_ROLE_DELETE,
				roleDeleteList);
		request.getSession().setAttribute(WebConst.SESS_ACTION_ROLE, roleList);

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
	@RequestMapping(value = "/removeActionPost")
	public String removeActionPost(@RequestParam("postIds") String postIds,
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
		postList = deleteActionPost(postModel, request);
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
	private List<CompanyPostModel> deleteActionPost(CompanyPostModel postModel,
			HttpServletRequest request) {

		// 职位列表
		List<CompanyPostModel> postList = null;
		// 追加职位
		List<CompanyPostModel> postDeleteList = null;

		//
		// 从会话中取得职位列表
		//
		postList = (List<CompanyPostModel>) request.getSession().getAttribute(
				WebConst.SESS_ACTION_POST);
		postDeleteList = (List<CompanyPostModel>) request.getSession()
				.getAttribute(WebConst.SESS_ACTION_POST_DELETE);

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
		request.getSession().setAttribute(WebConst.SESS_ACTION_POST_DELETE,
				postDeleteList);
		request.getSession().setAttribute(WebConst.SESS_ACTION_POST, postList);

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
	@RequestMapping(value = "/removeActionDept")
	public String removeActionDepartment(
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
		departmentList = deleteActionDepartment(departmentModel, request);
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
	private List<DepartmentModel> deleteActionDepartment(
			DepartmentModel departmentModel, HttpServletRequest request) {

		// 部门列表
		List<DepartmentModel> departmentList = null;
		// 追加部门
		List<DepartmentModel> departmentDeleteList = null;

		//
		// 从会话中取得部门列表
		//
		departmentList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_ACTION_DEPT);
		departmentDeleteList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_ACTION_DEPT_DELETE);

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
		request.getSession().setAttribute(WebConst.SESS_ACTION_DEPT_DELETE,
				departmentDeleteList);
		request.getSession().setAttribute(WebConst.SESS_ACTION_DEPT,
				departmentList);

		// 返回部门列表
		return departmentList;
	}

	*//**
	 * 功能唯一性检查
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param actionId
	 *            功能ID
	 * @return String 处理结果信息
	 * @throws 无
	 *//*
	@RequestMapping(value = "/checkActionUnique")
	@ResponseBody
	public String checkActionUnique(@RequestParam("actionId") String actionId) {

		// 返回处理结果信息
		return actionService.checkActionUnique(actionId);
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
	@RequestMapping(value = "/actionMain")
	public String actionMain(Model model) {

		String strHtml = getPageActionTree();

		// 将用户信息设置到画面显示对象
		model.addAttribute("actionTreeModel", strHtml);

		// 跳转至用户查看页面
		return "admin/action/mainFrame";
	}

	*//**
	 * 取得页面功能树形数据
	 * 
	 * @return String 页面功能树形数据
	 *//*
	private String getPageActionTree() {
		String strHtml = "";
		TreeUtil treeUtil = new TreeUtil();
		List<TreeModel> treeList = null;

		treeList = treeUtil.getPageActionTreeData();
		treeUtil.setHref("action/editAction.do");
		treeUtil.setRel("actionBox");

		treeUtil.setIsPutParameter(true);
		treeUtil.setTarget("ajax");
		strHtml = treeUtil.writeTree(treeList);

		return strHtml;
	}

	*//**
	 * 取得功能信息
	 * 
	 * @param pageId
	 *            页面ID
	 * @param actionId
	 *            功能ID
	 * @param ActionModel
	 *            功能信息
	 *//*
	public ActionModel getActionData(String pageId, String actionId) {
		// 取得功能信息
		ActionModel actionModel = actionService.findAction(pageId, actionId);

		// 返回功能
		return actionModel;

	}

	*//**
	 * 取得功能信息
	 * 
	 * @param pageId
	 *            页面ID
	 * @param ActionModel
	 *            功能信息
	 *//*
	public ActionModel getActionData(String pageId) {
		// 取得功能信息
		ActionModel actionModel = actionService.findPage(pageId);

		// 返回功能
		return actionModel;

	}

	*//**
	 * 清空功能职位权限Session中的信息
	 * 
	 * @param request
	 *//*
	@SuppressWarnings("unchecked")
	private List<ActionPostModel> getActionPostBySession(
			ActionModel actionModel, HttpServletRequest request) {

		// 功能职位权限列表
		List<ActionPostModel> actionPostList = null;
		// 追加对象功能职位权限列表
		List<CompanyPostModel> addPostList = null;
		// 删除对象功能职位权限列表
		List<CompanyPostModel> deletePostList = null;

		addPostList = (List<CompanyPostModel>) request.getSession()
				.getAttribute(WebConst.SESS_ACTION_POST_ADD);
		deletePostList = (List<CompanyPostModel>) request.getSession()
				.getAttribute(WebConst.SESS_ACTION_POST_DELETE);

		actionPostList = DataMergeCommon.mergeActionPost(addPostList,
				deletePostList, actionModel);

		// 追加对象功能职位权限
		request.getSession().setAttribute(WebConst.SESS_ACTION_POST_ADD, null);
		// 删除对象功能职位权限
		request.getSession().setAttribute(WebConst.SESS_ACTION_POST_DELETE,
				null);

		return actionPostList;
	}

	*//**
	 * 清空功能角色权限Session中的信息，并返回该对象
	 * 
	 * @param request
	 *//*
	@SuppressWarnings("unchecked")
	private List<ActionRoleModel> getActionRoleBySession(
			ActionModel actionModel, HttpServletRequest request) {

		// 功能角色权限列表
		List<ActionRoleModel> actionRoleList = null;
		// 追加对象功能角色权限列表
		List<RoleModel> addRoleList = null;
		// 删除对象功能角色权限列表
		List<RoleModel> deleteRoleList = null;

		addRoleList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_ACTION_ROLE_ADD);
		deleteRoleList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_ACTION_ROLE_DELETE);

		actionRoleList = DataMergeCommon.mergeActionRole(addRoleList,
				deleteRoleList, actionModel);

		// 追加对象功能角色权限
		request.getSession().setAttribute(WebConst.SESS_ACTION_ROLE_ADD, null);
		// 删除对象功能角色权限
		request.getSession().setAttribute(WebConst.SESS_ACTION_ROLE_DELETE,
				null);

		return actionRoleList;
	}

	*//**
	 * 清空功能角色权限Session中的信息，并返回该对象
	 * 
	 * @param request
	 *//*
	@SuppressWarnings("unchecked")
	private List<ActionDepartmentModel> getActionDeptBySession(
			ActionModel actionModel, HttpServletRequest request) {

		// 功能角色权限列表
		List<ActionDepartmentModel> actionDepartmentList = null;
		// 追加对象功能角色权限列表
		List<DepartmentModel> addDepartmentList = null;
		// 删除对象功能角色权限列表
		List<DepartmentModel> deleteDepartmentList = null;

		addDepartmentList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_ACTION_DEPT_ADD);
		deleteDepartmentList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_ACTION_DEPT_DELETE);

		actionDepartmentList = DataMergeCommon.mergeActionDepartment(
				addDepartmentList, deleteDepartmentList, actionModel);

		// 追加对象功能角色权限
		request.getSession().setAttribute(WebConst.SESS_ACTION_DEPT_ADD, null);
		// 删除对象功能角色权限
		request.getSession().setAttribute(WebConst.SESS_ACTION_DEPT_DELETE,
				null);

		return actionDepartmentList;
	}

	*//**
	 * 清空Session中功能权限的信息
	 * 
	 * @param request
	 *//*
	private void clearActionAuthSession(HttpServletRequest request) {
		// 功能信息
		request.getSession().setAttribute(WebConst.SESS_ACTION_INFO, null);
		// 功能角色权限
		request.getSession().setAttribute(WebConst.SESS_ACTION_ROLE, null);
		// 功能职位权限
		request.getSession().setAttribute(WebConst.SESS_ACTION_POST, null);
		// 功能部门权限
		request.getSession().setAttribute(WebConst.SESS_ACTION_DEPT, null);
	}

	*//**
	 * 清空Session中功能权限修改的信息
	 * 
	 * @param request
	 *//*
	private void clearModifyActionAuthSession(HttpServletRequest request) {

		// 追加对象功能职位权限
		request.getSession().setAttribute(WebConst.SESS_ACTION_POST_ADD, null);
		// 删除对象功能职位权限
		request.getSession().setAttribute(WebConst.SESS_ACTION_POST_DELETE,
				null);
		// 追加对象功能角色权限
		request.getSession().setAttribute(WebConst.SESS_ACTION_ROLE_ADD, null);
		// 删除对象功能角色权限
		request.getSession().setAttribute(WebConst.SESS_ACTION_ROLE_DELETE,
				null);
		// 追加对象功能角色权限
		request.getSession().setAttribute(WebConst.SESS_ACTION_DEPT_ADD, null);
		// 删除对象功能角色权限
		request.getSession().setAttribute(WebConst.SESS_ACTION_DEPT_DELETE,
				null);

	}

	*//**
	 * 设置部门信息到session中
	 * 
	 * @param actionModel
	 * @param request
	 *//*
	private void setActionInfoToSession(ActionModel actionModel,
			HttpServletRequest request) {

		HttpSession session = request.getSession();

		if (actionModel == null) {
			return;
		}
		// 功能职位权限列表
		List<CompanyPostModel> actionPostList = actionModel.getPostList();

		// 功能角色权限列表
		List<RoleModel> actionRoleList = actionModel.getRoleList();

		// 功能部门权限列表
		List<DepartmentModel> actionDeptList = actionModel.getDeptList();

		session.setAttribute(WebConst.SESS_ACTION_INFO, actionModel);
		session.setAttribute(WebConst.SESS_ACTION_POST, actionPostList);
		session.setAttribute(WebConst.SESS_ACTION_ROLE, actionRoleList);
		session.setAttribute(WebConst.SESS_ACTION_DEPT, actionDeptList);
	}

	*//**
	 * 设置功能权限对象（部门，角色，部门）
	 * 
	 * @param actionModel
	 *            功能对象
	 * @param request
	 *            请求对象
	 * @return 无
	 * @exception 无
	 * 
	 *//*
	private void setActionAuthInfo(ActionModel actionModel,
			HttpServletRequest request) {

		if (actionModel == null) {
			return;
		}
		// 功能职位权限列表
		List<ActionPostModel> actionPostList = null;

		// 功能角色权限列表
		List<ActionRoleModel> actionRoleList = null;

		// 功能部门权限列表
		List<ActionDepartmentModel> actionDeptList = null;

		// 取得功能职位权限列表
		actionPostList = getActionPostBySession(actionModel, request);
		// 取得功能角色权限列表
		actionRoleList = getActionRoleBySession(actionModel, request);
		// 取得功能部门权限列表
		actionDeptList = getActionDeptBySession(actionModel, request);

		actionModel.setActionPostList(actionPostList);
		actionModel.setActionRoleList(actionRoleList);
		actionModel.setActionDeptList(actionDeptList);
	}
}
*/