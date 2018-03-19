package com.wanma.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.AppPager;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.service.AdvertService;

@Controller
@RequestMapping("/app/advert")
public class AdvertController {
	private static Log log = LogFactory.getLog(AdvertController.class);
	@Autowired
	private AdvertService advertService;

	/**
	 * 获取闪屏广告位 type 广告类型 1闪屏2插屏，location 插屏的按钮位置，1: 左一button 2: 左二button
	 * 3:右二button 4:右一button
	 * 
	 * @return
	 */
	@RequestMapping("/advertList")
	@ResponseBody
	public String getAdvertList(@RequestParam Map<String, Object> params) {
		String type = (String) params.get("type");
//		if (StringUtils.isEmpty(type)) {
//			return new AccessErrorResult(1050, "error.msg.miss.parameter")
//					.toString();
//		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = advertService.getAdvertList(params);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		return new AccessSuccessResult(list).toString();
	}

	/**
	 * 获取动态列表 pageNumber 当前页码从1开始，pageNum 每页数据量
	 * 
	 * @return
	 */
	@RequestMapping("/dynamicList")
	@ResponseBody
	public String getDynamicList(@RequestParam Map<String, Object> params,
			AppPager pager) {
		if (StringUtils.isEmpty(pager.getPageNum())
				|| StringUtils.isEmpty(pager.getPageNumber())) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		params.put("pagerNumber", pager.getPageNumber());
		params.put("pagerNum", pager.getPageNum());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = advertService.getDynamicList(params);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		return new AccessSuccessResult(list).toString();
	}

}
