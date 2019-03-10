package com.dubbo.boot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.boot.service.OrderService;
import com.dubbo.boot.service.ProductService;
import com.dubbo.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
public class IndexController {
    //=true时如果没有生产者则无法启动应用
    @Reference(check = true)
    private UserService userService;

    @Reference(check = true)
    private OrderService orderService;
    /*实现类中使用了com.alibaba.dubbo.config.annotation.Service修饰，可以暴露接口，也可以在本地使用
    * 在当前工程中使用该实例可以使用@Autowired，但是idea可能提示波浪线
    * */
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Map index(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");
        String userView = userService.getDetail(id);
        String orderView = orderService.getDetail(id);
        String productView = productService.getDetail(id);

        Map map = new HashMap<>();
        map.put("userView", userView);
        map.put("orderView", orderView);
        map.put("productView", productView);
        return map;
    }

}
