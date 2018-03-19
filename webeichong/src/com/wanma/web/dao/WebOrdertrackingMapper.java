package com.wanma.web.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblOrdertracking;

/**
 * @Description: 订单跟踪操作dao接口
 * @author songjf
 * @createTime：2015-3-24 下午03:45:50
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface WebOrdertrackingMapper {
	public final static String PREFIX = WebOrdertrackingMapper.class.getName();

	public TblOrdertracking get(java.lang.Integer pkOrdertracking);

	public <K, V> Map<K, V> findOne(java.lang.Integer pkOrdertracking);

	/**
	 * @Title: findOrderTracks
	 * @Description: 获取订单跟踪详情
	 * @param params
	 * @return
	 */
	public <T, K, V> List<T> findOrderTracks(java.lang.Integer pkOrder);

	/**
	 * @Title: insert
	 * @Description: 新增订单跟踪信息
	 * @param params
	 * @return
	 */
	public int insert(TblOrdertracking tblOrdertracking);

	public int update(TblOrdertracking tblOrdertracking);

	public int delete(java.lang.Integer pkOrdertracking);

}
