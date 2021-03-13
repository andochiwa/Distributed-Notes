package com.github.service;

import com.github.bean.UserAddress;

import java.util.List;

/**
 *
 * @author HAN
 * @version 1.0
 * @create 03-14-1:46
 */
public interface OrderService {

    public List<UserAddress> initOrder(String userId);

}
