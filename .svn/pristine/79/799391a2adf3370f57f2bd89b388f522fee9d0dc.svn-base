/**
 * FileName:RoleController.java
 * Author: Administrator
 * Create: 2014年7月4日
 * Last Modified: 2014年7月4日
 * Version: V1.0 
 */
package com.bluemobi.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.bluemobi.product.model.RoleModel;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.service.MenuService;
import com.bluemobi.product.service.RoleService;
import com.bluemobi.product.service.TblUserService;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblUser;

/**
 * 角色相关处理控制器
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月4日
 */

@Controller
@RequestMapping("/admin/role")
public class RoleController {
		
	// 日志输出对象
	private static Logger log = Logger.getLogger(RoleController.class);
	/** 角色业务处理对象 */
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
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
	@RequestMapping(value = "/findRoleList")
	public String findRoleList(@ModelAttribute("pager") DwzPagerMySQL pager,
			RoleModel roleModel, Model model,HttpServletRequest request) {
		// 登录角色信息
		List<RoleModel> roleList = null;
		// 取得角色列表
		TblUser user=SessionMgr.getWebUser(request);
		long total = roleService.getParentRoleListCountByCurrentUser(user);
		if(total<=pager.getOffset()){
			pager.setPageNum(1L);
		}
		
		user.setPager(pager);
		roleList = roleService.getParentRoleListByCurrentUser(user);
		pager.setTotal(total);
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
	public String newRole(ModelMap map, HttpServletRequest request) {
		if(!checkCsjRole(request)){
			return WanmaConstants.ERROR_PAGE;
		}
		String roleId = "";
		RoleModel roleModel = new RoleModel();
		roleId = ProcessInfoCommon.getRandomKey(10);
		roleModel.setRoleId(roleId);
		List<MenuModel> menuList=SessionMgr.getUserMenus(request);
		String menuIds="";
		for(MenuModel menu:menuList){
			menuIds+=menu.getMenuId()+",";
		}
		String menuData = menuService.getMenuListByRoleIdAndMenuIds(roleId,StringUtils.removeEnd(menuIds, ","));
		// 将角色信息设置到画面显示对象
		map.put("role", roleModel);
		map.put("menuData", menuData);
		// 跳转至角色添加页面
		return "admin/role/addRole";
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
	@RequestMapping(value = "/saveRole")
	@ResponseBody
	public String saveRole(@ModelAttribute("roleModel") RoleModel roleModel,
			BindingResult result, HttpServletRequest request, Model model) throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		if(!checkCsjRole(request)){
			dwzResult = new DwzAjaxResult("300", "保存失败:用户不能执行该操作", "role", "", "");
		}else{
			try {
				String menuIds=request.getParameter("menuIds");
				// 设置更新者用户信息
				ProcessInfoCommon.setCreateUserInfo(roleModel, request);
				roleService.saveRole(roleModel, menuIds);
				roleService.initRolesAndMenus(request);
				dwzResult = new DwzAjaxResult("200", "保存成功", "role", "closeCurrent", "");
			} catch (Exception e) {
				log.error(e.getLocalizedMessage());
				dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "role", "", "");
			}
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
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
	public String editRole(@RequestParam("roleId") String roleId, Model model,
			HttpServletRequest request, HttpSession session) {
		RoleModel role = roleService.findRole(roleId);
		if(!checkCsjRole(request)){
			return WanmaConstants.ERROR_PAGE;
		}else{
			List<MenuModel> menuList=SessionMgr.getUserMenus(request);
			String menuIds="";
			for(MenuModel menu:menuList){
				menuIds+=menu.getMenuId()+",";
			}
			String menuData = menuService.getMenuListByRoleIdAndMenuIds(roleId,StringUtils.removeEnd(menuIds, ","));
			model.addAttribute("role", role);
			model.addAttribute("menuData", menuData);
			// 跳转至角色编辑页面
			return "admin/role/editRole";
		}
		
	}

	/**
	 * 角色编辑处理
	 * 
	 */
	@RequestMapping(value = "/modifyRole")
	@ResponseBody
	public String modifyRole(@ModelAttribute("roleModel") RoleModel roleModel,
			Model model, HttpServletRequest request) throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		if(!checkCsjRole(request)){
			dwzResult = new DwzAjaxResult("300", "保存失败:用户不能执行该操作", "role", "", "");
		}else{
			try {
				String menuIds=request.getParameter("menuIds");
				// 设置更新者用户信息
				ProcessInfoCommon.setUpdateUserInfo(roleModel, request);
				roleService.modifyRole(roleModel, menuIds);
				dwzResult = new DwzAjaxResult("200", "保存成功", "role", "closeCurrent", "");
				//刷新会话中角色和菜单的缓存
				roleService.initRolesAndMenus(request);
			} catch (Exception e) {
				log.error(e.getLocalizedMessage());
				dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "role", "", "");
			}
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
	 * @return String 处理结果信息
	 * @throws 无
	 */
	@RequestMapping(value = "/removeRole")
	@ResponseBody
	public String removeRole(@ModelAttribute("roleModel") RoleModel roleModel,HttpServletRequest request) {
		DwzAjaxResult dwzResult;
		roleModel = roleService.findRole(roleModel.getRoleId());
		if(!checkCsjRole(request)){
			dwzResult = new DwzAjaxResult("300", "操作权限不足", "role", "", "");
		}else{
			String roleId = roleModel.getRoleId();
			List<TblUser> roleUserList=tblUserService.getRoleUserList(roleId);
			if(roleUserList!=null&&roleUserList.size()>0){
				dwzResult = new DwzAjaxResult("300", "此角色下已有用户，请先删除用户", "role", "", "");
			}else{
				// 执行删除处理
				roleService.removeRole(roleId);
				// 设置处理结果信息
				dwzResult = new DwzAjaxResult("200", "删除成功", "role", "", "");
				//刷新会话中角色和菜单的缓存
				roleService.initRolesAndMenus(request);
			}
			
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: checkRoleValid 
	 * @Description: 检验用户是否是纯商家
	 * @author wbc	
	 * 2015年11月4日 	
	 * @return: boolean 
	 */
	private boolean checkCsjRole(HttpServletRequest request) {
		int userlevel=SessionMgr.getWebUser(request).getUserLevel();
		List<RoleModel> roleList=SessionMgr.getUserRoles(request);
		RoleModel role=new RoleModel();
		if(roleList!=null&roleList.size()>0){
			role=roleList.get(0);
		}else{
			role.setCategory("0");
		}
		boolean flag=true;
		if(userlevel==3&&role.getCategory().equals("4")){
			flag=false;
		}else if(userlevel==5){
			flag=false;
		}
		return flag;
	}
	
	/**
	 * 唯一性检查
	 */
	@RequestMapping(value = "/checkUnique")
	@ResponseBody
	public String checkUnique(HttpServletRequest request) {
		String roleName=request.getParameter("roleName");
		String roleId=request.getParameter("roleId");
		TblUser user=SessionMgr.getWebUser(request);
		List<RoleModel> roleList = roleService.getParentRoleListByCurrentUser(user);
		for(RoleModel role:roleList){
			if(role.getRoleName().equals(roleName)){
				if(StringUtils.isNotBlank(roleId)){
					if(!role.getRoleId().equals(roleId)){
						return "false";
					}
				}else{
					return "false";
				}
			}
		}
		// 返回处理结果信息
		return "true";
	}
	
	
}
