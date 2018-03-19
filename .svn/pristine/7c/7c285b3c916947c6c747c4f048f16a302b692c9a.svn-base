package com.wanma.ims.service.parkinglock.impl;

import com.google.common.base.Strings;
import com.wanma.ims.common.domain.JpushDO;
import com.wanma.ims.common.domain.ParkingLockDO;
import com.wanma.ims.common.domain.ParkingLockTradeDO;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.mapper.ParkingLockMapper;
import com.wanma.ims.mapper.ParkingLockTradeMapper;
import com.wanma.ims.mapper.UserCompanyMapper;
import com.wanma.ims.service.AppJpushService;
import com.wanma.ims.service.parkinglock.Platform;
import com.wanma.ims.service.parkinglock.PlatformFactory;
import com.wanma.ims.service.parkinglock.PlatformService;
import com.wanma.ims.service.parkinglock.constant.ParkingLockOperating;
import com.wanma.ims.service.parkinglock.constant.PlatformErrorCode;
import com.wanma.ims.util.JPushUtil;
import com.wanma.ims.util.SerializationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by xyc on 2018/2/2.
 * 地锁平台逻辑处理类
 */
@Service
public class PlatformServiceImpl implements PlatformService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    /**
     * 推送到app的成功落锁code
     */
    private static final String LOWER_LOCK_SUCCESS = "100";
    /**
     * 推送到app的失败落锁code
     */
    private static final String LOWER_LOCK_FAIL = "10004";

    @Autowired
    private AppJpushService appJpushService;
    @Autowired
    private ParkingLockMapper parkingLockMapper;
    @Autowired
    private ParkingLockTradeMapper parkingLockTradeMapper;
    @Autowired
    private UserCompanyMapper userCompanyMapper;

    @Override
    public boolean asyncModifyLockStatus(String result, Long parkingLockPlatform) {
        LOGGER.debug(this.getClass() + "-asyncModifyLockStatus|result={}|parkingLockPlatform={}", result, parkingLockPlatform);
        Platform platform = PlatformFactory.getPlatform(parkingLockPlatform);
        if (null == platform) {
            LOGGER.error(this.getClass() + "-asyncModifyLockStatus is error, create platform is fail|result={}|parkingLockPlatform={}", result, parkingLockPlatform);
            return false;
        }

        ParkingLockDO platformLock = platform.getConvertLock(result);
        if (null == platformLock) {
            LOGGER.error(this.getClass() + "-asyncModifyLockStatus is error, getConvertLock is null|result={}|parkingLockPlatform={}", result, parkingLockPlatform);
            return false;
        }

        ParkingLockDO oldLock = parkingLockMapper.selectByPlatformLockKey(parkingLockPlatform, platformLock.getPlatformLockKey());
        if (null == oldLock) {
            LOGGER.error(this.getClass() + "-asyncModifyLockStatus is error, oldLock is null|result={}|parkingLockPlatform={}|platformLock={}", result, parkingLockPlatform, SerializationUtil.gson2String(platformLock));
            return false;
        }

        return processPlatformLock(platformLock, oldLock);
    }

    private boolean processPlatformLock(ParkingLockDO platformLock, ParkingLockDO oldLock) {
        LOGGER.debug(this.getClass() + "-processPlatformLock is start|platformLock={}|oldLock={}", SerializationUtil.gson2String(platformLock), SerializationUtil.gson2String(oldLock));
        if (oldLock.getPlatformModifyTime().after(platformLock.getPlatformModifyTime())) {
            LOGGER.warn(this.getClass() + "-processPlatformLock is end, not process,async msg is old|platformLock={}|oldLock={}", SerializationUtil.gson2String(platformLock), SerializationUtil.gson2String(oldLock));
            return true;
        }

        platformLock.setId(oldLock.getId());

        switch (platformLock.getStatus()) {
            case WanmaConstants.PARKING_LOCK_NORMAL:
                processRiseLock(platformLock, oldLock);
                break;
            case WanmaConstants.PARKING_LOCK_USING:
                processLowerLock(platformLock, oldLock);
                break;
            case WanmaConstants.PARKING_LOCK_ERROR:
                processLockError(platformLock, oldLock);
                break;
            default:
                break;
        }

        return updatePlatformInfo(platformLock);
    }

    private void processRiseLock(ParkingLockDO platformLock, ParkingLockDO oldLock) {
        ParkingLockTradeDO usingTrade = getTradeByStatusAndLockId(oldLock.getId(), WanmaConstants.PARKING_LOCK_TRADE_STATUS_LOWER_SUCCESS);
        if (null == usingTrade) {
            LOGGER.info(this.getClass() + "-processRiseLock is end, not process trade,async msg is maybe old|platformLock={}|oldLock={}", SerializationUtil.gson2String(platformLock), SerializationUtil.gson2String(oldLock));
            return;
        }

        ParkingLockTradeDO modifyTrade = new ParkingLockTradeDO();
        modifyTrade.setId(usingTrade.getId());
        modifyTrade.setStatus(WanmaConstants.PARKING_LOCK_TRADE_STATUS_RISE_SUCCESS);
        modifyTrade.setParkingLockEndTime(platformLock.getPlatformModifyTime());
        updatePlatformTrade(modifyTrade);
    }

    private void processLowerLock(ParkingLockDO platformLock, ParkingLockDO oldLock) {
        ParkingLockTradeDO usingTrade = getTradeByStatusAndLockId(oldLock.getId(), WanmaConstants.PARKING_LOCK_TRADE_STATUS_LOWER);
        if (null == usingTrade) {
            LOGGER.info(this.getClass() + "-processLowerLock is end, not process trade, async msg is maybe old|platformLock={}|oldLock={}", SerializationUtil.gson2String(platformLock), SerializationUtil.gson2String(oldLock));
            return;
        }

        ParkingLockTradeDO modifyTrade = new ParkingLockTradeDO();
        modifyTrade.setId(usingTrade.getId());
        modifyTrade.setStatus(WanmaConstants.PARKING_LOCK_TRADE_STATUS_LOWER_SUCCESS);
        modifyTrade.setParkingLockStartTime(platformLock.getPlatformModifyTime());
        updatePlatformTrade(modifyTrade);

        pushLowerLockResult(usingTrade.getUserId(), true, platformLock.getAutoRiseLockTime());
    }

    private void processLockError(ParkingLockDO platformLock, ParkingLockDO oldLock) {
        ParkingLockTradeDO usingTrade = getTradeByStatusAndLockId(oldLock.getId(), WanmaConstants.PARKING_LOCK_TRADE_STATUS_LOWER);
        if (null == usingTrade) {
            LOGGER.info(this.getClass() + "-processLockError is end, not process trade, async msg is maybe old|platformLock={}|oldLock={}", SerializationUtil.gson2String(platformLock), SerializationUtil.gson2String(oldLock));
            return;
        }

        ParkingLockTradeDO modifyTrade = new ParkingLockTradeDO();
        modifyTrade.setId(usingTrade.getId());
        modifyTrade.setStatus(WanmaConstants.PARKING_LOCK_TRADE_STATUS_LOWER_ERROR);
        modifyTrade.setParkingLockEndTime(platformLock.getPlatformModifyTime());
        updatePlatformTrade(modifyTrade);

        pushLowerLockResult(usingTrade.getUserId(), false, platformLock.getAutoRiseLockTime());
    }

    private ParkingLockTradeDO getTradeByStatusAndLockId(Long lockId, Integer status) {
        ParkingLockTradeDO searchModel = new ParkingLockTradeDO();
        searchModel.setParkingLockId(lockId);
        searchModel.setStatus(status);
        return parkingLockTradeMapper.selectByStatusAndLockId(searchModel);
    }

    private void pushLowerLockResult(Long userId, boolean isSuccess, Integer waitTime) {
        JpushDO jpush = appJpushService.getByuserInfo(userId);
        if (null == jpush || Strings.isNullOrEmpty(jpush.getJpushRegistrationid())) {
            LOGGER.warn(this.getClass() + "-pushLowerLockResult is error, jpush or getJpushRegistrationid is null|userId={}|isSuccess={}", userId, isSuccess);
            return;
        }

        //判断用户是不是渠道用户从而使用对应的极光key
        boolean isCompanyUser = userCompanyMapper.selectUserCompanyByUserId(userId) != null;

        Map<String, String> msgMap = JPushUtil.getBasicMsgMap("地锁标题", "地锁内容");
        msgMap.put("errorCode", isSuccess ? LOWER_LOCK_SUCCESS : LOWER_LOCK_FAIL);
        msgMap.put("waitTime", waitTime == null ? null : "" + waitTime);
        JPushUtil.jpushCustom(msgMap, jpush.getJpushRegistrationid(), "14", isCompanyUser);
    }

    private boolean updatePlatformTrade(ParkingLockTradeDO trade) {
        if (parkingLockTradeMapper.updateByIdSelective(trade) < 1) {
            LOGGER.warn(this.getClass() + "-updatePlatformTrade is error, update to db error|parkingLockTrade={}", SerializationUtil.gson2String(trade));
            return false;
        }
        return true;
    }

    private boolean updatePlatformInfo(ParkingLockDO parkingLock) {
        if (parkingLockMapper.updateLockPlatformInfo(parkingLock) < 1) {
            LOGGER.warn(this.getClass() + "-updatePlatformInfo is error, update to db error|parkingLock={}", SerializationUtil.gson2String(parkingLock));
            return false;
        }
        LOGGER.debug(this.getClass() + "-processPlatformLock is success|modifyLock={}", SerializationUtil.gson2String(parkingLock));
        return true;
    }

    @Override
    public boolean getLockStatus(ParkingLockDO parkingLock) {
        Platform platform = PlatformFactory.getPlatform(parkingLock.getParkingLockPlatform());
        if (null == platform) {
            LOGGER.error(this.getClass() + "-getLockStatus is error, create platform is fail|parkingLock={}", SerializationUtil.gson2String(parkingLock));
            return false;
        }

        try {
            platform.operatingLock(parkingLock, ParkingLockOperating.GET_LOCK_STATUS);
            return PlatformErrorCode.isTransferSuccess(parkingLock.getPlatformErrorCode(), parkingLock.getPlatformErrorMsg());
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-checkLockIsNormal is error|lockId=" + parkingLock.getId(), e);
        }

        return false;
    }

    @Override
    public boolean lowerLock(ParkingLockDO parkingLock) {
        Platform platform = PlatformFactory.getPlatform(parkingLock.getParkingLockPlatform());
        if (platform == null) {
            LOGGER.error(this.getClass() + "-lowerLock is error, create platform is fail|parkingLock={}", SerializationUtil.gson2String(parkingLock));
            return false;
        }

        try {
            platform.operatingLock(parkingLock, ParkingLockOperating.LOWER_LOCK);
            return PlatformErrorCode.isTransferSuccess(parkingLock.getPlatformErrorCode(), parkingLock.getPlatformErrorMsg());
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-lowerLock is error|lockId=" + parkingLock.getId(), e);
        }

        return false;
    }

    @Override
    public boolean riseLock(ParkingLockDO parkingLock) {
        Platform platform = PlatformFactory.getPlatform(parkingLock.getParkingLockPlatform());
        if (platform == null) {
            LOGGER.error(this.getClass() + "-riseLock is error, create platform is fail|parkingLock={}", SerializationUtil.gson2String(parkingLock));
            return false;
        }

        try {
            platform.operatingLock(parkingLock, ParkingLockOperating.RISE_LOCK);
            return PlatformErrorCode.isTransferSuccess(parkingLock.getPlatformErrorCode(), parkingLock.getPlatformErrorMsg());
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-riseLock is error|lockId=" + parkingLock.getId(), e);
        }

        return false;
    }
}
