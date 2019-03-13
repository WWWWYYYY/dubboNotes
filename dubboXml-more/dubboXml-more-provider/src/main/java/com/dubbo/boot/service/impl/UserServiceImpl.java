package com.dubbo.boot.service.impl;

import com.dubbo.boot.service.UserService;
import com.dubbo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public String getDetail(String id) {
        System.out.println(super.getClass().getName()+"被调用一次："+System.currentTimeMillis());
        return userDao.getDetail(id);
    }

}
