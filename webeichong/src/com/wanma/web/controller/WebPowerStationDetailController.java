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
import org.springframework.web.servlet.ModelAndView;

import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.common.SessionMgr;
import com.wanma.model.PowerStationDetail;
import com.wanma.model.PowerStationElictric;
import com.wanma.model.TblPowerstation;
import com.wanma.model.TblUser;
import com.wanma.model.Userinfo;
import com.wanma.web.service.WebPowerStationDetailService;
import com.wanma.web.service.impl.WebPowerStationCommentServiceImpl;
import com.wanma.web.service.impl.WebPowerStationStarServiceImpl;

/**
 * 电站详情
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-11 下午04:25:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/web/powerstation")
public class WebPowerStationDetailController {
	private static Log log = LogFactory.getLog(WebPowerStationDetailController.class);

	@Autowired
	private WebPowerStationDetailService powerStationDetailService;
	@Autowired
	private WebPowerStationStarServiceImpl pscService;
	@Autowired
	private WebPowerStationCommentServiceImpl commentService;

	/**
	 * 获取电站详情
	 */
	@RequestMapping("/detail")
	public ModelAndView getPowerStationDetail(HttpServletRequest request) {
		ModelMap map = new ModelMap();
		// 电桩Id
		String powerStationId = RequestParamUtil.getEncodeParam(request, "eid");
		// 用户id
		String pkUserinfo = RequestParamUtil.getEncodeParam(request, "uid");
		PowerStationDetail powerStationDetail = new PowerStationDetail();
//		int score=0;
		try {
			TblPowerstation tblPowerstation = new TblPowerstation();
			if (StringUtil.isNotEmpty(pkUserinfo)) {
				tblPowerstation.setPkUserinfo(Integer.parseInt(pkUserinfo));
			}else if(SessionMgr.getWebUser(request)!=null){
				int userId=SessionMgr.getUserId(request).intValue();
				tblPowerstation.setPkUserinfo(userId);
			}
			if (StringUtil.isNotEmpty(powerStationId)) {
				tblPowerstation.setPkPowerstation(Integer.parseInt(powerStationId));
				powerStationDetail = powerStationDetailService.getPowerStationDetail(tblPowerstation);
			}
			TblUser user=SessionMgr.getWebUser(request);
			if(user!=null){
				Map<String, Object> params=new HashMap<String, Object>();
				params.put("psId", powerStationId);
				params.put("uId", user.getUserId());	
//				score= pscService.getPsCommentStar(params);
			}
			
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电站详情数据失败", e);
		}
		map.put("station", powerStationDetail);
		Map<String, Integer> countMap = powerStationDetailService.getPileCount(powerStationDetail.getPowerElectricpileList());
		map.put("linkingCount", countMap.get("linkingCount"));
		map.put("kongxianCount", countMap.get("kongxianCount"));
		String commentId = RequestParamUtil.getEncodeParam(request, "commentId");
		if(commentId != null){
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("commentId", commentId);
			params.put("powerStationId", powerStationId);
			map.put("commentId", commentId);
			map.put("linkCommentsRowNum", commentService.getPsCommentsRowNum(params));
		}
		PowerStationElictric elictric = powerStationDetail.getPowerElectricpileList().get(0);
		powerStationDetailService.makeFengzhiPrice(map, elictric.getElictricPicId());
		TblUser userInfo = SessionMgr.getWebUser(request);
		if(userInfo != null){
			map.put("myImage", userInfo.getUserImage());
			map.put("userName", userInfo.getNormName());
		}
		return new ModelAndView("frontend/web2/electric-station", map);
	}
}