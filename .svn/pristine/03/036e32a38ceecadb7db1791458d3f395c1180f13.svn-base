/**
 * FileName:MenuController.java
 * Author: Administrator
 * Create: 2014年7月4日
 * Last Modified: 2014年7月4日
 * Version: V1.0 
 */
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
import com.bluemobi.product.model.CompanyPostModel;
import com.bluemobi.product.model.DepartmentModel;
import com.bluemobi.product.model.MenuDepartmentModel;
import com.bluemobi.product.model.MenuModel;
import com.bluemobi.product.model.MenuPostModel;
import com.bluemobi.product.model.MenuRoleModel;
import com.bluemobi.product.model.RoleModel;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.model.common.TreeModel;
//import com.bluemobi.product.service.CompanyPostService;
//import com.bluemobi.product.service.DepartmentService;
import com.bluemobi.product.service.MenuService;
import com.bluemobi.product.service.RoleService;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.MenuTreeUtil;
import com.bluemobi.product.utils.ObjectUtil;
import com.bluemobi.product.utils.UserUtil;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblUser;

/**
 * 菜单相关处理控制器
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月4日
 */

@Controller
@RequestMapping("/admin/menu2")
public class MenuController2 {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(MenuController2.class);

	/** 菜单业务处理对象 */
	@Autowired
	private MenuService menuService;

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
	 * 菜单一览处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/menuList")
	public String getMenuList(@ModelAttribute("pager") DwzPagerMySQL pager,
			Model model) {

		// 登录菜单信息
		List<MenuModel> menuList = null;

		// 取得菜单列表
		menuList = menuService.getMenuList();

		// 将菜单信息放到会话中
		model.addAttribute("menuList", menuList);
		model.addAttribute("pager", pager);

		// 跳转至管理员主页面
		return "admin/menu/listMenu";
	}

