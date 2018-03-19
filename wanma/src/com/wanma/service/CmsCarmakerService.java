package com.wanma.service;

import java.util.List;

import com.wanma.model.TblCarmaker;

/**
 * 制造厂商处理接口
 * 
 * @version V1.0
 * @author wubc
 * @date 2015年5月28日
 */
public interface CmsCarmakerService {

	/**
	 * 获取所有制造厂商
	 * 
	 * @return
	 */
	public List<TblCarmaker> getAll(TblCarmaker carmaker);

	public int getCount(TblCarmaker carmaker);

	public TblCarmaker getOne(TblCarmaker carmaker);

	public int insert(TblCarmaker tblCarMaker);

	public int update(TblCarmaker tblCarMaker);

	public int delete(java.lang.Integer pkCarmaker);

	public int deleteBatch(String pkCarmakers);
	
	public List<TblCarmaker> getByProperty(TblCarmaker carmaker);

	public boolean isBondWithElectricPile(Integer pkCarmaker);
	
}
