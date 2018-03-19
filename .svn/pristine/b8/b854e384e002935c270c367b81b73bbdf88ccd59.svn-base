package com.wanma.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.model.Userinfo;
import com.wanma.web.service.impl.RedisService;

@Controller
public class RedisController {
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/login/redis")
	@ResponseBody
	public String jedisTest(){
		Date d1=new Date();
		//redisService.listEmpty("list1");
		Userinfo u1=new Userinfo();
		u1.setPkUserId("1");
		u1.setUserName("zs6"+Math.random());
		for(int i=0;i<300;i++){
			redisService.listSet("list", "zs1");
			redisService.listSet("list1", "zs2");
			redisService.listSet("list1", "zs3");
			redisService.listSet("list1", "3123");
			redisService.listSet("list1", "li12313st1");
			/*redisService.listSet("list1", "li313123123st1");
			List<Object> list=redisService.listGetAll("list1");*/
			//redisService.listSet("list1", u1);
		}
		/*List<Object> list=redisService.listGetByRange("list1", 100, 300);//redisService.listGetAll("list1");
		redisService.hashPut("hash1", "key6", u1);
		u1.setUserName("zs1231237");
		redisService.hashPut("hash1", "key7", u1);
		u1.setUserName("zs8");
		redisService.hashPut("hash1", "key8", u1);
		u1.setUserName("zs9");
		redisService.hashPut("hash1", "key9", u1);
		u1.setUserName("zs10");
		redisService.hashPut("hash1", "key10", u1);
		Userinfo obj=(Userinfo) redisService.hashGet("hash1", "key7");*/
		Date d2=new Date();
		System.out.println(d2.getTime()-d1.getTime());
		//return list.toString();
		return null;
	}
	
}
