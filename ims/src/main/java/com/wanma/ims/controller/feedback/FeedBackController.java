package com.wanma.ims.controller.feedback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.ims.common.domain.FeedBackDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.FeedBackService;


/**
 * 意见反馈
 */
@Controller
@RequestMapping("/manage/feedback")
public class FeedBackController extends BaseController{
	 private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Autowired private FeedBackService feedBackService;
 	/**
     * 意见反馈列表
     */
    @RequestMapping(value = "/getFeedBackList")
    @ResponseBody
    public void getFeedBackList(FeedBackDO feedBack,Pager pager) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            long total = feedBackService.getFeedBackListCount(feedBack);
            if (total <= pager.getOffset()) {
				pager.setPageNo(1L);
			}
            pager.setTotal(total);
            feedBack.setPager(pager);
            List<FeedBackDO> resultList = feedBackService.getFeedBackList(feedBack);
            result.setPager(pager);
            result.setDataObject(resultList);
            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-getFeedBackList is error|loginUser={}", loginUser, e);
            responseJson(new JsonException("查询意见反馈列表失败！"));
        }
    }

    /**
     * 根据主键获取意见反馈
     */
    @RequestMapping(value = "/getFeedBackById")
    @ResponseBody
    public void getFeedBackById(int pkFeedBack) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();
        try {
        	FeedBackDO feedBack = feedBackService.getFeedBackById(pkFeedBack);
            result.setDataObject(feedBack);
            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-getFeedBackById is error|loginUser={}", loginUser, e);
            responseJson(new JsonException("查询意见反馈失败！"));
        }
    }
	
    /**
     * 处理意见反馈
     */
    @RequestMapping(value = "/editFeedBack")
    @ResponseBody
    public void editFeedBack(FeedBackDO feedBack) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();
        try {
        	feedBack.setUpdateUserId(loginUser.getUserId().intValue());
        	BaseResultDTO dto = feedBackService.editFeedBack(feedBack);
        	if(!dto.isSuccess()){
				responseJson(new JsonResult(false, ResultCodeConstants.FAILED, dto.getErrorDetail()));
				return;
			}
        	responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-editFeedBack is error|loginUser={}", loginUser, e);
            responseJson(new JsonException("查询意见反馈列表失败！"));
        }
    }
	
}
