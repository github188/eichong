package com.wanma.service;

import java.util.List;
import com.wanma.model.TblConfigparameter;

/**
 * @Description: 配置参数业务处理接口
 * @author songjf
 * @createTime：2015-4-2 上午10:50:57
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface CmsConfigparameterService {

	/**
	 * @Title: findConPara
	 * @Description: 获取配置参数
	 * @param params
	 * @return
	 */
	public TblConfigparameter findConPara(Integer pkConfigparameter);

	/**
	 * @Title: findConParaList
	 * @Description: 获取配置参数列表
	 * @param params
	 * @return
	 */
	public List<TblConfigparameter> findConParaList(
			TblConfigparameter tblConfigparameter);

	/**
	 * @Title: insertConPara
	 * @Description: 新增配置参数名称
	 * @param params
	 * @return
	 */
	public int insertConPara(TblConfigparameter tblConfigparameter);

	/**
	 * @Title: updateConPara
	 * @Description: 更新配置参数名称
	 * @param params
	 * @return
	 */
	public int updateConPara(TblConfigparameter tblConfigparameter);

	/**
	 * @Title: deleteById
	 * @Description: 删除配置参数名称
	 * @param params
	 * @return
	 */
	public int deleteById(TblConfigparameter tblConfigparameter);

	/**
	 * @Title: findCount
	 * @Description: 获取配置参数总数
	 * @param params
	 * @return
	 */
	public long findCount(TblConfigparameter tblConfigparameter);

}
