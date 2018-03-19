package com.pub.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.BaseSuccess;
import com.base.common.ProcessInfoCommon;
import com.base.common.SessionMgr;
import com.base.common.WanmaConstants;
import com.base.util.MD5Util;
import com.pub.model.Pager;
import com.pub.model.RoleModel;
import com.pub.model.TblUser;
import com.pub.service.RoleService;
import com.pub.service.TblUserService;
 
/**
 * 用户控制器
 */

@Controller
@RequestMapping("/admin/user")
public class UserController {

	// 日志输出对象
	private static Logger log = Logger.getLogger(UserController.class);

	/** 用户业务处理对象 */
	@Autowired
	private TblUserService tblUserService;
	@Autowired
	private RoleService roleService;

	/**
	 * 取得用户列表处理
	 * 
	 * @param pager
	 *            分页显示相关信息
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/userList")
	public String getUserList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblUser tblUser, Model model,
			HttpServletRequest request) {
		if (tblUser == null) {
			tblUser = new TblUser();
			tblUser.setUserLevel(1);
		}
		// 登录用户信息
		List<TblUser> userList = null;
		// 用户总数
		long total = 0;
		// 取得用户列表
		try {
			// 用户总数
			total = tblUserService.getUserCount(tblUser);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblUser.setPager(pager);
			userList = tblUserService.getUserList(tblUser);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		pager.setTotal(total);
		// 将用户信息放到会话中
		model.addAttribute("tblUser", tblUser);
		model.addAttribute("userList", userList);
		model.addAttribute("pager", pager);
		// 跳转至管理员主页面
		return "admin/user/listUser";
	}

	/**
	 * 用户添加初始化处理
	 * 
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/newUser")
	public String newUser(Model model, HttpServletRequest request) {
		TblUser user = new TblUser();
		user.setUserLevel(WanmaConstants.USER_LEVEL_ADMIN);
		List<RoleModel> roleList = roleService
				.getParentRoleListByCurrentUser(user);
		model.addAttribute("roleList", roleList);
		// 跳转至用户添加页面
		return "admin/user/newUser";
	}

	/**
	 * 用户添加处理
	 * 
	 * @param TblUser
	 *            用户输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/saveUser", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String saveUser(@ModelAttribute("TblUser") TblUser tblUser,
			BindingResult result, HttpServletRequest request)
			throws IOException {
		BaseResult baseResult = new BaseSuccess();
		if (result.hasErrors()) {
			return new BaseFail(5000).toString();
		}
		try {
			// 取得当前用户级别
			Integer userLevel = SessionMgr.getWebUser(request).getUserLevel();
			if (userLevel != null && userLevel <= 2) {
				userLevel = WanmaConstants.USER_LEVEL_ADMIN;
			}
			// 设置用户类型
			tblUser.setUserLevel(userLevel);
			// 执行用户添加处理，并取得添加成功的用户详细信息
			tblUser.setUserPassword(MD5Util.Md5(tblUser.getUserPassword()));
			tblUser.setUserStatus(WanmaConstants.USER_STATUS_NORMAL);
			tblUserService.insertUser(tblUser);
			String roleIds = "";
			String roleIdArr[] = request.getParameterValues("roleId");
			for (String roleId : roleIdArr) {
				roleIds += roleId + ",";
			}
			roleIds = StringUtils.removeEnd(roleIds, ",");
			// 更新用户角色关系
			roleService.updateUserRoleRelation(tblUser.getUserId(), roleIds);
		} catch (Exception e) {
			log.error(this.getClass() + ".saveUser() error:"
					+ e.getLocalizedMessage());
			baseResult.setStatus(5001);
		}
		return baseResult.toString();
	}

	/**
	 * 用户编辑初始化处理
	 * 
	 * @param userId
	 *            用户ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public String editUser(@ModelAttribute("tblUser") TblUser tblUser,
			Model model, HttpServletRequest request) {
		tblUser.setUserLevel(1);
		// 取得编辑对象用户信息
		TblUser user = null;
		try {
			user = tblUserService.findUser(tblUser);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		// 将用户信息设置到画面显示对象
		model.addAttribute("user", user);
		List<RoleModel> roleList = roleService
				.getParentRoleListByCurrentUser(user);
		model.addAttribute("roleList", roleList);
		List<RoleModel> selectRoleList = roleService.getRoleListByUserId(user
				.getUserId());
		model.addAttribute("selectRoleList", selectRoleList);
		// 跳转至用户编辑页面
		return "admin/user/editUser";
	}

	/**
	 * 用户编辑初始化处理
	 * 
	 * @param userId
	 *            用户ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/editSelf", method = RequestMethod.GET)
	public String editSelf(Model model, HttpServletRequest request) {
		TblUser user = SessionMgr.getWebUser(request);

		// 将用户信息设置到画面显示对象
		model.addAttribute("user", user);
		List<RoleModel> roleList = roleService
				.getParentRoleListByCurrentUser(user);
		model.addAttribute("roleList", roleList);
		List<RoleModel> selectRoleList = roleService.getRoleListByUserId(user
				.getUserId());
		model.addAttribute("selectRoleList", selectRoleList);

		// 跳转至用户编辑页面
		return "admin/user/editUser";
	}

	/**
	 * 用户查看初始化处理
	 * 
	 * @param userId
	 *            用户ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/viewUser", method = RequestMethod.GET)
	public String viewUser(@ModelAttribute("tblUser") TblUser tblUser,
			Model model) {
		// 取得查看对象用户信息
		TblUser user = null;
		try {
			user = tblUserService.findUser(tblUser);
		} catch (Exception e) {
			log.error(this.getClass() + ".viewUser() error:"
					+ e.getLocalizedMessage());
		}
		// 将用户信息设置到画面显示对象
		model.addAttribute("user", user);
		List<RoleModel> selectRoleList = roleService.getRoleListByUserId(user
				.getUserId());
		model.addAttribute("selectRoleList", selectRoleList);
		// 跳转至用户查看页面
		return "admin/user/viewUser";
	}

	/**
	 * 用户编辑处理
	 * 
	 * @param TblUser
	 *            用户输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/modifyUser", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String modifyUser(@ModelAttribute("TblUser") TblUser user,
			BindingResult result, HttpServletRequest request)
			throws IOException {
		BaseResult baseResult = new BaseSuccess();
		if (result.hasErrors()) {
			return new BaseFail(5000).toString();
		}
		// 设置更新者用户信息
		ProcessInfoCommon.setUpdateUserInfo(user, request);
		try {
			// 执行用户添加处理
			tblUserService.updateUser(user);
			String roleIds = "";
			String roleIdArr[] = request.getParameterValues("roleId");
			for (String roleId : roleIdArr) {
				roleIds += roleId + ",";
			}
			roleIds = StringUtils.removeEnd(roleIds, ",");
			roleService.updateUserRoleRelation(user.getUserId(), roleIds);
		} catch (Exception e) {
			log.error(this.getClass() + ".modifyUser() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

	/**
	 * 管理员电话唯一性检查
	 * 
	 */
	@RequestMapping(value = "/checkAminUserPhone")
	@ResponseBody
	public String checkBusinessUserPhone(TblUser user) {
		// 返回处理结果信息
		return String.valueOf(tblUserService.checkAdminPhone(user));
	}

