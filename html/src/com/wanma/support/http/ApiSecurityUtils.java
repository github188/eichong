/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wanma.support.http;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 *
 * @author
 */
public class ApiSecurityUtils {

    private static final Log LOGGER = LogFactory.getLog(ApiSecurityUtils.class);

    public static final String HMD5 = "HmacMD5";
    public static final String HS1 = "HmacSHA1";
    public static final String HS256 = "HmacSHA256";
    public static final String HS384 = "HmacSHA384";
    public static final String HS512 = "HmacSHA512";

    /**
     * Hex编码.
     */
    public static String encodeHex(byte[] input) {
        return Hex.encodeHexString(input);
    }

    /**
     * Hex解码.
     */
    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw  new RuntimeException(e);
        }
    }

    /**
     * Base64编码.
     */
    public static String encodeBase64(byte[] input) {

        return Base64.encodeBase64String(input);
    }

    /**
     * Base64解码.
     */
    public static byte[] decodeBase64(String input) {
        return Base64.decodeBase64(input);
    }

    /**
     * HMAC加密
     *
     * @param data 需要加密的字符串
     * @param key 密钥
     * @return Hex字符串
     */
    public static String encrypt(String data, String key, String hmacAlgorithm) {
        if (data == null || "".equals(data.trim())) {
            return null;
        }
        byte[] bytes = encrypt(data.getBytes(), key, hmacAlgorithm);
        return encodeHex(bytes);
    }

    public static byte[] encrypt(byte[] data, String key, String hmacAlgorithm) {
        try {
            SecretKey secretKey;
            byte[] bytes = null;

            secretKey = new SecretKeySpec(decodeBase64(key), hmacAlgorithm);
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data);

            return bytes;
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("不支持的Hmac 算法： "+hmacAlgorithm, ex);
            return new byte[]{};
        } catch (InvalidKeyException ex) {
            LOGGER.error("无效的Key： "+key, ex);
            return new byte[]{};
        }
    }

    public static void main(String[] args) {
          System.out.println("signMe");
        String partnerKey = "/57Ic8Cc+TSUcWaRI7zgDzyDw9kE3UN6FhP2xyQhwXQ=";
     
      
      
        long timestamp = 1463454650558L;//System.currentTimeMillis();
        System.out.println("timestamp:"+timestamp);
     
        String token = "JyOA6nhKeRMVZP9c";
     
       
        String expResult = "45a19ccd5c63adcfd6da1ab37850b97a67944e53051d536127a3eca848171568";
     
      
       String data = token + "#" + timestamp;
       String result = ApiSecurityUtils.encrypt(data,partnerKey,ApiSecurityUtils.HS256);
        System.out.println(result);
        System.out.println("result is expResult:" + result.equals(expResult));
    }
}
