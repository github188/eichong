package com.wanma.web.support.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

public final class ObjectUtil {
	private static Logger log = Logger.getLogger(DateUtil.class);

	private static String DEFAULT_SEPERATOR = ",";

	/** 私有化构造器 */
	private ObjectUtil() {
	}

	/**
	 * 判断对象是否为空 ObjectUtil.isEmpty(""); ObjectUtil.isEmpty(0);
	 * ObjectUtil.isEmpty(new String[]{}); ObjectUtil.isEmpty(new
	 * ArrayList<String>());
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		return !(isNotEmpty(obj));
	}

	/**
	 * 判断对象是否不为空 ObjectUtil.isNotEmpty(""); ObjectUtil.isNotEmpty(0);
	 * ObjectUtil.isNotEmpty(new String[]{}); ObjectUtil.isNotEmpty(new
	 * ArrayList<String>());
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		if (obj instanceof Number) {
			Number num = (Number) obj;
			return num != null;
		}
		if (obj instanceof String) {
			String arg = (String) obj;
			return arg != null && arg.trim().length() > 0;
		}

		if (obj instanceof Collection<?>) {
			Collection<?> list = (Collection<?>) obj;
			return list != null && !list.isEmpty();
		}
		if (obj instanceof Object[]) {
			Object[] arg = (Object[]) obj;
			return arg != null && arg.length > 0;
		}
		return obj != null;
	}

	/**
	 * 是否相等
	 * 
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static boolean equals(Object arg1, Object arg2) {
		return arg1 == arg2 ? true : (arg1 != null ? arg1.equals(arg2) : false);
	}

	/**
	 * 数组转换成arraylist
	 * 
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> toList(T... args) {
		if (args == null)
			return new ArrayList<T>(0);
		List<T> list = new ArrayList<T>(args.length);
		for (T t : args) {
			list.add(t);
		}
		return list;
	}

	/**
	 * 集合转换成arraylist
	 * 
	 * @param collection
	 * @return
	 */
	public static <T> List<T> toList(Collection<T> collection) {
		if (collection == null)
			return null;
		if (collection instanceof List) {
			return (List<T>) collection;
		}
		return new ArrayList<T>(collection);
	}

	/**
	 * 数组转换成hashset
	 * 
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Set<T> toSet(T... args) {
		if (args == null)
			return new HashSet<T>(0);
		Set<T> set = new HashSet<T>(args.length);
		for (T t : args) {
			set.add(t);
		}
		return set;
	}

	/**
	 * 字符串转换成数组
	 * 
	 * @param content
	 * @return
	 */
	public static String[] split(String content) {
		if (isNotEmpty(content)) {
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
		if (isNotEmpty(content)) {
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
		if (isNotEmpty(array)) {
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
		if (isNotEmpty(array)) {
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
		if (isNotEmpty(collection)) {
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

	public static void main(String[] args) {
		// System.out.println(ObjectUtil.isNotEmpty(""));
		// System.out.println(ObjectUtil.isNotEmpty(11));
		// System.out.println(ObjectUtil.isNotEmpty(new String[]{}));
		// List<String> list = new ArrayList<String>();
		// list.add("1");
		// System.out.println(ObjectUtil.isNotEmpty(new ArrayList<String>()));
		System.out.println(ObjectUtil.parseInt("3s"));
	}
}
