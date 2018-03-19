package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.wanma.common.log.SystemControllerLog;
import com.wanma.dao.CmsProductcommentMapper;
import com.wanma.model.TblProductcomment;
import com.wanma.service.CmsProductcommentService;

/**
 * 评论业务处理器
 * 
 * @author xiay
 *
 */
@Controller
public class CmsProductcommentServiceImpl implements CmsProductcommentService{

	/** 评论表作用Dao */
	@Autowired
	private CmsProductcommentMapper tblProductcommentDao;
	
	/**
	 * 取得评论列表
	 * 
	 */
	@Override
	public List<TblProductcomment> getContentList() {
		// 评论表一览
		List<TblProductcomment> listContent;
		
		//取得评论表一览
		listContent = tblProductcommentDao.getContentList();
		
		//返回评论表一览
		return listContent;
	}

	/**
	 * 查询评论个数
	 * 
	 */
	public long searchContentCount(TblProductcomment tblProductcomment) {
		// 咨询个数
		long dataCount;

		// 取得咨询个数
		dataCount = tblProductcommentDao.searchContentCount(tblProductcomment);

		// 返回咨询个数
		return dataCount;

	}
	
	/**
	 * 查询查询评论列表
	 * 
	 */
	@Override
	public List<TblProductcomment> searchProContentList(TblProductcomment tblProductcomment) {
		// 评论表一览
		List<TblProductcomment> listContent;
				
		//取得评论表一览
		listContent = tblProductcommentDao.searchProContentList(tblProductcomment);
				
		//返回评论表一览
		return listContent;
	}

	/**
	 * 删除评论列表
	 * 
	 * @param deleteProduct
	 * @return
	 */
	@SystemControllerLog(description = "删除评论列表")
	@Override
	public void deleteProduct(int pkProductcomment) {
		tblProductcommentDao.removeProduct(pkProductcomment);
	}
	
	@SystemControllerLog(description = "删除电桩评论")
	@Override
	public void removeElectricpilecomment(int pkProductcomment) {
		tblProductcommentDao.removeElectricpilecomment(pkProductcomment);
	}

	@SystemControllerLog(description = "删除充电点评论")
	@Override
	public void removePowerstationcomment(int pkProductcomment) {
		tblProductcommentDao.removePowerstationcomment(pkProductcomment);
		
	}
}
