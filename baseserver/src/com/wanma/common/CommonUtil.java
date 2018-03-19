package com.wanma.common;

import java.util.Map;


import com.wanma.dubbox.model.common.BasicModel;

/**
 * XML文件处理工具类
 * 
 * @author yangweir
 * @date 2014/07/15
 */
public class CommonUtil {
	/**
	 * 动态设置数据库查询字段
	 * 
	 * @param CLOUMNS_MAP
	 * @param model
	 */
	public static String setReturnClumns(BasicModel model) {
		String key = model.getClass().getName();
		Map<String, String> rtMap = Global.CLOUMNS_MAP.get(key);
		String tColumns = model.getRtColumns();
		if (tColumns != null) {
			String[] tColumnsArray = tColumns.split(",");
			String newTColumns = "";
			for(String str:tColumnsArray)
				newTColumns += rtMap.get(str)+",";
			return newTColumns.substring(0,newTColumns.length()-1);
		}else{
			return "*";
		}
	}
}
