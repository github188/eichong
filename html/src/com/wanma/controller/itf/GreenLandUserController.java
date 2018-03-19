package com.wanma.controller.itf;

import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.service.GreenLandUserService;
import com.wanma.support.common.AccessSuccessResult;
import com.wanma.support.utils.MD5Util;

@Controller
@RequestMapping("/itf/greenland")
public class GreenLandUserController {
	Logger log = Logger.getLogger(GreenLandUserController.class);
	@Autowired
	private GreenLandUserService greenLandUserService;

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/yaUser")
	@ResponseBody
	public String yaUser(String tokenId) {
		String appKey = "acwl";
		String appSecret = "acwl123";
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(
				"http://greenland.ismarthousing.cn/api/WechatServices/Entry/get_customerinfo?token="
						+ tokenId);
		getMethod.addRequestHeader("wow-appkey", appKey);
		Date date = new Date();
		long lTime = date.getTime();
		getMethod.addRequestHeader("wow-timestamp", lTime + "");
		getMethod.addRequestHeader("wow-sign",
				MD5Util.MD5Encode(appKey + appSecret + lTime, null));
		try {
			client.executeMethod(getMethod);
			String jsonStr = getMethod.getResponseBodyAsString();
			System.out.println(jsonStr);
			getMethod.releaseConnection();
			greenLandUserService.dealWithUserData(jsonStr);
		} catch (Exception e) {
			log.error("用户信息处理异常！");
			e.printStackTrace();
		}
		return new AccessSuccessResult().toString();
	}

	public static void main(String[] args) {
		GreenLandUserController a = new GreenLandUserController();
		System.out.println("111111111");
		a.yaUser("122bcddb-6d4a-46a2-a6b0-0b7df9be4a91");
		System.out.println("2222222222");
	}
}
