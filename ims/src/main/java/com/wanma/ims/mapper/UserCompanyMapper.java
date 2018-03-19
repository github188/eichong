package com.wanma.ims.mapper;

import com.wanma.ims.common.domain.UserCompanyDO;

import java.util.List;
import java.util.Map;

public interface UserCompanyMapper {

    UserCompanyDO selectUserCompanyByUserId(Long userId);

    List<Map<String, Long>> countCompanyUserByCpyId(Long cpyId);

    int insertUserCompany(UserCompanyDO userCompany);

    int deleteByUserId(Long userId);

    int updateByUserId(UserCompanyDO userCompany);

    int updateByUserIdSelective(UserCompanyDO userCompany);
}