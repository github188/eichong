package com.wanma.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.HttpUtil;
import com.wanma.app.service.AppCityService;
import com.wanma.model.TblCity;

/**
 * 获取城市列表
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-11 下午04:25:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/app/city")
public class CityController {
	private static Log log = LogFactory.getLog(CityController.class);

	@Autowired
	private AppCityService appCityService;

	/**
	 * 查询万马所有城市列表
	 */
	@RequestMapping("/getCityList")
	@ResponseBody
	public String getCityList(HttpServletRequest request)
			throws AppRequestErrorException {
		List<TblCity> cityList = new ArrayList<TblCity> ();
		try {
			cityList = appCityService.getCityList();
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取城市列表失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult(cityList).toString();
	}
	
	@RequestMapping("/initCityLatLng")
	@ResponseBody
	public void initCityLatLng(HttpServletRequest request)
			throws AppRequestErrorException {
		try {
		List<Map<String,String>> List = appCityService.getCityOrProvinceInfo();
		
			for(Map<String,String> item:List){
				
				String url = "http://restapi.amap.com/v3/geocode/geo?key=389880a06e3f893ea46036f030c94700&address="+item.get("name");
				JSONArray geocodes = JSON.parseArray(JSON.parseObject(HttpUtil.getGeocodeMapStr(url)).get("geocodes").toString());
				if(geocodes.size() > 0)
				{ 
					String latlng=((JSONObject)geocodes.get(0)).get("location").toString();
					
					String lat=latlng.split(",")[0];
					String lng=latlng.split(",")[1];
					
					Map<String,String> map = new HashMap<String,String>();
					
					map.put("id",item.get("id"));
					map.put("lat",lat);
					map.put("lng",lng);
					
					appCityService.updateLatLng(map);
					
					System.out.println(item.get("name")+":"+latlng+"......");
				}
					
				
			}
		
	
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			
		
		}
		// 返回处理成功信息
	
	}

}