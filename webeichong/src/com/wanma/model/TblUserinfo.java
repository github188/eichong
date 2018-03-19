package com.wanma.model;

import com.bluemobi.product.model.common.BasicListAndMutiFile;
import com.wanma.web.support.utils.DateUtil;

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
	private java.lang.String usinUsername; // 用户名
	private java.lang.String usinPhone; // 手机号码
	private java.lang.String usinFacticityname; // 真实姓名
	private java.lang.Integer usinSex; // 性别 0 男 1 女
	private java.lang.String usinIccode; // IC卡号
	private java.math.BigDecimal usinAccountbalance; // 账户余额
	private java.util.Date usinBirthdate; // 出生日期
	private java.lang.String usinEmail; // 邮箱
	private java.lang.String usinDrivinglicense; // 驾驶证号
	private java.lang.String usinAcura; // 品牌车型，根据电动车品牌类型详情ID
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
	private java.lang.Integer  usinRegistetype;
	private String picType; // 上传图片类型
	private String usInDeviceId;//登录设备MAC
	private String usInCarCompanyId;//车厂家ID
	private String usinCarinfoId;//车型ID
	
	
	public String getUsInCarCompanyId() {
		return usInCarCompanyId;
	}
	public void setUsInCarCompanyId(String usInCarCompanyId) {
		this.usInCarCompanyId = usInCarCompanyId;
	}
	public String getUsinCarinfoId() {
		return usinCarinfoId;
	}
	public void setUsinCarinfoId(String usinCarinfoId) {
		this.usinCarinfoId = usinCarinfoId;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPicType() {
		return picType;
	}

	public void setPicType(String picType) {
		this.picType = picType;
	}

	// 以下字段不予数据库对应
	private java.lang.String elPiElectricPileCode;// 电桩编号

	private String idCardimage; // 身份证图片

	private int electricpileHeadId;// 电桩编号
	
	private String brandName;	//品牌名称
	
	private String usinBrand;
	
	private String pkConfigcontent;
	
	private String usinCar;		//车型
	
	public String getUsinCar() {
		return usinCar;
	}

	public void setUsinCar(String usinCar) {
		this.usinCar = usinCar;
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
	 * 获取分组ID属性
	 * 
	 * @return usinUsername
	 */
	public java.lang.String getUsinUsername() {
		return usinUsername;
	}

	/**
	 * 设置分组ID属性
	 * 
	 * @param usinUsername
	 */
	public void setUsinUsername(java.lang.String usinUsername) {
		this.usinUsername = usinUsername;
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

	/**
	 * 获取出生日期属性
	 * 
	 * @return usinBirthdate
	 */
	public java.util.Date getUsinBirthdate() {
		return usinBirthdate;
	}

	/**
	 * 设置出生日期属性
	 * 
	 * @param usinBirthdate
	 */
	public void setUsinBirthdate(java.util.Date usinBirthdate) {
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
	


	


	public java.lang.Integer getUsinRegistetype() {
		return usinRegistetype;
	}

	public void setUsinRegistetype(java.lang.Integer usinRegistetype) {
		this.usinRegistetype = usinRegistetype;
	}
	public String getUsInDeviceId() {
		return usInDeviceId;
	}
	public void setUsInDeviceId(String usInDeviceId) {
		this.usInDeviceId = usInDeviceId;
	}



	

	@Override
	public String toString() {
		return "TblUserinfo [pkUserinfo=" + pkUserinfo + ", usinGroupid="
				+ usinGroupid + ", usinUsername=" + usinUsername
				+ ", usinPhone=" + usinPhone + ", usinFacticityname="
				+ usinFacticityname + ", usinSex=" + usinSex + ", usinIccode="
				+ usinIccode + ", usinAccountbalance=" + usinAccountbalance
				+ ", usinBirthdate=" + usinBirthdate + ", usinEmail="
				+ usinEmail + ", usinDrivinglicense=" + usinDrivinglicense
				+ ", usinAcura=" + usinAcura + ", usinIntegrate="
				+ usinIntegrate + ", usinHeadimage=" + usinHeadimage
				+ ", usinMembercode=" + usinMembercode + ", usinUseraddress="
				+ usinUseraddress + ", usinUserstatus=" + usinUserstatus
				+ ", usinPassword=" + usinPassword + ", usinCreatedate="
				+ usinCreatedate + ", usinUpdatedate=" + usinUpdatedate
				+ ", usinPlatenumber=" + usinPlatenumber
				+ ", usinVehiclenumbe=" + usinVehiclenumbe
				+ ", usinRegistetype=" + usinRegistetype + ", picType="
				+ picType + ", usInDeviceId=" + usInDeviceId
				+ ", usInCarCompanyId=" + usInCarCompanyId + ", usinCarinfoId="
				+ usinCarinfoId + ", elPiElectricPileCode="
				+ elPiElectricPileCode + ", idCardimage=" + idCardimage
				+ ", electricpileHeadId=" + electricpileHeadId + ", brandName="
				+ brandName + ", usinBrand=" + usinBrand + ", pkConfigcontent="
				+ pkConfigcontent + ", usinCar=" + usinCar + ", groupId="
				+ groupId + ", HeadImage=" + HeadImage + ", carName=" + carName
				+ ", birthday=" + birthday + "]";
	}

	/**
	 * 生日日期字符串处理
	 */
	private String birthday;


	public String getBirthday() {
		if(usinBirthdate!=null){
			return DateUtil.convertDateToString(usinBirthdate);
		}
		return birthday;
	}
	
}