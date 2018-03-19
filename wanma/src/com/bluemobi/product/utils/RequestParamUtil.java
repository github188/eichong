/**
 * FileName:RequestParamUtil.java
 * Author: Administrator
 * Create: 2014年8月10日
 * Last Modified: 2014年8月10日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

/**
 * 通过request取得参数UTF-8字符参数值
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月10日
 */
public class RequestParamUtil {

	/** 默认编码 */
	private static String DEFAULT_END_CODE = "UTF-8";

	/**
	 * 通过request取得参数UTF-8字符参数值
	 * 
	 * @param request
	 *            请求信息
	 * @param key
	 *            参数属性
	 * @param endCode
	 *            编码
	 * @return String 转码后的参数值
	 */
	public static String getEncodeParam(HttpServletRequest request, String key,
			String endCode) {
		String parameterValue = null;
		String tempValue = null;

		if (request == null || key == null) {
			return parameterValue;
		}

		tempValue = request.getParameter(key);

		if (tempValue != null) {
			try {
				parameterValue = java.net.URLDecoder.decode(tempValue, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		return parameterValue;
	}

	/**
	 * 通过request取得参数UTF-8字符参数值(默认编码)
	 * 
	 * @param request
	 *            请求信息
	 * @param key
	 *            参数属性
	 * @return String 转码后的参数值
	 */
	public static String getEncodeParam(HttpServletRequest request, String key) {
		String parameterValue = null;

		parameterValue = getEncodeParam(request, key, DEFAULT_END_CODE);

		return parameterValue;

	}

}
