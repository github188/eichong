package com.wanma.model;

import com.bluemobi.product.model.common.BasicListAndMutiFile;
import com.wanma.app.util.DateUtil;

/**
 * 
 * tbl_UserInfo表
 * 
 * @author mew
 * 
 */
/**
 * 听管理
 * @Description:
 * @author songjf 
 * @createTime：2015-4-3 下午02:51:16 
 * @updator： 
 * @updateTime：   
 * @version：V1.0
 */
/**
 * 听管理
 * 
 * @Description:
 * @author songjf
 * @createTime：2015-4-3 下午02:51:18
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public class TblUserinfo extends BasicListAndMutiFile {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8465550416418032334L;
	private java.lang.Integer pkUserinfo; // 主键
	private java.lang.Integer usinGroupid; // 分组ID
	private java.lang.String usinPhone; // 手机号码
	private java.lang.String usinUsername;
	private java.lang.String usinFacticityname; // 真实姓名
	private java.lang.Integer usinSex; // 性别 0 男 1 女
	private java.lang.String usinIccode; // IC卡号
	private java.math.BigDecimal usinAccountbalance; // 账户余额
	private String usinBirthdate; // 出生日期
	private  java.lang.String birthdate; // 出生日期
	private java.lang.String usinEmail; // 邮箱
	private java.lang.String usinDrivinglicense; // 驾驶证号
	private java.lang.String usinAcura; // 品牌车型，根据电动车品牌类型详情ID
	private String usIn_Acura_name; //品牌车型名称
	private String platform; //用户注册平台 1管理后台，2web，3android，4ios
	private String payPwd;//支付密码
	private int isPpw;//是否设置支付密码
	private String invitePhone; //邀请人号码
	private java.lang.Integer usinIntegrate; // 积分
	private java.lang.String usinHeadimage; // 头像
	private java.lang.String usinMembercode; // 会员号码
	private java.lang.String usinUseraddress; // 联系地址
	private java.lang.Integer usinUserstatus; // 1：正常 2 ：冻结 3 ：商家升级审核中 4 普通用户
	private java.lang.String usinPassword; // 密码
	private java.util.Date usinCreatedate; // 创建时间
	private java.util.Date usinUpdatedate; // 修改时间
	private java.lang.String usinPlatenumber; // 车牌号
	private java.lang.String usinVehiclenumbe; // 车架号
	private String picType; // 上传图片类型
	private String did; //用户使用的设备的设备码
	
	private Integer usinCarcompanyId;// 汽车厂家ID
	private Integer usinCarinfoId;//品牌车型，根据电动车品牌类型详情ID
	private String usinCarinfoName; //车型名称
	private String pCode;//省编码
	private String cCode;//市编码
	private String aCode;//区县编码
	private int isCard;//是否申领电卡 1申请中 2已发放
	private String chargeCard; //充电卡外卡号
	private String applyCard; //是否申请充电卡
	private java.lang.Integer accountId; //充电卡外卡号
	private String accountNo; //是否申请充电卡
	
	
	
	public java.lang.Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(java.lang.Integer accountId) {
		this.accountId = accountId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getInvitePhone() {
		return invitePhone;
	}

	public void setInvitePhone(String invitePhone) {
		this.invitePhone = invitePhone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getApplyCard() {
		return applyCard;
	}

	public void setApplyCard(String applyCard) {
		this.applyCard = applyCard;
	}

	public String getChargeCard() {
		return chargeCard;
	}

	public void setChargeCard(String chargeCard) {
		this.chargeCard = chargeCard;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public int getIsPpw() {
		return isPpw;
	}

	public void setIsPpw(int isPpw) {
		this.isPpw = isPpw;
	}

	public String getUsinCarinfoName() {
		return usinCarinfoName;
	}

	public void setUsinCarinfoName(String usinCarinfoName) {
		this.usinCarinfoName = usinCarinfoName;
	}

	public int getIsCard() {
		return isCard;
	}

	public void setIsCard(int isCard) {
		this.isCard = isCard;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public String getaCode() {
		return aCode;
	}

	public void setaCode(String aCode) {
		this.aCode = aCode;
	}

	public java.lang.String getUsinUsername() {
		return usinUsername;
	}

	public void setUsinUsername(java.lang.String usinUsername) {
		this.usinUsername = usinUsername;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPicType() {
		return picType;
	}

	public void setPicType(String picType) {
		this.picType = picType;
	}

	// 以下字段不予数据库对应
	private String elPiElectricPileCode;// 电桩编号

	private String idCardimage; // 身份证图片

	private int electricpileHeadId;// 电桩编号
	
	private String brandName;	//品牌名称
	
	private String usinBrand;
	
	private String pkConfigcontent;
	
	private String usinCar;		//车型
	
	
	public String getUsIn_Acura_name() {
		return usIn_Acura_name;
	}

	public void setUsIn_Acura_name(String usIn_Acura_name) {
		this.usIn_Acura_name = usIn_Acura_name;
	}

	public String getUsinCar() {
		return usinCar;
	}

	public void setUsinCar(String usinCar) {
		this.usinCar = usinCar;
	}

	public java.lang.String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(java.lang.String birthdate) {
		this.birthdate = birthdate;
	}

	public String getPkConfigcontent() {
		return pkConfigcontent;
	}

	public void setPkConfigcontent(String pkConfigcontent) {
		this.pkConfigcontent = pkConfigcontent;
	}

	public String getUsinBrand() {
		return usinBrand;
	}

	public void setUsinBrand(String usinBrand) {
		this.usinBrand = usinBrand;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getElectricpileHeadId() {
		return electricpileHeadId;
	}

	public void setElectricpileHeadId(int electricpileHeadId) {
		this.electricpileHeadId = electricpileHeadId;
	}

	public String getIdCardimage() {
		return idCardimage;
	}

	public void setIdCardimage(String idCardimage) {
		this.idCardimage = idCardimage;
	}

	private String groupId;

	private String HeadImage;

	private String carName;

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getHeadImage() {
		return HeadImage;
	}

	public void setHeadImage(String headImage) {
		HeadImage = headImage;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * 获取主键属性
	 * 
	 * @return pkUserinfo
	 */
	public java.lang.Integer getPkUserinfo() {
		return pkUserinfo;
	}

	/**
	 * 设置主键属性
	 * 
	 * @param pkUserinfo
	 */
	public void setPkUserinfo(java.lang.Integer pkUserinfo) {
		this.pkUserinfo = pkUserinfo;
	}

	/**
	 * 获取分组ID属性
	 * 
	 * @return usinGroupid
	 */
	public java.lang.Integer getUsinGroupid() {
		return usinGroupid;
	}

	/**
	 * 设置分组ID属性
	 * 
	 * @param usinGroupid
	 */
	public void setUsinGroupid(java.lang.Integer usinGroupid) {
		this.usinGroupid = usinGroupid;
	}

	/**
	 * 获取手机号码属性
	 * 
	 * @return usinPhone
	 */
	public java.lang.String getUsinPhone() {
		return usinPhone;
	}

	/**
	 * 设置手机号码属性
	 * 
	 * @param usinPhone
	 */
	public void setUsinPhone(java.lang.String usinPhone) {
		this.usinPhone = usinPhone;
	}

	/**
	 * 获取真实姓名属性
	 * 
	 * @return usinFacticityname
	 */
	public java.lang.String getUsinFacticityname() {
		return usinFacticityname;
	}

	/**
	 * 设置真实姓名属性
	 * 
	 * @param usinFacticityname
	 */
	public void setUsinFacticityname(java.lang.String usinFacticityname) {
		this.usinFacticityname = usinFacticityname;
	}

	/**
	 * 获取性别 0 男 1 女属性
	 * 
	 * @return usinSex
	 */
	public java.lang.Integer getUsinSex() {
		return usinSex;
	}

	/**
	 * 设置性别 0 男 1 女属性
	 * 
	 * @param usinSex
	 */
	public void setUsinSex(java.lang.Integer usinSex) {
		this.usinSex = usinSex;
	}

	/**
	 * 获取IC卡号属性
	 * 
	 * @return usinIccode
	 */
	public java.lang.String getUsinIccode() {
		return usinIccode;
	}

	/**
	 * 设置IC卡号属性
	 * 
	 * @param usinIccode
	 */
	public void setUsinIccode(java.lang.String usinIccode) {
		this.usinIccode = usinIccode;
	}

	/**
	 * 获取账户余额属性
	 * 
	 * @return usinAccountbalance
	 */
	public java.math.BigDecimal getUsinAccountbalance() {
		return usinAccountbalance;
	}

	/**
	 * 设置账户余额属性
	 * 
	 * @param usinAccountbalance
	 */
	public void setUsinAccountbalance(java.math.BigDecimal usinAccountbalance) {
		this.usinAccountbalance = usinAccountbalance;
	}

	public String getUsinBirthdate() {
		return usinBirthdate;
	}

	public void setUsinBirthdate(String usinBirthdate) {
		this.usinBirthdate = usinBirthdate;
	}

	/**
	 * 获取邮箱属性
	 * 
	 * @return usinEmail
	 */
	public java.lang.String getUsinEmail() {
		return usinEmail;
	}

	/**
	 * 设置邮箱属性
	 * 
	 * @param usinEmail
	 */
	public void setUsinEmail(java.lang.String usinEmail) {
		this.usinEmail = usinEmail;
	}

	/**
	 * 获取驾驶证号属性
	 * 
	 * @return usinDrivinglicense
	 */
	public java.lang.String getUsinDrivinglicense() {
		return usinDrivinglicense;
	}

	/**
	 * 设置驾驶证号属性
	 * 
	 * @param usinDrivinglicense
	 */
	public void setUsinDrivinglicense(java.lang.String usinDrivinglicense) {
		this.usinDrivinglicense = usinDrivinglicense;
	}

	/**
	 * 获取品牌车型，根据电动车品牌类型详情ID属性
	 * 
	 * @return usinAcura
	 */
	public java.lang.String getUsinAcura() {
		return usinAcura;
	}

	/**
	 * 设置品牌车型，根据电动车品牌类型详情ID属性
	 * 
	 * @param usinAcura
	 */
	public void setUsinAcura(java.lang.String usinAcura) {
		this.usinAcura = usinAcura;
	}

	/**
	 * 获取积分属性
	 * 
	 * @return usinIntegrate
	 */
	public java.lang.Integer getUsinIntegrate() {
		return usinIntegrate;
	}

	/**
	 * 设置积分属性
	 * 
	 * @param usinIntegrate
	 */
	public void setUsinIntegrate(java.lang.Integer usinIntegrate) {
		this.usinIntegrate = usinIntegrate;
	}

	/**
	 * 获取头像属性
	 * 
	 * @return usinHeadimage
	 */
	public java.lang.String getUsinHeadimage() {
		return usinHeadimage;
	}

	/**
	 * 设置头像属性
	 * 
	 * @param usinHeadimage
	 */
	public void setUsinHeadimage(java.lang.String usinHeadimage) {
		this.usinHeadimage = usinHeadimage;
	}

	/**
	 * 获取会员号码属性
	 * 
	 * @return usinMembercode
	 */
	public java.lang.String getUsinMembercode() {
		return usinMembercode;
	}

	/**
	 * 设置会员号码属性
	 * 
	 * @param usinMembercode
	 */
	public void setUsinMembercode(java.lang.String usinMembercode) {
		this.usinMembercode = usinMembercode;
	}

	/**
	 * 获取联系地址属性
	 * 
	 * @return usinUseraddress
	 */
	public java.lang.String getUsinUseraddress() {
		return usinUseraddress;
	}

	/**
	 * 设置联系地址属性
	 * 
	 * @param usinUseraddress
	 */
	public void setUsinUseraddress(java.lang.String usinUseraddress) {
		this.usinUseraddress = usinUseraddress;
	}

	/**
	 * 获取1：正常 2 ：冻结 3 ：商家升级审核中 4 普通用户属性
	 * 
	 * @return usinUserstatus
	 */
	public java.lang.Integer getUsinUserstatus() {
		return usinUserstatus;
	}

	/**
	 * 设置1：正常 2 ：冻结 3 ：商家升级审核中 4 普通用户属性
	 * 
	 * @param usinUserstatus
	 */
	public void setUsinUserstatus(java.lang.Integer usinUserstatus) {
		this.usinUserstatus = usinUserstatus;
	}

	/**
	 * 获取密码属性
	 * 
	 * @return usinPassword
	 */
	public java.lang.String getUsinPassword() {
		return usinPassword;
	}

	/**
	 * 设置密码属性
	 * 
	 * @param usinPassword
	 */
	public void setUsinPassword(java.lang.String usinPassword) {
		this.usinPassword = usinPassword;
	}

	/**
	 * 获取创建时间属性
	 * 
	 * @return usinCreatedate
	 */
	public java.util.Date getUsinCreatedate() {
		return usinCreatedate;
	}

	/**
	 * 设置创建时间属性
	 * 
	 * @param usinCreatedate
	 */
	public void setUsinCreatedate(java.util.Date usinCreatedate) {
		this.usinCreatedate = usinCreatedate;
	}

	/**
	 * 获取修改时间属性
	 * 
	 * @return usinUpdatedate
	 */
	public java.util.Date getUsinUpdatedate() {
		return usinUpdatedate;
	}

	/**
	 * 设置修改时间属性
	 * 
	 * @param usinUpdatedate
	 */
	public void setUsinUpdatedate(java.util.Date usinUpdatedate) {
		this.usinUpdatedate = usinUpdatedate;
	}

	/**
	 * 获取车牌号属性
	 * 
	 * @return usinPlatenumber
	 */
	public java.lang.String getUsinPlatenumber() {
		return usinPlatenumber;
	}

	/**
	 * 设置车牌号属性
	 * 
	 * @param usinPlatenumber
	 */
	public void setUsinPlatenumber(java.lang.String usinPlatenumber) {
		this.usinPlatenumber = usinPlatenumber;
	}

	/**
	 * 获取车架号属性
	 * 
	 * @return usinVehiclenumbe
	 */
	public java.lang.String getUsinVehiclenumbe() {
		return usinVehiclenumbe;
	}

	/**
	 * 设置车架号属性
	 * 
	 * @param usinVehiclenumbe
	 */
	public void setUsinVehiclenumbe(java.lang.String usinVehiclenumbe) {
		this.usinVehiclenumbe = usinVehiclenumbe;
	}

	public java.lang.String getElPiElectricPileCode() {
		return elPiElectricPileCode;
	}

	public void setElPiElectricPileCode(java.lang.String elPiElectricPileCode) {
		this.elPiElectricPileCode = elPiElectricPileCode;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblUserinfo");
		sb.append("{pkUserinfo=").append(pkUserinfo);
		sb.append(", usinGroupid=").append(usinGroupid);
		sb.append(", usinPhone=").append(usinPhone);
		sb.append(", usinFacticityname=").append(usinFacticityname);
		sb.append(", usinSex=").append(usinSex);
		sb.append(", usinIccode=").append(usinIccode);
		sb.append(", usinAccountbalance=").append(usinAccountbalance);
		sb.append(", usinBirthdate=").append(usinBirthdate);
		sb.append(", usinEmail=").append(usinEmail);
		sb.append(", usinDrivinglicense=").append(usinDrivinglicense);
		sb.append(", usinAcura=").append(usinAcura);
		sb.append(", usinIntegrate=").append(usinIntegrate);
		sb.append(", usinHeadimage=").append(usinHeadimage);
		sb.append(", usinMembercode=").append(usinMembercode);
		sb.append(", usinUseraddress=").append(usinUseraddress);
		sb.append(", usinUserstatus=").append(usinUserstatus);
		sb.append(", usinPassword=").append(usinPassword);
		sb.append(", usinCreatedate=").append(usinCreatedate);
		sb.append(", usinUpdatedate=").append(usinUpdatedate);
		sb.append(", usinPlatenumber=").append(usinPlatenumber);
		sb.append(", usinVehiclenumbe=").append(usinVehiclenumbe);
		sb.append('}');
		return sb.toString();
	}

	/**
	 * 生日日期字符串处理
	 */
	private String birthday;


	public String getBirthday() {
		if(usinBirthdate!=null){
			return usinBirthdate;
		}
		return birthday;
	}

	public Integer getUsinCarcompanyId() {
		return usinCarcompanyId;
	}

	public void setUsinCarcompanyId(Integer usinCarcompanyId) {
		this.usinCarcompanyId = usinCarcompanyId;
	}

	public Integer getUsinCarinfoId() {
		return usinCarinfoId;
	}

	public void setUsinCarinfoId(Integer usinCarinfoId) {
		this.usinCarinfoId = usinCarinfoId;
	}
	
	
}