package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblActivity;
import com.wanma.model.TblUser;
import com.wanma.model.TblUserCard;
import com.wanma.model.UserCoupon;

public interface TblUserMapper {

	public TblUser get(TblUser tblUser);
	public TblUser getByAccount(TblUser tblUser);
	//插入tbl_user表
	public int insert(TblUser tblUser);
	//插入tbl_user_normal表
	public int insertNormalUser(TblUser tblUser);
	//更新tbl_user表,本系统内只能更新userAccount和userPassword
	public int update(TblUser tblUser);
	//更新tbl_user_normal表
	public int updateNormalUser(TblUser tblUser);
	//获取普通用户表信息
	public TblUser findNomalUser(Long userId);
	//获取普通用列表
	public List<TblUser> getNomalUserList(TblUser user);
	//获取普通用户数量
	public long getNomalUserCount(TblUser user);
	//修改用户状态
	public void updateStatus(TblUser user);
	//获取纯商家列表
	public List<TblUser> getBusinessUserList(TblUser user);
	//获取纯商家信息数量
	public long getBusinessUserCount(TblUser user);
	//保存纯商家信息
	public void insertBusinessUser(TblUser user);
	//修改纯商家信息
	public void updateBusinessUser(TblUser user);
	//获取商家信息
	public TblUser findBusinessUser(Long userId);
	//校验账号一致性
	public int checkAccount(TblUser user);
	//校验商家账号一致性
	public int checkBusinessAccount(TblUser user);
	public List<TblUser> getAdminUserList(TblUser user);
	public long getAdminUserCount(TblUser user);
	public TblUser findUser(Long userId);
	public List<TblUser> getRoleUserList(String roleId);
	public List<TblUser> getSelectRoleUserList(TblUser user);
	public long getSelectRoleUserCount(TblUser user);
	//有消费记录的用户数量
	public int getUserConsumeCount(TblUser user);
	//得到商家用户拥有的电桩数和子商家数
	public TblUser getPileAndChildCount(Long userId);
	//插入tbl_user_admin表
	public int insertAdminUser(TblUser tblUser);
	public TblUser findAdminUser(Long userId);
	public void updateAdminUser(TblUser user);
	
	//获取充电卡用户列表
	public List<TblUserCard> getCardUserList(TblUserCard user);
	//绑定充电卡号
	public void bindCard(TblUserCard userCard);
	//获取卡号用户数量
	public long getCardUserListCount(TblUserCard userCard);
	//校验卡号是否存在
	public String findUnbindCardByCardNo(String cardNo);
	public int checkBusinessPhone(TblUser user);
	public int checkAdminPhone(TblUser user);
	public TblUser findPhoneByAccount(TblUser user);
	public int findUserCountByPhone(TblUser user);
	public TblUser getAllUserByAccount(TblUser user);
	public List<TblUser> getApplyCardUserList(TblUser user);
	public long getApplyCardUserListCount(TblUser user);
	public List<TblUser> getQuicklyApplyCardUserList(TblUser user);
	public long getQuicklyApplyCardUserListCount(TblUser user);
	//获取用户优惠券列表
	public List<UserCoupon> findUserCouponList(UserCoupon userCoupon);
	public long getUserCouponCount(UserCoupon userCoupon);
	public List<TblActivity> getActivityList(UserCoupon userCoupon);

}
