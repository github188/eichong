package com.wanma.app.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.AppPager;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.dao.GovBusiRecordMapper;
import com.wanma.app.service.GovBusiRecordService;


/**
 * @description : APP用户信息处理控制器
 * @Author: songjf
 * @Date: 2015年03月12日
 */
@Controller
@RequestMapping("/gov/record")
public class GovBusiRecordController extends BaseController {
	private static Logger log = Logger.getLogger(GovBusiRecordController.class);
	@Autowired
	GovBusiRecordMapper mapper;
	@Autowired
	GovBusiRecordService service;
	
	@RequestMapping("/chargingrecord")
	@ResponseBody
	public String getconvertibleList(@RequestParam Map<String, Object> param,AppPager pager) {

		log.info("*********获得充电记录--begin****************");
		if (StringUtils.isEmpty(param.get("userId"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		param.put("pager", pager);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		list = service.getorder(param);
		try {
			
			
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> tmpMap = list.get(i);
				String begin = tmpMap.get("begin_charge_time").toString();
				if(!"0".equals(begin)){
					 Date begin_date =	sdf.parse(tmpMap.get("begin_charge_time").toString());
					 Date end_date =	sdf.parse(tmpMap.get("end_charge_time").toString());
				      long diff = end_date.getTime()/1000 - begin_date.getTime()/1000;//这样得到的差值是微秒级别  
				     
				      long hours = (diff/3600);  
				      long mins = (diff-(hours*3600))/60;
				      long secs =  (diff-mins*60-hours*3600);
				      String span = String.valueOf(hours)+":"+ String.valueOf(mins)+":"+ String.valueOf(secs);
				      tmpMap.put("timespan", span);
				      result.add(tmpMap);
				      
				     
				}else{
					  result.add(tmpMap);
				}
			 
			
			  
				
			
			
			}
			
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("充电记录", e);
			return new AccessErrorResult(2004, "获得充电记录失败").toString();
		}
		log.info("*********获得充电记录--end****************");

		return new AccessSuccessResult(result).toString();

	}

	
	
	@RequestMapping("/bill")
	@ResponseBody
	public String getbill(@RequestParam Map<String, Object> param,AppPager pager) {

		log.info("*********获得充电记录--begin****************");
		if (StringUtils.isEmpty(param.get("userId"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		param.put("pager", pager);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		//list = service.getorder(param);
		try {

			list = service.getbill(param);	
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("充电记录", e);
			return new AccessErrorResult(2004, "获得充电记录失败").toString();
		}
		log.info("*********获得充电记录--end****************");

		return new AccessSuccessResult(list).toString();

	}
}
