/**     
 * @Title:  UUIDUtil.java   
 * @Package com.wanma.web.support.utils   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2015年11月3日 下午1:22:42   
 * @version V1.0     
 */  
package com.wanma.util;

import java.util.UUID;

/**
 * @author wbc
 *
 */
public class UUIDUtil {
	/** 
	 * 以62进制（字母加数字）生成19位UUID，最短的UUID 
	 *  
	 * @return 
	 */  
	public static String uuid() {  
	    UUID uuid = UUID.randomUUID();  
	    StringBuilder sb = new StringBuilder();  
	    sb.append(digits(uuid.getMostSignificantBits() >> 32, 8));  
	    sb.append(digits(uuid.getMostSignificantBits() >> 16, 4));  
	    sb.append(digits(uuid.getMostSignificantBits(), 4));  
	    sb.append(digits(uuid.getLeastSignificantBits() >> 48, 4));  
	    sb.append(digits(uuid.getLeastSignificantBits(), 12));  
	    return sb.toString();  
	}  
	
	private static String digits(long val, int digits) {  
	    long hi = 1L << (digits * 4);  
	    return Numbers.toString(hi | (val & (hi - 1)), Numbers.MAX_RADIX)  
	            .substring(1);  
	}  
	
	public static void main(String[] args) {
		System.out.println(UUIDUtil.uuid());
		System.out.println(UUIDUtil.uuid());
		System.out.println(UUIDUtil.uuid());
		System.out.println(UUIDUtil.uuid());
		System.out.println(UUIDUtil.uuid());
	}
}
