package com.bluemobi.product.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Util {

	public static String strTOSHA(String str) {

		try {
			byte[] plainText = str.getBytes("UTF-8");
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.update(plainText);
			byte[] digest = messageDigest.digest();
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < digest.length; i++) {
				builder = builder.append(String.format("%x", digest[i]));
			}
			return builder.toString().toUpperCase();			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	

}
