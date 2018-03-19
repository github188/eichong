package com.wanma.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wanma.util.ObjectUtil;

public class StringUtil {
	private static Logger log = Logger.getLogger(StringUtil.class);
	private static final String DEFAULT_SEPERATOR = ",";

	/**
	 * 去除最后一位
	 * 
	 * @param s
	 * @return
	 */
	public static String removeEnd(String s) {
		return removeEnd(s, DEFAULT_SEPERATOR);
	}

	/**
	 * 去除最后一位
	 * 
	 * @param s
	 * @param remove
	 * @return
	 */
	public static String removeEnd(String s, String remove) {
		return StringUtils.removeEnd(s, remove);
	}
	
	/**
	 * 字符串转换成数组
	 * 
	 * @param content
	 * @return
	 */
	public static String[] split(String content) {
		if (ObjectUtil.isNotEmpty(content)) {
			return content.split(DEFAULT_SEPERATOR);
		}
		return null;
	}

	/**
	 * 字符串转换成数组
	 * 
	 * @param content
	 * @param seperator
	 * @return
	 */
	public static String[] split(String content, String seperator) {
		if (ObjectUtil.isNotEmpty(content)) {
			return content.split(seperator);
		}
		return null;
	}

	/**
	 * 字符串转换成arrayList
	 * 
	 * @param content
	 * @return
	 */
	public static List<String> splitToList(String content) {
		return splitToList(content, DEFAULT_SEPERATOR);
	}

	/**
	 * 字符串转换成arrayList
	 * 
	 * @param content
	 * @param seperator
	 * @return
	 */
	public static List<String> splitToList(String content, String seperator) {
		String[] array = split(content, seperator);
		List<String> list = null;
		if (ObjectUtil.isNotEmpty(array)) {
			list = new ArrayList<String>();
			for (String s : array) {
				list.add(s);
			}
		}
		return list;
	}

	/**
	 * 数组合并为字符串
	 * 
	 * @param array
	 * @return
	 */
	public static String concatArray(Object[] array) {
		return concatArray(array, DEFAULT_SEPERATOR);
	}

	/**
	 * 数组合并为字符串
	 * 
	 * @param array
	 * @param seperator
	 * @return
	 */
	public static String concatArray(Object[] array, String seperator) {
		StringBuilder sb = new StringBuilder();
		if (ObjectUtil.isNotEmpty(array)) {
			int length = array.length;
			for (int i = 0; i < length; i++) {
				sb.append(array[i]);
				if (i != length - 1) {
					sb.append(seperator);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 集合合并为字符串
	 * 
	 * @param collection
	 * @return
	 */
	public static <T> String concatCollection(Collection<T> collection) {
		return concatCollection(collection, DEFAULT_SEPERATOR);
	}

	/**
	 * 集合合并为字符串
	 * 
	 * @param cllt
	 * @param seperator
	 * @return
	 */
	public static <T> String concatCollection(Collection<T> collection,
			String seperator) {
		StringBuilder sb = new StringBuilder();
		if (ObjectUtil.isNotEmpty(collection)) {
			int count = 0;
			int size = collection.size();
			for (T str : collection) {
				sb.append(str);
				if (++count != size) {
					sb.append(seperator);
				}
			}
		}
		return sb.length() == 0 ? "" : sb.toString();
	}

	/**
	 * 字符串转成整数
	 * 
	 * @param s
	 * @param defaultValue
	 * @return
	 */
	public static Integer parseInt(String s, Integer defaultValue) {
		Integer value = defaultValue;
		try {
			value = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			log.error("ObjectUtil.parseInt(" + s + "," + defaultValue
					+ ") error：" + e.getLocalizedMessage());
		}
		return value;
	}

	/**
	 * 字符串转成整数
	 * 
	 * @param s
	 * @return
	 */
	public static Integer parseInt(String s) {
		return parseInt(s, null);
	}

	/**
	 * 字符串转成double型
	 * 
	 * @param s
	 * @param defaultValue
	 * @return
	 */
	public static Double parseDouble(String s, Double defaultValue) {
		Double value = defaultValue;
		try {
			value = Double.parseDouble(s);
		} catch (NumberFormatException e) {
			log.error("ObjectUtil.parseDouble(" + s + "," + defaultValue
					+ ") error：" + e.getLocalizedMessage());
		}
		return value;
	}

	/**
	 * 字符串转成double型
	 * 
	 * @param s
	 * @return
	 */
	public static Double parseDouble(String s) {
		return parseDouble(s, null);
	}

	/**
	 * 字符串转成Float型
	 * 
	 * @param s
	 * @param defaultValue
	 * @return
	 */
	public static Float parseFloat(String s, Float defaultValue) {
		Float value = defaultValue;
		try {
			value = Float.parseFloat(s);
		} catch (NumberFormatException e) {
			log.error("ObjectUtil.parseFloat(" + s + "," + defaultValue
					+ ") error：" + e.getLocalizedMessage());
		}
		return value;
	}

	/**
	 * 字符串转成Float型
	 * 
	 * @param s
	 * @return
	 */
	public static Float parseFloat(String s) {
		return parseFloat(s, null);
	}
	
	/**
	 * null转空串
	 * @param object
	 * @return
	 */
	public static String nullToEmpty(Object object) {
		return object == null ? "" : String.valueOf(object);
	}
	
	/**
	 * null或空串转0
	 * 
	 * @param object
	 * @return
	 */
	public static Integer nullToZero(Object object) {
		return "".equals(nullToEmpty(object)) ? 0 : new Integer(object.toString());
	}
	
}
