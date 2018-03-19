package com.wanma.web.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblEquipmentrepair;
import com.wanma.page.Page;

/**
 * 数据访问接口
 * 
 */
public interface WebEquipmentrepairMapper {
	public final static String PREFIX = WebEquipmentrepairMapper.class.getName();

	public TblEquipmentrepair get(java.lang.Integer pkEquipmentrepair);

	public <K, V> Map<K, V> findOne(java.lang.Integer pkEquipmentrepair);

	public <T, K, V> List<T> find(Map<K, V> params);

	public int insert(TblEquipmentrepair tblEquipmentrepair);

	public int update(TblEquipmentrepair tblEquipmentrepair);

	public int delete(java.lang.Integer pkEquipmentrepair);

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}
