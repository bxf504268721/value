package com.value.security;

import com.value.entities.Role;
import com.value.entities.User;
import com.value.service.RoleService;
import com.value.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
* @Description 用户登入权限控制
* @Author bxf
* @Date   2019/8/8
*/
@Component("securityUserDetails")
public class SecurityUserDetails implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取用户信息
        User user = userService.getUserByName(username);

        // 获取角色信息
        List<Role> roleList = roleService.listByUsername(username);

        // 权限列表
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // 赋予查询到的角色
        for (Role role :
                roleList) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getAuth());
            authorities.add(authority);
        }

        // 创建UserDetails对象，设置用户名、密码和权限
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
                new BCryptPasswordEncoder().encode(user.getPassword()), authorities);

        return userDetails;
    }
}
