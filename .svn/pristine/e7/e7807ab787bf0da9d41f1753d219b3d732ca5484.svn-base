package com.bluemobi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

/**
 * 产出和消耗记录
 *
 * @author haojian
 *         Jul 9, 2013 1:13:43 PM
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "GM_LOG_OUTPUT_AND_CONSUME")
public class GmLogOutputAndConsume extends GameObject {

    public static final long serialVersionUID = 1L;

    @Id //主键
    @GeneratedValue(strategy = GenerationType.AUTO) //默认自动增长
    private Integer id;// 主键
    @Column
    @Index(name = "playerId")
    private Integer playerId;//玩家id
    @Column
    @Index(name = "index_type")
    private Integer type;//1、产出 2、消耗
    @Column
    @Index(name = "index_event")
    private Integer eventType;//事件类型：1、商城购买 2、系统奖励 3、关卡掉落
    @Column
    @Index(name = "index_no")
    private Integer no;//产出物品编号 ： 钻石8001， 金钱8002，体力8003， 精力8004
    @Column
    private Integer num;//产出物品数量

    @Column
    @Index(name = "index_time")
    private Integer recordTime;//记录时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Integer recordTime) {
        this.recordTime = recordTime;
    }


}
