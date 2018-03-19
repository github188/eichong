/**
 * FileName:RoleController.java
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
import com.bluemobi.product.model.RoleModel;
import com.bluemobi.product.model.UserRoleModel;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.model.common.TreeModel;
import com.bluemobi.product.service.RoleService;
import com.bluemobi.product.service.TblUserService;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.ObjectUtil;
import com.bluemobi.product.utils.TreeUtil;
import com.bluemobi.product.utils.UserUtil;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblUser;

/**
 * 角色相关处理控制器
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月4日
 */

@Controller
@RequestMapping("/admin/role2")
public class RoleController2 {

	/** 角色业务处理对象 */
	@Autowired
	private RoleService roleService;

	@Autowired
	private TblUserService tblUserService;

	/**
	 * 角色登录处理
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
	@RequestMapping(value = "/roleList")
	public String getRoleList(@ModelAttribute("pager") DwzPagerMySQL pager,
			Model model) {

		// 登录角色信息
		List<RoleModel> roleList = null;

		// 取得角色列表
		roleList = roleService.getRoleList();

		// 将角色信息放到会话中
		model.addAttribute("roleList", roleList);
		model.addAttribute("pager", pager);

		// 跳转至管理员主页面
		return "admin/role/listRole";
	}

	/**
	 * 角色添加初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/newRole")
	public String newRole(@RequestParam("roleId") String parentRoleId,
			Model model, HttpServletRequest request) {

		// 清空角色用户Session中的信息
		clearRoleUserSession(request);

		String roleId = "";
		RoleModel roleModel = new RoleModel();

		roleId = ProcessInfoCommon.getRandomKey();

		roleModel.setRoleId(roleId);
		roleModel.setParentRoleId(parentRoleId);
		// 将角色信息设置到画面显示对象
		model.addAttribute("roleModel", roleModel);
		// 跳转至角色添加页面
		return "admin/role/role";
	}

	/**
	 * 角色添加处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param roleModel
	 *            角色输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/saveRole", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String saveRole(@ModelAttribute("roleModel") RoleModel roleModel,
			BindingResult result, HttpServletRequest request, Model model,HttpSession session)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		// 角色添加的结果信息
		String processResult;

		// 角色用户列表
		List<UserRoleModel> userRoleList = null;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "roleAddPage", "", "");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		// 设置更新者用户信息
		ProcessInfoCommon.setCreateUserInfo(roleModel, request);

		// 取得角色用户列表
		userRoleList = getRoleUserBySession(roleModel, request);

		// 执行角色添加处理，并取得角色添加的结果信息
		processResult = roleService.addRole(roleModel, userRoleList);

		// 如果更新角色处理成功
		if (CommonConsts.PROCESS_STATUS_OK.equals(processResult)) {
			// 设置成功并返回角色一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "roleM", "", "");
			// 如果更新角色处理不成功
		} else {
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "roleM", "", "");
		}
		//获取登陆用户
		TblUser loginUser=SessionMgr.getWebUser(request);
		// 刷新角色树
		refreshRoleTree(model,loginUser);

		// 将角色信息设置到画面显示对象
		model.addAttribute("roleModel", roleModel);

		// request.getSession().setAttribute(WebConst.SESS_ROLE_INFO,
		// roleModel);
		// request.getSession().setAttribute(WebConst.SESS_ROLE_USERS,
		// roleModel);

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 角色拷贝初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param roleId
	 *            角色ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/copyRole", method = RequestMethod.GET)
	public String copyRole(@RequestParam("roleId") String roleId, Model model,
			HttpServletRequest request) {

		// 清空角色用户Session中的信息
		clearModifyRoleUserSession(request);

		// 取得拷贝对象角色信息
		RoleModel roleModel = getRoleData(roleId);

		// 新角色信息的设置
		roleModel.setRoleId(ProcessInfoCommon.getRandomKey());

		// 清空角色信息
		ProcessInfoCommon.clearProcessUser(roleModel);

		// 将角色信息设置到画面显示对象
		model.addAttribute("roleModel", roleModel);

		// 跳转至角色编辑页面
		return "admin/role/role";
	}

	/**
	 * 角色编辑初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param roleId
	 *            角色ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/editRole", method = RequestMethod.GET)
	public String editRole(@RequestParam("modelKey") String modelKey,
			Model model, HttpServletRequest request,HttpSession session) {

		// 清空角色用户Session中的信息
		clearRoleUserSession(request);

		// 角色ID
		String roleId = "";
		// 角色用户列表
		List<TblUser> userList = null;

		// 对象角色信息
		RoleModel roleModel = null;

		// 角色ID
		roleId = modelKey;

		// 取得对象角色信息
		roleModel = getRoleData(roleId);

		// 角色用户列表
		userList = getRoleUserList(roleId);

		// 将角色信息设置到画面显示对象
		model.addAttribute("roleModel", roleModel);

		// 将用户信息放到会话中
		model.addAttribute("userList", userList);
		//获取登陆用户
		TblUser loginUser=SessionMgr.getWebUser(request);
		model.addAttribute("loginUser", loginUser);
		request.getSession().setAttribute(WebConst.SESS_ROLE_INFO, roleModel);
		request.getSession().setAttribute(WebConst.SESS_ROLE_USERS, userList);

		// 跳转至角色编辑页面
		return "admin/role/role";
	}

	/**
	 * 角色编辑处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param roleModel
	 *            角色输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/modifyRole", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String modifyRole(@ModelAttribute("roleModel") RoleModel roleModel,
			BindingResult result, Model model, HttpServletRequest request,HttpSession session)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		// 更新处理结果
		String processResult = "";
		// 角色用户列表
		List<UserRoleModel> userRoleList = null;

		// 对象角色信息
		RoleModel newModel = null;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "roleM", "", "");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		// 设置更新者用户信息
		ProcessInfoCommon.setUpdateUserInfo(roleModel, request);

		// 取得角色用户列表
		userRoleList = getRoleUserBySession(roleModel, request);

		// 执行角色更新处理，并取得处理结果信息
		processResult = roleService.modifyRole(roleModel, userRoleList);

		// 如果更新角色处理成功
		if (CommonConsts.PROCESS_STATUS_OK.equals(processResult)) {
			// 设置成功并返回角色一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "roleM", "", "");
			// 如果更新角色处理不成功
		} else {
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "roleM", "", "");
		}

		//获取登陆用户
		TblUser loginUser=SessionMgr.getWebUser(request);
		// 刷新画面角色树
		refreshRoleTree(model,loginUser);

		// 取得对象角色信息
		newModel = getRoleData(roleModel.getRoleId());

		// 将角色信息设置到画面显示对象
		model.addAttribute("roleModel", newModel);

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
	 * @return String 处理结果信息
	 * @throws 无
	 */
	@RequestMapping(value = "/removeRole")
	@ResponseBody
	public String removeRole(@ModelAttribute("roleModel") RoleModel roleModel) {
		String roleId = roleModel.getRoleId();

		// 执行删除处理
		roleService.removeRole(roleId);

		// 设置处理结果信息
		DwzAjaxResult result = new DwzAjaxResult("200", "删除成功", "roleM", "", "");

		// 跳转至角色编辑页面
		// return "admin/role/mainFrame";

		// 返回处理结果信息
		return new JsonObject(result).toString();
	}

