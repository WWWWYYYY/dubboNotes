package com.dubbo.service.impl;

import com.dubbo.dao.UserDao;
import com.dubbo.service.UserService;
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
