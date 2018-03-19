package com.wanma.app.dao;

import java.util.List;
import java.util.Map;


public interface AppCarGarageMapper {
	/**
	 * 获取修理厂列表
	 * @return
	 */
	public List<Map<String, Object>> getCarGarageList(Map<String, Object> params);
}
