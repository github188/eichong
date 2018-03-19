package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.SvUtil;
import com.wanma.dubbox.dao.TblElectricPileMapper;
import com.wanma.dubbox.model.TblElectricPile;
import com.wanma.dubbox.service.TblElectricPileService;
import com.wanma.redis.CacheDelete;
import com.wanma.redis.CacheGet;
import com.wanma.redis.CachePut;

/**
 * 电桩数据查询接口
 * 
 * @author lhy
 *
 */
@Service
public class TblElectricPileServiceImpl implements TblElectricPileService {

	@Autowired
	TblElectricPileMapper tblElectricPileMapper;

	@CacheDelete(clazz = TblElectricPile.class, key = {"#record.id","#record.pkIds"})
	@Override
	public int delete(TblElectricPile record) throws Exception {
		if (record.getPkIds() == null && record.getFkIds() == null
				&& record.getId() == null && record.getPowId() == null)
			throw new Exception("缺少过滤字段值，请至少指定一个字段！");
		return tblElectricPileMapper.delete(record);
	}

	@CachePut(clazz = TblElectricPile.class, key = "#record.id", value = "#record")
	@Override
	public int insert(TblElectricPile record) {
		return tblElectricPileMapper.insert(record);
	}

	@CacheGet(clazz = TblElectricPile.class, key = "#record.id")
	@Override
	public TblElectricPile selectOne(TblElectricPile record) {
		return tblElectricPileMapper.selectOne(record);
	}

	@CacheDelete(clazz = TblElectricPile.class, key = "#record.id")
	@Override
	public int update(TblElectricPile record) {
		return tblElectricPileMapper.update(record);
	}

	/**
	 * 获取列表
	 * 
	 * @throws Exception
	 */
	@Override
	public List<TblElectricPile> getList(TblElectricPile model) {
		return tblElectricPileMapper.getList(model);
	}

	@Override
	public int getCount(TblElectricPile model) {
		return tblElectricPileMapper.getCount(model);
	}

	@CacheDelete(clazz = TblElectricPile.class, key = "#record.id")
	@Override
	public int updateMore(TblElectricPile record) throws Exception {
		if (record.getPowId() == null && record.getPkIds() == null
				&& record.getFkIds() == null)
			throw new Exception("缺少过滤字段值，请至少指定一个字段！");
		int num= tblElectricPileMapper.updateMore(record);
		SvUtil.setCasheDeleteIds(record, getList(record));
		return num;
	}
}