	/**
	 * 用户删除处理
	 * 
	 * @param userId
	 *            用户ID
	 * @param model
	 *            画面显示对象
	 * @return String 处理结果信息
	 * @throws 无
	 */
	@RequestMapping(value = "/removeUser")
	@ResponseBody
	public String removeUser(@RequestParam("pkUserinfos") String pkUserinfos) {
		BaseResult baseResult = new BaseSuccess();
		TblUser tblUser = new TblUser();
		try {
			String[] ids = pkUserinfos.split(",");
			for (String id : ids) {
				// 设置更新者用户信息
				tblUser.setUserId(Long.valueOf(id));
				ProcessInfoCommon.setUpdateUserInfo(tblUser);
				// 执行删除处理
				tblUser.setUserStatus(3);
				tblUserService.deleteUser(tblUser);
				roleService.updateUserRoleRelation(tblUser.getUserId(), null);
			}
		} catch (Exception e) {
			log.error(this.getClass() + ".removeUser() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

	/**
	 * 用户唯一性检查
	 * 
	 * @param userId
	 *            用户ID
	 * @return String 处理结果信息
	 * @throws 无
	 */
	@RequestMapping(value = "/checkUserUnique")
	@ResponseBody
	public String checkUserUnique(TblUser user) {
		// 返回处理结果信息
		return String.valueOf(tblUserService.checkAccount(user));
	}

	/**
	 * 用户查看初始化处理
	 * 
	 * @param model
	 *            画面显示对象
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/editPassword")
	public String changePassword(Model model, HttpServletRequest request) {
		// 登录用户信息
		TblUser loginUser = null;
		// 从会话中取得登录用户信息
		loginUser = SessionMgr.getWebUser(request);
		// 将用户信息设置到画面显示对象
		model.addAttribute("tblUser", loginUser);
		// 跳转至用户查看页面
		return "admin/user/changePassword";
	}

	/**
	 * 检查当前密码
	 * 
	 * @param userId
	 *            用户ID
	 * @param model
	 *            画面显示对象
	 * @return String 处理结果信息
	 * @throws 无
	 */
	@RequestMapping(value = "/checkNowPassword")
	@ResponseBody
	public String checkNowPassword(@RequestParam("userId") String userId,
			@RequestParam("nowPassword") String nowPassword) {
		// 返回处理结果信息
		return tblUserService.checkNowPassword(userId, nowPassword) + "";
	}

	/**
	 * 密码重置处理
	 * 
	 * @param userId
	 *            用户ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/resetPassword")
	@ResponseBody
	public String resetPassword(@ModelAttribute("TblUser") TblUser user,
			Model model) {
		BaseResult baseResult = new BaseSuccess();
		try {
			String password = ProcessInfoCommon.getRandomKey(6);
			// 执行更新处理
			user.setUserPassword(password);
			tblUserService.updateUser(user);
			baseResult = new BaseSuccess("密码重置成功,重置密码为：" + password);
		} catch (Exception e) {
			log.error(this.getClass() + ".resetPassword() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		// 返回处理结果信息
		return baseResult.toString();
	}

	/**
	 * 密码更新处理
	 * 
	 * @param TblUser
	 *            用户输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/modifyPassword", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String modifyPassword(@ModelAttribute("TblUser") TblUser TblUser,
			BindingResult result, HttpServletRequest request)
			throws IOException {
		BaseResult baseResult = new BaseSuccess();
		if (result.hasErrors()) {
			return new BaseFail(5000).toString();
		}
		// 设置更新者用户信息
		ProcessInfoCommon.setUpdateUserInfo(TblUser, request);
		try {
			// 执行用户添加处理
			tblUserService.updateUser(TblUser);
		} catch (Exception e) {
			log.error(this.getClass() + ".modifyPassword() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

}
