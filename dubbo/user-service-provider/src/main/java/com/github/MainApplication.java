package com.github;

import lombok.SneakyThrows;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author HAN
 * @version 1.0
 * @create 03-14-2:16
 */
public class MainApplication {

    @SneakyThrows
    public static void main(String[] args){
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("provider.xml");
        ioc.start();

        System.in.read();
    }

}
