package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.SvUtil;
import com.wanma.dubbox.dao.TblElectricPileHeadMapper;
import com.wanma.dubbox.model.TblElectricPileHead;
import com.wanma.dubbox.service.TblElectricPileHeadService;
import com.wanma.redis.CacheDelete;
import com.wanma.redis.CacheGet;
import com.wanma.redis.CachePut;

/**
 * 枪头数据查询接口
 * 
 * @author lhy
 *
 */
@Service
public class TblElectricPileHeadServiceImpl implements
		TblElectricPileHeadService {
	@Autowired
	private TblElectricPileHeadMapper tblElectricPileHeadMapper;

	@CachePut(clazz = TblElectricPileHead.class, key = "#record.id", value = "#record")
	@Override
	public int insert(TblElectricPileHead record) {
		return tblElectricPileHeadMapper.insert(record);
	}

	@CacheGet(clazz = TblElectricPileHead.class, key = "#model.id")
	@Override
	public TblElectricPileHead selectOne(TblElectricPileHead model) {
		return tblElectricPileHeadMapper.selectOne(model);
	}

	/**
	 * 获取列表
	 * 
	 * @throws Exception
	 */
	@Override
	public List<TblElectricPileHead> getList(TblElectricPileHead model) {
		return tblElectricPileHeadMapper.getList(model);
	}

	@CacheDelete(clazz=TblElectricPileHead.class,key="#model.id")
	@Override
	public int update(TblElectricPileHead model) {
		return tblElectricPileHeadMapper.update(model);
	}

	@CacheDelete(clazz=TblElectricPileHead.class,key="#model.id")
	@Override
	public int delete(TblElectricPileHead model) throws Exception {
		if (model.getPkIds() == null && model.getFkIds() == null
				&& model.getId() == null && model.geteId() == null)
			throw new Exception("缺少过滤字段值，请至少指定一个字段！");
		int num= tblElectricPileHeadMapper.delete(model);
		SvUtil.setCasheDeleteIds(model,getList(model));
		return num;
	}

	@Override
	public int getCount(TblElectricPileHead model) {
		return tblElectricPileHeadMapper.getCount(model);
	}

}
