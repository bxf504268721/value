package com.value.service;

import com.value.entities.Role;

import java.util.List;
/**
* @Description 角色信息service
* @Author bxf
* @Date   2019/8/11
*/
public interface RoleService {
    /** 根据用户名获取用户角色*/
    List<Role> listByUsername(String username);
}
