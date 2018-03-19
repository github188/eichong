/**
 * FileName:CompanyModel.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.model;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

/**
 * 公司实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class CompanyModel extends BasicListAndMutiFile {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 8996818222808013236L;

	/** 公司ID */
	private String companyId;
	/** 公司分类 */
	private String companyType;
	/** 公司名称 */
	private String companyName;
	/** 省份ID */
	private String provinceId;
	/** 城市ID */
	private String cityId;
	/** 区县ID */
	private String areaId;
	/** 开发公司 */
	private String developCompany;
	/** 物业公司 */
	private String serviceCompany;
	/** 物业服务电话 */
	private String servicePhone;
	/** 物业服务时间 */
	private String serviceTime;
	/** 物业保修范围与规定 */
	private String serviceRule;
	/** 物业服务内容与收费标准 */
	private String serviceComment;
	/** 电话号码 */
	private String phoneNumber;
	/** 基本信息 */
	private String notes;
	/** 详情描述 */
	private String detail;

	/** 省份名称 */
	private String provinceName;
	/** 城市名称 */
	private String cityName;
	/** 区县名称 */
	private String areaName;

	/** 客服用户ID */
	private String serviceUserId;
	/** 互动空间管理用户ID */
	private String comUserId;

	/** 责任公司 */
	private String responsibleCompany;

	/** 责任部门 */
	private String responsibleDepart;

	/** 责任部门名称 */
	private String responsibleDepartName;

	/**
	 * 公司ID的取得。
	 * 
	 * @return 公司ID
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * 公司ID的设定。
	 * 
	 * @param pCompanyId
	 *            公司ID
	 */
	public void setCompanyId(String pCompanyId) {
		this.companyId = pCompanyId;
	}

	/**
	 * 公司分类的取得。
	 * 
	 * @return 公司分类
	 */
	public String getCompanyType() {
		return companyType;
	}

	/**
	 * 公司分类的设定。
	 * 
	 * @param pCompanyType
	 *            公司分类
	 */
	public void setCompanyType(String pCompanyType) {
		this.companyType = pCompanyType;
	}

	/**
	 * 公司名称的取得。
	 * 
	 * @return 公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 公司名称的设定。
	 * 
	 * @param pCompanyName
	 *            公司名称
	 */
	public void setCompanyName(String pCompanyName) {
		this.companyName = pCompanyName;
	}

	/**
	 * 省份ID的取得。
	 * 
	 * @return 省份ID
	 */
	public String getProvinceId() {
		return provinceId;
	}

	/**
	 * 省份ID的设定。
	 * 
	 * @param pProvinceId
	 *            省份ID
	 */
	public void setProvinceId(String pProvinceId) {
		this.provinceId = pProvinceId;
	}

	/**
	 * 城市ID的取得。
	 * 
	 * @return 城市ID
	 */
	public String getCityId() {
		return cityId;
	}

	/**
	 * 城市ID的设定。
	 * 
	 * @param pCityId
	 *            城市ID
	 */
	public void setCityId(String pCityId) {
		this.cityId = pCityId;
	}

	/**
	 * 区县ID的取得。
	 * 
	 * @return 区县ID
	 */
	public String getAreaId() {
		return areaId;
	}

	/**
	 * 区县ID的设定。
	 * 
	 * @param pAreaId
	 *            区县ID
	 */
	public void setAreaId(String pAreaId) {
		this.areaId = pAreaId;
	}

	/**
	 * 开发公司的取得。
	 * 
	 * @return 开发公司
	 */
	public String getDevelopCompany() {
		return developCompany;
	}

	/**
	 * 开发公司的设定。
	 * 
	 * @param pDevelopCompany
	 *            开发公司
	 */
	public void setDevelopCompany(String pDevelopCompany) {
		this.developCompany = pDevelopCompany;
	}

	/**
	 * 物业公司的取得。
	 * 
	 * @return 物业公司
	 */
	public String getServiceCompany() {
		return serviceCompany;
	}

	/**
	 * 物业公司的设定。
	 * 
	 * @param pServiceCompany
	 *            物业公司
	 */
	public void setServiceCompany(String pServiceCompany) {
		this.serviceCompany = pServiceCompany;
	}

	/**
	 * 物业服务电话的取得。
	 * 
	 * @return 物业服务电话
	 */
	public String getServicePhone() {
		return servicePhone;
	}

	/**
	 * 物业服务电话的设定。
	 * 
	 * @param pServicePhone
	 *            物业服务电话
	 */
	public void setServicePhone(String pServicePhone) {
		this.servicePhone = pServicePhone;
	}

	/**
	 * 物业服务时间的取得。
	 * 
	 * @return 物业服务时间
	 */
	public String getServiceTime() {
		return serviceTime;
	}

	/**
	 * 物业服务时间的设定。
	 * 
	 * @param pServiceTime
	 *            物业服务时间
	 */
	public void setServiceTime(String pServiceTime) {
		this.serviceTime = pServiceTime;
	}

	/**
	 * 物业保修范围与规定的取得。
	 * 
	 * @return 物业保修范围与规定
	 */
	public String getServiceRule() {
		return serviceRule;
	}

	/**
	 * 物业保修范围与规定的设定。
	 * 
	 * @param pServiceRule
	 *            物业保修范围与规定
	 */
	public void setServiceRule(String pServiceRule) {
		this.serviceRule = pServiceRule;
	}

	/**
	 * 物业服务内容与收费标准的取得。
	 * 
	 * @return 物业服务内容与收费标准
	 */
	public String getServiceComment() {
		return serviceComment;
	}

	/**
	 * 物业服务内容与收费标准的设定。
	 * 
	 * @param pServiceComment
	 *            物业服务内容与收费标准
	 */
	public void setServiceComment(String pServiceComment) {
		this.serviceComment = pServiceComment;
	}

	/**
	 * 电话号码的取得。
	 * 
	 * @return 电话号码
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 电话号码的设定。
	 * 
	 * @param pPhoneNumber
	 *            电话号码
	 */
	public void setPhoneNumber(String pPhoneNumber) {
		this.phoneNumber = pPhoneNumber;
	}

	/**
	 * 基本信息的取得。
	 * 
	 * @return 基本信息
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * 基本信息的设定。
	 * 
	 * @param pNotes
	 *            基本信息
	 */
	public void setNotes(String pNotes) {
		this.notes = pNotes;
	}

	/**
	 * 详情描述的取得。
	 * 
	 * @return 详情描述
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 详情描述的设定。
	 * 
	 * @param pDetail
	 *            详情描述
	 */
	public void setDetail(String pDetail) {
		this.detail = pDetail;
	}

	/**
	 * 省份名称
	 * 
	 * @return 省份名称
	 */
	public String getProvinceName() {
		return provinceName;
	}

	/**
	 * 省份名称
	 * 
	 * @param pProvinceName
	 *            省份名称
	 */
	public void setProvinceName(String pProvinceName) {
		this.provinceName = pProvinceName;
	}

	/**
	 * 城市名称
	 * 
	 * @return 城市名称
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * 城市名称
	 * 
	 * @param pCityName
	 *            城市名称
	 */
	public void setCityName(String pCityName) {
		this.cityName = pCityName;
	}

	/**
	 * 区县名称的取得。
	 * 
	 * @return 区县名称
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * 区县名称的设定。
	 * 
	 * @param pAreaName
	 *            区县名称
	 */
	public void setAreaName(String pAreaName) {
		this.areaName = pAreaName;
	}

	/**
	 * 客服用户ID的取得。
	 * 
	 * @return 客服用户ID
	 */
	public String getServiceUserId() {
		return serviceUserId;
	}

	/**
	 * 客服用户ID的设定。
	 * 
	 * @param pServiceUserId
	 *            客服用户ID
	 */
	public void setServiceUserId(String pServiceUserId) {
		this.serviceUserId = pServiceUserId;
	}

	/**
	 * 互动空间管理用户ID的取得。
	 * 
	 * @return 互动空间管理用户ID
	 */
	public String getComUserId() {
		return comUserId;
	}

	/**
	 * 互动空间管理用户ID的设定。
	 * 
	 * @param pComUserId
	 *            互动空间管理用户ID
	 */
	public void setComUserId(String pComUserId) {
		this.comUserId = pComUserId;
	}

	/**
	 * 责任公司的取得。
	 * 
	 * @return 责任公司
	 */
	public String getResponsibleCompany() {
		return responsibleCompany;
	}

	/**
	 * 责任公司的设定。
	 * 
	 * @param pResponsibleCompany
	 *            责任公司
	 */
	public void setResponsibleCompany(String pResponsibleCompany) {
		this.responsibleCompany = pResponsibleCompany;
	}

	/**
	 * 责任部门的取得。
	 * 
	 * @return 责任部门
	 */
	public String getResponsibleDepart() {
		return responsibleDepart;
	}

	/**
	 * 责任部门的设定。。
	 * 
	 * @param pResponsibleDepart
	 *            责任部门
	 */
	public void setResponsibleDepart(String pResponsibleDepart) {
		this.responsibleDepart = pResponsibleDepart;
	}

	/**
	 * 责任部门名称的取得。
	 * 
	 * @return 责任部门名称
	 */
	public String getResponsibleDepartName() {
		return responsibleDepartName;
	}

	/**
	 * 责任部门名称的设定。
	 * 
	 * @param pResponsibleDepartName
	 *            责任部门名称
	 */
	public void setResponsibleDepartName(String pResponsibleDepartName) {
		this.responsibleDepartName = pResponsibleDepartName;
	}

}
