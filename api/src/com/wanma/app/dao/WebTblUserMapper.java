package com.wanma.app.dao;

import com.wanma.model.TblUser;

public interface WebTblUserMapper {

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

    //更新tbl_user_normal表的同时更新fin_account表的account_balance
    public int updateFinAccount(TblUser tempUser);

    public TblUser getGovBusiUser(TblUser user);

    public void updateGovFinAccount(TblUser user);

    Long getUserCpyIdByUserId(Long userId);
}
