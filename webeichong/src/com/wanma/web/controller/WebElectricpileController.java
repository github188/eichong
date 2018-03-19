package com.wanma.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.common.SessionMgr;
import com.wanma.model.ElectricPileDetail;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblUser;
import com.wanma.model.Userinfo;
import com.wanma.web.service.WebElectricPileDetaillService;
import com.wanma.web.service.WebElectricpileService;
import com.wanma.web.service.WebPowerStationDetailService;
import com.wanma.web.service.impl.WebElecPileCommentServiceImpl;
import com.wanma.web.service.impl.WebElecPileStarServiceImpl;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.SuccessResponse;

@Controller
@RequestMapping("/web/electricpile")
public class WebElectricpileController {
	private static Log log = LogFactory.getLog(WebElectricpileController.class);

	@Autowired
	private WebElectricpileService electricpileService;
	@Autowired
	private WebElectricPileDetaillService electricPileDetaillService;
	@Autowired
	private WebElecPileStarServiceImpl elecPileStarService;
	@Autowired
	private WebPowerStationDetailService powerStationDetailService;
	@Autowired
	private WebElecPileCommentServiceImpl commentService;

	@RequestMapping("/insert")
	@ResponseBody
	public String insertElectricpile(TblElectricpile tblElectricpile) {
		log.info("******************新增电桩分享-begin************************");
		try {
			electricpileService.insertElectricpile(tblElectricpile);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("新增电桩分享错误", e);
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		log.info("******************新增电桩分享-end************************");
		return new SuccessResponse().toString();
	}

	/**
	 * 电桩模块
	 *
	 * @param path
	 * @return
	 */
	@RequestMapping("/detail")
	public ModelAndView electricPile(HttpServletRequest request) {
		// 电桩Id
		String electricPileId = RequestParamUtil.getEncodeParam(request, "eid");
		// 用户id
		String pkUserinfo = RequestParamUtil.getEncodeParam(request, "uid");
		int score = 0;
		ElectricPileDetail electricPileDetail = new ElectricPileDetail();
		try {
			TblElectricpile tblElectricpile = new TblElectricpile();
			if (StringUtil.isNotEmpty(electricPileId)) {
				if (StringUtil.isNotEmpty(pkUserinfo)) {
					tblElectricpile.setPkUserinfo(Integer.parseInt(pkUserinfo));
				} else if (SessionMgr.getWebUser(request) != null) {
					int userId = SessionMgr
							.getUserId(request).intValue();
					tblElectricpile.setPkUserinfo(userId);
				}
				tblElectricpile.setPkElectricpile(Integer
						.parseInt(electricPileId));
				electricPileDetail = electricPileDetaillService
						.getElectricPileDetail(tblElectricpile);
			}
			//桩体评分
			TblUser user = SessionMgr.getWebUser(request);
			if (user != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("epId", electricPileId);
				params.put("uId", user.getUserId());
				score = elecPileStarService.getEpCommentStar(params);
			}
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电桩详情数据失败", e);
		}
		ModelMap map = new ModelMap();
		map.put("pile", electricPileDetail);
		map.put("score", score);
		String commentId = RequestParamUtil.getEncodeParam(request, "commentId");
		if(commentId != null){
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("commentId", commentId);
			params.put("electricPileId", electricPileId);
			map.put("commentId", commentId);
			map.put("linkCommentsRowNum", commentService.getEpCommentsRowNum(params));
		}
		
		map.put("kongxianCount", elecPileStarService.getKongxianCount(electricPileDetail));
		powerStationDetailService.makeFengzhiPrice(map, electricPileDetail.getPkElectricPile());
		TblUser userInfo = SessionMgr.getWebUser(request);
		if(userInfo != null){
			map.put("myImage", userInfo.getUserImage());
			map.put("userName", userInfo.getNormName());
		}
		return new ModelAndView("frontend/web2/electric-pile", map);
	}
}
