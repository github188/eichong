package com.wanma.ims.mapper;

import java.util.List;

import com.wanma.ims.common.domain.UserCardDO;

public interface UserCardMapper {

	long getCardCount(UserCardDO userCard);

	List<UserCardDO> getCardList(UserCardDO userCard);

	UserCardDO getUserCard(UserCardDO userCard);

	int updateUserCard(UserCardDO userCard);

	List<UserCardDO> getCardInfoByUserId(UserCardDO userCard);

	UserCardDO checkExternalCardNumber(UserCardDO userCard);

	int bindCard(UserCardDO userCard);

	int disableCardByUserId(UserCardDO userCard);

	int releaseCardByUserId(UserCardDO userCard);

	UserCardDO getCardBasicInfo(UserCardDO userCard);

	List<UserCardDO> getExportCardList(UserCardDO searchModel);

	int getTotalCpyCardNum(UserCardDO userCard);

	int getLossCpyCardNum(UserCardDO userCard);

	UserCardDO getUserCardByAccountId(Long accountId);

	UserCardDO getUnBindCardInfo(Long ucUserId);
	
	void updateIsValid(Long ucId);

	long countUser(Long levelId);
}