	/**
	 * 菜单添加初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/newMenu")
	public String newMenu(@RequestParam("menuId") String parentMenuId,
			Model model, HttpServletRequest request) {

		// 清空Session中菜单权限的信息
		clearMenuAuthSession(request);
		// 清空Session中菜单权限修改的信息
		clearModifyMenuAuthSession(request);

		String menuId = "";
		MenuModel menuModel = new MenuModel();

		menuId = ProcessInfoCommon.getRandomKey();

		menuModel.setMenuId(menuId);
		menuModel.setParentMenuId(parentMenuId);
		// 将菜单信息设置到画面显示对象
		model.addAttribute("menuModel", menuModel);
		//判断是否为超级管理员
		boolean isMaintenance=false;
        if(!UserUtil.isSuperUser()){//不是超级管理员 去出所属权限菜单
        	 isMaintenance = false;
        }else{
        	 isMaintenance = true;
        }
        model.addAttribute("isSuperUser", isMaintenance);//是否是超级
		// 跳转至菜单添加页面
		return "admin/menu/menu";
	}

	/**
	 * 菜单添加处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param menuModel
	 *            菜单输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/saveMenu", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String saveMenu(@ModelAttribute("menuModel") MenuModel menuModel,
			BindingResult result, HttpServletRequest request, Model model)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		// 菜单添加的结果信息
		String processResult;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "menuAddPage", "", "");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		// 设置更新者用户信息
		ProcessInfoCommon.setCreateUserInfo(menuModel);

		// 设置菜单权限列表
		setMenuAuthInfo(menuModel, request);

		// 执行菜单添加处理，并取得菜单添加的结果信息
		try {
			menuService.addMenu(menuModel);
			processResult = CommonConsts.PROCESS_STATUS_OK;
		} catch (Exception e) {
			processResult = CommonConsts.PROCESS_STATUS_NG;
			log.error(e.getLocalizedMessage());
		}

		// 如果更新菜单处理成功
		if (CommonConsts.PROCESS_STATUS_OK.equals(processResult)) {
			// 设置成功并返回菜单一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "menuM", "", "");
			// 如果更新菜单处理不成功
		} else {
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "menuM", "", "");
		}
		// 刷新菜单树
		refreshMenuTree(model);

		// 将菜单信息设置到画面显示对象
		model.addAttribute("menuModel", menuModel);

		// request.getSession().setAttribute(WebConst.SESS_ROLE_INFO,
		// menuModel);
		// request.getSession().setAttribute(WebConst.SESS_ROLE_USERS,
		// menuModel);

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 菜单拷贝初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param menuId
	 *            菜单ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/copyMenu", method = RequestMethod.GET)
	public String copyMenu(@RequestParam("menuId") String menuId, Model model,
			HttpServletRequest request) {

		// 清空Session中菜单权限的信息
		// clearMenuAuthSession(request);
		// 清空Session中菜单权限修改的信息
		clearModifyMenuAuthSession(request);

		// 取得拷贝对象菜单信息
		MenuModel menuModel = getMenuData(menuId,null);

		// 新菜单信息的设置
		menuModel.setMenuId(ProcessInfoCommon.getRandomKey());

		// 清空菜单信息
		ProcessInfoCommon.clearProcessUser(menuModel);

		// 将菜单信息设置到画面显示对象
		model.addAttribute("menuModel", menuModel);
		//判断是否为超级管理员
		boolean isMaintenance=false;
        if(!UserUtil.isSuperUser()){//不是超级管理员 去出所属权限菜单
        	 isMaintenance = false;
        }else{
        	 isMaintenance = true;
        }
        model.addAttribute("isSuperUser", isMaintenance);//是否是超级
		// 跳转至菜单编辑页面
		return "admin/menu/menu";
	}

	/**
	 * 菜单编辑初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param menuId
	 *            菜单ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/editMenu", method = RequestMethod.GET)
	public String editMenu(@RequestParam("modelKey") String modelKeys,
			Model model, HttpServletRequest request, HttpSession session) {

		// 清空Session中菜单权限的信息
		// clearMenuAuthSession(request);
		// 清空Session中菜单权限修改的信息
		clearModifyMenuAuthSession(request);

		// 菜单ID
		String[] menuIds = modelKeys.split(",");

		// 菜单ID
		String menuId = "";

		if (menuIds.length > 0) {
			menuId = menuIds[0];
		}

		//判断是否为超级管理员
		boolean isMaintenance=false;
        if(!UserUtil.isSuperUser()){//不是超级管理员 去出所属权限菜单
        	 isMaintenance = false;
        }else{
        	 isMaintenance = true;
        }
        model.addAttribute("isSuperUser", isMaintenance);//是否是超级
        
		// 对象菜单信息
		MenuModel menuModel = null;
		//获取登陆用户
		TblUser loginUser=SessionMgr.getWebUser(request);
		// 取得对象菜单信息
		menuModel = getMenuData(menuId,loginUser);

		// 将菜单信息设置到画面显示对象
		model.addAttribute("menuModel", menuModel);

		// 将菜单权限信息放到会话中
		setMenuInfoToSession(menuModel, request);

		// 跳转至菜单编辑页面
		return "admin/menu/menu";
	}

	/**
	 * 菜单编辑处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param menuModel
	 *            菜单输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/modifyMenu", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String modifyMenu(@ModelAttribute("menuModel") MenuModel menuModel,
			BindingResult result, Model model, HttpServletRequest request)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		// 更新处理结果
		String processResult = "";

		// 对象菜单信息
		MenuModel newModel = null;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "menuM", "", "");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		// 设置更新者用户信息
		ProcessInfoCommon.setUpdateUserInfo(menuModel, request);

		// 设置菜单权限列表
		setMenuAuthInfo(menuModel, request);

		// 执行菜单更新处理，并取得处理结果信息
		try {
			menuService.modifyMenu(menuModel);
			processResult = CommonConsts.PROCESS_STATUS_OK;
		} catch (Exception e) {
			System.out.print(e);
			processResult = CommonConsts.PROCESS_STATUS_NG;
			log.error(e.getLocalizedMessage());
		}

		// 如果更新菜单处理成功
		if (CommonConsts.PROCESS_STATUS_OK.equals(processResult)) {
			// 设置成功并返回菜单一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "menuM", "", "");
			// 如果更新菜单处理不成功
		} else {
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "menuM", "", "");
		}

		// 刷新画面菜单树
		refreshMenuTree(model);

		// 取得对象菜单信息
		newModel = getMenuData(menuModel.getMenuId(),null);

		// 将菜单信息设置到画面显示对象
		model.addAttribute("menuModel", newModel);

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 菜单删除处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param menuId
	 *            菜单ID
	 * @param model
	 *            画面显示对象
	 * @return String 处理结果信息
	 * @throws 无
	 */
	@RequestMapping(value = "/removeMenu")
	@ResponseBody
	public String removeMenu(@ModelAttribute("menuModel") MenuModel menuModel) {

		String menuId = menuModel.getMenuId();

		// 更新处理结果
		String processResult = "";
		// 处理结果信息
		DwzAjaxResult dwzResult;

		try {
			// 执行删除处理
			menuService.removeMenu(menuId);
			processResult = CommonConsts.PROCESS_STATUS_OK;
		} catch (Exception e) {
			processResult = CommonConsts.PROCESS_STATUS_NG;
			log.error(e.getLocalizedMessage());
		}

		// 如果更新菜单处理成功
		if (CommonConsts.PROCESS_STATUS_OK.equals(processResult)) {
			// 设置成功并返回菜单一览画面信息
			dwzResult = new DwzAjaxResult("200", "删除成功", "menuM", "", "");
			// 如果删除菜单处理不成功
		} else {
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败:系统错误", "menuM", "", "");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
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
	 */
	@RequestMapping(value = "/removeMenuRole")
	public String removeMenuRole(@RequestParam("roleId") String roleId,
			Model model, HttpServletRequest request) {

		// 取得选择对象角色信息
		RoleModel roleModel = roleService.findRole(roleId);

		// 角色列表
		List<RoleModel> roleList = null;

		// 追加角色对象到session
		roleList = deleteMenuRole(roleModel, request);
		//
		// 将角色信息放到画面显示对象中
		//
		model.addAttribute("roleSelectList", roleList);

		// // 跳转至角色列表显示AJAX画面
		return "admin/common/roleAddAjax";
	}

	/**
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
	 */
	@SuppressWarnings("unchecked")
	private List<RoleModel> deleteMenuRole(RoleModel roleModel,
			HttpServletRequest request) {

		// 角色列表
		List<RoleModel> roleList = null;
		// 追加角色
		List<RoleModel> roleDeleteList = null;

		//
		// 从会话中取得角色列表
		//
		roleList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_MENU_ROLE);
		roleDeleteList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_MENU_ROLE_DELETE);

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
		request.getSession().setAttribute(WebConst.SESS_MENU_ROLE_DELETE,
				roleDeleteList);
		request.getSession().setAttribute(WebConst.SESS_MENU_ROLE, roleList);

		// 返回角色列表
		return roleList;
	}

