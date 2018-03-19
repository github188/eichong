package com.wanma.ims.mapper;

import java.util.List;

import com.wanma.ims.common.domain.UserAdminDO;

public interface UserAdminMapper {

    public UserAdminDO selectUserAdminByUserId(Long userId);
    
    public Long countUserAdminList(UserAdminDO userAdminDO);
    
    public List<UserAdminDO> selectUserAdminList(UserAdminDO userAdminDO);
    
    public Integer insertUserAdmin(UserAdminDO userAdminDO);
    
    public List<UserAdminDO> selectUserAdminByCpyId(Long cpyId);
    
    public List<UserAdminDO> selectUserAccountByCpyId(Long cpyId);
    
}