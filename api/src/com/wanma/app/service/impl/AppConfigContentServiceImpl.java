package com.wanma.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.AppConfigContentMapper;

@Service
public class AppConfigContentServiceImpl {
	
	/**
	 * 根据id获取配置表相关列表
	 * @return
	 */
	public List<Map<String, Object>> getConfigContentListByCpId(String cpId){
		return appConfigContentMapper.getConfigContentListByCpId(cpId);
	}
	
	@Autowired
	AppConfigContentMapper appConfigContentMapper;
}
