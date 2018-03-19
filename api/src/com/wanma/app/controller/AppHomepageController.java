package com.wanma.app.controller;

import java.util.List;
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
import com.wanma.app.service.AppHomepageService;

/**
  * @Description: 首页广告控制器
  * @author songjf 
  * @createTime：2015-4-23 上午10:42:01 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
@Controller
@RequestMapping("/app/homepage")
public class AppHomepageController {
	
	private static Log log = LogFactory.getLog(AppHomepageController.class);
//	首页广告处理对象
	@Autowired
	private AppHomepageService homepageService;
	
	/**
	 * @Title: findHomepages
	 * @Description: 获取首页广告
	 * @param params
	 * @return 
	 */
	@RequestMapping("/findHomepages")
	@ResponseBody
    public String findHomepages(@RequestParam Map<String,Object> params){
		log.info("******************获取首页广告-begin************************");
		List<Map<String,Object>> homepages = null;
		try {
			homepages = homepageService.findHomepages(params);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取首页广告错误", e);
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}
		
		log.info("******************获取首页广告-end************************");
		return new AccessSuccessResult(homepages).toString();
	}
	
}
