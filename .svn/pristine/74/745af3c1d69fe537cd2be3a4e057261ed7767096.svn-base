package com.wanma.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.wanma.support.common.BasicListAndMutiFile;


public class TblElectricpile extends BasicListAndMutiFile  implements Serializable {

	private static final long serialVersionUID = 3050775264484769401L;
	private Integer pkElectricpile; // 主键
	private String elpiElectricpilename; // 电桩名称
	private String elpiElectricpilecode; // 桩体编号
	private String elpiElectricpileaddress; // 地址
	private BigDecimal elpiLongitude; // 电桩地址经度
	private BigDecimal elpiLatitude; // 电桩地址维度
	private Integer elpiPowernumber; // 电桩枪口数量
	private String elpiAreacode; // 电桩所属区域代码(根据省、市、区表关联)
	private Integer elpiState; // 电桩状态（0-草稿 5-提交审核 3-已驳回 10-离线15-上线）
	private String elpiRejectionreason; // 审核驳回原因
	private Integer elpiType; // 电桩类型，配置参数内容的ID （落地式，壁挂式，拉杆式，便携式）
	private Integer elpiPoweruser; // 电桩用途，配置参数内容的ID（电动车，电动自行车，多功能（电动车、电动自行车、手机USB））
	private Integer elpiChargingmode; // 电桩充电方式，配置参数内容的ID （直流充电桩，交流充电桩）
	private Integer elpiPowersize; // 电桩额定功率，配置参数内容的ID（3.5kw，7kw，20kw，50kw，75kw）
	private Integer elpiPowerinterface; // 电桩接口方式，配置参数内容的ID（国标、欧标、美标）7国标，19美标，20欧标
	private Integer elpiMaker; // 电桩制造商，配置参数内容的ID（万马新能源，南京循道，北京三优，上海普天）
	private String elpiImage; // 电桩图片
	private String elpiDetailimage; // 电桩详情图片
	private BigDecimal elpiOutputvoltage; // 电桩额定输出电压
	private BigDecimal elpiInputvoltage; // 电桩额定输入电压
	private BigDecimal elpiOutputcurrent; // 电桩额定输出电流
	private BigDecimal elpiInputcurrent; // 电桩额定输入电流
	private Integer elpiUsertype; // 电桩所属用户类型
	private String elpiUserid; // 电桩所属用户ID
	private String elpiCreatedate; // 创建时间
	private String elpiUpdatedate; // 修改时间
	private String elpiRemark; // 备注
	private String elpiCarid; // 电桩适用车型，根据电动车品牌类型详情表关联，id用逗号隔开
	private Integer elpiBinding; // 电桩是否绑定电站（0-未绑定 1-已绑定）
	private Integer elpiIsappoint; // 电桩是否支持预约（0不支持，1支持）
	private Integer elpiPaystyle; // 电桩充电支付方式，配置参数内容的ID（刷卡方式，手机方式，人工方式）
	private BigDecimal elpiMaxelectricity; //最大电流
	private String elpiPowerusername; // 充电模式用途名称
	private String elpiChargingmodename; // 充电模式名称
	private String elpiPowerinterfacename;//电桩接口方式名称
	private Integer elpiRelevancepowerstation;//所属电站
	private String elpiTell;// 联系电话
	private Integer elpiRateinformationid;//费率id
	private Integer commStatus;//连接状态
	private Integer epNum;//电桩在电站中的排序号
	private String  elpiUsername;//所属用户
	private String  elpiOnlinetime; // 开放时间
	private String  elpiOwnprovincecode;//所属省份code
	private String  elpiOwncitycode;//所属城市code
	private String  elpiOwncountycode;//所属地区代码code
	private String  elpiOfflinetime; // 下线时间
	private Integer elpiGateid;//GATE服务器ID
	//private Integer deleteFlag;//删除标识 父属性里已存在
	private Integer elpiOwnercompany;//桩体归属:0其他，1爱充网，2国网，3特斯拉
	private String  simMac; //SIM卡卡号
	private String  simPhoneNum; //SIM卡电话号码
	private Integer haveLedFlash;//LED灯(0:不支持;1:支持)
	private Integer haveGps;//是否有gps模块,0:没有，1:有
	private Integer pkConcentratorid;//集中器ID
	private Integer concentratorNum;//集中器内序号
	
