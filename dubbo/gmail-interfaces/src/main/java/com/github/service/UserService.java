package com.github.service;

import com.github.bean.UserAddress;

import java.util.List;

/**
 * @author HAN
 * @version 1.0
 * @create 03-14-1:43
 */
public interface UserService {

    /**
     * 按照用户id返回所有收货地址
     */
    public List<UserAddress> userAddresses(String userId);
}
