package com.wanma.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblConfigcontent;

/**
 * @Description: 配置参数内容业务处理接口
 * @author songjf
 * @createTime：2015-4-2 下午02:06:54
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface CmsConfigcontentService {

	/**
	 * @Title: findContent
	 * @Description: 获取配置参数内容
	 * @param params
	 * @return
	 */
	public TblConfigcontent findContent(Integer pkConfigcontent);

	/**
	 * @Title: findContentList
	 * @Description: 获取配置参数内容列表
	 * @param params
	 * @return
	 */
	public List<TblConfigcontent> findContentList(
			TblConfigcontent tblConfigcontent);

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
	
	public List<Map<String, Object>> getConfigContentListByCpId(String cpId);

	public int getConfigIdByContent(Map<String, String> map);

}
