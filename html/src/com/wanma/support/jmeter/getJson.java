package com.wanma.support.jmeter;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;


public class getJson {
	private static getJson instance = null;
	
	public static getJson getInstance() {
		if (instance == null)
			instance = new getJson();
		return instance;
	}

public static String getItemBy(String data,String item,int num){
	
	String[] a=item.split("-");
	int n=a.length;
	
	JSONObject json1 = new JSONObject();
	
	String bak=json1.fromObject(data).get(a[0]).toString();
   

	if(n==1){
		return bak;
	}
	else if(n==2){
		JSONArray array2 = JSONArray.fromObject(bak);
		JSONObject json2=new JSONObject();
		bak=json2.fromObject(array2.get(num-1).toString()).get(a[1]).toString();
		return bak;
	}
	
	
	
	return bak;
}
}
