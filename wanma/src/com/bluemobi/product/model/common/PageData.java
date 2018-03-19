package com.bluemobi.product.model.common;

import java.io.Serializable;

public class PageData implements Serializable {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = -7454351535537701012L;

	private Integer totalnum;
	private Integer totalpage;
	private Integer currentpage;
	private Object info;

	public Integer getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
	}

	public Integer getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(Integer totalpage) {
		this.totalpage = totalpage;
	}

	public Integer getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}
}
