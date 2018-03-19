package com.wanma.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.AppOrderAddressMapper;
import com.wanma.app.service.AppOrderAddressService;
import com.wanma.common.WanmaConstants;
import com.wanma.model.OrderAddress;

/**
 * @Description: 已购商品安装地址关联表业务处理实现类
 * @author songjf
 * @createTime：2015-6-16 下午06:35:21
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class AppOrderAddressServiceImpl implements AppOrderAddressService {
	/** 已购商品安装地址关联表操作dao */
	@Autowired
	private AppOrderAddressMapper orderAddressMapper;

	/**
	 * @Title: getOrderAddress
	 * @Description: 根据地址主键和商品主键获取信息
	 * @param OrderAddress
	 * @return
	 */
	@Override
	public OrderAddress getOrderAddress(OrderAddress orderAddress) {
		return orderAddressMapper.getOrderAddress(orderAddress);
	}

	/**
	 * @Title: insertOrderAddress
	 * @Description: 新增已购商品安装地址信息
	 * @param OrderAddress
	 * @return
	 */
	@Override
	public void insertOrderAddress(String productIds, String productNames,
			String Quantities, String pkOrderdetails, int pkInstalladdress) {
		String[] productIdArray = productIds.split(",");
		String[] productNameArray = productNames.split(",");
		String[] QuantityArray = Quantities.split(",");
		String[] pkOrderdetailArray = pkOrderdetails.split(",");
		for (int i = 0; i < productIdArray.length; i++) {
			OrderAddress orderAddress = new OrderAddress();
			orderAddress.setOradInstalladdress(pkInstalladdress);
			orderAddress.setOradProductid(Integer.parseInt(productIdArray[i]));
			// 判断此商品是否关联地址，若存在更新数量，否则新增
			OrderAddress isExist = getOrderAddress(orderAddress);
			if (null != isExist) {
				// 地址关联商品数量
				int oradQuantity = isExist.getOradQuantity();
				orderAddress.setOradQuantity(oradQuantity
						+ Integer.parseInt(QuantityArray[i]));
				orderAddress.setPkOrderaddress(isExist.getPkOrderaddress());
				updateOrderAddress(orderAddress);
			} else {
				orderAddress.setOradOrderdetail(Integer
						.parseInt(pkOrderdetailArray[i]));
				orderAddress.setOradProductname(productNameArray[i]);
				orderAddress.setOradQuantity(Integer.parseInt(QuantityArray[i]));
				orderAddress.setOradCreatedate(new Date());
				orderAddress.setOradUpdatedate(new Date());
				orderAddress.setOradStatus(WanmaConstants.ORAD__BESPOKE_NO);
				orderAddressMapper.insertOrderAddress(orderAddress);
			}
		}

	}

	/**
	 * @Title: updateOrderAddress
	 * @Description: 更新已购商品安装地址信息
	 * @param OrderAddress
	 * @return
	 */
	@Override
	public int updateOrderAddress(OrderAddress orderAddress) {
		orderAddress.setOradUpdatedate(new Date());
		return orderAddressMapper.updateOrderAddress(orderAddress);
	}

	/**
	 * @Title: deleteOrderAddress
	 * @Description: 删除已购商品安装地址信息
	 * @param pkOrderaddress
	 *            主键
	 * @return
	 */
	@Override
	public int deleteOrderAddress(Integer pkOrderaddress) {
		return orderAddressMapper.deleteOrderAddress(pkOrderaddress);
	}

}
