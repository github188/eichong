package com.wanma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wanma.dao.CmsStatisticMapper;
import com.wanma.service.CmsEchartDataCountService;

@Service
public class CmsEchartDataCountServiceImpl implements CmsEchartDataCountService{
	@Autowired
	private CmsStatisticMapper cmsStatisticMapper;

	public String getZcPeopleCount() {
		JSONObject obj = new JSONObject();
		obj.put("data", cmsStatisticMapper.getZcPeopleCount());
		return obj.toString();
	}

	public String getCzPeopleCount() {
		JSONObject obj = new JSONObject();
		obj.put("data", cmsStatisticMapper.getCzPeopleCount());
		return obj.toString();
	}

	public String getAllPileCount() {
		JSONObject obj = new JSONObject();
		obj.put("data", cmsStatisticMapper.getAllPileCount());
		return obj.toString();
	}

	@Override
	public String getAichongPileCount() {
		JSONObject obj = new JSONObject();
		obj.put("data", cmsStatisticMapper.getAichongPileCount());
		return obj.toString();
	}
}
