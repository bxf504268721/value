package com.value.security;

import com.value.entities.User;
import com.value.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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
        // 获取用户信息
        User user = userService.getUserByName(username);

        // 获取角色信息
        List<String> roleList = new ArrayList<>();
        roleList.add("admin");
        roleList.add("visitor");

        // 权限列表
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // 赋予查询到的角色
        for (String role :
                roleList) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
        }

        // 创建UserDetails对象，设置用户名、密码和权限
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), authorities);

        return userDetails;
    }
}
