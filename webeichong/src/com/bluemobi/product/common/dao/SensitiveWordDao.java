/**
 * FileName:AuthorizedDao.java
 * Author: Administrator
 * Create: 2014年7月1日
 * Last Modified: 2014年7月1日
 * Version: V1.0 
 */
package com.bluemobi.product.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 关键字相关查询DAO
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月1日
 */
public class SensitiveWordDao extends CommonDao {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(SensitiveWordDao.class);

	/**
	 * 查询关键字列表
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userId
	 *            用户ID
	 * @param actionId
	 *            功能ID
	 * @return int 用户数
	 * @throws 无
	 */
	public List<String> getSensitiveWordList() {
		// 关键字内容
		String wordContent = "";
		// 关键字列表
		List<String> contentList = new ArrayList<String>();

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append("    word_content,");
		sql.append("    length(word_content) as data_length ");
		sql.append(" from tb_mask_words");
		sql.append(" order by word_content asc,data_length desc");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				// 取得关键字内容
				wordContent = resultSet.getString("word_content");
				// 键入到列表
				contentList.add(wordContent);
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回关键字内容
		return contentList;
	}

}
