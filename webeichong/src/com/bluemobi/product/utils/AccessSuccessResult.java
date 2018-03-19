/**
 * FileName:AccessSuccessResult.java
 * Author: Administrator
 * Create: 2014年8月10日
 * Last Modified: 2014年8月10日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理成功JSON对象
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月10日
 */
public class AccessSuccessResult {
	/** 处理成功标识 */
	private static final String CODE_OK = "OK";
	/** 处理成功标识 */
	private static final String RESULT_OK = "1";
	/** 处理成功信息 */
	private static final String RESULT_OK_MSG = "处理成功";

	private StringBuffer sb;

	/**
	 * 构造函数
	 * 
	 * @param obj
	 *            返回信息对象
	 */
	public AccessSuccessResult() {
		handleResult(null);
	}

	/**
	 * 构造函数
	 * 
	 * @param obj
	 *            返回信息对象
	 */
	public AccessSuccessResult(Object obj) {
		handleResult(obj);
	}

	/**
	 * 构造函数
	 * 
	 * @param obj
	 *            返回信息对象
	 */
	public AccessSuccessResult(List<?> obj) {
		if (obj == null) {
			obj = new ArrayList<Object>();
		}
		handleResult(obj);
	}

	/**
	 * 生成JSON数据（App端调用时使用）
	 * 
	 * @param obj
	 */
	@SuppressWarnings("rawtypes")
	private void handleResult(Object obj) {
		sb = new StringBuffer();
		String data = "";
		List<String> pubList = null;

		Map<Class, List<String>> controlMap = new HashMap<Class, List<String>>();
		if (obj != null) {
			pubList = JsonPublishXmlReader.getPublishList(obj.getClass()
					.getName());
			controlMap.put(obj.getClass(), pubList);
			data = new AppJsonObject(obj, controlMap).toString();
		} else {
			data = "null";
		}
		sb.append("{");
		sb.append("\"code\": \"" + CODE_OK + "\",");
		sb.append("\"status\": \"" + RESULT_OK + "\",");
		sb.append("\"msg\": \"" + RESULT_OK_MSG + "\",");
		sb.append("\"data\":");
		sb.append(data);
		sb.append("}");

	}

	/**
	 * 返回JSON文字列
	 */
	public String toString() {
		return sb.toString();
	}

	/**
	 * 返回JSON文字列
	 */
	public byte[] toBytes() {

		byte[] reBytes = new byte[0];
		try {
			reBytes = sb.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reBytes;
	}

}
