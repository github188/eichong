package com.wanma.service;

import java.util.List;

import com.wanma.model.TblPilemaker;

/**
 * 制造厂商处理接口
 * 
 * @version V1.0
 * @author wubc
 * @date 2015年5月28日
 */
public interface CmsPilemakerService {

	/**
	 * 获取所有制造厂商
	 * 
	 * @return
	 */
	public List<TblPilemaker> getAll(TblPilemaker carmaker);

	public int getCount(TblPilemaker carmaker);

	public TblPilemaker getOne(TblPilemaker carmaker);

	public int insert(TblPilemaker tblCarMaker);

	public int update(TblPilemaker tblCarMaker);

	public int delete(java.lang.Integer pkCarmaker);

	public int deleteBatch(String pkCarmakers);
	
	public List<TblPilemaker> getByProperty(TblPilemaker carmaker);

	public boolean isBondWithElectricPile(Integer pkCarmaker);
	
}
