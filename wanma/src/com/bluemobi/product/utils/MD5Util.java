/**
 * FileName:MD5Util.java
 * Author: Administrator
 * Create: 2014年7月16日
 * Last Modified: 2014年7月16日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

import java.security.MessageDigest;

/**
 * 采用MD5加密解密
 * 
 * @author yangweir
 * @datetime 2011-10-13
 */
public class MD5Util {

	/***
	 * MD5加码 生成32位md5码11
	 */
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */
	public static String convertMD5(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}

	// 测试主函数
	public static void main(String args[]) {
		String s = new String("123456");
		System.out.println("原始：" + s);
		System.out.println("MD5后：" + MD5(s));
		System.out.println("加密的：" + convertMD5(s));
		System.out.println("解密的：" + convertMD5(convertMD5(s)));
		System.out.println("21232f297a57a5a743894a0e4a801fc3".equals("21232f297a57a5a743894a0e4a801fc3"));
		System.out.println("解密的：" + convertMD5("21232f297a57a5a743894a0e4a801fc3"));

	}
}