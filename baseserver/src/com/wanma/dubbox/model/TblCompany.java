package com.wanma.dubbox.model;

import java.io.Serializable;

import com.wanma.dubbox.model.common.BasicModel;

/**
 * 
 * tbl_company表
 * 
 * @author mew
 *
 */
public class TblCompany extends BasicModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5923543473327896021L;
	private String name; // 企业名称
	private String adr; // 企业地址
	private String mail; // 企业邮箱
	private Integer postCd; // 邮编
	private String scope; // 经营范围
	private String atrName; // 授权人名称
	private String atrPhone; // 授权人联系电话
	private String atrCid; // 授权人身份证
	private String buslic; // 营业执照
	private String orgCd; // 组织机构代码
	private String djz; // 税务登记证
	private String sqz; // 授权证明
	private String maiAdr; // 邮寄地址
	private String picTp; // 1:授权人身份证 2:营业执照 3:税务登记证 4:授权证明
	private String comNum; // 公司标识
	private String[] comNums;// 公司标识数组，查询专用

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getPostCd() {
		return postCd;
	}

	public void setPostCd(Integer postCd) {
		this.postCd = postCd;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAtrName() {
		return atrName;
	}

	public void setAtrName(String atrName) {
		this.atrName = atrName;
	}

	public String getAtrPhone() {
		return atrPhone;
	}

	public void setAtrPhone(String atrPhone) {
		this.atrPhone = atrPhone;
	}

	public String getAtrCid() {
		return atrCid;
	}

	public void setAtrCid(String atrCid) {
		this.atrCid = atrCid;
	}

	public String getBuslic() {
		return buslic;
	}

	public void setBuslic(String buslic) {
		this.buslic = buslic;
	}

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getDjz() {
		return djz;
	}

	public void setDjz(String djz) {
		this.djz = djz;
	}

	public String getSqz() {
		return sqz;
	}

	public void setSqz(String sqz) {
		this.sqz = sqz;
	}

	public String getMaiAdr() {
		return maiAdr;
	}

	public void setMaiAdr(String maiAdr) {
		this.maiAdr = maiAdr;
	}

	public String getPicTp() {
		return picTp;
	}

	public void setPicTp(String picTp) {
		this.picTp = picTp;
	}

	public String getComNum() {
		return comNum;
	}

	public void setComNum(String comNum) {
		this.comNum = comNum;
	}

	public String[] getComNums() {
		return comNums;
	}

	public void setComNums(String[] comNums) {
		this.comNums = comNums;
	}

}