package com.wanma.controller;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.service.impl.CmsUserMessageServiceImpl;

/**
 * 个人信息
 * @author hFei
 *
 */
@Controller
@RequestMapping("/admin/usermessge")
public class CmsUserMessageController {
	private static Log log = LogFactory.getLog(CmsUserMessageController.class);
	
	/**
	 * 添加消息
	 * @param params
	 * 			title 标题
	 * 			content 内容
	 * 			to_user_id 接收人id
	 * 			from_user_id 发送人id
	 * 			from_user_name 发送人姓名
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String addUserMessage(@RequestParam Map<String, String> params){
		
		try{
			cmsUserMessageService.addUserMessage(params);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("添加消息错误", e);
			return new AccessErrorResult(1000,"error.msg.invalid.parameter").toString();
		}
		return new AccessSuccessResult().toString();
	}
	
	@Autowired
	CmsUserMessageServiceImpl cmsUserMessageService;
}
