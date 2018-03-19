package com.wanma.web.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblUser;
import com.wanma.model.TblUserinfo;
import com.wanma.web.service.WebUserinfoService;
import com.wanma.web.support.utils.Escape;

/**
 * Created by Haner on 2015/3/31.
 * 前端过滤器
 */
public class FrontEndFilter implements Filter{
    private String[] checkUrl;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //记录访问来源
//		new RedisClient().addClickLog(request);
        Cookie[] cookies= request.getCookies();
    	//验证标识，false则拦截
    	Boolean hasUserCookie=false;
    	TblUser user=getCookieUser(cookies);
		ServletContext sc=request.getSession().getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		WebUserinfoService webUserinfoService=(WebUserinfoService) ac.getBean("webUserinfoServiceImpl");
		TblUser sessionUser=SessionMgr.getWebUser(request);
		if(user!=null){
			//session用户存在时，验证帐号密码是否匹配
			if(sessionUser!=null
				&&sessionUser.getUserAccount().equals(user.getUserAccount())
				&&sessionUser.getUserPassword().equals(user.getUserPassword())){
				hasUserCookie=true;
			}else{
				//验证用cookie能否登录用户
				hasUserCookie=webUserinfoService.loginByCookieUser(request,user);
			}
		}else{
			//过滤要被验证的URL
			if(!isCheck(request.getServletPath())){
	    		hasUserCookie=true;
	    	}	
		}
		
    	
        	if(!hasUserCookie){
    			SessionMgr.removeWebUser(request);
    			response.sendRedirect(request.getContextPath()+"/index.html?from="+ URLEncoder.encode(request.getRequestURL().toString()));
                return;
    		}
            
       
        filterChain.doFilter(servletRequest, servletResponse);
    }

    
    @Override
    public void destroy() {

    }

    /**
     * 初始化获取不被过滤的url
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String check = filterConfig.getInitParameter("check");
        check = check.trim();
        check = check.replaceAll("\n", "");
        check = check.replaceAll("\r", "");
        check = check.replaceAll("\t", "");
        checkUrl = check.split(",");
    }

    private boolean isCheck(String servletPath) {
        boolean isCheck = false;
        for (String url : checkUrl) {
            if (servletPath.contains(url)) {
                isCheck = true;
                break;
            }
        }
        return isCheck;
    }
    
    private TblUser getCookieUser(Cookie[] cookies){
    	TblUser user=null;
    	if(cookies!=null){
        	for(Cookie cookie:cookies){
        		if(cookie.getName().equals("com.wm.user")){
        			if(!cookie.getValue().equals("null")){
            			JSONObject obj=(JSONObject)JSON.parse(Escape.unescape(cookie.getValue()));
            			user=new TblUser();
            			user.setNormName((String)obj.get("normName"));
            			user.setUserAccount((String)obj.get("userAccount"));
            			user.setUserPassword((String)obj.get("userPassword"));
        			}
        			break;
        		}
        	}
    	}
    	
    	return user;
    }
    
}
