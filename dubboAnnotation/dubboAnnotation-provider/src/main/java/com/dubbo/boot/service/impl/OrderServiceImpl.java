package com.dubbo.boot.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.dao.OrderDao;
import com.dubbo.boot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Override
    public String getDetail(String id) {
        System.out.println(super.getClass().getName()+"被调用一次："+System.currentTimeMillis());
        return orderDao.getDetail(id);
    }
}
