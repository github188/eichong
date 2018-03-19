package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblCompanyMapper;
import com.wanma.dubbox.model.TblCompany;
import com.wanma.dubbox.service.TblCompanyService;
/**
 * 车型数据查询接口
 * @author lhy
 *
 */
@Service
public class TblCompanyServiceImpl implements TblCompanyService {

	@Autowired
	TblCompanyMapper tblCompanyMapper;

	@Override
	public int delete(TblCompany record) throws Exception {
		if(record.getId() == null && record.getPkIds() == null)
			throw new Exception("缺少过滤字段值，请至少指定一个字段！");
		return tblCompanyMapper.delete(record);
	}

	@Override
	public int insert(TblCompany record) {
		return tblCompanyMapper.insert(record);
	}

	@Override
	public TblCompany selectOne(TblCompany model) {
		return tblCompanyMapper.selectOne(model);
	}

	@Override
	public int update(TblCompany record) {
		return tblCompanyMapper.update(record);
	}

	/**
	 * 获取数据条数
	 * @throws Exception 
	 */
	@Override
	public int getCount(TblCompany model) {
		return tblCompanyMapper.getCount(model);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblCompany> getList(TblCompany model) {
		return tblCompanyMapper.getList(model);
	}
	
}