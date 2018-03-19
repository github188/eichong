package com.wanma.ims.mapper;


import com.wanma.ims.common.domain.UserVinRelaDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserVinRelaMapper {


    void addUserVinRela(UserVinRelaDO userVinRelaDO);

    int checkUserVinRela(UserVinRelaDO userVinRelaDO);

    List<UserVinRelaDO> getVinInfoByUserId(Long userId);

    int deleteUserVinRela(Long pkId);

    void updateUserVinRela(@Param("userId") Long userId,@Param("ucUserId") Long ucUserId);

    int getVinCountByUserId(Long userId);
}
