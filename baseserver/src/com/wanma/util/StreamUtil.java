package com.wanma.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

public class StreamUtil {
	private static Logger log = Logger.getLogger(StreamUtil.class);
	private final static int BUFFER_SIZE = 4096;
	private final static String DEFAULT_CHARSET = "UTF-8";

	/**
	 * InputStream转换成UTF-8字符编码的String
	 * 
	 * @param in
	 *            InputStream
	 * @return String
	 * @throws Exception
	 * 
	 */
	public static String streamToString(InputStream in) {
		return streamToString(in, DEFAULT_CHARSET);
	}

	/**
	 * InputStream转换成某种字符编码的String
	 * 
	 * @param in
	 * @param encoding
	 * @return String
	 * @throws Exception
	 */
	public static String streamToString(InputStream in, String encoding) {
		String string = null;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		try {
			while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
				outStream.write(data, 0, count);
		} catch (IOException e) {
			log.error("StreamUtils.streamToString error1:"
					+ e.getLocalizedMessage());
		}

		data = null;
		try {
			string = new String(outStream.toByteArray(), encoding);
		} catch (UnsupportedEncodingException e) {
			log.error("StreamUtils.streamToString error2:"
					+ e.getLocalizedMessage());
		}
		return string;
	}

	/**
	 * InputStream转换成byte数组
	 * 
	 * @param in
	 *            InputStream
	 * @return byte[]
	 */
	public static byte[] streamToByte(InputStream in) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		try {
			while ((count = in.read(data, 0, BUFFER_SIZE)) != -1) {
				outStream.write(data, 0, count);
			}
		} catch (IOException e) {
			log.error("StreamUtils.streamToByte error:"
					+ e.getLocalizedMessage());
			return null;
		}
		data = null;
		return outStream.toByteArray();
	}

	/**
	 * String转换成InputStream
	 * 
	 * @param in
	 * @return InputStream
	 */
	public static InputStream stringToStream(String in) {
		return stringToStream(in, DEFAULT_CHARSET);
	}

	/**
	 * String转换成InputStream
	 * 
	 * @param in
	 * @param encode
	 * @return InputStream
	 */
	public static InputStream stringToStream(String in, String encode) {
		ByteArrayInputStream is = null;
		try {
			is = new ByteArrayInputStream(in.getBytes(encode));
		} catch (UnsupportedEncodingException e) {
			log.error("StreamUtils.stringToStream error:"
					+ e.getLocalizedMessage());
		}
		return is;
	}

	/**
	 * String转换成byte[]
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static byte[] stringToByte(String in) {
		return stringToByte(in, DEFAULT_CHARSET);
	}

	/**
	 * String转换成byte[]
	 * 
	 * @param in
	 * @param encode
	 * @return
	 */
	public static byte[] stringToByte(String in, String encode) {
		try {
			return in.getBytes(encode);
		} catch (UnsupportedEncodingException e) {
			log.error("StreamUtils.stringToByte error:"
					+ e.getLocalizedMessage());
			return null;
		}
	}

	/**
	 * 将byte数组转换成InputStream
	 * 
	 * @param in
	 * @return InputStream
	 */
	public static InputStream byteToStream(byte[] bytes) {
		if(bytes==null){
			return null;
		}
		return new ByteArrayInputStream(bytes);
	}

	public static String byteToString(byte[] bytes){
		return byteToString(bytes, DEFAULT_CHARSET);
	}
	/**
	 * 将byte数组转换成String
	 * 
	 * @param in
	 * @return String
	 */
	public static String byteToString(byte[] bytes,String encode) {
		try {
			return new String(bytes, encode);
		} catch (UnsupportedEncodingException e) {
			log.error("StreamUtils.byteToString error:"
					+ e.getLocalizedMessage());
			return null;
		}
	}


	/**
	 * 根据文件路径创建文件输入流处理 以字节为单位（非 unicode ）
	 * 
	 * @param path
	 * @return FileInputStream
	 */
	public static FileInputStream getFileInputStream(String filepath) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			System.out.print("错误信息:文件不存在");
			e.printStackTrace();
		}
		return fileInputStream;
	}

	/**
	 * 根据文件对象创建文件输入流处理 以字节为单位（非 unicode ）
	 * 
	 * @param path
	 * @return FileInputStream
	 */
	public static FileInputStream getFileInputStream(File file) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.print("错误信息:文件不存在");
			e.printStackTrace();
		}
		return fileInputStream;
	}

	/**
	 * 根据文件对象创建文件输出流处理 以字节为单位（非 unicode ）
	 * 
	 * @param file
	 * @param append
	 *            true:文件以追加方式打开,false:则覆盖原文件的内容
	 * @return FileOutputStream
	 */
	public static FileOutputStream getFileOutputStream(File file, boolean append) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file, append);
		} catch (FileNotFoundException e) {
			System.out.print("错误信息:文件不存在");
			e.printStackTrace();
		}
		return fileOutputStream;
	}

	/**
	 * 根据文件路径创建文件输出流处理 以字节为单位（非 unicode ）
	 * 
	 * @param path
	 * @param append
	 *            true:文件以追加方式打开,false:则覆盖原文件的内容
	 * @return FileOutputStream
	 */
	public static FileOutputStream getFileOutputStream(String filepath,
			boolean append) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(filepath, append);
		} catch (FileNotFoundException e) {
			System.out.print("错误信息:文件不存在");
			e.printStackTrace();
		}
		return fileOutputStream;
	}

}