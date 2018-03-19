package com.wanma.support.http;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
//对接口进行测试
public class TestMain {
	private String url = "https://122.224.200.93:18080/";
	private String charset = "utf-8";
	private HttpClientUtil httpClientUtil = null;
	
	public TestMain(){
		httpClientUtil = new HttpClientUtil();
	}
	
	public void test(){
		String httpOrgCreateTest = url + "api/pipe/status";
		Map<String,String> createMap = new HashMap<String,String>();
		String token=RandomStringUtils.randomAlphanumeric(16);
		String timestamp=String.valueOf(System.currentTimeMillis());
		String input = "{\"data\":[{\"equipNo\":\"123340001\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340002\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340003\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340004\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340005\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340006\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340007\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340008\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340009\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340010\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334}]}";//
        String toSign = token + "#" + timestamp;
        String partnerId = "00000004";
        String partnerKey = "jaGYGJ/tC1wXK4ZnoLbJ+MgqYSD5BaQurx3sRyJWH6c=";
        String sign = ApiSecurityUtils.encrypt(toSign, partnerKey, ApiSecurityUtils.HS256);
		createMap.put("partnerId",partnerId);
		createMap.put("timestamp", timestamp);
		createMap.put("token", token);
		createMap.put("input", input);
		createMap.put("sign", sign);
		String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,createMap);
		System.out.println("result:"+httpOrgCreateTestRtn);
	}
	
	public static void main(String[] args){
		TestMain main = new TestMain();
		main.test();
	}
}