	// 页面搜索用，不以数据库对应
	private String elpiPowersizename;//电桩额定功率
	private String epheElectricpileheadid;//枪头序列ID
	private String pkElectricpilehead;//枪头主键
	private String electricPrices;// 价格(APP电桩查找列表模式，价格排序) 1-默认模式 2-按照最优排序
	private String electricEvaluate;// 评价(APP电桩查找列表模式，评价排序) 1-默认模式 2-按照最优排序
	private String screenRadius;// //距离(APP电桩查找列表模式，搜索半径) 单位m
	private String pileHeadSum;// 电桩枪头数量
	private String elctrcState;// 枪头状态
	private String searchName; // 检索名称
	private Integer pkUserinfo;// 用户id
	private String isCollect;// 是否收藏 0未收藏 1已收藏
	private String userName;
	private String userLevel;
	private String elpiOwner;//电桩拥有者
	private String screenType; // 列表接口用，3电站电桩 4电动自行车
	private BigDecimal raInReservationRate; //预约单价
	private BigDecimal raInServiceCharge; //服务费
	
	private String distance;
	private String newElpiElectricpilecode;//新的电桩编号
	private String ChargingMode;//电桩冲动方式
	private String makerRemark;//制造厂家标示
	
	private Integer electricType;//电桩类型1：电桩2：电站
	private Integer electricPileSum;//电桩数量
	private Integer headNum;//枪头数量
	private Integer freeHeadNum;//空闲枪头数量
	private Integer headState;//枪头状态
	
