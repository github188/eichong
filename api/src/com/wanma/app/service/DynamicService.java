package com.wanma.app.service;

import java.util.List;
import java.util.Map;

import com.bluemobi.product.common.AppPager;


public interface DynamicService {
	public List<Map<String, Object>> getDynamicList(AppPager pager);
}
