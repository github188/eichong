/**
 * FileName:MultipartFileDao.java
 * Author: Administrator
 * Create: 2014年8月10日
 * Last Modified: 2014年8月10日
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

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.model.common.BasicMutiFileModel;
import com.bluemobi.product.utils.UUIDUtil;

/**
 * 文件工具DAO
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月10日
 */
public class MultipartFileDao extends CommonDao {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(MultipartFileDao.class);

	/**
	 * 取得所有文件url
	 * 
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public List<String> getAllMultiUrl(String type, String referenceId,
			boolean isServer) {
		// 图片链接列表
		List<String> urlList = new ArrayList<String>();
		String deployUrl = "";
		String parentPath = "";
		String filePre = "";

		MessageManager manager = MessageManager.getMessageManager();

		if (!isServer) {
			deployUrl = manager.getSystemProperties("deploy.url");
		}
		parentPath = manager.getSystemProperties("storage.path.real.file");

		//filePre = deployUrl + parentPath + "/";
		 filePre =  manager.getSystemProperties(CommonConsts.PICTURE_SERVICE_SCANURL);
		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append(" select ");
		// 相对路径
		sql.append("     relative_path,");
		// 相对路径
		sql.append(" right(left(reverse(file_name),instr(reverse(file_name),'.') + 1),1) as sort_number,");
		// 文件名称
		sql.append("     file_name");

		sql.append(" from");
		sql.append("     tb_multi_media");

		sql.append(" where");
		// 业务分类
		sql.append("     bussiness_type = ?");
		// 关联ID
		sql.append("     and reference_id = ?");
		// 排序(默认标识降序、排序号升序)
		sql.append(" order by title_flg desc,create_date asc, sort_number asc");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());
			pstmt.setString(1, type);
			pstmt.setString(2, referenceId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				String url = "";
				// 相对路径
				String relativePath = "";
				// 文件名
				String fileName = "";
				//
				// 取得查询结果
				//
				// 相对路径
				relativePath = resultSet.getString("relative_path");
				// 文件名
				fileName = resultSet.getString("file_name");

				url = filePre + relativePath + "/" + fileName;

				// 加入到图片链接列表
				urlList.add(url);
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回图片链接列表
		return urlList;
	}

	/**
	 * 取得更多文件url
	 * 
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public List<String> getOtherMultiUrl(String type, String referenceId,
			boolean isServer) {
		// 图片链接列表
		List<String> urlList = new ArrayList<String>();
		String deployUrl = "";
		String parentPath = "";
		String filePre = "";

		MessageManager manager = MessageManager.getMessageManager();

		if (!isServer) {
			deployUrl = manager.getSystemProperties("deploy.url");
		}
		parentPath = manager.getSystemProperties("storage.path.real.file");

		filePre = deployUrl + parentPath + "/";

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append(" select ");
		// 相对路径
		sql.append("     relative_path,");
		// 相对路径
		sql.append(" right(left(reverse(file_name),instr(reverse(file_name),'.') + 1),1) as sort_number,");
		// 文件名称
		sql.append("     file_name");

		sql.append(" from");
		sql.append("     tb_multi_media");

		sql.append(" where");
		// 业务分类
		sql.append("     bussiness_type = ?");
		// 关联ID
		sql.append("     and reference_id = ?");
		// 关联ID
		sql.append("     and title_flg != '1'");
		// 排序(创建时间升序、排序号升序)
		sql.append(" order by create_date asc, sort_number asc");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());
			pstmt.setString(1, type);
			pstmt.setString(2, referenceId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				String url = "";
				// 相对路径
				String relativePath = "";
				// 文件名
				String fileName = "";
				//
				// 取得查询结果
				//
				// 相对路径
				relativePath = resultSet.getString("relative_path");
				// 文件名
				fileName = resultSet.getString("file_name");

				url = filePre + relativePath + "/" + fileName;

				// 加入到图片链接列表
				urlList.add(url);
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回图片链接列表
		return urlList;
	}

	/**
	 * 取得所有文件名称
	 * 
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public List<String> getAllMultiFileName(String type, String referenceId) {
		// 图片链接列表
		List<String> urlList = new ArrayList<String>();

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append(" select ");
		// 相对路径
		sql.append("     relative_path,");
		// 相对路径
		sql.append(" right(left(reverse(file_name),instr(reverse(file_name),'.') + 1),1) as sort_number,");
		// 文件名称
		sql.append("     file_name");

		sql.append(" from");
		sql.append("     tb_multi_media");

		sql.append(" where");
		// 业务分类
		sql.append("     bussiness_type = ?");
		// 关联ID
		sql.append("     and reference_id = ?");
		// 排序(默认标识降序、排序号升序)
		sql.append(" order by title_flg desc,create_date asc, sort_number asc");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());
			pstmt.setString(1, type);
			pstmt.setString(2, referenceId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				// 文件名
				String fileName = "";
				//
				// 取得查询结果
				//
				fileName = resultSet.getString("file_name");
				// 加入到图片链接列表
				urlList.add(fileName);
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回图片链接列表
		return urlList;
	}

	/**
	 * 删除所有文件url
	 * 
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 无
	 */
	public void deleteAllMultiFile(String type, String referenceId) {

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append(" delete from ");
		sql.append("     tb_multi_media");

		sql.append(" where");
		// 业务分类
		sql.append("     bussiness_type = ?");
		// 关联ID
		sql.append("     and reference_id = ?");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());
			pstmt.setString(1, type);
			pstmt.setString(2, referenceId);
			// 执行删除处理
			pstmt.execute();
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

	}

	/**
	 * 删除所有文件url
	 * 
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @param fileName
	 *            文件名称
	 * @return 无
	 */
	public void deleteMultiFile(String type, String referenceId, String fileName) {

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append(" delete from ");
		sql.append("     tb_multi_media");

		sql.append(" where");
		// 业务分类
		sql.append("     bussiness_type = ?");
		// 关联ID
		sql.append("     and reference_id = ?");
		// 文件名称
		sql.append("     and file_name = ?");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());
			pstmt.setString(1, type);
			pstmt.setString(2, referenceId);
			pstmt.setString(3, fileName);
			// 执行删除处理
			pstmt.execute();
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

	}

	/**
	 * 追加其他图片
	 * 
	 * @param bussinessType
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 无
	 */
	public void addTitleMultiFile(String bussinessType, String referenceId,
			String relativePath, String fileName, String userId) {
		addMultiFile(bussinessType, referenceId, "1", relativePath, fileName,
				userId);
	}

	/**
	 * 追加其他图片
	 * 
	 * @param bussinessType
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 无
	 */
	public void addOtherMultiFile(String bussinessType, String referenceId,
			String relativePath, String fileName, String userId) {
		addMultiFile(bussinessType, referenceId, "0", relativePath, fileName,
				userId);
	}

	/**
	 * 删除所有文件url
	 * 
	 * @param bussinessType
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 无
	 */
	public void addMultiFile(String bussinessType, String referenceId,
			String titleFlg, String relativePath, String fileName, String userId) {
		// 文件ID
		String fileId = UUIDUtil.getUUID();

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append(" insert into ");
		sql.append("     tb_multi_media");
		// 文件ID
		sql.append("     (file_id,");
		// 业务分类
		sql.append("     bussiness_type,");
		// 关联ID
		sql.append("     reference_id,");
		// 相对路径
		sql.append("     relative_path,");
		// 文件名称
		sql.append("     file_name,");
		// 创建者
		sql.append("     create_user,");
		// 最后更新者
		sql.append("     last_update_user,");
		// 标题图片
		sql.append("     title_flg)");

		sql.append("     values(");
		// 文件ID
		sql.append("     ?,");
		// 业务分类
		sql.append("     ?,");
		// 关联ID
		sql.append("     ?,");
		// 相对路径
		sql.append("     ?,");
		// 文件名称
		sql.append("     ?,");
		// 创建者
		sql.append("     ?,");
		// 文件名称
		sql.append("     ?,");
		// 标题图片
		sql.append("     ?)");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());
			// 文件ID
			pstmt.setString(1, fileId);
			// 业务分类
			pstmt.setString(2, bussinessType);
			// 关联ID
			pstmt.setString(3, referenceId);
			// 相对路径
			pstmt.setString(4, relativePath);
			// 文件名称
			pstmt.setString(5, fileName);
			// 创建者
			pstmt.setString(6, userId);
			// 最后更新者
			pstmt.setString(7, userId);
			// 标题图片
			pstmt.setString(8, titleFlg);

			// 执行删除处理
			pstmt.execute();
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

	}

	/**
	 * 取得所有文件url
	 * 
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return List<BasicMutiFileModel> 所有文件对象
	 */
	public List<BasicMutiFileModel> getAllMultiObject(String type,
			String referenceId) {
		// 图片对象列表
		List<BasicMutiFileModel> objectlList = new ArrayList<BasicMutiFileModel>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append(" select ");
		// 相对路径
		sql.append("     title_flg,");
		// 相对路径
		sql.append(" right(left(reverse(file_name),instr(reverse(file_name),'.') + 1),1) as sort_number,");
		// 文件名称
		sql.append("     file_name");

		sql.append(" from");
		sql.append("     tb_multi_media");

		sql.append(" where");
		// 业务分类
		sql.append("     bussiness_type = ?");
		// 关联ID
		sql.append("     and reference_id = ?");
		// 排序(默认标识降序、排序号升序)
		sql.append(" order by title_flg desc,create_date asc, sort_number asc");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());
			pstmt.setString(1, type);
			pstmt.setString(2, referenceId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				// 标题图片标识
				String titleFlg = "";
				// 文件名
				String fileName = "";
				//
				// 取得查询结果
				//
				// 标题图片标识
				titleFlg = resultSet.getString("title_flg");
				// 文件名
				fileName = resultSet.getString("file_name");

				// 图片对象
				BasicMutiFileModel basicMutiFileModel = new BasicMutiFileModel();
				// 设置文件名
				basicMutiFileModel.setFileName(fileName);
				// 设置标题图片标识
				basicMutiFileModel.setIsTitle(titleFlg);

				// 加入到图片对象列表
				objectlList.add(basicMutiFileModel);
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回图片对象列表
		return objectlList;
	}
}
