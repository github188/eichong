package com.wanma.dao;

import com.wanma.model.TblUser;

public interface TblUserMapper {

	public TblUser get(TblUser tblUser);
	public TblUser getByAccount(String userAccount);
	//插入tbl_user表
	public int insert(TblUser tblUser);
	//插入tbl_user_normal表
	public int insertNormalUser(TblUser tblUser);
	//更新tbl_user表,本系统内只能更新userAccount和userPassword
	public int update(TblUser tblUser);
	//更新tbl_user_normal表
	public int updateNormalUser(TblUser tblUser);

	public TblUser getGreenLangUserByAccount(String userAccount);
	//充值赠送操作修改用户余额
	public void updateUserChargeGift(TblUser user);

}
