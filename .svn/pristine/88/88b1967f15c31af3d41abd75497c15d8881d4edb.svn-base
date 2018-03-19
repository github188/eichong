package com.bluemobi.model.abstractModel;

import com.bluemobi.model.GameObject;

/**
 * 要保存到数据库的游戏对象需继承子类
 * 备注：只有在 同一个HashMap或HashSet集合中，不会同时存放 不同类型的子类对象的前提下，子类才可以不重写 equals 和 hashCode 方法！
 *
 * @author haojian
 *         May 10, 2013 10:45:11 AM
 */
public abstract class DBGameObject extends GameObject implements IDBGameObject {

    public static final long serialVersionUID = 1L;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DBGameObject) {
            DBGameObject dbGameObject = (DBGameObject) obj;
            if (getId().equals(dbGameObject.getId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return getId();
    }


}
