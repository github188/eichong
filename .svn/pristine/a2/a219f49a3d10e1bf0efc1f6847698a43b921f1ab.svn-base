package com.wanma.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class WebFilter implements Filter {
	static String appToken = "";
	private static Logger log = Logger.getLogger(WebFilter.class);
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		chain.doFilter(req, resp);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	/**
	 * 校验请求参数中的tk值是否合法
	 * @param tk
	 * @return
	 */
	public static boolean checkTK(String tk){
		//配置文件中的token必须大于4个字符
		if(StringUtils.isEmpty(tk) || tk.length() < 18){
			return false;
		}
		byte[] b = Base64Coder.decode(tk);
		if(StringUtils.isEmpty(b)){
			return false;
		}
		
		tk = new String(b);
		
		String ntk = judgeRequest(tk);
		if(!checkAppTK(ntk)){
			return false;
		}
		//log.info("解析后的token：" + ntk + ",配置文件：" + appToken);
		if(!judgeMark(ntk, appToken)){
			return false;
		}
		return true;
	}
	
	private static String getCommToken(){
		String appToken = "";
		InputStream in = WebFilter.class.getClassLoader().getResourceAsStream("system.properties");
		try {
			Properties p = new Properties();
			p.load(in);
			appToken = p.getProperty("apiToken");
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return appToken;
	}
	
	/**
	 * 对tk解密
	 * @return notk 时间戳13位
	 */
	public static String judgeRequest(String tk){
		String str = "";
		//对Loginkey进行解密处理
		LoginKeyDecode lkd = new LoginKeyDecode();
		char[] cha = tk.toCharArray();
		for (char c : cha) {
			str = str + lkd.Replace(c);
		}
		return str;
	}
	
	/**
	 * 检查接口过来的加密字符串在解密后是否包含配置文件中给定的特定字符串
	 * @param ntk
	 * @return
	 */
	public static boolean checkAppTK(String ntk){
		appToken = getCommToken();
		if(ntk.lastIndexOf(appToken) < 0){
			return false;
		}
		
		return true;
	}

	/**
	 * 检查解密后的时间戳是否在给定范围
	 * @param notk 解密后的完整tk，固定串+时间戳
	 * @param capptk 配置文件中的apptk
	 * @return
	 */
	public static boolean judgeMark(String notk ,String capptk){
		String inputime = notk.replace(capptk, "");
		
		/*if(inputime.length() <= 10){
			inputime += "000";
		}*/
		//时间戳在补全后必须是13位
		if(inputime.length() != 13){
			return false;
		}
		
		long d = new Date().getTime();
		try{
			if(Math.abs(d - Long.valueOf(inputime)) > 300000){
				return false;
			}else {
				return true;
			}
		}catch(Exception e){
			log.error("token检查中时间比对出错...");
			log.error(e.getMessage());
			return false;
		}
	}
	
	
	private static String endcodeReplace(byte[] a){
		String s="";
		for(byte b:a){
			s+=encodeReplace(b);
		}
		return s;
	}
	private static String encodeReplace(byte a) {
		String str = String.valueOf((char)a);
		if (a >= 48 && a <= 57) {
			if ("1".equals(str)) {
				str = str.replaceAll("1", "7");
			} else if ("2".equals(str)) {
				str = str.replaceAll("2", "5");
			} else if ("3".equals(str)) {
				str = str.replaceAll("3", "8");
			} else if ("5".equals(str)) {
				str = str.replaceAll("5", "2");
			} else if ("6".equals(str)) {
				str = str.replaceAll("6", "9");
			} else if ("7".equals(str)) {
				str = str.replaceAll("7", "1");
			} else if ("8".equals(str)) {
				str = str.replace("8", "3");
			} else if ("9".equals(str)) {
				str = str.replace("9", "6");
			}
		}else if (a >= 65 && a <= 90) {
			//大写转小写 +32
			char b = (char) (a +32);
			b = (char) (b + 3);
			if(b > 122){
				b = (char) (b-26);
			}
			str = String.valueOf(b);
		}else if (a >= 97 && a <= 122) {
			char b = (char)(a-32);
			b = (char) (b + 3);
			if (b > 90) {
				b = (char) (b - 26);
			}
			str = String.valueOf(b);
		}
		return str;
	}
}
