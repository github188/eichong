package com.wanma.dubbox.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.wanma.dubbox.model.common.BasicListAndMutiFile;

/**
 * 
 * tbl_ElectricPile表
 * 
 * @author lhy
 * 
 */
public class TblElectricpile extends BasicListAndMutiFile  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3050775264484769401L;
	private java.lang.Integer eId; // 主键
	private java.lang.String eName; // 电桩名称
	private java.lang.String eCode; // 桩体编号
	private java.lang.String addr; // 地址
	private java.math.BigDecimal eLot; // 电桩地址经度
	private java.math.BigDecimal eLat; // 电桩地址维度
	private java.lang.Integer eHnum; // 电桩枪口数量
	private java.lang.String eAcode; // 电桩所属区域代码(根据省、市、区表关联)
	private java.lang.Integer eState; // 电桩状态（0-草稿 5-提交审核 3-已驳回 10-离线15-上线）
	private java.lang.String rejectTxt; // 审核驳回原因
	private java.lang.Integer eType; // 电桩类型，配置参数内容的ID （落地式，壁挂式，拉杆式，便携式）
	private java.lang.Integer ePuser; // 电桩用途，配置参数内容的ID（电动车，电动自行车，多功能（电动车、电动自行车、手机USB））
	private java.lang.Integer eCmode; // 电桩充电方式，配置参数内容的ID
												// （直流充电桩，交流充电桩）
	private java.lang.Integer ePsize; // 电桩额定功率，配置参数内容的ID（3.5kw，7kw，20kw，50kw，75kw）
	private java.lang.Integer ePinf; // 电桩接口方式，配置参数内容的ID（国标、欧标、美标）7国标，19美标，20欧标
	private java.lang.Integer eMaker; // 电桩制造商，配置参数内容的ID
											// （万马新能源，南京循道，北京三优，上海普天）
	private java.lang.String image; // 电桩图片
	private java.lang.String detImage; // 电桩详情图片
	private java.math.BigDecimal outV; // 电桩额定输出电压
	private java.math.BigDecimal inV; // 电桩额定输入电压
	private java.math.BigDecimal outC; // 电桩额定输出电流
	private java.math.BigDecimal inC; // 电桩额定输入电流
	private java.lang.Integer euType; // 电桩所属用户类型
	private java.lang.String eUid; // 电桩所属用户ID
	private java.util.Date createdate; // 创建时间
	private String updatedate; // 修改时间
	private java.lang.String remark; // 备注
	private java.lang.String cId; // 电桩适用车型，根据电动车品牌类型详情表关联，id用逗号隔开
	private java.lang.Integer eBind; // 电桩是否绑定电站（0-未绑定 1-已绑定）
	private java.lang.Integer isAppoint; // 电桩是否支持预约（0不支持，1支持）
	private java.lang.Integer payStyle; // 电桩充电支付方式，配置参数内容的ID
											// （刷卡方式，手机方式，人工方式）
	private java.math.BigDecimal maxE; //

	private java.lang.String CModeName; // 充电模式名称
	private java.lang.String powerUName; // 充电模式用途名称
	private java.lang.String eTell;// 联系电话
	private java.lang.String  uName;//所属用户
	private int sId;//所属电站
	private int eSt;//连接状态
	private java.lang.String  olTime; // 开放时间
	private java.lang.String  eProCode;//所属城市code
	private java.lang.String  eCityCode;//所属地区区县code
	private java.lang.String  eCountyCode;//所属地区代码code
	private String rateDate;//json格式的费率模型
	private BigDecimal jPrice; //尖时段电价
	private BigDecimal fPrice; //峰时段电价
	private BigDecimal pPrice; //平时段电价
	private BigDecimal gPrice; //谷时段电价
	
	// 页面搜索用，不以数据库对应
	private String electricPrices;// 价格(APP电桩查找列表模式，价格排序) 1-默认模式 2-按照最优排序
	private String electricEvaluate;// 评价(APP电桩查找列表模式，评价排序) 1-默认模式 2-按照最优排序
	private String screenRadius;// //距离(APP电桩查找列表模式，搜索半径) 单位m
	private String elctrcState;// 枪头状态   0空闲 3预约 6充电 9故障
	private String searchName; // 检索名称
	private int pkUserinfo;// 用户id
	private String isCollect;// 是否收藏 0未收藏 1已收藏
	private String userName;
	private String useLevel;
	private String owner;//电桩拥有者
	private String screenType; // 列表接口用，3电站电桩 4电动自行车
	private BigDecimal resRate; //预约单价
	private BigDecimal chargeS; //服务费
	private int ePindex;//电桩在电站中的排序号
	private String distance;
	private java.lang.Integer gateId;//电桩id
	private String deleteFlag;
	private String headStatus;//枪口状态


	public java.lang.Integer geteId() {
		return eId;
	}


	public void seteId(java.lang.Integer eId) {
		this.eId = eId;
	}


	public java.lang.String geteName() {
		return eName;
	}


	public void seteName(java.lang.String eName) {
		this.eName = eName;
	}


	public java.lang.String geteCode() {
		return eCode;
	}


	public void seteCode(java.lang.String eCode) {
		this.eCode = eCode;
	}


	public java.lang.String getAddr() {
		return addr;
	}


	public void setAddr(java.lang.String addr) {
		this.addr = addr;
	}


	public java.math.BigDecimal geteLot() {
		return eLot;
	}


	public void seteLit(java.math.BigDecimal eLit) {
		this.eLot = eLot;
	}


	public java.math.BigDecimal geteLat() {
		return eLat;
	}


	public void seteLat(java.math.BigDecimal eLat) {
		this.eLat = eLat;
	}


	public java.lang.Integer geteHnum() {
		return eHnum;
	}


	public void seteHnum(java.lang.Integer eHnum) {
		this.eHnum = eHnum;
	}


	public java.lang.String geteAcode() {
		return eAcode;
	}


	public void seteAcode(java.lang.String eAcode) {
		this.eAcode = eAcode;
	}


	public java.lang.Integer geteState() {
		return eState;
	}


	public void seteState(java.lang.Integer eState) {
		this.eState = eState;
	}


	public java.lang.String getRejectTxt() {
		return rejectTxt;
	}


	public void setRejectTxt(java.lang.String rejectTxt) {
		this.rejectTxt = rejectTxt;
	}


	public java.lang.Integer geteType() {
		return eType;
	}


	public void seteType(java.lang.Integer eType) {
		this.eType = eType;
	}


	public java.lang.Integer getePuser() {
		return ePuser;
	}


	public void setePuser(java.lang.Integer ePuser) {
		this.ePuser = ePuser;
	}


	public java.lang.Integer geteCmode() {
		return eCmode;
	}


	public void seteCmode(java.lang.Integer eCmode) {
		this.eCmode = eCmode;
	}


	public java.lang.Integer getePsize() {
		return ePsize;
	}


	public void setePsize(java.lang.Integer ePsize) {
		this.ePsize = ePsize;
	}


	public java.lang.Integer getePinf() {
		return ePinf;
	}


	public void setePinf(java.lang.Integer ePinf) {
		this.ePinf = ePinf;
	}


	public java.lang.Integer geteMaker() {
		return eMaker;
	}


	public void seteMaker(java.lang.Integer eMaker) {
		this.eMaker = eMaker;
	}


	public java.lang.String getImage() {
		return image;
	}


	public void setImage(java.lang.String image) {
		this.image = image;
	}


	public java.lang.String getDetImage() {
		return detImage;
	}


	public void setDetImage(java.lang.String detImage) {
		this.detImage = detImage;
	}


	public java.math.BigDecimal getOutV() {
		return outV;
	}


	public void setOutV(java.math.BigDecimal outV) {
		this.outV = outV;
	}


	public java.math.BigDecimal getInV() {
		return inV;
	}


	public void setInV(java.math.BigDecimal inV) {
		this.inV = inV;
	}


	public java.math.BigDecimal getOutC() {
		return outC;
	}


	public void setOutC(java.math.BigDecimal outC) {
		this.outC = outC;
	}


	public java.math.BigDecimal getInC() {
		return inC;
	}


	public void setInC(java.math.BigDecimal inC) {
		this.inC = inC;
	}


	public java.lang.Integer getEuType() {
		return euType;
	}


	public void setEuType(java.lang.Integer euType) {
		this.euType = euType;
	}


	public java.lang.String geteUid() {
		return eUid;
	}


	public void seteUid(java.lang.String eUid) {
		this.eUid = eUid;
	}


	public java.util.Date getCreatedate() {
		return createdate;
	}


	public void setCreatedate(java.util.Date createdate) {
		this.createdate = createdate;
	}


	public String getUpdatedate() {
		return updatedate;
	}


	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}


	public java.lang.String getRemark() {
		return remark;
	}


	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}


	public java.lang.String getcId() {
		return cId;
	}


	public void setcId(java.lang.String cId) {
		this.cId = cId;
	}


	public java.lang.Integer geteBind() {
		return eBind;
	}


	public void seteBind(java.lang.Integer eBind) {
		this.eBind = eBind;
	}


	public java.lang.Integer getIsAppoint() {
		return isAppoint;
	}


	public void setIsAppoint(java.lang.Integer isAppoint) {
		this.isAppoint = isAppoint;
	}


	public java.lang.Integer getPayStyle() {
		return payStyle;
	}


	public void setPayStyle(java.lang.Integer payStyle) {
		this.payStyle = payStyle;
	}


	public java.math.BigDecimal getMaxE() {
		return maxE;
	}


	public void setMaxE(java.math.BigDecimal maxE) {
		this.maxE = maxE;
	}


	public java.lang.String getCModeName() {
		return CModeName;
	}


	public void setCModeName(java.lang.String cModeName) {
		CModeName = cModeName;
	}


	public java.lang.String getPowerUName() {
		return powerUName;
	}


	public void setPowerUName(java.lang.String powerUName) {
		this.powerUName = powerUName;
	}


	public java.lang.String geteTell() {
		return eTell;
	}


	public void seteTell(java.lang.String eTell) {
		this.eTell = eTell;
	}


	public java.lang.String getuName() {
		return uName;
	}


	public void setuName(java.lang.String uName) {
		this.uName = uName;
	}


	public int getsId() {
		return sId;
	}


	public void setsId(int sId) {
		this.sId = sId;
	}


	public int geteSt() {
		return eSt;
	}


	public void seteSt(int eSt) {
		this.eSt = eSt;
	}


	public java.lang.String getOlTime() {
		return olTime;
	}


	public void setOlTime(java.lang.String olTime) {
		this.olTime = olTime;
	}


	public java.lang.String geteProCode() {
		return eProCode;
	}


	public void seteProCode(java.lang.String eProCode) {
		this.eProCode = eProCode;
	}


	public java.lang.String geteCityCode() {
		return eCityCode;
	}


	public void seteCityCode(java.lang.String eCityCode) {
		this.eCityCode = eCityCode;
	}


	public java.lang.String geteCountyCode() {
		return eCountyCode;
	}


	public void seteCountyCode(java.lang.String eCountyCode) {
		this.eCountyCode = eCountyCode;
	}


	public String getRateDate() {
		return rateDate;
	}


	public void setRateDate(String rateDate) {
		this.rateDate = rateDate;
	}


	public BigDecimal getjPrice() {
		return jPrice;
	}


	public void setjPrice(BigDecimal jPrice) {
		this.jPrice = jPrice;
	}


	public BigDecimal getfPrice() {
		return fPrice;
	}


	public void setfPrice(BigDecimal fPrice) {
		this.fPrice = fPrice;
	}


	public BigDecimal getpPrice() {
		return pPrice;
	}


	public void setpPrice(BigDecimal pPrice) {
		this.pPrice = pPrice;
	}


	public BigDecimal getgPrice() {
		return gPrice;
	}


	public void setgPrice(BigDecimal gPrice) {
		this.gPrice = gPrice;
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


	public int getPkUserinfo() {
		return pkUserinfo;
	}


	public void setPkUserinfo(int pkUserinfo) {
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


	public String getUseLevel() {
		return useLevel;
	}


	public void setUseLevel(String useLevel) {
		this.useLevel = useLevel;
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public String getScreenType() {
		return screenType;
	}


	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}


	public BigDecimal getResRate() {
		return resRate;
	}


	public void setResRate(BigDecimal resRate) {
		this.resRate = resRate;
	}


	public BigDecimal getChargeS() {
		return chargeS;
	}


	public void setChargeS(BigDecimal chargeS) {
		this.chargeS = chargeS;
	}


	public int getePindex() {
		return ePindex;
	}


	public void setePindex(int ePindex) {
		this.ePindex = ePindex;
	}


	public String getDistance() {
		return distance;
	}


	public void setDistance(String distance) {
		this.distance = distance;
	}


	public java.lang.Integer getGateId() {
		return gateId;
	}


	public void setGateId(java.lang.Integer gateId) {
		this.gateId = gateId;
	}


	public String getDeleteFlag() {
		return deleteFlag;
	}


	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}


	public String getHeadStatus() {
		return headStatus;
	}


	public void setHeadStatus(String headStatus) {
		this.headStatus = headStatus;
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblElectricpile");
		sb.append("{eId=").append(eId);
		sb.append(", eName=").append(eName);
		sb.append(", eCode=").append(eCode);
		sb.append(", addr=").append(addr);
		sb.append(", eLit=").append(eLot);
		sb.append(", eLat=").append(eLat);
		sb.append(", eHnum=").append(eHnum);
		sb.append(", eAcode=").append(eAcode);
		sb.append(", eState=").append(eState);
		sb.append(", rejectTxt=").append(rejectTxt);
		sb.append(", eType=").append(eType);
		sb.append(", ePuser=").append(ePuser);
		sb.append(", eCmode=").append(eCmode);
		sb.append(", ePsize=").append(ePsize);
		sb.append(", ePinf=").append(ePinf);
		sb.append(", eMaker=").append(eMaker);
		sb.append(", image=").append(image);
		sb.append(", detImage=").append(detImage);
		sb.append(", outV=").append(outV);
		sb.append(", inV=").append(inV);
		sb.append(", outC=").append(outC);
		sb.append(", inC=").append(inC);
		sb.append(", euType=").append(euType);
		sb.append(", eUid=").append(eUid);
		sb.append(", createdate=").append(createdate);
		sb.append(", updatedate=").append(updatedate);
		sb.append(", remark=").append(remark);
		sb.append(", cId=").append(cId);
		sb.append(", eBind=").append(eBind);
		sb.append(", isAppoint=").append(isAppoint);
		sb.append(", payStyle=").append(payStyle);
		sb.append(", maxE=").append(maxE);
		sb.append('}');
		return sb.toString();
	}
}