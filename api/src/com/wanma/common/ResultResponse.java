package com.wanma.common;

import com.alibaba.fastjson.JSON;

/**
 * Created by Haner on 2015/3/30.
 * 结果集响应
 */
public class ResultResponse<T> extends Response<T> {

    protected String code;
    protected String msg;
    protected T data;

    public ResultResponse() {
        this.code = SUCCESS;
        this.msg = SUCCESS_MSG;
    }

    public ResultResponse(T data) {
        this.code = SUCCESS;
        this.msg = SUCCESS_MSG;
        this.data = data;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
