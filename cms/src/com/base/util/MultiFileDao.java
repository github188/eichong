/**
 * FileName:MultipartFileDao.java
 * Author: Administrator
 * Create: 2014年8月10日
 * Last Modified: 2014年8月10日
 * Version: V1.0 
 */
package com.base.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;
import com.base.common.CommonConsts;
import com.base.common.GlobalSystem;

@SuppressWarnings("deprecation")
public class MultiFileDao {
	private static SimpleJdbcTemplate jdbcTemplate;

	static {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(GlobalSystem.getConfig("jdbc.url"));
		dataSource.setUsername(GlobalSystem.getConfig("jdbc.username"));// 用户名
		dataSource.setPassword(GlobalSystem.getConfig("jdbc.password"));// 密码
		dataSource.setInitialSize(2);
		dataSource.setMaxActive(20);
		dataSource.setMinIdle(0);
		dataSource.setMaxWait(60000);
		dataSource.setValidationQuery("SELECT 1");
		dataSource.setTestOnBorrow(false);
		dataSource.setTestWhileIdle(true);
		dataSource.setPoolPreparedStatements(false);
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	/**
	 * 取得所有文件url
	 * 
	 * @param bussinessType
	 *            业务分类
	 * @param referenceId
	 *            关联ID
	 * @param fileName
	 *            文件名
	 * @return 所有文件url
	 */
	public List<String> getUrls(String bussinessType, String referenceId,
			String fileName) {
		List<String> urlList = new ArrayList<String>();
		String filePrefix = GlobalSystem
				.getConfig(CommonConsts.PICTURE_SERVICE_SCANURL);
		String sql = "SELECT relative_path, "
				+ "RIGHT ( LEFT ( reverse(file_name), instr(reverse(file_name), '.') + 1 ), 1 ) AS sort_number,"
				+ " file_name FROM tb_multi_media"
				+ " WHERE bussiness_type = ? " + " AND reference_id = ?";
		if (ObjectUtil.isNotEmpty(fileName)) {
			sql += " AND file_name = ? ";
		}
		sql += " ORDER BY title_flg DESC, create_date ASC, sort_number ASC";
		Object[] args = null;
		if (ObjectUtil.isNotEmpty(fileName)) {
			args = new Object[] { bussinessType, referenceId, fileName };
		} else {
			args = new Object[] { bussinessType, referenceId };
		}
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
		for (Map<String, Object> result : list) {
			urlList.add(filePrefix + result.get("relative_path") + "/"
					+ result.get("file_name"));
		}
		return urlList;
	}

	/**
	 * 删除某业务下某关联ID的所有文件
	 * 
	 * @param bussinessType
	 *            业务分类
	 * @param referenceId
	 *            关联ID
	 */
	public void deleteFile(String bussinessType, String referenceId,String fileName) {
		String sql = "delete from tb_multi_media WHERE bussiness_type = ? "
				+ " AND reference_id = ?";
		if (ObjectUtil.isNotEmpty(fileName)) {
			sql += " AND file_name = ? ";
		}
		Object[] args = null;
		if (ObjectUtil.isNotEmpty(fileName)) {
			args = new Object[] { bussinessType, referenceId, fileName };
		} else {
			args = new Object[] { bussinessType, referenceId };
		}
		jdbcTemplate.update(sql, args);
	}

	/**
	 * 保存文件记录
	 * 
	 * @param bussinessType
	 *            业务分类
	 * @param referenceId
	 *            关联ID
	 * @param relativePath
	 *            文件相对路径
	 * @param fileName
	 *            文件名
	 * @param isTitle
	 *            是否是标题文件
	 */
	public void saveFile(String bussinessType, String referenceId,
			String relativePath, String fileName, boolean isTitle) {
		String fileId = UUIDUtil.uuid();
		String titleFlg = isTitle ? "1" : "0";
		String user = "system";
		String sql = "insert into tb_multi_media"
				+ "(file_id,bussiness_type,reference_id,relative_path,file_name,"
				+ "create_user,last_update_user, title_flg) values(?,?,?,?,?,?,?,?)";
		Object[] args = new Object[] { fileId, bussinessType, referenceId,
				relativePath, fileName, user, user, titleFlg };
		jdbcTemplate.update(sql, args);
	}

	/**
	 * 保存文件记录
	 * 
	 * @param bussinessType
	 *            业务分类
	 * @param referenceId
	 *            关联ID
	 * @param relativePath
	 *            文件相对路径
	 * @param fileName
	 *            文件名
	 */
	public void saveFile(String bussinessType, String referenceId,
			String relativePath, String fileName) {
		saveFile(bussinessType, referenceId, relativePath, fileName, false);
	}
}
