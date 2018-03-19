package com.wanma.web.support.model;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by haner on 15/4/14.
 */
public class SinaAccessToken extends BaseAccessToken {

    private String access_token; // token
    private Long expires_in; // 有效期
    private String remind_in;//用于刷新token
    private String refresh_token;//用于刷新token


    @Override
    public String getAccess_token() {
        return access_token;
    }

    @Override
    public Long getExpires_in() {
        return expires_in;
    }

    @Override
    public String getRefresh_token() {
        return refresh_token;
    }


    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public String getRemind_in() {
        return remind_in;
    }

    public void setRemind_in(String remind_in) {
        this.remind_in = remind_in;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    @Override
    public String toString() {
        return "SinaAccessToken{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", remind_in='" + remind_in + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                '}';
    }
}
