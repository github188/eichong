package com.wanma.web.dao;


import java.util.List;
import java.util.Map;

import com.wanma.model.TblAppointmentinstallationorder;
import com.wanma.page.Page;

/**
 * 数据访问接口
 *
 */
public interface WebAppointmentinstallationorderMapper {    
    public final static String PREFIX = WebAppointmentinstallationorderMapper.class.getName();
    
	public TblAppointmentinstallationorder get(java.lang.Integer pkAppointmentinstallationorder);
	
	public <K, V> Map<K, V> findOne(java.lang.Integer pkAppointmentinstallationorder);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(TblAppointmentinstallationorder tblAppointmentinstallationorder);
	
	public int update(TblAppointmentinstallationorder tblAppointmentinstallationorder);
	
	public int delete(java.lang.Integer pkAppointmentinstallationorder );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