	public Integer getPkElectricpile() {
		return pkElectricpile;
	}
	public void setPkElectricpile(Integer pkElectricpile) {
		this.pkElectricpile = pkElectricpile;
	}
	public String getElpiElectricpilename() {
		return elpiElectricpilename;
	}
	public void setElpiElectricpilename(String elpiElectricpilename) {
		this.elpiElectricpilename = elpiElectricpilename;
	}
	public String getElpiElectricpilecode() {
		return elpiElectricpilecode;
	}
	public void setElpiElectricpilecode(String elpiElectricpilecode) {
		this.elpiElectricpilecode = elpiElectricpilecode;
	}
	public String getElpiElectricpileaddress() {
		return elpiElectricpileaddress;
	}
	public void setElpiElectricpileaddress(String elpiElectricpileaddress) {
		this.elpiElectricpileaddress = elpiElectricpileaddress;
	}
	public BigDecimal getElpiLongitude() {
		return elpiLongitude;
	}
	public void setElpiLongitude(BigDecimal elpiLongitude) {
		this.elpiLongitude = elpiLongitude;
	}
	public BigDecimal getElpiLatitude() {
		return elpiLatitude;
	}
	public void setElpiLatitude(BigDecimal elpiLatitude) {
		this.elpiLatitude = elpiLatitude;
	}
	public Integer getElpiPowernumber() {
		return elpiPowernumber;
	}
	public void setElpiPowernumber(Integer elpiPowernumber) {
		this.elpiPowernumber = elpiPowernumber;
	}
	public String getElpiAreacode() {
		return elpiAreacode;
	}
	public void setElpiAreacode(String elpiAreacode) {
		this.elpiAreacode = elpiAreacode;
	}
	public Integer getElpiState() {
		return elpiState;
	}
	public void setElpiState(Integer elpiState) {
		this.elpiState = elpiState;
	}
	public String getElpiRejectionreason() {
		return elpiRejectionreason;
	}
	public void setElpiRejectionreason(String elpiRejectionreason) {
		this.elpiRejectionreason = elpiRejectionreason;
	}
	public Integer getElpiType() {
		return elpiType;
	}
	public void setElpiType(Integer elpiType) {
		this.elpiType = elpiType;
	}
	public Integer getElpiPoweruser() {
		return elpiPoweruser;
	}
	public void setElpiPoweruser(Integer elpiPoweruser) {
		this.elpiPoweruser = elpiPoweruser;
	}
	public Integer getElpiChargingmode() {
		return elpiChargingmode;
	}
	public void setElpiChargingmode(Integer elpiChargingmode) {
		this.elpiChargingmode = elpiChargingmode;
	}
	public Integer getElpiPowersize() {
		return elpiPowersize;
	}
	public void setElpiPowersize(Integer elpiPowersize) {
		this.elpiPowersize = elpiPowersize;
	}
	public Integer getElpiPowerinterface() {
		return elpiPowerinterface;
	}
	public void setElpiPowerinterface(Integer elpiPowerinterface) {
		this.elpiPowerinterface = elpiPowerinterface;
	}
	public Integer getElpiMaker() {
		return elpiMaker;
	}
	public void setElpiMaker(Integer elpiMaker) {
		this.elpiMaker = elpiMaker;
	}
	public String getElpiImage() {
		return elpiImage;
	}
	public void setElpiImage(String elpiImage) {
		this.elpiImage = elpiImage;
	}
	public String getElpiDetailimage() {
		return elpiDetailimage;
	}
	public void setElpiDetailimage(String elpiDetailimage) {
		this.elpiDetailimage = elpiDetailimage;
	}
	public BigDecimal getElpiOutputvoltage() {
		return elpiOutputvoltage;
	}
	public void setElpiOutputvoltage(BigDecimal elpiOutputvoltage) {
		this.elpiOutputvoltage = elpiOutputvoltage;
	}
	public BigDecimal getElpiInputvoltage() {
		return elpiInputvoltage;
	}
	public void setElpiInputvoltage(BigDecimal elpiInputvoltage) {
		this.elpiInputvoltage = elpiInputvoltage;
	}
	public BigDecimal getElpiOutputcurrent() {
		return elpiOutputcurrent;
	}
	public void setElpiOutputcurrent(BigDecimal elpiOutputcurrent) {
		this.elpiOutputcurrent = elpiOutputcurrent;
	}
	public BigDecimal getElpiInputcurrent() {
		return elpiInputcurrent;
	}
	public void setElpiInputcurrent(BigDecimal elpiInputcurrent) {
		this.elpiInputcurrent = elpiInputcurrent;
	}
	public Integer getElpiUsertype() {
		return elpiUsertype;
	}
	public void setElpiUsertype(Integer elpiUsertype) {
		this.elpiUsertype = elpiUsertype;
	}
	public String getElpiUserid() {
		return elpiUserid;
	}
	public void setElpiUserid(String elpiUserid) {
		this.elpiUserid = elpiUserid;
	}
	
	public String getElpiCreatedate() {
		return elpiCreatedate;
	}
	public void setElpiCreatedate(String elpiCreatedate) {
		this.elpiCreatedate = elpiCreatedate;
	}
	public String getElpiUpdatedate() {
		return elpiUpdatedate;
	}
	public void setElpiUpdatedate(String elpiUpdatedate) {
		this.elpiUpdatedate = elpiUpdatedate;
	}
	public String getElpiRemark() {
		return elpiRemark;
	}
	public void setElpiRemark(String elpiRemark) {
		this.elpiRemark = elpiRemark;
	}
	public String getElpiCarid() {
		return elpiCarid;
	}
	public void setElpiCarid(String elpiCarid) {
		this.elpiCarid = elpiCarid;
	}
	public Integer getElpiBinding() {
		return elpiBinding;
	}
	public void setElpiBinding(Integer elpiBinding) {
		this.elpiBinding = elpiBinding;
	}
	public Integer getElpiIsappoint() {
		return elpiIsappoint;
	}
	public void setElpiIsappoint(Integer elpiIsappoint) {
		this.elpiIsappoint = elpiIsappoint;
	}
	public Integer getElpiPaystyle() {
		return elpiPaystyle;
	}
	public void setElpiPaystyle(Integer elpiPaystyle) {
		this.elpiPaystyle = elpiPaystyle;
	}
	public BigDecimal getElpiMaxelectricity() {
		return elpiMaxelectricity;
	}
	public void setElpiMaxelectricity(BigDecimal elpiMaxelectricity) {
		this.elpiMaxelectricity = elpiMaxelectricity;
	}
	public String getElpiPowerusername() {
		return elpiPowerusername;
	}
	public void setElpiPowerusername(String elpiPowerusername) {
		this.elpiPowerusername = elpiPowerusername;
	}
	public String getElpiChargingmodename() {
		return elpiChargingmodename;
	}
	public void setElpiChargingmodename(String elpiChargingmodename) {
		this.elpiChargingmodename = elpiChargingmodename;
	}
	public String getElpiPowerinterfacename() {
		return elpiPowerinterfacename;
	}
	public void setElpiPowerinterfacename(String elpiPowerinterfacename) {
		this.elpiPowerinterfacename = elpiPowerinterfacename;
	}
	
