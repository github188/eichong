package com.wanma.ims.mapper;

import com.wanma.ims.common.domain.CardApplicationFormDO;

import java.util.List;

public interface CardApplicationFormMapper {
    long countCardApplicationForm(CardApplicationFormDO searchModel);

    List<CardApplicationFormDO> selectCardApplicationForm(CardApplicationFormDO searchModel);

    int deleteCardApplicationFormById(Long id);

    int insertCardApplicationForm(CardApplicationFormDO cardApplicationForm);

    CardApplicationFormDO selectCardApplicationFormById(Long id);

    int updateCardApplicationFormSelective(CardApplicationFormDO cardApplicationForm);
}