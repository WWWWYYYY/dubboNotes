package com.dubbo.async;

public class Callback {

    /**
     *
     * @param result 返回值
     * @param from 入参
     */
    public void dealResult(String result,String from){
        System.out.println("入参："+from+"；返回值："+result);
    }
    public void error(Throwable ex,String from){
        ex.printStackTrace();
        System.out.println("入参："+from);
    }
}
