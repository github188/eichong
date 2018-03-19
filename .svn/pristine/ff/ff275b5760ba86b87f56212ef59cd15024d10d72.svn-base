/**
 * FileName:AccessSuccessResult.java
 * Author: Administrator
 * Create: 2014年9月10日
 * Last Modified: 2014年9月10日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

/**
 * 32位UUID生成器
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年9月10日
 */
public class UUIDUtil {

	/**
	 * 生成不带-的UUID
	 * 
	 * @return UUID
	 */
	public static String getUUID() {
		// 返回用UUID
		String uuidResult = "";
		// 临时用UUID
		String uuidTemp = "";
		// 取得UUID存储到临时用UUID
		uuidTemp = java.util.UUID.randomUUID().toString();

		// 替换掉所有-字符
		uuidResult = uuidTemp.replaceAll("-", "");

		// 返回UUID
		return uuidResult;
	}
}
