package com.wanma.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.AppPager;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.service.impl.AppUserMessageServiceImpl;

/**
 * 救援信息
 * 
 * @author hFei
 *
 */
@Controller
@RequestMapping("/app/usermessge")
public class AppUserMessageController {
	private static Log log = LogFactory.getLog(AppUserMessageController.class);

	/**
	 * 查询给定用户的所有收到的信息
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/mylist")
	@ResponseBody
	public String getRescueList(int userId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try {
			list = appUserMessageService.getUserMessageList(userId);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取用户消息错误", e);
			return new AccessErrorResult(2004, "error.msg.invalid.parameter")
					.toString();
		}
		//return new AccessSuccessResult(list).toString();
		return AccessSuccessResult.nAccessSuccessResult(list).toJSONString();
	}

	/**
	 * 查询给定用户的所有收到的信息
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/myMessageTitlelist")
	@ResponseBody
	public String getMessageTitleList(@RequestParam Map<String, Object> params,
			AppPager pager) {
		// 分页参数
		params.put("pageNum", pager.getPageNum());
		params.put("pageNumber", pager.getPageNumber());

		List<Map<String, Object>> list = null;

		try {
			list = appUserMessageService.getUserMessageTitleList(params);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取用户消息标题列表错误", e);
			return new AccessErrorResult(2004, "error.msg.invalid.parameter")
					.toString();
		}
		return new AccessSuccessResult(list).toString();
	}

	/**
	 * 查询选定消息详情
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/myMessageContent")
	@ResponseBody
	public String myMessageContent(int messageId) {

		Map<String, Object> contentMap = new HashMap<String, Object>();

		try {
			contentMap = appUserMessageService.myMessageContent(messageId);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取用户消息详情错误", e);
			return new AccessErrorResult(2004, "error.msg.invalid.parameter")
					.toString();
		}
		return new AccessSuccessResult(contentMap).toString();
	}

	@Autowired
	AppUserMessageServiceImpl appUserMessageService;
}
