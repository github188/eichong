package com.wanma.model;

import java.util.Date;

import com.bluemobi.product.model.common.BasicListAndMutiFile;
import com.bluemobi.product.model.common.BasicModel;


/**
 * 咨询表
 * 
 * @version V1.0
 * @author xiay
 * @date 2015年3月18日
 *
 */
public class Consult extends BasicListAndMutiFile{
	
	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private String consultId; //主键ID
	
	private String userLogin; //用户账号
	
	private String contact; //联系人
	
	private String consultContent; //咨询内容
	
	private String consultAddress; //联系地址
	
	private Date createDate; //创建时间
	
	private String updateDate; //修改时间
	
	// 以下字段不予数据库对应
	private String start_create_date;
	
	private String end_create_date;

	public String getStart_create_date() {
		return start_create_date;
	}

	public void setStart_create_date(String start_create_date) {
		this.start_create_date = start_create_date;
	}

	public String getEnd_create_date() {
		return end_create_date;
	}

	public void setEnd_create_date(String end_create_date) {
		this.end_create_date = end_create_date;
	}

	public String getConsultId() {
		return consultId;
	}

	public void setConsultId(String consultId) {
		this.consultId = consultId;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getConsultContent() {
		return consultContent;
	}

	public void setConsultContent(String consultContent) {
		this.consultContent = consultContent;
	}

	public String getConsultAddress() {
		return consultAddress;
	}

	public void setConsultAddress(String consultAddress) {
		this.consultAddress = consultAddress;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
}
