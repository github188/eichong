package com.wanma.web.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.ElectricPileComment;
import com.wanma.model.PowerStationComment;
import com.wanma.web.service.impl.RedisService;
import com.wanma.web.service.impl.WebElecPileCommentServiceImpl;
import com.wanma.web.support.common.CommentPageResponse;
import com.wanma.web.support.common.PageResponse;
import com.wanma.web.support.utils.SensitiveWordUtil;

@Controller
@RequestMapping("/web/epComment")
public class WebElecPileCommentController extends BaseController {
	private static Log log = LogFactory
			.getLog(WebElecPileCommentController.class);

	@Autowired
	private WebElecPileCommentServiceImpl epcService;
	@Autowired
	private RedisService redisService;

	/**
	 * @Description: 新增电桩评论
	 * @param params
	 *            epId 电桩id，uId 用户id，uName 用户名称，epContent 评论内容
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/insertEpCommnet")
	@ResponseBody
	public String insertEpCommnet(@ModelAttribute("epComment") ElectricPileComment epComment,
			HttpServletRequest request) throws UnsupportedEncodingException {

//		String epId = RequestParamUtil.getEncodeParam(request, "eId");
		String content = epComment.getEpc_Content();
		List sensitiveWordList=redisService.listGetAll(WanmaConstants.SENSITIVE_WORD_LIST);
		if (SensitiveWordUtil.getSensitiveWord(content,sensitiveWordList).size() >= 2) {
			// 返回信息输入有敏感词信息
			return new AccessErrorResult("error.msg.invalid.sensitive")
					.toString();
		};
		
		if (SensitiveWordUtil.getSensitiveWord(content,sensitiveWordList).size() == 1) {
			content = SensitiveWordUtil.replaceSensitiveWord(content, "*",sensitiveWordList);
		};
		epComment.setEpc_Createdate(new Date());

		try {
			epcService.insertEpCommnet(epComment);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("新增电桩评论错误", e);
			System.out.println(e);
			// 返回登录信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult(content+"|||"+epComment.getPk_EpcComment()+"|||"+epComment.getEpc_EpId()).toString();
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
			HttpServletRequest request) {
		// 分页参数
		Integer begin = null, pageSize = null;
		// 获取分页参数
		if (!StringUtils.isEmpty((String) params.get("pageSize"))) {
			pageSize = Integer.valueOf((String) params.get("pageSize"));
		}
		if (!StringUtils.isEmpty((String) params.get("pageNum"))) {
			begin = Integer.valueOf((String) params.get("pageNum"));
		}
		PageResponse<List<HashMap<String, Object>>> pager = new PageResponse<List<HashMap<String, Object>>>(
				begin, pageSize);
		CommentPageResponse<List<HashMap<String, Object>>> commentPage = new CommentPageResponse<List<HashMap<String, Object>>>();
		params.put("pageNum", pager.getCount());
		params.put("pageNumber", pager.getBegin());
		List<HashMap<String, Object>> maps = null;
		List<HashMap<String, Object>> replyMaps = null;
		String electricPileId = RequestParamUtil.getEncodeParam(request,
				"prCoProductId");
		params.put("prCoProductId", electricPileId);
		try {
			// proCommentList = productCommentService.findProComments(params);
			maps = epcService.getEpCommentsPageList(params);
			pager.setCountData(epcService.countEpCommentsByPowerId(params));
			pager.setDate(maps);
			commentPage.setPager(pager);
			replyMaps = epcService.getEpReplyCommentsList(params.get(
					"prCoProductId").toString());
			commentPage.setReplyData(replyMaps);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取产品信息错误", e);
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}
		MessageManager messageManager = MessageManager.getMessageManager();
		// 正式文件存放路径
		String relFilePath = messageManager
				.getSystemProperties(CommonConsts.PRO_KEY_STORAGE_REL_PATH);
		// relative path
		String relativePath = messageManager
				.getSystemProperties(CommonConsts.MULTI_TYPE_USER);
		// absolute path
		String fullPath = relFilePath + "/" + relativePath;
		pager.setMessage(fullPath);
		// 返回处理成功信息
		return commentPage.toString();
	}

	/**
	 * @Description: 保存点赞信息
	 * @param params
	 *            分页参数 产品id
	 * @return
	 */
	@RequestMapping("/praise")
	@ResponseBody
	public String praise(@RequestParam Map<String, Object> params) {
		try {
			if (epcService.checkIsHavePraise(params))
				return new AccessErrorResult("error.msg.invalid.parameter")
						.toString();
			epcService.insertEpPraiseCommnet(params);
			epcService.updateEpPraiseCount(params);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("保存点赞信息错误", e);
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}

	/**
	 * @Description: 保存回复信息
	 * @param params
	 *            分页参数 产品id
	 * @return
	 */
	@RequestMapping("/insertReply")
	@ResponseBody
	public String insertReply(@RequestParam Map<String, Object> params,
			HttpServletRequest request) {
		String content = "";
		try {
		content = params.get("epContent").toString();
		List sensitiveWordList=redisService.listGetAll(WanmaConstants.SENSITIVE_WORD_LIST);
		if (SensitiveWordUtil.getSensitiveWord(content,sensitiveWordList).size() >= 2) {
			// 返回信息输入有敏感词信息
			return new AccessErrorResult("error.msg.invalid.sensitive")
					.toString();
		};
		
		if (SensitiveWordUtil.getSensitiveWord(content,sensitiveWordList).size() == 1) {
			content = SensitiveWordUtil.replaceSensitiveWord(content, "*",sensitiveWordList);
		};
				params.put("epContent", content);
			params.put("createdate", new Date());
			epcService.insertEpReply(params);
			epcService.updateEpReplyCount(params);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("保存回复信息错误", e);
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult(content).toString();
	}

}