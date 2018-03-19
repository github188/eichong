/** 
 * FileName UnionPayCommon.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/9/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */

package com.bluemobi.product.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PropertyResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.bluemobi.product.utils.AmountUtils;
import com.chinaums.pay.api.PayException;
import com.chinaums.pay.api.entities.NoticeEntity;
import com.chinaums.pay.api.entities.OrderEntity;
import com.chinaums.pay.api.entities.QueryEntity;
import com.chinaums.pay.api.impl.DefaultSecurityService;
import com.chinaums.pay.api.impl.UMSPayServiceImpl;

/**
 * 
 * @author yangwr testMerSignVerify(); 签名验签方法示例
 * 
 *         商户后台的下单、查询、通知可直接参考如下方法 testCreateOrder(); 创建订单 testQueryOrder(); 订单查询
 *         testNoticfyMer( HttpServletRequest httpRequest) 处理收到的通知
 * 
 *         相关参数均为测试环境参数
 * 
 */
public class UnionPayCommon {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(UnionPayCommon.class);

	/** properties 配置参数 */
	/** 银联关联配置文件名称 */
	private static final String CONF_FILE_NAME = "upmp.properties";
	/** 下单地址 */
	public static String KEY_CREATORDERURL = "creatOrderUrl";
	/** 订单查询地址 */
	public static String KEY_QUERYORDERURL = "queryOrderUrl";
	/** 银商验签公钥 (模) */
	public static String KEY_VERIFYKEYMOD = "verifyKeyMod";
	/** 银商验签公钥 (指数) */
	public static String KEY_VERIFYKEYEXP = "verifyKeyExp";
	/** 商户号 */
	public static String KEY_MERID = "merId";
	/** 终端号 */
	public static String KEY_MERTERMID = "merTermId";
	/** 商户签名私钥 (模) */
	public static String KEY_SIGNKEYMOD = "signKeyMod";
	/** 商户签名私钥 (指数) */
	public static String KEY_SIGNKEYEXP = "signKeyExp";
	/** 回调URL */
	public static String KEY_MERNOTICEURL = "merNoticeUrl";
	/** 订单有效时长 */
	public static String KEY_EFFECTIVETIME = "effectiveTime";
	/** 订单有效时长 */
	public static String KEY_ORDERDESC = "orderDesc";

	/** 订单返回状态:下单成功 */
	public static String ORDER_OK_STATUS_CODE = "0000";

	/** 支付返回状态:交易成功 */
	public static String TRANS_STATE_OK = "1";

	/** 支付返回状态:销账成功 */
	public static String PAY_OK_STATUS_CODE = "00";

	// 这2个地址是银商的测试地址
	private static String creatOrderUrl = "";
	private static String queryOrderUrl = "";
	// 测试环境银商验签公钥
	private static String verifyKeyMod = "";
	private static String verifyKeyExp = "";
	// 下面是商户的一些参数，需要根据实际修改
	private static String merId = "";// 商户号
	private static String merTermId = "";// 终端号
	// 测试环境商户签名私钥
	private static String signKeyMod = "";
	private static String signKeyExp = "";
	private static String merNoticeUrl = ""; // 换成商户的url,保证外网可以访问
	private static String orderDesc = ""; // 换成商户的url,保证外网可以访问
	private static String effectiveTime = "0"; // 订单有效期期限（秒），值小于等于0表示订单长期有效

