package com.wanma.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wanma.dao.CmsEchartsStatisticMapper;
import com.wanma.service.CmsAssetStatisticsService;
@Service
public class CmsAssetStatisticsServiceImpl implements CmsAssetStatisticsService {
	@Autowired
	private CmsEchartsStatisticMapper cmsEchartsStatisticMapper;
	
	/**
	 * 电桩数量统计
	 */
	@Override
	public Map<String, Object> queryPileInfoCount(Map<String, Object> params) {
		return cmsEchartsStatisticMapper.queryPileInfoCount(params);
	}

	/**
	 * 充电点数量统计
	 */
	@Override
	public Map<String, Object> queryAllPointCount(Map<String, Object> params) {
		return cmsEchartsStatisticMapper.queryAllPointCount(params);
	}

	/**
	 * 用户数量统计
	 */
	@Override
	public String queryUserCount(Map<String, Object> params) {
		return JSONObject.toJSONString(cmsEchartsStatisticMapper.queryUserCount(params));
	}

	/**
	 * 充电情况数量统计
	 */
	@Override
	public String queryChargeInfoCount(Map<String, Object> params) {
		return JSONObject.toJSONString(cmsEchartsStatisticMapper.queryChargeInfoCount(params));
	}

	/**
	 * 枪头情况数量统计
	 */
	@Override
	public String queryHeadInfoCount(Map<String, Object> params) {
		return JSONObject.toJSONString(cmsEchartsStatisticMapper.queryHeadInfoCount(params));
	}

	@Override
	public Map<String, Object> queryAllHeadCount(Map<String, Object> params) {
		return cmsEchartsStatisticMapper.queryAllHeadCount(params);
	}

	@Override
	public Map<String, Object> queryPileInfoCountInner(
			Map<String, Object> params) {
		return cmsEchartsStatisticMapper.queryPileInfoCountInner(params);
	}

	@Override
	public Map<String, Object> queryAllHeadCountInner(Map<String, Object> params) {
		return cmsEchartsStatisticMapper.queryAllHeadCountInner(params);
	}

	@Override
	public Map<String, Object> queryChargeInfoCountInner(Map<String, Object> params) {
		Map<String, Object> params1 = cmsEchartsStatisticMapper.queryChargeInfoCountInner(params);
		if(params.get("headId")!=null){
			String elPi_PowerSize =  cmsEchartsStatisticMapper.getPilePowerSize(params);
			params1.put("elPi_PowerSize", elPi_PowerSize);
		}
		return params1;
	}

	@Override
	public Map<String, Object> queryHeadInfoCountInner(Map<String, Object> params) {
		return cmsEchartsStatisticMapper.queryHeadInfoCountInner(params);
	}

	@Override
	public Object getCarLicenseValue(Map<String, Object> params) {
		return cmsEchartsStatisticMapper.getCarLicenseValue(params);
	}
	
}
