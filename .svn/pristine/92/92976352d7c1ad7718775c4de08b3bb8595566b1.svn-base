package com.wanma.ims.service;

import com.wanma.ims.common.domain.PileMakerDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;

import java.util.List;

/**
 * Created by xyc on 2017/7/21.
 * 电桩制造商逻辑处理接口
 */
public interface PileMakerService {

    /**
     * 获取所有电桩制造商
     */
    List<PileMakerDO> getPileMakerList(PileMakerDO searchModel);

    /**
     * 获取电桩制造商总数
     */
    long countPileMaker(PileMakerDO searchModel);

    /**
     * 根据ids获取电桩制造商
     */
    List<PileMakerDO> getPileMakerListByIds(List<Long> ids);

    /**
     * 根据id获取电桩制造商
     */
    PileMakerDO getPileMakerById(Long pileMakerId);

    /**
     * 新增单个电桩制造商
     */
    BaseResultDTO addPileMaker(PileMakerDO pileMaker, UserDO loginUser);

    /**
     * 修改单个电桩制造商
     */
    BaseResultDTO modifyPileMaker(PileMakerDO pileMaker, UserDO loginUser);

    /**
     * 删除单个电桩制造商
     */
    BaseResultDTO delPileMaker(String pileMakerIds, UserDO loginUser);
}
