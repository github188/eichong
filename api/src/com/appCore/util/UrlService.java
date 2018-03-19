/*package com.appCore.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

*//**
 * 处理url中请求参数的服务
 * @author haojian
 * Apr 1, 2013 9:52:43 AM
 *//*
public class UrlService {
	
	
	*//**
	 * 将请求map中的转换成安装key排序的url
	 * @author haojian
	 * Feb 1, 2013 6:18:38 PM
	 * @param parameterMap
	 * @return
	 *//*
	public static String getSortedUrl(Map parameterMap){
		//排序
		Map<String,String> map = new TreeMap<String,String>(parameterMap);
		
		Set<String> keySet = map.keySet();
		StringBuilder sb = new StringBuilder();
		for(String key:keySet){
			sb.append(key).append("=").append(map.get(key)).append("&");
		}
		String str = sb.substring(0, sb.length()-1);
		return str;
	}
	
	
	*//**
	 * 将url后面的参数放到 TreeMap 中
	 * @author haojian
	 * Feb 1, 2013 6:50:42 PM
	 * @param params
	 * @return
	 *//*
	public static Map<String,String> getMapbyUrlParams(String params){
		
		Map<String,String> map = new TreeMap<String,String>();
		String[] ss = params.split("&");
		for(String s:ss){
			int index = s.indexOf("=");
			if(index>0){
				String key = s.substring(0,index);
				String value = s.substring(index+1);
				map.put(key, value);
				
			}
		}

		return map;
	}
	
	*//**
	 * 通过一个地址访问一个web服务器，并返回相应的结果
	 * @author haojian
	 * Jun 24, 2013 12:10:16 PM
	 * @param goUrl
	 * @return
	 * @throws Exception
	 *//*
	public static String HttpGetGo(String goUrl) throws Exception{   
	    StringBuffer readOneLineBuff = new StringBuffer();   
	    String content ="";   
        URL url = new URL(goUrl);   
        URLConnection conn = url.openConnection();   
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
	 * 对字符串进行url编码
	 * @author haojian
	 * Dec 25, 2013 5:26:08 PM
	 * @param str
	 * @return
	 *//*
	public static String urlEncode(String str){
		String result=null;
		try {
			result = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	*//**
	 * 对字符串进行url解码
	 * @author haojian
	 * Dec 25, 2013 5:26:08 PM
	 * @param str
	 * @return
	 *//*
	public static String urlDecode(String str){
		String result=null;
		try {
			result = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	public static void main(String[] args){
		String str = "a=playlogin&c=game&gid=jisuxiangxi&openid=chendi1985&pf=iahgames&sid=1&t=1360047266&sig=ODNmZWIwMzcxYjc2OWVjZjU4NTFhNTBmNzhkNGQzZWQ=";
		Map map = UrlService.getMapbyUrlParams(str);
		System.out.println(UrlService.getSortedUrl(map));
		//编码前
		String s1 = "jo040cBZON3Wb/cN5OD3KOJ76IR2Zjpso6hZIdm+5w2vkvWSsx/uVwyTSGInJ3l/zlX/142s+WjP303z53lMjRlZRl8/1lHjZKI/ILb1D7WV40wbQkl7LjvEV5nAc+H/QIsJqDwCr3lt97Adcp+s54UJuj4RjukVBCSk9UZBIWuuYe/TIWw6nw6kmHqeNXQT+ggX9rKtj/H/L3dBGxnm1Vcg4pM0xu8OeNepyLWyyXPgiZr1jNBsgcNaPmpsgNsZVRt+DGVBXQuVYm3yfm4oChVujFQtfNQRnjh2OkPeViZr8/ZIO6D8auikKnC3WuunNOVFZANRz6bdYbAII6IBCdXClo30pO1dgpratyG257fTtc+8XTVx+XJbN7cZNZw4NkGe/hl/tWn/rdqAbbTdKA==";
		//编码后
		String s2 = "jo040cBZON3Wb%2FcN5OD3KOJ76IR2Zjpso6hZIdm%2B5w2vkvWSsx%2FuVwyTSGInJ3l%2FzlX%2F142s%2BWjP303z53lMjRlZRl8%2F1lHjZKI%2FILb1D7WV40wbQkl7LjvEV5nAc%2BH%2FQIsJqDwCr3lt97Adcp%2Bs54UJuj4RjukVBCSk9UZBIWuuYe%2FTIWw6nw6kmHqeNXQT%2BggX9rKtj%2FH%2FL3dBGxnm1Vcg4pM0xu8OeNepyLWyyXPgiZr1jNBsgcNaPmpsgNsZVRt%2BDGVBXQuVYm3yfm4oChVujFQtfNQRnjh2OkPeViZr8%2FZIO6D8auikKnC3WuunNOVFZANRz6bdYbAII6IBCdXClo30pO1dgpratyG257fTtc%2B8XTVx%2BXJbN7cZNZw4NkGe%2Fhl%2FtWn%2FrdqAbbTdKA%3D%3D";
		
		s1 = UrlService.urlEncode("元宝");
		s2 = UrlService.urlDecode(s1);
		
		System.out.println("编码前="+s1);
		System.out.println("编码后="+s2);
		if(s1.equals(s2)){
			System.out.println("编码前后字符串一样");
		}else{
			System.out.println("编码前后字符串不一样...");
		}
		
		
	}
	

}
*/