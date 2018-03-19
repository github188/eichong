/**
 * FileName:CityUtil.java
 * Author: Administrator
 * Create: 2014年8月14日
 * Last Modified: 2014年8月14日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.bluemobi.product.model.City;

/**
 * 城市分组工具
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月14日
 */
public class CityUtil {

	// 分组列表
	public static final String[] keyWordList = { "HOT", "A", "B", "C", "D",
			"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
			"R", "S", "T", "U", "V", "X", "Y", "Z" };

	/**
	 * 城市分组处理
	 * 
	 * @param cityList
	 * @return 分组后的城市列表
	 */
	public static List<CityGroupObject> goupCity(List<City> cityList) {
		List<CityGroupObject> groupList = new ArrayList<CityGroupObject>();

		//
		// 按字母A-Z追加分组
		//
		for (int i = 0; i < keyWordList.length; i++) {
			List<City> listOne = new ArrayList<City>();
			String keyWord = keyWordList[i];
			CityGroupObject groupObject = new CityGroupObject();
			groupObject.setKeyWord(keyWord);
			groupObject.setCityList(listOne);
			groupList.add(groupObject);

		}

		if (cityList == null || cityList.size() == 0) {
			return groupList;
		}

		//
		// 为分组匹配数据
		//
		for (City city : cityList) {
			String keyWord = city.getKeyWord();
			for (int i = 0; i < keyWordList.length; i++) {
				if (StringUtils.equals(keyWordList[i], keyWord)) {
					groupList.get(i).getCityList().add(city);
					break;
				}
			}
		}

		// 返回分组后的城市列表
		return groupList;

	}
}
