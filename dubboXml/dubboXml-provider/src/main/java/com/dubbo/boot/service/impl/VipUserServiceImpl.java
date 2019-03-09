package com.dubbo.boot.service.impl;

import com.dubbo.boot.service.VipUserService;

public class VipUserServiceImpl implements VipUserService {

    public String getVipDetail(String id) {
        System.out.println(super.getClass().getName()+"被调用一次："+System.currentTimeMillis());
        return "13221";
    }
}
