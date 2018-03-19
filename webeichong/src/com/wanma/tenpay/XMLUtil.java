package com.wanma.tenpay;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


/**
 * xml工具类
 * @author miklchen
 *
 */
public class XMLUtil {

	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map<String,Object> doXMLParse(String strxml) throws JDOMException, IOException  {
		Map<String,Object> m = new HashMap<String,Object>();
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = XMLUtil.getChildrenText(children);
			}
			
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		return m;
	}
	
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(XMLUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取xml编码字符集
	 * @param strxml
	 * @return
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public static String getXMLEncoding(String strxml) throws JDOMException, IOException {
		InputStream in = HttpClientUtil.String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		in.close();
		return (String)doc.getProperty("encoding");
	}
	
	
	public static void main(String[] args) throws JDOMException, IOException {
		String s="<xml><appid><![CDATA[wx6f19e4001b2c467a]]></appid>"
		+"<bank_type><![CDATA[BOC_DEBIT]]></bank_type>"
		+"<cash_fee><![CDATA[1]]></cash_fee>"
		+"<fee_type><![CDATA[CNY]]></fee_type>"
		+"<is_subscribe><![CDATA[N]]></is_subscribe>"
		+"<mch_id><![CDATA[1235355502]]></mch_id>"
		+"<nonce_str><![CDATA[3d98b79ac6c8d1cef43d7bf1dadf8647]]></nonce_str>"
		+"<openid><![CDATA[oO6SeuB2zHwFVFTzDxCptqkK2yno]]></openid>"
		+"<out_trade_no><![CDATA[1434678987893ech284]]></out_trade_no>"
		+"<result_code><![CDATA[SUCCESS]]></result_code>"
		+"<return_code><![CDATA[SUCCESS]]></return_code>"
		+"<sign><![CDATA[20685C32369FB41D2AF1F6306CA178C0]]></sign>"
		+"<time_end><![CDATA[20150619095638]]></time_end>"
		+"<total_fee>1</total_fee>"
		+"<trade_type><![CDATA[APP]]></trade_type>"
		+"<transaction_id><![CDATA[1000560974201506190270007884]]></transaction_id>"
		+"</xml>";
		Map<String,Object> map=XMLUtil.doXMLParse(s);
		for(Entry<String,Object> entry : map.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
	
}