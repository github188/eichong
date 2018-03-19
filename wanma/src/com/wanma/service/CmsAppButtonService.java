package com.wanma.service;

import java.util.List;

import com.wanma.model.TblAppButton;


/**
 * banner业务处理器
 * @author mb
 * 
 */
public interface CmsAppButtonService {

	long getButtonListCount();

	List<TblAppButton> getButtonList(TblAppButton appButton);

	void insertButton(TblAppButton appButton);

	TblAppButton getButtonById(int pkButtonId);

	void updateButton(TblAppButton appButton);

	void deleteButtonById(int pkButtonId);

	void downButtonById(int pkButtonId);

	void changeButtonSort(TblAppButton appButton);


	

}
