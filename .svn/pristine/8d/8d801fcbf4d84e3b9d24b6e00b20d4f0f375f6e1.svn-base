package com.wanma.ims.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface OrderStatisticService {
     
	/** 总电量*/
	public Double countTotalElectric(Map<String, Object> params);
	/** 用户总电量*/
	public Double countTotalElectricCharge(Long userId);
	
	/** 用户订单*/
	public String countTotalOrder(Long userId);
	
	/** 用户今日订单*/
	public String countTotalTodayOrder(Long userId);
	
	/** 公司累计充电量*/
	public Double countTotalCpyElectricCharge(Integer cpyNumber);
	
	/** 公司累计订单*/
	public String countTotalCpyOrder(Integer cpyNumber);
	
	/** 公司累计消费*/
	public Double countTotalCpyConsume(Integer cpyNumber);
	
	/** 公司今日订单*/
	public String countTotalTodayCpyOrder(Integer cpyNumber);
	
	/** 渠道充电统计*/
	public Map<String,String> countCpyCharge(Map<String,Object> params);
	
	public long countCpyChargeDetail(Map<String,Object> params);
	
	public List<Map<String, Object>> getCpyChargeDetail(Map<String,Object> params);
	
	/** 充电数据对比*/
	public List<Map<String,Object>> countChargeDataTop10(Map<String, Object> params);
	 
	public long countChargeDataDetail(Map<String,Object> params);
	
	public List<Map<String,Object>> getChargeDataDetail(Map<String, Object> params);
	
	/** 充电数据占比*/
	public Map<String,String> countChargeMoney(Map<String,Object> params);
	
	public List<Map<String,Object>> countChargeElectricMoney(Map<String,Object> params);
	
	public List<Map<String,Object>> countChargeServiceMoney(Map<String,Object> params);
	
	public long countChargeMoneyDetail(Map<String,Object> params);
	
	public List<Map<String,Object>> getChargeMoneyDetail(Map<String,Object> params);

	/** 城市充电统计*/
	public Map<String,String> countCityCharge(Map<String,Object> params);

	public long countCityChargeDetail(Map<String,Object> params);

	public List<Map<String, String>> getCityChargeDetail(Map<String,Object> params);

	/** 充电数据对比*/
	public long countPowerStationChargeDataDetail(Map<String,Object> params);

	public List<Map<String,Object>> getPowerStationChargeDataDetail(Map<String, Object> params);

	public List<Map<String,Object>> getPowerStationChargeDataDetailTop10(Map<String, Object> params);

	/**
	 * 统计当天的实时数据
	 */
	public Map<String,BigDecimal> countNowCharge(Map<String,Object> params);

	/**
	 * 获取城市当天的充电量
	 */
	public List<Map<String, String>> getNowCityCharge(Map<String,Object> params);

	/**
	 * 获取充电站当天的充电量
	 */
	public List<Map<String, String>> getNowPowerStationCharge(Map<String,Object> params);

	/**
	 * 统计月份的充电数据
	 */
	public List<Map<String, String>> getMonthCharge(Map<String, Object> params);

}