	/**
	 * 加载银联配置文件
	 */
	static {

		try {
			InputStream fis = UnionPayCommon.class.getClassLoader()
					.getResourceAsStream(CONF_FILE_NAME);
			PropertyResourceBundle props = new PropertyResourceBundle(fis);
			creatOrderUrl = props.getString(KEY_CREATORDERURL);
			queryOrderUrl = props.getString(KEY_QUERYORDERURL);
			verifyKeyMod = props.getString(KEY_VERIFYKEYMOD);
			verifyKeyExp = props.getString(KEY_VERIFYKEYEXP);
			merId = props.getString(KEY_MERID);
			merTermId = props.getString(KEY_MERTERMID);
			signKeyMod = props.getString(KEY_SIGNKEYMOD);
			signKeyExp = props.getString(KEY_SIGNKEYEXP);
			merNoticeUrl = props.getString(KEY_MERNOTICEURL);
			orderDesc = props.getString(KEY_ORDERDESC);
			effectiveTime = props.getString(KEY_EFFECTIVETIME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下单处理
	 * 
	 * @param bookId
	 *            订单ID
	 * @param amount
	 *            金额
	 * @param order
	 *            下单请求对象
	 * @return String 商户客户端调用时使用的报文
	 */
	public static String createOrder(String bookId, double amount,
			OrderEntity order) {
		// 下单返回信息
		OrderEntity respOrder = null;

		// 测试参数
		DefaultSecurityService ss = new DefaultSecurityService();
		// 设置签名的商户私钥，验签的银商公钥
		ss.setSignKeyModHex(signKeyMod);// 签名私钥Mod
		ss.setSignKeyExpHex(signKeyExp);// 签名私钥Exp
		ss.setVerifyKeyExpHex(verifyKeyExp);// 公钥Mod
		ss.setVerifyKeyModHex(verifyKeyMod);// 公钥Exp

		UMSPayServiceImpl service = new UMSPayServiceImpl();
		service.setSecurityService(ss);
		service.setOrderServiceURL(creatOrderUrl);
		// 构建订单
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss",
				java.util.Locale.US);
		String curreTime = sf.format(new Date());

		if (order == null) {
			order = new OrderEntity();
		}

		order.setMerId(merId);// 商户号
		order.setMerTermId(merTermId);// 终端号
		order.setMerOrderId(bookId);// 订单号，商户根据自己的规则生成，最长32位
		order.setOrderDate(curreTime.substring(0, 8));// 订单日期
		order.setOrderTime(curreTime.substring(8));// 订单时间
		order.setTransAmt(moneyToFen(amount));// 订单金额(单位分)
		order.setOrderDesc(orderDesc);// 订单描述
		order.setNotifyUrl(merNoticeUrl);// 通知商户地址，保证外网能够访问
		order.setTransType("NoticePay");// 固定值
		order.setEffectiveTime(effectiveTime);// 订单有效期期限（秒），值小于等于0表示订单长期有效
		order.setMerSign(ss.sign(order.buildSignString()));
		log.info("下单请求数据:" + order.convertToJsonString());

		try {
			// 发送创建订单请求,该方法中已经封装了签名验签的操作，我们不需要关心，只需要设置好公私钥即可
			respOrder = service.createOrder(order);

			//
			// 将响应信息保存到一个对象
			//
			if (respOrder != null) {
				// 订单特征码
				order.setChrCode(respOrder.getChrCode());
				// 银商订单流水号
				order.setTransId(respOrder.getTransId());
				// 备用字段
				order.setReserve(respOrder.getReserve());
				// 响应码
				order.setRespCode(respOrder.getRespMsg());
				// 响应信息
				order.setRespMsg(respOrder.getRespMsg());
				// 签名数据
				order.setSignature(respOrder.getSignature());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("下单返回数据：" + respOrder.convertToJsonString());

		// content作为商户app调用全民付收银台客户端的参数，由商户后台传给商户客户端
		String content = ss.sign(respOrder.getTransId()
				+ respOrder.getChrCode())
				+ "|"
				+ respOrder.getChrCode()
				+ "|"
				+ respOrder.getTransId()
				+ "|" + merId;
		log.info(content);

		// 返回商户客户端调用时使用的报文
		return content;
	}

	/**
	 * 从前次银联下单申请数据中获得报文
	 * 
	 * @param respOrder
	 *            前次银联下单申请数据
	 * 
	 * @return String 商户客户端调用时使用的报文
	 */
	public static String getOrderedContent(OrderEntity respOrder) {

		// 参数
		DefaultSecurityService ss = new DefaultSecurityService();
		// 设置签名的商户私钥，验签的银商公钥
		ss.setSignKeyModHex(signKeyMod);// 签名私钥Mod
		ss.setSignKeyExpHex(signKeyExp);// 签名私钥Exp
		ss.setVerifyKeyExpHex(verifyKeyExp);// 公钥Mod
		ss.setVerifyKeyModHex(verifyKeyMod);// 公钥Exp

		// content作为商户app调用全民付收银台客户端的参数，由商户后台传给商户客户端
		String content = ss.sign(respOrder.getTransId()
				+ respOrder.getChrCode())
				+ "|"
				+ respOrder.getChrCode()
				+ "|"
				+ respOrder.getTransId()
				+ "|" + merId;
		log.info(content);

		// 返回商户客户端调用时使用的报文
		return content;

	}

	/**
	 * 订单查询
	 * 
	 * @param orderEntity
	 *            订单信息
	 * @return QueryEntity 订单查询结果对象
	 */
	public static QueryEntity queryOrder(OrderEntity orderEntity) {

		if (orderEntity == null) {
			return null;
		}

		// 测试参数
		DefaultSecurityService ss = new DefaultSecurityService();
		// 设置签名的商户私钥，验签的银商公钥
		ss.setSignKeyModHex(signKeyMod);// 签名私钥Mod

		// 签名私钥Exp
		ss.setSignKeyExpHex(signKeyExp);
		ss.setVerifyKeyExpHex(verifyKeyExp);
		ss.setVerifyKeyModHex(verifyKeyMod);
		UMSPayServiceImpl service = new UMSPayServiceImpl();
		service.setSecurityService(ss);
		service.setQueryServiceURL(queryOrderUrl);
		QueryEntity queryOrder = new QueryEntity();
		queryOrder.setMerId(merId);
		queryOrder.setMerTermId(merTermId);
		// 下单返回的TransId
		queryOrder.setTransId(orderEntity.getTransId());
		// 商户的订单号
		queryOrder.setMerOrderId(orderEntity.getMerOrderId());
		// 下单日期
		queryOrder.setOrderDate(orderEntity.getOrderDate());
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss",
				java.util.Locale.US);
		String curreTime = sf.format(new Date());
		queryOrder.setReqTime(curreTime);
		log.info("订单查询请求数据:" + queryOrder.convertToJsonString());
		QueryEntity respOrder = new QueryEntity();

		try {
			respOrder = service.queryOrder(queryOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("订单查询返回数据：" + respOrder.convertToJsonString());

		return respOrder;
	}

	/**
	 * 通知商户接口,银商发送http请求过来，只有支付成功银商才会通知商户
	 * 
	 * @throws PayException
	 */

	public static NoticeEntity noticfyMer(HttpServletRequest httpRequest) {
		// 测试参数
		DefaultSecurityService ss = new DefaultSecurityService();
		// 银商传过来的参数
		NoticeEntity noticeEntity = null;
		try {
			// 设置签名的商户私钥，验签的银商公钥
			ss.setSignKeyModHex(signKeyMod);// 签名私钥Mod
			ss.setSignKeyExpHex(signKeyMod);// 签名私钥Exp
			ss.setVerifyKeyExpHex(verifyKeyExp);
			ss.setVerifyKeyModHex(verifyKeyMod);
			UMSPayServiceImpl service = new UMSPayServiceImpl();
			service.setSecurityService(ss);
			// 1.银商会传这些参数过来
			// 2.处理银商传过来的参数，例如修改订单号等。
			noticeEntity = service.parseNoticeEntity(httpRequest);
			log.error("noticeEntity.toString()："
					+ noticeEntity.convertToJsonString());

			if (noticeEntity != null
					&& StringUtils.equals(TRANS_STATE_OK,
							noticeEntity.getTransState())) {
				// 3.响应给银商的参数：
				NoticeEntity respEntity = new NoticeEntity();
				respEntity.setMerOrderState("00");
				String respStr = service.getNoticeRespString(respEntity);
				log.error("params.toString()：" + respStr);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			//
			return null;
		}

		// 返回银商传过来的参数
		return noticeEntity;
	}

	public static void main(String[] args) {
		 //UnionPayCommon.createOrder("B-2014-00000099", 100, new
		 //OrderEntity());
		// testMerSignVerify();
		//testCreateOrder();
		 testQueryOrder();
		// System.out.println(moneyToFen(99.99));
	}

	// 下单
	public static void testCreateOrder() {
		// 测试参数
		DefaultSecurityService ss = new DefaultSecurityService();
		// 设置签名的商户私钥，验签的银商公钥
		ss.setSignKeyModHex(signKeyMod);// 签名私钥Mod
		ss.setSignKeyExpHex(signKeyExp);// 签名私钥Exp
		ss.setVerifyKeyExpHex(verifyKeyExp);
		ss.setVerifyKeyModHex(verifyKeyMod);

		UMSPayServiceImpl service = new UMSPayServiceImpl();
		service.setSecurityService(ss);
		service.setOrderServiceURL(creatOrderUrl);
		// 构建订单
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss",
				java.util.Locale.US);
		String curreTime = sf.format(new Date());
		OrderEntity order = new OrderEntity();
		order.setMerId(merId);// 商户号
		order.setMerTermId(merTermId);// 终端号
		order.setMerOrderId("E-000000000000000000111111");// 订单号，商户根据自己的规则生成，最长32位
		order.setOrderDate(curreTime.substring(0, 8));// 订单日期
		order.setOrderTime(curreTime.substring(8));// 订单时间
		order.setTransAmt("1");// 订单金额(单位分)
		order.setOrderDesc("某某商户订单描述");// 订单描述
		order.setNotifyUrl(merNoticeUrl);// 通知商户地址，保证外网能够访问
		order.setTransType("NoticePay");// 固定值
		order.setEffectiveTime("0");// 订单有效期期限（秒），值小于等于0表示订单长期有效
		order.setMerSign(ss.sign(order.buildSignString()));
		System.out.println("下单请求数据:" + order);
		OrderEntity respOrder = new OrderEntity();
		try {
			// 发送创建订单请求,该方法中已经封装了签名验签的操作，我们不需要关心，只需要设置好公私钥即可
			respOrder = service.createOrder(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("下单返回数据：" + respOrder);
		// content作为商户app调用全民付收银台客户端的参数，由商户后台传给商户客户端
		String content = ss.sign(respOrder.getTransId()
				+ respOrder.getChrCode())
				+ "|"
				+ respOrder.getChrCode()
				+ "|"
				+ respOrder.getTransId()
				+ "|" + merId;
		System.out.println(content);
	}

	// 订单查询
	public static void testQueryOrder() {
		// 测试参数
		DefaultSecurityService ss = new DefaultSecurityService();
		// 设置签名的商户私钥，验签的银商公钥
		ss.setSignKeyModHex(signKeyMod);// 签名私钥Mod

		ss.setSignKeyExpHex(signKeyExp);// 签名私钥Exp
		ss.setVerifyKeyExpHex(verifyKeyExp);
		ss.setVerifyKeyModHex(verifyKeyMod);
		UMSPayServiceImpl service = new UMSPayServiceImpl();
		service.setSecurityService(ss);
		service.setQueryServiceURL(queryOrderUrl);
		QueryEntity queryOrder = new QueryEntity();
		queryOrder.setMerId(merId);
		queryOrder.setMerTermId(merTermId);
		queryOrder.setTransId("662014112126894439");// 下单返回的TransId
		queryOrder.setMerOrderId("B-2014-00000061");// 商户的订单号
		queryOrder.setOrderDate("20141121");// 下单日期
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss",
				java.util.Locale.US);
		String curreTime = sf.format(new Date());
		queryOrder.setReqTime(curreTime);
		System.out.println("订单查询请求数据:" + queryOrder);
		QueryEntity respOrder = new QueryEntity();
		try {
			respOrder = service.queryOrder(queryOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("订单查询返回数据：" + respOrder);
	}

	/**
	 * 通知商户接口,银商发送http请求过来，只有支付成功银商才会通知商户
	 * 
	 * @throws PayException
	 */

	public static void testNoticfyMer(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws PayException {
		// 测试参数
		DefaultSecurityService ss = new DefaultSecurityService();
		try {
			// 设置签名的商户私钥，验签的银商公钥
			ss.setSignKeyModHex(signKeyMod);// 签名私钥Mod
			ss.setSignKeyExpHex(signKeyMod);// 签名私钥Exp
			ss.setVerifyKeyExpHex(verifyKeyExp);
			ss.setVerifyKeyModHex(verifyKeyMod);
			UMSPayServiceImpl service = new UMSPayServiceImpl();
			service.setSecurityService(ss); // 1.银商会传这些参数过来
			// 2.处理银商传过来的参数，例如修改订单号等。
			NoticeEntity noticeEntity = service.parseNoticeEntity(httpRequest);
			log.error("noticeEntity.toString()：" + noticeEntity);
			// 3.响应给银商的参数：
			NoticeEntity respEntity = new NoticeEntity();
			respEntity.setMerOrderState("00");// respEntity.set...
			String respStr = service.getNoticeRespString(respEntity);
			httpResponse.setCharacterEncoding("utf-8");
			PrintWriter writer = httpResponse.getWriter();
			System.out.println("params.toString()：" + respStr);
			writer.write(respStr);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试客户端签名，服务端验签
	 */
	public static void testMerSignVerify() {
		String singKeyMod = "8accede946e8f9605d5a87336101a62c4964214ab8e431668bb3ce5b2c7f7fc3e189b6b2c227e3ea1e5eb9e9cdc7b290a707c219f5e5301c1e79078bd2a45f526a94ed0cb30bef49948fd149f4c355867e781693cbfd115620d83f791c2c5c14e6644b1010ace6839f16ed4a38055a6624f95c8303a3616545a9194e1c97329f";
		String singKeyExp = "11bb24194cc9ae31b53b3f523cee2d00334feb40d465b239db56d8e1a4c994d4bd170e93bf61c127c3416e96838723347f61ea55cea7bc819b12f2f7d5acd1d0514c01a39492357ca396815d05d551ebd8cec84254c9d1e5ecf42a12f5e413cb82d13865dd475627a4b7f326b83d0fa4f3091f06b629316b289547acf56c6521";
		String verifyKeyMod = "8accede946e8f9605d5a87336101a62c4964214ab8e431668bb3ce5b2c7f7fc3e189b6b2c227e3ea1e5eb9e9cdc7b290a707c219f5e5301c1e79078bd2a45f526a94ed0cb30bef49948fd149f4c355867e781693cbfd115620d83f791c2c5c14e6644b1010ace6839f16ed4a38055a6624f95c8303a3616545a9194e1c97329f";
		String verifyKeyExp = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010001";

		try {

			String data = "2013082116014120130821160141Z11brBfdT066201308212440612100000下单成功";
			// 测试参数
			DefaultSecurityService ss = new DefaultSecurityService();
			// 设置签名的商户私钥，验签的银商公钥
			ss.setSignKeyModHex(singKeyMod);// 签名私钥Mod
			ss.setSignKeyExpHex(singKeyExp);// 签名私钥Exp
			ss.setVerifyKeyExpHex(verifyKeyExp);// 验签公钥exp
			ss.setVerifyKeyModHex(verifyKeyMod);// 验签公钥mod
			// 签名
			String sign = ss.sign(data);
			System.out.println("签名数据：" + sign);
			// 验签
			boolean verifyResult = ss.verify(data, sign);
			System.out.println("验签结果:" + verifyResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将金额转换成分
	 * 
	 * @param amount
	 *            金额
	 * @return String 分为单位的金额
	 */
	public static String moneyToFen(double amount) {
		// 分为单位的金额
		String toFenTemp = String.valueOf(amount);
		// 临时保存用金额
		String toFen = AmountUtils.changeY2F(toFenTemp);

		// 返回分为单位的金额
		return toFen;
	}
}
