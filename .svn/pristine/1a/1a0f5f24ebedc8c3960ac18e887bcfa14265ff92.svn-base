package com.wanma.ims.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.wanma.ims.common.domain.CardApplicationFormDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.UserNormalDO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.mapper.CardApplicationFormMapper;
import com.wanma.ims.mapper.UserNormalMapper;
import com.wanma.ims.service.CardApplicationFormService;
import com.wanma.ims.service.UserService;
import com.wanma.ims.util.SplitterUtil;


@Service("cardApplicationFormService")
public class CardApplicationFormServiceImpl implements CardApplicationFormService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CardApplicationFormServiceImpl.class);

    @Autowired
    private CardApplicationFormMapper cardApplicationFormMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserNormalMapper userNormalMapper;


    @Override
    public List<CardApplicationFormDO> getCardApplicationFormList(CardApplicationFormDO searchModel) {
        List<CardApplicationFormDO> resultList = cardApplicationFormMapper.selectCardApplicationForm(searchModel);

        if (CollectionUtils.isEmpty(resultList)) {
            return resultList;
        }

        Set<Long> userIds = new HashSet<>();
        for (CardApplicationFormDO cardApplicationForm : resultList) {
            userIds.add(cardApplicationForm.getUserId());
        }

        UserDO userSearchModel = new UserDO();
        userSearchModel.setUserIds(new ArrayList<>(userIds));

        List<UserDO> userList = userService.getUserList(userSearchModel);
        Map<Long, UserDO> userMap = Maps.uniqueIndex(userList, new Function<UserDO, Long>() {
            @Override
            public Long apply(UserDO input) {
                return input.getUserId();
            }
        });
        for (CardApplicationFormDO cardApplicationForm : resultList) {
            UserDO user = userMap.get(cardApplicationForm.getUserId());
            if (user != null) {
                cardApplicationForm.setUserAccount(user.getUserAccount());
                cardApplicationForm.setCpyName(user.getCpyName());
                cardApplicationForm.setCpyNumber(user.getCpyNumber());
            }
        }

        return resultList;
    }

    @Override
    public long countCardApplicationForm(CardApplicationFormDO searchModel) {
        return cardApplicationFormMapper.countCardApplicationForm(searchModel);
    }

    @Override
    @Transactional
    public BaseResultDTO addCardApplicationForm(CardApplicationFormDO cardApplicationForm, UserDO loginUser) {
        if (cardApplicationForm == null) {
            LOGGER.warn(this.getClass() + "-addCardApplicationForm is error, addCardApplicationForm is null|loginUser={}", loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "新增申请卡失败，申请卡不能为空！");
        }

        if (cardApplicationFormMapper.insertCardApplicationForm(cardApplicationForm) < 1) {
            LOGGER.warn(this.getClass() + "-addCardApplicationForm is error, insert cardApplicationForm to db is error|addCardApplicationForm={}|loginUser={}", cardApplicationForm, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "新增申请卡失败，请刷新页面后重试！");
        }

        return new BaseResultDTO();
    }

    @Override
    @Transactional
    public BaseResultDTO rejectionApply(String cardApplicationFormIds, UserDO loginUser) throws Exception {
        if (Strings.isNullOrEmpty(cardApplicationFormIds)) {
            LOGGER.warn(this.getClass() + "-rejectionApply is error, cardApplicationFormIds is null|loginUser={}", loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "申请驳回失败，申请卡id不能为空！");
        }

        List<Long> cardApplicationFormIdList = SplitterUtil.splitToLongList(cardApplicationFormIds, ",", null);
        if (CollectionUtils.isEmpty(cardApplicationFormIdList)) {
            LOGGER.warn(this.getClass() + "-rejectionApply is error, cardApplicationFormIdList is null|cardApplicationFormIds={}|loginUser={}", cardApplicationFormIds, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "申请驳回失败，申请卡id不能为空！");
        }

        for (Long applyId : cardApplicationFormIdList) {
            CardApplicationFormDO cardApplicationForm = new CardApplicationFormDO();
            cardApplicationForm.setId(applyId);
            cardApplicationForm.setStatus(WanmaConstants.CARD_APPLICATION_FAILED);

            CardApplicationFormDO oldCard = cardApplicationFormMapper.selectCardApplicationFormById(applyId);
            if (oldCard == null) {
                LOGGER.warn(this.getClass() + "-rejectionApply is error, oldCard is null|applyId={}|loginUser={}", applyId, loginUser);
                return new BaseResultDTO(false, ResultCodeConstants.FAILED, "申请驳回失败，申请卡不存在，请刷新页面后重试！");
            }

            UserNormalDO oldUser = userNormalMapper.selectUserNormalByUserId(oldCard.getUserId());
            if (oldUser == null) {
                LOGGER.warn(this.getClass() + "-rejectionApply is error, oldUser is null|applyId={}|loginUser={}", applyId, loginUser);
                return new BaseResultDTO(false, ResultCodeConstants.FAILED, "申请驳回失败，请刷新页面后重试！");
            }

            if (cardApplicationFormMapper.updateCardApplicationFormSelective(cardApplicationForm) < 1) {
                LOGGER.warn(this.getClass() + "-rejectionApply is error, updateCardApplicationFormSelective to db is error|applyId={}|loginUser={}", applyId, loginUser);
                return new BaseResultDTO(false, ResultCodeConstants.FAILED, "申请驳回失败，请刷新页面后重试！");
            }

            UserNormalDO modifyUser = new UserNormalDO();
            modifyUser.setUserId(oldUser.getUserId());
            modifyUser.setApplyCard(0);
            if (userNormalMapper.updateByUserIdSelective(modifyUser) < 1) {
                LOGGER.warn(this.getClass() + "-rejectionApply is error, updateByUserIdSelective to db is error|applyId={}|loginUser={}", applyId, loginUser);
                return new BaseResultDTO(false, ResultCodeConstants.FAILED, "申请驳回失败，请刷新页面后重试！");
            }
        }
        return new BaseResultDTO();
    }
}
