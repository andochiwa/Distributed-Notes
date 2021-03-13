package com.github.gmall.service;

import com.github.bean.UserAddress;
import com.github.service.OrderService;
import com.github.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HAN
 * @version 1.0
 * @create 03-14-1:38
 */
@Service
public class OrderServiceImpl implements OrderService {

    @DubboReference
    UserService userServiceImpl;

    public List<UserAddress> initOrder(String userId) {
        List<UserAddress> userAddresses = userServiceImpl.userAddresses(userId);
        userAddresses.forEach(System.out::println);

        return userAddresses;
    }

}
