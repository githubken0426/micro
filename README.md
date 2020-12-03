# MicroServices
1、本地模拟方式：修改系统host:C:\Windows\System32\drivers\etc
2、追加
	127.0.0.1 server-light-snow
	127.0.0.1 server-great-snow
# 一、Eureka包含server和client两个基本组件
1.Eureka 是一种基于REST服务，主要用在AWS云中，用来提供负载均衡和中间层服务的故障转移，我们把这种REST服务称作Eureka server。
  Eureka 还包含一个基于Java的客户端，可以用来更好的server交互，Eureka client包含一个內建的基于round-robin（轮询）的负载均衡策略。

2.Eureka client应用被称作一个instance。Eureka client应用和Eureka client也有着细微的差别，前者指的是我们自己的应用程序，后者代表的是框架提供的一个组件。

3.Eureka 具有很灵活的动态配置特性。既有用来设置初始配置的属性，也有用来周期性的检查这些配置是否有变化的配置。其中的大多数配置都可以在运行时修改，并在下一个刷新周期中起作用。例如：Eureka client用来注册到Eureka server的URL可以动态改变，并在5分钟后被识别（eureka.client.eurekaServiceUrlPollIntervalSeconds配置）。

所有的配置属性如下：
Eureka server：所有的配置属性以eureka.server开头
org.springframework.cloud.netflix.eureka.server.EurekaServerConfigBean，此类实现了com.netflix.eureka.EurekaServerConfig。

Eureka client：所有的配置属性以eureka.client开头
org.springframework.cloud.netflix.eureka.EurekaClientConfigBean，此类实现了com.netflix.discovery.EurekaClientConfig。

Eureka instance：所有的配置属性以eureka.instance开头。
org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean，此类间接实现了com.netflix.appinfo.EurekaInstanceConfig。
# 二、Eureka instance
在Eureka中，一个 instance 通过一个 eureka.instance.instanceId 来唯一标识，如果这个值没有设置，就采用eureka.instance.metadataMap.instanceId 来代替。
instance 之间通过 eureka.instance.appName 来彼此访问，在spring cloud中默认值是 spring.application.name ,如果没有设置则为 UNKNOWN。

在实际使用中 spring.application.name 不可或缺, 因为相同名字的应用会被Eureka合并成一个群集（Eureka Server会以双层Map进行存储信息，第一层Map的key 就是服务名， 第二层 Map的 key 就是 instanceId）。

eureka.instance.instanceId 也可以不设置，直接使用缺省值(client.hostname:application.name:port),同一个appName下InstanceId不能相同。属性eureka.instance.virtualHostName目前在spring cloud中目前没有用，默认值是appName或者UNKNOWN。
# 三、Eureka控制台简介
# 1.Eureka控制台HOME
【System Status】 
(1) Environment:环境，默认为test， 该参数在实际使用过程中，可以不用更改。 
(2) Data center： 数据中心，使用的是默认的是 “MyOwn”。 
(3) Current time：当前的系统时间。 
(4) Uptime： 已经运行了多少时间。 
(5) Lease expiration enabled：是否启用租约过期 ， 自我保护机制关闭时，该值默认是true， 自我保护机制开启之后为false。 
(6) Renews threshold： 每分钟最少续约数。  
(7) Renews (last min)： 最后一分钟的续约数量（不含当前，1分钟更新一次）。
# 2.DS Replicas 和 Instances currently registered with Eureka
(1) DS Replicas:表示这个地址是这个Eureka Server相邻节点，互为一个集群。 
(2) Instances currently registered with Eureka:表示各个微服务注册到这个服务上的实例信息。 
# 2.1.Eureka自我保护机制关闭(eureka.server.enable-self-preservation: false)
RENEWALS ARE LESSER THAN THE THRESHOLD. THE SELF PRESERVATION MODE IS TURNED OFF.THIS MAY NOT PROTECT INSTANCE EXPIRY IN CASE OF NETWORK/OTHER PROBLEMS.
【Renewals低于Threshold。自保存模式被关闭。这可能不能保护实例在网络/其他问题的情况下过期】
# 2.2.Eureka自我保护机制关闭, 但一分钟内的续约数没有达到85%(可能发生了网络分区)
THE SELF PRESERVATION MODE IS TURNED OFF.THIS MAY NOT PROTECT INSTANCE EXPIRY IN CASE OF NETWORK/OTHER PROBLEMS.
【自保存模式被关闭。这可能不能保护实例在网络/其他问题的情况下过期】
# 2.3.Eureka自我保护机制开启(eureka.server.enable-self-preservation: true）
EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY’RE NOT. RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUST TO BE SAFE.
【Emergency!Eureka可能是错误地声称实例已经启动，而实际上它们还没有启动。Renewals低于Threshold，因此为了安全起见，实例不会过期】
# 四、General Info
(1) total-avail-memory : 总共可用的内存。 
(2) environment : 环境名称，默认test。 
(3) num-of-cpus : CPU的个数。 
(4) current-memory-usage : 当前已经使用内存的百分比。 
(5) server-uptime : 服务启动时间。 
(6) registered-replicas : 相邻集群复制节点。 
(7) unavailable-replicas ：不可用的集群复制节点，如何确定不可用？ 主要是server1 向 server2和server3... 发送接口查询自身的注册信息，
如果查询不到，则默认为不可用 ， 也就是说如果Eureka Server自身不作为客户端注册到上面去，则相邻节点都会显示为不可用。
(8) available-replicas ：可用的相邻集群复制节点。 
# 五、Eureka自我保护机制
默认情况下，如果Eureka Server在一定时间内（默认90秒eureka.instance.lease-expiration-duration-in-seconds: 90 ）没有接收到某个微服务实例的心跳，
Eureka Server将会移除该实例。但是当网络分区故障发生时，微服务与Eureka Server之间无法正常通信，而微服务本身是正常运行的，此时不应该移除这个微服务，所以引入了自我保护机制。

