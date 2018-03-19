package com.wanma.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.RoleModel;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.service.RoleService;
import com.bluemobi.product.service.TblUserService;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.MD5Util;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblCompany;
import com.wanma.model.TblUser;
import com.wanma.service.CmsPureBusinessService;
import com.wanma.service.CompanyManagerService;

@Controller
@RequestMapping("/admin/userManager")
public class CmsPureBusinessController extends BaseController {

	// 日志输出对象
	private static Logger log = Logger
			.getLogger(CmsPureBusinessController.class);

	/** 纯商家处理器 */
	@Autowired
	private CmsPureBusinessService tblPureBusinessService;
	@Autowired
	private TblUserService tblUserService;
	@Autowired
	private CompanyManagerService companyManagerService;
	@Autowired
	private RoleService roleService;

	/**
	 * 取得纯商家列表处理
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/ShopList")
	public String getBusinessList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblUser tblPureBusiness, Model model,
			HttpServletRequest request) {
		tblPureBusiness.setUserLevel(WanmaConstants.USER_LEVEL_BUSINESS);
		// 登录纯商家信息
		List<TblUser> businessList = null;
		// 纯商家总数
		long total = 0;
		// 获取登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		// 子商家角色,只能看自己
		if (loginUser.getBusiParentId() != null
				&& loginUser.getBusiParentId() != 0) {
			tblPureBusiness.setUserId(loginUser.getUserId());
		}
		// 商家能看到自己公司的用户
		if (loginUser.getBusiCompanyId() != null) {
			tblPureBusiness.setBusiCompanyId(loginUser.getBusiCompanyId());
		}
		// 取得纯商家列表
		try {
			// 纯商家总数
			total = tblUserService.getUserCount(tblPureBusiness);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblPureBusiness.setPager(pager);
			businessList = tblUserService.getUserList(tblPureBusiness);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		pager.setTotal(total);

		// 将纯商家信息放到会话中
		model.addAttribute("tblPureBusiness", tblPureBusiness);
		model.addAttribute("businessList", businessList);
		model.addAttribute("pager", pager);
		model.addAttribute("loginUserLevel", loginUser.getUserLevel());

		// 跳转至纯商家主页面
		return "backstage/Business/listBusinessInfo";
	}

	/**
	 * 纯商家添加初始化处理
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/newBusiness")
	public String newBusiness(Model model, HttpServletRequest request) {
		TblUser loginUser = SessionMgr.getWebUser(request);
		TblCompany company = new TblCompany();
		List<TblCompany> companyList = companyManagerService
				.getUnSelectCompanyList(company);
		model.addAttribute("companyList", companyList);
		List<RoleModel> roleList = roleService.getRoleListByCompanyId(loginUser
				.getBusiCompanyId());
		model.addAttribute("roleList", roleList);
		model.addAttribute("loginUser", loginUser);
		// 跳转至纯商家添加页面
		return "backstage/Business/newBusinessInfo";
	}

	/**
	 * 电话唯一性检查
	 * 
	 */
	@RequestMapping(value = "/checkBusinessAcount")
	@ResponseBody
	public String checkAcount(HttpServletRequest request, TblUser user) {
		TblUser loginUser = SessionMgr.getWebUser(request);
		user.setBusiCompanyId(loginUser.getBusiCompanyId());
		// 返回处理结果信息
		return String.valueOf(tblUserService.checkBusinessAccount(user));

	}

