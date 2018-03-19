package com.bluemobi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
 * 玩家登录明细表
 * @author haojian
 * Jul 8, 2013 1:07:00 PM
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "GM_LOG_LOGIN_DETAIL")
public class GmLogLoginDetail extends GameObject {

	public static final long serialVersionUID = 1L;
	
	@Id //主键
	@GeneratedValue(strategy=GenerationType.AUTO) //默认自动增长
	private Integer id;// 主键
	@Column
	private Integer playerId;//玩家id
	@Column
	private Integer playerType;//1、新用户(当天创建角色的用户) 2、老用户(前一天或者更早创建角色的用户)
	@Column
	private Integer type;//1、登入 2、登出
	@Column
	private Integer source;//玩家来源（使用哪个平台的客户端登陆）
	@Column
	private Integer onlineTime;//在线时长(秒) (登出时候记录)
	@Column
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
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(Integer onlineTime) {
		this.onlineTime = onlineTime;
	}
	public Integer getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Integer recordTime) {
		this.recordTime = recordTime;
	}
	public Integer getPlayerType() {
		return playerType;
	}
	public void setPlayerType(Integer playerType) {
		this.playerType = playerType;
	}
	
	
	
}
