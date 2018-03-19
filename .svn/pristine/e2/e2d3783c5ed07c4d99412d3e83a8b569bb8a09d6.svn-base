package com.wanma.model.sgcc;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/**
 * Created by zangyaoyi on 2017/6/5.
 * 同步充电设备统计信息
 */
public class SyncEquipmentStatsInfo {
    private String EquipmentID;//设备编码
    private String StatsDate;//统计日期
    private BigDecimal EquipmentElectricity;//充电设备累计电量
    private BigDecimal EquipmentChargeTime;//充电设备累计充电时长

    @JSONField(name = "EquipmentID")
    public String getEquipmentID() {
        return EquipmentID;
    }

    public void EquipmentID(String equipmentID) {
        EquipmentID = equipmentID;
    }

    @JSONField(name = "StatsDate")
    public String getStatsDate() {
        return StatsDate;
    }

    public void setStatsDate(String statsDate) {
        StatsDate = statsDate;
    }

    @JSONField(name = "EquipmentElectricity")

    public BigDecimal getEquipmentElectricity() {
        return EquipmentElectricity;
    }

    public void setEquipmentElectricity(BigDecimal equipmentElectricity) {
        EquipmentElectricity = equipmentElectricity;
    }

    @JSONField(name = "EquipmentChargeTime")
    public BigDecimal getEquipmentChargeTime() {
        return EquipmentChargeTime;
    }

    public void setEquipmentChargeTime(BigDecimal equipmentChargeTime) {
        EquipmentChargeTime = equipmentChargeTime;
    }
}
