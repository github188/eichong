package com.wanma.web.support.model;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by haner on 15/4/14.
 */
public class QQAccessToken extends BaseAccessToken {

    private String access_token; // token
    private Long expires_in; // 有效期
    private String refresh_token;//用于刷新token

    @Override
    public String getAccess_token() {
        return null;
    }

    @Override
    public Long getExpires_in() {
        return null;
    }

    @Override
    public String getRefresh_token() {
        return null;
    }

}
