package com.wanma.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wanma.dao.CmsStatisticMapper;
import com.wanma.model.echarts.EchartsParamModel;

/**
 * 用户充值情况图表service
 * 
 * @author lhy
 *
 */
@Service
public class CmsEchartPeopleChargeServiceImpl extends CmsEchartsServiceImpl {
	@Autowired
	private CmsStatisticMapper cmsStatisticMapper;

	@Override
	public void setData(JSONObject obj, EchartsParamModel paramsModel) {
		List<Map<String, Object>> beanList = cmsStatisticMapper
				.queryPeopleChargeList(paramsModel);
		if (beanList != null && beanList.size() > 0) {
			int initMonthLength = 12;
			List<Object> monthList = new ArrayList<Object>();
			List<Object> typeList = new ArrayList<Object>();
			Map<String, String> dataMap = new HashMap<String, String>();
			for (Map<String, Object> beanMap : beanList) {
				monthList.add(beanMap.get("month"));
				typeList.add(beanMap.get("puHiChargeType"));
				dataMap.put(
						beanMap.get("month") + ""
								+ beanMap.get("puHiChargeType"),
						beanMap.get("count") + "," + beanMap.get("amount"));

			}
			// X轴月份序列
			Object[] monthGroup = makeBeginMonthGroup(monthList);
			Object[] monthGroupInner = makeGroup(monthList, initMonthLength);
			// 充值种类去重
			Set<Object> typeSet = new HashSet<Object>();
			typeSet.addAll(typeList);
			Object[] typeGroup = typeSet.toArray();
			// Y轴充值数据
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> dataListTemp = new ArrayList<Map<String, Object>>();
			for (Object type : typeGroup) {
				makeData((String) type, monthGroup, dataMap, dataListTemp);
				makeData((String) type, monthGroupInner, dataMap, dataList);
			}
			// 分隔年月
			// obj.put("year", remakeDate(monthGroup));
			Map<String, Object> monthMap = new HashMap<String, Object>();
			monthMap.put("type", "category");
			monthMap.put("data", monthGroup);
			obj.put("xAxis", monthMap);
			obj.put("series", dataListTemp);
			obj.put("innerMonthGroup",monthGroupInner);
			obj.put("tempDataList",dataList);
		} else {
			obj.put("isEmpty", "Y");
		}
	}

	/**
	 * 组装消费数据，Y轴左轴为充值次数，右轴为充值金额
	 * 
	 * @param list
	 * @param initLength
	 * @return
	 */
	private void makeData(String type, Object[] monthGroup,
			Map<String, String> dataMap, List<Map<String, Object>> dataList) {
		double[] dataGroupCount = new double[monthGroup.length];
		double[] dataGroupAmount = new double[monthGroup.length];
		for (int i = 0; i < monthGroup.length; i++) {
			String dataString = dataMap.get(monthGroup[i] + type);
			if (dataString != null) {
				dataGroupCount[i] = Double.valueOf(dataString.split(",")[0]);
				dataGroupAmount[i] = Double.valueOf(dataString.split(",")[1]);
			} else {
				dataGroupCount[i] = 0;
				dataGroupAmount[i] = 0;
			}
		}
		Map<String, Object> dataMapEachYleft = new HashMap<String, Object>();
		Map<String, Object> dataMapEachYRight = new HashMap<String, Object>();
		dataMapEachYleft.put("name", type + "次数");
		dataMapEachYleft.put("type", "bar");
		dataMapEachYleft.put("data", dataGroupCount);
		dataMapEachYRight.put("name", type + "金额");
		dataMapEachYRight.put("type", "line");
		dataMapEachYRight.put("data", dataGroupAmount);
		dataMapEachYRight.put("yAxisIndex", 1);
		dataList.add(dataMapEachYleft);
		dataList.add(dataMapEachYRight);
	}
}
