/**
 * FileName:AccessErrorResult.java
 * Author: Administrator
 * Create: 2014年8月10日
 * Last Modified: 2014年8月10日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.bluemobi.product.common.MessageManager;
import com.wanma.web.support.common.PageResponse;
import com.wanma.web.support.common.ResultResponse;
import com.wanma.web.support.utils.*;
import com.wanma.web.support.utils.DateUtil;

/**
 * 处理出错JSON对象
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月10日
 */
public class AccessErrorResult {
	/** 处理成功标识 */
	private static final String CODE_ERROR = "FAILED";
	/** 处理成功标识 */
	private static final String RESULT_ERROR = "0";
	/** 处理成功信息 */
	private static final String RESULT_ERROR_MSG = "处理失败";

	private StringBuffer sb;

	/**
	 * 构造函数
	 * 
	 * @param errorMsgKey
	 *            返回信息对象
	 */
	public AccessErrorResult(String errorMsgKey) {
		String errorMsg = "";
		errorMsg = MessageManager.getMessageManager().getMessage(errorMsgKey);
		handleResult(errorMsg);
	}

	/**
	 * 构造函数
	 * 
	 * @param obj
	 *            返回信息对象
	 */
	public AccessErrorResult() {
		handleResult(RESULT_ERROR_MSG);
	}

	/**
	 * 生成JSON数据（App端调用时使用）
	 * 
	 * @param obj
	 */
	private void handleResult(String errorMsg) {
		sb = new StringBuffer();

		sb.append("{");
		sb.append("\"code\": \"" + CODE_ERROR + "\",");
		sb.append("\"status\": \"" + RESULT_ERROR + "\",");
		sb.append("\"msg\": \"" + errorMsg + "\",");
		sb.append("\"data\":null");
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
	public byte[]  toBytes() {

		byte[] reBytes = new byte[0];
		try {
			reBytes =  sb.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reBytes;
	}

	public static void main(String[] args) throws ParseException {
//		List<String> result = Arrays.asList("a","b","c","d");
//		PageResponse<List<String>> response = new PageResponse<List<String>>(0,10);
//		response.setDate(result);
//
//		ResultResponse resultResponse = new ResultResponse("a");
//		System.out.println(resultResponse.toString());
	}

}
