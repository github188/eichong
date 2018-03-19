package com.wanma.service;

import java.util.List;
import com.wanma.model.TblElctrcplscrnconfgurtn;

/**
 * @Description: 桩体配置参数业务处理接口
 * @author songjf
 * @createTime：2015-4-1 上午11:33:44
 * @version：V1.0
 */
public interface CmsElctrcplscrnconfgurtnService {

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
	 * @param params
	 * @return
	 */
	public List<TblElctrcplscrnconfgurtn> findByEpscType(
			TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn);

	/**
	 * @Title: findCount
	 * @Description: 根据配置类型获取桩体配置参数总数
	 * @param params
	 * @return
	 */
	public long findCount(TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn);

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
	 * @param pkElctrcplscrnconfgurtn
	 * @return
	 */
	public int deleteById(java.lang.Integer pkElctrcplscrnconfgurtn);

	/**
	 * @Title: deleteEpscs
	 * @Description: 桩体配置参数，批量删除配置名称s
	 * @param pkElctrcplscrnconfgurtns
	 *            所有需要删除的主键，以英文逗号分隔
	 * @return
	 */
	public void deleteEpscs(String pkElctrcplscrnconfgurtns);

}
