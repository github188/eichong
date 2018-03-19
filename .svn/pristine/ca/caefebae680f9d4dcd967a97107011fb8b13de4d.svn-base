package com.bluemobi.config;

import org.apache.log4j.Logger;
import org.jdom.Element;

import com.bluemobi.service.XmlService;

/**
 * 配置的服务参数
 * @author haojian
 * Apr 22, 2013 4:07:24 PM
 */
public class GameParam {
    private static final Logger logger = Logger.getLogger(GameParam.class);
    
    static {
        init();
    }


    /**
     * 初始化参数表模板数据
     */
    public static void init() {
        logger.info("初始化策划配置的服务参数!!!");
        Element root = XmlService.getRootElement("parameters.xml");
        Element e = root.getChild("parameter");
    } 	

}
