package com.wanma.controller.itf;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.model.TblDynamic;
import com.wanma.service.TblDynamicService;
import com.wanma.support.common.RedisService;
import com.wanma.support.common.ResultResponse;
import com.wanma.support.utils.DateUtil;


/**
 * @Description: 动态管理控制层
 * @author wbc
 * @createTime：2015-8-24 16:25:05
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/itf/news")
public class TblDynamicController {
		@Autowired
		private TblDynamicService dynamicService;
		@Autowired
		private RedisService redisService;
		

		/**
		 * @Title: edit
		 * @Description: 跳转至动态详情页面
		 * @param params
		 * @return
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@RequestMapping(value = "/{id}", method = RequestMethod.GET)
		@ResponseBody
		public String getDynamicDetail(@PathVariable Integer id,HttpServletRequest request) {
			/*String userId=request.getParameter("userId");
			String type=request.getParameter("type");
			String ip=request.getRemoteHost();*/
			TblDynamic dynamic = dynamicService.get(id);
			//type--2：web 3：android 4：ios
			/*setRedisDynamic(userId, type, id, ip);*/
			return new ResultResponse(dynamic).toString();
		}
		
	    
		/**
		 * 存储格式count-web:android:ios,detail-type:ip:time
		 * type--2：web 3：android 4：ios
		 */
	 /*   private void setRedisDynamic(String userId,String type,int dynamicId,String ip){
	    	String userRecordStr="";
	    	//web端来源，根据IP来记录统计数量
	    	if("2".equals(type)){
	    		userRecordStr=redisService.strGet("html:dynamic:"+dynamicId+":"+ip);
	    	}else{//android和ios端根据用户ID统计数量
	    		userRecordStr=redisService.strGet("html:dynamic:"+dynamicId+":"+userId+":"+type);
	    	}
	    	//同一用户多次看同一个消息，记录数为1
	    	if(StringUtils.isBlank(userRecordStr)){
	    		String dynamicStr=redisService.strGet("html:dynamic:count:"+dynamicId);
		    	dynamicStr=StringUtils.isNotBlank(dynamicStr)?dynamicStr:"0:0:0";
		    	int arrIndex=new Integer(type)-2;
		    	String[] arr=dynamicStr.split(":");
		    	int countNum=new Integer(arr[arrIndex]);
	    		arr[arrIndex]=countNum+1+"";
	    		String temp="";
	    		for(String s:arr){
	    			temp+=s+":";
	    		}
	    		redisService.strSet("html:dynamic:count:"+dynamicId,StringUtils.removeEnd(temp, ":"));
	    		String currentDate = DateUtil.currentYourDate("yyyyMMddhhmmss");
	    		//web端来源，根据IP来记录统计数量
		    	if("2".equals(type)){
		    		redisService.strSet("html:dynamic:"+dynamicId+":"+ip,type+":"+userId+":"+currentDate);
		    	}else{//android和ios端根据用户ID和类型统计数量
		    		redisService.strSet("html:dynamic:"+dynamicId+":"+userId+":"+type,ip+":"+currentDate);
		    	}
	    		
	    	}
	    	
	    }*/

}
