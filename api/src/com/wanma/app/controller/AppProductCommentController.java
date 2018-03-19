package com.wanma.app.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.AppPager;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.service.AppProductCommentService;
import com.wanma.model.TblProductcomment;

/**
 * @Description: 产品评论控制器
 * @author songjf
 * @createTime：2015-3-16 下午03:53:21
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/app/productComment")
public class AppProductCommentController extends BaseController {
	private static Log log = LogFactory
			.getLog(AppProductCommentController.class);

	/** 产品评论业务处理对象 */
	@Autowired
	private AppProductCommentService productCommentService;

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
			// 返回登录信息错误信息
			return new AccessErrorResult(2002,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************新增产品评论-begin************************");
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
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
	public String findProComments(@RequestParam Map<String, Object> params,
			AppPager pager) {
		log.info("******************获取产品信息-begin************************");
		// 分页参数
		params.put("pageNum", pager.getPageNum());
		params.put("pageNumber", pager.getPageNumber());

		List<TblProductcomment> proCommentList = null;

		try {
			proCommentList = productCommentService.findProComments(params);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取产品信息错误", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************获取产品信息-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult(proCommentList).toString();
	}

}