/**
 * FileName:FormartUtil.java
 * Author: Administrator
 * Create: 2014年8月16日
 * Last Modified: 2014年8月16日
 * Version: V1.0 
 */
package com.wanma.support.utils;

import java.text.DecimalFormat;

/**
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月16日
 */
public class FormartUtil {
	/**
	 * 数字前补0转成字符串
	 * @param place
	 * @param param
	 * @return
	 */
	public static String addPreZero(int place, long param) {
		// 格式化后的数据
		String formartData = "";

		String formart = "";

		for (int i = 0; i < place; i++) {
			formart = "0" + formart;
		}

		formartData = formartNumber(formart, param);

		return formartData;

	}

	/**
	 * 格式化数字
	 * @param formart
	 * @param param
	 * @return
	 */
	public static String formartNumber(String formart, long param) {
		// 格式化后的数据
		String formartData = "";
		try {
			DecimalFormat df = new DecimalFormat(formart);
			formartData = df.format(param);
		} catch (Exception e) {

		}

		return formartData;

	}

	/**
	 * 格式化数字
	 * @param formart
	 * @param param
	 * @return
	 */
	public static String formartDouble(String formart, double param) {
		// 格式化后的数据
		String formartData = "";
		try {
			DecimalFormat df = new DecimalFormat(formart);
			formartData = df.format(param);
		} catch (Exception e) {

		}

		return formartData;

	}

	public static void main(String[] args) {
		System.out.println(FormartUtil.formartNumber("000000000", 1));
	}
}
