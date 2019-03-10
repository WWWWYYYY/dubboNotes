package com.dubbo.boot;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;


@CrossOrigin//允许跨越访问
@SpringBootApplication
@DubboComponentScan(basePackages = "com.dubbo.boot")
@ComponentScan(basePackages = "com.dubbo.boot")
@EnableDubboConfiguration
public class BootApp {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(BootApp.class, args);
//        System.in.read();
    }
}
