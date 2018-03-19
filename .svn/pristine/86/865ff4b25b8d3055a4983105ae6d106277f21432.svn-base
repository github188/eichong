package com.echong.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by zangyaoyi on 2016/12/30.
 */
public class Bill {
    private String pile_code;// 充电桩编码
    private Long session_id;// 此次充电启动时对应的 session_id
    private Integer user_id;// 用户 ID(调用启动/停止充电时传递的 user_id)
    private Float money;// 本次充电消费总金额（电费+服务费）
    private Float elect_money;// 本次充电电费金额
    private Float service_money;// 本次充电服务费金额
    private Float elect;//充电电量
    private Float start_elect;// 开始充电电量
    private Float end_elect;// 结束充电电量
    private Float cusp_elect;// 尖阶段电量
    private Float cusp_elect_price;// 尖电价价格
    private Float cusp_service_price;// 尖服务费单价
    private Float cusp_money;// 尖总金额
    private Float cusp_elect_money;// 尖充电金额
    private Float cusp_service_money;// 尖服务费金额
    private Float peak_elect;// 峰阶段电量
    private Float peak_elect_price;// 峰电价价格
    private Float peak_service_price;// 峰服务费单价
    private Float peak_money;// 峰总金额
    private Float peak_elect_money;// 峰充电金额
    private Float peak_service_money;// 峰服务费金额
    private Float flat_elect;// 平阶段电量
    private Float flat_elect_price;// 平阶段电价
    private Float flat_service_price;// 平阶段服务费单价
    private Float flat_money;// 平总金额
    private Float flat_elect_money;// 平充电金额
    private Float flat_service_money;// 平服务费金额
    private Float valley_elect;// 谷阶段电量
    private Float valley_elect_price;// 谷阶段电价
    private Float valley_service_price;// 谷阶段服务费单价
    private Float valley_money;// 谷总金额
    private Float valley_elect_money;// 谷充电金额
    private Float valley_service_money;// 谷服务费金额
    private Integer start_time;// 充电开始时间（秒格式 Unix 时间戳）
    private Integer end_time;// 充电结束时间（秒格式 Unix 时间戳）
    private Integer stop_model;// 停止时充电模式，1 表示恒压，2 表示恒流
    private Integer stop_reason;// 停止充电原因,1: 故障 2 充满 3 刷卡 4 其他收到 e充网的停止请求而停止则传 4
    private Integer soc;// 当前的 soc（汽车电量的百分比，范围 0-100）
    private Integer time;// 订单创建时间（秒格式 Unix 时间戳）

    public static Bill getInstance() {
        String s = "{\"pile_code\":\"1110108217001001\",\"session_id\":683222,\"user_id\":48521,\"money\":40.6,\"elect_money\":20.3,\"service_money\":20.3,\"elect\":22.51,\"start_elect\":70359.5,\"end_elect\":70382,\"cusp_elect\":0,\"cusp_elect_price\":1.0044,\"cusp_service_price\":0.8,\"cusp_money\":0,\"cusp_elect_money\":0,\"cusp_service_money\":0,\"peak_elect\":22.51,\"peak_elect_price\":1.0044,\"peak_service_price\":0.8,\"peak_money\":0,\"peak_elect_money\":22.6,\"peak_service_money\":18,\"flat_elect\":0,\"flat_elect_price\":0.6950000000000001,\"flat_service_price\":0.8,\"flat_money\":0,\"flat_elect_money\":0,\"flat_service_money\":0,\"valley_elect\":0,\"valley_elect_price\":0.3946,\"valley_service_price\":0.8,\"valley_money\":0,\"valley_elect_money\":0,\"valley_service_money\":0,\"start_time\":1480588539,\"end_time\":1480593339,\"stop_model\":2,\"stop_reason\":0,\"soc\":88,\"time\":1480593334}";
        return JSON.parseObject(s, Bill.class);
    }

    public String getPile_code() {
        return pile_code;
    }

    public void setPile_code(String pile_code) {
        this.pile_code = pile_code;
    }

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Float getElect_money() {
        return elect_money;
    }

    public void setElect_money(Float elect_money) {
        this.elect_money = elect_money;
    }

    public Float getService_money() {
        return service_money;
    }

    public void setService_money(Float service_money) {
        this.service_money = service_money;
    }

    public Float getElect() {
        return elect;
    }

    public void setElect(Float elect) {
        this.elect = elect;
    }

    public Float getStart_elect() {
        return start_elect;
    }

    public void setStart_elect(Float start_elect) {
        this.start_elect = start_elect;
    }

    public Float getEnd_elect() {
        return end_elect;
    }

    public void setEnd_elect(Float end_elect) {
        this.end_elect = end_elect;
    }

    public Float getCusp_elect() {
        return cusp_elect;
    }

