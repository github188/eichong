/**
 * FileName:UserUtil.java
 * Author: Administrator
 * Create: 2014年7月10日
 * Last Modified: 2014年7月10日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.druid.util.StringUtils;
import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.common.WebConst;
import com.bluemobi.product.common.dao.RoleDao;
import com.bluemobi.product.common.dao.UserDao;
import com.bluemobi.product.model.CompanyPostModel;
import com.bluemobi.product.model.DepartmentModel;
import com.bluemobi.product.model.RoleModel;
import com.bluemobi.product.model.UserDepartmentModel;
import com.bluemobi.product.model.UserPostModel;
import com.bluemobi.product.model.UserRoleModel;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblUser;

/**
 * 用户工具类
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月10日
 */
public class UserUtil {

	/**
	 * 判断用户列表中是否已存在用户信息
	 * 
	 * @param targetList
	 *            对象用户列表
	 * @param TblUser
	 *            对象用户
	 * @return 存在标识
	 */
	public static boolean containsUser(List<TblUser> targetList,
			TblUser targetTblUser) {

		// 用户ID
		String targetUserId = "";

		//
		// 对象用户为空，返回不存在标识
		//
		if (targetTblUser == null) {
			// 返回不存在标识
			return false;
		}

		// 取得用户ID
		targetUserId = targetTblUser.getUserId().toString();

		// 返回存在标识
		return containsUser(targetList, targetUserId);

	}

	/**
	 * 判断用户列表中是否已存在用户信息
	 * 
	 * @param targetList
	 *            对象用户列表
	 * @param targetUserId
	 *            对象用户Id
	 * @return 存在标识
	 */
	public static boolean containsUser(List<TblUser> targetList,
			String targetUserId) {
		// 存在标识
		boolean containUser = false;

		//
		// 用户列表或者对象用户为空，返回不存在标识
		//
		if (targetList == null || targetList.size() < 1
				|| StringUtil.isEmpty(targetUserId)) {
			// 返回不存在标识
			return false;
		}

		for (TblUser TblUser : targetList) {
			// 取得用户ID
			String userId = TblUser.getUserId().toString();

			if (StringUtils.equals(targetUserId, userId)) {
				containUser = true;
				break;
			}

		}

		// 返回存在标识
		return containUser;

	}

	/**
	 * 合并处理对象用户列表
	 * 
	 * @param addUserList
	 *            追加对象用户列表
	 * @param deleteUserList
	 *            删除对象用户列表
	 * @return List<TblUser> 合并处理对象用户列表
	 */
	public static List<UserDepartmentModel> mergeProcessUser(
			List<TblUser> addUserList, List<TblUser> deleteUserList,
			DepartmentModel departmentModel) {
		// 公司ID
		String companyId = "";
		// 部门ID
		String departmentId = "";

		// 处理对象用户列表
		List<UserDepartmentModel> processUser = new ArrayList<UserDepartmentModel>();

		if ((addUserList == null || addUserList.size() == 0)
				&& (deleteUserList == null || deleteUserList.size() == 0)
				|| ObjectUtil.isEmpty(departmentModel)) {
			return null;
		}

		// 取得公司ID
		companyId = departmentModel.getCompanyId();
		// 取得部门ID
		departmentId = departmentModel.getDepartmentId();

		//
		// 处理追加对象用户
		//
		if (addUserList != null && addUserList.size() > 0) {
			for (TblUser userAdd : addUserList) {

				if (!containsUser(deleteUserList, userAdd)) {
					// 用户部门对象
					UserDepartmentModel userDepartmentModel = new UserDepartmentModel();

					// 处理分类：追加
					userDepartmentModel
							.setPrcessFlg(CommonConsts.PROCESS_FLG_ADD);
					// 用户ID
					userDepartmentModel.setUserId(userAdd.getUserId().toString());
					// 公司ID设置
					userDepartmentModel.setCompanyId(companyId);
					// 部门ID设置
					userDepartmentModel.setDepartmentId(departmentId);
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(userDepartmentModel);

					// 加入到列表
					processUser.add(userDepartmentModel);
				}
			}
		}

		//
		// 处理追加对象用户
		//
		if (deleteUserList != null && deleteUserList.size() > 0) {
			for (TblUser userDelete : deleteUserList) {

				if (!containsUser(addUserList, userDelete)) {
					// 用户部门对象
					UserDepartmentModel userDepartmentModel = new UserDepartmentModel();

					// 处理分类：删除
					userDepartmentModel
							.setPrcessFlg(CommonConsts.PROCESS_FLG_DELETE);
					// 用户ID
					userDepartmentModel.setUserId(userDelete.getUserId().toString());
					// 公司ID设置
					userDepartmentModel.setCompanyId(companyId);
					// 部门ID设置
					userDepartmentModel.setDepartmentId(departmentId);
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(userDepartmentModel);

					// 加入到列表
					processUser.add(userDepartmentModel);
				}
			}
		}

		// 返回处理对象用户列表
		return processUser;
	}

