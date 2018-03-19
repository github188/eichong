package com.wanma.ims.common.domain.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Basic基类 
 */
public class BasicModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	// 分页对象
	private Pager pager;
	// 资金账户标识：1.用户  2.公司 3.卡
	private Integer sysType;
	// 是否删除 0.否 1.是
	private Integer isDel;
	// 创建人ID
	private Long creatorId;
	// 创建人
	private String creator;
	// 修改人
	private String modifier;
	// 创建时间
	private Date gmtCreate;
	// 修改时间
	private Date gmtModified;
	
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	public Integer getSysType() {
		return sysType;
	}
	
	public void setSysType(Integer sysType) {
		this.sysType = sysType;
	}
	public Integer getIsDel() {
		return isDel;
	}
	
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
	public Long getCreatorId() {
		return creatorId;
	}
	
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public String getModifier() {
		return modifier;
	}
	
	public void setModifier(String modifier) {
		this.modifier = modifier;
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
	@Override
	public String toString() {
		return "BasicModel [isDel=" + isDel + ", creator=" + creator + ", modifier=" + modifier + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified +  "]";
	}
	
	
}
