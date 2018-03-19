/**
 * FileName:InitDeployUrlServelt.java
 * Author: Administrator
 * Create: 2014年8月10日
 * Last Modified: 2014年8月10日
 * Version: V1.0 
 */
package com.base.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.base.common.SpringContextHolder;
import com.base.common.WanmaConstants;
import com.wanma.service.impl.CmsConfigServiceImpl;
import com.wanma.service.impl.CmsConfigcontentServiceImpl;
import com.wanma.service.impl.CmsRateInfoServiceImpl;

/**
 * 初始化发布环境信息
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月10日
 */
public class InitDeployServelt extends HttpServlet {
	private static final Logger logger = Logger
			.getLogger(InitDeployServelt.class);

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = -1447273313093174751L;

	/**
	 * 初始化发布环境信息
	 */
	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();

		ApplicationContext context = SpringContextHolder.getSpringContext();
		// String[] arr=context.getBeanDefinitionNames();
		// for(String s:arr){
		// System.out.println(s);
		// }
		/*
		 * CmsRateInfoServiceImpl rateInfoService = (CmsRateInfoServiceImpl)
		 * context .getBean("cmsRateInfoServiceImpl");
		 */
		CmsConfigServiceImpl configService = (CmsConfigServiceImpl) context
				.getBean("cmsConfigServiceImpl");
		CmsConfigcontentServiceImpl configContentServiceImpl = (CmsConfigcontentServiceImpl) context
				.getBean("cmsConfigcontentServiceImpl");
		List<Map<String, Object>> proList = configService
				.searchProvinceList(null);
		List<HashMap<String, Object>> cityList = configService
				.searchCityList(null);
		List<HashMap<String, Object>> areaList = configService
				.searchAreaList(null);
		Map<String, Object> cityMap = new HashMap<String, Object>();
		Map<String, Object> areaMap = new HashMap<String, Object>();
		List<HashMap<String, Object>> childCityList = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> childAreaList = new ArrayList<HashMap<String, Object>>();
		String provinceId = "", cityId = "";
		for (Map<String, Object> pmap : proList) {
			provinceId = pmap.get("provinceId").toString();
			childCityList = new ArrayList<HashMap<String, Object>>();
			for (HashMap<String, Object> cmap : cityList) {
				cityId = cmap.get("cityId").toString();
				// 把该省下的城市加入列表
				if (provinceId.equals(cmap.get("provinceId").toString())) {
					childCityList.add(cmap);
				}
				childAreaList = new ArrayList<HashMap<String, Object>>();
				for (HashMap<String, Object> amap : areaList) {
					// 把该城市下的区加入列表
					if (amap.get("cityId").toString().equals(cityId)) {
						childAreaList.add(amap);
					}
				}
				// 把该城市下的区列表加到市区关系map中
				areaMap.put(cityId, childAreaList);
			}
			// 把该省下的城市列表加到省市map中
			cityMap.put(provinceId, childCityList);
		}

		List<Map<String, Object>> list = configContentServiceImpl
				.getAllConfigContentList();
		Map<String, List<Map<String, Object>>> dictMap = new HashMap<String, List<Map<String, Object>>>();
		// key值定义,1:电桩类型 (壁挂式);2:电桩使用类型(电动车);3:充电方式;4:功率;5:接口;6:枪头状态;7:搜索范围
		// ;8:跟6重复;: ;: ;: ;
		// 9: ;10: ;11:制造厂商 ;12:电桩状态 ;13:绑定 ;14:预约支持 ;15:付款方式 ;29:故障类型 ;
		Integer key = (Integer) list.get(0).get("t");
		List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
		int paramKey = 0;
		for (Map<String, Object> map : list) {
			paramKey = (Integer) map.get("t");
			if (key != paramKey) {
				dictMap.put(key + "", tempList);
				key = paramKey;
				tempList = new ArrayList<Map<String, Object>>();

			}
			tempList.add(map);
		}
		// 最后的属性循环中未设置，此处补上
		dictMap.put(key + "", tempList);
		// 功率需要排序下~~
		list = dictMap.get("4");
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				String s1 = (String) o1.get("v");
				String s2 = (String) o2.get("v");
				if (Float.parseFloat(s1.substring(0, s1.length() - 2)) > Float
						.parseFloat(s2.substring(0, s2.length() - 2))) {
					return 1;
				}
				return -1;
			}
		});
		dictMap.put("4", list);
		WanmaConstants.initDictionary(dictMap);
		WanmaConstants.provinceList = proList;
		WanmaConstants.cityMap = cityMap;
		WanmaConstants.areaMap = areaMap;
	}

	/**
	 * 获取当前操作系统名称. return 操作系统名称 例如:windows xp,linux 等.
	 */
	public static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}
}
