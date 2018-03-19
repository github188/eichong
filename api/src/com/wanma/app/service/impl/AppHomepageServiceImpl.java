package com.wanma.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.AppHomepageMapper;
import com.wanma.app.service.AppHomepageService;
import com.wanma.common.WanmaConstants;

/**
 * @Description: 首页广告业务处理接口
 * @author songjf
 * @createTime：2015-4-23 上午10:38:06
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class AppHomepageServiceImpl implements AppHomepageService {
	/* 首页广告表操作dao */
	@Autowired
	private AppHomepageMapper homepageMapper;

	@Override
	public List<Map<String, Object>> findHomepages(Map<String, Object> params) {
		params.put("hopaStatus", WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
		List<Map<String, Object>> homeList = homepageMapper
				.findHomepages(params);
		if (homeList != null) {
			for (int i = 0; i < homeList.size(); i++) {
				String hopaImage = (String) homeList.get(i).get("hopaImage");
				if (hopaImage == null) {
					homeList.remove(i);
					i--;
				}
			}
		}
		return homeList;
	}
	
}
