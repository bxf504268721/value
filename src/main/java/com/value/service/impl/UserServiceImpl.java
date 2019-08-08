package com.value.service.impl;

import com.value.entities.User;
import com.value.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public User getUserByName(String username) {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("admin");
        user.setNickname("admin");
        user.setPassword("admin");
        user.setLevel("1");
        return user;
    }
}
