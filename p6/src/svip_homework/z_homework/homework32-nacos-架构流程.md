1 请按照安装文档安装好集群Nacos，并且完成一个多环境配置demo！<br>
2 请你说下Nacos作为配置中心，主要的实现流程（语音作业）<br>

1 请按照安装文档安装好集群Nacos，并且完成一个多环境配置demo！<br>

```text

```

2 请你说下nacos作为配置中心，主要的实现流程（语音作业）<br>

```text
一、springboot项目启动时候进行加载配置
  1 springboot启动run方法
  2 run方法里面有监听环境事件
       该方法从META-INF/spring.factories下去找BootstrapConfiguration的配置【PropertySourceBootstrapConfiguration.class】
       调用PropertySourceBootstrapConfiguration的initialize 里面有个重要方法，去拿配置 source =locator.locateCollection(environment)
       里面调用locator.locate(environment);走到实现类NacosPropertySourceLocator
       取需要加载的配置，自身配置文件会默认取${spring.application.name}+指定后缀的配置
       多配置文件的话会按照 //加载共享配置//加载扩展配置//加载自身配置 顺序加载，后面的值会覆盖前面的值
       并把值存入CHM中
二、运行过程中进行实时刷新
    META-INF/spring.factories里面配置的NacosConfigBootstrapConfiguration.class
    里面会加载NacosConfigManager 加载的时候会反射初始化ConfigService //实例化NacosConfigService Class.forName("com.alibaba.nacos.client.config.NacosConfigService"); 
    //拿到NacosConfigService的有参构造函数 ，构造函数会起一个 worker = new ClientWorker(agent, configFilterChainManager, properties);
    
    ClientWorker里面有个定时任务一直在 执行完后延时10ms进行再次进入任务【scheduleAtFixedRate 固定频率】
     executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    checkConfigInfo();
                } catch (Throwable e) {
                    LOGGER.error("[" + agent.getName() + "] [sub-check] rotate check error", e);
                }
            }
        }, 1L, 10L, TimeUnit.MILLISECONDS);
    
    循环里面有个长连接一直在等待与服务端通信的get请求返回 如果有改动里面就返回哪些改动 get请求 /v1/cs/configs/listener
    
   
    //没有变化直接返回，如果有变化，需要通知监听器 
    通过检测到改动过的key去重新从配置中心拿取最新的值
    String content = getServerConfig(dataId, group, tenant, 3000L);  get请求 /v1/cs/configs
    
```