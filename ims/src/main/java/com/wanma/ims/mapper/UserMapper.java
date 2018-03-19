package com.wanma.ims.mapper;

import com.wanma.ims.common.domain.UserDO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    // 用户登录
    UserDO loginUser(UserDO tblUser);

    //根据ID查询基表信息
    UserDO selectUserByUserId(Long userId);

    //根据Ids查询基表信息
    List<UserDO> selectUserByUserIds(@Param("ids") List<Long> ids);

    //根据userAccount查询基表信息
    List<UserDO> selectUserByUserAccount(String userAccount);

    //根据userAccount查询companyUser或者normalUser信息
    UserDO selectUserByUserAccountAndCpyId(@Param("userAccount") String userAccount, @Param("cpyId") Long cpyId);

    //根据userAccount查询normalUser信息
    UserDO selectNomalUserByUserAccount(@Param("userAccount") String userAccount);

    //查询用户列表
    List<UserDO> selectUserList(UserDO searchModel);

    long countUser(UserDO searchModel);

    //插入tbl_user表
    int insertUser(UserDO user);

    //更新tbl_user表
    int updateUser(UserDO user);

    //只更新非空字段
    int updateByUserIdSelective(UserDO user);

    //根据userId删除用户
    int deleteByUserId(Long userId);


    /*********************************/
    //插入tbl_user_normal表
    int insertNormalUser(UserDO user);

    //更新tbl_user_normal表
    int updateNormalUser(UserDO user);

    //获取普通用户表信息
    UserDO findNomalUser(Long userId);

    //获取普通用列表
    List<UserDO> getNomalUserList(UserDO user);

    //获取普通用户数量
    long getNomalUserCount(UserDO user);

    //修改用户状态
    void updateStatus(UserDO user);

    //获取纯商家列表
    List<UserDO> getBusinessUserList(UserDO user);

    //获取纯商家信息数量
    long getBusinessUserCount(UserDO user);

    //保存纯商家信息
    void insertBusinessUser(UserDO user);

    //修改纯商家信息
    void updateBusinessUser(UserDO user);

    //校验账号一致性
    int checkAccount(UserDO user);

    //校验商家账号一致性
    int checkBusinessAccount(UserDO user);

    List<UserDO> getAdminUserList(UserDO user);

    long getAdminUserCount(UserDO user);

    List<UserDO> getRoleUserList(String roleId);

    List<UserDO> getSelectRoleUserList(UserDO user);

    long getSelectRoleUserCount(UserDO user);

    //有消费记录的用户数量
    int getUserConsumeCount(UserDO user);

    //得到商家用户拥有的电桩数和子商家数
    UserDO getPileAndChildCount(Long userId);

    //插入tbl_user_admin表
    int insertAdminUser(UserDO tblUser);

    void updateAdminUser(UserDO user);

    //校验卡号是否存在
    String findUnbindCardByCardNo(String cardNo);

    int checkBusinessPhone(UserDO user);

    int checkAdminPhone(UserDO user);

    UserDO findPhoneByAccount(UserDO user);

    int findUserCountByPhone(UserDO user);

    UserDO getAllUserByAccount(UserDO user);

    List<UserDO> getApplyCardUserList(UserDO user);

    long getApplyCardUserListCount(UserDO user);

    List<UserDO> getQuicklyApplyCardUserList(UserDO user);

    long getQuicklyApplyCardUserListCount(UserDO user);

    List<UserDO> getUserListByCpyId(Long cpyId);

    UserDO getCpyUserByAccountId(Long accountId);


}