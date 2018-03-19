package com.echong.dto;

import com.alibaba.fastjson.JSON;

/**
 * 发送充电实时数据到e充网
 * Created by zangyaoyi on 2016/12/30.
 */
public class PileChargeRealTime {
    private Long session_id; //此次操作的 session_id(可作为交易流水号)
    private String pile_code;//充电桩编码
    private Integer inter_no;//电桩上的接口(1 表示 A 口, 2 表示 B 口)
    private Integer user_id;//用户 ID,后续其它数据上报时可能需要该 user_id
    private Float cur_elect;//当前已经充电的电量
    private Float cur_money;//当前总消费金额(电费＋服务费)
    private Float cur_elect_money;//当前充电电费金额
    private Float cur_service_money;//当前服务费金额
    private Integer cur_time;//当前已充电时间，单位分钟
    private Integer soc;//当前的 soc（汽车电量的百分比，范围 0-100）
    private Integer stop;//1:未停机 2:停机
    private Integer stop_reason;//停机原因,1: 故障 2 充满 3 刷卡 4 其他，未停机时传 4
    private Integer time;//上报时间（秒格式 Unix 时间戳）

    public static PileChargeRealTime getInstance() {
        String s = "{\"pile_code\":\"1110108217001001\",\"inter_no\":1,\"user_id\":35829,\"session_id\":683121,\"cur_elect\":13.69,\"cur_money\":20.46,\"stop\":1,\"soc\":97,\"cur_time\":51,\"stop_reason\":0,\"time\":1480586288,\"cur_elect_money\":0,\"cur_service_money\":0}";
        return JSON.parseObject(s, PileChargeRealTime.class);
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

    public Float getCur_elect() {
        return cur_elect;
    }

    public void setCur_elect(Float cur_elect) {
        this.cur_elect = cur_elect;
    }

    public Float getCur_money() {
        return cur_money;
    }

    public void setCur_money(Float cur_money) {
        this.cur_money = cur_money;
    }

    public Float getCur_elect_money() {
        return cur_elect_money;
    }

    public void setCur_elect_money(Float cur_elect_money) {
        this.cur_elect_money = cur_elect_money;
    }

    public Float getCur_service_money() {
        return cur_service_money;
    }

    public void setCur_service_money(Float cur_service_money) {
        this.cur_service_money = cur_service_money;
    }

    public Integer getCur_time() {
        return cur_time;
    }

    public void setCur_time(Integer cur_time) {
        this.cur_time = cur_time;
    }

    public Integer getSoc() {
        return soc;
    }

    public void setSoc(Integer soc) {
        this.soc = soc;
    }

    public Integer getStop() {
        return stop;
    }

    public void setStop(Integer stop) {
        this.stop = stop;
    }

    public Integer getStop_reason() {
        return stop_reason;
    }

    public void setStop_reason(Integer stop_reason) {
        this.stop_reason = stop_reason;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
