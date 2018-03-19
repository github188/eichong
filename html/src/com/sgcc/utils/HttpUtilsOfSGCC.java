package com.sgcc.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgcc.constant.CommonConsts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by gaoyang on 17/1/10.
 */
public class HttpUtilsOfSGCC {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtilsOfSGCC.class);

    /**
     * 发送请求到服务端
     */
    public static JSONObject sendData(String url, String accessToken, String data) {
        LOGGER.info("sendData data:url:{}|data:{}", url, data);
        //加密数据
        try {
            data = AESUtils.encrypt(data, CommonConsts.SGCC_DATA_SECRET, CommonConsts.SGCC_DATA_SECRET_IV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //生成签名
        HashMap<String, String> map = AppTool.makeSig(data);
        //把token放到头部
        HashMap<String, String> headers = new HashMap();
        if (null != accessToken && "" != accessToken) {
        	 /*if("true".equals(CommonConsts.SGCC_IS_TEST)){
	            LOGGER.info("sendData answer:test");
	            return new JSONObject();
	        }*/
			if(!"1".equals(CommonConsts.SGCC_IS_TEST)){
		        LOGGER.info("sendData answer:test");
		        return new JSONObject();
		    }
            headers.put("Authorization", "Bearer " + accessToken);
        }

        //发送请求
        String answerStr = doPost(url, headers, map);
        LOGGER.info("sendData answer:url:{}|answer:{}", url, answerStr);
        JSONObject result = JSON.parseObject(answerStr);
        if (result == null || !result.containsKey("Ret")) {
            LOGGER.error("sendData answer error,not JSON:url:{}|result:{}", url, result);
            return null;
        }
        if (!AppTool.verifySig(result)) {
            LOGGER.error("sendData answer error,ensure sig fail:url:{}|result:{}", url, result);
        }
        String dataStr = null;

        try {
            dataStr = AESUtils.decrypt(result.getString("Data"), CommonConsts.SGCC_DATA_SECRET, CommonConsts.SGCC_DATA_SECRET_IV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dataStr == null) {
            LOGGER.error("sendData answer error,decrypt fail:url:{}|result:{}", url, dataStr);
        }
        result.put("Data", JSON.parseObject(dataStr));
        System.out.println("服务端返回解密数据：" + JSON.toJSONString(result));
        LOGGER.info("sendData answer success;url:{}|result:{}", url, result);
        if (0 != (int) result.get("Ret")) {
            LOGGER.error("sendData answer fail;url:{}|result:{}", url, result);
        }
        return result;
    }

    public static String doPost(String connectURL, Map<String, String> headers, Map<String, String> params) {
        HttpPost httpPost = new HttpPost(connectURL);
        for (String headerName : headers.keySet()) {
            httpPost.setHeader(headerName, headers.get(headerName));
        }
        httpPost.setEntity(new StringEntity(JSON.toJSONString(params), Charset.forName("UTF-8")));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response2 = null;
        try {
            response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
//    	    EntityUtils.toString(arg0, arg1)
            return EntityUtils.toString(entity2, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response2 != null) {
                try {

                    response2.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    public static void main(String[] args) {
        String a = "[{\"StationID\":\"YFYSTATIONID114\",\"OperatorID\":\"321895837\",\"EquipmentOwnerID\":\"321895837\",\"StationUrl\":\"012345678\",\"StationName\":\"站点YFY101\",\"CountryCode\":\"CN\",\"AreaCode\":\"230623\",\"Address\":\"测试地址1\",\"StationTel\":\"站点电话\",\"ServiceTel\":\"服务电话\",\"StationType\":1,\"StationStatus\":50,\"ParkNums\":3,\"StationLng\":116.293058,\"StationLat\":40.40672,\"SiteGuide\":\"左转直行50米，右转就到了\",\"Construction\":1,\"Pictures\":\"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png,http://www.a.com/b.png\",\"MatchCars\":\"大巴\",\"ParkInfo\":\"地下一层，4\",\"BusineHours\":\"08:00-20:00\",\"ElectricityFee\":\"0.8/度\",\"ServiceFee\":\"10元\",\"ParkFee\":\"10元/小时\",\"Payment\":\"刷卡\",\"SupportOrder\":0,\"Remark\":\"备注12\",\"OpenForBusinessDate\":\"2014-01-01\",\"StationUrl\":\"http://www.baidu.com\"}]";
        //发送请求
        String b = "[{\"StationID\":\"YFYSTATIONID114\",\"OperatorID\":\"321895837\",\"EquipmentOwnerID\":\"321895837\",\"StationUrl\":\"012345678\",\"StationName\":\"站点YFY101\",\"CountryCode\":\"CN\",\"AreaCode\":\"230623\",\"Address\":\"测试地址1\",\"StationTel\":\"站点电话\",\"ServiceTel\":\"服务电话\",\"StationType\":1,\"StationStatus\":50,\"ParkNums\":3,\"StationLng\":116.293058,\"StationLat\":40.40672,\"SiteGuide\":\"左转直行50米，右转就到了\",\"Construction\":1,\"Pictures\":\"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png,http://www.a.com/b.png\",\"MatchCars\":\"大巴\",\"ParkInfo\":\"地下一层，4\",\"BusineHours\":\"08:00-20:00\",\"ElectricityFee\":\"0.8/度\",\"ServiceFee\":\"10元\",\"ParkFee\":\"10元/小时\",\"Payment\":\"刷卡\",\"SupportOrder\":0,\"Remark\":\"备注12\",\"OpenForBusinessDate\":\"2014-01-01\",\"StationUrl\":\"http://www.baidu.com\"}]";

        String c = "Data=[{\"Address\":\"杭州市江干区运河东路与杭海路交叉口北侧东方大厦东侧地面停车场\",\"AreaCode\":\"330104\",\"Construction\":255,\"CountryCode\":\"CN\",\"EquipmentOwnerID\":\"321895837\",\"OpenForBusinessDate\":\"2015-11-12\",\"OperatorID\":\"321895837\",\"ParkNums\":0,\"ServiceTel\":\"400-085-0006\",\"StationID\":\"260\",\"StationLat\":30.269449,\"StationLng\":120.228144,\"StationName\":\"杭州市江干区东方大厦\",\"StationStatus\":50,\"StationType\":255},{\"Address\":\"杭州市西湖区石祥西路859号\",\"AreaCode\":\"330106\",\"Construction\":255,\"CountryCode\":\"CN\",\"EquipmentOwnerID\":\"321895837\",\"OpenForBusinessDate\":\"2017-04-13\",\"OperatorID\":\"321895837\",\"ParkNums\":0,\"ServiceTel\":\"400-085-0006\",\"StationID\":\"901\",\"StationLat\":30.183932,\"StationLng\":120.042022,\"StationName\":\"紫金创业园开放（不对外）\",\"StationStatus\":50,\"StationType\":255},{\"Address\":\"杭州市西湖区石祥西路859号\",\"AreaCode\":\"330106\",\"Construction\":255,\"CountryCode\":\"CN\",\"EquipmentOwnerID\":\"321895837\",\"OpenForBusinessDate\":\"2017-04-13\",\"OperatorID\":\"321895837\",\"ParkNums\":0,\"ServiceTel\":\"400-085-0006\",\"StationID\":\"902\",\"StationLat\":30.183932,\"StationLng\":120.042022,\"StationName\":\"紫金创业园专属\",\"StationStatus\":50,\"StationType\":255}]";
        System.out.println(a.equals(b));
    }

}
