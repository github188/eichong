package com.bluemobi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.bluemobi.model.template.LanguageTemplate;
import com.bluemobi.product.utils.StringUtil;


/**
 * 读取xml的服务类
 *
 * @author haojian
 *         Apr 18, 2012 6:47:32 PM
 */
public class XmlService {

    private static final Logger logger = Logger.getLogger(XmlService.class);

    private static String xmlPath = System.getProperty("user.dir")
            + System.getProperty("file.separator") + "xml"
            + System.getProperty("file.separator");

    public static final String SPLIT_SIGN_ERR = "；";
    public static final String SPLIT_SIGN = ";";

    /**
     * 通过xml名称获取 xml 的根元素
     *
     * @param fileName
     * @return
     * @author haojian
     * Apr 7, 2013 10:33:24 AM
     */
    public static Element getRootElement(String fileName) {
        Document doc = null;
        try {
            SAXBuilder sb = new SAXBuilder();
            doc = sb.build(xmlPath + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Element root = doc.getRootElement();
        return root;
    }

    /**
     * 初始化国际化语言配置
     */
    public static Map<String, LanguageTemplate> initLanguageTemplateMap() {
        Map<String, LanguageTemplate> languagetemplateMap = new HashMap<String, LanguageTemplate>();

        Element root = XmlService.getRootElement("language.xml");

        List<Element> languages = root.getChildren();
        for (Element element : languages) {
            String key = element.getName();
           // String language = StringUtil.toString(element.getText());

            LanguageTemplate template = new LanguageTemplate();
            template.setKey(key);
          //  template.setLanguage(language);

            languagetemplateMap.put(key, template);
        }

        return languagetemplateMap;
    }

}
