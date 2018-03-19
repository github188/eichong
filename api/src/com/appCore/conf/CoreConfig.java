package com.appCore.conf;

import java.io.File;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.appCore.model.GameObject;
import com.appCore.model.conf.ClientConfigs;
import com.appCore.model.conf.GameConfig;
import com.appCore.model.conf.GlobalConfig;
import com.appCore.model.conf.ServerConfigs;
import com.appCore.service.GameConfigService;
import com.appCore.service.GlobalConfigService;
/**
 * 核心配置
 * @author hao
 * Mar 19, 2014 2:42:44 PM
 */
public class CoreConfig extends GameObject{
	
	private static final Logger initConfigLog = Logger.getLogger("InitConfigLog");
	
	private static String confPath = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "conf"
			+ System.getProperty("file.separator");
	
	
	/**全局的配置*/
	public static GlobalConfig globalConfig;
	
	/**服务器配置*/
	public static GameConfig gameConfig;
	
	/**netty服务器配置*/
	public static ServerConfigs serverConfigs;
	
	/**netty客户端配置*/
	public static ClientConfigs clientConfigs;
	
	public CoreConfig(){
		
		globalConfig = GlobalConfigService.initGlobalConfig();
		
		gameConfig = GameConfigService.initGameConfig();
		
		//serverConfigs = ServerConfigService.initServerConfigs();
		
		//clientConfigs = ClientConfigService.initClientConfigs();
		
	}
	
	
	
	/**
	 * 通过xml名称获取 xml 的根元素
	 * @author haojian
	 * Apr 7, 2013 10:33:24 AM
	 * @param fileName
	 * @return
	 */
	public static Element getRootElement(String fileName){
		Document doc = null;
		try {
			SAXBuilder sb = new SAXBuilder();
			String fileFullName = confPath+fileName;
			initConfigLog.info("读取xml文件：【" + fileFullName + "】");
			doc = sb.build(new File(fileFullName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		return root;
	}
	
}
