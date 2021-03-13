package com.github.service;

import com.github.bean.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  1.将服务提供者注册到注册中心（暴露服务）
 *       1.导入dubbo依赖，以及zookeeper的客户端
 *       2.配置服务提供者
 *  2.让服务消费者去注册中心订阅提供者的服务地址
 *
 * @author HAN
 * @version 1.0
 * @create 03-14-1:38
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    UserService userServiceImpl;

    public void initOrder(String userId) {
        List<UserAddress> userAddresses = userServiceImpl.userAddresses(userId);
        userAddresses.forEach(System.out::println);

    }

}
