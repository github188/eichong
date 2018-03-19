/**
 * FileName: SensitiveWordUtils.java
 * Author: yangwr
 * Create: 2014年7月1日
 * Last Modified: 2014年9月1日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bluemobi.product.common.dao.SensitiveWordDao;

/**
 * 敏感词处理用工具类
 * 
 * @version V1.0
 * @author yangwr
 * @date 2014年9月1日
 */
public class SensitiveWordUtils {

	/** 上一次使用过的关键字列表 */
	public static List<String> first = new ArrayList<String>();
	/** 排序用上一次使用过的关键字列表 */
	public static String[] sortFirst;
	/** 用上一次使用过的关键字字符列表 */
	public static char[] charFirst;
	/** 关键字MAP */
	public static HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	/** 排序后用关键字MAP */
	public static HashMap<String, String[]> sortMap = new HashMap<String, String[]>();
	/** 关键字字符串MAP */
	public static HashMap<String, char[]> charMap = new HashMap<String, char[]>();
	/** 临时保存用列表 */
	public static ArrayList<String> temp;
	/** 键 */
	public static String key;
	/** 值 */
	public static String value;
	/** 长度 */
	public static int length;

	static {
		SensitiveWordDao sensitiveWordDao = new SensitiveWordDao();
		first = sensitiveWordDao.getSensitiveWordList();
		initSensitiveWords(new ArrayList<String>());
	}

	public static void resetSensitiveWords() {
		SensitiveWordDao sensitiveWordDao = new SensitiveWordDao();
		first = sensitiveWordDao.getSensitiveWordList();
		initSensitiveWords(new ArrayList<String>());
	}

	/**
	 * 带参数的构造函数
	 * 
	 * @param keys
	 *            敏感词
	 * @param tContent
	 *            需要过滤的内容
	 */
	public static void initSensitiveWords(List<String> keys) {

		for (String k : keys) {
			if (!first.contains(k)) {
				first.add(k);
			}
			length = k.length();
			for (int i = 1; i < length; i++) {
				key = k.substring(0, i);
				value = k.substring(i, i + 1);
				if (i == 1 && !first.contains(key)) {
					first.add(key);
				}

				// 有，添加
				if (map.containsKey(key)) {
					if (!map.get(key).contains(value)) {
						map.get(key).add(value);
					}
				}
				// 没有添加
				else {
					temp = new ArrayList<String>();
					temp.add(value);
					map.put(key, temp);
				}
			}
		}
		sortFirst = first.toArray(new String[first.size()]);
		Arrays.sort(sortFirst); // 排序

		charFirst = new char[first.size()];
		for (int i = 0; i < charFirst.length; i++) {
			charFirst[i] = first.get(i).charAt(0);
		}
		Arrays.sort(charFirst); // 排序

		String[] sortValue;
		ArrayList<String> v;
		Map.Entry<String, ArrayList<String>> entry;
		Iterator<Entry<String, ArrayList<String>>> iter = map.entrySet()
				.iterator();
		while (iter.hasNext()) {
			entry = (Map.Entry<String, ArrayList<String>>) iter.next();
			v = (ArrayList<String>) entry.getValue();
			sortValue = v.toArray(new String[v.size()]);
			Arrays.sort(sortValue); // 排序
			sortMap.put(entry.getKey(), sortValue);
		}

		char[] charValue;
		iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			entry = (Map.Entry<String, ArrayList<String>>) iter.next();
			v = (ArrayList<String>) entry.getValue();
			charValue = new char[v.size()];
			for (int i = 0; i < charValue.length; i++) {
				charValue[i] = v.get(i).charAt(0);
			}
			Arrays.sort(charValue); // 排序
			charMap.put(entry.getKey(), charValue);
		}
	}

	/**
	 * 把敏感词替换成*
	 * 
	 * @param content
	 *            需要过滤的内容
	 * @return 过滤完后的符合要求的内容
	 */
	public static String replace(String content) {
		for (String tempStr : first) {
			if (content.indexOf(tempStr) >= 0) {
				content = content.replaceAll(tempStr, appendMask(tempStr.length()));
			}
		}

		return content;
	}

	/**
	 * 把敏感词替换成*
	 * 
	 * @param content
	 *            需要过滤的内容
	 * @return 过滤完后的符合要求的内容
	 */
	public static String replaceChar(String content) {
		String r = null, f, c = content;
		String replacedword = content;
		char g;
		char[] temps;
		int length = c.length();
		for (int i = 0; i < length - 1; i++) {
			g = c.charAt(i);
			// 二分查找
			if (Arrays.binarySearch(charFirst, g) > -1) {
				tag: for (int j = i + 1; j < length; j++) {
					f = c.substring(i, j);
					g = c.charAt(j);
					temps = charMap.get(f);
					if (temps == null) { // 找到了
						System.out.println("ok");
						r = f;
						String str = "";
						for (int m = 1; m <= r.length(); m++) {
							str = str + "*";
						}
						replacedword = c.replace(r, str);
						c = replacedword;
						break tag;
					}
					// 二分查找
					if (Arrays.binarySearch(temps, g) > -1) {
						if (j == length - 1) {
							// print("find!");
							System.out.println("find!");
							r = c.substring(i, j + 1);
							String str = "";
							for (int m = 1; m <= r.length(); m++) {
								str = str + "*";
							}
							replacedword = c.replace(r, str);
							c = replacedword;
							break tag;
						}
					} else { // 没有找到了
						break;
					}
				}
			}
		}
		return replacedword;
	}

	/**
	 * 追加*个数
	 * 
	 * @param args
	 */
	private static String appendMask(int length) {
		String maskString = "";
		for (int i = 0; i < length; i++) {
			maskString = maskString + "*";
		}

		return maskString;
	}

	public static void main(String args[]) {
		SensitiveWordUtils.resetSensitiveWords();
		System.out.println(SensitiveWordUtils.replace("态度差啊"));
	}
}