	/**
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
	 */
	/*@RequestMapping(value = "/removeMenuPost")
	public String removeMenuPost(@RequestParam("postIds") String postIds,
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
		postList = deleteMenuPost(postModel, request);
		//
		// 将职位信息放到画面显示对象中
		//
		model.addAttribute("postSelectList", postList);

		// // 跳转至职位列表显示AJAX画面
		return "admin/common/postAddAjax";
	}*/

	/**
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
	 */
	@SuppressWarnings("unchecked")
	private List<CompanyPostModel> deleteMenuPost(CompanyPostModel postModel,
			HttpServletRequest request) {

		// 职位列表
		List<CompanyPostModel> postList = null;
		// 追加职位
		List<CompanyPostModel> postDeleteList = null;

		//
		// 从会话中取得职位列表
		//
		postList = (List<CompanyPostModel>) request.getSession().getAttribute(
				WebConst.SESS_MENU_POST);
		postDeleteList = (List<CompanyPostModel>) request.getSession()
				.getAttribute(WebConst.SESS_MENU_POST_DELETE);

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
		request.getSession().setAttribute(WebConst.SESS_MENU_POST_DELETE,
				postDeleteList);
		request.getSession().setAttribute(WebConst.SESS_MENU_POST, postList);

		// 返回职位列表
		return postList;
	}

