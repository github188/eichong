package com.wanma.ims.common.domain.bo;

import com.wanma.ims.common.domain.ProvinceDO;

import java.util.List;

public class IntgeralAreaRelaBO {

	private String name;

	private Boolean isSelected;

	private List<ProvinceDO> list;

	public static IntgeralAreaRelaBO valueOf(List<ProvinceDO> provinceDOList) {
		IntgeralAreaRelaBO vo = new IntgeralAreaRelaBO();
		vo.setName("全国");
		vo.setIsSelected(true);
		vo.setList(provinceDOList);
		for (ProvinceDO province : provinceDOList) {
			if (!province.getIsSelected()) {
				vo.setIsSelected(false);
				return vo;
			}
		}
		return vo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public List<ProvinceDO> getList() {
		return list;
	}

	public void setList(List<ProvinceDO> list) {
		this.list = list;
	}
}
