/**
 *Copyright (c) 2014,bruce cheng
 *All rights reserved.
 */
package com.wanma.util;

/**
 * 判空工具类
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime 2014-10-26 下午10:20:36
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
public class JudgeNullUtils {

	/**
	 * 检查字符串是否为空
	 * 
	 * @author bruce cheng(http://blog.csdn.net/brucehome)
	 * @param s
	 * @return true 为空 false 不为空
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.trim().equals("");
	}

	/**
	 * 检查字是否为空
	 * 
	 * @author bruce cheng(http://blog.csdn.net/brucehome)
	 * @param s
	 * @return true 为空 false 不为空
	 */
	public static boolean isEmpty(Object s) {
		return s == null || "".equals(s);
	}
	
	/**
	 * 处理空值结果
	 * 
	 * @author bruce cheng(http://blog.csdn.net/brucehome)
	 * @param1 contend ,desc 目标值
	 * @return 返回String,如空则返回""
	 */
	public static String nvlStr(Object contend) {
		return contend != null ? (contend.toString()) : "";
	}

	/**
	 * 处理空值结果
	 * 
	 * @author bruce cheng(http://blog.csdn.net/brucehome)
	 * @param1 contend ,desc 目标值
	 * @return 返回String,如空则返回""
	 */
	public static String nvlStr0(Object contend) {
		return contend != null ? (contend.toString()) : "0";
	}

	/**
	 * 处理空值结果
	 * 
	 * @author bruce cheng(http://blog.csdn.net/brucehome)
	 * @param1 contend ,desc 目标值
	 * @return 返回字符,如空则返回"0"
	 */
	public static Long nvlLang(Object contend) {
		return contend != null ? (Long.parseLong(contend.toString())) : 0L;
	}
	
	/**
	 * 处理空值结果
	 * 
	 * @author bruce cheng(http://blog.csdn.net/brucehome)
	 * @param1 contend ,desc 目标值
	 * @return 返回字符,如空则返回"0"
	 */
	public static Integer nvlInteger(Object contend) {
		return contend != null ? (Integer.parseInt(contend.toString())) : 0;
	}
}
