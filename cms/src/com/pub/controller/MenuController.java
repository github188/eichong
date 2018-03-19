package com.pub.controller;

import java.io.IOException;
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

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.BaseSuccess;
import com.base.common.ProcessInfoCommon;
import com.base.common.SessionMgr;
import com.base.util.UUIDUtil;
import com.pub.model.MenuModel;
import com.pub.model.Node;
import com.pub.model.Pager;
import com.pub.service.MenuService;
import com.pub.service.RoleService;

/**
 * 菜单相关处理控制器
 * 
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuController extends BaseController {

	private static Logger log = Logger.getLogger(MenuController.class);
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;

	/**
	 * 菜单列表
	 */
	@RequestMapping(value = "/findMenuList")
	public String findMenuList(MenuModel menu,
			@ModelAttribute("pager") Pager pager, Model model,
			HttpServletRequest request) {
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
		MenuModel menu = new MenuModel();
		menu.setMenuId(UUIDUtil.uuid());
		model.addAttribute("menu", menu);
		return "admin/menu/addMenu";
	}

	/**
	 * 菜单添加处理
	 */
	@RequestMapping("/saveMenu")
	@ResponseBody
	public String saveMenu(MenuModel menu, BindingResult result,
			HttpServletRequest request, Model model) {
		BaseResult baseResult = new BaseSuccess();
		if (result.hasErrors()) {
			return new BaseFail(5000).toString();
		}
		ProcessInfoCommon.setCreateUserInfo(menu);
		try {
			String parentMenuId = request.getParameter("org.id");
			menu.setParentMenuId(parentMenuId);
			menuService.addMenu(menu);
			// 刷新会话中角色和菜单的缓存
			roleService.initRolesAndMenus(request);
		} catch (Exception e) {
			log.error(this.getClass() + ".saveMenu() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

	/**
	 * 菜单编辑初始化处理
	 */
	@RequestMapping(value = "/editMenu", method = RequestMethod.GET)
	public String editMenu(@RequestParam("menuId") String menuId, Model model,
			HttpServletRequest request) {
		MenuModel menu = menuService.getMenu(menuId);
		if (StringUtils.isBlank(menu.getParentMenuId())) {
			menu.setParentMenuId("");
			menu.setParentMenuName("根菜单");
		}
		model.addAttribute("menu", menu);
		// 跳转至菜单编辑页面
		return "admin/menu/editMenu";

	}

	/**
	 * 菜单编辑处理
	 */
	@RequestMapping(value = "/modifyMenu", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String modifyMenu(@ModelAttribute("menuModel") MenuModel menuModel,
			BindingResult result, Model model, HttpServletRequest request)
			throws IOException {
		BaseResult baseResult = new BaseSuccess();
		if (result.hasErrors()) {
			return new BaseFail(5000).toString();
		}
		ProcessInfoCommon.setUpdateUserInfo(menuModel, request);
		try {
			String parentMenuId = request.getParameter("org.id");
			menuModel.setParentMenuId(parentMenuId);
			menuService.modifyMenu(menuModel);
			// 刷新会话中角色和菜单的缓存
			roleService.initRolesAndMenus(request);
		} catch (Exception e) {
			log.error(this.getClass() + ".modifyMenu() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

	/**
	 * 菜单删除处理
	 */
	@RequestMapping(value = "/removeMenu")
	@ResponseBody
	public String removeMenu(@RequestParam("ids") String ids,
			HttpServletRequest request) {
		BaseResult baseResult = new BaseSuccess();
		try {
			String[] idArray = ids.split(",");
			MenuModel tempMenu = null;
			String errorCode = "";
			for (String id : idArray) {
				tempMenu = new MenuModel();
				tempMenu.setParentMenuId(id);
				List<MenuModel> menuList = menuService.getMenuList(tempMenu);
				if (menuList != null && menuList.size() > 0) {
					errorCode += id + ",";
				}
			}
			if (StringUtils.isNotBlank(errorCode)) {
				String msg = "菜单下有其他菜单/按钮权限:"
						+ StringUtils.removeEnd(errorCode, ",");
				return new BaseFail(msg).toString();
			}
			for (String id : idArray) {
				menuService.removeMenu(id);
			}
			roleService.initRolesAndMenus(request);
		} catch (Exception e) {
			log.error(this.getClass() + ".removeMenu() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

	@RequestMapping(value = "/getMenuTreeLookup")
	public String getMenuTreeLookup(ModelMap map, HttpServletRequest request) {
		List<MenuModel> menuList = SessionMgr.getUserMenus(request);
		String strHtml = menuService.getMenuTree(menuList, true);
		map.put("strHtml", strHtml);
		// 跳转至菜单添加页面
		return "admin/menu/menuTreeLookup";
	}

	@RequestMapping("/getChildrenMenu")
	@ResponseBody
	public String getChildrenMenu(HttpServletRequest request, Model model) {
		BaseResult baseResult = new BaseSuccess();
		try {
			String menuId = request.getParameter("menuId");
			MenuModel menu = menuService.getMenu(menuId);
			Node node=menuService.getChildrenMenu(request,menu);
			baseResult=new BaseResult(node);
		} catch (Exception e) {
			log.error(this.getClass() + ".getChildrenMenu() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

}
