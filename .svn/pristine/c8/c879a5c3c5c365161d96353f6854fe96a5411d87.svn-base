package com.bluemobi.db.dao;

import com.bluemobi.model.User;
/**
 * 用户相关数据访问对象接口
 * @author haojian
 * Apr 8, 2013 5:01:55 PM
 */
public interface IUserDao {
	
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
