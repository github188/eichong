package com.wanma.app.service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 首页广告业务处理接口
 * @author songjf
 * @createTime：2015-4-23 上午10:38:06
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface AppHomepageService {

	/**
	 * @Title: findHomepages
	 * @Description: 获取首页广告
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> findHomepages(Map<String, Object> params);

}
