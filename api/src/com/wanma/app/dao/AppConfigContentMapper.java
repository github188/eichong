package com.wanma.app.dao;

import java.util.List;
import java.util.Map;

public interface AppConfigContentMapper {
	/**
	 * 根据主配置id获取配置项列表
	 * @return
	 */
	public List<Map<String, Object>> getConfigContentListByCpId(String cpId);
	
	public List<Map<String, Object>> getAllConfigContent();
}
