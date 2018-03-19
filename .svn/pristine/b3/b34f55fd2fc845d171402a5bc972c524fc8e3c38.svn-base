/** 
 * FileName UserController.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */
package com.bluemobi.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.DataMergeCommon;
import com.bluemobi.product.common.WebConst;
import com.bluemobi.product.model.CompanyPostModel;
import com.bluemobi.product.model.DepartmentModel;
import com.bluemobi.product.model.RoleModel;
import com.bluemobi.product.model.common.DwzPagerMySQL;
//import com.bluemobi.product.service.CompanyPostService;
//import com.bluemobi.product.service.DepartmentService;
import com.bluemobi.product.service.RoleService;
import com.bluemobi.product.service.TblUserService;
import com.bluemobi.product.utils.ObjectUtil;
import com.bluemobi.product.utils.UserUtil;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblUser;

/**
 * FileName AdminCommonController.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 后台管理公共控制器
 */

@Controller
@RequestMapping("/admin/common")
public class AdminCommonController {

	@Autowired
	private TblUserService tblUserService;

	/** 角色业务处理对象 */
	@Autowired
	private RoleService roleService;

	/** 部门业务处理对象 */
	//@Autowired
	//private DepartmentService departmentService;

	/** 部门业务处理对象 */
	//@Autowired
	//private CompanyPostService companyPostService;

