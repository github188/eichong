package com.wanma.support.simple;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleUtils.class);
	/**
     * POST方式发起http请求
     *
     * @param url    要请求的url
     * @param info 请求参数
     * @return http返回的response的body内容
     */
    public static String httpPost(String url, String info) throws IOException {
        HttpPost post = new HttpPost(url);      
        List<NameValuePair> list = new ArrayList<>();
        BasicNameValuePair  p1 = new BasicNameValuePair("url", url); 
        BasicNameValuePair  p2 = new BasicNameValuePair("info",info); 
        list.add(p1);
        list.add(p2);
        post.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
         HttpClient httpClient = getHttpClient();
         HttpResponse response = httpClient.execute(post);
         String strResult="";  
         if(response.getStatusLine().getStatusCode()==200){    
             try {  
                   /**读取服务器返回过来的json字符串数据**/  
                    strResult = EntityUtils.toString(response.getEntity());     
             } catch (IllegalStateException e) {  
                 e.printStackTrace();  
             } catch (IOException e) {  
                 e.printStackTrace(); 
             }
         }
        return strResult;
    }
    public static HttpClient getHttpClient() {
        try {
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
            schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
            ClientConnectionManager ccm = new PoolingClientConnectionManager(schemeRegistry);

            //fixme 此处创建支持https的httpClient对象，但会接受任意的https证书，有安全隐患，生产环境中应避免不对https证书做校验
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs, String string) {
                }

                public void checkServerTrusted(X509Certificate[] xcs, String string) {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", ssf, 443));
            return new DefaultHttpClient(ccm);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
	public static String sendSimple(String url, String info) {       
		
        if (null == info) {
            LOGGER.error("sendSimple is fail;url={}", url);
            return null;
        }
        String response = null;
        try {
            response = SimpleUtils.httpPost(url, info);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("send to simple is fail;url={}|info={}", url, info, e);
        }
        return response;
    }
	
}
