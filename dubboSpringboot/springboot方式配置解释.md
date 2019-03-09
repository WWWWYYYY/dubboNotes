dubbo结合springboot》后续使用 dubbo-spring-boot-starter


开发步骤
1、pom导入springboot依赖、dubbo和zk依赖和业务接口api依赖；
2、编辑application.yml配置zookeeper地址和dubboName
3、编辑DubboConfiguration.java 定义ApplicationConfig、RegistryConfig、ProtocolConfig
4、编辑BootApp.java启动类使用@ComponentScan(basePackages = "com.dubbo")扫描要暴露的接口和需要注入的rpc bean
5、编辑IndexController.java获取 rpc bean


ps:启动项目前，先启动dubboAannotation-provider工程下的com.dubbo.app.StoreServer启动类
该工程说明一个项目几个是消费者也是个生产者。


#启动: nohup java   -Dspring.profiles.active=dev -jar synchronius-1.0.jar >  log.erp 2>&1 &
