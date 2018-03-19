package com.wanma.app.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.utils.StringUtil;
import com.wanma.app.dao.AppUseraddressMapper;
import com.wanma.app.service.AppUseraddressService;
import com.wanma.model.TblUseraddress;

/**
 * @Description: 用户收货地址业务处理接口
 * @author songjf
 * @createTime：2015-3-21 下午06:43:21
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class AppUseraddressServiceImpl implements AppUseraddressService {

	/* 用户收货地址表操作dao */
	@Autowired
	private AppUseraddressMapper useraddressMapper;

	/**
	 * @Title: findAddresses
	 * @Description: 获取用户收货地址
	 * @param params
	 * @return
	 */
	@Override
	public <T, K, V> List<T> findAddresses(Map<K, V> params) {
		return useraddressMapper.findAddresses(params);
	}

	/**
	 * @Title: insertAddress
	 * @Description: 新增用户收货地址
	 * @param params
	 * @return
	 */
	@Override
	public int insertAddress(TblUseraddress useraddress) {
		useraddress.setPradIsDefault("0");
		useraddress.setPradCreatedate(new Date());
		useraddress.setPradUpdatedate(new Date());
		return useraddressMapper.insertAddress(useraddress);
	}

	/**
	 * @Title: updateAddress
	 * @Description: 更新用户收货地址
	 * @param params
	 * @return
	 */
	@Override
	public int updateAddress(TblUseraddress useraddress) {
		useraddress.setPradUpdatedate(new Date());
		return useraddressMapper.updateAddress(useraddress);
	}

	/**
	 * @Title: deleteAddress
	 * @Description: 删除用户收货地址
	 * @param params
	 * @return
	 */
	@Override
	public void deleteAddress(Map<String, Object> params) {
		String pkUseraddress = (String) params.get("pkUseraddress");
		if (StringUtil.isNotEmpty(pkUseraddress)) {
			String[] pkuseraddressA = pkUseraddress.split(",");
			for (int i = 0; i < pkuseraddressA.length; i++) {
				useraddressMapper.deleteAddress(Integer
						.parseInt(pkuseraddressA[i]));
			}
		}
	}

	/**
	 * @Title: getAddress
	 * @Description: 根据id获取用户收货地址
	 * @param params
	 * @return
	 */
	@Override
	public TblUseraddress getAddress(Integer pkUseraddress) {
		return useraddressMapper.getAddress(pkUseraddress);
	}

	/**
	 * @Title: updateIsDefault
	 * @Description: 更新用户默认地址
	 * @param params
	 * @return
	 */
	@Override
	public void updateIsDefault(Map<String, Object> params) {
		params.put("pradUpdatedate", new Date());
		useraddressMapper.updateIsNotDefault(params);
		useraddressMapper.updateDefault(params);
	}

	/**
	 * @Title: findDefault
	 * @Description: 获取用户默认地址
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> findDefault(int pkUserInfo) {
		return useraddressMapper.findDefault(pkUserInfo);
	}

}
