package com.wanma.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public final class ObjectUtil {
	private static Logger log = Logger.getLogger(ObjectUtil.class);

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
	 * 处理空值结果
	 * 
	 * @param contend
	 * @return
	 */
	public static String nvlStr(Object contend) {
		return contend != null ? (contend.toString()) : "";
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

	public static void main(String[] args) {
		// System.out.println(ObjectUtil.isNotEmpty(""));
		// System.out.println(ObjectUtil.isNotEmpty(11));
		// System.out.println(ObjectUtil.isNotEmpty(new String[]{}));
		// List<String> list = new ArrayList<String>();
		// list.add("1");
		// System.out.println(ObjectUtil.isNotEmpty(new ArrayList<String>()));
		// System.out.println(ObjectUtil.parseInt("3s"));
	}
}
