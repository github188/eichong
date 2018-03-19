package com.wanma.app.service;

import java.util.List;
import java.util.Map;


public interface ApplyEpService {
	/**
	 * 添加建桩申请
	 * @param params
	 */
	public void saveApplyEp(Map<String, String> params) throws Exception;
	/**
	 * 根据用户id获取申请列表
	 * @param uid
	 * @return
	 */
	public List<Map<String, String>> getApplyEpList(int uid);
}
