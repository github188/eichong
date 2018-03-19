package com.bluemobi.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.db.dao.IUserDao;
import com.bluemobi.db.service.IUserService;
import com.bluemobi.model.User;

/**
 * 用户相关DB服务
 *
 * @author haojian
 *         Apr 8, 2013 4:56:57 PM
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public void saveOrUpdate(User user) {
        userDao.saveOrUpdate(user);
    }

    @Override
    public User findUserByUserName(String username) {
        return userDao.findUserByUserName(username);
    }


    @Override
    public User findUserByUserName(String username, int source) {
        return userDao.findUserByUserName(username, source);
    }

    /**
     * 根据userid获取user信息
     *
     * @param userId
     * @return
     */
    public User findUserByUserId(int userId) {
        return userDao.findUserByUserId(userId);
    }

}
