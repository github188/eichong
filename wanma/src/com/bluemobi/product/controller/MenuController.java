/**
 * FileName:MenuController.java
 * Author: Administrator
 * Create: 2014年7月4日
 * Last Modified: 2014年7月4日
 * Version: V1.0 
 */
package com.bluemobi.product.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.model.MenuModel;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
//import com.bluemobi.product.service.CompanyPostService;
//import com.bluemobi.product.service.DepartmentService;
import com.bluemobi.product.service.MenuService;
import com.bluemobi.product.service.RoleService;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.web.support.utils.UUIDUtil;

/**
 * 菜单相关处理控制器
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月4日
 */

/**
 * @author bc
 *
 */
/**
 * @author bc
 *
 */
/**
 * @author bc
 *
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuController extends BaseController {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(MenuController.class);

	/** 菜单业务处理对象 */
	@Autowired
	private MenuService menuService;

	/** 角色业务处理对象 */
	@Autowired
	private RoleService roleService;

	/**
	 * 菜单列表
	 */
	@RequestMapping(value = "/findMenuList")
	public String findMenuList(MenuModel menu,
			@ModelAttribute("pager") DwzPagerMySQL pager, Model model,
			HttpServletRequest request) {
		if (!checkOprateValid(request)) {
			return WanmaConstants.ERROR_PAGE;
		}
		String parentMenuId = request.getParameter("org.id");
		String parentMenuName = request.getParameter("org.name");
		if (StringUtils.isNotBlank(parentMenuId)) {
			menu.setParentMenuId(parentMenuId);
			menu.setParentMenuName(parentMenuName);
		}
		List<MenuModel> menuList = null;
		Long total = menuService.getMenuListCount(menu);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置分页对象
		menu.setPager(pager);
		menuList = menuService.getMenuList(menu);
		pager.setTotal(total);
		model.addAttribute("menuList", menuList);
		model.addAttribute("menu", menu);
		model.addAttribute("pager", pager);
		return "admin/menu/listMenu";
	}

	/**
	 * 菜单添加初始化处理
	 * 
	 */
	@RequestMapping("/newMenu")
	public String newMenu(Model model, HttpServletRequest request) {
		if (!checkOprateValid(request)) {
			return WanmaConstants.ERROR_PAGE;
		} else {
			MenuModel menu = new MenuModel();
			menu.setMenuId(UUIDUtil.uuid());
			model.addAttribute("menu", menu);
			return "admin/menu/addMenu";
		}
	}

	/**
	 * 菜单添加处理
	 */
	@RequestMapping("/saveMenu")
	@ResponseBody
	public String saveMenu(MenuModel menu, BindingResult result,
			HttpServletRequest request, Model model) {
		DwzAjaxResult dwzResult;
		if (!checkOprateValid(request)) {
			dwzResult = new DwzAjaxResult("300", "操作权限不足", "menu", "", "");
		} else {
			if (result.hasErrors()) {
				dwzResult = new DwzAjaxResult("300", "参数错误", "menuAddPage", "",
						"");
				return new JsonObject(dwzResult).toString();
			}
			ProcessInfoCommon.setCreateUserInfo(menu);
			try {
				String parentMenuId = request.getParameter("org.id");
				menu.setParentMenuId(parentMenuId);
				menuService.addMenu(menu);
				dwzResult = new DwzAjaxResult("200", "保存成功", "menu",
						"closeCurrent", "");
				// 刷新会话中角色和菜单的缓存
				roleService.initRolesAndMenus(request);
			} catch (Exception e) {
				dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "menu", "",
						"");
				log.error(e.getLocalizedMessage());
			}
		}
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 菜单编辑初始化处理
	 */
	@RequestMapping(value = "/editMenu", method = RequestMethod.GET)
	public String editMenu(@RequestParam("menuId") String menuId, Model model,
			HttpServletRequest request) {
		if (!checkOprateValid(request)) {
			return WanmaConstants.ERROR_PAGE;
		} else {
			MenuModel menu = menuService.getMenu(menuId);
			if (StringUtils.isBlank(menu.getParentMenuId())) {
				menu.setParentMenuId("");
				menu.setParentMenuName("根菜单");
			}
			model.addAttribute("menu", menu);
			// 跳转至菜单编辑页面
			return "admin/menu/editMenu";
		}

	}

	/**
	 * 菜单编辑处理
	 */
	@RequestMapping(value = "/modifyMenu", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String modifyMenu(@ModelAttribute("menuModel") MenuModel menuModel,
			BindingResult result, Model model, HttpServletRequest request)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		if (!checkOprateValid(request)) {
			dwzResult = new DwzAjaxResult("300", "操作权限不足", "role", "", "");
		} else {
			if (result.hasErrors()) {
				dwzResult = new DwzAjaxResult("300", "参数错误", "menuM", "", "");
				return new JsonObject(dwzResult).toString();
			}
			ProcessInfoCommon.setUpdateUserInfo(menuModel, request);
			try {
				String parentMenuId = request.getParameter("org.id");
				menuModel.setParentMenuId(parentMenuId);
				menuService.modifyMenu(menuModel);
				dwzResult = new DwzAjaxResult("200", "保存成功", "menu",
						"closeCurrent", "");
				// 刷新会话中角色和菜单的缓存
				roleService.initRolesAndMenus(request);
			} catch (Exception e) {
				dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "menu", "",
						"");
				log.error(e.getLocalizedMessage());
			}
		}
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 菜单删除处理
	 */
	@RequestMapping(value = "/removeMenu")
	@ResponseBody
	public String removeMenu(@RequestParam("ids") String ids,
			HttpServletRequest request) {
		DwzAjaxResult dwzResult = null;
		if (!checkOprateValid(request)) {
			dwzResult = new DwzAjaxResult("300", "操作权限不足", "menu", "", "");
		} else {
			try {
				String[] idArray = ids.split(",");
				MenuModel tempMenu = null;
				String errorCode = "";
				for (String id : idArray) {
					tempMenu = new MenuModel();
					tempMenu.setParentMenuId(id);
					List<MenuModel> menuList = menuService
							.getMenuList(tempMenu);
					if (menuList != null && menuList.size() > 0) {
						errorCode += id + ",";
					}
				}
				if (StringUtils.isNotBlank(errorCode)) {
					dwzResult = new DwzAjaxResult("300", "菜单下有其他菜单/按钮权限:"
							+ StringUtils.removeEnd(errorCode, ","), "menu",
							"", "");
					return new JsonObject(dwzResult).toString();
				}
				for (String id : idArray) {
					menuService.removeMenu(id);
					dwzResult = new DwzAjaxResult("200", "删除成功", "menu", "", "");
					// 刷新会话中角色和菜单的缓存
				}
				roleService.initRolesAndMenus(request);
			} catch (Exception e) {
				dwzResult = new DwzAjaxResult("300", "删除失败:系统错误", "menu", "",
						"");
				log.error(e.getLocalizedMessage());
			}
		}
		return new JsonObject(dwzResult).toString();
	}

	@RequestMapping(value = "/getMenuTreeLookup")
	public String getMenuTreeLookup(ModelMap map, HttpServletRequest request) {
		List<MenuModel> menuList = SessionMgr.getUserMenus(request);
		String strHtml = menuService.getMenuTree(menuList, true);
		map.put("strHtml", strHtml);
		// 跳转至菜单添加页面
		return "admin/menu/menuTreeLookup";
	}

	@RequestMapping("initMenus")
	@ResponseBody
	public String initMenus() {
		MenuModel menu = new MenuModel();
		String[] arr = null;
		List<MenuModel> menuList = menuService.getMenuList();
		menu = getMenu(menuList, "角色管理");
		arr = new String[] { menu.getMenuId(), "角色新增", "1",
				"/admin/role/newRole.do", "roleOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "角色编辑", "2",
				"/admin/role/editRole.do", "roleOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "角色删除", "3",
				"/admin/role/removeRole.do", "roleOperate" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "公司管理");
		arr = new String[] { menu.getMenuId(), "公司新增", "1",
				"/admin/companyManager/addCompanyUi.do", "companyOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "公司编辑", "2",
				"/admin/companyManager/changeCompanyUI.do", "companyOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "公司删除", "3",
				"/admin/companyManager/removeCompany.do", "companyOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "公司查看", "4",
				"/admin/companyManager/showCompanyUI.do", "companyOperate" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "菜单管理");
		arr = new String[] { menu.getMenuId(), "菜单新增", "1",
				"/admin/menu/newMenu.do", "menuOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "菜单编辑", "2",
				"/admin/menu/editMenu.do", "menuOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "菜单删除", "3",
				"/admin/menu/removeMenu.do", "menuOperate" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "普通用户列表");
		arr = new String[] { menu.getMenuId(), "普通用户新增", "1",
				"/admin/userManager/newUser.do", "userNormalOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "普通用户编辑", "2",
				"/admin/userManager/editUser.do", "userNormalOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "普通用户删除", "3",
				"/admin/userManager/removeUser.do", "userNormalOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "普通用户批量删除", "4",
				"/admin/userManager/removeAll.do", "userNormalOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "普通用户升级个体商家", "5",
				"/admin/userManager/upgradeUser.do", "userNormalOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "普通用户审批/批量审批", "6",
				"/admin/userManager/approvalAllList.do", "userNormalOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "普通用户冻结/批量冻结", "7",
				"/admin/userManager/frostAllList.do", "userNormalOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "普通用户解冻/批量解冻", "8",
				"/admin/userManager/unFrost.do", "userNormalOperate" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "纯商家用户列表");
		arr = new String[] { menu.getMenuId(), "纯商家用户新增", "1",
				"/admin/userManager/newBusiness.do", "userBusinessOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "纯商家用户编辑", "2",
				"/admin/userManager/editBusiness.do", "userBusinessOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "纯商家用户删除", "3",
				"/admin/userManager/removeBusiness.do", "userBusinessOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "纯商家用户查看", "4",
				"/admin/userManager/viewBusiness.do", "userBusinessOperate" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "个体商家用户列表");
		arr = new String[] { menu.getMenuId(), "个体商家用户新增", "1",
				"/admin/userManager/newUnit.do", "userUnitOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "个体商家用户编辑", "2",
				"/admin/userManager/editUnit.do", "userUnitOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "个体商家用户删除", "3",
				"/admin/userManager/removeUnit.do", "userUnitOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "个体商家用户查看", "4",
				"/admin/userManager/viewUnit.do", "userUnitOperate" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "管理员列表");
		arr = new String[] { menu.getMenuId(), "管理员新增", "1",
				"/admin/user/newUser.do", "userAdminOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "管理员编辑", "2",
				"/admin/user/editUser.do", "userAdminOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "管理员删除", "3",
				"/admin/user/removeUser.do", "userAdminOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "管理员查看", "4",
				"/admin/user/viewUser.do", "userAdminOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "管理员密码重置", "5",
				"/admin/user/resetPassword.do", "userAdminOperate" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "电动车品牌设置");
		arr = new String[] { menu.getMenuId(), "电动车品牌新增", "1",
				"/admin/carCompany/newCarCompany.do", "carCompanyOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "电动车品牌编辑", "2",
				"/admin/carCompany/editCarCompany.do", "carCompanyOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "电动车品牌删除", "3",
				"/admin/carCompany/deleteById.do", "carCompanyOperate" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "电动车车型设置");
		arr = new String[] { menu.getMenuId(), "电动车车型新增", "1",
				"/admin/carinfo/newCarinfo.do", "carinfoOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "电动车车型编辑", "2",
				"/admin/carinfo/editCarinfo.do", "carinfoOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "电动车车型删除", "3",
				"/admin/carinfo/deleteCarinfos.do", "carinfoOperate" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "省份电费设置");
		arr = new String[] { menu.getMenuId(), "省份电费设置电费编辑", "1",
				"/admin/electricCharge/editElectricCharge.do", "electricCharge" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "服务费率上限维护");
		arr = new String[] { menu.getMenuId(), "服务费率上限维护费率编辑", "1",
				"/admin/feelimit/editServiceLimit.do", "feeOperate" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "电桩制造厂商");
		arr = new String[] { menu.getMenuId(), "电桩制造厂商新增", "1",
				"/admin/carmaker/newCarmaker.do", "carmakerOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "电桩制造厂商编辑", "2",
				"/admin/carmaker/editCarMaker.do", "carmakerOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "电桩制造厂商删除", "3",
				"/admin/carmaker/deleteCarMakers.do", "carmakerOperate" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "费率设置");
		arr = new String[] { menu.getMenuId(), "费率设置新增", "1",
				"/admin/rateinfo/addRateInfoUi.do", "rateinfoOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "费率设置编辑", "2",
				"/admin/rateinfo/changeRateInfoUi.do", "rateinfoOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "费率设置删除", "3",
				"/admin/rateinfo/removeRateInfo.do", "rateinfoOperate" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "商城配置参数设置");
		arr = new String[] { menu.getMenuId(), "配置参数新增", "1",
				"/admin/configParameter/newConPara.do", "conParaOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "配置参数编辑", "2",
				"/admin/configParameter/editConPara.do", "conParaOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "配置参数删除", "3",
				"/admin/configParameter/deleteById.do", "conParaOperate" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "所属Gate服务器");
		arr = new String[] { menu.getMenuId(), "所属Gate服务器新增", "1",
				"/admin/gate/addGateUi.do", "gateOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "所属Gate服务器删除", "3",
				"/admin/gate/removeGate.do", "gateOperate" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "电桩管理列表");
		arr = new String[] { menu.getMenuId(), "电桩管理列表新增", "1",
				"/admin/electric/addElectricPileUi.do", "electricOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "电桩管理列表修改", "2",
				"/admin/electric/changeElectricPileUi.do", "electricOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "电桩管理列表删除", "3",
				"/admin/electric/removeElectricPile.do", "electricOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "电桩管理列表查看", "4",
				"/admin/electric/showElectricPile.do", "electricOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "电桩管理列表操作申请", "5",
				"/admin/electric/changeElectricPileState.do", "electricOperate" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "电桩待审批列表");
		arr = new String[] { menu.getMenuId(), "电桩待审批列表驳回", "1",
				"/admin/electric/examineReasonUi.do", "elecExamineOperate" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "充电点管理列表");
		arr = new String[] { menu.getMenuId(), "充电点管理列表新增", "1",
				"/admin/powerstation/addPowersUi.do", "psOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "充电点管理列表修改", "2",
				"/admin/powerstation/changePowersUi.do", "psOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "充电点管理列表删除", "3",
				"/admin/powerstation/removePower.do", "psOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "充电点管理列表查看", "4",
				"/admin/powerstation/showPowers.do", "psOperate" };
		saveNewMenu(arr);
		arr = new String[] { menu.getMenuId(), "充电点管理列表操作申请", "5",
				"/admin/powerstation/changePowersState.do", "psOperate" };
		saveNewMenu(arr);

		menu = getMenu(menuList, "充电点待审批列表");
		arr = new String[] { menu.getMenuId(), "充电点待审批列表驳回", "1",
				"/admin/powerstation/examineReasonUi.do", "psOperate" };
		saveNewMenu(arr);

		return "OK";

	}

	private MenuModel getMenu(List<MenuModel> menuList, String menuName) {
		for (MenuModel menu : menuList) {
			if (menu.getContents().indexOf(menuName) >= 0) {
				return menu;
			}
		}
		return null;
	}

	private void saveNewMenu(String[] arr) {
		MenuModel newMenu = new MenuModel();
		newMenu.setParentMenuId(arr[0]);
		newMenu.setMenuId(UUIDUtil.uuid());
		newMenu.setMenuType("2");
		newMenu.setContents(arr[1]);
		newMenu.setSortNumber(new Long(arr[2]));
		newMenu.setUrl(arr[3]);
		newMenu.setRel(arr[4]);
		newMenu.setCreateUser("1");
		newMenu.setCreateDate(new Date());
		newMenu.setLastUpdateUser("1");
		newMenu.setLastUpdateDate(new Date());
		menuService.addMenu(newMenu);
	}
}
