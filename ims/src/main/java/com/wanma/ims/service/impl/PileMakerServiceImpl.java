package com.wanma.ims.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.wanma.ims.common.domain.PileMakerDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.mapper.PileMakerMapper;
import com.wanma.ims.service.PileMakerService;
import com.wanma.ims.util.SplitterUtil;

/**
 * Created by xyc on 2017/7/25.
 * 电桩制造商逻辑处理接口
 */
@Service
public class PileMakerServiceImpl implements PileMakerService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PileMakerMapper pileMakerMapper;

    @Override
    public List<PileMakerDO> getPileMakerList(PileMakerDO searchModel) {
        List<PileMakerDO> resultList = pileMakerMapper.getPileMakerList(searchModel);
        return resultList == null ? new ArrayList<PileMakerDO>() : resultList;
    }

    @Override
    public long countPileMaker(PileMakerDO searchModel) {
        return pileMakerMapper.countPileMaker(searchModel);
    }

    @Override
    public List<PileMakerDO> getPileMakerListByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return pileMakerMapper.getPileMakerListByIds(ids);
    }

    @Override
    public PileMakerDO getPileMakerById(Long pileMakerId) {
        return pileMakerMapper.getPileMakerById(pileMakerId);
    }

    @Override
    @Transactional
    public BaseResultDTO addPileMaker(PileMakerDO pileMaker, UserDO loginUser) {
        if (pileMaker == null) {
            LOGGER.warn(this.getClass() + "-addPileMaker is warn, newPileMaker is null|loginUser={}", loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "新增电桩制造商不能为空！");
        }

        PileMakerDO searchModel = new PileMakerDO();
        searchModel.setMakerName(pileMaker.getMakerName());
        if (CollectionUtils.isNotEmpty(pileMakerMapper.getPileMakerList(searchModel))) {
            LOGGER.error(this.getClass() + "-addPileMaker is error, newPileMaker is not unique|newPileMaker={}|loginUser={}", pileMaker, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "电桩制造商已存在，请刷新页面后重试！");
        }

        BaseResultDTO result = checkPileMaker(pileMaker);
        if (result.isFailed()) {
            return result;
        }

        pileMaker.setIsDel(0);
        pileMaker.setCreatorId(loginUser.getUserId());
        pileMaker.setCreator(loginUser.getUserAccount());
        pileMaker.setModifier(loginUser.getUserAccount());

        if (pileMakerMapper.insertPileMaker(pileMaker) < 1) {
            LOGGER.error(this.getClass() + "-addPileMaker is error, insert to db is error|newPileMaker={}|loginUser={}", pileMaker, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "新增电桩制造商出错，请刷新页面后重试！");
        }

        return new BaseResultDTO();
    }

    private BaseResultDTO checkPileMaker(PileMakerDO pileMaker) {
        if (Strings.isNullOrEmpty(pileMaker.getMakerName())) {
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "电桩制造商名称不能为空，请刷新页面后重试！");
        }

        if (Strings.isNullOrEmpty(pileMaker.getMakerRemark())) {
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "电桩制造商标识不能为空，请刷新页面后重试！");
        }

        if (pileMaker.getMakerRemark().length() > 2) {
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "电桩制造商标识最多两位数，请刷新页面后重试！");
        }

        return new BaseResultDTO();
    }

    @Override
    @Transactional
    public BaseResultDTO modifyPileMaker(PileMakerDO pileMaker, UserDO loginUser) {
        if (pileMaker == null) {
            LOGGER.warn(this.getClass() + "-modifyPileMaker is warn, modifyPileMaker is null|loginUser={}", loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "修改的电桩制造商不能为空！");
        }

        PileMakerDO oldPileMaker = pileMakerMapper.getPileMakerById(pileMaker.getId());
        if (oldPileMaker == null) {
            LOGGER.warn(this.getClass() + "-modifyPileMaker is warn, oldPileMaker is null|modifyPileMaker={}|loginUser={}", pileMaker, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "找不到要被修改的电桩制造商！");
        }

        PileMakerDO searchModel = new PileMakerDO();
        searchModel.setMakerName(pileMaker.getMakerName());
        List<PileMakerDO> pileMakerList = pileMakerMapper.getPileMakerList(searchModel);
        if (CollectionUtils.isNotEmpty(pileMakerList)) {
            for (PileMakerDO old : pileMakerList) {
                if (!Objects.equals(pileMaker.getId(), old.getId())) {
                    LOGGER.warn(this.getClass() + "-modifyPileMaker is error, modifyPileMaker is not unique|modifyPileMaker={}|loginUser={}", pileMaker, loginUser);
                    return new BaseResultDTO(false, ResultCodeConstants.FAILED, "电桩制造商已存在，请刷新页面后重试！");
                }
            }
        }

        BaseResultDTO result = checkPileMaker(pileMaker);
        if (result.isFailed()) {
            return result;
        }

        pileMaker.setModifier(loginUser.getUserAccount());
        if (pileMakerMapper.updatePileMakerByIdSelective(pileMaker) < 1) {
            LOGGER.error(this.getClass() + "-modifyPileMaker is error, update to db is error|newPileMaker={}|loginUser={}", pileMaker, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "修改电桩制造商出错，请刷新页面后重试！");
        }

        return new BaseResultDTO();
    }

    @Override
    @Transactional
    public BaseResultDTO delPileMaker(String pileMakerIds, UserDO loginUser) {
        List<Long> pileMakerIdList = SplitterUtil.splitToLongList(pileMakerIds, ",", null);
        for (Long pileMakerId : pileMakerIdList) {
            PileMakerDO oldPileMaker = pileMakerMapper.getPileMakerById(pileMakerId);
            if (oldPileMaker == null) {
                LOGGER.warn(this.getClass() + "-delPileMaker is warn, oldPileMaker is null|delPileMakerId={}|loginUser={}", pileMakerId, loginUser);
                return new BaseResultDTO(false, ResultCodeConstants.FAILED, "找不到要被删除的电桩制造商！");
            }

            if (pileMakerMapper.deletePileMakerById(pileMakerId) < 1) {
                LOGGER.error(this.getClass() + "-delPileMaker is error, del to db is error|delPileMakerId={}|loginUser={}", pileMakerId, loginUser);
                return new BaseResultDTO(false, ResultCodeConstants.FAILED, "删除电桩制造商出错，请刷新页面后重试！");
            }
        }

        return new BaseResultDTO();
    }
}
