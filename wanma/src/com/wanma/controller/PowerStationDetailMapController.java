package com.wanma.controller;

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
import com.wanma.model.PowerStationDetail;
import com.wanma.model.PowerStationElictric;
import com.wanma.model.TblPowerstation;
import com.wanma.service.PowerStationDetailMapService;

/**
 * 充电点详情
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-11 下午04:25:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/powerstation")
public class PowerStationDetailMapController {
	private static Log log = LogFactory.getLog(PowerStationDetailMapController.class);

	@Autowired
	private PowerStationDetailMapService powerStationDetailService;
	/*@Autowired
	private WebPowerStationStarServiceImpl pscService;
	@Autowired
	private WebPowerStationCommentServiceImpl commentService;*/

	/**
	 * 获取充电点详情
	 */
	@RequestMapping("/detailMap")
	public ModelAndView getPowerStationDetail(HttpServletRequest request) {
		ModelMap map = new ModelMap();
		// 电桩Id
		String powerStationId = RequestParamUtil.getEncodeParam(request, "eid");
		/*// 用户id
		String pkUserinfo = RequestParamUtil.getEncodeParam(request, "uid");*/
		PowerStationDetail powerStationDetail = new PowerStationDetail();
//		int score=0;
		try {
			TblPowerstation tblPowerstation = new TblPowerstation();
			/*if (StringUtil.isNotEmpty(pkUserinfo)) {
				tblPowerstation.setPkUserinfo(Integer.parseInt(pkUserinfo));
			}else if(SessionMgr.getWebUser(request)!=null){
				int userId=Integer.parseInt(SessionMgr.getWebUser(request).getPkUserId());
				tblPowerstation.setPkUserinfo(userId);
			}*/
			if (StringUtil.isNotEmpty(powerStationId)) {
				tblPowerstation.setPkPowerstation(Integer.parseInt(powerStationId));
				powerStationDetail = powerStationDetailService.getPowerStationDetail(tblPowerstation);
			}
			/*Userinfo user=SessionMgr.getWebUser(request);*/
			/*if(user!=null){
				Map<String, Object> params=new HashMap<String, Object>();
				params.put("psId", powerStationId);
				params.put("uId", user.getPkUserId());
//				score= pscService.getPsCommentStar(params);
			}*/
			
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取充电点详情数据失败", e);
		}
		map.put("station", powerStationDetail);
		Map<String, Integer> countMap = powerStationDetailService.getPileCount(powerStationDetail.getPowerElectricpileList());
		map.put("linkingCount", countMap.get("linkingCount"));
		map.put("kongxianCount", countMap.get("kongxianCount"));
		/*String commentId = RequestParamUtil.getEncodeParam(request, "commentId");*/
		/*if(commentId != null){
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("commentId", commentId);
			params.put("powerStationId", powerStationId);
			map.put("commentId", commentId);
			map.put("linkCommentsRowNum", commentService.getPsCommentsRowNum(params));
		}*/
		PowerStationElictric elictric = powerStationDetail.getPowerElectricpileList().get(0);
		powerStationDetailService.makeFengzhiPrice(map, elictric.getElictricPicId());
		/*Userinfo userInfo = SessionMgr.getWebUser(request);*/
		/*if(userInfo != null){
			map.put("myImage", userInfo.getUserImage());
			map.put("userName", userInfo.getUserRealName());
		}*/
		return new ModelAndView("backstage/electricSearchMode/station", map);
	}
}