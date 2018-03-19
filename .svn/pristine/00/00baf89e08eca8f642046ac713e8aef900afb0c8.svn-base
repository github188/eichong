package com.wanma.ims.service;

import com.wanma.ims.common.domain.UserDO;


public interface CommonRedisService {
	/**
	 * 业务管理员公司ID权限 
	 */
	void initCurrentLogin(UserDO loginUser);
	
	/**
	 * 获取业务管理员公司权限
	 */
	UserDO getCurrentLogin(Long userId);
	
	/**
	 * 删除当前登录人
	 */
	void removeCurrentLogin(Long userId);
}
