package com.wanma.service;

import java.util.Map;

public interface CmsAssetStatisticsService {
	public Map<String, Object> queryAllPointCount(Map<String, Object> params);
	public Map<String, Object> queryPileInfoCount(Map<String, Object> params);
	public String queryUserCount(Map<String, Object> params);
	public String queryChargeInfoCount(Map<String, Object> params);
	public String queryHeadInfoCount(Map<String, Object> params);
	public Map<String, Object> queryAllHeadCount(
			Map<String, Object> params);
	public Map<String, Object> queryPileInfoCountInner(
			Map<String, Object> params);
	public Map<String, Object> queryAllHeadCountInner(
			Map<String, Object> params);
	public Map<String, Object> queryChargeInfoCountInner(Map<String, Object> params);
	public Map<String, Object> queryHeadInfoCountInner(Map<String, Object> params);
	public Object getCarLicenseValue(Map<String, Object> params);
}
