package com.wanma.web.dao;

import java.util.List;

import com.wanma.model.Homepage;

/**
 * 首页广告图
 * @author xiay
 * 数据访问接口
 *
 */
public interface CmsHomepageMapper {
	
	/**
	 * 初始新增首页广告
	 * 
	 * @author taoxd
	 * @return 无
	 */
	public void insertHomepages(int i);
	
	/**
	 * 根据首页广告ID取得信息
	 * 
	 * @author taoxd
	 * @return 无
	 */
	public Homepage findHomePage(String homepageId);
	/**
	 * 添加广告栏图片
	 * 
	 * @param homepage
	 * @return
	 */
	public int insertPic(Homepage homepage);

	/**
	 * 编辑首页广告图片
	 * 
	 * @author taoxd
	 * @return 无
	 */
	public void modifyHomePage(Homepage homepage);
	/**
	 * 取得图片列表
	 * 
	 * @return
	 */
	public List<Homepage> getHomepageList();
	
	/**
	 * 查询图片个数
	 * 
	 * @param consult
	 * @return
	 */
	public long searchHomepageCount(Homepage homepage);

	
	/**
	 * 查询图片列表
	 * 
	 * @param consult
	 * @return
	 */
	public List<Homepage> searchHomepageList(Homepage homepage);
	
}
