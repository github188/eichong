package com.bluemobi.model.abstractModel;
/**
 * DB游戏对象接口
 * @author haojian
 * May 10, 2013 10:42:25 AM
 */
public interface IDBGameObject {
	
	public static final long serialVersionUID = 1L;
	
	/**由于java注释无法被继承，为了给所有的DB对象统一提供 hashCode 和 equals 方法，这里提供一个getId()方法，让子类去实现*/
	Integer getId();
	
}
