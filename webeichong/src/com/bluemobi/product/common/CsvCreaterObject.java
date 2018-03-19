package com.bluemobi.product.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.bluemobi.product.model.common.ExportFileModel;
import com.bluemobi.product.utils.ReflectUtil;
import com.bluemobi.product.utils.StringUtil;

/**
 * 将Object对象转化成json字符串
 */
public class CsvCreaterObject {
	private StringBuffer sb;
	private List<ExportFileModel> excludeMap;
	public static final String separate = "\t";
	private long index = 1;
	private static boolean isWithIndex = false;

	public CsvCreaterObject(List<?> objList, List<ExportFileModel> settingList,
			boolean pIsWithIndex) {
		excludeMap = settingList;
		sb = new StringBuffer();
		isWithIndex = pIsWithIndex;
		try {

			if (settingList != null && settingList.size() > 0) {
				if (isWithIndex) {
					sb.append("\"序号\"");
					sb.append(separate);
				}
				for (ExportFileModel exportFileModel : settingList) {
					sb.append("\"" + exportFileModel.getName() + "\"");
					sb.append(separate);
				}
				// 删除最后的逗号
				if (sb.lastIndexOf(separate) == sb.length() - 1) {
					sb.deleteCharAt(sb.length() - 1);
				}
			} else {
				return;
			}
			handleList(objList);
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
		if (javaName.startsWith("java.util.") && javaName.endsWith("Map")) {
			handleMap((Map) obj);
		} else {

			if (isWithIndex) {
				sb.append("\"" + index + "\"");
				sb.append(separate);
				index++;
			}

			for (int i = 0; i < excludeMap.size(); i++) {
				ExportFileModel exportFileModel = excludeMap.get(i);
				String fieldName = exportFileModel.getProperty();
				String format = exportFileModel.getFormart();
				String data = StringUtil.fomartObject(
						ReflectUtil.getVal(obj, fieldName), format);
				sb.append("\"" + data + "\"");
				if (i < excludeMap.size() - 1)
					sb.append(separate);
			}
			// 删除最后的逗号
			if (sb.lastIndexOf(separate) == sb.length() - 1)
				sb.deleteCharAt(sb.length() - 1);
		}
	}

	@SuppressWarnings("rawtypes")
	public void handleList(List list) {
		Iterator it = list.iterator();
		try {
			while (it.hasNext()) {
				Object obj = it.next();
				sb.append("\r\n");
				handleObject(obj);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	private void handleMap(Map map) {
		try {

			if (isWithIndex) {
				sb.append("\"" + index + "\"");
				sb.append(separate);
				index++;
			}
			for (int i = 0; i < excludeMap.size(); i++) {
				ExportFileModel exportFileModel = excludeMap.get(i);
				String fieldName = exportFileModel.getProperty();
				String format = exportFileModel.getFormart();
				String data = StringUtil.fomartObject(map.get(fieldName),
						format);
				sb.append("\"" + data + "\"");
				if (i < excludeMap.size() - 1)
					sb.append(separate);
			}
			// 删除最后的逗号
			if (sb.lastIndexOf(separate) == sb.length() - 1) {
				sb.deleteCharAt(sb.length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return sb.toString();
	}

	public static void main(String[] args) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("journalTitle", "测试合刊");
		map1.put("createdTime", "20140404");
		map1.put("tradeName", "所属行业01");
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("journalTitle", "测试合刊2");
		map2.put("createdTime", "20140504");
		map2.put("tradeName", "所属行业02");

		mapList.add(map1);
		mapList.add(map2);

		System.out.println(DataExportCommon.createCsvData("Journal_csv",
				mapList, false));
	}
}
