package com.wanma.app.service.parkinglock.impl;

import com.wanma.app.dao.CompanyChargeRelaMapper;
import com.wanma.app.dao.ParkingLockMapper;
import com.wanma.app.dao.ParkingLockTradeMapper;
import com.wanma.app.dao.WebTblUserMapper;
import com.wanma.app.service.parkinglock.ParkingLockService;
import com.wanma.app.service.parkinglock.PlatformService;
import com.wanma.app.util.ParkingLockTradeNoGenerate;
import com.wanma.app.util.ResultUtil;
import com.wanma.common.ParkingLockConstants;
import com.wanma.model.ParkingLock;
import com.wanma.model.ParkingLockTrade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 地锁逻辑处理类
 */
@Service
public class ParkingLockServiceImpl implements ParkingLockService {
    private static Logger LOGGER = Logger.getLogger(ParkingLockServiceImpl.class);

    @Autowired
    private ParkingLockMapper parkingLockMapper;
    @Autowired
    private ParkingLockTradeMapper parkingLockTradeMapper;
    @Autowired
    private WebTblUserMapper userMapper;
    @Autowired
    private CompanyChargeRelaMapper companyChargeRelaMapper;
    @Autowired
    private PlatformService platformService;

    @Override
    public Map<String, String> lowerParkingLock(Long userId, String parkingLockCode, Long parkingLockPlatform) {
        ParkingLock parkingLock = parkingLockMapper.selectByCodeAndPlatform(parkingLockCode, parkingLockPlatform);

        Map<String, String> resultErrorMap = checkLowerParkingLockParam(userId, parkingLock);
        if (resultErrorMap != null) {
            return resultErrorMap;
        }

        return processLowerParkingLock(userId, parkingLock);
    }

    private Map<String, String> checkLowerParkingLockParam(Long userId, ParkingLock parkingLock) {
        //验证地锁是否存在
        if (null == parkingLock) {
            LOGGER.warn("ParkingLockServiceImpl-checkLowerParkingLockParam is error, parkingLock is null|userId=" + userId);
            return ResultUtil.getResultMap(ParkingLockConstants.ERROR_CODE, ParkingLockConstants.ERROR_MSG);
        }

        //调用第三方接口验证地锁状态,如果地锁为降锁状态则清理之前未填写结束时间的订单，否则返回地锁被使用中，并打印日志
        if (!platformService.checkLockIsNormal(parkingLock)) {
            if (ParkingLockConstants.PARKING_LOCK_ERROR.equals(parkingLock.getStatus())) {
                LOGGER.warn("ParkingLockServiceImpl-checkLowerParkingLockParam is error, check lock to platform is error|userId=" + userId + "|parkingLockId=" + parkingLock.getId());
                return ResultUtil.getResultMap(ParkingLockConstants.ERROR_CODE, ParkingLockConstants.ERROR_MSG);
            }
            LOGGER.warn("ParkingLockServiceImpl-checkLowerParkingLockParam is error, lock is lower, not lower again|userId=" + userId + "|parkingLockId=" + parkingLock.getId());
            return ResultUtil.getResultMap(ParkingLockConstants.LOCK_IS_LOWER_ERROR_CODE, ParkingLockConstants.LOCK_IS_LOWER_ERROR_MSG);
        }

        if (!cleanErrorTrade(parkingLock.getId())) {
            LOGGER.warn("ParkingLockServiceImpl-checkLowerParkingLockParam is error, cleanErrorTrade is error|userId=" + userId + "|parkingLockId=" + parkingLock.getId());
            return ResultUtil.getResultMap(ParkingLockConstants.ERROR_CODE, ParkingLockConstants.ERROR_MSG);
        }

        //验证用户是否存在
        Long cpyId = userMapper.getUserCpyIdByUserId(userId);
        if (null == cpyId) {
            LOGGER.warn("ParkingLockServiceImpl-checkLowerParkingLockParam is error, get cpyId is error|userId=" + userId + "|parkingLockId=" + parkingLock.getId());
            return ResultUtil.getResultMap(ParkingLockConstants.USER_IS_NO_LOGIN_ERROR_CODE, ParkingLockConstants.USER_IS_NO_LOGIN_ERROR_MSG);
        }

        //验证用户是否可在该充电点充电
        boolean isCanChange = companyChargeRelaMapper.countByCpyIdAndPowerStationId(cpyId, parkingLock.getPowerStationId()) > 0;
        if (!isCanChange) {
            LOGGER.warn("ParkingLockServiceImpl-checkLowerParkingLockParam is error, user is not can change|userId=" + userId + "|parkingLockId=" + parkingLock.getId());
            return ResultUtil.getResultMap(ParkingLockConstants.USER_IS_NOT_CHANGE_ERROR_CODE, ParkingLockConstants.USER_IS_NOT_CHANGE_ERROR_MSG);
        }

        //验证用户是否已经打开一个地锁
        ParkingLockTrade searchModel = new ParkingLockTrade();
        searchModel.setUserId(userId);
        ParkingLockTrade userNoEndTrade = parkingLockTradeMapper.selectNoEndTrade(searchModel);
        if (null != userNoEndTrade) {
            LOGGER.warn("ParkingLockServiceImpl-checkLowerParkingLockParam is error, user is lower lock, not lower other lock|userId=" + userId + "|parkingLockId=" + parkingLock.getId());
            return ResultUtil.getResultMap(ParkingLockConstants.USER_HAS_LOWER_LOCK_ERROR_CODE, ParkingLockConstants.USER_HAS_LOWER_LOCK_ERROR_MSG);
        }

        return null;
    }

