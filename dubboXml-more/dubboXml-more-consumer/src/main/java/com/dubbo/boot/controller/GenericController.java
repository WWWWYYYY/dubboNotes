package com.dubbo.boot.controller;

import com.alibaba.dubbo.rpc.service.GenericService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GenericController implements ApplicationContextAware {
    private ApplicationContext ctx;

    /**
     * 泛化调用
     * 当前项目，没有对应的接口---- com.enjoy.service.OtherService
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/other", method = RequestMethod.GET)
    @ResponseBody
    public String other(HttpServletRequest request, HttpServletResponse response) {
        GenericService genericService = (GenericService) ctx.getBean("otherService");//从ioc中获取对应的bean
        //参数一：方法名；参数二：形参类型列表：参数三：实参值列表
        Object ret = genericService.$invoke("getDetail", new String[]{"java.lang.String"}, new Object[]{"name"});
        return ret.toString();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
