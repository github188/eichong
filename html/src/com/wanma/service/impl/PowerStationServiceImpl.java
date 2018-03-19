package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.PowerStationMapper;
import com.wanma.model.simple.PowerStation;
import com.wanma.service.PowerStationService;

@Service
public class PowerStationServiceImpl implements PowerStationService{
	@Autowired
	private PowerStationMapper powerStationMapper;

	@Override
	public List<Map<String, Object>> getPkPowerStations(String org) {
		return powerStationMapper.getPkPowerStations(org);
	}

	@Override
	public PowerStation getPowerStationList(Integer pkPowerStation) {
		return powerStationMapper.getPowerStationList(pkPowerStation);
	}

	@Override
	public int getCountResult(PowerStation ps) {
		return powerStationMapper.getCountResult(ps);
	}

}
