package com.wanma.controller.wechat;

import com.wanma.model.WxAccount;
import com.wanma.model.WxAccountDetail;
import com.wanma.model.WxElectircpile;
import com.wanma.service.TblChargingOrderService;
import com.wanma.service.TblElectricpileService;
import com.wanma.service.WeChatService;
import com.wanma.support.common.AccessErrorResult;
import com.wanma.support.common.AccessSuccessResult;
import com.wanma.support.common.RedisService;
import com.wanma.support.common.WanmaConstants;
import com.wanma.support.utils.ControllerUtil;
import com.wanma.support.utils.WeChatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @name 微信服务号
 * @author gao_xiang
 * @date 2017_04_06
 */
@Controller
@RequestMapping("/WeChatCharge")
public class WeChatChgController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WeChatChgController.class);

	@Autowired
	private WeChatService weChatService;

	@Autowired
	private TblChargingOrderService ordService;

	@Autowired
	private TblElectricpileService eService;

	@Autowired
	private RedisService redisService;

	/**
	 * 获取用户充电状态
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getChgStatus")
	@ResponseBody
	public String getChgStatus(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Access-Control-Allow-Headers", "Content-Type");

		LOGGER.info("获取用户充电状态开始，");
		// 微信用户标识
		String code = request.getParameter("code");
		String openId = request.getParameter("openId");

		if (StringUtils.isEmpty(code)) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}

		if ("".equals(openId)) {
			openId = WeChatUtil.getOpenId(code);
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("openId", openId);

		try {

			String data = redisService.strGet("wechat:"+openId);
			if ("".equals(data) || data == null) {
				map.put("status", "0");
				return new AccessSuccessResult(map).toString();

			} else {

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss");
				Date now = new Date();

					Date d = sdf.parse(data);
					// 如果大于十分钟，则为未充电状态
					if (now.getTime() - d.getTime() > 600000) {
						map.put("status", "0");
						return new AccessSuccessResult(map).toString();
					}
			
				map.put("status", "1");
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取用户充电状态失败！");
			return new AccessErrorResult(1000, "未知错误！").toString();
		}
		LOGGER.info("获取用户充电状态结束！");
		return new AccessSuccessResult(map).toString();
	}

	/**
	 * 根据epCodes 获取充电桩信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getElectricPile")
	@ResponseBody
	public String getElectricPile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		LOGGER.info("获取充电桩信息开始，");

		// 充电桩屏幕上的二维码识别号(6位)
		String epCodes = request.getParameter("epCodes");
		if (StringUtils.isEmpty(epCodes)) {
			return new AccessErrorResult(1050, "请输入完整参数！").toString();
		}
		WxElectircpile ElectricPile;
		try {
			ElectricPile = weChatService.getElectricPile(epCodes);

			if (ElectricPile == null) {
				return new AccessErrorResult(1004, "未找到该识别码的电桩信息").toString();
			}

			if ("0".equals(ElectricPile.getCommStatus())) {

				return new AccessErrorResult(1001, "电桩通讯未连接").toString();
			}
			if (!"0".equals(ElectricPile.getePHeElectricpileHeadState())) {
				return new AccessErrorResult(1002, "电桩枪头未空闲").toString();
			}
			if (!"1".equals(ElectricPile.getElPiState())) {
				return new AccessErrorResult(1003, "电桩未分享").toString();

			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取充电桩信息失败！");
			return new AccessErrorResult(1000, "未知错误！").toString();
		}
		LOGGER.info("获取充电桩信息结束，");
		return new AccessSuccessResult(ElectricPile).toString();
	}

	/**
	 * 微信公众号结束充电
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/wxChargeStop")
	@ResponseBody
	public String wxChargeStop(HttpServletRequest request) throws Exception {

		int org = WanmaConstants.WX_CODE;
		String openId = request.getParameter("openId");
		String pileCode = request.getParameter("pileCode");
		String gunCode = request.getParameter("gunCode");

		String data = redisService.strGet("wechat:"+openId);
		if ("".equals(data) || data == null) {
			return new AccessErrorResult(2004, "充电已结束").toString();
		}
		/*
		 * String[] str = data.split("\\|"); String epcode=str[3]; String
		 * epcodeGun=str[4];
		 */
		WanmaConstants.stopCgEvt.remove(openId);

		LOGGER.info("下发停止充电命令开始，微信标识：" + openId + ";第三方标识：" + org);
		int rtCode = WanmaConstants.cs.stopChange(pileCode,
				Integer.parseInt(gunCode), org, openId, "");// 下发结束充电命令
		LOGGER.info("下发停止充电命令结束！");

		return ControllerUtil.doReturn(WanmaConstants.stopCgEvt, openId);

	}

	/**
	 * 获取充电实时信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getOrderInfo")
	@ResponseBody
	public String getOrderInfo(HttpServletRequest request) throws Exception {
		int org = WanmaConstants.WX_CODE;
		String openId = request.getParameter("openId");
		String pileCode = request.getParameter("pileCode");
		String gunCode = request.getParameter("gunCode");

		String datas = redisService.strGet("wechat:"+openId);
		if ("".equals(datas) || datas == null) {
			return ControllerUtil.doReturn(WanmaConstants.stopCgEvt, openId);
		}
		/*
		 * String[] str = datas.split("\\|"); String epcode=str[3]; String
		 * epcodeGun=str[4];
		 */
		LOGGER.info("获取充电实时订单信息结束！");
		return ControllerUtil.doReturn(WanmaConstants.chargingOrderEvt, openId);
	}

	/**
	 * 获取用户账单
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getWxAccount")
	@ResponseBody
	public String getWxAccount(HttpServletRequest request) throws Exception {
		LOGGER.info("获取微信账单开始，");
		// 微信用户标识,缓存到用户本地，可为空
		String openId = request.getParameter("openId");
		// 用户页面获取的授权code,不可为空
		String code = request.getParameter("code");

		if (StringUtils.isEmpty(code)) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		if ("".equals(openId)) {
			openId = WeChatUtil.getOpenId(code);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<WxAccount> account;
		try {

			account = weChatService.getWeChatAccount(openId);
			map.put("account", account);
			map.put("openId", openId);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取微信账单失败，");
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		LOGGER.info("获取微信账单结束，");
		return new AccessSuccessResult(map).toString();
	}

	/**
	 * 获取用户账单明细
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getWxAccountDetail")
	@ResponseBody
	public String getWxAccountDetail(HttpServletRequest request)
			throws Exception {
		LOGGER.info("获取微信账单明细开始，");
		// 微信用户标识
		String openId = request.getParameter("openId");
		String month = request.getParameter("month");

		if (StringUtils.isEmpty(openId)) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}

		List<WxAccountDetail> accountDetail;
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("openId", openId);
			map.put("month", month);

			accountDetail = weChatService.getWxAccountDetail(map);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取微信账单明细失败，");
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		LOGGER.info("获取微信账单明细结束，");
		return new AccessSuccessResult(accountDetail).toString();
	}

}
