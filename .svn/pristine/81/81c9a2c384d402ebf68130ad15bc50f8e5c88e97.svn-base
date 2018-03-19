package com.wanma.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.service.impl.AppInitServiceImpl;

@Controller
@RequestMapping("/app/init")
public class AppInitController {
	
	@RequestMapping("/config")
	@ResponseBody
	public String getConfigInfo(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = appInitService.getAllConfigList();
		
		return new AccessSuccessResult(list).toString();
	}
	
	@RequestMapping("/psList")
	@ResponseBody
	public String getAllPs(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = appInitService.getAllPsList();
		
		return new AccessSuccessResult(list).toString();
	}
	
	@Autowired
	private AppInitServiceImpl appInitService;
}
