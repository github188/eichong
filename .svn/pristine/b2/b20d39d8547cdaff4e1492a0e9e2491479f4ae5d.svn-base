package com.wanma.service.impl;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.wanma.controller.CmsChartController;
import com.wanma.controller.CmsConsultController;
import com.wanma.web.support.utils.JsonUtil;

public abstract class CmsEchartsV2ServiceImpl {
	protected static Logger log = Logger.getLogger(CmsConsultController.class);
	protected String path = "";

	public CmsEchartsV2ServiceImpl() {
		try {
			path = CmsEchartPeopleCountServiceImpl.class.getClassLoader()
					.getResource("").toURI().getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public String getJsonData(Map<String, Object> params) {
		log.info("******************图表JSON数据获取-begin************************");
		JSONObject obj = null;
		try {
			// 图表结构在前端生成，只需后端传输数据时,onlyData设置为1
			if ("1".equals(params.get("onlyData"))) {
				obj = new JSONObject();
			} else {
				String path = CmsChartController.class.getClassLoader()
						.getResource("").toURI().getPath();
				// 报表样式JSON格式
				obj = JsonUtil.getJsonObject(path + "/json/"
						+ params.get("type") + ".json");
			}
			// 根据数据源设置属性值
			setData(obj, params);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		log.info("******************图表JSON数据获取-end************************");
		// 返回处理结果信息
		return obj.toString();
	}

	/**
	 * 去重复，排序
	 * 
	 * @param list
	 * @param initLength
	 * @return
	 */
	protected Object[] makeGroup(List<Object> list, int initLength) {
		Set<Object> set = new HashSet<Object>();
		set.addAll(list);
		Object[] group = null;
		if (set.size() < initLength) {
			group = new Object[] { 300000, 300000, 300000, 300000, 300000,
					300000, 300000, 300000, 300000, 300000, 300000, 300000 };
			System.arraycopy(set.toArray(), 0, group, 0, set.size());
		} else {
			group = set.toArray();
		}
		Arrays.sort(group);
		for (int i = 0; i < group.length; i++) {
			if (group[i].equals(300000)) {
				group[i] = "";
			}
		}
		return group;
	}

	/**
	 * 去重复，排序
	 * 
	 * @param list
	 * @param initLength
	 * @return
	 */
	protected Object[] makeBeginMonthGroup(List<Object> list) {
		Set<Object> set = new HashSet<Object>();
		set.addAll(list);
		Object[] group = null;
		if (set.size() < 6) {
			group = new Object[] { 300000, 300000, 300000, 300000, 300000,
					300000 };
			System.arraycopy(set.toArray(), 0, group, 0, set.size());
		} else {
			Object[] groupTemp = set.toArray(); 
			Arrays.sort(groupTemp);
			group = new Object[6];
			System.arraycopy(groupTemp, groupTemp.length-6, group, 0, 6);
		}
		Arrays.sort(group);
		for (int i = 0; i < group.length; i++) {
			if (group[i].equals(300000)) {
				group[i] = "";
			}
		}
		return group;
	}
	
	protected Object[] makeBeginDataList(List<Map<String, Object>> dataList){
		if(dataList.size()<=6)
			return dataList.toArray();
		Object[] dataArrayTemp = dataList.toArray();
		Object[] dataArray = new Object[6];
		System.arraycopy(dataArrayTemp, dataArrayTemp.length-6, dataArray, 0, 6);
		return dataArray;
	}

	/**
	 * 日期年月分隔开
	 */
	protected String remakeDate(Object[] dataArray) {
		String year = "";
		if (dataArray != null && dataArray.length > 0) {
			String strVal = dataArray[0] + "";
			year = strVal.substring(0, 4);
			int strSize = strVal.length();
			int arraySize = dataArray.length;
			for (int i = 0; i < arraySize; i++) {
				if ((dataArray[i] + "").length() == strSize)
					dataArray[i] = (dataArray[i] + "").substring(4, strSize);
			}
		}
		return year;
	}

	protected abstract void setData(JSONObject obj,
			Map<String, Object> params);
}
