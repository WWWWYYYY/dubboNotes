package com.dubbo.app;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class StoreServer {
    public static void main(String[] args) throws IOException {
        /**
         * dubbo-annotation.xml
         * dubbo_annotation.xml
         */
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:dubbo-annotation.xml");
        context.start();

        System.out.println("-----dubbo开启-----");

        System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
    }
}
