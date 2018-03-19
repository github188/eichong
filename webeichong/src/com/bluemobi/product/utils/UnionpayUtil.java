/**
 * FileName: UnionpayUtil.java
 * Author: Administrator
 * Create: 2014年7月17日
 * Last Modified: 2014年7月17日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

import java.util.Random;

/**
 * 银联支付工具类
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月17日
 */
public class UnionpayUtil {

	/**
	 * 生成银联交易码
	 * 
	 * @return String 银联交易码
	 */
	public static String createUnionpayCode() {
		// 银联交易码
		String unionpayCode = "";
		
		// TO-DO
		unionpayCode = createRandom();
		
		// 返回银联交易码
		return unionpayCode;
	}
	


	/**
	 * 生成4位随机数
	 * 
	 * @return String 4位随机数
	 */
	public static String createRandom() {
		Random dom = new Random();
		int random = dom.nextInt(100000000);
		if(random < 10000000) {
			FormartUtil.addPreZero(7, random);
		}
		return String.valueOf(random);
	}
}
