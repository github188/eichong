package com.wanma.ims.controller.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8205344106579446864L;

	private String menuId; // 菜单ID
	private String contents; // 菜单名称
	private String url; // 菜单链接
	private String parentMenuId; // 菜单父级
	private String isSelected; // 是否选中
	private String sort; // 排序
	private List<ButtonVO> menuList = new ArrayList<ButtonVO>();

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
	
	public List<ButtonVO> getMenuList() {
		return menuList;
	}

	
	public void setMenuList(List<ButtonVO> menuList) {
		this.menuList = menuList;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
