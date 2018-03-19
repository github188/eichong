package com.wanma.support.utils;

import java.util.Random;

import cn.jpush.api.utils.Base64;


public class PasswordUtil {
	
	//api接口需要的密码设置
	public static String getWanmaEncodePassword(String password, String phone) {
		password = MD5Util.Md5(password);
		password+=phone;
		password = MD5Util.Md5(password);
		String random = getRandomChar(1);
		password = password+random;
		return password;
	}
	
	/**
	 * 获取0-9 a-z A-Z随机数 cm
	 */
	public static String getRandomChar(int length) {
		char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
		buffer.append(chr[random.nextInt(62)]);
		}
		return buffer.toString();
		}
	
	
	public static String getWanmaDeviceId() {
		char[] chars = MD5Util.Md5("123456").toCharArray();
   		String encodeID = "";
   		for (int i = 0; i < chars.length; i++) {
   			encodeID += replace((byte) chars[i]);
   		}
   		encodeID = new String(Base64.encode(encodeID.getBytes()));
		return encodeID;
	}
	
	//token加密 替换字符
		public static String replace(byte a) {
			
			String str = String.valueOf((char)a);
			if (a >= 48 && a <= 57) {
				if ("1".equals(str)) {
					str = str.replaceAll("1", "7");
				} else if ("2".equals(str)) {
					str = str.replaceAll("2", "5");
				} else if ("3".equals(str)) {
					str = str.replaceAll("3", "8");
				} else if ("5".equals(str)) {
					str = str.replaceAll("5", "2");
				} else if ("6".equals(str)) {
					str = str.replaceAll("6", "9");
				} else if ("7".equals(str)) {
					str = str.replaceAll("7", "1");
				} else if ("8".equals(str)) {
					str = str.replace("8", "3");
				} else if ("9".equals(str)) {
					str = str.replace("9", "6");
				}
			}else if (a >= 65 && a <= 90) {
				//大写转小写 +32
				char b = (char) (a +32);
				b = (char) (b + 3);
				if(b > 122){
					b = (char) (b-26);
				}
				str = String.valueOf(b);
			}else if (a >= 97 && a <= 122) {
				char b = (char)(a-32);
				b = (char) (b + 3);
				if (b > 90) {
					b = (char) (b - 26);
				}
				str = String.valueOf(b);
			}
			return str;
		}
		public static void main(String[] args) {
			System.out.println(getWanmaDeviceId());
		}
}
