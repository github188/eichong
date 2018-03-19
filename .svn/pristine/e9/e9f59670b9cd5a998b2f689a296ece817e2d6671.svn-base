/**
 * FileName:InitDeployUrlServelt.java
 * Author: Administrator
 * Create: 2014年8月10日
 * Last Modified: 2014年8月10日
 * Version: V1.0 
 */
package com.wanma.controller.itf;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;
import com.epcentre.server.GateServer;
import com.netCore.conf.CoreConfig;
import com.wanma.hbase.RealtimeUtil;

/**
 * 初始化发布环境信息
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月10日
 */
public class InitDeployUrlServelt extends HttpServlet {
	private static final Logger logger = Logger
			.getLogger(InitDeployUrlServelt.class);

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = -1447273313093174751L;

	/**
	 * 初始化发布环境信息
	 */
	public void init() throws ServletException {
		/*ServletContext servletContext = this.getServletContext();
		ApplicationContext context = SpringContextHolder.getSpringContext();
		AbstractGameServer aa = null;*/
		long begin = System.currentTimeMillis();
		CoreConfig.setConfPath(getCurrentPath());
		// 创建网关服务器
		GateServer gateServer = GateServer.getInstance();
		gateServer.start();// 启动服务器

		long end = System.currentTimeMillis();
		logger.info("【网关】服务器启动成功！启动耗时：【" + (end - begin) / 1000d + "】秒");
		RealtimeUtil util = RealtimeUtil.getInstance();
		util.saveDatas();
		util.checkTimeOut();

	}

	public String getCurrentPath() {
		// 取得根目录路径
		String rootPath = getClass().getResource("/").getFile().toString();
		/*
		 * //当前目录路径 String
		 * currentPath1=getClass().getResource(".").getFile().toString(); String
		 * currentPath2=getClass().getResource("").getFile().toString();
		 * //当前目录的上级目录路径 String
		 * parentPath=getClass().getResource("../").getFile().toString();
		 */
		return rootPath;

	}
}