	public Integer getElpiRelevancepowerstation() {
		return elpiRelevancepowerstation;
	}
	public void setElpiRelevancepowerstation(Integer elpiRelevancepowerstation) {
		this.elpiRelevancepowerstation = elpiRelevancepowerstation;
	}
	public String getElpiTell() {
		return elpiTell;
	}
	public void setElpiTell(String elpiTell) {
		this.elpiTell = elpiTell;
	}
	public Integer getElpiRateinformationid() {
		return elpiRateinformationid;
	}
	public void setElpiRateinformationid(Integer elpiRateinformationid) {
		this.elpiRateinformationid = elpiRateinformationid;
	}
	public Integer getCommStatus() {
		return commStatus;
	}
	public void setCommStatus(Integer commStatus) {
		this.commStatus = commStatus;
	}
	public Integer getEpNum() {
		return epNum;
	}
	public void setEpNum(Integer epNum) {
		this.epNum = epNum;
	}
	public String getElpiUsername() {
		return elpiUsername;
	}
	public void setElpiUsername(String elpiUsername) {
		this.elpiUsername = elpiUsername;
	}
	public String getElpiOnlinetime() {
		return elpiOnlinetime;
	}
	public void setElpiOnlinetime(String elpiOnlinetime) {
		this.elpiOnlinetime = elpiOnlinetime;
	}
	public String getElpiOwnprovincecode() {
		return elpiOwnprovincecode;
	}
	public void setElpiOwnprovincecode(String elpiOwnprovincecode) {
		this.elpiOwnprovincecode = elpiOwnprovincecode;
	}
	public String getElpiOwncitycode() {
		return elpiOwncitycode;
	}
	public void setElpiOwncitycode(String elpiOwncitycode) {
		this.elpiOwncitycode = elpiOwncitycode;
	}
	public String getElpiOwncountycode() {
		return elpiOwncountycode;
	}
	public void setElpiOwncountycode(String elpiOwncountycode) {
		this.elpiOwncountycode = elpiOwncountycode;
	}
	public String getElpiOfflinetime() {
		return elpiOfflinetime;
	}
	public void setElpiOfflinetime(String elpiOfflinetime) {
		this.elpiOfflinetime = elpiOfflinetime;
	}
	public Integer getElpiGateid() {
		return elpiGateid;
	}
	public void setElpiGateid(Integer elpiGateid) {
		this.elpiGateid = elpiGateid;
	}
	public Integer getElpiOwnercompany() {
		return elpiOwnercompany;
	}
	public void setElpiOwnercompany(Integer elpiOwnercompany) {
		this.elpiOwnercompany = elpiOwnercompany;
	}
	public String getSimMac() {
		return simMac;
	}
	public void setSimMac(String simMac) {
		this.simMac = simMac;
	}
	public String getSimPhoneNum() {
		return simPhoneNum;
	}
	public void setSimPhoneNum(String simPhoneNum) {
		this.simPhoneNum = simPhoneNum;
	}
	public Integer getHaveLedFlash() {
		return haveLedFlash;
	}
	public void setHaveLedFlash(Integer haveLedFlash) {
		this.haveLedFlash = haveLedFlash;
	}
	public Integer getHaveGps() {
		return haveGps;
	}
	public void setHaveGps(Integer haveGps) {
		this.haveGps = haveGps;
	}
	public Integer getPkConcentratorid() {
		return pkConcentratorid;
	}
	public void setPkConcentratorid(Integer pkConcentratorid) {
		this.pkConcentratorid = pkConcentratorid;
	}
	public Integer getConcentratorNum() {
		return concentratorNum;
	}
	public void setConcentratorNum(Integer concentratorNum) {
		this.concentratorNum = concentratorNum;
	}
	
