package com.wanma.page;

import java.util.Collection;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class PageContainer<E, K, V> implements Page<E> {

	/** 每页显示容量 */
	public static final int DEFAULT_PAGE_ROWS = 10;

	public int pagesize = DEFAULT_PAGE_ROWS;

	/** 记录的总数 */
	private long totalRecords = 0;

	/** 记录的总页数 */
	private static int pageCount = 0;

	/** 得到的页码 */
	public int current = 1;

	/** 记录集合 */
	private Collection<E> records;

	private Map<K, V> conditions;

	public PageContainer(long totalRecords, int pagesize, int current, Collection<E> records, Map<K, V> conditions) {
		this.totalRecords = totalRecords;
		this.pagesize = pagesize;
		this.current = current;
		this.records = records;
		this.conditions = conditions;
	}

	@Override
	public long getTotal() {
		return totalRecords;
	}

	@Override
	public int getPageCount() {
		pageCount = (int) (totalRecords / pagesize);
		if (totalRecords % pagesize != 0) {
			pageCount++;
		}
		return pageCount;
	}

	@Override
	public int getPageSize() {
		return pagesize;
	}

	@Override
	public int getCurrent() {
		if (current > getPageCount()) {
			return getPageCount();
		} else if (current < 1) {
			return 1;
		} else {
			return current;
		}
	}

	@Override
	public int getStart() {
		return (current * pagesize) - pagesize;
	}

	@Override
	public int getEnd() {
		return current * pagesize;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<K, V> getConditionsMap() {
		return this.conditions;
	}

	@Override
	public String getConditionsString() {
		JSONObject json=new JSONObject();
		return json.toJSONString(this.conditions);
	}

	@Override
	public Collection<E> getRows() {
		return this.records;
	}

}
