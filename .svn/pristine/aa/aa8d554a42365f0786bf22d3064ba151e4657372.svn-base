 package com.wanma.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wanma.model.TblProductcomment;
import com.wanma.model.TblUsermessage;
import com.wanma.page.Page;

/**
 * @Description: 产品评论操作dao接口
 * @author songjf
 * @createTime：2015-3-16 下午04:20:35
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface WebProductcommentMapper {
	public final static String PREFIX = WebProductcommentMapper.class.getName();

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

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

	/**
	 * @Title: findProComments
	 * @Description: 获取产品评论
	 * @param params
	 * @return
	 */
	public <T, K, V> List<T> findProComments(Map<K, V> params);
	/**产品评论分页使用*/
	public <K, V> long countProComments(Map<K, V> params);
	/**
	 * @Title: findProComments
	 * @Description: 获取电站评论
	 * @param params
	 * @return
	 */
	public List<TblProductcomment> findProCommentsByPowerId(TblProductcomment tblProductcomment);
	/**
	 * @Title: findProComments
	 * @Description: 获取用户评论
	 * @param params
	 * @return
	 */
	public <T, K, V> List<T> findProCommentsByUser(Map<K, V> params);
	/**用户评论分页使用*/
	public <K, V> long countProCommentsByUser(Map<K, V> params);
	
	/**
	 * 根据用户id，获取评论列表
	 * @param userId
	 * @return
	 */
	 public List<HashMap<String, Object>> findProCommentsByUserId(int userId);
	
	 
	 //获取当前用户的全部评论
	 public List<HashMap<String, Object>> getProCommentsByUserId(Map<String,Object> params); 
	 
	 public  long countComments(Map<String,Object>params);	 
	 
	 //根据评论ID获取单个评论
	 public HashMap<String, Object> getCommentById(int pkComment);

}
