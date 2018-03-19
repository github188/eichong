package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.log.SystemControllerLog;
import com.wanma.dao.CmsFilterWordsMapper;
import com.wanma.model.TblFilterWords;
import com.wanma.service.CmsFilterWordsService;

/**
 * 过滤字处理操作
 * 
 * @author xiay
 *
 */
@Service
public class CmsFilterWordsServiceImpl implements CmsFilterWordsService{

	/** 调用Dao处理 */
	@Autowired
	private CmsFilterWordsMapper tblFilterWordsDao;
	
	/**
	 * 获得过滤字列表
	 * 
	 * @param tblFilterWords
	 * @return
	 */
	@Override
	public List<TblFilterWords> getFilterWordList() {
		// 过滤字表一览
		List<TblFilterWords> listFilterWords;
				
		//取得过滤字表一览
		listFilterWords = tblFilterWordsDao.getFilterWordList();
				
		//返回过滤字表一览
		return listFilterWords;
	}

	/**
	 * 查询过滤字数量
	 * 
	 * @param tblFilterWords
	 * @return
	 */
	@Override
	public long searchFilterWordCount(TblFilterWords tblFilterWords) {
		// 过滤字个数
		long dataCount;

		// 取得过滤字个数
		dataCount = tblFilterWordsDao.searchFilterWordCount(tblFilterWords);

		// 返回过滤字个数
		return dataCount;
	}

	/**
	 * 查询过滤字列表
	 * 
	 * @param tblFilterWords
	 * @return
	 */
	@Override
	public List<TblFilterWords> searchFilterWordList(
			TblFilterWords tblFilterWords) {
		// 过滤字表一览
		List<TblFilterWords> listFilterWords;
						
		//取得过滤字表一览
		listFilterWords = tblFilterWordsDao.searchFilterWordList(tblFilterWords);
						
		//返回过滤字表一览
		return listFilterWords;
	}
	
	/**
	 * 添加过滤字
	 * 
	 * @param tblFilterWords
	 * @return
	 */
	@SystemControllerLog(description = "添加过滤字")
	@Override
	public void addFilterWords(TblFilterWords tblFilterWords) {
		tblFilterWordsDao.insertFilterWords(tblFilterWords);
	}

	/**
	 * 删除过滤字
	 * 
	 * @param tblFilterWords
	 * @return
	 */
	@SystemControllerLog(description = "删除过滤字")
	@Override
	public void deleteFilterWord(TblFilterWords tblFilterWords) {
		tblFilterWordsDao.removeFilterWord(tblFilterWords);
	}

}
