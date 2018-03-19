package com.bluemobi.db.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.bluemobi.model.GameObject;
/**
 * 公共的 DB服务接口
 * @author haojian
 * Jun 3, 2013 2:09:44 PM
 */
public interface IGameObjectService {
	
	/**保存 更新 对象*/
	void saveOrUpdate(GameObject object);
	/**批量 保存 更新 对象*/
	void saveOrUpdateAll(Collection<GameObject> coll);
	/**获取玩家的某个对象列表 , 查询条件仅仅为 playerId*/
	List<GameObject> findGameObjectListByPlayerId(Class clazz,int playerId);
	/**获取某个对象列表 ，查询条件为自定义的map， key 为数据库表的列名，value 为条件值*/
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
