package com.wanma.ims.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wanma.ims.common.domain.MenuDO;
/**
 * 菜单Mapper
 * 
 * @version V1.0
 * @author zcy
 * @date 2017年5月26日
 */
public interface MenuMapper {
	/**
	 * 根据roleId获取菜单
	 * @param roleId
	 */
	public List<MenuDO> selectMenuListByRoleIds(@Param("roleId") String roleId);
	
	/**
	 * 父级菜单 
	 */
	public List<MenuDO> selectParentMenuListByRoleIds(@Param("roleId") String roleId);
	
	/**
	 * 按钮菜单
	 */
	public List<MenuDO> selectMenuButtonListByRoleIds(@Param("roleId") String roleId);
	
	 /**
	  * 统计菜单列表
	  *  @param menuDO
	 */
    public Long countMenuList(MenuDO menuDO);
    /**
	 * 查询菜单列表
	 * @param menuDO
	 */
	public List<MenuDO> selectMenuListPage(MenuDO menuDO);
	/**
	 * 添加菜单
	 * @param menuDO
	 */
	public Integer insertMenu(MenuDO menuDO);
	/**
	 * 根据ID查询菜单
	 * @param menuId
	 */
	public MenuDO selectMenuById(String menuId);
	/**
	 * 根据父级ID查询菜单
	 * @param menuId
	 */
	public List<MenuDO> selectMenuByParentId(String menuId);
	/**
	 * 编辑菜单
	 * @param menuDO
	 */
	public Integer updateMenu(MenuDO menuDO);
	/**
	 * 删除(批量)菜单
	 * @param menuDO
	 */
	public void deleteMenu(List<String> list);
	
	/**
	 * 唯一性校验 
	 */
	public int checkMenu(@Param("menuName") String menuName);
	
	/**
	 * 按钮菜单树 
	 */
	public List<MenuDO> selectSelfButtonList(Map<String,String> map);
	
	public List<MenuDO> selectSelfButtonListByAdmin(Map<String,String> map); 
	
}
