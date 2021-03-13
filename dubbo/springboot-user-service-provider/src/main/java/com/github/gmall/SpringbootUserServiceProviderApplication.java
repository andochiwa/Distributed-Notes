package com.github.gmall;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.导入依赖
 *      1.导入dubbo-starter
 *      2.导入dubbo
 */
@EnableDubbo // 开启基于注解的Dubbo功能
@SpringBootApplication
public class SpringbootUserServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootUserServiceProviderApplication.class, args);
    }

}
