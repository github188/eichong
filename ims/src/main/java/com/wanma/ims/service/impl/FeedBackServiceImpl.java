package com.wanma.ims.service.impl;

import com.wanma.ims.common.domain.FeedBackDO;
import com.wanma.ims.common.domain.JpushDO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.mapper.FeedBackMapper;
import com.wanma.ims.service.AppJpushService;
import com.wanma.ims.service.FeedBackService;
import com.wanma.ims.util.JPushUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("feedBackService")
public class FeedBackServiceImpl implements FeedBackService {
    @Autowired
    private FeedBackMapper feedBackMapper;
    @Autowired
    private AppJpushService appJpushService;

    @Override
    public long getFeedBackListCount(FeedBackDO feedBack) {
        return feedBackMapper.getFeedBackListCount(feedBack);
    }

    @Override
    public List<FeedBackDO> getFeedBackList(FeedBackDO feedBack) {
        return feedBackMapper.getFeedBackList(feedBack);
    }

    @Override
    public FeedBackDO getFeedBackById(int pkFeedBack) {
        return feedBackMapper.getFeedBackById(pkFeedBack);
    }

    @Override
    @Transactional
    public BaseResultDTO editFeedBack(FeedBackDO feedBack) {
        if (feedBackMapper.editFeedBack(feedBack) <= 0) {
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "处理意见反馈失败!");
        }

        //消息极光推送app
        if (feedBack.getIsPush() == 1) {
            feedBack = feedBackMapper.getFeedBackById(feedBack.getPkFeedBack());
            JpushDO jpush = appJpushService.getByuserInfo(feedBack.getUserId());
            BaseResultDTO result = new BaseResultDTO();
            JPushUtil.jpushNotice("充充侠回复您啦", feedBack.getReason(), jpush.getJpushRegistrationid(), "13");

            Map<String, String> msgMap = JPushUtil.getBasicMsgMap("充充侠回复您啦", feedBack.getReason());
            msgMap.put("orderid", "null");
            msgMap.put("userid", "null");
            JPushUtil.jpushCustom(msgMap, jpush.getJpushRegistrationid(), "13", false);
            if (result.isFailed()) {
                return new BaseResultDTO(false, result.getResultCode(), result.getErrorDetail());
            }
        }
        return new BaseResultDTO();
    }


}
