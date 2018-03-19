package com.wanma.app.util.wxhandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

public class InitWXTokenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(InitWXTokenServlet.class);
	
	public void init() throws ServletException {   
		log.info("初始化微信token");
		new Thread(new WXTokenThread()).start();    
    }   
}
