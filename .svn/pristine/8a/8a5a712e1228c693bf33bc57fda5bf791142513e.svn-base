package com.wanma.web.controller;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.web.service.WebProductService;

/**
 * @Description: 产品控制器
 * @author songjf
 * @createTime：2015-3-15 下午05:52:08
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/web/product")
public class WebProductController {
	private static Log log = LogFactory.getLog(WebProductController.class);

	/** 产品业务处理对象 */
	@Autowired
	private WebProductService appProductService;

	/**
	 * @Title: findProducts
	 * @Description: 获取产品列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/findProducts")
	@ResponseBody
	public String findProducts(@RequestParam Map<String, Object> params) {
		return appProductService.findProducts(params).toString();
	}


	/**
	 * @Title: findProductDetail
	 * @Description: 获取产品详情
	 * @param params
	 * @return
	 */
	@RequestMapping("/findProductDetail")
	@ResponseBody
	public String findProductDetail(@RequestParam Map<String, Object> params) {
		return appProductService.findProductDetail(params).toString();
	}

}