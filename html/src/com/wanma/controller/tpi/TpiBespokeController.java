package com.wanma.controller.tpi;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblUser;
import com.wanma.service.TblElectricpileHeadService;
import com.wanma.service.TblElectricpileService;
import com.wanma.service.TblUserService;
import com.wanma.support.common.FailedResponse;
import com.wanma.support.common.RedisService;
import com.wanma.support.common.ResultResponse;
import com.wanma.support.common.WanmaConstants;
import com.wanma.support.utils.ApiUtil;
import com.wanma.support.utils.HttpUtil;

/**
 * @Description: 电桩预约控制层
 * @author wbc
 * @createTime：2015-11-19 16:25:05
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/tpi/electric")
public class TpiBespokeController {
	@Autowired
	private TblElectricpileService electricpileService;
	@Autowired
	private TblElectricpileHeadService electricpileHeadService;
	@Autowired
	private TblUserService userService;

	@Autowired
	private RedisService redisService;

	/**
	 * @Description: 获取预约枪头详情
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/getBespokeHeadDetail")
	@ResponseBody
	public String getBespokeHeadDetail(HttpServletRequest request) {
		String electricId = request.getParameter("electricId");
		// electricType:1电桩，2电站
		String electricType = request.getParameter("electricType");
		if (StringUtils.isBlank(electricId)
				|| StringUtils.isBlank(electricType)) {
			return new FailedResponse(1001, "params error").toString();
		}
		TblElectricpilehead head = new TblElectricpilehead();
		if (electricType.equals("1")) {
			head.setPkElectricpile(new Integer(electricId));
			head = electricpileHeadService.getBespokeHeadDetailByPile(head);
		} else {
			head.setPkPowerStation(new Integer(electricId));
			head = electricpileHeadService.getBespokeHeadDetailByPile(head);
		}
		Map map = new HashMap();
		map.put("pId", head.getPkElectricpile());
		map.put("hId", head.getPkElectricpilehead());
		map.put("hName", head.getEpheElectricpileheadname());
		map.put("rate", head.getRaInReservationRate());
		return new ResultResponse(map).toString();
	}

	/**
	 * @Description：枪头预约、续约
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/bespoke")
	@ResponseBody
	public String insertBespoke(HttpServletRequest request) {
		String pkBespoke = request.getParameter("pkBespoke");
		String bespElectricpileid = request.getParameter("epId");
		String bespBespoketime = request.getParameter("bkTime");
		String bespElectricpilehead = request.getParameter("ephId");
		String userAcc = request.getParameter("userAcc");
		String bespFrozenamt = request.getParameter("frozenamt");
		Map params = new HashMap();
		params.put("pkBespoke", StringUtils.isNotBlank(pkBespoke) ? pkBespoke
				: null);
		if (StringUtils.isBlank(bespElectricpileid)) {
			return new FailedResponse(1050, "电桩ID不能为空").toString();
		} else {
			params.put("bespElectricpileid", bespElectricpileid);
		}
		if (StringUtils.isBlank(bespBespoketime)) {
			return new FailedResponse(1050, "预约时间不能为空").toString();
		}
		if (Integer.parseInt(bespBespoketime) > 360) {
			return new FailedResponse(1001, "预约时间不可超过6小时").toString();
		} else {
			params.put("bespBespoketime", bespBespoketime);
		}
		if (StringUtils.isBlank(bespElectricpilehead)) {
			return new FailedResponse(1050, "枪口ID不能为空").toString();
		} else {
			params.put("bespElectricpilehead", bespElectricpilehead);
		}
		if (StringUtils.isBlank(userAcc)) {
			return new FailedResponse(1050, "用户帐号不能为空").toString();
		} else {
			TblUser user = userService.getNormUserByAccount(userAcc);
			if (null == user || user.getUserId() == 0) {
				return new FailedResponse(1001, "用户未初始化").toString();
			}
			params.put("bespUserinfo", user.getUserId() + "");
		}
		String org = request.getParameter("org");
		params.put("partner", org);
		String parmod = redisService.strGet(WanmaConstants.PREFIX_ORG + org)
				.split(":")[1];
		params.put("paymod", parmod);
		params.put("bespFrozenamt", bespFrozenamt);
		String did = WanmaConstants.DEFAULT_DEVICE_ID;
		did = ApiUtil.encode(did);
		params.put("did", did);

		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_INSERT_BESPOKE, params);
	}

	/**
	 * @Description: 取消预约
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/cancelBespoke")
	@ResponseBody
	public String cancelBespoke(HttpServletRequest request) {
		String pkBespoke = request.getParameter("pkBespoke");
		String ephId = request.getParameter("ephId");
		String userAcc = request.getParameter("userAcc");
		Map params = new HashMap();
		if (StringUtils.isBlank(pkBespoke) || StringUtils.isBlank(ephId)) {
			return new FailedResponse(1001, "params error").toString();
		}
		params.put("pkBespoke", pkBespoke);
		params.put("bespElectricpilehead", ephId);
		if (StringUtils.isBlank(userAcc)) {
			return new FailedResponse(1050, "用户帐号不能为空").toString();
		} else {
			TblUser user = userService.getNormUserByAccount(userAcc);
			if (null == user || user.getUserId() == 0) {
				return new FailedResponse(1001, "用户未初始化").toString();
			}
			params.put("uId", user.getUserId() + "");
			String did = user.getNormDeviceId();
			did = ApiUtil.encode(did);
			params.put("did", did);
		}
		return HttpUtil.doPost(WanmaConstants.API_URL_CANCEL_BESPOKE, params);
	}

	/**
	 * @Description: 预约列表
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/bespokeList")
	@ResponseBody
	public String bespokeList(HttpServletRequest request) {
		String userAcc = request.getParameter("userAcc");
		Map params = new HashMap();
		if (StringUtils.isBlank(userAcc)) {
			return new FailedResponse(1050, "用户帐号不能为空").toString();
		} else {
			TblUser user = userService.getNormUserByAccount(userAcc);
			if (null == user || user.getUserId() == 0) {
				return new FailedResponse(1001, "用户未初始化").toString();
			}
			params.put("bespUserInfo", user.getUserId());
		}

		return HttpUtil.doPost(WanmaConstants.API_URL_CANCEL_BESPOKE, params);
	}

	/**
	 * @Description: 预约详情
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/bespokeDetail")
	@ResponseBody
	public String bespokeDetail(HttpServletRequest request) {
		String pkBespoke = request.getParameter("pkBespoke");
		if (StringUtils.isBlank(pkBespoke) ) {
			return new FailedResponse(1001, "params error").toString();
		}
		Map params = new HashMap();
		params.put("pkBespoke", pkBespoke);
		return HttpUtil.doPost(WanmaConstants.API_URL_BESPOKE_DETAIL, params);
	}
}
