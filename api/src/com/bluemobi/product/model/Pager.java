package com.bluemobi.product.model;

public class Pager {
	protected long page = 1;
	protected long page_prev;
	protected long page_next;
	protected long page_size = 50;
	protected long page_count;
	protected long total;
	protected long offset = page_size*(page-1);
	
	public Pager(){}
	
	public Pager(long total){
		this.total = total;
	}
	
	public Pager(long page,  long total){
		this(total);
		if(total == 0) {
			this.page = 0;
			this.page_size = 0;
		}
		else this.page = page;
	}
	
	public Pager(long page, long page_size, long total){
		this(page, total);
		this.page_size = page_size;
	}
	
	public long getPage() {
		return page;
	}
	public void setPage(long page) {
		this.page = page;
	}
	public long getPage_size() {
		return page_size;
	}
	public void setPage_size(long page_size) {
		this.page_size = page_size;
	}
	public long getPage_count() {
		//计算共有多少页
		if(0 == total) return 0;
		else return (total+page_size-1)/page_size;
	}
	public long getOffset() {
		if(0 == total) return 0;
		//计算位移值
		return page_size*(page-1);
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPage_prev() {
		//计算前一页页码
		return page<=1? page : page-1;
	}

	public void setPage_prev(long page_prev) {
		this.page_prev = page_prev;
	}

	public long getPage_next() {
		if(0 == getPage_count()) return 1;
		//计算后一页页码
		return page>=getPage_count()? getPage_count() : page+1;
	}

	public void setPage_next(long page_next) {
		this.page_next = page_next;
	}
}
