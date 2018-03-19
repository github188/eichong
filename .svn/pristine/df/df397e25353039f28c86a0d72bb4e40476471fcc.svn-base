package com.wanma.app.service.parkinglock.wiparking;

/**
 * Created by xyc on 2018/1/29.
 * 慧泊金返回结果实体类
 */
public class WiParkingResult {

    /**
     * errCode 0:成功。1:失败。-1:服务器异常
     */
    private Integer errCode;

    private String msg;

    private ResultData ResultData;

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public WiParkingResult.ResultData getResultData() {
        return ResultData;
    }

    public void setResultData(WiParkingResult.ResultData resultData) {
        ResultData = resultData;
    }

    public class ResultData {

        /**
         * 地锁状态-0:已降下、1:已升起、2:故障（故障码）、3:已锁车、4:已休眠、5:未知、6:离网
         */
        private Integer lockStatus;

        /**
         * 车位状态-0:无车、1:有车、2:未知、3:故障
         */
        private Integer parkStatus;

        /**
         * 报警状态-0：无报警，1：降五次不成功和低电量故障，2：降锁五次不成功故障，3：低电量故障
         */
        private Integer alarmStatus;

        /**
         * 地锁电池余量 单位为10%
         */
        private Integer battery;

        /**
         * 地锁对接Key
         */
        private Integer spaceId;

        public Integer getLockStatus() {
            return lockStatus;
        }

        public void setLockStatus(Integer lockStatus) {
            this.lockStatus = lockStatus;
        }

        public Integer getParkStatus() {
            return parkStatus;
        }

        public void setParkStatus(Integer parkStatus) {
            this.parkStatus = parkStatus;
        }

        public Integer getAlarmStatus() {
            return alarmStatus;
        }

        public void setAlarmStatus(Integer alarmStatus) {
            this.alarmStatus = alarmStatus;
        }

        public Integer getBattery() {
            return battery;
        }

        public void setBattery(Integer battery) {
            this.battery = battery;
        }

        public Integer getSpaceId() {
            return spaceId;
        }

        public void setSpaceId(Integer spaceId) {
            this.spaceId = spaceId;
        }
    }
}
