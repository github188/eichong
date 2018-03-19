package com.echong.model;

/**
 * Created by zangyaoyi on 2016/12/29.
 */
public class Result {

    private String ret = "0";
    private String msg = "success";

    public Result() {

    }

    public Result(String ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
