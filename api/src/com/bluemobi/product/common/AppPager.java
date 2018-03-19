package com.bluemobi.product.common;

import java.io.Serializable;

/**
 * @description : App分页封装实体(默认为MySql 不考虑数据库切换)
 * @Author: chenb
 * @Date: 2014年11月5日 下午8:24:19
 */
public class AppPager implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long pageNumber = 0L; // 页码 从第几条记录开始查询
	private Long pageNum = 20L;// 条数：默认20

	AppPager() {

	}

	AppPager(Long pageNumber, Long pageNum) {
		if (null != pageNumber && pageNumber > 0) {
			this.pageNumber = pageNum*(pageNumber - 1);
		}
		this.pageNum = pageNum;
	}

	public Long getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Long pageNumber) {
		if (null != pageNumber && pageNumber > 0) {
			this.pageNumber = pageNum*(pageNumber - 1);
		} 
	}

	public Long getPageNum() {
		return pageNum;
	}

	public void setPageNum(Long pageNum) {
		this.pageNum = pageNum;
	}
}
