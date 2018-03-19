package com.wanma.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.model.TblFavorite;
import com.wanma.web.service.WebFavoriteService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.PageResponse;
import com.wanma.web.support.common.SuccessResponse;

/**
 * @Description:收藏夹控制器
 * @author songjf
 * @createTime：2015-3-15 下午07:59:29
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/web/favorite")
public class WebFavoriteController {

	private static Log log = LogFactory.getLog(WebUserController.class);

	/** 收藏夹业务处理对象 */
	@Autowired
	private WebFavoriteService appFavoriteService;

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
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		log.info("******************新增收藏-end************************");
		return new SuccessResponse().toString();
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
		// 收藏Id
		String userCollectId = RequestParamUtil.getEncodeParam(request, "userCollectId");
		try {
			if (StringUtil.isEmpty(userCollectId)) {
				// 未输入收藏Id
				return new FailedResponse("error.msg.invalid.parameter").toString();
			}
			appFavoriteService.removeFavoritesList(userCollectId);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("删除收藏列表失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		// 返回处理成功信息
		return new SuccessResponse().toString();
	}

	/**
	 * @Title: insertFavorite
	 * @Description: 获取收藏列表
	 * @param tblFavorite
	 * @return
	 */
	@RequestMapping("/getFavoriteList")
	@ResponseBody
	public String getFavoriteList(@RequestParam Map<String, Object> params) {
		Integer begin = null, pageSize = null;
        //获取分页参数
        if (!StringUtils.isEmpty((String) params.get("pageNum"))) {
        	pageSize = Integer.valueOf((String) params.get("pageNum"));
        }
        if (!StringUtils.isEmpty((String) params.get("pageNumber"))) {
        	begin = Integer.valueOf((String) params.get("pageNumber"));
        }
        PageResponse<List<Map<String, Object>>> pager = new PageResponse<List<Map<String, Object>>>(begin, pageSize);
		params.put("pageNum", pager.getCount());
		params.put("pageNumber", pager.getBegin());
		try {
			if (StringUtil.isEmpty((String) params.get("userId"))) {
				// 未输入用户Id
				return new FailedResponse("error.msg.invalid.parameter").toString();
			}
			List<Map<String, Object>> favoritesList = appFavoriteService.getFavoriteList(params);
            pager.setCountData(appFavoriteService.countFavorite(params));
            pager.setDate(favoritesList);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error(" 获取收藏列表失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		// 返回处理成功信息
		return pager.toString();
	}
}
