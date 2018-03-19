package com.wanma.ims.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanma.ims.common.domain.MenuDO;
import com.wanma.ims.constants.MenuEnum;
import com.wanma.ims.controller.vo.ButtonVO;
import com.wanma.ims.controller.vo.MenuVO;
import com.wanma.ims.controller.vo.ParentMenuVO;
import com.wanma.ims.controller.vo.TopMenuVO;
import com.wanma.ims.mapper.MenuMapper;
import com.wanma.ims.mapper.RoleMapper;
import com.wanma.ims.service.MenuService;

/**
 * FileName MenuServiceImpl.java
 * 
 * VersionDO 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 菜单业务处理类
 */
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	RoleMapper roleMapper;

	
	@Override
	public List<MenuDO> getMenuListByRoleIds(String roleId){
		return menuMapper.selectMenuListByRoleIds(roleId);
	}
	
	@Override
	public Long countMenuList(MenuDO menuDO) {
		return menuMapper.countMenuList(menuDO);
	}

	@Override
	public List<MenuDO> getMenuListPage(MenuDO menuDO) {
		return menuMapper.selectMenuListPage(menuDO);
	}
	
	@Override
	@Transactional
	public boolean addMenu(MenuDO menuDO) {
		return menuMapper.insertMenu(menuDO) > 0 ?true:false;
	}
	
	@Override
	public MenuDO getMenuById(String menuId) {
		return menuMapper.selectMenuById(menuId);
	}
	
	@Override
	public List<MenuDO> getMenuByParentId(String parentMenuId) {
		return menuMapper.selectMenuByParentId(parentMenuId);
	}
	
	@Override
	@Transactional
	public boolean modifyMenu(MenuDO menuDO) {
		return menuMapper.updateMenu(menuDO) > 0 ? true:false;
	}
	
	@Override
	@Transactional
	public void removeMenu(List<String> menuIds) {
		menuMapper.deleteMenu(menuIds);
	}
	
	@Override
	public boolean checkMenu(String menuId,String menuName) {
		MenuDO menuDO = menuMapper.selectMenuById(menuId);
		if(null == menuDO){
			return menuMapper.checkMenu(menuName) > 0;
		}
		if(!menuDO.getContents().equals(menuName)){
			return menuMapper.checkMenu(menuName) > 0;
		}
		return false;
	
	}

	@Override
	public TopMenuVO getMenuTreeByRoleId(String roleId) {
		List<MenuDO> parentMenuList = menuMapper.selectParentMenuListByRoleIds(roleId);
		List<MenuDO> menuList = menuMapper.selectMenuListByRoleIds(roleId);
		List<MenuDO> buttonList = menuMapper.selectMenuButtonListByRoleIds(roleId);
		Map<String, List<ButtonVO>> buttonMap = new HashMap<String, List<ButtonVO>>();
		for (MenuDO menuDO : buttonList) {
			List<ButtonVO> buttonVOList = buttonMap.get(menuDO.getParentMenuId());
			if (null == buttonVOList) {
				buttonVOList = new ArrayList<ButtonVO>();
			}
			ButtonVO buttonVO = new ButtonVO();
			buttonVO.setMenuId(menuDO.getMenuId());
			buttonVO.setContents(menuDO.getContents());
			buttonVO.setParentMenuId(menuDO.getParentMenuId());
			buttonVO.setIsSelected(menuDO.getIsSelected());
			buttonVO.setSort(String.valueOf((menuDO.getSortNumber())));
			buttonVOList.add(buttonVO);
			buttonMap.put(menuDO.getParentMenuId(), buttonVOList);
		}
		Map<String, List<MenuVO>> menuMap = new HashMap<String, List<MenuVO>>();
		for (MenuDO menuDO : menuList) {
			List<MenuVO> menuVOList = menuMap.get(menuDO.getParentMenuId());
			if (null == menuVOList) {
				menuVOList = new ArrayList<MenuVO>();
			}
			MenuVO menuVO = new MenuVO();
			menuVO.setMenuId(menuDO.getMenuId());
			menuVO.setContents(menuDO.getContents());
			menuVO.setParentMenuId(menuDO.getParentMenuId());
			menuVO.setIsSelected(menuDO.getIsSelected());
			menuVO.setSort(String.valueOf((menuDO.getSortNumber())));
			if (null != buttonMap.get(menuDO.getMenuId())) {
				menuVO.setMenuList(buttonMap.get(menuDO.getMenuId()));
			}
			menuVOList.add(menuVO);
			menuMap.put(menuDO.getParentMenuId(), menuVOList);
		}
		List<ParentMenuVO> parentMenuVOList = new ArrayList<ParentMenuVO>();
		for (MenuDO menuDO : parentMenuList) {
			ParentMenuVO parentMenuVO = new ParentMenuVO();
			parentMenuVO.setMenuId(menuDO.getMenuId());
			parentMenuVO.setContents(menuDO.getContents());
			parentMenuVO.setIsSelected(menuDO.getIsSelected());
			parentMenuVO.setSort(String.valueOf((menuDO.getSortNumber())));
//			parentMenuVO.setImageUrl(MenuEnum.getMenuUrl(menuDO.getContents()));
			if (null != menuMap.get(menuDO.getMenuId())) {
				parentMenuVO.setMenuList(menuMap.get(menuDO.getMenuId()));
			}
			parentMenuVOList.add(parentMenuVO);
		}
		TopMenuVO topMenuVO = new TopMenuVO();
		topMenuVO.setContents("根菜单");
		topMenuVO.setMenuList(parentMenuVOList);
		return topMenuVO;
	}
	
	@Override
	public List<ParentMenuVO> getSelfMenuTree(String roleId) {
		List<MenuDO> parentMenuList = menuMapper.selectParentMenuListByRoleIds(roleId);
		List<MenuDO> menuList = menuMapper.selectMenuListByRoleIds(roleId);
		Map<String, List<MenuVO>> menuMap = new HashMap<String, List<MenuVO>>();
		for (MenuDO menuDO : menuList) {
			if(StringUtils.isNotEmpty(roleId)){
				if("0".equals(menuDO.getIsSelected())){
					continue;
				}
			}
			List<MenuVO> menuVOList = menuMap.get(menuDO.getParentMenuId());
			if (null == menuVOList) {
				menuVOList = new ArrayList<MenuVO>();
			}
			
			MenuVO menuVO = new MenuVO();
			menuVO.setMenuId(menuDO.getMenuId());
			menuVO.setContents(menuDO.getContents());
			menuVO.setUrl(menuDO.getUrl());
			menuVO.setParentMenuId(menuDO.getParentMenuId());
			menuVO.setIsSelected(menuDO.getIsSelected());
			menuVO.setSort(String.valueOf((menuDO.getSortNumber())));
			menuVOList.add(menuVO);
			menuMap.put(menuDO.getParentMenuId(), menuVOList);
		}
		List<ParentMenuVO> parentMenuVOList = new ArrayList<ParentMenuVO>();
		for (MenuDO menuDO : parentMenuList) {
			if(StringUtils.isNotEmpty(roleId)){
				if("0".equals(menuDO.getIsSelected())){
					continue;
				}
			}
			ParentMenuVO parentMenuVO = new ParentMenuVO();
			parentMenuVO.setMenuId(menuDO.getMenuId());
			parentMenuVO.setContents(menuDO.getContents());
			parentMenuVO.setIsSelected(menuDO.getIsSelected());
			parentMenuVO.setSort(String.valueOf((menuDO.getSortNumber())));
			parentMenuVO.setImageUrl(MenuEnum.getMenuUrl(menuDO.getContents()));
			if (null != menuMap.get(menuDO.getMenuId())) {
				parentMenuVO.setMenuList(menuMap.get(menuDO.getMenuId()));
			}
			parentMenuVOList.add(parentMenuVO);
		}
		return parentMenuVOList;
	}

	@Override
	public List<ButtonVO> getSelfButtonTree(String roleId,String menuId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("roleId", roleId);
		map.put("menuId", menuId);
		List<MenuDO> list = null;
		if(StringUtils.isEmpty(roleId)){
			list = menuMapper.selectSelfButtonListByAdmin(map);
		}else{
			list = menuMapper.selectSelfButtonList(map);
		}
		List<ButtonVO> buttonList = new ArrayList<ButtonVO>();
		for (MenuDO menuDO : list) {
			ButtonVO buttonVO = new ButtonVO();
			buttonVO.setMenuId(menuDO.getMenuId());
			buttonVO.setContents(menuDO.getContents());
			buttonVO.setParentMenuId(menuDO.getParentMenuId());
			buttonVO.setSort(String.valueOf((menuDO.getSortNumber())));
			buttonList.add(buttonVO);
		}
		return buttonList;
	}

}
