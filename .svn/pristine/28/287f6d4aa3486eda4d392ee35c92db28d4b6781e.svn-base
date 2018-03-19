package com.wanma.dubbox.service.impl;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.wanma.common.DataAuthXmlReader;
import com.wanma.dubbox.model.common.BasicModel;
import com.wanma.dubbox.service.CmsRedisCacheableService;

@Service
public class CmsRedisCacheableServiceImpl implements CmsRedisCacheableService {

	@Autowired
	private RedisService redisService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void cachedDataInit(ApplicationContext context) {
		FileInputStream inputStream = null;
		// path
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(false);
		factory.setValidating(false);
		// create the URL of xml source
		URL confURL = DataAuthXmlReader.class.getClassLoader().getResource(
				DataAuthXmlReader.REDIS_CACHE_CLASS_XML);
		// create inputstream
		try {
			inputStream = new FileInputStream(confURL.getFile());
			SAXBuilder reader = new SAXBuilder();
			Document doc = reader.build(inputStream);

			Element root = doc.getRootElement();
			List<Element> childElements = root.getChildren("class-config");
			for (Element element : childElements) {
				String beanName = element.getChild("class-bean-name")
						.getValue();
				if (beanName != null) {
					Object serviceObj = context.getBean(beanName);
					Class clazz = serviceObj.getClass();
					String data_key = element.getChild("key-cache").getValue();
					Class modelClass = Class.forName(data_key);
					Method m = clazz.getDeclaredMethod(
							element.getChild("method-name").getValue(),
							modelClass);
					if(!redisService.exists(data_key)){
						// 主键存储
						List<Object> dataList = (List<Object>) m.invoke(serviceObj,
								modelClass.newInstance());
						for (int i = 0; i < dataList.size(); i++) {
							Object data = dataList.get(i);
							String key2 = ((BasicModel) data).getId().toString();
							// 基本数据缓存
							redisService.objPut(data_key, key2, data);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
