package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblJpush;

/**
 * @Description: 极光推送操作dao
 * @author songjf
 * @createTime：2015-3-18 下午07:15:38
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface AppJpushMapper {
	public final static String PREFIX = AppJpushMapper.class.getName();

	/**
	 * @Title: getByuserInfo
	 * @Description: 根据用户id获取用户推送信息
	 * @param params
	 * @return
	 */
	public TblJpush getByuserInfo(java.lang.Integer jpushUserInfo);

	public <K, V> Map<K, V> findOne(java.lang.Integer pkJpush);

	public <T, K, V> List<T> find(Map<K, V> params);

	/**
	 * @Title: insert
	 * @Description: 新增用户推送信息
	 * @param params
	 * @return
	 */
	public int insert(TblJpush tblJpush);

	/**
	 * @Title: update
	 * @Description: 更新用户推送信息
	 * @param params
	 * @return
	 */
	public int update(TblJpush tblJpush);

	public int delete(java.lang.Integer pkJpush);


}