    public void setCusp_elect(Float cusp_elect) {
        this.cusp_elect = cusp_elect;
    }

    public Float getCusp_elect_price() {
        return cusp_elect_price;
    }

    public void setCusp_elect_price(Float cusp_elect_price) {
        this.cusp_elect_price = cusp_elect_price;
    }

    public Float getCusp_service_price() {
        return cusp_service_price;
    }

    public void setCusp_service_price(Float cusp_service_price) {
        this.cusp_service_price = cusp_service_price;
    }

    public Float getCusp_money() {
        return cusp_money;
    }

    public void setCusp_money(Float cusp_money) {
        this.cusp_money = cusp_money;
    }

    public Float getCusp_elect_money() {
        return cusp_elect_money;
    }

    public void setCusp_elect_money(Float cusp_elect_money) {
        this.cusp_elect_money = cusp_elect_money;
    }

    public Float getCusp_service_money() {
        return cusp_service_money;
    }

    public void setCusp_service_money(Float cusp_service_money) {
        this.cusp_service_money = cusp_service_money;
    }

    public Float getPeak_elect() {
        return peak_elect;
    }

    public void setPeak_elect(Float peak_elect) {
        this.peak_elect = peak_elect;
    }

    public Float getPeak_elect_price() {
        return peak_elect_price;
    }

    public void setPeak_elect_price(Float peak_elect_price) {
        this.peak_elect_price = peak_elect_price;
    }

    public Float getPeak_service_price() {
        return peak_service_price;
    }

    public void setPeak_service_price(Float peak_service_price) {
        this.peak_service_price = peak_service_price;
    }

    public Float getPeak_money() {
        return peak_money;
    }

    public void setPeak_money(Float peak_money) {
        this.peak_money = peak_money;
    }

    public Float getPeak_elect_money() {
        return peak_elect_money;
    }

    public void setPeak_elect_money(Float peak_elect_money) {
        this.peak_elect_money = peak_elect_money;
    }

    public Float getPeak_service_money() {
        return peak_service_money;
    }

    public void setPeak_service_money(Float peak_service_money) {
        this.peak_service_money = peak_service_money;
    }

    public Float getFlat_elect() {
        return flat_elect;
    }

    public void setFlat_elect(Float flat_elect) {
        this.flat_elect = flat_elect;
    }

    public Float getFlat_elect_price() {
        return flat_elect_price;
    }

    public void setFlat_elect_price(Float flat_elect_price) {
        this.flat_elect_price = flat_elect_price;
    }

    public Float getFlat_service_price() {
        return flat_service_price;
    }

    public void setFlat_service_price(Float flat_service_price) {
        this.flat_service_price = flat_service_price;
    }

    public Float getFlat_money() {
        return flat_money;
    }

    public void setFlat_money(Float flat_money) {
        this.flat_money = flat_money;
    }

    public Float getFlat_elect_money() {
        return flat_elect_money;
    }

    public void setFlat_elect_money(Float flat_elect_money) {
        this.flat_elect_money = flat_elect_money;
    }

    public Float getFlat_service_money() {
        return flat_service_money;
    }

    public void setFlat_service_money(Float flat_service_money) {
        this.flat_service_money = flat_service_money;
    }

    public Float getValley_elect() {
        return valley_elect;
    }

    public void setValley_elect(Float valley_elect) {
        this.valley_elect = valley_elect;
    }

    public Float getValley_elect_price() {
        return valley_elect_price;
    }

    public void setValley_elect_price(Float valley_elect_price) {
        this.valley_elect_price = valley_elect_price;
    }

    public Float getValley_service_price() {
        return valley_service_price;
    }

    public void setValley_service_price(Float valley_service_price) {
        this.valley_service_price = valley_service_price;
    }

    public Float getValley_money() {
        return valley_money;
    }

    public void setValley_money(Float valley_money) {
        this.valley_money = valley_money;
    }

    public Float getValley_elect_money() {
        return valley_elect_money;
    }

    public void setValley_elect_money(Float valley_elect_money) {
        this.valley_elect_money = valley_elect_money;
    }

    public Float getValley_service_money() {
        return valley_service_money;
    }

    public void setValley_service_money(Float valley_service_money) {
        this.valley_service_money = valley_service_money;
    }

    public Integer getStart_time() {
        return start_time;
    }

    public void setStart_time(Integer start_time) {
        this.start_time = start_time;
    }

    public Integer getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Integer end_time) {
        this.end_time = end_time;
    }

    public Integer getStop_model() {
        return stop_model;
    }

    public void setStop_model(Integer stop_model) {
        this.stop_model = stop_model;
    }

    public Integer getStop_reason() {
        return stop_reason;
    }

    public void setStop_reason(Integer stop_reason) {
        this.stop_reason = stop_reason;
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
