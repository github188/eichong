package com.wanma.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wanma.common.SessionMgr;

@Controller
@RequestMapping("/web/log")
public class WebLogController {
	
	@RequestMapping("/list")
	public String findCarList(HttpServletRequest request,ModelMap map) {
		if(SessionMgr.getWebUser(request)!=null&&"18057138912".equals(SessionMgr.getUserId(request))){
			/*RedisClient redisClient=new RedisClient();
			ShardedJedis shardedJedis=redisClient.getShardedJedis();
			List<String> logList=shardedJedis.lrange("ClickLog", 0, -1);
			map.put("logList", logList);*/
		}
		return "frontend/web2/logList";
	}
}
