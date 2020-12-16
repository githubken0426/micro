# ZUUL 说明
#  一、zuul的工作原理
`过滤器机制`

zuul 是netflix开源的一个API Gateway 服务器, 本质上是一个web servlet应用。
zuul的核心是一系列的filters, 其作用可以类比Servlet框架的Filter，或者AOP。
zuul把Request route到 用户处理逻辑 的过程中，这些filter参与一些过滤处理，比如`Authentication`，`LoadShedding`等。

Zuul 内部提供了一个动态读取、编译和运行这些 Filter 的机制。Filter 之间不直接通信，在请求线程中会通过 RequestContext 来共享状态，它的内部是用 ThreadLocal 实现的，当然你也可以在 Filter之间使用 ThreadLocal 来收集自己需要的状态或数据。

Zuul的过滤器是由Groovy写成，这些过滤器文件被放在Zuul Server上的特定目录下面，Zuul会定期轮询这些目录，修改过的过滤器会动态的加载到Zuul Server中以便过滤请求使用。


`1.1	标准的过滤器：` Zuul大部分功能都是通过过滤器来实现的。Zuul中定义了四种标准过滤器类型，这些过滤器类型对应于请求的典型生命周期。

(1) `pre` ：在请求被路由之前调用。如果需要对请求进行预处理，比如鉴权、限流等，都应考虑在此类 Filter 实现。

(2) `route` ：路由动作的执行者，将请求路由到微服务。这种过滤器用于构建发送给微服务的请求，并使用Apache HttpClient或Netfilx Ribbon请求微服务。

(3) `post` ：在源服务返回结果或者异常信息发生后执行。如果需要对返回信息做一些处理，则在此类 Filter 进行处理，为响应添加标准的HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等。

(4) `error` ：在整个生命周期内如果发生异常，则会进入 error Filter，可做全局异常处理。

`1.2	内置的特殊过滤器：` zuul还提供了一类特殊的过滤器，分别为：StaticResponseFilter和SurgicalDebugFilter。

StaticResponseFilter：StaticResponseFilter允许从Zuul本身生成响应，而不是将请求转发到源。

SurgicalDebugFilter：SurgicalDebugFilter允许将特定请求路由到分隔的调试集群或主机。

`1.3	自定义过滤器：` 除了默认的过滤器类型，Zuul还允许我们创建自定义的过滤器类型。例如，我们可以定制一种STATIC类型的过滤器，直接在Zuul中生成响应，而不将请求转发到后端的微服务。

`1.4	禁用/替换 原生过滤器：` 
如果你不想使用原生的Filter功能，可以采取替代实现的方式，覆盖掉其原生代码; 

也可以采取禁用策略，语法如下： zuul.`<SimpleClassName>`.`<flterType>`.disable=true  比如要禁用 SendErrorFilter，在配置文件中添加 zuul.`SendErrorFilter`.`error`.disable=true即可。
# 二、zuul提供的功能
Zuul可以通过加载动态过滤机制，实现以下功能：

`2.1	验证与安全保障：` 识别面向各类资源的验证要求并拒绝那些与要求不符的请求。

`2.2	审查与监控: `在边缘位置追踪有意义数据及统计结果，从而为我们带来准确的生产状态结论。

`2.3	动态路由：` 以动态方式根据需要将请求路由至不同后端集群处。

`2.4	压力测试：` 逐渐增加指向集群的负载流量，从而计算性能水平。

`2.5	负载分配：` 为每一种负载类型分配对应容量，并弃用超出限定值的请求。

`2.6	静态响应处理：` 在边缘位置直接建立部分响应，从而避免其流入内部集群。

`2.7	多区域弹性：` 跨越AWS区域进行请求路由，旨在实现ELB使用多样化并保证边缘位置与使用者尽可能接近。

除此之外，Netflix还利用Zuul的功能通过金丝雀版本实现 `精确路由` 与 `压力测试`。
