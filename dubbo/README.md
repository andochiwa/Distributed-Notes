# 负载均衡(LoadBalance)

dubbo内置了四种负载均衡算法

1. `RandomLoadBalance`随机负载均衡。随机的选择一个。是Dubbo的**默认**负载均衡策略。
2. `RoundRobinLoadBalance`轮询负载均衡。轮询选择一个。
3. `LeastActiveLoadBalance`最少活跃调用数，相同活跃数的随机。活跃数指调用前后计数差。使慢的 Provider 收到更少请求，因为越慢的 Provider 的调用前后计数差会越大。
4. `ConsistentHashLoadBalance`一致性哈希负载均衡。相同参数的请求总是落在同一台机器上。



# 服务降级

> 当服务器压力剧增时，根据实际业务情况以及流量，对一些服务和页面有策略的不处理或换种简单的处理方式，从而释放服务器资源以保证核心服务器正常或高效率运作

* `mock=force:return+null`表示消费方对该服务的方法调用都放回`null`值，不发起远程调用。用来屏蔽不重要服务不可用时对调用方的影响
* `mock=fail:return+null` 表示消费方对该服务的方法调用在失败后再返回`null` 值，不抛异常，用来**容忍**不重要服务不稳定时对调用方的影响



# 集群容错(Cluster fault tolerance)

> 在集群调用失败后，服务框架需要能够在底层自动容错，调用其容错方案

Dubbo提供了多种集群容错方式，默认为Failover重试

* `Failover Cluster` 失败自动切换，出现失败时重试其他服务器，通常用于读操作，重试会带来延迟，可以通过`retries=2`设置重试次数
* `Failfast Cluster` 快速失败，只发起一次调用，失败立即报错，通常用于**非幂等性**的写操作
* `Failsafe Cluster` 失败安全，出现异常时忽略，通常用于写于审计日志等操作
* `Forking Cluster` 并行调用多个服务器，只要有一个成功就返回。通常用于实时性较高的读操作，但更浪费资源，可以通过`forks=2`来设置最大并行数
* `Broadcast Cluster` 广播调用所有提供者，逐一调用，任意一台出错就报错。通常用于通知所有提供者更新缓存或日志等本地信息

开发中通常整合hystrix来实现集群容错