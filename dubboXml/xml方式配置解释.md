dubboXml 子工程表示xml方式配置dubbo
    -dubboXml-api 表示 提供的接口，由生产者提供给消费者 
    -dubboXml-provider 表示 生产者工程
    -dubboXml-consumer 表示 消费者工程

一、生产者开发步骤
1、编辑pom文件；引入dubboXml-api、spring、zookeeper、dubbo
2、实现dubboXml-api中的接口
3、编辑dubbo.xml、dubbo.properties；dubbo.xml优先级高于dubbo.properties；dubbo.properties文件默认被加载；
    <context:component-scan base-package="com.dubbo.dao"/>  spring bean扫描 对应的 org.springframework.stereotype.Service
    <bean id="orderService" class="com.dubbo.service.impl.OrderServiceImpl"/> ps：orderService必须要在当前xml中定义，不能使用包扫描的方式查找orderService，不然找不到orderService bean
    <dubbo:service interface="com.dubbo.service.OrderService" ref="orderService" protocol="dubbo"> 暴露服务接口
4、com.dubbo.app.StoreServer启动类

二、消费者开发步骤

1、编辑pom文件；引入dubboXml-api、spring、zookeeper、dubbo
2、编辑dubbo.xml、dubbo.properties；
     <dubbo:reference id="orderService" interface="com.dubbo.service.OrderService" > ioc加载orderService bean
3、代码引用
    @Autowired
    private OrderService orderService;
    
    
ps：该方式使用的spring ioc 管理service bean
    
