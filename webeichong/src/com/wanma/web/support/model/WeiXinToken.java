package com.wanma.web.support.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wanma.web.support.utils.HttpsUtil;

import java.io.IOException;

/**
 * Created by haner on 15/4/14.
 * 微信token
 */
public class WeiXinToken extends BaseAccessToken {

    private String access_token; // token
    private Long expires_in; // 有效期
    private String refresh_token;//用于刷新token

    private String openid;
    private String scope;
    private String unionid;

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

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
