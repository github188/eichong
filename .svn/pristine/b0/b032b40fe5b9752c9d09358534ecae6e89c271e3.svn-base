package com.bluemobi.db.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.bluemobi.model.GameObject;
/**
 * 公共的 数据访问对象接口
 * @author haojian
 * May 28, 2013 2:42:07 PM
 */
public interface IGameObjectDao {
	
	/**保存 更新 对象*/
	void saveOrUpdate(GameObject object);
	/**批量 保存 更新 对象*/
	void saveOrUpdateAll(Collection<GameObject> coll);
	/**获取玩家的某个对象列表*/
	List<GameObject> findGameObjectListByPlayerId(Class clazz,int playerId);
	/**获取某个对象列表*/
	List<GameObject> findGameObject(Class clazz, Map<String,Object> paraMap);
	/**获取玩家的某个对象列表 , 查询条件为 where 字符串*/
	List<GameObject> findGameObject(Class clazz,String condition);
	/**获取玩家的某个对象列表 , 查询条件为 where 字符串,传入最大记录数*/
	List<GameObject> findGameObject(Class clazz,String condition,int maxResults);
	/**删除对象*/
	void delete(GameObject object);
	/**批量删除对象*/
	void deleteAll(Collection<GameObject> coll);

}
