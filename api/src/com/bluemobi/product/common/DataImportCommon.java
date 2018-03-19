/**
 * FileName:DataExportCommonjava
 * Author: Administrator
 * Create: 2014年6月30日
 * Last Modified: 2014年9月30日
 * Version: V1.0 
 */
package com.bluemobi.product.common;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.bluemobi.product.model.common.ExportFileModel;

/**
 * 数据导入共用类
 * 
 * @version V1.0
 * @author Administrator
 * @param <T>
 * @date 2014年9月30日
 */
public class DataImportCommon<T> {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(DataImportCommon.class);

	/** 导入配置文件 */
	public static String IMPORT_FILE_NAME = "data-import.properties";
	/** 导入配置文件 */
	public static String FILE_NAME_PRE = "class_name";
	/** 配置文件 */
	private static OrderedProperties messageProperties = null;
	/** 文件扩展名 */
	private static String FILE_EXTENSION = ".csv";

	/** 配置文件MAP列表 */
	public static Map<String, List<ExportFileModel>> settingListMap = new HashMap<String, List<ExportFileModel>>();

	static {
		try {
			// 取得配置文件
			InputStream inputStreamMsg = MessageManager.class.getClassLoader()
					.getResourceAsStream(IMPORT_FILE_NAME);
			try {
				//
				// 消息配置文件
				//
				messageProperties = new OrderedProperties();
				messageProperties.load(inputStreamMsg);
				Set<Object> keySet = messageProperties.keySet(); // 遍历属性列表
				for (Object obj : keySet) {
					String key = obj.toString();

					//
					// 如果设置属性为文件属性
					//
					if (key.startsWith(FILE_NAME_PRE)) {
						// 取得文件名称
						String fileName = messageProperties.getProperty(key);
						String keys0 = key.substring(0, key.indexOf("."));

						List<ExportFileModel> settingList = settingListMap
								.get(keys0);
						if (settingList == null) {
							// 为文件名称设置一个新的属性列表
							settingList = new ArrayList<ExportFileModel>();
							settingListMap.put(fileName, settingList);
						}
					} else {
						ExportFileModel importFileModel = new ExportFileModel();
						String keys0 = key.substring(0, key.lastIndexOf("."));
						List<ExportFileModel> settingList = settingListMap
								.get(keys0);

						if (settingList == null) {
							settingList = new ArrayList<ExportFileModel>();
							settingListMap.put(keys0, settingList);
						}
						String value = messageProperties.getProperty(key);
						String[] values = value.split(";");

						if (values.length < 2) {
							continue;
						}
						String property = values[0];
						String name = values[1];
						String format = null;

						if (values.length == 3) {
							format = values[2];

						}

						importFileModel.setFormart(format);
						importFileModel.setProperty(property);
						importFileModel.setName(name);

						settingList.add(importFileModel);
					}
				}
				// 记录系统设置文件加载成功
				log.info("Secure " + IMPORT_FILE_NAME + " is loaded.");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				inputStreamMsg.close();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

	/**
	 * 根据传入的数据列表，生成CSV文件字符串
	 * 
	 * @param fileName
	 *            文件名
	 * @param dataList
	 *            数据列表
	 * @return String csv文件字符串
	 */
	/*@SuppressWarnings("unchecked")
	public static final <T> List<T> getCsvData(MultipartFile multipartFile,
			Class<?> clazz) {

		if (multipartFile == null || multipartFile.isEmpty()
				|| multipartFile.getSize() == 0) {
			return null;
		}
		// csv文件字符串
		List<T> list = new ArrayList<T>();
		String[][] contentList;
		try {
			contentList = CsvFleOperator
					.readUpdateedCSVFileIS(new ByteArrayInputStream(
							multipartFile.getBytes()));
			List<ExportFileModel> settingList = settingListMap.get(clazz
					.getName());
			int index = 0;
			for (String[] arrayData : contentList) {

				if (index == 0) {
					index++;
					continue;
				}
				Object object = Class.forName(clazz.getName()).newInstance();
				for (int i = 0; i < settingList.size(); i++) {
					String value = arrayData[i];
					String fieldName = settingList.get(i).getProperty();
					ReflectionUtils.setFieldValue(object, fieldName, value);
				}
				list.add((T) object);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 返回csv文件字符串
		return list;

	}*/

	/**
	 * 根据传入的数据列表，生成CSV文件字符串
	 * 
	 * @param fileName
	 *            文件名
	 * @param dataList
	 *            数据列表
	 * @return String csv文件字符串
	 */
	/*public static String createCsvFlieName(String fileName) {
		// csv文件文件名
		String csvFlieName = new String();

		csvFlieName = fileName + "_"
				+ DateUtil.toDateFormat(new Date(), "yyyyMMddHHmmSS")
				+ FILE_EXTENSION;

		// 返回csv文件名
		return csvFlieName;

	}*/

}
