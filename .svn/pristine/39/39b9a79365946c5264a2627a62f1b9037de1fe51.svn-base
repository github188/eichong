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

import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.PowerStationComment;
import com.wanma.web.service.impl.RedisService;
import com.wanma.web.service.impl.WebPowerStationCommentServiceImpl;
import com.wanma.web.support.common.CommentPageResponse;
import com.wanma.web.support.common.PageResponse;
import com.wanma.web.support.utils.SensitiveWordUtil;

@Controller
@RequestMapping("/web/psComment")
public class WebPowerStationCommentController extends BaseController {
	private static Log log = LogFactory
			.getLog(WebPowerStationCommentController.class);

	@Autowired
	private WebPowerStationCommentServiceImpl pscService;
	@Autowired
	private RedisService redisService;

	/**
	 * @Description: 新增产品评论
	 * @param params
	 *            psId 电站id，uId 用户id，uName 用户名称，psContent 评论内容
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/insertPsCommnet")
	@ResponseBody
	public String insertProCommnet(@ModelAttribute("psComment") PowerStationComment psComment,
			HttpServletRequest request) throws UnsupportedEncodingException {

//		String powerStationId = RequestParamUtil
//				.getEncodeParam(request, "psId");
		// String psContent = RequestParamUtil.getEncodeParam(request,
		// "psContent");
		String content = psComment.getPsc_Content();
		// epCopsContentntent=sensitivewordFilter.replaceSensitiveWord(psContent,
		// 1, "*");
		List sensitiveWordList=redisService.listGetAll(WanmaConstants.SENSITIVE_WORD_LIST);
		if (SensitiveWordUtil.getSensitiveWord(content,sensitiveWordList).size() >= 2) {
			// 返回信息输入有敏感词信息
			return new AccessErrorResult("error.msg.invalid.sensitive")
					.toString();
		};
		
		if (SensitiveWordUtil.getSensitiveWord(content,sensitiveWordList).size() == 1) {
			content = SensitiveWordUtil.replaceSensitiveWord(content, "*",sensitiveWordList);
		};
//		param.put("psId", powerStationId);
//		param.put("psContent", content);
//		param.put("uId", SessionMgr.getWebUser(request).getPkUserId());
//		param.put("uName", SessionMgr.getWebUser(request).getUserName());
//		Date now = new Date();
//		SimpleDateFormat dateFormat = new SimpleDateFormat(
//				"yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
//		String date = dateFormat.format(now);
//		param.put("createdate", date);
		psComment.setPsc_Createdate(new Date());
		try {
			pscService.insertPsCommnet(psComment);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("新增电站评论错误", e);
			System.out.println(e);
			// 返回登录信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult(content+"|||"+psComment.getPk_PsComment()+"|||"+psComment.getPsc_PsId()).toString();
	}

	/**
	 * @Description: 获取产品信息
	 * @param params
	 *            分页参数 产品id
	 * @return
	 */
	@RequestMapping("/findPsComments")
	@ResponseBody
	public String findProComments(@RequestParam Map<String, Object> params,
			HttpServletRequest request) {
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
		String powerStationId = RequestParamUtil.getEncodeParam(request,
				"prCoProductId");
		params.put("prCoProductId", powerStationId);
		try {
			// proCommentList = productCommentService.findProComments(params);
			maps = pscService.getPsCommentsPageList(params);
			pager.setCountData(pscService.countPsCommentsByPowerId(params));
			pager.setDate(maps);
			commentPage.setPager(pager);
			replyMaps = pscService.getPsReplyCommentsList(params.get("prCoProductId").toString());
			commentPage.setReplyData(replyMaps);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取产品信息错误", e);
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}
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
			if (pscService.checkIsHavePraise(params))
				return new AccessErrorResult("error.msg.invalid.parameter")
						.toString();
			pscService.insertPsPraiseCommnet(params);
			pscService.updatePsPraiseCount(params);
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
			content = params.get("psContent").toString();
			List sensitiveWordList=redisService.listGetAll(WanmaConstants.SENSITIVE_WORD_LIST);
			if (SensitiveWordUtil.getSensitiveWord(content,sensitiveWordList).size() >= 2) {
				// 返回信息输入有敏感词信息
				return new AccessErrorResult("error.msg.invalid.sensitive")
						.toString();
			};
			
			if (SensitiveWordUtil.getSensitiveWord(content,sensitiveWordList).size() == 1) {
				content = SensitiveWordUtil.replaceSensitiveWord(content, "*",sensitiveWordList);
			};
			params.put("psContent", content);
			params.put("createdate", new Date());
			pscService.insertReply(params);
			pscService.updatePsReplyCount(params);
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