	/**
	 * 合并处理对象用户列表
	 * 
	 * @param addUserList
	 *            追加对象用户列表
	 * @param deleteUserList
	 *            删除对象用户列表
	 * @return List<TblUser> 合并处理对象用户列表
	 */
	public static List<UserPostModel> mergeProcessUser(
			List<TblUser> addUserList, List<TblUser> deleteUserList,
			CompanyPostModel postModel) {
		// 公司ID
		String companyId = "";
		// 职位ID
		String postId = "";

		// 处理对象用户列表
		List<UserPostModel> processUser = new ArrayList<UserPostModel>();

		if ((addUserList == null || addUserList.size() == 0)
				&& (deleteUserList == null || deleteUserList.size() == 0)
				|| ObjectUtil.isEmpty(postModel)) {
			return null;
		}

		// 取得公司ID
		companyId = postModel.getCompanyId();
		// 取得职位ID
		postId = postModel.getPostId();

		//
		// 处理追加对象用户
		//
		if (addUserList != null && addUserList.size() > 0) {
			for (TblUser userAdd : addUserList) {

				if (!containsUser(deleteUserList, userAdd)) {
					// 用户职位对象
					UserPostModel userPostModel = new UserPostModel();

					// 处理分类：追加
					userPostModel.setPrcessFlg(CommonConsts.PROCESS_FLG_ADD);
					// 用户ID
					userPostModel.setUserId(userAdd.getUserId().toString());
					// 公司ID设置
					userPostModel.setCompanyId(companyId);
					// 职位ID设置
					userPostModel.setPostId(postId);
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(userPostModel);

					// 加入到列表
					processUser.add(userPostModel);
				}
			}
		}

		//
		// 处理追加对象用户
		//
		if (deleteUserList != null && deleteUserList.size() > 0) {
			for (TblUser userDelete : deleteUserList) {

				if (!containsUser(addUserList, userDelete)) {
					// 用户职位对象
					UserPostModel userPostModel = new UserPostModel();

					// 处理分类：删除
					userPostModel.setPrcessFlg(CommonConsts.PROCESS_FLG_DELETE);
					// 用户ID
					userPostModel.setUserId(userDelete.getUserId().toString());
					// 公司ID设置
					userPostModel.setCompanyId(companyId);
					// 职位ID设置
					userPostModel.setPostId(postId);
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(userPostModel);

					// 加入到列表
					processUser.add(userPostModel);
				}
			}
		}

		// 返回处理对象用户列表
		return processUser;
	}

	/**
	 * 合并处理对象用户列表
	 * 
	 * @param addUserList
	 *            追加对象用户列表
	 * @param deleteUserList
	 *            删除对象用户列表
	 * @return List<TblUser> 合并处理对象用户列表
	 */
	public static List<UserRoleModel> mergeProcessUser(
			List<TblUser> addUserList, List<TblUser> deleteUserList,
			RoleModel roleModel) {

		// 角色ID
		String roleId = "";

		// 处理对象用户列表
		List<UserRoleModel> processUser = new ArrayList<UserRoleModel>();

		if ((addUserList == null || addUserList.size() == 0)
				&& (deleteUserList == null || deleteUserList.size() == 0)
				|| ObjectUtil.isEmpty(roleModel)) {
			return null;
		}

		// 取得角色ID
		roleId = roleModel.getRoleId();

		//
		// 处理追加对象用户
		//
		if (addUserList != null && addUserList.size() > 0) {
			for (TblUser userAdd : addUserList) {
				if(userAdd != null){
					if (!containsUser(deleteUserList, userAdd)) {
						// 用户角色对象
						UserRoleModel userRoleModel = new UserRoleModel();

						// 处理分类：删除
						userRoleModel.setPrcessFlg(CommonConsts.PROCESS_FLG_ADD);
						// 用户ID
						userRoleModel.setUserId(userAdd.getUserId().toString());
						// 角色ID设置
						userRoleModel.setRoleId(roleId);
						// 设置创建者用户信息
						ProcessInfoCommon.setCreateUserInfo(userRoleModel);

						// 加入到列表
						processUser.add(userRoleModel);
					}
				}
			}
		}

		//
		// 处理追加对象用户
		//
		if (deleteUserList != null && deleteUserList.size() > 0) {
			for (TblUser userDelete : deleteUserList) {

				if (!containsUser(addUserList, userDelete)) {
					// 用户角色对象
					UserRoleModel userRoleModel = new UserRoleModel();

					// 处理分类：追加
					userRoleModel.setPrcessFlg(CommonConsts.PROCESS_FLG_DELETE);
					// 用户ID
					userRoleModel.setUserId(userDelete.getUserId().toString());
					// 角色ID设置
					userRoleModel.setRoleId(roleId);
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(userRoleModel);

					// 加入到列表
					processUser.add(userRoleModel);
				}
			}
		}

		// 返回处理对象用户列表
		return processUser;
	}