	/**
	 * 角色用户删除处理
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
	 */
	@RequestMapping(value = "/removeRoleUser")
	public String removeRoleUser(@ModelAttribute("tblUser") TblUser tblUser,
			Model model, HttpServletRequest request) {

		// 用户列表
		List<TblUser> userList = null;

		// 取得选择对象用户信息
		TblUser TblUser = null;
		try {
			TblUser = tblUserService.findUser(tblUser);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 追加用户对象到session
		userList = deleteRoleUser(TblUser, request);
		//
		// 将用户信息放到画面显示对象中
		//
		model.addAttribute("userSelectList", userList);

		// // 跳转至用户列表显示AJAX画面
		return "admin/common/userAddAjax";
	}

	/**
	 * 删除并返回用户列表（将修改后的用户列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param TblUser
	 *            用户对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@SuppressWarnings("unchecked")
	private List<TblUser> deleteRoleUser(TblUser TblUser,
			HttpServletRequest request) {

		// 用户列表
		List<TblUser> userList = null;
		// 追加用户
		List<TblUser> userAddList = null;
		// 删除用户
		List<TblUser> userDeleteList = null;

		//
		// 从会话中取得用户列表
		//
		userList = (List<TblUser>) request.getSession().getAttribute(
				WebConst.SESS_ROLE_USERS);
		userAddList = (List<TblUser>) request.getSession().getAttribute(
				WebConst.SESS_ROLE_ADD_USERS);
		userDeleteList = (List<TblUser>) request.getSession().getAttribute(
				WebConst.SESS_ROLE_DELETE_USERS);

		if (ObjectUtil.isEmpty(userList)) {
			userList = new ArrayList<TblUser>();
		}

		if (ObjectUtil.isEmpty(userDeleteList)) {
			userDeleteList = new ArrayList<TblUser>();
		}

		//
		// 如果当前用户列表中含有该用户，从列表中删除
		//
		if (UserUtil.containsUser(userList, TblUser)) {
			UserUtil.removeUser(userList, TblUser);
			if (UserUtil.containsUser(userAddList, TblUser)) {
				UserUtil.removeUser(userAddList, TblUser);
			} else {
				userDeleteList.add(TblUser);
			}
		}

		//
		// 将用户列表放到会话中
		//
		request.getSession().setAttribute(WebConst.SESS_ROLE_ADD_USERS,
				userAddList);
		request.getSession().setAttribute(WebConst.SESS_ROLE_DELETE_USERS,
				userDeleteList);
		request.getSession().setAttribute(WebConst.SESS_ROLE_USERS, userList);

		// 返回用户列表
		return userList;
	}

	/**
	 * 角色唯一性检查
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param roleId
	 *            角色ID
	 * @return String 处理结果信息
	 * @throws 无
	 */
	@RequestMapping(value = "/checkRoleUnique")
	@ResponseBody
	public String checkRoleUnique(@RequestParam("roleId") String roleId) {

		// 返回处理结果信息
		return roleService.checkRoleUnique(roleId);
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
	@RequestMapping(value = "/roleMain")
	public String roleMain(Model model, HttpServletRequest request,HttpSession session) {

		//获取登陆用户
		TblUser loginUser=SessionMgr.getWebUser(request);
		String strHtml = getRoleTree(false,loginUser);

		// 将用户信息设置到画面显示对象
		model.addAttribute("roleTreeModel", strHtml);

		//判断是否为超级管理员
		boolean isMaintenance=false;
        if(!UserUtil.isSuperUser()){//不是超级管理员 去出所属权限菜单
        	 isMaintenance = false;
        }else{
        	 isMaintenance = true;
        }
        model.addAttribute("isSuperUser", isMaintenance);//是否是超级
        
		// 跳转至用户查看页面
		return "admin/role/mainFrame";
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
	@RequestMapping(value = "/roleRoleTree")
	public String roleRoleTree(Model model, HttpServletRequest request,HttpSession session) {
		/*
		 * TreeUtil treeUtil = new TreeUtil(); List<TreeModel> treeList =
		 * treeUtil.getRoleTreeData(); treeUtil.setHref("role/editRole.do");
		 * treeUtil.setIsPutParameter(true); treeUtil.setTarget("ajax");
		 * treeUtil.setRel("jbsxBox");
		 */
		//获取登陆用户
		TblUser loginUser=SessionMgr.getWebUser(request);
		String strHtml = getRoleTree(true,loginUser);

		// 将用户信息设置到画面显示对象
		model.addAttribute("roleTreeModel", strHtml);

		// 跳转至用户查看页面
		return "admin/role/mainFrame";
	}

	/**
	 * 刷新角色画面
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
	@RequestMapping(value = "/refreshRolePage")
	public String refreshRolePage(Model model, HttpServletRequest request) {

		// 角色用户列表
		List<TblUser> userList = null;

		// 对象角色信息
		RoleModel roleModel = null;

		roleModel = (RoleModel) request.getSession().getAttribute(
				WebConst.SESS_ROLE_INFO);
		userList = (List<TblUser>) request.getSession().getAttribute(
				WebConst.SESS_ROLE_USERS);

		// 将角色信息设置到画面显示对象
		model.addAttribute("roleModel", roleModel);

		// 将用户信息放到会话中
		model.addAttribute("userList", userList);

		// 跳转至用户查看页面
		return "admin/role/role";

	}

	private String getRoleTree(boolean isRole,TblUser loginUser) {
		String strHtml = "";
		TreeUtil treeUtil = new TreeUtil();
		List<TreeModel> treeList = null;

		treeList = treeUtil.getRoleTreeData(loginUser);
		treeUtil.setHref("role/editRole.do");
		treeUtil.setRel("roleBox");

		treeUtil.setIsPutParameter(true);
		treeUtil.setTarget("ajax");
		strHtml = treeUtil.writeTree(treeList);

		return strHtml;
	}

	public void refreshRoleTree(Model model,TblUser loginUser) {
		String strHtml = getRoleTree(false,loginUser);

		// 将用户信息设置到画面显示对象
		model.addAttribute("roleTreeModel", strHtml);

	}

	/**
	 * 取得角色信息
	 * 
	 * @param roleId
	 *            角色ID
	 * @param RoleModel
	 *            角色信息
	 */
	public RoleModel getRoleData(String roleId) {
		// 取得角色信息
		RoleModel roleModel = roleService.findRole(roleId);

		// 返回角色
		return roleModel;

	}

	/**
	 * 取得角色用户列表
	 * 
	 * @param roleId
	 *            角色ID
	 * @param List
	 *            <TblUser> 角色用户列表
	 */
	private List<TblUser> getRoleUserList(String roleId) {

		// 角色用户列表
		List<TblUser> userList = null;

		// 取得角色用户列表
		userList = tblUserService.getRoleUserList(roleId);

		// 返回角色用户列表
		return userList;

	}

	/**
	 * 清空角色用户Session中的信息
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	private List<UserRoleModel> getRoleUserBySession(RoleModel roleModel,
			HttpServletRequest request) {

		// 角色用户列表
		List<UserRoleModel> userRoleList = null;
		// 追加对象角色用户列表
		List<TblUser> addUserList = null;
		// 删除对象角色用户列表
		List<TblUser> deleteUserList = null;

		addUserList = (List<TblUser>) request.getSession().getAttribute(
				WebConst.SESS_ROLE_ADD_USERS);
		deleteUserList = (List<TblUser>) request.getSession().getAttribute(
				WebConst.SESS_ROLE_DELETE_USERS);

		userRoleList = UserUtil.mergeProcessUser(addUserList, deleteUserList,
				roleModel);

		// 角色用户
		// request.getSession().setAttribute(WebConst.SESS_ROLE_USERS, null);
		// 追加对象角色用户
		request.getSession().setAttribute(WebConst.SESS_ROLE_ADD_USERS, null);
		// 删除对象角色用户
		request.getSession()
				.setAttribute(WebConst.SESS_ROLE_DELETE_USERS, null);

		return userRoleList;
	}

	/**
	 * 清空角色用户Session中的信息
	 * 
	 * @param request
	 */
	private void clearRoleUserSession(HttpServletRequest request) {
		// 角色用户
		request.getSession().setAttribute(WebConst.SESS_ROLE_USERS, null);
	}

	/**
	 * 清空角色用户Session中的信息
	 * 
	 * @param request
	 */
	private void clearModifyRoleUserSession(HttpServletRequest request) {
		// 追加对象角色用户
		request.getSession().setAttribute(WebConst.SESS_ROLE_ADD_USERS, null);
		// 删除对象角色用户
		request.getSession()
				.setAttribute(WebConst.SESS_ROLE_DELETE_USERS, null);
	}
}
