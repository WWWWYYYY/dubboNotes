package com.dubbo.dao.impl;

import com.dubbo.dao.OrderDao;
import org.springframework.stereotype.Service;

@Service
public class OrderDaoImpl implements OrderDao {
    @Override
    public String getDetail(String id) {

        return "666";
    }
}
