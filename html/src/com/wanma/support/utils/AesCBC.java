package com.wanma.support.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AesCBC {
	/*
	 * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
	 */
	private static AesCBC instance = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(AesCBC.class);
	private AesCBC() {

	}

	public static AesCBC getInstance() {
		if (instance == null)
			instance = new AesCBC();
		return instance;
	}

	// 加密
	public String encrypt(String sSrc, String encodingFormat, String sKey,String ivParameter) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			LOGGER.info("Cipher error");
			return null;
		}
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		try {
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			LOGGER.info("CBC (IvParameterSpec) error");
			return null;
		}
		byte[] encrypted = null;
		try {
			encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));
		} catch (IllegalBlockSizeException | BadPaddingException| UnsupportedEncodingException e) {
			LOGGER.info("byte (encrypted) error");
			return null;
		}
		return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
	}

	// 解密
	public String decrypt(String sSrc, String encodingFormat, String sKey,String ivParameter) {
		try {
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			return new String(original, encodingFormat);
		} catch (Exception ex) {
			return null;
		}
	}

}