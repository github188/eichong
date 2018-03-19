package com.wanma;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionTemplate;

public class SqlSessionDaoSupport implements DaoSupport {
	private SqlSessionTemplate sqlSession;

	public SqlSessionDaoSupport(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insert(String statement, Object parameter) {
		return sqlSession.insert(statement, parameter);
	}

	@Override
	public int update(String statement, Object parameter) {
		return sqlSession.update(statement, parameter);
	}

	@Override
	public <K, V, T> T get(String statement, Map<K, V> parameter) {
		return sqlSession.selectOne(statement, parameter);
	}

	@Override
	public <K, V> Map<K, V> findOne(String statement, Map<K, V> parameter) {
		return sqlSession.selectOne(statement, parameter);
	}

	@Override
	public <K, V> int delete(String statement, Map<K, V> parameter) {
		return sqlSession.delete(statement, parameter);
	}

	@Override
	public <E, K, V> List<E> find(String statement, Map<K, V> parameter) {
		return sqlSession.selectList(statement, parameter);
	}

	@Override
	public <E> List<E> find(String statement) {
		return sqlSession.selectList(statement);
	}

	@Override
	public Connection getConnection() {
		return sqlSession.getConnection();
	}

	@Override
	public Configuration getConfiguration() {
		return sqlSession.getConfiguration();
	}

	@Override
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSession;
	}

  

}
