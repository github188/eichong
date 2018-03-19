package com.wanma.app.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.app.service.UsercollectService;
import com.wanma.model.TblUsercollect;


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
@RequestMapping("/app/usercollect")
public class UsercollectController {
    private static Logger log = Logger.getLogger(UsercollectController.class);
	
    @Autowired
    private UsercollectService usercollectService;

    
    /**
     * 电桩/电站收藏
     */
    @RequestMapping("/userFavorites")
	@ResponseBody
	public String  userFavorites(HttpServletRequest request) throws AppRequestErrorException {
    	
    	//用户Id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		String favoriteType = RequestParamUtil.getEncodeParam(request, "favoriteType");//类型（1电桩，2电站）
		String favoriteTypeId = RequestParamUtil.getEncodeParam(request, "favoriteTypeId");//电桩ID/电桩ID
		//String isCollect = RequestParamUtil.getEncodeParam(request, "isCollect");//0未收藏过1收藏过
    	try {
    		TblUsercollect tblUsercollecty=new TblUsercollect();
    		
			if (StringUtil.isEmpty(userId)) {
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			if (StringUtil.isEmpty(favoriteType)) {
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			if (StringUtil.isEmpty(favoriteTypeId)) {
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			//if(!"1".equals(isCollect)){
				tblUsercollecty.setUscoObjectid(Integer.parseInt(favoriteTypeId));
				tblUsercollecty.setUscoType(Integer.parseInt(favoriteType));
				tblUsercollecty.setUscoUserid(Integer.parseInt(userId));
				tblUsercollecty.setUscoAddtime(new Date());
				usercollectService.userFavorites(tblUsercollecty);
			//}
		} catch (Exception e) {
			log.error(e.getMessage());
			// 返回登录信息错误信息
			return new AccessErrorResult(2002,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}
   
    /**
     * 电桩/电站收藏列表
     */
    @RequestMapping("/getFavoritesList")
	@ResponseBody
	public String  getFavoritesList(HttpServletRequest request) throws AppRequestErrorException {
    	
    	//用户Id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		
    	try {
    		TblUsercollect tblUsercollecty=new TblUsercollect();
    		
			if (StringUtil.isEmpty(userId)) {
				// 未输入 电桩ID
				return new AccessErrorResult(1050,"error.msg.invalid.parameter").toString();
			}
			
			tblUsercollecty.setUscoUserid(Integer.parseInt(userId));
			usercollectService.userFavorites(tblUsercollecty);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电桩/电站收藏列表失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2002,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}

    /**
     * 删除收藏列表
     */
    @RequestMapping("/removeFavoritesList")
	@ResponseBody
	public String  removeFavoritesList(HttpServletRequest request) throws AppRequestErrorException {
    	
    	//收藏Id
		String userCollectId = RequestParamUtil.getEncodeParam(request, "userCollectId");
		
    	try {
    		
			if (StringUtil.isEmpty(userCollectId)) {
				// 未输入 电桩ID
				return new AccessErrorResult(1050,"error.msg.invalid.parameter").toString();
			}
			usercollectService.removeFavoritesList(userCollectId);
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
}