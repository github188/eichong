package com.wanma.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.wanma.common.SessionMgr;
import com.wanma.web.service.impl.WebElecPileStarServiceImpl;

@Controller
@RequestMapping("/web/epStar")
public class WebElecPileStarController {
	private static Log log = LogFactory.getLog(WebElecPileStarController.class);

	@Autowired
	private WebElecPileStarServiceImpl epsService;
	
	/**
	 * @Description: 新增电站星评
	 * @param params 
	 * 			epId 电站id，uId 用户id，epStar 星评等级
	 * @return
	 */
	@RequestMapping("/insertEpStar")
	@ResponseBody
	public String insertEpStar(@RequestParam Map<String, Object> param,HttpServletRequest request) {
		String epId = RequestParamUtil.getEncodeParam(request, "epId");
		String epStar=request.getParameter("epStar");
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		String date = dateFormat.format( now ); 
		param.put("createdate", date);
		param.put("epId", epId);
		param.put("epStar", epStar);
		param.put("uId", SessionMgr.getWebUser(request).getUserId());
		param.put("uName",SessionMgr.getWebUser(request).getNormName());

		try {
			epsService.insertEpStar(param);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("新增电桩星评错误", e);
			System.out.println(e);
			// 返回登录信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}

	/**
	 * @Description: 获取产品信息
	 * @param params
	 *            分页参数 产品id
	 * @return
	 */
	/*@RequestMapping("/findPsStar")
	@ResponseBody
	public String findProComments(@RequestParam Map<String, Object> params,
			AppPager pager) {
		// 分页参数
		params.put("pageNum", pager.getPageNum());
		params.put("pageNumber", pager.getPageNumber());

		List<Map<String, Object>> proCommentList = null;

		try {
			//proCommentList = productCommentService.findProComments(params);
			proCommentList = pscService.getPsCommentsPageList(params);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取产品信息错误", e);
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}

		// 返回处理成功信息
		return new AccessSuccessResult(proCommentList).toString();
	}*/

}