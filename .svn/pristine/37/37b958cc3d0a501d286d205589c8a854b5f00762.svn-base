package com.wanma.ims.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import net.sf.json.JSONObject;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import sun.misc.BASE64Decoder;


/**
 * Created by haner on 15/4/14.
 *
 * @author Haner
 *         网络请求工具
 */
public class HttpsUtil {

    private static PoolingHttpClientConnectionManager connManager;
    private static CloseableHttpClient httpClient;
    private static HttpGet httpGet;
    private static HttpPost httpPost;
    private static RequestConfig requestConfig;
    private static final int REQUEST_TIMEOUT = 30 * 1000; // 设置请求超时10秒钟
    private static final int TIMEOUT = 60 * 1000; // 连接超时时间
    private static final int SO_TIMEOUT = 60 * 1000; // 数据传输超时
    private static final String CHARSET = "UTF-8";


    /**
     * 获取HttpClient
     *
     * @return
     * httpClient
     */
    static {

        /**
         *  初始化httpclient
         */
        try {
            // SSLContext
            SSLContextBuilder sslContextbuilder = new SSLContextBuilder();
            sslContextbuilder.useTLS();
            SSLContext sslContext = sslContextbuilder.loadTrustMaterial(null, new TrustStrategy() {

                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }

            }).build();

            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)).build();

            // Create ConnectionManager
            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

            // Create socket configuration
            SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
            connManager.setDefaultSocketConfig(socketConfig);

            // Create message constraints
            MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount(200).setMaxLineLength(2000).build();

            // Create connection configuration
            ConnectionConfig connectionConfig = ConnectionConfig.custom()
                    .setMalformedInputAction(CodingErrorAction.IGNORE)
                    .setUnmappableInputAction(CodingErrorAction.IGNORE)
                    .setCharset(Consts.UTF_8)
                    .setMessageConstraints(messageConstraints).build();

            connManager.setDefaultConnectionConfig(connectionConfig);
            connManager.setMaxTotal(200);
            connManager.setDefaultMaxPerRoute(20);

            // Create httpClient
            httpClient = HttpClients.custom().disableRedirectHandling().setConnectionManager(connManager).build();
        } catch (KeyManagementException e) {

        } catch (NoSuchAlgorithmException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 初始化 config
         */
        requestConfig = RequestConfig.custom()
                .setSocketTimeout(SO_TIMEOUT)
                .setConnectTimeout(TIMEOUT)
                .setConnectionRequestTimeout(REQUEST_TIMEOUT).build();

    }


    /**
     * Http Get请求 请求地址
     *
     * @param url    Get参数
     * @param params 编码
     * @param encode 返回请求结果
     * @return
     * @throws IOException
     */
    public static String sendGetRequest(String url, Map<String, String> params, String encode) {
        String result = null;
        try {
            if (null == params) {
                httpGet = new HttpGet(url);
            } else {
                httpGet = new HttpGet(url + dealGetParams(params, encode));
            }

            httpGet.setConfig(requestConfig);

            HttpResponse response = httpClient.execute(httpGet);

            printHeaders(response);

            result = EntityUtils.toString(response.getEntity());

            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.err.print("请求状态码:" + response.getStatusLine().getStatusCode());
                result = null;
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result != null ? result : "";
    }

    public static String sendGetRequest(String url, Map<String, String> params) throws IOException {
        return sendGetRequest(url, params, CHARSET);
    }

    public static String sendGetRequest(String url) throws IOException {
        return sendGetRequest(url, null, CHARSET);
    }


    /**
     * POST请求 返回请求结果 HashMap键值参数
     *
     * @param params
     * @return
     * @throws Exception
     */
    public static String sendPostRequest(String url, Object params, String encode) throws Exception {
        String resultStr = null;
        httpPost = new HttpPost(url);
        if (params != null) {
            StringEntity entity;
            if (params instanceof Map) {
                entity = new StringEntity(dealPostParams((HashMap<String, String>) params, encode));
            } else if (params instanceof String) {
                entity = new StringEntity((String) params, encode);
            } else if (params instanceof List) {
                entity = new UrlEncodedFormEntity((List<? extends NameValuePair>) params, encode);
            } else {
                throw new Exception("参数有误!");
            }
            httpPost.setEntity(entity);
        }
        try {

            HttpResponse response = httpClient.execute(httpPost);

            //输出头信息
           // printHeaders(response);

            resultStr = EntityUtils.toString(response.getEntity());

            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.err.println("请求结果:" + resultStr);
                System.err.println("请求状态码:" + response.getStatusLine().getStatusCode());
                resultStr = null;
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultStr != null ? resultStr : null;
    }

    public static String sendPostRequest(String url) throws Exception {
        return sendPostRequest(url, "");
    }

    public static String sendPostRequest(String url, Map<String, Object> params) throws Exception {
        return sendPostRequest(url, params, CHARSET);
    }

    public static String sendPostRequest(String url, String params) throws Exception {
        return sendPostRequest(url, params == null ? null : params, CHARSET);
    }

    public static String sendPostRequest(String url, List<NameValuePair> params) throws Exception {
        return sendPostRequest(url, params, CHARSET);
    }

    /**
     * 处理Get方式请求的URL
     *
     * @param params
     * @param enc
     * @return
     */
    private static String dealGetParams(Map<String, String> params, String enc) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        sb.append("?");
        for (Entry<String, String> entry : params.entrySet()) {
            // 参数名=参数&参数名=参数
            sb.append(entry.getKey()).append("=")
                    .append(URLEncoder.encode(entry.getValue(), enc))
                    .append("&");
        }
        // 删除最后一个&
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * 处理POST请求URL
     *
     * @param params
     * @param enc
     * @return
     */
    private static String dealPostParams(Map<String, String> params, String enc) {
        StringBuffer sb = new StringBuffer();
        for (Entry<String, String> entry : params.entrySet()) {
            try {
                sb.append(entry.getKey()).append("=")
                        .append(URLEncoder.encode(entry.getValue(), enc))
                        .append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    /**
     * 输出头信息
     *
     * @param httpResponse
     */
    private static void printHeaders(HttpResponse httpResponse) {
//        HeaderIterator it = httpResponse.headerIterator();
//        while (it.hasNext()) {
//        }
    }
    
    public static String getResponseParam(String url,String paramName){
    	JSONObject json=null;
		try {
			json = JSONObject.fromObject(sendPostRequest(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return json.getString(paramName);
    }
    
    /** 
     * 执行一个HTTP POST请求，返回请求响应的HTML 
     * 
     * @param url        请求的URL地址 
     * @param params 请求的查询参数,可以为null 
     * @return 返回请求响应的HTML 
     */ 
    public static String doPost(String url, Map<String, String> params) { 
    	return HttpRequestUtil.post(url, params);
           /* String response = null; 
            HttpClient client = new HttpClient(); 
            HttpMethod method=null;
            //设置Http Post数据 
            String param="";
            if (params != null) { 
                    HttpMethodParams p = new HttpMethodParams(); 
                    for (Map.Entry<String, String> entry : params.entrySet()) { 
                    	param+=entry.getKey()+"="+entry.getValue()+"&"; 
                    } 
                    param=param.substring(0,param.length()-1);
            } 
            try { 	
            	 	method = new PostMethod(url+"?"+param); 
                    client.executeMethod(method); 
                    if (method.getStatusCode() == HttpStatus.SC_OK) { 
                            response = method.getResponseBodyAsString(); 
                    } 
            } catch (IOException e) { 
                    System.out.println("执行HTTP Post请求" + url + "时，发生异常！"); 
            } finally { 
                    method.releaseConnection(); 
            } 

            return response; */
    } 

    public static void main(String[] args) { 
    		Map map=new HashMap();
    		map.put("paramStrs", "3301851010000005:1");
    		map.put("t", "NTA3Mkhma1JRSjA5NzI3NDgyNTMxODU3ODU0");
            String x = doPost("http://10.9.3.123/api/app/net/sendTwoDiCode.do", map); 
            System.out.println(x); 
    } 
    
    public static void main2(String[] args) {
    	String token="";
		try {
			token = ApiUtil.getToken();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("----------------------------------------------");
    	System.out.println(token);
    	try {
    		System.out.println("----------------------------------------------");
			System.out.println(new String(new BASE64Decoder().decodeBuffer(token)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
