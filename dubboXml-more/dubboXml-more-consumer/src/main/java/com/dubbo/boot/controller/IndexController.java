package com.dubbo.boot.controller;


import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.dubbo.boot.service.OrderService;
import com.dubbo.boot.service.UserService;
import com.dubbo.boot.service.VipUserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Controller
public class IndexController implements ApplicationContextAware {
    private ApplicationContext context;

    //提示波浪线属于正常情况
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private VipUserService vipUserService;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");
        String userView = userService.getDetail(id);
        String orderView = orderService.getDetail(id);
        String rtn =vipUserService.getVipDetail(id);//异步方法调用时返回值为null，必须通过future.get()获取返回值
        Future<String> future = RpcContext.getContext().getFuture();
        //在调用future.get()之前可以先执行其他业务代码
        try {
            rtn = future.get();//需要的时候get；阻塞状态
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        request.setAttribute("userView", userView);
        request.setAttribute("orderView", orderView);
        return "index";
    }
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(HttpServletRequest request, HttpServletResponse response) {

        String id = "12345";
        String userView = "666";
        String orderView = "666";
        vipUserService.getVipDetail(id);//异步方法调用时返回值为null，必须通过future.get()获取返回值
        request.setAttribute("userView", userView);
        request.setAttribute("orderView", orderView);
        return "index";
    }


    //http://localhost:8099/consumer/check?serviceId=vipUserService
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @ResponseBody
    public String check(HttpServletRequest request, HttpServletResponse response) {
        String serviceId = request.getParameter("serviceId");
        Object checkMsg = serviceId + " is not ready";
        try {
            EchoService echoService = (EchoService) context.getBean(serviceId);
            if ("OK".equals(echoService.$echo("OK"))){
                checkMsg = serviceId + " is OK";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(checkMsg);;
        return checkMsg.toString();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
