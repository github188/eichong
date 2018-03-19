/**
 * FileName:AuthorizedXmlReader.java
 * Author: Administrator
 * Create: 2014年7月8日
 * Last Modified: 2014年7月8日
 * Version: V1.0 
 */
package com.wanma.common;

import java.util.List;

import com.alibaba.druid.util.StringUtils;
import com.wanma.util.ObjectUtil;

/**
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月8日
 */
public class DataAuthXmlReader {

	/** 权限设置信息 */
	private static List<XMLModel> xmlModelList = null;

	/** XML标签：Mapper名称 */
	public static String XML_TAGERT_MAPPER = "mapper";
	/** XML标签：SQL ID */
	public static String XML_TAGERT_SQL_ID = "sql-id";
	/** XML标签：权限对象表 */
	public static String XML_TAGERT_AUTH_FACE_TABLE = "auth-face-table";
	/** XML标签：权限分类 */
	public static String XML_TAGERT_AUTH_TYPE = "auth-type";
	/** XML标签：是否可以访问下级部门/角色数据 */
	public static String XML_TAGERT_CHILD_ACCESSABLE = "child-accessable";
	/** XML标签：是否有where条件 */
	public static String XML_TAGERT_HAS_WHERE = "has-where";
	/** XML标签：是否有Group By处理 */
	public static String XML_TAGERT_HAS_GROUP_BY = "has-group-by";
	/** XML标签：是否有having子句 */
	public static String XML_TAGERT_HAS_HAVING = "has-having";
	/** XML标签：是否有排序处理 */
	public static String XML_TAGERT_HAS_ORDER_BY = "has-order-by";
	/** XML标签：是否有limit处理 */
	public static String XML_TAGERT_HAS_LIMIT = "has-limit";
	public static String REDIS_CACHE_CLASS_XML = "data-redis-cache-class.xml";

	public static void main(String[] args) {
		DataAuthXmlReader
				.getAuthSqlSetting("com.bluemobi.product.dao.UserMapper.getUserList");
	}

	public static List<XMLModel> getAuthSqlSetting(String sqlId) {
		if (StringUtils.isEmpty(sqlId)) {
			return null;
		}

		xmlModelList = null;
		
		// 通过设置文件取得权限设置信息
//		XMLModel xmlModel = XmlUtil
//				.parseNoAwareXML(CommonConsts.DATA_AUTH_SETTING_XML);
//
//		if (ObjectUtil.isEmpty(xmlModel) || !xmlModel.getHasChildFlg()) {
//			return null;
//		}
//
//		getAuthSqlSetByKey(sqlId, xmlModel);

		// 权限设置信息
		return xmlModelList;
	}

	@SuppressWarnings("unused")
	private static void getAuthSqlSetByKey(String sqlId, XMLModel xmlModel) {

		if (ObjectUtil.isEmpty(xmlModel) || !xmlModel.getHasChildFlg()
				|| xmlModelList != null) {
			return;
		}

		List<XMLModel> childModelList = xmlModel.getChildList();

		String fullSqlId = "";
		String mapperKeyValue = "";
		String sqlIdKeyValue = "";

		for (XMLModel childModel : childModelList) {
			if (childModel.getHasChildFlg()) {
				getAuthSqlSetByKey(sqlId, childModel);
			}
			String xmlTarget = childModel.getKey();
			if (XML_TAGERT_MAPPER.equalsIgnoreCase(xmlTarget)) {
				mapperKeyValue = childModel.getValue();
				fullSqlId = mapperKeyValue + fullSqlId;
			}
			if (XML_TAGERT_SQL_ID.equalsIgnoreCase(xmlTarget)) {
				sqlIdKeyValue = childModel.getValue();
				fullSqlId = fullSqlId + "." + sqlIdKeyValue;
			}

			if (StringUtils.equals(sqlId, fullSqlId)) {
				xmlModelList = childModelList;
				break;
			}
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

}
