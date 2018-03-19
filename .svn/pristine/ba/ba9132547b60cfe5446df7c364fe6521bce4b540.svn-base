package com.appCore.service;

import org.apache.log4j.Logger;
import org.jdom.Element;

import com.appCore.conf.CoreConfig;
import com.appCore.model.conf.GlobalConfig;

public class GlobalConfigService {
	
	private static final Logger initConfigLog = Logger.getLogger("InitConfigLog");
	
	/**
	 * 初始化全局配置
	 * @author hao
	 * Mar 19, 2014 10:47:09 AM
	 * @return
	 */
	public static GlobalConfig initGlobalConfig() {
		
		initConfigLog.info("开始初始化全局配置...");
		
		GlobalConfig globalConfig = new GlobalConfig();
		
		Element root =  CoreConfig.getRootElement("global-config.xml");
		
		byte mask1 = Byte.valueOf(root.getChild("mask1").getValue());
		byte mask2 = Byte.valueOf(root.getChild("mask2").getValue());
		byte mask3 = Byte.valueOf(root.getChild("mask3").getValue());
		byte mask4 = Byte.valueOf(root.getChild("mask4").getValue());
		
		globalConfig.setMask1(mask1);
		globalConfig.setMask2(mask2);
		globalConfig.setMask3(mask3);
		globalConfig.setMask4(mask4);
		
		initConfigLog.info("初始化全局配置结束...配置信息:【" + globalConfig.toString() + "】");
		
		return globalConfig;
	}



}