可以在eureka管理界面看到Renews threshold和Renews(last min)，当后者（最后一分钟收到的心跳数）小于前者（心跳阈值）的时候，触发保护机制，会出现红色的警告：
EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY’RE NOT. RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUST TO BE SAFE。 

Eureka认为虽然收不到实例的心跳，但它认为实例还是健康的，Eureka会保护这些实例，不会把它们从注册表中删掉。 

# 注意：
自我保护机制的目的是避免网络连接故障。在发生网络故障时，微服务和注册中心之间无法正常通信，但服务本身是健康的，不应该注销该服务。 如果eureka因网络故障而把微服务误删了，那即使网络恢复了，该微服务也不会重新注册到eureka server了，因为只有在微服务启动的时候才会发起注册请求，后面只会发送心跳和服务列表请求，这样的话，该实例虽然是运行着，但永远不会被其它服务所感知。 所以，eureka server在短时间内丢失过多的客户端心跳时，会进入自我保护模式，该模式下，eureka会保护注册表中的信息，不在注销任何微服务，当网络故障恢复后，eureka会自动退出保护模式。

但是我们在开发测试阶段，需要频繁地重启发布，如果触发了保护机制，则旧的服务实例没有被删除，这时请求有可能跑到旧的实例中，而该实例已经关闭了，这就导致请求错误，影响开发测试。所以，在开发测试阶段，我们可以把自我保护模式关闭。

但在生产环境，不会频繁重启，所以，一定要把自我保护机制打开，否则网络一旦中断，就无法恢复。自我保护模式可以让集群更加健壮。

# 六、region
当设置region,availability-zones时候，当保护机制开启时候，此时失效的服务还会再注册中心中被发现，导致相同zone的服务调用方还在尝试调用此zone的服务，会出现java.net.ConnectException: Connection refused: connect

考虑设置（开发环境）：
eureka.client.filter-only-up-instances: true ,
eureka.server.eviction-interval-timer-in-ms: 10000 ,
eureka.server.enable-self-preservation: false 

# 七、Spring Cloud Ribbon
7.1 Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端负载均衡的工具。它是一个基于HTTP和TCP的客户端负载均衡器。它可以通过在客户端中配置ribbonServerList来设置服务端列表去轮询访问以达到均衡负载的作用。

7.2 Ribbon与Eureka联合使用时，ribbonServerList会被DiscoveryEnabledNIWSServerList重写，扩展成从Eureka注册中心中获取服务实例列表。同时它也会用NIWSDiscoveryPing来取代IPing，它将职责委托给Eureka来确定服务端是否已经启动。

7.3 Ribbon与Consul联合使用时，ribbonServerList会被ConsulServerList来扩展成从Consul获取服务实例列表。同时由ConsulPing来作为IPing接口的实现。
# 八、Spring Cloud Feign
8.1 Spring Cloud Feign是一套基于Netflix Feign实现的声明式服务调用客户端。

