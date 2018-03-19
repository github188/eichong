package com.wanma.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.model.ElectricPileDetail;
import com.wanma.model.TblElectricpile;
import com.wanma.web.dao.WebElecPileStarMapper;
import com.wanma.web.service.WebElectricPileDetaillService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.ResultResponse;

/**
 * 电桩详情
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-11 下午04:25:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/web/electricPileDetail")
public class WebElectricPileDetailController {
	private static Log log = LogFactory.getLog(WebElectricPileDetailController.class);

	@Autowired
	private WebElectricPileDetaillService electricPileDetaillService;

	/**
	 * 获取电桩详情
	 */
	@RequestMapping("/getElectricPileDetail")
	@ResponseBody
	public String getElectricPileDetail(HttpServletRequest request) {

		// 电桩Id
		String electricPileId = RequestParamUtil.getEncodeParam(request, "electricPileId");
		// 用户id
		String pkUserinfo = RequestParamUtil.getEncodeParam(request, "pkUserinfo");

		ElectricPileDetail electricPileDetail = new ElectricPileDetail();
		try {
			TblElectricpile tblElectricpile = new TblElectricpile();

			if (StringUtil.isEmpty(electricPileId)) {
				// 未输入 电桩ID
				return new FailedResponse("error.msg.invalid.parameter").toString();
			}
			if (StringUtil.isNotEmpty(pkUserinfo)) {
				tblElectricpile.setPkUserinfo(Integer.parseInt(pkUserinfo));
			}
			tblElectricpile.setPkElectricpile(Integer.parseInt(electricPileId));
			electricPileDetail = electricPileDetaillService.getElectricPileDetail(tblElectricpile);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电桩详情数据失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		// 返回处理成功信息
		return new ResultResponse<ElectricPileDetail>(electricPileDetail).toString();
	}
}