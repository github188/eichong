package com.pub.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.base.common.GlobalSystem;
import com.base.common.SessionMgr;
import com.base.util.DateUtil;
import com.base.util.MD5Util;
import com.base.util.RedisUtil;
import com.pub.model.MenuModel;
import com.pub.model.TblCommitLog;
import com.pub.model.TblUser;
import com.pub.service.MenuService;
import com.pub.service.RoleService;
import com.pub.service.TblCommitLogService;
import com.pub.service.TblUserService;

@Controller
public class LoginController {

	@Autowired
	private TblUserService userService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RedisUtil redisService;
	@Autowired
	private TblCommitLogService commitLogService;

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(LoginController.class);

	/**
	 * 跳转到登陆页面
	 */
	@RequestMapping("/tologin")
	public String toLogin() {
		return "login";
	}

	/**
	 * 用户登录处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param username
	 *            用户名
	 * @param passwd
	 *            密码
	 * @param session
	 *            会话
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/login")
	public String login(@RequestParam("username") String username,
			@RequestParam("passwd") String passwd, HttpSession session,
			HttpServletRequest request, Model model) {
		// 登录用户信息
		TblUser loginUser = null;
		// 错误信息
		String errorMessage = "";

		// 用户名设置
		model.addAttribute("username", username);
		String notCheckAuthCode = GlobalSystem.getConfig("notCheckAuthCode");
		if (!"1".equals(notCheckAuthCode)) {
			// 检查验证码
			String webCode = request.getParameter("code");
			if (StringUtils.isEmpty(webCode)
					|| !webCode
							.equalsIgnoreCase(SessionMgr.getWebCode(request))) {
				model.addAttribute("passwd", passwd);
				errorMessage = GlobalSystem
						.getConfig("error.msg.invalid.auth_code");
				model.addAttribute("erMessage", errorMessage);
				// 跳转至登录页面
				return "login";
			}
		}
		// 未输入用户名
		if (StringUtils.isBlank(username)) {
			errorMessage = GlobalSystem.getConfig("error.msg.empty.user");
			model.addAttribute("erMessage", errorMessage);
			TblCommitLog commitLog = new TblCommitLog();
			commitLog.setLogName(username != null ? username : "");
			commitLog.setIpAddress(getIpAddr(request));
			commitLog.setLogContent("登录失败");
			commitLog.setCreateDate(new Date());
			commitLog.setUpdateDate(new Date());
			commitLog.setCoLoUserId("0");
			commitLogService.insert("登录失败");
			// 跳转至登录页面
			return "login";
			// 未输入密码
		} else if (StringUtils.isBlank(passwd)) {
			errorMessage = GlobalSystem.getConfig("error.msg.empty.password");
			model.addAttribute("erMessage", errorMessage);
			commitLogService.insert("登录失败");
			return "login";
		}

		TblUser tempUser = new TblUser();
		tempUser.setUserAccount(username);
		tempUser.setUserPassword(passwd);
		// 验证当天登录出错次数
		int loginErrorCount = getLoginErrorCount(tempUser);
		if (loginErrorCount == 5) {
			model.addAttribute("erMessage", "您已输错5次密码，请明天再试");
			return "login";
		}
		try {
			loginUser = userService.findLoginUser(tempUser);
			// 数据库获取的密码进行二次加密md5（数据库密码 + 账户）
			String loginPassword = loginUser.getUserPassword();
			String loginAccount = loginUser.getUserAccount();
			loginUser
					.setUserPassword(MD5Util.Md5(loginPassword + loginAccount));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if (loginUser == null) {
			errorMessage = GlobalSystem.getConfig("error.msg.invalid.login");
			model.addAttribute("erMessage", errorMessage);
			return "login";
		} else if (!tempUser.getUserAccount()
				.equals(loginUser.getUserAccount())) {
			model.addAttribute("erMessage", "用户名错误，请区分大小写");
			return "login";
		} else if (!loginUser.getUserPassword().equals(
				tempUser.getUserPassword().substring(0,
						tempUser.getUserPassword().length() - 1))) {
			errorMessage = GlobalSystem.getConfig("error.msg.invalid.password");
			model.addAttribute("erMessage", errorMessage);
			// 设置登陆错误次数
			setLoginErrorCount(tempUser);
			return "login";
		}

		// 将用户信息放到会话中
		SessionMgr.addWebUser(request, loginUser);
		roleService.initRolesAndMenus(request);
		/******* 以下操作为实现唯一登录-begin *********/
		// 保存用户登录
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", session.getId());
		map.put("ip", getIpAddr(request));
		map.put("date", new Date());
		// 将用户信息添加到application对象中
		ServletContext application = session.getServletContext();
		application.setAttribute(loginUser.getUserId() + "", map);

		// 跳转至管理员主页面
		return "redirect:/admin/index.do";
	}
	

	@RequestMapping("/simpleLogin")
	public String simpleLogin() {
		return "/simple_login";
	}
	/**
	 * 用户登录处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param username
	 *            用户名
	 * @param passwd
	 *            密码
	 * @param session
	 *            会话
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/toSimplelogin", method = RequestMethod.POST)
	@ResponseBody
	public String toSimplelogin(@RequestParam("username") String username,
			@RequestParam("passwd") String passwd, HttpServletRequest request,
			Model model) {
		return null;
	}

	/**
	 * 用户登出处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param session
	 *            会话
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		// 清空会话信息
		SessionMgr.removeWebUser(request);
		// 跳转至管理员登录页面
		return "redirect:/tologin.do";
	}

	@RequestMapping("/admin/index")
	public String mainPage(Model model, HttpServletRequest request) {
		log.info("******************/admin/main:begin************************");
		// 登录用户信息
		TblUser loginUser = null;

		// 将用户信息放到会话中
		loginUser = SessionMgr.getWebUser(request);
		// 加载刷新用户角色和菜单，感觉有点凶~~
		roleService.initRolesAndMenus(request);
		List<MenuModel> menuList = SessionMgr.getUserMenus(request);
		String strHtml = menuService.getMenuTree(menuList, false);

		// 将用户信息设置到画面显示对象
		model.addAttribute("menuTreeModel", strHtml);

		// 将登录用户信息设置到画面显示对象
		model.addAttribute("loginUser", loginUser);

		List<TblUser> userList = new ArrayList<TblUser>();
		userList.add(loginUser);

		log.info("******************/admin/main:end************************");
		return "index";
	}

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	private int getLoginErrorCount(TblUser user) {
		try {
			String currentDate = DateUtil.getNow(DateUtil.TYPE_COM_YMD);
			String failNum = redisService.strGet("app:lpw:"
					+ user.getUserAccount() + ":" + currentDate);
			return StringUtils.isNotBlank(failNum) ? new Integer(failNum) : 0;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		return 0;
	}

	private void setLoginErrorCount(TblUser user) {
		try {
			if ("admin".equals(user.getUserAccount()))
				return;
			int failNum = getLoginErrorCount(user);
			String currentDate = DateUtil.getNow(DateUtil.TYPE_COM_YMD);
			redisService.strSet("app:lpw:" + user.getUserAccount() + ":"
					+ currentDate, failNum + 1 + "");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}
}
