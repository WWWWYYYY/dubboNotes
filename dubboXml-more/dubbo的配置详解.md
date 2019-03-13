
例子：消费者配置
    <dubbo:consumer  cluster="failover" retries="2" loadbalance="random" timeout="1000" check="false"/>
    <dubbo:reference id="orderService" interface="com.dubbo.boot.service.OrderService" loadbalance="roundrobin"   cluster="failover" retries="2" timeout="1000"   />
    
    
1、超时配置：当一个请求超时后会发送至下一个服务器，请求发送起始时间重置为0后再计算。超时配置使用timeout属性。

2、集群容错配置：使用cluster="failover" retries="2" 两种属性，cluster表示策略，retries表示尝试次数，不包含第一次
缺省为 failover 重试，自动切换其它服务器。
其它(Failfast/ Failsafe/ Failback);其中常用的策略为Failover、Failfast
①、 Failover ：当出现失败，重试其它服务器。 retries=“2” 来设置重试次数(不含第一次)。（常用）幂等性操作使用，如读操作
②、 Failfast ：快速失败，只发起一次调用，失败立即报错（常用）非幂等性操作，如写操作
③、 Failsafe ：出现异常时，直接忽略无关紧要的旁支操作，如打日志
④、 Failback ：后台记录失败请求，定时重发后续专业处理
⑤、 Forking ：并行调用多个服务器，只要一个成功即返回 forks=“2” 来设置最大并行数

3、负载配置：使用loadbalance；可选值为：
①、 random ：随机
②、 roundrobin ：轮询
③、 leastactive ：最少活跃调用

4、缓存配置：使用标签cache：可选值为：
①、 lru：根据最少使用原则，当缓存空间不够时将最少使用的缓存清除
②、 threadlocal：根据线程信息来缓存数据
例如：生产者示例：当消费方发起请求后，生产者判断同样的参数是否查询过，如果有直接从缓存里取出后返回，如果缓存中没有则执行对应方法。
    <dubbo:service interface="com.dubbo.boot.service.OrderService" ref="orderService" protocol="dubbo">
        <dubbo:method name="getDetail" cache="lru" />
    </dubbo:service>
    消费方示例：当发现参数作为key可以在缓存中取出值时不会发出请求。
    <dubbo:reference id="userService" interface="com.dubbo.boot.service.UserService"  >
        <dubbo:method name="getDetail" cache="lru" />
    </dubbo:reference>
    
    生产者和消费者同一个服务配置可以选择都配置缓存，可以选择某一方进行缓存，看业务需要。缓存要慎用。
    
5、异步调用：
使用场景：当调用的服务比较耗时的情况下使用，不要盲目的使用异步，异步毕竟使用的多线程原理，要消耗更多的资源的。要根据业务的需要来使用异步。
方式一：future模式（官方不推荐）
    步骤1：消费方配置：
        <dubbo:reference id="vipUserService" interface="com.dubbo.boot.service.VipUserService"  >
            <dubbo:method name="getVipDetail" async="true" />//假设getVipDetail是一个耗时方法
        </dubbo:reference>
        
    步骤2：消费方java代码调用：（java中同步转异步使用的future模式）
            String rtn =vipUserService.getVipDetail(id);//异步方法调用时返回值为null，必须通过future.get()获取返回值
            Future<String> future = RpcContext.getContext().getFuture();
            //在调用future.get()之前可以先执行其他业务代码
            rtn = future.get();//需要的时候get；阻塞状态

方式二：异步回调（推荐）
    步骤1：消费方配置
        <dubbo:reference id="vipUserService" interface="com.dubbo.boot.service.VipUserService"  >
            <dubbo:method name="getVipDetail" async="true"  onreturn="callBack.dealResult" onthrow="callBack.error"/>
        </dubbo:reference>
        <bean id="callBack" class="com.dubbo.async.Callback"/>
        自定义一个结果处理bean；onreturn表示回调时调用哪个方法；onthrow表示异常时调用哪个方法
    步骤2：消费方java代码调用：
        vipUserService.getVipDetail(id);//不用时不需要管结果，直接交给回调处理
    
    
6、回声测试：检查项目中依赖的服务是否正常
    java代码：
                    EchoService echoService = (EchoService)ctx.getBean(id);
                    ret = echoService.$echo("ok");
     具体参考com.dubbo.boot.controller.EchoController.java类的echo方法。

7、dubbo泛化调用；dubbo提供的一种紧急情况下非 常规调用的方式，当生产者存在某个接口时并向外暴露，而消费方没有对应的接口api。
（这种场景通常在紧急情况下服务端突然发现业务bug需要新增一个接口提供给外部调用但是却没有对外提供api，做的紧急措施或者其他情况）泛化调用只能用在临时处理；在下一个版本中必须纠正过来。
步骤1：编辑dubbo.xml添加内容：
    <dubbo:reference id="otherService" interface="com.dubbo.boot.service.OtherService" generic="true" />//这段内容可以通过练习生产者开发人员获取；
步骤2：java代码调用
            GenericService genericService = (GenericService) ctx.getBean("otherService");
            Object ret = genericService.$invoke("getDetail", new String[]{"java.lang.String"}, new Object[]{"name"});
    具体代码参考com.dubbo.boot.controller.GenericController.java类
