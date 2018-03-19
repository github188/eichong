/**
 * FileName:MenuUtil.java
 * Author: Administrator
 * Create: 2014年7月10日
 * Last Modified: 2014年7月10日
 * Version: V1.0 
 */
package com.bluemobi.product.common;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.util.StringUtils;
import com.bluemobi.product.model.CompanyPostModel;
import com.bluemobi.product.model.DepartmentModel;
import com.bluemobi.product.model.MenuDepartmentModel;
import com.bluemobi.product.model.MenuModel;
import com.bluemobi.product.model.MenuPostModel;
import com.bluemobi.product.model.MenuRoleModel;
import com.bluemobi.product.model.RoleModel;
import com.bluemobi.product.utils.ObjectUtil;
import com.bluemobi.product.utils.StringUtil;

/**
 * 数据合并工具类
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月10日
 */
public class DataMergeCommon {

	/**
	 * 判断部门列表中是否已存在部门信息
	 * 
	 * @param targetList
	 *            对象部门列表
	 * @param departmentModel
	 *            对象部门
	 * @return 存在标识
	 */
	public static boolean containsDepartment(List<DepartmentModel> targetList,
			DepartmentModel targetDepartmentModel) {

		// 公司ID
		String targetCompanyId = "";
		// 部门ID
		String targetDepartmentId = "";

		//
		// 对象部门为空，返回不存在标识
		//
		if (targetDepartmentModel == null) {
			// 返回不存在标识
			return false;
		}

		// 取得公司ID
		targetCompanyId = targetDepartmentModel.getDepartmentId();
		// 取得部门ID
		targetDepartmentId = targetDepartmentModel.getDepartmentId();

		// 返回存在标识
		return containsDepartment(targetList, targetCompanyId,
				targetDepartmentId);

	}

	/**
	 * 判断部门列表中是否已存在部门信息
	 * 
	 * @param targetList
	 *            对象部门列表
	 * @param targetCompanyId
	 *            对象公司Id
	 * @param targetDepartmentId
	 *            对象部门Id
	 * @return 存在标识
	 */
	public static boolean containsDepartment(List<DepartmentModel> targetList,
			String targetCompanyId, String targetDepartmentId) {
		// 存在标识
		boolean containDepartment = false;

		//
		// 部门列表或者对象部门为空，返回不存在标识
		//
		if (targetList == null || targetList.size() < 1
				|| StringUtil.isEmpty(targetDepartmentId)) {
			// 返回不存在标识
			return false;
		}

		for (DepartmentModel departmentModel : targetList) {
			// 取得公司ID
			String companyId = departmentModel.getDepartmentId();
			// 取得部门ID
			String departmentId = departmentModel.getDepartmentId();

			if (StringUtils.equals(targetCompanyId, companyId)
					&& StringUtils.equals(targetDepartmentId, departmentId)) {
				containDepartment = true;
				break;
			}

		}

		// 返回存在标识
		return containDepartment;

	}

	/**
	 * 判断角色列表中是否已存在角色信息
	 * 
	 * @param targetList
	 *            对象角色列表
	 * @param targetRoleModel
	 *            对象角色
	 * @return 存在标识
	 */
	public static boolean containsRole(List<RoleModel> targetList,
			RoleModel targetRoleModel) {

		// 角色ID
		String targetRoleId = "";

		//
		// 对象角色为空，返回不存在标识
		//
		if (targetRoleModel == null) {
			// 返回不存在标识
			return false;
		}

		// 取得角色ID
		targetRoleId = targetRoleModel.getRoleId();

		// 返回存在标识
		return containsRole(targetList, targetRoleId);

	}

	/**
	 * 判断角色列表中是否已存在角色信息
	 * 
	 * @param targetList
	 *            对象角色列表
	 * @param targetRoleId
	 *            对象角色Id
	 * @return 存在标识
	 */
	public static boolean containsRole(List<RoleModel> targetList,
			String targetRoleId) {
		// 存在标识
		boolean containRole = false;

		//
		// 角色列表或者对象角色为空，返回不存在标识
		//
		if (targetList == null || targetList.size() < 1
				|| StringUtil.isEmpty(targetRoleId)) {
			// 返回不存在标识
			return false;
		}

		for (RoleModel roleModel : targetList) {
			// 取得角色ID
			String roleId = roleModel.getRoleId();

			if (StringUtils.equals(targetRoleId, roleId)) {
				containRole = true;
				break;
			}

		}

		// 返回存在标识
		return containRole;

	}

