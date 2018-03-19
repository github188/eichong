package com.bluemobi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bluemobi.constant.UserConstant;


@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "USER")
public class User extends GameObject {

    public static final long serialVersionUID = 1L;

    @Id //主键
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;// 主键
    @Column(length = 64)
    private String username;//用户名
    @Column(length = 32)
    private String password;//密码
    @Column
    private Integer createTime;// 创建时间
    @Column
    private Integer status;//账号状态 0:未激活 1:正常 2:暂时冻结 3:永久冻结
    @Column
    private Integer canLoginTime;//可登陆时间 踢下线的时间设置
    @Column
    private Integer activateTime;//账号激活日期
    @Column
    private Integer createSource;//创建用户来源
    @Column
    private Integer type;//玩家类型 1、真实玩家  2、数据库机器人  3、内存npc
    @Transient
    private Integer loginSource;//登陆来源
    @Transient
	private Integer gateId;//用户登陆的网关

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        if (status == null) {
            return UserConstant.NORMAL;
        }
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCanLoginTime() {
        if (canLoginTime == null) return 0;
        return canLoginTime;
    }

    public void setCanLoginTime(Integer canLoginTime) {
        this.canLoginTime = canLoginTime;
    }

    public Integer getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Integer activateTime) {
        this.activateTime = activateTime;
    }

    public Integer getGateId() {
		return gateId;
	}

	public void setGateId(Integer gateId) {
		this.gateId = gateId;
	}

	/**
     * 创建用户来源
     */
    public Integer getCreateSource() {
        if (createSource == null) return 0;
        return createSource;
    }

    /**
     * 创建用户来源
     */
    public void setCreateSource(Integer createSource) {
        this.createSource = createSource;
    }

    /**
     * 登陆来源
     */
    public Integer getLoginSource() {
        if (loginSource == null) {
            return createSource;
        }
        return loginSource;
    }

    /**
     * 登陆来源
     */
    public void setLoginSource(Integer loginSource) {
        this.loginSource = loginSource;
    }

    /**
     * 玩家类型 1、真实玩家  2、数据库机器人  3、内存npc
     */
    public Integer getType() {
        if (type == null) return 0;
        return type;
    }

    /**
     * 玩家类型 1、真实玩家  2、数据库机器人  3、内存npc
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof User) {
            User user = (User) obj;
            if (this.getId().equals(user.getId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.id;
    }

}
