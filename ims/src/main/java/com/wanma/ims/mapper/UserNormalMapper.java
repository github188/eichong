package com.wanma.ims.mapper;

import com.wanma.ims.common.domain.UserNormalDO;

import java.util.List;
import java.util.Map;

public interface UserNormalMapper {

    UserNormalDO selectUserNormalByUserId(Long userId);

    List<Map<String, Long>> countNormalUserByCpyId(Long cpyId);

    int insertUserNormal(UserNormalDO record);

    int deleteByUserId(Long userId);

    int updateByUserId(UserNormalDO userNormal);

    int updateByUserIdSelective(UserNormalDO userNormal);
}