	public String getElectricPrices() {
		return electricPrices;
	}
	public void setElectricPrices(String electricPrices) {
		this.electricPrices = electricPrices;
	}
	public String getElectricEvaluate() {
		return electricEvaluate;
	}
	public void setElectricEvaluate(String electricEvaluate) {
		this.electricEvaluate = electricEvaluate;
	}
	public String getScreenRadius() {
		return screenRadius;
	}
	public void setScreenRadius(String screenRadius) {
		this.screenRadius = screenRadius;
	}
	public String getPileHeadSum() {
		return pileHeadSum;
	}
	public void setPileHeadSum(String pileHeadSum) {
		this.pileHeadSum = pileHeadSum;
	}
	public String getElctrcState() {
		return elctrcState;
	}
	public void setElctrcState(String elctrcState) {
		this.elctrcState = elctrcState;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public Integer getPkUserinfo() {
		return pkUserinfo;
	}
	public void setPkUserinfo(Integer pkUserinfo) {
		this.pkUserinfo = pkUserinfo;
	}
	public String getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public String getElpiOwner() {
		return elpiOwner;
	}
	public void setElpiOwner(String elpiOwner) {
		this.elpiOwner = elpiOwner;
	}
	public String getScreenType() {
		return screenType;
	}
	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}
	public BigDecimal getRaInReservationRate() {
		return raInReservationRate;
	}
	public void setRaInReservationRate(BigDecimal raInReservationRate) {
		this.raInReservationRate = raInReservationRate;
	}
	public BigDecimal getRaInServiceCharge() {
		return raInServiceCharge;
	}
	public void setRaInServiceCharge(BigDecimal raInServiceCharge) {
		this.raInServiceCharge = raInServiceCharge;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getNewElpiElectricpilecode() {
		return newElpiElectricpilecode;
	}
	public void setNewElpiElectricpilecode(String newElpiElectricpilecode) {
		this.newElpiElectricpilecode = newElpiElectricpilecode;
	}
	public String getChargingMode() {
		return ChargingMode;
	}
	public void setChargingMode(String chargingMode) {
		ChargingMode = chargingMode;
	}
	public String getMakerRemark() {
		return makerRemark;
	}
	public void setMakerRemark(String makerRemark) {
		this.makerRemark = makerRemark;
	}
	
	public Integer getElectricType() {
		return electricType;
	}
	public void setElectricType(Integer electricType) {
		this.electricType = electricType;
	}
	public Integer getElectricPileSum() {
		return electricPileSum;
	}
	public void setElectricPileSum(Integer electricPileSum) {
		this.electricPileSum = electricPileSum;
	}
	public Integer getHeadNum() {
		return headNum;
	}
	public void setHeadNum(Integer headNum) {
		this.headNum = headNum;
	}
	public Integer getFreeHeadNum() {
		return freeHeadNum;
	}
	public void setFreeHeadNum(Integer freeHeadNum) {
		this.freeHeadNum = freeHeadNum;
	}
	public Integer getHeadState() {
		return headState;
	}
	public void setHeadState(Integer headState) {
		this.headState = headState;
	}
	public String getElpiPowersizename() {
		return elpiPowersizename;
	}
	public void setElpiPowersizename(String elpiPowersizename) {
		this.elpiPowersizename = elpiPowersizename;
	}
	public String getEpheElectricpileheadid() {
		return epheElectricpileheadid;
	}
	public void setEpheElectricpileheadid(String epheElectricpileheadid) {
		this.epheElectricpileheadid = epheElectricpileheadid;
	}
	public String getPkElectricpilehead() {
		return pkElectricpilehead;
	}
	public void setPkElectricpilehead(String pkElectricpilehead) {
		this.pkElectricpilehead = pkElectricpilehead;
	}
	
	
}