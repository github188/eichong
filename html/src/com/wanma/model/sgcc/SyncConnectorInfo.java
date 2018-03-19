package com.wanma.model.sgcc;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/**
 * Created by zangyaoyi on 2017/6/5.
 * 同步充电设备接口信息
 */
public class SyncConnectorInfo {

    private String EquipmentID;//所属充电设备编号
    private String ConnectorID;//充电设备接口编码，同一运营商内唯一
    private Integer ConnectorType;//充电设备接口类型
    private BigDecimal VoltageUpperLimits;//额定电压上限
    private BigDecimal VoltageLowerLimits;//额定电压下限
    private BigDecimal Current;//额定电流
    private Integer Power;//额定功率
    private Integer NationalStandard;//国家标准

    @JSONField(name = "EquipmentID")
    public String getEquipmentID() {
        return EquipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        EquipmentID = equipmentID;
    }

    @JSONField(name = "ConnectorID")
    public String getConnectorID() {
        return ConnectorID;
    }

    public void setConnectorID(String connectorID) {
        ConnectorID = connectorID;
    }

    @JSONField(name = "ConnectorType")
    public Integer getConnectorType() {
        return ConnectorType;
    }

    public void setConnectorType(Integer connectorType) {
        ConnectorType = connectorType;
    }

    @JSONField(name = "VoltageUpperLimits")
    public BigDecimal getVoltageUpperLimits() {
        return VoltageUpperLimits;
    }

    public void setVoltageUpperLimits(BigDecimal voltageUpperLimits) {
        VoltageUpperLimits = voltageUpperLimits;
    }

    @JSONField(name = "VoltageLowerLimits")
    public BigDecimal getVoltageLowerLimits() {
        return VoltageLowerLimits;
    }

    public void setVoltageLowerLimits(BigDecimal voltageLowerLimits) {
        VoltageLowerLimits = voltageLowerLimits;
    }

    @JSONField(name = "Current")
    public BigDecimal getCurrent() {
        return Current;
    }

    public void setCurrent(BigDecimal current) {
        Current = current;
    }

    @JSONField(name = "Power")
    public Integer getPower() {
        return Power;
    }

    public void setPower(Integer power) {
        Power = power;
    }

    @JSONField(name = "NationalStandard")
    public Integer getNationalStandard() {
        return NationalStandard;
    }

    public void setNationalStandard(Integer nationalStandard) {
        NationalStandard = nationalStandard;
    }
}