	/**
	 * 用户一览处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param backRel
	 *            返回对象Rel
	 * @param processType
	 *            处理对象区分
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 * 备注：用户列表默认获取管理员用户 UserModel(userLevel 1-管理员 2-商家 3-个体商家)
	 */
	@RequestMapping(value = "/userSelectList")
	public String getUserList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblUser userModel,
			@RequestParam("backRel") String backRel,
			@RequestParam("processType") String processType, Model model,HttpServletRequest request) {

		// 用户列表
		List<TblUser> userList = null;
		// 用户总数
		long total = 0;

		//获取登陆用户
		TblUser loginUser=SessionMgr.getWebUser(request);
		if(loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS)
			userModel.setBusiCompanyId(loginUser.getBusiCompanyId());
		// 设置分页对象
		userModel.setPager(pager);
		// 取得用户列表
		userList = tblUserService.getSelectRoleUserList(userModel);
		// 用户总数
		total = tblUserService.getSelectRoleUserCount(userModel);
		pager.setTotal(total);

		// 将相关信息放到画面显示对象中
		model.addAttribute("userSelectList", userList);
		model.addAttribute("pager", pager);
		model.addAttribute("backRel", backRel);
		model.addAttribute("processType", processType);
		model.addAttribute("userModel", userModel);
		//判断是否为超级管理员
		boolean isMaintenance=false;
        if(!UserUtil.isSuperUser()){//不是超级管理员 去出所属权限菜单
        	 isMaintenance = false;
        }else{
        	 isMaintenance = true;
        }
        model.addAttribute("isSuperUser", isMaintenance);//是否是超级
		// 跳转至用户选择画面
		return "admin/common/userSelect";
	}

	/**
	 * 角色一览处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param backRel
	 *            返回对象Rel
	 * @param processType
	 *            处理对象区分
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/roleSelectList")
	public String getRoleList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute RoleModel roleModel,
			@RequestParam("backRel") String backRel,
			@RequestParam("processType") String processType, Model model,HttpServletRequest request) {

		// 角色列表
		List<RoleModel> roleList = null;

		// 设置分页对象
		roleModel.setPager(pager);
		//获取登陆用户
		TblUser loginUser=SessionMgr.getWebUser(request);
		if (loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS) {// 纯商家,
			roleModel.setCreateUser(loginUser.getUserId().toString());
		} 
		roleModel.setUserLevel(loginUser.getUserLevel()+"");
		// 取得角色列表
		roleList = roleService.searchRoleList(roleModel);
		// 角色总数
		long total = 0;
		// 角色总数
		total = roleService.searchRoleCount(roleModel);
		pager.setTotal(total);

		// 将相关信息放到画面显示对象中
		model.addAttribute("roleSelectList", roleList);
		model.addAttribute("pager", pager);
		model.addAttribute("backRel", backRel);
		model.addAttribute("processType", processType);
		model.addAttribute("roleModel", roleModel);

		// 跳转角色选择画面
		return "admin/common/roleSelect";
	}

	/**
	 * 职位一览处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param backRel
	 *            返回对象Rel
	 * @param processType
	 *            处理对象区分
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	/*@RequestMapping(value = "/postSelectList")
	public String getPostList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute CompanyPostModel companyPostModel,
			@RequestParam("backRel") String backRel,
			@RequestParam("processType") String processType, Model model) {

		// 职位列表
		List<CompanyPostModel> postList = null;
		
		// 设置分页对象
		companyPostModel.setPager(pager);
		// 职位总数
		long total = 0;
		// 取得职位总数
		total = companyPostService.searchPostCount(companyPostModel);
		pager.setTotal(total);

		// 取得职位列表
		postList = companyPostService.searchPostList(companyPostModel);

		// 将职位列表信息放到画面显示对象中
		model.addAttribute("postSelectList", postList);
		model.addAttribute("pager", pager);
		model.addAttribute("backRel", backRel);
		model.addAttribute("processType", processType);

		// 跳转至职位选择画面
		return "admin/common/postSelect";
	}*/

	/**
	 * 部门一览处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param backRel
	 *            返回对象Rel
	 * @param processType
	 *            处理对象区分
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	/*@RequestMapping(value = "/departmentSelectList")
	public String getDepartmentList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute DepartmentModel departmentModel,
			@RequestParam("backRel") String backRel,
			@RequestParam("processType") String processType, Model model) {

		// 部门列表
		List<DepartmentModel> departmentList = null;
		
		// 设置分页对象
		departmentModel.setPager(pager);
		// 部门总数
		long total = 0;
		// 取得部门总数
		total = departmentService.searchDepartmentCount(departmentModel);
		pager.setTotal(total);

		// 取得部门列表
		departmentList = departmentService.searchDepartmentList(departmentModel);

		// 将部门列表信息放到显示对象中
		model.addAttribute("departmentSelectList", departmentList);
		model.addAttribute("pager", pager);
		model.addAttribute("backRel", backRel);
		model.addAttribute("processType", processType);

		// 跳转至部门选择画面
		return "admin/common/departmentSelect";
	}*/

	/**
	 * 用户选择处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param userId
	 *            用户ID
	 * @param processType
	 *            处理对象区分
	 * @param model
	 *            画面显示对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/userSelect")
	public String userSelect(@ModelAttribute("tblUser") TblUser tblUser,
			@RequestParam("processType") String processType, Model model,
			HttpServletRequest request) {

		// 取得选择对象用户信息
		TblUser userModel = null;
		try {
			userModel = tblUserService.findUser(tblUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 用户列表
		List<TblUser> userList = null;

		// 追加用户对象到session
		userList = addUser(userModel, processType, request);

		// 将用户信息放到显示对象中
		model.addAttribute("userSelectList", userList);

		// 跳转至用户列表显示AJAX画面
		return "admin/common/userAddAjax";
	}

	/**
	 * 角色选择处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param roleId
	 *            角色ID
	 * @param processType
	 *            处理对象区分
	 * @param model
	 *            画面显示对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/roleSelect")
	public String roleSelect(@RequestParam("roleId") String roleId,
			@RequestParam("processType") String processType, Model model,
			HttpServletRequest request) {

		// 取得选择对象角色信息
		RoleModel roleModel = roleService.findRole(roleId);

		// 角色列表
		List<RoleModel> roleList = null;

		// 追加角色对象到session
		roleList = addRole(roleModel, processType, request);

		// 将角色信息放到画面显示对象中
		model.addAttribute("roleSelectList", roleList);

		// 跳转至角色列表显示AJAX画面
		return "admin/common/roleAddAjax";
	}

	/**
	 * 职位选择处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param postIds
	 *            公司ID和职位ID
	 * @param processType
	 *            处理对象区分
	 * @param model
	 *            画面显示对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	/*@RequestMapping(value = "/postSelect")
	public String postSelect(@RequestParam("postIds") String postIds,
			@RequestParam("processType") String processType, Model model,
			HttpServletRequest request) {
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

		// 取得职位信息
		CompanyPostModel postModel = companyPostService
				.findCompanyPost(searchModel);

		// 菜单职位权限列表
		List<CompanyPostModel> postList = null;

		// 追加菜单职位权限列表到session
		postList = addPost(postModel, processType, request);

		// 将用户信息放到会话中
		model.addAttribute("postSelectList", postList);

		// 跳转至职位列表显示AJAX画面
		return "admin/common/postAddAjax";
	}*/

	/**
	 * 部门选择处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param departmentIds
	 *            公司ID和职位ID
	 * @param processType
	 *            处理对象区分
	 * @param model
	 *            画面显示对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	/*@RequestMapping(value = "/departmentSelect")
	public String departmentSelect(
			@RequestParam("departmentIds") String departmentIds,
			@RequestParam("processType") String processType, Model model,
			HttpServletRequest request) {
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

		// 职位信息查询用对象
		DepartmentModel searchModel = new DepartmentModel();

		// 设置职位信息查询用对象信息
		searchModel.setCompanyId(companyId);
		searchModel.setDepartmentId(departmentId);

		// 取得选择对象角色信息
		DepartmentModel departmentModel = departmentService
				.findDepartment(searchModel);

		// 角色列表
		List<DepartmentModel> departmentList = null;

		// 追加角色对象到session
		departmentList = addDepartment(departmentModel, processType, request);

		// 将用户信息放到画面显示对象
		model.addAttribute("departmentSelectList", departmentList);

		// 跳转至部门列表显示AJAX画面
		return "admin/common/departmentAddAjax";
	}*/

	/**
	 * 追加并返回用户列表（将修改后的用户列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param userModel
	 *            用户对象
	 * @param processType
	 *            处理对象区分
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	private List<TblUser> addUser(TblUser userModel, String processType,
			HttpServletRequest request) {

		// 追加用户列表
		List<TblUser> userList = null;

		if (CommonConsts.USER_PROCESS_TYPE_DEPARTMENT.equals(processType)) {
			// 追加部门用户对象到session
			userList = addDeptUser(userModel, request);
		} else if (CommonConsts.USER_PROCESS_TYPE_ROLE.equals(processType)) {
			// 追加角色用户对象到session
			userList = addRoleUser(userModel, request);
		} else if (CommonConsts.USER_PROCESS_TYPE_POST.equals(processType)) {
			// 追加角色用户对象到session
			userList = addPostUser(userModel, request);
		}

		// 返回追加用户列表
		return userList;

	}

	/**
	 * 追加并返回部门用户列表（将修改后的用户列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param userModel
	 *            用户对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@SuppressWarnings("unchecked")
	private List<TblUser> addDeptUser(TblUser userModel,
			HttpServletRequest request) {

		// 部门用户列表
		List<TblUser> userList = null;
		// 追加对象部门用户
		List<TblUser> userAddList = null;
		// 删除用户
		List<TblUser> userDeleteList = null;

		userDeleteList = (List<TblUser>) request.getSession().getAttribute(
				WebConst.SESS_DEPT_DELETE_USERS);
		userList = (List<TblUser>) request.getSession().getAttribute(
				WebConst.SESS_DEPT_USERS);
		userAddList = (List<TblUser>) request.getSession().getAttribute(
				WebConst.SESS_DEPT_ADD_USERS);

		if (ObjectUtil.isEmpty(userList)) {
			userList = new ArrayList<TblUser>();
		}

		if (ObjectUtil.isEmpty(userAddList)) {
			userAddList = new ArrayList<TblUser>();
		}

		if (!UserUtil.containsUser(userList, userModel)) {
			userList.add(userModel);
			if (UserUtil.containsUser(userDeleteList, userModel)) {
				UserUtil.removeUser(userDeleteList, userModel);
			} else {
				userAddList.add(userModel);
			}
		}

		request.getSession().setAttribute(WebConst.SESS_DEPT_DELETE_USERS,
				userDeleteList);
		request.getSession().setAttribute(WebConst.SESS_DEPT_ADD_USERS,
				userAddList);
		request.getSession().setAttribute(WebConst.SESS_DEPT_USERS, userList);

		return userList;

	}

	/**
	 * 追加并返回角色用户列表（将修改后的用户列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param userModel
	 *            用户对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@SuppressWarnings("unchecked")
	private List<TblUser> addRoleUser(TblUser userModel,
			HttpServletRequest request) {

		// 角色用户列表
		List<TblUser> userList = null;
		// 追加角色部门用户
		List<TblUser> userAddList = null;
		// 删除用户
		List<TblUser> userDeleteList = null;

		userDeleteList = (List<TblUser>) request.getSession().getAttribute(
				WebConst.SESS_ROLE_DELETE_USERS);

		userList = (List<TblUser>) request.getSession().getAttribute(
				WebConst.SESS_ROLE_USERS);

		userAddList = (List<TblUser>) request.getSession().getAttribute(
				WebConst.SESS_ROLE_ADD_USERS);

		if (ObjectUtil.isEmpty(userList)) {
			userList = new ArrayList<TblUser>();
		}

		if (ObjectUtil.isEmpty(userAddList)) {
			userAddList = new ArrayList<TblUser>();
		}

		if (!UserUtil.containsUser(userList, userModel)) {
			userList.add(userModel);
			if (UserUtil.containsUser(userDeleteList, userModel)) {
				UserUtil.removeUser(userDeleteList, userModel);
			} else {
				userAddList.add(userModel);
			}
		}

		request.getSession().setAttribute(WebConst.SESS_ROLE_DELETE_USERS,
				userDeleteList);
		request.getSession().setAttribute(WebConst.SESS_ROLE_ADD_USERS,
				userAddList);
		request.getSession().setAttribute(WebConst.SESS_ROLE_USERS, userList);

		return userList;

	}

	/**
	 * 追加并返回职位用户列表（将修改后的用户列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param userModel
	 *            用户对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@SuppressWarnings("unchecked")
	private List<TblUser> addPostUser(TblUser userModel,
			HttpServletRequest request) {

		// 职位用户列表
		List<TblUser> userList = null;
		// 追加对象职位用户
		List<TblUser> userAddList = null;
		// 删除用户
		List<TblUser> userDeleteList = null;

		userDeleteList = (List<TblUser>) request.getSession().getAttribute(
				WebConst.SESS_POST_DELETE_USERS);
		userList = (List<TblUser>) request.getSession().getAttribute(
				WebConst.SESS_POST_USERS);
		userAddList = (List<TblUser>) request.getSession().getAttribute(
				WebConst.SESS_POST_ADD_USERS);

		if (ObjectUtil.isEmpty(userList)) {
			userList = new ArrayList<TblUser>();
		}

		if (ObjectUtil.isEmpty(userAddList)) {
			userAddList = new ArrayList<TblUser>();
		}

		if (!UserUtil.containsUser(userList, userModel)) {
			userList.add(userModel);
			if (UserUtil.containsUser(userDeleteList, userModel)) {
				UserUtil.removeUser(userDeleteList, userModel);
			} else {
				userAddList.add(userModel);
			}
		}

		request.getSession().setAttribute(WebConst.SESS_POST_DELETE_USERS,
				userDeleteList);
		request.getSession().setAttribute(WebConst.SESS_POST_ADD_USERS,
				userAddList);
		request.getSession().setAttribute(WebConst.SESS_POST_USERS, userList);

		return userList;

	}

	/**
	 * 追加并返回角色列表（将修改后的角色列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param roleModel
	 *            角色对象
	 * @param processType
	 *            处理对象区分
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	private List<RoleModel> addRole(RoleModel roleModel, String processType,
			HttpServletRequest request) {

		// 追加角色列表
		List<RoleModel> roleList = null;

		if (CommonConsts.ROLE_PROCESS_TYPE_MENU.equals(processType)) {
			// 追加菜单角色对象到session
			roleList = addMenuRole(roleModel, request);
		} else if (CommonConsts.ROLE_PROCESS_TYPE_ACTION.equals(processType)) {
			// 追加画面功能角色对象到session
			roleList = addActionRole(roleModel, request);
		} else if (CommonConsts.ROLE_PROCESS_TYPE_APP.equals(processType)) {
			// 追加接口角色对象到session
			roleList = addAppRole(roleModel, request);
		}

		// 返回追加角色列表
		return roleList;

	}

	/**
	 * 追加并返回角色列表（将修改后的角色列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param roleModel
	 *            角色对象
	 * @param request
	 *            画面请求对象
	 * @return List<RoleModel> 角色列表
	 * @throws 无
	 */
	@SuppressWarnings("unchecked")
	private List<RoleModel> addMenuRole(RoleModel roleModel,
			HttpServletRequest request) {

		// 角色列表
		List<RoleModel> roleList = null;
		// 追加角色
		List<RoleModel> roleAddList = null;
		// 删除角色
		List<RoleModel> roleDeleteList = null;
		// 从会话中取得角色列表
		roleDeleteList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_MENU_ROLE_DELETE);
		roleList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_MENU_ROLE);
		roleAddList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_MENU_ROLE_ADD);

		if (ObjectUtil.isEmpty(roleList)) {
			roleList = new ArrayList<RoleModel>();
		}

		if (ObjectUtil.isEmpty(roleAddList)) {
			roleAddList = new ArrayList<RoleModel>();
		}
		// 如果当前角色列表中不含有该角色，追加到列表中
		if (!DataMergeCommon.containsRole(roleList, roleModel)) {
			roleList.add(roleModel);
			if (DataMergeCommon.containsRole(roleDeleteList, roleModel)) {
				RoleModel.removeRole(roleDeleteList, roleModel);
			} else {
				roleAddList.add(roleModel);
			}
		}
		// 将追加角色列表放到会话中
		request.getSession().setAttribute(WebConst.SESS_MENU_ROLE_DELETE,
				roleDeleteList);
		request.getSession().setAttribute(WebConst.SESS_MENU_ROLE_ADD,
				roleAddList);
		request.getSession().setAttribute(WebConst.SESS_MENU_ROLE, roleList);

		// 返回角色列表
		return roleList;

	}

	/**
	 * 追加并返回角色列表（将修改后的角色列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param roleModel
	 *            角色对象
	 * @param request
	 *            画面请求对象
	 * @return List<RoleModel> 角色列表
	 * @throws 无
	 */
	@SuppressWarnings("unchecked")
	private List<RoleModel> addActionRole(RoleModel roleModel,
			HttpServletRequest request) {

		// 角色列表
		List<RoleModel> roleList = null;
		// 追加角色
		List<RoleModel> roleAddList = null;
		// 删除角色
		List<RoleModel> roleDeleteList = null;
		// 从会话中取得角色列表
		roleDeleteList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_ACTION_ROLE_DELETE);
		roleList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_ACTION_ROLE);
		roleAddList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_ACTION_ROLE_ADD);

		if (ObjectUtil.isEmpty(roleList)) {
			roleList = new ArrayList<RoleModel>();
		}

		if (ObjectUtil.isEmpty(roleAddList)) {
			roleAddList = new ArrayList<RoleModel>();
		}
		// 如果当前角色列表中不含有该角色，追加到列表中
		if (!DataMergeCommon.containsRole(roleList, roleModel)) {
			roleList.add(roleModel);
			if (DataMergeCommon.containsRole(roleDeleteList, roleModel)) {
				RoleModel.removeRole(roleDeleteList, roleModel);
			} else {
				roleAddList.add(roleModel);
			}
		}
		// 将追加角色列表放到会话中
		request.getSession().setAttribute(WebConst.SESS_ACTION_ROLE_DELETE,
				roleDeleteList);
		request.getSession().setAttribute(WebConst.SESS_ACTION_ROLE_ADD,
				roleAddList);
		request.getSession().setAttribute(WebConst.SESS_ACTION_ROLE, roleList);

		// 返回角色列表
		return roleList;

	}

	/**
	 * 追加并返回角色列表（将修改后的角色列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param roleModel
	 *            角色对象
	 * @param request
	 *            画面请求对象
	 * @return List<RoleModel> 角色列表
	 * @throws 无
	 */
	@SuppressWarnings("unchecked")
	private List<RoleModel> addAppRole(RoleModel roleModel,
			HttpServletRequest request) {

		// 角色列表
		List<RoleModel> roleList = null;
		// 追加角色
		List<RoleModel> roleAddList = null;
		// 删除角色
		List<RoleModel> roleDeleteList = null;
		// 从会话中取得角色列表
		roleDeleteList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_APP_ROLE_DELETE);
		roleList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_APP_ROLE);
		roleAddList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_APP_ROLE_ADD);

		if (ObjectUtil.isEmpty(roleList)) {
			roleList = new ArrayList<RoleModel>();
		}

		if (ObjectUtil.isEmpty(roleAddList)) {
			roleAddList = new ArrayList<RoleModel>();
		}
		// 如果当前角色列表中不含有该角色，追加到列表中
		if (!DataMergeCommon.containsRole(roleList, roleModel)) {
			roleList.add(roleModel);
			if (DataMergeCommon.containsRole(roleDeleteList, roleModel)) {
				RoleModel.removeRole(roleDeleteList, roleModel);
			} else {
				roleAddList.add(roleModel);
			}
		}

		// 将追加角色列表放到会话中
		request.getSession().setAttribute(WebConst.SESS_APP_ROLE_DELETE,
				roleDeleteList);
		request.getSession().setAttribute(WebConst.SESS_APP_ROLE_ADD,
				roleAddList);
		request.getSession().setAttribute(WebConst.SESS_APP_ROLE, roleList);

		// 返回角色列表
		return roleList;

	}

	/**
	 * 追加并返回职位列表（将修改后的职位列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param postModel
	 *            职位对象
	 * @param processType
	 *            处理对象区分
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@SuppressWarnings("unused")
	private List<CompanyPostModel> addPost(CompanyPostModel postModel,
			String processType, HttpServletRequest request) {

		// 追加职位列表
		List<CompanyPostModel> postList = null;

		if (CommonConsts.POST_PROCESS_TYPE_MENU.equals(processType)) {
			// 追加部门职位对象到session
			postList = addMenuPost(postModel, request);
		} else if (CommonConsts.POST_PROCESS_TYPE_ACTION.equals(processType)) {
			// 追加画面功能部门职位对象到session
			postList = addActionPost(postModel, request);
		} else if (CommonConsts.POST_PROCESS_TYPE_APP.equals(processType)) {
			// 追加接口部门职位对象到session
			postList = addAppPost(postModel, request);
		}

		// 返回追加职位列表
		return postList;

	}

	/**
	 * 追加并返回职位列表（将修改后的职位列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param postModel
	 *            职位对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@SuppressWarnings("unchecked")
	private List<CompanyPostModel> addMenuPost(CompanyPostModel postModel,
			HttpServletRequest request) {

		// 职位列表
		List<CompanyPostModel> postList = null;
		// 追加职位
		List<CompanyPostModel> postAddList = null;
		// 删除职位
		List<CompanyPostModel> postDeleteList = null;
		// 从会话中取得职位列表
		postDeleteList = (List<CompanyPostModel>) request.getSession()
				.getAttribute(WebConst.SESS_MENU_POST_DELETE);
		postList = (List<CompanyPostModel>) request.getSession().getAttribute(
				WebConst.SESS_MENU_POST);
		postAddList = (List<CompanyPostModel>) request.getSession()
				.getAttribute(WebConst.SESS_MENU_POST_ADD);

		if (ObjectUtil.isEmpty(postList)) {
			postList = new ArrayList<CompanyPostModel>();
		}

		if (ObjectUtil.isEmpty(postAddList)) {
			postAddList = new ArrayList<CompanyPostModel>();
		}
		// 如果当前职位列表中不含有该职位，追加到列表中
		if (!DataMergeCommon.containsPost(postList, postModel)) {
			postList.add(postModel);
			if (DataMergeCommon.containsPost(postDeleteList, postModel)) {
				CompanyPostModel.removePost(postDeleteList, postModel);
			} else {
				postAddList.add(postModel);
			}
		}
		// 将追加职位列表放到会话中
		request.getSession().setAttribute(WebConst.SESS_MENU_POST_DELETE,
				postDeleteList);
		request.getSession().setAttribute(WebConst.SESS_MENU_POST_ADD,
				postAddList);
		request.getSession().setAttribute(WebConst.SESS_MENU_POST, postList);

		// 返回职位列表
		return postList;

	}

	/**
	 * 追加并返回职位列表（将修改后的职位列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param postModel
	 *            职位对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@SuppressWarnings("unchecked")
	private List<CompanyPostModel> addActionPost(CompanyPostModel postModel,
			HttpServletRequest request) {

		// 职位列表
		List<CompanyPostModel> postList = null;
		// 追加职位
		List<CompanyPostModel> postAddList = null;
		// 删除职位
		List<CompanyPostModel> postDeleteList = null;
		// 从会话中取得职位列表
		postDeleteList = (List<CompanyPostModel>) request.getSession()
				.getAttribute(WebConst.SESS_MENU_POST_DELETE);
		postList = (List<CompanyPostModel>) request.getSession().getAttribute(
				WebConst.SESS_ACTION_POST);
		postAddList = (List<CompanyPostModel>) request.getSession()
				.getAttribute(WebConst.SESS_ACTION_POST_ADD);

		if (ObjectUtil.isEmpty(postList)) {
			postList = new ArrayList<CompanyPostModel>();
		}

		if (ObjectUtil.isEmpty(postAddList)) {
			postAddList = new ArrayList<CompanyPostModel>();
		}
		// 如果当前职位列表中不含有该职位，追加到列表中
		if (!DataMergeCommon.containsPost(postList, postModel)) {
			postList.add(postModel);
			if (DataMergeCommon.containsPost(postDeleteList, postModel)) {
				CompanyPostModel.removePost(postDeleteList, postModel);
			} else {
				postAddList.add(postModel);
			}
		}
		// 将追加职位列表放到会话中
		request.getSession().setAttribute(WebConst.SESS_ACTION_POST_DELETE,
				postDeleteList);
		request.getSession().setAttribute(WebConst.SESS_ACTION_POST_ADD,
				postAddList);
		request.getSession().setAttribute(WebConst.SESS_ACTION_POST, postList);

		// 返回职位列表
		return postList;

	}

	/**
	 * 追加并返回职位列表（将修改后的职位列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param postModel
	 *            职位对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@SuppressWarnings("unchecked")
	private List<CompanyPostModel> addAppPost(CompanyPostModel postModel,
			HttpServletRequest request) {

		// 职位列表
		List<CompanyPostModel> postList = null;
		// 追加职位
		List<CompanyPostModel> postAddList = null;
		// 删除职位
		List<CompanyPostModel> postDeleteList = null;

		// 从会话中取得职位列表
		postDeleteList = (List<CompanyPostModel>) request.getSession()
				.getAttribute(WebConst.SESS_APP_POST_DELETE);
		postList = (List<CompanyPostModel>) request.getSession().getAttribute(
				WebConst.SESS_APP_POST);
		postAddList = (List<CompanyPostModel>) request.getSession()
				.getAttribute(WebConst.SESS_APP_POST_ADD);

		if (ObjectUtil.isEmpty(postList)) {
			postList = new ArrayList<CompanyPostModel>();
		}

		if (ObjectUtil.isEmpty(postAddList)) {
			postAddList = new ArrayList<CompanyPostModel>();
		}

		// 如果当前职位列表中不含有该职位，追加到列表中
		if (!DataMergeCommon.containsPost(postList, postModel)) {
			postList.add(postModel);
			if (DataMergeCommon.containsPost(postDeleteList, postModel)) {
				CompanyPostModel.removePost(postDeleteList, postModel);
			} else {
				postAddList.add(postModel);
			}
		}

		// 将追加职位列表放到会话中
		request.getSession().setAttribute(WebConst.SESS_APP_POST_DELETE,
				postDeleteList);
		request.getSession().setAttribute(WebConst.SESS_APP_POST_ADD,
				postAddList);
		request.getSession().setAttribute(WebConst.SESS_APP_POST, postList);

		// 返回职位列表
		return postList;

	}

	/**
	 * 追加并返回部门列表（将修改后的部门列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param departmentModel
	 *            部门对象
	 * @param processType
	 *            处理对象区分
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@SuppressWarnings("unused")
	private List<DepartmentModel> addDepartment(
			DepartmentModel departmentModel, String processType,
			HttpServletRequest request) {

		// 追加部门列表
		List<DepartmentModel> departmentList = null;

		if (CommonConsts.DEPARTMENT_PROCESS_TYPE_MENU.equals(processType)) {
			// 追加菜单部门对象到session
			departmentList = addMenuDepartment(departmentModel, request);
		} else if (CommonConsts.DEPARTMENT_PROCESS_TYPE_ACTION
				.equals(processType)) {
			// 追加画面功能部门对象到session
			departmentList = addActionDepartment(departmentModel, request);
		} else if (CommonConsts.DEPARTMENT_PROCESS_TYPE_APP.equals(processType)) {
			// 追加接口部门对象到session
			departmentList = addAppDepartment(departmentModel, request);
		}

		// 返回追加部门列表
		return departmentList;

	}

	/**
	 * 追加并返回部门列表（将修改后的部门列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param departmentModel
	 *            部门对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@SuppressWarnings("unchecked")
	private List<DepartmentModel> addMenuDepartment(
			DepartmentModel departmentModel, HttpServletRequest request) {

		// 部门列表
		List<DepartmentModel> departmentList = null;
		// 追加部门
		List<DepartmentModel> departmentAddList = null;
		// 部门职位
		List<DepartmentModel> departmentDeleteList = null;

		// 从会话中取得部门列表
		departmentList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_MENU_DEPT);
		departmentAddList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_MENU_DEPT_ADD);
		departmentDeleteList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_MENU_DEPT_DELETE);

		if (ObjectUtil.isEmpty(departmentList)) {
			departmentList = new ArrayList<DepartmentModel>();
		}

		if (ObjectUtil.isEmpty(departmentAddList)) {
			departmentAddList = new ArrayList<DepartmentModel>();
		}

		// 如果当前部门列表中不含有该部门，追加到列表中
		if (!DataMergeCommon
				.containsDepartment(departmentList, departmentModel)) {
			departmentList.add(departmentModel);
			if (DataMergeCommon.containsDepartment(departmentDeleteList,
					departmentModel)) {
				DepartmentModel.removeDepartment(departmentDeleteList,
						departmentModel);
			} else {
				departmentAddList.add(departmentModel);
			}
		}

		// 将追加部门列表放到会话中
		request.getSession().setAttribute(WebConst.SESS_MENU_DEPT_DELETE,
				departmentDeleteList);
		request.getSession().setAttribute(WebConst.SESS_MENU_DEPT_ADD,
				departmentAddList);
		request.getSession().setAttribute(WebConst.SESS_MENU_DEPT,
				departmentList);

		// 返回部门列表
		return departmentList;

	}

	/**
	 * 追加并返回部门列表（将修改后的部门列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param departmentModel
	 *            部门对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@SuppressWarnings("unchecked")
	private List<DepartmentModel> addActionDepartment(
			DepartmentModel departmentModel, HttpServletRequest request) {

		// 部门列表
		List<DepartmentModel> departmentList = null;
		// 追加部门
		List<DepartmentModel> departmentAddList = null;
		// 部门职位
		List<DepartmentModel> departmentDeleteList = null;

		// 从会话中取得部门列表
		departmentList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_ACTION_DEPT);
		departmentAddList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_ACTION_DEPT_ADD);
		departmentDeleteList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_ACTION_DEPT_DELETE);

		if (ObjectUtil.isEmpty(departmentList)) {
			departmentList = new ArrayList<DepartmentModel>();
		}

		if (ObjectUtil.isEmpty(departmentAddList)) {
			departmentAddList = new ArrayList<DepartmentModel>();
		}

		// 如果当前部门列表中不含有该部门，追加到列表中
		if (!DataMergeCommon
				.containsDepartment(departmentList, departmentModel)) {
			departmentList.add(departmentModel);
			if (DataMergeCommon.containsDepartment(departmentDeleteList,
					departmentModel)) {
				DepartmentModel.removeDepartment(departmentDeleteList,
						departmentModel);
			} else {
				departmentAddList.add(departmentModel);
			}
		}

		// 将追加部门列表放到会话中
		request.getSession().setAttribute(WebConst.SESS_ACTION_DEPT_DELETE,
				departmentDeleteList);
		request.getSession().setAttribute(WebConst.SESS_ACTION_DEPT_ADD,
				departmentAddList);
		request.getSession().setAttribute(WebConst.SESS_ACTION_DEPT,
				departmentList);

		// 返回部门列表
		return departmentList;

	}

	/**
	 * 追加并返回部门列表（将修改后的部门列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param departmentModel
	 *            部门对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@SuppressWarnings("unchecked")
	private List<DepartmentModel> addAppDepartment(
			DepartmentModel departmentModel, HttpServletRequest request) {

		// 部门列表
		List<DepartmentModel> departmentList = null;
		// 追加部门
		List<DepartmentModel> departmentAddList = null;
		// 部门职位
		List<DepartmentModel> departmentDeleteList = null;

		// 从会话中取得部门列表
		departmentList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_APP_DEPT);
		departmentAddList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_APP_DEPT_ADD);
		departmentDeleteList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_APP_DEPT_DELETE);

		if (ObjectUtil.isEmpty(departmentList)) {
			departmentList = new ArrayList<DepartmentModel>();
		}

		if (ObjectUtil.isEmpty(departmentAddList)) {
			departmentAddList = new ArrayList<DepartmentModel>();
		}

		// 如果当前部门列表中不含有该部门，追加到列表中
		if (!DataMergeCommon
				.containsDepartment(departmentList, departmentModel)) {
			departmentList.add(departmentModel);
			if (DataMergeCommon.containsDepartment(departmentDeleteList,
					departmentModel)) {
				DepartmentModel.removeDepartment(departmentDeleteList,
						departmentModel);
			} else {
				departmentAddList.add(departmentModel);
			}
		}

		// 将追加部门列表放到会话中
		request.getSession().setAttribute(WebConst.SESS_APP_DEPT_DELETE,
				departmentDeleteList);
		request.getSession().setAttribute(WebConst.SESS_APP_DEPT_ADD,
				departmentAddList);
		request.getSession().setAttribute(WebConst.SESS_APP_DEPT,
				departmentList);

		// 返回部门列表
		return departmentList;

	}
}
