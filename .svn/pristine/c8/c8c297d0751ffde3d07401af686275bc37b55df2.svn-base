package com.wanma.app.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bluemobi.product.common.DataModel;
import com.bluemobi.product.common.MessageManager;
import com.wanma.app.util.AliSMS;
import com.wanma.app.util.DateUtil;
import com.wanma.app.util.HttpRequestUtil;
import com.bluemobi.product.utils.JPushUtil;
import com.wanma.app.dao.TblInvoiceMapper;
import com.wanma.app.dao.WebTblUserMapper;
import com.wanma.app.dao.WebCouponMapper;
import com.wanma.app.dao.WebPurchasehistoryMapper;
import com.wanma.app.service.WebJpushService;
import com.wanma.app.service.WebUserinfoService;
import com.wanma.dao.CmsCommitLogMapper;
import com.wanma.model.CommitLog;
import com.wanma.model.TblInvoice;
import com.wanma.model.TblJpush;
import com.wanma.model.TblPurchasehistory;
import com.wanma.model.TblUser;

/**
 * @author songjf
 * @Description: 用户信息业务处理类
 * @createTime：2015-3-13 上午10:31:56
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class WebUserinfoServiceImpl implements WebUserinfoService {
	private Log log = LogFactory.getLog(this.getClass());
	/**
	 * 用户信息业务操作DAO
	 */
	@Autowired
	private WebTblUserMapper userDao;
	@Autowired
	private WebPurchasehistoryMapper tblPurchasehistoryMapper;
	@Autowired
	private CmsCommitLogMapper commitLogDao;
	@Autowired
	private TblInvoiceMapper invoiceDao;
	@Autowired
	private WebCouponMapper couponMapper;
	@Autowired
	WebJpushService jpushService;

	/**
	 * 支付宝充值回调
	 */
	public Boolean addReChargeRecord(HttpServletRequest request) {
		String tradeNo = request.getParameter("out_trade_no");
		String totalFee = request.getParameter("total_fee");
		// body参数格式:userId_充值类型_订单id
		String[] extArray = request.getParameter("body").split("_");
		log.error("addReCharge-alipay:参数打印,tradeNo=" + tradeNo + ",totalFee="
				+ totalFee);
		String userId = "", puhiType = "", orderId = "", cityId = "";
		if (extArray.length == 1) {
			userId = extArray[0];
			puhiType = "4";
			orderId = "";
		} else {
			userId = extArray[0];
			puhiType = extArray[1];
			orderId = extArray[2];
			log.info("body size " + extArray.length);
			if (extArray.length == 4) {
				cityId = extArray[3];
			}
		}
		log.error("addReCharge-alipay:参数打印,userId=" + userId + ",puhiType="
				+ puhiType + ",orderId=" + orderId + ",cityId=" + cityId);
		String puhiPurchasecontent = "支付宝充值";
		if ("5".equals(puhiType)) {
			puhiPurchasecontent = "发票运费(支付宝)";
		}
		TblPurchasehistory purchase = new TblPurchasehistory();
		purchase.setPuhiUserid(new Integer(userId));
		purchase.setPuhiType(new Integer(puhiType));
		purchase.setPuhiMonetary(new BigDecimal(totalFee));
		purchase.setPuhiPaymentNumber(tradeNo);
		purchase.setPuhiConsumerremark(tradeNo);
		Date d = new Date();
		purchase.setPuhiPurchasehistorytime(d);
		purchase.setPuhiCreatedate(d);
		purchase.setPuhiUpdatedate(d);
		purchase.setPuhiPurchasecontent(puhiPurchasecontent);
		purchase.setPuhiChargeType(1);
		// 开发票
		if (puhiType.equals("5")) {
			log.error("addReCharge-alipay:发票充值标志处理");
			TblInvoice iv = new TblInvoice();
			iv.setPkInvoice(new Long(orderId));
			iv.setIvPayFreight(1);
			iv.setIvInvoiceStatus(0);
			iv.setIvFreightAmount(new BigDecimal(totalFee));
			invoiceDao.updateInvoice(iv);
		}
		int cityNo = 0;
		if (cityId != "") {
			cityNo = Integer.parseInt(cityId);
		}
		log.error("addReCharge-alipay:参数打印," + purchase);
		return userRecharge(purchase, request, cityNo);
	}

	/**
	 * 微信支付充值回调
	 * 
	 * @param map
	 * @param request
	 * @return
	 */
	public Boolean addReChargeRecordForTenpay(Map<String, Object> map,
			HttpServletRequest request) {
		String tradeNo = (String) map.get("out_trade_no");
		String totalFee = (String) map.get("total_fee");
		BigDecimal fee = new BigDecimal(totalFee).divide(new BigDecimal("100"),
				2, RoundingMode.UP);
		log.error("addReCharge-wechat:参数打印,tradeNo=" + tradeNo + ",totalFee="
				+ totalFee);

		String extString = tradeNo.split("ech")[1];
		String[] extArray = extString.split("_");
		int cityId = 0;
		if (map.containsKey("attach")) {
			cityId = Integer.parseInt(map.get("attach").toString());
		}
		String userId = "", puhiType = "", orderId = "";
		if (extArray.length == 1) {
			userId = extArray[0];
			puhiType = "4";
			orderId = "";
		} else {
			userId = extArray[0];
			puhiType = extArray[1];
			orderId = extArray[2];
		}
		log.error("addReCharge-wechat:参数打印,userId=" + userId + ",puhiType="
				+ puhiType + ",orderId=" + orderId + ",cityId=" + cityId);
		String puhiPurchasecontent = "微信充值";
		if ("5".equals(puhiType)) {
			puhiPurchasecontent = "发票运费(微信)";
		}
		TblPurchasehistory purchase = new TblPurchasehistory();
		purchase.setPuhiUserid(new Integer(userId));
		purchase.setPuhiType(new Integer(puhiType));
		purchase.setPuhiMonetary(fee);
		purchase.setPuhiPaymentNumber(tradeNo);
		purchase.setPuhiConsumerremark(tradeNo);
		Date d = new Date();
		purchase.setPuhiPurchasehistorytime(d);
		purchase.setPuhiCreatedate(d);
		purchase.setPuhiUpdatedate(d);
		purchase.setPuhiChargeType(2);
		purchase.setPuhiPurchasecontent(puhiPurchasecontent);
		// 开发票
		if (puhiType.equals("5")) {
			log.error("addReCharge-wechat:发票充值标志处理");
			TblInvoice iv = new TblInvoice();
			iv.setPkInvoice(new Long(orderId));
			iv.setIvPayFreight(2);
			iv.setIvInvoiceStatus(0);
			iv.setIvFreightAmount(fee);
			invoiceDao.updateInvoice(iv);
		}
		log.error("addReCharge-wechat:参数打印," + purchase);
		return userRecharge(purchase, request, cityId);

	}

	private Boolean userRecharge(TblPurchasehistory purchase,
			HttpServletRequest request, int code) {

		// 用户账户添加余额
		TblUser user = new TblUser();
		user.setUserId(purchase.getPuhiUserid().longValue());
		user = userDao.get(user);
		user.setNormAccountBalance(user.getNormAccountBalance().add(
				purchase.getPuhiMonetary()));
		log.error("addReCharge-all:用户信息,userId=" + user.getUserId()
				+ ",accountBalance=" + user.getNormAccountBalance() + "money="
				+ purchase.getPuhiMonetary());
		if (user.getNormAccountBalance().intValue() > 1000000) {
			return false;
		}
		// 添加充值记录
		long count = tblPurchasehistoryMapper.getPurchaseHistory(purchase);
		log.error("addReCharge-all:充值记录count=" + count);
		if (count == 0 && purchase.getPuhiType() == 4) {

			// 添加充值记录
			tblPurchasehistoryMapper.insert(purchase);
			userDao.updateFinAccount(user);
			try {
				// 1、充值送活动
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("actRule", 5);
				if (code != 0) {
					params.put("ccode", code);
				}
				List<Map<String, Object>> generateList = couponMapper
						.getGenerateCouponList(params);
				if (generateList != null && generateList.size() > 0) {
					TblPurchasehistory newPurchase = new TblPurchasehistory();
					newPurchase.setPuhiUserid(purchase.getPuhiUserid());
					newPurchase.setAct_beginTime(DateUtil.parse(generateList
							.get(0).get("act_beginDate").toString(),
							"yyyy-MM-dd"));
					chargeReturnCoupon(user.getUserId(), purchase
							.getPuhiMonetary().doubleValue(), generateList);// 充值送活动
				}
				log.error("addReCharge-all:充值送活动赠送成功");
			} catch (Exception e) {
				log.error("addReCharge-all:充值送活动赠送失败");
			}
			// 2.积分送活动
			try {
				Map<String, String> postpars = new HashMap<String, String>();
				postpars.put("userId", user.getUserId().toString());
				postpars.put("direction", "0");
				postpars.put("integralActivityId", "1");
				postpars.put("moneyInvolved", purchase.getPuhiMonetary()
						.toString());
				Map<String, String> postmap = new HashMap<String, String>();
				postmap.put("contents", postpars.toString());
				MessageManager mmg = new MessageManager();
				String ims_url = mmg.getSystemProperties("ims.url")
						+ "/integral/addIntegralDetails4Api.do";
				//HttpRequestUtil.post(ims_url, postmap);
				log.error("addReCharge-all:充值送积分赠送接口调用成功");
			} catch (Exception e) {
				log.error("addReCharge-all:充值送积分赠送接口调用失败");
			}

			return true;
		} else {
			log.error("addReCharge-all:充值回调多次,不做任何处理");
			return false;
		}
	}

	// 充值异常日志记录
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void chargeErrorLog(CommitLog commitLog, HttpServletRequest request) {
		String totalFee = commitLog.getLogContent();
		commitLog.setIpAddress(getIpAddr(request));
		commitLog.setCreateDate(new Date());
		commitLog.setUpdatedate(new Date());
		commitLog.setLogContent("充值金额为:" + totalFee);
		commitLogDao.insert(commitLog);
		TblUser user = new TblUser();
		user.setUserId(new Long(commitLog.getCoLoUserId()));
		user = userDao.get(user);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map smsParams = new HashMap();
		smsParams.put("order", sdf.format(new Date()));
		smsParams.put("num", totalFee);
		AliSMS.sendAliSMS(user.getUserAccount(), "totalFee",
				JSON.toJSONString(smsParams));
	}

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 充值送(规则解析：对于任意一次充值，始终只有一条最优规则，并且每个用户具有最高额限定)
	 * 
	 * @param uId
	 *            用户id
	 * @param cv
	 *            充值金额
	 * @param generateList
	 */
	private void chargeReturnCoupon(long uId, double cv,
			List<Map<String, Object>> generateList) {
		Map<String, Object> params = new HashMap<String, Object>();

		log.info("遍历优惠券");
		List<Map<String, Object>> generateList0 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> generateList1 = new ArrayList<Map<String, Object>>();

		if (generateList != null && generateList.size() > 0) {
			for (int i = 0; i < generateList.size(); i++) {
				int scope = Integer.valueOf(generateList.get(i)
						.get("act_Scope").toString());
				if (scope == 0) {
					generateList0.add(generateList.get(i));
				} else {
					generateList1.add(generateList.get(i));
				}
			}
		}
		log.info("chargeReturnCoupon");
		Double chargeSumB = couponMapper.getPersonChargeSum(params);
		double chargeSum = chargeSumB == null ? 0 : chargeSumB;
		double topMoney = Double.valueOf(generateList.get(0)
				.get("act_top_money").toString());
		double divisor = cv;
		if (chargeSum + cv > topMoney) {
			divisor = topMoney - chargeSum;
		}
		// 全国送
		double present0 = divisor;
		// 本地送
		double present1 = divisor;
		if (present1 > 0) {
			try {
				this.sendCoupon(uId, generateList1, present1);
			} catch (Exception e) {
				log.error("whole native coupon is error");
			}
		} else {
			log.info("whole native coupon is limit");
		}
		if (present0 > 0) {
			try {
				this.sendCoupon(uId, generateList0, present0);
			} catch (Exception e) {
				log.error("local native coupon is error");
			}
		} else {
			log.info("local native coupon is limit");
		}
	}

	public static Double sub(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return b1.subtract(b2).doubleValue();
	}

	public void sendCoupon(long uId, List<Map<String, Object>> generateList,
			double parapresent) {
		double present = parapresent;
		for (int i = 0; i < generateList.size(); i++) {
			Map<String, Object> obj0 = generateList.get(i);
			String singleMoneyStr = obj0.get("act_single_money").toString();

			int n = (int) Math.floor(present / Double.valueOf(singleMoneyStr));

			log.error("全国提前退出 " + String.valueOf(present) + "--"
					+ singleMoneyStr + "---count" + String.valueOf(n));
			if (Double.valueOf(singleMoneyStr) > present) {
				continue;
			}

			if (n >= 1) {
				int acscNum = 0;// 生成数量
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				Date d = new Date();
				List<Map<String, Object>> list = null;
				double money = Double.valueOf(singleMoneyStr);
				for (int j = 0; j < n && present >= money; j++) {
					Map<String, Object> obj = generateList.get(i);
					if (!singleMoneyStr.equals(obj.get("act_single_money")
							.toString()))
						return;
					acscNum = ((Integer) obj.get("acsc_num")) * 1;
					obj.put("cp_UserId", uId);
					obj.put("cp_Status", 0);
					obj.put("cp_CouponCode", "");
					obj.put("cp_BeginDate", sdf.format(d));
					obj.put("cp_EndDate", obj.get("act_CouponEndDate"));
					list = new ArrayList<Map<String, Object>>();

					for (int count = 0; count < acscNum; count++) {
						list.add(obj);
					}

					present = sub(present, Double.valueOf(singleMoneyStr));
					log.info("送券全国" + obj.toString());

					log.info("充值后金额 :" + String.valueOf(present));

					couponMapper.generateCode(list);
				}
			}
			// 获取用户推送信息
			TblJpush jpush = jpushService.getByuserInfo(Integer.valueOf(String
					.valueOf(uId)));
			DataModel dm = JPushUtil.point2point("新的优惠券", "",
					jpush.getJpushRegistrationid(), jpush.getJpushDevicetype(),
					"10");
			log.error(dm.getMsg() + "设备唯一标识：" + jpush.getJpushRegistrationid());
		}
	}

}
