/**
 * FileName:DepartmentModel.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.bluemobi.product.model.common.BasicListModel;

/**
 * 部门实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class DepartmentModel extends BasicListModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 3751652319620592615L;

	/** 公司ID */
	private String companyId;

	/** 公司名称 */
	private String companyName;

	/** 部门ID */
	private String departmentId;

	/** 部门名称 */
	private String departmentName;

	/** 地址1 */
	private String address1;

	/** 地址2 */
	private String address2;

	/** 电话号码 */
	private String telephoneNumber;

	/** 传真号码 */
	private String faxNumber;

	/** 分机号 */
	private String extensionNumber;

	/** 传真分机号 */
	private String extensionFaxNumber;

	/** 国家ID */
	private String countryId;

	/** 邮政编码 */
	private String zipCode;

	/** 邮件地址1 */
	private String emailAddress1;

	/** 邮件地址2 */
	private String emailAddress2;

	/** 主页 */
	private String url;

	/** 是否有子部门 */
	private boolean hasChild;

	/** 父部门ID */
	private String parentDepartmentId;

	/** 父部门名称 */
	private String parentDepartmentName;

	/** 责任公司 */
	private String responsibleCompany;

	/** 责任部门 */
	private String responsibleDepart;

	/** 责任部门名称 */
	private String responsibleDepartName;

	/** 公司分类 */
	private String companyType;

	/**
	 * 父部门名称的取得。
	 * 
	 * @return 父部门名称
	 */
	public String getParentDepartmentName() {
		return parentDepartmentName;
	}

	/**
	 * 父部门名称的设定。
	 * 
	 * @param pParentDepartmentName
	 *            父部门名称
	 */
	public void setParentDepartmentName(String pParentDepartmentName) {
		this.parentDepartmentName = pParentDepartmentName;
	}

	/** 子部门列表 */
	private List<DepartmentModel> childDepartments;

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
	 * 部门ID的取得。
	 * 
	 * @return 部门ID
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * 部门ID的设定。
	 * 
	 * @param pDepartmentId
	 *            部门ID
	 */
	public void setDepartmentId(String pDepartmentId) {
		this.departmentId = pDepartmentId;
	}

	/**
	 * 部门名称的取得。
	 * 
	 * @return 部门名称
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * 部门名称的设定。
	 * 
	 * @param pDepartmentName
	 *            部门名称
	 */
	public void setDepartmentName(String pDepartmentName) {
		this.departmentName = pDepartmentName;
	}

	/**
	 * 地址1的取得。
	 * 
	 * @return 地址1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * 地址1的设定。
	 * 
	 * @param pAddress1
	 *            地址1
	 */
	public void setAddress1(String pAddress1) {
		this.address1 = pAddress1;
	}

	/**
	 * 地址2的取得。
	 * 
	 * @return 地址2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * 地址2的设定。
	 * 
	 * @param pAddress2
	 *            地址2
	 */
	public void setAddress2(String pAddress2) {
		this.address2 = pAddress2;
	}

	/**
	 * 电话号码的取得。
	 * 
	 * @return 电话号码
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * 电话号码的设定。
	 * 
	 * @param pTelephoneNumber
	 *            电话号码
	 */
	public void setTelephoneNumber(String pTelephoneNumber) {
		this.telephoneNumber = pTelephoneNumber;
	}

	/**
	 * 传真号码的取得。
	 * 
	 * @return 传真号码
	 */
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * 传真号码的设定。
	 * 
	 * @param pFaxNumber
	 *            传真号码
	 */
	public void setFaxNumber(String pFaxNumber) {
		this.faxNumber = pFaxNumber;
	}

	/**
	 * 分机号的取得。
	 * 
	 * @return 分机号
	 */
	public String getExtensionNumber() {
		return extensionNumber;
	}

	/**
	 * 分机号的设定。
	 * 
	 * @param pExtensionNumber
	 *            分机号
	 */
	public void setExtensionNumber(String pExtensionNumber) {
		this.extensionNumber = pExtensionNumber;
	}

	/**
	 * 传真分机号的取得。
	 * 
	 * @return 传真分机号
	 */
	public String getExtensionFaxNumber() {
		return extensionFaxNumber;
	}

	/**
	 * 传真分机号的设定。
	 * 
	 * @param pExtensionFaxNumber
	 *            传真分机号
	 */
	public void setExtensionFaxNumber(String pExtensionFaxNumber) {
		this.extensionFaxNumber = pExtensionFaxNumber;
	}

	/**
	 * 国家ID的取得。
	 * 
	 * @return 国家ID
	 */
	public String getCountryId() {
		return countryId;
	}

	/**
	 * 国家ID的设定。
	 * 
	 * @param pCountryId
	 *            国家ID
	 */
	public void setCountryId(String pCountryId) {
		this.countryId = pCountryId;
	}

	/**
	 * 邮政编码的取得。
	 * 
	 * @return 邮政编码
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 邮政编码的设定。
	 * 
	 * @param pZipCode
	 *            邮政编码
	 */
	public void setZipCode(String pZipCode) {
		this.zipCode = pZipCode;
	}

	/**
	 * 邮件地址1的取得。
	 * 
	 * @return 邮件地址1
	 */
	public String getEmailAddress1() {
		return emailAddress1;
	}

	/**
	 * 邮件地址1的设定。
	 * 
	 * @param pEmailAddress1
	 *            邮件地址1
	 */
	public void setEmailAddress1(String pEmailAddress1) {
		this.emailAddress1 = pEmailAddress1;
	}

	/**
	 * 邮件地址2的取得。
	 * 
	 * @return 邮件地址2
	 */
	public String getEmailAddress2() {
		return emailAddress2;
	}

	/**
	 * 邮件地址2的设定。
	 * 
	 * @param pEmailAddress2
	 *            邮件地址2
	 */
	public void setEmailAddress2(String pEmailAddress2) {
		this.emailAddress2 = pEmailAddress2;
	}

	/**
	 * 主页的取得。
	 * 
	 * @return 主页
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 主页的设定。
	 * 
	 * @param pUrl
	 *            主页
	 */
	public void setUrl(String pUrl) {
		this.url = pUrl;
	}

	/**
	 * 子部门列表的取得。
	 * 
	 * @return 子部门列表
	 */
	public List<DepartmentModel> getChildDepartments() {
		return childDepartments;
	}

	/**
	 * 子部门列表的设定。
	 * 
	 * @param pChildDepartments
	 *            子部门列表
	 */
	public void setChildDepartments(List<DepartmentModel> pChildDepartments) {
		this.childDepartments = pChildDepartments;
	}

	/**
	 * 是否有子部门的取得。
	 * 
	 * @return 是否有子部门
	 */
	public boolean getHasChild() {
		return hasChild;
	}

	/**
	 * 是否有子部门的设定。
	 * 
	 * @param pHasChild
	 *            是否有子部门
	 */
	public void setHasChild(boolean pHasChild) {
		this.hasChild = pHasChild;
	}

	/**
	 * 父部门ID的取得。
	 * 
	 * @return 父部门ID
	 */
	public String getParentDepartmentId() {
		return parentDepartmentId;
	}

	/**
	 * 父部门ID的设定。
	 * 
	 * @param pParentDepartmentId
	 *            父部门ID
	 */
	public void setParentDepartmentId(String pParentDepartmentId) {
		this.parentDepartmentId = pParentDepartmentId;
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
	 * 从部门列表中删除指定部门对象
	 * 
	 * @param targetDepartmentList
	 *            部门列表
	 * @param targetDepartmentModel
	 *            指定部门对象
	 * @return 无
	 * @throws 无
	 */
	public static void removeDepartment(
			List<DepartmentModel> targetDepartmentList,
			DepartmentModel targetDepartmentModel) {
		if (targetDepartmentList == null || targetDepartmentList.size() == 0
				|| targetDepartmentModel == null
				|| targetDepartmentModel.getDepartmentId() == null
				|| targetDepartmentModel.getCompanyId() == null) {
			return;
		}

		for (DepartmentModel departmentModel : targetDepartmentList) {
			if (StringUtils.equals(departmentModel.getDepartmentId(),
					targetDepartmentModel.getDepartmentId())
					&& StringUtils.equals(departmentModel.getCompanyId(),
							targetDepartmentModel.getCompanyId())) {
				targetDepartmentList.remove(departmentModel);
				break;
			}
		}
	}

}
