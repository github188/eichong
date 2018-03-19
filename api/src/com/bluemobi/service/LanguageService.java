package com.bluemobi.service;

import java.util.Map;

import com.bluemobi.cache.GameTemplate;
import com.bluemobi.model.template.LanguageTemplate;

/**
 * 语言服务相关处理
 *
 * @author xujianxin
 * @time Jun 24, 2013 5:55:41 PM
 */
public class LanguageService {

    static Map<String, LanguageTemplate> languageMap = GameTemplate.getLanguageTemplateMap();

    /**
     * 根据语言设置的key获取 对应的文字内容
     *
     * @param key
     * @return
     */
    public static String getLanguage(String key) {
        LanguageTemplate temp = languageMap.get(key);
        if (temp == null) {
            return key;
        }
        return temp.getLanguage();
    }
}
