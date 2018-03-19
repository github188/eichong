package com.wanma.controller.tpi;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.model.TblUser;
import com.wanma.service.TblElectricpileHeadService;
import com.wanma.service.TblElectricpileService;
import com.wanma.service.TblUserService;
import com.wanma.support.common.FailedResponse;
import com.wanma.support.common.RedisService;
import com.wanma.support.common.WanmaConstants;
import com.wanma.support.utils.ApiUtil;
import com.wanma.support.utils.HttpUtil;
import com.wanma.support.utils.PasswordUtil;

/**
 * @Description: 电桩充电控制层
 * @author wbc
 * @createTime：2015-11-19 16:25:05
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/tpi/electric")
public class TpiChargeController {
	@Autowired
	private TblElectricpileService electricpileService;
	@Autowired
	private TblElectricpileHeadService electricpileHeadService;
	@Autowired
	private TblUserService userService;

	@Autowired
	private RedisService redisService;

	/**
	 * 扫码获取桩信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/selEpInfo")
	@ResponseBody
	public String selEpInfo(HttpServletRequest request) {
		String epCode = request.getParameter("epCode");
		String ephCode = request.getParameter("ephCode");
		if (StringUtils.isEmpty(ephCode) || StringUtils.isEmpty(epCode)) {
			return new FailedResponse(1050, "请求参数缺失").toString();
		}

		Map<String, String> params = new HashMap<String, String>();
		params.put("elpiElectricpilecode", epCode);
		params.put("ePHeElectricpileHeadId", ephCode);
		String jsonRes = HttpUtil.doPost(
				WanmaConstants.API_URL_SELECT_PILEINFO, params);
		jsonRes = jsonRes.replace("pkElectricpile", "epId");
		jsonRes = jsonRes.replace("elpiElectricpilecode", "epCode");
		jsonRes = jsonRes.replace("elpiElectricpilename", "epName");
		jsonRes = jsonRes.replace("ePHeElectricpileHeadId", "ephCode");
		jsonRes = jsonRes.replace("ePHe_ElectricpileHeadState", "ephState");
		jsonRes = jsonRes.replace("elpiElectricpileaddress", "epAddr");
		jsonRes = jsonRes.replace("comm_status ", "conStatus");

		return jsonRes;
	}

	/**
	 * 数字扫码获取桩信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/zNumSelPileInfo")
	@ResponseBody
	public String zNumSelPileInfo(HttpServletRequest request) {
		String zCodeNum = request.getParameter("zCodeNum");
		if (StringUtils.isEmpty(zCodeNum)) {
			return new FailedResponse(1050, "请求参数缺失").toString();
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("zCodeNum", zCodeNum);
		return HttpUtil.doPost(WanmaConstants.API_URL_SELECT_NUM_PILEINFO,
				params);
	}

	/**
	 * 开始充电
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/beginCharge")
	@ResponseBody
	public String beginCharge(HttpServletRequest request) {
		String epId = request.getParameter("epId");
		String epCode = request.getParameter("epCode");
		String ephCode = request.getParameter("ephCode");
		String userAcc = request.getParameter("userAcc");
		String preMoney = request.getParameter("preMoney");
		if (StringUtils.isEmpty(epId) || StringUtils.isEmpty(epCode)
				|| StringUtils.isEmpty(ephCode) || StringUtils.isEmpty(userAcc)) {
			return new FailedResponse(1050, "请求参数缺失").toString();
		}

		Map<String, String> params = new HashMap<String, String>();
//		TblUser user = userService.getNormUserByAccount(userAcc);
//		if (null == user || user.getUserId() == 0) {
//			return new FailedResponse(1001, "用户未初始化").toString();
//		}
		params.put("pkUserinfo", "0");
		params.put("pkElectricPile", epId);
		params.put("userAcc", userAcc);
		params.put("elpiElectricpilecode", epCode);
		params.put("ePHeElectricpileHeadId", ephCode);
		params.put("scantype", "1");
		params.put("did", ApiUtil.encode(WanmaConstants.DEFAULT_DEVICE_ID));
		params.put("preMoney", preMoney);
		String org = request.getParameter("org");
		params.put("org", org);
//		params.put(
//				"paymod",
//				redisService.strGet(WanmaConstants.PREFIX_ORG + org).split(":")[1]);
		params.put("payMod","2");

		return HttpUtil.doPost(WanmaConstants.API_URL_BEGIN_CHARGE_TPI, params);

	}

	/**
	 * 结束充电
	 * 
	 * @author mb
	 * @param request
	 */
	@RequestMapping("/finishCharge")
	@ResponseBody
	public String finishCharge(HttpServletRequest request) {
		String epId = request.getParameter("epId");// 桩体id
		String epCode = request.getParameter("epCode");// 桩体编号
		String ephCode = request.getParameter("ephCode");// 枪头编号
		String userAcc = request.getParameter("userAcc");//用户标识
		String org = request.getParameter("org");//组织编号
		// 准备参数params
		Map<String, String> params = new HashMap<String, String>();
		// 判断信息是否完整, 如不完整则返回FailedResponse
		if (StringUtils.isBlank(epId) || StringUtils.isBlank(epCode)
				|| StringUtils.isBlank(ephCode)) {
			return new FailedResponse(1050, "输入参数不全！").toString();
		}
		if (StringUtils.isBlank(userAcc)) {
			return new FailedResponse(1050, "用户帐号不能为空").toString();
		}
//		else {
//			TblUser user = userService.getNormUserByAccount(userAcc);
//			if (null == user || user.getUserId() == 0) {
//				return new FailedResponse(1001, "用户未初始化").toString();
//			}
//			params.put("uid", user.getUserId() + "");
//			String did = user.getNormDeviceId();
//			did = ApiUtil.encode(did);
//			params.put("did", did);
//		}
		params.put("uid", "0");
		params.put("org", org);
		params.put("userAcc", userAcc);
		params.put("pkElectricPile", epId);
		params.put("elpiElectricpilecode", epCode);
		params.put("ePHeElectricpileHeadId", ephCode);
		return HttpUtil.doPost(WanmaConstants.API_URL_FINISH_CHARGE_TPI, params);
	}

	/**
	 * @Description: 充电订单列表（只列10条）
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/chargeOrderList")
	@ResponseBody
	public String chargeOrderList(HttpServletRequest request) {
		String userAcc = request.getParameter("userAcc");
		Map params = new HashMap();
		if (StringUtils.isBlank(userAcc)) {
			return new FailedResponse(1050, "用户帐号不能为空").toString();
		} else {
			TblUser user = userService.getNormUserByAccount(userAcc);
			if (null == user || user.getUserId() == 0) {
				return new FailedResponse(1001, "用户未初始化").toString();
			}
			params.put("userId", user.getUserId() + "");
		}
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_CHARGE_ORDERLIST, params);
	}

	/**
	 * @Description: 充电订单详情
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/chargeOrderDetail")
	@ResponseBody
	public String chargeOrderDetail(HttpServletRequest request) {
//		String userAcc = request.getParameter("userAcc");
		String coId = request.getParameter("coId");
		Map params = new HashMap();
		if (StringUtils.isBlank(coId)) {
			return new FailedResponse(1050, "充电订单不能为空").toString();
		}
		params.put("coId", coId);
//		if (StringUtils.isBlank(userAcc)) {
//			return new FailedResponse(1050, "用户帐号不能为空").toString();
//		}
//		else {
//			TblUser user = userService.getNormUserByAccount(userAcc);
//			if (null == user || user.getUserId() == 0) {
//				return new FailedResponse(1001, "用户未初始化").toString();
//			}
//			params.put("userId", user.getUserId() + "");
//		}
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_CHARGE_ORDERLIST, params);
	}
}
