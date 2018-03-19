package com.wanma.ims.filter;

import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.session.SessionRequestWrapper;
import com.wanma.ims.session.service.SessionService;
import net.sf.json.JSONObject;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class LoginFilter implements Filter {
    protected SessionService sessionService;

    @Override
    public void init(FilterConfig config) throws ServletException {
        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        sessionService = (SessionService) springContext.getBean("sessionService");
        if (sessionService == null) {
            throw new RuntimeException("sessionService should not be null");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //清空缓存
        disableResponseCache(response);

        SessionRequestWrapper sessionRequestWrapper = new SessionRequestWrapper(request);
        HttpSession session = sessionService.getSession(request, response);
        if (session != null) {
            sessionRequestWrapper.setSession(session);
            chain.doFilter(sessionRequestWrapper, response);
        } else {
            // 登录失败或会话超时
            if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With")) || request.getParameter("ajax") != null) {
                // 设置会话超时
                PrintWriter out;
                response.setContentType("text/json; charset=UTF-8");
                JsonResult result = new JsonResult();
                result.setSuccess(false);
                result.setStatus(ResultCodeConstants.SESSION_OUT);
                result.setMsg("会话超时");
                JSONObject jsonObj = JSONObject.fromObject(result);
                String json = jsonObj.toString();
                out = response.getWriter();
                out.print(json);
                out.flush();
                out.close();
            } else {
                response.sendRedirect(request.getContextPath() + "tologin.do");
            }
        }
    }

    private void disableResponseCache(HttpServletResponse response) {
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        // 浏览器和缓存服务器都不应该缓存页面信息
        response.addHeader("Cache-Control", "no-cache");
        // 请求和响应的信息都不应该被存储在对方的磁盘系统中；
        response.addHeader("Cache-Control", "no-store");
        // 于客户机的每次请求，代理服务器必须想服务器验证缓存是否过时；
        response.addHeader("Cache-Control", "must-revalidate");
    }

    @Override
    public void destroy() {
    }
}
