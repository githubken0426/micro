package org.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 断路器是根据一段时间窗内的请求情况来判断并操作断路器的打开和关闭状态的。
 * 而这些请求情况的指标信息都是HystrixCommand和HystrixObservableCommand实例在执行过程中记录的重要度量信息，
 * 它们除了Hystrix断路器实现中使用之外，对于系统运维也有非常大的帮助。
 * 这些指标信息会以“滚动时间窗”与“桶”结合的方式进行汇总，并在内存中驻留一段时间，以供内部或外部进行查询使用，HystrixDashboard就是这些指标内容的消费者之一。
 * 1、	http://hystrix-dashboard:4001/hystrix
 * 2、	访问http://consumer-ribbon:3001/actuator/，将返回的http://consumer-ribbon:3001/actuator/hystrix.stream写入Hystrix Dashboard页面表单中。
 * 3、	Hystrix监控数据聚合同上，将 http://hystrix-dashboard-turbine:4002/turbine.stream填写到Hystrix Dashboard页面表单中。
 * @author kun.f.wang
 */
@EnableHystrixDashboard
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class HystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardApplication.class, args);
	}
}
