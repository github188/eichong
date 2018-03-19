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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.bluemobi.product.model.common.ExportFileModel;
import com.bluemobi.product.utils.DateUtil;

/**
 * 数据导出共用类
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年9月30日
 */
public class DataExportCommon {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(DataExportCommon.class);

	/** 导出配置文件 */
	public static String EXPORT_FILE_NAME = "data-export.properties";
	/** 导出配置文件 */
	public static String FILE_NAME_PRE = "file_name";
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
					.getResourceAsStream(EXPORT_FILE_NAME);
			try {
				//
				// 消息配置文件
				//
				messageProperties = new OrderedProperties();
				messageProperties.load(inputStreamMsg);
				Set<Object> keySet = messageProperties.keySet(); // 遍历属性列表
																	// 并打印属性信息
				for (Object obj : keySet) {
					String key = obj.toString();
					String keys0 = key.substring(0, key.indexOf("."));

					//
					// 如果设置属性为文件属性
					//
					if (key.startsWith(FILE_NAME_PRE)) {
						// 取得文件名称
						String fileName = messageProperties.getProperty(key);

						List<ExportFileModel> settingList = settingListMap
								.get(keys0);
						if (settingList == null) {
							// 为文件名称设置一个新的属性列表
							settingList = new ArrayList<ExportFileModel>();
							settingListMap.put(fileName, settingList);
						}
					} else {
						ExportFileModel exportFileModel = new ExportFileModel();
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

						exportFileModel.setFormart(format);
						exportFileModel.setProperty(property);
						exportFileModel.setName(name);

						settingList.add(exportFileModel);
					}
				}
				// 记录系统设置文件加载成功
				log.info("Secure " + EXPORT_FILE_NAME + " is loaded.");
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
	 * @param isWithIndex
	 *            是否加入序号
	 * @return String csv文件字符串
	 */
	public static String createCsvData(String fileName, List<?> dataList,
			boolean isWithIndex) {
		// csv文件字符串
		String csvFileData = new String();

		if (dataList == null) {
			dataList = new ArrayList<Object>();
		}
		List<ExportFileModel> settingList = settingListMap.get(fileName);

		if (settingList == null || settingList.size() == 0) {
			return "";
		}
		csvFileData = handleResult(dataList, settingList, isWithIndex);

		// 返回csv文件字符串
		return csvFileData;

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
	public static String createCsvFlieName(String fileName) {
		// csv文件文件名
		String csvFlieName = new String();

		csvFlieName = fileName + "_"
				+ DateUtil.toDateFormat(new Date(), "yyyyMMddHHmmSS")
				+ FILE_EXTENSION;

		// 返回csv文件名
		return csvFlieName;

	}

	/**
	 * 生成Csv数据
	 * 
	 * @param objList
	 *            数据列表
	 * @param settingList
	 *            设置列表
	 * @param isWithIndex
	 *            是否加入序号
	 * @return String csv文件字符串
	 */
	private static String handleResult(List<?> objList,
			List<ExportFileModel> settingList, boolean isWithIndex) {
		String data = "";

		// if (objList != null) {
		data = new CsvCreaterObject(objList, settingList, isWithIndex)
				.toString();
		// } else {
		// data = "";
		// }

		return data;

	}

	public static void main(String[] args) {
		new DataExportCommon();
	}
}
