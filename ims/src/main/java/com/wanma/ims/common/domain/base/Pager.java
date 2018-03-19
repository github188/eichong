package com.wanma.ims.common.domain.base;
 
import java.io.Serializable;

public class Pager implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String status = "";
	protected String keywords = "";
	protected Long pageNo = 1L;
	protected Long pageSize = 20L;
	protected Long pageTotal = 0L;
	protected Long total = 0L;
	private Long offset = 0L;
	
	public Pager(){}
	
	
	
	public Pager(Long pageNo, Long pageSize, Long total){
		if(null != pageNo && pageNo > 0)
			this.pageNo = pageNo;
		if(null != pageSize && pageSize > 0)
			this.pageSize = pageSize;
		if(null != total && total > 0)
			this.total = total;
		calculate();
	}
	
	private void calculate(){
		if(total <= 0) return;
		pageTotal = (total + pageSize - 1) / pageSize;
		if(pageNo < 1) pageNo = 1L;
		else if(pageNo > pageTotal) pageNo = pageTotal;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Long getPageNo() {
		return pageNo;
	}
	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
		calculate();
	}
	public Long getPageSize() {
		return pageSize;
	}
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
		calculate();
	}
	public Long getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(Long pageTotal) {
		this.pageTotal = pageTotal;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
		if(0 >= total) {
			pageNo = 0L;
			pageTotal = 0L;
		}else{
			calculate();
		}
	}

	public Long getOffset() {
		offset = pageSize * (pageNo - 1);
		return offset > 0 ? offset : 0;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}
	
}
