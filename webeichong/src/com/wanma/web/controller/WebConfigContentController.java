package com.wanma.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.web.service.impl.WebConfigContentServiceImpl;
import com.wanma.web.support.utils.JsonUtil;

/**
 * 获取城市列表
 */
@Controller
@RequestMapping("/web/config")
public class WebConfigContentController {


	@Autowired
	private WebConfigContentServiceImpl webConfigContentServiceImpl;



	
	/**
	 * 根据id获取配置表相关列表
	 * @return
	 */
	public String getConfigContentListByCpId(String cpId){
		List<Map<String, Object>> list= webConfigContentServiceImpl.getConfigContentListByCpId(cpId);
		return JsonUtil.list2json(list);
	}


}