	/**
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
	 */
	/*@RequestMapping(value = "/removeMenuDept")
	public String removeMenuDepartment(
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
		departmentList = deleteMenuDepartment(departmentModel, request);
		//
		// 将部门信息放到画面显示对象中
		//
		model.addAttribute("departmentSelectList", departmentList);

		// // 跳转至部门列表显示AJAX画面
		return "admin/common/departmentAddAjax";
	}*/

	/**
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
	 */
	@SuppressWarnings("unchecked")
	private List<DepartmentModel> deleteMenuDepartment(
			DepartmentModel departmentModel, HttpServletRequest request) {

		// 部门列表
		List<DepartmentModel> departmentList = null;
		// 追加部门
		List<DepartmentModel> departmentDeleteList = null;

		//
		// 从会话中取得部门列表
		//
		departmentList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_MENU_DEPT);
		departmentDeleteList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_MENU_DEPT_DELETE);

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
		request.getSession().setAttribute(WebConst.SESS_MENU_DEPT_DELETE,
				departmentDeleteList);
		request.getSession().setAttribute(WebConst.SESS_MENU_DEPT,
				departmentList);

		// 返回部门列表
		return departmentList;
	}

	/**
	 * 菜单唯一性检查
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param menuId
	 *            菜单ID
	 * @return String 处理结果信息
	 * @throws 无
	 */
	@RequestMapping(value = "/checkMenuUnique")
	@ResponseBody
	public String checkMenuUnique(@RequestParam("menuId") String menuId) {

		// 返回处理结果信息
		return menuService.checkMenuUnique(menuId);
	}

	/**
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
	 */
	@RequestMapping(value = "/menuMain")
	public String menuMain(Model model, HttpServletRequest request) {
		/*
		 * MenuTreeUtil treeUtil = new MenuTreeUtil(); List<TreeModel> treeList
		 * = treeUtil.getMenuMenuData();
		 * treeUtil.setHref("menu/editMenuLink.do");
		 * treeUtil.setIsPutParameter(true); treeUtil.setTarget("ajax");
		 * treeUtil.setRel("menuBox");
		 */
		String strHtml = getMenuTree(false);

		//判断是否为超级管理员
		boolean isMaintenance=false;
        if(!UserUtil.isSuperUser()){//不是超级管理员 去出所属权限菜单
        	 isMaintenance = false;
        }else{
        	 isMaintenance = true;
        }
        model.addAttribute("isSuperUser", isMaintenance);//是否是超级
		// 将用户信息设置到画面显示对象
		model.addAttribute("menuTreeModel", strHtml);
		// 跳转至用户查看页面
		return "admin/menu/mainFrame";
	}

	/**
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
	 */
	@RequestMapping(value = "/menuMenuTree")
	public String menuMenuTree(Model model, HttpServletRequest request) {
		/*
		 * MenuTreeUtil treeUtil = new MenuTreeUtil(); List<TreeModel> treeList
		 * = treeUtil.getMenuTreeData(); treeUtil.setHref("menu/editMenu.do");
		 * treeUtil.setIsPutParameter(true); treeUtil.setTarget("ajax");
		 * treeUtil.setRel("jbsxBox");
		 */
		String strHtml = getMenuTree(true);

		// 将用户信息设置到画面显示对象
		model.addAttribute("menuTreeModel", strHtml);

		// 跳转至用户查看页面
		return "admin/menu/mainFrame";
	}

	/**
	 * 刷新菜单画面
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param model
	 *            画面显示对象
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/refreshMenuPage")
	public String refreshMenuPage(Model model, HttpServletRequest request) {

		// 菜单用户列表
		List<TblUser> userList = null;

		// 对象菜单信息
		MenuModel menuModel = null;

		menuModel = (MenuModel) request.getSession().getAttribute(
				WebConst.SESS_ROLE_INFO);
		userList = (List<TblUser>) request.getSession().getAttribute(
				WebConst.SESS_ROLE_USERS);

		// 将菜单信息设置到画面显示对象
		model.addAttribute("menuModel", menuModel);

		// 将用户信息放到会话中
		model.addAttribute("userList", userList);

		// 跳转至用户查看页面
		return "admin/menu/menu";

	}

	private String getMenuTree(boolean isMenu) {
		String strHtml = "";
		MenuTreeUtil treeUtil = new MenuTreeUtil();
		List<TreeModel> treeList = null;

		treeList = treeUtil.getMenuTreeData();
		treeUtil.setHref("menu/editMenu.do");
		treeUtil.setRel("menuBox");

		treeUtil.setIsPutParameter(true);
		treeUtil.setTarget("ajax");
		strHtml = treeUtil.writeTree(treeList);

		return strHtml;
	}

	public void refreshMenuTree(Model model) {
		String strHtml = getMenuTree(false);

		// 将用户信息设置到画面显示对象
		model.addAttribute("menuTreeModel", strHtml);

	}

	/**
	 * 取得菜单信息
	 * 
	 * @param menuId
	 *            菜单ID
	 * @param MenuModel
	 *            菜单信息
	 */
	public MenuModel getMenuData(String menuId,TblUser loginUser) {
		// 取得菜单信息
		MenuModel menuModel = menuService.findMenu(menuId,loginUser);

		// 返回菜单
		return menuModel;

	}

	/**
	 * 清空菜单职位权限Session中的信息
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	private List<MenuPostModel> getMenuPostBySession(MenuModel menuModel,
			HttpServletRequest request) {

		// 菜单职位权限列表
		List<MenuPostModel> menuPostList = null;
		// 追加对象菜单职位权限列表
		List<CompanyPostModel> addPostList = null;
		// 删除对象菜单职位权限列表
		List<CompanyPostModel> deletePostList = null;

		addPostList = (List<CompanyPostModel>) request.getSession()
				.getAttribute(WebConst.SESS_MENU_POST_ADD);
		deletePostList = (List<CompanyPostModel>) request.getSession()
				.getAttribute(WebConst.SESS_MENU_POST_DELETE);

		menuPostList = DataMergeCommon.mergeMenuPost(addPostList,
				deletePostList, menuModel);

		// 追加对象菜单职位权限
		request.getSession().setAttribute(WebConst.SESS_MENU_POST_ADD, null);
		// 删除对象菜单职位权限
		request.getSession().setAttribute(WebConst.SESS_MENU_POST_DELETE, null);

		return menuPostList;
	}

	/**
	 * 清空菜单角色权限Session中的信息，并返回该对象
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	private List<MenuRoleModel> getMenuRoleBySession(MenuModel menuModel,
			HttpServletRequest request) {

		// 菜单角色权限列表
		List<MenuRoleModel> menuRoleList = null;
		// 追加对象菜单角色权限列表
		List<RoleModel> addRoleList = null;
		// 删除对象菜单角色权限列表
		List<RoleModel> deleteRoleList = null;

		addRoleList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_MENU_ROLE_ADD);
		deleteRoleList = (List<RoleModel>) request.getSession().getAttribute(
				WebConst.SESS_MENU_ROLE_DELETE);

		menuRoleList = DataMergeCommon.mergeMenuRole(addRoleList,
				deleteRoleList, menuModel);

		// 追加对象菜单角色权限
		request.getSession().setAttribute(WebConst.SESS_MENU_ROLE_ADD, null);
		// 删除对象菜单角色权限
		request.getSession().setAttribute(WebConst.SESS_MENU_ROLE_DELETE, null);

		return menuRoleList;
	}

	/**
	 * 清空菜单角色权限Session中的信息，并返回该对象
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	private List<MenuDepartmentModel> getMenuDeptBySession(MenuModel menuModel,
			HttpServletRequest request) {

		// 菜单角色权限列表
		List<MenuDepartmentModel> menuDepartmentList = null;
		// 追加对象菜单角色权限列表
		List<DepartmentModel> addDepartmentList = null;
		// 删除对象菜单角色权限列表
		List<DepartmentModel> deleteDepartmentList = null;

		addDepartmentList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_MENU_DEPT_ADD);
		deleteDepartmentList = (List<DepartmentModel>) request.getSession()
				.getAttribute(WebConst.SESS_MENU_DEPT_DELETE);

		menuDepartmentList = DataMergeCommon.mergeMenuDepartment(
				addDepartmentList, deleteDepartmentList, menuModel);

		// 追加对象菜单角色权限
		request.getSession().setAttribute(WebConst.SESS_MENU_DEPT_ADD, null);
		// 删除对象菜单角色权限
		request.getSession().setAttribute(WebConst.SESS_MENU_DEPT_DELETE, null);

		return menuDepartmentList;
	}

	/**
	 * 清空Session中菜单权限的信息
	 * 
	 * @param request
	 */
	private void clearMenuAuthSession(HttpServletRequest request) {
		// 菜单信息
		request.getSession().setAttribute(WebConst.SESS_MENU_INFO, null);
		// 菜单角色权限
		request.getSession().setAttribute(WebConst.SESS_MENU_ROLE, null);
		// 菜单职位权限
		request.getSession().setAttribute(WebConst.SESS_MENU_POST, null);
		// 菜单部门权限
		request.getSession().setAttribute(WebConst.SESS_MENU_DEPT, null);
	}

	/**
	 * 清空Session中菜单权限修改的信息
	 * 
	 * @param request
	 */
	private void clearModifyMenuAuthSession(HttpServletRequest request) {

		// 追加对象菜单职位权限
		request.getSession().setAttribute(WebConst.SESS_MENU_POST_ADD, null);
		// 删除对象菜单职位权限
		request.getSession().setAttribute(WebConst.SESS_MENU_POST_DELETE, null);
		// 追加对象菜单角色权限
		request.getSession().setAttribute(WebConst.SESS_MENU_ROLE_ADD, null);
		// 删除对象菜单角色权限
		request.getSession().setAttribute(WebConst.SESS_MENU_ROLE_DELETE, null);
		// 追加对象菜单角色权限
		request.getSession().setAttribute(WebConst.SESS_MENU_DEPT_ADD, null);
		// 删除对象菜单角色权限
		request.getSession().setAttribute(WebConst.SESS_MENU_DEPT_DELETE, null);

	}

	/**
	 * 设置部门信息到session中
	 * 
	 * @param menuModel
	 * @param request
	 */
	private void setMenuInfoToSession(MenuModel menuModel,
			HttpServletRequest request) {

		HttpSession session = request.getSession();

		if (menuModel == null) {
			return;
		}
		// 菜单职位权限列表
		List<CompanyPostModel> menuPostList = menuModel.getPostList();

		// 菜单角色权限列表
		List<RoleModel> menuRoleList = menuModel.getRoleList();

		// 菜单部门权限列表
		List<DepartmentModel> menuDeptList = menuModel.getDeptList();

		session.setAttribute(WebConst.SESS_MENU_INFO, menuModel);
		session.setAttribute(WebConst.SESS_MENU_POST, menuPostList);
		session.setAttribute(WebConst.SESS_MENU_ROLE, menuRoleList);
		session.setAttribute(WebConst.SESS_MENU_DEPT, menuDeptList);
	}

	/**
	 * 设置菜单权限对象（部门，角色，部门）
	 * 
	 * @param menuModel
	 *            菜单对象
	 * @param request
	 *            请求对象
	 * @return 无
	 * @exception 无
	 * 
	 */
	private void setMenuAuthInfo(MenuModel menuModel, HttpServletRequest request) {

		if (menuModel == null) {
			return;
		}
		// 菜单职位权限列表
		List<MenuPostModel> menuPostList = null;

		// 菜单角色权限列表
		List<MenuRoleModel> menuRoleList = null;

		// 菜单部门权限列表
		List<MenuDepartmentModel> menuDeptList = null;

		// 取得菜单职位权限列表
		menuPostList = getMenuPostBySession(menuModel, request);
		// 取得菜单角色权限列表
		menuRoleList = getMenuRoleBySession(menuModel, request);
		// 取得菜单部门权限列表
		menuDeptList = getMenuDeptBySession(menuModel, request);

		menuModel.setMenuPostList(menuPostList);
		menuModel.setMenuRoleList(menuRoleList);
		menuModel.setMenuDeptList(menuDeptList);
	}
}
