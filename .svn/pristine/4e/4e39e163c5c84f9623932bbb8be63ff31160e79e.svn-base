package com.wanma.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.AdvertMapper;
import com.wanma.app.service.AdvertService;

@Service
public class AdvertServiceImpl implements AdvertService {
	@Autowired
	private AdvertMapper adverMapper;

	/**
	 * 获取闪屏广告位
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getAdvertList(
			Map<String, Object> params) {
		return adverMapper.getAdvertList(params);
	}

	/**
	 * 获取动态列表
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getDynamicList(
			Map<String, Object> params) {
		return adverMapper.getDynamicList(params);
	}

}
