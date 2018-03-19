package com.wanma.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wanma.model.TblPowerstation;
import com.wanma.model.TblProductcomment;
import com.wanma.page.Page;

/**
 * @Description: 产品评论操作dao接口
 * @author songjf
 * @createTime：2015-3-16 下午04:20:35
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface AppProductcommentMapper {
	public final static String PREFIX = AppProductcommentMapper.class.getName();

	public TblProductcomment get(java.lang.Integer pkProductcomment);

	public <K, V> Map<K, V> findOne(java.lang.Integer pkProductcomment);

	public <T, K, V> List<T> find(Map<K, V> params);

	/**
	 * @Title: insert
	 * @Description: 新增产品评论
	 * @param params
	 * @return
	 */
	public int insert(TblProductcomment tblProductcomment);

	public int update(TblProductcomment tblProductcomment);

	public int delete(java.lang.Integer pkProductcomment);
	
	/**
	 * @Title: getMaxStar
	 * @Description:取得最大星级
	 * @param params
	 * @return
	 */
	public Double getMaxStar(TblProductcomment tblProductcomment);
	
	/**
	 * @Title: searchIdCount
	 * @Description:查询单个id的总评论数
	 * @param params
	 * @return
	 */
	public long searchIdCount(TblProductcomment tblProductcomment);

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

	/**
	 * @Title: findProComments
	 * @Description: 获取产品评论
	 * @param params
	 * @return
	 */
	public <T, K, V> List<T> findProComments(Map<K, V> params);
	/**
	 * @Title: findProComments
	 * @Description: 获取电站评论
	 * @param params
	 * @return
	 */
	public  List<?> findProCommentsByPowerId(TblProductcomment tblProductcomment);
	/**
	 * @Title: findProComments
	 * @Description: 获取用户评论
	 * @param params
	 * @return
	 */
	public <T, K, V> List<T> findProCommentsByUser(TblProductcomment tblProductcomment);
	
	/**
	 * 根据用户id，获取评论列表
	 * @param userId
	 * @return
	 */
	public List<HashMap<String, Object>> findProCommentsByUserId(int userId);

}
