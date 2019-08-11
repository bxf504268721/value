package com.value.service;

import com.value.entities.User;
/**
* @Description 用户service
* @Author bxf
* @Date   2019/8/11
*/
public interface UserService {
    /** 通过用户名获取用户*/
    User getUserByName(String username);
}
