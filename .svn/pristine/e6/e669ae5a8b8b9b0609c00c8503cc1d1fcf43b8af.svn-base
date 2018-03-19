/**
 * FileName:InitDeployUrlServelt.java
 * Author: Administrator
 * Create: 2014年8月10日
 * Last Modified: 2014年8月10日
 * Version: V1.0 
 */
package com.wanma.support.common;

import com.ec.usrcore.config.GameBaseConfig;
import com.wanma.service.impl.EpServiceImpl;
import com.wanma.service.impl.TcbPartnerServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
/**
 * 初始化发布环境信息
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月10日
 */
public class InitDeployUrlServelt extends HttpServlet {
	private static final Logger logger = Logger.getLogger(InitDeployUrlServelt.class);

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = -1447273313093174751L;

	/**
	 * 初始化发布环境信息
	 */
	public void init() throws ServletException {
		try {
		
		long begin = System.currentTimeMillis();
		ApplicationContext context = SpringContextHolder.getSpringContext();
		WanmaConstants.cs.init(3, this.getClass().getResource("/").getPath(), (EpServiceImpl) context.getBean("epServiceImpl"));//通讯服务回调初始化
		GameBaseConfig.loadGameBaseConfig();
		long end = System.currentTimeMillis();
		logger.info("ep服务初始化成功！耗时：【" + (end - begin) / 1000d + "】秒");
		((RedisService) context.getBean("redisService")).strSet(WanmaConstants.PREFIX_ORG + WanmaConstants.CCZC_CODE, ((TcbPartnerServiceImpl)context.getBean("tcbPartnerServiceImpl")).getKey(WanmaConstants.CCZC_CODE));
        ((RedisService) context.getBean("redisService")).strSet(WanmaConstants.PREFIX_ORG + WanmaConstants.ECW_CODE, ((TcbPartnerServiceImpl)context.getBean("tcbPartnerServiceImpl")).getKey(WanmaConstants.ECW_CODE));
        ((RedisService) context.getBean("redisService")).strSet(WanmaConstants.PREFIX_ORG + WanmaConstants.WX_CODE, ((TcbPartnerServiceImpl)context.getBean("tcbPartnerServiceImpl")).getKey(WanmaConstants.WX_CODE));

		
		}catch(Exception e){
			logger.error("InitDeploy初始化失败！");
		}
	}
	
}
