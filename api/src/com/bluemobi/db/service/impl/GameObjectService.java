package com.bluemobi.db.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.db.dao.IGameObjectDao;
import com.bluemobi.db.service.IGameObjectService;
import com.bluemobi.model.GameObject;
/**
 * 公共的 DB服务
 * @author haojian
 * Jun 3, 2013 2:09:20 PM
 */
@Service
public class GameObjectService implements IGameObjectService {

	
	@Autowired
	private IGameObjectDao gameObjectDao;
	
	@Override
	public void delete(GameObject object) {
		gameObjectDao.delete(object);

	}

	@Override
	public void deleteAll(Collection<GameObject> coll) {
		gameObjectDao.deleteAll(coll);

	}

	@Override
	public List<GameObject> findGameObjectListByPlayerId(Class clazz, int playerId) {
		List<GameObject> list = gameObjectDao.findGameObjectListByPlayerId(clazz, playerId);
		return list;
	}
	
	@Override
	public List<GameObject> findGameObject(Class clazz,Map<String, Object> paraMap) {
		List<GameObject> list = gameObjectDao.findGameObject(clazz, paraMap);
		return list;
	}

	@Override
	public void saveOrUpdate(GameObject object) {
		gameObjectDao.saveOrUpdate(object);

	}

	@Override
	public void saveOrUpdateAll(Collection<GameObject> coll) {
		gameObjectDao.saveOrUpdateAll(coll);

	}

	@Override
	public List<GameObject> findGameObject(Class clazz, String condition) {
		List<GameObject> list = gameObjectDao.findGameObject(clazz, condition);
		return list;
	}

	@Override
	public List<GameObject> findGameObject(Class clazz, String condition,int maxResults) {
		List<GameObject> list = gameObjectDao.findGameObject(clazz, condition, maxResults);
		return list;
	}

	

}
