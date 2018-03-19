package com.wanma.page;

import java.util.Collection;
import java.util.Map;

/**
 * 
 * 分页器接口，根据page,pageSize,total用于页面上分页显示多项内容，计算页码和当前页的偏移量，方便页面分页使用.
 * 
 */
public interface Page<E> {
	/**
	 * 取总记录数
	 */
	public long getTotal();

	/**
	 * 取总页数.
	 */
	public int getPageCount();

	/**
	 * 取每页数据容量
	 */
	public int getPageSize();

	/**
	 * 得到当前页码
	 */
	public int getCurrent();

	/**
	 * 得到该页的数据
	 */
	public Collection<E> getRows();

	/**
	 * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
	 */
	public int getStart();

	/**
	 * 获取任一页第后一条数据在数据集的位置，每页条数使用默认值.
	 */
	public int getEnd();

	/**
	 * 获取分页查询所用条件
	 */
	public <K, V> Map<K, V> getConditionsMap();

	/**
	 * 获取分页查询所用条件（JSON格式）
	 */
	public String getConditionsString();

}
