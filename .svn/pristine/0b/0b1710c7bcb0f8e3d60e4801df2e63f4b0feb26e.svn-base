package com.wanma.app.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblOrderdetail;
import com.wanma.page.Page;

/**
 * 数据访问接口
 * 
 */
public interface AppOrderdetailMapper {
	public final static String PREFIX = AppOrderdetailMapper.class.getName();

	public TblOrderdetail get(java.lang.Integer pkOrderdetail);

	public <K, V> Map<K, V> findOne(java.lang.Integer pkOrderdetail);

	public <T, K, V> List<T> find(Map<K, V> params);

	/**
	 * @Title: insert
	 * @Description: 新增订单详情
	 * @param params
	 * @return
	 */
	public int insert(TblOrderdetail tblOrderdetail);

	public int update(TblOrderdetail tblOrderdetail);

	/**
	 * @Title: delete
	 * @Description: 删除订单详情
	 * @param params
	 * @return
	 */
	public int delete(java.lang.Integer ordeParentid);

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

	/**
	 * @Title: selectProductByOrderId
	 * @Description: 获取订单中商品信息
	 * @param params
	 * @return
	 */
	public <T> List<T> selectProductByOrderId(java.lang.Integer orDeParentId);

}
