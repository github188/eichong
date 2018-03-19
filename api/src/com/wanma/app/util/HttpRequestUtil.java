package com.wanma.app.util;

import com.alibaba.fastjson.JSON;
import com.wanma.app.service.parkinglock.constant.PlatformConfig;
import com.wanma.app.service.parkinglock.wiparking.WiParkingLockOperating;
import com.wanma.app.service.parkinglock.wiparking.WiParkingRequest;
import com.wanma.app.service.parkinglock.wiparking.WiParkingResult;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class HttpRequestUtil {
    private static Logger log = Logger.getLogger(HttpRequestUtil.class);

    public static HttpServletRequest getHttpRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return request;
    }

    public static HttpSession getHttpSession() {
        HttpSession session = getHttpRequest().getSession();
        return session;
    }

    public static String getSessionId() {
        String sessionId = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getSessionId();
        return sessionId;
    }

    public static String post(String url, Map<String, String> params) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;

        log.info("create httppost:" + url);
        HttpPost post = postForm(url, params);
        post.setHeader("ContentType",
                "application/x-www-form-urlencoded;charset=UTF-8");

        body = invoke(httpclient, post);

        httpclient.getConnectionManager().shutdown();

        return body;
    }

    public static String get(String url) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;

        log.info("create httppost:" + url);
        HttpGet get = new HttpGet(url);
        body = invoke(httpclient, get);

        httpclient.getConnectionManager().shutdown();

        return body;
    }

    private static String invoke(DefaultHttpClient httpclient,
                                 HttpUriRequest httpost) {

        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);

        return body;
    }

    private static String paseResponse(HttpResponse response) {
        log.info("get response from http server..");
        HttpEntity entity = response.getEntity();

        log.info("response status: " + response.getStatusLine());
        String charset = EntityUtils.getContentCharSet(entity);
        log.info(charset);

        String body = null;
        try {
            body = EntityUtils.toString(entity);
            log.info(body);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return body;
    }

    private static HttpResponse sendRequest(DefaultHttpClient httpclient,
                                            HttpUriRequest httpost) {
        log.info("execute post...");
        HttpResponse response = null;

        try {
            response = httpclient.execute(httpost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static HttpPost postForm(String url, Map<String, String> params) {

        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }

        try {
            log.info("set utf-8 form entity to httppost");
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return httpost;
    }

    public static Object json2Map(String jsonstring) {

        Stack<Map<String, Object>> maps = new Stack<Map<String, Object>>(); //用来表示多层的json对象
        Stack<List> lists = new Stack<List>(); //用来表示多层的list对象
        Stack<Boolean> islist = new Stack<Boolean>();//判断是不是list
        Stack<String> keys = new Stack<String>(); //用来表示多层的key

        String keytmp = null;
        Object valuetmp = null;
        StringBuilder builder = new StringBuilder();
        char[] cs = jsonstring.toCharArray();

        for (int i = 0; i < cs.length; i++) {

            switch (cs[i]) {
                case '{': //如果是{map进栈
                    maps.push(new HashMap<String, Object>());
                    islist.push(false);
                    break;
                case ':'://如果是：表示这是一个属性建，key进栈
                    keys.push(builder.toString());
                    builder = new StringBuilder();
                    break;
                case '[':
                    islist.push(true);
                    lists.push(new ArrayList());
                    break;
                case ','://这是一个分割，因为可能是简单地string的键值对，也有可能是string=map
                    //的键值对，因此valuetmp 使用object类型；
                    //如果valuetmp是null 应该是第一次，如果value不是空有可能是string，那是上一个键值对，需要重新赋值
                    //还有可能是map对象，如果是map对象就不需要了

                    boolean listis = islist.peek();

                    if (builder.length() > 0)
                        valuetmp = builder.toString();
                    builder = new StringBuilder();
                    if (!listis) {
                        keytmp = keys.pop();
                        maps.peek().put(keytmp, valuetmp);
                    } else
                        lists.peek().add(valuetmp);

                    break;
                case ']':
                    islist.pop();

                    if (builder.length() > 0)
                        valuetmp = builder.toString();
                    builder = new StringBuilder();
                    lists.peek().add(valuetmp);
                    valuetmp = lists.pop();
                    break;
                case '}':
                    islist.pop();
                    //这里做的和，做的差不多，只是需要把valuetmp=maps.pop();把map弹出栈
                    keytmp = keys.pop();

                    if (builder.length() > 0)
                        valuetmp = builder.toString();

                    builder = new StringBuilder();
                    maps.peek().put(keytmp, valuetmp);
                    valuetmp = maps.pop();
                    break;
                default:
                    builder.append(cs[i]);
                    break;
            }

        }
        return valuetmp;
    }

    public static void main(String[] args) {
        String url = "/zhidianzhuangs/app/controLock/controLocks.do";

        WiParkingResult result = post(url, "808", WiParkingLockOperating.LOWER_LOCK);
        System.out.println(JSON.toJSON(result));
    }

    private static WiParkingResult post(String url, String lockCode, String operating) {
        WiParkingResult result = null;
        url = PlatformConfig.WIPARKING_PLATFORM_URL + url;

        Map<String, String> map = new HashMap<>(1);
        map.put("info", JSON.toJSONString(WiParkingRequest.valueOf(lockCode, operating)));

        try {
            String requestResult = HttpRequestUtil.post(url, map);

            result = JSON.parseObject(requestResult, WiParkingResult.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
