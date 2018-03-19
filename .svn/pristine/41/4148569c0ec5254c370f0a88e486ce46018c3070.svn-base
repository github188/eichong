package com.bluemobi.product.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.bluemobi.product.common.dao.DataAuthXmlReader;
import com.bluemobi.product.model.common.XMLModel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * XML文件处理工具类
 * 
 * @author yangweir
 * @date 2014/07/15
 */
public class XmlUtil {

	// 日志输出对象
	private static Logger log = Logger
			.getLogger(XmlUtil.class);
	
	/**
	 * 简单Java对象转成XML文件
	 * 
	 * @param bean
	 * @return Java类名为根节点，属性为子节点的XML文件
	 */
	@SuppressWarnings("rawtypes")
	public static String objectToXML(Object bean) {
		Class clazz = bean.getClass();
		XStream xstream = new XStream();
		xstream.alias(clazz.getSimpleName(), clazz);
		return xstream.toXML(bean);
	}

	/**
	 * Java对象转成XML文件
	 * 
	 * @param bean
	 * @param aliases
	 *            Map<String, Class> 别名-类型关联配置
	 * @return XML格式的字符串
	 */
	@SuppressWarnings("rawtypes")
	public static String objectToXML(Object bean, Map<String, Class> aliases) {
		XStream xstream = new XStream();

		Iterator<Entry<String, Class>> it = aliases.entrySet().iterator();

		while (it.hasNext()) {

			Entry<String, Class> el = it.next();
			xstream.alias(el.getKey(), el.getValue());
		}
		return xstream.toXML(bean);
	}

	/**
	 * XML格式字符串转Java对象
	 * 
	 * @param xml
	 *            XML格式字符串
	 * @param aliases
	 *            Map<String, Class> 别名-类型关联配置
	 * @return Java对象
	 */
	@SuppressWarnings("rawtypes")
	public static Object xmlToObject(String xml, Map<String, Class> aliases) {
		XStream xstream = new XStream(new DomDriver());

		Iterator<Entry<String, Class>> it = aliases.entrySet().iterator();
		while (it.hasNext()) {

			Entry<String, Class> el = it.next();
			xstream.alias(el.getKey(), el.getValue());
		}
		return xstream.fromXML(xml);
	}

	/**
	 * 取得XML对象
	 * 
	 * @param strXMLSource
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static XMLModel parseNoAwareXML(String strXMLSource) {
		// 存储xml元素信息的容器
		XMLModel xmlModel = new XMLModel();

		// variable declare //
		FileInputStream inputStream = null;
		// path
		URL confURL = null;

		try {
			// get SAX factory object
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setNamespaceAware(false);
			factory.setValidating(false);

			// create the URL of xml source
			confURL = DataAuthXmlReader.class.getClassLoader().getResource(
					strXMLSource);
			// create inputstream
			inputStream = new FileInputStream(confURL.getFile());

			SAXBuilder reader = new SAXBuilder();
			Document doc = reader.build(inputStream);

			Element root = doc.getRootElement();

			List<Element> childElements = root.getChildren();

			xmlModel = getModelByElement(root);
			if (childElements != null && childElements.size() > 0) {
				List<XMLModel> childList = new ArrayList<XMLModel>();
				xmlModel.setHasChildFlg(true);
				getElementList(root, xmlModel, childList);
			}

		} catch (IOException ie) {
			log.error("XML IO READ ERROR");
			log.error(ie.getLocalizedMessage());
			return null;
		} catch (Exception e) {
			log.error("XML READ ERROR");
			log.error(e.getLocalizedMessage());
			return null;
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException ioe) {

			}
		}

		return xmlModel;
	}

	

	/**
	 * 递归遍历方法
	 * 
	 * @param element
	 */
	@SuppressWarnings("unchecked")
	public static void getElementList(Element element, XMLModel parentModel,
			List<XMLModel> childList) {
		List<Element> elements = element.getChildren();

		// 有子元素
		for (Iterator<Element> it = elements.iterator(); it.hasNext();) {
			Element childElements = (Element) it.next();
			XMLModel childModel = getModelByElement(childElements);
			childList.add(childModel);
			parentModel.setChildList(childList);

			if (childElements.getChildren().size() > 0) {
				childModel.setHasChildFlg(true);
				// 递归遍历
				List<XMLModel> newChildList = new ArrayList<XMLModel>();
				getElementList(childElements, childModel, newChildList);
			}
		}
	}

	/**
	 * 根据XML Element生成XML对象
	 * 
	 * @param element
	 *            XML Element
	 * @return XML对象
	 */
	public static XMLModel getModelByElement(Element element) {
		String key = element.getName();
		String value = element.getTextTrim();
		XMLModel xmlModel = new XMLModel();
		xmlModel.setKey(key);
		xmlModel.setValue(value);

		return xmlModel;
	}
	
	
	
	public static void main(String[] args) {
		//XMLModel xmlModel = XmlUtil.parseNoAwareXML("data-auth-setting.xml");
		//System.out.println(xmlModel);
		System.out.println(StringUtil.lastIndexOfIgnoreCase("ABCD",("cd")));
	}
}
