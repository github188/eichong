package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsBespokeMapper;
import com.wanma.model.TblBespoke;
import com.wanma.service.CmsBespokeService;

@Service
public class CmsBespokeServiceImpl implements CmsBespokeService{

	/** 预约消费操作dao */
	@Autowired
	private CmsBespokeMapper tblBespokeDao;
	
	/**
	 * 根据预约消费ID取得预约消费信息
	 * 
	 * @author xaiy
	 * @return
	 * @throws 
	 */
	public TblBespoke findBespoke(String pkBespoke) {

		// 预约消费信息
		TblBespoke Bespoke;

		// 取得预约消费信息
		Bespoke = tblBespokeDao.findBespoke(pkBespoke);

		// 返回预约消费一览
		return Bespoke;
	}

	/**
	 * 编辑预约消费
	 * 
	 * @author xaiy
	 * @return 无
	 * @throws 无
	 */
	public void modifyBespoke(TblBespoke tblBespoke) {
		
		// 调用DAO执行预约消费更新处理
		tblBespokeDao.modifyBespoke(tblBespoke);
	}

	
	/**
	 * 删除预约消费
	 * 
	 * @author xaiy
	 * @return 无
	 * @throws 无
	 */
	@Override
	public int deleteBespoke(String pkBespoke) {
		return tblBespokeDao.deleteBespoke(pkBespoke);
	}

	
	/**
	 * 取得预约消费一览
	 * 
	 * @author xaiy
	 * @return 无
	 * @throws 无
	 */
	public List<TblBespoke> getBespokeList() {
		// 预约消费一览
		List<TblBespoke> Bespoke;

		// 取得预约消费信息
		Bespoke = tblBespokeDao.getBespokeList();

		// 返回预约消费一览
		return Bespoke;
	}

	/**
	 * 查询预约消费个数
	 * 
	 * @author xaiy
	 * @return 无
	 * @throws 无
	 */
	public long searchBespokeCount(TblBespoke tblBespoke) {
		// 预约消费信息
		long dataCount;

		// 取得预约消费信息
		dataCount = tblBespokeDao.searchBespokeCount(tblBespoke);

		// 返回预约消费一览
		return dataCount;

	}

	/**
	 * 查询预约消费一览
	 * 
	 * @author xaiy
	 * @return 无
	 * @throws 无
	 */
	public List<TblBespoke> searchBespokeList(TblBespoke tblBespoke) {
		// 预约消费信息
		List<TblBespoke> Bespoke;

		// 取得预约消费信息
		Bespoke = tblBespokeDao.searchBespokeList(tblBespoke);

		// 返回预约消费一览
		return Bespoke;

	}
	
	/**
	 * 根据预约消费ID取得预约消费信息
	 * 
	 * @author xaiy
	 * @return
	 * @throws 
	 */
	public TblBespoke findUnitBespoke(String pkBespoke) {

		// 预约消费信息
		TblBespoke Bespoke;

		// 取得预约消费信息
		Bespoke = tblBespokeDao.findBespoke(pkBespoke);

		// 返回预约消费一览
		return Bespoke;
	}
	
	/**
	 * 删除预约消费
	 * 
	 * @author xaiy
	 * @return 无
	 * @throws 无
	 */
	@Override
	public int deleteUnitBespoke(String pkBespoke) {
		return tblBespokeDao.deleteBespoke(pkBespoke);
	}

	
	/**
	 * 取得预约消费一览
	 * 
	 * @author xaiy
	 * @return 无
	 * @throws 无
	 */
	public List<TblBespoke> getUnitBespokeList() {
		// 预约消费一览
		List<TblBespoke> Bespoke;

		// 取得预约消费信息
		Bespoke = tblBespokeDao.getBespokeList();

		// 返回预约消费一览
		return Bespoke;
	}

	/**
	 * 查询预约消费个数
	 * 
	 * @author xaiy
	 * @return 无
	 * @throws 无
	 */
	public long searchUnitBespokeCount(TblBespoke tblBespoke) {
		// 预约消费信息
		long dataCount;

		// 取得预约消费信息
		dataCount = tblBespokeDao.searchUnitBespokeCount(tblBespoke);

		// 返回预约消费一览
		return dataCount;

	}

	/**
	 * 查询预约消费一览
	 * 
	 * @author xaiy
	 * @return 无
	 * @throws 无
	 */
	public List<TblBespoke> searchUnitBespokeList(TblBespoke tblBespoke) {
		// 预约消费信息
		List<TblBespoke> Bespoke;

		// 取得预约消费信息
		Bespoke = tblBespokeDao.searchUnitBespokeList(tblBespoke);

		// 返回预约消费一览
		return Bespoke;

	}
	
	/**
	 * 根据预约消费ID取得预约消费信息--纯商家
	 * 
	 * @author xaiy
	 * @return
	 * @throws 
	 */
	public TblBespoke findFirmBespoke(String pkBespoke) {

		// 预约消费信息
		TblBespoke Bespoke;

		// 取得预约消费信息
		Bespoke = tblBespokeDao.findFirmBespoke(pkBespoke);

		// 返回预约消费一览
		return Bespoke;
	}
	
	/**
	 * 取得预约消费一览--纯商家
	 * 
	 * @author xaiy
	 * @return 无
	 * @throws 无
	 */
	public List<TblBespoke> getFirmBespokeList() {
		// 预约消费一览
		List<TblBespoke> Bespoke;

		// 取得预约消费信息
		Bespoke = tblBespokeDao.getFirmBespokeList();

		// 返回预约消费一览
		return Bespoke;
	}

	/**
	 * 查询预约消费个数--纯商家
	 * 
	 * @author xaiy
	 * @return 无
	 * @throws 无
	 */
	public long searchFirmBespokeCount(TblBespoke tblBespoke) {
		// 预约消费信息
		long dataCount;

		// 取得预约消费信息
		dataCount = tblBespokeDao.searchFirmBespokeCount(tblBespoke);

		// 返回预约消费一览
		return dataCount;

	}

	/**
	 * 查询预约消费一览--纯商家
	 * 
	 * @author xaiy
	 * @return 无
	 * @throws 无
	 */
	public List<TblBespoke> searchFirmBespokeList(TblBespoke tblBespoke) {
		// 预约消费信息
		List<TblBespoke> Bespoke;

		// 取得预约消费信息
		Bespoke = tblBespokeDao.searchFirmBespokeList(tblBespoke);

		// 返回预约消费一览
		return Bespoke;

	}


}