	/**
	 * 判断职位列表中是否已存在职位信息
	 * 
	 * @param targetList
	 *            对象职位列表
	 * @param targetPostModel
	 *            对象职位
	 * @return 存在标识
	 */
	public static boolean containsPost(List<CompanyPostModel> targetList,
			CompanyPostModel targetPostModel) {

		// 职位ID
		String targetPostId = "";
		// 取得职位公司
		String targetCompanyId = "";

		//
		// 对象职位为空，返回不存在标识
		//
		if (targetPostModel == null) {
			// 返回不存在标识
			return false;
		}

		// 取得职位ID
		targetPostId = targetPostModel.getPostId();
		// 取得职位公司
		targetCompanyId = targetPostModel.getCompanyId();

		// 返回存在标识
		return containsPost(targetList, targetCompanyId, targetPostId);

	}

	/**
	 * 判断职位列表中是否已存在职位信息
	 * 
	 * @param targetList
	 *            对象职位列表
	 * @param targetPostId
	 *            对象职位Id
	 * @return 存在标识
	 */
	public static boolean containsPost(List<CompanyPostModel> targetList,
			String targetCompanyId, String targetPostId) {
		// 存在标识
		boolean containPost = false;

		//
		// 职位列表或者对象职位为空，返回不存在标识
		//
		if (targetList == null || targetList.size() < 1
				|| StringUtil.isEmpty(targetPostId)) {
			// 返回不存在标识
			return false;
		}

		for (CompanyPostModel postModel : targetList) {
			// 取得职位ID
			String companyId = postModel.getCompanyId();
			// 取得职位公司
			String postId = postModel.getPostId();

			if (StringUtils.equals(targetCompanyId, companyId)
					&& StringUtils.equals(targetPostId, postId)) {
				containPost = true;
				break;
			}

		}

		// 返回存在标识
		return containPost;

	}

