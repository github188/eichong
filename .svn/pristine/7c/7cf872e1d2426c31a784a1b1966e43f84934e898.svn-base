package com.wanma.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bluemobi.product.common.dao.MultipartFileDao;
import com.bluemobi.product.utils.MultipartFileUtil;
import com.bluemobi.product.utils.ObjectUtil;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.WanmaConstants;
import com.wanma.model.Homepage;
import com.wanma.web.dao.CmsHomepageMapper;
import com.wanma.web.service.CmsHomepageService;

@Service
public class CmsHomepageServiceImpl implements CmsHomepageService{
	
	/** 用户信息业务操作Dao */
	@Autowired
	private CmsHomepageMapper homepageDao;
	
	/**
	 * 修改首页广告图片
	 * 
	 * @param homepage
	 */
	@Override
	public void changeHomepage(Homepage homepage,MultipartFile[] homePageImage){
		//调用dao进行修改操作
		homepageDao.modifyHomePage(homepage);
		if (ObjectUtil.isNotEmpty(homePageImage)) {//添加列表图片
			homepage.setAllMultiFile(homePageImage);
	    	if(MultipartFileUtil.verifyImageIsNotNull(homepage)){
	    		MultipartFileDao multipartFileDao = new MultipartFileDao();
				List<String> allMultiFileName = multipartFileDao.getAllMultiFileName(WanmaConstants.MULTI_TYPE_TOTAL_Home_IMAGE, homepage.getHomepageId() + "");
		    	MultipartFileUtil.delelteMulti(allMultiFileName, WanmaConstants.MULTI_TYPE_TOTAL_Home_IMAGE, homepage.getHomepageId() + "");

		    	MultipartFileUtil.saveAllMulti(homepage,
		    			WanmaConstants.MULTI_TYPE_TOTAL_Home_IMAGE,
		    			homepage.getHomepageId() + "");
				}
	    	}
	}

	/**
	 * 删除首页广告图片
	 * 
	 * @param homepage
	 */
	@Override
	public void deleteHomePage(String homepageId){
		//调用dao查询
		Homepage homepage = homepageDao.findHomePage(homepageId);
		//删除图片
		MultipartFileDao multipartFileDao = new MultipartFileDao();
		List<String> allMultiFileName = multipartFileDao.getAllMultiFileName(WanmaConstants.MULTI_TYPE_TOTAL_Home_IMAGE, homepageId + "");
    	MultipartFileUtil.delelteMulti(allMultiFileName, WanmaConstants.MULTI_TYPE_TOTAL_Home_IMAGE, homepageId + "");
		//调用dao进行修改操作
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
		if(ObjectUtil.isNotEmpty(homepage.getAllMultiFile())){
			// 处理用户图片
			MultipartFileUtil.saveAllMulti(homepage,
				WanmaConstants.MULTI_TYPE_TOTAL_Home_IMAGE, homepage.getHomepageId()
					+ "");
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
					
		//取得广告图片一览
		listHomepage = homepageDao.getHomepageList();
		//如果广告列表为null，初始化数据
		if(listHomepage.size()==0){
			for(int i=1;i<6;i++){
				homepageDao.insertHomepages(i);
			}
			//取得广告图片一览
			listHomepage = homepageDao.getHomepageList();
			
		}else if(listHomepage.size()<5){
			Homepage homepage = listHomepage.get(listHomepage.size()-1);
			int j = Integer.parseInt(homepage.getHomepageId());
			for(int i = j+1;i<j+(6-listHomepage.size());i++){
				homepageDao.insertHomepages(i);
			}
			//取得广告图片一览
			listHomepage = homepageDao.getHomepageList();
		}
		Homepage h=null;
		for(int i=listHomepage.size()-1;i>=0;i--){
			h=listHomepage.get(i);
		//获取广告图片
		List<String> listImage=MultipartFileUtil.getAllMultiUrl(WanmaConstants.MULTI_TYPE_TOTAL_Home_IMAGE,
				h.getHomePageSequence() + "");
		if(listImage.size()>0 && !listImage.isEmpty()){
			h.setHomePageImage(JudgeNullUtils.nvlStr(listImage.get(0)));
			}else{
				listHomepage.remove(h);
			}
		}
		
		//返回广告图片一览
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
							
		//取得广告图片一览
		listHomepage = homepageDao.searchHomepageList(homepage);
							
		//返回广告图片一览
		return listHomepage;
	}

}
