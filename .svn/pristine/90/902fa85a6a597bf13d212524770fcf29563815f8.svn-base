package com.bluemobi.model;

import io.netty.channel.Channel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

import com.bluemobi.model.abstractModel.CacheGameObject;

/**
 * 玩家
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "PLAYER")
public class Player extends CacheGameObject {

    public static final long serialVersionUID = 1L;

    @Id //主键
    @GeneratedValue(strategy = GenerationType.AUTO) //默认自动增长
    private Integer id;// 主键
    @Column
    @Index(name = "userId")
    private Integer userId;//用户id
    @Column(length = 32, unique = true)
    private String name;//名字
    @Column
    private Integer level;//等级
    @Column
    private Integer exp;//经验、威望
    @Column
    private Integer gold;//钻石
    @Column
    private Integer money;//金钱
    @Column
    private Integer payMoney;//累计充值消耗的 现实货币
    @Column
    private Byte isFirstPay;//是否可以首冲翻倍， 0不能翻倍  1能翻倍
    @Column
    private Integer vipLevel;//vip等级
    @Column
    private Integer hp;//体力 pve消耗
    @Column
    private Integer hpRecoverTime;//上一次体力恢复时间
    @Column
    private Integer createTime;//角色创建时间
    @Column
    private Integer loginTime;//最后一次上线时间
    @Column
    private Integer logoutTime;//最后一次离线时间
    @Column
    private Integer resetTime;//最后一次更新玩家每日数据那天凌晨零点的时间,记录该时间，通过该时间来控制玩家每日数据是否能重置
    @Column
    private Integer type;//玩家类型 1、真实玩家  2、数据库机器人  3、内存npc
    @Column
    private Integer todayHpRecoverTimes;//当天已花钻石恢复体力次数 (上限可从vip表取)
    @Column
    private Integer todayBuyMoneyTimes;//当天已花钻石购买金钱次数 (上限可从vip表取)
    @Column
    private Integer nextFreeLotteryTime;//下一次免费抽奖时间
    @Column
    private String fightVerifyCode;//战斗验证码
    @Column
    private Integer reliveTimes;//副本复活次数（同一时间只会有一个进行，所以放在player中）
    @Column
    private Integer hotGoodsRefreshTimes;//热销商品刷新次数
    @Column
    private Integer normalGoodsRefreshTimes;//普通商品刷新次数
    @Column
    private Integer vipGoodsRefreshTimes;//VIP商品刷新次数
    @Column
    private Integer nextNormalShopRefreshTime;//普通商店下回刷新时间（秒）
    @Column
    private Integer nextVipShopRefreshTime;//VIP商店下回刷新时间（秒）
    @Column
    private Integer nextHotShopRefreshTime;//热销商店下回刷新时间（秒）
    @Column
    private Integer endlessModeChallengeTimes;//无尽模式挑战次数
    @Column
    private Integer endlessModeFloor;//无尽模式当前层数

    @Column
    private Integer endlessModePoint;//无尽模式累计积分
    
    @Column
    private Integer bagNumNo;//背部格子编号
    
    @Column
	private Integer guildStep;//新手步骤
    
    //********************运营活动数据***************************//
    @Column
	private Integer continueLoginTimes;//连续登录次数
	@Column
	private Integer continueLoginRewardStatus; //连续登录领奖状态  0 未领奖  1已领奖
	@Column
    private Integer roomToNum;//多人协作战斗每日挑战次数
    
    /**
     * ***********************临时属性，不保存到数据库********************
     */

    @Transient
    private int msgEventType;//玩家当前操作的消息事件类型  msgEventType = msgName*100 + msgType;
    @Transient
    private transient Channel channel;//玩家的socket连接
    @Transient
    private User user;//玩家的User对象
   

   

    public Integer getEndlessModePoint() {
        return endlessModePoint == null ? endlessModePoint = 0 : endlessModePoint;
    }

    public void setEndlessModePoint(Integer endlessModePoint) {
        this.endlessModePoint = endlessModePoint;
    }
    

    public Integer getBagNumNo() {
		return bagNumNo == null ? bagNumNo = 0 : bagNumNo;
	}

	public void setBagNumNo(Integer bagNumNo) {
		this.bagNumNo = bagNumNo;
	}

	public String getFightVerifyCode() {
        return fightVerifyCode;
    }

    public void setFightVerifyCode(String fightVerifyCode) {
        this.fightVerifyCode = fightVerifyCode;
    }

    public Integer getNextFreeLotteryTime() {
        return nextFreeLotteryTime;
    }

    public void setNextFreeLotteryTime(Integer nextFreeLotteryTime) {
        this.nextFreeLotteryTime = nextFreeLotteryTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        if (level == null) return 0;
        return level;
    }

    public Integer getHotGoodsRefreshTimes() {
        if (hotGoodsRefreshTimes == null) {
            hotGoodsRefreshTimes = 0;

        }
        return hotGoodsRefreshTimes;
    }

    public void setHotGoodsRefreshTimes(Integer hotGoodsRefreshTimes) {
        this.hotGoodsRefreshTimes = hotGoodsRefreshTimes;
    }

    public Integer getNormalGoodsRefreshTimes() {
        if (normalGoodsRefreshTimes == null) {
            normalGoodsRefreshTimes = 0;
        }
        return normalGoodsRefreshTimes;
    }

    public void setNormalGoodsRefreshTimes(Integer normalGoodsRefreshTimes) {
        this.normalGoodsRefreshTimes = normalGoodsRefreshTimes;
    }

    public Integer getVipGoodsRefreshTimes() {
        if (vipGoodsRefreshTimes == null) {
            vipGoodsRefreshTimes = 0;
        }
        return vipGoodsRefreshTimes;
    }

    public void setVipGoodsRefreshTimes(Integer vipGoodsRefreshTimes) {
        this.vipGoodsRefreshTimes = vipGoodsRefreshTimes;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getReliveTimes() {
        if (reliveTimes == null) {
            reliveTimes = 0;
        }
        return reliveTimes;
    }

    public void setReliveTimes(Integer reliveTimes) {
        this.reliveTimes = reliveTimes;
    }

    public Integer getExp() {
        if (exp == null) return 0;
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getGold() {
        if (gold == null) return 0;
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getMoney() {
        if (money == null) return 0;
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getPayMoney() {
        if (payMoney == null) {
            payMoney = 0;
        }
        return payMoney;
    }

    public void setPayMoney(Integer payMoney) {
        this.payMoney = payMoney;
    }

    public Byte getIsFirstPay() {
        if (isFirstPay == null) return 0;
        return isFirstPay;
    }

    public void setIsFirstPay(Byte isFirstPay) {
        this.isFirstPay = isFirstPay;
    }

    public Integer getVipLevel() {
        if (vipLevel == null) return 0;
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Integer getHp() {
        return hp == null ? hp = 0 : hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getHpRecoverTime() {
        if (hpRecoverTime == null) return 0;
        return hpRecoverTime;
    }

    public int getEndlessModeChallengeTimes() {
        return endlessModeChallengeTimes == null ? endlessModeChallengeTimes = 0 : endlessModeChallengeTimes;
    }

    public void setEndlessModeChallengeTimes(int endlessModeChallengeTimes) {
        this.endlessModeChallengeTimes = endlessModeChallengeTimes;
    }

    public void setHpRecoverTime(Integer hpRecoverTime) {
        this.hpRecoverTime = hpRecoverTime;
    }

    public Integer getCreateTime() {
        if (createTime == null) return 0;
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getLoginTime() {
        if (loginTime == null) return 0;
        return loginTime;
    }

    public void setLoginTime(Integer loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getLogoutTime() {
        if (logoutTime == null) return 0;
        return logoutTime;
    }

    public void setLogoutTime(Integer logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Integer getResetTime() {
        if (resetTime == null) return 0;
        return resetTime;
    }

    public void setResetTime(Integer resetTime) {
        this.resetTime = resetTime;
    }

    public Integer getType() {
        if (type == null) return 1;
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public int getMsgEventType() {
        return msgEventType;
    }

    public void setMsgEventType(int msgEventType) {
        this.msgEventType = msgEventType;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getNextNormalShopRefreshTime() {
        return nextNormalShopRefreshTime == null ? nextNormalShopRefreshTime = 0 : nextNormalShopRefreshTime;
    }

    public void setNextNormalShopRefreshTime(Integer nextNormalShopRefreshTime) {
        this.nextNormalShopRefreshTime = nextNormalShopRefreshTime;
    }

    public Integer getNextVipShopRefreshTime() {
        return nextVipShopRefreshTime == null ? nextVipShopRefreshTime = 0 : nextVipShopRefreshTime;
    }

    public void setNextVipShopRefreshTime(Integer nextVipShopRefreshTime) {
        this.nextVipShopRefreshTime = nextVipShopRefreshTime;
    }

    public Integer getNextHotShopRefreshTime() {
        return nextHotShopRefreshTime == null ? nextHotShopRefreshTime = 0 : nextHotShopRefreshTime;
    }

    public void setNextHotShopRefreshTime(Integer nextHotShopRefreshTime) {
        this.nextHotShopRefreshTime = nextHotShopRefreshTime;
    }

    public int getEndlessModeFloor() {
        return endlessModeFloor == null ? endlessModeFloor = 0 : endlessModeFloor;
    }

    public void setEndlessModeFloor(int endlessModeFloor) {
        this.endlessModeFloor = endlessModeFloor;
    }

    /**
     * 当天已花钻石恢复体力次数 (上限可从vip表取)
     */
    public Integer getTodayHpRecoverTimes() {
        if (todayHpRecoverTimes == null) {
            return 0;
        }
        return todayHpRecoverTimes;
    }

    public Integer getTodayBuyMoneyTimes() {
        if (todayBuyMoneyTimes == null) {
            todayBuyMoneyTimes = 0;
        }
        return todayBuyMoneyTimes;
    }

    public void setTodayBuyMoneyTimes(Integer todayBuyMoneyTimes) {
        this.todayBuyMoneyTimes = todayBuyMoneyTimes;
    }

    /**
     * 当天已花钻石恢复体力次数 (上限可从vip表取)
     */
    public void setTodayHpRecoverTimes(Integer todayHpRecoverTimes) {
        this.todayHpRecoverTimes = todayHpRecoverTimes;
    }

    public int hashCode() {
        return this.id;
    }
    

  

	public Integer getRoomToNum() {
		return roomToNum == null ? roomToNum = 0 : roomToNum;
	}

	public void setRoomToNum(Integer roomToNum) {
		this.roomToNum = roomToNum;
	}

	/**新手引导步骤*/
	public Integer getGuildStep() {
		if(guildStep==null)return 0;
		return guildStep;
	}
	/**新手引导步骤*/
	public void setGuildStep(Integer guildStep) {
		this.guildStep = guildStep;
	}

	/**连续登录次数*/
	public Integer getContinueLoginTimes() {
		if(continueLoginTimes==null)return 0;
		return continueLoginTimes;
	}
	/**连续登录次数*/
	public void setContinueLoginTimes(Integer continueLoginTimes) {
		this.continueLoginTimes = continueLoginTimes;
	}
	/**连续登录领奖状态 0 未领奖  1已领奖*/
	public Integer getContinueLoginRewardStatus() {
		if(continueLoginRewardStatus==null)return 0;
		return continueLoginRewardStatus;
	}
    /**
     * 判断玩家是否在线
     */
    public boolean isOnline() {
        return this.channel != null;
    }

}
