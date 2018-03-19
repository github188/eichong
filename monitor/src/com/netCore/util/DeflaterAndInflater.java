package com.netCore.util;

import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 压缩、解压 字节数组需调用此类方法
 * 服务端发给客户端的消息，如果消息量过大，可以压缩该消息
 * @author haojian
 * Apr 1, 2013 9:49:17 AM
 */
public class DeflaterAndInflater {
	
	private static final Logger logger = LoggerFactory.getLogger(DeflaterAndInflater.class);
	
	/**
	 * 压缩字节数组
	 * @author haojian
	 * Apr 1, 2013 9:49:29 AM
	 * @param input
	 * @return
	 */
	public static byte[] compressBytes(byte[] input){
		Deflater compresser = new Deflater();
		compresser.setInput(input);
		compresser.finish();
		byte[] temp = new byte[input.length+128];
		int compressedDataLength = compresser.deflate(temp);
		byte[] output = Arrays.copyOf(temp,compressedDataLength);
		//byte[] output = Arrays.copyOfRange(temp, 0, compressedDataLength);
		return output;
	}
	
	/**
	 * 解压字节数组
	 * @author haojian
	 * Apr 1, 2013 9:49:34 AM
	 * @param input
	 * @return
	 */
	public static byte[] decompressBytes(byte[] input){
		Inflater decompresser = new Inflater();
		decompresser.setInput(input);
		byte[] temp = new byte[1024];
		byte[] output = new byte[0];
		while(!decompresser.needsInput()){//如果数据没有全部解压完
			try {
				int decompressedDateLength =  decompresser.inflate(temp);
				int currentLen = output.length;
				output = Arrays.copyOf(output, currentLen + decompressedDateLength);
				System.arraycopy(temp, 0, output, currentLen, decompressedDateLength);
			} catch (DataFormatException e) {
				e.printStackTrace();
				logger.error("解压字节数组出错.");
				break;
			}
		}
		decompresser.end();
		return output;
	}

}
