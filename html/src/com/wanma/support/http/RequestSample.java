/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wanma.support.http;

//import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author
 */
public class RequestSample {

//    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) throws Exception {

        //信任证书相关的配置（单向验证，client不准备自己的证书）
        SSLContextBuilder builder = new SSLContextBuilder();
        //信任自签名证书
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                builder.build(), new HostnameVerifier() {

                    @Override
                    public boolean verify(String string, SSLSession ssls) {
                        //方便测试，先全部信任看能否调通
                        return true;
//                return "localhost".equalsIgnoreCase(string) || "127.0.0.1".equalsIgnoreCase(string);

                    }
                });

//        首先设置全局的标准cookie策略
        RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();

//设置可关闭的httpclient
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslsf).setDefaultRequestConfig(config).build();

        HttpPost post = new HttpPost("https://122.224.200.93:18080/api/pipe/records");
        // 创建参数队列    
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        String partnerId = "00000004";

        String partnerKey = "jaGYGJ/tC1wXK4ZnoLbJ+MgqYSD5BaQurx3sRyJWH6c=";

        long timestamp = System.currentTimeMillis();
        String token = genToken();

        //String input = "{\"data\":[{\"equipNo\":\"123340001\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340002\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340003\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340004\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340005\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340006\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340007\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340008\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340009\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334},{\"equipNo\":\"123340010\",\"gunNo\":\"12334\",\"manageOrgNo\":\"1234567890123456\",\"chargepoleStatus\":\"1\",\"fault_code\":\"4\",\"sampTime\":\"2016-01-02 11:12:12\",\"volt\":123,\"cur\":32,\"soc\":12334,\"ygzdd\":12334}]}";//
        String input="{\"data\":[{\"autoMaxVolt\":0.00,\"currentRated\":0.00,\"equipAddr\":\"杭州市西湖区古荡街道天目山路181号\",\"equipName\":\"天际大厦充电桩1\",\"equipId\":\"12312313\",\"equipNo\":\"3301021010000001\",\"equipStatus\":\"03\",\"equipType\":\"02\",\"gunNum\":\"1\",\"guns\":[{\"connectorType\":\"4\",\"gunEquipType\":\"02\",\"gunName\":\"1号枪头\",\"gunNo\":\"1\"}],\"madeDate\":\"2015-03-18\",\"operType\":\"2\",\"operatorId\":\"operatorId\",\"powerRating\":15,\"runDate\":\"2016-06-12\",\"voltageRated\":0.00}]}";;
        String toSign = token + "#" + timestamp;
        String sign = ApiSecurityUtils.encrypt(toSign, partnerKey, ApiSecurityUtils.HS256);

        nameValuePairs.add(new BasicNameValuePair("partnerId", partnerId));
        nameValuePairs.add(new BasicNameValuePair("timestamp", String.valueOf(timestamp)));
        nameValuePairs.add(new BasicNameValuePair("token", token));
        nameValuePairs.add(new BasicNameValuePair("input", input));
        nameValuePairs.add(new BasicNameValuePair("sign", sign));
//        nameValuePairs.add(new BasicNameValuePair("", ""));
        try {
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpResponse httpresponse = httpClient.execute(post);
        System.out.println("status:" + httpresponse.getStatusLine().getStatusCode());
        System.out.println("" + EntityUtils.toString(httpresponse.getEntity(), "utf-8"));
    }

    public static String genToken() {
        return RandomStringUtils.randomAlphanumeric(16);
    }
    
}
