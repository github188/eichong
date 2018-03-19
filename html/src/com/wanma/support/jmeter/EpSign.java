package com.wanma.support.jmeter;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EpSign {

	private static EpSign instance = null;


	public static EpSign getInstance() {
		if (instance == null)
			instance = new EpSign();
		return instance;
	}

	public static String checkSign(String str) {
		System.out.println("进入epsign jar包！");
		
		Map<String,String> params=new HashMap<String,String>();
       
		String[] aa=str.split(",");
		for(int i=0;i<aa.length;i++){
		params.put(aa[i].split("=")[0], aa[i].split("=")[1]);
		}
		String src = "";
		Collection<String> keyset = params.keySet();
		List<String> list = new ArrayList<>(keyset);

		// 对key键值按字典升序排序
		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) {
			String key = list.get(i);
			if (key.compareTo("sign") != 0) {
				if (src.length() != 0) {
					src = src + "&";
				}
				src += list.get(i) + "=" + params.get(key);
			}
		}

		String userKey = "SXZB88LV";

		src += userKey;

		String calcSign = MD5Encode(src.getBytes());

		return calcSign;
	}

	public static String MD5Encode(byte[] origin) {
		String resultString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(origin));
		} catch (Exception ex) {
		}
		return resultString;
	}

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static void main(String[] args) throws Exception {
		/*
		 * Map<String, String> params=new HashMap<String,String>();
		 * params.put("code", "3202130030000002"); params.put("gunno","1");
		 * params.put("user","admin"); checkSign(params);
		 */
		 String str="code=3202130030000002,gunno=1,user=admin";
		 checkSign(str);
	}
}
