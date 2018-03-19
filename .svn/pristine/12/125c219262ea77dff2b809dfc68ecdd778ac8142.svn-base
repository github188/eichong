package com.wanma.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.model.TblUsercollect;
import com.wanma.web.service.WebUsercollectService;


/**
 *  电桩/电站收藏
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-11 下午04:25:53 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
@Controller
@RequestMapping("/web/usercollect")
public class WebUsercollectController {
    private static Log log = LogFactory.getLog(WebUsercollectController.class);
	
    @Autowired
    private WebUsercollectService usercollectService;

    
    /**
     * 电桩/电站收藏
     */
    @RequestMapping("/userFavorites")
	@ResponseBody
	public String userFavorites(HttpServletRequest request) {
    	
    	//用户Id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		String favoriteType = RequestParamUtil.getEncodeParam(request, "favoriteType");//类型（1电桩，2电站）
		String favoriteTypeId = RequestParamUtil.getEncodeParam(request, "favoriteTypeId");//电桩ID/电桩ID
		TblUsercollect tblUsercollect=new TblUsercollect();
    	try {
			if (StringUtil.isEmpty(userId)) {
				// 未输入 电桩ID
				return new AccessErrorResult("error.msg.invalid.parameter").toString();
			}
			if (StringUtil.isEmpty(favoriteType)) {
				// 未输入 电桩ID
				return new AccessErrorResult("error.msg.invalid.parameter").toString();
			}
			if (StringUtil.isEmpty(favoriteTypeId)) {
				// 未输入 电桩ID
				return new AccessErrorResult("error.msg.invalid.parameter").toString();
			}
			tblUsercollect.setUscoObjectid(Integer.parseInt(favoriteTypeId));
			tblUsercollect.setUscoType(Integer.parseInt(favoriteType));
			tblUsercollect.setUscoUserid(Integer.parseInt(userId));
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("电桩/电站收藏失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter").toString();
		}
		// 返回处理成功信息
		return usercollectService.userFavorites(tblUsercollect).toString();
	}
   
    /**
     * 电桩/电站收藏列表
     */
    @RequestMapping("/getFavoritesList")
	@ResponseBody
	public String getFavoritesList(HttpServletRequest request){
    	//用户Id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
    	try {
    		TblUsercollect tblUsercollecty=new TblUsercollect();
			if (StringUtil.isEmpty(userId)) {
				// 未输入 电桩ID
				return new AccessErrorResult("error.msg.invalid.parameter").toString();
			}
			tblUsercollecty.setUscoUserid(Integer.parseInt(userId));
			usercollectService.userFavorites(tblUsercollecty);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电桩/电站收藏列表失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter").toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}

    /**
     * 删除收藏列表
     */
    @RequestMapping("/removeFavoritesList")
	@ResponseBody
	public String  removeFavoritesList(HttpServletRequest request) {
    	//收藏Id
		String userCollectId = RequestParamUtil.getEncodeParam(request, "userCollectId");
    	try {
			if (StringUtil.isEmpty(userCollectId)) {
				// 未输入 电桩ID
				return new AccessErrorResult("error.msg.invalid.parameter").toString();
			}
			usercollectService.removeFavoritesList(userCollectId);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("删除收藏列表失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter").toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}
}