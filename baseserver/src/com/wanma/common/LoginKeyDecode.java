package com.wanma.common;


/**
 * 手机设备码的解密规则
 * 
 * @author Administrator
 *
 */
public class LoginKeyDecode {
	public String Replace(char a) {
		String str = String.valueOf(a);
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
			char b = (char) (a - 3);
			if (b < 65) {
				int i = 65 - b;
				b = (char) (91 - i);
			}
			str = String.valueOf((char) (b + 32));
		}else if (a >= 97 && a <= 122) {
			char b = (char) (a - 3);
			if (b < 97) {
				int i = 97 - b;
				b = (char) (123 - i);
			}
			str = String.valueOf((char) (b - 32));
		}
		return str;
	}
	
	/*public static void main(String[] args){
		String s = "b1=";
		String ns = Base64Coder.encode(s.getBytes());
		System.out.println(ns);
		byte[] bs = Base64Coder.decode(ns);
		System.out.println(new String(bs));
		
		LoginKeyDecode k = new LoginKeyDecode();
		char[] cs = s.toCharArray();
		String n = "";
		for(char c : cs){
			n += k.Replace(c);
		}
		System.out.println(n);
	}*/
}