8.2 Feign使得编写Web服务客户端变得更加简单。我们只需要通过创建接口并用注解来配置它既可完成对Web服务接口的绑定。

8.3 Feign具备可插拔的注解支持，包括Feign注解、JAX-RS注解。

8.4 Feign也支持可插拔的编码器和解码器。Spring Cloud Feign还扩展了对Spring MVC注解的支持，同时还整合了Ribbon和Eureka来提供均衡负载的HTTP客户端实现。

8.5 Feign调用报错The bean 'XXX.FeignClientSpecification', defined in null, could not be registered:
把多个消费者放在一个控制层的包的里的时候会包这个错，那是因为，多个同名的消费者早在注册中心都是同名注册的，
所以需要配置spring.main.allow-bean-definition-overriding=true
# 九、Spring Cloud Config
Spring Cloud Config是Spring Cloud团队创建的一个全新项目，用来为分布式系统中的基础设施和微服务应用提供集中化的外部配置支持，它分为服务端与客户端两个部分。

其中服务端也称为分布式配置中心，它是一个独立的微服务应用，用来连接配置仓库并为客户端提供获取配置信息、加密/解密信息等访问接口；
而客户端则是微服务架构中的各个微服务应用或基础设施，它们通过指定的配置中心来管理应用资源与业务相关的配置内容，并在启动的时候从配置中心获取和加载配置信息。

Spring Cloud Config实现了对服务端和客户端中环境变量和属性配置的抽象映射，所以它除了适用于Spring构建的应用程序之外，也可以在任何其他语言运行的应用程序中使用。由于Spring Cloud Config实现的配置中心默认采用Git来存储配置信息，所以使用Spring Cloud Config构建的配置服务器，天然就支持对微服务应用配置信息的版本管理，并且可以通过Git客户端工具来方便的管理和访问配置内容。当然它也提供了对其他存储方式的支持，比如：SVN仓库、本地化文件系统。

/{application}/{profile}[/{label}]

/{application}-{profile}.yml(properties)

/{label}/{application}-{profile}.yml(properties)

`@RefreshScope实现自动刷新`

Spring Cloud Config 在项目启动时加载配置内容这一机制，导致了它存在一个缺陷，修改配置文件内容后，不会自动刷新。但它提供了一个刷新机制，需要我们主动触发。那就是 @RefreshScope 注解并结合 actuator ，注意要引入 spring-boot-starter-actuator 包。
经由@RefreshScope修饰的bean将会被RefreshScope代理，RefreshScope代理的bean强制为懒加载，只有在第一次使用的时候才会生成实例，当其需要刷新配置的时候直接调用destory()方法销毁当前bean，这样在刷新配置后在需要生成的bean已经是根据新的配置信息生成，完成bean的热加载。

# 十、bootstrap.yml（.properties）与application.yml（.properties）执行顺序
为何需要把 config server 的信息放在 bootstrap.yml 里？
技术上，bootstrap.yml 是被一个父级的 Spring ApplicationContext 加载的。这个父级的 Spring ApplicationContext是先加载的，在加载application.yml的 ApplicationContext之前。
当使用 Spring Cloud的时候，配置信息一般是从 config server加载的，为了取得配置信息（比如密码等），你需要一些提早的引导配置。因此，把 config server信息放在 bootstrap.yml，用来加载在这个时期真正需要的配置信息。

10.1 bootstrap.yml 先于 application.yml 加载。

10.2 bootstrap.yml（.properties）用来在程序引导时执行，应用于更加早期配置信息读取，如可以使用来配置application.yml中使用到参数等

10.3 application.yml（.properties)应用程序特有配置信息，可以用来配置后续各个模块中需使用的公共参数等。
Spring Cloud会创建一个`Bootstrap Context`，作为Spring应用的`Application Context`的父上下文。初始化的时候，`Bootstrap Context`负责从外部源加载配置属性并解析配置。这两个上下文共享一个从外部获取的`Environment`。`Bootstrap`属性有高优先级，默认情况下，它们不会被本地配置覆盖。 `Bootstrap context`和`Application Context`有着不同的约定，所以新增了一个`bootstrap.yml`文件，而不是使用`application.yml` (或者`application.properties`)。保证`Bootstrap Context`和`Application Context`配置的分离。
可以通过设置`spring.cloud.bootstrap.enabled=false`来禁用`bootstrap`。

