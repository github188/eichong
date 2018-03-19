package com.wanma.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.AppPager;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.service.DynamicService;

@Controller
@RequestMapping("app/dynamic")
public class DynamicController {
	
	private Logger log = Logger.getLogger(DynamicController.class);
	
	@Autowired
	private DynamicService dynamicService;
	
	/**
	 * 获取活动列表
	 * @param pager 分页参数
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public String getDynamicList(AppPager pager){
		/*if(0L == pager.getPageNum() || 0L == pager.getPageNumber()){
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}*/
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try{
			list = dynamicService.getDynamicList(pager);
		}catch(Exception e){
			log.error(e.getMessage());
			return new AccessErrorResult(1001, "error.msg.invalid.parameter").toString();
		}
		return new AccessSuccessResult(list).toString();
	}
}
