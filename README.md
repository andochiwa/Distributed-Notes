# 分布式系统框架学习笔记 Distributed Systems Framework Study Notes

# 目录

|                              1                               |                              2                               |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
| [dubbo](https://github.com/andochiwa/Distributed-Study/tree/master/dubbo) | [zookeeper](https://github.com/andochiwa/Distributed-Study/tree/master/zookeeper) |



# 分布式系统

> 通俗的来讲，就是一个请求由服务器端的多个服务或系统协同处理完成

与单体架构不同的是，单体架构是一个请求发起jvm调度线程(确切的说是tomcat线程池)分配线程来处理请求直到释放。

而分布式系统是，一个请求是由多个系统共同来协同完成，jvm和环境都可能是独立。

# 特点

* 服务系统的独立，占用的服务器资源减少和占用硬件的成本减少
* 系统的独立维护和部署，耦合度降低，可插拔性
* 系统的架构和技术栈的选择可以变的灵活
* 弹性的部署，不会造成平台因部署造成的瘫痪和停服的状态