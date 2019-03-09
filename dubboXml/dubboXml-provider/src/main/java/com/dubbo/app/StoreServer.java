package com.dubbo.app;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StoreServer {
    public static void main(String[] args)   {
        /**
         * dubbo.xml
         * dubbo_annotation.xml
         */
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:dubbo.xml");
        context.start();

        System.out.println("-----dubbo开启-----");

//        System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
    }
}
