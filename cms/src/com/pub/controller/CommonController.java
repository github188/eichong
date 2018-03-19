package com.pub.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.common.SessionMgr;
import com.base.util.ValidCode;

@Controller
public class CommonController {
	/**
	 * 图片验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/common/getValidCode")
	public void getValiCode(HttpServletRequest request,
			HttpServletResponse response) {
		int width = 0, height = 0;
		try {
			width = Integer.parseInt(request.getParameter("width"));
			height = Integer.parseInt(request.getParameter("height"));
		} catch (Exception e) {
		}
		ValidCode vCode = new ValidCode(width, height);
		SessionMgr.addWebCode(request, vCode.getCode());
		try {
			vCode.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
