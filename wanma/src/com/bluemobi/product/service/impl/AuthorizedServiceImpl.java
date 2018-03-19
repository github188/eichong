/**
 * FileName:AuthorizedServiceImpl.java
 * Author: Administrator
 * Create: 2014年7月1日
 * Last Modified: 2014年7月1日
 * Version: V1.0 
 */
package com.bluemobi.product.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.dao.AuthorizedMapper;
import com.bluemobi.product.service.AuthorizedService;

/**
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月1日
 */
@Service
public class AuthorizedServiceImpl implements AuthorizedService {

	/** 权限相关查询DAO接口Mapper */
	@Autowired
	private AuthorizedMapper authorizedMapper;

	/** 用户表操作用DAO */

	/**
	 * 根据用户登录ID和功能ID，查询用户是否拥有使用权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param authMap
	 *            userId ：用户ID actionId：功能ID
	 * @return int 用户数
	 * @throws 无
	 */
	public long getActionRoleCount(Map<String, String> searchAuthMap) {
		long dataCount = 0;

		/*
		 * searchAuthMap.put(CommonConsts.AUTH_MAP_KEY_USER_ID, userId); //
		 * searchAuthMap.put(CommonConsts.AUTH_MAP_KEY_ACTION_ID, actionId);
		 */
		searchAuthMap.put(CommonConsts.AUTH_MAP_KEY_USER_ID, "yangweir");
		searchAuthMap.put(CommonConsts.AUTH_MAP_KEY_ACTION_ID, "ACT-001-001");
		dataCount = authorizedMapper.getActionRoleCount(searchAuthMap);
		return dataCount;

	}

}
