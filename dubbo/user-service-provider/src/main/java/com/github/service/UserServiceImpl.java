package com.github.service;

import com.github.bean.UserAddress;

import java.util.Arrays;
import java.util.List;

/**
 * @author HAN
 * @version 1.0
 * @create 03-14-1:30
 */
public class UserServiceImpl implements UserService {

    /**
     * 按照用户id返回所有收货地址
     */
    public List<UserAddress> userAddresses(String userId) {
        UserAddress address1 = new UserAddress(1, "beijing", "1", "lisi", "1234", "Y");
        UserAddress address2 = new UserAddress(2, "shenzhen", "2", "wangwu", "6789", "Y");
        return Arrays.asList(address1, address2);
    }

}
