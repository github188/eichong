package com.wanma.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.controller.BaseController;

/**
 * @Description: 跳转服务与支持子菜单业务纵览
 * @author shenj
 * @createTime：2015-3-17 下午03:59:20
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/serverBusiness")
public class WebServerBusiness2Controller extends BaseController {

	private static Log log = LogFactory.getLog(WebServerBusiness2Controller.class);

	@RequestMapping("/ff")
	@ResponseBody
	public void fff(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException { 
		response.setContentType("text/html; charset=gb2312"); 
		response.sendRedirect(request.getContextPath()+"/view/frontend/web2/serverBusiness.jsp"); 
	}

}
