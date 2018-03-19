package com.wanma.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.web.service.WebElectricPileDetaillService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.ResultResponse;
import com.wanma.web.support.utils.AddressUtil;

/**
 * 所在地区
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-11 下午04:25:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/web/point")
public class WebElectricPilePointController {
	private static Log log = LogFactory.getLog(WebElectricPileDetailController.class);

	@Autowired
	private WebElectricPileDetaillService electricPileDetaillService;

	/**
	 * 获取所在地区
	 */
	@RequestMapping("/getPoint")
	@ResponseBody
	public String getElectricPilePoint(HttpServletRequest request) {
		log.info("******************获取所在地区数据查询信息-begin************************");
		Map<String,Object> pointMap = new HashMap<String, Object>();
		try {
			    String ip = request.getHeader("x-forwarded-for"); 
			    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			        ip = request.getHeader("Proxy-Client-IP"); 
			    } 
			    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			        ip = request.getHeader("WL-Proxy-Client-IP"); 
			    } 
			    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			        ip = request.getRemoteAddr(); 
			    } 
			    AddressUtil addressUtil = new AddressUtil();
			    ip="115.236.3.66";
				String address = addressUtil.getAddresses(ip, "utf-8");
				//如果没连外网就用默认
				if("".equals(address)){
					address = "杭州市西湖区";
				}
				pointMap.put("point", address);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取所在地区数据失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		// 返回处理成功信息
		return new ResultResponse<Map<String,Object>>(pointMap).toString();
	}

}