package com.wanma.ims.common.domain;

import java.util.Date;

import com.wanma.ims.constants.WanmaConstants;

public class UserNormalDO {
    private Long userId;//用户ID

    private String normName;//普通用户昵称

    private String normRealName;//普通用户真实姓名

    private Integer normRegisteType;//普通用户注册来源(1：管理后台 2：web 3：android 4：ios)

    private String normDeviceId;//普通用户登录设备ID

    private String normEmail;//普通用户邮箱

    private String normAddress;//普通用户地址

    private String normPhone;//普通用户手机号

    private Integer normSex;//普通用户性别 (1:男 2：女)

    private String normBirthday;//普通用户生日

    private Integer normIntegrate;//普通用户积分

    private Integer normCarCompanyId;//普通用户汽车品牌ID

    private Integer normCarTypeId;//普通用户汽车车型ID

    private String normVehicleNumber;//普通用户车架号

    private String normPlateNum;//车牌号

    private String normDrivingLicence;//普通用户驾驶证号

    private Integer applyCard;//充电卡申请标志：0：无申请 1：申请充电卡 2：已发放充电卡

    private Integer normOrigin;//用户来源 1富士康;2吉利;3绿地;4;浙誉;5.车纷享; 以后根据情况再扩展或修改

    private String normInvitephone;//邀请人号码

    private Long accountId;//资金账户ID

    private Long cpyId;//渠道公司ID

    private Date gmtCreate;//创建时间

    private Date gmtModified;//修改时间

    public static UserNormalDO valueOf(UserDO user, Long accountId) {
        UserNormalDO userNormal = new UserNormalDO();
        userNormal.setUserId(user.getUserId());
        userNormal.setNormName("");
        userNormal.setNormRealName(user.getUserCpyName());
        userNormal.setNormRegisteType(WanmaConstants.NORM_REGISTER_TYPE_SYS);
        userNormal.setNormDeviceId("");
        userNormal.setNormEmail("");
        userNormal.setNormAddress("");
        userNormal.setNormPhone(user.getUserCpyPhone());
        userNormal.setNormSex(user.getUserSex());
        userNormal.setNormBirthday("");
        userNormal.setNormIntegrate(0);
        userNormal.setNormCarCompanyId(0);
        userNormal.setNormCarTypeId(0);
        userNormal.setNormVehicleNumber("");
        userNormal.setNormPlateNum(user.getNormPlateNum());
        userNormal.setNormDrivingLicence("");
        userNormal.setApplyCard(0);
        userNormal.setNormOrigin(0);
        userNormal.setNormInvitephone("");
        userNormal.setAccountId(accountId);
        userNormal.setCpyId(user.getCpyId());
        return userNormal;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNormName() {
        return normName;
    }

    public void setNormName(String normName) {
        this.normName = normName;
    }

    public String getNormRealName() {
        return normRealName;
    }

    public void setNormRealName(String normRealName) {
        this.normRealName = normRealName;
    }

    public Integer getNormRegisteType() {
        return normRegisteType;
    }

    public void setNormRegisteType(Integer normRegisteType) {
        this.normRegisteType = normRegisteType;
    }

    public String getNormDeviceId() {
        return normDeviceId;
    }

    public void setNormDeviceId(String normDeviceId) {
        this.normDeviceId = normDeviceId;
    }

    public String getNormEmail() {
        return normEmail;
    }

    public void setNormEmail(String normEmail) {
        this.normEmail = normEmail;
    }

    public String getNormAddress() {
        return normAddress;
    }

    public void setNormAddress(String normAddress) {
        this.normAddress = normAddress;
    }

    public String getNormPhone() {
        return normPhone;
    }

    public void setNormPhone(String normPhone) {
        this.normPhone = normPhone;
    }

    public Integer getNormSex() {
        return normSex;
    }

    public void setNormSex(Integer normSex) {
        this.normSex = normSex;
    }

    public String getNormBirthday() {
        return normBirthday;
    }

    public void setNormBirthday(String normBirthday) {
        this.normBirthday = normBirthday;
    }

    public Integer getNormIntegrate() {
        return normIntegrate;
    }

    public void setNormIntegrate(Integer normIntegrate) {
        this.normIntegrate = normIntegrate;
    }

    public Integer getNormCarCompanyId() {
        return normCarCompanyId;
    }

    public void setNormCarCompanyId(Integer normCarCompanyId) {
        this.normCarCompanyId = normCarCompanyId;
    }

    public Integer getNormCarTypeId() {
        return normCarTypeId;
    }

    public void setNormCarTypeId(Integer normCarTypeId) {
        this.normCarTypeId = normCarTypeId;
    }

    public String getNormVehicleNumber() {
        return normVehicleNumber;
    }

    public void setNormVehicleNumber(String normVehicleNumber) {
        this.normVehicleNumber = normVehicleNumber;
    }

    public String getNormPlateNum() {
        return normPlateNum;
    }

    public void setNormPlateNum(String normPlateNum) {
        this.normPlateNum = normPlateNum;
    }

    public String getNormDrivingLicence() {
        return normDrivingLicence;
    }

    public void setNormDrivingLicence(String normDrivingLicence) {
        this.normDrivingLicence = normDrivingLicence;
    }

    public Integer getApplyCard() {
        return applyCard;
    }

    public void setApplyCard(Integer applyCard) {
        this.applyCard = applyCard;
    }

    public Integer getNormOrigin() {
        return normOrigin;
    }

    public void setNormOrigin(Integer normOrigin) {
        this.normOrigin = normOrigin;
    }

    public String getNormInvitephone() {
        return normInvitephone;
    }

    public void setNormInvitephone(String normInvitephone) {
        this.normInvitephone = normInvitephone;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCpyId() {
        return cpyId;
    }

    public void setCpyId(Long cpyId) {
        this.cpyId = cpyId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}