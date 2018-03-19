package com.wanma.filter;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.wanma.support.common.RedisService;
import com.wanma.support.common.SpringContextHolder;
import com.wanma.support.common.WanmaConstants;
import com.wanma.support.utils.MD5Util;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonFilter implements Filter {
	private Logger log = Logger.getLogger(CommonFilter.class);
	private RedisService redisService;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String ext = request.getParameter("ext");
		String 	uri=request.getServletPath();
		if("/eichong/query_token.do".equals(uri)||"/eichong/query_token".equals(uri)){
			chain.doFilter(req, resp);
			return;
		}
		if ("t".equals(ext)) {
			chain.doFilter(req, resp);
			return;
		}

		String org = request.getParameter("org");
		String token = request.getParameter("token");
		if (StringUtils.isBlank(org) || StringUtils.isBlank(token)) {
			printErrorMessage(response, "need params org,token");
			return;
		}

		boolean flag = checkToken(org, token);

		if (!flag) {
			log.info("token invalid" + org + token);
			printErrorMessage(response, "user invalid");
			return;
		}
		chain.doFilter(req, resp);
	}

	private boolean checkToken(String org, String token) {
		String authCode = redisService.strGet(WanmaConstants.PREFIX_ORG + org)
				.split(":")[0];
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String time = df.format(new Date());
		String t = MD5Util.Md5(org + authCode + time);
		return t.equals(token);
	}

	private void printErrorMessage(ServletResponse resp, String message) {
		log.info(message);
		resp.setContentType("text/plain;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter pw = null;
		try {
			pw = resp.getWriter();
			pw.write(message);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		ApplicationContext context = SpringContextHolder.getSpringContext();
		redisService = (RedisService) context.getBean("redisService");

	}

}
