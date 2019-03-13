package com.dubbo.boot.controller;

import com.alibaba.dubbo.rpc.service.EchoService;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;


@Controller
public class EchoController implements ApplicationContextAware{
    private ApplicationContext ctx;

    /**
     * 回声测试：扫一遍服务是否都已就绪
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    @ResponseBody
    public String echo(HttpServletRequest request, HttpServletResponse response) {
        String[] serviceIds = new String[]{"orderService","userService","vipUserService"};
        HashMap<String,String> retMap = new HashMap<>();

        Object ret = null;
        for (String id:serviceIds){
            try {
                EchoService echoService = (EchoService)ctx.getBean(id);
                ret = echoService.$echo("ok");
                retMap.put(id,ret.toString());
            } catch (Exception e) {
                retMap.put(id,"not ready");
            }
        }
        return retMap.toString();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
