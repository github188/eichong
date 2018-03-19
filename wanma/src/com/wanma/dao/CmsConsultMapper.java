package com.wanma.dao;

import java.util.List;

import com.wanma.model.Consult;

/**
 * 访问咨询表数据接口
 * 
 * @author xiay
 *
 */
public interface CmsConsultMapper {	
	
	/**
	 * 取得咨询列表
	 * 
	 * @return
	 */
	public List<Consult> getConsultList();
	
	/**
	 * 查询咨询个数
	 * 
	 * @param consult
	 * @return
	 */
	public long searchConsultCount(Consult consult);

	
	/**
	 * 查询咨询列表
	 * 
	 * @param consult
	 * @return
	 */
	public List<Consult> searchConsultList(Consult consult);
	
	
}


