    private boolean cleanErrorTrade(Long parkingLockId) {
        //验证地锁是否存在异常订单，即存在未写入结束时间的订单
        ParkingLockTrade searchModel = new ParkingLockTrade();
        searchModel.setParkingLockId(parkingLockId);
        ParkingLockTrade errorTrade = parkingLockTradeMapper.selectNoEndTrade(searchModel);
        if (null == errorTrade) {
            return true;
        }

        LOGGER.warn(this.getClass() + "-cleanErrorTrade wait, trade id is " + errorTrade.getId());

        if (parkingLockTradeMapper.updateEndTimeAndStatusById(errorTrade.getId(), new Date()) < 1) {
            LOGGER.error(this.getClass() + "-cleanErrorTrade error, trade id is " + errorTrade.getId());
            return false;
        } else {
            LOGGER.warn(this.getClass() + "-cleanErrorTrade success, trade id is " + errorTrade.getId());
            return true;
        }
    }

    private Map<String, String> processLowerParkingLock(Long userId, ParkingLock parkingLock) {
        //调用第三方降锁接口
        if (!platformService.lowerLock(parkingLock)) {
            LOGGER.warn("ParkingLockServiceImpl-checkLowerParkingLockParam is error, lowerLock is error|userId=" + userId + "|parkingLockId=" + parkingLock.getId());
            return ResultUtil.getResultMap(ParkingLockConstants.LOWER_LOCK_IS_TIME_OUT_ERROR_CODE, ParkingLockConstants.LOWER_LOCK_IS_TIME_OUT_ERROR_MSG);
        }

        ParkingLockTrade trade = ParkingLockTrade.valueOf(userId, parkingLock.getId(), parkingLock.getPowerStationId());
        parkingLockTradeMapper.insertParkingLockTrade(trade);
        parkingLockTradeMapper.updateTradeNoById(trade.getId(), ParkingLockTradeNoGenerate.generateTradeNo(parkingLock.getId(), trade.getId()));

        return ResultUtil.getResultMap(ParkingLockConstants.SUCCESS_CODE, ParkingLockConstants.SUCCESS_MSG);
    }
}