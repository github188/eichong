package com.wanma.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.RoleModel;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.service.RoleService;
import com.bluemobi.product.service.TblUserService;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.MD5Util;
import com.wanma.common.AliSMS;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblActivity;
import com.wanma.model.TblCarinfo;
import com.wanma.model.TblConfigcontent;
import com.wanma.model.TblUser;
import com.wanma.model.TblUserCard;
import com.wanma.model.UserCoupon;
import com.wanma.service.ChargeCardService;
import com.wanma.service.CmsCarCompanyService;
import com.wanma.service.CmsCarinfoService;
import com.wanma.service.CmsCommitLogService;
import com.wanma.service.CmsCouponDetailService;

@Controller
@RequestMapping("/admin/userManager")
public class CmsUserInfoController extends BaseController {
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsUserInfoController.class);

	@Autowired
	private TblUserService tblUserService;
	@Autowired
	private CmsCarinfoService carinfoService;
	@Autowired
	CmsCommitLogService commitLogService;
	@Autowired
	private CmsCarCompanyService carCompanyService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private CmsCouponDetailService cmsCouponDetailService;
	@Autowired
	private ChargeCardService cardService;

	/**
	 * 取得用户列表处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/userManagerList")
	public String getUserList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblUser user,
			@ModelAttribute TblConfigcontent tblConfigcontent, Model model,
			HttpServletRequest request) {
		user.setUserLevel(WanmaConstants.USER_LEVEL_NORMAL);
		// 登录用户信息
		List<TblUser> userList = null;
		List<HashMap<String, Object>> brandList = new ArrayList<HashMap<String, Object>>();

		if (user.getNormCarCompanyId() != null) {
			List<TblCarinfo> carTypeList = null;
			int pkCarcompany = user.getNormCarCompanyId();
			// 获取电动车车型
			carTypeList = carinfoService.searchCarinfoList(pkCarcompany);
			model.addAttribute("carTypeList", carTypeList);
		}
		// 用户总数
		long total = 0;
		try {

			// 获取电动车厂家
			brandList = carCompanyService.findCarCompanyList(null);
			// 用户总数
			user.setPager(pager);
			userList = tblUserService.getUserList(user);
			total = tblUserService.getUserCount(user);

			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			// 取得用户列表
			pager.setTotal(total);

			// 取得登录用户信息
			TblUser loginUser = SessionMgr.getWebUser(request);
			model.addAttribute("loginUserLevel", loginUser.getUserLevel());
		} catch (Exception e) {
			log.error("获取普通用户列表失败...");
			log.error(e.getMessage());
			return "backstage/userInfo/listUserInfo";
		}
		// 将用户信息放到会话中
		model.addAttribute("user", user);
		model.addAttribute("userList", userList);
		model.addAttribute("brandList", brandList);
		model.addAttribute("pager", pager);
		// 跳转至管理员主页面
		return "backstage/userInfo/listUserInfo";
	}

	/**
	 * 用户添加初始化处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/newUser")
	public String newUser(@ModelAttribute TblConfigcontent tblConfigcontent,
			Model model) {
		// 获取品牌车型
		List<HashMap<String, Object>> brandList = carCompanyService
				.findCarCompanyList(null);
		TblUser user = new TblUser();
		// 将用户信息设置到画面显示对象
		model.addAttribute("user", user);
		model.addAttribute("brandList", brandList);
		// 跳转至用户添加页面
		return "backstage/userInfo/newUserInfo";
	}

	/**
	 * 用户添加处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param user
	 *            用户输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 */
	@RequestMapping(value = "/saveUser", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String saveUser(
			TblUser user,
			BindingResult result,
			HttpServletRequest request,
			@RequestParam(value = "userImage", required = false) MultipartFile[] userImage)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		try {
			// 添加大账户用户
			if (!tblUserService.checkAccount(user)) {
				return new JsonObject(new DwzAjaxResult("300", "账号重复",
						"userAddPage", "", "")).toString();
			}
			// 执行用户添加处理，并取得添加成功的用户详细信息
			user.setNormBirthday(request.getParameter("normBirthday"));
			user.setNormStatus(1);
			user.setUserStatus(1);
			// 初始化密码
			String password = ProcessInfoCommon.getDigitRandomKey(6);
			user.setUserPassword(MD5Util.MD5(password));
			tblUserService.insertUser(user);
			String phone = user.getPhone();
			// 发送初始化密码给用户
			// 大账户和普通账户短信内容不同
			JSONObject parValue = new JSONObject();
			parValue.put("password", password.trim());
			// 阿里大于短信发送
			AliSMS.sendAliSMS(phone, "SMS_16790100", parValue.toString());
			// 设置成功并返回用户一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "userManagerList",
					"closeCurrent", "");
			// 如果添加用户处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			System.out.println(e);
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "userAddPage",
					"", "");

		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 电话唯一性检查
	 * 
	 */
	@RequestMapping(value = "/checkUserPhone")
	@ResponseBody
	public String checkElectricUnique(TblUser user) {
		// 返回处理结果信息
		return String.valueOf(tblUserService.checkAccount(user));
	}

	/**
	 * 商家电话唯一性检查
	 * 
	 */
	@RequestMapping(value = "/checkBusinessUserPhone")
	@ResponseBody
	public String checkBusinessUserPhone(TblUser user) {
		// 返回处理结果信息
		return String.valueOf(tblUserService.checkBusinessPhone(user));
	}

	/**
	 * 用户编辑初始化处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pkUserinfo
	 *            用户ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public String editUser(@RequestParam("pkUserinfo") String pkUserinfo,
			@ModelAttribute TblConfigcontent tblConfigcontent, Model model) {
		TblUser user = new TblUser();
		user.setUserId(new Long(pkUserinfo));
		user.setUserLevel(WanmaConstants.USER_LEVEL_NORMAL);
		// 取得编辑对象用户信息
		try {
			user = tblUserService.findUser(user);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		// 获取品牌车型
		List<HashMap<String, Object>> brandList = carCompanyService
				.findCarCompanyList(null);
		// 获取电动车车型
		List<TblCarinfo> carTypeList = null;
		Integer pkCarcompany = user.getNormCarCompanyId();
		if (pkCarcompany != null) {
			carTypeList = carinfoService.searchCarinfoList(pkCarcompany);
			model.addAttribute("carTypeList", carTypeList);
		}
		// 大账户手机号处理
		if (user.getUserAccount().length() == 12) {
			user.setUserAccount(user.getUserAccount().substring(0,
					user.getUserAccount().length() - 1));
			user.setIsBigAccount(1);
		}
		model.addAttribute("user", user);
		model.addAttribute("brandList", brandList);

		// 跳转至用户编辑页面
		return "backstage/userInfo/editUserInfo";
	}

	/**
	 * 用户编辑处理
	 * 
	 * @author xiay
	 * @since Version 1.0
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
	public String modifyUser(@ModelAttribute("user") TblUser user,
			BindingResult result, HttpServletRequest request)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		try {
			// if (StringUtils.isBlank(request.getParameter("normBirthday"))) {
			// dwzResult = new DwzAjaxResult("300", "出生年月不能为空", "userAddPage",
			// "", "");
			// return new JsonObject(dwzResult).toString();
			// }
			// 大账户手机号处理
			if (user.getIsBigAccount() == 1)
				user.setUserAccount(user.getUserAccount() + "1");
			if (!tblUserService.checkAccount(user)) {
				return new JsonObject(new DwzAjaxResult("300", "手机号重复",
						"userAddPage", "", "")).toString();
			}
			user.setNormBirthday(request.getParameter("normBirthday"));
			// 执行用户添加处理
			tblUserService.updateUser(user);
			// 设置成功并返回用户一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "userManagerList",
					"closeCurrent", "");
			// 如果更新用户处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());

			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "userEditPage",
					"", "");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 个体商家升级处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pkUserinfo
	 *            用户ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/upgradeUser")
	@ResponseBody
	public String upgradeUser(@ModelAttribute("user") TblUser tblUser,
			Model model) {
		DwzAjaxResult dwzResult;
		try {
			TblUser user = tblUserService.findUser(tblUser);
			if (user.getUserStatus() != 1 || user.getNormStatus() != 1) {
				dwzResult = new DwzAjaxResult("300", "只有正常状态才可提升为个体商家",
						"userManagerList", "", "");
				return new JsonObject(dwzResult).toString();
			}
			user.setNormStatus(2);
			tblUserService.upgradeUser(user);

			dwzResult = new DwzAjaxResult("200", "保存成功", "userManagerList", "",
					"");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误",
					"userManagerList", "", "");
		}

		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 个体商家升级处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param user
	 *            用户输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/upgradeUserList", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String upgradeUserList(
			@ModelAttribute("user") TblUser user,
			BindingResult result,
			@RequestParam(value = "IdCardPic", required = false) MultipartFile[] IdCardPic,
			HttpServletRequest request) throws IOException {
		DwzAjaxResult dwzResult;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "userUpgradePage", "",
					"");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}
		try {
			user.setNormStatus(2);
			tblUserService.upgradeUser(user);
			// 设置成功并返回用户一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "userManagerList",
					"closeCurrent", "");
			// 如果更新用户处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());

			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误",
					"userUpgradePage", "", "");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 批量审批
	 * 
	 * @author songjf
	 * @since Version 1.0
	 * @param id
	 *            注册用户id
	 * @throws 无
	 */
	@RequestMapping("/approvalAllList")
	@ResponseBody
	public String approvalAllList(
			@RequestParam("pkUserinfos") String pkUserinfos) {
		log.info("************批量审批用户状态-begin************");

		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		String[] pkUserinfoArray = pkUserinfos.split(",");
		TblUser user = null;
		List<TblUser> userList = new ArrayList<TblUser>();
		try {
			String errorCode = "";
			for (int i = 0; i < pkUserinfoArray.length; i++) {
				TblUser userTemp = new TblUser();
				userTemp.setUserId(Long.valueOf(pkUserinfoArray[i]));
				userTemp.setUserLevel(WanmaConstants.USER_LEVEL_NORMAL);
				user = tblUserService.findUser(userTemp);
				if (user.getUserStatus() != 1 || user.getNormStatus() != 2) {
					errorCode += pkUserinfoArray[i] + ",";
				} else {
					userList.add(user);
				}
			}
			if (StringUtils.isNotBlank(errorCode)) {
				dwzResult = new DwzAjaxResult("300", "有用户不是待审批状态:"
						+ StringUtils.removeEnd(errorCode, ","),
						"userManagerList", "", "");
				return new JsonObject(dwzResult).toString();
			}
			for (TblUser user2 : userList) {
				if (user2.getUserStatus() == 1 && user2.getNormStatus() == 2) {
					user2.setUserLevel(5);
					user2.setNormStatus(3);
					user2.setUserPassword(null);
					tblUserService.updateUser(user2);
					RoleModel role = roleService.getBusinessNormalRole();
					roleService.updateUserRoleRelation(user.getUserId(),
							role.getRoleId());
				}
			}
			dwzResult = new DwzAjaxResult("200", "审批成功", "", "", "");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "审批失败", "userManagerList", "",
					"");
		}
		log.info("************编辑用户状态-end************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 冻结
	 * 
	 * @author songjf
	 * @since Version 1.0
	 * @param id
	 *            注册用户id
	 * @throws 无
	 */
	@RequestMapping("/frostUserList")
	@ResponseBody
	public String frostUserList(TblUser frost) {
		log.info("************冻结用户状态-begin************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {

			tblUserService.updateUser(frost);
			dwzResult = new DwzAjaxResult("200", "解冻成功", "userManagerList", "",
					"");

		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "解冻失败", "userManagerList", "",
					"");
		}
		log.info("************冻结用户状态-end************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 批量冻结
	 * 
	 * @author songjf
	 * @since Version 1.0
	 * @param id
	 *            注册用户id
	 * @throws 无
	 */
	@RequestMapping("/frostAllList")
	@ResponseBody
	public String frostAllList(HttpServletRequest request,
			@RequestParam("pkUserinfos") String pkUserinfos,
			@RequestParam("rel") String rel) {
		log.info("************批量冻结用户状态-begin************");

		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		String message = null;

		try {
			String userLevelStr = request.getParameter("userlevel");
			Integer userLevel = StringUtils.isNotBlank(userLevelStr) ? new Integer(
					userLevelStr) : null;
			String[] approvalArray = pkUserinfos.split(",");
			TblUser userinfo = new TblUser();
			TblUser user = null;
			String errorCode = "";
			for (int i = 0; i < approvalArray.length; i++) {
				TblUser userTemp = new TblUser();
				userTemp.setUserId(Long.valueOf(approvalArray[i]));
				userTemp.setUserLevel(userLevel);
				user = tblUserService.findUser(userTemp);
				if (user.getUserStatus() != 1
						|| (user.getNormStatus() != null && user
								.getNormStatus() == 2)) {
					errorCode = approvalArray[i] + ",";
				}
			}
			if (StringUtils.isNotBlank(errorCode)) {
				dwzResult = new DwzAjaxResult("300", "非初始状态不能冻结:"
						+ StringUtils.removeEnd(errorCode, ","), rel, "", "");
				return new JsonObject(dwzResult).toString();
			}
			for (int i = 0; i < approvalArray.length; i++) {
				userinfo.setUserId(Long.valueOf(approvalArray[i]));
				userinfo.setUserStatus(WanmaConstants.USER_STATUS_FROZEN);
				tblUserService.frozenUser(userinfo);
			}
			message = "冻结成功";
			dwzResult = new DwzAjaxResult("200", message, rel, "", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			message = "冻结失败";
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", message, rel, "", "");
		}
		log.info("************冻结用户状态-end************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 批量解冻
	 * 
	 * @author songjf
	 * @since Version 1.0
	 * @param id
	 *            注册用户id
	 * @throws 无
	 */
	@RequestMapping("/unFrost")
	@ResponseBody
	public String unFrostAllList(
			@RequestParam("pkUserinfos") String pkUserinfos,
			@RequestParam("rel") String rel) {
		log.info("************批量解冻用户状态-begin************");
		DwzAjaxResult dwzResult = null;
		String message = null;
		try {
			String[] approvalArray = pkUserinfos.split(",");
			TblUser userinfo = new TblUser();
			TblUser user = null;
			String errorCode = "";
			for (int i = 0; i < approvalArray.length; i++) {
				TblUser userTemp = new TblUser();
				userTemp.setUserId(Long.valueOf(approvalArray[i]));
				user = tblUserService.findUser(userTemp);
				if (user.getUserStatus() != 2) {
					errorCode += approvalArray[i] + ",";
				}
			}
			if (StringUtils.isNotBlank(errorCode)) {
				dwzResult = new DwzAjaxResult("300", "用户不是冻结状态:"
						+ StringUtils.removeEnd(errorCode, ","), rel, "", "");
				return new JsonObject(dwzResult).toString();
			}
			for (int i = 0; i < approvalArray.length; i++) {
				userinfo.setUserId(Long.valueOf(approvalArray[i]));
				userinfo.setUserStatus(WanmaConstants.USER_STATUS_NORMAL);
				tblUserService.unFrozenUser(userinfo);
			}
			message = "解冻成功";
			dwzResult = new DwzAjaxResult("200", message, rel, "", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			message = "解冻失败";
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", message, rel, "", "");
		}
		log.info("************解冻用户状态-end************");
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 删除用户
	 * 
	 * @author xiay
	 * @param pkUserinfo
	 * @return
	 */
	@RequestMapping("/removeUser")
	@ResponseBody
	public String removeUser(TblUser user) {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		user.setUserStatus(3);
		try {
			// 执行删除处理
			TblUser tblUser1 = tblUserService.findUser(user);
			BigDecimal accountBalance = tblUser1.getNormAccountBalance();
			if (accountBalance == null || accountBalance.doubleValue() == 0.00) {
				int consumeCount = tblUserService.getUserConsumeCount(user);
				if (consumeCount == 0) {
					tblUserService.deleteUser(user);
					// 设置处理结果信息
					dwzResult = new DwzAjaxResult("200", "删除成功",
							"userManagerList", "", "");
				} else {
					dwzResult = new DwzAjaxResult("300",
							"删除失败:用户有过充值消费记录，不能删除", "userManagerList", "", "");
				}

			} else {
				dwzResult = new DwzAjaxResult("300", "删除失败:用户余额不为零",
						"userManagerList", "", "");
			}
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());

			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败:系统错误",
					"userManagerList", "", "");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 批量删除
	 * 
	 * @author xiay
	 * @param pkUserinfo
	 * @return
	 */
	@RequestMapping("/removeAll")
	@ResponseBody
	public String removeAll(@RequestParam("pkUserinfos") String pkUserinfos) {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		try {
			String[] approvalArray = pkUserinfos.split(",");
			TblUser userinfo = new TblUser();
			// 删除前验证
			String error1 = "", error2 = "";
			for (int i = 0; i < approvalArray.length; i++) {
				long userId = Long.valueOf(approvalArray[i]);
				userinfo.setUserId(userId);
				userinfo.setUserLevel(6);
				userinfo.setUserStatus(3);
				TblUser user1 = tblUserService.findUser(userinfo);
				BigDecimal accountBalance = user1.getNormAccountBalance();
				if (accountBalance != null && accountBalance.doubleValue() > 0) {
					error1 = approvalArray[i] + ",";
				}
				int consumeCount = tblUserService.getUserConsumeCount(userinfo);
				if (consumeCount > 0) {
					error2 = approvalArray[i] + ",";
				}
				if (1 == user1.getUserStatus())
					return new JsonObject(new DwzAjaxResult("300",
							"删除失败！用户为非冻结状态", "userManagerList", "", ""))
							.toString();
			}
			String errorCode = "";
			if (StringUtils.isNotBlank(error1)) {
				errorCode += "用户余额不为零:" + error1;
			}
			if (StringUtils.isNotBlank(error2)) {
				errorCode += "用户有过充值消费记录:" + error2;
			}
			if (StringUtils.isNotBlank(errorCode)) {
				dwzResult = new DwzAjaxResult("300", StringUtils.removeEnd(
						errorCode, ","), "userManagerList", "", "");
				return new JsonObject(dwzResult).toString();
			}
			// 删除操作
			for (int i = 0; i < approvalArray.length; i++) {
				long userId = Long.valueOf(approvalArray[i]);
				userinfo.setUserId(userId);
				tblUserService.deleteUser(userinfo);
				commitLogService.insert("删除普通用户，用户主键：[" + userId + "]");

			}
			dwzResult = new DwzAjaxResult("200", "删除成功", "userManagerList", "",
					"");
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败:系统错误",
					"userManagerList", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 取得个体商家列表处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/unitShopList")
	public String getUnitList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute("user") TblUser user,
			@ModelAttribute TblConfigcontent tblConfigcontent, Model model,
			HttpServletRequest request) {
		user.setUserLevel(WanmaConstants.USER_LEVEL_BUSINESS_NORMAL);
		List<HashMap<String, Object>> brandList = new ArrayList<HashMap<String, Object>>();
		if (user.getNormCarCompanyId() != null) {
			List<TblCarinfo> carTypeList = null;
			int pkCarcompany = user.getNormCarCompanyId();
			// 获取电动车车型
			carTypeList = carinfoService.searchCarinfoList(pkCarcompany);
			model.addAttribute("carTypeList", carTypeList);
		}
		// 获取电动车厂家
		brandList = carCompanyService.findCarCompanyList(null);
		// 登录个体商家信息
		List<TblUser> unitList = null;
		// 个体商家总数
		long total = 0;
		// 取得个体商家列表
		try {
			// 个体商家总数
			total = tblUserService.getUserCount(user);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			user.setPager(pager);
			unitList = tblUserService.getUserList(user);
		} catch (Exception e) {
			log.error("获取个体商家用户列表失败...");
			log.error(e.getMessage());
		}
		pager.setTotal(total);
		// 将个体商家信息放到会话中
		model.addAttribute("user", user);
		model.addAttribute("unitList", unitList);
		model.addAttribute("brandList", brandList);
		model.addAttribute("pager", pager);

		// 跳转至管理员主页面
		return "backstage/UnitShops/listUnitInfo";
	}

	/**
	 * 个体商家添加初始化处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/newUnit")
	public String newUnit(@ModelAttribute TblConfigcontent tblConfigcontent,
			Model model) {
		// 获取品牌车型
		List<HashMap<String, Object>> brandList = carCompanyService
				.findCarCompanyList(null);
		// 将个体商家信息设置到画面显示对象
		model.addAttribute("brandList", brandList);
		TblUser user = new TblUser();
		user.setUserLevel(WanmaConstants.USER_LEVEL_BUSINESS_NORMAL);
		// 查询个体商家角色列表
		RoleModel role = new RoleModel();
		role.setCategory("5");
		List<RoleModel> roleList = roleService.searchRoleList(role);
		model.addAttribute("roleList", roleList);
		// 跳转至个体商家添加页面
		return "backstage/UnitShops/newUnitInfo";
	}

	/**
	 * 个体商家添加处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param user
	 *            个体商家输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 */
	@RequestMapping(value = "/saveUnit", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String saveUnit(
			@ModelAttribute("user") TblUser user,
			BindingResult result,
			@RequestParam(value = "IdCardPic", required = false) MultipartFile[] IdCardPic,
			@RequestParam(value = "IdCardPic", required = false) MultipartFile[] titleMultiFile,
			HttpServletRequest request) throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		try {
			// if (StringUtils.isBlank(user.getNormBirthday())) {
			// dwzResult = new DwzAjaxResult("300", "出生年月不能为空", "unitAddPage",
			// "", "");
			// return new JsonObject(dwzResult).toString();
			// }
			// 执行用户添加处理，并取得添加成功的用户详细信息
			user.setUserStatus(1);
			user.setUserPassword(MD5Util.MD5(user.getUserPassword()));
			tblUserService.insertUser(user);
			String roleIds = "";
			String roleIdArr[] = request.getParameterValues("roleId");
			for (String roleId : roleIdArr) {
				roleIds += roleId + ",";
			}
			roleIds = StringUtils.removeEnd(roleIds, ",");
			// 更新用户角色关系
			roleService.updateUserRoleRelation(user.getUserId(), roleIds);
			// 设置成功并返回个体商家一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "unitShopList",
					"closeCurrent", "");
			// 如果添加个体商家处理不成功
		} catch (Exception e) {
			// 出错日志
			System.out.print(e);
			log.error(e.getLocalizedMessage());
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "unitAddPage",
					"", "");

		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 个体商家编辑初始化处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pkUserinfo
	 *            个体商家ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/editUnit", method = RequestMethod.GET)
	public String editUnit(@ModelAttribute("user") TblUser tblUser,
			@ModelAttribute TblConfigcontent tblConfigcontent, Model model,
			HttpServletRequest request) {
		// 获取品牌车型
		List<HashMap<String, Object>> brandList = carCompanyService
				.findCarCompanyList(null);
		// 取得编辑对象个体商家信息
		TblUser user = null;
		try {
			user = tblUserService.findUser(tblUser);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		// 获取电动车车型
		List<TblCarinfo> carTypeList = null;
		int pkCarcompany = user.getNormCarCompanyId();
		carTypeList = carinfoService.searchCarinfoList(pkCarcompany);
		// 将用户信息设置到画面显示对象
		model.addAttribute("carTypeList", carTypeList);
		model.addAttribute("user", user);
		model.addAttribute("brandList", brandList);
		// 查询个体商家角色列表
		RoleModel role = new RoleModel();
		role.setCategory("5");
		List<RoleModel> roleList = roleService.searchRoleList(role);
		model.addAttribute("roleList", roleList);
		List<RoleModel> selectRoleList = roleService.getRoleListByUserId(user
				.getUserId());
		model.addAttribute("selectRoleList", selectRoleList);
		// 跳转至个体商家编辑页面
		return "backstage/UnitShops/editUnitInfo";
	}

	/**
	 * 个体商家编辑处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param user
	 *            个体商家输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/modifyUnit", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String modifyUnit(@ModelAttribute("user") TblUser user,
			BindingResult result, HttpServletRequest request)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		try {
			// if (StringUtils.isBlank(user.getNormBirthday())) {
			// dwzResult = new DwzAjaxResult("300", "出生年月不能为空",
			// "unitEditPage", "", "");
			// return new JsonObject(dwzResult).toString();
			// }
			// 执行个体商家添加处理
			tblUserService.updateUser(user);
			String roleIds = "";
			String roleIdArr[] = request.getParameterValues("roleId");
			for (String roleId : roleIdArr) {
				roleIds += roleId + ",";
			}
			roleIds = StringUtils.removeEnd(roleIds, ",");
			roleService.updateUserRoleRelation(user.getUserId(), roleIds);
			// 设置成功并返回个体商家一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "unitShopList",
					"closeCurrent", "");
			// 如果更新个体商家处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "unitEditPage",
					"", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 用户查看初始化处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pkUserinfo
	 *            用户ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/viewUnit", method = RequestMethod.GET)
	public String viewUnit(@ModelAttribute("user") TblUser tblUser, Model model) {
		// 取得查看对象用户信息
		TblUser user = null;
		try {
			user = tblUserService.findUser(tblUser);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		// 将用户信息设置到画面显示对象
		model.addAttribute("user", user);
		List<RoleModel> selectRoleList = roleService.getRoleListByUserId(user
				.getUserId());
		model.addAttribute("selectRoleList", selectRoleList);
		// 获取品牌车型
		List<HashMap<String, Object>> brandList = carCompanyService
				.findCarCompanyList(null);
		// 获取电动车车型
		List<TblCarinfo> carTypeList = null;
		Integer pkCarcompany = user.getNormCarCompanyId();
		if (pkCarcompany != null) {
			carTypeList = carinfoService.searchCarinfoList(pkCarcompany);
			model.addAttribute("carTypeList", carTypeList);
		}
		model.addAttribute("brandList", brandList);
		// 跳转至用户查看页面
		return "backstage/UnitShops/viewUnit";
	}

	/**
	 * 取得用户列表处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/userListLookup")
	public String getUserListLookup(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblUser user, Model model) {
		user.setUserLevel(6);
		// 登录用户信息
		List<TblUser> userList = null;
		try {
			// 用户总数
			long total = tblUserService.getUserCount(user);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			user.setPager(pager);
			// 取得用户列表
			userList = tblUserService.getUserList(user);
			pager.setTotal(total);
		} catch (Exception e) {
			log.error("获取普通用户列表失败...");
			log.error(e.getMessage());
			return "backstage/userInfo/listUserInfo";
		}
		// 把系统放入第一行
		TblUser systemUser = new TblUser();
		systemUser.setUserId(0L);
		systemUser.setNormRealName("爱充网");
		systemUser.setUserAccount("---");
		systemUser.setNormEmail("---");
		if (userList == null || userList.isEmpty()) {
			userList = new ArrayList<TblUser>();
			pager.setTotal(1L);
		}
		userList.add(0, systemUser);
		// 将用户信息放到会话中
		model.addAttribute("user", user);
		model.addAttribute("userList", userList);
		model.addAttribute("pager", pager);

		// 跳转至管理员主页面
		return "backstage/userInfo/listUserInfoLookup";
	}

	/**
	 * 取得用户列表处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/businessListLookup")
	public String getBusinessListLookup(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblUser user, Model model) {
		List<TblUser> userList = null;
		try {
			long total = tblUserService.getApplyCardUserListCount(user);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			user.setPager(pager);
			userList = tblUserService.getApplyCardUserList(user);
			pager.setTotal(total);
		} catch (Exception e) {
			log.error("获取商家用户列表失败...");
			log.error(e.getMessage());
			return "backstage/userInfo/listUserInfo";
		}
		model.addAttribute("user", user);
		model.addAttribute("userList", userList);
		model.addAttribute("pager", pager);
		return "backstage/userInfo/listBusinessLookup";
	}

	/**
	 * 删除个体商家
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pkUserinfo
	 * @throws 无
	 */
	@RequestMapping("/removeUnit")
	@ResponseBody
	public String removeUnit(@RequestParam("pkUserinfos") String pkUserinfos) {

		// 处理结果信息
		DwzAjaxResult dwzResult;
		TblUser tblUser = new TblUser();
		;
		try {
			String[] ids = pkUserinfos.split(",");
			TblUser tempUser = null;
			String errorCode = "";
			for (String id : ids) {
				tempUser = tblUserService.getPileAndChildCount(new Long(id));
				if (tempUser.getPileCount() > 0 || tempUser.getChildCount() > 0) {
					errorCode += tempUser.getUserAccount() + ",";
				}
			}
			if (StringUtils.isNotBlank(errorCode)) {
				dwzResult = new DwzAjaxResult("300", "商家下已建电桩数据:"
						+ StringUtils.removeEnd(errorCode, ","),
						"unitShopList", "", "");
				return dwzResult.toJSONString();
			}
			for (String id : ids) {
				// 执行删除处理
				tblUser.setUserId(Long.valueOf(id));
				tblUser.setUserStatus(3);
				tblUserService.deleteUser(tblUser);
				roleService.updateUserRoleRelation(tblUser.getUserId(), null);
			}
			// 设置处理结果信息
			dwzResult = new DwzAjaxResult("200", "删除成功", "unitShopList", "", "");
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败:系统错误", "unitShopList",
					"", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();

	}

	/**
	 * 取得用户优惠券列表
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/userCouponList")
	public String userCouponList(@ModelAttribute("pager") DwzPagerMySQL pager,
			UserCoupon userCoupon, Model model, TblActivity activity,
			HttpServletRequest request) {
		List<UserCoupon> userCouponList = null;
		List<TblActivity> activityList = null;
		// 用户总数
		long total = 0;
		try {
			// 获取用户优惠券列表
			if (userCoupon.getActType() == null) {// 刚进页面默认为渠道活动
				userCoupon.setActType("2");
			} else if (userCoupon.getActType().equals("3")) {
				userCoupon.setActType("");
			}
			if (activity.getActActivityname() == null) {
				activityList = tblUserService.getActivityList(userCoupon);
			}
			// 用户总数
			total = tblUserService.getUserCouponCount(userCoupon);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			userCoupon.setPager(pager);
			pager.setTotal(total);
			userCouponList = tblUserService.findUserCouponList(userCoupon);
			// 查到后修改优惠券的状态
			Date currentTime = new Date();// 获取当前时间
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
			String nowtime = formatter.format(currentTime);
			for (UserCoupon uc : userCouponList) {
				if (uc.getCpEndDate().compareTo(nowtime) < 0
						&& uc.getCpStatus() != 1) {// 已过期
					uc.setCpStatus(4);
				} else if (uc.getCpStatus() == 1) {// 已使用
					uc.setCpStatus(3);
				} else if (uc.getCpStatus() != 1 && uc.getCpUserId() != 0
						&& uc.getCpEndDate().compareTo(nowtime) > 0) {// 已兑换
					uc.setCpStatus(2);
				} else if (uc.getCpStatus() != 1 && uc.getCpUserId() == 0) {// 未兑换
					uc.setCpStatus(1);
				}
			}

		} catch (Exception e) {
			log.error("获取用户优惠券列表失败...");
			log.error(e.getMessage());
		}
		// 将用户信息放到会话中
		model.addAttribute("userCouponList", userCouponList);
		model.addAttribute("userCoupon", userCoupon);
		model.addAttribute("activityList", activityList);
		model.addAttribute("pager", pager);
		// 跳转至管理员主页面
		return "backstage/userInfo/userCouponList";
	}

	@RequestMapping("getActivityList")
	@ResponseBody
	public String getActivityList(@RequestParam String state) {
		List<TblActivity> list = new ArrayList<TblActivity>();
		if (StringUtils.isBlank(state)) {
			return new AccessErrorResult().toString();
		}
		try {
			list = cmsCouponDetailService.getActivityList(new Integer(state));
		} catch (Exception e) {
			log.error(e.getMessage());
			// 返回活动列表错误信息
			return new AccessErrorResult(2004, "error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************获取活动列表-end************************");
		return new AccessSuccessResult(list).toString();
	}

	/**
	 * 取得未充点卡列表处理
	 * 
	 * @author mb
	 * @param pager
	 *            分页显示相关信息
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/cardListLookup")
	public String cardListLookup(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblUserCard userCard, @ModelAttribute TblUser user,
			Model model) {
		List<TblUserCard> userCardList = null;
		long total = 0;
		try {
			user = tblUserService.findUser(user);
			userCard.setBindFlag(2);// 未绑定充点卡
			total = cardService.getCardCount(userCard);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			pager.setTotal(total);
			userCard.setPager(pager);
			userCardList = cardService.getCardUserList(userCard);
		} catch (Exception e) {
			log.error("获取充电卡列表失败...");
			log.error(e.getMessage());
			return "backstage/userCardInfo/listCardInfo";
		}
		model.addAttribute("userCard", userCard);
		model.addAttribute("user", user);
		model.addAttribute("userCardList", userCardList);
		model.addAttribute("pager", pager);
		return "backstage/userInfo/listCardLookup";
	}

	/**
	 * 绑定卡号处理
	 */
	@RequestMapping("/bindingCard")
	@ResponseBody
	public String bindingCard(TblUserCard userCard, HttpServletRequest request)
			throws IOException {
		DwzAjaxResult dwzResult;
		String ucUserId = request.getParameter("userId");
		String pkUserCards = request.getParameter("cardIds");
		String pkUserCardsArray[] = pkUserCards.split(",");

		userCard.setUcUserId(ucUserId);
		try {
			for (int i = 0; i < pkUserCardsArray.length; i++) {
				userCard.setPkUserCard(Long.parseLong(pkUserCardsArray[i]));
				cardService.bindCard(userCard);
			}
			dwzResult = new DwzAjaxResult("200", "绑定成功", "userManagerList", "",
					"closeCurrent", "");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "", "", "");

		}
		return new JsonObject(dwzResult).toString();
	}

}
