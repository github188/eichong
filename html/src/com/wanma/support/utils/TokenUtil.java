package com.wanma.support.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * XML文件处理工具类
 * 
 * @author yangweir
 * @date 2014/07/15
 */
public class TokenUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	/**
	 * 简单Java对象转成XML文件
	 * 
	 * @param bean
	 * @return Java类名为根节点，属性为子节点的XML文件
	 * @throws Exception 
	 */
	public static String makeToken(String org,String secret) throws Exception {
		return MD5Util.Md5(org+secret+System.currentTimeMillis());
	}

	public static void main(String[] args) {
		//XMLModel xmlModel = XmlUtil.parseNoAwareXML("data-auth-setting.xml");
		//System.out.println(xmlModel);
		System.out.println(StringUtil.lastIndexOfIgnoreCase("ABCD",("cd")));
	}
}
