package com.wanma.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsConfigMapper;
import com.wanma.service.CmsConfigService;

@Service
public class CmsConfigServiceImpl implements CmsConfigService {
	@Autowired
	private CmsConfigMapper configMapper;

	@Override
	public List<Map<String, Object>> getTypespanDictList() {
		return configMapper.getTypespanDictList();
	}

	@Override
	public List<Map<String, Object>> getRateInfoList() {
		return configMapper.getRateInfoList();
	}

	@Override
	public List<Map<String, Object>> getPileMakerDictList() {
		return configMapper.getPileMakerDictList();
	}

	@Override
	public List<Map<String, Object>> getCarCompanyList() {
		return configMapper.getCarCompanyList();
	}

	@Override
	public List<Map<String, Object>> searchProvinceList(
			Map<String, Object> params) {
		return configMapper.searchProvinceList(params);
	}

	@Override
	public List<HashMap<String, Object>> searchCityList(
			Map<String, Object> params) {
		return configMapper.searchCityList(params);
	}

	@Override
	public List<HashMap<String, Object>> searchAreaList(
			Map<String, Object> params) {
		return configMapper.searchAreaList(params);
	}

	@Override
	public List<Map<String, Object>> getCompanyDictList() {
		return configMapper.getCompanyDictList();
	}

}
