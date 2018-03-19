package com.wanma.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.AppInstalladdressMapper;
import com.wanma.app.dao.AppOrderAddressMapper;
import com.wanma.app.service.AppInstalladdressService;
import com.wanma.model.Installaddress;
/**
 * @Description: 用户安装地址业务处理实现类
 * @author songjf
 * @createTime：2015-6-15 下午06:43:21
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class AppInstalladdressServiceImpl implements AppInstalladdressService {
	/* 用户安装地址表操作dao */
	@Autowired
	private AppInstalladdressMapper installaddressMapper;
	/* 已购商品安装地址关联表操作dao */
	@Autowired
	private AppOrderAddressMapper orderAddressMapper;
	/**
	 * @Title: getAddress
	 * @Description: 根据主键获取安装地址 
	 * @param params
	 * @return
	 */
	@Override
	public Installaddress getAddress(Integer pkInstalladdress) {
		return installaddressMapper.getAddress(pkInstalladdress);
	}

	/**
	 * @Title: findAddresses
	 * @Description: 根据用户获取安装地址
	 * @param pradUserid
	 * @return
	 */
	@Override
	public List<Installaddress> findAddresses(Integer pradUserid) {
		return installaddressMapper.findAddresses(pradUserid);
	}

	/**
	 * @Title: insertAddress
	 * @Description: 新增安装地址 
	 * @param params
	 * @return
	 */
	@Override
	public int insertAddress(Installaddress installaddress) {
		installaddress.setPradCreatedate(new Date());
		installaddress.setPradUpdatedate(new Date());
		return installaddressMapper.insertAddress(installaddress);
	}

	/**
	 * @Title: updateAddress
	 * @Description: 更新安装地址 
	 * @param params
	 * @return
	 */
	@Override
	public int updateAddress(Installaddress installaddress) {
		installaddress.setPradUpdatedate(new Date());
		return installaddressMapper.updateAddress(installaddress);
	}

	/**
	 * @Title: deleteAddress
	 * @Description: 删除安装地址
	 * @param params
	 * @return
	 */
	@Override
	public void deleteAddress(Integer pkInstalladdress) {
		orderAddressMapper.deleteByInstallAddress(pkInstalladdress);
		installaddressMapper.deleteAddress(pkInstalladdress);
	}

}
