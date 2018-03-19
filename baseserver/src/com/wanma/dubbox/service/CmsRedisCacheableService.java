package com.wanma.dubbox.service;

import org.springframework.context.ApplicationContext;

public interface CmsRedisCacheableService {
	public void cachedDataInit(ApplicationContext context);
}
