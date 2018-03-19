package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblProductcomment;


/**
 * 产品评论
 * 访问评论表数据接口
 * 
 * @author xiay
 *
 */
public interface CmsProductcommentMapper {

	/**
	 * 取得评论列表
	 * 
	 * @return
	 */
	public List<TblProductcomment> getContentList();
	
	/**
	 * 查询评论个数
	 * 
	 * @param tblProductcomment
	 * @return
	 */
	public long searchContentCount(TblProductcomment tblProductcomment);

	
	/**
	 * 查询评论列表
	 * 
	 * @param tblProductcomment
	 * @return
	 */
	public List<TblProductcomment> searchProContentList(TblProductcomment tblProductcomment);
	
	
	/**
	 * 删除评论列表
	 * 
	 * @param tblProductcomment
	 * @return
	 */
	public void removeProduct( int pkProductcomment );
	
	public void removeElectricpilecomment(int pkProductcomment);
	
	public void removePowerstationcomment(int pkProductcomment);
	
}
