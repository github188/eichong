/**     
 * @Title:  Pager.java   
 * @Package com.wanma.support.common   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2015年11月20日 上午10:07:04   
 * @version V1.0     
 */
package com.wanma.support.common;

import java.io.Serializable;

/**
 * @author bc
 *
 */
public class Pager implements Serializable {
	private static final long serialVersionUID = -768351850999956527L;
	private Integer pageNum;
	private Integer pageSize=10;
	private Integer totalCount=0;
	private Integer offset;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getOffset() {
		pageNum=pageNum!=null?pageNum:0;
		offset = pageSize * (pageNum - 1);
		return offset > 0 ? offset : 0;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
}
