package com.bluemobi.product.utils;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 将List对象转化成json字符�?
 * @author John.liu
 *
 */
public class JsonList {
	private StringBuffer sb;
	
	@SuppressWarnings("rawtypes")
	public JsonList(List list){
		sb = new StringBuffer();
		handleList(list);
	}
	
	@SuppressWarnings("rawtypes")
	private void handleObject(Object obj) throws Exception{
		if(obj == null) {
			sb.append("\"\"");
			return;
		}
		Class clazz = obj.getClass();
		String javaName = clazz.getName();
		if(javaName.startsWith("java.")){
			if(clazz.getSuperclass() == null){
				sb.append("\"").append(obj!=null?obj.toString():"").append("\"");
			}else{
				String superName = clazz.getSuperclass().getName();
				if(superName.startsWith("java.util.")&&superName.endsWith("List")){
					handleList((List)obj);
				}else if(superName.startsWith("java.util.")&&superName.endsWith("Map")){
					handleMap((Map)obj);
				}else{
					sb.append("\"").append(obj!=null?obj.toString():"").append("\"");
				}
			}
		}else{
			sb.append("{");
			Field[] fields = clazz.getDeclaredFields();
			for(int i=0;i<fields.length; i++){
				Field f = fields[i];
				f.setAccessible(true);
				Object val = f.get(obj);
				sb.append("\"").append(f.getName()).append("\":");
				handleObject(val);
//				sb.append("\"");
				
				if(i < fields.length-1) sb.append(",");
			}
			//删除�?��的�?�?
			if(sb.lastIndexOf(",") == sb.length()-1) sb.deleteCharAt(sb.length()-1);
			sb.append("}");
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void handleList(List list){
		sb.append("[");
		if(list == null) {
			sb.append("]");
			return;
		}
		Iterator it = list.iterator();
		try {
			while(it.hasNext()){
				Object obj = it.next();
				handleObject(obj);
				if(it.hasNext()) sb.append(",");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sb.append("]");
	}
	
	@SuppressWarnings("rawtypes")
	private void handleMap(Map map){
		sb.append("{");
		Iterator it = map.keySet().iterator();
		try {
			while(it.hasNext()){
				Object key = it.next();
				Object val = map.get(key);
				sb.append("\"");
				if(key != null) sb.append(key.toString());
				sb.append("\":");
				handleObject(val);
				if(it.hasNext()) sb.append(",");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		sb.append("}");
	}
	public String toString(){
		return sb.toString();
	}
}
