/**
 * FileName:UnionPayModel.java
 * Author: Administrator
 * Create: 2014年9月6日
 * Last Modified: 2014年9月6日
 * Version: V1.0 
 */
package com.bluemobi.product.model;

import java.util.Date;

import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.chinaums.pay.api.entities.NoticeEntity;

/**
 * 银联返回通知实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年9月6日
 */
public class UnionPayNoticeModel extends NoticeEntity implements
		java.io.Serializable {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 958032353623822207L;

	/** 创建时间 */
	private Date createDate;

	/** 创建者 */
	private String createUser;

	/** 最后更新时间 */
	private Date lastUpdateDate;

	/** 最后更新者 */
	private String lastUpdateUser;
	
	
	//查询参数:订单时间开始时间
	private String startDate;
	
	//查询参数:订单时间结束时间
	private String endDate;
	
	
	/** 分页用对象 */
	private DwzPagerMySQL pager;

	/**
	 * 分页用对象取得
	 * 
	 * @return 分页用对象
	 */
	public DwzPagerMySQL getPager() {
		return pager;
	}

	/**
	 * 分页用对象设定
	 * 
	 * @param pPager
	 *            分页用对象
	 */
	public void setPager(DwzPagerMySQL pPager) {
		this.pager = pPager;
	}

	/**
	 * 创建时间的取得
	 * 
	 * @return 创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 创建时间的设定
	 * 
	 * @param pCreateDate
	 *            创建时间
	 */
	public void setCreateDate(Date pCreateDate) {
		this.createDate = pCreateDate;
	}

	/**
	 * 创建者的取得
	 * 
	 * @return 创建者
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * 创建者的设定
	 * 
	 * @param pCreateUser
	 *            创建者
	 */
	public void setCreateUser(String pCreateUser) {
		this.createUser = pCreateUser;
	}

	/**
	 * 最后更新时间的取得
	 * 
	 * @return 最后更新时间
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * 最后更新时间的设定
	 * 
	 * @param pLastUpdateDate
	 *            最后更新时间
	 */
	public void setLastUpdateDate(Date pLastUpdateDate) {
		this.lastUpdateDate = pLastUpdateDate;
	}

	/**
	 * 最后更新者的取得
	 * 
	 * @return 最后更新者
	 */
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	/**
	 * 最后更新者的设定
	 * 
	 * @param pLastUpdateUser
	 *            最后更新者
	 */
	public void setLastUpdateUser(String pLastUpdateUser) {
		this.lastUpdateUser = pLastUpdateUser;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
