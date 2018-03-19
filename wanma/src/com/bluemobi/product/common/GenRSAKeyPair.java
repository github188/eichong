package com.bluemobi.product.common;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 生成银联相关公钥和私钥
 * 
 * @author Administrator
 * 
 */
public class GenRSAKeyPair {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");

		KeyPair pair = kpg.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) pair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) pair.getPrivate();
		String pubMod = publicKey.getModulus().toString(16);
		String pubExp = publicKey.getPublicExponent().toString(16);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 256 - pubExp.length(); i++) {
			buf.append("0");
		}
		String pubKey = pubMod + buf + pubExp;
		System.out.println("RSA key pair generated:");
		System.out.println();
		System.out.println("Public key: \n" + pubKey);
		System.out.println();
		System.out.println("Public key modulus: \n" + pubMod);
		System.out.println();
		System.out.println("Public key exponent: \n" + buf + pubExp);
		System.out.println();
		System.out.println("Private key modulus: \n"
				+ privateKey.getModulus().toString(16));
		System.out.println();
		System.out.println("Private key exponent: \n"
				+ privateKey.getPrivateExponent().toString(16));
	}
}
