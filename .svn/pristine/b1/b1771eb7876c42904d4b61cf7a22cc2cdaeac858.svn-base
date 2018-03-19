package com.wanma.ims.service.impl;

import com.wanma.ims.mapper.OrderStatisticMapper;
import com.wanma.ims.service.OrderStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class OrderStatisticServiceImpl implements OrderStatisticService{

	@Autowired
	private OrderStatisticMapper orderStatisticMapper;
	
	@Override
	public Double countTotalElectricCharge(Long userId) {
		return orderStatisticMapper.countTotalElectricCharge(userId);
	}

	@Override
	public String countTotalOrder(Long userId) {
		return orderStatisticMapper.countTotalOrder(userId);
	}

	@Override
	public String countTotalTodayOrder(Long userId) {
		return orderStatisticMapper.countTotalTodayOrder(userId);
	}

	@Override
	public String countTotalCpyOrder(Integer cpyNumber) {
		return orderStatisticMapper.countTotalCpyOrder(cpyNumber);
	}

	@Override
	public String countTotalTodayCpyOrder(Integer cpyNumber) {
		return orderStatisticMapper.countTotalTodayCpyOrder(cpyNumber);
	}

	@Override
	public Double countTotalCpyElectricCharge(Integer cpyNumber) {
		return orderStatisticMapper.countTotalCpyElectricCharge(cpyNumber);
	}

	@Override
	public Double countTotalCpyConsume(Integer cpyNumber) {
		return orderStatisticMapper.countTotalCpyConsume(cpyNumber);
	}

	@Override
	public Map<String, String> countCpyCharge(Map<String,Object> params) {
		return orderStatisticMapper.countCpyCharge(params);
	}
	
	@Override
	public long countCpyChargeDetail(Map<String,Object> params) {
		return orderStatisticMapper.countCpyChargeDetail(params);
	}

	@Override
	public List<Map<String, Object>> getCpyChargeDetail(Map<String,Object> params) {
		return orderStatisticMapper.selectCpyChargeDetail(params);
	}

	@Override
	public List<Map<String, Object>> countChargeDataTop10(Map<String, Object> params) {
		return orderStatisticMapper.countChargeDataTop(params);
	}
	
	@Override
	public long countChargeDataDetail(Map<String, Object> params) {
		return orderStatisticMapper.countChargeDataDetail(params);
	}

	@Override
	public List<Map<String, Object>> getChargeDataDetail(Map<String, Object> params) {
		return orderStatisticMapper.selectChargeDataDetail(params);
	}

	@Override
	public Map<String, String> countChargeMoney(Map<String, Object> params) {
		return orderStatisticMapper.countChargeMoney(params);
	}
	
	@Override
	public List<Map<String, Object>> countChargeElectricMoney(Map<String, Object> params) {
		return orderStatisticMapper.countChargeElectricMoney(params);
	}

	@Override
	public List<Map<String, Object>> countChargeServiceMoney(Map<String, Object> params) {
		return orderStatisticMapper.countChargeServiceMoney(params);
	}

	@Override
	public long countChargeMoneyDetail(Map<String, Object> params) {
		return orderStatisticMapper.countChargeMoneyDetail(params);
	}

	@Override
	public List<Map<String, Object>> getChargeMoneyDetail(Map<String, Object> params) {
		return orderStatisticMapper.selectChargeMoneyDetail(params);
	}

	@Override
	public Map<String, String> countCityCharge(Map<String, Object> params) {
		return orderStatisticMapper.countCityCharge(params);
	}

	@Override
	public long countCityChargeDetail(Map<String, Object> params) {
		return orderStatisticMapper.countCityChargeDetail(params);
	}

	@Override
	public List<Map<String, String>> getCityChargeDetail(Map<String, Object> params) {
		return orderStatisticMapper.selectCityChargeDetail(params);
	}

	@Override
	public long countPowerStationChargeDataDetail(Map<String, Object> params) {
		return orderStatisticMapper.countPowerStationChargeDataDetail(params);
	}

	@Override
	public List<Map<String, Object>> getPowerStationChargeDataDetail(Map<String, Object> params) {
		return orderStatisticMapper.selectPowerStationChargeDataDetail(params);
	}

	@Override
	public List<Map<String, Object>> getPowerStationChargeDataDetailTop10(Map<String, Object> params) {
		return orderStatisticMapper.selectPowerStationChargeDataDetailTop10(params);
	}

	@Override
	public Double countTotalElectric(Map<String, Object> params) {
		return orderStatisticMapper.countTotalElectric(params);
	}


	@Override
	public Map<String, BigDecimal> countNowCharge(Map<String, Object> params) {
		return orderStatisticMapper.countNowCharge(params);
	}

	@Override
	public List<Map<String, String>> getNowCityCharge(Map<String, Object> params) {
		return orderStatisticMapper.selectNowCityCharge(params);
	}

    @Override
    public List<Map<String, String>> getNowPowerStationCharge(Map<String, Object> params) {
        return orderStatisticMapper.selectNowPowerStationCharge(params);
    }

    @Override
    public List<Map<String, String>> getMonthCharge(Map<String, Object> params) {
        return orderStatisticMapper.selectMonthCharge(params);
    }
}
