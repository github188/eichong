package com.wanma.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.web.dao.WebConfigContentMapper;

@Service
public class WebConfigContentServiceImpl {
	@Autowired
	WebConfigContentMapper webConfigContentMapper;
	/**
	 * 根据id获取配置表相关列表
	 * @return
	 */
	public List<Map<String, Object>> getConfigContentListByCpId(String cpId){
		return webConfigContentMapper.getConfigContentListByCpId(cpId);
	}
	
	
}
