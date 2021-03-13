package com.github;

import com.github.service.OrderService;
import lombok.SneakyThrows;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author HAN
 * @version 1.0
 * @create 03-14-2:34
 */
public class MainApplication {

    @SneakyThrows
    public static void main(String[] args){
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("consumer.xml");

        OrderService orderService = ioc.getBean(OrderService.class);

        orderService.initOrder("1");
        System.out.println("调用结束");

        System.in.read();
    }

}
