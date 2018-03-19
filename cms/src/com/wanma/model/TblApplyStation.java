package com.wanma.model;

import java.util.Date;

import com.pub.model.Entity;

public class TblApplyStation extends Entity{
			 private static final long serialVersionUID = 1L;
             private Integer pkApplyStation;
             private Integer apStUserInfoId;     //与tbl_user_normal_view表中用户ID关联
             private String  apStUserFacticityName;   //加充电点信息表中联系人真实姓名，不一定是申请人的。同下
             private String  apStUserPhone;   //加充电点信息表中联系人电话，不一定是申请人。可以是申请人帮联系人填表申请这种用户场景
             private String  apStUserEmail;			//联系人EMaiL  同上
             private String  apStStationAddress;	//申请地址
             private Integer apStPropertyNature;    //物业性质
             private Date    apStCreatedate;  
             private Date    apStUpdatedate;
             private Integer apStProcessState;      //申请审批状态
             private String  apStRemark;			//备注
             private String  apStDealReason;		//驳回原因
             
             //以下字段不与数据库对应
             private String createDateStarte;
             private String createDateEnd;
             private String updateDateStart;
             private String updateDateEnd;
             private String userInfoPhone;   //申请人联系电话，即userInfo表中用户联系电话
		


			public Integer getPkApplyStation() {
				return pkApplyStation;
			}



			public void setPkApplyStation(Integer pkApplyStation) {
				this.pkApplyStation = pkApplyStation;
			}



			public Integer getApStUserInfoId() {
				return apStUserInfoId;
			}



			public void setApStUserInfoId(Integer apStUserInfoId) {
				this.apStUserInfoId = apStUserInfoId;
			}



			public String getApStUserFacticityName() {
				return apStUserFacticityName;
			}



			public void setApStUserFacticityName(String apStUserFacticityName) {
				this.apStUserFacticityName = apStUserFacticityName;
			}



			public String getApStUserPhone() {
				return apStUserPhone;
			}



			public void setApStUserPhone(String apStUserPhone) {
				this.apStUserPhone = apStUserPhone;
			}



			public String getApStUserEmail() {
				return apStUserEmail;
			}



			public void setApStUserEmail(String apStUserEmail) {
				this.apStUserEmail = apStUserEmail;
			}



			public String getApStStationAddress() {
				return apStStationAddress;
			}



			public void setApStStationAddress(String apStStationAddress) {
				this.apStStationAddress = apStStationAddress;
			}



			public Integer getApStPropertyNature() {
				return apStPropertyNature;
			}



			public void setApStPropertyNature(Integer apStPropertyNature) {
				this.apStPropertyNature = apStPropertyNature;
			}



			public Date getApStCreatedate() {
				return apStCreatedate;
			}



			public void setApStCreatedate(Date apStCreatedate) {
				this.apStCreatedate = apStCreatedate;
			}



			public Date getApStUpdatedate() {
				return apStUpdatedate;
			}



			public void setApStUpdatedate(Date apStUpdatedate) {
				this.apStUpdatedate = apStUpdatedate;
			}



			public Integer getApStProcessState() {
				return apStProcessState;
			}



			public void setApStProcessState(Integer apStProcessState) {
				this.apStProcessState = apStProcessState;
			}



			public String getApStRemark() {
				return apStRemark;
			}



			public void setApStRemark(String apStRemark) {
				this.apStRemark = apStRemark;
			}



			public String getApStDealReason() {
				return apStDealReason;
			}



			public void setApStDealReason(String apStDealReason) {
				this.apStDealReason = apStDealReason;
			}



			public String getCreateDateStarte() {
				return createDateStarte;
			}



			public void setCreateDateStarte(String createDateStarte) {
				this.createDateStarte = createDateStarte;
			}



			public String getCreateDateEnd() {
				return createDateEnd;
			}



			public void setCreateDateEnd(String createDateEnd) {
				this.createDateEnd = createDateEnd;
			}



			public String getUpdateDateStart() {
				return updateDateStart;
			}



			public void setUpdateDateStart(String updateDateStart) {
				this.updateDateStart = updateDateStart;
			}



			public String getUpdateDateEnd() {
				return updateDateEnd;
			}



			public void setUpdateDateEnd(String updateDateEnd) {
				this.updateDateEnd = updateDateEnd;
			}



			public String getUserInfoPhone() {
				return userInfoPhone;
			}



			public void setUserInfoPhone(String userInfoPhone) {
				this.userInfoPhone = userInfoPhone;
			}



			@Override
			public String toString() {
				return "TblApplyStation [pkApplyStation=" + pkApplyStation
						+ ", apStUserInfoId=" + apStUserInfoId
						+ ", apStUserFacticityName=" + apStUserFacticityName
						+ ", apStUserPhone=" + apStUserPhone
						+ ", apStUserEmail=" + apStUserEmail
						+ ", apStStationAddress=" + apStStationAddress
						+ ", apStPropertyNature=" + apStPropertyNature
						+ ", apStCreatedate=" + apStCreatedate
						+ ", apStUpdatedate=" + apStUpdatedate
						+ ", apStProcessState=" + apStProcessState
						+ ", apStRemark=" + apStRemark + ", apStDealReason="
						+ apStDealReason + ", createDateStarte="
						+ createDateStarte + ", createDateEnd=" + createDateEnd
						+ ", updateDateStart=" + updateDateStart
						+ ", updateDateEnd=" + updateDateEnd
						+ ", userInfoPhone=" + userInfoPhone + "]";
			}
	
	
             			
}
