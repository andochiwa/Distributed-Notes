package com.github.gmall;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.导入依赖
 *      1.导入dubbo-starter
 *      2.导入dubbo
 *
 * 2.dubbo与springboot整合的三种方式
 *      1.导入dubbo-starter，在application.yml中配置属性，使用@DubboService暴露服务，@Reference引用服务
 *      2.保留dubbo配置文件（麻烦）
 *      3.使用注解api写一个配置类
 */
@EnableDubbo // 开启基于注解的Dubbo功能
@SpringBootApplication
public class SpringbootUserServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootUserServiceProviderApplication.class, args);
    }

}
