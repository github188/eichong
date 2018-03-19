package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.SvUtil;
import com.wanma.dubbox.dao.TblPowerStationMapper;
import com.wanma.dubbox.model.TblPowerStation;
import com.wanma.dubbox.service.TblPowerStationService;
import com.wanma.redis.CacheDelete;
import com.wanma.redis.CacheGet;
import com.wanma.redis.CachePut;

/**
 * 充电点数据查询接口
 * 
 * @author lhy
 *
 */
@Service
public class TblPowerStationServiceImpl implements TblPowerStationService {
	@Autowired
	private TblPowerStationMapper tblPowerStationMapper;

	@CachePut(clazz = TblPowerStation.class, key = "#record.id", value = "#record")
	@Override
	public TblPowerStation insert(TblPowerStation record) {
		tblPowerStationMapper.insert(record);
		return record;
	}

	@CacheGet(clazz = TblPowerStation.class, key = "#record.id")
	@Override
	public TblPowerStation selectOne(TblPowerStation record) {
		return tblPowerStationMapper.selectOne(record);
	}

	@Override
	public List<TblPowerStation> getList(TblPowerStation model) {
		return tblPowerStationMapper.getList(model);
	}

	@CacheDelete(clazz = TblPowerStation.class, key = { "#model.id",
			"#model.pkIds" })
	@Override
	public int update(TblPowerStation model) throws Exception {
		if (model.getPkIds() == null && model.getId() == null) {
			throw new Exception("缺少过滤字段值，请至少指定一个字段！");
		}
		return tblPowerStationMapper.update(model);
	}

	@Override
	public int getCount(TblPowerStation model) {
		return tblPowerStationMapper.getCount(model);
	}

	@CacheDelete(clazz = TblPowerStation.class, key = { "#model.id",
			"#model.pkIds" })
	@Override
	public int delete(TblPowerStation model) throws Exception {
		if (model.getPkIds() == null && model.getId() == null) {
			throw new Exception("缺少过滤字段值，请至少指定一个字段！");
		}
		return tblPowerStationMapper.delete(model);
	}

	@CacheDelete(clazz = TblPowerStation.class, key = "#model.id")
	@Override
	public int updateMore(TblPowerStation model) throws Exception {
		SvUtil.initDeleteIds(model);
		int num = tblPowerStationMapper.updateMore(model);

		return num;
	}

}
