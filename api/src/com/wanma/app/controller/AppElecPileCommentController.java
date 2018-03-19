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
import com.wanma.app.service.AppProductCommentService;
import com.wanma.app.service.impl.AppElecPileCommentServiceImpl;
import com.wanma.app.service.impl.AppPowerStationCommentServiceImpl;
import com.wanma.model.TblProductcomment;

@Controller
@RequestMapping("/app/epComment")
public class AppElecPileCommentController extends BaseController {
	private static Log log = LogFactory
			.getLog(AppElecPileCommentController.class);

	@Autowired
	private AppElecPileCommentServiceImpl epcService;

	/**
	 * @Description: 新增电桩评论
	 * @param params
	 *            epId 电桩id，uId 用户id，uName 用户名称，epContent 评论内容，pcId 上级评论id（对站的评论为0，对评论的回复为被回复评论id）
	 * @return
	 */
	@RequestMapping("/insertEpCommnet")
	@ResponseBody
	public String insertEpCommnet(@RequestParam Map<String, Object> param) {
		if(StringUtils.isEmpty(param.get("epId")) || StringUtils.isEmpty(param.get("epContent"))
				|| StringUtils.isEmpty(param.get("pcId")) || StringUtils.isEmpty(param.get("type"))){
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		String message_key = null;
		try {
			param.put("epContent", param.get("epContent").toString().replaceAll("<", "《"));
			message_key = epcService.insertEpCommnetWithPsCheck(param);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("新增电桩评论错误", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		if (!"".equals(message_key))
			// 敏感词校验不通过，拒绝添加评论
			return new AccessErrorResult(1001, message_key).toString();
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}

	/**
	 * @Description: 获取产品信息
	 * @param params
	 *            pageNum、pageNumber 分页参数， prCoProductId 电桩id
	 * @return
	 */
	@RequestMapping("/findEpComments")
	@ResponseBody
	public String findEpComments(@RequestParam Map<String, Object> params,
			AppPager pager) {
		// 分页参数
		params.put("pageNum", pager.getPageNum());
		params.put("pageNumber", pager.getPageNumber());

		List<Map<String, Object>> proCommentList = null;

		try {
			// proCommentList = productCommentService.findProComments(params);
			proCommentList = (List<Map<String, Object>>) epcService.getEpCommentsPageList(params);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取产品信息错误", e);
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}

		// 返回处理成功信息
		return new AccessSuccessResult(proCommentList).toString();
	}

}