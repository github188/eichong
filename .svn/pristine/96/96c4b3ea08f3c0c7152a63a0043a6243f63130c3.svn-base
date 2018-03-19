package com.wanma.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.common.BluemobiCommon;
import com.bluemobi.product.utils.JacksonJsonMapper;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.common.WanmaConstants;
import com.wanma.model.OrderAddress;
import com.wanma.model.TblAppointmentinstallationorder;
import com.wanma.model.TblInstalldetail;
import com.wanma.model.TblOrderdetail;
import com.wanma.model.TblOrdertracking;
import com.wanma.web.dao.WebAppointmentinstallationorderMapper;
import com.wanma.web.dao.WebInstalldetailMapper;
import com.wanma.web.dao.WebOrderAddressMapper;
import com.wanma.web.dao.WebOrderdetailMapper;
import com.wanma.web.dao.WebOrdertrackingMapper;
import com.wanma.web.dao.WebUseraddressMapper;
import com.wanma.web.dao.WebUserinfoMapper;
import com.wanma.web.service.WebAppointmentService;

/**
 * @Description: 预约安装订单业务处理接口
 * @author songjf
 * @createTime：2015-3-24 下午01:12:24
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class WebAppointmentServiceImpl implements WebAppointmentService {
	/* 订单详情表操作dao */
	@Autowired
	private WebOrderdetailMapper orderdetailMapper;
	/* 用户地址表操作dao */
	@Autowired
	private WebUseraddressMapper useraddressMapper;
	/* 预约安装订单表操作dao */
	@Autowired
	private WebAppointmentinstallationorderMapper appointmentMapper;
	/* 预约安装表操作dao */
	@Autowired
	private WebInstalldetailMapper installdetailMapper;
	/* 订单跟踪表操作dao */
	@Autowired
	private WebOrdertrackingMapper ordertrackingMapper;
	/* 用户表操作dao */
	@Autowired
	private WebUserinfoMapper webUserinfoMapper;
	/* 已购商品安装地址关联表操作dao */
	@Autowired
	private WebOrderAddressMapper orderAddressMapper;

	/**
	 * @Title: selectAddAndPro
	 * @Description: 安装预约 确认安装地址 商品
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> selectAddAndPro(Map<String, Object> params) {
		// 订单id
		int orDeParentId = Integer
				.parseInt((String) params.get("orDeParentId"));
		List<TblOrderdetail> orderDetailList = orderdetailMapper
				.selectProductByOrderId(orDeParentId);
		// 用户id
		int pkUserInfo = Integer.parseInt((String) params.get("pkUserInfo"));
		// 获取用户默认收货地址
		Map<String, Object> resultMap = useraddressMapper
				.findDefault(pkUserInfo);
		if (null == resultMap) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("pradUsername", "");
			resultMap.put("pradPhonenumber", "");
			resultMap.put("pradPostalCode", "");
			resultMap.put("pradAddress", "");
			resultMap.put("pradStreet", "");
		}
		resultMap.put("orderDetailList", orderDetailList);

		return resultMap;
	}

	/**
	 * @Title: insertAppointment
	 * @Description: 新增预约安装订单
	 * @param params
	 * @return
	 */
	@Override
	public void insertAppointment(TblAppointmentinstallationorder appointment,
			String ordeProductids, String ordeProductnames, String ordePrices,
			String inDeQuantitys, String prodProductCode) {
		// 新增预约安装订单信息
		appointment.setAlorInstallationordercode(BluemobiCommon.getOrderNo());
		appointmentMapper.insert(appointment);
		// 新增预约详情信息
		if (StringUtil.isNotEmpty(ordeProductids)) {
			String[] productidArray = ordeProductids.split(",");
			String[] productNameArray = ordeProductnames.split(",");
			String[] priceArray = ordePrices.split(",");
			String[] quantityArray = inDeQuantitys.split(",");
			String[] productCodeArray = prodProductCode.split(",");

			for (int i = 0; i < productidArray.length; i++) {
				TblInstalldetail installdetail = new TblInstalldetail();
				installdetail.setIndeParentid(appointment
						.getPkAppointmentinstallationorder());
				installdetail.setIndeProductid(Integer
						.parseInt(productidArray[i]));
				installdetail.setIndeProductname(productNameArray[i]);
				installdetail.setIndePrice(new BigDecimal(priceArray[i]));
				installdetail.setIndeQuantity(Integer
						.parseInt(quantityArray[i]));
				installdetail.setIndeCreatedate(new Date());
				installdetail.setIndeUpdatedate(new Date());
				installdetail.setIndeProductcode(productCodeArray[i]);
				installdetailMapper.insert(installdetail);
			}
		}

		// 新增订单跟踪状态信息
		TblOrdertracking ordertracking = new TblOrdertracking();
		ordertracking.setOrtrId(appointment.getPkOrder());
		// 订单跟踪状态 1购买 2付款 3预约安装 4收货 5安装中 6完成
		ordertracking.setOrtrStatus(WanmaConstants.ORDER_TRACK_APPEMENT);
		ordertracking.setOrtrCreatedate(new Date());
		ordertracking.setOrtrUpdatedate(new Date());
		ordertrackingMapper.insert(ordertracking);
	}

	/*********** web ******/

	/**
	 * @Title: insertAppointmentNew
	 * @Description: 新增预约安装订单
	 * @param appointment
	 *            json字符串
	 * @param userId
	 *            登陆用户id
	 * @param orderAddressids
	 *            已购商品安装地址关联表主键                        
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void insertAppointmentNew(String appointment, int userId,String orderAddressids) {
//		根据主键获取未提交的预约安装商品的总数和总价格(多个)
		Map<String,Object> totalMap = orderAddressMapper.findTotalInstallInfo(orderAddressids);
		//商品总价格
		BigDecimal totalPrice = (BigDecimal)totalMap.get("totalPrice");
		//商品总数
		BigDecimal totalQuantity = (BigDecimal)totalMap.get("totalQuantity");
		List<Map<String, Object>> list = JSONObject.parseObject(
				appointment, List.class);
		TblAppointmentinstallationorder appointmentOrder = new TblAppointmentinstallationorder();
		appointmentOrder.setAlorInstallationordercode(BluemobiCommon
				.getOrderNo());
		appointmentOrder.setAlorUserid(userId);
		appointmentOrder.setAlorTellogin(webUserinfoMapper.get(userId + "")
				.getUsinPhone());
		appointmentOrder.setAlorMoney(totalPrice.longValue());
		appointmentOrder.setAlorCommoditytotal(totalQuantity.intValue());
		appointmentOrder
				.setAlorInstallationorderstatus(WanmaConstants.ORDER_STATUS_INSTALL_WAIT);
		
		appointmentOrder.setAlorBuyingtime(new Date());
		appointmentOrder.setAiorInstallationaddress("");
		appointmentOrder.setAlorInstallationperson("");
		appointmentOrder.setAlorLnstallationtel("");
		appointmentOrder.setAlorCommodityshops("");
		appointmentOrder.setaLorOrderType("0");
		
		
		appointmentMapper.insert(appointmentOrder);
		// 预约安装订单详情
		TblInstalldetail installdetail = new TblInstalldetail();
		OrderAddress orderAddress = new OrderAddress();
		for (Map<String, Object> map : list) {
//			根据主键获取未提交的预约安装商品的总数和总价格(单个)
			Map<String,Object> unitTotalMap = orderAddressMapper.findInstallInfo((String) map.get("addressid"));
			// 新增预约安装订单详情
			installdetail.setIndeParentid(appointmentOrder
					.getPkAppointmentinstallationorder());
			installdetail.setIndeParentid(appointmentOrder.getPkAppointmentinstallationorder());
			installdetail.setIndeProductid(Integer.parseInt((String) map
					.get("productid")));
			installdetail.setIndeProductname((String) map.get("productname"));
			installdetail.setIndeQuantity((Integer)unitTotalMap.get("quantity"));
			installdetail.setIndePrice((BigDecimal)unitTotalMap.get("unitPrice"));
			installdetail.setIndeTotalamount((BigDecimal)unitTotalMap.get("price"));
			installdetail.setIndeInstallationaddress((String) map
					.get("address"));
			installdetail.setIndeInstallationperson((String) map.get("people"));
			installdetail.setIndeLnstallationtel((String) map.get("phone"));
			installdetail.setIndeProductcode((String) map.get("productCode"));

			installdetail.setIndeCreatedate(new Date());
			installdetail.setIndeUpdatedate(new Date());
			installdetailMapper.insert(installdetail);
			// 更新已购商品地址关联表状态
			orderAddress.setPkOrderaddress(Integer.parseInt((String) map
					.get("addressid")));
			orderAddress.setOradStatus(WanmaConstants.ORAD__BESPOKE_YES);
			orderAddressMapper.updateOrderAddress(orderAddress);

		}

	}

}
