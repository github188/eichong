package com.bluemobi.product.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.bluemobi.product.common.MessageManager;

public class WebFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		req.setAttribute("webroot", request.getContextPath());
		ServletContext context=request.getSession().getServletContext();
		if(context.getAttribute("apiRoot")==null){
			String apiRoot=MessageManager.getMessageManager().getSystemProperties("apiRoot");
			context.setAttribute("apiRoot", apiRoot);
		}
		//System.out.println(context.getAttribute("apiRoot").toString());
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
