package com.wanma.controller;
 
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.BaseSuccess;
import com.base.common.BluemobiCommon;
import com.base.common.ProcessInfoCommon;
import com.base.common.WanmaConstants;
import com.base.util.DateUtil;
import com.base.util.ExcelUtil;
import com.base.util.MD5Util;
import com.pub.controller.BaseController;
import com.pub.model.Pager;
import com.pub.model.RoleModel;
import com.pub.model.TblUser;
import com.pub.service.RoleService;
import com.pub.service.TblUserService;
import com.wanma.model.TblActivity;
import com.wanma.model.TblCarcompany;
import com.wanma.model.TblCarinfo;
import com.wanma.model.TblConfigcontent;
import com.wanma.model.TblPowerstation;
import com.wanma.model.UserCoupon;
import com.wanma.service.CmsCarCompanyService;
import com.wanma.service.CmsCarinfoService;
import com.wanma.service.CmsCommitLogService;

/**
 * 运营管理-配置管理-产品型号
 * @author bc
 *
 */
@Controller
@RequestMapping("/admin/userNormal")
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

	@RequestMapping(value = "/userNormalListPage")
	public String userNormalListPage(HttpServletRequest request) {
		return "backstage/userNormal/userNormalListPage";
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
	@RequestMapping(value = "/getUserNormalList")
	@ResponseBody
	public String getUserList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblUser user,
			@ModelAttribute TblConfigcontent tblConfigcontent, Model model,
			HttpServletRequest request) {
		BaseResult baseResult = new BaseFail(5001);
		user.setUserLevel(WanmaConstants.USER_LEVEL_NORMAL);
		// 登录用户信息
		List<TblUser> userList = null;
		// 用户总数
		long total = 0;
		try {
			List<TblCarinfo> carinfoList = carinfoService.findCarList(null);
			List<TblCarcompany> carCompanyList = carCompanyService
					.findCarCompanyList(null);
			// 用户总数
			user.setPager(pager);
			userList = tblUserService.getUserList(user);
			for (TblUser entity : userList) {
				entity.setExtValue1(WanmaConstants.getConfigName("10", entity
						.getNormRegisteType().toString()));
				entity.setExtValue2(getCarinfoName(entity.getNormCarTypeId(),
						carinfoList));
				entity.setExtValue3(getCarCompanyName(
						entity.getNormCarCompanyId(), carCompanyList));
				entity.setExtValue4(WanmaConstants.getConfigName("21", entity
						.getUserStatus().toString()));
			}
			total = tblUserService.getUserCount(user);

			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			pager.setTotal(total);
			baseResult = new BaseResult(userList, pager);
		} catch (Exception e) {
			log.error("获取普通用户列表失败...");
			log.error(e.getMessage());
		}
		return baseResult.toString();
	}

	private String getCarinfoName(int normCarTypeId,
			List<TblCarinfo> carinfoList) {
		for (TblCarinfo car : carinfoList) {
			if (normCarTypeId == car.getPkCarinfo()) {
				return car.getCarinfoStylename();
			}
		}
		return "---";
	}

	private String getCarCompanyName(int normCarCompanyId,
			List<TblCarcompany> carCompanyList) {
		for (TblCarcompany obj : carCompanyList) {
			if (normCarCompanyId == obj.getPkCarcompany()) {
				return obj.getCarcompanyName().toString();
			}
		}
		return "---";
	}

	@RequestMapping(value = "/userNormalExport")
	@ResponseBody
	public void userNormalExport(@ModelAttribute TblUser user,
			HttpServletRequest request, HttpServletResponse response) {
		user.setUserLevel(WanmaConstants.USER_LEVEL_NORMAL);
		// 登录用户信息
		List<TblUser> userList = null;
		List<TblCarinfo> carinfoList = carinfoService.findCarList(null);
		List<TblCarcompany> carCompanyList = carCompanyService
				.findCarCompanyList(null);
		try {
			userList = tblUserService.getUserList(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (TblUser entity : userList) {
			entity.setExtValue1(WanmaConstants.getConfigName("10", entity
					.getNormRegisteType().toString()));
			entity.setExtValue2(getCarinfoName(entity.getNormCarTypeId(),
					carinfoList));
			entity.setExtValue3(getCarCompanyName(entity.getNormCarCompanyId(),
					carCompanyList));
			entity.setExtValue4(WanmaConstants.getConfigName("21", entity
					.getUserStatus().toString()));
		}

		// excel导出
		ExcelUtil eu = new ExcelUtil();
		// 转换成excel可用的数据格式
		List<String[]> dataList = new ArrayList<String[]>();
		String[] data = new String[] { "注册时间", "手机号", "用户姓名", "注册渠道", "邮箱",
				"联系地址", "品牌", "车型", "状态" };
		dataList.add(data);

		for (TblUser obj : userList) {
			data = new String[9];
			data[0] = DateUtil.format(obj.getCreateDate(),
					DateUtil.TYPE_COM_YMD);
			data[1] = obj.getUserAccount();
			data[2] = obj.getNormName();
			data[3] = WanmaConstants.getConfigName("10", obj
					.getNormRegisteType().toString());
			data[4] = obj.getNormEmail();
			data[5] = obj.getNormAddress();
			data[6] = getCarinfoName(obj.getNormCarTypeId(), carinfoList);
			data[7] = getCarCompanyName(obj.getNormCarCompanyId(),
					carCompanyList);
			data[8] = WanmaConstants.getConfigName("21", obj.getUserStatus()
					.toString());
			dataList.add(data);
		}
		try {
			eu.export(dataList, response, "普通用户列表.xlsx");
		} catch (IOException e) {
			e.printStackTrace();
			log.error(this.getClass() + ".userNormalExport() error:"
					+ e.getLocalizedMessage());
		}
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
		List<TblCarcompany> brandList = carCompanyService
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
		BaseResult baseResult = new BaseSuccess();

		try {
			// 添加大账户用户
			if (user.getIsBigAccount() == 1)
				user.setUserAccount(user.getUserAccount() + "1");
			if (!tblUserService.checkAccount(user)) {
				return new BaseFail("手机号重复").toString();
			}
			// 执行用户添加处理，并取得添加成功的用户详细信息
			user.setNormBirthday(request.getParameter("normBirthday"));
			user.setNormStatus(1);
			user.setUserStatus(1);
			// 初始化密码
			String password = ProcessInfoCommon.getDigitRandomKey(6);
			user.setUserPassword(MD5Util.Md5(password));
			tblUserService.insertUser(user);
			String userAccount = user.getUserAccount();
			// 发送初始化密码给用户
			// 大账户和普通账户短信内容不同
			if (user.getIsBigAccount() == 1) {
				userAccount = userAccount
						.substring(0, userAccount.length() - 1);
				BluemobiCommon.sendWanMatMessage(new String("您的充电卡初始化密码为"
						+ password + "，请妥善保管。"), userAccount);
			} else {
				BluemobiCommon.sendWanMatMessage(new String("您的充电卡初始化密码为"
						+ password + "，请在电桩上或APP客户端修改密码。"), userAccount);
			}

		} catch (Exception e) {
			log.error(this.getClass() + ".modifyPassword() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
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
		List<TblCarcompany> brandList = carCompanyService
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
		BaseResult baseResult = new BaseSuccess();
		try {
			// if (StringUtils.isBlank(request.getParameter("normBirthday"))) {
			// dwzResult = new DwzAjaxResult("300", "出生年月不能为空", "userAddPage",
			// "", "");
			// return baseResult.toString();
			// }
			// 大账户手机号处理
			if (user.getIsBigAccount() == 1)
				user.setUserAccount(user.getUserAccount() + "1");
			if (!tblUserService.checkAccount(user)) {
				return new BaseFail("手机号重复").toString();
			}
			user.setNormBirthday(request.getParameter("normBirthday"));
			// 执行用户添加处理
			tblUserService.updateUser(user);
		} catch (Exception e) {
			log.error(this.getClass() + ".modifyUser() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}

		// 返回处理结果信息
		return baseResult.toString();
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
		BaseResult baseResult = new BaseSuccess();
		try {
			TblUser user = tblUserService.findUser(tblUser);
			if (user.getUserStatus() != 1 || user.getNormStatus() != 1) {
				return new BaseFail("只有正常状态才可提升为个体商家").toString();
			}
			user.setNormStatus(2);
			tblUserService.upgradeUser(user);
		} catch (Exception e) {
			log.error(this.getClass() + ".upgradeUser() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}

		return baseResult.toString();
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
		BaseResult baseResult = new BaseSuccess();
		if (result.hasErrors()) {
			return new BaseFail(5000).toString();
		}
		try {
			user.setNormStatus(2);
			tblUserService.upgradeUser(user);
		} catch (Exception e) {
			log.error(this.getClass() + ".upgradeUserList() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}

		// 返回处理结果信息
		return baseResult.toString();
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
		BaseResult baseResult = new BaseSuccess();
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
				return new BaseFail("有用户不是待审批状态:"
						+ StringUtils.removeEnd(errorCode, ",")).toString();
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
		} catch (Exception e) {
			log.error(this.getClass() + ".approvalAllList() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		log.info("************编辑用户状态-end************");
		// 返回处理结果信息
		return baseResult.toString();
	}

	/**
	 * 批量冻结
	 * 
	 * @since Version 1.0
	 * @param id
	 *            注册用户id
	 * @throws 无
	 */
	@RequestMapping("/frostUsers")
	@ResponseBody
	public String frostUsers(HttpServletRequest request, TblUser tblUser) {
		log.info("************批量冻结用户状态-begin************");
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		try {
			String userLevelStr = request.getParameter("userlevel");
			Integer userLevel = StringUtils.isNotBlank(userLevelStr) ? new Integer(
					userLevelStr) : null;
			String[] approvalArray = tblUser.getIds().split(",");
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
				return new BaseFail("非初始状态不能冻结:"
						+ StringUtils.removeEnd(errorCode, ",")).toString();
			}
			for (int i = 0; i < approvalArray.length; i++) {
				userinfo.setUserId(Long.valueOf(approvalArray[i]));
				userinfo.setUserStatus(WanmaConstants.USER_STATUS_FROZEN);
				tblUserService.frozenUser(userinfo);
			}
		} catch (Exception e) {
			log.error(this.getClass() + ".frostUsers() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		log.info("************冻结用户状态-end************");
		// 返回处理结果信息
		return baseResult.toString();
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
	@RequestMapping("/unFrostUsers")
	@ResponseBody
	public String unFrostAllList(TblUser tblUser) {
		log.info("************批量解冻用户状态-begin************");
		BaseResult baseResult = new BaseSuccess();
		try {
			String[] approvalArray = tblUser.getIds().split(",");
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
				return new BaseFail("用户不是冻结状态:"
						+ StringUtils.removeEnd(errorCode, ",")).toString();
			}
			for (int i = 0; i < approvalArray.length; i++) {
				userinfo.setUserId(Long.valueOf(approvalArray[i]));
				userinfo.setUserStatus(WanmaConstants.USER_STATUS_NORMAL);
				tblUserService.unFrozenUser(userinfo);
			}
		} catch (Exception e) {
			log.error(this.getClass() + ".unFrostAllList() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		log.info("************解冻用户状态-end************");
		return baseResult.toString();
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
		BaseResult baseResult = new BaseSuccess();
		user.setUserStatus(3);
		try {
			// 执行删除处理
			TblUser tblUser1 = tblUserService.findUser(user);
			BigDecimal accountBalance = tblUser1.getNormAccountBalance();
			if (accountBalance == null || accountBalance.doubleValue() == 0.00) {
				int consumeCount = tblUserService.getUserConsumeCount(user);
				if (consumeCount == 0) {
					tblUserService.deleteUser(user);
				} else {
					return new BaseFail("删除失败:用户有过充值消费记录，不能删除").toString();
				}

			} else {
				return new BaseFail("删除失败:用户余额不为零").toString();
			}
		} catch (Exception e) {
			log.error(this.getClass() + ".removeUser() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}

		// 返回处理结果信息
		return baseResult.toString();
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
		BaseResult baseResult = new BaseSuccess();
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
					return new BaseFail("删除失败！用户为非冻结状态").toString();
			}
			String errorCode = "";
			if (StringUtils.isNotBlank(error1)) {
				errorCode += "用户余额不为零:" + error1;
			}
			if (StringUtils.isNotBlank(error2)) {
				errorCode += "用户有过充值消费记录:" + error2;
			}
			if (StringUtils.isNotBlank(errorCode)) {
				return new BaseFail(StringUtils.removeEnd(errorCode, ","))
						.toString();
			}
			// 删除操作
			for (int i = 0; i < approvalArray.length; i++) {
				long userId = Long.valueOf(approvalArray[i]);
				userinfo.setUserId(userId);
				tblUserService.deleteUser(userinfo);
				commitLogService.insert("删除普通用户，用户主键：[" + userId + "]");

			}
		} catch (Exception e) {
			log.error(this.getClass() + ".removeAll() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		// 返回处理结果信息
		return baseResult.toString();
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
	public String getUnitList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute("user") TblUser user,
			@ModelAttribute TblConfigcontent tblConfigcontent, Model model,
			HttpServletRequest request) {
		user.setUserLevel(WanmaConstants.USER_LEVEL_BUSINESS_NORMAL);
		List<TblCarcompany> brandList = new ArrayList<TblCarcompany>();
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
		List<TblCarcompany> brandList = carCompanyService
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
		BaseResult baseResult = new BaseSuccess();
		try {
			// if (StringUtils.isBlank(user.getNormBirthday())) {
			// dwzResult = new DwzAjaxResult("300", "出生年月不能为空", "unitAddPage",
			// "", "");
			// return baseResult.toString();
			// }
			// 执行用户添加处理，并取得添加成功的用户详细信息
			user.setUserStatus(1);
			user.setUserPassword(MD5Util.Md5(user.getUserPassword()));
			tblUserService.insertUser(user);
			String roleIds = "";
			String roleIdArr[] = request.getParameterValues("roleId");
			for (String roleId : roleIdArr) {
				roleIds += roleId + ",";
			}
			roleIds = StringUtils.removeEnd(roleIds, ",");
			// 更新用户角色关系
			roleService.updateUserRoleRelation(user.getUserId(), roleIds);
		} catch (Exception e) {
			log.error(this.getClass() + ".saveUnit() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);

		}
		// 返回处理结果信息
		return baseResult.toString();
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
		List<TblCarcompany> brandList = carCompanyService
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
		BaseResult baseResult = new BaseSuccess();

		try {
			// if (StringUtils.isBlank(user.getNormBirthday())) {
			// dwzResult = new DwzAjaxResult("300", "出生年月不能为空",
			// "unitEditPage", "", "");
			// return baseResult.toString();
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
		} catch (Exception e) {
			log.error(this.getClass() + ".modifyUnit() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		// 返回处理结果信息
		return baseResult.toString();
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
		List<TblCarcompany> brandList = carCompanyService
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
	public String getUserListLookup(@ModelAttribute("pager") Pager pager,
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
	public String getBusinessListLookup(@ModelAttribute("pager") Pager pager,
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
		BaseResult baseResult = new BaseSuccess();
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
				return new BaseFail("商家下已建电桩数据:"
						+ StringUtils.removeEnd(errorCode, ",")).toString();
			}
			for (String id : ids) {
				// 执行删除处理
				tblUser.setUserId(Long.valueOf(id));
				tblUser.setUserStatus(3);
				tblUserService.deleteUser(tblUser);
				roleService.updateUserRoleRelation(tblUser.getUserId(), null);
			}
		} catch (Exception e) {
			log.error(this.getClass() + ".removeUnit() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		// 返回处理结果信息
		return baseResult.toString();

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
	public String userCouponList(@ModelAttribute("pager") Pager pager,
			UserCoupon userCoupon, Model model, TblActivity activity,
			HttpServletRequest request) {
		// List<UserCoupon> userCouponList = null;
		// List<TblActivity> activityList = null;
		// if (activity.getActActivityname() == null) {
		// activityList = tblUserService.getActivityList();
		// }
		// // 用户总数
		// long total = 0;
		// try {
		// // 获取用户优惠券列表
		// if (userCoupon.getActType() == null) {// 刚进页面默认为渠道活动
		// userCoupon.setActType("2");
		// } else if (userCoupon.getActType().equals("3")) {
		// userCoupon.setActType("");
		// }
		// if (userCoupon.getCpStatus() == null) {
		//
		// }
		// // 用户总数
		// total = tblUserService.getUserCouponCount(userCoupon);
		// if (total <= pager.getOffset()) {
		// pager.setPageNum(1L);
		// }
		// // 设置分页对象
		// userCoupon.setPager(pager);
		// pager.setTotal(total);
		// userCouponList = tblUserService.findUserCouponList(userCoupon);
		// // 查到后修改优惠券的状态
		// Date currentTime = new Date();// 获取当前时间
		// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
		// String nowtime = formatter.format(currentTime);
		// for (UserCoupon uc : userCouponList) {
		// if (uc.getCpEndDate().compareTo(nowtime) < 0
		// && uc.getCpStatus() != 1) {// 已过期
		// uc.setCpStatus(4);
		// } else if (uc.getCpStatus() == 1) {// 已使用
		// uc.setCpStatus(3);
		// } else if (uc.getCpStatus() != 1 && uc.getCpUserId() != 0
		// && uc.getCpEndDate().compareTo(nowtime) > 0) {// 已兑换
		// uc.setCpStatus(2);
		// } else if (uc.getCpStatus() != 1 && uc.getCpUserId() == 0) {// 未兑换
		// uc.setCpStatus(1);
		// }
		// }
		//
		// } catch (Exception e) {
		// log.error("获取用户优惠券列表失败...");
		// log.error(e.getMessage());
		// }
		// // 将用户信息放到会话中
		// model.addAttribute("userCouponList", userCouponList);
		// model.addAttribute("userCoupon", userCoupon);
		// model.addAttribute("activityList", activityList);
		// model.addAttribute("pager", pager);
		// 跳转至管理员主页面
		return "backstage/userInfo/userCouponList";
	}
}
