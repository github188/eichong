package com.wanma.ims.service.impl;

import com.wanma.ims.common.domain.CompanyDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BatchResultDTO;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.redis.RedisUtil;
import com.wanma.ims.service.CommonRedisService;
import com.wanma.ims.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共缓存对象
 * 目前：管理员与充电桩的数据权限
 */
@Service
public class CommonRedisServiceImpl implements CommonRedisService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CompanyService companyService;
    @Autowired
    private RedisUtil redisService;

    @Override
    public void initCurrentLogin(UserDO loginUser) {
        try {
            if (loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_WORK) {
                BatchResultDTO<CompanyDO> dto = companyService.getCpyListByUserLevel(loginUser, "", "");
                List<Long> cpyIdList = new ArrayList<Long>();
                List<Integer> cpyNumberList = new ArrayList<Integer>();
                if (dto.isSuccess()) {
                    List<CompanyDO> cpyList = dto.getModule();
                    for (CompanyDO companyDO : cpyList) {
                        cpyIdList.add(companyDO.getCpyId());
                        cpyNumberList.add(companyDO.getCpyNumber());
                    }
                    loginUser.setCpyIdList(cpyIdList);
                    loginUser.setCpyNumberList(cpyNumberList);
                }
            }
            redisService.set("ims:login:" + loginUser.getUserId(), loginUser);
        } catch (Exception e) {
            LOGGER.error("CommonRedisServiceImpl called initLoginPermission failed", e);
        }
    }

    @Override
    public UserDO getCurrentLogin(Long userId) {
        return (UserDO) redisService.get("ims:login:" + userId, UserDO.class);
    }

    @Override
    public void removeCurrentLogin(Long userId) {
        redisService.remove("ims:login:" + userId, UserDO.class);
    }
}
