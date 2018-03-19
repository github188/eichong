/**
 * FileName:AuthorizedXmlReader.java
 * Author: Administrator
 * Create: 2014年7月8日
 * Last Modified: 2014年7月8日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.model.common.XMLModel;

/**
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月8日
 */
public class JsonPublishXmlReader {
	/** 发送标识 */
	public static String publish_FLG_YES = "1";

	/** XML标签：类名 */
	public static String XML_TAGERT_CLASS_NAME = "class-name";

	/** XML标签：是否有limit处理 */
	private static List<String> publishList = null;

	private static boolean childMatch = false;

	public static void main(String[] args) {
		List<String> pubList = JsonPublishXmlReader
				.getPublishList("com.bluemobi.product.model.AppApiModel");

		for (String sss : pubList) {
			System.out.println(sss);
		}
	}

	public static List<String> getPublishList(String clasName) {
		if (StringUtil.isEmpty(clasName)) {
			return null;
		}

		childMatch = false;
		publishList = null;

		// 通过设置文件取得权限设置信息
		XMLModel xmlModel = XmlUtil
				.parseNoAwareXML(CommonConsts.JSON_PUBLISH_SETTING_XML);

		if (ObjectUtil.isEmpty(xmlModel) || !xmlModel.getHasChildFlg()) {
			return null;
		}

		getAuthSqlSetByKey(clasName, xmlModel);

		// 权限设置信息
		return publishList;
	}

	private static void getAuthSqlSetByKey(String className, XMLModel xmlModel) {

		if (ObjectUtil.isEmpty(xmlModel) || !xmlModel.getHasChildFlg()
				|| (publishList != null && publishList.size() > 0)) {
			return;
		}

		List<XMLModel> childModelList = xmlModel.getChildList();

		String xmlValue = "";
		if (childMatch) {
			return;
		}
		publishList = new ArrayList<String>();

		for (XMLModel childModel : childModelList) {
			if (childModel.getHasChildFlg()) {
				if (!childMatch) {
					getAuthSqlSetByKey(className, childModel);
				}
			} else {

				String xmlTarget = childModel.getKey();
				xmlValue = childModel.getValue();
				if (XML_TAGERT_CLASS_NAME.equalsIgnoreCase(xmlTarget)
						&& StringUtils.equalsIgnoreCase(className, xmlValue)) {
					childMatch = true;
				}
				if (publish_FLG_YES.equalsIgnoreCase(xmlValue)) {
					if (publishList == null) {
						publishList = new ArrayList<String>();
					}
					publishList.add(xmlTarget);
				}
			}

		}

		if (!childMatch) {
			publishList = null;
		}
	}

	public static String getValueFromList(List<XMLModel> modelList, String key) {

		String tagertValue = "";

		for (XMLModel xmlModel : modelList) {
			String tagertKey = xmlModel.getKey();

			if (StringUtils.equals(key, tagertKey)) {
				tagertValue = xmlModel.getValue();
				break;
			}
		}

		return tagertValue;
	}

	/**
	 * @return the publishList
	 */
	public static List<String> getPublishList() {
		return publishList;
	}

	/**
	 * @param publishList
	 *            the publishList to set
	 */
	public static void setPublishList(List<String> publishList) {
		JsonPublishXmlReader.publishList = publishList;
	}

}
