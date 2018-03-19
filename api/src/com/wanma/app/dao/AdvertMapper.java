package com.wanma.app.dao;

import java.util.List;
import java.util.Map;

public interface AdvertMapper {

	/**
	 * 获取闪屏广告位
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getAdvertList(
			Map<String, Object> params);

	/**
	 * 获取动态列表
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getDynamicList(
			Map<String, Object> params);
}
