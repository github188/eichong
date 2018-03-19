/** 
 * FileName UserService.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */

package com.bluemobi.product.service;

import java.util.List;

import com.bluemobi.product.model.common.DwzAjaxResult;
import com.wanma.model.TblActivity;
import com.wanma.model.TblUser;
import com.wanma.model.UserCoupon;
/**
 * FileName UserService.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 用户业务处理接口
 */
public interface TblUserService {

	/**
	 * 添加用户
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param UserModel
	 *            用户信息
	 * @return 用户ID
	 * @throws Exception 
	 * @throws 无
	 */
	public void insertUser(TblUser user) throws Exception;

	/**
	 * 编辑用户
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param UserModel
	 *            用户信息
	 * @return String 处理结果标识
	 * @throws Exception 
	 * @throws 无
	 */
	public void updateUser(TblUser user) throws Exception;
	
	/**
	 * 用户列表获取
	 * @param userModel
	 * @return
	 * @throws Exception 
	 */
	public List<TblUser> getUserList(TblUser user) throws Exception;

	/**
	 * 获取用户数量
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public long getUserCount(TblUser user) throws Exception;

	/**
	 * 获取用户信息
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public TblUser findUser(TblUser user) throws Exception;

	/**
	 * 删除用户
	 * @param user
	 * @throws Exception 
	 */
	public void deleteUser(TblUser user) throws Exception;

	/**
	 * 冻结用户
	 * @param user
	 */
	public void frozenUser(TblUser user);

	/**
	 * 升级个体商家
	 * @param user
	 */
	public void upgradeUser(TblUser user);

	/**
	 * 账号一致性检查
	 * @param user
	 */
	public boolean checkAccount(TblUser user);
	
	public boolean checkBusinessAccount(TblUser user);

	public List<TblUser> getRoleUserList(String roleId);

	public List<TblUser> getSelectRoleUserList(TblUser user);

	public long getSelectRoleUserCount(TblUser user);

	public TblUser findLoginUser(TblUser user) throws Exception;

	public boolean checkNowPassword(String userId, String nowPassword);

	/**
	 * @Title: unFrozenUser 
	 * @Description: TODO 	
	 * @author wbc	
	 * 2015年11月9日 	
	 * @return: void 
	 */
	public void unFrozenUser(TblUser userinfo);

	/**
	 * @Description: 查看用户是否有消费记录 	
	 * @author wbc	
	 * 2015年12月1日 	
	 * @return: int 
	 */
	public int getUserConsumeCount(TblUser user);

	/**
	 * @Description: 查看纯商家下是否有电桩、子商家
	 * @author wbc	
	 * 2015年12月9日 	
	 * @return: TblUser 
	 */
	public TblUser getPileAndChildCount(Long userId);

	public boolean checkBusinessPhone(TblUser user);

	public boolean checkAdminPhone(TblUser user);

	public String findPhoneByAccount(TblUser user);

	public int findUserCountByPhone(TblUser user);

	//商家列表，包含纯商家和子商家
	public List<TblUser> getAppUserList(TblUser user);

	public long getAppUserListCount(TblUser user);

	List<TblUser> getApplyCardUserList(TblUser user);

	long getApplyCardUserListCount(TblUser user);

	//获取用户优惠券列表
	public List<UserCoupon>  findUserCouponList(UserCoupon userCoupon);
	//获取用户优惠券数量
	public long getUserCouponCount(UserCoupon userCoupon);

	public List<TblActivity> getActivityList(UserCoupon userCoupon);

}