	/**
	 * 判断登录用户是否是超级用户
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @return boolean 是否是超级用户
	 * @throws 无
	 */
	public static boolean isSuperUser() {
		// 是否是超级用户
		boolean isSuperUser = false;

		HttpServletRequest request = HttpServletRequestUtil.getHttpRequest();

		// 用户管理DAO
		UserDao userDao = new UserDao();

		// 登录用户信息
		TblUser loginUser;
		// 登录用户Id（默认为guest）
		String userLogin = "guest";

		// 取得登录用户信息
		loginUser = SessionMgr.getWebUser(request);
		// 如果设置对象不为空
		if (loginUser != null) {
			// 取得登录用户Id
			userLogin = loginUser.getUserId().toString();

			if (StringUtils.equals(CommonConsts.SUPPER_USER_LEVEL_ID,
					loginUser.getUserLevel()+"")) {
				return true;
			}
		} else {
			return false;
		}

		// 调用用户DAO取得登录用户是否是超级用户标识
		isSuperUser = userDao.isSuperUser(userLogin);

		// 返回是否是超级用户
		return isSuperUser;

	}

	/**
	 * 取得登录用户Id
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @return 登录用户Id
	 * @throws 无
	 */
	public static String getLoginUserId() {

		HttpServletRequest request = HttpServletRequestUtil.getHttpRequest();

		// 登录用户信息
		TblUser loginUser;
		// 登录用户Id（默认为guest）
		String userLogin = "guest";

		// 取得登录用户信息
		loginUser = SessionMgr.getWebUser(request);
		// 如果设置对象不为空
		if (loginUser != null) {
			// 取得登录用户Id
			userLogin = loginUser.getUserId().toString();
		}

		// 返回登录用户Id
		return userLogin;
	}

	/**
	 * 取得登录用户级别
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @return 用户级别
	 * @throws 无
	 */
	public static String getLoginUserLevel() {

		HttpServletRequest request = HttpServletRequestUtil.getHttpRequest();

		// 登录用户信息
		TblUser loginUser;
		// 登录用户用户级别
		String userLevel = "99";

		// 取得登录用户信息
		loginUser = SessionMgr.getWebUser(request);

		// 如果设置对象不为空
		if (loginUser != null) {
			// 取得登录用户用户级别
			userLevel = loginUser.getUserLevel()+"";
		}

		// 返回登录用户用户级别
		return userLevel;
	}

	/**
	 * 判断登录用户是否是管理员用户
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @return boolean 是否是管理员用户
	 * @throws 无
	 */
	public static boolean isManagerUser() {

		if (isSuperUser()) {
			return true;
		}

		// 是否是管理员用户
		boolean isManagerUser = false;
		// 用户最小的角色级别
		String minCategory = "99";

		HttpServletRequest request = HttpServletRequestUtil.getHttpRequest();

		// 角色管理DAO
		RoleDao roleDao = new RoleDao();

		// 登录用户信息
		TblUser loginUser;
		// 登录用户Id（默认为guest）
		String userLogin = "guest";

		// 取得登录用户信息
		loginUser = SessionMgr.getWebUser(request);
		// 如果设置对象不为空
		if (loginUser != null) {
			// 取得登录用户Id
			userLogin = loginUser.getUserId().toString();

			// 调用用户DAO取得登录用户是否是管理员用户标识
			minCategory = roleDao.getUserMinRole(userLogin);

			if (StringUtil.equals("0", minCategory)) {
				return true;
			}
		} else {
			return false;
		}

		// 返回是否是管理员用户
		return isManagerUser;

	}

	/**
	 * 从用户列表中删除指定用户对象
	 * 
	 * @param targetUserList
	 *            用户列表
	 * @param targetTblUser
	 *            指定用户对象
	 * @return 无
	 * @throws 无
	 */
	public static void removeUser(List<TblUser> targetUserList,
			TblUser targetTblUser) {
		if (targetUserList == null || targetUserList.size() == 0
				|| targetTblUser == null
				|| targetTblUser.getUserId() == null) {
			return;
		}

		for (TblUser roleModel : targetUserList) {
			if (StringUtils.equals(roleModel.getUserId().toString(),
					targetTblUser.getUserId().toString())) {
				targetUserList.remove(roleModel);
				break;
			}
		}
	}

	/**
	 * 从部门一览中筛选用户列表
	 * 
	 * @param targetUserList
	 *            角色列表
	 * @param targetTblUser
	 *            指定角色对象
	 * @return 无
	 * @throws 无
	 */
	public static List<TblUser> searchDeptUser(
			List<TblUser> targetUserList, TblUser targetTblUser) {
		List<TblUser> reUserList = new ArrayList<TblUser>();

		if (targetUserList == null || targetUserList.size() == 0
				|| targetTblUser == null
				|| targetTblUser.getUserId() == null) {
			return targetUserList;
		}

		for (TblUser TblUser : targetUserList) {
			boolean matchUserId = false;
			boolean matchUserName = false;
			/*if (StringUtils.isEmpty(TblUser.getUserId())
					|| StringUtils.equals(TblUser.getUserId(),
							targetTblUser.getUserId())) {
				matchUserId = true;
			}

			if (StringUtils.isEmpty(TblUser.getUserName())
					|| StringUtils.equals(TblUser.getUserName(),
							targetTblUser.getUserName())) {
				matchUserName = true;
			}*/

			if (matchUserId && matchUserName) {
				reUserList.add(TblUser);
			}
		}

		return reUserList;
	}

}
