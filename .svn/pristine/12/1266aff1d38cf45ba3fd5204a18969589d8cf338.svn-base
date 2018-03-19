package com.wanma.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.service.impl.AppRescueServiceImpl;

/**
 * 救援信息
 * @author hFei
 *
 */
@Controller
@RequestMapping("/app/rescue")
public class AppRescueController {
	private static Log log = LogFactory.getLog(AppRescueController.class);
	
	@RequestMapping("/list")
	@ResponseBody
	public String getRescueList(){
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		try{
			list = appRescueService.getCarGarageList();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取产品信息错误", e);
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}
		return new AccessSuccessResult(list).toString();
	}
	
	@Autowired
	AppRescueServiceImpl appRescueService;
}
