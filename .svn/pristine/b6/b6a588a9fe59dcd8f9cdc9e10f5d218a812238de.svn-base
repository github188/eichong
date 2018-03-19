package com.bluemobi.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.utils.HttpUtil;
import com.wanma.common.WanmaConstants;

public class test {

	public static void main(String[] args) throws Exception {
		
	
			String url = "http://restapi.amap.com/v3/geocode/geo?key=389880a06e3f893ea46036f030c94700&address=高雄市";
			JSONArray geocodes = JSON.parseArray(JSON.parseObject(HttpUtil.getGeocodeMapStr(url)).get("geocodes").toString());
			if(geocodes.size() > 0)
				System.out.println(".....:"+((JSONObject)geocodes.get(0)).get("location").toString());
	


	}
	public static List<Map<String, Object>> getCtyPoints() throws Exception {
		
		List<Map<String, String>> points = new ArrayList<Map<String,String>>();
		
		Map<String, String> ok =new HashMap<String,String>();
		
		ok.put("pCount", "80");
		ok.put("cd", "610100");
		points.add(ok);
		
		ok.put("pCount", "17");
		ok.put("cd", "610400");
		points.add(ok);
		

		
		ok.put("pCount", "7");
		ok.put("cd", "610500");
		points.add(ok);
		
		List<Map<String, Object>> locationCountList = new ArrayList<Map<String,Object>>();
		Map<String, Object> locationCountMap = null;
	//	Map<String, String> jwdMap = WanmaConstants.jwdMap;
		for(Map<String, String> point:points){
			String cityCode = point.get("cd");
			if(cityCode != null && !"".equals(cityCode)){
				locationCountMap = new HashMap<String, Object>();
				locationCountMap.put("location", cityCode);
				locationCountMap.put("count", point.get("pCount"));
				locationCountList.add(locationCountMap);
			}
		}
		return locationCountList;
	}

}
