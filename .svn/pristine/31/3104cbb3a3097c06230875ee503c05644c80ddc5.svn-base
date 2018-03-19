package com.wanma.web.support.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wanma.web.support.utils.HttpsUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by haner on 15/4/14.
 * oauth2.0 access_token model
 */
public abstract class BaseAccessToken {

    public static String WEIXIN_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    public static String SINA_ACCESS_TOKEN_URL = "https://api.weibo.com/oauth2/access_token?client_id=APPID&client_secret=SECRET&grant_type=authorization_code&redirect_uri=REDIRECT_URL&code=CODE";
    public static String QQ_ACCESS_TOKEN_URL = "";


    public abstract String getAccess_token();
    public abstract Long getExpires_in();
    public abstract String getRefresh_token();

    /**
     * API 获取数据
     * @param apiurl
     * @return
     */
    public JSONObject getPlatformInfoByAPI(String apiurl){
        try {
            return JSON.parseObject(HttpsUtil.sendPostRequest(apiurl));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    };

}
