package com.echong.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 发送电桩启动/停止回调到e充网
 * Created by zangyaoyi on 2016/12/30.
 */
public class Callback {
    private Long session_id; //启动/停止操作对应的 session_id
    private String pile_code;//充电桩编码
    private Integer inter_no;//电桩上的接口(1 表示 A 口, 2 表示 B 口)
    private Integer user_id;//e 充网调用启动/停止接口时传递的用户 id
    private Integer action;//1:启动 2:停止
    private Integer result;//1:操作成功 2:操作失败
    private Integer ecode;//错误码:0:无错误 1: 已经开机 2: 未开机 3: 枪未连接 4:其他错误
    private Integer soc;//当前的 soc（汽车电量的百分比，范围 0-100）
    private Integer time;

    public static Callback getInstance() {
        String s = "{\"pile_code\":\"1110108217001001\",\"inter_no\":1,\"session_id\":1903,\"user_id\":228,\"action\":1,\"result\":1,\"soc\":57,\"time\":1480508124,\"ecode\":0}";
        return JSON.parseObject(s, Callback.class);
    }

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public String getPile_code() {
        return pile_code;
    }

    public void setPile_code(String pile_code) {
        this.pile_code = pile_code;
    }

    public Integer getInter_no() {
        return inter_no;
    }

    public void setInter_no(Integer inter_no) {
        this.inter_no = inter_no;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getEcode() {
        return ecode;
    }

    public void setEcode(Integer ecode) {
        this.ecode = ecode;
    }

    public Integer getSoc() {
        return soc;
    }

    public void setSoc(Integer soc) {
        this.soc = soc;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
