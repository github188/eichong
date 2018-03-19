package com.wanma.model.sgcc;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zangyaoyi on 2017/6/1.
 * 同步充电设备信息
 */
public class SyncEquipmentInfo {
    private Integer StationID;//所属充电站ID
    private String EquipmentID;// 设备编码
    private String ManufacturerID;// 设备生产商组织机构代码
    private String EquipmentModel;// 设备型号
    private Integer EquipmentType;// elPi_ChargingMode
    private String Power;//充电设备总功率(小数点后一位)

    private String OpenForBusinessDate;//投入运营日期（yyyy-MM-dd）
    private Integer EquipmentStatus;//设备状态
    @JSONField(name = "StationID")

    public Integer getStationID() {
        return StationID;
    }

    public void setStationID(Integer stationID) {
        StationID = stationID;
    }
    @JSONField(name = "EquipmentID")
    public String getEquipmentID() {
        return EquipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        EquipmentID = equipmentID;
    }
    @JSONField(name = "ManufacturerID")
    public String getManufacturerID() {
        return ManufacturerID;
    }

    public void setManufacturerID(String manufacturerID) {
        ManufacturerID = manufacturerID;
    }
    @JSONField(name = "EquipmentModel")
    public String getEquipmentModel() {
        return EquipmentModel;
    }

    public void setEquipmentModel(String equipmentModel) {
        EquipmentModel = equipmentModel;
    }
    @JSONField(name = "EquipmentType")
    public Integer getEquipmentType() {
        return EquipmentType;
    }

    public void setEquipmentType(Integer equipmentType) {
        EquipmentType = equipmentType;
    }
    @JSONField(name = "Power")
    public String getPower() {
        return Power;
    }

    public void setPower(String power) {
        Power = power;
    }
    @JSONField(name = "OpenForBusinessDate")
    public String getOpenForBusinessDate() {
        return OpenForBusinessDate;
    }

    public void setOpenForBusinessDate(String openForBusinessDate) {
        OpenForBusinessDate = openForBusinessDate;
    }
    @JSONField(name = "EquipmentStatus")
    public Integer getEquipmentStatus() {
        return EquipmentStatus;
    }

    public void setEquipmentStatus(Integer equipmentStatus) {
        EquipmentStatus = equipmentStatus;
    }
}
