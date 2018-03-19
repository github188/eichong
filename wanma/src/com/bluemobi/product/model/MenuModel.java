/**
 * FileName:MenuModel.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.model;

import java.util.List;

import com.bluemobi.product.model.common.BasicListModel;

/**
 * 菜单实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class MenuModel extends BasicListModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = -8855032796679657161L;

	/** 菜单ID */
	private String menuId;

	/** 菜单分类：1菜单权限，2按钮权限 */
	private String menuType;

	/** 显示内容 */
	private String contents;

	/** 排序 */
	private Long sortNumber;

	/** 链接 */
	private String url;

	/** 父菜单ID */
	private String parentMenuId;

	/** 画面显示标签 */
	private String rel;

	/** 画面显示标签 */
	private String referChild;
	
	/** 备注 */
	private String notes;
	
	private Integer isSelected;
	private Integer hasChild;
	private String parentMenuName;

	/** 菜单职位权限列表 */
	private List<MenuPostModel> menuPostList;

	/** 菜单角色权限列表 */
	private List<MenuRoleModel> menuRoleList;

	/** 菜单部门权限列表 */
	private List<MenuDepartmentModel> menuDeptList;

	/** 菜单职位权限列表 */
	private List<CompanyPostModel> postList;

	/** 菜单角色权限列表 */
	private List<RoleModel> roleList;

	/** 菜单部门权限列表 */
	private List<DepartmentModel> deptList;
	
	

	/**
	 * 菜单ID的取得。
	 * 
	 * @return 菜单ID
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * 菜单ID的设定。
	 * 
	 * @param pMenuId
	 *            菜单ID
	 */
	public void setMenuId(String pMenuId) {
		this.menuId = pMenuId;
	}

	

	/**
	 * 显示内容的取得。
	 * 
	 * @return 显示内容
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * 显示内容的设定。
	 * 
	 * @param pContents
	 *            显示内容
	 */
	public void setContents(String pContents) {
		this.contents = pContents;
	}

	

	public Long getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(Long sortNumber) {
		this.sortNumber = sortNumber;
	}

	/**
	 * 链接的取得。
	 * 
	 * @return 链接
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 链接的设定。
	 * 
	 * @param pUrl
	 *            链接
	 */
	public void setUrl(String pUrl) {
		this.url = pUrl;
	}

	/**
	 * 父菜单ID的取得。
	 * 
	 * @return 父菜单ID
	 */
	public String getParentMenuId() {
		return parentMenuId;
	}

	/**
	 * 父菜单ID的设定。
	 * 
	 * @param pParentMenuId
	 *            父菜单ID
	 */
	public void setParentMenuId(String pParentMenuId) {
		this.parentMenuId = pParentMenuId;
	}

	/**
	 * 备注的取得。
	 * 
	 * @return 备注
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * 备注的设定。
	 * 
	 * @param pNotes
	 *            备注
	 */
	public void setNotes(String pNotes) {
		this.notes = pNotes;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	/**
	 * @return the menuPostList
	 */
	public List<MenuPostModel> getMenuPostList() {
		return menuPostList;
	}

	/**
	 * @param menuPostList
	 *            the menuPostList to set
	 */
	public void setMenuPostList(List<MenuPostModel> menuPostList) {
		this.menuPostList = menuPostList;
	}

	/**
	 * @return the menuRoleList
	 */
	public List<MenuRoleModel> getMenuRoleList() {
		return menuRoleList;
	}

	/**
	 * @param menuRoleList
	 *            the menuRoleList to set
	 */
	public void setMenuRoleList(List<MenuRoleModel> menuRoleList) {
		this.menuRoleList = menuRoleList;
	}

	/**
	 * @return the menuDeptList
	 */
	public List<MenuDepartmentModel> getMenuDeptList() {
		return menuDeptList;
	}

	/**
	 * @param menuDeptList
	 *            the menuDeptList to set
	 */
	public void setMenuDeptList(List<MenuDepartmentModel> menuDeptList) {
		this.menuDeptList = menuDeptList;
	}

	/**
	 * @return the postList
	 */
	public List<CompanyPostModel> getPostList() {
		return postList;
	}

	/**
	 * @param postList
	 *            the postList to set
	 */
	public void setPostList(List<CompanyPostModel> postList) {
		this.postList = postList;
	}

	/**
	 * @return the roleList
	 */
	public List<RoleModel> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList
	 *            the roleList to set
	 */
	public void setRoleList(List<RoleModel> roleList) {
		this.roleList = roleList;
	}

	/**
	 * @return the deptList
	 */
	public List<DepartmentModel> getDeptList() {
		return deptList;
	}

	/**
	 * @param deptList
	 *            the deptList to set
	 */
	public void setDeptList(List<DepartmentModel> deptList) {
		this.deptList = deptList;
	}

	/**
	 * 画面显示标签的取得。
	 * 
	 * @return 画面显示标签
	 */
	public String getRel() {
		return rel;
	}

	/**
	 * 画面显示标签的设定。
	 * 
	 * @param pRel
	 *            画面显示标签
	 */
	public void setRel(String pRel) {
		this.rel = pRel;
	}

	/**
	 * 画面显示标签的取得。
	 * 
	 * @return 画面显示标签
	 */
	public String getReferChild() {
		return referChild;
	}

	/**
	 * 画面显示标签的设定。
	 * 
	 * @param pReferChild
	 *            画面显示标签
	 */
	public void setReferChild(String pReferChild) {
		this.referChild = pReferChild;
	}

	public Integer getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Integer isSelected) {
		this.isSelected = isSelected;
	}

	public Integer getHasChild() {
		return hasChild;
	}

	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}

	public String getParentMenuName() {
		return parentMenuName;
	}

	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}
	
}
