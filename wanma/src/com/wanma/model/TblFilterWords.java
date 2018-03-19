package com.wanma.model;

import java.util.Date;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

public class TblFilterWords extends BasicListAndMutiFile{

	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String filterwordsId;  //主键ID
	
	private String wordsName;	//过滤字
	
	private Date createdate;	//创建时间
	
	private Date updatedate;	//修改时间
	
	private String status;	//状态

	public String getFilterwordsId() {
		return filterwordsId;
	}

	public void setFilterwordsId(String filterwordsId) {
		this.filterwordsId = filterwordsId;
	}

	public String getWordsName() {
		return wordsName;
	}

	public void setWordsName(String wordsName) {
		this.wordsName = wordsName;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
