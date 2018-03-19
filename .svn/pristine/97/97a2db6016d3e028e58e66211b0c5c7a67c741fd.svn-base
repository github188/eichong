package com.bluemobi.product.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 将Object对象转化成json字符串
 */
public class AppJsonObject {
	private StringBuffer sb;
	@SuppressWarnings("rawtypes")
	private Map<Class, List<String>> excludeMap;

	public AppJsonObject(
			Object obj,
			@SuppressWarnings("rawtypes") Map<Class, List<String>> typeExcludesFields) {
		excludeMap = typeExcludesFields;
		sb = new StringBuffer();
		try {
			handleObject(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AppJsonObject(Object obj) {
		sb = new StringBuffer();
		try {
			handleObject(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	private void handleObject(Object obj) throws Exception {
		if (obj == null) {
			sb.append(String.valueOf(obj));
			return;
		}
		Class<? extends Object> clazz = obj.getClass();
		String javaName = clazz.getName();
		if (javaName.startsWith("java.")) {
			if (clazz.getSuperclass() == null) {
				sb.append("\"").append(obj != null ? obj.toString() : "")
						.append("\"");
			} else if (javaName.startsWith("java.util.")
					&& javaName.endsWith("Date")) {
				sb.append("\"")
						.append(obj != null ? DateUtil
								.toDateDefaultFormat((Date) obj) : "")
						.append("\"");
			} else {
				String superName = clazz.getSuperclass().getName();
				if (superName.startsWith("java.util.")
						&& superName.endsWith("List")) {
					handleList((List<?>) obj);
				} else if (superName.startsWith("java.util.")
						&& superName.endsWith("Map")) {
					handleMap((Map) obj);
				} else {
					sb.append("\"").append(StringUtil.toJsonString(obj))
							.append("\"");
				}
			}
		} else {
			sb.append("{");
			Field[] fields = clazz.getDeclaredFields();
			fields = getSupperClassFile(clazz, fields);
			fields = this.unQuine(fields);
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];

				boolean isForbiden = true;
				if (excludeMap == null || excludeMap.get(clazz) == null) {
					isForbiden = false;
				} else if (excludeMap.get(clazz).contains(f.getName())) {
					isForbiden = false;
				}
				if (isForbiden) {
					continue;
				}
				f.setAccessible(true);
				Object val = f.get(obj);
				sb.append("\"").append(f.getName()).append("\":");

				Map<Class, List<String>> controlMap = new HashMap<Class, List<String>>();
				List<String> pubList = null;
				if (val != null) {
					pubList = JsonPublishXmlReader.getPublishList(val
							.getClass().getName());
					controlMap.put(val.getClass(), pubList);
				}

				handleObject(val, controlMap);

				if (i < fields.length - 1)
					sb.append(",");
			}
			// 删除最后的逗号
			if (sb.lastIndexOf(",") == sb.length() - 1)
				sb.deleteCharAt(sb.length() - 1);
			sb.append("}");
		}
	}

	@SuppressWarnings("rawtypes")
	private void handleObject(Object obj,
			Map<Class, List<String>> typeExcludesFields) throws Exception {
		if (obj == null) {
			sb.append(String.valueOf(obj));
			return;
		}
		Class<? extends Object> clazz = obj.getClass();
		String javaName = clazz.getName();
		if (javaName.startsWith("java.")) {
			if (clazz.getSuperclass() == null) {
				sb.append("\"").append(obj != null ? obj.toString() : "")
						.append("\"");
			} else if (javaName.startsWith("java.util.")
					&& javaName.endsWith("Date")) {
				sb.append("\"")
						.append(obj != null ? DateUtil
								.toDateDefaultFormat((Date) obj) : "")
						.append("\"");
			} else {
				String superName = clazz.getSuperclass().getName();
				if (superName.startsWith("java.util.")
						&& superName.endsWith("List")) {
					handleList((List) obj);
				} else if (superName.startsWith("java.util.")
						&& superName.endsWith("Map")) {
					handleMap((Map) obj);
				} else {
					sb.append("\"").append(StringUtil.toJsonString(obj))
							.append("\"");
				}
			}
		} else {
			sb.append("{");
			Field[] fields = clazz.getDeclaredFields();
			fields = getSupperClassFile(clazz, fields);
			fields = this.unQuine(fields);
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];

				boolean isForbiden = true;
				if (typeExcludesFields == null
						|| typeExcludesFields.get(clazz) == null) {
					isForbiden = false;
				} else if (typeExcludesFields.get(clazz).contains(f.getName())) {
					isForbiden = false;
				}
				if (isForbiden) {
					continue;
				}
				f.setAccessible(true);
				Object val = f.get(obj);
				sb.append("\"").append(f.getName()).append("\":");

				Map<Class, List<String>> controlMap = new HashMap<Class, List<String>>();
				List<String> pubList = null;
				if (val != null) {
					pubList = JsonPublishXmlReader.getPublishList(val
							.getClass().getName());
					controlMap.put(val.getClass(), pubList);
				}

				handleObject(val, controlMap);

				if (i < fields.length - 1)
					sb.append(",");
			}
			// 删除最后的逗号
			if (sb.lastIndexOf(",") == sb.length() - 1)
				sb.deleteCharAt(sb.length() - 1);
			sb.append("}");
		}
	}

	private Field[] getSupperClassFile(Class<? extends Object> clazz,
			Field[] fields) {
		String javaName = clazz.getSuperclass().getName();
		if (!javaName.startsWith("java.")) {

			Field[] superFields = clazz.getSuperclass().getDeclaredFields();
			fields = concat(fields, superFields);
			if (!javaName.startsWith("java.")) {
				fields = getSupperClassFile(clazz.getSuperclass(), fields);
			}
		}
		return fields;
	}

	private Field[] unQuine(Field[] fields) {

		List<Field> list = new ArrayList<Field>();
		List<String> listName = new ArrayList<String>();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String propertyName = field.getName();
			if (!listName.contains(propertyName)) {// 如果数组 list 不包含当前项，则增加该项到数组中
				listName.add(propertyName);
				list.add(fields[i]);
			}
		}

		return list.toArray(new Field[list.size()]);

	}

	public static Field[] concat(Field[] a, Field[] b) {
		Field[] c = new Field[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}

	@SuppressWarnings("rawtypes")
	private void handleList(List list) {
		sb.append("[");
		Iterator it = list.iterator();
		try {
			while (it.hasNext()) {
				Object obj = it.next();

				Map<Class, List<String>> controlMap = new HashMap<Class, List<String>>();
				List<String> pubList = null;
				if (obj != null) {
					pubList = JsonPublishXmlReader.getPublishList(obj
							.getClass().getName());
					controlMap.put(obj.getClass(), pubList);
				}

				handleObject(obj, controlMap);
				if (it.hasNext())
					sb.append(",");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		sb.append("]");
	}

	@SuppressWarnings("rawtypes")
	private void handleMap(Map map) {
		sb.append("{");
		Iterator it = map.keySet().iterator();
		try {
			while (it.hasNext()) {
				Object key = it.next();
				Object val = map.get(key);
				sb.append("\"");
				if (key != null)
					sb.append(key.toString());
				sb.append("\":");
				handleObject(val);
				if (it.hasNext())
					sb.append(",");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		sb.append("}");
	}

	public String toString() {
		return sb.toString();
	}

}
