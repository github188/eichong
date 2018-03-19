package com.wanma.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wanma.support.common.MessageManager;
import com.wanma.support.utils.MD5Util;

public class MethodFilter implements Filter {
	private Logger log = Logger.getLogger(MethodFilter.class);

	@Override
	public void destroy() {

	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		MessageManager manager = MessageManager.getMessageManager();
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// 验证method参数
		String method = request.getParameter("method");
		if (StringUtils.isBlank(method)) {
			printErrorMessage(response, "param method error");
			return;
		}
		String time = request.getParameter("time");
		String org = request.getParameter("org");
		String token = request.getParameter("token");
		log.info("第三方接口请求，来源：" + request.getParameter("org") + ",请求方法："
				+ method+",参数:{time:"+time+",org:"+org+",token:"+token+"}");
		String methodUrl = manager.getInterfaceProperties(method);
		if(StringUtils.isBlank(methodUrl)){
			printErrorMessage(response, "method eeror");
			return;
		}
		request.getRequestDispatcher(methodUrl).forward(request, response);
	}

	private void printErrorMessage(ServletResponse resp, String message) {
		log.info(message);
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
		
	}

	public static void main(String[] args) {
		long time=System.currentTimeMillis()/1000;
		System.out.println(time);
		String s=(""+time).substring(5);
		System.out.println(MD5Util.Md5("1003902DE0C8E924F53B3D1F521B3C36F638"+s));
	}
}
