package com.bluemobi.db.service;

import com.bluemobi.model.User;
/**
 * 用户相关DB服务接口
 * @author haojian
 * Apr 8, 2013 4:58:20 PM
 */
public interface IUserService {
	
	/**保存 更新 用户*/
	void saveOrUpdate(User user);
	/**根据用户名查找用户*/
	User findUserByUserName(String username);
	/**根据用户名、来源查找用户*/
	User findUserByUserName(String username,int source);
	/**
	 * 根据userid获取user信息
	 * @param userId
	 * @return
	 */
	public User findUserByUserId(int userId);
}
