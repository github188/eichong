/**
 * FileName:BaseMybatisInterceptor.java
 * Author: Administrator
 * Create: 2014年7月8日
 * Last Modified: 2014年7月8日
 * Version: V1.0 
 */
package com.wanma.common;

import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.log4j.Logger;

/**
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月8日
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class BaseMybatisInterceptor implements Interceptor {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger("SQL DEBUG:");

	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

	private Properties properties;

	/**
	 * SQL解析时，增加数据权限设置
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		StatementHandler statementHandler = (StatementHandler) invocation
				.getTarget();

		statementHandler.getBoundSql().getParameterMappings();

		BoundSql boundSql = statementHandler.getBoundSql();

		MetaObject metaStatementHandler = MetaObject.forObject(
				statementHandler, DEFAULT_OBJECT_FACTORY,
				DEFAULT_OBJECT_WRAPPER_FACTORY);

		Configuration configuration = (Configuration) metaStatementHandler
				.getValue("delegate.configuration");
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler
				.getValue("delegate.mappedStatement");
		String sqlId = mappedStatement.getId();

		String originalSql = (String) metaStatementHandler
				.getValue("delegate.boundSql.sql");
		List<XMLModel> xmlModelList = DataAuthXmlReader
				.getAuthSqlSetting(sqlId);

		log.info(getSql(configuration, boundSql, sqlId, 10));

		// 如果该SQL没有设置数据权限控制，跳出以下处理，继续执行
		if (xmlModelList == null || xmlModelList.size() < 0 
//				|| UserUtil.isManagerUser()
				) {
			return invocation.proceed();
		}

		// 向Mapper设定的SQL中加入权限限制
		originalSql = AuthorizedSqlCreater.getDataAuthSql(originalSql,
				xmlModelList);

		// 重置SQL
		metaStatementHandler.setValue("delegate.boundSql.sql", originalSql);

		log.info(getSql(configuration, boundSql, sqlId, 10));

		return invocation.proceed();

	}

	public static String getSql(Configuration configuration, BoundSql boundSql,
			String sqlId, long time) {
		String sql = showSql(configuration, boundSql);
		StringBuilder str = new StringBuilder(100);
		str.append(sqlId);
		str.append(":");
		str.append(sql);
		str.append(":");
		str.append(time);
		str.append("ms");
		return str.toString();
	}

	private static String getParameterValue(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(
					DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(new Date()) + "'";
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "";
			}

		}

		if (value != null && value.indexOf("$") >= 0) {
			value = value.replaceAll("\\$","\\\\\\$");
		}
		return value;
	}

	public static String showSql(Configuration configuration, BoundSql boundSql) {
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		if (parameterMappings.size() > 0 && parameterObject != null) {
			TypeHandlerRegistry typeHandlerRegistry = configuration
					.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?",
						getParameterValue(parameterObject));

			} else {
				MetaObject metaObject = configuration
						.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(obj));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object obj = boundSql
								.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(obj));
					}
				}
			}
		}
		return sql;
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties0) {
		this.properties = properties0;
	}

	public Properties setProperties() {
		return this.properties;
	}
}