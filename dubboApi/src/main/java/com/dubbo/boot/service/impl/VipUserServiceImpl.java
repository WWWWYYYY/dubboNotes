package com.dubbo.boot.service.impl;

import com.dubbo.boot.service.VipUserService;

public class VipUserServiceImpl implements VipUserService {

    public String getVipDetail(String id) {
        String ret = super.getClass().getName()+"被调用一次："+System.currentTimeMillis();
        System.out.println(ret);
        return ret;
    }
}
