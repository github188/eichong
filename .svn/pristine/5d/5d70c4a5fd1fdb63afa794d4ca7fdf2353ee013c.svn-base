/**
 * FileName:AuthorizedUtil.java
 * Author: Administrator
 * Create: 2014年6月30日
 * Last Modified: 2014年6月30日
 * Version: V1.0 
 */
package com.bluemobi.product.common;

import org.apache.log4j.Logger;

import com.bluemobi.product.common.dao.AuthorizedDao;
import com.bluemobi.product.utils.UserUtil;

/**
 * 权限工具类
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月30日
 */
public class AuthorizedManger {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(AuthorizedManger.class);

	/** 限工具类 */
	private static AuthorizedManger authorizedManger;

	/** 权限DAO */
	private static AuthorizedDao authorizedDao;

	/**
	 * 是否拥有按钮权限
	 * @param userId 用户ID
	 * @param actionId 按钮ID
	 * @return 是否拥有按钮权限
	 */
	public boolean isHasActionAuth(String userId, String actionId) {
		long dataCount = 0;
		boolean isHasActionAuth = false;

		if (UserUtil.isSuperUser()) {
			return true;
		}

		authorizedDao = new AuthorizedDao();
		dataCount = authorizedDao.getActionRoleCount(userId, actionId);

		if (dataCount > 0) {
			isHasActionAuth = true;
		}

		if (!isHasActionAuth) {
			dataCount = authorizedDao
					.getActionDepartmentCount(userId, actionId);
			if (dataCount > 0) {
				isHasActionAuth = true;
			}
		}

		if (!isHasActionAuth) {
			dataCount = authorizedDao.getActionPostCount(userId, actionId);
			if (dataCount > 0) {
				isHasActionAuth = true;
			}
		}

		log.debug(isHasActionAuth);

		return isHasActionAuth;
	}

	/**
	 * 是否拥有APP接口权限
	 * @param userId 用户ID
	 * @param actionId 按钮ID
	 * @return 是否拥有按钮权限
	 */
	public boolean isHasAppApiAuth(String userId, String appApiId) {
		long dataCount = 0;
		boolean isHasAppApiAuth = false;
		boolean isOnControl = false;

		authorizedDao = new AuthorizedDao();
		isOnControl = authorizedDao.getIsOnControl(appApiId);
		if (!isOnControl) {
			return true;
		}
		dataCount = authorizedDao.getAppApiRoleCount(userId, appApiId);

		if (dataCount > 0) {
			isHasAppApiAuth = true;
		}

		if (!isHasAppApiAuth) {
			dataCount = authorizedDao.getAppApiDeptCount(userId, appApiId);
			if (dataCount > 0) {
				isHasAppApiAuth = true;
			}
		}

		if (!isHasAppApiAuth) {
			dataCount = authorizedDao.getAppApiPostCount(userId, appApiId);
			if (dataCount > 0) {
				isHasAppApiAuth = true;
			}
		}

		log.debug(isHasAppApiAuth);

		return isHasAppApiAuth;
	}

	/**
	 * 初始化Manager
	 */
	public static AuthorizedManger getAuthorizedManger() {
		AuthorizedManger manager = null;
		if (authorizedManger == null) {
			manager = new AuthorizedManger();
		} else {
			manager = authorizedManger;
		}

		return manager;

	}

	public static void main(String args[]) {
		AuthorizedManger.getAuthorizedManger().isHasActionAuth("yangweir",
				"ACT-001-001");
	}
}
