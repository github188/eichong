package com.bluemobi.db.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.db.dao.IPlayerDao;
import com.bluemobi.db.service.IPlayerService;
import com.bluemobi.model.Player;
/**
 * 玩家相关DB服务
 * @author haojian
 * Apr 8, 2013 4:56:28 PM
 */
@Service
public class PlayerService implements IPlayerService {
	
	@Autowired
	private IPlayerDao playerDao;
	
	@Override
	public Player getPlayerById(int playerId) {
		Player player = playerDao.getPlayerById(playerId);
		return player;
	}
	
	@Override
	public Player findPlayerByUserId(int userId) {
		Player player = playerDao.findPlayerByUserId(userId);
		return player;
	}

	@Override
	public void saveOrUpdate(Player player) {
		playerDao.saveOrUpdate(player);
	}
	
	@Override
	public void saveOrUpdateAll(Collection<Player> coll) {
		playerDao.saveOrUpdateAll(coll);
		
	}

	@Override
	public Player findPlayerByName(String name) {
		Player player = playerDao.findPlayerByName(name);
		return player;
	}

	@Override
	public List<Player> findPlayerByLevel(int minLevel, int maxLevel, int num) {
		List<Player> list = playerDao.findPlayerByLevel(minLevel, maxLevel, num);
		return list;
	}
	
	/**获取全服玩家*/
	@Override
	public List<Player> findAllPlayer(){
		return playerDao.findAllPlayer();
	}
	
	/**获取数据库离线玩家*/
	@Override
	public List<Player> findOffLinePlayer(int nowTime){
		return playerDao.findOffLinePlayer(nowTime);
	}

}
