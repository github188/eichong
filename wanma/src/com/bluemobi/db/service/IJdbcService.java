package com.bluemobi.db.service;

import java.util.List;

public interface IJdbcService {

	/**查询多列结果集*/
	List<Object[]> find(String sql);
	/**查询一列结果集*/
	List<Object> findOneColumn(String sql);

}

