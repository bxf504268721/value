package com.value.security;

import com.value.entities.User;
import com.value.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
* @Description 用户登入逻辑
* @Author bxf
* @Date   2019/8/8
*/
@Component("securityUserDetails")
public class SecurityUserDetails implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名获取用户信息
        User user = userService.getUserByName(username);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // 获取用户权限

        return null;
    }
}
