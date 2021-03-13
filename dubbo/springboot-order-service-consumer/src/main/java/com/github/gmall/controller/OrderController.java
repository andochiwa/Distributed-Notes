package com.github.gmall.controller;

import com.github.bean.UserAddress;
import com.github.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author HAN
 * @version 1.0
 * @create 03-14-3:24
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/initOrder/{id}")
    public List<UserAddress> initOrder(@PathVariable("id") String userId) {
        System.out.println("hello");
        return orderService.initOrder(userId);
    }

}
