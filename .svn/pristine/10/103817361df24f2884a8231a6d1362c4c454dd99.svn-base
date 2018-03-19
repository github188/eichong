package com.bluemobi.protocol;

import com.appCore.netty.buffer.DynamicByteBuffer;
/**
 * 通用的的协议
 * @author haojian
 * Apr 26, 2013 11:59:03 AM
 */
public class UtilProtocol {
	
	/**
	 * 将字符串转换成字节数组
	 * @author haojian
	 * Apr 26, 2013 11:59:32 AM
	 * @param str
	 * @return
	 */
	public static byte[] stringToBytes(String str){
		DynamicByteBuffer byteBuffer = DynamicByteBuffer.allocate();
		byteBuffer.putString(str);
		return byteBuffer.getBytes();
		
	}
	
	/**
	 * 将byte转换成字节数组
	 * @author haojian
	 * Apr 26, 2013 11:59:37 AM
	 * @param b
	 * @return
	 */
	public static byte[] byteToBytes(byte b){
		DynamicByteBuffer byteBuffer = DynamicByteBuffer.allocate();
		byteBuffer.put(b);
		return byteBuffer.getBytes();
		
	}
	
	/**
	 * 将short转换成字节数组
	 * @author haojian
	 * Apr 26, 2013 11:59:54 AM
	 * @param s
	 * @return
	 */
	public static byte[] shortToBytes(short s){
		DynamicByteBuffer byteBuffer = DynamicByteBuffer.allocate();
		byteBuffer.putShort(s);
		return byteBuffer.getBytes();
		
	}
	
	/**
	 * 将int转换成字节数组
	 * @author haojian
	 * Apr 26, 2013 12:00:06 PM
	 * @param i
	 * @return
	 */
	public static byte[] intToBytes(int i){
		DynamicByteBuffer byteBuffer = DynamicByteBuffer.allocate();
		byteBuffer.putInt(i);
		return byteBuffer.getBytes();
		
	}
	

}
