package com.wanma.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wanma.model.TblConfigcontent;

/**
 * @Description: 配置参数内容操作dao
 * @author songjf
 * @createTime：2015-4-2 下午02:02:28
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface CmsConfigcontentMapper {

	/**
	 * @Title: findContent
	 * @Description: 获取配置参数内容
	 * @param params
	 * @return
	 */
	public TblConfigcontent findContent(java.lang.Integer pkConfigcontent);

	/**
	 * @Title: findContentList
	 * @Description: 获取配置参数内容列表
	 * @param params
	 * @return
	 */
	public <T> List<T> findContentList(TblConfigcontent tblConfigcontent);

	/**
	 * @Title: findCount
	 * @Description: 获取配置参数内容总数
	 * @param params
	 * @return
	 */
	public long findCount(TblConfigcontent tblConfigcontent);

	/**
	 * @Title: insertContent
	 * @Description: 新增配置参数内容
	 * @param params
	 * @return
	 */
	public int insertContent(TblConfigcontent tblConfigcontent);

	/**
	 * @Title: updateContent
	 * @Description: 更新配置参数内容
	 * @param params
	 * @return
	 */
	public int updateContent(TblConfigcontent tblConfigcontent);

	/**
	 * @Title: deleteById
	 * @Description: 删除配置参数内容
	 * @param params
	 * @return
	 */
	public int deleteById(TblConfigcontent tblConfigcontent);
	
	

	/**
	 * 根据主配置id获取配置项列表
	 * @return
	 */
	public List<Map<String, Object>> getConfigContentListByCpId(@Param(value="cpId")String cpId);

	public int getConfigIdByContent(Map<String, String> map);

}
