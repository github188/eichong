package com.wanma.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.model.Cache;
import com.wanma.service.CacheService;
import com.wanma.service.impl.CacheServiceImpl;

@Controller
@RequestMapping("/cache")
public class CacheController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);

	@ResponseBody
	@RequestMapping("/clearCache")
	public String cahche(String OperatorID) {
		LOGGER.info("~~~~~~~~~~~~~~传入参数:",OperatorID);
		Cache cache = CacheServiceImpl.getCacheInfo(OperatorID);
		LOGGER.info("~~~~~~~~~~~~~~判断第三方是否有缓存~~~~~~~~~~");
		if (cache !=null ){
			CacheServiceImpl.clearOnly(OperatorID);
			LOGGER.info("~~~~~~~~~~~~~~清空当前第三方缓存完成~~~~~~~~~~");
		}
		return "Success";
	}
}