	/**
	 * 合并处理对象角色列表
	 * 
	 * @param addRoleList
	 *            追加对象角色列表
	 * @param deleteRoleList
	 *            删除对象角色列表
	 * @return List<RoleModel> 合并处理对象角色列表
	 */
	public static List<MenuRoleModel> mergeMenuRole(
			List<RoleModel> addRoleList, List<RoleModel> deleteRoleList,
			MenuModel menuModel) {
		// 菜单ID
		String menuId = "";

		// 处理对象角色列表
		List<MenuRoleModel> processRole = new ArrayList<MenuRoleModel>();

		if ((addRoleList == null || addRoleList.size() == 0)
				&& (deleteRoleList == null || deleteRoleList.size() == 0)
				|| ObjectUtil.isEmpty(menuModel)) {
			return null;
		}

		// 取得菜单ID
		menuId = menuModel.getMenuId();

		//
		// 处理追加对象角色
		//
		if (addRoleList != null && addRoleList.size() > 0) {
			for (RoleModel roleAdd : addRoleList) {

				if (!containsRole(deleteRoleList, roleAdd)) {
					// 角色角色对象
					MenuRoleModel menuRoleModel = new MenuRoleModel();

					// 处理分类：追加
					menuRoleModel.setPrcessFlg(CommonConsts.PROCESS_FLG_ADD);
					// 菜单ID设置
					menuRoleModel.setMenuId(menuId);
					// 角色ID设置
					menuRoleModel.setRoleId(roleAdd.getRoleId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(menuRoleModel);

					// 加入到列表
					processRole.add(menuRoleModel);
				}
			}
		}

		//
		// 处理追加对象角色
		//
		if (deleteRoleList != null && deleteRoleList.size() > 0) {
			for (RoleModel roleDelete : deleteRoleList) {

				if (!containsRole(addRoleList, roleDelete)) {
					// 角色角色对象
					MenuRoleModel menuRoleModel = new MenuRoleModel();

					// 处理分类：删除
					menuRoleModel.setPrcessFlg(CommonConsts.PROCESS_FLG_DELETE);
					// 菜单ID设置
					menuRoleModel.setMenuId(menuId);
					// 角色ID设置
					menuRoleModel.setRoleId(roleDelete.getRoleId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(menuRoleModel);

					// 加入到列表
					processRole.add(menuRoleModel);
				}
			}
		}

		// 返回处理对象角色列表
		return processRole;
	}

	/**
	 * 合并处理对象部门列表
	 * 
	 * @param addDepartmentList
	 *            追加对象部门列表
	 * @param deleteDepartmentList
	 *            删除对象部门列表
	 * @return List<DepartmentModel> 合并处理对象部门列表
	 */
	public static List<MenuDepartmentModel> mergeMenuDepartment(
			List<DepartmentModel> addDepartmentList,
			List<DepartmentModel> deleteDepartmentList, MenuModel menuModel) {
		// 菜单ID
		String menuId = "";

		// 处理对象部门列表
		List<MenuDepartmentModel> processDepartment = new ArrayList<MenuDepartmentModel>();

		if ((addDepartmentList == null || addDepartmentList.size() == 0)
				&& (deleteDepartmentList == null || deleteDepartmentList.size() == 0)
				|| ObjectUtil.isEmpty(menuModel)) {
			return null;
		}

		// 取得菜单ID
		menuId = menuModel.getMenuId();

		//
		// 处理追加对象部门
		//
		if (addDepartmentList != null && addDepartmentList.size() > 0) {
			for (DepartmentModel departmentAdd : addDepartmentList) {

				if (!containsDepartment(deleteDepartmentList, departmentAdd)) {
					// 部门部门对象
					MenuDepartmentModel menuDepartmentModel = new MenuDepartmentModel();

					// 处理分类：追加
					menuDepartmentModel
							.setPrcessFlg(CommonConsts.PROCESS_FLG_ADD);
					// 菜单ID设置
					menuDepartmentModel.setMenuId(menuId);
					// 公司ID设置
					menuDepartmentModel.setCompanyId(departmentAdd
							.getCompanyId());
					// 部门ID设置
					menuDepartmentModel.setDepartmentId(departmentAdd
							.getDepartmentId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(menuDepartmentModel);

					// 加入到列表
					processDepartment.add(menuDepartmentModel);
				}
			}
		}

		//
		// 处理追加对象部门
		//
		if (deleteDepartmentList != null && deleteDepartmentList.size() > 0) {
			for (DepartmentModel departmentDelete : deleteDepartmentList) {

				if (!containsDepartment(addDepartmentList, departmentDelete)) {
					// 部门部门对象
					MenuDepartmentModel menuDepartmentModel = new MenuDepartmentModel();

					// 处理分类：删除
					menuDepartmentModel
							.setPrcessFlg(CommonConsts.PROCESS_FLG_DELETE);
					// 菜单ID设置
					menuDepartmentModel.setMenuId(menuId);
					// 公司ID设置
					menuDepartmentModel.setCompanyId(departmentDelete
							.getCompanyId());
					// 部门ID设置
					menuDepartmentModel.setDepartmentId(departmentDelete
							.getDepartmentId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(menuDepartmentModel);

					// 加入到列表
					processDepartment.add(menuDepartmentModel);
				}
			}
		}

		// 返回处理对象部门列表
		return processDepartment;
	}

	/**
	 * 合并处理对象职位列表
	 * 
	 * @param addPostList
	 *            追加对象职位列表
	 * @param deletePostList
	 *            删除对象职位列表
	 * @return List<CompanyPostModel> 合并处理对象职位列表
	 */
	public static List<MenuPostModel> mergeMenuPost(
			List<CompanyPostModel> addPostList,
			List<CompanyPostModel> deletePostList, MenuModel menuModel) {
		// 菜单ID
		String menuId = "";

		// 处理对象职位列表
		List<MenuPostModel> processPost = new ArrayList<MenuPostModel>();

		if ((addPostList == null || addPostList.size() == 0)
				&& (deletePostList == null || deletePostList.size() == 0)
				|| ObjectUtil.isEmpty(menuModel)) {
			return null;
		}

		// 取得菜单ID
		menuId = menuModel.getMenuId();

		//
		// 处理追加对象职位
		//
		if (addPostList != null && addPostList.size() > 0) {
			for (CompanyPostModel postAdd : addPostList) {

				if (!containsPost(deletePostList, postAdd)) {
					// 职位职位对象
					MenuPostModel menuPostModel = new MenuPostModel();

					// 处理分类：追加
					menuPostModel.setPrcessFlg(CommonConsts.PROCESS_FLG_ADD);
					// 菜单ID设置
					menuPostModel.setMenuId(menuId);
					// 公司ID设置
					menuPostModel.setCompanyId(postAdd.getCompanyId());
					// 职位ID设置
					menuPostModel.setPostId(postAdd.getPostId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(menuPostModel);

					// 加入到列表
					processPost.add(menuPostModel);
				}
			}
		}

		//
		// 处理追加对象职位
		//
		if (deletePostList != null && deletePostList.size() > 0) {
			for (CompanyPostModel postDelete : deletePostList) {

				if (!containsPost(addPostList, postDelete)) {
					// 职位职位对象
					MenuPostModel menuPostModel = new MenuPostModel();

					// 处理分类：删除
					menuPostModel.setPrcessFlg(CommonConsts.PROCESS_FLG_DELETE);
					// 菜单ID设置
					menuPostModel.setMenuId(menuId);
					// 公司ID设置
					menuPostModel.setCompanyId(postDelete.getCompanyId());
					// 职位ID设置
					menuPostModel.setPostId(postDelete.getPostId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(menuPostModel);

					// 加入到列表
					processPost.add(menuPostModel);
				}
			}
		}

		// 返回处理对象职位列表
		return processPost;
	}

	/**
	 * 合并处理对象角色列表
	 * 
	 * @param addRoleList
	 *            追加对象角色列表
	 * @param deleteRoleList
	 *            删除对象角色列表
	 * @return List<RoleModel> 合并处理对象角色列表
	 */
	/*public static List<ActionRoleModel> mergeActionRole(
			List<RoleModel> addRoleList, List<RoleModel> deleteRoleList,
			ActionModel actionModel) {
		// 画面功能ID
		String actionId = "";

		// 处理对象角色列表
		List<ActionRoleModel> processRole = new ArrayList<ActionRoleModel>();

		if ((addRoleList == null || addRoleList.size() == 0)
				&& (deleteRoleList == null || deleteRoleList.size() == 0)
				|| ObjectUtil.isEmpty(actionModel)) {
			return null;
		}

		// 取得画面功能ID
		actionId = actionModel.getActionId();

		//
		// 处理追加对象角色
		//
		if (addRoleList != null && addRoleList.size() > 0) {
			for (RoleModel roleAdd : addRoleList) {

				if (!containsRole(deleteRoleList, roleAdd)) {
					// 角色角色对象
					ActionRoleModel actionRoleModel = new ActionRoleModel();

					// 处理分类：追加
					actionRoleModel.setPrcessFlg(CommonConsts.PROCESS_FLG_ADD);
					// 画面功能ID设置
					actionRoleModel.setActionId(actionId);
					// 角色ID设置
					actionRoleModel.setRoleId(roleAdd.getRoleId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(actionRoleModel);

					// 加入到列表
					processRole.add(actionRoleModel);
				}
			}
		}

		//
		// 处理追加对象角色
		//
		if (deleteRoleList != null && deleteRoleList.size() > 0) {
			for (RoleModel roleDelete : deleteRoleList) {

				if (!containsRole(addRoleList, roleDelete)) {
					// 角色角色对象
					ActionRoleModel actionRoleModel = new ActionRoleModel();

					// 处理分类：删除
					actionRoleModel
							.setPrcessFlg(CommonConsts.PROCESS_FLG_DELETE);
					// 画面功能ID设置
					actionRoleModel.setActionId(actionId);
					// 角色ID设置
					actionRoleModel.setRoleId(roleDelete.getRoleId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(actionRoleModel);

					// 加入到列表
					processRole.add(actionRoleModel);
				}
			}
		}

		// 返回处理对象角色列表
		return processRole;
	}*/

	/**
	 * 合并处理对象部门列表
	 * 
	 * @param addDepartmentList
	 *            追加对象部门列表
	 * @param deleteDepartmentList
	 *            删除对象部门列表
	 * @return List<DepartmentModel> 合并处理对象部门列表
	 */
	/*public static List<ActionDepartmentModel> mergeActionDepartment(
			List<DepartmentModel> addDepartmentList,
			List<DepartmentModel> deleteDepartmentList, ActionModel actionModel) {
		// 画面功能ID
		String actionId = "";

		// 处理对象部门列表
		List<ActionDepartmentModel> processDepartment = new ArrayList<ActionDepartmentModel>();

		if ((addDepartmentList == null || addDepartmentList.size() == 0)
				&& (deleteDepartmentList == null || deleteDepartmentList.size() == 0)
				|| ObjectUtil.isEmpty(actionModel)) {
			return null;
		}

		// 取得画面功能ID
		actionId = actionModel.getActionId();

		//
		// 处理追加对象部门
		//
		if (addDepartmentList != null && addDepartmentList.size() > 0) {
			for (DepartmentModel departmentAdd : addDepartmentList) {

				if (!containsDepartment(deleteDepartmentList, departmentAdd)) {
					// 部门部门对象
					ActionDepartmentModel actionDepartmentModel = new ActionDepartmentModel();

					// 处理分类：追加
					actionDepartmentModel
							.setPrcessFlg(CommonConsts.PROCESS_FLG_ADD);
					// 画面功能ID设置
					actionDepartmentModel.setActionId(actionId);
					// 公司ID设置
					actionDepartmentModel.setCompanyId(departmentAdd
							.getCompanyId());
					// 部门ID设置
					actionDepartmentModel.setDepartmentId(departmentAdd
							.getDepartmentId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(actionDepartmentModel);

					// 加入到列表
					processDepartment.add(actionDepartmentModel);
				}
			}
		}

		//
		// 处理追加对象部门
		//
		if (deleteDepartmentList != null && deleteDepartmentList.size() > 0) {
			for (DepartmentModel departmentDelete : deleteDepartmentList) {

				if (!containsDepartment(addDepartmentList, departmentDelete)) {
					// 部门部门对象
					ActionDepartmentModel actionDepartmentModel = new ActionDepartmentModel();

					// 处理分类：删除
					actionDepartmentModel
							.setPrcessFlg(CommonConsts.PROCESS_FLG_DELETE);
					// 画面功能ID设置
					actionDepartmentModel.setActionId(actionId);
					// 公司ID设置
					actionDepartmentModel.setCompanyId(departmentDelete
							.getCompanyId());
					// 部门ID设置
					actionDepartmentModel.setDepartmentId(departmentDelete
							.getDepartmentId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(actionDepartmentModel);

					// 加入到列表
					processDepartment.add(actionDepartmentModel);
				}
			}
		}

		// 返回处理对象部门列表
		return processDepartment;
	}*/

	/**
	 * 合并处理对象职位列表
	 * 
	 * @param addPostList
	 *            追加对象职位列表
	 * @param deletePostList
	 *            删除对象职位列表
	 * @return List<CompanyPostModel> 合并处理对象职位列表
	 */
	/*public static List<ActionPostModel> mergeActionPost(
			List<CompanyPostModel> addPostList,
			List<CompanyPostModel> deletePostList, ActionModel actionModel) {
		// 画面功能ID
		String actionId = "";

		// 处理对象职位列表
		List<ActionPostModel> processPost = new ArrayList<ActionPostModel>();

		if ((addPostList == null || addPostList.size() == 0)
				&& (deletePostList == null || deletePostList.size() == 0)
				|| ObjectUtil.isEmpty(actionModel)) {
			return null;
		}

		// 取得画面功能ID
		actionId = actionModel.getActionId();

		//
		// 处理追加对象职位
		//
		if (addPostList != null && addPostList.size() > 0) {
			for (CompanyPostModel postAdd : addPostList) {

				if (!containsPost(deletePostList, postAdd)) {
					// 职位职位对象
					ActionPostModel actionPostModel = new ActionPostModel();

					// 处理分类：追加
					actionPostModel.setPrcessFlg(CommonConsts.PROCESS_FLG_ADD);
					// 画面功能ID设置
					actionPostModel.setActionId(actionId);
					// 公司ID设置
					actionPostModel.setCompanyId(postAdd.getCompanyId());
					// 职位ID设置
					actionPostModel.setPostId(postAdd.getPostId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(actionPostModel);

					// 加入到列表
					processPost.add(actionPostModel);
				}
			}
		}

		//
		// 处理追加对象职位
		//
		if (deletePostList != null && deletePostList.size() > 0) {
			for (CompanyPostModel postDelete : deletePostList) {

				if (!containsPost(addPostList, postDelete)) {
					// 职位职位对象
					ActionPostModel actionPostModel = new ActionPostModel();

					// 处理分类：删除
					actionPostModel
							.setPrcessFlg(CommonConsts.PROCESS_FLG_DELETE);
					// 画面功能ID设置
					actionPostModel.setActionId(actionId);
					// 公司ID设置
					actionPostModel.setCompanyId(postDelete.getCompanyId());
					// 职位ID设置
					actionPostModel.setPostId(postDelete.getPostId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(actionPostModel);

					// 加入到列表
					processPost.add(actionPostModel);
				}
			}
		}

		// 返回处理对象职位列表
		return processPost;
	}*/

	/**
	 * 合并处理对象角色列表
	 * 
	 * @param addRoleList
	 *            追加对象角色列表
	 * @param deleteRoleList
	 *            删除对象角色列表
	 * @return List<RoleModel> 合并处理对象角色列表
	 */
	/*public static List<AppApiRoleModel> mergeAppApiRole(
			List<RoleModel> addRoleList, List<RoleModel> deleteRoleList,
			AppApiModel appApiModel) {
		// App接口ID
		String appApiId = "";

		// 处理对象角色列表
		List<AppApiRoleModel> processRole = new ArrayList<AppApiRoleModel>();

		if ((addRoleList == null || addRoleList.size() == 0)
				&& (deleteRoleList == null || deleteRoleList.size() == 0)
				|| ObjectUtil.isEmpty(appApiModel)) {
			return null;
		}

		// 取得App接口ID
		appApiId = appApiModel.getAppApiId();

		//
		// 处理追加对象角色
		//
		if (addRoleList != null && addRoleList.size() > 0) {
			for (RoleModel roleAdd : addRoleList) {

				if (!containsRole(deleteRoleList, roleAdd)) {
					// 角色角色对象
					AppApiRoleModel appApiRoleModel = new AppApiRoleModel();

					// 处理分类：追加
					appApiRoleModel.setPrcessFlg(CommonConsts.PROCESS_FLG_ADD);
					// App接口ID设置
					appApiRoleModel.setAppApiId(appApiId);
					// 角色ID设置
					appApiRoleModel.setRoleId(roleAdd.getRoleId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(appApiRoleModel);

					// 加入到列表
					processRole.add(appApiRoleModel);
				}
			}
		}

		//
		// 处理追加对象角色
		//
		if (deleteRoleList != null && deleteRoleList.size() > 0) {
			for (RoleModel roleDelete : deleteRoleList) {

				if (!containsRole(addRoleList, roleDelete)) {
					// 角色角色对象
					AppApiRoleModel appApiRoleModel = new AppApiRoleModel();

					// 处理分类：删除
					appApiRoleModel
							.setPrcessFlg(CommonConsts.PROCESS_FLG_DELETE);
					// App接口ID设置
					appApiRoleModel.setAppApiId(appApiId);
					// 角色ID设置
					appApiRoleModel.setRoleId(roleDelete.getRoleId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(appApiRoleModel);

					// 加入到列表
					processRole.add(appApiRoleModel);
				}
			}
		}

		// 返回处理对象角色列表
		return processRole;
	}*/

	/**
	 * 合并处理对象部门列表
	 * 
	 * @param addDepartmentList
	 *            追加对象部门列表
	 * @param deleteDepartmentList
	 *            删除对象部门列表
	 * @return List<DepartmentModel> 合并处理对象部门列表
	 */
	/*public static List<AppApiDepartmentModel> mergeAppApiDepartment(
			List<DepartmentModel> addDepartmentList,
			List<DepartmentModel> deleteDepartmentList, AppApiModel appApiModel) {
		// App接口ID
		String appApiId = "";

		// 处理对象部门列表
		List<AppApiDepartmentModel> processDepartment = new ArrayList<AppApiDepartmentModel>();

		if ((addDepartmentList == null || addDepartmentList.size() == 0)
				&& (deleteDepartmentList == null || deleteDepartmentList.size() == 0)
				|| ObjectUtil.isEmpty(appApiModel)) {
			return null;
		}

		// 取得App接口ID
		appApiId = appApiModel.getAppApiId();

		//
		// 处理追加对象部门
		//
		if (addDepartmentList != null && addDepartmentList.size() > 0) {
			for (DepartmentModel departmentAdd : addDepartmentList) {

				if (!containsDepartment(deleteDepartmentList, departmentAdd)) {
					// 部门部门对象
					AppApiDepartmentModel appApiDepartmentModel = new AppApiDepartmentModel();

					// 处理分类：追加
					appApiDepartmentModel
							.setPrcessFlg(CommonConsts.PROCESS_FLG_ADD);
					// App接口ID设置
					appApiDepartmentModel.setAppApiId(appApiId);
					// 公司ID设置
					appApiDepartmentModel.setCompanyId(departmentAdd
							.getCompanyId());
					// 部门ID设置
					appApiDepartmentModel.setDepartmentId(departmentAdd
							.getDepartmentId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(appApiDepartmentModel);

					// 加入到列表
					processDepartment.add(appApiDepartmentModel);
				}
			}
		}

		//
		// 处理追加对象部门
		//
		if (deleteDepartmentList != null && deleteDepartmentList.size() > 0) {
			for (DepartmentModel departmentDelete : deleteDepartmentList) {

				if (!containsDepartment(addDepartmentList, departmentDelete)) {
					// 部门部门对象
					AppApiDepartmentModel appApiDepartmentModel = new AppApiDepartmentModel();

					// 处理分类：删除
					appApiDepartmentModel
							.setPrcessFlg(CommonConsts.PROCESS_FLG_DELETE);
					// App接口ID设置
					appApiDepartmentModel.setAppApiId(appApiId);
					// 公司ID设置
					appApiDepartmentModel.setCompanyId(departmentDelete
							.getCompanyId());
					// 部门ID设置
					appApiDepartmentModel.setDepartmentId(departmentDelete
							.getDepartmentId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(appApiDepartmentModel);

					// 加入到列表
					processDepartment.add(appApiDepartmentModel);
				}
			}
		}

		// 返回处理对象部门列表
		return processDepartment;
	}*/

	/**
	 * 合并处理对象职位列表
	 * 
	 * @param addPostList
	 *            追加对象职位列表
	 * @param deletePostList
	 *            删除对象职位列表
	 * @return List<CompanyPostModel> 合并处理对象职位列表
	 */
	/*public static List<AppApiPostModel> mergeAppApiPost(
			List<CompanyPostModel> addPostList,
			List<CompanyPostModel> deletePostList, AppApiModel appApiModel) {
		// App接口ID
		String appApiId = "";

		// 处理对象职位列表
		List<AppApiPostModel> processPost = new ArrayList<AppApiPostModel>();

		if ((addPostList == null || addPostList.size() == 0)
				&& (deletePostList == null || deletePostList.size() == 0)
				|| ObjectUtil.isEmpty(appApiModel)) {
			return null;
		}

		// 取得App接口ID
		appApiId = appApiModel.getAppApiId();

		//
		// 处理追加对象职位
		//
		if (addPostList != null && addPostList.size() > 0) {
			for (CompanyPostModel postAdd : addPostList) {

				if (!containsPost(deletePostList, postAdd)) {
					// 职位职位对象
					AppApiPostModel appApiPostModel = new AppApiPostModel();

					// 处理分类：追加
					appApiPostModel.setPrcessFlg(CommonConsts.PROCESS_FLG_ADD);
					// App接口ID设置
					appApiPostModel.setAppApiId(appApiId);
					// 公司ID设置
					appApiPostModel.setCompanyId(postAdd.getCompanyId());
					// 职位ID设置
					appApiPostModel.setPostId(postAdd.getPostId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(appApiPostModel);

					// 加入到列表
					processPost.add(appApiPostModel);
				}
			}
		}

		//
		// 处理追加对象职位
		//
		if (deletePostList != null && deletePostList.size() > 0) {
			for (CompanyPostModel postDelete : deletePostList) {

				if (!containsPost(addPostList, postDelete)) {
					// 职位职位对象
					AppApiPostModel appApiPostModel = new AppApiPostModel();

					// 处理分类：删除
					appApiPostModel
							.setPrcessFlg(CommonConsts.PROCESS_FLG_DELETE);
					// App接口ID设置
					appApiPostModel.setAppApiId(appApiId);
					// 公司ID设置
					appApiPostModel.setCompanyId(postDelete.getCompanyId());
					// 职位ID设置
					appApiPostModel.setPostId(postDelete.getPostId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(appApiPostModel);

					// 加入到列表
					processPost.add(appApiPostModel);
				}
			}
		}

		// 返回处理对象职位列表
		return processPost;
	}*/
}
