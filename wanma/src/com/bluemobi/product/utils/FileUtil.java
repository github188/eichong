/**
 * FileName:FileUtil.java
 * Author: Administrator
 * Create: 2014年7月3日
 * Last Modified: 2014年7月3日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.log4j.Logger;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.MessageManager;

/**
 * 文件操作工具类
 * 
 * @author Administrator
 * 
 */
public class FileUtil {

	private static final Logger logger = Logger.getLogger(FileUtil.class);

	/**
	 * 创建一个新的文件
	 * 
	 * @param fileName
	 *            文件名称
	 * @param delete
	 *            是否删除旧文件
	 * @return 成功true,成功false
	 */
	public static boolean createFile(String fileName, boolean delete) {

		File file = new File(fileName);

		if (file.exists()) {

			if (!delete) {
				return true;
			}

			if (file.delete()) {
				try {
					if (file.createNewFile()) {
						return true;
					}
				} catch (IOException e) {
					logger.fatal(e);
				}
			}
		}

		return false;
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 *            文件路径
	 * @return 成功返回true,成功返回false
	 */
	public static boolean deleteFile(String path) {

		File file = new File(path);

		if (file.exists()) {
			if (!file.delete()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 创建文件夹
	 * 
	 * @param path
	 *            路径
	 * @return true 成功，false 失败
	 */
	public static boolean createFloader(String path) {

		File file = new File(path);

		if (file.exists()) {
			if (!file.isDirectory()) {
				if (!file.mkdirs()) {

					return false;

				}
			}
		}

		return true;
	}

	/**
	 * 移动文件
	 * 
	 * @param source
	 *            要移动的文件
	 * @param target
	 *            移动到的绝对路径
	 * @return
	 */
	public static boolean moveFile(File source, String target) {

		if (source == null || target == null || "".equals(target)) {
			return false;
		}

		int dot = target.lastIndexOf("/");
		String floder = target.substring(0, dot);
		File fl = new File(floder);
		if (!fl.exists()) {
			fl.mkdirs();
		}

		File f = new File(target);

		if (!f.exists()) {
			return source.renameTo(f);
		}

		return false;
	}

	/**
	 * 检测文件是否为图片
	 * 
	 * @param filename
	 *            检测的文件名
	 * @return true 文件,false其他文件
	 */
	public static boolean isPic(String filename) {

		String reg = "[\\s\\S]+.(png|gif|bmp|jpg|jpeg)";

		return filename.toLowerCase().matches(reg);
	}

	/**
	 * 检测文件是否为FreeMarker的模版文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isFreeMarkerTemplate(String fileName) {

		String reg = "[\\s\\S]+.(ftl)";

		return fileName.toLowerCase().matches(reg);
	}

	/**
	 * 根据文件显示类型取得存放路径
	 * 
	 * @param displayType
	 *            文件显示类型
	 * @return 文件存放路径
	 */
	public static String getPathByType(String displayType) {
		// 默认正式文件路径
		String settingPath = "'";
		MessageManager messageManager = MessageManager.getMessageManager();

		// 取得默认正式文件路径
		settingPath = messageManager
				.getSystemProperties(CommonConsts.PRO_KEY_STORAGE_REL_PATH);

		// 根据文件文件类型取得properties key
		String propertiesKey = messageManager.getSystemProperties(displayType);

		// 根据properties key取得存放路径
		String tempPath = messageManager.getSystemProperties(propertiesKey);

		// 如果设置了文件显示类型的路径，返回设置的路径
		if (StringUtil.isNotEmpty(tempPath)) {
			settingPath = settingPath + tempPath;
		}
		// 返回文件存放路径
		return settingPath;
	}

	public static String getName(String fileName) {
		Random random = new Random();
		return fileName = "" + random.nextInt(10000)
				+ System.currentTimeMillis() + getFileExt(fileName);
	}
	
	public static  String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}
}
