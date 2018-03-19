package com.pub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.base.common.GlobalSystem;
import com.base.common.WanmaConstants;
import com.base.util.MultiFileUtil;
import com.base.util.ObjectUtil;
import com.pub.dao.CmsHomepageMapper;
import com.pub.model.Homepage;
import com.pub.service.CmsHomepageService;

@Service
public class CmsHomepageServiceImpl implements CmsHomepageService {

	/** 用户信息业务操作Dao */
	@Autowired
	private CmsHomepageMapper homepageDao;

	/**
	 * 修改首页广告图片
	 * 
	 * @param homepage
	 */
	@Override
	public void changeHomepage(Homepage homepage, MultipartFile[] homePageImage) {
		// 调用dao进行修改操作
		homepageDao.modifyHomePage(homepage);
		if (ObjectUtil.isNotEmpty(homePageImage)) {// 添加列表图片
			homepage.setMultiFiles(homePageImage);
			if (MultiFileUtil.hasFiles(homePageImage)) {
				MultiFileUtil.delelteFile(
						WanmaConstants.MULTI_TYPE_TOTAL_Home_IMAGE,
						homepage.getHomepageId() + "");
				MultiFileUtil.saveFile(homePageImage,
						WanmaConstants.MULTI_TYPE_TOTAL_Home_IMAGE,
						homepage.getHomepageId() + "", false);
			}
		}
	}

	/**
	 * 删除首页广告图片
	 * 
	 * @param homepage
	 */
	@Override
	public void deleteHomePage(String homepageId) {
		// 调用dao查询
		Homepage homepage = homepageDao.findHomePage(homepageId);
		// 删除图片
		MultiFileUtil.delelteFile(
				WanmaConstants.MULTI_TYPE_TOTAL_Home_IMAGE, homepageId + "");
		// 调用dao进行修改操作
		homepageDao.modifyHomePage(homepage);

	}

	/**
	 * 添加首页图片信息
	 * 
	 * @param homepage
	 */
	@Override
	public void insertHomePage(Homepage homepage) {

		homepageDao.insertPic(homepage);
		if (ObjectUtil.isNotEmpty(homepage.getMultiFiles())) {
			// 处理用户图片
			MultiFileUtil.saveFile(homepage.getMultiFiles(),
					WanmaConstants.MULTI_TYPE_TOTAL_Home_IMAGE,
					homepage.getHomepageId() + "",true);
		}

	}

	/**
	 * 首页展示广告列表
	 * 
	 * @param 无
	 */
	@Override
	public List<Homepage> getHomepageList() {
		// 广告图片一览
		List<Homepage> listHomepage;

		// 取得广告图片一览
		listHomepage = homepageDao.getHomepageList();
		// 如果广告列表为null，初始化数据
		if (listHomepage.size() == 0) {
			for (int i = 1; i < 6; i++) {
				homepageDao.insertHomepages(i);
			}
			// 取得广告图片一览
			listHomepage = homepageDao.getHomepageList();

		} else if (listHomepage.size() < 5) {
			Homepage homepage = listHomepage.get(listHomepage.size() - 1);
			int j = Integer.parseInt(homepage.getHomepageId());
			for (int i = j + 1; i < j + (6 - listHomepage.size()); i++) {
				homepageDao.insertHomepages(i);
			}
			// 取得广告图片一览
			listHomepage = homepageDao.getHomepageList();
		}

		for (Homepage h : listHomepage) {
			// 获取广告图片
			List<String> listImage = MultiFileUtil.getUrls(
					WanmaConstants.MULTI_TYPE_TOTAL_Home_IMAGE,
					h.getHomepageId() + "");
			if (listImage.size() > 0 && !listImage.isEmpty()) {
				h.setHomePageImage(ObjectUtil.nvlStr(listImage.get(0)));
			} else {
				String deployUrl = GlobalSystem.getConfig("deploy.url");
				h.setHomePageImage(deployUrl + "/upload/shareImage/share.jpg");
			}
		}

		// 返回广告图片一览
		return listHomepage;
	}

	@Override
	public long searchHomepageCount(Homepage homepage) {
		// 咨询个数
		long dataCount;

		// 取得咨询个数
		dataCount = homepageDao.searchHomepageCount(homepage);

		// 返回咨询个数
		return dataCount;
	}

	@Override
	public List<Homepage> searchHomepageList(Homepage homepage) {
		// 广告图片一览
		List<Homepage> listHomepage;

		// 取得广告图片一览
		listHomepage = homepageDao.searchHomepageList(homepage);

		// 返回广告图片一览
		return listHomepage;
	}

}
