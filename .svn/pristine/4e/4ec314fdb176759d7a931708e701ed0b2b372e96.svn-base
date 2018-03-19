package com.wanma.ims.service.parkinglock;

import com.wanma.ims.common.domain.ParkingLockDO;
import com.wanma.ims.service.parkinglock.constant.PlatformErrorCode;
import com.wanma.ims.util.SerializationUtil;
import com.wanma.ims.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by xyc on 2018/1/29.
 * 地锁平台抽象类
 */
public abstract class AbstractPlatform implements Platform {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    /**
     * 调用第三方地锁平台失败时重试次数
     */
    public static final int RETRY_TIMES = 3;

    @Override
    public ParkingLockDO getConvertLock(String result) {
        return parkingLockConvert(result);
    }

    @Override
    public void operatingLock(ParkingLockDO lock, String operating) throws Exception {
        String platformOperating = operatingConvert(operating);
        if (null == platformOperating || "".equals(platformOperating)) {
            LOGGER.error(this.getClass() + "-getConvertLock is error, get platformOperating is null|lockId={}", lock.getId());
            throw new Exception("get platformOperating is null");
        }

        boolean res;

        for (int i = 0; i < RETRY_TIMES; i++) {
            res = post(lock, platformOperating);
            if (res) {
                break;
            }

            long operatingLockFailedSleepTime = 200L;
            LOGGER.error("operatingLock is error,retry after " + operatingLockFailedSleepTime + " milliseconds sleep |retryTimes=" + i + "|lock=" + SerializationUtil.gson2String(lock));
            ThreadUtil.safeSleep(operatingLockFailedSleepTime, getPlatformName() + " operatingLock is error,sleep waiting error,waiting retry");
            fillFailLock(lock);
        }
    }

    private void fillFailLock(ParkingLockDO parkingLock) {
        parkingLock.setPlatformModifyTime(new Date());
        parkingLock.setPlatformErrorCode(PlatformErrorCode.ERROR_CODE);
        parkingLock.setPlatformErrorMsg(PlatformErrorCode.ERROR_MSG);
    }

    protected abstract ParkingLockDO parkingLockConvert(String result);

    protected abstract String operatingConvert(String operating);

    protected abstract boolean post(ParkingLockDO parkingLock, String operating);
}
