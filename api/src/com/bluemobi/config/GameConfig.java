package com.bluemobi.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.bluemobi.model.GameObject;

/**
 *  服务配置
 * @author haojian
 * Apr 27, 2012 3:21:26 PM
 */
public class GameConfig extends GameObject{
	
	private static final Logger logger = Logger.getLogger(GameConfig.class);
	
	static String fileName = 
		System.getProperty("file.separator")
		+ System.getProperty("user.dir")
		+ System.getProperty("file.separator") + "conf"
		+ System.getProperty("file.separator")
		+ "GameConfig.properties";
	
	
	public static final String crossDomainPolicy = "<cross-domain-policy>"
		+ "<site-control permitted-cross-domain-policies=\"all\"/>"
		+ "<allow-access-from domain=\"*\" to-ports=\"*\" />"
		+ "</cross-domain-policy>"
		+ "\0";
	
	
	/**可用cup的数量*/
	public static int cpuCoreNum = Runtime.getRuntime().availableProcessors();

	/** 平台id  1、大陆苹果正版 2、大陆苹果越狱 3、大陆安卓 4、台湾橘子 5、台湾橘子beanfun 6、台湾苹果正版*/
	public static int pfId;
	/** 区号 */
	public static int zoneId;
	/** 端口号 */
	public static int port;
	/** 停止服务的端口号 */
	public static int shutdownPoint;
	/** 字符编码 */
	public static String encoding;
	/** 需要压缩的数据的最小字节数*/
	public static int minCompressByteNum;
	/**系统允许的最大连接数量*/
	public static int maxOnLineNumber;
	
	/**是否是测试版本*/
	public static boolean isTest;
	
	/**通信协议混淆参数1*/
	public static byte mask1;
	/**通信协议混淆参数2*/
	public static byte mask2;
	/**通信协议混淆参数3*/
	public static byte mask3;
	/**通信协议混淆参数4*/
	public static byte mask4;
	
	/**是否使用Nagle算法*/
	public static boolean tcpNoDelay;
	/**keepAlive*/
	public static boolean keepAlive;
	/**是否打开数据统计日志*/
	public static boolean isOpenLog;
	
	/**最大缓存离线用户数量*/
	public static int maxCachePlayerNum;
	/**用户数据缓存过期时间 (单位：分钟)*/
	public static int cacheExpiryTime;
	/**是否需要激活服务*/
	public static boolean isNeedActivate;
	
	/**官网平台appId*/
	public static String appId_ecn;
	/**官网平台的访问地址*/
	public static String url_ecn;
	
	/**苹果支付服务器*/
	public static String url_apple;
	
	/**自定义密钥，支付web服务器 跟 服务服务器 发消息用*/
	public static String mySecurity;
	/**自定义密钥2，服务服务器登陆公共服务器用*/
	public static String mySecurity2;
	
	
	/** 服务器类型 1、普通服务服务器  2、跨服战公共服务器*/
	public static int serverType;
	/** 跨服战是否开启 */
	public static boolean crossFightIsOpen;
	/** 跨服战服务器ip */
	public static String crossServerIp;
	/** 跨服战服务器端口 */
	public static int crossServerPort;
	
	/**校验激活码使用状态的getInfo_url*/
	public static String check_getInfo_url;
	
	/**校验激活码使用状态的setUsed_url*/
	public static String check_setUsed_url;
	
	
	static {
		
		GameConfig.loadGameConfig();
		
	}
	
	/**
	 * 加载GameConfig.properties文件
	 * @author haojian
	 * Jan 24, 2013 3:16:17 PM
	 */
	public static void loadGameConfig(){
		
		Properties p = getProperties(fileName);
		
		//所有属性都可以在这里配置
		pfId = Integer.valueOf(p.getProperty("pfId","1").trim());
		zoneId = Integer.valueOf(p.getProperty("zoneId","1").trim());
		port = Integer.valueOf(p.getProperty("port","8888").trim());
		shutdownPoint = Integer.valueOf(p.getProperty("shutdownPoint","1138").trim());
		encoding = p.getProperty("encoding","UTF-8").trim();
		minCompressByteNum = Integer.valueOf(p.getProperty("minCompressByteNum","128").trim());
		maxOnLineNumber = Integer.valueOf(p.getProperty("maxOnLineNumber","2000").trim());
		
		isTest = Boolean.valueOf(p.getProperty("isTest","false").trim());
		
		mask1 = Byte.valueOf(p.getProperty("mask1","89").trim());
		mask2 = Byte.valueOf(p.getProperty("mask2","122").trim());
		mask3 = Byte.valueOf(p.getProperty("mask3","122").trim());
		mask4 = Byte.valueOf(p.getProperty("mask4","89").trim());
			
		tcpNoDelay = Boolean.valueOf(p.getProperty("tcpNoDelay","true").trim());
		keepAlive = Boolean.valueOf(p.getProperty("keepAlive","false").trim());
		isOpenLog = Boolean.valueOf(p.getProperty("isOpenLog","true").trim());
		
		maxCachePlayerNum = Integer.valueOf(p.getProperty("maxCachePlayerNum","5000").trim());//最大缓存数量
		cacheExpiryTime = Integer.valueOf(p.getProperty("cacheExpiryTime","180").trim());//默认缓存时间（分钟）
		isNeedActivate = Boolean.valueOf(p.getProperty("isNeedActivate","false").trim());
		
		appId_ecn = p.getProperty("appId_ecn","1001").trim();
		url_ecn = p.getProperty("url_ecn", "http://").trim();
		
		url_apple = p.getProperty("url_apple", "https://").trim();
		mySecurity = p.getProperty("mySecurity", "ecngameperfect").trim();
		mySecurity2 = p.getProperty("mySecurity2", "a").trim();
		

		serverType = Integer.valueOf(p.getProperty("serverType","1").trim());//服务器类型 1、普通服务服务器  2、跨服战公共服务器
		crossFightIsOpen = Boolean.valueOf(p.getProperty("crossFightIsOpen","false").trim());//跨服战是否开启
		crossServerIp = p.getProperty("crossServerIp","localhost").trim();//跨服战服务器ip
		crossServerPort = Integer.valueOf(p.getProperty("crossServerPort","7001").trim());//跨服战服务器端口
		
		
		//查询激活码是否能被使用
		check_getInfo_url = p.getProperty("check_getInfo_url", "http://").trim();//校验激活码使用状态的getInfo_url
		//设置激活码被使用
		check_setUsed_url = p.getProperty("check_setUsed_url", "http://").trim();//校验激活码使用状态的setUsed_url
		
		logger.info("可用cpu数量【" + GameConfig.cpuCoreNum + "】");
		
	}
	
	
	/**
	 * 读取propertity文件的方法
	 * @author haojian
	 * Apr 27, 2012 3:00:56 PM
	 * @param fileName
	 * @return
	 */
	public static Properties getProperties(String fileName){
		InputStream is=null;
		try {
			is=new FileInputStream(fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Properties properties=new Properties();
		try {
			properties.load(is);
			if(is!=null)is.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return properties;
	}
	
	
	
}
