package com.wanma.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class GlobalInterceptors implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception { 
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		//log.info("解析token开始...");
		String url = request.getRequestURI();
		String ext = request.getParameter("ext");
		if("t".equals(ext)){
			return true;
		}
		if(url.indexOf("/app/common") > 0 || url.indexOf("/app/normal") > 0
				|| url.indexOf("/app/card") > 0 ){
			return true;
		}else{
			boolean boo = WebFilter.checkTK(request.getParameter("t"));//true;
			if(!boo){
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().write("{\"status\":5000,\"msg\":\"校验失败或请求已过期\"}");
				return false;
			}else{
				return true;
			}
		}
	}


}
