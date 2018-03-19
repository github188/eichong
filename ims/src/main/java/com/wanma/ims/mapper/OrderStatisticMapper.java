package com.wanma.ims.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface OrderStatisticMapper {

    public Double countTotalElectric(Map<String, Object> params);

    public Double countTotalElectricCharge(Long userId);

    public String countTotalOrder(Long userId);

    public String countTotalTodayOrder(Long userId);

    public Double countTotalCpyElectricCharge(Integer cpyNumber);

    public String countTotalCpyOrder(Integer cpyNumber);

    public Double countTotalCpyConsume(Integer cpyNumber);

    public String countTotalTodayCpyOrder(Integer Integer);

    public Map<String, String> countCpyCharge(Map<String, Object> params);

    public long countCpyChargeDetail(Map<String, Object> params);

    public List<Map<String, Object>> selectCpyChargeDetail(Map<String, Object> params);

    /**
     * 充电数据对比
     */
    public List<Map<String, Object>> countChargeDataTop(Map<String, Object> params);

    public long countChargeDataDetail(Map<String, Object> params);

    public List<Map<String, Object>> selectChargeDataDetail(Map<String, Object> params);

    /**
     * 充电费用占比
     */
    public Map<String, String> countChargeMoney(Map<String, Object> params);

    public List<Map<String, Object>> countChargeElectricMoney(Map<String, Object> params);

    public List<Map<String, Object>> countChargeServiceMoney(Map<String, Object> params);

    public long countChargeMoneyDetail(Map<String, Object> params);

    public List<Map<String, Object>> selectChargeMoneyDetail(Map<String, Object> params);

    /**
     * 城市充电统计
     */
    public Map<String, String> countCityCharge(Map<String, Object> params);

    public long countCityChargeDetail(Map<String, Object> params);

    public List<Map<String, String>> selectCityChargeDetail(Map<String, Object> params);

    /**
     * 充电数据对比-充电点
     */
    public long countPowerStationChargeDataDetail(Map<String, Object> params);

    public List<Map<String, Object>> selectPowerStationChargeDataDetail(Map<String, Object> params);

    public List<Map<String, Object>> selectPowerStationChargeDataDetailTop10(Map<String, Object> params);

    /**
     * 统计当天的实时数据
     */
    public Map<String, BigDecimal> countNowCharge(Map<String, Object> params);

    /**
     * 统计城市的实时数据
     */
    public List<Map<String, String>> selectNowCityCharge(Map<String, Object> params);

    /**
     * 统计充电站的实时数据
     */
    public List<Map<String, String>> selectNowPowerStationCharge(Map<String, Object> params);

    /**
     * 统计月份的充电数据
     */
    public List<Map<String, String>> selectMonthCharge(Map<String, Object> params);
}
