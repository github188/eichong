package com.wanma.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Encoder;

import com.bluemobi.product.utils.JsonObject;
import com.wanma.model.TblElectricpile;
import com.wanma.web.support.utils.ApiUtil;
import com.wanma.web.support.utils.HttpsUtil;

/**
 * 预约管理控制器
 * 
 * @author xiay
 * 
 */
@Controller
@RequestMapping("/web/common")
public class CommonController {
	
	/***
	 * token
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getToken")
	@ResponseBody
	public String getToken() {
		String token="";
		try {
			token=ApiUtil.getToken();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JsonObject(token).toString();
	}
	
	/***
	 * sendRequest
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sendRequest")
	@ResponseBody
	public String sendRequest(Model model,HttpServletRequest request,@RequestParam Map<String, String> params) {
		String token="";
		String result="";
		try {
			token=ApiUtil.getToken();
			params.put("t", token);
			String sessionId=request.getSession().getId();
			params.put("did", ApiUtil.encode(sessionId));
			result=ApiUtil.sendRequest(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
