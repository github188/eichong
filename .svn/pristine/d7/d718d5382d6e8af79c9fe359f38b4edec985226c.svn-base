package com.wanma.ims.service;

import com.wanma.ims.common.domain.CardApplicationFormDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;

import java.util.List;

/**
 * 申请卡Service
 */
public interface CardApplicationFormService {

    /**
     * 获取申请卡列表
     */
    List<CardApplicationFormDO> getCardApplicationFormList(CardApplicationFormDO searchModel);

    long countCardApplicationForm(CardApplicationFormDO searchModel);

    /**
     * 新增申请卡
     */
    BaseResultDTO addCardApplicationForm(CardApplicationFormDO cardApplicationForm, UserDO loginUser);

    /**
     * 驳回申请卡
     */
    BaseResultDTO rejectionApply(String cardApplicationFormIds, UserDO loginUser) throws Exception;
}
