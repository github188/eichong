package com.wanma.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.app.service.AppFavoriteService;
import com.wanma.model.FavoritesList;
import com.wanma.model.TblFavorite;
import com.wanma.model.TblUsercollect;

/**
 * @Description:收藏夹控制器
 * @author songjf
 * @createTime：2015-3-15 下午07:59:29
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/app/favorite")
public class AppFavoriteController {

	private static Logger log = Logger.getLogger(AppUserController.class);

	/** 收藏夹业务处理对象 */
	@Autowired
	private AppFavoriteService appFavoriteService;

	/**
	 * @Title: insertFavorite
	 * @Description: 新增收藏
	 * @param tblFavorite
	 * @return
	 */
	@RequestMapping("/insertFavorite")
	@ResponseBody
	public String insertFavorite(TblFavorite tblFavorite) {
		log.info("******************新增收藏-begin************************");

		try {
			appFavoriteService.insertFavorite(tblFavorite);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("新增收藏错误", e);
			return new AccessErrorResult(1000,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************新增收藏-end************************");
		return new AccessSuccessResult().toString();
	}
	/**
	 * @Title: insertFavorite
	 * @Description: 删除收藏
	 * @param tblFavorite
	 * @return
	 */
	@RequestMapping("/removeFavorite")
	@ResponseBody
	public String removeFavorite(HttpServletRequest request) {
    	
    	//收藏Id
		String userCollectId = RequestParamUtil.getEncodeParam(request, "userCollectId");
		//收藏类型
		//String collecType = RequestParamUtil.getEncodeParam(request, "favoriteType");//收藏类型 3-商城收藏  1-电桩 2-电站
		
    	try {
    		
			if (StringUtil.isEmpty(userCollectId)) {
				// 未输入 电桩ID
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			/*if (StringUtil.isEmpty(collecType)) {
				// 未输入 电桩ID
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}*/
			appFavoriteService.removeFavoritesList(userCollectId);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("删除收藏列表失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2003,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}
	/**
	 * @Title: insertFavorite
	 * @Description: 获取收藏列表
	 * @param tblFavorite
	 * @return
	 */
	@RequestMapping("/getFavoriteList")
	@ResponseBody
	public String getFavoriteList(HttpServletRequest request) {
    	
    	//用户Id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		 List<FavoritesList> favoritesList=new ArrayList<FavoritesList>();
    	try {
    		
			if (StringUtil.isEmpty(userId)) {
				// 未输入 电桩ID
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			TblFavorite tblFavorite =new TblFavorite();
			tblFavorite.setFavoUserid(Integer.parseInt(userId));
			favoritesList=appFavoriteService.getFavoriteList(tblFavorite);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error(" 获取收藏列表失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult(favoritesList).toString();
	}
	
	/**
	 * @Title: insertFavorite
	 * @Description: 获取收藏列表
	 * @param tblFavorite
	 * @return
	 */
	@RequestMapping("/getFavoriteListN")
	@ResponseBody
	public String getFavoriteListN(@RequestParam Map<String, String> params) {
    	
    	//用户Id
		/*String userId = RequestParamUtil.getEncodeParam(request, "userId");
		String lat = RequestParamUtil.getEncodeParam(request, "lat");
		String lng = RequestParamUtil.getEncodeParam(request, "lng");*/
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    	try {
    		
			if (StringUtils.isEmpty(params.get("userId")) || StringUtils.isEmpty(params.get("lat")) || StringUtils.isEmpty(params.get("lng"))) {
				// 未输入 电桩ID
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			TblUsercollect c = new TblUsercollect();
			c.setUscoUserid(Integer.parseInt(params.get("userId")));
			c.setLat(Float.parseFloat(params.get("lat")));
			c.setLng(Float.parseFloat(params.get("lng")));
			list = appFavoriteService.getFavoriteListN(c);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error(" 获取收藏列表失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult(list).toString();
	}
}
