package com.bluemobi.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bluemobi.model.template.LanguageTemplate;
import com.bluemobi.service.XmlService;


/**
 * 服务基础数据
 */
public class GameTemplate {

    private static final Logger logger = Logger.getLogger(GameTemplate.class);
    //国际化语言配置
    private static Map<String, LanguageTemplate> languageTemplateMap = new HashMap<String, LanguageTemplate>();
  

    /**
     * 获取国际化语言配置
     */
    public static Map<String, LanguageTemplate> getLanguageTemplateMap() {
        return languageTemplateMap;
    }


	static {
        //初始化国际化语言配置
        languageTemplateMap = XmlService.initLanguageTemplateMap();
    }
}
