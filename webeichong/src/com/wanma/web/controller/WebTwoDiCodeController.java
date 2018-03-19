package com.wanma.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web/twoDiCode")
public class WebTwoDiCodeController {
	private static Log log = LogFactory.getLog(WebTwoDiCodeController.class);
	
	@RequestMapping("/getPileTwoDiCode")
	@ResponseBody
	public String getPileTwoDiCode(HttpServletRequest request,HttpServletResponse response) {

	    String s = "";
		return s;	
		
	}
}
