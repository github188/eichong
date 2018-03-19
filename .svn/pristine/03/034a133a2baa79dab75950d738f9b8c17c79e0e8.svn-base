package com.sgcc.utils;


import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*******************************************************************************
 * AES加解密算法 2016.07.22
 * 
 * @author 王学明 aes 128位 cbc 算法 HTML的&lt;
 *         &gt;&amp;&quot;&copy;&nbsp;分别是<，>，&，"，©;空格的转义字符
 */
public class AESUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(AESUtils.class);
	private static String encoded = "UTF-8";
	// 加密
	public static String encrypt(String sSrc, String keyStr, String ivStr) {

		if (keyStr==null||keyStr.length()!=16) {
			LOGGER.info("没有传入AES加密的加密秘钥");
			return null;
		}

		if (ivStr==null||ivStr.length()!=16) {
			LOGGER.info("没有传入AES CBC加密的加密向量");
			return null;
		}

		if (keyStr.length() != 16) {
			LOGGER.info("AES加密的加密秘钥长度不是16位");
			return null;
		}

		SecretKeySpec skeySpec = new SecretKeySpec(keyStr.getBytes(), "AES");

		// "算法/模式/补码方式"
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			LOGGER.info("Cipher error");
			return null;
		}

		// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());

		try {
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			LOGGER.info("CBC (IvParameterSpec) error");
			return null;
		}

		byte[] encrypted;
		try {
			encrypted = cipher.doFinal(sSrc.getBytes(encoded));
		} catch (IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
			LOGGER.info("byte (encrypted) error");
			return null;
		}
		String str = new BASE64Encoder().encode(encrypted);
		return str.replaceAll("\r\n", "");
	}

	// 解密
	public static String decrypt(String sSrc, String sKey, String ivStr) {
		try {
			// 判断Key是否正确
			if (sKey == null) {
				LOGGER.info("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
				LOGGER.info("Key长度不是16位");
				return null;
			}
			byte[] raw = sKey.getBytes(encoded);
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			return new String(original,encoded);			
		} catch (Exception ex) {
			LOGGER.info(ex.toString());
			return null;
		}
	}

	public static void main(String[] args) throws Throwable {
		/*
		 * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符，虽然不会错，至于怎么裁决，个人看情况而定
		 * 此处使用AES-128-CBC加密模式，key需要为16位。
		 */
		String cKey = "1234567890abcdef";

		// 需要加密的字串
		String cSrc = "hello world!";

		LOGGER.info(cSrc);
		// 加密
		long lStart = System.currentTimeMillis();
		String enString = AESUtils.encrypt(cSrc, cKey, cKey);
		LOGGER.info("加密后的字串是：{}", enString);

		long lUseTime = System.currentTimeMillis() - lStart;
		LOGGER.info("加密耗时：{}毫秒" , lUseTime );
		// 解密
		lStart = System.currentTimeMillis();
		String deString = AESUtils.decrypt(enString, cKey, cKey);
		LOGGER.info("解密后的字串是：{}" , deString);
		lUseTime = System.currentTimeMillis() - lStart;
		LOGGER.info("解密耗时：{}毫秒" , lUseTime);
	}
}