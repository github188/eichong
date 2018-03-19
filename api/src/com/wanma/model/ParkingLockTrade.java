package com.wanma.model;

import com.wanma.common.ParkingLockConstants;

import java.util.Date;

/**
 * 地锁订单实体类
 *
 * @author xyc
 */
public class ParkingLockTrade {
    /**
     * 主键
     */
    private Long id;

    /**
     * 地锁订单流水号
     */
    private String tradeNo;

    /**
     * 地锁id
     */
    private Long parkingLockId;

    /**
     * 生成订单时地锁所在的充电点id
     */
    private Long powerStationId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 订单状态 0 降锁中 1 降锁成功 2 升锁中 3 升锁成功 4 降锁故障 5 升锁故障 6 订单结束时间异常
     */
    private Integer status;

    /**
     * 地锁开始使用时间
     */
    private Date parkingLockStartTime;

    /**
     * 地锁结束使用时间
     */
    private Date parkingLockEndTime;

    /**
     * 充电订单号
     */
    private String chargeOrderCode;

    public static ParkingLockTrade valueOf(Long userId, Long parkingLockId, Long powerStationId) {
        ParkingLockTrade trade = new ParkingLockTrade();
        trade.setUserId(userId);
        trade.setParkingLockId(parkingLockId);
        trade.setPowerStationId(powerStationId);
        trade.setStatus(ParkingLockConstants.PARKING_LOCK_TRADE_STATUS_LOWER);
        trade.setParkingLockStartTime(new Date());
        return trade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Long getParkingLockId() {
        return parkingLockId;
    }

    public void setParkingLockId(Long parkingLockId) {
        this.parkingLockId = parkingLockId;
    }

    public Long getPowerStationId() {
        return powerStationId;
    }

    public void setPowerStationId(Long powerStationId) {
        this.powerStationId = powerStationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getParkingLockStartTime() {
        return parkingLockStartTime;
    }

    public void setParkingLockStartTime(Date parkingLockStartTime) {
        this.parkingLockStartTime = parkingLockStartTime;
    }

    public Date getParkingLockEndTime() {
        return parkingLockEndTime;
    }

    public void setParkingLockEndTime(Date parkingLockEndTime) {
        this.parkingLockEndTime = parkingLockEndTime;
    }

    public String getChargeOrderCode() {
        return chargeOrderCode;
    }

    public void setChargeOrderCode(String chargeOrderCode) {
        this.chargeOrderCode = chargeOrderCode;
    }
}