	/**
	 * 纯商家添加处理
	 * 
	 * @author xiay
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/saveBusinessList", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String saveBusinessList(
			@ModelAttribute("tblPureBusiness") TblUser tblPureBusiness,
			BindingResult result, HttpServletRequest request,
			HttpSession session) throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		try {
			// 获取当前登录用户信息
			TblUser loginUser = SessionMgr.getWebUser(request);
			if (loginUser.getBusiParentId() != null
					&& loginUser.getBusiParentId() != 0) {
				// 设置处理错误信息
				dwzResult = new DwzAjaxResult("300", "保存失败:子商家不能新增用户",
						"businessAddPage", "", "");
				return new JsonObject(dwzResult).toString();
			}
			// 如果当前登录是商家用户，那么建立的商家用户属于当前登录的子账户，会选择子角色
			if (loginUser.getUserLevel().equals(
					WanmaConstants.USER_LEVEL_BUSINESS)) {
				tblPureBusiness.setBusiParentId(loginUser.getUserId()
						.intValue());
				tblPureBusiness.setBusiCompanyId(loginUser.getBusiCompanyId());
			} else {
				tblPureBusiness.setBusiParentId(0);
			}
			tblPureBusiness.setUserLevel(WanmaConstants.USER_LEVEL_BUSINESS);
			tblPureBusiness.setUserPassword(MD5Util.MD5(tblPureBusiness
					.getUserPassword()));
			tblPureBusiness.setUserStatus(WanmaConstants.USER_STATUS_NORMAL);
			tblUserService.insertUser(tblPureBusiness);
			// 更新用户角色关系
			String roleIds = "";
			String[] roleIdArr = request.getParameterValues("roleId");
			if (roleIdArr == null || roleIdArr.length == 0) {
				dwzResult = new DwzAjaxResult("300", "保存失败:角色不能为空,请创建或选中角色",
						"businessAddPage", "", "");
				return new JsonObject(dwzResult).toString();
			}
			for (String roleId : roleIdArr) {
				roleIds += roleId + ",";
			}
			roleIds = StringUtils.removeEnd(roleIds, ",");
			roleService.updateUserRoleRelation(tblPureBusiness.getUserId(),
					roleIds);
			// 设置成功并返回纯商家一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "ShopList",
					"closeCurrent", "");
			// 如果添加纯商家处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error("添加失败：", e);
			System.out.println(e);
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误",
					"businessAddPage", "", "");

		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 纯商家编辑初始化处理
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/editBusiness", method = RequestMethod.GET)
	public String editBusiness(
			@ModelAttribute("businessUser") TblUser businessUser, Model model,
			HttpServletRequest request) {

		// 取得编辑纯商家信息
		TblUser tblPureBusiness = null;
		try {
			tblPureBusiness = tblUserService.findUser(businessUser);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		TblUser loginUser = SessionMgr.getWebUser(request);
		TblCompany company = new TblCompany();
		company.setPkCompanyid(tblPureBusiness.getBusiCompanyId());
		List<TblCompany> companyList = companyManagerService
				.getCompanyList(company);
		model.addAttribute("companyList", companyList);
		List<RoleModel> roleList = roleService.getRoleListByCompanyId(loginUser
				.getBusiCompanyId());
		model.addAttribute("roleList", roleList);
		List<RoleModel> selectRoleList = roleService
				.getRoleListByUserId(tblPureBusiness.getUserId());
		model.addAttribute("selectRoleList", selectRoleList);
		// 将纯商家信息设置到画面显示对象
		model.addAttribute("tblPureBusiness", tblPureBusiness);
		model.addAttribute("loginUser", loginUser);
		// 跳转至纯商家编辑页面
		return "backstage/Business/editBusinessInfo";
	}

	/**
	 * 纯商家编辑处理
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/modifyBusiness", produces = "plain/text; charset=UTF-8")
	@ResponseBody
	public String modifyBusiness(
			@ModelAttribute("TblPureBusiness") TblUser tblPureBusiness,
			BindingResult result, HttpServletRequest request,
			HttpSession session) throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		try {
			tblPureBusiness.setUserLevel(WanmaConstants.USER_LEVEL_BUSINESS);
			tblUserService.updateUser(tblPureBusiness);
			String roleIds = "";
			String roleIdArr[] = request.getParameterValues("roleId");
			for (String roleId : roleIdArr) {
				roleIds += roleId + ",";
			}
			roleIds = StringUtils.removeEnd(roleIds, ",");
			roleService.updateUserRoleRelation(tblPureBusiness.getUserId(),
					roleIds);
			// 设置成功并返回纯商家一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "ShopList",
					"closeCurrent", "");
			// 如果更新纯商家处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误",
					"businessEditPage", "", "");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 显示详细纯商家信息
	 * 
	 * @author xiay
	 * @param businessId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewBusiness", method = RequestMethod.GET)
	public String viewBusiness(
			@ModelAttribute("TblPureBusiness") TblUser businessUser, Model model) {
		// 取得查看对象纯商家信息
		TblUser tblPureBusiness = null;
		try {
			tblPureBusiness = tblUserService.findUser(businessUser);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		TblCompany company = companyManagerService
				.getCompanyById(JudgeNullUtils.nvlInteger(tblPureBusiness
						.getBusiCompanyId()));
		List<RoleModel> selectRoleList = roleService
				.getRoleListByUserId(tblPureBusiness.getUserId());
		model.addAttribute("selectRoleList", selectRoleList);
		// 将纯商家信息设置到画面显示对象
		model.addAttribute("tblPureBusiness", tblPureBusiness);
		model.addAttribute("company", company);
		// 跳转至纯商家查看页面
		return "backstage/Business/viewBusiness";
	}

	/**
	 * 删除纯商家
	 * 
	 * @author xiay
	 * @param businessId
	 * @param model
	 * @return
	 */
	@RequestMapping("/removeBusiness")
	@ResponseBody
	public String removeBusiness(@RequestParam("pkUserinfos") String pkUserinfos) {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		TblUser tblPureBusiness = new TblUser();
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
				dwzResult = new DwzAjaxResult("300", "纯商家下有电桩、子商家等数据:"
						+ StringUtils.removeEnd(errorCode, ","), "ShopList",
						"", "");
				return dwzResult.toJSONString();
			}
			for (String id : ids) {
				tblPureBusiness.setUserId(Long.valueOf(id));
				// 执行删除处理
				tblPureBusiness.setUserStatus(3);
				tblUserService.deleteUser(tblPureBusiness);
				roleService.updateUserRoleRelation(tblPureBusiness.getUserId(),
						null);
			}
			// 设置处理结果信息
			dwzResult = new DwzAjaxResult("200", "删除成功", "ShopList", "", "");

		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());

			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败:系统错误", "ShopList", "",
					"");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
}
