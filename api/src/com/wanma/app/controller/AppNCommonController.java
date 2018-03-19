package com.wanma.app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.HttpClientUtils;
import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.service.impl.RedisService;
import com.wanma.app.util.HttpClientUtil;
import com.wanma.app.util.MD5Util;

@Controller
@RequestMapping("/app/common")
public class AppNCommonController {
	Logger log = Logger.getLogger(AppNCommonController.class);
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/getToken")
	@ResponseBody
	public String getToken(){
		InputStream in = AppNCommonController.class.getClassLoader().getResourceAsStream("system.properties");
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			log.error("获取token失败...");
			log.error(e.getMessage());
			try {
				if(null != in) in.close();
			} catch (IOException e1) {
				log.error("关闭文件输入流失败...");
				log.error(e1.getStackTrace());
				return new AccessErrorResult(1001, "获取相关数据失败").toString();
			}
			return new AccessErrorResult(1001, "获取相关数据失败").toString();
		}
		return new AccessSuccessResult(p.getProperty("apiToken")).toString();
	}

	/**
	 * 清除登陆次数和支付次数限制
	 * @param uPhone 用户电话
	 * @param uId 用户id
	 * @return
	 */
	@RequestMapping("/cleanPWFail")
	@ResponseBody
	public String cleanPWFail(String uPhone,String uId){
		if(!StringUtils.isEmpty(uPhone)){
			redisService.strSet("app:lpw:" + uPhone + ":num", "0");
		}
		if(!StringUtils.isEmpty(uId)){
			redisService.strSet("app:ppw:" + uId + ":num", "0");
		}
		
		return new AccessSuccessResult().toString();
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/yaUser")
	@ResponseBody
	public String yaUser(){
		String appKey = "acwl";
		String appSecret = "acwl123";
		String tokenId = "67c60ead-e8c8-40f6-9ecb-ceb046bea712";
		HttpClient client = new HttpClient();
		
		GetMethod getMethod = new GetMethod("http://greenland.ismarthousing.cn/api/WechatServices/Entry/get_customerinfo?token=" + tokenId);
		getMethod.addRequestHeader("wow-appkey", appKey);
		Date date = new Date();
		long lTime = date.getTime();
		getMethod.addRequestHeader("wow-timestamp", lTime + "");
		getMethod.addRequestHeader("wow-sign", MD5Util.MD5Encode(appKey+appSecret+lTime, null));
		try {
			client.executeMethod(getMethod);
			String jsonStr = getMethod.getResponseBodyAsString();
			getMethod.releaseConnection();
			JSONObject jo = JSONObject.parseObject(jsonStr);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new AccessSuccessResult().toString();
	}
}
