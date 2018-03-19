package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblUserCard;

public interface TblUserCardMapper {
	public void bindCard(TblUserCard userCard);

	public List<TblUserCard> getCardList(TblUserCard user);

	public long getCardListCount(TblUserCard userCard);

	public TblUserCard getUserCard(TblUserCard userCard);

	public void updateUserard(TblUserCard userCard);

	public void bindCardUser(TblUserCard userCard);
}
