package com.wanma.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import com.wanma.dubbox.service.impl.CmsRedisCacheableServiceImpl;

/**
 * 初始化数据库字段映射信息
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月10日
 */
public class InitDeployUrlServelt extends HttpServlet {
	private static final Logger logger = Logger
			.getLogger(InitDeployUrlServelt.class);
	private static final ApplicationContext context = ApplicationContextUtils.getApplicationContext();
	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = -1447273313093174751L;

	/**
	 * 初始化字段映射到内存
	 */
	public void init() throws ServletException {
		long begin = System.currentTimeMillis();
		initColumnsMap();
		long end = System.currentTimeMillis();
		logger.info("字段映射到内存！启动耗时：【" + (end - begin) / 1000d + "】秒");
		begin = System.currentTimeMillis();
		baseData2Redis();
		end = System.currentTimeMillis();
		logger.info("基础数据缓存！耗时：【" + (end - begin) / 1000d + "】秒");
	}

	public void initColumnsMap() {
		// 获取mybatis映射mapping
		Map<String, String> columnMap = null;
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) context
				.getBean("sqlSessionFactory");
		Collection<ResultMap> maps = sqlSessionFactory.getConfiguration()
				.getResultMaps();
		Iterator<ResultMap> it = maps.iterator();
		Object obj = null;
		ResultMap map = null;
		while (it.hasNext()) {
			obj = it.next();
			if (obj.getClass().toString().contains("Ambiguity")) {
				continue;
			}
			map = (ResultMap) obj;
			columnMap = new HashMap<String, String>();
			List<ResultMapping> mappings = map.getResultMappings();
			for (ResultMapping mapping : mappings) {
				columnMap.put(mapping.getProperty(), mapping.getColumn());
			}
			Global.CLOUMNS_MAP.put(
					map.getType().toString().replace("class ", ""), columnMap);
		}
	}

	public void baseData2Redis() {
		CmsRedisCacheableServiceImpl cacheableService = (CmsRedisCacheableServiceImpl) context
				.getBean("cmsRedisCacheableServiceImpl");
		cacheableService.cachedDataInit(context);
	}
}
