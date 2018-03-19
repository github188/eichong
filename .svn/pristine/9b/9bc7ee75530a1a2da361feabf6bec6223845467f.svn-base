package com.wanma.web.controller;
import java.util.HashMap;
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
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.common.SessionMgr;
import com.wanma.web.service.WebTblFeedbackService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.PageResponse;
@Controller
@RequestMapping("web/feedback")
public class WebTblFeedbackController {
	private static Log log = LogFactory.getLog(WebTblFeedbackController.class);
	
	/** 意见反馈业务处理对象 */
	@Autowired
	private WebTblFeedbackService tblFeedbackService;
	
	
	@RequestMapping("/getFeedbackList")
	@ResponseBody
	public String getFeedbackList(HttpServletRequest request,@RequestParam Map<String, Object> params) {
		log.info("******************保存用户的意见反馈-begin************************");
		// 用户ID
		String febaUserid =SessionMgr.getUserId(request)+"";
		//String febaUserid = request.getParameter("febaUserid");
		if (StringUtil.isEmpty(febaUserid)){
			// 请传入ID！
		return new AccessErrorResult("请传入用户ID！").toString();
		}			
		params.put("feBa_UserId", febaUserid);
		Integer begin = null, pageSize = null;
        //获取分页参数
        if (!StringUtils.isEmpty((String) params.get("pageSize"))) {
        	pageSize = Integer.valueOf((String) params.get("pageSize"));
        }
        if (!StringUtils.isEmpty((String) params.get("pageNum"))) {
        	begin = Integer.valueOf((String) params.get("pageNum"));
        }
        PageResponse<List<HashMap<String,Object>>> pager = new PageResponse<List<HashMap<String,Object>>>(begin, pageSize);
		params.put("pageNum", pager.getCount());
		params.put("pageNumber", pager.getBegin());
		try {
			List<HashMap<String,Object>> feedBackList = tblFeedbackService.getFeedbackList(params);
            pager.setCountData(tblFeedbackService.feedbackCount(params));
            pager.setDate(feedBackList);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取产品信息错误", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		log.info("******************获取产品信息-end************************");
		// 返回处理成功信息
		return pager.toString();
	}		
}


