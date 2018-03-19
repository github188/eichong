package com.bluemobi.product.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bluemobi.product.model.common.AccessToken;
import com.bluemobi.product.security.Authorization;

public class AccessFilter implements Filter {
	//
	private static List<String> secure_url_list;

	private static String unauthenticated;

	@Override
	public void destroy() {
		secure_url_list = null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// 解析请求URI
		String uri = request.getRequestURI().replace(request.getContextPath(),
				"");
		// 判断请求URI是否在需要认证的列表
		if (secure_url_list.contains(uri)) {
			String remoteIp = request.getRemoteAddr();
			// 验证ACCESS TOKEN
			String token = request.getParameter("token");
			// TODO 主角（需要根据principal名字来修改）
			// String principal = (String) request.getSession().getAttribute(
			// WebConst.SESS_PRINCIPAL);
			// TODO 角色（需要根据principal的角色来修改）
			// String role = (String) request.getSession().getAttribute(
			// WebConst.SESS_PRINCIPAL_ROLE);

			// Authorization auth = new Authorization(request, principal, role);
			Authorization auth = new Authorization(request, remoteIp);
			// 获取Access Token信息，
			AccessToken access_token = (AccessToken) auth
					.authAccessToken(token);
			// 判断返回的令牌，null为非法
			if (null == access_token) {
				// 跳转到为认证页面
				request.getRequestDispatcher(unauthenticated).forward(request,
						response);
				return;
			}
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig conf) throws ServletException {
		// 获取配置URI列表
		String[] uris = conf.getInitParameter("authentication").split("\n");
		// 初始化URI列表
		secure_url_list = new ArrayList<String>(uris.length);
		// 填充URI集合
		for (String uri : uris) {
			// 配置URI为空，不作处理
			if (null == uri)
				continue;
			if ("".equals(uri.trim()))
				continue;
			// 把需要验证ACCESS TOKEN的URI加入列表
			secure_url_list.add(uri.trim());
		}
		// 未认证页面
		unauthenticated = conf.getInitParameter("unauthenticated");
	}
}
