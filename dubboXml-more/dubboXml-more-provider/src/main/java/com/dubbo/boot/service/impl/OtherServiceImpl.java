package com.dubbo.boot.service.impl;

import com.dubbo.boot.service.OtherService;

public class OtherServiceImpl implements OtherService {

    @Override
    public String getDetail(String id) {

        return "OtherServiceImpl is in service";
    }
}
