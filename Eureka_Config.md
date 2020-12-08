# Eureka 配置参数说明
#  一、Eureka Client 配置项（eureka.client.*）
org.springframework.cloud.netflix.eureka.EurekaClientConfigBean

`service-url:` 指定服务注册中心地址，类型为 HashMap，并设置有一组默认值，默认的Key为 defaultZone；默认的Value为http://username:password@localhost:8761/eureka

`prefer-same-zone-eureka:` `true` 实例是否使用同一zone里的eureka服务器

`filter-only-up-instances:` `true` 获取实例时是否过滤，只保留UP状态的实例

`fetch-registery:` `true` 检索服务；false不会向Eureka Server注册中心获取注册信息

`register-with-eureka:` `true` 实例是否应将其信息注册到eureka服务器以供其他服务发现

`registery-fetch-interval-seconds:` `30` 从Eureka服务器端获取注册信息的间隔时间(s)

`registry-fetch-interval-seconds:` `30` 指示从eureka服务器获取注册表信息的频率（s） 

`registry-refresh-single-vip-address:` `null` 指示客户端是否仅对单个VIP的注册表信息感兴趣 

`instance-info-replication-interval-seconds:` `30` 更新实例信息的变化到Eureka服务端的间隔时间（s） 

`initial-instance-info-replication-interval-seconds:` `40` 初始化实例信息到Eureka服务端的间隔时间（s） 

`eureka-service-url-poll-interval-seconds:` `300` 询问Eureka Server信息变化的时间间隔（s） 

`eureka-server-read-timeout-seconds:` `8` 读取Eureka Server 超时时间（s） 

`eureka-server-connect-timeout-seconds:` `5` 连接Eureka Server 超时时间（s） 

`eureka-server-total-connections:` `200` 获取从eureka客户端到所有eureka服务器的连接总数 

`eureka-server-total-connections-per-host:` `50` 获取从eureka客户端到eureka服务器主机允许的连接总数 

`eureka-connection-idle-timeout-seconds:` `30` 连接到 Eureka Server 空闲连接的超时时间（s） 

`heartbeat-executor-thread-pool-size:` `2` 心跳保持线程池初始化线程数 

`heartbeat-executor-exponential-back-off-bound:` `10` 心跳超时重试延迟时间的最大乘数值 

`use-dns-for-fetching-service-urls:` `false` eureka客户端是否应使用DNS机制来获取要与之通信的eureka服务器列表。当DNS名称更新为具有其他服务器时，eureka客户端轮询eurekaServiceUrlPollIntervalSeconds中指定的信息后立即使用该信息 
#  二、Eureka Instance 配置项（eureka.instance.*）
org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean

`instance-id:` Get the unique Id (within the scope of the appName) of this instance to be registered with eureka

`ip-address:` IP地址

`prefer-ip-address:` `false` 不使用主机名来定义注册中心的地址，而使用IP地址的形式，如果设置了`ip-address`，则使用该属性配置的IP，否则自动获取除环路IP外的第一个IP地址

`hostname:` 设置当前实例的主机名称

`appname:` `unknown` 服务名，默认取 spring.application.name 配置值，如果没有则为 unknown

`lease-renewal-interval-in-seconds:` `30` 定义服务续约任务（心跳）的调用间隔，单位：秒

`lease-expiration-duration-in-seconds:` `90` 定义服务失效的时间，单位：秒

`status-page-url-path:` `/info` 状态页面的URL，相对路径，默认使用 HTTP 访问，如果需要使用 HTTPS则需要使用绝对路径配置

`health-check-url-path:` `/health` 健康检查页面的URL，相对路径，默认使用 HTTP 访问，如果需要使用 HTTPS则需要使用绝对路径配置
#  三、Eureka Server 配置项（eureka.server.*）
org.springframework.cloud.netflix.eureka.server.EurekaServerConfigBean

`peer-node-read-timeout-ms:` `200` 节点之间数据读取超时时间（ms）

`enable-self-preservation:` `true` 自我保护机制

`eviction-interval-timer-in-ms:` `600000` 清除无效服务实例的时间间隔（ms），默认1分钟

