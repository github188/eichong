package com.wanma.web.support.model;

/**
 * Created by haner on 15/4/14.
 */
public interface API {
    String W_API_SERVER = "";
    String Q_API_SERVER = "";
    String S_API_SERVER = "https://api.weibo.com/oauth2/";

    /**
     * wechat API interface url
     */
    String W_USERINFO = W_API_SERVER + "";


    /**
     * QQ API interface url
     */
    String Q_USERINFO = Q_API_SERVER + "";


    /**
     * SINA API interface url
     */
    String S_USERINFO = S_API_SERVER + "get_token_info";
}
