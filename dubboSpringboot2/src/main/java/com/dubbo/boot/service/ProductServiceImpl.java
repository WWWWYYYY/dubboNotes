package com.dubbo.boot.service;

import com.alibaba.dubbo.config.annotation.Service;

@Service
//@Component //不需要会用Component
public class ProductServiceImpl implements ProductService {

    @Override
    public String getDetail(String id) {
        System.out.println(super.getClass().getName()+"被调用一次："+System.currentTimeMillis());
        return "iponex 售价：1万元";
    }
}
