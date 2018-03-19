package com.wanma;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionTemplate;

public interface DaoSupport {
	public int insert(String statement, Object parameter);

	public int update(String statement, Object parameter);

	public <K, V, T> T get(String statement, Map<K, V> parameter);

	public <K, V> Map<K, V> findOne(String statement, Map<K, V> parameter);

	public <K, V> int delete(String statement, Map<K, V> parameter);

	public <E, K, V> List<E> find(String statement, Map<K, V> parameter);

	public <E> List<E> find(String statement);


	public Connection getConnection();

	public Configuration getConfiguration();

	public SqlSessionTemplate getSqlSessionTemplate();



}
