package com.echong.common;

import com.google.common.base.Strings;
import com.echong.constant.EChongWangConsts;
import com.sgcc.constant.SGCCWangConsts;
import com.wanma.support.common.CommonConsts;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zangyaoyi on 2016/12/30.
 */
public class MessageManager {

    private static Logger log = Logger.getLogger(MessageManager.class);
    //系统配置文件
    private static Properties eChongProperties = null;
    private static Properties sgccProperties = null;
	private static Properties sysProperties = null;
	
    private static MessageManager propertiesManager = null;

    static {
        try {
            // 系统配置文件
            InputStream inputStreamEChong = MessageManager.class.getClassLoader()
                    .getResourceAsStream(EChongWangConsts.FILE_SETTING);
            InputStream inputStreamSGCC = MessageManager.class.getClassLoader()
                    .getResourceAsStream(SGCCWangConsts.FILE_SETTING);
            InputStream inputStreamSys = MessageManager.class.getClassLoader()
                    .getResourceAsStream(CommonConsts.PRO_FILE_SYSTEM_SETTING);
            try {
                // 系统配置文件
                eChongProperties = new Properties();
                eChongProperties.load(inputStreamEChong);
                sgccProperties = new Properties();
                sgccProperties.load(inputStreamSGCC);
                sysProperties =  new Properties();
                sysProperties.load(inputStreamSys);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                inputStreamEChong.close();
                inputStreamSGCC.close();
                inputStreamSys.close();
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    /**
     * 初始化Manager
     */
    public static MessageManager getMessageManager() {
        MessageManager manager;
        if (propertiesManager == null) {
            manager = new MessageManager();
        } else {
            manager = propertiesManager;
        }

        return manager;

    }

    public static String getEChongProperties(String messageKey) {
        if (Strings.isNullOrEmpty(messageKey)) {
            return null;
        }
        return eChongProperties.getProperty(messageKey);

    }

    public static String getSGCCProperties(String messageKey) {
        if (Strings.isNullOrEmpty(messageKey)) {
            return null;
        }
        return sgccProperties.getProperty(messageKey);

    }
    
    public static String getSystemProperties(String messageKey) {
    	 if (Strings.isNullOrEmpty(messageKey)) {
             return null;
         }
         return sysProperties.getProperty(messageKey);

    }
    
    public static void main(String[] args) {
        String a = getSGCCProperties("base_url");
        System.out.println(a);
    }

	
}
