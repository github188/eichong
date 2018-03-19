package com.wanma.service;

import java.util.List;

import com.wanma.model.TblFilterWords;

/**
 * 过滤字处理器
 * 
 * @author xiay
 *
 */
public interface CmsFilterWordsService {

	/**
	 * 取得过滤字列表
	 * 
	 * @return
	 */
	public List<TblFilterWords> getFilterWordList();
	
	/**
	 * 查询过滤字个数
	 * 
	 * @param tblFilterWords
	 * @return
	 */
	public long searchFilterWordCount(TblFilterWords tblFilterWords);
	
	/**
	 * 查询过滤字列表
	 * 
	 * @param tblFilterWords
	 * @return
	 */
	public List<TblFilterWords> searchFilterWordList(TblFilterWords tblFilterWords);
	
	/**
	 * 添加过滤字
	 * 
	 * @param tblFilterWords
	 * @return
	 */
	public void addFilterWords(TblFilterWords tblFilterWords);
	
	/**
	 * 删除过滤字
	 * 
	 * @param tblFilterWords
	 * @return
	 */
	public void deleteFilterWord(TblFilterWords tblFilterWords);
	
}
