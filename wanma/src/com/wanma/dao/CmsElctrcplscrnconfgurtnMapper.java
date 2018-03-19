package com.wanma.dao;

import java.util.List;
import com.wanma.model.TblElctrcplscrnconfgurtn;

/**
 * @Description: 桩体配置参数操作dao
 * @author songjf
 * @createTime：2015-4-1 上午10:55:37
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface CmsElctrcplscrnconfgurtnMapper {

	/**
	 * @Title: findById
	 * @Description: 根据id获取桩体配置参数信息
	 * @param pkElctrcplscrnconfgurtn
	 *            主键
	 * @return
	 */
	public TblElctrcplscrnconfgurtn findById(
			java.lang.Integer pkElctrcplscrnconfgurtn);

	/**
	 * @Title: findByEpscType
	 * @Description: 根据配置类型获取桩体配置参数信息
	 * @param elctrcplscrnconfgurtn
	 * @return
	 */
	public <T, K, V> List<T> findByEpscType(
			TblElctrcplscrnconfgurtn elctrcplscrnconfgurtn);

	/**
	 * @Title: findCount
	 * @Description: 根据配置类型获取桩体配置参数总数
	 * @param elctrcplscrnconfgurtn
	 * @return
	 */
	public <K, V> long findCount(TblElctrcplscrnconfgurtn elctrcplscrnconfgurtn);

	/**
	 * @Title: insert
	 * @Description: 新增桩体配置参数
	 * @param params
	 * @return
	 */
	public int insertInfo(TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn);

	/**
	 * @Title: updateById
	 * @Description: 根据id更新桩体配置参数
	 * @param tblElctrcplscrnconfgurtn
	 * @return
	 */
	public int updateById(TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn);

	/**
	 * @Title: deleteById
	 * @Description: 根据id删除桩体配置参数
	 * @param params
	 * @return
	 */
	public int deleteById(java.lang.Integer pkElctrcplscrnconfgurtn);

}
