/**
 * @author haojian
 * Oct 10, 2011
 * 处理自定义协议的帮助类
 */

package com.netCore.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netCore.netty.buffer.DynamicByteBuffer;

public class ByteUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ByteUtil.class);
	
	/**读取字符串，字节流格式： 2字节长度 + 字符串字节数组 */
	public static String getString(ByteBuffer byteBuffer){
		
		short len = byteBuffer.getShort();
		byte[] bb = new byte[len];
		byteBuffer.get(bb);
		String str = null;
		try {
			str = new String(bb,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("读取字符串错误.");
		}
		//System.out.println("receive_str："+len+"---> "+str);
		return str;
	}
	
	
	/**
	 * 将两个字节数组合并成一个数组
	 * @author haojian
	 * Apr 22, 2013 10:29:35 AM
	 * @param bb1
	 * @param bb2
	 * @return
	 */
	public static byte[] mergeByteArray(byte[] bb1, byte[] bb2){
		int len1 = bb1.length;
		int len2 = bb2.length;
		byte[] bb = new byte[len1+len2];
		System.arraycopy(bb1, 0, bb, 0, len1);
		System.arraycopy(bb2, 0, bb, len1, len2);
		return bb;
	}
	
	/**
	 * 将boolean转换成byte数组
	 * @author haojian
	 * May 3, 2013 4:20:46 PM
	 * @param b
	 * @return
	 */
	public static byte[] booleanToBytes(boolean b){
		byte[] bb = new byte[1];
		bb[0] = (byte)(b?1:0);
		return bb;
	}
	
	/**
	 * 将byte转换成byte数组
	 * @author haojian
	 * Apr 11, 2013 3:16:51 PM
	 * @param b
	 * @return
	 */
	public static byte[] byteToBytes(byte b){
		byte[] bb = new byte[1];
		bb[0] = b;
		return bb;
	}
	/**
	 * 将short转换成byte数组
	 * @author haojian
	 * Apr 11, 2013 3:16:51 PM
	 * @param s
	 * @return
	 */
	public static byte[] shortToBytes(short s){

		ByteBuffer byteBuffer = ByteBuffer.allocate(2);
		byteBuffer.putShort(s);
		byteBuffer.flip();
		byte[] bb = new byte[byteBuffer.limit()];
		byteBuffer.get(bb);	
		return bb;
		
	}
	
	/**
	 * 将int转换成byte数组
	 * @author haojian
	 * Apr 11, 2013 3:16:51 PM
	 * @param i
	 * @return
	 */
	public static byte[] intToBytes(int i){
		ByteBuffer byteBuffer = ByteBuffer.allocate(4);
		byteBuffer.putInt(i);
		byteBuffer.flip();
		byte[] bb = new byte[byteBuffer.limit()];
		byteBuffer.get(bb);	
		return bb;
	}
	
	/**
	 * 将long转换成byte数组
	 * @author haojian
	 * @date 2014-7-22 下午1:28:21 
	 * @param i
	 * @return
	 * @return byte[]
	 */
	public static byte[] longToBytes(long l){
		ByteBuffer byteBuffer = ByteBuffer.allocate(8);
		byteBuffer.putLong(l);
		byteBuffer.flip();
		byte[] bb = new byte[byteBuffer.limit()];
		byteBuffer.get(bb);	
		return bb;
	}
	
	
	/**
	 * 将一个字符串转换成字节数组
	 * @author haojian
	 * Aug 20, 2013 10:46:15 AM
	 * @param s
	 * @return
	 */
	public static byte[] stringToBytes(String s){
		
		DynamicByteBuffer buffer = DynamicByteBuffer.allocate();
		buffer.putString(s);
		byte[] bb = buffer.getBytes();
		
		return bb;
	}

	
	/**
	 * 将字节数组转换成 java对象
	 * @author haojian
	 * Nov 15, 2013 2:46:03 PM
	 * @param buffer
	 * @return
	 * @throws Exception
	 */
	public static Object byteArrayToObject(byte[] bb){
		Object obj = null;
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try{
			bais = new ByteArrayInputStream(bb);
			ois = new ObjectInputStream(bais);
			obj = ois.readObject();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {if(ois!=null)ois.close();} catch (IOException e) {e.printStackTrace();}
			try {if(bais!=null)bais.close();} catch (IOException e) {e.printStackTrace();}
		}
		
		return obj;
	}

	/**
	 * 将 java对象转换成字节数组
	 * @author haojian
	 * Nov 15, 2013 2:46:08 PM
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static byte[] objectToByteArray(Object obj){
		byte[] bb = null;
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		try{
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			bb = baos.toByteArray();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {if(oos!=null)oos.close();} catch (IOException e) {e.printStackTrace();}
			try {if(baos!=null)baos.close();} catch (IOException e) {e.printStackTrace();}
		}
		
		return bb;
	}
	

	
}
