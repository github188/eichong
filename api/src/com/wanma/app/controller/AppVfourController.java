package com.wanma.app.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.AppPager;
import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.wanma.app.service.AppVfourService;
import com.wanma.model.ChargingOrderBrief;
import com.wanma.model.TblAppButtonList;
import com.wanma.model.TblBanner;
import com.wanma.model.TblMessageInfo;
import com.wanma.model.TblMessagePointRela;
import com.wanma.model.TblNewsInfo;

@Controller
@RequestMapping("/app/vfour")
public class AppVfourController {
	private static Log log = LogFactory.getLog(AppVfourController.class);
	@Autowired
	private AppVfourService appVfourService;

	/**
	 * APPV4.0第四迭代 banner页轮播图片获取接口 获取banner页轮播图片
	 * 参数：省份编码:blProvincecode,市区编码:blCitycode,区县编码:blCountycode
	 * 
	 * @return
	 */
	@RequestMapping("/banner")
	@ResponseBody
	public String banner(HttpServletRequest request)
			throws AppRequestErrorException {

		String blProvincecode = RequestParamUtil.getEncodeParam(request,
				"provincecode");
		String blCitycode = RequestParamUtil
				.getEncodeParam(request, "citycode");
		/*String blCountycode = RequestParamUtil.getEncodeParam(request,
				"countycode");*/

		TblBanner tblBanner = new TblBanner();
		tblBanner.setBlProvincecode(blProvincecode);
		tblBanner.setBlCitycode(blCitycode);
		//tblBanner.setBlCountycode(blCountycode);
		List<TblBanner> bannerList;
		try {
			bannerList = appVfourService.getBannerList(tblBanner);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		return new AccessSuccessResult(bannerList).toString();
	}

	/**
	 * APPV4.0第四迭代 正在充电图标 正在充电提醒 接口 参数：用户id:userId
	 * 
	 * @return
	 */
	@RequestMapping("/charging")
	@ResponseBody
	public String charging(HttpServletRequest request)
			throws AppRequestErrorException {
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if ("".equals(userId)) {

			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}

		List<ChargingOrderBrief> chargingList;
		try {

			ChargingOrderBrief chargingOrderBrief = new ChargingOrderBrief();
			chargingOrderBrief.setChorUserid(userId);

			chargingList = appVfourService.getCharging(chargingOrderBrief);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		return new AccessSuccessResult(chargingList).toString();
	}

	/**
	 * 滚动故障、新建消息
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping("/rollMessage")
	@ResponseBody
	public String rollMessage(HttpServletRequest request)
			throws AppRequestErrorException {

		String meiProvincecode = RequestParamUtil.getEncodeParam(request,
				"provincecode");
		String meiCitycode = RequestParamUtil.getEncodeParam(request,
				"citycode");
		/*String meiCountycode = RequestParamUtil.getEncodeParam(request,
				"countycode");*/

		TblMessageInfo tblMessageInfo = new TblMessageInfo();
		tblMessageInfo.setMeiProvincecode(meiProvincecode);
		tblMessageInfo.setMeiCitycode(meiCitycode);
		//tblMessageInfo.setMeiCountycode(meiCountycode);

		List<TblMessageInfo> messageList;
		try {
			messageList = appVfourService.getMessageList(tblMessageInfo);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		return new AccessSuccessResult(messageList).toString();
	}
	/**
	 * 滚动故障、新建充电点详情
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping("/messageInfo")
	@ResponseBody
	public String messageInfo(HttpServletRequest request)
			throws AppRequestErrorException {

		String pkMeiId = RequestParamUtil.getEncodeParam(request,
				"pkMeiId");
		if (StringUtils.isEmpty(pkMeiId)
				|| StringUtils.isEmpty(pkMeiId)) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}

		TblMessagePointRela tblMessagePointRela = new TblMessagePointRela();
		tblMessagePointRela.setPkMeiId(Integer.parseInt(pkMeiId));

		List<TblMessagePointRela> messageInfo;
		try {
			messageInfo = appVfourService.getMessageInfo(tblMessagePointRela);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		return new AccessSuccessResult(messageInfo).toString();
	}
	/**
	 * 功能按钮
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping("/button")
	@ResponseBody
	public String button(HttpServletRequest request)
			throws AppRequestErrorException {

		List<TblAppButtonList> buttonList;
		try {
			buttonList = appVfourService.getButtonList();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		return new AccessSuccessResult(buttonList).toString();
	}

	/**
	 * 资讯
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping("/information")
	@ResponseBody
	public String information(@RequestParam Map<String, Object> params,
			AppPager pager) {
		if (StringUtils.isEmpty(pager.getPageNum())
				|| StringUtils.isEmpty(pager.getPageNumber())) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		params.put("pagerNumber", pager.getPageNumber());
		params.put("pagerNum", pager.getPageNum());

		List<TblNewsInfo> infoList;
		try {
			infoList = appVfourService.getInfoList(params);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		return new AccessSuccessResult(infoList).toString();
	}

}
