package com.wanma.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.common.AppPager;
import com.wanma.app.dao.DynamicMapper;
import com.wanma.app.service.DynamicService;

@Service
public class DynamicServiceImpl implements DynamicService {
	
	@Autowired
	private DynamicMapper dynamicMapper;
	
	public List<Map<String, Object>> getDynamicList(AppPager pager){
		return dynamicMapper.getDynamicList(pager); 
	}
}
