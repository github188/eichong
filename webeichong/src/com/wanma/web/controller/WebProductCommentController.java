package com.wanma.web.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.controller.BaseController;
import com.wanma.model.TblProductcomment;
import com.wanma.web.service.WebProductCommentService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.PageResponse;
import com.wanma.web.support.common.SuccessResponse;

/**
 * @Description: 产品评论控制器
 * @author songjf
 * @createTime：2015-3-16 下午03:53:21
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/web/productComment")
public class WebProductCommentController extends BaseController {
	private static Log log = LogFactory.getLog(WebProductCommentController.class);

	/** 产品评论业务处理对象 */
	@Autowired
	private WebProductCommentService productCommentService;

	/**
	 * @Title: insertProCommnet
	 * @Description: 新增产品评论
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertProCommnet")
	@ResponseBody
	public String insertProCommnet(TblProductcomment productcomment) {
		log.info("******************新增产品评论-begin************************");
		try {
			productCommentService.insertProCommnet(productcomment);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("新增产品评论错误", e);
			System.out.println(e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		log.info("******************新增产品评论-begin************************");
		// 返回处理成功信息
		return new SuccessResponse().toString();
	}

	/**
	 * @Title: findProComments
	 * @Description: 获取产品信息
	 * @param params
	 *            分页参数 产品id 评论类型 1：桩体评价 2：商城商品评价 3：充值评价
	 * @return
	 */
	@RequestMapping("/findProComments")
	@ResponseBody
	public String findProComments(@RequestParam Map<String, Object> params) {
		log.info("******************获取产品信息-begin************************");
		Integer begin = null, pageSize = null;
        //获取分页参数
        if (!StringUtils.isEmpty((String) params.get("pageNum"))) {
        	pageSize = Integer.valueOf((String) params.get("pageNum"));
        }
        if (!StringUtils.isEmpty((String) params.get("pageNumber"))) {
        	begin = Integer.valueOf((String) params.get("pageNumber"));
        }
        PageResponse<List<TblProductcomment>> pager = new PageResponse<List<TblProductcomment>>(begin, pageSize);
		params.put("pageNum", pager.getCount());
		params.put("pageNumber", pager.getBegin());
		try {
			List<TblProductcomment> proCommentList = productCommentService.findProComments(params);
            pager.setCountData(productCommentService.countProComments(params));
            pager.setDate(proCommentList);
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