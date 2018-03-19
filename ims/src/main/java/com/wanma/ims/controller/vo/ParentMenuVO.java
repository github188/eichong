package com.wanma.ims.controller.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParentMenuVO implements Serializable {

	/**
	 * 菜单父级
	 */
	private static final long serialVersionUID = -865224869574298900L;

	private String menuId; // 菜单ID
	private String contents; // 菜单名称
	private String imageUrl; // 菜单图片
	private String isSelected;// 是否选中
	private String sort; // 排序
	private List<MenuVO> menuList = new ArrayList<MenuVO>();
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}

	public List<MenuVO> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuVO> menuList) {
		this.menuList = menuList;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
