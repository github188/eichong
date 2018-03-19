package com.wanma.app.controller;

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
import com.wanma.app.service.impl.AppPowerStationCommentServiceImpl;

@Controller
@RequestMapping("/app/psComment")
public class AppPowerStationCommentController extends BaseController {
	private static Log log = LogFactory
			.getLog(AppPowerStationCommentController.class);

	@Autowired
	private AppPowerStationCommentServiceImpl pscService;

	/**
	 * @Description: 新增产品评论
	 * @param params
	 *            psId 电站id，uId 用户id，uName 用户名称，psContent 评论内容, pcId 上级评论id（对站的评论为0，对评论的回复为被回复评论id）
	 * @return
	 */
	@RequestMapping("/insertPsCommnet")
	@ResponseBody
	public String insertProCommnet(@RequestParam Map<String, Object> param) {
		if(StringUtils.isEmpty(param.get("pcId")) || StringUtils.isEmpty(param.get("uId"))
				|| StringUtils.isEmpty(param.get("psContent"))){
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		String message_key = null;
		try {
			param.put("psContent", param.get("psContent").toString().replaceAll("<", "《"));
			message_key = pscService.insertPsCommnetWithPsCheck(param);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("新增电站评论错误", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2002, "error.msg.invalid.parameter")
					.toString();
		}
		if (!"".equals(message_key))
			// 敏感词校验不通过，拒绝添加评论
			return new AccessErrorResult(1001, message_key).toString();
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}

	/**
	 * @param params
	 *            产品id
	 * @return
	 */
	@RequestMapping("/findPsComments")
	@ResponseBody
	public String findProComments(@RequestParam Map<String, Object> params,
			AppPager pager) {
		// 分页参数
		params.put("pageNum", pager.getPageNum());
		params.put("pageNumber", pager.getPageNumber());

		List<Map<String, Object>> proCommentList = null;

		try {
			// proCommentList = productCommentService.findProComments(params);
			proCommentList = pscService.getPsCommentsPageList(params);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取产品信息错误", e);
			return new AccessErrorResult(2004, "error.msg.invalid.parameter")
					.toString();
		}

		// 返回处理成功信息
		return new AccessSuccessResult(proCommentList).toString();
	}

}