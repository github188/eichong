/*package com.appCore.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;


*//**
 * 一个简单的http客户端
 * @author haojian
 * Aug 7, 2013 2:40:43 PM
 *//*
public class MyHttpClient {
	
	*//**连接超时时间  (单位:毫秒)*//*
    public static final int CONNECT_TIMEOUT = 3000;
    *//**读取超时 (单位:毫秒)*//*
    public static final int READ_TIMEOUT = 3000;
	
	
	*//**
	 * 发送TET请求，并获取结果
	 * @author haojian
	 * Dec 25, 2013 11:16:37 AM
	 * @param getUrl
	 * @param parameterMap 参数map
	 * @return
	 * @throws Exception
	 *//*
	public static String httpGetGo(String getUrl,Map<String,String> parameterMap) throws Exception{  
		String param = UrlService.getSortedUrl(parameterMap);
		getUrl = getUrl+"?"+param;
		  
		String result = MyHttpClient.httpGetGo(getUrl);
		System.out.println("getUrl====="+getUrl);
		return result;
	}
	
	*//**
	 * 发送GET请求，并获取结果
	 * @param getUrl
	 * @return
	 * @throws Exception
	 *//*
	public static String httpGetGo(String getUrl) throws Exception{   
	    StringBuffer readOneLineBuff = new StringBuffer();   
	    String content ="";   
        URL url = new URL(getUrl);   
        URLConnection conn = url.openConnection();
        conn.setConnectTimeout(MyHttpClient.CONNECT_TIMEOUT);//连接超时 单位毫秒
        conn.setReadTimeout(MyHttpClient.READ_TIMEOUT);//读取超时 单位毫秒
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));          
        String line = "";   
        while ((line = reader.readLine()) != null) {   
            readOneLineBuff.append(line);   
        }   
        content = readOneLineBuff.toString();   
        reader.close();   
	    return content;   
	}   
	
	
	*//**
	 * 发送POST请求并获取结果
	 * @author haojian
	 * Aug 7, 2013 4:05:55 PM
	 * @param postUrl
	 * @param params
	 * @return
	 * @throws Exception
	 *//*
	public static String httpPostGo(String postUrl, String params) throws Exception{
		
		StringBuffer readOneLineBuff = new StringBuffer();   
		String content ="";
		
		if(!postUrl.substring(postUrl.length()-1).equals("?")){
			postUrl = postUrl + "?";
		}
		System.out.println(postUrl);
        URL url = new URL(postUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");// 提交模式
        conn.setConnectTimeout(MyHttpClient.CONNECT_TIMEOUT);//连接超时 单位毫秒
        conn.setReadTimeout(MyHttpClient.READ_TIMEOUT);//读取超时 单位毫秒
        conn.setDoOutput(true);// 是否输入参数
        
        byte[] bypes = params.getBytes("utf-8");
        conn.getOutputStream().write(bypes);// 输入参数
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));          
        String line = "";   
        while ((line = reader.readLine()) != null) {   
            readOneLineBuff.append(line);   
        }   
        content = readOneLineBuff.toString();   
        reader.close();   
	    return content;   
	}
	
}
*/