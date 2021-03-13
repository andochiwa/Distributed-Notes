package com.github.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author HAN
 * @version 1.0
 * @create 03-14-1:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress implements Serializable {

    private Integer id;
    private String userAddress;
    private String userId;
    private String consignee; // 收货人
    private String phoneNum;
    private String isDefault; // 是否为默认地址 Y-是，N-否

}
