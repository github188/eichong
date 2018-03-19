package com.wanma.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 获取省市、区县代码及地区代码
  * @Description:通过气象局获取
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-5-26 上午11:08:46 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
public class CityUtil {

	public static String provinceCode;
	/** 
     * 获取所有中国 省份代码
     * */  
    public static String getProvinceCode() {  
            // TODO Auto-generated method stub  
    	    if(!StringUtils.isBlank(provinceCode)){
    	      return provinceCode;	
    	    }
            String ws_url = "http://www.weather.com.cn/data/list3/city.xml?level=1";  
            try {  
                URL url = new URL(ws_url);  
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));//解决乱码问题  
                StringBuffer sb = new StringBuffer();  
                String s = "";  
                while ((s = br.readLine()) != null) {  
                    sb.append(s + "\r\n"); //将内容读取到StringBuffer中  
                }  
                br.close();  
                //System.out.println(sb.toString()); 屏幕  
                provinceCode = new String(sb.toString().getBytes());  
            } catch (MalformedURLException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
            return provinceCode;  
    }  
    
	/** 
     * 获取所有中国 城市代码
     * */  
    public static String getCityCode(String provinceCode) {  
            // TODO Auto-generated method stub  
            String ws_url = "http://www.weather.com.cn/data/list3/city"+provinceCode+".xml?level=2";  
            String str= "";  
            try {  
                URL url = new URL(ws_url);  
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));//解决乱码问题  
                StringBuffer sb = new StringBuffer();  
                String s = "";  
                while ((s = br.readLine()) != null) {  
                    sb.append(s + "\r\n"); //将内容读取到StringBuffer中  
                }  
                br.close();  
                //System.out.println(sb.toString()); 屏幕  
                str = new String(sb.toString().getBytes());  
            } catch (MalformedURLException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
            return str;  
    } 
	/** 
     * 获取所有中国 区县代码
     * */  
    public static String getCountyCode(String cityCode) {  
            // TODO Auto-generated method stub  
            String ws_url = "http://www.weather.com.cn/data/list3/city"+cityCode+".xml?level=3";  
            String str= "";  
            try {  
                URL url = new URL(ws_url);  
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));//解决乱码问题  
                StringBuffer sb = new StringBuffer();  
                String s = "";  
                while ((s = br.readLine()) != null) {  
                    sb.append(s + "\r\n"); //将内容读取到StringBuffer中  
                }  
                br.close();  
                //System.out.println(sb.toString()); 屏幕  
                str = new String(sb.toString().getBytes());  
            } catch (MalformedURLException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
            return str;  
    } 
  
    /**
     * 获取省份列表
     * 01|北京,02|上海,03|天津,
     * @return
     */
    public static List<Map<String,Object>> getProvinceCodeByAarray(){
    	List<Map<String,Object>> provinceCodeList = new ArrayList<Map<String,Object>>();
    	String provinces=getProvinceCode();
    	String provinceArrayl[]=provinces.split(",");
    	for (int i=0;i<provinceArrayl.length;i++) {
    		String p[]=provinceArrayl[i].toString().split("\\|");
    		Map<String,Object> provinceCodeMap=new HashMap<String,Object>();
    		provinceCodeMap.put("provinceCode", p[0]+"|"+p[1]);
    		provinceCodeMap.put("provinceName", p[1]);
    		provinceCodeList.add(provinceCodeMap);
		}
    	return provinceCodeList;
    }
    
    /**
     * 获取城市列表
     * 01|北京,02|上海,03|天津,
     * @return
     */
    public static List<Map<String,Object>> getCityCodeByAarray(String proviceId){
    	List<Map<String,Object>> provinceCodeList = new ArrayList<Map<String,Object>>();
    	if(StringUtils.isBlank(proviceId)){
    		return null;
    	}
    	String citys=getCityCode(proviceId);
    	String cityArrayl[]=citys.split(",");
    	/*Map<String,Object> provinceCodeMap1=new HashMap<String,Object>();
    	provinceCodeMap1.put("cityCode", "");
    	provinceCodeMap1.put("cityName","-请选择-");
		provinceCodeList.add(provinceCodeMap1);
*/
		for (int i=0;i<cityArrayl.length;i++) {
    		String p[]=cityArrayl[i].toString().split("\\|");
    		Map<String,Object> provinceCodeMap=new HashMap<String,Object>();
    		provinceCodeMap.put("cityCode", p[0]+"|"+p[1]);
    		provinceCodeMap.put("cityName", p[1]);
    		provinceCodeList.add(provinceCodeMap);
		}
    	return provinceCodeList;
    }
    
    /**
     * 获取区县列表
     * 01|北京,02|上海,03|天津,
     * @return
     */
    public static List<Map<String,Object>> getCountyCodeByAarray(String cityId){
    	List<Map<String,Object>> countyCodeList = new ArrayList<Map<String,Object>>();
    	if(StringUtils.isBlank(cityId)){
    		return null;
    	}
    	String countys=getCountyCode(cityId);
    	String countyArrayl[]=countys.toString().split(",");
    	/*Map<String,Object> provinceCodeMap1=new HashMap<String,Object>();
    	provinceCodeMap1.put("countyCode", "");
    	provinceCodeMap1.put("countyName","-请选择-");*/
    	//countyCodeList.add(provinceCodeMap1);
		
    	for (int i=0;i<countyArrayl.length;i++) {
    		String p[]=countyArrayl[i].split("\\|");
    		Map<String,Object> provinceCodeMap=new HashMap<String,Object>();
    		provinceCodeMap.put("countyCode", p[0]+"|"+p[1]);
    		provinceCodeMap.put("countyName", p[1]);
    		countyCodeList.add(provinceCodeMap);
		}
    	return countyCodeList;
    }
    /** 
     * 根据传入参数得到城市天气预报信息ID, 其实也可以直接调用上面方法，为理解方便，故多加一个 
     * */  
    public static String weatherCityId(String id){  
        String ws_url = "http://m.weather.com.cn/data5/city"+id+".xml";  
        String str= "";  
        try {  
            URL url = new URL(ws_url);  
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));//解决乱码问题  
            StringBuffer sb = new StringBuffer();  
            String s = "";  
            while ((s = br.readLine()) != null) {  
                sb.append(s + "\r\n"); //将内容读取到StringBuffer中  
            }  
            br.close();  
            //System.out.println(sb.toString()); 屏幕  
            str = new String(sb.toString().getBytes());  
        } catch (MalformedURLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return str;  
    }  
	  public static void main(String[] args) {
		System.out.println("省份："+ getProvinceCodeByAarray());
		System.out.println("城市："+getCityCode("01"));
		System.out.println("区县："+getCountyCode("0101"));
	}
}
