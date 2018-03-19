package com.wanma.ims.service;

import java.util.List;

import com.wanma.ims.common.domain.MenuDO;
import com.wanma.ims.controller.vo.ButtonVO;
import com.wanma.ims.controller.vo.ParentMenuVO;
import com.wanma.ims.controller.vo.TopMenuVO;

 
/**
 * 菜单业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface MenuService {
	
	
	/**
	 * 根据RoleId 加载菜单
	 * @param roleId 角色ID
	 * @param userLevel 用户级别
	 * @return 
	 * */
	public List<MenuDO> getMenuListByRoleIds(String roleId);

	/**
	 * 统计菜单列表
	 * @param MenuDO 
	 * @return 
	 * */
	public Long countMenuList(MenuDO menuDO);
	
	/**
	 * 查询菜单列表
	 * @param MenuDO 
	 * @return 
	 * */
	public List<MenuDO> getMenuListPage(MenuDO menuDO);
	
	/**
	 * 添加菜单
	 * @param MenuDO 
	 * @return 
	 * */
	public boolean addMenu(MenuDO menuDO);
	
	/**
	 * 根据ID查询菜单信息
	 * @param MenuId
	 * @return 
	 * */
	public MenuDO getMenuById(String menuId);
	
	/**
	 * 根据父级ID查询菜单信息
	 * @param MenuId
	 * @return 
	 * */
	public List<MenuDO> getMenuByParentId(String parentMenuId);
	
	/**
	 * 编辑菜单
	 * @param MenuDO 
	 * @return 
	 * */
	public boolean modifyMenu(MenuDO menuDO);
	
	/**
	 * 删除菜单
	 * @param menuIds 
	 * @return 
	 * */
	public void removeMenu(List<String> menuIds);
	
    /**
     * 校验菜单唯一性
     * @param menuName
     * @return 
     */
	public boolean checkMenu(String menuId,String menuName);
	
	/**
	 * 树型菜单
	 * @param  
	 */
    public TopMenuVO getMenuTreeByRoleId(String roleId);
    
    /**
	 * 菜单仅自己
	 * @param  
	 */
    List<ParentMenuVO> getSelfMenuTree(String roleId);
    
    /**
     * 按钮菜单 
     */
    List<ButtonVO> getSelfButtonTree(String roleId,String menuId);
}
