package com.bluemobi.product.utils;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 将Object对象转化成json字符串
 */
public class JsonObject {
	private StringBuffer sb;
	@SuppressWarnings("rawtypes")
	private Map<Class, List<String>> excludeMap;

	@SuppressWarnings("rawtypes")
	public JsonObject(Object obj, Map<Class, List<String>> typeExcludesFields) {
		excludeMap = typeExcludesFields;
		sb = new StringBuffer();
		try {
			handleObject(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JsonObject(Object obj) {
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
			sb.append("\"\"");
			return;
		}
		Class<? extends Object> clazz = obj.getClass();
		String javaName = clazz.getName();
		if (javaName.startsWith("java.")) {
			if (clazz.getSuperclass() == null) {
				sb.append("\"").append(obj != null ? obj.toString() : "")
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
					sb.append("\"").append(obj != null ? obj.toString() : "")
							.append("\"");
				}
			}
		} else {
			sb.append("{");
			Field[] fields = clazz.getDeclaredFields();
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
				handleObject(val);

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
	private void handleList(List list) {
		sb.append("[");
		Iterator it = list.iterator();
		try {
			while (it.hasNext()) {
				Object obj = it.next();
				handleObject(obj);
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
