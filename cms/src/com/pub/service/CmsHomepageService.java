package com.pub.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pub.model.Homepage;

/**
 * 首页广告栏处理器
 * 
 * @author xiay
 *
 */
public interface CmsHomepageService {
	
	/**
	 * 添加首页图片信息
	 * 
	 * @param homepage
	 */
	public void insertHomePage(Homepage homepage);
	
	/**
	 * 修改首页图片
	 * 
	 * @param homepage
	 */
	
	public void changeHomepage(Homepage homepage,MultipartFile[] homePageImage);
	
	/**
	 * 删除首页图片
	 * 
	 * @param homepage
	 */
	public void deleteHomePage(String homepageId);

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
	 * 查询咨询列表
	 * 
	 * @param consult
	 * @return
	 */
	public List<Homepage> searchHomepageList(Homepage homepage);
}
