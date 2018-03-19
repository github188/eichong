package com.bluemobi.db.dao;

import java.util.Collection;
import java.util.List;

import com.bluemobi.model.Player;
/**
 * 用户相关数据访问对象接口
 * @author haojian
 * Apr 8, 2013 5:01:32 PM
 */
public interface IPlayerDao {
	
	/**保存 更新 玩家*/
	void saveOrUpdate(Player player);
	/**批量保存 更新 玩家*/
	void saveOrUpdateAll(Collection<Player> coll);
	/**根据玩家id查询 玩家信息*/
	Player getPlayerById(int playerId);
	/**根据用户id查询玩家信息*/
	Player findPlayerByUserId(int userId);
	/**根据名字查询玩家信息*/
	Player findPlayerByName(String name);
	
	/**按照等级段、数量查询玩家列表*/
	List<Player> findPlayerByLevel(int minLevel, int maxLevel, int num);

	/**获取全服玩家*/
	public List<Player> findAllPlayer();
	
	/**获取数据库离线玩家*/
	public List<Player> findOffLinePlayer(int nowTime);
}
